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
        
     public Comment createComment(DebateUser user, Long debId, String commentText, Long parentCommentId) throws Exception
    {
        Debate debate = em.find(Debate.class, debId);
        if (user == null || debate == null || commentText == null) {
            throw new IllegalArgumentException();
        }
        Comment newComment = new Comment();
        newComment.setOwner(user);
        newComment.setDebate(debate);
        newComment.setCreationDate(new Date());
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
    public boolean deleteComment(Comment comment, DebateUser owner){
        
        if (comment.getOwner() == owner) {
         //try-catch block?
         em.remove(comment);
         return true;
        }
        return false;
               
    }
    
    public Comment getComment(Long commentId){
        
        //try-catch block?
        Comment comment = em.find(Comment.class, commentId);
        
        return comment;
    }
    
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
