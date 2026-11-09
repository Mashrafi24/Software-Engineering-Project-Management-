<%@ page contentType="text/html;charset=UTF-8" %><%@ page import="java.util.List,com.mathwhiz.model.GameQuestion" %>
<% List<GameQuestion> questions=(List<GameQuestion>)request.getAttribute("questions"); if(questions==null){response.sendRedirect(request.getContextPath()+"/index.jsp");return;} String player=(String)session.getAttribute("playerName"); %>
<!DOCTYPE html><html lang="en"><head><meta charset="UTF-8"><meta name="viewport" content="width=device-width,initial-scale=1"><title>Math Whiz Game</title><link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css"></head><body><main class="container">
<header class="page-head"><div><span class="eyebrow">PLAYER: <%=player%></span><h1>Your Challenge</h1></div><a class="text-link" href="${pageContext.request.contextPath}/leaderboard">Leaderboard</a></header>
<% if(request.getAttribute("error")!=null){ %><p class="alert"><%=request.getAttribute("error")%></p><% } %>
<form action="${pageContext.request.contextPath}/game" method="post" class="panel"><input type="hidden" name="action" value="submit">
<% for(int i=0;i<questions.size();i++){GameQuestion q=questions.get(i); %><section class="question"><div class="question-top"><h2>Question <%=i+1%></h2><span class="marks"><%=q.getMarks()%> marks</span></div><p><%=q.getPrompt()%></p>
<% if("prime".equals(q.getType())){ %><label for="answer<%=i%>">Answer (1 = Prime, 0 = Not Prime)</label><select id="answer<%=i%>" name="answer<%=i%>" required><option value="">Choose</option><option value="1">1 - Prime</option><option value="0">0 - Not Prime</option></select>
<% }else if("mod".equals(q.getType())){ %><label for="answer<%=i%>">Integer remainder</label><input id="answer<%=i%>" name="answer<%=i%>" type="number" step="1" required>
<% }else{ %><label for="answer<%=i%>">Decimal answer (e.g. 0.5)</label><input id="answer<%=i%>" name="answer<%=i%>" type="number" step="any" required><% } %></section><% } %>
<button type="submit">Submit Answers</button></form><p class="foot">All answers are evaluated by Java methods on the server.</p></main></body></html>
