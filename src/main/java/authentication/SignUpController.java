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
import javax.servlet.http.HttpSession;

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
       String username = request.getParameter("newUser");
       String password = request.getParameter("newPassword");
       String password2 = request.getParameter("newPassword2");
       
       if(username!=null && password!=null && password2!=null){
           try{
               signUp.createUser(username,password);
           }
           catch(javax.ejb.EJBException ex){
               HttpSession session = request.getSession();
               session.setAttribute("PostFailedMsg", "Username already exists.");
               
           }
           response.sendRedirect(FrontController.FRONT_PATH);
       }
       else{
           HttpSession session = request.getSession();
               session.setAttribute("PostFailedMsg", "enter a username!");
               response.sendRedirect(FrontController.FRONT_PATH);
       }
       
       
       
    }


}
