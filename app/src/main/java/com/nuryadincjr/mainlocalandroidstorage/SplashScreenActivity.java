package com.nuryadincjr.mainlocalandroidstorage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

public class SplashScreenActivity extends AppCompatActivity {

    private  LocalPreference localPreference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        getSupportActionBar().hide();

        localPreference = new LocalPreference(this);

        Handler handler = new Handler(Looper.getMainLooper());
        handler.postDelayed(() -> {
            boolean isLogin = localPreference.getPreferences().getBoolean(Constaint.PREF_IS_LOGIN, false);

            Intent intent;

            if(isLogin) {
                intent = new Intent(this, MainActivity.class);
            } else
                intent = new Intent(this, LoginActivity.class);

            startActivity(intent);
        }, 3000);
    }
}