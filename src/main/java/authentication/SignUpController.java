/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package authentication;

import debate.FrontController;
import java.io.IOException;
import java.io.PrintWriter;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author fabi
 */
@WebServlet(name = "SignUpController", urlPatterns = {"/servlets/SignUpController"})
public class SignUpController extends HttpServlet {
    
    @EJB
    private SignUpBean signUp;
   
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        
        String newUsername = request.getParameter("newUsername");
        String newPassword = request.getParameter("newPassword");
        
       signUp.addUser(newUsername, newPassword);
       
       response.sendRedirect(FrontController.FRONT_PATH);
    }


}
