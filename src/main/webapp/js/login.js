/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$('document').ready(function () {


    $('#submitBtn').click(function () {
        var validated = false;

        $('#newUser').bind('input', function () {

            if ($(this).val() === "") {
                $('#userNameField').addClass('has-error');

            } else {
                $('#userNameField').removeClass('has-error');

            }
        });
        $('#newPasswordField').bind('input', function () {

            if ($(this).val() === "") {
                $('#newPassword').addClass('has-error');

            } else {
                $('#newPassword').removeClass('has-error');

            }
        });
        $('#newPassword2Field').bind('input', function () {

            if ($(this).val() === "") {
                $('#newPassword2').addClass('has-error');

            } else {
                $('#newPassword2').removeClass('has-error');

            }
        });

        var user = document.forms["SignUp"]["newUser"].value;
        var userNameField = document.getElementById("userNameField");
        var password = $("#newPasswordField");
        var password2 = $("#newPassword2Field");

        if (user === "") {
            userNameField.className = "has-error form-group";
            document.getElementById("newUser").className = "form-control";
            document.forms["SignUp"]["newUser"].placeholder = "Enter a username!";
            validated = false;
        } else if (password.val() === "") {
            alert("Password can't be empty!");
            $("#newPassword").addClass("has-error");
            validated = false;
        } else if (password.val() !== password2.val()) {
            alert("Password validation failed!");
            $("#newPassword").addClass("has-error");
            $("#newPassword2").addClass("has-error");
            password.val("");
            password2.val("");
            validated = false;
        } else {
            alert("Created!");
            validated = true;
        }

        if (validated) {

            var username = $('#newUser').val();
            var password = $('#newPasswordField').val();
            var password2 = $('#newPassword2Field').val();

            $.post('/OpenDebate/pages/', {
                newUser: username,
                newPassword: password,
                newPassword2: password2,
                action: "signUp"
            },function(data){
                console.log(data);
            });/*.fail(function (data) {
                $('#testSpan').text(data);
                alert(data);
            }).done(function(data){
                window.location.assign('/OpenDebate/pages/');
                
              $('#testSpan').text(data);
            });
            */
        }


    });
});