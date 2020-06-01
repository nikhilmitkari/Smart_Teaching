package com.example.kc_academy.Home;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.kc_academy.R;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class TeacherNotification extends AppCompatActivity {
    EditText ed1,ed3;
    NotificationHelper notificationHelper;

    private static final String TAG = "Teacher";

    private static final String KEY_TITLE = "title";
    private static final String KEY_DESCRIPTION = "description";




    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference notebookRef = db.collection("Notification_Box");
    private DocumentReference noteRef = db.document("Users/My First Note");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_notification);
        ed1= findViewById(R.id.editText);
        ed3= findViewById(R.id.editText3);

        notificationHelper = new NotificationHelper(this);

    }

    public void sendNotification(View view) {



  /*      String tittle=ed1.getText().toString().trim();
        String body=ed3.getText().toString().trim();
        String Who = "\t~by Teacher";
        if (tittle.isEmpty()){
            Toast.makeText(this, "Please Enter Title", Toast.LENGTH_SHORT).show(); }

        else if (body.isEmpty()){
            Toast.makeText(this, "Please Enter Body", Toast.LENGTH_SHORT).show();
        }
        else{
            notificationHelper.sendHighPriorityNotification(tittle,body, ThomeActivity.class);

   */

            String title = ed1.getText().toString();
            String description = ed3.getText().toString();
            Note note = new Note(title, description);
            notebookRef.add(note);

  //      }
      //  notificationHelper.sendHighPriorityNotification("this is title", "this is some awesome notificaiton. wow i learnt it the easy way.", ThomeActivity.class);

    }

    @Override
    protected void onStart() {
        super.onStart();
        notebookRef.addSnapshotListener(this, new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(QuerySnapshot queryDocumentSnapshots, FirebaseFirestoreException e) {
                if (e != null) {
                    return;
                }

                String data = "";

                for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
                    Note note = documentSnapshot.toObject(Note.class);
                    note.setDocumentId(documentSnapshot.getId());

                    String documentId = note.getDocumentId();
                    String title = note.getTitle();
                //    String subject = note.getSubject();
                    String description = note.getDescription();
                    data += "ID: " + documentId
                            + "\nTitle: " + title + "\nDescription: " + description + "\n\n";
                }

            }
        });
    }
   /* public void addNote(View view) {

        String title = ed1.getText().toString();
        String description = ed3.getText().toString();

        Note note = new Note(title, description);

        notebookRef.add(note);

    }

    public void loadNotes(View view) {
        notebookRef.get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        String data = "";

                        for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
                            Note note = documentSnapshot.toObject(Note.class);
                            note.setDocumentId(documentSnapshot.getId());

                            String documentId = note.getDocumentId();
                            String title = note.getTitle();
                            String description = note.getDescription();

                            data += "ID: " + documentId
                                    + "\nTitle: " + title + "\nDescription: " + description + "\n\n";
                        }

                   //     textViewData.setText(data);
                    }
                });
    }                    */
}










/*
  String tittle=ed1.getText().toString().trim();
                String subject=ed2.getText().toString().trim();
                String body=ed3.getText().toString().trim();

                NotificationManager notif=(NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
                Notification notify=new Notification.Builder
                        (getApplicationContext()).setContentTitle(tittle).setContentText(body).
                        setContentTitle(subject).setSmallIcon(R.drawable.kc).build();

                notify.flags |= Notification.FLAG_AUTO_CANCEL;
                notif.notify(0, notify);
 */
/*
protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_notification);

        ed1=(EditText)findViewById(R.id.editText);
        ed2=(EditText)findViewById(R.id.editText2);
        ed3=(EditText)findViewById(R.id.editText3);
        Button b1=(Button)findViewById(R.id.button);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tittle=ed1.getText().toString().trim();
                String subject=ed2.getText().toString().trim();
                String body=ed3.getText().toString().trim();

                NotificationCompat.Builder builder = new NotificationCompat.Builder(
                        TeacherNotification.this)
                        .setContentTitle(tittle)
                        .setContentText(body)
                        .setContentTitle(subject)
                        .setSmallIcon(R.drawable.kc);

                Intent intent = new Intent(TeacherNotification.this, ThomeActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtra("message",body);

                PendingIntent pendingIntent= PendingIntent.getActivity(TeacherNotification.this,
                        0,intent,PendingIntent.FLAG_UPDATE_CURRENT);
                builder.setContentIntent(pendingIntent);

                NotificationManager notificationManager = (NotificationManager)getSystemService(
                        Context.NOTIFICATION_SERVICE
                );
                notificationManager.notify(0,builder.build());
            }
        });
    }
 */


/*

 */