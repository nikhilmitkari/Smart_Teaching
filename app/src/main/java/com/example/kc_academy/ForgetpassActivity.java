package com.example.kc_academy;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ForgetpassActivity extends AppCompatActivity {
    FirebaseAuth mFirebaseAuth;
    EditText emailAddress;
    Button btnverify;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgetpass);

        emailAddress = findViewById(R.id.reemail);
        btnverify = findViewById(R.id.btn_verify);
        mFirebaseAuth = FirebaseAuth.getInstance();

        btnverify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String useremail = emailAddress.getText().toString().trim();

                if(useremail.equals("")){
                    Toast.makeText(ForgetpassActivity.this, "Please enter your registered email ID", Toast.LENGTH_SHORT).show();
                }else{
                    mFirebaseAuth.sendPasswordResetEmail(useremail).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(ForgetpassActivity.this, "Password reset email sent!", Toast.LENGTH_SHORT).show();
                                finish();
                                startActivity(new Intent(ForgetpassActivity.this, LoginActivity.class));
                            }else{
                                Toast.makeText(ForgetpassActivity.this, "Error in sending password reset email", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });

    }

}