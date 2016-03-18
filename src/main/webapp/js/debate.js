/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
var listenersReload = function () {
    $('.upVote').on('click', function () {
        console.log("abgeschickt");
        $.post('/OpenDebate/pages/', {
            action: 'comment',
            command: 'rate',
            rating: 'positive',
            comId: $(this).closest(".comment").attr('id')
        });
    });

    $('.downVote').on('click', function () {
        console.log("abgeschickt");
        $.post('/OpenDebate/pages/', {
            action: 'comment',
            command: 'rate',
            rating: 'negative',
            comId: $(this).closest(".comment").attr('id')
        });
    });
    
    $("focus, input").keydown(function (e) {
        if (e.which === 13)
        {
            if (e.target.id !== "commentInput")
            {
                var currentInput = e.target;
                var currentCommentId = currentInput.parentElement.id;
                currentCommentId = currentCommentId.slice(0, currentCommentId.search("_"));
                var urlParameter = window.location.search;
                var debateId = urlParameter.slice(urlParameter.search("id=") + 3);
                var nextParam = debateId.search("&");
                if (nextParam !== -1) {
                    debateId = debateId.slice(0, nextParam);
                }
                var currentCommentIdField = '#' + currentCommentId + '_input';
                if ($(currentCommentIdField).val() !== "") {
                    $.post('/OpenDebate/pages/', {
                        action: 'comment',
                        commentText: $(currentCommentIdField).val(),
                        debateId: debateId,
                        commentParentId: currentCommentId,
                        command: 'create'

                    }, function (data) {
                        $(currentCommentIdField).val("");
                        $('#comments').append(data);
                    });
                }
            }


        }
    });
};

$(document).ready(function () {

    $("#commentInput").keydown(function (e) {
        if (e.which === 13) {
            $("#commentInputBtn").click();
        }
    });

    $('#commentInputBtn').on('click', function () {

        var urlParameter = window.location.search;
        var debateId = urlParameter.slice(urlParameter.search("id=") + 3);
        var nextParam = debateId.search("&");
        if (nextParam !== -1) {
            debateId = debateId.slice(0, nextParam);
        }
        if ($("#commentInput").val() !== "") {
            $.post('/OpenDebate/pages/', {
                action: 'comment',
                commentText: $("#commentInput").val(),
                debateId: debateId,
                command: $("#commentInputBtn").val()

            }, function (data) {
                $("#commentInput").val("");
                $('#comments').append(data);
                listenersReload();
            });
        }
    });

    listenersReload();

    


});