package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.myapplication.api.ApiService;
import com.example.myapplication.api.UserDto;
import com.example.myapplication.databinding.ActivityMainBinding;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        EdgeToEdge.enable(this);

        // generated from activity_main.xml
        ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.pikeButton.setOnClickListener(view -> {
            Intent intent = new Intent(this, MainActivity2.class);
            startActivity(intent);
        });

        EditText editTextText = findViewById(R.id.editTextText);
        Button retrieveBtn = findViewById(R.id.retrieveBtn);
        TextView usernameTextView = findViewById(R.id.usernameTextView);
        TextView emailTextView = findViewById(R.id.emailTextView);

        retrieveBtn.setOnClickListener(v -> {

            String idText = editTextText.getText().toString();

            if (idText.isEmpty()) {
                editTextText.setError("Introdueix un ID");
                return;
            }

            Long userId = Long.parseLong(idText);

            App app = (App) getApplication();
            ApiService apiService = app.getAPI();

            Call<UserDto> call = apiService.getUserById(userId);

            call.enqueue(new Callback<UserDto>() {

                @Override
                public void onResponse(Call<UserDto> call, Response<UserDto> response) {

                    if (response.isSuccessful() && response.body() != null) {

                        UserDto user = response.body();

                        // 4️⃣ Actualitzar TextViews
                        usernameTextView.setText(user.username);
                        emailTextView.setText(user.email);

                    } else {
                        usernameTextView.setText("No trobat");
                        emailTextView.setText("");
                    }
                }

                @Override
                public void onFailure(Call<UserDto> call, Throwable t) {
                    usernameTextView.setText("Error connexió");
                    emailTextView.setText("");
                }
            });

        });

        ViewCompat.setOnApplyWindowInsetsListener(binding.getRoot(), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
            // :)
        });
    }
}
