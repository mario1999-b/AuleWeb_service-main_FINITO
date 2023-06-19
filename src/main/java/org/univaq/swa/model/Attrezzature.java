package org.univaq.swa.model;

public class Attrezzature {
    private int id_attrezzature;
    private int numeroSedie;
    private int numeroTavolo;
    private int numeroLavagna;

    public Attrezzature(int id_attrezzature, int sedie, int tavolo, int lavagna) {
        this.id_attrezzature = id_attrezzature;
        this.numeroSedie = sedie;
        this.numeroTavolo = tavolo;
        this.numeroLavagna = lavagna;
    }

    public int getIdAttrezzature() {
        return id_attrezzature;
    }

    public void setIdAttrezzature(int id) {
        this.id_attrezzature = id;
    }

    public int getNumeroSedie() {
        return numeroSedie;
    }

    public void setNumeroSedie(int numeroSedie) {
        this.numeroSedie = numeroSedie;
    }

    public int getNumeroTavolo() {
        return numeroTavolo;
    }

    public void setNumeroTavolo(int numeroTavolo) {
        this.numeroTavolo = numeroTavolo;
    }

    public int getNumeroLavagna() {
        return numeroLavagna;
    }

    public void setNumeroLavagna(int numeroLavagna) {
        this.numeroLavagna = numeroLavagna;
    }
}
