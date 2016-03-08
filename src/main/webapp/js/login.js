/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */






function validateForm() {

$('#newUser').bind('input', function() {

    if($(this).val()===""){
        $('#userNameField').addClass('has-error');

    }else{
        $('#userNameField').removeClass('has-error');

    }
});
$('#newPasswordField').bind('input', function() {

    if($(this).val()===""){
        $('#newPassword').addClass('has-error');

    }else{
        $('#newPassword').removeClass('has-error');

    }
});
$('#newPassword2Field').bind('input', function() {

    if($(this).val()===""){
        $('#newPassword2').addClass('has-error');

    }else{
        $('#newPassword2').removeClass('has-error');

    }
});



    var user = document.forms["SignUp"]["newUser"].value;
    var userNameField = document.getElementById("userNameField");
    if (user === "") {
        userNameField.className = "has-error form-group";
        document.getElementById("newUser").className = "form-control";
        document.forms["SignUp"]["newUser"].placeholder = "Enter a username!";
        showNotification("error","Enter a username!")
        return false;
    }

    var password = $("#newPasswordField");
    var password2 = $("#newPassword2Field");
    if (password.val() === ""){
        showNotification("error","Password can't be empty!");
        $("#newPassword").addClass("has-error");
        $("#newPasswordField").focus();
        return false;
    }
    if (password.val() !== password2.val()){
        showNotification("error","Password validation failed!");
        $("#newPassword").addClass("has-error");
        $("#newPassword2").addClass("has-error");
        password.val("");
        password2.val("");
        $("#newPasswordField").focus();
        return false;
    }
    showNotification("success","User was created!");
    return true;

}

function showNotification(notificationTyp, notificationText){
    var dialogbox = $("#dialogbox");
    var dialogcontent = $("#dialogcontent");
    var leftSize = (window.innerWidth/2) - 200 + "px";
    var style = 'left:' + leftSize + '; top: 100px; width: 400px; position: fixed; z-index: 10;';
    dialogbox.hide();
    dialogbox.attr("style", style);
    
    switch(notificationTyp){
        case "success": dialogcontent.html('<div id="dialogcontent" class="alert alert-success"><strong>Success!</strong> ' + notificationText + '</div>');break;
        case "info": dialogcontent.html('<div id="dialogcontent" class="alert alert-info"><strong>Info!</strong> ' + notificationText + '</div>');break;
        case "warning": dialogcontent.html('<div id="dialogcontent" class="alert alert-warning"><strong>Warning!</strong> ' + notificationText + '</div>');break;
        case "error": dialogcontent.html('<div id="dialogcontent" class="alert alert-danger"><strong>Error!</strong> ' + notificationText + '</div>');break;
    }
    dialogbox.show();
}
