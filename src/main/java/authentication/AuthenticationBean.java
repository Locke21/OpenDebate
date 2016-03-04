/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package authentication;

import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author D062572
 */
@Stateless(name="AuthenticationBean")
@LocalBean
public class AuthenticationBean{

    public static final String USERNAME_ATTRIBUTE = "user";    
    
    @PersistenceContext(unitName="OpenDebatePU") 
    private EntityManager em;
     
    /**
     * 
     * @param clientRequest ...
     * @return boolean value ...
     */
    public boolean isClientAuthenticated(HttpServletRequest clientRequest){
    
        boolean isAuthenticated = false;
        
        HttpSession session = clientRequest.getSession();
        //check if session is new
        if(!session.isNew()){
            
            String username = (String) session.getAttribute(USERNAME_ATTRIBUTE);
            Cookie loginCookie = null;
            Cookie[] cookies = clientRequest.getCookies();
            //check if username is set in the session context
            if(username != null && cookies != null){
                
                //find username cookie
                for(Cookie cookie : cookies){
                    if(cookie.getName().equals(USERNAME_ATTRIBUTE)){
                        loginCookie = cookie;
                        break;
                    }
                }
                if(loginCookie != null){
                    isAuthenticated = loginCookie.getValue().equals(username);
                }       
            }  
        }
        return isAuthenticated;
    }
    
    /**
     * 
     * @param user
     * @param pwd
     * @return 
     */
    public boolean authenticateClient(String user, String pwd){
    
        if(em == null){
            System.out.println("is null");
            return false;
        }
        List<DebateUser> users = em.createQuery("SELECT u "
                                        + "FROM DebateUser u "
                                        + "WHERE u.username = :username AND u.password = :password")
                              .setParameter("username", user)
                              .setParameter("password", pwd)
                              .getResultList();
                
        System.out.println(users);
        return users.size() == 1;
        
    }
    
    
}
