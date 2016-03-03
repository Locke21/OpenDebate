/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package authentication;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import debate.FrontController;

/**
 *
 * @author D062572
 */
@WebServlet(name = "LogoutController", urlPatterns = {"/servlets/LogoutController"})
public class LogoutController extends HttpServlet {


     /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        if(!Authenticator.isClientAuthenticated(request)){
            response.sendRedirect(FrontController.FRONT_PATH);
        }else{
        
            //invalidate the session if exists
            HttpSession session = request.getSession(false);
            if(session != null){
                request.setAttribute("name", session.getAttribute(Authenticator.USERNAME_ATTRIBUTE));
                session.invalidate();
            }
            
             getServletContext().getRequestDispatcher( FrontController.PAGES_PREFIX+"/logout.jsp").forward(request, response);
        }
        
    }


}
