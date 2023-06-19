/*
*
*
 */


//DICHIARAZIONE SELETTORI

const aula_result = $('#aula-result');
const aula_empty = $('#aula-empty');
const aula_container = $('#aula-container');

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
                 aula_result.children().remove();
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
        //Se la collezione non è vuota viene inserita nella tabella.
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
        aula_result.children().remove();
        aula_empty.show();
    }
    aula_empty.text("Non ci sono aule.");
}