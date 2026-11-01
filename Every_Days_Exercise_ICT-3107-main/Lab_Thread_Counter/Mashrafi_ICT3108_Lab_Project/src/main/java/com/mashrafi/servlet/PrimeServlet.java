package com.mashrafi.servlet;
import com.mashrafi.model.PrimeNumber;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
@WebServlet(name="PrimeServlet", urlPatterns={"/prime"})
public class PrimeServlet extends HttpServlet {
    @Override protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        render(resp, null, null);
    }
    @Override protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String raw = req.getParameter("number");
        if (raw == null || raw.isBlank()) { render(resp, null, "Please enter an integer."); return; }
        try {
            long n = Long.parseLong(raw.trim());
            render(resp, n + (PrimeNumber.isPrime(n) ? " is a PRIME number." : " is NOT a prime number."), null);
        } catch (NumberFormatException e) {
            render(resp, null, "Invalid integer. Enter a value within the long integer range.");
        }
    }
    private void render(HttpServletResponse resp, String result, String error) throws IOException {
        resp.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = resp.getWriter()) {
            out.println("<!doctype html><html lang='en'><head><meta charset='UTF-8'><meta name='viewport' content='width=device-width,initial-scale=1'><title>Prime Checker</title>");
            out.println("<style>body{font-family:Arial;background:#eef2ff;display:grid;place-items:center;min-height:100vh;color:#172033}.card{background:white;padding:36px;border-radius:18px;width:min(90%,480px);box-shadow:0 15px 45px #0002}h1{color:#1d4ed8}input,button{box-sizing:border-box;width:100%;padding:13px;margin-top:12px;border-radius:8px;font-size:16px}input{border:1px solid #cbd5e1}button{border:0;background:#2563eb;color:white;font-weight:bold;cursor:pointer}.result,.error{padding:14px;margin-top:20px;border-radius:8px;background:#dcfce7;color:#166534}.error{background:#fee2e2;color:#991b1b}</style></head><body><main class='card'><h1>Prime Number Checker</h1><p>Enter an integer to check whether it is prime.</p><form method='post' action='prime'><label for='number'>Number</label><input id='number' name='number' type='number' step='1' required placeholder='Example: 13'><button type='submit'>Check Number</button></form>");
            if (result != null) out.println("<div class='result'>" + esc(result) + "</div>");
            if (error != null) out.println("<div class='error'>" + esc(error) + "</div>");
            out.println("<p><small>ICT 3108 SEPM Lab | Md. Mashrafi Rahman</small></p></main></body></html>");
        }
    }
    private String esc(String s) { return s.replace("&","&amp;").replace("<","&lt;").replace(">","&gt;").replace("\"","&quot;").replace("'","&#39;"); }
}