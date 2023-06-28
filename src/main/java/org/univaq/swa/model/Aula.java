package org.univaq.swa.model;

import org.apache.commons.csv.CSVFormat;

public class Aula {
    private int id;
    private String nome;
    private String luogo;
    private String piano;
    private String emailResponsabile;
    private Integer idGruppo;
    private int idAttrezzature;

    public Aula(int id, String nome, String luogo, String piano, String emailResponsabile, Integer idGruppo, int idAttrezzature) {
        this.id = id;
        this.nome = nome;
        this.luogo = luogo;
        this.piano = piano;
        this.emailResponsabile = emailResponsabile;
        this.idGruppo = idGruppo;
        this.idAttrezzature = idAttrezzature;
    }

    public Aula(int id, String nome,Integer idGruppo) {
        this.id = id;
        this.nome = nome;
        this.idGruppo= idGruppo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getLuogo() {
        return luogo;
    }

    public void setLuogo(String luogo) {
        this.luogo = luogo;
    }

    public String getPiano() {
        return piano;
    }

    public void setPiano(String piano) {
        this.piano = piano;
    }

    public String getEmailResponsabile() {
        return emailResponsabile;
    }

    public void setEmailResponsabile(String emailResponsabile) {
        this.emailResponsabile = emailResponsabile;
    }

    public int getIdAttrezzature() {
        return idAttrezzature;
    }
    public void setIdAttrezzature(int idAttrezzature) {
        this.idAttrezzature = idAttrezzature;
    }

    public Integer getIdGruppo() {
        return idGruppo;
    }

    public void setIdGruppo(Integer idGruppo) {
        this.idGruppo = idGruppo;
    }

    @Override
    public String toString() {
        return
                 id +
                "," + nome  +
                "," + luogo  +
                "," + piano +
                "," + emailResponsabile +
                "," + idGruppo +
                "," + idAttrezzature +
                ","
                ;
    }

}
