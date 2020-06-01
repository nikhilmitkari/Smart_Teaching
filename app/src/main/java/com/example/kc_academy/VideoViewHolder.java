package com.example.kc_academy;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class VideoViewHolder extends RecyclerView.ViewHolder {

    TextView mName;TextView vLink;
    Button mDownload;

    public VideoViewHolder(@NonNull View itemView) {
        super(itemView);

        mName=itemView.findViewById(R.id.name);
        mDownload=itemView.findViewById(R.id.down);
    }

}
