/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


$('document').ready(function () {


    $('.submit-span').on('click', function () {
        $(this).closest('form').submit();

    });

    $('.debateBox').on('click', function () {
        // $.get("/OpenDebate/pages",{content: "getDebate"});
        window.location.assign(".?action=debate&command=getDebate&id=" + this.id);
    });

    $(".debateMonth").stick_in_parent();

    $('#searchInput').popover({
        html: true,
        content: function () {
            return $('#searchContentWrapper').html();
        },
        trigger: 'manual'
    }).blur(function () {
        $(this).popover('hide');
    });

    $('#searchInput').on('input', function () {

        $.post('/OpenDebate/pages/', {
            action: 'search',
            pattern: $('#searchInput').val()

        }, function (data) {

            var popover = $('#searchInput').data('bs.popover');
            var tip = popover.tip();


            popover.options.content = data;

            var visible = popover && tip && tip.is(':visible');

            if (visible) {
                tip.find('.popover-content > *').html(data);
                $('.resultDebate').on('click', function () {
                    window.location.assign(".?action=debate&command=getDebate&id=" + this.id);
                });
            } else {
                popover.show();
            }


        });

    });


});

