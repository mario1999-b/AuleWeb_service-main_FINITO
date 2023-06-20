/*
*
*
 */


//DICHIARAZIONE SELETTORI
const aula_result = $('#aule-result');
const aula_empty = $('#aule-empty');
const aula_container = $('#aule-container');
const attrezzature_result = $('#attrezzature-result')
const attrezzature_empty = $('#attrezzature-empty');
const attrezzature_container = $('#attrezzature-container');

//END DICHIARAZIONE SELETTORI


// OP.5 INFORMAZIONI DI BASE DI UN AULA
function getInfAula(val){
    message("", "");
    clear();
    toggleVisibility(aula_container);

    if (val) {
        $.ajax({
            url: "rest/aule/" + val,
            method: "GET",
            success: function (data) {
                //data - aula
                 $(aula_result).children().remove();
                message("Aula caricata.", "success");
                populateAula(data); //popolo la tabella con l'aula
            },
            error: function (request, status, error) {
                handleError(request, status, error, "#aula", "Errore nel caricamento della collezione.");
            },
            cache: false,
        });
    } else {
        handleError("", "", "", "#aula", "Input errato");
    }
}

/*
 Funzione Utility per il riempimento della tabella delle aule
 */


function populateAula(data) {
    if (data) {
        aula_result.show();
        aula_empty.hide();

        aula_result.append('<tr>')
        aula_result.append('<td>' + data['id'] + '</td>')
        aula_result.append('<td>' + data['nome'] + '</td>')
        aula_result.append('<td>' + data['luogo'] + '</td>')
        aula_result.append('<td>' + data['edificio'] + '</td>')
        aula_result.append('<td>' + data['emailResponsabile'] + '</td>')
        aula_result.append('<td>' + data['gruppo'] + '</td>')
        aula_result.append('</tr>')
    } else {
        //Se l'aula è vuota viene svuotata la tabella e viene mostrato un messaggio.
        $(aula_result).children().remove();
        aula_empty.show();
    }
    aula_empty.text("Non ci sono aule.");
}

// OP.5 ATTREZZATURE DI UN AULA
function getListAttAula(val){
    message("", "");
    clear();
    toggleVisibility(attrezzature_container);

    if (val) {
        $.ajax({
            url: "rest/aule/" + val + "/attrezzature",
            method: "GET",
            success: function (data) {
                //data - aula
                $(attrezzature_result).children().remove();
                message("Attrezzatura caricata.", "success");
                populateAttrezzature(data); //popolo la tabella con l'attrezzatura
            },
            error: function (request, status, error) {
                handleError(request, status, error, "#attrezzature", "Errore nel caricamento della collezione.");
            },
            cache: false,
        });
    } else {
        handleError("", "", "", "#attrezzature", "Input errato");
    }
}

/*
 Funzione Utility per il riempimento della tabella delle attrezzature
 */


function populateAttrezzature(data) {
    if (data) {

        attrezzature_result.show();
        attrezzature_empty.hide();

        attrezzature_result.append('<tr>')
        attrezzature_result.append('<td>' + data['id'] + '</td>')
        attrezzature_result.append('<td>' + data['n_tavoli'] + '</td>')
        attrezzature_result.append('<td>' + data['n_sedie'] + '</td>')
        attrezzature_result.append('<td>' + data['n_lavagne'] + '</td>')
        attrezzature_result.append('</tr>')
    } else {
        //Se l'aula è vuota viene svuotata la tabella e viene mostrato un messaggio.
        $(attrezzature_result).children().remove();
        attrezzature_empty.show();
    }
    attrezzature_empty.text("Non ci sono attrezzature.");
}

// OP.3 INSERIMENTO DI UNA NUOVA AULA
