package com.SimonMasson_EmilyWu_MaximeAdam.mastermind.dao;

import com.SimonMasson_EmilyWu_MaximeAdam.mastermind.dao.api.HttpJsonService;
import com.SimonMasson_EmilyWu_MaximeAdam.mastermind.modele.entite.Code;

import org.json.JSONException;

import java.io.IOException;

public class CodeDao {

    public static Code getCode(int longueurCode, int nbCouleur)throws IOException, JSONException {

        return new HttpJsonService().getCode(longueurCode,nbCouleur);
    }
}
