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
    LoginButton facebookLogin;
    RelativeLayout phoneLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        phoneLogin = findViewById(R.id.lay_3);
        phoneLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, PhoneVerification.class);
                startActivity(intent);

            }
        });


        facebookLogin = findViewById(R.id.loginButton);
        facebookLogin.setOnClickListener(new View.OnClickListener() {
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




