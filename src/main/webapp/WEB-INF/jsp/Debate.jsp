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
        <div class="col-md-12 jumbotron">
            <h3>${requestScope.debate.getTopic()}</h3>
        </div>

    </div>
    <div class="row">
        <div class="genInfos col-md-12">
            <span class="tags">useful </span> 
            <span class="tags">mavenMarco </span> 
            <span class="tags">developer </span> 
            <span class="tags">fancyStuff </span> 
            <span class="tags">philgrasIsInDaHouse </span> 
        </div>
    </div>

    <div class="row">
        <div class="mainInfos col-md-4">
            <div class="modal-header">
                Main Infos
            </div>
            <div class="modal-body">
                <i class="glyphicon glyphicon-user"></i> ${requestScope.debate.getOwner().getUsername()}</br>
                <i class="glyphicon glyphicon-calendar"></i> ${requestScope.debate.getCreationDate()}
            </div>
        </div>
        <div class=" commentBox col-md-offset-1 col-md-7">
            <div class="modal-header">
                Discussion
            </div>
            <div class="modal-body">
                <div class="comment">
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


                <div class="comment">
                    <div class="commentHeader">
                        <div id="commentUser">philgras</div>
                        <div id="commentDate">01.01.2016 | 08:54</div>
                    </div>
                    <div class="commentBody">Ja ich stimme dir zu</div>
                    <div class="commentFooter"><a role="button" data-toggle="collapse" href="#addCommentChild" aria-expanded="true" >test</a></div>
                </div>

                <div class="commentChild">
                    <div class="commentHeader">
                        <div id="commentUser">philgras</div>
                        <div id="commentDate">01.01.2016 | 08:54</div>
                    </div>
                    <div class="commentBody">Ja ich stimme dir zu</div>
                    <div class="commentFooter">footer</div>
                </div>

                <div class="comment">
                    <div class="commentHeader">
                        <div id="commentUser">MavenMarco</div>
                        <div id="commentDate">01.01.2016 | 08:54</div>
                    </div>
                    <div class="commentBody">Ich finde die Idee super. Vielleicht können wir dazu ja mal ein Meeting aufsetzen!</div>
                    <div class="commentFooter">footer</div>
                </div>

                <div class="comment">
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


                <div class="comment">
                    <div class="commentHeader">
                        <div id="commentUser">philgras</div>
                        <div id="commentDate">01.01.2016 | 08:54</div>
                    </div>
                    <div class="commentBody">Ja ich stimme dir zu</div>
                    <div class="commentFooter"><a role="button" data-toggle="collapse" href="#addCommentChild" aria-expanded="true" >test</a></div>
                </div>

                <div class="commentChild">
                    <div class="commentHeader">
                        <div id="commentUser">philgras</div>
                        <div id="commentDate">01.01.2016 | 08:54</div>
                    </div>
                    <div class="commentBody">Ja ich stimme dir zu</div>
                    <div class="commentFooter">footer</div>
                </div>

                <div class="comment">
                    <div class="commentHeader">
                        <div id="commentUser">MavenMarco</div>
                        <div id="commentDate">01.01.2016 | 08:54</div>
                    </div>
                    <div class="commentBody">Ich finde die Idee super. Vielleicht können wir dazu ja mal ein Meeting aufsetzen!</div>
                    <div class="commentFooter">footer</div>
                </div>



            </div>

            <div class="addComment">

                <div class="input-group">

                    <form action="." method="post">
                        <div class="form-group">
                            <input type="text" class="form-control" placeholder="new comment..">
                            <span class="input-group-btn">
                                <button class="btn btn-default" type="submit"><i class="glyphicon glyphicon-send"></i></button>
                            </span>
                            <input type="hidden" name="action" value="comment">
                            <input type="hidden" name="command" value="create">
                        </div>
                    </form>


                </div>


            </div>

        </div>
    </div>

</div>

