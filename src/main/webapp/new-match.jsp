<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Game</title>
    <link rel="stylesheet" href="css/new-match.css">
</head>
<body>

<nav class="navbar">
    <div class="nav-container">
        <a href="home.jsp" class="nav-button">Home</a>
        <a href="" class="nav-button">Completed matches</a>
    </div>
</nav>

<div class="form-container">
    <h1>Start of the game</h1>
    <form action="${pageContext.request.contextPath}/new-match" method="POST">
        <label for="player1">First player:</label>
        <input type="text" id="player1" name="player1" required placeholder="enter the name">

        <label for="player2">Second player:</label>
        <input type="text" id="player2" name="player2" required placeholder="enter the name">

        <button type="submit">Start</button>
    </form>
</div>
</body>
</html>
