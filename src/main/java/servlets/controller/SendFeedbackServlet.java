package servlets.controller;

        import java.io.IOException;
        import java.io.PrintWriter;
        import java.sql.Connection;
        import java.sql.PreparedStatement;
        import java.sql.ResultSet;
        import java.sql.SQLException;

        import javax.servlet.RequestDispatcher;
        import javax.servlet.ServletException;
        import javax.servlet.annotation.WebServlet;
        import javax.servlet.http.HttpServlet;
        import javax.servlet.http.HttpServletRequest;
        import javax.servlet.http.HttpServletResponse;
        import javax.servlet.http.HttpSession;

        import org.apache.log4j.Logger;

        import util.User;
@WebServlet(name = "SendFeedback", urlPatterns = { "/SendFeedback" })
public class SendFeedbackServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    static Logger logger = Logger.getLogger(LoginServlet.class);

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String answer = request.getParameter("ask");
        Connection con = (Connection) getServletContext().getAttribute("DBConnection");
        PreparedStatement ps = null;
        ResultSet rs = null;
        User user = (User) request.getSession().getAttribute("User");

        try {
            ps = con.prepareStatement("INSERT INTO lark.feedback (userID, result) VALUES (?, ?)");
            ps.setString(1, String.valueOf(user.getId()));
            if (answer == "yes")
                ps.setString(2, "true");
            else
                ps.setString(2, "false");
            ps.execute();
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/peoplehome.jsp");
            PrintWriter out= response.getWriter();
            out.println("<font color=green>Feedback Succesfully send<br></font>");
            rd.include(request, response);
        } catch (SQLException e) {
            logger.error("DBconnectionError");
        }
    }
}
