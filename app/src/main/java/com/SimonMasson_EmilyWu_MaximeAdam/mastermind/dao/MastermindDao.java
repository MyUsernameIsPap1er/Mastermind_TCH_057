package com.SimonMasson_EmilyWu_MaximeAdam.mastermind.dao;




import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import androidx.annotation.NonNull;

import com.SimonMasson_EmilyWu_MaximeAdam.mastermind.dao.sql.MastermindContrat;
import com.SimonMasson_EmilyWu_MaximeAdam.mastermind.dao.sql.dao;
import com.SimonMasson_EmilyWu_MaximeAdam.mastermind.modele.entite.Mastermind;
import com.google.android.material.transition.MaterialSharedAxis;

import java.util.ArrayList;
import java.util.List;

public class MastermindDao {
    public static boolean insertHardcodedData(Context context){
        dao dao = com.SimonMasson_EmilyWu_MaximeAdam.mastermind.dao.sql.dao.getInstance(context);
        SQLiteDatabase db = dao.getWritableDatabase();

        String[] ids = {"1", "2", "3"};
        String[] emails = {"email1@example.com", "email2@example.com", "email3@example.com"};
        String[] codes = {"code1", "code2", "code3"};
        int[] nbColors = {3, 4, 5};
        String[] results = {"result1", "result2", "result3"};
        int[] nbAttempts = {10, 8, 12};
        String[] dates = {"1999-01-01","2000-02-02","2001-03-03"};

        for (int i = 0; i < ids.length; i++) {
            ContentValues values = new ContentValues();
            values.put(MastermindContrat.colonnes.COLONNE_ID_CODE, ids[i]);
            values.put(MastermindContrat.colonnes.COLONNE_COURRIEL, emails[i]);
            values.put(MastermindContrat.colonnes.COLONNE_CODE, codes[i]);
            values.put(MastermindContrat.colonnes.COLONNE_NB_COULEUR, nbColors[i]);
            values.put(MastermindContrat.colonnes.COLONNE_RESULTAT, results[i]);
            values.put(MastermindContrat.colonnes.COLONNE_NB_TENTATIVE, nbAttempts[i]);
            values.put(MastermindContrat.colonnes.COLONNE_DATE, nbAttempts[i]);
            if(db.insert(MastermindContrat.NOM_TABLE, null, values) == -1){
                return false;
            }
        }
        return true;
    }
    public boolean partieCompleter(@NonNull Mastermind partie,Context context){
        dao dao = com.SimonMasson_EmilyWu_MaximeAdam.mastermind.dao.sql.dao.getInstance(context);

        SQLiteDatabase db = dao.getWritableDatabase();
        ContentValues cv = new ContentValues();
        //! :TODO link to presentator
        cv.put(MastermindContrat.colonnes.COLONNE_ID_CODE,partie.getSecretCode().getId());
        cv.put(MastermindContrat.colonnes.COLONNE_COURRIEL,partie.getMail());
        cv.put(MastermindContrat.colonnes.COLONNE_CODE,partie.getSecretCode().toString());
        cv.put(MastermindContrat.colonnes.COLONNE_NB_COULEUR,partie.getSecretCode().getColors().length);
        cv.put(MastermindContrat.colonnes.COLONNE_RESULTAT,partie.getResultat());
        cv.put(MastermindContrat.colonnes.COLONNE_NB_TENTATIVE,partie.getTentatives().size());
        cv.put(MastermindContrat.colonnes.COLONNE_DATE,partie.getDate().toString());
        try{
            long insert = db.insert(MastermindContrat.NOM_TABLE, null,cv);
            return insert != -1; //? retourne true si la partie a ete sauvegarder dans la base de donnes...
        }catch (SQLException e ){
            Log.d("Database INSERT","Database ERROR : "+e );
            return false;
        }finally {
            db.close();
        }
    }
    public static List<Mastermind> getAllStats(Context context){
        dao dao = com.SimonMasson_EmilyWu_MaximeAdam.mastermind.dao.sql.dao.getInstance(context);
        List<Mastermind> list = new ArrayList<>();
        SQLiteDatabase db = null;
        Cursor cursor = null;
        //get data
        String query = "SELECT * FROM "+ MastermindContrat.NOM_TABLE;
        try {
             db = dao.getReadableDatabase();
             cursor = db.rawQuery(query, null);
            if (cursor.moveToFirst()) {
                //loop a travers la liste de toute les parties
                //creer un object pour chaques resultats
                do {
                    String ID = cursor.getString(0);
                    String courriel = cursor.getString(1);
                    String code = cursor.getString(2); //!might cause error
                    code = code.substring(1, code.length() - 1);
                    String[] couleur = code.split(",\\s*");
                    int nb_coul = cursor.getInt(3);
                    String resultat = cursor.getString(4);
                    int nb_tentative = cursor.getInt(5);

                    Mastermind partie = new Mastermind(ID, courriel, couleur, nb_coul, resultat, nb_tentative);
                    list.add(partie);

                } while (cursor.moveToNext());
            } else {
                //failure...
            }
        }catch(Exception e){
            Log.d("DATABASE","FETCH ERROR : "+e.getMessage());
        }finally {
            if(cursor != null){
                cursor.close();
            }
            if(db != null){
                db.close();
            }
        }
      return list;
    }
}
