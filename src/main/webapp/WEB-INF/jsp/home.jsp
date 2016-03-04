<%-- 
    Document   : home
    Created on : Feb 29, 2016, 2:29:29 PM
    Author     : D062572
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>${sessionScope.user} - Home</title>
        <link type="text/css" rel="stylesheet" href="/OpenDebate/css/libs/bootstrap.css">
        <script type="text/javascript" src="/OpenDebate/js/libs/jquery-1.11.0.js"></script>
        <script type="text/javascript" src="/OpenDebate/js/libs/bootstrap.js"></script>
        
    </head>
    <body>
        <nav class="navbar navbar-default">
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
                        <button type="submit" class="btn btn-default">Submit</button>
                    </form>
                    
                    <form class="navbar-form navbar-right" action="." method="post">
                        <div class="form-group">
                            <input type="hidden" name="action" value="logout">
                            <input class="btn btn-default"type="submit" value="Logout">
                        </div>
                    </form>
                    <p class="navbar-text navbar-right">Hello ${sessionScope.user}</p>
                </div>
            </div>
        </nav>
        <div class="container mainbox">
            <div class="row">
                <div class="col-lg-6">
                    <h2>Create debate</h2>
                    <form action="." method="post">
                        <div class="form-group">
                          
                            <input type="hidden" name="action" value="logout">
                            <input class="form-control" type="text" name="topic" placeholder="Topic">
                            <input class="form-control" type="text" name="description" placeholder="Description">
                            <input class="form-control" type="text" name="tags" placeholder="Tags">
                            <input class="form-control" type="date" name="closingDate" placeholder="1.1.2017">
                            <input class="btn btn-default"type="submit" value="Create">
                        </div>
                    </form>
                </div>
                <div class="col-lg-6">
                    <h2>Create comment</h2>
                    <form action="." method="post">
                        <div class="form-group">
                            <input class="form-control" type="text" name="debateId" placeholder="DebateID">
                            <input class="form-control" type="text" name="comment" placeholder="comment">
                            <input type="hidden" name="action" value="logout">
                            <input class="btn btn-default"type="submit" value="Create">
                        </div>
                    </form>
                </div>
            </div>
        </div>
        
    </body>
</html>
