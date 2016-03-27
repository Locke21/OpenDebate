/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var listenersReload = function () {
    $(".counter").popover({
        html: true,
        content: "Feggit69<br>Pimmelberger<br>MavenMarco<br>RedHat<br>MisterX<br>Everybody<br>You",
        trigger: "manual"
    }).on("mouseenter", function () {
        $(this).popover("show");
        $('.popover').addClass('ratersList');
        $('.popover-content').addClass('ratersList');

        leavePopover();
    });
    
    


    $('.upVote').on('click', function () {

        var counter = $(this).siblings('.counter');
        var counterValue = parseInt(counter.html());
        $.post('/OpenDebate/pages/', {
            action: 'comment',
            command: 'rate',
            rating: 'positive',
            comId: $(this).closest(".comment").attr('id')
        }).done(function () {
            counter.html(++counterValue);



        }).fail(function () {
            console.log("Already commented");
        });
    });

    $('.downVote').on('click', function () {

        var counter = $(this).siblings('.counter');
        var counterValue = parseInt(counter.html());
        $.post('/OpenDebate/pages/', {
            action: 'comment',
            command: 'rate',
            rating: 'negative',
            comId: $(this).closest(".comment").attr('id')
        }).done(function () {
            counter.html(--counterValue);

        }).fail(function () {
            console.log("Already commented");
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
                        var childComment = '#'+currentCommentId+'_child';
                        localStorage.setItem("newComment", childComment);
                        
                        
                        window.location = (window.location.search);
                        

                    });
                }
            }


        }
    });
};

var leavePopover = function () {
    $(".popover").on("mouseleave", function () {
        $(this).hide();
    });
};

$(document).ready(function () {
    
    if($(document).find(localStorage.getItem("newComment")).val() !== undefined){
        var newComment = localStorage.getItem("newComment");
       
        $(document.body).animate({
            'scrollTop':   $(""+newComment+"").offset().top
        }, 1000);
        
        localStorage.removeItem("newComment");
    }
    
    

    $("#commentInput").keydown(function (e) {
        if (e.which === 13) {
            $("#commentInputBtn").click();
        }
    });

    $(".tags").on('click', function () {
        var search = $(this).html();
        $("#searchInput").val(search);
        $("#searchInput").trigger('input');




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
                $('.commentsInfo').hide();
                $('#comments').append(data);
                listenersReload();
            });
        }
    });

    listenersReload();
});
