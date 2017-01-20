# glarimy-spring-boot
<h1>Illustrating Spring Boot Application with MVC, REST, JDBC, JPA, Security and Integration Test</h1>
#what it illustrates?
1. Developing Spring Boot MVC Application.
2. Exposing REST API
3. Using JPA for resource persistence
4. Using default Form based security
5. Overriding default security authentication
6. Persisting security credentials in database
7. Developing Test cases
8. Expositing image files
9. Consuming REST API using REST Templates

#What to do?
1. Import the project into Eclipse.
2. Edit application.properties appropriately, if needed.
2. Create the database schema 'boot' in MySQL Database.
3. Run mvn clean test from the root folder.
4. Both the tests must pass.
5. Run LibraryApplication from within Eclipse.
6. Visit http://localhost:8080/library/book in the browser. It should return JSON with data of two books
7. Visit http://localhost:8080/library/book/12345 in the browser. It should return JSON with data of one book
8. Run POST http://localhost:8080/library/book from a REST Client like Postman. It should return the details of the book added.
9. Visit http://localhost:8080/library/image/12345 in the browser. It should show login page. Enter 'krishna' and '123456' as credentials. It should download an image file.

#How to understand?
1. The Book represents the resource to be exposed via REST. This resource is mapped as an JPA entity as well.
2. The Library represents the service interface.
3. The LibraryService represents Business Layer. It is a Spring Service bean.
4. The LibraryDAO represnts the DAO. Its a JPA Repository. Helps in saving/reading the Book objects to/from database.
5. The LibraryController represnts the MVC layer. It exposes various APIs to add a book, find a book, listing all books and downloading an image file.
6. The LibrarySecurity represnts the security configuration. It secures resources under /library/image. 
7. The UserAuthenticationProvider represents the Authentication Provider for the AuthenticationManager. It overrides the default behavior to lock a user after 3 failed attempts.
8. The UserService represents User Credentials service for the Authentication Provider.
9. The UserDAO interface and its implementation UserRdbmsDAO connects the UserService with the JDBC layer to load user credentials.
10. The UserAttempts represents the state of a user. 
11. application.properties provides database, hibernate settings.
12. schema.sql has the database schema that gets initialized automatically.
13. data.sql has the DML commands to populate the database tables with two books and three users.
14. scintillations.png is a sample file to be used for image transfer. (Edit the LibraryController for its location)
15. LibraryTests represents couple of test cases on POST requests.
16. pom.xml represents the maven project dependencies.
17. LibraryApplication represents bootstraping class.
