/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package debate.rating;

import authentication.DebateUser;
import debate.Comment;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 *
 * @author D062572
 */

@Entity
@Table(
    uniqueConstraints=
        @UniqueConstraint(columnNames={"USER_ID", "COMMENT_ID"})
)
public class Rating implements Serializable {
    
    public enum RatingValue{   
        POSITIVE,
        NEGATIVE   
    };
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne(optional = false)
    @JoinColumn(name = "USER_ID", nullable = false)
    private DebateUser user;
    
    @OneToOne(optional = false)
    @JoinColumn(name = "COMMENT_ID", nullable = false)
    private Comment comment;
    
    @Enumerated(EnumType.ORDINAL)
    private RatingValue ratingValue;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public DebateUser getUser() {
        return user;
    }

    public void setUser(DebateUser user) {
        this.user = user;
    }

    public Comment getComment() {
        return comment;
    }

    public void setComment(Comment comment) {
        this.comment = comment;
    }

    public RatingValue getRatingValue() {
        return ratingValue;
    }

    public void setRatingValue(RatingValue ratingValue) {
        this.ratingValue = ratingValue;
    }
    
    
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Rating)) {
            return false;
        }
        Rating other = (Rating) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "debate.rating.Rating[ id=" + id + " ]";
    }
    
}
