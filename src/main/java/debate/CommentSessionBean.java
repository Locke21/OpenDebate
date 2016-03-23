
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package debate;

import authentication.DebateUser;
import java.text.SimpleDateFormat;
import debate.rating.Rating;
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

        em.persist(newComment);

        return newComment;
    }

    public long rateComment(Long commentId, DebateUser user, Rating.RatingValue value) {
        Comment comment = em.find(Comment.class, commentId);
        if(user.getUsername().equals(comment.getOwner().getUsername())){
            throw new IllegalArgumentException();
        }
        Rating rating = new Rating();
        rating.setComment(comment);
        rating.setUser(user);
        rating.setRatingValue(value);

        em.persist(rating);
        
        return readCommentRating(comment);
        
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

        for(Comment c : comments){
            c.setRating(readCommentRating(c));
        }
        return comments;
    }
    
    public Comment getCommentById(Long commentId){
        
        return em.find(Comment.class, commentId);
    }
    
    /**
     * Checks whether a comment is a comment related to a debate 
     * or to a comment (= has a parent)
     * 
     * @param commentId
     * @return true if comment has parentComment
     */
    public boolean hasParentComment(Long commentId){
        
        if (this.getCommentById(commentId).getParentCommentId() != null) {
            return true;
        }
        
        return false;
    }
    
    
    private long readCommentRating(Comment c) {
        
        String query = "SELECT count(r) FROM Rating r WHERE r.ratingValue = :rating AND r.comment = :comment";
        
        long rating = (Long) em.createQuery(query)
                .setParameter("rating", Rating.RatingValue.POSITIVE)
                .setParameter("comment", c)
                .getSingleResult();
        
        rating -= (Long) em.createQuery(query)
                .setParameter("rating", Rating.RatingValue.NEGATIVE)
                .setParameter("comment", c)
                .getSingleResult();
        
        return rating;
        
    }
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
