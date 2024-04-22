package com.SimonMasson_EmilyWu_MaximeAdam.mastermind.modele.entite;

import com.SimonMasson_EmilyWu_MaximeAdam.mastermind.modele.entite.Mastermind;

public class Statistique {
    private String id,idCode,courriel;
    private int record;

    public Mastermind getMastermind() {

        return mastermind;
    }

    public void setMastermind(Mastermind mastermind) {
        this.mastermind = mastermind;
    }

    private Mastermind mastermind;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdCode() {
        return idCode;
    }

    public void setIdCode(String idCode) {
        this.idCode = idCode;
    }

    public int getRecord() {
        return record;
    }

    public void setRecord(int record) {
        this.record = record;
    }

    public String getCourriel() {
        return courriel;
    }

    public void setCourriel(String courriel) {
        this.courriel = courriel;
    }
}
