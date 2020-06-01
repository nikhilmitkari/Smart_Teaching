package com.example.kc_academy.Home;

import android.app.DownloadManager;
import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kc_academy.R;

import java.util.ArrayList;

import static android.os.Environment.DIRECTORY_DOWNLOADS;

class DataAdapter extends RecyclerView.Adapter<DataViewHolder>{
    TestData testData;

    ArrayList<DataModel> dataModels;
    public DataAdapter(TestData testData, ArrayList<DataModel> dataModels) {
        this.testData = testData ;
        this.dataModels = dataModels;
    }

    @NonNull
    @Override
    public DataViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        LayoutInflater layoutInflater = LayoutInflater.from(testData.getBaseContext());
        View view = layoutInflater.inflate(R.layout.elements, null, false);

        return new DataViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final DataViewHolder dataViewHolder, final int i) {

        dataViewHolder.dName.setText(dataModels.get(i).getTitle());
        dataViewHolder.dDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                downloadFile(dataViewHolder.dName.getContext(),dataModels.get(i).getTitle(),".pdf",DIRECTORY_DOWNLOADS,dataModels.get(i).getUrl());
            }
        });


    }

    public void downloadFile(Context context, String fileName, String fileExtension, String destinationDirectory, String url) {

        DownloadManager downloadmanager = (DownloadManager) context.
                getSystemService(Context.DOWNLOAD_SERVICE);
        Uri uri = Uri.parse(url);
        DownloadManager.Request request = new DownloadManager.Request(uri);

        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        request.setDestinationInExternalFilesDir(context, destinationDirectory, fileName + fileExtension);

        downloadmanager.enqueue(request);
    }


    @Override
    public int getItemCount() {
        return dataModels.size();
    }
}

