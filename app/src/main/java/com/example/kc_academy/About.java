package com.example.kc_academy;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class About extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        //Initialized and assign variable
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        //Set home selected
        bottomNavigationView.setSelectedItemId(R.id.about);

        //Perform ItemSelectedListner
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.dashboard:
                        startActivity(new Intent(getApplicationContext()
                                ,DashBoard.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.home:
                        startActivity(new Intent(getApplicationContext()
                                ,HomeActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.about:

                        return true;

                }
                return false;
            }
        });
    }
}



/*<LinearLayout
           android:layout_width="match_parent"
           android:layout_height="match_parent"
           android:layout_marginTop="0dp"
           android:orientation="vertical"
           android:padding="12dp">



           <TextView
               android:layout_width="wrap_content"
               android:layout_height="wrap_content"
               android:fontFamily="cursive"
               android:text="About Us"
               android:textColor="@android:color/black"
               android:textSize="30sp"
               android:textStyle="bold|italic"
               android:layout_gravity="center"/>
           <TextView
               android:layout_width="wrap_content"
               android:layout_height="wrap_content"
               android:layout_marginTop="20dp"
               android:text="@string/finally_after_so_many_years_the_wait_for_students_are_over_n_we_are_coming_with_the_best_coaching_institute_for_students_of_jawaharnagar_and_nearby_places"/>

           <TextView
               android:layout_width="wrap_content"
               android:layout_height="wrap_content"
               android:text="@string/with_a_desire_to_change_rural_scenario_to_improve_quality_of_education_to_provide_moral_values_to_provide_a_guidance_helping_students_to_achieve_their_dreams_k_c_academy_is_providing_this_facilities_at_different_branches_kindly_share_to_all_and_spread_the_good_news_thank_you"
               android:layout_marginTop="12dp"
               android:layout_marginBottom="50dp"/>

       </LinearLayout>

 */
