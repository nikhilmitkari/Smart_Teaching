package com.example.kc_academy;


import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class LoginActivity extends AppCompatActivity {
    EditText emailId, password;
    Button btnSignIn;
    TextView tvSignUp, tvForget;
    FirebaseAuth mFirebaseAuth;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    private FirebaseUser user;
    FirebaseDatabase database;
    DatabaseReference mReference;
    boolean isEmailValid, isPasswordValid;
    private FirebaseAuth.AuthStateListener mAuthStateListener;

    ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mFirebaseAuth = FirebaseAuth.getInstance();
        emailId = findViewById(R.id.email);
        password = findViewById(R.id.rt_pass);
        btnSignIn = findViewById(R.id.btn_signin);
        tvSignUp = findViewById(R.id.textView4);
        tvForget = findViewById(R.id.resetpassword);

        mAuthStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser mFirebaseUser = mFirebaseAuth.getCurrentUser();
                if (mFirebaseUser != null) {
                    Toast.makeText(LoginActivity.this, "You are logged in", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(LoginActivity.this, HomeActivity.class);
                    startActivity(i);
                } else {
                    Toast.makeText(LoginActivity.this, "Please Login", Toast.LENGTH_SHORT).show();
                }
            }
        };

        tvSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent itr = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(itr);
            }
        });

        tvForget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent itf = new Intent(LoginActivity.this, ForgetpassActivity.class);
                startActivity(itf);
            }
        });


        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final String email = emailId.getText().toString();
                final String pwd = password.getText().toString();



                if (email.isEmpty()) {
                    emailId.setError("Please enter email Id");
                    emailId.requestFocus();
                }
                else if (!(emailId.getText().toString().trim().matches(emailPattern))){
                    Toast.makeText(getApplicationContext(),"Invalid email address", Toast.LENGTH_SHORT).show();
                }
                else if (pwd.isEmpty()) {
                    password.setError("Please enter a Password");
                    password.requestFocus();
                }
                else if (password.getText().length() < 6) {
                    password.setError("Password must be min 6 char.");
                    password.requestFocus();
                }
                else if (email.isEmpty() && pwd.isEmpty()) {
                    Toast.makeText(LoginActivity.this, "Fields are empty", Toast.LENGTH_SHORT).show();
                }
                else if (!(email.isEmpty() && pwd.isEmpty())){
                    mFirebaseAuth.signInWithEmailAndPassword(email, pwd)
                            .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (!task.isSuccessful()){
                                Toast.makeText(LoginActivity.this,"login Error, pLease Try again",Toast.LENGTH_SHORT).show();

                            }
                            else{
                                Intent intToHome = new Intent(LoginActivity.this, HomeActivity.class);
                                startActivity(intToHome);
                                loginuser(email,pwd);
                            }
                        }
                    });


                }
                else {
                    Toast.makeText(LoginActivity.this,"Error Occurred",Toast.LENGTH_SHORT).show();
                }
            }
        });

        pd = new ProgressDialog(this);
        pd.setMessage("Loggin....");
        
        }

    private void loginuser(String email, String pwd) {
        pd.show();
        mFirebaseAuth.signInWithEmailAndPassword(email,pwd)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    pd.dismiss();
                    // Sign in success, update UI with the signed-in user's information

                    startActivity(new Intent(LoginActivity.this,HomeActivity.class));
                    finish();
                } else {
                    pd.dismiss();
                    // If sign in fails, display a message to the user.
                    Toast.makeText(LoginActivity.this, "Authentication failed.", Toast.LENGTH_SHORT).show();

                }

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(LoginActivity.this,""+e.getMessage(),Toast.LENGTH_SHORT).show();
                pd.dismiss();
            }
        });
    }

    @Override
    public void onBackPressed() {
        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {}
        });
        InterstitialAd mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId("ca-app-pub-3940256099942544~3347511713");
        mInterstitialAd.loadAd(new AdRequest.Builder().build());

        Exit();
    }

    public void Exit() {

        new android.app.AlertDialog.Builder(this)
                .setIcon(R.mipmap.kc)
                .setTitle(getString(R.string.app_name))
                .setMessage(getString(R.string.backbutton))
                .setPositiveButton(getString(R.string.yes_dialog), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();

                        Intent i = new Intent();
                        i.setAction(Intent.ACTION_MAIN);
                        i.addCategory(Intent.CATEGORY_HOME);
                        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(i);
                        finish();

                    }
                })
                .setNegativeButton(getString(R.string.no_dialog), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .show();
    }



    @Override
    protected void onStart() {
        super.onStart();
        mFirebaseAuth.addAuthStateListener(mAuthStateListener);
    }
}
/*
FirebaseUser user = mFirebaseAuth.getCurrentUser();
                    //get useremail and uid from auth
                    String email = user.getEmail();
                    String uid = user.getUid();
                    //store details in realtime
                    HashMap<Object, String> hashMap = new HashMap<>();
                    hashMap.put("email",email);
                    hashMap.put("uid",uid);
                    hashMap.put("name","");
                    hashMap.put("phone","");
                    hashMap.put("dob","");
                    hashMap.put("image","");

                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                    //path to store data named "Users"
                    DatabaseReference reference = database.getReference("Users");
                    reference.child(uid).setValue(hashMap);
 */