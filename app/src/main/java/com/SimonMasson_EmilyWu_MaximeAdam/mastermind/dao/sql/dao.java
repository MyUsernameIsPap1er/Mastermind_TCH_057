package com.SimonMasson_EmilyWu_MaximeAdam.mastermind.dao.sql;



import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.SimonMasson_EmilyWu_MaximeAdam.mastermind.modele.entite.Mastermind;

import java.util.ArrayList;
import java.util.List;

public class dao extends SQLiteOpenHelper /*implements Parcelable */{
   private static dao instance;
    private dao(@Nullable Context context) {
        super(context, MastermindContrat.DB_NAME, null, MastermindContrat.DB_VERSION);
    }
    public static synchronized dao getInstance(Context context){
        if(instance == null){
            instance = new dao(context);
        }
        return instance;
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableQuery = "CREATE TABLE "+ MastermindContrat.NOM_TABLE+" (" +
                MastermindContrat.colonnes.COLONNE_ID_CODE+" TEXT PRIMARY KEY ," +
                MastermindContrat.colonnes.COLONNE_COURRIEL+" TEXT," +
                MastermindContrat.colonnes.COLONNE_CODE+" TEXT," +
                MastermindContrat.colonnes.COLONNE_NB_COULEUR+" INTEGER," +
                MastermindContrat.colonnes.COLONNE_RESULTAT+" TEXT," +
                MastermindContrat.colonnes.COLONNE_NB_TENTATIVE+" INTEGER,"+
                MastermindContrat.colonnes.COLONNE_DATE+" DATE)";
        db.execSQL(createTableQuery);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

}
