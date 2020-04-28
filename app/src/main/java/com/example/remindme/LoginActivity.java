package com.example.remindme;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import com.facebook.login.widget.LoginButton;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends Activity {
    AnimationDrawable animDrawable;
    FirebaseAuth mAuth;
    LoginButton layout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        layout = findViewById(R.id.loginButton);
        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, FacebookLogin.class);
                startActivity(intent);
            }
        });
        //gradient animation
        RelativeLayout rootLayout = findViewById(R.id.layout);
        rootLayout.setBackgroundResource(R.drawable.gradient_animation);
        animDrawable = (AnimationDrawable) rootLayout.getBackground();
        animDrawable.start();

    }
        }




