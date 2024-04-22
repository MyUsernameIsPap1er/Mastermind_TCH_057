package com.SimonMasson_EmilyWu_MaximeAdam.mastermind.activites;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.SimonMasson_EmilyWu_MaximeAdam.mastermind.R;

public class SettingsActivity extends AppCompatActivity implements View.OnClickListener {
    private Spinner spinnerCodeLength;
    private Spinner spinnerColorCount;
    private Spinner spinnerMaxAttempts;
    private Button buttonSave;
    private Button buttonReturnToMain;

    private int codeLength;
    private int colorCount;
    private int maxAttempts;

    private ArrayAdapter<CharSequence> codeLengthAdapter;
    private ArrayAdapter<CharSequence> colorCountAdapter;
    private ArrayAdapter<CharSequence> maxAttemptsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_settings);

        spinnerCodeLength = findViewById(R.id.spinnerCodeLength);
        spinnerColorCount = findViewById(R.id.spinnerColorCount);
        spinnerMaxAttempts = findViewById(R.id.spinnerMaxAttempts);
        buttonSave = findViewById(R.id.buttonSave);
        buttonReturnToMain = findViewById(R.id.buttonReturnToMain);

        // Créer les adaptateurs pour les spinners
        codeLengthAdapter = ArrayAdapter.createFromResource(this,
                R.array.code_length_array, android.R.layout.simple_spinner_item);
        colorCountAdapter = ArrayAdapter.createFromResource(this,
                R.array.color_count_array, android.R.layout.simple_spinner_item);
        maxAttemptsAdapter = ArrayAdapter.createFromResource(this,
                R.array.max_attempts_array, android.R.layout.simple_spinner_item);

        // Spécifier le layout pour les éléments déroulants
        codeLengthAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        colorCountAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        maxAttemptsAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Appliquer les adaptateurs aux spinners
        spinnerCodeLength.setAdapter(codeLengthAdapter);
        spinnerColorCount.setAdapter(colorCountAdapter);
        spinnerMaxAttempts.setAdapter(maxAttemptsAdapter);


        buttonReturnToMain.setOnClickListener(this);
        buttonSave.setOnClickListener(this);

        /*
        buttonReturnToMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                retournerAMain();
            }
        });

        // Définir les écouteurs de clic pour les boutons
        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enregistrerConfiguration();
            }
        });

         */

        // Restaurer les paramètres enregistrés


        restoreSettings();
    }

    private void enregistrerConfiguration() {
        codeLength = Integer.parseInt(spinnerCodeLength.getSelectedItem().toString());
        colorCount = Integer.parseInt(spinnerColorCount.getSelectedItem().toString());
        maxAttempts = Integer.parseInt(spinnerMaxAttempts.getSelectedItem().toString());

        // Enregistrer les paramètres
        saveSettings();

        Toast.makeText(SettingsActivity.this, "Configuration enregistrée", Toast.LENGTH_SHORT).show();
    }

    private void retournerAMain() {
        Intent intent = new Intent(SettingsActivity.this, MainActivity.class);
        startActivity(intent);
    }

    private void saveSettings() {
        SharedPreferences.Editor editor = getSharedPreferences("Settings", MODE_PRIVATE).edit();
        editor.putInt("codeLength", codeLength);
        editor.putInt("colorCount", colorCount);
        editor.putInt("maxAttempts", maxAttempts);
        editor.apply();
    }

    private void restoreSettings() {
        SharedPreferences prefs = getSharedPreferences("Settings", MODE_PRIVATE);
        codeLength = prefs.getInt("codeLength", 4); // Valeur par défaut : 4
        colorCount = prefs.getInt("colorCount", 8); // Valeur par défaut : 8
        maxAttempts = prefs.getInt("maxAttempts", 10); // Valeur par défaut : 10

        // Mettre les valeurs restaurées dans les spinners
        spinnerCodeLength.setSelection(codeLengthAdapter.getPosition(String.valueOf(codeLength)));
        spinnerColorCount.setSelection(colorCountAdapter.getPosition(String.valueOf(colorCount)));
        spinnerMaxAttempts.setSelection(maxAttemptsAdapter.getPosition(String.valueOf(maxAttempts)));
    }

    @Override
    public void onClick(View v) {
        if(v==buttonReturnToMain) {
            retournerAMain();
        } else if (v==buttonSave) {
            enregistrerConfiguration();
        }
    }
}