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

## Technologies Used

- Java
- Spring Boot
- JPA (Java Persistence API)
- Hibernate
- POSTMAN
- MySQL
- Swagger
- Maven
- Lombok

##Setup

-Clone the repository: bash Copy code git clone https://github.com/your-username/book-my-show-appication.git

#Database setup:

-Install MySQL. Create a new database named cinemaDb. Update application.properties with your MySQL username and password.

#Build and run the application:

bash Copy code cd library-management-system mvn clean install mvn spring-boot:run

#Access APIs:

Use Postman to test the APIs. Import the provided book-my-show.postman_collection.json file for convenience.

Access Swagger UI at http://localhost:8080/swagger-ui.html for detailed API documentation.

#20+ APIs Writtern:

1.Add Movie API : @PostMapping("addMovie")

2.Update Movie Details API : @PutMapping("updateMovie")

3.Most Liked Movie API : @GetMapping("mostLikedMovie")
 
4.Delete Movie API : @DeleteMapping("deleteMovie")

5.Add Show API : @PostMapping("addShow")

6.Delete Show API : @DeleteMapping("deleteShow")

7.Add Show Seats API : @PostMapping("addShowSeats")

8.Get Shows on Given Date API : @GetMapping("getShowsOnGivenDate")

9.Count of Booked Seats API : @GetMapping("countOfBookedSeats")

10.Add Theater API : @RequestMapping("addTheater")

11.Delete Theater API : @DeleteMapping("deleteTheater")

12.Add Physical Theater Seats API : @PostMapping("addTheaterSeats")

13.Get GrossRevenue of Movie : @GetMapping("grossRevenueOfMovie")

14.Book Ticket API : @PostMapping("bookTicket")

15.Download Movie Ticket API : @GetMapping("viewTicket")

16.Cancel Movie Ticket API : @DeleteMapping("cancelBookedTicket")

17.Add User API : @PostMapping("addUser")

18.Get User Profile API :  @GetMapping("getUserProfile") 

19. Update User Profile API : @PutMapping("updateUser")

20. Delete User API : @DeleteMapping("deleteUser")


