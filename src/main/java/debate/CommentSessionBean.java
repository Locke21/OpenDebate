/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package debate;

import authentication.DebateUser;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author D062575
 */
@Stateless
@LocalBean
public class CommentSessionBean {

    @PersistenceContext(unitName = "OpenDebatePU")
    private EntityManager em;

    public Comment createComment(DebateUser user, Long debId, String commentText, Long parentCommentId) throws Exception {
        Date currentTime = new Date();
        SimpleDateFormat myForm = new SimpleDateFormat("YYYY-MM-dd kk:mm:ss");
        String creationDateStr = myForm.format(currentTime);
        Date creationDate = myForm.parse(creationDateStr);

        Debate debate = em.find(Debate.class, debId);
        if (user == null || debate == null || commentText == null) {
            throw new IllegalArgumentException();
        }
        Comment newComment = new Comment();
        newComment.setOwner(user);
        newComment.setDebate(debate);
        newComment.setCreationDate(creationDate);
        newComment.setCommentText(commentText);
        newComment.setParentComment(parentCommentId);
        newComment.setLikes(0);
        newComment.setDislikes(0);

        em.persist(newComment);

        return newComment;
    }

    /**
     *
     * @param comment current comment
     * @param owner current user
     * @return true if comment was deleted
     */
    public boolean deleteComment(Comment comment, DebateUser owner) {

        if (comment.getOwner() == owner) {
            //try-catch block?
            em.remove(comment);
            return true;
        }
        return false;

    }

    public List<Comment> getComments(Debate d) {
        List<Comment> comments = em.createQuery("SELECT c "
                + "FROM Comment c "
                + "WHERE c.debate = :debateId")
                .setParameter("debateId", d)
                .getResultList();
        
        
        return comments;
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
