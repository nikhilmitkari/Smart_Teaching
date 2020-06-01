package com.example.kc_academy.Home;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.kc_academy.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;
import java.util.HashMap;

public class TeacherProfile extends AppCompatActivity{

    private static final String TAG = TeacherProfile.class.getSimpleName();


    //firebase
    private FirebaseAuth mfirebaseAuth;
    private FirebaseUser userT;
    //views from xml

    EditText tvphoneT, tvnameT;
     TextView tvemailT;
     EditText tvdobT, tvgenderT;
     FirebaseDatabase mfirebaseDatabase;
     DatabaseReference myrefT;
     Button btnEditT;
    private DatePickerDialog.OnDateSetListener mDateSetListener;

    public TeacherProfile() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_profile);
        tvnameT = findViewById(R.id.name012);
        tvphoneT = findViewById(R.id.mbno012);
        tvemailT = findViewById(R.id.mail012);
        tvdobT = findViewById(R.id.dob014);
        tvgenderT = findViewById(R.id.genderT);
        btnEditT = findViewById(R.id.btn_editT);

        //initialize firebase
        mfirebaseAuth = FirebaseAuth.getInstance();
        userT = mfirebaseAuth.getCurrentUser();
        mfirebaseDatabase = FirebaseDatabase.getInstance();
        myrefT = mfirebaseDatabase.getReference("Teacher");

        Query query = myrefT.orderByChild("email").equalTo(userT.getEmail());
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                //check untill required data get

                for (DataSnapshot ds: dataSnapshot.getChildren()){
                    //
                    String Name = ""+ ds.child("name").getValue();
                    String Phone = ""+ ds.child("phone").getValue();
                    String Email = ""+ ds.child("email").getValue();
                    String dob = ""+ ds.child("dob").getValue();
                    String gender = ""+ ds.child("gender").getValue();

                    tvnameT.setText(Name);
                    tvphoneT.setText(Phone);
                    tvemailT.setText(Email);
                    tvdobT.setText(dob);
                    tvgenderT.setText(gender);

                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



        btnEditT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userT = mfirebaseAuth.getCurrentUser();

                //get useremail and uid from auth

                String email = userT.getEmail();
                String uid = userT.getUid();
                //store details in realtime
                HashMap<Object, String> hashMap = new HashMap<>();
                hashMap.put("email", email);
                hashMap.put("uid", uid);
                hashMap.put("name", tvnameT.getText().toString());
                hashMap.put("phone", tvphoneT.getText().toString());
                hashMap.put("dob", tvdobT.getText().toString());
                hashMap.put("gender", tvgenderT.getText().toString());

                FirebaseDatabase database = FirebaseDatabase.getInstance();
                //path to store data named "Users"
                DatabaseReference reference = database.getReference("Teacher");
                reference.child(uid).setValue(hashMap);

                Toast.makeText(TeacherProfile.this, "Profile Update Successfully", Toast.LENGTH_SHORT).show();

            }
        });

        tvdobT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                       TeacherProfile.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mDateSetListener,
                        year,month,day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                String date = month + "/" + day + "/" + year;
                tvdobT.setText(date);
            }
        };

    }

  /*  private void SignIn(){
        myrefT.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    //

                    String Email = "" + ds.child("email").getValue();
                    String pwd = "" + ds.child("password").getValue();
                    String TName = "" + ds.child("name").getValue();
                    String TDob = ""+ ds.child("").getValue();
                    String Tphone = ""+ ds.child("phone").getValue();
                    //      LogIn(Email, pwd);
                    tvnameT.setText(TName);
                    tvemailT.setText(Email);
                    tvdobT.setText(TDob);
                    tvphoneT.setText(Tphone);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
    */

}
/*
 Query query = myrefT.orderByChild("email").equalTo(userT.getEmail());
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                //check untill required data get

                for (DataSnapshot ds: dataSnapshot.getChildren()){
                    //
                    String Name = ""+ ds.child("name").getValue();
                    String Phone = ""+ ds.child("phone").getValue();
                    String Email = ""+ ds.child("email").getValue();
                    String dob = ""+ ds.child("dob").getValue();
                    String gender = ""+ ds.child("gender").getValue();

                    tvnameT.setText(Name);
                    tvphoneT.setText(Phone);
                    tvemailT.setText(Email);
                    tvdobT.setText(dob);
                    tvgenderT.setText(gender);

                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

 */
/*
  userT = mfirebaseAuth.getCurrentUser();

                //get useremail and uid from auth

                String email = userT.getEmail();
                String uid = userT.getUid();
                //store details in realtime
                HashMap<Object, String> hashMap = new HashMap<>();
                hashMap.put("email", email);
                hashMap.put("uid", uid);
                hashMap.put("name", tvnameT.getText().toString());
                hashMap.put("phone", tvphoneT.getText().toString());
                hashMap.put("dob", tvdobT.getText().toString());
                hashMap.put("gender", tvgenderT.getText().toString());
          //      hashMap.put("class",spinner1.getSelectedItem().toString());
          //      hashMap.put("pattern", spinner2.getSelectedItem().toString());

                FirebaseDatabase database = FirebaseDatabase.getInstance();
                //path to store data named "Users"
                DatabaseReference reference = database.getReference("Teacher");
                reference.child(uid).setValue(hashMap);

                Toast.makeText(TeacherProfile.this, "Profile Update Successfully", Toast.LENGTH_SHORT).show();

 */