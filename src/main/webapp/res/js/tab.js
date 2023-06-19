$(document).ready(function () {
    const tab_aule = $('#tab-aule');

    const tab_aule_btn = $('#tab-aule-btn');


    function hideAll() {
        clear();
        message("", "")
        tab_aule.hide();


        tab_aule_btn.addClass('btn-dark');

    }

    tab_aule_btn.click(function () {
        hideAll();
        tab_aule.show();
        if (tab_aule_btn.hasClass('btn-dark')) {
            tab_aule_btn.removeClass('btn-dark');
            tab_aule_btn.addClass('btn-warning');
        } else {
            tab_aule_btn.removeClass('btn-warning');
            tab_aule_btn.addClass('btn-dark');
        }
    });


});