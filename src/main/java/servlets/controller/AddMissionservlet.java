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

@WebServlet(name = "AddMission", urlPatterns = { "/AddMission" })
public class AddMissionservlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    static Logger logger = Logger.getLogger(RegisterServlet.class);
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String desc = request.getParameter("desc");

        String errorMsg = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Missions> dataList = new ArrayList();
        if(desc == null || desc.equals("")){
            errorMsg = "desc can't be  empty.";
        }

        if(errorMsg != null){
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/govhome.jsp");
            PrintWriter out= response.getWriter();
            out.println("<font color=red>"+errorMsg+"</font>");
            rd.include(request, response);
        }else{

            Connection con = (Connection) getServletContext().getAttribute("DBConnection");

            try {
                ps = con.prepareStatement("insert into LARK.missions(desc, status) values (?,?)");
                ps.setString(1, desc);
                ps.setInt(2, 0);
                ps.execute();
                logger.info("Add requesst");

                ps = con.prepareStatement("select * from lark.missions  order by id");
                rs = ps.executeQuery();
                while (rs.next ()){
                    //Add records into data list
                    Missions tmp = new Missions(rs.getInt("id"),rs.getString("desc"), rs.getInt("status"));
                    dataList.add(tmp);
                }
                HttpSession session = request.getSession();
                Answers ans = new Answers("AddMission");
                session.setAttribute("Answers",ans);
                session.setAttribute("Missions",dataList);
                response.sendRedirect("govhome.jsp");

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
