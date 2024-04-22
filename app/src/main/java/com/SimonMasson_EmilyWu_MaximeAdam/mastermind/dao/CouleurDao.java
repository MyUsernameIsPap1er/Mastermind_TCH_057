package com.SimonMasson_EmilyWu_MaximeAdam.mastermind.dao;

import com.SimonMasson_EmilyWu_MaximeAdam.mastermind.dao.api.HttpJsonService;
import com.SimonMasson_EmilyWu_MaximeAdam.mastermind.modele.entite.Color;

import org.json.JSONException;

import java.io.IOException;

public class CouleurDao {

    public static Color getColorList(int nbCouleur)throws IOException, JSONException {

        return new HttpJsonService().getColorList(nbCouleur);
    }
}
