/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package authentication;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.FlushModeType;
import javax.persistence.PersistenceContext;

/**
 *
 * @author fabi
 */
@Stateless(name="SignUpBean")
@LocalBean
public class SignUpBean {
    
    @PersistenceContext(unitName = "OpenDebatePU")
    private EntityManager em;
    

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    
    public void createUser(String name, String pwd){       
        
        DebateUser newDebateUser = new DebateUser();
        newDebateUser.setUsername(name);
        newDebateUser.setPassword(pwd);
        
        em.persist(newDebateUser); 
       
                                   
    }
}
