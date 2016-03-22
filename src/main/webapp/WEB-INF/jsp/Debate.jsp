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
            <span class="tags">useful</span> 
            <span class="tags">MavenMarco</span> 
            <span class="tags">developer</span> 
            <span class="tags">fancyStuff</span> 
            <span class="tags">philgrasIsInDaHouse</span> 
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
                <!--<div class="comment">
                    <div class="commentHeader">
                        <div id="commentUser">philgras</div>
                        <div id="commentDate">01.01.2016 | 08:54</div>
                    </div>
                    <div class="commentBody">So ein Schwachsinn..</div>
                    <div class="commentFooter"><a role="button" data-toggle="collapse" href="#addCommentChild" aria-expanded="true" >answer</a></div>
                </div>

                <div class="addCommentChild collapse" id="addCommentChild">
                    <input type="text" class="form-control" placeholder="addComment"/>
                </div> 


                

                <div class="commentChild">
                    <div class="commentHeader">
                        <div id="commentUser">philgras</div>
                        <div id="commentDate">01.01.2016 | 08:54</div>
                    </div>
                    <div class="commentBody">Ja ich stimme dir zu</div>
                    <div class="commentFooter">footer</div>
                </div>-->

                
                <jsp:include page="Comments.jsp"></jsp:include>
               

            </div>
            </div>

            <div class="addComment">

                <div class="input-group">
                    <input id="commentInput" type="text" class="form-control" placeholder="new comment..">
                    <span class="input-group-btn">
                        <button id="commentInputBtn" class="btn btn-default" value="create" type="button"><i class="glyphicon glyphicon-send"></i></button>
                    </span>
                </div>


            </div>

        </div>
            
    </div>

</div>


