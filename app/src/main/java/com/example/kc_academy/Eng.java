package com.example.kc_academy;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class Eng extends AppCompatActivity {

    TextView tvnotes, tvVideos, tvTests;
    ImageView ivback;
    Button btnNotes, btnVideo, btnTest;

    FirebaseAuth firebaseAuth;
    FirebaseUser user1;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference myref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eng);
        btnVideo = findViewById(R.id.btn_videos);
        btnNotes = findViewById(R.id.btn_notes);
        btnTest = findViewById(R.id.btn_tests);


        firebaseAuth = FirebaseAuth.getInstance();
        user1 = firebaseAuth.getCurrentUser();
        firebaseDatabase = FirebaseDatabase.getInstance();
        myref = firebaseDatabase.getReference("Users");


        btnNotes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String subject = getIntent().getStringExtra("xyz");
                Intent pdf = new Intent(Eng.this, NotesActivity.class);
                pdf.putExtra("xyz", subject);
                startActivity(pdf);
            }
        });

        btnVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent vdo = new Intent(Eng.this, Videos.class);
                String subject = getIntent().getStringExtra("xyz");
                vdo.putExtra("xyz", subject);
                startActivity(vdo);
            }
        });


        btnTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Query query = myref.orderByChild("email").equalTo(user1.getEmail());
                query.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for (DataSnapshot ds : dataSnapshot.getChildren()) {
                            //
                                String Status = "" + ds.child("payment").getValue();
                                if (!Status.isEmpty()) {
                                    Intent tst = new Intent(Eng.this, Tests.class);
                                    String subject = getIntent().getStringExtra("xyz");
                                    tst.putExtra("xyz", subject);
                                    startActivity(tst);
                                } else {
                                    alert();
                                }
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });

        ivback = findViewById(R.id.bk_dash);
        ivback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent go = new Intent(Eng.this, DashBoard.class);
                startActivity(go);
            }
        });

    }

    private void alert() {
        AlertDialog.Builder alertdialog = new AlertDialog.Builder(Eng.this);
        alertdialog.setTitle("Yo Don't Paid for Test Series");
        alertdialog.setMessage("Are you want pay now?");
        //       alertdialog.setIcon(R.drawable.ic_logout);
        alertdialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finish();
                FirebaseAuth.getInstance().signOut();
                Intent close = new Intent(Eng.this, PaymentTest.class);
                startActivity(close);
            }
        });
        alertdialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });
        alertdialog.show();
    }
}
