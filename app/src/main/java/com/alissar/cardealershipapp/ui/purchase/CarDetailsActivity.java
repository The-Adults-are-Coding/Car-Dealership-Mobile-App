package com.alissar.cardealershipapp.ui.purchase;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.alissar.cardealershipapp.R;
import com.alissar.cardealershipapp.data.model.Car;

public class CarDetailsActivity extends AppCompatActivity {

    private Car car;
    private SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_details);

        prefs = getSharedPreferences("UserProfile", MODE_PRIVATE);

        // Get Car object from Intent
        car = (Car) getIntent().getSerializableExtra("car_data");

        if (car != null) {
            setupUI();
        }

        Button btnBuy = findViewById(R.id.btnBuy);
        btnBuy.setOnClickListener(v -> showPaymentMethodDialog());
    }

    private void setupUI() {
        ImageView ivCarImage = findViewById(R.id.ivCarImage);
        TextView tvManufacturer = findViewById(R.id.tvManufacturer);
        TextView tvCarName = findViewById(R.id.tvCarName);
        TextView tvPrice = findViewById(R.id.tvPrice);
        TextView tvModel = findViewById(R.id.tvModel);
        TextView tvCondition = findViewById(R.id.tvCondition);
        TextView tvColor = findViewById(R.id.tvColor);
        TextView tvEngine = findViewById(R.id.tvEngine);

        //tvManufacturer.setText(car.getManufacturer());
        tvCarName.setText(car.getFullName());
        tvPrice.setText(car.getFormattedPrice());
        //tvModel.setText(car.getModel());
        //tvCondition.setText(car.getCondition());
        //tvColor.setText(car.getColor());
        //tvEngine.setText(car.getEngine());
    }

    private void showPaymentMethodDialog() {
        com.google.android.material.bottomsheet.BottomSheetDialog bottomSheetDialog = new com.google.android.material.bottomsheet.BottomSheetDialog(this);
        View view = getLayoutInflater().inflate(R.layout.dialog_payment_method, null);
        bottomSheetDialog.setContentView(view);

        Button btnPayCash = view.findViewById(R.id.btnPayCash);
        Button btnPayInstallments = view.findViewById(R.id.btnPayInstallments);

        btnPayCash.setOnClickListener(v -> {
            bottomSheetDialog.dismiss();
            handleCashPurchase();
        });

        btnPayInstallments.setOnClickListener(v -> {
            bottomSheetDialog.dismiss();
            showInstallmentDialog();
        });

        bottomSheetDialog.show();
    }

    private void handleCashPurchase() {
        // Get user balance (defaulting to 100000 for testing)
        String balanceStr = prefs.getString("balance", "100000");
        double balance = Double.parseDouble(balanceStr.replace(",", ""));

        // Parse car price (removing $ and commas)
        String cleanPrice = car.getFormattedPrice().replaceAll("[^\\d.]", "");
        double carPrice = Double.parseDouble(cleanPrice);

        if (balance >= carPrice) {
            // Deduct balance
            double newBalance = balance - carPrice;
            prefs.edit().putString("balance", String.valueOf(newBalance)).apply();

            showResultDialog("Success!", "You have successfully purchased the " + car.getFullName() + ".");
        } else {
            showResultDialog("Insufficient Funds", "Your balance is too low to purchase this vehicle.");
        }
    }

    private void showInstallmentDialog() {
        String[] months = {"12 Months", "24 Months", "36 Months", "48 Months", "60 Months"};
        final int[] selectedMonths = {12, 24, 36, 48, 60};

        new AlertDialog.Builder(this)
                .setTitle("Select Installment Plan")
                .setItems(months, (dialog, which) -> {
                    int monthsToPay = selectedMonths[which];

                    String cleanPrice = car.getFormattedPrice().replaceAll("[^\\d.]", "");
                    double carPrice = Double.parseDouble(cleanPrice);
                    double monthlyPayment = carPrice / monthsToPay;

                    String message = String.format("You will pay $%.2f per month for %d months.", monthlyPayment, monthsToPay);

                    new AlertDialog.Builder(CarDetailsActivity.this)
                            .setTitle("Confirm Installment Plan")
                            .setMessage(message)
                            .setPositiveButton("Confirm", (d, w) -> {
                                showResultDialog("Success!", "Your installment plan has been approved.");
                            })
                            .setNegativeButton("Cancel", null)
                            .show();
                })
                .show();
    }

    private void showResultDialog(String title, String message) {
        new AlertDialog.Builder(this)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton("OK", (dialog, which) -> {
                    if (title.equals("Success!")) {
                        finish(); // Go back to main screen on success
                    }
                })
                .show();
    }
}
