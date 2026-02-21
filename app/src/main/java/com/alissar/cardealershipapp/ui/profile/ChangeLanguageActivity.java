package com.alissar.cardealershipapp.ui.profile;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

import com.alissar.cardealershipapp.R;

public class ChangeLanguageActivity extends AppCompatActivity {


    private EditText etBalance;
    private SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_language);

        prefs = getSharedPreferences("UserProfile", MODE_PRIVATE);
        etBalance = findViewById(R.id.etLanguage);

        Button btnSave = findViewById(R.id.btnSaveLanguage);
        Button btnCancel = findViewById(R.id.btnCancelLanguage);

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
