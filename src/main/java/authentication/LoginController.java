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
/**
 *
 * @author D062572
 */
@WebServlet(name = "LoginController", urlPatterns = {"/servlets/LoginController"})
public class LoginController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        if(Authenticator.isClientAuthenticated(request)){
            response.sendRedirect(FrontController.FRONT_PATH);
        }else{
        
            String user = request.getParameter("user");
            String pwd = request.getParameter("pwd");
            
            if(pwd != null && user != null){
                if(Authenticator.authenticateClient(user,pwd)){
                    
                    HttpSession session = request.getSession();
                    session.setAttribute(Authenticator.USERNAME_ATTRIBUTE, user);
                    session.setMaxInactiveInterval(30*60);

                    Cookie userName = new Cookie(Authenticator.USERNAME_ATTRIBUTE, user);
                    userName.setMaxAge(30*60);
                    response.addCookie(userName);
                
                }
                response.sendRedirect(FrontController.FRONT_PATH);   
            }else{
                response.sendError(HttpServletResponse.SC_BAD_REQUEST);
            } 
        }
    }

}
