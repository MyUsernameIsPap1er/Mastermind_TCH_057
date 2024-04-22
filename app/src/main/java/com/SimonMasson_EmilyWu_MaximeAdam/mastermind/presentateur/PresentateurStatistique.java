package com.SimonMasson_EmilyWu_MaximeAdam.mastermind.presentateur;

import android.app.Activity;

import com.SimonMasson_EmilyWu_MaximeAdam.mastermind.activites.MainActivity;
import com.SimonMasson_EmilyWu_MaximeAdam.mastermind.activites.StatisticActivity;
import com.SimonMasson_EmilyWu_MaximeAdam.mastermind.dao.StatisticDao;
import com.SimonMasson_EmilyWu_MaximeAdam.mastermind.modele.Modele;
import com.SimonMasson_EmilyWu_MaximeAdam.mastermind.modele.ModeleManager;
import com.SimonMasson_EmilyWu_MaximeAdam.mastermind.modele.entite.Statistique;

import org.json.JSONException;

import java.io.IOException;
import java.util.List;

public class PresentateurStatistique {
    private Activity activite;
    private Modele modele;

    public PresentateurStatistique(Activity activite){
        this.activite = activite;
        this.modele = ModeleManager.getModele();
    }
    public int getNombreStatistique(){ return modele.getStatistiques().size();}

    public void obtenirStatistiques(){
        new Thread(){
            @Override
            public void run(){
                try{
                    // demande a DAO la liste des stats
                    List<Statistique> liste = StatisticDao.getStatistique();
                    //injection dans le modele
                    modele.setStatistique(liste);

                    ((StatisticActivity)activite).runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            ((StatisticActivity)activite).raffraichirListe();
                        }
                    });
                }catch(JSONException e) {
                    ((StatisticActivity)activite).runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            ((StatisticActivity)activite).afficherMessage("Probleme JSON des stats");
                        }
                    });
                }catch(IOException e){
                    ((MainActivity)activite).runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            ((StatisticActivity)activite).afficherMessage("Probleme avec l'API");
                        }
                    });
                }
            }
        }.start();
    }

    public List<Statistique> getListeStatistique(){return this.modele.getStatistiques();}

    public Statistique getStatistique(int position){return modele.getStatistiques().get(position);}

    public Statistique trouverStatistique(String id){
        Statistique stat = modele.getStat(id);
        if (stat!=null)
            return stat;
        return null;
    }



}
