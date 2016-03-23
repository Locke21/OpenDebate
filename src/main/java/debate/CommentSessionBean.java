
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package debate;

import authentication.DebateUser;
import java.text.SimpleDateFormat;
import debate.rating.Rating;
import java.util.ArrayList;
import java.util.Collection;
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

    public Comment createComment(DebateUser user, Long debateId, String commentText, Long parentId) throws Exception {

        //argumente prüfen
        if (user == null || debateId == null || commentText == null) {
            throw new IllegalArgumentException();
        }
        
        //validieren
        Debate debate = em.find(Debate.class, debateId);
        Comment parent = null;
        if(parentId != null){
            parent = em.find(Comment.class, parentId);
        }
        if(debate == null && (parent != null || parentId == null)){
            //vllt bessere klasse
            throw new IllegalArgumentException();
        }
        
        //check child
        if(parent != null && parent.getParent() != null){
            throw new IllegalArgumentException(){
                @Override
                public String getMessage() {
                    return "Invalid parent";
                }
                    
            };
        }        
        //erzeugen
        Comment newComment = new Comment();
        newComment.setOwner(user);
        newComment.setDebate(debate);
        newComment.setCreationDate(new Date());
        newComment.setParent(parent);
        newComment.setCommentText(commentText);
        newComment.setChildren(new ArrayList<Comment>());
        newComment.setRating(0);
        
        if(parent != null){
            parent.getChildren().add(newComment);
        }
        

        em.persist(newComment);

        return newComment;
    }
    
    
//    public Comment createChildComment(DebateUser user, Long debId, String commentText, Comment parentComment) throws Exception {
//        Date currentTime = new Date();
//        SimpleDateFormat myForm = new SimpleDateFormat("YYYY-MM-dd kk:mm:ss");
//        String creationDateStr = myForm.format(currentTime);
//        Date creationDate = myForm.parse(creationDateStr);
//
//        Debate debate = em.find(Debate.class, debId);
//        if (user == null || debate == null || commentText == null) {
//            throw new IllegalArgumentException();
//        }
//        Comment newComment = new Comment();
//        newComment.setOwner(user);
//        newComment.setDebate(debate);
//        newComment.setCreationDate(creationDate);
//        newComment.setCommentText(commentText);
//        newComment.setParent(parentComment);
//        newComment.setRating(0);
//        
//        Collection<Comment> childComments = parentComment.getChildren();
//        childComments.add(newComment);
//        parentComment.setChildren(childComments);
//        String children = parentComment.getChildren().toString();
//        System.out.println("kinder "+children);
//        
//
//        em.persist(newComment);
//
//        return newComment;
//    }
    

    public long rateComment(Long commentId, DebateUser user, Rating.RatingValue value) {
        Comment comment = em.find(Comment.class, commentId);

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
                + "WHERE c.debate = :debateId AND c.parent is null")
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
        
        if (this.getCommentById(commentId).getParent() != null) {
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
