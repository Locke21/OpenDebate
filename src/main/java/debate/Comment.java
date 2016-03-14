package debate;


import authentication.DebateUser;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author D062575
 */
@Entity
@Table(name="COMMENT")
public class Comment {
    
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @ManyToOne(optional = false)
    private DebateUser owner;
    
    @ManyToOne(optional = false)
    private Debate debate;
    
    @Column(nullable = true )
    private int dislikes;
    
    @Column(nullable = true )
    private int likes;
        
    @Column(nullable = true)
    private Long parentCommentId;
    
    @Column(nullable = false)
    private String commentText;

    public String getCommentText() {
        return commentText;
    }

    public void setCommentText(String commentText) {
        this.commentText = commentText;
    }
    

    public Long getId() {
        return id;
    }

    public DebateUser getOwner() {
        return owner;
    }

    public void setOwner(DebateUser owner) {
        this.owner = owner;
    }

    public Debate getDebate() {
        return debate;
    }

    public void setDebate(Debate debate) {
        this.debate = debate;
    }

    public int getDislikes() {
        return dislikes;
    }

    public void setDislikes(int dislikes) {
        this.dislikes = dislikes;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

   
    public Long isParentComment() {
        return parentCommentId;
    }

    public void setParentComment(Long parentCommentId) {
        this.parentCommentId = parentCommentId;
    }

    /*
    @Override
    public int hashCode() {
        return super.hashCode(); //To change body of generated methods, choose Tools | Templates.
    }
    */

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Comment)) {
            return false;
        }
        Comment other = (Comment) object;
       if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;   
    }

    @Override
    public String toString() {
        return "comment.Comment[ id="+id+" ]";
    }
    
    
    

   
    
    
}
