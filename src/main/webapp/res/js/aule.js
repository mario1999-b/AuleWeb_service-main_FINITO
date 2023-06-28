/*
*
*
 */


//DICHIARAZIONE SELETTORI
const aula_result = $('#aule-result');
const aula_empty = $('#aule-empty');
const aula_container = $('#aule-container');
const aula_csv_result = $('#aule-csv-result');
const aula_csv_empty = $('#aule-csv-empty');
const aula_csv_container = $('#aule-csv-container');
const attrezzature_result = $('#attrezzature-result')
const attrezzature_empty = $('#attrezzature-empty');
const attrezzature_container = $('#attrezzature-container');

//END DICHIARAZIONE SELETTORI


// OP.5 INFORMAZIONI DI BASE DI UN AULA
function getInfAula(val) {
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
                handleError(request, status, error, "#aula", "Errore nel caricamento dell aula.");
            },
            cache: false,
        });
    } else {
        handleError("", "", "", "#aula", "Input errato");
    }
}

/*
 Funzione per il riempimento della tabella delle aule
 */


function populateAula(data) {
    if (data) {
        aula_result.show();
        aula_empty.hide();

        aula_result.append('<tr>')
        aula_result.append('<td>' + data['id'] + '</td>')
        aula_result.append('<td>' + data['nome'] + '</td>')
        aula_result.append('<td>' + data['luogo'] + '</td>')
        aula_result.append('<td>' + data['piano'] + '</td>')
        aula_result.append('<td>' + data['emailResponsabile'] + '</td>')
        aula_result.append('<td>' + data['idGruppo'] + '</td>')
        aula_result.append('<td>' + data['idAttrezzature'] + '</td>')
        aula_result.append('</tr>')
    } else {
        //Se l'aula è vuota viene svuotata la tabella e viene mostrato un messaggio.
        $(aula_result).children().remove();
        aula_empty.show();
    }
    aula_empty.text("Non ci sono aule.");
}

// OP.5 ATTREZZATURE DI UN AULA
function getListAttAula(val) {
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
                handleError(request, status, error, "#attrezzature", "Errore nel caricamento");
            },
            cache: false,
        });
    } else {
        handleError("", "", "", "#attrezzature", "Input errato");
    }
}

/*
 Funzione per il riempimento della tabella delle attrezzature
 */


function populateAttrezzature(data) {
    if (data) {

        attrezzature_result.show();
        attrezzature_empty.hide();

        attrezzature_result.append('<tr>')
        attrezzature_result.append('<td>' + data['idAttrezzature'] + '</td>')
        attrezzature_result.append('<td>' + data['numeroLavagna'] + '</td>')
        attrezzature_result.append('<td>' + data['numeroSedie'] + '</td>')
        attrezzature_result.append('<td>' + data['numeroTavolo'] + '</td>')
        attrezzature_result.append('</tr>')
    } else {
        //Se l'aula è vuota viene svuotata la tabella e viene mostrato un messaggio.
        $(attrezzature_result).children().remove();
        attrezzature_empty.show();
    }
    attrezzature_empty.text("Non ci sono attrezzature.");
}


// OP.3 INSERIMENTO DI UNA NUOVA AULA
function inserimentoAula() {
    message("", "");
    clear();
    toggleVisibility(aula_container);


    $.ajax({
        url: "rest/aule",
        method: "POST",
        contentType: "application/json",
        data: JSON.stringify({
            nome: $('#nomeAula_add').val(),
            luogo: $('#luogo_add').val(),
            piano: $('#piano_add').val(),
            emailResponsabile: $('#emailResponsabile_add').val(),
            idGruppo: $('#gruppo_add').val(),
            idAttrezzature: $('#idAttrezzature_add').val()
        }),
        success: function (data) {
            aula_result.children().remove();
            clear();
            message("Nuova aula inserita.", "success");
        },
        error: function (request, status, error) {
            handleError(request, status, error, "#aula", "Errore nel caricamento");
        },
        cache: false,
    });

}

// OP.2 ESPORTAZIONE CSV
function getCsvAule() {
    message("", "");
    clear();
    toggleVisibility(aula_csv_container);

    $.ajax({
        url: "rest/aule/esport/CSV",
        method: "GET",
        success: function (data) {
            //data - aula
            $(aula_csv_result).children().remove();
             populateAulaCsv(data);
            message("Attrezzatura caricata.", "success");
        },
        error: function (request, status, error) {
            handleError(request, status, error, "#attrezzature", "Errore nel caricamento delle aule.");
        },
        cache: false,
    });

}
function populateAulaCsv(data) {
    if (data) {
        aula_csv_result.show();
        aula_csv_empty.hide();

        aula_csv_result.append('<tr>')
        aula_csv_result.append('<td>' + data + '</td>')
        aula_result.append('</tr>')
    } else {
        //Se l'aula è vuota viene svuotata la tabella e viene mostrato un messaggio.
        $(aula_csv_result).children().remove();
        aula_csv_empty.show();
    }
    aula_csv_empty.text("Non ci sono aule.");

}


// OP. 4 ASSEGNAZIONE AULA A GRUPPO
function assegnazioneAulaToGruppo(idAula,idGruppo) {
    message("", "");
    clear();
    toggleVisibility(aula_container);

    var idAula = $('#idAulaPut').val();
    var idGruppo = $('#idGruppoPut').val();

    $.ajax({
        url: "rest/aule/" + idAula + "/gruppo/" + idGruppo,
        method: "PUT",
        contentType: "application/json",
        data: JSON.stringify({
            id: idAula,
            idGruppo: idGruppo

        }),
        success: function (data) {
            aula_result.children().remove();
            clear();
            message("assegnato con successo.", "success");
        },
        error: function (request, status, error) {
            handleError(request, status, error, "#aula", "Errore nel caricamento.");
        },
        cache: false,
    });

}

// OP.2 IMPORTAZIONE CSV
function inserimentoCsvAule() {
    var formData = new FormData();
    formData.set("csvInputFile", $('#csvFile')[0].files[0]);
    $.ajax({
        url: 'rest/aule/import/CSV',
        type: 'POST',
        data: formData,
        contentType: false,
        processData: false,
        success: function (data) {
            alert('Importazione CSV completata');
        },
        error: function () {
            alert('Errore durante l\'importazione CSV');
        }
    });
}