/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package debate;

import authentication.AuthenticationBean;
import authentication.LoginController;
import authentication.LogoutController;
import java.io.IOException;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import register.SignUpController;

/**
 *
 * @author D062572
 */
@WebServlet(name = "FrontController", urlPatterns = {"/pages/*"})

public class FrontController extends HttpServlet {

    public static final String ACTION_PARAMETER    = "action";
    private static final String ACTION_LOGIN        = LoginController.CONTEXT_NAME;
    private static final String ACTION_SIGNUP       = SignUpController.CONTEXT_NAME;
    private static final String ACTION_LOGOUT       = LogoutController.CONTEXT_NAME;
    private static final String ACTION_DEBATE       = DebateController.CONTEXT_NAME;
    private static final String ACTION_COMMENT      = CommentController.CONTEXT_NAME;
    private static final String ACTION_HOME         = HomeController.CONTEXT_NAME;
    private static final String ACTION_SEARCH       = SearchController.CONTEXT_NAME;

    public static final String PAGES_PREFIX         = "/WEB-INF/jsp";
    public static final String FRONT_PATH           = "/OpenDebate/pages/";
    public static final String TEMPLATE_PAGE        = PAGES_PREFIX + "/MainTemplate.jsp";
    public static final String INCL_PAGE_ATTR_NAME  = "content";

    @EJB
    private AuthenticationBean authenticator;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        if (authenticator.isClientAuthenticated(request)) {
            
            
            String action = request.getParameter(ACTION_PARAMETER);
            String servletUrl;
            if (action == null) {
                action = ACTION_HOME;
            } 

            switch (action) {
                
                case ACTION_HOME:
                    servletUrl = HomeController.URL_PATTERN; break;                            
                case ACTION_DEBATE:
                    servletUrl = DebateController.URL_PATTERN; break;
                default:
                    servletUrl = HomeController.URL_PATTERN; break; 
            }

            getServletContext().getRequestDispatcher(servletUrl)
                    .forward(request, response);
        } else {
            getServletContext().getRequestDispatcher(PAGES_PREFIX + "/login.jsp")
                    .forward(request, response);

        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        /**
         * check authentication
         */
        
        
        String action = req.getParameter(ACTION_PARAMETER);
        if (action == null) {
                resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
        } else {

            switch (action) {

                case ACTION_LOGIN:
                    getServletContext().getRequestDispatcher(LoginController.URL_PATTERN).forward(req, resp);
                    break;
                case ACTION_LOGOUT:
                    getServletContext().getRequestDispatcher(LogoutController.URL_PATTERN).forward(req, resp);
                    break;
                case ACTION_SIGNUP:
                    getServletContext().getRequestDispatcher(SignUpController.URL_PATTERN).forward(req, resp);
                    break;
                case ACTION_DEBATE:
                    getServletContext().getRequestDispatcher(DebateController.URL_PATTERN).forward(req, resp);
                    break;
                case ACTION_COMMENT:
                    getServletContext().getRequestDispatcher(CommentController.URL_PATTERN).forward(req, resp);
                    break;
                case ACTION_SEARCH:
                    getServletContext().getRequestDispatcher(SearchController.URL_PATTERN).forward(req, resp);
                    break;   
                default:
                    resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
                    break;
            }

        }
    }
}
