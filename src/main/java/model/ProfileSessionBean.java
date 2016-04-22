/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import entity.DebateUser;
import java.io.Serializable;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author fabi
 */
@Stateless
public class ProfileSessionBean {
    
     @PersistenceContext(unitName="OpenDebatePU") 
    private EntityManager em;
     
     public DebateUser getUserByName(String username){
         DebateUser dU = em.find(DebateUser.class, username);
         return dU;
     }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
