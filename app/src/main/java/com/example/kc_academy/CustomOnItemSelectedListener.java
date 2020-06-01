package com.example.kc_academy;

import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Toast;

import androidx.annotation.NonNull;


public class CustomOnItemSelectedListener implements OnItemSelectedListener {


    public void onItemSelected(@NonNull AdapterView<?> parent, View view, int pos, long id) {


        Toast.makeText(parent.getContext(),

                "OnItemSelectedListener : " + parent.getItemAtPosition(pos).toString(),

                Toast.LENGTH_SHORT).show();
    }



    @Override

    public void onNothingSelected(AdapterView<?> arg0) {

// TODO Auto-generated method stub 

    }



} 