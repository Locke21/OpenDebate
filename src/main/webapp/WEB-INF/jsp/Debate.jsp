<%-- 
    Document   : Debate
    Created on : 09.03.2016, 15:37:22
    Author     : fabi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html> 

<div class="container">
    <div class="row">
        <div class="col-md-12 titleHeader">
            ${requestScope.debate.getTopic()}
        </div>

    </div>
    <div class="row">
        <div class="genInfos col-md-12">
            <c:forEach items="${requestScope.debate.getTagList()}" var="tag" >
                <span class="tags">${tag}</span>
            </c:forEach> 
             
        </div>
    </div>

    <div class="row">
        <div class="mainInfos col-md-12">
            <div class="modal-header">
                Main Infos
            </div>
            <div class="modal-body">
                <i class="glyphicon glyphicon-user"></i> ${requestScope.debate.getOwner().getUsername()}</br>
                <i class="glyphicon glyphicon-calendar"></i> ${requestScope.debate.getCreationDate()}
            </div>
        </div>
    </div>
            <div class="row">
        <div class="col-md-12">
            <div class="modal-header">
                Discussion
            </div>
            <div class="commentBox">
            <div id="comments" class="modal-body ">
             
                <jsp:include page="Comments.jsp"></jsp:include>
               
            </div>
            </div>
            <c:if test="${comment.getDebate().getIsOpen() == true}">
                <div class="addComment">

                    <div class="input-group">
                        <input id="commentInput" type="text" class="form-control" placeholder="new comment..">
                        <span class="input-group-btn">
                            <button id="commentInputBtn" class="btn btn-default" value="create" type="button"><i class="glyphicon glyphicon-send"></i></button>
                        </span>
                    </div>


                </div>
            </c:if>
        </div>
            
    </div>

</div>


