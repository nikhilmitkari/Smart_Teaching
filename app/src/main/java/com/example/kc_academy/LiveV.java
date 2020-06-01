package com.example.kc_academy;

import android.net.Uri;
import android.os.Bundle;
import android.view.Display;
import android.view.Surface;
import android.view.WindowManager;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Objects;

public class LiveV extends AppCompatActivity {

    VideoView videov;
    MediaController mc;
    TextView tvname;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_live_v);
        videov = findViewById(R.id.player);
        tvname = findViewById(R.id.videoname);
        mc = new MediaController(this);

        // pd = new ProgressDialog(LiveV.this);
        //pd.setMessage("Buffering video please wait");
        // pd.show();

        String videoname = getIntent().getStringExtra("video");
        tvname.setText(videoname);

        String videopath = getIntent().getStringExtra("vurl");
        Uri uri = Uri.parse(videopath);
        videov.setVideoURI(uri);
        videov.setMediaController(mc);
        mc.setAnchorView(videov);
        videov.start();
        mc.setAnchorView(videov);

    }


    private boolean isLandScape() {
        Display display = ((WindowManager) Objects.requireNonNull(getSystemService(WINDOW_SERVICE)))
                .getDefaultDisplay();
        int rotation = display.getRotation();

        return rotation == Surface.ROTATION_90
                || rotation == Surface.ROTATION_270;
    }
    }


 /*  videov.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                pd.dismiss();
            }
        });

    }

   /* public void playvideo(View view){
        String videopath =getIntent().getStringExtra("vurl");
        Uri uri = Uri.parse(videopath);
        videov.setVideoURI(uri);
        videov.setMediaController(mc);
        mc.setAnchorView(videov);
        videov.start();
        {videov.addPlaybackSpeedButton();
        videov.addSeekBackwardButton();
        videov.addSeekForwardButton();
        videov.enableAutoStart();
        videov.fastForwardSeconds(5);
        videov.rewindSeconds(5);}
       */