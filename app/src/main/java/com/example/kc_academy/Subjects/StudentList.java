package com.example.kc_academy.Subjects;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.kc_academy.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Iterator;

public class StudentList extends AppCompatActivity {
    private ListView dataListView;
    private EditText itemText;
    private Button findButton;
    private Button deleteButton;
    private Boolean searchMode = false;
    private Boolean itemSelected = false;
    private int selectedPosition = 0;

    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference dbRef = database.getReference("Users");

    FirebaseAuth mAuth;


    ArrayList<String> listItems = new ArrayList<String>();
    ArrayList<String> listKeys = new ArrayList<String>();
    ArrayAdapter<String> adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_list);

        dataListView = findViewById(R.id.dataList);
        itemText = findViewById(R.id.StudentInput);
        findButton = findViewById(R.id.find01);
        deleteButton = findViewById(R.id.delete01);
        deleteButton.setEnabled(false);


        adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_single_choice,
                listItems);
        dataListView.setAdapter(adapter);
        dataListView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

        dataListView.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    public void onItemClick(AdapterView<?> parent,
                                            View view, int position, long id) {
                        selectedPosition = position;
                        itemSelected = true;
                        deleteButton.setEnabled(true);
                    }
                });

        addChildEventListener();

    }
    private void addChildEventListener() {
        ChildEventListener childListener = new ChildEventListener() {

            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                adapter.add(
                        (String) dataSnapshot.child("name").getValue());

                listKeys.add(dataSnapshot.getKey());
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                String key = dataSnapshot.getKey();
                int index = listKeys.indexOf(key);

                if (index != -1) {
                    listItems.remove(index);
                    listKeys.remove(index);
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        };
        dbRef.addChildEventListener(childListener);
    }

    public void addItem(View view) {

        String item = itemText.getText().toString();
        String key = dbRef.push().getKey();

        itemText.setText("");
        dbRef.child(key).child("name").setValue(item);

        adapter.notifyDataSetChanged();
    }

    public void deleteStudent(View view) {
        dataListView.setItemChecked(selectedPosition, false);
        dbRef.child(listKeys.get(selectedPosition)).removeValue();
    }
    @SuppressLint("SetTextI18n")
    public void findStudent(View view) {

        Query query;

        if (!searchMode) {
            findButton.setText("Clear");
            query = dbRef.orderByChild("name").
                    equalTo(itemText.getText().toString());
            searchMode = true;
        } else {
            searchMode = false;
            findButton.setText("Find");
            query = dbRef.orderByKey();
        }

        if (itemSelected) {
            dataListView.setItemChecked(selectedPosition, false);
            itemSelected = false;
            deleteButton.setEnabled(false);
        }

        query.addListenerForSingleValueEvent(queryValueListener);
    }
    ValueEventListener queryValueListener = new ValueEventListener() {

        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {

            Iterable<DataSnapshot> snapshotIterator = dataSnapshot.getChildren();
            Iterator<DataSnapshot> iterator = snapshotIterator.iterator();

            adapter.clear();
            listKeys.clear();

            while (iterator.hasNext()) {
                DataSnapshot next = iterator.next();

                String match = (String) next.child("name").getValue();
                String key = next.getKey();
                listKeys.add(key);
                adapter.add(match);
            }

        }

        @Override
        public void onCancelled(@NonNull DatabaseError databaseError) {

        }
    };

}
