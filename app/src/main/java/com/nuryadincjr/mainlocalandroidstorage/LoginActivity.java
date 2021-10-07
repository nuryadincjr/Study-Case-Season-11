package com.nuryadincjr.mainlocalandroidstorage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.nuryadincjr.mainlocalandroidstorage.databinding.ActivityLoginBinding;

public class LoginActivity extends AppCompatActivity {

    private ActivityLoginBinding binding;
    private LocalPreference localPreference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        localPreference = LocalPreference.getInstance(this);

        binding.btnLogin.setOnClickListener(v -> {
            localPreference.getEditor().putBoolean(Constaint.PREF_IS_LOGIN, true);
            localPreference.getEditor().apply();

            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        });
    }
}

