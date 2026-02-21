package com.alissar.cardealershipapp.ui.register;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.ViewModelProvider;

import com.alissar.cardealershipapp.R;
import com.alissar.cardealershipapp.data.model.Customer;
import com.alissar.cardealershipapp.ui.login.LogInActivity;
import com.alissar.cardealershipapp.ui.main.MainActivity;
import com.alissar.cardealershipapp.utils.Validators;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class UserRegister extends AppCompatActivity {

    // Declare UI elements - changed to EditText
    private EditText editTextFirstName;
    private EditText editTextLastName;
    private EditText editTextNationalNumber;
    private EditText editTextPhone;
    private EditText editTextAddress;
    private EditText editTextOccupation;
    private EditText editTextEmail;
    private EditText editTextPassword;
    private Button buttonSubmit;
    private TextView footerText;
    private RegisterViewModel viewModel;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_user_register);
        viewModel = new ViewModelProvider(this).get(RegisterViewModel.class);
        View rootView = findViewById(R.id.user_register);


        // Initialize UI elements
        initViews();

        // Handle window insets for edge-to-edge display
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.user_register), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Set click listener for the register button
        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerCustomer();
            }
        });

        // Set click listener for the footer text (Sign In link)
        footerText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to Sign In activity
                goToSignInActivity();
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(rootView, (v, insets) -> {
            // Get the height of the keyboard (ime) and system bars (status/navigation)
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            Insets imeInsets = insets.getInsets(WindowInsetsCompat.Type.ime());

            // Set padding:
            // Bottom padding = Keyboard height (if open) OR Navigation bar height (if closed)
            v.setPadding(systemBars.left, systemBars.top, systemBars.right,
                    Math.max(systemBars.bottom, imeInsets.bottom));

            return insets;
        });
    }

    /**
     * Initialize all views from the layout
     */
    private void initViews() {
        editTextFirstName = findViewById(R.id.editTextFirstName);
        editTextLastName = findViewById(R.id.editTextLastName);
        editTextNationalNumber = findViewById(R.id.editTextNationalNumber);
        editTextPhone = findViewById(R.id.editTextPhone);
        editTextAddress = findViewById(R.id.editTextAddress);
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPassword = findViewById(R.id.editTextPassword);
        editTextOccupation = findViewById(R.id.editTextOccupation);
        buttonSubmit = findViewById(R.id.buttonSubmit);
        footerText = findViewById(R.id.footerText);
        progressBar = findViewById(R.id.register_progress_bar);

    }

    /**
     * Navigate to Sign In Activity
     */
    private void goToSignInActivity() {
        // Create intent to start SignInActivity
        Intent intent = new Intent(UserRegister.this, LogInActivity.class);

        // Start the activity
        startActivity(intent);

        // Add transition animation
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }

    /**
     * Navigate to Main Activity
     */
    private void goToMainActivity() {
        // Create intent to start MainActivity
        Intent intent = new Intent(UserRegister.this, MainActivity.class);

        // You can pass customer data to MainActivity if needed
        // intent.putExtra("customer_name", name);

        // Start the activity
        startActivity(intent);

        // Add transition animation
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);

        // Finish this activity so user can't go back to registration
        finish();
    }

    /**
     * Validate inputs, create Customer object, and process registration
     */
    private void registerCustomer() {
        // Get input values as strings
        String lastName = editTextLastName.getText() != null ? editTextLastName.getText().toString().trim() : "";
        String firstName = editTextFirstName.getText() != null ? editTextFirstName.getText().toString().trim() : "";
        String nationalId = editTextNationalNumber.getText() != null ? editTextNationalNumber.getText().toString().trim() : "";
        String phone = editTextPhone.getText() != null ? editTextPhone.getText().toString().trim() : "";
        String address = editTextAddress.getText() != null ? editTextAddress.getText().toString().trim() : "";
        String occupation = editTextOccupation.getText() != null ? editTextOccupation.getText().toString().trim() : "";
        String email = editTextEmail.getText() != null ? editTextEmail.getText().toString().trim() : "";
        String password = editTextPassword.getText() != null ? editTextPassword.getText().toString().trim() : "";

        // Validate ALL required fields
        if (firstName.isEmpty()) {
            showError(editTextFirstName, "first name is required");
            return;
        }
        if (lastName.isEmpty()) {
            showError(editTextLastName, "last name is required");
            return;
        }

        if (nationalId.isEmpty()) {
            showError(editTextNationalNumber, "National number is required");
            return;
        }



        if (address.isEmpty()) {
            showError(editTextAddress, "Address is required");
            return;
        }
        if (email.isEmpty()) {
            showError(editTextEmail, "email is required");
            return;
        }
        if (occupation.isEmpty()) {
            showError(editTextOccupation, "Occupation is required");
            return;
        }

        // Validate national number format
        if (nationalId.length() < 10) {
            showError(editTextNationalNumber, "National number must be at least 10 digits");
            return;
        }

        // Validate phone number format
        if (phone.length() < 10&&!phone.isEmpty()) {
            showError(editTextPhone, "Please enter a valid phone number (at least 10 digits)");
            return;
        }
        if(!Validators.isValidPassword(password)) {
            showError(editTextPassword, "Please enter a valid password");
            return;
        }



        viewModel.register(nationalId, address, occupation, firstName,lastName, email, password, phone).observe(this, resource -> {
            switch (resource.status) {
                case LOADING:
                    buttonSubmit.setEnabled(false);
                    progressBar.setVisibility(View.VISIBLE);
                    break;
                case SUCCESS:
                    progressBar.setVisibility(View.GONE);
                    // Navigate to Login or Main
                    Toast.makeText(this, "Registration Successful! Welcome " + firstName+" "+lastName, Toast.LENGTH_SHORT).show();
                    goToMainActivity();
                    finish();
                    break;
                case ERROR:
                    buttonSubmit.setEnabled(true);
                    Toast.makeText(this, resource.message, Toast.LENGTH_LONG).show();
                    progressBar.setVisibility(View.GONE);
                    break;
            }
        });

        // Navigate to MainActivity
    }

    /**
     * Show error on an EditText field
     */
    private void showError(EditText editText, String errorMessage) {
        editText.setError(errorMessage);
        editText.requestFocus();
        Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show();
    }

    /**
     * Clear all input fields
     */
    private void clearForm() {
        editTextLastName.setText("");
        editTextFirstName.setText("");
        editTextNationalNumber.setText("");
        editTextPhone.setText("");
        editTextAddress.setText("");
        editTextOccupation.setText("");

        // Clear any errors
        editTextLastName.setError(null);
        editTextFirstName.setError(null);
        editTextNationalNumber.setError(null);
        editTextPhone.setError(null);
        editTextAddress.setError(null);
        editTextOccupation.setError(null);
    }

}