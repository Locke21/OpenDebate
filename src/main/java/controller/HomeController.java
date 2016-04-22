/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.Debate;
import model.DebateSessionBean;

import entity.DebateUser;
import java.io.IOException;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.UserBean;

/**
 *
 * @author D062572
 */
@WebServlet(name = "HomeController", urlPatterns = {"/servlets/HomeController"})
public class HomeController extends HttpServlet {

    public static final String URL_PATTERN = "/servlets/HomeController";
    public static final String CONTEXT_NAME = "home";

    @EJB
    private DebateSessionBean debateBean;

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Utilities util = new Utilities();
        HttpSession session = request.getSession();
        List<Debate> userDebates = debateBean.getDebatesByUser(
                (DebateUser) session.getAttribute(UserBean.USER_ATTRIBUTE));

        request.setAttribute("debates", util.sortDebatesByDate(userDebates));

        request.setAttribute(FrontController.INCL_PAGE_ATTR_NAME, "Home.jsp");
        getServletContext().getRequestDispatcher(FrontController.TEMPLATE_PAGE)
                .forward(request, response);

    }


}
