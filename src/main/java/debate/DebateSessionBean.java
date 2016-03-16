/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package debate;

import authentication.DebateUser;
import java.util.Date;
import java.util.List;
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
    @PersistenceContext(unitName = "OpenDebatePU")
    private EntityManager em;

    public Debate createDebate(DebateUser user, String topic, String description,
            String tags, Date closingDate) throws Exception {

        if (user == null || topic == null || description == null
                || closingDate == null || closingDate.before(new Date()) || topic.length() == 0
                || description.length() == 0) {
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

    public void closeDebate() {

    }

    public void deleteDebate() {

    }

    public Debate getDebateById(Long id) {

        Debate debates = em.find(Debate.class, id);
        return debates;
    }

    public List<Debate> getDebatesByUser(DebateUser user) {

        List<Debate> debates = em.createQuery("SELECT d "
                + "FROM Debate d "
                + "WHERE d.owner = :owner ORDER BY d.creationDate DESC")
                .setParameter("owner", user)
                .getResultList();

        return debates;
    }
}
