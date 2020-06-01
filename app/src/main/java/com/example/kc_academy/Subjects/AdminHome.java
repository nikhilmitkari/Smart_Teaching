package com.example.kc_academy.Subjects;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.kc_academy.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AdminHome extends AppCompatActivity {
    Button  addTeacher, teacherList, studentList, notify, myRevenue;
    int count, count2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home);
        addTeacher = findViewById(R.id.RegTeacher);
        teacherList = findViewById(R.id.ViewTeacher);
        studentList = findViewById(R.id.ViewStudent);
        notify = findViewById(R.id.Notify);
        myRevenue = findViewById(R.id.CheckRevenue);

        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("Users");
        DatabaseReference reference2 = FirebaseDatabase.getInstance().getReference().child("Teacher");



        teacherList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AdminHome.this, TeacherList.class));
            }
        });

        addTeacher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AdminHome.this, AddTeacher.class));
            }
        });

        studentList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AdminHome.this, StudentList.class));
            }
        });

        notify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AdminHome.this, Notice.class));
            }
        });

        reference.addValueEventListener(new ValueEventListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()){
                    count = (int) dataSnapshot.getChildrenCount();
                    studentList.setText(count + " Students");
                }
                else{
                    studentList.setText("0 Students");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        reference2.addValueEventListener(new ValueEventListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()){
                    count2 = (int) dataSnapshot.getChildrenCount();
                    teacherList.setText(count2 + " Teachers");
                }
                else{
                    teacherList.setText("0 Students");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }
}
