package com.example.kc_academy;

import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class VideoAdapter extends RecyclerView.Adapter<VideoViewHolder> {

    Videos videos;
    ArrayList<VideoDownModel> videoDownModels;

    public VideoAdapter(Videos videos, ArrayList<VideoDownModel> videoDownModels) {
        this.videos = videos ;
        this.videoDownModels = videoDownModels;
    }

    @NonNull
    @Override
    public VideoViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        LayoutInflater layoutInflater = LayoutInflater.from(videos.getBaseContext());
        View view = layoutInflater.inflate(R.layout.elements, null, false);

        return new VideoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final VideoViewHolder videoViewHolder, final int i) {

        videoViewHolder.mName.setText(videoDownModels.get(i).getName());
        videoViewHolder.mDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              //  downloadFile(videoViewHolder.mName.getContext(),videoDownModels.get(i).getName(),".mp4",DIRECTORY_DOWNLOADS,videoDownModels.get(i).getVLink());
                Intent go = new Intent(v.getContext(), LiveV.class);
                go.putExtra("vurl",videoDownModels.get(i).getVLink());
                go.putExtra("video",videoDownModels.get(i).getName());
                v.getContext().startActivity(go);
            }
        });

    }

    public void downloadFile(Context context, String fileName, String fileExtension, String destinationDirectory, String vurl) {

        DownloadManager downloadmanager = (DownloadManager) context.
                getSystemService(Context.DOWNLOAD_SERVICE);
        Uri uri = Uri.parse(vurl);
        DownloadManager.Request request = new DownloadManager.Request(uri);

        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        request.setDestinationInExternalFilesDir(context, destinationDirectory, fileName + fileExtension);

        downloadmanager.enqueue(request);
    }

    @Override
    public int getItemCount() {
        return videoDownModels.size();
    }


}
