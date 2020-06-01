package com.example.kc_academy.Home;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

import java.util.Timer;
import java.util.TimerTask;

import javax.annotation.Nullable;


public class BaseActivity extends AppCompatActivity  implements LogoutListner{

    private LogoutListner listner;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        registerSessionListner(this);
        startUserSession();
    }

    private void registerSessionListner(LogoutListner listner) {
        this.listner=listner;
    }

    private void startUserSession() {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {

            }
        }, 5000);
    }

    @Override
    public void onSessionLogout() {
        finish();
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(this,TeacherLogin.class));
    }
}