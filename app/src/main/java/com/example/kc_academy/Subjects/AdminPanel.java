package com.example.kc_academy.Subjects;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.kc_academy.Home.TeacherLogin;
import com.example.kc_academy.R;

public class AdminPanel extends AppCompatActivity {
    TextView goTeacher;
    EditText key;
    Button Enter;
    String password = "ThereCanOnlyBeOneAdmin";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_panel);
        goTeacher = findViewById(R.id.go_teacher);
        key = findViewById(R.id.key);
        Enter = findViewById(R.id.enter);
        Enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Check = key.getText().toString();
                if (Check.equals("Admin")){
                    startActivity(new Intent(AdminPanel.this, AdminHome.class));
                    finish();
                }
                else{
                    Toast.makeText(AdminPanel.this, "Please Enter Correct Security Key", Toast.LENGTH_SHORT).show();
                }
            }
        });


        goTeacher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AdminPanel.this, TeacherLogin.class));
                finish();
            }
        });
    }



    @Override
    public void onBackPressed() {
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
}
