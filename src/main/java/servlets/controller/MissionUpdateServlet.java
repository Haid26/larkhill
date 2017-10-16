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
@WebServlet(name = "MissionUpdate", urlPatterns = { "/MissionUpdate" })
public class MissionUpdateServlet extends  HttpServlet{
    private static final long serialVersionUID = 1L;

    static Logger logger = Logger.getLogger(LoginServlet.class);
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Connection con = (Connection) getServletContext().getAttribute("DBConnection");
        PreparedStatement ps = null;
        ResultSet rs = null;
        String id, status;
        id = request.getParameter("MissionId");
        status = request.getParameter("Status");
        int idd, statusd;
        idd = Integer.parseInt(id);
        statusd = Integer.parseInt(status);
        logger.info("params"+id+"; "+status);
        List<Missions> dataList = new ArrayList();
        try {
            ps = con.prepareStatement("UPDATE LARK.MISSIONS SET STATUS = ? WHERE ID = ?");
            ps.setInt(1, statusd);
            ps.setInt(2, idd);
            //ps.setString(1,status);
            //ps.setString(2,id);
            ps.executeUpdate();
            logger.info("updated mission table");
            ps = con.prepareStatement("select * from lark.missions WHERE status <> 2");
            rs = ps.executeQuery();
            while (rs.next ()){
                //Add records into data list
                Missions tmp = new Missions(rs.getInt("id"),rs.getString("desc"), rs.getInt("status"));
                dataList.add(tmp);
            }
            HttpSession session = request.getSession();
            Answers ans = new Answers("RefreshMission");
            session.setAttribute("Answers",ans);
            session.setAttribute("Missions",dataList);
            response.sendRedirect("solderhome.jsp");
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
