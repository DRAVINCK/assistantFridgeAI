package com.example.assistantFridgeAI.Service;

import com.example.assistantFridgeAI.model.FoodItem;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;


import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
public class AIService {

  private final WebClient webClient;
  private String apiKey = System.getenv("GEMINI_API_KEY");

  public AIService(WebClient webClient) {
    this.webClient = webClient;
  }

  public Mono<String> generateRecipe(List<FoodItem> foodItems) {
    String foods = foodItems.stream()
            .map(item -> String.format("%s (%s) - Quantity: %d, Validade: %s",
                item.getName(),item.getCategory(), item.getQuantity(), item.getExpirationDate()))
            .collect(Collectors.joining("\n"));

    String prompt = "Com base nos seguintes ingredientes, por favor, gere uma receita: " + foods;
    Map<String, Object> requestBody = Map.of(
            "contents", List.of(
                    Map.of(
                            "parts", List.of(
                                    Map.of("text", prompt)
                            )
                    )
            )
    );

    return webClient.post()
            .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
            .header("x-goog-api-key", apiKey)
            .bodyValue(requestBody)
            .retrieve()
            .bodyToMono(Map.class)
            .map(response -> {
                var candidates = (List<Map<String, Object>>) response.get("candidates");

                if (candidates != null && !candidates.isEmpty()) {
                  var content = (Map<String, Object>) candidates.get(0).get("content");

                  if (content != null) {
                    var parts = (List<Map<String, Object>>) content.get("parts");

                    if (parts != null && !parts.isEmpty()) {
                      var text = (String) parts.get(0).get("text");
                      return text;
                    }
                  }
                }
                return "not found recipe";
              });

  }
}
