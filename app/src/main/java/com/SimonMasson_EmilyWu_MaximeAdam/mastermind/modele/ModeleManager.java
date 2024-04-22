package com.SimonMasson_EmilyWu_MaximeAdam.mastermind.modele;

public class ModeleManager {
    private static Modele modele = null;

    /**
     *
     * @return
     */
    public static Modele getModele(){
        if(modele == null)
            modele = new Modele();
        return modele;
    }

    /**
     *
     * @return
     */
    public static boolean detruire(){
        boolean detruit = true;

        if(modele != null)
            modele = null;
        else
            detruit = false;

        return detruit;
    }
}
