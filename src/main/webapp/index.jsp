<%@ page contentType="text/html;charset=UTF-8"%>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Tennis Scoreboard</title>
    <link rel="stylesheet" href="css/index.css">
</head>
<body>
<div class="form-container">
    <h1>Welcome to the Tennis Scoreboard</h1>
    <button class="button" onclick="location.href='<%= request.getContextPath() %>/new-match'">New match</button>
    <button class="button" onclick="location.href='<%= request.getContextPath() %>/matches?page=&filter_by_player_name='">Completed matches</button>
</div>
</body>
</html>