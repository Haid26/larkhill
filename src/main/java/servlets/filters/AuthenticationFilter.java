package servlets.filters;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import util.User;

@WebFilter("/AuthenticationFilter")
public class AuthenticationFilter implements Filter {

    private Logger logger = Logger.getLogger(AuthenticationFilter.class);

    public void init(FilterConfig fConfig) throws ServletException {
        logger.info("AuthenticationFilter initialized");
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        String uri = req.getRequestURI();
        logger.info("Requested Resource::"+uri);
        StringBuffer url =  req.getRequestURL();
        String requesturl = url.toString();
        boolean flag = false;
        HttpSession session = req.getSession(false);

        if(requesturl.endsWith(".css") || requesturl.endsWith(".png")) {
            chain.doFilter(request, response);
        }
        else {
            if (session == null) {
                flag = true;
                if (requesturl.endsWith("login.html") || requesturl.endsWith("Login")) {
                    chain.doFilter(request, response);
                } else {
                    logger.error(requesturl);
                    res.sendRedirect("login.html");
                }
            } else {
                User user = (User) session.getAttribute("User");
                if (user == null) {
                    flag = true;
                    if (requesturl.endsWith("login.html") || requesturl.endsWith("Login")) {
                        chain.doFilter(request, response);

                    } else {
                        logger.error(requesturl);
                        res.sendRedirect("login.html");
                    }
                }
                if (requesturl.endsWith("Logout"))
                    chain.doFilter(request, response);
                else {



                    //                ADMiN
                    if (user.getRole().endsWith("Admin")) {
                        flag = true;
                        if (!(requesturl.endsWith("register.html") || requesturl.endsWith("Register"))) {
                            res.sendRedirect("register.html");
                        } else
                            chain.doFilter(request, response);
                    }



                    //              PARTY
                    if (user.getRole().endsWith("gov")) {
                        flag = true;
                        if (!requesturl.endsWith("govhome.jsp")) {
                            res.sendRedirect("govhome.jsp");
                        } else
                            chain.doFilter(request, response);
                    }



                    //                      SOLDER

                    if (user.getRole().endsWith("sol")) {
                        flag = true;
                        if (!(requesturl.endsWith("solderhome.jsp") || requesturl.endsWith("RefreshMission") || requesturl.endsWith("MissionUpdate"))) {
                            res.sendRedirect("solderhome.jsp");
                        } else
                            chain.doFilter(request, response);
                    }



                    //                      SCIENTIST

                    if (user.getRole().endsWith("sci")) {
                        flag = true;
                        if (!(requesturl.endsWith("scientisthome.jsp")||requesturl.endsWith("RefreshProd")||requesturl.endsWith("UpdateProd")||requesturl.endsWith("RefreshProdReq")|| requesturl.endsWith("UpdateProdReq"))) {
                            res.sendRedirect("scientisthome.jsp");
                        } else
                            chain.doFilter(request, response);
                    }



                    //                    SELLER

                    if (user.getRole().endsWith("sel")) {
                        flag = true;
                        if (!(requesturl.endsWith("sellerhome.jsp")|| requesturl.endsWith("RefreshProd")||requesturl.endsWith("UpdateProd"))) {
                            res.sendRedirect("sellerhome.jsp");
                        } else
                            chain.doFilter(request, response);
                    }



                    //                    PEOPLE

                    if (user.getRole().endsWith("pep")) {
                        flag = true;
                        if (!(requesturl.endsWith("peoplehome.jsp") || requesturl.endsWith("Feedback"))) {
                            res.sendRedirect("peoplehome.jsp");
                        } else
                            chain.doFilter(request, response);
                    }
                    logger.info(user.getRole() + "and " + requesturl);
                }
            }
        }
        //if(!flag)
            //logger.error("flag ne vipal");
        /*if(session == null && !(uri.endsWith("html") || uri.endsWith("Login"))){
            logger.error("Unauthorized access request");
            res.sendRedirect("login.html");
        }else{
            // pass the request along the filter chain
            chain.doFilter(request, response);
        }*/


    }

    public void destroy() {
        //close any resources here
    }

}