package com.example.kc_academy;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class DashBoard extends AppCompatActivity {
   ImageView ivChat, ivstudy, ivrateus, ivProfile, ivSchedule;
   CardView cvNotice, cvHelp, cvSetting;
    final Context context = this;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board);
        ivProfile = findViewById(R.id.profile);
        ivstudy = findViewById(R.id.studymatrial);
        ivrateus = findViewById(R.id.rateus);
        ivSchedule = findViewById(R.id.Schedule);
        cvNotice = findViewById(R.id.btn_notice);
        cvHelp = findViewById(R.id.btn_help);
        cvSetting = findViewById(R.id.btn_setting);

        ivProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent itpr = new Intent(DashBoard.this, MyprofileActivity.class);
                startActivity(itpr);
            }
        });

        ivstudy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent itsb = new Intent(DashBoard.this, SelectSubject.class);
                startActivity(itsb);
            }
        });

        ivrateus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent itrt = new Intent(DashBoard.this, FeedbackActivity.class);
                startActivity(itrt);
            }
        });

        ivSchedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent itcal = new Intent(DashBoard.this, ScheduleActivity.class);
                startActivity(itcal);
            }
        });

        cvNotice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent itn = new Intent(DashBoard.this, Notification.class);
                startActivity(itn);
            }
        });

        cvHelp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ithp = new Intent(DashBoard.this, Help.class);
                startActivity(ithp);
            }
        });

        cvSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent itstg = new Intent(DashBoard.this, Settings.class);
                startActivity(itstg);
            }
        });


    /* Initialized and assign variable */
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        //Set home selected
        bottomNavigationView.setSelectedItemId(R.id.dashboard);

        //Perform ItemSelectedListner
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.dashboard:
                        return true;
                    case R.id.home:
                        startActivity(new Intent(getApplicationContext()
                                ,HomeActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.about:
                        startActivity(new Intent(getApplicationContext()
                                ,About.class));
                        overridePendingTransition(0,0);
                        return true;

                }
                return false;
            }
        });



    }
}
