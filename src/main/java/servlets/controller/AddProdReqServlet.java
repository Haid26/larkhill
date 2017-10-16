package servlets.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import util.Answers;

@WebServlet(name = "AddProdReq", urlPatterns = { "/AddProdReq" })

public class AddProdReqServlet extends HttpServlet{
    private static final long serialVersionUID = 1L;

    static Logger logger = Logger.getLogger(RegisterServlet.class);

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("ProdType");
        String amount = request.getParameter("amount");
        String errorMsg = null;

        if(amount == null || amount.equals("")){
            errorMsg = "amount can't be null or empty.";
        }

        if(errorMsg != null){
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/sellerhome.jsp");
            PrintWriter out= response.getWriter();
            out.println("<font color=red>"+errorMsg+"</font>");
            rd.include(request, response);
        }else{

            Connection con = (Connection) getServletContext().getAttribute("DBConnection");
            PreparedStatement ps = null;

            try {
                ps = con.prepareStatement("insert into LARK.productreq(productid, amount, status) values (?,?,?)");
                if(name.endsWith("Virus"))
                    ps.setInt(1, 1);
                else
                    ps.setInt(1,2);
                int tmp = Integer.parseInt(amount);
                ps.setInt(2, tmp);
                ps.setInt(3, 0);


                ps.execute();

                logger.info("Add requesst");

                //forward to login page to login
                RequestDispatcher rd = getServletContext().getRequestDispatcher("/sellerhome.jsp");
                //PrintWriter out= response.getWriter();
                HttpSession session = request.getSession();
                Answers ans = new Answers("AddProdReq");
                session.setAttribute("Answers",ans);
                //out.println("<font color=green>Registration successful, please login below.</font>");
                rd.include(request, response);
            } catch (SQLException e) {
                e.printStackTrace();
                logger.error("Database connection problem");
                throw new ServletException("DB Connection problem.");
            }finally{
                try {

                    ps.close();


                } catch (SQLException e) {
                    logger.error("SQLException in closing PreparedStatement");
                }
                catch (NullPointerException e)
                {
                    e.printStackTrace();
                    logger.error("null pointer");
                }
            }
        }

    }
}
