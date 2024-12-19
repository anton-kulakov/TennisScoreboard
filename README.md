# Tennis Scoreboard

A tennis scoreboard is a web application for keeping track of the points in a tennis match. 
This project is based on the technical specification provided in the Java Backend Roadmap by Sergey Zhukov (link [here](https://zhukovsd.github.io/java-backend-learning-course/projects/tennis-scoreboard/)).

- [Built with](#built-with)
- [Functionality](#functionality)
- [Score Calculation](#score-calculation)
- [Description of the application pages](#description-of-the-application-pages)
- [How to run](#how-to-run)

## Built with

- Java (Java Core, Servlets)
- H2 database
- Apache Maven
- Apache Tomcat
- Lombok
- HTML/CSS
- JSP

## Functionality

The application allows the user to create a new match with two players. The players can either be new or have previously participated in other matches.
After creating a new match, the user is directed to the tennis score tracking page, where they can increase the players' scores using buttons until the match is completed.

## Score Calculation

You can familiarize yourself with the rules of tennis match scoring by following the [link](https://olympics.com/en/news/tennis-rules-regulations-how-to-play-basics). The project implements score tracking with the assumption that the match continues until one of the players wins two out of three sets.

## Description of the application pages

The project includes 5 pages:

1. Index – The page where the user can either create a new match or go to the list of completed matches
2. New match (/new-match) - a page for creating a new match
3. Match score (/match-score) - a page for keeping track of the current match score
4. Error - a page for displaying application errors
5. Completed match - a page for displaying information about a completed match, including the players' names, the winner, and the set scores
6. Finished-matches (/matches) - a page for displaying a list of completed matches with the ability to filter by player name and pagination

## How to Run
To run the application, make sure that you have Java (JDK), GIT, Apache Maven and Apache Tomcat installed on your computer.
1. Download or Clone the Project

You can get the project by either downloading it as a ZIP archive or by cloning it using Git.

**Option 1: Download as ZIP Archive** 
- Go to the project’s GitHub page
- Click the "Code" button
- Select "Download ZIP"
- Extract the ZIP file to a directory on your local machine.

**Option 2: Clone the Repository using Git**

Open your terminal or Git Bash and run the following command:

  ```bash
  git clone https://github.com/anton-kulakov/TennisScoreboard.git
  ```
  
2. Go to the configurations menu

![1  Go to the configurations menu](https://github.com/user-attachments/assets/e59d28ef-c385-4551-b63d-b897c69839f4)

3. Select "Edit configurations"

![2  Select Edit configurations](https://github.com/user-attachments/assets/49cf4038-bf76-465c-a1dd-5c0fcb97a29b)

4. Click the plus sign

![3  Click the plus sign](https://github.com/user-attachments/assets/c668675d-2168-4be2-ba29-932bf7c30178)

5. Select Tomcat -> Local
6. Select Tomcat as a Application server

![4  Select Tomcat as a Application server](https://github.com/user-attachments/assets/68211fa6-a9e1-4e43-bc15-99b77678182e)

7. Click "Fix"

![5  Click Fix](https://github.com/user-attachments/assets/3886edbe-a2af-477e-96ab-b0061b86d248)

8. Select "TennisScoreboard:war exploded"
9. Remove "TennisScoreboard_war_exploded" from the application context
10. Click "Run"
