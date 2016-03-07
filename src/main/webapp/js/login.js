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
$('#newPassword').bind('input', function() {
   
    if($(this).val()===""){
        $('#passwordField').addClass('has-error');
       
    }else{
        $('#passwordField').removeClass('has-error');
        
    }
});


    var user = document.forms["SignUp"]["newUser"].value;
    var userNameField = document.getElementById("userNameField");
    if (user === "") {
        userNameField.className = "has-error form-group";
        document.getElementById("newUser").className = "form-control";
        document.forms["SignUp"]["newUser"].placeholder = "Enter a username!";
        return false;
    } else if (user !== "") {

        alert("Created!");
        return true;
    }
    return false;
}

