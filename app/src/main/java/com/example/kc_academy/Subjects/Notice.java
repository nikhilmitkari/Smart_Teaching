package com.example.kc_academy.Subjects;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.kc_academy.Home.Note;
import com.example.kc_academy.R;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class Notice extends AppCompatActivity {
    EditText subject, message;
    Button teacher,student;

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference notebookRef = db.collection("Notification_Box");
    private CollectionReference noteRef = db.collection("Notification_Teacher");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice);
        subject =findViewById(R.id.subject);
        message= findViewById(R.id.body);
        teacher = findViewById(R.id.btnTeacher);
        student = findViewById(R.id.btnStudent);
    }

    public void ToStudent(View view) {
        String title = subject.getText().toString();
        String description = message.getText().toString();
        Note note = new Note(title, description);
        notebookRef.add(note);
    }

    public void ToTeacher(View view) {
        String title = subject.getText().toString();
        String description = message.getText().toString();
        Note note = new Note(title, description);
        noteRef.add(note);
    }
}
