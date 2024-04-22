package com.SimonMasson_EmilyWu_MaximeAdam.mastermind.modele;

import com.SimonMasson_EmilyWu_MaximeAdam.mastermind.dao.sql.dao;
import com.SimonMasson_EmilyWu_MaximeAdam.mastermind.modele.entite.Code;
import com.SimonMasson_EmilyWu_MaximeAdam.mastermind.modele.entite.Color;
import com.SimonMasson_EmilyWu_MaximeAdam.mastermind.modele.entite.Mastermind;
import com.SimonMasson_EmilyWu_MaximeAdam.mastermind.modele.entite.Statistique;

import java.util.ArrayList;
import java.util.List;

public class Modele {
    private List<com.SimonMasson_EmilyWu_MaximeAdam.mastermind.modele.entite.Statistique> Statistique = new ArrayList<>();
    private Code code;
    private Color color;

    private List<Mastermind> mastermind;
    public List<Mastermind> getNbMastermind(){return mastermind;}

    public void setMastermind(List<Mastermind> liste){this.mastermind = liste;}
    private dao dao;

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public List<Statistique> getStatistiques(){return Statistique;}

    public void setStatistique(List<Statistique> statistiques){this.Statistique = statistiques;}

    public void setCode(Code code){this.code = code;}
    public Code getCode(){ return code;}
    public Statistique getStat(String id){
        for(Statistique stat :Statistique){
            if(id.equals(stat.getId()))
                return stat;
        }
        return null;
    }

}
