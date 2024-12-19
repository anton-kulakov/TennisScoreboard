# Tennis Scoreboard

A tennis scoreboard is a web application for keeping track of the points in a tennis match. 
This project is based on the technical specification provided in the Java Backend Roadmap by Sergey Zhukov (link [here](https://zhukovsd.github.io/java-backend-learning-course/projects/tennis-scoreboard/)).

- [Built with](#built-with)
- [Functionality](#functionality)
- [Score Calculation](#score-calculation)
- [Description of the application pages](#description-of-the-application-pages)

## Built with

- Java (Java Core, Servlets)
- H2 database
- Apache Maven
- Apache Tomcat
- Lombok

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

![1. Go to the configurations menu.png](..%2F..%2FDownloads%2F1.%20Go%20to%20the%20configurations%20menu.png)

3. Select "Edit configurations"

![2. Select Edit configurations.png](..%2F..%2FDownloads%2F2.%20Select%20Edit%20configurations.png)

4. Click the plus sign

![3. Click the plus sign.png](..%2F..%2FDownloads%2F3.%20Click%20the%20plus%20sign.png)

5. Select Tomcat -> Local
6. Select Tomcat as a Application server

![4. Select Tomcat as a Application server.png](..%2F..%2FDownloads%2F4.%20Select%20Tomcat%20as%20a%20Application%20server.png)

7. Click "Fix"

![5. Click Fix.png](..%2F..%2FDownloads%2F5.%20Click%20Fix.png)

8. Select "TennisScoreboard:war exploded"
9. Remove "TennisScoreboard_war_exploded" from the application context
10. Click "Run"