package com.mashrafi.web;
import com.mashrafi.core.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.*;
import java.time.LocalDate;
import java.util.List;
@WebServlet(urlPatterns={"/tools","/"},loadOnStartup=1)
public class ToolsServlet extends HttpServlet {
 private final StudyRepository repo=new StudyRepository();
 @Override public void init()throws ServletException{try{repo.initialize();}catch(Exception e){throw new ServletException("MySQL initialization failed. Check database credentials and server.",e);}}
 @Override protected void doGet(HttpServletRequest q,HttpServletResponse r)throws IOException{render(q,r,null);}
 @Override protected void doPost(HttpServletRequest q,HttpServletResponse r)throws IOException{q.setCharacterEncoding("UTF-8");String msg=null;try{
  if("calc".equals(q.getParameter("action"))){double x=Double.parseDouble(q.getParameter("x")),y=q.getParameter("y")==null||q.getParameter("y").isBlank()?0:Double.parseDouble(q.getParameter("y"));String op=q.getParameter("op");double v=Calculator.calculate(x,y,op,q.getParameter("degrees")!=null);repo.saveCalculation(x+" "+op+" "+y,Double.toString(v));msg="Result: "+v+" — saved to database";}
  else if("plan".equals(q.getParameter("action"))){repo.savePlan(GanttPlanner.create30DayPlan(LocalDate.parse(q.getParameter("start"))));msg="30-day plan saved to database";}
 }catch(Exception e){msg="Error: "+e.getMessage();}render(q,r,msg);}
 private void render(HttpServletRequest q,HttpServletResponse r,String msg)throws IOException{r.setContentType("text/html;charset=UTF-8");PrintWriter o=r.getWriter();String c=q.getContextPath();
  o.print("<!doctype html><html><head><meta charset='UTF-8'><meta name='viewport' content='width=device-width,initial-scale=1'><title>Mashrafi Study Tools</title><style>*{box-sizing:border-box}body{margin:0;background:#eef3f9;font:15px Arial;color:#1c2e43}.wrap{max-width:1050px;margin:35px auto;padding:20px}.hero{padding:30px;border-radius:18px;background:linear-gradient(120deg,#102a43,#28618c);color:white}.grid{display:grid;grid-template-columns:1fr 1fr;gap:18px}.card{background:white;padding:22px;border-radius:15px;margin-top:18px;box-shadow:0 8px 24px #142b4610}input,select,button{padding:11px;border:1px solid #d5deea;border-radius:8px;margin:5px}button{background:#245b83;color:white;border:0;font-weight:bold;cursor:pointer}.message{padding:12px;background:#e2f5e9;border-radius:8px;margin:15px 0}table{width:100%;border-collapse:collapse}td,th{text-align:left;padding:9px;border-bottom:1px solid #e5eaf0}h1,h2{margin-top:0}@media(max-width:700px){.grid{grid-template-columns:1fr}}</style></head><body><main class='wrap'><header class='hero'><h1>Mashrafi Study Tools</h1><p>Scientific Calculator · 30-Day Project Planner · MySQL History</p></header>");
  if(msg!=null)o.print("<div class='message'>"+esc(msg)+"</div>");
  o.print("<div class='grid'><section class='card'><h2>Scientific Calculator</h2><form method='post'><input type='hidden' name='action' value='calc'><input name='x' type='number' step='any' placeholder='First number' required><input name='y' type='number' step='any' placeholder='Second number'><select name='op'>");for(String op:List.of("add","subtract","multiply","divide","sin","cos","tan","sqrt","cbrt","log","ln","square","power","factorial","percent","asin","acos"))o.print("<option>"+op+"</option>");o.print("</select><label><input type='checkbox' name='degrees' checked> Degrees</label><button>Calculate & Save</button></form></section>");
  o.print("<section class='card'><h2>30-Day Planner</h2><form method='post'><input type='hidden' name='action' value='plan'><label>Start date <input type='date' name='start' required></label><button>Generate & Save</button></form></section></div><section class='card'><h2>Saved Calculator History</h2><table><tr><th>Expression</th><th>Result</th><th>Created</th></tr>");
  try{for(Calculation a:repo.getRecentCalculations(30))o.print("<tr><td>"+esc(a.expression())+"</td><td>"+esc(a.result())+"</td><td>"+esc(String.valueOf(a.createdAt()))+"</td></tr>");}catch(Exception e){o.print("<tr><td colspan='3'>"+esc(e.getMessage())+"</td></tr>");}
  o.print("</table></section><section class='card'><h2>Saved Gantt Tasks</h2><table><tr><th>Day</th><th>Date</th><th>Task</th><th>Progress</th><th>Status</th></tr>");
  try{for(GanttTask t:repo.getTasks())o.print("<tr><td>"+t.dayNumber()+"</td><td>"+t.taskDate()+"</td><td>"+esc(t.title())+"</td><td>"+t.progress()+"%</td><td>"+esc(t.status())+"</td></tr>");}catch(Exception e){o.print("<tr><td colspan='5'>"+esc(e.getMessage())+"</td></tr>");}
  o.print("</table></section><p>Md. Mashrafi Rahman · IT-24034</p></main></body></html>");
 }
 private String esc(String s){if(s==null)return "";return s.replace("&","&amp;").replace("<","&lt;").replace(">","&gt;").replace("\"","&quot;").replace("'","&#39;");}
}