package com.alissar.cardealershipapp;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

public class ChangeLanguageActivity extends AppCompatActivity {

    private EditText etBalance;
    private SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_language);

        prefs = getSharedPreferences("UserProfile", MODE_PRIVATE);
        etBalance = findViewById(R.id.etBalance);

        Button btnSave = findViewById(R.id.btnSaveBalance);
        Button btnCancel = findViewById(R.id.btnCancelBalance);

        // Load existing balance
        etBalance.setText(prefs.getString("balance", "0.00"));

        btnSave.setOnClickListener(v -> {
            SharedPreferences.Editor editor = prefs.edit();
            editor.putString("balance", etBalance.getText().toString());
            editor.apply();
            finish();
        });

        btnCancel.setOnClickListener(v -> finish());
    }
}
