/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package debate;

import authentication.DebateUser;
import java.util.Date;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.naming.InitialContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;

/**
 *
 * @author D062572
 */
@Stateless
@LocalBean
public class DebateSessionBean {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @PersistenceContext(unitName="OpenDebatePU") 
    private EntityManager em;
    
    public Debate createDebate(DebateUser user, String topic, String description, 
                                                String tags, Date closingDate)throws Exception{
        
        if(user == null || topic == null || description == null || 
                closingDate == null || closingDate.before(new Date()) ){
            throw new IllegalArgumentException();
        }
        
        Debate newDebate = new Debate();
        newDebate.setTopic(topic);
        newDebate.setDescription(description);
        newDebate.setOwner(user);
        newDebate.setIsOpen(true);
        newDebate.setClicks(0);
        newDebate.setCreationDate(new Date());
        newDebate.setClosingDate(closingDate);
        newDebate.setTags(tags);
        
        
        em.persist(newDebate);
        
        
        return newDebate;      
    }
    
    public void closeDebate(){
    
    }
    
    public void deleteDebate(){
    
    }
    
    public Debate getDebate(){
    
        return null;
    }
}
