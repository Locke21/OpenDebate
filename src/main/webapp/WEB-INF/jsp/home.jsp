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
        <h1>Hello ${sessionScope.user}</h1>
        
        <div class="col-lg-8">
            <form action="." method="post">
                <input type="hidden" name="action" value="logout">
                <input type="submit" value="Logout">
            </form>
        </div>
    </body>
</html>
