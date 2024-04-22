package com.SimonMasson_EmilyWu_MaximeAdam.mastermind.activites;



import android.content.Intent;
import android.content.SharedPreferences;
import android.database.SQLException;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import com.SimonMasson_EmilyWu_MaximeAdam.mastermind.R;
import com.SimonMasson_EmilyWu_MaximeAdam.mastermind.adaptateur.MenuAdapter;
import com.SimonMasson_EmilyWu_MaximeAdam.mastermind.modele.entite.Color;
import com.SimonMasson_EmilyWu_MaximeAdam.mastermind.modele.entite.Mastermind;
import com.SimonMasson_EmilyWu_MaximeAdam.mastermind.presentateur.PresentateurCode;
import com.SimonMasson_EmilyWu_MaximeAdam.mastermind.presentateur.PresentateurCouleur;
import com.SimonMasson_EmilyWu_MaximeAdam.mastermind.dao.sql.dao;
import com.SimonMasson_EmilyWu_MaximeAdam.mastermind.presentateur.PresentateurMastermind;

public class GameActivity extends AppCompatActivity implements View.OnClickListener {

    TextView emailTextView, attemptRecordTextView,separateurBouton;
    Button confirmCodeButton, menuButton, btnGrid, btnPalette;

    GridLayout paletteColor, codeAndFeedback;
    Drawable gameBtnDrawable;

    int codeLength,colorCount,maxAttempts,selectedColor;
    PresentateurCouleur presentateurCouleur;
    PresentateurCode presentateurCode;
    PresentateurMastermind presentateurMastermind;
    MenuAdapter menu;
    String email;
    Mastermind mastermind;
    private dao dao;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

//* Récupérer les paramètres sauvegardés depuis SettingsActivity
        SharedPreferences prefs = getSharedPreferences("Settings", MODE_PRIVATE);
        codeLength = prefs.getInt("codeLength", 4);
        colorCount = prefs.getInt("colorCount", 4);
        maxAttempts = prefs.getInt("maxAttempts", 10); // Valeur par défaut : 10

        Intent intention = getIntent();

        //* Retrieve email from intent
        if(intention != null){
            email = intention.getStringExtra("EMAIL");
            /*
            Parcelable parcel = intention.getParcelableExtra("DAO");
            if(parcel != null){
                try{
                    dao = (dao)parcel;

                    dao.insertHardcodedData();
                    Log.d("SQL TRANSFER+INSERT","Succes");
                }catch (SQLException e){
                    Log.d("SQL TRANSFER+INSERT","Une erreure est survenue : "+e.getMessage());
                }

            }

             */
        }
        try{
            dao = dao.getInstance(this);
            presentateurMastermind = new PresentateurMastermind(this);
            presentateurMastermind.insertHardCodedData(GameActivity.this);
            Log.d("DATABASE","SUCCES");
        }catch(SQLException e){
            Log.d("DATABASE","ERREUR :"+e.getMessage());
        }



        //? Display email in a TextView
        emailTextView = findViewById(R.id.emailTextView);
        emailTextView.setText("Bonjour : " + email + "\n ! Bienvenue dans le jeu Mastermind !");
        //? grid
        gameBtnDrawable = ResourcesCompat.getDrawable(getResources(),R.drawable.gamebtn,null);
        paletteColor = findViewById(R.id.paletteColor);                              //? palette de couleur
        codeAndFeedback = findViewById(R.id.codeAndFeedback);                       //? affichage de tentative/code
        addGridButtons();
        //? texte                                                                   //
        separateurBouton = findViewById(R.id.separateurBouton);                    //?
        attemptRecordTextView = findViewById(R.id.attemptRecordTextView);         //?
        //? boutons                                                              //
        confirmCodeButton = findViewById(R.id.confirmCodeButton);                //?
        menuButton = findViewById(R.id.menuButton);
        menuButton.setOnClickListener(this);
        confirmCodeButton.setOnClickListener(this);

        presentateurCouleur = new PresentateurCouleur(this);

        presentateurCouleur.obtenirCouleur(colorCount);

        presentateurCode = new PresentateurCode(this);

        presentateurCode.obtenirCode(codeLength,colorCount);

    }
    public void commencerPartie(){
        for(int i = 0 ; i < colorCount ; i++){
            View view = paletteColor.getChildAt(i);
            view.setOnClickListener(this);
        }
        mastermind = new Mastermind(presentateurCode.codePartie(),maxAttempts);
    }
    /**
     * fonction qui genere les colonnes et ranges dun gridLayout deja genere en .xml
     * la fonction utilise codeLength et maxAttempts arbritrairement.
     * -
     */
    public void addGridButtons(){
        codeAndFeedback.setColumnCount(codeLength);
        codeAndFeedback.setRowCount(maxAttempts); // Add an extra row for the feedback drawable
        for(int i=0; i<maxAttempts;i++){
            for (int j=0;j<codeLength;j++) {
                btnGrid = new Button(this);
                btnGrid.setBackground(gameBtnDrawable);
                GridLayout.LayoutParams params = new GridLayout.LayoutParams();
                params.width = 100;
                params.height = 100;
                params.setMargins(10, 20, 10, 5);
                btnGrid.setLayoutParams(params);

                btnGrid.setId(i * codeLength + j);
                btnGrid.setTag(i + 1);
                btnGrid.setOnClickListener(this);
                codeAndFeedback.addView(btnGrid);
            }
        }
// Add an extra space for the feedback drawable at the end
    }
    public void addGridPalette(){
        Color colors = presentateurCouleur.getColorsList();
        paletteColor.setRowCount(1);
        paletteColor.setColumnCount(colorCount);
        for(int i = 0 ; i<colorCount ; i++ ){
            btnPalette = new Button(this);
            btnPalette.setText(String.valueOf(i+1));
            GridLayout.LayoutParams params = new GridLayout.LayoutParams();
            params.width =100;
            params.height = 100;
            params.setMargins(10,20,10,5);
            btnPalette.setLayoutParams(params);
            btnPalette.setBackgroundColor((int)Long.parseLong(colors.getListe()[i],16));
            paletteColor.addView(btnPalette);
        }
    }

    //?implementation du click "Retourner au Menu Principal"
    void saveState() {
        // Implement your save state logic here
    }
    @Override
    public void onClick(View v) {
        if(v==menuButton){
            menu = new MenuAdapter(GameActivity.this, menuButton, email, new Runnable() {
                @Override
                public void run() {
                    finish();
                }
            }, new Runnable() {
                @Override
                public void run() {
                    finish();
                }
            }, new Runnable() {
                @Override
                public void run() {
                    finish();
                }
            });
            menu.showMenu();
        } else if (v==confirmCodeButton) {

        }else if (v.getParent() == codeAndFeedback ){

            Log.d("ButtonClick","Button Clicked with TAG -> "+ v.getTag()+" ID -> "+v.getId());
            if(v.getTag().equals(5)){
                v.getBackground().setTint(selectedColor);
            }

        }else if (v.getParent() == paletteColor){
            if(v instanceof Button){
                ColorDrawable buttonColor = (ColorDrawable) ((Button)v).getBackground();
                selectedColor = buttonColor.getColor();
                separateurBouton.setBackgroundColor(selectedColor);
                separateurBouton.setTextColor(Color.WHITE);
            }
        }
    }
    public void afficherMessage(String message){
        Toast.makeText(this,message,Toast.LENGTH_LONG).show();
    }

}