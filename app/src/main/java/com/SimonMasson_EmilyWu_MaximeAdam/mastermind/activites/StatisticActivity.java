package com.SimonMasson_EmilyWu_MaximeAdam.mastermind.activites;

import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.SimonMasson_EmilyWu_MaximeAdam.mastermind.R;
import com.SimonMasson_EmilyWu_MaximeAdam.mastermind.adaptateur.StatisticAdapter;
import com.SimonMasson_EmilyWu_MaximeAdam.mastermind.dao.sql.MastermindContrat;
import com.SimonMasson_EmilyWu_MaximeAdam.mastermind.modele.entite.Mastermind;
import com.SimonMasson_EmilyWu_MaximeAdam.mastermind.presentateur.PresentateurMastermind;
import com.SimonMasson_EmilyWu_MaximeAdam.mastermind.presentateur.PresentateurStatistique;

import java.sql.Date;
import java.util.List;
import com.SimonMasson_EmilyWu_MaximeAdam.mastermind.dao.sql.dao;

public class StatisticActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener{

    Button buttonajouterStats,retourMenu;
    List<Mastermind> gameStats;
    StatisticAdapter adapter;
    ListView listView;
    Dialog dialog;
    TextView textViewEmail, textViewGameResult, textViewTurns, textViewNumColors;

    PresentateurMastermind presentateurMastermind;
    private dao dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistic);
        try{
            dao = dao.getInstance(this);
            Log.d("DATABASE","SUCCES");
        }catch (Exception e){
            Log.d("DATABASE","ERREUR"+e.getMessage());
        }
        listView = findViewById(R.id.gamelist);
        buttonajouterStats = findViewById(R.id.buttonajouterStats);
        retourMenu = findViewById(R.id.retourMenu);

        //!presentateur
        //
        /*
        presentateurMastermind = new PresentateurMastermind(this);
        presentateurMastermind.getAllStats(this);
        */
        Cursor curseur = getStatsMastermindCurseur();
        sca = new SimpleCursorAdapter(this,R.layout.statistic_items,curseur,new String[]{
                MastermindContrat.colonnes.COLONNE_ID_CODE},
                new int[]{},
                CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
        //*adaptateur
        adapter = new StatisticAdapter(this, R.layout.statistic_items, presentateurMastermind);
        //?vue
        listView.setAdapter(adapter);
        buttonajouterStats.setOnClickListener(this);
        retourMenu.setOnClickListener(this);
        //? montrer le dialogue avec les statistique onClick
        listView.setOnItemClickListener(this);
    }

    private void showCustomDialog(String email, String gameResult, int turnsPlayed, int turnsAllowed, int numColors) {
        dialog = new Dialog(this); //create dialog
        dialog.setContentView(R.layout.statistic_dialog);//insert custom layout
        dialog.setCancelable(true); //dismiss dialog by tapping out

        textViewEmail = dialog.findViewById(R.id.textViewEmail);
        textViewGameResult = dialog.findViewById(R.id.textViewGameResult);
        textViewTurns = dialog.findViewById(R.id.textViewTurns);
        textViewNumColors = dialog.findViewById(R.id.textViewNumColors);//findView
        textViewEmail.setText(String.valueOf(email));
        textViewGameResult.setText(String.valueOf(gameResult));
        textViewTurns.setText(String.valueOf(turnsPlayed + " / " + turnsAllowed));
        textViewNumColors.setText(String.valueOf(numColors));//setView
        dialog.show();
    }

    private void retournerAMain() {
        Intent intent = new Intent(StatisticActivity.this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        if(v==buttonajouterStats){
            //?fait l'objet Mastermind
            Mastermind test = null;
            String[] code;
            try{
                code = new String[]{"ffffff"};
                test = new Mastermind("9999", "Couriel", code, 2, "gagner", 1);
                Toast.makeText(StatisticActivity.this,"Partie creer",Toast.LENGTH_SHORT).show();
            }catch(Exception e){
                Toast.makeText(StatisticActivity.this,"ERREUR", Toast.LENGTH_SHORT).show();
                Toast.makeText(StatisticActivity.this,e.getMessage(),Toast.LENGTH_SHORT).show();
            }
        }else if(v==retourMenu){
            retournerAMain();
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        try {
            showCustomDialog(gameStats.get(position).getMail(),
                    gameStats.get(position).getResultat(),
                    gameStats.get(position).getTentatives().size(),
                    gameStats.get(position).getMaxTentatives(),
                    gameStats.get(position).getSecretCode().getNbCouleurs());
            Toast.makeText(StatisticActivity.this,"Success",Toast.LENGTH_SHORT).show();
        }catch(Exception e ){
            Toast.makeText(StatisticActivity.this,e.getMessage(),Toast.LENGTH_SHORT).show();
        }
    }

    public void raffraichirListe(){
        adapter.notifyDataSetChanged();
        Toast.makeText(this,adapter.getCount()+" statistiques",Toast.LENGTH_SHORT).show();
    }
    public void afficherMessage(String message){
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
    }

    public Cursor getStatsMastermindCurseur(){
        String[] colonne = new String[] {
                MastermindContrat.colonnes.COLONNE_COURRIEL,
                MastermindContrat.colonnes.COLONNE_CODE,
                MastermindContrat.colonnes.COLONNE_NB_COULEUR,
                MastermindContrat.colonnes.COLONNE_RESULTAT,
                MastermindContrat.colonnes.COLONNE_NB_TENTATIVE,
        };
        dao.getInstance(this);
        SQLiteDatabase db = dao.getReadableDatabase();
        Cursor curseur = db.query(MastermindContrat.NOM_TABLE,colonne,
                null,null,
                null,null,
                null, null);
        if(curseur != null){
            curseur.moveToFirst();
        }
        dao.close();
        return curseur;
    }
}