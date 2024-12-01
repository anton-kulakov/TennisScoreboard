<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Tennis Match Result</title>
    <link rel="stylesheet" href="../css/completed-match.css">
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
    <h2>The match is over!</h2>
    <h2>The winner is ${matchResult.winner} 🏆</h2>

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
            <th>Set 1</th>
            <th>Set 2</th>
            <th>Set 3</th>
        </tr>
        </thead>
        <tbody>
        <tr>
            <td>${matchResult.firstPlayerName}</td>
            <td>${matchResult.firstSet.firstPlayer}</td>
            <td>${matchResult.secondSet.firstPlayer}</td>
            <td>${matchResult.thirdSet.firstPlayer}</td>
        </tr>
        <tr>
            <td>${matchResult.secondPlayerName}</td>
            <td>${matchResult.firstSet.secondPlayer}</td>
            <td>${matchResult.secondSet.secondPlayer}</td>
            <td>${matchResult.thirdSet.secondPlayer}</td>
        </tr>
        </tbody>
    </table>
</div>

</body>
</html>