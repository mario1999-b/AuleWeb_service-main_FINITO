package org.univaq.swa.model;

public class Evento {
    private String nomeCorso;
    private Tipologia tipologia;
    private String descrizioneEvento;
    private String nomeResponsabile;
    private String emailResponsabile;

    public Evento(String nomeCorso, Tipologia tipologia, String descrizioneEvento, String nomeResponsabile, String emailResponsabile) {
        this.nomeCorso = nomeCorso;
        this.tipologia = tipologia;
        this.descrizioneEvento = descrizioneEvento;
        this.nomeResponsabile = nomeResponsabile;
        this.emailResponsabile = emailResponsabile;
    }

    public String getNomeCorso() {
        return nomeCorso;
    }

    public void setNomeCorso(String nomeCorso) {
        this.nomeCorso = nomeCorso;
    }

    public Tipologia getTipologia() {
        return tipologia;
    }

    public void setTipologia(Tipologia tipologia) {
        this.tipologia = tipologia;
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
}
