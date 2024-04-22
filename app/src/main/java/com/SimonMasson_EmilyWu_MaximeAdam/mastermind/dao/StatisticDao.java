package com.SimonMasson_EmilyWu_MaximeAdam.mastermind.dao;

import com.SimonMasson_EmilyWu_MaximeAdam.mastermind.dao.api.HttpJsonService;
import com.SimonMasson_EmilyWu_MaximeAdam.mastermind.modele.entite.Statistique;

import org.json.JSONException;

import java.io.IOException;
import java.util.List;

public interface StatisticDao {
    List<Integer> getIdDesStatistiques();
    public static List<Statistique> getStatistique() throws JSONException, IOException {
        return new HttpJsonService().getStatistique();
    }

    Statistique getStatistiqueparId(int id);
    boolean ajouter(Statistique statistique);
    boolean modifier(Statistique statistique);
}
