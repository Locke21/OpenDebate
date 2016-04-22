
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import entity.Comment;
import entity.Debate;
import entity.DebateUser;
import java.text.SimpleDateFormat;
import entity.Rating;
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

    /**
     * Creates a new comment with an owner, debate, creationDate, commentText, rating, (parent, children).
     * Checks whether the new comment is going to be 
     * 1. a comment directly related to the debate or 
     * 2. a subcomment
     * If it is a subcomment a relation to its parent is being set.
     * If the new comment would be related to a subcomment, it will not be created.
     * 
     * 
     * @param user
     * @param debateId
     * @param commentText
     * @param parentId
     * @return a new comment
     * @throws IllegalArgumentException  if a comment does not belong to a user or a debate; if the commentText has no value.
     */
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
    
    
    public Collection<Rating> getRatings(Long commentId){
        Comment comment = em.find(Comment.class, commentId);
        return em.createQuery("SELECT r from Rating r WHERE r.comment = :comment")
                .setParameter("comment", comment).getResultList();
    }

    /**
     * Rates a comment if the User who rates is not the owner of the comment.
     * 
     * @param commentId
     * @param user
     * @param value
     * @return the current rating value of a comment
     */
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
     * Deletes a comment if the given user is the owner of the comment
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

    /**
     * Gets all the comments related to a given debate.
     * Assigns the corresponding rating to each comment.
     * 
     * @param d
     * @return a list of comments related to a given debate
     */
    public List<Comment> getComments(Debate d) {
        List<Comment> comments = em.createQuery("SELECT c "
                + "FROM Comment c "
                + "WHERE c.debate = :debateId AND c.parent is null")
                .setParameter("debateId", d)
                .getResultList();

        for(Comment c : comments){
            c.setRating(readCommentRating(c));
            for(Comment child : c.getChildren()){
                child.setRating(readCommentRating(child));
            }
        }
        return comments;
    }
    
    /**
     * Looking up a comment by ID
     * 
     * @param commentId
     * @return the comment corresponding to the given ID
     */
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
    
    /**
     * Reads the positive and negative rating values and calculates the difference between them as the current rating value.
     * 
     * @param c
     * @return the current rating value of a comment
     */
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
