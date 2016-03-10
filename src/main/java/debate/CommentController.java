/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package debate;

import authentication.DebateUser;
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
 * @author D062575
 */
@WebServlet(name = "CommentController", urlPatterns = {"/servlets/CommentController"})
public class CommentController extends HttpServlet {

    public static final String URL_PATTERN = "/servlets/CommentController";
    public static final String CONTEXT_NAME = "comment";
    //commands
    private static final String COMMAND_CREATE = "create";
    private static final String COMMAND_READ = "read";
    private static final String COMMAND_DELETE = "delete";
    private static final String COMMAND = "command";

    private static final String ATTR_DEBID = "debateId";
    private static final String ATTR_COMID = "comId";
    private static final String ATTR_COMMTEXT = "commentText";
    private static final String ATTR_PARENTCOMID = "commentParentId";

    private static final String ATTR_USER = "user";
    private static final String ATTR_DEBATE = "debate";

    @EJB
    private CommentSessionBean commentBean;

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
        try {

            String name = (String) request.getParameter(COMMAND);
            short depth;
            Long parentCommentId;

            if (name != null) {

                switch (name) {
                    case COMMAND_CREATE:
                        parentCommentId = (Long) request.getSession().getAttribute(ATTR_PARENTCOMID);

                        if (parentCommentId == null) {
                            //commentBean.createComment((DebateUser) request.getSession().getAttribute(ATTR_USER), 
                            //      (Debate) request.getSession().getAttribute(ATTR_DEBATE), request.getParameter(ATTR_COMMTEXT), parentCommentId);
                            commentBean.createComment((DebateUser) request.getSession().getAttribute(ATTR_USER),
                                    Long.parseLong(request.getParameter(ATTR_DEBID)),
                                    (String) request.getParameter(ATTR_COMMTEXT));
                        }
                        break;
                    case COMMAND_DELETE:
                        Comment currComment = commentBean.getComment((Long) request.getSession().getAttribute(ATTR_COMID));
                        commentBean.deleteComment(currComment, (DebateUser) request.getSession().getAttribute(ATTR_USER));
                        break;
                    case COMMAND_READ:
                        commentBean.getComment(null);
                        break;
                    default:
                        break;

                }
            }
            response.sendRedirect(FrontController.FRONT_PATH);
        }
        catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);

        }
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
