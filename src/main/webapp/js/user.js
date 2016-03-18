/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


$('document').ready(function () {
    $('.nav-class a').click(function (e) {
        e.preventDefault();
        $(this).tab('show');
    });
});