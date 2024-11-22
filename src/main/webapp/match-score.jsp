<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Tennis Scoreboard</title>
    <link rel="stylesheet" href="css/match-score.css">
</head>
<body>

<nav class="navbar">
    <div class="nav-container">
        <a href="${pageContext.request.contextPath}/" class="nav-button">Home</a>
        <a href="${pageContext.request.contextPath}/new-match" class="nav-button">New match</a>
        <a href="" class="nav-button">Completed matches</a>
    </div>
</nav>

<div class="scoreboard-container">
    <table class="scoreboard">
        <colgroup>
            <col style="width: 40%;">
            <col style="width: 20%;">
            <col style="width: 20%;">
            <col style="width: 20%;">
        </colgroup>
        <thead>
        <tr>
            <th>Players</th>
            <th>Sets</th>
            <th>Games</th>
            <th>Points</th>
        </tr>
        </thead>
        <tbody>
        <tr>
            <td>${match.player1.name}</td>
            <td>${match.matchScore.setScore.firstPlayerPoints}</td>
            <td>${match.matchScore.gameScore.firstPlayerPoints}</td>
            <td>${match.matchScore.currentPoints.firstPlayerPoints}</td>
        </tr>
        <tr>
            <td>${match.player2.name}</td>
            <td>${match.matchScore.setScore.secondPlayerPoints}</td>
            <td>${match.matchScore.gameScore.secondPlayerPoints}</td>
            <td>${match.matchScore.currentPoints.secondPlayerPoints}</td>
        </tr>
        </tbody>
    </table>

    <div class="buttons-container">
    <form action="${pageContext.request.contextPath}/match-score?uuid=${uuid}" method="POST">
        <input type="hidden" name="playerID" value="${match.player1.id}">
        <button type="submit" class="point-button">${match.player1.name} won a point</button>
    </form>
    <form action="${pageContext.request.contextPath}/match-score?uuid=${uuid}" method="POST">
        <input type="hidden" name="playerID" value="${match.player2.id}">
        <button type="submit" class="point-button">${match.player2.name} won a point</button>
    </form>
    </div>
</div>

</body>
</html>
