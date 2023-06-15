$(document).ready(function () {
    const tab_aule = $('#tab-aule');
    const tab_autori = $('#tab-autori');
    const tab_dischi = $('#tab-dischi');
    const tab_statistiche = $('#tab-statistiche');

    const tab_aule_btn = $('#tab-aule-btn');
    const tab_autori_btn = $('#tab-autori-btn');
    const tab_dischi_btn = $('#tab-dischi-btn');
    const tab_statistiche_btn = $('#tab-statistiche-btn');

    tab_autori.hide();
    tab_dischi.hide();
    tab_statistiche.hide();

    function hideAll() {
        clear();
        message("", "")
        tab_aule.hide();
        tab_autori.hide();
        tab_dischi.hide();
        tab_statistiche.hide();

        tab_aule_btn.addClass('btn-dark');
        tab_autori_btn.addClass('btn-dark');
        tab_dischi_btn.addClass('btn-dark');
        tab_statistiche_btn.addClass('btn-dark');
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

    tab_autori_btn.click(function () {
        hideAll();
        tab_autori.show();
        if (tab_autori_btn.hasClass('btn-dark')) {
            tab_autori_btn.removeClass('btn-dark');
            tab_autori_btn.addClass('btn-warning');
        } else {
            tab_autori_btn.removeClass('btn-warning');
            tab_autori_btn.addClass('btn-dark');
        }
    });

    tab_dischi_btn.click(function () {
        hideAll();
        tab_dischi.show();
        if (tab_dischi_btn.hasClass('btn-dark')) {
            tab_dischi_btn.removeClass('btn-dark');
            tab_dischi_btn.addClass('btn-warning');
        } else {
            tab_dischi_btn.removeClass('btn-warning');
            tab_dischi_btn.addClass('btn-dark');
        }
    });

    tab_statistiche_btn.click(function () {
        hideAll();
        tab_statistiche.show();
        if (tab_statistiche_btn.hasClass('btn-dark')) {
            tab_statistiche_btn.removeClass('btn-dark');
            tab_statistiche_btn.addClass('btn-warning');
        } else {
            tab_statistiche_btn.removeClass('btn-warning');
            tab_statistiche_btn.addClass('btn-dark');
        }
    });
});