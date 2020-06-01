package com.example.kc_academy.Home;

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

import com.example.kc_academy.R;
import com.example.kc_academy.Subjects.AdminPanel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Properties;

public class TeacherLogin extends AppCompatActivity {
    EditText Temail, Tpwd;
    Button Tsignin;
    Properties properties;
    private FirebaseAuth firebaseAuth;
    private FirebaseUser user1;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference myref;
    private FirebaseAuth.AuthStateListener mAuthStateListener;
    ProgressDialog pd;
    TextView admin;

    //    String emailT = "staff107@kc.com", pwd= "admin107";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_login);
        Temail = findViewById(R.id.email);
        Tpwd = findViewById(R.id.rt_pass);
        Tsignin = findViewById(R.id.btn_signin);
        admin = findViewById(R.id.go_admin);
        admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(TeacherLogin.this, AdminPanel.class));
                finish();
            }
        });

        //initialize firebase
        firebaseAuth = FirebaseAuth.getInstance();
        user1 = firebaseAuth.getCurrentUser();
        firebaseDatabase = FirebaseDatabase.getInstance();
        myref = firebaseDatabase.getReference("Teacher");


      /*  mAuthStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser mFirebaseUser = firebaseAuth.getCurrentUser();
                if (mFirebaseUser != null) {

                    Toast.makeText(TeacherLogin.this, "You are logged in", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(TeacherLogin.this, ThomeActivity.class);
                    //     i.putExtra("abc",TName);
                    startActivity(i);
                } else {
                    Toast.makeText(TeacherLogin.this, "Please Login", Toast.LENGTH_SHORT).show();
                }
            }
        };

       */






            Tsignin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    myref.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            for (DataSnapshot ds : dataSnapshot.getChildren()) {
                                //

                                String Email = "" + ds.child("email").getValue();
                                 final String TName = "" + ds.child("name").getValue();




                                final String email = Temail.getText().toString();
                                final String pwd = Tpwd.getText().toString();


                                if (email.isEmpty()) {
                                    Temail.setError("Please enter email Id");
                                    Temail.requestFocus();
                                } else if (pwd.isEmpty()) {
                                    Tpwd.setError("Please enter a Password");
                                    Tpwd.requestFocus();
                                } else if (Tpwd.getText().length() < 6) {
                                    Tpwd.setError("Password must be min 6 char.");
                                    Tpwd.requestFocus();
                                } else if (email.isEmpty() && pwd.isEmpty()) {
                                    Toast.makeText(TeacherLogin.this, "Fields are empty", Toast.LENGTH_SHORT).show();
                                }
                                else if (!(email.isEmpty() && pwd.isEmpty())) {


                                    if (Email.equals(Temail.getText().toString())) {
                                        firebaseAuth.signInWithEmailAndPassword(email, pwd)
                                                .addOnCompleteListener(TeacherLogin.this, new OnCompleteListener<AuthResult>() {
                                                    @Override
                                                    public void onComplete(@NonNull Task<AuthResult> task) {
                                                        if (!task.isSuccessful()) {
                                                            Toast.makeText(TeacherLogin.this, "login Error, pLease Try again", Toast.LENGTH_SHORT).show();

                                                        } else {
                                                            Intent intToHome = new Intent(TeacherLogin.this, ThomeActivity.class);
                                                            Toast.makeText(TeacherLogin.this, "You're LogIn ", Toast.LENGTH_SHORT).show();
                                                            intToHome.putExtra("abc",TName);
                                                            startActivity(intToHome);
                                                        }
                                                    }
                                                });
                                    }
                                    else {
                                        Toast.makeText(TeacherLogin.this, "Please Go to Student Section", Toast.LENGTH_SHORT).show();
                                    }

                                }
                                else {
                                    Toast.makeText(TeacherLogin.this, "Error Occurred", Toast.LENGTH_SHORT).show();
                                }
                            }

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                }
            });
        pd = new ProgressDialog(this);
        pd.setMessage("Loggin....");
        }


    private void SignIn(final String email, final String Tpwd) {
        myref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    //

                    String Email = "" + ds.child("email").getValue();
                    String pwd = "" + ds.child("Tpwd").getValue();
                    String TName = "" + ds.child("name").getValue();
                    String Tdob = "" + ds.child("dob").getValue();
                    String TGender = "" + ds.child("gender").getValue();
                    String TPhone = "" + ds.child("phone").getValue();

                }

            }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }


    @Override
    public void onBackPressed() {
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

  //  @Override
   /* protected void onStart() {
        super.onStart();
        firebaseAuth.addAuthStateListener(mAuthStateListener);
    }

    */
}


/*.............................................................................................................................................
 Query query = myref.orderByChild("email").equalTo(Temail.getText().toString());
                query.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        for (DataSnapshot ds : dataSnapshot.getChildren()) {
                            //

                            String Email = "" + ds.child("email").getValue();
                            String pwd = "" + ds.child("Tpwd").getValue();
                            //      LogIn(Email, pwd);
                            if (Email.equals(Temail.getText().toString())){
                                if (pwd.equals(Tpwd.getText().toString())){
                                    Toast.makeText(TeacherLogin.this, "Login Successfull", Toast.LENGTH_SHORT).show();
                                    Intent Tlog = new Intent(TeacherLogin.this, HomeActivity.class);
                                    startActivity(Tlog);
                                }
                                else
                                    Toast.makeText(TeacherLogin.this, "Please Enter correct Tpwd", Toast.LENGTH_SHORT).show();
                            }
                            else
                                Toast.makeText(TeacherLogin.this, "Login Failed.........", Toast.LENGTH_SHORT).show();



                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
 ......................................................................................................................................................*/
/*.......................................................................................................................................................................................................
 final String email = Temail.getText().toString();
                final String pwd = Tpwd.getText().toString();
    //            Toast.makeText(TeacherLogin.this, "Testing.....", Toast.LENGTH_SHORT).show();
                if (email.isEmpty()) {
                    Temail.setError("Please enter email Id");
                    Temail.requestFocus();
                }

                else if (pwd.isEmpty()) {
                    Tpwd.setError("Please enter a Password");
                    Tpwd.requestFocus();
                }

                else if (email.isEmpty() && pwd.isEmpty()) {
                    Toast.makeText(TeacherLogin.this, "Fields are empty", Toast.LENGTH_SHORT).show();
                }
                else if (!(email.isEmpty() && pwd.isEmpty())){
                    if (emailT.equals(Temail.getText().toString())){
                        if (pwd.equals(Tpwd.getText().toString())){

                            firebaseAuth.signInWithEmailAndPassword(email, pwd)
                                    .addOnCompleteListener(TeacherLogin.this, new OnCompleteListener<AuthResult>() {
                                        @Override
                                        public void onComplete(@NonNull Task<AuthResult> task) {
                                            if (!task.isSuccessful()){
                                                Toast.makeText(TeacherLogin.this,"login Error, pLease Try again",Toast.LENGTH_SHORT).show();
                                            }
                                            else{
                                                Intent intToHome = new Intent(TeacherLogin.this, HomeActivity.class);
                                                startActivity(intToHome);

                                            }
                                        }
                                    });
                        }
                    }
                }
                else {
                    Toast.makeText(TeacherLogin.this,"Error Occurred",Toast.LENGTH_SHORT).show();
                }
 */

/*
 //      LogIn(Email, pwd);
                            if (Email.equals(Temail.getText().toString())){
                                if (pwd.equals(Tpwd.getText().toString())){
                                    Toast.makeText(TeacherLogin.this, "Login Successfull", Toast.LENGTH_SHORT).show();
                                    Intent Tlog = new Intent(TeacherLogin.this, ThomeActivity.class);
                                    Tlog.putExtra("Name",TName);
                                    Tlog.putExtra("Dob",Tdob);
                                    Tlog.putExtra("Gender",TGender);
                                    Tlog.putExtra("Phone",TPhone);
                                    Tlog.putExtra("Email",Email);
                                    startActivity(Tlog);
                                }
                                else
                                    Toast.makeText(TeacherLogin.this, "Please Enter correct Tpwd", Toast.LENGTH_SHORT).show();
                            }
                            else
                                Toast.makeText(TeacherLogin.this, "Login Failed.........", Toast.LENGTH_SHORT).show();





 if (firebaseAuth.getCurrentUser() == null){
            Toast.makeText(TeacherLogin.this, "Please Login", Toast.LENGTH_SHORT).show();
        }
        else {
            startActivity(new Intent(TeacherLogin.this, ThomeActivity.class));
            finish();
        }
 */
