package com.SimonMasson_EmilyWu_MaximeAdam.mastermind.modele.entite;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Code {
    private String id;
    @JsonProperty("code")
    private String[] code;
    private int nbCouleurs;

    public Code(){}

    public Code(String[] colors) {
        this.code = colors;
    }

    /**Constructeur Code()-3
     * Uniquement pour tester StatisticActivity (Voir constructeur Mastermind()-6)
     * @param id
     * @param code
     * @param nbCoul
     */
    public Code(String id, String[] code, int nbCoul) {
        this.id = id;

        this.code = code;

        this.nbCouleurs = nbCoul;
    }
    public Feedback compareWith(Code otherCode) {
        int correctPosition = 0;
        int correctColor = 0;

        // Compter les positions correctes et les couleurs correctes
        for (int i = 0; i < code.length; i++) {
            if (code[i].equals(otherCode.code[i])) {
                correctPosition++;
            } else {
                for (int j = 0; j < otherCode.code.length; j++) {
                    if (code[i].equals(otherCode.code[j])) {
                        correctColor++;
                        break;
                    }
                }
            }
        }

        return new Feedback(correctPosition, correctColor);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String[] getColors() {
        return code;
    }

    public void setColors(String[] colors) {
        this.code = colors;
    }

    public int getNbCouleurs() {
        return nbCouleurs;
    }

    public void setNbCouleurs(int nbCouleurs) {
        this.nbCouleurs = nbCouleurs;
    }
}
