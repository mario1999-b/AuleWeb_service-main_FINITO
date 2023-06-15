package org.univaq.swa.model;

public class Aula {
    private int id;
    private String nome;
    private Posizione posizione;
    private ListaAttrezzature listaAttrezzature;
    private String emailResponsabile;
    private int numeroPreseElettriche;

    public Aula(int id, String nome, Posizione posizione, ListaAttrezzature listaAttrezzature, String emailResponsabile, int numeroPreseElettriche) {
        this.id = id;
        this.nome = nome;
        this.posizione = posizione;
        this.listaAttrezzature = listaAttrezzature;
        this.emailResponsabile = emailResponsabile;
        this.numeroPreseElettriche = numeroPreseElettriche;
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

    public Posizione getPosizione() {
        return posizione;
    }

    public void setPosizione(Posizione posizione) {
        this.posizione = posizione;
    }

    public ListaAttrezzature getListaAttrezzature() {
        return listaAttrezzature;
    }

    public void setListaAttrezzature(ListaAttrezzature listaAttrezzature) {
        this.listaAttrezzature = listaAttrezzature;
    }

    public String getEmailResponsabile() {
        return emailResponsabile;
    }

    public void setEmailResponsabile(String emailResponsabile) {
        this.emailResponsabile = emailResponsabile;
    }

    public int getNumeroPreseElettriche() {
        return numeroPreseElettriche;
    }

    public void setNumeroPreseElettriche(int numeroPreseElettriche) {
        this.numeroPreseElettriche = numeroPreseElettriche;
    }
}
