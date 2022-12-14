# VenPalMo
### My first attempt at a consumer payment platform system.

VenPalMo is a digital payment platform that combines the convenience and security of PayPal and Venmo with the added ability to send messages between users. With VenPalMo, you can easily and securely send and receive money from friends and family, and also keep track of your transactions through direct communication with your contacts. VenPalMo makes it easy to manage your finances on the go. Whether you're paying for a group dinner, splitting rent, or sending a gift, VenPalMo has you covered.

### Main User Features:
- Welcome info : Shows user their balance, recent activity, and any requests for payement.
- Send/Request Money : Users can send or request money to/from another user after searching by name or email.
- Admin : Admins can promote or delete[^1] other users. (deletion modifies the active flag in the database)
- Profile edit : users can modify their profile informatin at any time.
- Messaging : users can send and view messages/conversations from other users. Giving them a space to deliberate on payment or just catch up.

### Tech Stack and Accomplishments:
VenPalMo is a Java Spring Boot application tying to a MySql database. Notable features include:
  - Spring Web / Thymeleaf
  - Spring Security
  - Spring Validation
  - Spring Jpa
  - Spring Test / JUnit / h2
  - Lombok

I'm most proud of the following:
  - **Spring Security** : Proud of this because I've built spring applications in the past but never had a chance to touch on spring security. Doing so has helped close a gap in knowlege and will improve my confidence in creating production ready applications.
  - **Spring Web / Thymeleaf** : Proud of this because it was a whole new way to make a front end, in the past I've made applications using a separate front end using npm and a component based framework (Vue). I feel it has added to my tool kit and will assist me when stepping into older systems.

## Deploy
##### The application includes Docker and Docker Compose, making it easy to run on any system with Docker installed.

##### To start the VenPalMo application, follow these steps:
>######  1. Clone the project repository to your local machine.
>######  2. Navigate to the project directory in your terminal.
>######  3. Start the application using docker-compose.
>######  4. This will start the application and all of its services. Once the services are up and running, you can access the application in your web browser at http://localhost:8080.
>######  5. To stop the application, use the CTRL+C keyboard shortcut in your terminal.

-----------------------------------------------------------------------------------------------------------------------------------------------------------------------


## DB Schema

![image](https://user-images.githubusercontent.com/63884066/207433373-07829274-8d16-4ab0-a0d5-864b40e4c7d6.png)

## Login Screen

![image](https://user-images.githubusercontent.com/63884066/207629924-70bb1f5b-5dfd-4008-b457-7d7dc84ab43f.png)

## Welcome Screen

![image](https://user-images.githubusercontent.com/63884066/207628757-341d3a04-fb27-4534-942f-6bc819aaf41f.png)

## Search For Users

![image](https://user-images.githubusercontent.com/63884066/207628021-7753ff25-5f0b-4d68-b325-ccbdd59668fb.png)

## Sending/Requesting Money

![image](https://user-images.githubusercontent.com/63884066/207628374-c58c33e9-82ed-407f-b97c-320bc030fbde.png)


[^1]: Deletion modifies the active flag in the database
