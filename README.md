# 🤖 Assistant Fridge AI

[![Java](https://img.shields.io/badge/Java-17-orange.svg)](https://www.oracle.com/java/)
[![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.5.4-green.svg)](https://spring.io/projects/spring-boot)
[![Maven](https://img.shields.io/badge/Maven-3.6+-blue.svg)](https://maven.apache.org/)
[![H2 Database](https://img.shields.io/badge/H2-Database-blue.svg)](http://www.h2database.com/)
[![Gemini AI](https://img.shields.io/badge/Gemini-AI-blue.svg)](https://ai.google.dev/)

> **An intelligent fridge assistant that suggests recipes based on available ingredients**

*This project is also part of my English practice journey in programming projects, combining technical learning with language development.* 🌎📚

## 📖 About the Project

**Assistant Fridge AI** is a REST API built with Spring Boot that allows you to manage food items in a virtual fridge and uses artificial intelligence (Google Gemini AI) to suggest personalized recipes based on registered ingredients.

### ✨ Key Features

- 📝 **Food Management**: Complete CRUD operations for fridge items
- 🗂️ **Categorization**: Category system to organize food items
- 📅 **Expiration Control**: Track expiration dates
- 🤖 **Smart Suggestions**: Recipe generation via Google Gemini AI integration
- 💾 **Data Persistence**: H2 database with Flyway migrations
- 🔄 **Reactive Programming**: WebFlux for external API calls

## 🛠️ Technologies Used

### Backend
- **Java 17** - Main language
- **Spring Boot 3.5.4** - Main framework
- **Spring Data JPA** - Data persistence
- **Spring WebFlux** - Reactive client for external APIs
- **H2 Database** - In-memory database
- **Flyway** - Database version control
- **Lombok** - Boilerplate reduction
- **Maven** - Dependency management

### AI Integration
- **Google Gemini AI** - Recipe generation and cooking suggestions

## 🏗️ Project Architecture

```
src/main/java/com/example/assistantFridgeAI/
├── Controller/          # Presentation layer (REST endpoints)
│   ├── FoodItemController.java
│   └── RecipeController.java
├── Service/            # Business logic layer
│   ├── FoodItemService.java
│   └── AIService.java
├── Repository/         # Data access layer
│   └── FoodItemRepository.java
├── model/             # Domain entities
│   └── FoodItem.java
├── enums/             # Enumerations
│   └── FoodCategory.java
└── config/            # Configuration classes
    └── WebClientConfig.java
```

## 🚀 How to Run

### Prerequisites
- Java 17+
- Maven 3.6+
- Google Gemini API Key

### 1. Clone the repository
```bash
git clone <repository-url>
cd assistantFridgeAI
```

### 2. Set up environment variables
```bash
# Gemini AI Configuration
export GEMINI_API_KEY=your_api_key_here
export GEMINI_API_BASE_URL=https://generativelanguage.googleapis.com/v1beta/models/gemini-pro:generateContent

# Database Configuration (H2 - optional for development)
export DATABASE_URL=jdbc:h2:mem:testdb
export DATABASE_USERNAME=sa
export DATABASE_PASSWORD=
```

### 3. Run the project
```bash
# Via Maven
./mvnw spring-boot:run

# Or compile and run the JAR
./mvnw clean package
java -jar target/assistantFridgeAI-0.0.1-SNAPSHOT.jar
```

### 4. Access the application
- **API Base**: `http://localhost:8080`
- **H2 Console**: `http://localhost:8080/h2-console`

## 📋 API Endpoints

### 🥕 Food Management (`/food`)

| Method | Endpoint | Description |
|--------|----------|-------------|
| `POST` | `/food/create` | Create new food item |
| `GET` | `/food/list` | List all food items |
| `GET` | `/food/list/{id}` | Get food item by ID |
| `PUT` | `/food/updateAll/{id}` | Update food item (complete) |
| `PATCH` | `/food/update/{id}` | Update food item (partial) |
| `DELETE` | `/food/delete/{id}` | Delete food item |

### 🍳 Recipe Generation

| Method | Endpoint | Description |
|--------|----------|-------------|
| `GET` | `/generate` | Generate recipe with available ingredients |

## 📝 Usage Examples

### Create food items
```bash
# Add Tomato
curl -X POST http://localhost:8080/food/create \
  -H "Content-Type: application/json" \
  -d '{
    "name": "Tomato",
    "category": "VEGETABLES",
    "quantity": 5,
    "expirationDate": "2025-12-31"
  }'

# Add Spaghetti Pasta
curl -X POST http://localhost:8080/food/create \
  -H "Content-Type: application/json" \
  -d '{
    "name": "spaghetti pasta",
    "category": "OTHERS",
    "quantity": 2,
    "expirationDate": "2025-12-31"
  }'

# Add Garlic
curl -X POST http://localhost:8080/food/create \
  -H "Content-Type: application/json" \
  -d '{
    "name": "garlic",
    "category": "VEGETABLES",
    "quantity": 5,
    "expirationDate": "2025-12-31"
  }'
```

### List all food items
```bash
curl http://localhost:8080/food/list
```

### Generate recipe
```bash
curl http://localhost:8080/generate
```

**Example Response:**
```markdown
# Receita
## Nome da Receita
Spaghetti al Pomodoro Simples

## Descrição
Um clássico italiano minimalista, onde o frescor e a doçura do tomate se encontram com o aroma intenso do alho, complementando perfeitamente a massa para uma refeição reconfortante e saborosa.

## Ingredientes
* 5 Tomates maduros
* 2 unidades de Spaghetti pasta (equivalente a 2 porções ou embalagens, dependendo do tamanho)
* 3 dentes de Alho

## Modo de Preparo
1. Cozinhe o spaghetti: Em uma panela grande, ferva água (quantidade suficiente para cobrir a massa) com uma pitada de sal (opcional, para temperar a água). Adicione o spaghetti e cozinhe conforme as instruções da embalagem até ficar al dente. Reserve cerca de 1 xícara da água do cozimento antes de escorrer a massa.
2. Prepare o molho de tomate e alho: Enquanto a massa cozinha, lave os tomates e corte-os em cubos pequenos ou amasse-os ligeiramente. Descasque os dentes de alho e pique-os finamente ou amasse-os.
3. Em uma panela ou frigideira grande, adicione os tomates picados/amassados e o alho picado. Leve ao fogo médio.
4. Cozinhe os tomates e o alho por cerca de 8-10 minutos, mexendo ocasionalmente, até que os tomates comecem a desmanchar e formar um molho rústico. Adicione uma pitada de sal (opcional) a gosto para temperar o molho.
5. Incorpore a massa: Adicione o spaghetti escorrido diretamente à panela com o molho de tomate e alho. Misture bem para que a massa seja totalmente envolvida pelo molho.
6. Se o molho estiver muito espesso, adicione um pouco da água do cozimento da massa reservada, uma colher de sopa de cada vez, até atingir a consistência desejada.
7. Sirva imediatamente.
```

## 🗂️ Food Categories

The system supports the following categories:

- `MEAT` - Meat products
- `FRUIT` - Fruits
- `VEGETABLES` - Vegetables and legumes
- `DAIRY` - Dairy products
- `BEVERAGES` - Drinks
- `FROZEN` - Frozen foods
- `GRAINS` - Grains and cereals
- `CONDIMENTS` - Condiments and spices
- `OTHERS` - Other items

## 🏁 Database Structure

### Table: `food_item`
| Field | Type | Description |
|-------|------|-------------|
| `id` | UUID | Unique identifier |
| `name` | VARCHAR(255) | Food item name |
| `category` | VARCHAR(50) | Food category |
| `quantity` | INTEGER | Available quantity |
| `expiration_date` | DATE | Expiration date |
| `created_at` | TIMESTAMP | Creation date |
| `updated_at` | TIMESTAMP | Last update date |

## 🔧 Configuration

### application.properties
```properties
# Database
spring.datasource.url=${DATABASE_URL}
spring.datasource.username=${DATABASE_USERNAME}
spring.datasource.password=${DATABASE_PASSWORD}

# H2 Console (development)
spring.h2.console.enabled=true

# Flyway
spring.flyway.enabled=true
spring.flyway.locations=classpath:db/migrations
```

## 🌟 Features Highlights

### AI-Powered Recipe Generation
The system uses Google Gemini AI to create recipes based on your available ingredients. The AI considers:
- Available quantities
- Expiration dates  
- Food categories
- Multiple recipe suggestions when possible
- Structured output format in Portuguese

### Smart Inventory Management
- Track expiration dates to reduce food waste
- Categorize items for better organization
- UUID-based identification for reliable data integrity
- Reactive programming for better performance

## 🎯 Future Roadmap

- [ ] Authentication and authorization
- [ ] Web frontend interface
- [ ] Expiration notifications
- [ ] Multiple fridge support
- [ ] Recipe rating system
- [ ] Nutritional information
- [ ] Shopping list generation
- [ ] Docker containerization
- [ ] Cloud deployment

## 📚 Learning Notes

*This project serves as both a practical application and an English language learning exercise. By developing in English, I practice technical vocabulary, documentation writing, and code commenting - essential skills for international software development.*

## 🤝 Contributing

1. Fork the project
2. Create your feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

## 📄 License

This project is under the MIT license. See the [LICENSE](LICENSE) file for more details.

## 👨‍💻 Author

**Lucas**
- GitHub: [@Lucas Teixeira](https://github.com/DRAVINCK)
- LinkedIn: [Lucas Teixeira](https://www.linkedin.com/in/lucas-teixeira-20a221207/)

---

⭐ **If you liked this project, please give it a star!** ⭐

*"Cooking is like programming - you need the right ingredients, proper structure, and a pinch of creativity!"* 👨‍🍳💻
