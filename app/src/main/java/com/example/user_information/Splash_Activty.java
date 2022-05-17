package com.example.user_information;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.airbnb.lottie.LottieAnimationView;

public class Splash_Activty extends AppCompatActivity {
    LottieAnimationView lottieAnimationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_activty);

        lottieAnimationView = findViewById(R.id.splash);
        lottieAnimationView.setImageAssetsFolder("assets");
        lottieAnimationView.setAnimation("splash.json");
        lottieAnimationView.playAnimation();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(Splash_Activty.this,Login_Activty.class));
                finish();
            }
        },4000);
    }
}