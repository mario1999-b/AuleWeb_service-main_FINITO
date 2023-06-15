/*

*/

//DICHIARAZIONE SELETTORI
const aula_result = $('#aula-result');
const aula_empty = $('#aula-empty');
const aule_container = $('#aule-container');
const id_aula_update = $('#assegnazioneAulaGruppo_c');
const id_gruppo_update = $('#assegnazioneAulaGruppo_d');
const id_collezione_add = $('#addDiscoCollezione_c');
const update_gruppo_aula_form = $('#assegnazioneAulaGruppo_form_2')
const add_disco_collezione_form = $('#addDiscoCollezione_form_2')
//END DICHIARAZIONE SELETTOR

/*
* 2: Elenco CSV aule
*/
function getCSVaule() {
    message("", "");
    clear();
    toggleVisibility(aule_container);

    $.ajax({
        url: "rest/aule/CSV",
        method: "GET",
        success: function (data) {
            //data - lista CSV aule
            aula_result.children().remove();
            if (data.length > 0) {
                getAuleUtility(data); // ottengo le aule e le inserisco nella tabella
                message("CSV aule caricate.", "success");
            } else {
                handleError("", "", "", "#aula", "Non ci sono aule.");
            }
        },
        error: function (request, status, error) {
            handleError(request, status, error, "#aula", "Errore nel caricamento delle aule.");
        },
        cache: false,
    });
}

/*
* 3: Elenco collezioni condivise con un utente
*/
function getCollezioniCondivise() {
    message("", "");
    clear();
    toggleVisibility(collezioni_container);

    $.ajax({
        url: "rest/collezioni/condivise",
        method: "GET",
        success: function (data) {
            //data - lista di url di collezioni
            collezione_result.children().remove();
            if (data.length > 0) {
                getCollezioniUtility(data); //ottengo le collezioni e le inserisco nella tabella
                message("Collezioni condivise caricate.", "success");
            } else {
                handleError("", "", "", "#collezione", "Non ci sono collezioni condivise.");
            }
        },
        error: function (request, status, error) {
            handleError(request, status, error, "#collezione", "Errore nel caricamento delle collezioni.");
        },
        cache: false,
    });
}

/*
* 4: Singola collezione
* @param {int} val - id della collezione da visualizzare
*/
function getCollezione(val) {
    message("", "");
    clear();
    toggleVisibility(collezioni_container);

    if (val) {
        $.ajax({
            url: "rest/collezioni/" + val,
            method: "GET",
            success: function (data) {
                //data - collezione
                collezione_result.children().remove();
                message("Collezione caricata.", "success");
                populateCollezione(data); //popolo la tabella con la collezione
            },
            error: function (request, status, error) {
                handleError(request, status, error, "#collezione", "Errore nel caricamento della collezione.");
            },
            cache: false,
        });
    } else {
        handleError("", "", "", "#collezione", "Input errato");
    }
}

/*
* 6. Crea e aggiunge un disco esistente in una collezione
* @param {int} c - id della collezione
* @param {int} d - id del disco nella collezione
*/
function addDiscoCollezione(c) {
    message("", "");
    if (c) {
        $.ajax({
            url: "rest/collezioni/" + c + "/dischi",
            method: "POST",
            contentType: "application/json",
            data: JSON.stringify({
                titolo: $('#titolo_add').val(),
                anno: $('#anno_add').val(),
                genere: $('#genere_add').val(),
                formato: $('#formato_add').val(),
                stato_conservazione: $('#statoConservazione_add').val(),
                barcode: $('#barcode_add').val(),
                etichetta: $('#etichetta_add').val(),
                autori: $('#autore_add').val()
            }),
            success: function () {
                collezione_result.children().remove();
                clear();
                message("Collezione aggiornata con il nuovo disco.", "success");
            },
            error: function (request, status, error) {
                handleError(request, status, error, "#collezione", "Errore nel caricamento della collezione.");
            },
            cache: false,
        });
    } else {
        clear();
        handleError("", "", "", "#collezione", "Input errato");
    }
}

/*
*
* @param {int} c - id dell' aula
* @param {int} d - id del gruppo
*/
function assegnazioneAulaGruppo(c, d) {
    message("", "");
    if (c && d) {
        $.ajax({
            url: "rest/aule/" + c + "/gruppi/" + d,
            method: "PUT",
            contentType: "application/json",
            //vengono passati i dati della form assegnazioneAulaGruppo_form
            data: JSON.stringify({
                id: d,
                nome: $('#nome_update').val(),
                luogo: $('#luogo_update').val(),
                edificio: $('#edificio_update').val(),
                piano: $('#piano_update').val(),
                emailResponsabile: $('#emailResponsabile_update').val(),
                listaAttrezzature: $('#listaAttrezzature_update').val(),
                numeroPreseElettriche: $('#numeroPreseElettriche_update').val(),
                gruppo: $('#gruppo_update').val(),
            }),
            success: function () {
                collezione_result.children().remove();
                clear();
                message("Collezione aggiornata con il nuovo disco.", "success");
            },
            error: function (request, status, error) {
                handleError(request, status, error, "#collezione", "Errore nel caricamento della collezione.");
            },
            cache: false,
        });
    } else {
        clear();
        handleError("", "", "", "#collezione", "Input errato");
    }
}

/*----------------------------------FUNZIONI UTILITY PER LE COLLEZIONI------------------------------------------------*/

/*
* Utility che permette di visualizzare le form per le operazioni di add e update di un disco in una collezione
* @param {String} op - operazione da eseguire (add o update)
*/
function gruppoForm(op) {
    message("", "");
    clear();
    if (op === 'add') { //ADD DI UN gruupo in un aula
        //Se i campi non sono compilati non viene mostrata la form e si esce dalla funzione.
        if (id_aula_add.val() === '') {
            message('Inserire tutti i campi obbligatori.', 'error')
            return;
        } else if (checkCollezione(id_aula_add.val()) === false) {
            return;
        }
        //Mostra la form per creare e aggiungere un disco alla collezione specificata
        toggleVisibility(add_gruppo_aula_form);
        //Ottiene tutti gli autori del sistema e li inserisce nella select degli autori
        getAutoriForm($('#autore_add'));

    } else if (op === 'update') { //UPDATE DEL DISCO IN UNA COLLEZIONE
        if (id_aula_update.val() === '' || id_gruppo_update.val() === '') {
            message('Inserire tutti i campi obbligatori.', 'error')
            return;
        } else if (checkDiscoCollezione(id_aula_update.val(), id_gruppo_update.val()) === false) {
            return;
        }
        //Mostra la form per aggiornare un disco della collezione specificata
        toggleVisibility(update_gruppo_aula_form);
        //Popola i campi della form con i dati del disco selezionati
        populateDiscoCollezioneUpdateForm();
    }
}

/*
* Funzione Utility per Ottiene tutti gli autori del sistema e li inserisce nella select degli autori
* @param {Selectpicker} select - La select che deve essere riempita con gli autori
*/
function getAutoriForm(select) {

    $.ajax({
        url: "rest/autori",
        method: "GET",
        success: function (data) {
            $.each(data, function (key) {
                $.ajax({
                    url: "rest/autori/" + data[key].split("/")[6],
                    method: "GET",
                    success: function (data) {
                        select.append('<option value="' + data['id'] + '">' + data['nome_artistico'] + '</option>');
                        select.trigger('change');
                        select_picker.selectpicker('refresh');
                    }
                })
            })
        },
        error: function (request, status, error) {
            handleError(request, status, error, "#collezione", "Errore nel caricamento degli autori.");
        },
        cache: false,
    });
}

/*
* Funzione Utility per popolare i campi della form per l'aggiornamento di un disco di una collezione
*/
function populateDiscoCollezioneUpdateForm() {
    const titolo = $('#titolo_update');
    const anno = $('#anno_update');
    const genere = $('#genere_update');
    const formato = $('#formato_update');
    const statoConservazione = $('#statoConservazione_update');
    const barcode = $('#barcode_update');
    const etichetta = $('#etichetta_update');

    $.ajax({
        url: "rest/dischi/" + id_disco_update.val(),
        method: "GET",
        success: function (data) {
            titolo.val(data['titolo']);
            anno.val(data['anno']);
            barcode.val(data['barcode']);
            etichetta.val(data['etichetta']);
            genere.val(data['genere']).trigger('change');
            formato.val(data['formato']).trigger('change');
            if (formato.val() === 'DIGITALE') {
                disableStatoConservazione(statoConservazione, formato);
            } else {
                statoConservazione.val(data['stato_conservazione']).trigger('change');
            }
            getAutoriForm($('#autore_update'));
        },
        error: function (request, status, error) {
            clear()
            handleError(request, status, error, "#collezione", "Errore nel caricamento del disco.");
        },
        cache: false,
    });
}

/*
* Funzione Utility per Ottenere le collezioni
* @param {List<URL>} data - Lista di URL delle collezioni
*/
function getAuleUtility(data) {
    $.each(data, function (key) {
        $.ajax({
            url: "rest/aule/" + data[key].split("/")[6],
            method: "GET",
            success: function (data) {
                populateAula(data);
            },
            error: function (request, status, error) {
                handleError(request, status, error, "#aula", "Errore generico");
            },
            cache: false,
        });
    })
}

/*
* Funzione Utility il riempimento della tabella delle aule
* @param {Aula} data - Aula da inserire nella tabella
*/
function populateAula(data) {
    if (data) {
        //Se l Aula non è vuota viene inserita nella tabella.
        aula_result.show();
        aula_empty.hide();

        aula_result.append('<tr>')
        aula_result.append('<td>' + data['id'] + '</td>')
        aula_result.append('<td>' + data['no'] + '</td>')
        aula_result.append('<td>' + data['data_creazione'] + '</td>')
        aula_result.append('<td>' + data['privacy'] + '</td>')
        aula_result.append('</tr>')
    } else {
        //Se la collezione è vuota viene svuotata la tabella e viene mostrato un messaggio.
        collezione_result.children().remove();
        collezione_empty.show();
    }
    collezione_empty.text("Non ci sono collezioni.");
}

/*
* Alla selezione di un formato nella form update_disco_collezione_form viene disabilitato il campo statoConservazione_update se il formato è digitale
*/
$('#formato_update').on('change', function () {
    disableStatoConservazione($('#statoConservazione_update'), $('#formato_update option:selected'));
});

/*
* Alla selezione di un formato nella form add_disco_collezione_form viene disabilitato il campo statoConservazione_add se il formato è digitale
*/
$('#formato_add').on('change', function () {
    disableStatoConservazione($('#statoConservazione_add'), $('#formato_add option:selected'));
});

/*
* Utility che disabilita il campo statoConservazione se il formato è digitale
* @param {Selectpicker} statoConservazione - Il campo statoConservazione da disabilitare
* @param {Option} selected - L'opzione selezionata in formato
*/
function disableStatoConservazione(statoConservazione, selected) {
    if (selected.val() === 'DIGITALE') {
        statoConservazione.val('').trigger('change');
        statoConservazione.prop('disabled', true);
        statoConservazione.prop('required', false);
        statoConservazione.parent().addClass('off');
    } else {
        statoConservazione.prop('disabled', false);
        statoConservazione.prop('required', true);
        statoConservazione.parent().removeClass('off');
    }
    select_picker.selectpicker('refresh');
}

