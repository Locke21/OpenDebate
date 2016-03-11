/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package register;

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

    public static final String URL_PATTERN = "/servlets/SignUpController";
    public static final String CONTEXT_NAME = "signUp";
    
    @EJB
    private SignUpBean signUp;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("newUser");
        String password = request.getParameter("newPassword");
        String password2 = request.getParameter("newPassword2");
        System.out.println("neuer Name:" + username);
        System.out.println("neues PW:" + password);
        System.out.println("neues PW2:" + password2);

        if (!username.isEmpty() && !password.isEmpty() && !password2.isEmpty()) {

            if (signUp.createUser(username, password)) {
                PrintWriter out = response.getWriter();
                response.setContentType("text/plain");
                out.write(""+FrontController.FRONT_PATH);
                out.flush();
                out.close();

            } else {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST);
            }

        } else {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "You sent wrong parameters to our database!");
        }

    }

}
