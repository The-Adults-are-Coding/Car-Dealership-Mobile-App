package com.alissar.cardealershipapp.ui.register;

import static com.alissar.cardealershipapp.utils.Resource.Status.LOADING;

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

import com.alissar.cardealershipapp.R;
import com.alissar.cardealershipapp.data.model.Customer;
import com.alissar.cardealershipapp.ui.main.MainActivity;

import com.alissar.cardealershipapp.R;
import com.alissar.cardealershipapp.data.model.Customer;
import com.alissar.cardealershipapp.ui.main.MainActivity;

public class UserRegister extends AppCompatActivity {

    // Declare UI elements - changed to EditText
    private EditText editTextName;
    private EditText editTextNationalNumber;
    private EditText editTextPhone;
    private EditText editTextAddress;
    private EditText editTextOccupation;
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
                //handleRegistration();
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
    }

    /**
     * Initialize all views from the layout
     */
    private void initViews() {
        editTextName = findViewById(R.id.editTextName);
        editTextNationalNumber = findViewById(R.id.editTextNationalNumber);
        editTextPhone = findViewById(R.id.editTextPhone);
        editTextAddress = findViewById(R.id.editTextAddress);
        editTextOccupation = findViewById(R.id.editTextOccupation);
        buttonSubmit = findViewById(R.id.buttonSubmit);
        footerText = findViewById(R.id.footerText);
        progressBar = findViewById(R.id.login_progress_bar);

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
        String name = editTextName.getText() != null ? editTextName.getText().toString().trim() : "";
        String nationalId = editTextNationalNumber.getText() != null ? editTextNationalNumber.getText().toString().trim() : "";
        String phone = editTextPhone.getText() != null ? editTextPhone.getText().toString().trim() : "";
        String address = editTextAddress.getText() != null ? editTextAddress.getText().toString().trim() : "";
        String occupation = editTextOccupation.getText() != null ? editTextOccupation.getText().toString().trim() : "";

        // Validate ALL required fields
        if (name.isEmpty()) {
            showError(editTextName, "Full name is required");
            return;
        }

        if (nationalId.isEmpty()) {
            showError(editTextNationalNumber, "National number is required");
            return;
        }

        if (phone.isEmpty()) {
            showError(editTextPhone, "Phone number is required");
            return;
        }

        if (address.isEmpty()) {
            showError(editTextAddress, "Address is required");
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
        if (phone.length() < 10) {
            showError(editTextPhone, "Please enter a valid phone number (at least 10 digits)");
            return;
        }

        // Create Customer object
        Customer newCustomer = new Customer(
                name,
                nationalId,
                phone,
                address,
                occupation
        );

        // Show success message
        Toast.makeText(this, "Registration Successful! Welcome " + name, Toast.LENGTH_SHORT).show();

        // TODO: Save customer to database
        // saveCustomerToDatabase(newCustomer);

        // Navigate to MainActivity
        goToMainActivity();
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
        editTextName.setText("");
        editTextNationalNumber.setText("");
        editTextPhone.setText("");
        editTextAddress.setText("");
        editTextOccupation.setText("");

        // Clear any errors
        editTextName.setError(null);
        editTextNationalNumber.setError(null);
        editTextPhone.setError(null);
        editTextAddress.setError(null);
        editTextOccupation.setError(null);
    }

//    private void handleRegistration() {
//        String name = etName.getText().toString().trim();
//        String email = etEmail.getText().toString().trim();
//        String password = etPassword.getText().toString().trim();
//        String phone = etPhone.getText().toString().trim();
//
//        // Basic Validation
//        if (name.isEmpty() || email.isEmpty() || password.length() < 6) {
//            Toast.makeText(this, "Please fill all fields correctly", Toast.LENGTH_SHORT).show();
//            return;
//        }
//
//        viewModel.register(name, email, password, phone).observe(this, resource -> {
//            switch (resource.status) {
//                case LOADING:
//                    btnRegister.setEnabled(false);
//                    progressBar.setVisibility(View.VISIBLE);
//                    break;
//                case SUCCESS:
//                    progressBar.setVisibility(View.GONE);
//                    Toast.makeText(this, "Account Created Successfully!", Toast.LENGTH_SHORT).show();
//                    // Navigate to Login or Main
//                    finish();
//                    break;
//                case ERROR:
//                    btnRegister.setEnabled(true);
//                    progressBar.setVisibility(View.GONE);
//                    Toast.makeText(this, resource.message, Toast.LENGTH_LONG).show();
//                    break;
//            }
//        });
//    }
}