## Quiz Application
**Tech Stack:** Java, Spring Boot, REST API, MySQL

---

### How to Run the Application (API Endpoints)

---

#### 🔹 Question APIs

- **Retrieve all questions from the database**  
  `GET` → `http://localhost:8080/question/allquestions`

- **Add a single question to the database**  
  `POST` → `http://localhost:8080/question/add`

- **Add multiple questions to the database**  
  `POST` → `http://localhost:8080/question/addAll`

- **Retrieve questions by category (e.g., "Java")**  
  `GET` → `http://localhost:8080/question/category/Java`

- **Update a question by ID**  
  `PUT` → `http://localhost:8080/question/update/1`

- **Delete a question by ID**  
  `DELETE` → `http://localhost:8080/question/delete/1`

---

#### 🔹 Quiz APIs

- **Create a quiz with 5 random Java questions**  
  `POST` → `http://localhost:8080/quiz/create?category=Java&numQ=5&title=Java_Quiz_1`

- **Retrieve a quiz by ID**  
  `GET` → `http://localhost:8080/quiz/get/1`

- **Submit quiz responses and get the score**  
  `POST` → `http://localhost:8080/quiz/submit/1`
