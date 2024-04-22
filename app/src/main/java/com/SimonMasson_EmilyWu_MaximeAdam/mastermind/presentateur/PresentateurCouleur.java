package com.SimonMasson_EmilyWu_MaximeAdam.mastermind.presentateur;

import android.app.Activity;

import com.SimonMasson_EmilyWu_MaximeAdam.mastermind.activites.GameActivity;
import com.SimonMasson_EmilyWu_MaximeAdam.mastermind.dao.CouleurDao;
import com.SimonMasson_EmilyWu_MaximeAdam.mastermind.modele.entite.Color;
import com.SimonMasson_EmilyWu_MaximeAdam.mastermind.modele.Modele;
import com.SimonMasson_EmilyWu_MaximeAdam.mastermind.modele.ModeleManager;

import org.json.JSONException;

import java.io.IOException;

public class PresentateurCouleur {

    private Activity activite;
    private Modele modele;

    public PresentateurCouleur(Activity activite){
        this.activite = activite;
        this.modele = ModeleManager.getModele();
    }

    public void obtenirCouleur(int nbCouleur){
        new Thread(){
            @Override
            public void run(){
                try{
                    Color liste = CouleurDao.getColorList(nbCouleur);



                    modele.setColor(liste);
                    ((GameActivity)activite).runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            ((GameActivity)activite).addGridPalette();
                        }
                    });
                }catch (JSONException e){
                    ((GameActivity)activite).runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            ((GameActivity)activite).afficherMessage("***Une erreur avec le JSON est survenue.***");
                        }
                    });

                }catch (IOException e){
                    ((GameActivity)activite).runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            ((GameActivity)activite).afficherMessage("***Une erreur avec l'API est survenue.***");
                        }
                    });

                }
            }
        }.start();
    }
    public Color getColorsList(){return modele.getColor() ;}
}
