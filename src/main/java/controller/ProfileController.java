/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import controller.FrontController;
import java.io.IOException;
import java.io.PrintWriter;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.ProfileSessionBean;

/**
 *
 * @author fabi
 */
@WebServlet(name = "ProfileController", urlPatterns = {"/servlets/ProfileController"})
public class ProfileController extends HttpServlet {

    public static final String URL_PATTERN = "/servlets/ProfileController";
    public static final String CONTEXT_NAME = "userProfile";

    private static final String COMMAND = "command";
    private static final String GET_PROFILE = "getUserProfile";
    
    @EJB
    ProfileSessionBean pB;

    /**
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String command = request.getParameter(COMMAND);

        if (command == null) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
        } else {
            switch (command) {
                case GET_PROFILE:
                    request.setAttribute(FrontController.INCL_PAGE_ATTR_NAME, "Profile.jsp");
                    getServletContext().getRequestDispatcher(FrontController.TEMPLATE_PAGE)
                            .forward(request, response);
                    break;
                default:
                    response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Wrong command");

            }
        }
    }
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
        
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
