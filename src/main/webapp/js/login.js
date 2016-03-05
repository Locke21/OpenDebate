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
    } else if (user !== "") {

        alert("Created!");
        return true;
    }
}

