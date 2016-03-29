
<%-- 
    Document   : home
    Created on : Feb 29, 2016, 2:29:29 PM
    Author     : D062572
--%>

<%@page import="debate.FrontController" %>
<%@page import="debate.DebateController" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html> 
<div class="container mainbox">
    <div class="row">
        <div class="col-lg-8">
            <ul class="nav nav-tabs openDebateNavTabs">
                <li class="active"><a data-toggle="tab" href="#MyDebates">My debates</a></li>
                <li><a data-toggle="tab" href="#HotTopics">Hot topics</a></li>
                <li><a data-toggle="tab" href="#Participations">Participations</a></li>
            </ul>

            <div class="tab-content">
                <div id="MyDebates" class="tab-pane fade in active">
                    <div class="container">
                        <div class="header">
                            <h3>Overview of your Debates</h3>          
                            <form action="." method="get">
                                <input type="hidden" name="<%=FrontController.ACTION_PARAMETER%>" value="<%=DebateController.CONTEXT_NAME%>">
                                <input type="hidden" name="<%=DebateController.COMMAND_PARAMETER%>" value="<%=DebateController.GET_COMMAND_NEW_DEBATE%>">
                                <span class="submit-span"><i class="glyphicon glyphicon-plus-sign"></i></span>
                            </form>
                        </div>
                        <div class="debatesOuter">
                            
                            <c:forEach items="${requestScope.debates}" var="subdebates">
                                <div class="row debates">
                                    <div class="col-md-2 debateMonth">
                                        ${subdebates.key}
                                    </div>
                                    <div class="col-md-10">
                                        <c:forEach items="${subdebates.value}" var="debate">
                                            <div id="${debate.getId()}" class="debateBox">
                                                <div class="debateTitle">${debate.getTopic()}</div>
                                                <div class="debateInfo">
                                                    Views: ${debate.getClicks()}<br>
                                                    Owner: ${debate.getOwner().getUsername()}
                                                </div>
                                            </div>                                      
                                        </c:forEach>
                                    </div>
                                </div>
                            </c:forEach>                                                                  
                        </div>
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

