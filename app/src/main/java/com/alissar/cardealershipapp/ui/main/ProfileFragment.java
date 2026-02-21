package com.alissar.cardealershipapp.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.alissar.cardealershipapp.ui.main.MainActivity; // Check your package path
// import com.alissar.cardealershipapp.ui.login.LoginActivity; // You might need this for logout
import com.alissar.cardealershipapp.R;
import com.alissar.cardealershipapp.ui.profile.PersonalInfoActivity;

public class ProfileFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // 1. Inflate the renamed layout
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // 2. Find Views using 'view.findViewById'
        Button btnPersonalInfo = view.findViewById(R.id.btnPersonalInfo);
        Button btnChangeLanguage = view.findViewById(R.id.btnChangeLanguage); // Renamed variable to match ID
        Button btnLogout = view.findViewById(R.id.btnLogout);
        TextView tvChangePhoto = view.findViewById(R.id.tvChangePhoto);
        // ImageView ivProfile = view.findViewById(R.id.ivProfile); // If you need to set the image

        // 3. Setup Click Listeners

        // Navigate to Personal Info Activity
        btnPersonalInfo.setOnClickListener(v -> {
            Intent intent = new Intent(requireActivity(), PersonalInfoActivity.class);
            startActivity(intent);
        });

        // Navigate to Change Language Activity
        btnChangeLanguage.setOnClickListener(v -> {
            // Ensure ChangeLanguageActivity exists in your project
            // Intent intent = new Intent(requireActivity(), ChangeLanguageActivity.class);
            // startActivity(intent);
            Toast.makeText(getContext(), "Change Language Clicked", Toast.LENGTH_SHORT).show();
        });

        // Logout Logic
        btnLogout.setOnClickListener(v -> {
            // Usually logout goes to LoginActivity, but using MainActivity as per your code
            Intent intent = new Intent(requireActivity(), MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            // finish() is not needed here because strictly clearing task handles it
        });

        tvChangePhoto.setOnClickListener(v -> {
            Toast.makeText(getContext(), "Open Gallery...", Toast.LENGTH_SHORT).show();
            // Add image picker logic here
        });
    }
}