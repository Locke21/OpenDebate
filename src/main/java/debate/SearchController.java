/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package debate;

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
 * @author D062572
 */
@WebServlet(name = "SearchServlet", urlPatterns = {"/servlets/SearchController"})
public class SearchController extends HttpServlet {

    
    public static final String URL_PATTERN = "/servlets/SearchController";
    public static final String CONTEXT_NAME = "search";
    
    private static final String SEARCH_PARAMETER = "pattern";
    
    @EJB
    SearchBean searchBean;
    
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
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
            String pattern = request.getParameter(SEARCH_PARAMETER);
            if(pattern == null){
                pattern = "";
            }
           

            request.setAttribute("results",searchBean.searchForDebates(pattern));
            getServletContext().getRequestDispatcher(FrontController.PAGES_PREFIX +"/SearchResult.jsp").forward(request, response);
            
    }

    

}
