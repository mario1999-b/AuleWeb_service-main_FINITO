package org.univaq.swa.model;

public class Aula {
    private int id;
    private String nome;
    private String luogo;
    private String edificio;
    private String emailResponsabile;
    private Gruppo gruppo;

    public Aula(int id, String nome, String luogo, String edificio, String emailResponsabile, Gruppo gruppo) {
        this.id = id;
        this.nome = nome;
        this.luogo = luogo;
        this.edificio = edificio;
        this.emailResponsabile = emailResponsabile;
        this.gruppo = gruppo;
    }

    public Aula(int id, String nome) {
        this.id = id;
        this.nome = nome;
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

    public String getEdificio() {
        return edificio;
    }

    public void setEdificio(String edificio) {
        this.edificio = edificio;
    }

    public String getEmailResponsabile() {
        return emailResponsabile;
    }

    public void setEmailResponsabile(String emailResponsabile) {
        this.emailResponsabile = emailResponsabile;
    }

    public Gruppo getGruppo() {
        return gruppo;
    }

    public void setGruppo(Gruppo gruppo) {
        this.gruppo = gruppo;
    }
}
