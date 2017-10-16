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
import util.Product;

@WebServlet(name = "RefreshProd", urlPatterns = { "/RefreshProd" })
public class DownloadProductionServlet extends HttpServlet{
    private static final long serialVersionUID = 1L;
    Product virus, cure;
    static Logger logger = Logger.getLogger(LoginServlet.class);
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Connection con = (Connection) getServletContext().getAttribute("DBConnection");
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = con.prepareStatement("select * from lark.product ");
            rs = ps.executeQuery();
            rs.next();
            virus = new Product(rs.getInt("id"),rs.getString("name"),rs.getInt("amount"),rs.getInt("sold"));
            rs.next();
            cure = new Product(rs.getInt("id"),rs.getString("name"),rs.getInt("amount"),rs.getInt("sold"));
            HttpSession session = request.getSession();
            Answers ans = new Answers("RefreshProd");
            session.setAttribute("Answers",ans);
            session.setAttribute("Virus",virus);
            session.setAttribute("Cure",cure);
            response.sendRedirect("home.jsp");
        } catch (SQLException e) {
            e.printStackTrace();
            logger.error("Database connection problem");
            throw new ServletException("DB Connection problem.");
        }finally{
            try {
                rs.close();
                ps.close();
                //con.close();
            } catch (SQLException e) {
                logger.error("SQLException in closing PreparedStatement or ResultSet");;
            }

        }
    }
}
