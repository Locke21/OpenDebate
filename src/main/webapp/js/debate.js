/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


$(document).ready(function () {
    
    $("#commentInput").keydown(function(e){
        if(e.which == 13){
            $("#commentInputBtn").click();
        }
    });
    
    $('#commentInputBtn').on('click', function(){
        
        var urlParameter = window.location.search;
        var debateId = urlParameter.slice(urlParameter.search("id=") + 3);
        var nextParam = debateId.search("&");
        if(nextParam != -1){
            debateId = debateId.slice(0,nextParam);
        }
        if($("#commentInput").val() != ""){
            $.post('/OpenDebate/pages/', {
            
                action: 'comment',
                commentText: $("#commentInput").val(),
                debateId: debateId,
                command: $("#commentInputBtn").val()
            
            },function(){
                $("#commentInput").val("");
            });
        }
    });
    
    
});