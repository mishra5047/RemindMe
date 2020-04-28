package com.example.remindme;

import androidx.annotation.NonNull;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class PhoneVerification extends Activity {

    private EditText phoneNumber,enterOtp;
    private Button sendOtp, signIn;
    private String  number, otp;
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallback;
    private FirebaseAuth auth;
    private String verificationCode;
    private ImageView img1, img2;

    public String getPhoneNumber() {
        return phoneNumber.getText().toString();
    }

    public EditText getEnterOtp() {
        return enterOtp;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_verification);

        sendOtp = (Button) findViewById(R.id.generate);
        signIn = (Button) findViewById(R.id.singin);
        phoneNumber = (EditText) findViewById(R.id.enter_phonenumber);
        enterOtp = (EditText) findViewById(R.id.enter_otp);

        img1 = findViewById(R.id.checkLength);
        img2 = findViewById(R.id.checkOTP);



        sendOtp.setOnClickListener(new View.OnClickListener() {
            @Override
              public void onClick(View v) {
                String input = getPhoneNumber();
                sendOtp(input);
            }});

        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                otp = enterOtp.getText().toString();
                PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verificationCode, otp);
                SigninWithPhone(credential);
            }
        });

    }

    private void sendOtp(String number){
        if (number.equals("")) {
            Toast.makeText(PhoneVerification.this, "Input Is Null", Toast.LENGTH_LONG).show();
        }

        else
        {
            if (number.length() == 10) img1.setImageResource(R.drawable.tick);

            PhoneAuthProvider.getInstance().verifyPhoneNumber(
                    "+91" + number,
                    60 ,
                    TimeUnit.SECONDS,
                    PhoneVerification.this,
                    mCallback);
        }}

    private void SigninWithPhone(PhoneAuthCredential credential){
        auth.signInWithCredential(credential).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    startActivity(new Intent(PhoneVerification.this, InputActivity.class));
                    finish();
                }else
                {
                    Toast.makeText(PhoneVerification.this, "INCORRECT OTP", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
    private void StartFirebaseLogin(){
        auth = FirebaseAuth.getInstance();
        mCallback  = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            @Override
            public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {
                Toast.makeText(PhoneVerification.this,"verification complete ", Toast.LENGTH_LONG).show();
            }


            @Override
            public void onVerificationFailed(FirebaseException e) {
                Toast.makeText(PhoneVerification.this, "verification failed", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onCodeSent(String s, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                super.onCodeSent(s, forceResendingToken);
                verificationCode = s;
                Toast.makeText(PhoneVerification.this, "code sent", Toast.LENGTH_LONG).show();
            }
        };
    }

}