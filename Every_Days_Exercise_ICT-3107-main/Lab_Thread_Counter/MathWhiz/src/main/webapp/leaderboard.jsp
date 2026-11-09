<%@ page contentType="text/html;charset=UTF-8" %><%@ page import="java.util.List,com.mathwhiz.dao.ScoreDAO.ScoreRecord" %><% List<ScoreRecord> scores=(List<ScoreRecord>)request.getAttribute("scores"); %>
<!DOCTYPE html><html lang="en"><head><meta charset="UTF-8"><meta name="viewport" content="width=device-width,initial-scale=1"><title>Leaderboard</title><link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css"></head><body><main class="container">
<header class="page-head"><div><span class="eyebrow">HALL OF FAME</span><h1>Top 10 Leaderboard</h1></div><a class="text-link" href="${pageContext.request.contextPath}/index.jsp">← Back to Game</a></header>
<section class="panel"><table><thead><tr><th>Rank</th><th>Player</th><th>Score</th><th>Difficulty</th><th>Played at</th></tr></thead><tbody>
<%if(scores==null||scores.isEmpty()){%><tr><td colspan="5">No scores yet. Be the first player!</td></tr><%}else{int rank=1;for(ScoreRecord s:scores){%><tr><td><%=rank++%></td><td><%=s.playerName()%></td><td><strong><%=s.score()%>/10</strong></td><td><%=s.difficulty()%></td><td><%=s.playedAt()%></td></tr><%}}%>
</tbody></table></section></main></body></html>
