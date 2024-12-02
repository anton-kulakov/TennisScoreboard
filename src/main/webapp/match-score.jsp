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
            <td id="firstPlayerPoints"
                data-mode="${match.matchScore.currentPoints.isDeuce ? 'deuce' : (match.matchScore.currentPoints.isTiebreak ? 'tiebreak' : 'regular')}">
                ${match.matchScore.currentPoints.firstPlayerPoints}
            </td>
        </tr>
        <tr>
            <td>${match.player2.name}</td>
            <td>${match.matchScore.setScore.secondPlayerPoints}</td>
            <td>${match.matchScore.gameScore.secondPlayerPoints}</td>
            <td id="secondPlayerPoints"
                data-mode="${match.matchScore.currentPoints.isDeuce ? 'deuce' : (match.matchScore.currentPoints.isTiebreak ? 'tiebreak' : 'regular')}">
                ${match.matchScore.currentPoints.secondPlayerPoints}
            </td>
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

<script>
    document.addEventListener('DOMContentLoaded', function() {
        function updatePlayerPoints(playerPointsElement, playerPoints, mode) {
            if (mode === 'tiebreak') {
                return;
            } else if (mode === 'deuce') {
                if (playerPoints === 0) {
                    playerPointsElement.textContent = '';
                } else if (playerPoints === 1) {
                    playerPointsElement.textContent = 'AD';
                } else if (playerPoints === 3) {
                    playerPointsElement.textContent = '40';
                }
            } else {
                if (playerPoints === 1) {
                    playerPointsElement.textContent = '15';
                } else if (playerPoints === 2) {
                    playerPointsElement.textContent = '30';
                } else if (playerPoints === 3) {
                    playerPointsElement.textContent = '40';
                }
            }
        }

        const firstPlayerPointsElement = document.getElementById('firstPlayerPoints');
        let firstPlayerPoints = parseInt(firstPlayerPointsElement.textContent);
        let firstPlayerMode = firstPlayerPointsElement.getAttribute('data-mode');
        updatePlayerPoints(firstPlayerPointsElement, firstPlayerPoints, firstPlayerMode);

        const secondPlayerPointsElement = document.getElementById('secondPlayerPoints');
        let secondPlayerPoints = parseInt(secondPlayerPointsElement.textContent);
        let secondPlayerMode = firstPlayerPointsElement.getAttribute('data-mode');
        updatePlayerPoints(secondPlayerPointsElement, secondPlayerPoints, secondPlayerMode);
    });
</script>

</body>
</html>

