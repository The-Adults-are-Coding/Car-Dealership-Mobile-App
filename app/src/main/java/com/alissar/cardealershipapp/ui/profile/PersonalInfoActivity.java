package com.alissar.cardealershipapp.ui.profile;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

import com.alissar.cardealershipapp.R;

public class PersonalInfoActivity extends AppCompatActivity {

    private EditText etFirstName, etLastName, etNationality, etAge;
    private SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_info);

        prefs = getSharedPreferences("UserProfile", MODE_PRIVATE);

        etFirstName = findViewById(R.id.etFirstName);
        etLastName = findViewById(R.id.etLastName);
        etNationality = findViewById(R.id.etNationality);
        etAge = findViewById(R.id.etAge);

        Button btnSave = findViewById(R.id.btnSave);
        Button btnCancel = findViewById(R.id.btnCancel);

        // Load existing data
        loadUserData();

        btnSave.setOnClickListener(v -> {
            saveUserData();
            finish(); // Go back
        });

        btnCancel.setOnClickListener(v -> {
            finish(); // Go back without saving
        });
    }

    private void loadUserData() {
        etFirstName.setText(prefs.getString("firstName", ""));
        etLastName.setText(prefs.getString("lastName", ""));
        etNationality.setText(prefs.getString("nationality", ""));
        etAge.setText(prefs.getString("age", ""));
    }

    private void saveUserData() {
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("firstName", etFirstName.getText().toString());
        editor.putString("lastName", etLastName.getText().toString());
        editor.putString("nationality", etNationality.getText().toString());
        editor.putString("age", etAge.getText().toString());
        editor.apply();
    }
}
