package com.anees.pdma.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.anees.pdma.R;

public class SplashScreen extends AppCompatActivity {

    Animation TopAnimation, BottomAnimation;
    LinearLayout linearLayout_one,linearLayout_two;
    private static int SPLASH_SCREEN = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash_screen);

        linearLayout_one = (LinearLayout) findViewById(R.id.id_linearLayout_one);
        linearLayout_two = (LinearLayout) findViewById(R.id.id_linearLayout_two);

        TopAnimation = AnimationUtils.loadAnimation(this,R.anim.top_animation);
        BottomAnimation = AnimationUtils.loadAnimation(this,R.anim.bottom_animation);

        linearLayout_one.setAnimation(TopAnimation);
        linearLayout_two.setAnimation(BottomAnimation);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent newIntent = new Intent(SplashScreen.this, Home.class);
                startActivity(newIntent);
                finish();
            }
        }, SPLASH_SCREEN);

    }
}