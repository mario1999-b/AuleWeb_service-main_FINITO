package org.univaq.swa.model;

public class Gruppo {
    private int id;
    private Dipartimento nome;
    private String descrizione;

    public Gruppo(int id, Dipartimento nome, String descrizione) {
        this.id = id;
        this.nome = nome;
        this.descrizione = descrizione;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Dipartimento getNome() {
        return nome;
    }

    public void setNome(Dipartimento nome) {
        this.nome = nome;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }
}
