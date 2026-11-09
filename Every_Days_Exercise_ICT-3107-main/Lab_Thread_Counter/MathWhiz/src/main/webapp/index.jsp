
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Mashrafi Math Whiz</title>
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/css/style.css">
</head>

<body>
<main class="container">

    <section class="hero">
        <span class="eyebrow">JAVA SERVLET + JDBC GAME</span>

        <h1>MASHRAFI MATH WHIZ</h1>

        <p>Challenge your mathematical brain.</p>

        <div class="chips">
            <span>Prime · 2 marks</span>
            <span>Modulus · 2 marks</span>
            <span>Trigonometry · 6 marks</span>
        </div>
    </section>

    <section class="panel narrow">
        <h2>Start your challenge</h2>

        <% if(request.getAttribute("error") != null) { %>
        <p class="alert">
            <%= request.getAttribute("error") %>
        </p>
        <% } %>

        <form action="${pageContext.request.contextPath}/game"
              method="post">

            <input type="hidden" name="action" value="start">

            <label for="playerName">Player name</label>

            <input id="playerName"
                   name="playerName"
                   maxlength="100"
                   required
                   placeholder="Enter your name">

            <button type="submit">Start Game</button>
        </form>

        <a class="text-link"
           href="${pageContext.request.contextPath}/leaderboard">
            View Top 10 Leaderboard →
        </a>
    </section>

    <p class="foot">
        Difficulty: NORMAL · Maximum score: 10
    </p>

</main>
</body>
</html>
