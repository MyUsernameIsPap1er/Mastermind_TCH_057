package com.SimonMasson_EmilyWu_MaximeAdam.mastermind.presentateur;

import android.app.Activity;
import android.util.Log;

import com.SimonMasson_EmilyWu_MaximeAdam.mastermind.activites.GameActivity;
import com.SimonMasson_EmilyWu_MaximeAdam.mastermind.dao.CodeDao;
import com.SimonMasson_EmilyWu_MaximeAdam.mastermind.modele.entite.Code;
import com.SimonMasson_EmilyWu_MaximeAdam.mastermind.modele.Modele;
import com.SimonMasson_EmilyWu_MaximeAdam.mastermind.modele.ModeleManager;

import org.json.JSONException;

import java.io.IOException;

public class PresentateurCode {

    private Activity activite;
    private Modele modele;

    public PresentateurCode(Activity activite){
        this.activite = activite;
        this.modele = ModeleManager.getModele();
    }

    public void obtenirCode( int longueurCode, int nbCouleur){
        new Thread(){
            @Override
            public void run(){

                try {
                    Code code = CodeDao.getCode(longueurCode, nbCouleur);
                    Log.d("PresentateurCode:obtenirCode", "Code id : " + code.getId());
                    modele.setCode(code);
                    ((GameActivity) activite).runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            ((GameActivity) activite).commencerPartie();

                        }
                    });
                }catch(JSONException e){
                    ((GameActivity)activite).runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            ((GameActivity)activite).afficherMessage("Problème dans le JSON les CodesSecrets");
                        }
                    });
                }catch(IOException e){
                    ((GameActivity)activite).runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            ((GameActivity)activite).afficherMessage("Problème d'acces a l'API");
                        }
                    });
                }
            }
        }.start();

    }

    public Code codePartie(){return modele.getCode();}


}
