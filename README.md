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

- Add Movie API : `@PostMapping("addMovie")`
- Update Movie Details API : `@PutMapping("updateMovie")`
- Most Liked Movie API : `@GetMapping("mostLikedMovie")`
- Delete Movie API : `@DeleteMapping("deleteMovie")`
- Add Show API : `@PostMapping("addShow")`
- Delete Show API : `@DeleteMapping("deleteShow")`
- Add Show Seats API : `@PostMapping("addShowSeats")`
- Get Shows on Given Date API : `@GetMapping("getShowsOnGivenDate")`
- Count of Booked Seats API : `@GetMapping("countOfBookedSeats")`
- Add Theater API : `@RequestMapping("addTheater")`
- Delete Theater API : `@DeleteMapping("deleteTheater")`
- Add Physical Theater Seats API : `@PostMapping("addTheaterSeats")`
- Get Gross Revenue of Movie : `@GetMapping("grossRevenueOfMovie")`
- Book Ticket API : `@PostMapping("bookTicket")`
- Download Movie Ticket API : `@GetMapping("viewTicket")` and more...

![Screenshot (1)](https://github.com/rishabh0085/Book-My-Show/assets/52191143/c8c01325-7cf0-41e2-821e-3de5126b63ac)
![Screenshot (2)](https://github.com/rishabh0085/Book-My-Show/assets/52191143/768b7034-ee6a-4016-83c5-cca886a766d2)
