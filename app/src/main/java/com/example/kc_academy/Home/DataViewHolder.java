package com.example.kc_academy.Home;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kc_academy.R;

class DataViewHolder extends RecyclerView.ViewHolder {
    TextView dName;
    Button dDownload;

    public DataViewHolder(@NonNull View itemView) {
        super(itemView);
        dName=itemView.findViewById(R.id.name);
        dDownload=itemView.findViewById(R.id.down);

    }
}
