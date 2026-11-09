package com.mathwhiz.servlet;
import com.mathwhiz.dao.ScoreDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
@WebServlet("/leaderboard")
public class LeaderboardServlet extends HttpServlet {
    private final ScoreDAO dao=new ScoreDAO();
    @Override protected void doGet(HttpServletRequest req,HttpServletResponse resp)throws ServletException,IOException {
        try{req.setAttribute("scores",dao.getTopTen());req.getRequestDispatcher("/leaderboard.jsp").forward(req,resp);}
        catch(Exception e){throw new ServletException("Could not load leaderboard.",e);}
    }
}
