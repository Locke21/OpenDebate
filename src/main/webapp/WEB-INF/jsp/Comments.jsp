<%-- 
    Document   : Comments
    Created on : 16.03.2016, 15:58:25
    Author     : fabi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<c:forEach items="${comments}" var="comment">
    <div id="${comment.id}" class="comment">
        <div class="commentHeader">
            <div id="commentUser">philgras</div>
            <div id="commentDate">${comment.creationDate}</div>
        </div>
        <div class="commentBody">${comment.commentText}</div>
        <div class="commentFooter">
            <a role="button" data-toggle="collapse" href="#${comment.id}_collapse" aria-expanded="true" >Answer</a>
            <div class="ratingTool">
                <span><i class="glyphicon glyphicon-chevron-down downVote"></i> 0 <i class="glyphicon glyphicon-chevron-up upVote"></i></span>
            </div>
        </div>
    </div>

    <div class="addCommentChild collapse" id="${comment.id}_collapse">
        <input id="${commen.id}_input" type="text" class="form-control" placeholder="addComment"/>
    </div> 
</c:forEach>
