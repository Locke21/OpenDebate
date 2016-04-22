package entity;


import entity.DebateUser;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

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
public class Comment implements Serializable{
    
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @ManyToOne(optional = false)
    private DebateUser owner;
    
    @ManyToOne(optional = false)
    private Debate debate;
    
    @Column(nullable=false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDate;
        
    @Column(nullable = false)
    private String commentText;
    
    @JoinColumn
    @ManyToOne
    private Comment parent;
    
    @OneToMany(mappedBy = "parent")
    private Collection<Comment> children;
    
    @Transient
    private long rating;

    public Comment getParent() {
        return parent;
    }

    public void setParent(Comment parent) {
        this.parent = parent;
    }

    public Collection<Comment> getChildren() {
        return children;
    }

    public void setChildren(Collection<Comment> children) {
        this.children = children;
    }

    
    public String getCommentText() {
        return commentText;
    }

    public void setCommentText(String commentText) {
        this.commentText = commentText;
    }
    
    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
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

    public long getRating() {
        return rating;
    }

    public void setRating(long rating) {
        this.rating = rating;
    }

    public Debate getDebate() {
        return debate;
    }

    public void setDebate(Debate debate) {
        this.debate = debate;
    }
   
//    public Long isParentComment() {
//        return parentCommentId;
//    }


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
