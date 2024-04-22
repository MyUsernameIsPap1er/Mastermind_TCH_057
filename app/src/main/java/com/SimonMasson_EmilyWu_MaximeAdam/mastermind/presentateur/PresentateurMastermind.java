package com.SimonMasson_EmilyWu_MaximeAdam.mastermind.presentateur;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

import com.SimonMasson_EmilyWu_MaximeAdam.mastermind.activites.GameActivity;
import com.SimonMasson_EmilyWu_MaximeAdam.mastermind.dao.MastermindDao;
import com.SimonMasson_EmilyWu_MaximeAdam.mastermind.modele.Modele;
import com.SimonMasson_EmilyWu_MaximeAdam.mastermind.modele.ModeleManager;
import com.SimonMasson_EmilyWu_MaximeAdam.mastermind.modele.entite.Mastermind;

import java.util.List;

public class PresentateurMastermind {
    private Activity activite;
    private Modele modele;

    public PresentateurMastermind(Activity activite){
        this.modele = ModeleManager.getModele();
        this.activite = activite;
    }
    public int getNbMastermind(){return modele.getNbMastermind().size();}

    public List<Mastermind> getAllStats(Context context){
        modele.setMastermind(MastermindDao.getAllStats(context));
        return MastermindDao.getAllStats(context);
    }
    public Mastermind getMastermind(int position){return modele.getNbMastermind().get(position);}


    public boolean insertHardCodedData(GameActivity context){
        try{
            return MastermindDao.insertHardcodedData(context);
        }catch (Exception e){Log.d("DATABASSE",e.getMessage());};
        return false;
    }
}
