package com.academy;

import com.academy.base.CheckListService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

import static java.util.Objects.nonNull;

public class InformationServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter printWriter = resp.getWriter();
        printWriter.println("<html>");
        printWriter.println("<body>");
        final HttpSession session = req.getSession();
        if (nonNull(session) &&
                nonNull(session.getAttribute("login")) &&
                nonNull(session.getAttribute("password"))) {
            CheckListService service = new CheckListService();
            service.getCheck().forEach(check -> {
                printWriter.println(check.getCheckList() + " " + check.getYear());
                printWriter.println("<br>");
            });
            printWriter.println("</body>");
            printWriter.println("</html>");
        } else {
            printWriter.println("Locking");
        }
    }
}
