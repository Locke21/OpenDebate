<%-- 
    Document   : login
    Created on : Mar 2, 2016, 2:42:23 PM
    Author     : D062572
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Login</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">

        <link type="text/css" rel="stylesheet" href="/OpenDebate/css/libs/bootstrap.css">
        <link type="text/css" rel="stylesheet" href="/OpenDebate/css/libs/bootstrap-theme.css">
        <link type="text/css" rel="stylesheet" href="/OpenDebate/css/login.css">
        
        <script type="text/javascript" src="/OpenDebate/js/libs/jquery-1.11.0.js"></script>
        <script type="text/javascript" src="/OpenDebate/js/login.js"></script>
        <script type="text/javascript" src="/OpenDebate/js/libs/bootstrap.js"></script>

    </head>
    <body class="center-block">
        <div class="container mainbox">


            <img class="center-block logo" src="/OpenDebate/img/logo_open_debate.png" alt="logo"/>
            <form action="/OpenDebate/pages" method="post">
                <div class="form-group">
                    <input class="form-control" type="text" name="user" placeholder="Username">
                </div>
                <div class="form-group">
                    <input class="form-control" type="password" name="pwd" placeholder="Password">
                </div>
                <input type="hidden" name="action" value="login">
                <div class="modal-footer">
                    <a href="#collapseExample" data-toggle="collapse" aria-expanded="false" aria-controls="collapseExample">Noch kein Mitglied?</a>       
                    <input class="btn-default btn" type="submit" value="Login">                                        
                </div>
            </form>

            <div class="collapse" id="collapseExample">
                <form action="/OpenDebate/pages" name="SignUp" onsubmit="return validateForm();" method="post">
                    <div id="userNameField" class="form-group">
                        <input id="newUser" class="form-control" type="text" name="newUser" placeholder="Your Username">
                        <span></span>
                    </div>
                    <div class="form-group">
                        <input id="newPassword" class="form-control" type="password" name="newPassword" placeholder="Your Password">
                    </div>
                    <div class="form-group">
                        <input id="newPassword2" class="form-control" type="password" name="newPassword2" placeholder="Repeat Password">
                    </div>
                    <input type="hidden" name="action" value="signUp">
                    <div class="modal-footer">

                        <input class="btn-success btn" type="submit" value="Sign Up">                                        
                    </div>
                </form>
            </div>


        </div>
    </body>
</html>
