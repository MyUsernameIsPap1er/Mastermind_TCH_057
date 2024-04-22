package com.SimonMasson_EmilyWu_MaximeAdam.mastermind.dao.api;

import android.util.Log;


import com.SimonMasson_EmilyWu_MaximeAdam.mastermind.modele.entite.Code;
import com.SimonMasson_EmilyWu_MaximeAdam.mastermind.modele.entite.Color;
import com.SimonMasson_EmilyWu_MaximeAdam.mastermind.modele.entite.Statistique;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import kotlin.NotImplementedError;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class HttpJsonService  {

    final String URL_POINT_ENTREE = "http://10.0.2.2:3000";


    public List<Integer> getIdDesStatistiques() {
        // :TODO
        throw new NotImplementedError("On est pas rendu la encore");
    }


    public List<Statistique> getStatistique() throws IOException, JSONException {

        OkHttpClient client = new OkHttpClient();
        Request requete = new Request.Builder()
                .url(URL_POINT_ENTREE + "/stats")
                .build();
        Response response = null;
        try {
            response = client.newCall(requete).execute();
            ResponseBody responseBody = response.body();
            String jsonData = responseBody.string();

            ObjectMapper mapper = new ObjectMapper();
            try {
                Statistique[] tabStats = mapper.readValue(jsonData, Statistique[].class);
                return Arrays.asList(tabStats);
            } catch (
                    JsonProcessingException e) {
                throw new JSONException("Probleme avec le JSON entrant : "+e);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }


    public Statistique getStatistiqueparId(int id) {
        Statistique statistique = null;
        OkHttpClient client = new OkHttpClient();
        Request requete = new Request.Builder()
                .url(URL_POINT_ENTREE + "/stats/" + id)
                .build();
        try {
            Response response = client.newCall(requete).execute();
            ResponseBody responseBody = response.body();
            String jsonData = responseBody.string();

            ObjectMapper mapper = new ObjectMapper();
            try {
                statistique = mapper.readValue(jsonData, Statistique.class);
            } catch (
                    JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return statistique;
    }


    public boolean ajouter(Statistique statistique) {
        OkHttpClient client = new OkHttpClient();
        MediaType JSON = MediaType.parse("application/json; chartset=utf-8");

        JSONObject obj = new JSONObject();
        try {
            obj.put("id", statistique.getId());
            obj.put("idCode", statistique.getIdCode());
            obj.put("record", statistique.getRecord());
            obj.put("courriel", statistique.getCourriel());

            RequestBody corpsRequete = RequestBody.create(String.valueOf(obj), JSON);
            String url = URL_POINT_ENTREE + "/stats";

            Request request = new Request.Builder()
                    .url(url)
                    .post(corpsRequete)
                    .build();

            Response response = client.newCall(request).execute();
            Log.e("HttpJsonStatisticDao:ajouter()", "Code reponse : " + response.code());
            if (response.code() == 200) {
                Log.d("HttpJsonStatisticDao:ajouter()", "statistique ajoutée avec succès");
                return true;
            } else {
                Log.d("HttpJsonStatisticDao:ajouter()", "statistique non ajoutée");
                return false;
            }
        } catch (JSONException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public boolean modifier(Statistique statistique) {
        OkHttpClient client = new OkHttpClient();
        MediaType JSON = MediaType.parse("application/json; chartset=utf-8");

        JSONObject obj = new JSONObject();
        try {
            obj.put("id", statistique.getId());
            obj.put("idCode", statistique.getIdCode());
            obj.put("record", statistique.getRecord());
            obj.put("courriel", statistique.getCourriel());

            RequestBody corpsRequete = RequestBody.create(String.valueOf(obj), JSON);
            String url = URL_POINT_ENTREE + "/taches/" + statistique.getId();

            Request request = new Request.Builder()
                    .url(url)
                    .put(corpsRequete)
                    .build();

            Response response = client.newCall(request).execute();
            Log.e("HttpJsonStatisticDao:modifier()", "Code reponse : " + response.code());
            if (response.code() == 200) {
                Log.d("HttpJsonStatisticDao:modifier()", "Statistique modifiée avec succès");
                return true;
            } else {
                Log.d("HttpJsonStatisticDao:modifier()", "Statistique non modifié");
                return false;
            }
        } catch (JSONException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }


    public Code getCode(int longueurCode, int nbCouleur) throws IOException,JSONException{

        OkHttpClient client = new OkHttpClient();
        Request requete = new Request.Builder()
                .url(URL_POINT_ENTREE + "/codesSecrets?nbCouleurs="+nbCouleur)
                .build();
            try {
                Response response = client.newCall(requete).execute();
                ResponseBody responseBody = response.body();
                String jsonData = responseBody.string();

                ObjectMapper mapper = new ObjectMapper();
                try {
                    Code[] codes = mapper.readValue(jsonData, Code[].class);
                    List<Code> matches = new ArrayList<>();
                    for(Code code: codes){
                        if(code.getColors().length == longueurCode){
                            matches.add(code);
                        }
                    }
                    if(!matches.isEmpty()){
                        Random r = new Random();
                        return matches.get(r.nextInt(matches.size()));
                    }
                }catch(JsonProcessingException e){
                    System.err.println("Error parsing JSON : "+e.getMessage());
                }
            } catch (IOException e) {
                System.err.println("Error making HTTP request : "+e.getMessage());
                throw e;
            }
        return null;
    }

    public Color getColorList(int nbCouleur) throws IOException, JSONException{

        OkHttpClient client = new OkHttpClient();
        Request requete = new Request.Builder()
                .url(URL_POINT_ENTREE + "/couleursDisponibles")
                .build();
            try
            {
                Response response = client.newCall(requete).execute();
                ResponseBody responseBody = response.body();
                String jsonData = responseBody.string();

                ObjectMapper mapper = new ObjectMapper();
                try
                {
                    Color color = mapper.readValue(jsonData, Color.class);
                    String[] ogList = color.getListe();
                    String[] reList = new String[nbCouleur];
                    for(int i = 0 ; i<nbCouleur && i < ogList.length; i++){
                        reList[i] = ogList[i];
                    }
                    color.setListe(reList);
                    return color;
                } catch (JsonProcessingException e)
                {
                    throw new JSONException("Un probleme avec le JSON est survenue.");
                }
            } catch (IOException e)
            {
                throw new RuntimeException(e);
            }
    }
}
