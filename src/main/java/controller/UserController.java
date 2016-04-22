/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.DebateUser;
import java.io.IOException;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.UserBean;

/**
 *
 * @author fabi
 */
@WebServlet(name = "UserController", urlPatterns = {"/servlets/UserController"})
public class UserController extends HttpServlet {

    public static final String URL_PATTERN = "/servlets/UserController";
    public static final String CONTEXT_NAME = "user";

    public static final String CONTEXT_LOGIN = "login";
    public static final String CONTEXT_SIGNUP = "signUp";
    public static final String CONTEXT_LOGOUT = "logout";

    @EJB
    private UserBean ub;
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        

        String context = request.getParameter("context");

        switch (context) {
            case CONTEXT_LOGIN:
                performLogin(request, response);
                break;
            case CONTEXT_LOGOUT:
                performLogout(request, response);
                break;
            case CONTEXT_SIGNUP:
                performSignUp(request,response);
                break;
        }

    }
    
    
    private void performLogin(HttpServletRequest request, HttpServletResponse response) throws IOException{
        String username = request.getParameter("user");
        String pwd = request.getParameter("pwd");

        HttpSession session = request.getSession();
        
        if (pwd != null && username != null) {
            DebateUser user = ub.authenticateClient(username, pwd);

            if (user != null) {

                session.setAttribute(UserBean.USER_ATTRIBUTE, user);
                session.setMaxInactiveInterval(30 * 60);

                Cookie userName = new Cookie(UserBean.USER_ATTRIBUTE, user.getUsername());
                userName.setMaxAge(30 * 60);
                response.addCookie(userName);

            }
            session.setAttribute("errorMsg", "The username or password is incorrect!");
            response.sendRedirect(FrontController.FRONT_PATH);
        } else {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }
    }
    
    private void performLogout(HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException{
        if (!new UserBean().isClientAuthenticated(request)) {
            response.sendRedirect(FrontController.FRONT_PATH);
        } else {
            
            //invalidate the session if exists
            HttpSession session = request.getSession(false);
            if (session != null) {
                //request.setAttribute("name", session.getAttribute(Authenticator.USERNAME_ATTRIBUTE));
                session.invalidate();
            }

            getServletContext().getRequestDispatcher(FrontController.PAGES_PREFIX + "/Logout.jsp").forward(request, response);
        }
    }
    
    private void performSignUp(HttpServletRequest request,HttpServletResponse response) throws IOException{
        String name = request.getParameter("newUser");
        String password = request.getParameter("newPassword");
        String password2 = request.getParameter("newPassword2");

        if (!name.isEmpty() && !password.isEmpty() && !password2.isEmpty() && password.equals(password2)) {

            ub.createUser(name, password);
            
        } else {
            System.out.println(name + " test" + password + "  " + password2);
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "You sent wrong parameters to our database!");
        }
    }

}
