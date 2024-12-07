<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Error - Tennis Scoreboard</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/error.css">
</head>
<body>

<nav class="navbar">
    <div class="nav-container">
        <a href="${pageContext.request.contextPath}/" class="nav-button">Home</a>
        <a href="${pageContext.request.contextPath}/new-match" class="nav-button">New match</a>
        <a href="${pageContext.request.contextPath}/matches?page=&filter_by_player_name=" class="nav-button">Completed matches</a>
    </div>
</nav>

<div class="error-container">
    <h2 class="error-title">Error</h2>
    <div class="error-message">
        <h3>${errorMessage}</h3>
    </div>
</div>

</body>
</html>
