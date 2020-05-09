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
    private String  otp;
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallback;
    private FirebaseAuth auth = FirebaseAuth.getInstance();
    String number ;
    private String verificationCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_verification);

        Button sendOtp = findViewById(R.id.generateOTP);
        Button signIn = findViewById(R.id.singin);
        phoneNumber =  findViewById(R.id.enter_phonenumber);
        enterOtp =  findViewById(R.id.enter_otp);

        ImageView img1 = findViewById(R.id.checkLength);
        ImageView img2 = findViewById(R.id.checkOTP);
        final PhoneAuthProvider authProvider = PhoneAuthProvider.getInstance();

        sendOtp.setOnClickListener(new View.OnClickListener() {
            @Override
              public void onClick(View v) {
                String mobile = phoneNumber.getText().toString();
                if (mobile.isEmpty()) {
                    phoneNumber.setError("enter valid phone number");
                    phoneNumber.requestFocus();
                    return;
                }

                Toast.makeText(PhoneVerification.this, "No is " + mobile,Toast.LENGTH_LONG).show();

                authProvider.verifyPhoneNumber(
                        mobile,
                        60 ,
                        TimeUnit.SECONDS,
                        PhoneVerification.this,
                        mCallback);
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