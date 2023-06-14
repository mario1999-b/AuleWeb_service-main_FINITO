package org.univaq.swa.model;

public class Posizione {
    private Luogo luogo;
    private Edificio edificio;
    private Piano piano;

    public Posizione(Luogo luogo, Edificio edificio, Piano piano) {
        this.luogo = luogo;
        this.edificio = edificio;
        this.piano = piano;
    }

    public Luogo getLuogo() {
        return luogo;
    }

    public void setLuogo(Luogo luogo) {
        this.luogo = luogo;
    }

    public Edificio getEdificio() {
        return edificio;
    }

    public void setEdificio(Edificio edificio) {
        this.edificio = edificio;
    }

    public Piano getPiano() {
        return piano;
    }

    public void setPiano(Piano piano) {
        this.piano = piano;
    }
}
