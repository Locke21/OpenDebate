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
        <ul class="nav nav-tabs" role="tablist">
            <li role="presentation" class="active"><a href="#home" aria-controls="home" role="tab" data-toggle="tab">Home</a></li>
            <li role="presentation"><a href="#profile" aria-controls="profile" role="tab" data-toggle="tab">Profile</a></li>
            <li role="presentation"><a href="#messages" aria-controls="messages" role="tab" data-toggle="tab">Messages</a></li>
            <li role="presentation"><a href="#settings" aria-controls="settings" role="tab" data-toggle="tab">Settings</a></li>
        </ul>
    </div>

    <div class="tab-content">
        <div role="tabpanel" class="tab-pane active" id="home">Home</div>
        <div role="tabpanel" class="tab-pane" id="profile">Profile</div>
        <div role="tabpanel" class="tab-pane" id="messages">...</div>
        <div role="tabpanel" class="tab-pane" id="settings">...</div>
    </div>

</div>
