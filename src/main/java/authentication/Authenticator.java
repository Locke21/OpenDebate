/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package authentication;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author D062572
 */
public class Authenticator {
    
    public static final String USERNAME_ATTRIBUTE = "user";    
    
    /**
     * 
     * @param clientRequest ...
     * @return boolean value ...
     */
    public static boolean isClientAuthenticated(HttpServletRequest clientRequest){
    
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
    public static boolean authenticateClient(String user, String pwd){
    
        //hier session beans zur identifiezierung und DB-Abfrage etc
        
        return user.equals(User.DUMMY_NAME1) && pwd.equals(User.DUMMY_PW1) || 
                user.equals(User.DUMMY_NAME2) && pwd.equals(User.DUMMY_PW2);
        
    }
    

}
