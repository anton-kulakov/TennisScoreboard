<%@ page contentType="text/html;charset=UTF-8"%>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Tennis Scoreboard</title>
    <link rel="stylesheet" href="css/new-match.css">
    <script>
        function validateForm() {
            const player1 = document.getElementById('player1').value.trim();
            const player2 = document.getElementById('player2').value.trim();

            if (player1 === player2) {
                alert('The names of the players must be different');
                return false;
            }
            return true;
        }
    </script>
</head>
<body>

<nav class="navbar">
    <div class="nav-container">
        <a href="${pageContext.request.contextPath}/" class="nav-button">Home</a>
        <a href="" class="nav-button">Completed matches</a>
    </div>
</nav>

<div class="form-container">
    <h1>Start of the game</h1>
    <form action="${pageContext.request.contextPath}/new-match" method="POST" onsubmit="return validateForm();">
        <label for="player1">First player:</label>
        <input type="text" id="player1" name="player1" required placeholder="enter the name">

        <label for="player2">Second player:</label>
        <input type="text" id="player2" name="player2" required placeholder="enter the name">

        <button type="submit">Start</button>
    </form>
</div>
</body>
</html>

