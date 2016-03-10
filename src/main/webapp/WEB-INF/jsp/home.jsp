<%-- 
    Document   : home
    Created on : Feb 29, 2016, 2:29:29 PM
    Author     : D062572
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html> 

<div class="container mainbox">
    <div class="row">
        <div class="col-lg8">
            <ul class="nav nav-tabs openDebateNavTabs">
                <li class="active"><a data-toggle="tab" href="#MyDebates">My debates</a></li>
                <li><a data-toggle="tab" href="#HotTopics">Hot topics</a></li>
                <li><a data-toggle="tab" href="#Participations">Participations</a></li>
            </ul>

            <div class="tab-content">
              <div id="MyDebates" class="tab-pane fade in active">
                  <div class="container-fluid">
                    <h3>Overview of your Debates</h3>          
                    <form action="." method="get">
                      <input type="hidden" name="content" value="NewDebate">
                      <input class="btn btn-success"type="submit" value="New Debate">
                    </form>

                    <div class="row debateBox">
                        <div class="col-lg-9 debateTitle">
                            Thats the topic of a really interesting debate!
                        </div>
                        <div class="col-lg-3 debateInfo">
                            <div>
                                797 Clicks
                            </div>
                            <div>
                                82109 Comments
                            </div>
                        </div>
                    </div>

                    <c:forEach items="${requestScope.userDebates}" var="userDebate">
                        <div>
                            <h4>${userDebate.getTopic()}</h4>
                            <p>${userDebate.getDescription()}</p>
                            <p>Clicks: ${userDebate.getClicks()}</p>
                        </div>
                    </c:forEach>
                  </div>
              </div>
              <div id="HotTopics" class="tab-pane fade">
                <h3>Menu 1</h3>
                <p>Some content in menu 1.</p>
              </div>
              <div id="Participations" class="tab-pane fade">
                <h2>Create comment</h2>
                <form action="." method="post">
                    <div class="form-group">
                        <input class="form-control" type="text" name="debateId" placeholder="DebateID">
                        <input class="form-control" type="text" name="commentText" placeholder="comment">
                        <input type="hidden" name="action" value="comment">
                        <input type="hidden" name="command" value="create">
                        <input class="btn btn-default"type="submit" value="Create">
                    </div>
                </form>
              </div>
            </div>
        </div>

    </div>
</div>
        

