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
import util.ProdMissions;

@WebServlet(name = "UpdateProdReq", urlPatterns = { "/UpdateProdReq" })
public class ProdReqUpdateServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    static Logger logger = Logger.getLogger(LoginServlet.class);
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Connection con = (Connection) getServletContext().getAttribute("DBConnection");
        PreparedStatement ps = null;
        ResultSet rs = null;
        String id, status;
        id = request.getParameter("ProdMissionId");
        status = request.getParameter("StatusReq");
        int idd, statusd;
        idd = Integer.parseInt(id);
        statusd = Integer.parseInt(status);
        logger.info("params"+id+"; "+status);
        List<ProdMissions> dataList = new ArrayList();
        try {
            ps = con.prepareStatement("UPDATE LARK.ProductReq SET STATUS = ? WHERE ID = ?");
            ps.setInt(1, statusd);
            ps.setInt(2, idd);
            //ps.setString(1,status);
            //ps.setString(2,id);
            ps.executeUpdate();
            logger.info("updated mission table");
            ps = con.prepareStatement("SELECT lark.PRODUCTREQ.id, lark.PRODUCT.name, lark.PRODUCTREQ.AMOUNT, lark.PRODUCTREQ.STATUS FROM lark.PRODUCTREQ INNER JOIN lark.PRODUCT ON lark.PRODUCTREQ.PRODUCTID = lark.PRODUCT.id  ORDER BY id");
            rs = ps.executeQuery();
            while (rs.next()) {
                //Add records into data list
                ProdMissions tmp = new ProdMissions(rs.getInt("id"), rs.getString("name"), rs.getInt("amount"), rs.getInt("status"));
                dataList.add(tmp);
            }
            HttpSession session = request.getSession();
            Answers ans = new Answers("RefreshProdReq");
            session.setAttribute("Answers", ans);
            session.setAttribute("ProdMissions", dataList);
            logger.info("name = " + dataList.get(0).getName() + " amount=" + dataList.get(0).getAmount());
            response.sendRedirect("home.jsp");
        } catch (SQLException e) {
            e.printStackTrace();
            logger.error("Database connection problem");
            throw new ServletException("DB Connection problem.");
        } finally {
            try {
                rs.close();
                ps.close();
                //con.close();
            } catch (SQLException e) {
                logger.error("SQLException in closing PreparedStatement or ResultSet");
                ;
            }

        }
    }
}
