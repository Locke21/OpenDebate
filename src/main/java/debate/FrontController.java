/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package debate;

import authentication.AuthenticationBean;
import java.io.IOException;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author D062572
 */
@WebServlet(name = "FrontController", urlPatterns = {"/pages/*"})

public class FrontController extends HttpServlet {

    private static final String ACTION_PARAMETER = "action";
    private static final String ACTION_LOGIN = "login";
    private static final String ACTION_SIGNUP = "signUp";
    private static final String ACTION_LOGOUT = "logout";

    public static final String PAGES_PREFIX = "/WEB-INF/jsp";
    public static final String FRONT_PATH = "/OpenDebate/pages/";

    @EJB
    private AuthenticationBean authenticator;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        if (authenticator.isClientAuthenticated(request)) {
            getServletContext().getRequestDispatcher(PAGES_PREFIX + "/home.jsp")
                    .forward(request, response);
        } else {
            getServletContext().getRequestDispatcher(PAGES_PREFIX + "/login.jsp")
                    .forward(request, response);

        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String action = req.getParameter(ACTION_PARAMETER);
        if (action == null) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
        } else {

            switch (action) {

                case ACTION_LOGIN:

                    getServletContext().getRequestDispatcher("/servlets/LoginController").forward(req, resp);
                    break;
                case ACTION_LOGOUT:
                    getServletContext().getRequestDispatcher("/servlets/LogoutController").forward(req, resp);
                    break;
                case ACTION_SIGNUP:
                    getServletContext().getRequestDispatcher("/servlets/SignUpController").forward(req, resp);
                    break;
                default:
                    resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
                    break;
            }

        }

    }

}
