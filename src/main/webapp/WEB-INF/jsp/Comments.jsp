<%-- 
    Document   : Comments
    Created on : 16.03.2016, 15:58:25
    Author     : fabi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<c:forEach items="${comments}" var="comment">
    <div class="comment">
        <div class="commentHeader">
            <div id="commentUser">philgras</div>
            <div id="commentDate">${comment.creationDate}</div>
        </div>
        <div class="commentBody">${comment.commentText}</div>
        <div class="commentFooter"><a role="button" data-toggle="collapse" href="#${comment.id}" aria-expanded="true" >Answer</a></div>
    </div>

    <div class="addCommentChild collapse" id="${comment.id}">
        <input type="text" class="form-control" placeholder="addComment"/>
    </div> 
</c:forEach>
