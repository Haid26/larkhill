package servlets.controller;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import util.Answers;
import util.Missions;
import util.User;

@WebServlet(name = "ResultFeedback", urlPatterns = { "/ResultFeedback" })
public class ResultFeedbackServlet extends HttpServlet{
    private static final long serialVersionUID = 1L;

    static Logger logger = Logger.getLogger(LoginServlet.class);
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Connection con = (Connection) getServletContext().getAttribute("DBConnection");
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = con.prepareStatement("select count(id) from lark.FEEDBACK where RESULT=true");

            rs = ps.executeQuery();
            boolean t = rs.next();
            int pos=0;
            if(t)
                pos=rs.getInt("Count(id)");
            ps = con.prepareStatement("select count(id) from lark.FEEDBACK where RESULT=false");

            rs = ps.executeQuery();
            t = rs.next();
            int neg=0;
            if(t)
                neg=rs.getInt("Count(id)");
            pos = pos -neg;
            Answers ans = new Answers("ResultFeedback");
            HttpSession session = request.getSession();
            session.setAttribute("ResFed",pos);
            session.setAttribute("Answers", ans);
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/govhome.jsp");
            //PrintWriter out= response.getWriter();

            // out.println("<font color=green>Feedback Succesfully send<br></font>");
            rd.include(request, response);
        } catch (SQLException e) {
            logger.error("DBconnectionError");
        }
    }
}
