package com.SimonMasson_EmilyWu_MaximeAdam.mastermind.modele.entite;

public class Color {
    public static final int RED = 0xFFFF0000;       // Rouge
    public static final int GREEN = 0xFF00FF00;     // Vert
    public static final int BLUE = 0xFF0000FF;      // Bleu
    public static final int YELLOW = 0xFFFFFF00;    // Jaune
    public static final int MAGENTA = 0xFFFF00FF;   // Magenta
    public static final int ORANGE = 0xFFFFA500;    // Orange
    public static final int BLACK = 0xFF000000;     // Noir
    public static final int WHITE = 0xFFFFFFFF;    // Blanc

    private String[] liste;

    public String[] getListe() {
        return liste;
    }

    public void setListe(String[] liste) {
        this.liste = liste;
    }


}
