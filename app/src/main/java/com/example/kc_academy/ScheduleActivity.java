package com.example.kc_academy;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class ScheduleActivity extends AppCompatActivity {

    EditText etText;
    Button btSave;
    ListView listView;

    DatabaseHelper databaseHelper;
    ArrayList arrayList;
    ArrayAdapter arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);

        //Assign Variable
        etText = findViewById(R.id.stask);
        btSave = findViewById(R.id.btn_save);
        listView = findViewById(R.id.taskList);

        // Initialize databasehelper
        databaseHelper = new DatabaseHelper(ScheduleActivity.this);

        //add database value to arraylist
        arrayList = databaseHelper.getAllText();

        //initialize array adapter
        arrayAdapter = new ArrayAdapter(ScheduleActivity.this,android.R.layout.simple_list_item_1,arrayList);

        //set arrayadpter to listview
        listView.setAdapter(arrayAdapter);

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, final long id) {

                final int which_item =position;

                new AlertDialog.Builder(ScheduleActivity.this)
                        .setIcon(android.R.drawable.ic_delete)
                        .setTitle("Are you sure ?")
                        .setMessage("Do you want to delete this task")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @SuppressLint("ShowToast")
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                              //  databaseHelper.deleteName((String) arrayList.get(which_item));
                                arrayList.remove(which_item);
                                arrayAdapter.notifyDataSetChanged();
                                Toast.makeText(ScheduleActivity.this, "Task removed...", Toast.LENGTH_SHORT);
                            }
                        })
                        .setNegativeButton("No", null)
                        .show();
                return true;
            }
        });


        btSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Get text from EditText
                String text = etText.getText().toString();
                if (!text.isEmpty()) {
                    if (databaseHelper.addText(text)){
                        etText.setText(""); //Edit again
                        Toast.makeText(getApplicationContext(),"Task Added..."
                                ,Toast.LENGTH_SHORT).show();
                        //clear array list
                        arrayList.clear();
                        arrayList.addAll(databaseHelper.getAllText());
                        //Refresh listview data
                        arrayAdapter.notifyDataSetChanged();
                        listView.invalidateViews();
                        listView.refreshDrawableState();
                    }
                }
                            }
        });
    }
}
