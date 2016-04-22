/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import entity.DebateUser;
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
 * @author fabi
 */
@Stateless(name="UserBean")
@LocalBean
public class UserBean {
    
    public static final String USER_ATTRIBUTE = "user";
    
    @PersistenceContext(unitName = "OpenDebatePU")
    private EntityManager em;
    
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    public void createUser(String name, String pwd) {

        DebateUser newDebateUser = new DebateUser();
        newDebateUser.setUsername(name);
        newDebateUser.setPassword(pwd);

        em.persist(newDebateUser);
         
    }
    
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
            
            DebateUser user = (DebateUser) session.getAttribute(USER_ATTRIBUTE);
            Cookie loginCookie = null;
            Cookie[] cookies = clientRequest.getCookies();
            //check if username is set in the session context
            if(user != null && cookies != null){
                
                //find username cookie
                for(Cookie cookie : cookies){
                    if(cookie.getName().equals(USER_ATTRIBUTE)){
                        loginCookie = cookie;
                        break;
                    }
                }
                if(loginCookie != null){
                    isAuthenticated = loginCookie.getValue().equals(user.getUsername());
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
    public DebateUser authenticateClient(String user, String pwd){
    
        
        List<DebateUser> users = em.createQuery("SELECT u "
                                        + "FROM DebateUser u "
                                        + "WHERE u.username = :username AND u.password = :password")
                              .setParameter("username", user)
                              .setParameter("password", pwd)
                              .getResultList();
                
        if(users.size() == 1)
            return users.get(0);
        else
            return null;
        
    }
    

    
}
