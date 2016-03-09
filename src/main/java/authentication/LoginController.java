
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package authentication;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import debate.FrontController;
import javax.ejb.EJB;
/**
 *
 * @author D062572
 */
@WebServlet(name = "LoginController", urlPatterns = {"/servlets/LoginController"})
public class LoginController extends HttpServlet {
    @EJB
    private AuthenticationBean authenticator;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        if(authenticator.isClientAuthenticated(request)){
            response.sendRedirect(FrontController.FRONT_PATH);
        }else{
        
            String username = request.getParameter("user");
            String pwd = request.getParameter("pwd");
            HttpSession session = request.getSession();
            
            
            if(pwd != null && username != null){
                DebateUser user = authenticator.authenticateClient(username,pwd);
                
                if(user != null){
                    
                    
                    session.setAttribute(AuthenticationBean.USER_ATTRIBUTE, user);
                    session.setMaxInactiveInterval(30*60);

                    Cookie userName = new Cookie(AuthenticationBean.USER_ATTRIBUTE, user.getUsername());
                    userName.setMaxAge(30*60);
                    response.addCookie(userName);
                
                }
                session.setAttribute("errorMsg", "The username or password is incorrect!");
                response.sendRedirect(FrontController.FRONT_PATH);   
            }else{
                response.sendError(HttpServletResponse.SC_BAD_REQUEST);
            } 
        }
    }

}
