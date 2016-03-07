/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

    
    

function validateForm() {
    $('#newUser').bind('input', function() {
        $(this).next().stop(true, true).fadeIn(0).html('[input event fired!]: ' + $(this).val()).fadeOut(2000);
    });

    var user = document.forms["SignUp"]["newUser"].value;
    var input = document.getElementById("userNameField");
    if (user === "") {
        input.className = "has-error form-group";
        document.getElementById("newUser").className = "form-control";
        document.forms["SignUp"]["newUser"].placeholder = "Enter a username!";
        return false;
    }
        
    var password = $("#newPasswordField");
    var password2 = $("#newPassword2Field");
    if (password.val() === ""){
        alert("Password can't be empty!");
        $("#newPassword").addClass("has-error");
        return false;
    }
    if (password.val() !== password2.val()){
        alert("Password validation failed!");
        $("#newPassword").addClass("has-error");
        $("#newPassword2").addClass("has-error");
        password.val("");
        password2.val("");
        return false;
    }
    alert("Created!");
    return true;
}



