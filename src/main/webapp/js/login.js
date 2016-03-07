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
        return false;
    }

    var password = $("#newPasswordField");
    var password2 = $("#newPassword2Field");
    if (password.val() === ""){
        alert("Password can't be empty!");
        $("#newPassword").addClass("has-error");
        $("#newPasswordField").focus();
        return false;
    }
    if (password.val() !== password2.val()){
        alert("Password validation failed!");
        $("#newPassword").addClass("has-error");
        $("#newPassword2").addClass("has-error");
        password.val("");
        password2.val("");
        $("#newPasswordField").focus();
        return false;
    }
    alert("Created!");
    return true;

}
