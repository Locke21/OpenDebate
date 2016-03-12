/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package debate;

import authentication.AuthenticationBean;
import authentication.DebateUser;
import debate.FrontController;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TreeMap;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


/**
 *
 * @author D062572
 */
@WebServlet(name = "HomeController", urlPatterns = {"/servlets/HomeController"})
public class HomeController extends HttpServlet {

    public static final String URL_PATTERN = "/servlets/HomeController";
    public static final String CONTEXT_NAME = "home";
    
    @EJB private DebateSessionBean debateBean;

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
        
        HttpSession session = request.getSession();
        List<Debate> userDebates = debateBean.getDebatesByUser(
                                    (DebateUser) session.getAttribute(AuthenticationBean.USER_ATTRIBUTE));
        
        request.setAttribute("debates", sortDebatesByDate(userDebates));
        
        request.setAttribute(FrontController.INCL_PAGE_ATTR_NAME, "home.jsp");
        getServletContext().getRequestDispatcher(FrontController.TEMPLATE_PAGE)
                    .forward(request, response);
       
    }

   private Map<String,List<Debate>> sortDebatesByDate(List<Debate> debates){
        
       Map<String,List<Debate>> result = new TreeMap<>( Collections.reverseOrder (
                                                new Comparator<String>() {

                                                    @Override
                                                    public int compare(String o1, String o2) {
                                                        try {
                                                            
                                                            SimpleDateFormat formatter  = new SimpleDateFormat("MMM yyyy", Locale.ENGLISH);
                                                            return formatter.parse(o1).compareTo(formatter.parse(o2));
                                                            
                                                        } catch (ParseException ex) {
                                                            return 0;
                                                        }
                                                }}
                                           ));
       
        SimpleDateFormat formatter  = new SimpleDateFormat("MMM yyyy", Locale.ENGLISH);
        String date;
        List<Debate> debatesSubList;
        
        for(Debate d : debates){
            date = formatter.format(d.getCreationDate());
            debatesSubList = result.get(date);
            
            if(debatesSubList == null){
                debatesSubList = new ArrayList<>();
                result.put(date, debatesSubList);
            }
            
            debatesSubList.add(d);
        }
        
        return result;
    }

}
