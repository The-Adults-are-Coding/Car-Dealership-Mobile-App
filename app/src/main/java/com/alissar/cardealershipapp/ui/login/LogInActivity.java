package com.alissar.cardealershipapp.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.alissar.cardealershipapp.R;
import com.alissar.cardealershipapp.ui.main.MainActivity;
import com.alissar.cardealershipapp.utils.SessionManager;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint // <--- STEP 1: MUST HAVE THIS ANNOTATION

public class LogInActivity extends AppCompatActivity {

    private EditText etName, etPassword;
    private Button btnSignIn;
    private LoginViewModel viewModel;
    @Inject
    SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        // Initialize Views
        etName = findViewById(R.id.etName);
        etPassword = findViewById(R.id.etPassword);
        btnSignIn = findViewById(R.id.btnSignIn);
        viewModel = new ViewModelProvider(this).get(LoginViewModel.class);

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


        btnSignIn.setOnClickListener(v -> {
            String name = etName.getText().toString();
            String pass = etPassword.getText().toString();
            performLogin(name, pass);
        });
    }

    private void updateButtonState() {
        boolean isNameFilled = !etName.getText().toString().trim().isEmpty();
        boolean isPasswordFilled = !etPassword.getText().toString().trim().isEmpty();

        btnSignIn.setEnabled(isNameFilled && isPasswordFilled);
        btnSignIn.setAlpha(btnSignIn.isEnabled() ? 1.0f : 0.5f);
    }
    private void performLogin(String name, String pass) {
        viewModel.login(name, pass).observe(this, resource -> {
            switch (resource.status) {
                case LOADING:
                    showProgressBar(true);
                    break;
                case SUCCESS:
                    showProgressBar(false);
                    // SUCCESS! Navigate to Main Car Listing
                    startActivity(new Intent(this, MainActivity.class));
                    Toast.makeText(LogInActivity.this, "Welcome back, " + name + "!", Toast.LENGTH_SHORT).show();

                    sessionManager.saveToken(resource.data.getToken());
                    finish();
                    break;
                case ERROR:
                    showProgressBar(false);
                    Toast.makeText(this, resource.message, Toast.LENGTH_SHORT).show();
                    break;
            }
        });
    }
    private void showProgressBar(boolean isVisible) {
        ProgressBar progressBar = findViewById(R.id.login_progress_bar);
        Button loginButton = findViewById(R.id.btnSignIn);

        if (isVisible) {
            progressBar.setVisibility(View.VISIBLE); // Show the spinner
            loginButton.setEnabled(false);           // Prevent double clicks
            loginButton.setAlpha(0.5f);              // Make button look disabled
        } else {
            progressBar.setVisibility(View.GONE);    // Hide the spinner
            loginButton.setEnabled(true);            // Re-enable the button
            loginButton.setAlpha(1.0f);              // Reset button look
        }
    }
}
