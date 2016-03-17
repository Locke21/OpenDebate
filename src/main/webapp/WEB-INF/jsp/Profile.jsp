<%-- 
    Document   : Profile
    Created on : 17.03.2016, 20:11:17
    Author     : fabi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<div class="container">
    <div class="profileHeader">
        <div class="photo"></div>
        <div class="userName">${sessionScope.user.getUsername()}</div>
    </div>
    
</div>
