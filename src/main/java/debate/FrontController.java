/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package debate;

import authentication.AuthenticationBean;
import java.io.IOException;
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
@WebServlet(name = "FrontController", urlPatterns = {"/pages/*"})

public class FrontController extends HttpServlet {

    private static final String ACTION_PARAMETER = "action";
    private static final String ACTION_LOGIN = "login";
    private static final String ACTION_SIGNUP = "signUp";
    private static final String ACTION_LOGOUT = "logout";
    private static final String ACTION_DEBATE = DebateController.CONTEXT_NAME;
    private static final String ACTION_COMMENT = CommentController.CONTEXT_NAME;
    
    private static final String CONTENT_PARAMETER = "content";
    
    public static final String PAGES_PREFIX = "/WEB-INF/jsp";
    public static final String FRONT_PATH = "/OpenDebate/pages/";
    
    
    @EJB
    private AuthenticationBean authenticator;
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
       
        if(authenticator.isClientAuthenticated(request)){
            
            request.setAttribute(CONTENT_PARAMETER, getJSPName(request.getParameter(CONTENT_PARAMETER)));
            
            
            getServletContext().getRequestDispatcher(PAGES_PREFIX+"/MainTemplate.jsp")
                                .forward(request,response);
        }else{
            getServletContext().getRequestDispatcher(PAGES_PREFIX+"/login.jsp")
                                .forward(request,response);                      
        }
        
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
        String action = req.getParameter(ACTION_PARAMETER);
        System.out.println(action);
        if(action == null){
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }else{
        
            switch(action){
            
                case ACTION_LOGIN:
                    getServletContext().getRequestDispatcher("/servlets/LoginController").forward(req, resp);
                    break;
                case ACTION_LOGOUT:
                    getServletContext().getRequestDispatcher("/servlets/LogoutController").forward(req, resp);
                    break;
                case ACTION_SIGNUP:
                    getServletContext().getRequestDispatcher("/servlets/SignUpController").forward(req, resp);
                    break;
                case ACTION_DEBATE:
                    getServletContext().getRequestDispatcher(DebateController.URL_PATTERN).forward(req, resp);
                    break;
                case ACTION_COMMENT:
                    getServletContext().getRequestDispatcher(CommentController.URL_PATTERN).forward(req, resp);
                    break;
                default:
                    resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
                    break;
            }
        
        }
        
    }

    private String getJSPName(String content){
        
        String JSPname = "home.jsp";
        
        if(content != null){
        
            switch(content){
            
                case "NewDebate":
                   JSPname = "NewDebate.jsp";
                   break;
                default:
                   
                    break;
            
            }
        
        }
        
        return JSPname;
    }
}
