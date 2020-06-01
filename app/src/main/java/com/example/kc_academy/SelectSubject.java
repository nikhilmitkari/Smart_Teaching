package com.example.kc_academy;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class SelectSubject extends AppCompatActivity {
    CardView cvEnglish, cvHistoy, cvGeography, cvMaths, cvScience, cvPhysics, cvChem, cvBio;
    ImageView ivreturn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_subject);

        cvEnglish = findViewById(R.id.cv_eng);
        cvEnglish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent ite = new Intent(SelectSubject.this, Eng.class);
                String subject = "English";
                ite.putExtra("xyz",subject);
                startActivity(ite);
            }
        });

        cvHistoy = findViewById(R.id.cv_hsty);
        cvHistoy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String subject = "History";
                Intent ith = new Intent(SelectSubject.this, Eng.class);
                ith.putExtra("xyz",subject);
                startActivity(ith);
            }
        });

        cvGeography = findViewById(R.id.cv_gphy);
        cvGeography.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent geo = new Intent(SelectSubject.this, Eng.class);
                String subject = "Geography";
                geo.putExtra("xyz",subject);
                startActivity(geo);
            }
        });

        cvMaths = findViewById(R.id.cv_maths);
        cvMaths.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent maths = new Intent(SelectSubject.this, Eng.class);
                String subject = "Mathematics";
                maths.putExtra("xyz",subject);
                startActivity(maths);
            }
        });

        cvScience = findViewById(R.id.cv_science);
        cvScience.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent scs = new Intent(SelectSubject.this, Eng.class);
                String subject = "Science";
                scs.putExtra("xyz",subject);
                startActivity(scs);
            }
        });

        cvPhysics = findViewById(R.id.cv_physics);
        cvPhysics.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent phy = new Intent(SelectSubject.this, Eng.class);
                String subject = "Physics";
                phy.putExtra("xyz",subject);
                startActivity(phy);
            }
        });

        cvChem = findViewById(R.id.cv_chem);
        cvChem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent chem = new Intent(SelectSubject.this, Eng.class);
                String subject = "Chemistry";
                chem.putExtra("xyz",subject);
                startActivity(chem);
            }
        });

        cvBio = findViewById(R.id.cv_bio);
        cvBio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent bio = new Intent(SelectSubject.this, Eng.class);
                String subject = "Biology";
                bio.putExtra("xyz",subject);
                startActivity(bio);
            }
        });

        ivreturn = findViewById(R.id.rtn_dashboard);
        ivreturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent back = new Intent(SelectSubject.this, DashBoard.class);
                startActivity(back);
            }
        });
    }
}
