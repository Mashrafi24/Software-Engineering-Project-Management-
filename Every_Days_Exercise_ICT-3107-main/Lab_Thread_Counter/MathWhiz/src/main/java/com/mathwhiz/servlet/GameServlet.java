package com.mathwhiz.servlet;
import com.mathwhiz.dao.ScoreDAO;
import com.mathwhiz.model.*;
import com.mathwhiz.service.MathService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

@WebServlet("/game")
public class GameServlet extends HttpServlet {
    private final ScoreDAO dao=new ScoreDAO();
    @Override public void init()throws ServletException {try{dao.initialize();}catch(Exception e){throw new ServletException("MySQL initialization failed. Check database and DB credentials.",e);}}
    @Override protected void doPost(HttpServletRequest req,HttpServletResponse resp)throws ServletException,IOException {
        req.setCharacterEncoding("UTF-8"); String action=req.getParameter("action");
        if("start".equals(action)) start(req,resp); else if("submit".equals(action)) submit(req,resp); else resp.sendError(400,"Unknown action");
    }
    private void start(HttpServletRequest req,HttpServletResponse resp)throws ServletException,IOException {
        String name=req.getParameter("playerName");
        if(name==null||name.isBlank()||name.trim().length()>100){req.setAttribute("error","Enter a name (up to 100 characters).");req.getRequestDispatcher("/index.jsp").forward(req,resp);return;}
        List<GameQuestion> qs=createQuestions(); HttpSession session=req.getSession();
        session.setAttribute("playerName",name.trim());session.setAttribute("questions",qs);req.setAttribute("questions",qs);
        req.getRequestDispatcher("/game.jsp").forward(req,resp);
    }
    private List<GameQuestion> createQuestions(){
        ThreadLocalRandom r=ThreadLocalRandom.current(); int prime=r.nextInt(11,60),a=r.nextInt(20,100),b=r.nextInt(2,12);
        String[] ops={"sin","cos","tan"};int[] angles={0,30,45,60,90,120,135,150,180};List<GameQuestion> q=new ArrayList<>();
        q.add(new GameQuestion("prime","Is "+prime+" prime? Enter 1 for Prime or 0 for Not Prime.",2,prime,0,0,null,0));
        q.add(new GameQuestion("mod","Calculate "+a+" % "+b+".",2,0,a,b,null,0));
        for(int i=0;i<2;i++){String op=ops[r.nextInt(ops.length)];int angle=angles[r.nextInt(angles.length)];if("tan".equals(op)&&angle==90)angle=45;
            q.add(new GameQuestion("trig","Calculate "+op.toUpperCase()+"("+angle+"°). Give a decimal answer.",3,0,0,0,op,angle));}
        return q;
    }
    @SuppressWarnings("unchecked")
    private void submit(HttpServletRequest req,HttpServletResponse resp)throws ServletException,IOException {
        HttpSession session=req.getSession(false);
        if(session==null||session.getAttribute("questions")==null||session.getAttribute("playerName")==null){resp.sendRedirect(req.getContextPath()+"/index.jsp");return;}
        List<GameQuestion> qs=(List<GameQuestion>)session.getAttribute("questions");String name=(String)session.getAttribute("playerName");
        int score=0;List<String> feedback=new ArrayList<>();
        try{
            for(int i=0;i<qs.size();i++){GameQuestion q=qs.get(i);String raw=req.getParameter("answer"+i);boolean correct=false;
                if(raw==null||raw.isBlank()){feedback.add("Q"+(i+1)+": No answer submitted.");continue;}
                if("prime".equals(q.getType()))correct=Integer.parseInt(raw.trim())==MathService.isPrime(q.getNumber());
                else if("mod".equals(q.getType()))correct=Integer.parseInt(raw.trim())==MathService.calculateMod(q.getFirst(),q.getSecond());
                else {double answer=Double.parseDouble(raw.trim());double expected=MathService.calculateTrigonometry(q.getOperation(),q.getDegree());correct=Double.isFinite(answer)&&Math.abs(answer-expected)<=0.01;}
                if(correct){score+=q.getMarks();feedback.add("Q"+(i+1)+": Correct (+"+q.getMarks()+").");}else feedback.add("Q"+(i+1)+": Incorrect (0 marks).");
            }
            dao.saveScore(name,score,"NORMAL");req.setAttribute("result",new GameResult(name,score,feedback));
            session.removeAttribute("questions");session.removeAttribute("playerName");req.getRequestDispatcher("/result.jsp").forward(req,resp);
        }catch(NumberFormatException e){req.setAttribute("error","Use valid numeric answers.");req.setAttribute("questions",qs);req.getRequestDispatcher("/game.jsp").forward(req,resp);}
        catch(Exception e){throw new ServletException("Game evaluation or database save failed.",e);}
    }
}
