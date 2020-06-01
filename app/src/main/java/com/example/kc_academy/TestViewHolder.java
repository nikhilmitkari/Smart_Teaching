package com.example.kc_academy;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class TestViewHolder extends RecyclerView.ViewHolder {
    TextView tName;
    Button tDownload;

    public TestViewHolder(@NonNull View itemView) {
      super (itemView);

        tName=itemView.findViewById(R.id.nameT);
        tDownload=itemView.findViewById(R.id.downT);
    }
}
