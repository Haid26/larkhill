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
import util.Product;

@WebServlet(name = "UpdateProd", urlPatterns = { "/UpdateProd" })
public class ProductionUpdateServlet extends HttpServlet{
    private static final long serialVersionUID = 1L;

    static Logger logger = Logger.getLogger(LoginServlet.class);
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Connection con = (Connection) getServletContext().getAttribute("DBConnection");
        PreparedStatement ps = null;
        ResultSet rs = null;
        Product virus, cure;
        String tmp =  request.getParameter("VirusAmount");
        int virus_amount = Integer.parseInt(tmp);
        tmp =  request.getParameter("CureAmount");
        int cure_amount = Integer.parseInt(tmp);

        try {
            if(request.getParameter("UserType").endsWith("sci")) {
                tmp = request.getParameter("Virus_am");
                int am = Integer.parseInt(tmp);
                ps = con.prepareStatement("UPDATE LARK.Product SET amount = ? WHERE ID = 1");
                ps.setInt(1,virus_amount+am);
                ps.executeUpdate();
                tmp = request.getParameter("Cure_am");
                am = Integer.parseInt(tmp);
                ps = con.prepareStatement("UPDATE LARK.Product SET amount = ? WHERE ID = 2");
                ps.setInt(1,cure_amount+am);
                ps.executeUpdate();
            }
            else
            {
                tmp = request.getParameter("Virus_am");
                int am = Integer.parseInt(tmp);
                if(am<virus_amount){
                    RequestDispatcher rd = getServletContext().getRequestDispatcher("/sellerhome.jsp");
                    PrintWriter out= response.getWriter();
                    out.println("<font color=red>You can't sold more than there is Virus in stock</font>");
                    rd.include(request, response);
                }
                ps = con.prepareStatement("UPDATE LARK.PRODUCT SET AMOUNT = ?, SOLD = ? WHERE ID = 1");
                ps.setInt(1,am-virus_amount);
                ps.setInt(2,virus_amount);
                ps.executeUpdate();

                tmp = request.getParameter("Cure_am");
                am = Integer.parseInt(tmp);
                if(am<cure_amount){
                    RequestDispatcher rd = getServletContext().getRequestDispatcher("/sellerhome.jsp");
                    PrintWriter out= response.getWriter();
                    out.println("<font color=red>You can't sold more than there is cure in stock</font>");
                    rd.include(request, response);
                }
                ps = con.prepareStatement("UPDATE LARK.PRODUCT SET AMOUNT = ?, SOLD = ? WHERE ID = 2");
                ps.setInt(1,am-cure_amount);
                ps.setInt(2,cure_amount);
                ps.executeUpdate();
            }
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
