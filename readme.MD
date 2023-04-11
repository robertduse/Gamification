This Gamification App is a simple application that allows users to register and login, play a quiz game and earn rewards, view the ranking of all registered users, and add new quiz questions to the database.

Prerequisites:

JDK 11 or higher
Apache Maven 3.6.0 or higher
To run the application:

Clone the repository to your local machine.
Open the project in your preferred IDE.
Build the project using Maven.
Run the main class GamificationAppication.java.

API Endpoints:

UserController

POST /register - Creates a new user in the system.

POST /login - Authenticates an existing user and returns quiz questions to play the game.

GET / - Returns a list of all users in the system.

GET /ranking - Returns the ranking of all users based on their rewards.

QuizController
POST /addQuiz - Adds a new quiz question to the database.
POST /answer - Accepts a quiz answer from the user and returns the result.

Note: All endpoints are accessible through http://localhost:8080.

Open postman and import the file (GameProject.postman_collection.json)

