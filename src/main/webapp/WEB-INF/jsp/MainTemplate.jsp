<%-- 
    Document   : MainTemplate
    Created on : Mar 7, 2016, 3:17:56 PM
    Author     : D062572
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>${sessionScope.user.getUsername()} - Home</title>
        <link type="text/css" rel="stylesheet" href="/OpenDebate/css/libs/bootstrap.css">
        <link type="text/css" rel="stylesheet" href="/OpenDebate/css/home.css">
        <script type="text/javascript" src="/OpenDebate/js/libs/jquery-1.11.0.js"></script>
        <script type="text/javascript" src="/OpenDebate/js/libs/bootstrap.js"></script>
        
    </head>
    <body>
        <nav class="navbar navbar-default navbar-static-top openDebateNav">
            <div class="container">
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
                        <span class="sr-only">Toggle navigation</span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                    <a class="navbar-brand" href="."><img style="max-width: 400px; height: 25px" src="/OpenDebate/img/logo_open_debate.png"/></a>
                </div>
                <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                    <form class="navbar-form navbar-left" role="search">
                        <div class="form-group">
                            <input type="text" class="form-control" placeholder="Search">
                        </div>
                        <button type="submit" class="btn btn-default">Go!</button>
                    </form>
                    
                    <form class="navbar-form navbar-right" action="." method="post">
                        <div class="form-group">
                            <input type="hidden" name="action" value="logout">
                            <button class="btn btn-default logoutBtn"type="submit">
                                <i class="glyphicon glyphicon-off"></i>
                        </div>
                    </form>
                    <p class="navbar-text navbar-right">Hello ${sessionScope.user.getUsername()}</p>
                </div>
            </div>
        </nav>
                
        <jsp:include page="${requestScope.content}"></jsp:include>
    </body>
</html>
