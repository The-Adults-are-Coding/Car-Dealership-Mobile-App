package com.alissar.cardealershipapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_me);

        Button btnPersonalInfo = findViewById(R.id.btnPersonalInfo);
        Button btnChangeBalance = findViewById(R.id.btnChangeLanguage);
        Button btnLogout = findViewById(R.id.btnLogout);
        TextView tvChangePhoto = findViewById(R.id.tvChangePhoto);

        btnPersonalInfo.setOnClickListener(v -> {
            Intent intent = new Intent(MeActivity.this, PersonalInfoActivity.class);
            startActivity(intent);
        });

        btnChangeBalance.setOnClickListener(v -> {
            Intent intent = new Intent(MeActivity.this, ChangeLanguageActivity.class);
            startActivity(intent);
        });

        btnLogout.setOnClickListener(v -> {
            Intent intent = new Intent(MeActivity.this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();
        });

        tvChangePhoto.setOnClickListener(v -> {
            // Logic to open gallery/camera would go here
        });
    }
}
