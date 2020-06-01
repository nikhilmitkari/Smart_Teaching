package com.example.kc_academy;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.ViewPager;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Timer;
import java.util.TimerTask;


public class HomeActivity extends AppCompatActivity {

    private DatabaseReference databaseReference;
    TextView direct, etprofile;
    ImageView ivshare;
    FirebaseUser user1;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference myref;
    FirebaseFirestore db;
    FirebaseAuth firebaseAuth;
    TextView data;
    RelativeLayout profile;
    Timer timer;

    private int[] mImageIds = new int[]{R.drawable.academy4,R.drawable.academy5,
            R.drawable.academy6,R.drawable.academy7,R.drawable.academy3,R.drawable.academy2};
    private int dotscount;
    private ImageView[] dots;
    LinearLayout sliderDotspanel;


    @SuppressLint({"SetTextI18n", "WrongViewCast"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        data= findViewById(R.id.text_view_data);
        profile = findViewById(R.id.rlProfile);

        viewPager();



        MobileAds.initialize(this, "ca-app-pub-3940256099942544~3347511713");

        AdView mAdview1 = findViewById(R.id.adView1);
        AdView mAdview2 = findViewById(R.id.adView2);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdview1.loadAd(adRequest);
        mAdview2.loadAd(adRequest);

        firebaseAuth = FirebaseAuth.getInstance();
        user1 = firebaseAuth.getCurrentUser();
        firebaseDatabase = FirebaseDatabase.getInstance();
        myref = firebaseDatabase.getReference("Users");

        Query query = myref.orderByChild("email").equalTo(user1.getEmail());
        query.addValueEventListener(new ValueEventListener(){
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot ds: dataSnapshot.getChildren()){
                    //
                    String Name = ""+ ds.child("name").getValue();
                    String Phone = ""+ ds.child("phone").getValue();
                    String dob = ""+ ds.child("dob").getValue();
                    String gender = ""+ ds.child("gender").getValue();

                    if(!(Name.isEmpty())&&(!(Phone.isEmpty()))&&(!(dob.isEmpty()))&&(!(gender.isEmpty()))){
                        profile.setVisibility(View.GONE);
                    }
                    else
                        profile.setVisibility(View.VISIBLE);
            }
        }
        @Override
        public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });




                    ivshare = findViewById(R.id.share);
                    ivshare.setOnClickListener(new View.OnClickListener() {
                       @Override
                        public void onClick(View v) {
                        Intent myIntent = new Intent(Intent.ACTION_SEND);
                        myIntent.setType("text/plain");
                          String shareBody = "Your body here";
                        String sharesub = "Your Subject here!";
                    myIntent.putExtra(Intent.EXTRA_SUBJECT, sharesub);
                    myIntent.putExtra(Intent.EXTRA_TEXT, shareBody);
                    startActivity(Intent.createChooser(myIntent, "Share using"));

            }
        });

        direct = findViewById(R.id.etdirect);
        direct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, SelectSubject.class));
            }
        });

        etprofile = findViewById(R.id.etprofile);
        etprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, MyprofileActivity.class));
            }
        });




//........................................................................................................................................................................................................................................................................................
        //Initialized and assign variable
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        //Set home selected
        bottomNavigationView.setSelectedItemId(R.id.home);

        //Perform ItemSelectedListner
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.dashboard:
                        startActivity(new Intent(getApplicationContext(), DashBoard.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.home:
                        return true;
                    case R.id.about:
                        startActivity(new Intent(getApplicationContext(), About.class));
                        overridePendingTransition(0, 0);
                        return true;

                }
                return false;
            }
        });

    }

    private void viewPager() {
        final ViewPager viewPager = findViewById(R.id.viewpager);
        sliderDotspanel = findViewById(R.id.SliderDots);
        ImageAdapter adapter = new ImageAdapter(this);
        viewPager.setAdapter(adapter);
        dotscount = adapter.getCount();
        dots = new ImageView[dotscount];


        for (int i = 0; i < dotscount; i++) {

            dots[i] = new ImageView(this);
            dots[i].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.nonactive_dot));

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);

            params.setMargins(8, 0, 8, 0);

            sliderDotspanel.addView(dots[i], params);


            dots[0].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.active_dot));

            viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                }

                @Override
                public void onPageSelected(int position) {
                    for (int i = 0; i < dotscount; i++) {
                        dots[i].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.nonactive_dot));
                    }
                    dots[position].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.active_dot));
                }

                @Override
                public void onPageScrollStateChanged(int state) {
                }
            });
        }

        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                viewPager.post(new Runnable() {
                    @Override
                    public void run() {
                        viewPager.setCurrentItem((viewPager.getCurrentItem() + 1) % mImageIds.length);
                    }
                });
            }
        };
            timer = new Timer();
            timer.schedule(timerTask, 3000, 3000);
    }

    @Override
    protected void onDestroy() {
        timer.cancel();
        super.onDestroy();
    }

    //..............................................................................................................................................................................................................................................................................................

}







