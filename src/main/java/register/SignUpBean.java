/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package register;

import authentication.DebateUser;
import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.FlushModeType;
import javax.persistence.PersistenceContext;

/**
 *
 * @author fabi
 */
@Stateless(name = "SignUpBean")
@LocalBean
public class SignUpBean {

    @PersistenceContext(unitName = "OpenDebatePU")
    private EntityManager em;

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    public boolean createUser(String name, String pwd) {

        boolean created = false;

        List<DebateUser> dublicatedUsers = em.createQuery("SELECT u "
                + "FROM DebateUser u "
                + "WHERE u.username = :newUser")
                .setParameter("newUser", name)
                .getResultList();

        if (dublicatedUsers.isEmpty()) {
            DebateUser newDebateUser = new DebateUser();
            newDebateUser.setUsername(name);
            newDebateUser.setPassword(pwd);

            em.persist(newDebateUser);
            created = true;
        } else if (!dublicatedUsers.isEmpty()) {
            created = false;
        }
        return created;

    }
}
