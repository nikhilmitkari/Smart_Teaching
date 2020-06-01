package com.example.kc_academy;

import android.app.DownloadManager;
import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import static android.os.Environment.DIRECTORY_DOWNLOADS;

public class TestAdapter extends RecyclerView.Adapter<TestViewHolder>   {
    Tests tests;
    ArrayList<TestDownModel> testDownModels;

    public TestAdapter(Tests tests, ArrayList<TestDownModel> testDownModels) {
        this.tests = tests ;
        this.testDownModels = testDownModels;
    }


    @NonNull
    @Override
    public TestViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        LayoutInflater layoutInflater = LayoutInflater.from(tests.getBaseContext());
        View view = layoutInflater.inflate(R.layout.testelements, null, false);

        return new TestViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final TestViewHolder testViewHolder, final int i) {

        testViewHolder.tName.setText(testDownModels.get(i).getName());
        testViewHolder.tDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                downloadFile(testViewHolder.tName.getContext(),testDownModels.get(i).getName(),".pdf",DIRECTORY_DOWNLOADS,testDownModels.get(i).getLink());
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
        return testDownModels.size();
    }
}
