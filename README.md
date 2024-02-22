# About 

Book-My-Show is a Web-based ticket booking platform using Spring Boot, Maven, Lombok, and RESTful APIs.This application comes with intuitive movie selection, secure transactions, and admin theater management capabilities.
• In this Backend application you can book tickets with E-mail Integration, find shows, search-movies and more.

# Features

- Intuitive movie selection
- transactions
- Admin theater management capabilities
- E-mail integration for ticket bookings
- Show discovery functionality
- Movie search feature
- 20+ RESTful APIs for various functionalities

# Technologies Used

- Java
- Spring Boot
- JPA (Java Persistence API)
- Hibernate
- POSTMAN
- MySQL
- Swagger
- Maven
- Lombok

#Setup

-Clone the repository: bash Copy code git clone https://github.com/your-username/book-my-show-appication.git

#Database setup:

-Install MySQL. Create a new database named cinemaDb. Update application.properties with your MySQL username and password.

#Build and run the application:

bash Copy code cd library-management-system mvn clean install mvn spring-boot:run

#Access APIs:

Use Postman to test the APIs. Import the provided book-my-show.postman_collection.json file for convenience.

Access Swagger UI at http://localhost:8080/swagger-ui.html for detailed API documentation.

#20+ APIs Writtern:

- POST /issue-card: Issue a library card to a student.
- POST /associateCardWithStudent: Associate a library card with a student.
- POST /addBook: Add a new book to the library.
- POST /addAuthor: Add a new author to the library system.
- POST /addStudent: Add a new student to the library system.
- POST /return-book: Return a book to the library.
- POST /record-transaction: Record a transaction (e.g., borrowing, returning).
- GET /authors/{id}: Find an author by ID.
- GET /books/{id}: Find a book by ID.
- GET /students/{id}: Find a student by ID.
- POST /generate-library-card: Generate a new library card for a student.
- PUT /update-phone/{studentId}: Update a student's phone number and more...


