<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Completed Matches</title>
    <link rel="stylesheet" href="css/finished-matches.css">
</head>
<body>

<nav class="navbar">
    <div class="nav-container">
        <a href="${pageContext.request.contextPath}/" class="nav-button">Home</a>
        <a href="${pageContext.request.contextPath}/new-match" class="nav-button">New match</a>
        <a href="${pageContext.request.contextPath}/matches?page=&filter_by_player_name=" class="nav-button">Completed matches</a>
    </div>
</nav>

<div class="matches-container">
    <h2>Completed matches</h2>

    <form action="${pageContext.request.contextPath}/matches" method="GET" class="search-form">
        <input type="text" name="filter_by_player_name" placeholder="Search by player name" value="${param.filter_by_player_name}">
        <button type="submit" class="search-button">Search</button>
    </form>

    <div class="matches-list">
        <c:if test="${not empty page.matches}">
            <table class="scoreboard">
                <thead>
                <tr>
                    <th>First player</th>
                    <th>Second player</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${page.matches}" var="match">
                    <tr>
                        <td>
                                <span class="player-name">
                                    <c:if test="${match.winner.name == match.player1.name}">&#127942;</c:if>
                                    ${match.player1.name}
                                </span>
                        </td>
                        <td>
                                <span class="player-name">
                                    <c:if test="${match.winner.name == match.player2.name}">&#127942;</c:if>
                                    ${match.player2.name}
                                </span>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </c:if>
        <c:if test="${empty page.matches}">
            <p>No matches found.</p>
        </c:if>
    </div>

    <div class="pagination">
        <a href="${pageContext.request.contextPath}/matches?page=${page.pageNumber - 1}&filter_by_player_name=${param.filter_by_player_name}"
           class="pagination-button ${page.pageNumber <= 1 ? 'inactive' : ''}">
            Previous
        </a>
        <span class="page-number">Page ${page.pageNumber} of ${page.totalPagesNumber}</span>
        <a href="${pageContext.request.contextPath}/matches?page=${page.pageNumber + 1}&filter_by_player_name=${param.filter_by_player_name}"
           class="pagination-button ${page.pageNumber >= page.totalPagesNumber ? 'inactive' : ''}">
            Next
        </a>
    </div>

</div>
</body>
</html>


