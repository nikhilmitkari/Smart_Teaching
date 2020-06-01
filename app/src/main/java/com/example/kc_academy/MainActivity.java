package com.example.kc_academy;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.kc_academy.Home.TeacherLogin;
import com.example.kc_academy.Home.ThomeActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    ImageButton btnStudent, btnTeacher;
    TextView studenttext, teachertext;
    TextView setuptext, selecttext;
    LinearLayout teacher;
    FirebaseAuth mFirebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;
    FirebaseUser user1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnStudent = findViewById(R.id.Student);
        btnTeacher = findViewById(R.id.Teacher);
        studenttext = findViewById(R.id.textView11);
        teachertext = findViewById(R.id.textView12);
        teacher = findViewById(R.id.teacherlogin);
        setuptext = findViewById(R.id.textView8);
        selecttext = findViewById(R.id.textView9);

        mAuthStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
                if (mFirebaseAuth.getCurrentUser() != null) {

                    String uid = Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getUid();
                    DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
                    DatabaseReference uidRef = rootRef.child("Users").child(uid);

                    ValueEventListener valueEventListener = new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            if (dataSnapshot.exists()){
                                startActivity(new Intent(MainActivity.this, HomeActivity.class));
                                finish();
                            }else{
                                startActivity(new Intent(MainActivity.this, ThomeActivity.class));
                                finish();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    };
                    uidRef.addListenerForSingleValueEvent(valueEventListener);
                }

            }
            //   FirebaseUser mFirebaseUser = mFirebaseAuth.getCurrentUser();
        };

        btnStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent its = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(its);
                finish();
            }
        });

        btnTeacher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent its = new Intent(MainActivity.this, TeacherLogin.class);
                startActivity(its);
                finish();
            }
        });

    }

    @Override
    public void onStart() {
        super.onStart();

        mAuthStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
                if (mFirebaseAuth.getCurrentUser() != null) {

                    String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
                    DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
                    DatabaseReference uidRef = rootRef.child("Users").child(uid);

                    ValueEventListener valueEventListener = new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            if (dataSnapshot.exists()){
                                startActivity(new Intent(MainActivity.this, HomeActivity.class));
                                finish();
                            }else{
                                startActivity(new Intent(MainActivity.this, ThomeActivity.class));
                                finish();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    };
                    uidRef.addListenerForSingleValueEvent(valueEventListener);
                }

            }
            //   FirebaseUser mFirebaseUser = mFirebaseAuth.getCurrentUser();
        };
    }
}

/*
  mFirebaseAuth = FirebaseAuth.getInstance();
        user1 = mFirebaseAuth.getCurrentUser();
     FirebaseDatabase   firebaseDatabase = FirebaseDatabase.getInstance();
     if (user1!=null){
                           startActivity(new Intent(MainActivity.this, HomeActivity.class));
                           finish();
                   }
               }
 */

