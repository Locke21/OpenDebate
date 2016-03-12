/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package debate;

import authentication.DebateUser;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
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
@WebServlet(name = "DebateController", urlPatterns = {"/servlets/DebateController"})
public class DebateController extends HttpServlet {

    
    public static final String URL_PATTERN = "/servlets/DebateController";
    public static final String CONTEXT_NAME = "debate";
     //commands
    public static final String COMMAND_PARAMETER = "command";
    public static final String GET_COMMAND_NEW_DEBATE = "newDebate";
    public static final String GET_COMMAND_GET_DEBATE = "getDebate";

    public static final String POST_COMMAND_CREATE = "create";
    public static final String POST_COMMAND_READ = "read";
    public static final String POST_COMMAND_DELETE = "delete";
    

    private static final String ATTR_TOPIC = "topic";
    private static final String ATTR_DESC = "description";
    private static final String ATTR_TAGS = "tags";
    private static final String ATTR_DATE = "closingDate";

    @EJB
    private DebateSessionBean debateBean;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        String command = request.getParameter(COMMAND_PARAMETER);
        if(command == null){
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }else{
        
            switch(command){
            
                case GET_COMMAND_NEW_DEBATE:
                    request.setAttribute(FrontController.INCL_PAGE_ATTR_NAME, "NewDebate.jsp");
                    getServletContext().getRequestDispatcher(FrontController.TEMPLATE_PAGE)
                                .forward(request, response);
                    break;
                case GET_COMMAND_GET_DEBATE:
                    request.setAttribute(FrontController.INCL_PAGE_ATTR_NAME, "Debate.jsp");
                    getServletContext().getRequestDispatcher(FrontController.TEMPLATE_PAGE)
                                .forward(request, response);
                    break;
                default:
                    response.sendError(HttpServletResponse.SC_BAD_REQUEST,"Wrong command");
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
        try{
            
            String name = (String) request.getParameter(COMMAND_PARAMETER);
            switch(name){
                case POST_COMMAND_CREATE:
                    debateBean.createDebate((DebateUser) request.getSession().getAttribute("user"),
                                            (String) request.getParameter(ATTR_TOPIC), 
                                            (String) request.getParameter(ATTR_DESC),
                                            (String) request.getParameter(ATTR_TAGS),
                                            new SimpleDateFormat("dd.MM.yyyy").parse((String) request.getParameter(ATTR_DATE)));
                    break;
                case POST_COMMAND_READ:
                    break;
                case POST_COMMAND_DELETE:
                    break;
                default:
                    break;
            }
            response.sendRedirect(FrontController.FRONT_PATH);
        }catch(Exception e){
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_BAD_REQUEST,"Wrong command");
            
        }
        
        
        
    }
    
    

}
