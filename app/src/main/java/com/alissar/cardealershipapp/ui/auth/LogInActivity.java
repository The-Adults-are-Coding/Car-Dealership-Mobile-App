package com.alissar.cardealershipapp.ui.auth;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.alissar.cardealershipapp.R;

public class LogInActivity extends AppCompatActivity {

    private EditText etName, etPassword;
    private Button btnSignIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize Views
        etName = findViewById(R.id.etName);
        etPassword = findViewById(R.id.etPassword);
        btnSignIn = findViewById(R.id.btnSignIn);

        // Initial state
        updateButtonState();

        // Add Listeners to enable button when inputs are filled
        TextWatcher inputWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                updateButtonState();
            }

            @Override
            public void afterTextChanged(Editable s) {}
        };

        etName.addTextChangedListener(inputWatcher);
        etPassword.addTextChangedListener(inputWatcher);

        // Sign In logic
        btnSignIn.setOnClickListener(v -> {
            String name = etName.getText().toString();
            Toast.makeText(LogInActivity.this, "Welcome back, " + name + "!", Toast.LENGTH_SHORT).show();
            // Proceed to Dashboard Activity
        });
    }

    private void updateButtonState() {
        boolean isNameFilled = !etName.getText().toString().trim().isEmpty();
        boolean isPasswordFilled = !etPassword.getText().toString().trim().isEmpty();

        btnSignIn.setEnabled(isNameFilled && isPasswordFilled);
        btnSignIn.setAlpha(btnSignIn.isEnabled() ? 1.0f : 0.5f);
    }
}
