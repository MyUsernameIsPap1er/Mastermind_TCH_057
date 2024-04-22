package com.SimonMasson_EmilyWu_MaximeAdam.mastermind.activites;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.SimonMasson_EmilyWu_MaximeAdam.mastermind.R;
import com.SimonMasson_EmilyWu_MaximeAdam.mastermind.dao.sql.dao;

public class MainActivity extends AppCompatActivity  implements View.OnClickListener{
    private EditText txtNom;
    String email;
    Button btnStartGame,btnSettings,btnStats;
    private dao dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dao = com.SimonMasson_EmilyWu_MaximeAdam.mastermind.dao.sql.dao.getInstance(this);
        try{
            Log.d("DB CREATION","La Base de donee a ete creer avec succes");
        }catch(Exception e){
            Log.d("DB CREATION","Un proble est survenue en creant la base de donne");
        }

        txtNom = findViewById(R.id.inputEmail);
        btnStartGame = findViewById(R.id.btnStartGame);
        btnSettings = findViewById(R.id.btnSettings);
        btnStats = findViewById(R.id.btnStats);

        btnStartGame.setOnClickListener(this);
        btnSettings.setOnClickListener(this);
        btnStats.setOnClickListener(this);
    }
    @Override
    protected void onResume(){
        super.onResume();

    }
    public void goToGameView(View view) {
        email = txtNom.getText().toString().trim();
        if (TextUtils.isEmpty(email)) {
            // Display error message if email field is empty
            Toast.makeText(this, "Please enter your email", Toast.LENGTH_SHORT).show();
        } else {

            Intent intention = new Intent(this, GameActivity.class);
            intention.putExtra("EMAIL", email);
            //intention.putExtra("DAO",dao);
            startActivity(intention);
        }
    }
    //? naviguation au parametres
    public void goToSettingsView(View view) {
        Intent intention = new Intent(this, SettingsActivity.class);

        startActivity(intention);
    }
    //? naviguation au dao
    public void goToStatsView(View view) {

        Intent intention = new Intent(this, StatisticActivity.class);
        //intention.putExtra("DAO",dao);
        startActivity(intention);
    }
    @Override
    public void onClick(View v) {
        if(v==btnStartGame){
            goToGameView(v);
        }else if(v==btnSettings){
            goToSettingsView(v);
        }else if(v==btnStats){
            goToStatsView(v);
        }
    }
}