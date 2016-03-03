<%-- 
    Document   : login
    Created on : Mar 2, 2016, 2:42:23 PM
    Author     : D062572
--%>

<%@page import="authentication.Authenticator"%>
<%@page import="debate.FrontController"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Login</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        
        <link type="text/css" rel="stylesheet" href="/OpenDebate/css/libs/bootstrap.css">
        <link type="text/css" rel="stylesheet" href="/OpenDebate/css/libs/bootstrap-theme.css">
        <script type="text/javascript" src="/OpenDebate/js/libs/jquery-1.11.0.js"></script>
        <script type="text/javascript" src="/OpenDebate/js/libs/bootstrap.js"></script>
        
    </head>
    <body>
        <div class="container mainbox">
            <div class="row">
                <div class="col-lg-8 col-lg-offset-2">
                    <img class="center-block" src="/OpenDebate/img/logo_open_debate.png" alt="logo"/>
                    <form action="/OpenDebate/pages" method="post">
                        Username: <input type="text" name="user">
                        <br>
                        Password: <input type="password" name="pwd">
                        <br>
                        <input type="hidden" name="action" value="login">
                        <input class="btn-default btn" type="submit" value="Login">
                    </form>
                </div>
            </div>
        </div>
    </body>
</html>
