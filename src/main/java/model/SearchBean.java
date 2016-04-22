/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import entity.Debate;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author D062572
 */
@Stateless
public class SearchBean {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    
    @PersistenceContext(unitName="OpenDebatePU") 
    private EntityManager em;
    
    public List<Debate> searchForDebates(String pattern){
        pattern = "%"+pattern+"%";
        return em.createQuery("SELECT d "
                            + "FROM Debate d "
                            + "WHERE d.topic LIKE :pattern1 "
                               + "OR d.Tags LIKE :pattern2 "
                               + "OR d.description LIKE :pattern3")
                                    .setParameter("pattern1", pattern)
                                    .setParameter("pattern2", pattern)
                                    .setParameter("pattern3", pattern)
                                    .getResultList();
        
    }
    
}
