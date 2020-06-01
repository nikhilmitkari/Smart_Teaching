package com.example.kc_academy.Home;

import android.app.Application;

import java.util.Timer;
import java.util.TimerTask;

public class Myapp extends Application {
    public void startUserSession(){
       Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {

            }
        }, 5000);
    }


}
