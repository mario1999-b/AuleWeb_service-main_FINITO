package org.univaq.swa.model;

import java.sql.Date;
import java.time.LocalDate;

public class Evento {
    private int id;
    private String nomeEvento;
    private String descrizioneEvento;
    private String nomeResponsabile;
    private String emailResponsabile;
     private LocalDate dataEvento;

    public Evento(int id, String nomeEvento, String descrizioneEvento, String nomeResponsabile, String emailResponsabile, LocalDate dataEvento) {
        this.id = id;
        this.nomeEvento = nomeEvento;
        this.descrizioneEvento = descrizioneEvento;
        this.nomeResponsabile = nomeResponsabile;
        this.emailResponsabile = emailResponsabile;
        this.dataEvento = dataEvento;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomeEvento() {
        return nomeEvento;
    }

    public void setNomeEvento(String nomeEvento) {
        this.nomeEvento = nomeEvento;
    }

    public String getDescrizioneEvento() {
        return descrizioneEvento;
    }

    public void setDescrizioneEvento(String descrizioneEvento) {
        this.descrizioneEvento = descrizioneEvento;
    }

    public String getNomeResponsabile() {
        return nomeResponsabile;
    }

    public void setNomeResponsabile(String nomeResponsabile) {
        this.nomeResponsabile = nomeResponsabile;
    }

    public String getEmailResponsabile() {
        return emailResponsabile;
    }

    public void setEmailResponsabile(String emailResponsabile) {
        this.emailResponsabile = emailResponsabile;
    }

    public LocalDate getDataEvento() {
        return dataEvento;
    }

    public void setDataEvento(LocalDate dataEvento) {
        this.dataEvento = dataEvento;
    }


}
