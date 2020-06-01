package com.example.kc_academy;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.ArrayList;
import java.util.HashMap;

public class Tests extends AppCompatActivity {
    private static final int PICK_IMG = 1;
    private ArrayList<Uri> ImageList = new ArrayList<Uri>();
    private int uploads = 0;

    FirebaseFirestore db;
    RecyclerView mRecyclerView;
    ArrayList<TestDownModel> testDownModelArrayList = new ArrayList<>();
    Button upload, choose;
    TestAdapter testAdapter;
    FirebaseAuth firebaseAuth;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference myref;
    FirebaseUser user1;
    TextView sample, answer;
   private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tests);

        upload = findViewById(R.id.clkUpload);
        choose = findViewById(R.id.clkChoose);
        answer = findViewById(R.id.tvAnswer);
        answer.setVisibility(View.GONE);
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Uploading ..........");

        sample = findViewById(R.id.tvT);
        String subject = getIntent().getStringExtra("xyz");
        sample.setText(subject);

        setUpRV();
        setUpFB();
        dataFromFirebase();
    }

    private void dataFromFirebase() {
        if(testDownModelArrayList.size()>0)
            testDownModelArrayList.clear();

        firebaseAuth = FirebaseAuth.getInstance();
        user1 = firebaseAuth.getCurrentUser();
        firebaseDatabase = FirebaseDatabase.getInstance();
        myref = firebaseDatabase.getReference("Users");

        db=FirebaseFirestore.getInstance();
        String a = "Class1 Class2 Class3 Class4 Class5 Class6 Class7 Class8 Class9 Class10 Class11 Class12";
        final String b = "CBSE";

        Query query = myref.orderByChild("email").equalTo(user1.getEmail());
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot ds : dataSnapshot.getChildren()){

                    String sClass = ""+ds.child("class").getValue();
                    String sPattern = ""+ ds.child("pattern").getValue();
                    String subject = sample.getText().toString();

                    switch (sClass){
                        case "Class1":
                            if (b.contains(sPattern)){
                                switch (subject) {
                                    case "English": {
                                        CollectionReference ref = db.collection("Class1CBSE").document("CK6aEA860T6TIHucpEXw")
                                                .collection("Eng").document("PePdIxgaBgIy5p677HY1").collection("test");
                                        putData(ref);
                                    }
                                    break;

                                    case "History": {
                                        CollectionReference ref = db.collection("Class1CBSE").document("CK6aEA860T6TIHucpEXw")
                                                .collection("History").document("L7k3y6ZZmI2iO8QFSvs9").collection("test");
                                        putData(ref);
                                    }
                                    break;

                                    case "Geography": {
                                        CollectionReference ref = db.collection("Class1CBSE").document("CK6aEA860T6TIHucpEXw")
                                                .collection("Geography").document("XtQkhD23tifMQ8qdchWr").collection("test");
                                        putData(ref);
                                    }
                                    break;

                                    case "Mathematics": {
                                        CollectionReference ref = db.collection("Class1CBSE").document("CK6aEA860T6TIHucpEXw")
                                                .collection("Mathematics").document("NYCjx8n8qIqtJtTDNJGt").collection("test");
                                        putData(ref);
                                    }
                                    break;

                                    case "Science": {
                                        CollectionReference ref = db.collection("Class1CBSE").document("CK6aEA860T6TIHucpEXw")
                                                .collection("Science").document("freMDiIPbqstwDgE7Zyd").collection("test");
                                        putData(ref);
                                    }
                                    break;

                                    case "Physics": {
                                        CollectionReference ref = db.collection("Class1CBSE").document("CK6aEA860T6TIHucpEXw")
                                                .collection("Physics").document("307IuL9VYw7Esmi8Fl7m").collection("test");
                                        putData(ref);
                                    }
                                    break;

                                    case "Biology": {
                                        CollectionReference ref = db.collection("Class1CBSE").document("CK6aEA860T6TIHucpEXw")
                                                .collection("Biology").document("1Dm0FouhEU5EW2bkzyuD").collection("test");
                                        putData(ref);
                                    }
                                    break;

                                    case "Chemistry": {
                                        CollectionReference ref = db.collection("Class1CBSE").document("CK6aEA860T6TIHucpEXw")
                                                .collection("Chemistry").document("zKV4PwDP2eAlMSBkXJJj").collection("test");
                                        putData(ref);
                                    }
                                    break;
                                }
                            }
                            else {
                                switch (subject) {
                                    case "English": {
                                        CollectionReference ref = db.collection("Class1MH").document("AA")
                                                .collection("Eng").document("again").collection("test");
                                        putData(ref);
                                    }
                                    break;

                                    case "History": {
                                        CollectionReference ref = db.collection("Class1MH").document("AA")
                                                .collection("History").document("again").collection("test");
                                        putData(ref);
                                    }
                                    break;

                                    case "Geography": {
                                        CollectionReference ref = db.collection("Class1MH").document("AA")
                                                .collection("Geography").document("again").collection("test");
                                        putData(ref);
                                    }

                                    case "Mathematics": {
                                        CollectionReference ref = db.collection("Class1MH").document("AA")
                                                .collection("Mathematics").document("again").collection("test");
                                        putData(ref);
                                    }
                                    break;

                                    case "Science": {
                                        CollectionReference ref = db.collection("Class1MH").document("AA")
                                                .collection("Science").document("again").collection("test");
                                        putData(ref);
                                    }
                                    break;

                                    case "Physics": {
                                        CollectionReference ref = db.collection("Class1MH").document("AA")
                                                .collection("Physics").document("again").collection("test");
                                        putData(ref);
                                    }
                                    break;

                                    case "Biology": {
                                        CollectionReference ref = db.collection("Class1MH").document("AA")
                                                .collection("Biology").document("again").collection("test");
                                        putData(ref);
                                    }
                                    break;

                                    case "Chemistry": {
                                        CollectionReference ref = db.collection("Class1MH").document("AA")
                                                .collection("Chemistry").document("again").collection("test");
                                        putData(ref);
                                    }
                                    break;
                                }
                            }
                            break;

                        case "Class2":
                            if (b.contains(sPattern)){
                                switch (subject) {
                                    case "English": {
                                        CollectionReference ref = db.collection("Class2CBSE").document("gPOVSQW8gCKyO1RVCRQo")
                                                .collection("Eng").document("again").collection("test");
                                        putData(ref);
                                    }
                                    break;

                                    case "History": {
                                        CollectionReference ref = db.collection("Class2CBSE").document("gPOVSQW8gCKyO1RVCRQo")
                                                .collection("History").document("again").collection("test");
                                        putData(ref);
                                    }
                                    case "Geography": {
                                        CollectionReference ref = db.collection("Class2CBSE").document("gPOVSQW8gCKyO1RVCRQo")
                                                .collection("Geography").document("again").collection("test");
                                        putData(ref);
                                    }
                                    break;

                                    case "Mathematics": {
                                        CollectionReference ref = db.collection("Class2CBSE").document("gPOVSQW8gCKyO1RVCRQo")
                                                .collection("Mathematics").document("again").collection("test");
                                        putData(ref);
                                    }
                                    break;

                                    case "Science": {
                                        CollectionReference ref = db.collection("Class2CBSE").document("gPOVSQW8gCKyO1RVCRQo")
                                                .collection("Science").document("again").collection("test");
                                        putData(ref);
                                    }
                                    break;

                                    case "Physics": {
                                        CollectionReference ref = db.collection("Class2CBSE").document("gPOVSQW8gCKyO1RVCRQo")
                                                .collection("Physics").document("again").collection("test");
                                        putData(ref);
                                    }
                                    break;

                                    case "Biology": {
                                        CollectionReference ref = db.collection("Class2CBSE").document("gPOVSQW8gCKyO1RVCRQo")
                                                .collection("Biology").document("again").collection("test");
                                        putData(ref);
                                    }
                                    break;

                                    case "Chemistry": {
                                        CollectionReference ref = db.collection("Class2CBSE").document("gPOVSQW8gCKyO1RVCRQo")
                                                .collection("Chemistry").document("again").collection("test");
                                        putData(ref);
                                    }
                                    break;

                                }
                            }
                            else {
                                switch (subject) {
                                    case "English": {
                                        CollectionReference ref = db.collection("Class2MH").document("AB")
                                                .collection("Eng").document("again").collection("test");
                                        putData(ref);
                                    }
                                    break;

                                    case "History": {
                                        CollectionReference ref = db.collection("Class2MH").document("AB")
                                                .collection("History").document("again").collection("test");
                                        putData(ref);
                                    }
                                    case "Geography": {
                                        CollectionReference ref = db.collection("Class2MH").document("AB")
                                                .collection("Geography").document("again").collection("test");
                                        putData(ref);
                                    }
                                    break;

                                    case "Mathematics": {
                                        CollectionReference ref = db.collection("Class2MH").document("AB")
                                                .collection("Mathematics").document("again").collection("test");
                                        putData(ref);
                                    }
                                    break;

                                    case "Science": {
                                        CollectionReference ref = db.collection("Class2MH").document("AB")
                                                .collection("Science").document("again").collection("test");
                                        putData(ref);
                                    }
                                    break;

                                    case "Physics": {
                                        CollectionReference ref = db.collection("Class2MH").document("AB")
                                                .collection("Physics").document("again").collection("test");
                                        putData(ref);
                                    }
                                    break;

                                    case "Biology": {
                                        CollectionReference ref = db.collection("Class2MH").document("AB")
                                                .collection("Biology").document("again").collection("test");
                                        putData(ref);
                                    }
                                    break;

                                    case "Chemistry": {
                                        CollectionReference ref = db.collection("Class2MH").document("AB")
                                                .collection("Chemistry").document("again").collection("test");
                                        putData(ref);
                                    }
                                    break;

                                }
                            }
                            break;

                        case "Class3":
                            if (b.contains(sPattern)){
                                switch (subject) {
                                    case "English": {
                                        CollectionReference ref = db.collection("Class3CBSE").document("GfdFx0V3BaxIVfcT8VnX")
                                                .collection("Eng").document("again").collection("test");
                                        putData(ref);
                                    }
                                    break;

                                    case "History": {
                                        CollectionReference ref = db.collection("Class3CBSE").document("GfdFx0V3BaxIVfcT8VnX")
                                                .collection("History").document("again").collection("test");
                                        putData(ref);
                                    }
                                    case "Geography": {
                                        CollectionReference ref = db.collection("Class3CBSE").document("GfdFx0V3BaxIVfcT8VnX")
                                                .collection("Geography").document("again").collection("test");
                                        putData(ref);
                                    }
                                    break;

                                    case "Mathematics": {
                                        CollectionReference ref = db.collection("Class3CBSE").document("GfdFx0V3BaxIVfcT8VnX")
                                                .collection("Mathematics").document("again").collection("test");
                                        putData(ref);
                                    }
                                    break;

                                    case "Science": {
                                        CollectionReference ref = db.collection("Class3CBSE").document("GfdFx0V3BaxIVfcT8VnX")
                                                .collection("Science").document("again").collection("test");
                                        putData(ref);
                                    }
                                    break;

                                    case "Physics": {
                                        CollectionReference ref = db.collection("Class3CBSE").document("GfdFx0V3BaxIVfcT8VnX")
                                                .collection("Physics").document("again").collection("test");
                                        putData(ref);
                                    }
                                    break;

                                    case "Biology": {
                                        CollectionReference ref = db.collection("Class3CBSE").document("GfdFx0V3BaxIVfcT8VnX")
                                                .collection("Biology").document("again").collection("test");
                                        putData(ref);
                                    }
                                    break;

                                    case "Chemistry": {
                                        CollectionReference ref = db.collection("Class3CBSE").document("GfdFx0V3BaxIVfcT8VnX")
                                                .collection("Chemistry").document("again").collection("test");
                                        putData(ref);
                                    }
                                    break;

                                }
                            }
                            else {
                                switch (subject) {
                                    case "English": {
                                        CollectionReference ref = db.collection("Class3MH").document("AC")
                                                .collection("Eng").document("again").collection("test");
                                        putData(ref);
                                    }
                                    break;

                                    case "History": {
                                        CollectionReference ref = db.collection("Class3MH").document("AC")
                                                .collection("History").document("again").collection("test");
                                        putData(ref);
                                    }
                                    case "Geography": {
                                        CollectionReference ref = db.collection("Class3MH").document("AC")
                                                .collection("Geography").document("again").collection("test");
                                        putData(ref);
                                    }
                                    break;

                                    case "Mathematics": {
                                        CollectionReference ref = db.collection("Class3MH").document("AC")
                                                .collection("Mathematics").document("again").collection("test");
                                        putData(ref);
                                    }
                                    break;

                                    case "Science": {
                                        CollectionReference ref = db.collection("Class3MH").document("AC")
                                                .collection("Science").document("again").collection("test");
                                        putData(ref);
                                    }
                                    break;

                                    case "Physics": {
                                        CollectionReference ref = db.collection("Class3MH").document("AC")
                                                .collection("Physics").document("again").collection("test");
                                        putData(ref);
                                    }
                                    break;

                                    case "Biology": {
                                        CollectionReference ref = db.collection("Class3MH").document("AC")
                                                .collection("Biology").document("again").collection("test");
                                        putData(ref);
                                    }
                                    break;

                                    case "Chemistry": {
                                        CollectionReference ref = db.collection("Class3MH").document("AC")
                                                .collection("Chemistry").document("again").collection("test");
                                        putData(ref);
                                    }
                                    break;

                                }
                            }
                            break;

                        case "Class4":
                            if (b.contains(sPattern)){
                                switch (subject) {
                                    case "English": {
                                        CollectionReference ref = db.collection("Class4CBSE").document("jvcOWa6MXnTBVOAh6DYS")
                                                .collection("Eng").document("again").collection("test");
                                        putData(ref);
                                    }
                                    case "History": {
                                        CollectionReference ref = db.collection("Class4CBSE").document("jvcOWa6MXnTBVOAh6DYS")
                                                .collection("History").document("again").collection("test");
                                        putData(ref);
                                    }
                                    break;

                                    case "Geography": {
                                        CollectionReference ref = db.collection("Class4CBSE").document("jvcOWa6MXnTBVOAh6DYS")
                                                .collection("Geography").document("again").collection("test");
                                        putData(ref);
                                    }
                                    break;

                                    case "Mathematics": {
                                        CollectionReference ref = db.collection("Class4CBSE").document("jvcOWa6MXnTBVOAh6DYS")
                                                .collection("Mathematics").document("again").collection("test");
                                        putData(ref);
                                    }
                                    break;

                                    case "Science": {
                                        CollectionReference ref = db.collection("Class4CBSE").document("jvcOWa6MXnTBVOAh6DYS")
                                                .collection("Science").document("again").collection("test");
                                        putData(ref);
                                    }
                                    break;

                                    case "Physics": {
                                        CollectionReference ref = db.collection("Class4CBSE").document("GfdFx0V3BaxIVfcT8VnX")
                                                .collection("Physics").document("again").collection("test");
                                        putData(ref);
                                    }
                                    break;

                                    case "Biology": {
                                        CollectionReference ref = db.collection("Class4CBSE").document("GfdFx0V3BaxIVfcT8VnX")
                                                .collection("Biology").document("again").collection("test");
                                        putData(ref);
                                    }
                                    break;

                                    case "Chemistry": {
                                        CollectionReference ref = db.collection("Class4CBSE").document("GfdFx0V3BaxIVfcT8VnX")
                                                .collection("Chemistry").document("again").collection("test");
                                        putData(ref);
                                    }
                                    break;
                                }
                            }
                            else {
                                switch (subject) {
                                    case "English": {
                                        CollectionReference ref = db.collection("Class4MH").document("AD")
                                                .collection("Eng").document("again").collection("test");
                                        putData(ref);
                                    }
                                    break;

                                    case "History": {
                                        CollectionReference ref = db.collection("Class4MH").document("AD")
                                                .collection("History").document("again").collection("test");
                                        putData(ref);
                                    }
                                    break;

                                    case "Geography": {
                                        CollectionReference ref = db.collection("Class4MH").document("AD")
                                                .collection("Geography").document("again").collection("test");
                                        putData(ref);
                                    }
                                    break;

                                    case "Mathematics": {
                                        CollectionReference ref = db.collection("Class4MH").document("AD")
                                                .collection("Mathematics").document("again").collection("test");
                                        putData(ref);
                                    }
                                    break;

                                    case "Science": {
                                        CollectionReference ref = db.collection("Class4MH").document("AD")
                                                .collection("Science").document("again").collection("test");
                                        putData(ref);
                                    } break;

                                    case "Physics": {
                                        CollectionReference ref = db.collection("Class4MH").document("AD")
                                                .collection("Physics").document("again").collection("test");
                                        putData(ref);
                                    } break;

                                    case "Biology": {
                                        CollectionReference ref = db.collection("Class4MH").document("AD")
                                                .collection("Biology").document("again").collection("test");
                                        putData(ref);
                                    } break;

                                    case "Chemistry": {
                                        CollectionReference ref = db.collection("Class4MH").document("AD")
                                                .collection("Chemistry").document("again").collection("test");
                                        putData(ref);
                                    } break;

                                }
                            }
                            break;

                        case "Class5":
                            if (b.contains(sPattern)){
                                switch (subject) {
                                    case "English": {
                                        CollectionReference ref = db.collection("Class5CBSE").document("zu9Z7oNZM8Up9f9wRMjV")
                                                .collection("Eng").document("again").collection("test");
                                        putData(ref);
                                    } break;
                                    case "History": {
                                        CollectionReference ref = db.collection("Class5CBSE").document("zu9Z7oNZM8Up9f9wRMjV")
                                                .collection("History").document("again").collection("test");
                                        putData(ref);
                                    } break;
                                    case "Geography": {
                                        CollectionReference ref = db.collection("Class5CBSE").document("zu9Z7oNZM8Up9f9wRMjV")
                                                .collection("Geography").document("again").collection("test");
                                        putData(ref);

                                    } break;
                                    case "Mathematics": {
                                        CollectionReference ref = db.collection("Class5CBSE").document("zu9Z7oNZM8Up9f9wRMjV")
                                                .collection("Mathematics").document("again").collection("test");
                                        putData(ref);

                                    }break;
                                    case "Science": {
                                        CollectionReference ref = db.collection("Class5CBSE").document("zu9Z7oNZM8Up9f9wRMjV")
                                                .collection("Science").document("again").collection("test");
                                        putData(ref);

                                    }  break;
                                    case "Physics": {
                                        CollectionReference ref = db.collection("Class5CBSE").document("zu9Z7oNZM8Up9f9wRMjV")
                                                .collection("Physics").document("again").collection("test");
                                        putData(ref);

                                    } break;
                                    case "Biology": {
                                        CollectionReference ref = db.collection("Class5CBSE").document("zu9Z7oNZM8Up9f9wRMjV")
                                                .collection("Biology").document("again").collection("test");
                                        putData(ref);

                                    } break;
                                    case "Chemistry": {
                                        CollectionReference ref = db.collection("Class5CBSE").document("zu9Z7oNZM8Up9f9wRMjV")
                                                .collection("Chemistry").document("again").collection("test");
                                        putData(ref);

                                    }break;
                                }
                            }
                            else {
                                switch (subject) {
                                    case "English": {
                                        CollectionReference ref = db.collection("Class5MH").document("AD")
                                                .collection("Eng").document("again").collection("test");
                                        putData(ref);

                                    }break;
                                    case "History": {
                                        CollectionReference ref = db.collection("Class5MH").document("AE")
                                                .collection("History").document("again").collection("test");
                                        putData(ref);

                                    } break;
                                    case "Geography": {
                                        CollectionReference ref = db.collection("Class5MH").document("AE")
                                                .collection("Geography").document("again").collection("test");
                                        putData(ref);

                                    }break;
                                    case "Mathematics": {
                                        CollectionReference ref = db.collection("Class5MH").document("AE")
                                                .collection("Mathematics").document("again").collection("test");
                                        putData(ref);

                                    } break;
                                    case "Science": {
                                        CollectionReference ref = db.collection("Class5MH").document("AE")
                                                .collection("Science").document("again").collection("test");
                                        putData(ref);

                                    }  break;
                                    case "Physics": {
                                        CollectionReference ref = db.collection("Class5MH").document("AE")
                                                .collection("Physics").document("again").collection("test");
                                        putData(ref);

                                    } break;
                                    case "Biology": {
                                        CollectionReference ref = db.collection("Class5MH").document("AE")
                                                .collection("Biology").document("again").collection("test");
                                        putData(ref);

                                    }break;
                                    case "Chemistry": {
                                        CollectionReference ref = db.collection("Class5MH").document("AE")
                                                .collection("Chemistry").document("again").collection("test");
                                        putData(ref);

                                    }break;
                                }
                            } break;

                        case "Class6":
                            if (b.contains(sPattern)){
                                switch (subject) {
                                    case "English": {
                                        CollectionReference ref = db.collection("Class6CBSE").document("FIRST")
                                                .collection("Eng").document("again").collection("test");
                                        putData(ref);

                                    } break;
                                    case "History": {
                                        CollectionReference ref = db.collection("Class6CBSE").document("FIRST")
                                                .collection("History").document("again").collection("test");
                                        putData(ref);

                                    } break;
                                    case "Geography": {
                                        CollectionReference ref = db.collection("Class6CBSE").document("FIRST")
                                                .collection("Geography").document("again").collection("test");
                                        putData(ref);

                                    }break;
                                    case "Mathematics": {
                                        CollectionReference ref = db.collection("Class6CBSE").document("FIRST")
                                                .collection("Mathematics").document("again").collection("test");
                                        putData(ref);

                                    } break;
                                    case "Science": {
                                        CollectionReference ref = db.collection("Class6CBSE").document("FIRST")
                                                .collection("Science").document("again").collection("test");
                                        putData(ref);

                                    }  break;
                                    case "Physics": {
                                        CollectionReference ref = db.collection("Class6CBSE").document("FIRST")
                                                .collection("Physics").document("again").collection("test");
                                        putData(ref);

                                    } break;
                                    case "Biology": {
                                        CollectionReference ref = db.collection("Class6CBSE").document("FIRST")
                                                .collection("Biology").document("again").collection("test");
                                        putData(ref);

                                    }break;
                                    case "Chemistry": {
                                        CollectionReference ref = db.collection("Class6CBSE").document("FIRST")
                                                .collection("Chemistry").document("again").collection("test");
                                        putData(ref);

                                    }break;
                                }
                            }
                            else {
                                switch (subject) {
                                    case "English": {
                                        CollectionReference ref = db.collection("Class6MH").document("AF")
                                                .collection("Eng").document("again").collection("test");
                                        putData(ref);

                                    } break;
                                    case "History": {
                                        CollectionReference ref = db.collection("Class6MH").document("AF")
                                                .collection("History").document("again").collection("test");
                                        putData(ref);

                                    } break;
                                    case "Geography": {
                                        CollectionReference ref = db.collection("Class6MH").document("AF")
                                                .collection("Geography").document("again").collection("test");
                                        putData(ref);

                                    } break;
                                    case "Mathematics": {
                                        CollectionReference ref = db.collection("Class6MH").document("AF")
                                                .collection("Mathematics").document("again").collection("test");
                                        putData(ref);

                                    } break;
                                    case "Science": {
                                        CollectionReference ref = db.collection("Class6MH").document("AF")
                                                .collection("Science").document("again").collection("test");
                                        putData(ref);

                                    } break;
                                    case "Physics": {
                                        CollectionReference ref = db.collection("Class6MH").document("AF")
                                                .collection("Physics").document("again").collection("test");
                                        putData(ref);

                                    } break;
                                    case "Biology": {
                                        CollectionReference ref = db.collection("Class6MH").document("AF")
                                                .collection("Biology").document("again").collection("test");
                                        putData(ref);

                                    }  break;
                                    case "Chemistry": {
                                        CollectionReference ref = db.collection("Class6MH").document("AF")
                                                .collection("Chemistry").document("again").collection("test");
                                        putData(ref);

                                    } break;
                                }
                            } break;

                        case "Class7":
                            if (b.contains(sPattern)){
                                switch (subject) {
                                    case "English": {
                                        CollectionReference ref = db.collection("Class7CBSE").document("A")
                                                .collection("Eng").document("again").collection("test");
                                        putData(ref);

                                    } break;
                                    case "History": {
                                        CollectionReference ref = db.collection("Class7CBSE").document("A")
                                                .collection("History").document("again").collection("test");
                                        putData(ref);

                                    } break;
                                    case "Geography": {
                                        CollectionReference ref = db.collection("Class7CBSE").document("A")
                                                .collection("Geography").document("again").collection("test");
                                        putData(ref);

                                    } break;
                                    case "Mathematics": {
                                        CollectionReference ref = db.collection("Class7CBSE").document("A")
                                                .collection("Mathematics").document("again").collection("test");
                                        putData(ref);

                                    } break;
                                    case "Science": {
                                        CollectionReference ref = db.collection("Class7CBSE").document("A")
                                                .collection("Science").document("again").collection("test");
                                        putData(ref);

                                    }  break;
                                    case "Physics": {
                                        CollectionReference ref = db.collection("Class7CBSE").document("A")
                                                .collection("Physics").document("again").collection("test");
                                        putData(ref);

                                    } break;
                                    case "Biology": {
                                        CollectionReference ref = db.collection("Class7CBSE").document("A")
                                                .collection("Biology").document("again").collection("test");
                                        putData(ref);

                                    } break;
                                    case "Chemistry": {
                                        CollectionReference ref = db.collection("Class7CBSE").document("A")
                                                .collection("Chemistry").document("again").collection("test");
                                        putData(ref);

                                    }  break;
                                }
                            }
                            else {
                                switch (subject) {
                                    case "English": {
                                        CollectionReference ref = db.collection("Class7MH").document("AG")
                                                .collection("Eng").document("again").collection("test");
                                        putData(ref);

                                    } break;
                                    case "History": {
                                        CollectionReference ref = db.collection("Class7MH").document("AG")
                                                .collection("History").document("again").collection("test");
                                        putData(ref);

                                    }break;
                                    case "Geography": {
                                        CollectionReference ref = db.collection("Class7MH").document("AG")
                                                .collection("Geography").document("again").collection("test");
                                        putData(ref);

                                    } break;
                                    case "Mathematics": {
                                        CollectionReference ref = db.collection("Class7MH").document("AG")
                                                .collection("Mathematics").document("again").collection("test");
                                        putData(ref);

                                    }  break;
                                    case "Science": {
                                        CollectionReference ref = db.collection("Class7MH").document("AG")
                                                .collection("Science").document("again").collection("test");
                                        putData(ref);

                                    }  break;
                                    case "Physics": {
                                        CollectionReference ref = db.collection("Class7MH").document("AG")
                                                .collection("Physics").document("again").collection("test");
                                        putData(ref);

                                    }  break;
                                    case "Biology": {
                                        CollectionReference ref = db.collection("Class7MH").document("AG")
                                                .collection("Biology").document("again").collection("test");
                                        putData(ref);

                                    } break;
                                    case "Chemistry": {
                                        CollectionReference ref = db.collection("Class7MH").document("AG")
                                                .collection("Chemistry").document("again").collection("test");
                                        putData(ref);

                                    } break;
                                }
                            }break;

                        case "Class8":
                            if (b.contains(sPattern)){
                                switch (subject) {
                                    case "English": {
                                        CollectionReference ref = db.collection("Class8CBSE").document("B")
                                                .collection("Eng").document("again").collection("test");
                                        putData(ref);

                                    }break;
                                    case "History": {
                                        CollectionReference ref = db.collection("Class8CBSE").document("B")
                                                .collection("History").document("again").collection("test");
                                        putData(ref);

                                    }break;
                                    case "Geography": {
                                        CollectionReference ref = db.collection("Class8CBSE").document("B")
                                                .collection("Geography").document("again").collection("test");
                                        putData(ref);

                                    }break;
                                    case "Mathematics": {
                                        CollectionReference ref = db.collection("Class8CBSE").document("B")
                                                .collection("Mathematics").document("again").collection("test");
                                        putData(ref);

                                    }break;
                                    case "Science": {
                                        CollectionReference ref = db.collection("Class8CBSE").document("B")
                                                .collection("Science").document("again").collection("test");
                                        putData(ref);

                                    } break;
                                    case "Physics": {
                                        CollectionReference ref = db.collection("Class8CBSE").document("B")
                                                .collection("Physics").document("again").collection("test");
                                        putData(ref);

                                    }break;
                                    case "Biology": {
                                        CollectionReference ref = db.collection("Class8CBSE").document("B")
                                                .collection("Biology").document("again").collection("test");
                                        putData(ref);

                                    }  break;
                                    case "Chemistry": {
                                        CollectionReference ref = db.collection("Class8CBSE").document("B")
                                                .collection("Chemistry").document("again").collection("test");
                                        putData(ref);

                                    }  break;
                                }
                            }
                            else {
                                switch (subject) {
                                    case "English": {
                                        CollectionReference ref = db.collection("Class8MH").document("AH")
                                                .collection("Eng").document("again").collection("test");
                                        putData(ref);

                                    }  break;
                                    case "History": {
                                        CollectionReference ref = db.collection("Class8MH").document("AH")
                                                .collection("History").document("again").collection("test");
                                        putData(ref);

                                    }   break;
                                    case "Geography": {
                                        CollectionReference ref = db.collection("Class8MH").document("AH")
                                                .collection("Geography").document("again").collection("test");
                                        putData(ref);

                                    } break;
                                    case "Mathematics": {
                                        CollectionReference ref = db.collection("Class8MH").document("AH")
                                                .collection("Mathematics").document("again").collection("test");
                                        putData(ref);

                                    } break;
                                    case "Science": {
                                        CollectionReference ref = db.collection("Class8MH").document("AH")
                                                .collection("Science").document("again").collection("test");
                                        putData(ref);

                                    }  break;
                                    case "Physics": {
                                        CollectionReference ref = db.collection("Class8MH").document("AH")
                                                .collection("Physics").document("again").collection("test");
                                        putData(ref);

                                    }  break;
                                    case "Biology": {
                                        CollectionReference ref = db.collection("Class8MH").document("AH")
                                                .collection("Biology").document("again").collection("test");
                                        putData(ref);

                                    } break;
                                    case "Chemistry": {
                                        CollectionReference ref = db.collection("Class8MH").document("AH")
                                                .collection("Chemistry").document("again").collection("test");
                                        putData(ref);

                                    }break;
                                }
                            } break;

                        case "Class9":
                            if (b.contains(sPattern)){
                                switch (subject) {
                                    case "English": {
                                        CollectionReference ref = db.collection("Class9CBSE").document("C")
                                                .collection("Eng").document("again").collection("test");
                                        putData(ref);

                                    } break;
                                    case "History": {
                                        CollectionReference ref = db.collection("Class9CBSE").document("C")
                                                .collection("History").document("again").collection("test");
                                        putData(ref);

                                    }  break;
                                    case "Geography": {
                                        CollectionReference ref = db.collection("Class9CBSE").document("C")
                                                .collection("Geography").document("again").collection("test");
                                        putData(ref);

                                    }  break;
                                    case "Mathematics": {
                                        CollectionReference ref = db.collection("Class9CBSE").document("C")
                                                .collection("Mathematics").document("again").collection("test");
                                        putData(ref);

                                    }  break;
                                    case "Science": {
                                        CollectionReference ref = db.collection("Class9CBSE").document("C")
                                                .collection("Science").document("again").collection("test");
                                        putData(ref);

                                    } break;
                                    case "Physics": {
                                        CollectionReference ref = db.collection("Class9CBSE").document("C")
                                                .collection("Physics").document("again").collection("test");
                                        putData(ref);

                                    }  break;
                                    case "Biology": {
                                        CollectionReference ref = db.collection("Class9CBSE").document("C")
                                                .collection("Biology").document("again").collection("test");
                                        putData(ref);

                                    } break;
                                    case "Chemistry": {
                                        CollectionReference ref = db.collection("Class9CBSE").document("C")
                                                .collection("Chemistry").document("again").collection("test");
                                        putData(ref);

                                    } break;
                                }
                            }
                            else {
                                switch (subject) {
                                    case "English": {
                                        CollectionReference ref = db.collection("Class9MH").document("AI")
                                                .collection("Eng").document("again").collection("test");
                                        putData(ref);

                                    } break;
                                    case "History": {
                                        CollectionReference ref = db.collection("Class9MH").document("AI")
                                                .collection("History").document("again").collection("test");
                                        putData(ref);

                                    } break;
                                    case "Geography": {
                                        CollectionReference ref = db.collection("Class9MH").document("AI")
                                                .collection("Geography").document("again").collection("test");
                                        putData(ref);

                                    }break;
                                    case "Mathematics": {
                                        CollectionReference ref = db.collection("Class9MH").document("AI")
                                                .collection("Mathematics").document("again").collection("test");
                                        putData(ref);

                                    }   break;
                                    case "Science": {
                                        CollectionReference ref = db.collection("Class9MH").document("AI")
                                                .collection("Science").document("again").collection("test");
                                        putData(ref);

                                    } break;
                                    case "Physics": {
                                        CollectionReference ref = db.collection("Class9MH").document("AI")
                                                .collection("Physics").document("again").collection("test");
                                        putData(ref);

                                    }  break;
                                    case "Biology": {
                                        CollectionReference ref = db.collection("Class9MH").document("AI")
                                                .collection("Biology").document("again").collection("test");
                                        putData(ref);

                                    } break;
                                    case "Chemistry": {
                                        CollectionReference ref = db.collection("Class9MH").document("AI")
                                                .collection("Chemistry").document("again").collection("test");
                                        putData(ref);

                                    }break;
                                }
                            } break;

                        case "Class10":
                            if (b.contains(sPattern)){
                                switch (subject) {
                                    case "English": {
                                        CollectionReference ref = db.collection("Class10CBSE").document("D")
                                                .collection("Eng").document("again").collection("test");
                                        putData(ref);

                                    } break;
                                    case "History": {
                                        CollectionReference ref = db.collection("Class10CBSE").document("D")
                                                .collection("History").document("again").collection("test");
                                        putData(ref);

                                    } break;
                                    case "Geography": {
                                        CollectionReference ref = db.collection("Class10CBSE").document("D")
                                                .collection("Geography").document("again").collection("test");
                                        putData(ref);

                                    }  break;
                                    case "Mathematics": {
                                        CollectionReference ref = db.collection("Class10CBSE").document("D")
                                                .collection("Mathematics").document("again").collection("test");
                                        putData(ref);

                                    } break;
                                    case "Science": {
                                        CollectionReference ref = db.collection("Class10CBSE").document("D")
                                                .collection("Science").document("again").collection("test");
                                        putData(ref);

                                    }break;
                                    case "Physics": {
                                        CollectionReference ref = db.collection("Class10CBSE").document("D")
                                                .collection("Physics").document("again").collection("test");
                                        putData(ref);

                                    } break;
                                    case "Biology": {
                                        CollectionReference ref = db.collection("Class10CBSE").document("D")
                                                .collection("Biology").document("again").collection("test");
                                        putData(ref);

                                    } break;
                                    case "Chemistry": {
                                        CollectionReference ref = db.collection("Class10CBSE").document("D")
                                                .collection("Chemistry").document("again").collection("test");
                                        putData(ref);

                                    } break;
                                }
                            }
                            else {
                                switch (subject) {
                                    case "English": {
                                        CollectionReference ref = db.collection("Class10MH").document("AJ")
                                                .collection("Eng").document("again").collection("test");
                                        putData(ref);

                                    } break;
                                    case "History": {
                                        CollectionReference ref = db.collection("Class10MH").document("AJ")
                                                .collection("History").document("again").collection("test");
                                        putData(ref);

                                    }break;
                                    case "Geography": {
                                        CollectionReference ref = db.collection("Class9MH").document("AJ")
                                                .collection("Geography").document("again").collection("test");
                                        putData(ref);

                                    }  break;
                                    case "Mathematics": {
                                        CollectionReference ref = db.collection("Class9MH").document("AJ")
                                                .collection("Mathematics").document("again").collection("test");
                                        putData(ref);

                                    } break;
                                    case "Science": {
                                        CollectionReference ref = db.collection("Class9MH").document("AJ")
                                                .collection("Science").document("again").collection("test");
                                        putData(ref);

                                    } break;
                                    case "Physics": {
                                        CollectionReference ref = db.collection("Class9MH").document("AJ")
                                                .collection("Physics").document("again").collection("test");
                                        putData(ref);

                                    } break;
                                    case "Biology": {
                                        CollectionReference ref = db.collection("Class9MH").document("AJ")
                                                .collection("Biology").document("again").collection("test");
                                        putData(ref);

                                    } break;
                                    case "Chemistry": {
                                        CollectionReference ref = db.collection("Class9MH").document("AJ")
                                                .collection("Chemistry").document("again").collection("test");
                                        putData(ref);

                                    }break;
                                }
                            } break;

                        case "Class11":
                            if (b.contains(sPattern)){
                                switch (subject) {
                                    case "English": {
                                        CollectionReference ref = db.collection("Class11CBSE").document("E")
                                                .collection("Eng").document("again").collection("test");
                                        putData(ref);

                                    }    break;
                                    case "History": {
                                        CollectionReference ref = db.collection("Class11CBSE").document("E")
                                                .collection("History").document("again").collection("test");
                                        putData(ref);

                                    } break;
                                    case "Geography": {
                                        CollectionReference ref = db.collection("Class11CBSE").document("E")
                                                .collection("Geography").document("again").collection("test");
                                        putData(ref);

                                    }  break;
                                    case "Mathematics": {
                                        CollectionReference ref = db.collection("Class11CBSE").document("E")
                                                .collection("Mathematics").document("again").collection("test");
                                        putData(ref);

                                    } break;
                                    case "Science": {
                                        CollectionReference ref = db.collection("Class11CBSE").document("E")
                                                .collection("Science").document("again").collection("test");
                                        putData(ref);

                                    } break;
                                    case "Physics": {
                                        CollectionReference ref = db.collection("Class11CBSE").document("E")
                                                .collection("Physics").document("again").collection("test");
                                        putData(ref);

                                    }break;
                                    case "Biology": {
                                        CollectionReference ref = db.collection("Class11CBSE").document("E")
                                                .collection("Biology").document("again").collection("test");
                                        putData(ref);

                                    } break;
                                    case "Chemistry": {
                                        CollectionReference ref = db.collection("Class11CBSE").document("E")
                                                .collection("Chemistry").document("again").collection("test");
                                        putData(ref);

                                    }break;
                                }
                            }
                            else {
                                switch (subject) {
                                    case "English": {
                                        CollectionReference ref = db.collection("Class11MH").document("AK")
                                                .collection("Eng").document("again").collection("test");
                                        putData(ref);

                                    } break;
                                    case "History": {
                                        CollectionReference ref = db.collection("Class11MH").document("AK")
                                                .collection("History").document("again").collection("test");
                                        putData(ref);

                                    }  break;
                                    case "Geography": {
                                        CollectionReference ref = db.collection("Class9MH").document("AK")
                                                .collection("Geography").document("again").collection("test");
                                        putData(ref);

                                    } break;
                                    case "Mathematics": {
                                        CollectionReference ref = db.collection("Class9MH").document("AK")
                                                .collection("Mathematics").document("again").collection("test");
                                        putData(ref);

                                    } break;
                                    case "Science": {
                                        CollectionReference ref = db.collection("Class9MH").document("AK")
                                                .collection("Science").document("again").collection("test");
                                        putData(ref);

                                    } break;
                                    case "Physics": {
                                        CollectionReference ref = db.collection("Class9MH").document("AK")
                                                .collection("Physics").document("again").collection("test");
                                        putData(ref);

                                    } break;
                                    case "Biology": {
                                        CollectionReference ref = db.collection("Class9MH").document("AK")
                                                .collection("Biology").document("again").collection("test");
                                        putData(ref);

                                    }break;
                                    case "Chemistry": {
                                        CollectionReference ref = db.collection("Class9MH").document("AK")
                                                .collection("Chemistry").document("again").collection("test");
                                        putData(ref);

                                    } break;
                                }
                            } break;

                        case "Class12":
                            if (b.contains(sPattern)){
                                switch (subject) {
                                    case "English": {
                                        CollectionReference ref = db.collection("Class12CBSE").document("F")
                                                .collection("Eng").document("again").collection("test");
                                        putData(ref);

                                    } break;
                                    case "History": {
                                        CollectionReference ref = db.collection("Class12CBSE").document("F")
                                                .collection("History").document("again").collection("test");
                                        putData(ref);

                                    }  break;
                                    case "Geography": {
                                        CollectionReference ref = db.collection("Class12CBSE").document("F")
                                                .collection("Geography").document("again").collection("test");
                                        putData(ref);

                                    } break;
                                    case "Mathematics": {
                                        CollectionReference ref = db.collection("Class12CBSE").document("F")
                                                .collection("Mathematics").document("again").collection("test");
                                        putData(ref);

                                    }  break;
                                    case "Science": {
                                        CollectionReference ref = db.collection("Class12CBSE").document("F")
                                                .collection("Science").document("again").collection("test");
                                        putData(ref);

                                    }  break;
                                    case "Physics": {
                                        CollectionReference ref = db.collection("Class12CBSE").document("F")
                                                .collection("Physics").document("again").collection("test");
                                        putData(ref);

                                    } break;
                                    case "Biology": {
                                        CollectionReference ref = db.collection("Class12CBSE").document("F")
                                                .collection("Biology").document("again").collection("test");
                                        putData(ref);

                                    } break;
                                    case "Chemistry": {
                                        CollectionReference ref = db.collection("Class12CBSE").document("F")
                                                .collection("Chemistry").document("again").collection("test");
                                        putData(ref);

                                    } break;
                                }
                            }
                            else {
                                switch (subject) {
                                    case "English": {
                                        CollectionReference ref = db.collection("Class12MH").document("AL")
                                                .collection("Eng").document("again").collection("test");
                                        putData(ref);

                                    }break;
                                    case "History": {
                                        CollectionReference ref = db.collection("Class12MH").document("AL")
                                                .collection("History").document("again").collection("test");
                                        putData(ref);

                                    } break;
                                    case "Geography": {
                                        CollectionReference ref = db.collection("Class9MH").document("AL")
                                                .collection("Geography").document("again").collection("test");
                                        putData(ref);

                                    }   break;
                                    case "Mathematics": {
                                        CollectionReference ref = db.collection("Class9MH").document("AL")
                                                .collection("Mathematics").document("again").collection("test");
                                        putData(ref);

                                    }  break;
                                    case "Science": {
                                        CollectionReference ref = db.collection("Class9MH").document("AL")
                                                .collection("Science").document("again").collection("test");
                                        putData(ref);

                                    }  break;
                                    case "Physics": {
                                        CollectionReference ref = db.collection("Class9MH").document("AL")
                                                .collection("Physics").document("again").collection("test");
                                        putData(ref);

                                    }  break;
                                    case "Biology": {
                                        CollectionReference ref = db.collection("Class9MH").document("AL")
                                                .collection("Biology").document("again").collection("test");
                                        putData(ref);

                                    } break;
                                    case "Chemistry": {
                                        CollectionReference ref = db.collection("Class9MH").document("AL")
                                                .collection("Chemistry").document("again").collection("test");
                                        putData(ref);

                                    }  break;
                                }
                            }
                            break;
                    }

                }

            }

            private void putData(CollectionReference ref) {
                ref.get()
                        .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                for (DocumentSnapshot documentSnapshot : task.getResult()) {

                                    TestDownModel testDownModel = new TestDownModel(documentSnapshot.getString("TestName"),
                                            documentSnapshot.getString("TestLink"));
                                    testDownModelArrayList.add(testDownModel);
                                }

                                testAdapter = new TestAdapter(Tests.this, testDownModelArrayList);
                                mRecyclerView.setAdapter(testAdapter);
                            }
                        })

                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(Tests.this, "Error ;-.-;", Toast.LENGTH_SHORT).show();
                            }
                        });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }

    private void setUpFB(){
        db=FirebaseFirestore.getInstance();
    }

    private void setUpRV(){
        mRecyclerView= findViewById(R.id.recycyleT);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    public void selectFile(View view) {
        //we will pick images
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("*/*");
        //     intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
        startActivityForResult(intent, PICK_IMG);
        upload.setVisibility(View.GONE);

    }

    @SuppressLint("SetTextI18n")
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMG) {
            if (resultCode == RESULT_OK) {

                Uri imageuri = data.getData();
                ImageList.add(imageuri);
                Toast.makeText(Tests.this, "You have selected " + ImageList.size() + " File", Toast.LENGTH_SHORT).show();
                upload.setVisibility(View.VISIBLE);
                choose.setVisibility(View.GONE);
            }
        }
    }

   // @SuppressLint("SetTextI18n")
    public void AddFiles(View view) {
        //      textView.setText("Please Wait ... If Uploading takes Too much time please the button again ");
        progressDialog.show();
        final StorageReference ImageFolder = FirebaseStorage.getInstance().getReference().child("Study Materials");
        for (uploads = 0; uploads < ImageList.size(); uploads++) {
            Uri Image = ImageList.get(uploads);
            final StorageReference imagename = ImageFolder.child("Test_Data/" + Image.getLastPathSegment());
            imagename.putFile(ImageList.get(uploads)).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    imagename.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {

                            String url = String.valueOf(uri);
                            SendLink(url);

                        }
                    });
                }
            });
        }
    }

    private void SendLink(final String url) {
        String a = "Class1 Class2 Class3 Class4 Class5 Class6 Class7 Class8 Class9 Class10 Class11 Class12";
        final String b = "CBSE";

        Query query = myref.orderByChild("email").equalTo(user1.getEmail());
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot ds : dataSnapshot.getChildren()){

                    String Name = ""+ds.child("name").getValue();
                    String sClass = ""+ds.child("class").getValue();
                    String sPattern = ""+ ds.child("pattern").getValue();
                    String subject = sample.getText().toString();

                    switch (sClass){
                        case "Class1":
                            if (b.contains(sPattern)){
                                switch (subject) {
                                    case "English": {
                                        CollectionReference ref = db.collection("Class1CBSE").document("CK6aEA860T6TIHucpEXw")
                                                .collection("Eng").document("PePdIxgaBgIy5p677HY1").collection("answer");
                                        putData(ref, url, Name);
                                    }
                                    break;

                                    case "History": {
                                        CollectionReference ref = db.collection("Class1CBSE").document("CK6aEA860T6TIHucpEXw")
                                                .collection("History").document("L7k3y6ZZmI2iO8QFSvs9").collection("answer");
                                        putData(ref, url, Name);
                                    }
                                    break;

                                    case "Geography": {
                                        CollectionReference ref = db.collection("Class1CBSE").document("CK6aEA860T6TIHucpEXw")
                                                .collection("Geography").document("XtQkhD23tifMQ8qdchWr").collection("answer");
                                        putData(ref, url, Name);
                                    }
                                    break;

                                    case "Mathematics": {
                                        CollectionReference ref = db.collection("Class1CBSE").document("CK6aEA860T6TIHucpEXw")
                                                .collection("Mathematics").document("NYCjx8n8qIqtJtTDNJGt").collection("answer");
                                        putData(ref, url, Name);
                                    }
                                    break;

                                    case "Science": {
                                        CollectionReference ref = db.collection("Class1CBSE").document("CK6aEA860T6TIHucpEXw")
                                                .collection("Science").document("freMDiIPbqstwDgE7Zyd").collection("answer");
                                        putData(ref, url, Name);
                                    }
                                    break;

                                    case "Physics": {
                                        CollectionReference ref = db.collection("Class1CBSE").document("CK6aEA860T6TIHucpEXw")
                                                .collection("Physics").document("307IuL9VYw7Esmi8Fl7m").collection("answer");
                                        putData(ref, url, Name);
                                    }
                                    break;

                                    case "Biology": {
                                        CollectionReference ref = db.collection("Class1CBSE").document("CK6aEA860T6TIHucpEXw")
                                                .collection("Biology").document("1Dm0FouhEU5EW2bkzyuD").collection("answer");
                                        putData(ref, url, Name);
                                    }
                                    break;

                                    case "Chemistry": {
                                        CollectionReference ref = db.collection("Class1CBSE").document("CK6aEA860T6TIHucpEXw")
                                                .collection("Chemistry").document("zKV4PwDP2eAlMSBkXJJj").collection("answer");
                                        putData(ref, url, Name);
                                    }
                                    break;
                                }
                            }
                            else {
                                switch (subject) {
                                    case "English": {
                                        CollectionReference ref = db.collection("Class1MH").document("AA")
                                                .collection("Eng").document("again").collection("answer");
                                        putData(ref, url, Name);
                                    }
                                    break;

                                    case "History": {
                                        CollectionReference ref = db.collection("Class1MH").document("AA")
                                                .collection("History").document("again").collection("answer");
                                        putData(ref, url, Name);
                                    }
                                    break;

                                    case "Geography": {
                                        CollectionReference ref = db.collection("Class1MH").document("AA")
                                                .collection("Geography").document("again").collection("answer");
                                        putData(ref, url, Name);
                                    }

                                    case "Mathematics": {
                                        CollectionReference ref = db.collection("Class1MH").document("AA")
                                                .collection("Mathematics").document("again").collection("answer");
                                        putData(ref, url, Name);
                                    }
                                    break;

                                    case "Science": {
                                        CollectionReference ref = db.collection("Class1MH").document("AA")
                                                .collection("Science").document("again").collection("answer");
                                        putData(ref, url, Name);
                                    }
                                    break;

                                    case "Physics": {
                                        CollectionReference ref = db.collection("Class1MH").document("AA")
                                                .collection("Physics").document("again").collection("answer");
                                        putData(ref, url, Name);
                                    }
                                    break;

                                    case "Biology": {
                                        CollectionReference ref = db.collection("Class1MH").document("AA")
                                                .collection("Biology").document("again").collection("answer");
                                        putData(ref, url, Name);
                                    }
                                    break;

                                    case "Chemistry": {
                                        CollectionReference ref = db.collection("Class1MH").document("AA")
                                                .collection("Chemistry").document("again").collection("answer");
                                        putData(ref, url, Name);
                                    }
                                    break;
                                }
                            }
                            break;

                        case "Class2":
                            if (b.contains(sPattern)){
                                switch (subject) {
                                    case "English": {
                                        CollectionReference ref = db.collection("Class2CBSE").document("gPOVSQW8gCKyO1RVCRQo")
                                                .collection("Eng").document("again").collection("answer");
                                        putData(ref, url, Name);
                                    }
                                    break;

                                    case "History": {
                                        CollectionReference ref = db.collection("Class2CBSE").document("gPOVSQW8gCKyO1RVCRQo")
                                                .collection("History").document("again").collection("answer");
                                        putData(ref, url, Name);
                                    }
                                    case "Geography": {
                                        CollectionReference ref = db.collection("Class2CBSE").document("gPOVSQW8gCKyO1RVCRQo")
                                                .collection("Geography").document("again").collection("answer");
                                        putData(ref, url, Name);
                                    }
                                    break;

                                    case "Mathematics": {
                                        CollectionReference ref = db.collection("Class2CBSE").document("gPOVSQW8gCKyO1RVCRQo")
                                                .collection("Mathematics").document("again").collection("answer");
                                        putData(ref, url, Name);
                                    }
                                    break;

                                    case "Science": {
                                        CollectionReference ref = db.collection("Class2CBSE").document("gPOVSQW8gCKyO1RVCRQo")
                                                .collection("Science").document("again").collection("answer");
                                        putData(ref, url, Name);
                                    }
                                    break;

                                    case "Physics": {
                                        CollectionReference ref = db.collection("Class2CBSE").document("gPOVSQW8gCKyO1RVCRQo")
                                                .collection("Physics").document("again").collection("answer");
                                        putData(ref, url, Name);
                                    }
                                    break;

                                    case "Biology": {
                                        CollectionReference ref = db.collection("Class2CBSE").document("gPOVSQW8gCKyO1RVCRQo")
                                                .collection("Biology").document("again").collection("answer");
                                        putData(ref, url, Name);
                                    }
                                    break;

                                    case "Chemistry": {
                                        CollectionReference ref = db.collection("Class2CBSE").document("gPOVSQW8gCKyO1RVCRQo")
                                                .collection("Chemistry").document("again").collection("answer");
                                        putData(ref, url, Name);
                                    }
                                    break;

                                }
                            }
                            else {
                                switch (subject) {
                                    case "English": {
                                        CollectionReference ref = db.collection("Class2MH").document("AB")
                                                .collection("Eng").document("again").collection("answer");
                                        putData(ref, url, Name);
                                    }
                                    break;

                                    case "History": {
                                        CollectionReference ref = db.collection("Class2MH").document("AB")
                                                .collection("History").document("again").collection("answer");
                                        putData(ref, url, Name);
                                    }
                                    case "Geography": {
                                        CollectionReference ref = db.collection("Class2MH").document("AB")
                                                .collection("Geography").document("again").collection("answer");
                                        putData(ref, url, Name);
                                    }
                                    break;

                                    case "Mathematics": {
                                        CollectionReference ref = db.collection("Class2MH").document("AB")
                                                .collection("Mathematics").document("again").collection("answer");
                                        putData(ref, url, Name);
                                    }
                                    break;

                                    case "Science": {
                                        CollectionReference ref = db.collection("Class2MH").document("AB")
                                                .collection("Science").document("again").collection("answer");
                                        putData(ref, url, Name);
                                    }
                                    break;

                                    case "Physics": {
                                        CollectionReference ref = db.collection("Class2MH").document("AB")
                                                .collection("Physics").document("again").collection("answer");
                                        putData(ref, url, Name);
                                    }
                                    break;

                                    case "Biology": {
                                        CollectionReference ref = db.collection("Class2MH").document("AB")
                                                .collection("Biology").document("again").collection("answer");
                                        putData(ref, url, Name);
                                    }
                                    break;

                                    case "Chemistry": {
                                        CollectionReference ref = db.collection("Class2MH").document("AB")
                                                .collection("Chemistry").document("again").collection("answer");
                                        putData(ref, url, Name);
                                    }
                                    break;

                                }
                            }
                            break;

                        case "Class3":
                            if (b.contains(sPattern)){
                                switch (subject) {
                                    case "English": {
                                        CollectionReference ref = db.collection("Class3CBSE").document("GfdFx0V3BaxIVfcT8VnX")
                                                .collection("Eng").document("again").collection("answer");
                                        putData(ref, url, Name);
                                    }
                                    break;

                                    case "History": {
                                        CollectionReference ref = db.collection("Class3CBSE").document("GfdFx0V3BaxIVfcT8VnX")
                                                .collection("History").document("again").collection("answer");
                                        putData(ref, url, Name);
                                    }
                                    case "Geography": {
                                        CollectionReference ref = db.collection("Class3CBSE").document("GfdFx0V3BaxIVfcT8VnX")
                                                .collection("Geography").document("again").collection("answer");
                                        putData(ref, url, Name);
                                    }
                                    break;

                                    case "Mathematics": {
                                        CollectionReference ref = db.collection("Class3CBSE").document("GfdFx0V3BaxIVfcT8VnX")
                                                .collection("Mathematics").document("again").collection("answer");
                                        putData(ref, url, Name);
                                    }
                                    break;

                                    case "Science": {
                                        CollectionReference ref = db.collection("Class3CBSE").document("GfdFx0V3BaxIVfcT8VnX")
                                                .collection("Science").document("again").collection("answer");
                                        putData(ref, url, Name);
                                    }
                                    break;

                                    case "Physics": {
                                        CollectionReference ref = db.collection("Class3CBSE").document("GfdFx0V3BaxIVfcT8VnX")
                                                .collection("Physics").document("again").collection("answer");
                                        putData(ref, url, Name);
                                    }
                                    break;

                                    case "Biology": {
                                        CollectionReference ref = db.collection("Class3CBSE").document("GfdFx0V3BaxIVfcT8VnX")
                                                .collection("Biology").document("again").collection("answer");
                                        putData(ref, url, Name);
                                    }
                                    break;

                                    case "Chemistry": {
                                        CollectionReference ref = db.collection("Class3CBSE").document("GfdFx0V3BaxIVfcT8VnX")
                                                .collection("Chemistry").document("again").collection("answer");
                                        putData(ref, url, Name);
                                    }
                                    break;

                                }
                            }
                            else {
                                switch (subject) {
                                    case "English": {
                                        CollectionReference ref = db.collection("Class3MH").document("AC")
                                                .collection("Eng").document("again").collection("answer");
                                        putData(ref, url, Name);
                                    }
                                    break;

                                    case "History": {
                                        CollectionReference ref = db.collection("Class3MH").document("AC")
                                                .collection("History").document("again").collection("answer");
                                        putData(ref, url, Name);
                                    }
                                    case "Geography": {
                                        CollectionReference ref = db.collection("Class3MH").document("AC")
                                                .collection("Geography").document("again").collection("answer");
                                        putData(ref, url, Name);
                                    }
                                    break;

                                    case "Mathematics": {
                                        CollectionReference ref = db.collection("Class3MH").document("AC")
                                                .collection("Mathematics").document("again").collection("answer");
                                        putData(ref, url, Name);
                                    }
                                    break;

                                    case "Science": {
                                        CollectionReference ref = db.collection("Class3MH").document("AC")
                                                .collection("Science").document("again").collection("answer");
                                        putData(ref, url, Name);
                                    }
                                    break;

                                    case "Physics": {
                                        CollectionReference ref = db.collection("Class3MH").document("AC")
                                                .collection("Physics").document("again").collection("answer");
                                        putData(ref, url, Name);
                                    }
                                    break;

                                    case "Biology": {
                                        CollectionReference ref = db.collection("Class3MH").document("AC")
                                                .collection("Biology").document("again").collection("answer");
                                        putData(ref, url, Name);
                                    }
                                    break;

                                    case "Chemistry": {
                                        CollectionReference ref = db.collection("Class3MH").document("AC")
                                                .collection("Chemistry").document("again").collection("answer");
                                        putData(ref, url, Name);
                                    }
                                    break;

                                }
                            }
                            break;

                        case "Class4":
                            if (b.contains(sPattern)){
                                switch (subject) {
                                    case "English": {
                                        CollectionReference ref = db.collection("Class4CBSE").document("jvcOWa6MXnTBVOAh6DYS")
                                                .collection("Eng").document("again").collection("answer");
                                        putData(ref, url, Name);
                                    }
                                    case "History": {
                                        CollectionReference ref = db.collection("Class4CBSE").document("jvcOWa6MXnTBVOAh6DYS")
                                                .collection("History").document("again").collection("answer");
                                        putData(ref, url, Name);
                                    }
                                    break;

                                    case "Geography": {
                                        CollectionReference ref = db.collection("Class4CBSE").document("jvcOWa6MXnTBVOAh6DYS")
                                                .collection("Geography").document("again").collection("answer");
                                        putData(ref, url, Name);
                                    }
                                    break;

                                    case "Mathematics": {
                                        CollectionReference ref = db.collection("Class4CBSE").document("jvcOWa6MXnTBVOAh6DYS")
                                                .collection("Mathematics").document("again").collection("answer");
                                        putData(ref, url, Name);
                                    }
                                    break;

                                    case "Science": {
                                        CollectionReference ref = db.collection("Class4CBSE").document("jvcOWa6MXnTBVOAh6DYS")
                                                .collection("Science").document("again").collection("answer");
                                        putData(ref, url, Name);
                                    }
                                    break;

                                    case "Physics": {
                                        CollectionReference ref = db.collection("Class4CBSE").document("GfdFx0V3BaxIVfcT8VnX")
                                                .collection("Physics").document("again").collection("answer");
                                        putData(ref, url, Name);
                                    }
                                    break;

                                    case "Biology": {
                                        CollectionReference ref = db.collection("Class4CBSE").document("GfdFx0V3BaxIVfcT8VnX")
                                                .collection("Biology").document("again").collection("answer");
                                        putData(ref, url, Name);
                                    }
                                    break;

                                    case "Chemistry": {
                                        CollectionReference ref = db.collection("Class4CBSE").document("GfdFx0V3BaxIVfcT8VnX")
                                                .collection("Chemistry").document("again").collection("answer");
                                        putData(ref, url, Name);
                                    }
                                    break;
                                }
                            }
                            else {
                                switch (subject) {
                                    case "English": {
                                        CollectionReference ref = db.collection("Class4MH").document("AD")
                                                .collection("Eng").document("again").collection("answer");
                                        putData(ref, url, Name);
                                    }
                                    break;

                                    case "History": {
                                        CollectionReference ref = db.collection("Class4MH").document("AD")
                                                .collection("History").document("again").collection("answer");
                                        putData(ref, url, Name);
                                    }
                                    break;

                                    case "Geography": {
                                        CollectionReference ref = db.collection("Class4MH").document("AD")
                                                .collection("Geography").document("again").collection("answer");
                                        putData(ref, url, Name);
                                    }
                                    break;

                                    case "Mathematics": {
                                        CollectionReference ref = db.collection("Class4MH").document("AD")
                                                .collection("Mathematics").document("again").collection("answer");
                                        putData(ref, url, Name);
                                    }
                                    break;

                                    case "Science": {
                                        CollectionReference ref = db.collection("Class4MH").document("AD")
                                                .collection("Science").document("again").collection("answer");
                                        putData(ref, url, Name);
                                    } break;

                                    case "Physics": {
                                        CollectionReference ref = db.collection("Class4MH").document("AD")
                                                .collection("Physics").document("again").collection("answer");
                                        putData(ref, url, Name);
                                    } break;

                                    case "Biology": {
                                        CollectionReference ref = db.collection("Class4MH").document("AD")
                                                .collection("Biology").document("again").collection("answer");
                                        putData(ref, url, Name);
                                    } break;

                                    case "Chemistry": {
                                        CollectionReference ref = db.collection("Class4MH").document("AD")
                                                .collection("Chemistry").document("again").collection("answer");
                                        putData(ref, url, Name);
                                    } break;

                                }
                            }
                            break;

                        case "Class5":
                            if (b.contains(sPattern)){
                                switch (subject) {
                                    case "English": {
                                        CollectionReference ref = db.collection("Class5CBSE").document("zu9Z7oNZM8Up9f9wRMjV")
                                                .collection("Eng").document("again").collection("answer");
                                        putData(ref, url, Name);
                                    } break;
                                    case "History": {
                                        CollectionReference ref = db.collection("Class5CBSE").document("zu9Z7oNZM8Up9f9wRMjV")
                                                .collection("History").document("again").collection("answer");
                                        putData(ref, url, Name);
                                    } break;
                                    case "Geography": {
                                        CollectionReference ref = db.collection("Class5CBSE").document("zu9Z7oNZM8Up9f9wRMjV")
                                                .collection("Geography").document("again").collection("answer");
                                        putData(ref, url, Name);

                                    } break;
                                    case "Mathematics": {
                                        CollectionReference ref = db.collection("Class5CBSE").document("zu9Z7oNZM8Up9f9wRMjV")
                                                .collection("Mathematics").document("again").collection("answer");
                                        putData(ref, url, Name);

                                    }break;
                                    case "Science": {
                                        CollectionReference ref = db.collection("Class5CBSE").document("zu9Z7oNZM8Up9f9wRMjV")
                                                .collection("Science").document("again").collection("answer");
                                        putData(ref, url, Name);

                                    }  break;
                                    case "Physics": {
                                        CollectionReference ref = db.collection("Class5CBSE").document("zu9Z7oNZM8Up9f9wRMjV")
                                                .collection("Physics").document("again").collection("answer");
                                        putData(ref, url, Name);

                                    } break;
                                    case "Biology": {
                                        CollectionReference ref = db.collection("Class5CBSE").document("zu9Z7oNZM8Up9f9wRMjV")
                                                .collection("Biology").document("again").collection("answer");
                                        putData(ref, url, Name);

                                    } break;
                                    case "Chemistry": {
                                        CollectionReference ref = db.collection("Class5CBSE").document("zu9Z7oNZM8Up9f9wRMjV")
                                                .collection("Chemistry").document("again").collection("answer");
                                        putData(ref, url, Name);

                                    }break;
                                }
                            }
                            else {
                                switch (subject) {
                                    case "English": {
                                        CollectionReference ref = db.collection("Class5MH").document("AD")
                                                .collection("Eng").document("again").collection("answer");
                                        putData(ref, url, Name);

                                    }break;
                                    case "History": {
                                        CollectionReference ref = db.collection("Class5MH").document("AE")
                                                .collection("History").document("again").collection("answer");
                                        putData(ref, url, Name);

                                    } break;
                                    case "Geography": {
                                        CollectionReference ref = db.collection("Class5MH").document("AE")
                                                .collection("Geography").document("again").collection("answer");
                                        putData(ref, url, Name);

                                    }break;
                                    case "Mathematics": {
                                        CollectionReference ref = db.collection("Class5MH").document("AE")
                                                .collection("Mathematics").document("again").collection("answer");
                                        putData(ref, url, Name);

                                    } break;
                                    case "Science": {
                                        CollectionReference ref = db.collection("Class5MH").document("AE")
                                                .collection("Science").document("again").collection("answer");
                                        putData(ref, url, Name);

                                    }  break;
                                    case "Physics": {
                                        CollectionReference ref = db.collection("Class5MH").document("AE")
                                                .collection("Physics").document("again").collection("answer");
                                        putData(ref, url, Name);

                                    } break;
                                    case "Biology": {
                                        CollectionReference ref = db.collection("Class5MH").document("AE")
                                                .collection("Biology").document("again").collection("answer");
                                        putData(ref, url, Name);

                                    }break;
                                    case "Chemistry": {
                                        CollectionReference ref = db.collection("Class5MH").document("AE")
                                                .collection("Chemistry").document("again").collection("answer");
                                        putData(ref, url, Name);

                                    }break;
                                }
                            } break;

                        case "Class6":
                            if (b.contains(sPattern)){
                                switch (subject) {
                                    case "English": {
                                        CollectionReference ref = db.collection("Class6CBSE").document("FIRST")
                                                .collection("Eng").document("again").collection("answer");
                                        putData(ref, url, Name);

                                    } break;
                                    case "History": {
                                        CollectionReference ref = db.collection("Class6CBSE").document("FIRST")
                                                .collection("History").document("again").collection("answer");
                                        putData(ref, url, Name);

                                    } break;
                                    case "Geography": {
                                        CollectionReference ref = db.collection("Class6CBSE").document("FIRST")
                                                .collection("Geography").document("again").collection("answer");
                                        putData(ref, url, Name);

                                    }break;
                                    case "Mathematics": {
                                        CollectionReference ref = db.collection("Class6CBSE").document("FIRST")
                                                .collection("Mathematics").document("again").collection("answer");
                                        putData(ref, url, Name);

                                    } break;
                                    case "Science": {
                                        CollectionReference ref = db.collection("Class6CBSE").document("FIRST")
                                                .collection("Science").document("again").collection("answer");
                                        putData(ref, url, Name);

                                    }  break;
                                    case "Physics": {
                                        CollectionReference ref = db.collection("Class6CBSE").document("FIRST")
                                                .collection("Physics").document("again").collection("answer");
                                        putData(ref, url, Name);

                                    } break;
                                    case "Biology": {
                                        CollectionReference ref = db.collection("Class6CBSE").document("FIRST")
                                                .collection("Biology").document("again").collection("answer");
                                        putData(ref, url, Name);

                                    }break;
                                    case "Chemistry": {
                                        CollectionReference ref = db.collection("Class6CBSE").document("FIRST")
                                                .collection("Chemistry").document("again").collection("answer");
                                        putData(ref, url, Name);

                                    }break;
                                }
                            }
                            else {
                                switch (subject) {
                                    case "English": {
                                        CollectionReference ref = db.collection("Class6MH").document("AF")
                                                .collection("Eng").document("again").collection("answer");
                                        putData(ref, url, Name);

                                    } break;
                                    case "History": {
                                        CollectionReference ref = db.collection("Class6MH").document("AF")
                                                .collection("History").document("again").collection("answer");
                                        putData(ref, url, Name);

                                    } break;
                                    case "Geography": {
                                        CollectionReference ref = db.collection("Class6MH").document("AF")
                                                .collection("Geography").document("again").collection("answer");
                                        putData(ref, url, Name);

                                    } break;
                                    case "Mathematics": {
                                        CollectionReference ref = db.collection("Class6MH").document("AF")
                                                .collection("Mathematics").document("again").collection("answer");
                                        putData(ref, url, Name);

                                    } break;
                                    case "Science": {
                                        CollectionReference ref = db.collection("Class6MH").document("AF")
                                                .collection("Science").document("again").collection("answer");
                                        putData(ref, url, Name);

                                    } break;
                                    case "Physics": {
                                        CollectionReference ref = db.collection("Class6MH").document("AF")
                                                .collection("Physics").document("again").collection("answer");
                                        putData(ref, url, Name);

                                    } break;
                                    case "Biology": {
                                        CollectionReference ref = db.collection("Class6MH").document("AF")
                                                .collection("Biology").document("again").collection("answer");
                                        putData(ref, url, Name);

                                    }  break;
                                    case "Chemistry": {
                                        CollectionReference ref = db.collection("Class6MH").document("AF")
                                                .collection("Chemistry").document("again").collection("answer");
                                        putData(ref, url, Name);

                                    } break;
                                }
                            } break;

                        case "Class7":
                            if (b.contains(sPattern)){
                                switch (subject) {
                                    case "English": {
                                        CollectionReference ref = db.collection("Class7CBSE").document("A")
                                                .collection("Eng").document("again").collection("answer");
                                        putData(ref, url, Name);

                                    } break;
                                    case "History": {
                                        CollectionReference ref = db.collection("Class7CBSE").document("A")
                                                .collection("History").document("again").collection("answer");
                                        putData(ref, url, Name);

                                    } break;
                                    case "Geography": {
                                        CollectionReference ref = db.collection("Class7CBSE").document("A")
                                                .collection("Geography").document("again").collection("answer");
                                        putData(ref, url, Name);

                                    } break;
                                    case "Mathematics": {
                                        CollectionReference ref = db.collection("Class7CBSE").document("A")
                                                .collection("Mathematics").document("again").collection("answer");
                                        putData(ref, url, Name);

                                    } break;
                                    case "Science": {
                                        CollectionReference ref = db.collection("Class7CBSE").document("A")
                                                .collection("Science").document("again").collection("answer");
                                        putData(ref, url, Name);

                                    }  break;
                                    case "Physics": {
                                        CollectionReference ref = db.collection("Class7CBSE").document("A")
                                                .collection("Physics").document("again").collection("answer");
                                        putData(ref, url, Name);

                                    } break;
                                    case "Biology": {
                                        CollectionReference ref = db.collection("Class7CBSE").document("A")
                                                .collection("Biology").document("again").collection("answer");
                                        putData(ref, url, Name);

                                    } break;
                                    case "Chemistry": {
                                        CollectionReference ref = db.collection("Class7CBSE").document("A")
                                                .collection("Chemistry").document("again").collection("answer");
                                        putData(ref, url, Name);

                                    }  break;
                                }
                            }
                            else {
                                switch (subject) {
                                    case "English": {
                                        CollectionReference ref = db.collection("Class7MH").document("AG")
                                                .collection("Eng").document("again").collection("answer");
                                        putData(ref, url, Name);

                                    } break;
                                    case "History": {
                                        CollectionReference ref = db.collection("Class7MH").document("AG")
                                                .collection("History").document("again").collection("answer");
                                        putData(ref, url, Name);

                                    }break;
                                    case "Geography": {
                                        CollectionReference ref = db.collection("Class7MH").document("AG")
                                                .collection("Geography").document("again").collection("answer");
                                        putData(ref, url, Name);

                                    } break;
                                    case "Mathematics": {
                                        CollectionReference ref = db.collection("Class7MH").document("AG")
                                                .collection("Mathematics").document("again").collection("answer");
                                        putData(ref, url, Name);

                                    }  break;
                                    case "Science": {
                                        CollectionReference ref = db.collection("Class7MH").document("AG")
                                                .collection("Science").document("again").collection("answer");
                                        putData(ref, url, Name);

                                    }  break;
                                    case "Physics": {
                                        CollectionReference ref = db.collection("Class7MH").document("AG")
                                                .collection("Physics").document("again").collection("answer");
                                        putData(ref, url, Name);

                                    }  break;
                                    case "Biology": {
                                        CollectionReference ref = db.collection("Class7MH").document("AG")
                                                .collection("Biology").document("again").collection("answer");
                                        putData(ref, url, Name);

                                    } break;
                                    case "Chemistry": {
                                        CollectionReference ref = db.collection("Class7MH").document("AG")
                                                .collection("Chemistry").document("again").collection("answer");
                                        putData(ref, url, Name);

                                    } break;
                                }
                            }break;

                        case "Class8":
                            if (b.contains(sPattern)){
                                switch (subject) {
                                    case "English": {
                                        CollectionReference ref = db.collection("Class8CBSE").document("B")
                                                .collection("Eng").document("again").collection("answer");
                                        putData(ref, url, Name);

                                    }break;
                                    case "History": {
                                        CollectionReference ref = db.collection("Class8CBSE").document("B")
                                                .collection("History").document("again").collection("answer");
                                        putData(ref, url, Name);

                                    }break;
                                    case "Geography": {
                                        CollectionReference ref = db.collection("Class8CBSE").document("B")
                                                .collection("Geography").document("again").collection("answer");
                                        putData(ref, url, Name);

                                    }break;
                                    case "Mathematics": {
                                        CollectionReference ref = db.collection("Class8CBSE").document("B")
                                                .collection("Mathematics").document("again").collection("answer");
                                        putData(ref, url, Name);

                                    }break;
                                    case "Science": {
                                        CollectionReference ref = db.collection("Class8CBSE").document("B")
                                                .collection("Science").document("again").collection("answer");
                                        putData(ref, url, Name);

                                    } break;
                                    case "Physics": {
                                        CollectionReference ref = db.collection("Class8CBSE").document("B")
                                                .collection("Physics").document("again").collection("answer");
                                        putData(ref, url, Name);

                                    }break;
                                    case "Biology": {
                                        CollectionReference ref = db.collection("Class8CBSE").document("B")
                                                .collection("Biology").document("again").collection("answer");
                                        putData(ref, url, Name);

                                    }  break;
                                    case "Chemistry": {
                                        CollectionReference ref = db.collection("Class8CBSE").document("B")
                                                .collection("Chemistry").document("again").collection("answer");
                                        putData(ref, url, Name);

                                    }  break;
                                }
                            }
                            else {
                                switch (subject) {
                                    case "English": {
                                        CollectionReference ref = db.collection("Class8MH").document("AH")
                                                .collection("Eng").document("again").collection("answer");
                                        putData(ref, url, Name);

                                    }  break;
                                    case "History": {
                                        CollectionReference ref = db.collection("Class8MH").document("AH")
                                                .collection("History").document("again").collection("answer");
                                        putData(ref, url, Name);

                                    }   break;
                                    case "Geography": {
                                        CollectionReference ref = db.collection("Class8MH").document("AH")
                                                .collection("Geography").document("again").collection("answer");
                                        putData(ref, url, Name);

                                    } break;
                                    case "Mathematics": {
                                        CollectionReference ref = db.collection("Class8MH").document("AH")
                                                .collection("Mathematics").document("again").collection("answer");
                                        putData(ref, url, Name);

                                    } break;
                                    case "Science": {
                                        CollectionReference ref = db.collection("Class8MH").document("AH")
                                                .collection("Science").document("again").collection("answer");
                                        putData(ref, url, Name);

                                    }  break;
                                    case "Physics": {
                                        CollectionReference ref = db.collection("Class8MH").document("AH")
                                                .collection("Physics").document("again").collection("answer");
                                        putData(ref, url, Name);

                                    }  break;
                                    case "Biology": {
                                        CollectionReference ref = db.collection("Class8MH").document("AH")
                                                .collection("Biology").document("again").collection("answer");
                                        putData(ref, url, Name);

                                    } break;
                                    case "Chemistry": {
                                        CollectionReference ref = db.collection("Class8MH").document("AH")
                                                .collection("Chemistry").document("again").collection("answer");
                                        putData(ref, url, Name);

                                    }break;
                                }
                            } break;

                        case "Class9":
                            if (b.contains(sPattern)){
                                switch (subject) {
                                    case "English": {
                                        CollectionReference ref = db.collection("Class9CBSE").document("C")
                                                .collection("Eng").document("again").collection("answer");
                                        putData(ref, url, Name);

                                    } break;
                                    case "History": {
                                        CollectionReference ref = db.collection("Class9CBSE").document("C")
                                                .collection("History").document("again").collection("answer");
                                        putData(ref, url, Name);

                                    }  break;
                                    case "Geography": {
                                        CollectionReference ref = db.collection("Class9CBSE").document("C")
                                                .collection("Geography").document("again").collection("answer");
                                        putData(ref, url, Name);

                                    }  break;
                                    case "Mathematics": {
                                        CollectionReference ref = db.collection("Class9CBSE").document("C")
                                                .collection("Mathematics").document("again").collection("answer");
                                        putData(ref, url, Name);

                                    }  break;
                                    case "Science": {
                                        CollectionReference ref = db.collection("Class9CBSE").document("C")
                                                .collection("Science").document("again").collection("answer");
                                        putData(ref, url, Name);

                                    } break;
                                    case "Physics": {
                                        CollectionReference ref = db.collection("Class9CBSE").document("C")
                                                .collection("Physics").document("again").collection("answer");
                                        putData(ref, url, Name);

                                    }  break;
                                    case "Biology": {
                                        CollectionReference ref = db.collection("Class9CBSE").document("C")
                                                .collection("Biology").document("again").collection("answer");
                                        putData(ref, url, Name);

                                    } break;
                                    case "Chemistry": {
                                        CollectionReference ref = db.collection("Class9CBSE").document("C")
                                                .collection("Chemistry").document("again").collection("answer");
                                        putData(ref, url, Name);

                                    } break;
                                }
                            }
                            else {
                                switch (subject) {
                                    case "English": {
                                        CollectionReference ref = db.collection("Class9MH").document("AI")
                                                .collection("Eng").document("again").collection("answer");
                                        putData(ref, url, Name);

                                    } break;
                                    case "History": {
                                        CollectionReference ref = db.collection("Class9MH").document("AI")
                                                .collection("History").document("again").collection("answer");
                                        putData(ref, url, Name);

                                    } break;
                                    case "Geography": {
                                        CollectionReference ref = db.collection("Class9MH").document("AI")
                                                .collection("Geography").document("again").collection("answer");
                                        putData(ref, url, Name);

                                    }break;
                                    case "Mathematics": {
                                        CollectionReference ref = db.collection("Class9MH").document("AI")
                                                .collection("Mathematics").document("again").collection("answer");
                                        putData(ref, url, Name);

                                    }   break;
                                    case "Science": {
                                        CollectionReference ref = db.collection("Class9MH").document("AI")
                                                .collection("Science").document("again").collection("answer");
                                        putData(ref, url, Name);

                                    } break;
                                    case "Physics": {
                                        CollectionReference ref = db.collection("Class9MH").document("AI")
                                                .collection("Physics").document("again").collection("answer");
                                        putData(ref, url, Name);

                                    }  break;
                                    case "Biology": {
                                        CollectionReference ref = db.collection("Class9MH").document("AI")
                                                .collection("Biology").document("again").collection("answer");
                                        putData(ref, url, Name);

                                    } break;
                                    case "Chemistry": {
                                        CollectionReference ref = db.collection("Class9MH").document("AI")
                                                .collection("Chemistry").document("again").collection("answer");
                                        putData(ref, url, Name);

                                    }break;
                                }
                            } break;

                        case "Class10":
                            if (b.contains(sPattern)){
                                switch (subject) {
                                    case "English": {
                                        CollectionReference ref = db.collection("Class10CBSE").document("D")
                                                .collection("Eng").document("again").collection("answer");
                                        putData(ref, url, Name);

                                    } break;
                                    case "History": {
                                        CollectionReference ref = db.collection("Class10CBSE").document("D")
                                                .collection("History").document("again").collection("answer");
                                        putData(ref, url, Name);

                                    } break;
                                    case "Geography": {
                                        CollectionReference ref = db.collection("Class10CBSE").document("D")
                                                .collection("Geography").document("again").collection("answer");
                                        putData(ref, url, Name);

                                    }  break;
                                    case "Mathematics": {
                                        CollectionReference ref = db.collection("Class10CBSE").document("D")
                                                .collection("Mathematics").document("again").collection("answer");
                                        putData(ref, url, Name);

                                    } break;
                                    case "Science": {
                                        CollectionReference ref = db.collection("Class10CBSE").document("D")
                                                .collection("Science").document("again").collection("answer");
                                        putData(ref, url, Name);

                                    }break;
                                    case "Physics": {
                                        CollectionReference ref = db.collection("Class10CBSE").document("D")
                                                .collection("Physics").document("again").collection("answer");
                                        putData(ref, url, Name);

                                    } break;
                                    case "Biology": {
                                        CollectionReference ref = db.collection("Class10CBSE").document("D")
                                                .collection("Biology").document("again").collection("answer");
                                        putData(ref, url, Name);

                                    } break;
                                    case "Chemistry": {
                                        CollectionReference ref = db.collection("Class10CBSE").document("D")
                                                .collection("Chemistry").document("again").collection("answer");
                                        putData(ref, url, Name);

                                    } break;
                                }
                            }
                            else {
                                switch (subject) {
                                    case "English": {
                                        CollectionReference ref = db.collection("Class10MH").document("AJ")
                                                .collection("Eng").document("again").collection("answer");
                                        putData(ref, url, Name);

                                    } break;
                                    case "History": {
                                        CollectionReference ref = db.collection("Class10MH").document("AJ")
                                                .collection("History").document("again").collection("answer");
                                        putData(ref, url, Name);

                                    }break;
                                    case "Geography": {
                                        CollectionReference ref = db.collection("Class9MH").document("AJ")
                                                .collection("Geography").document("again").collection("answer");
                                        putData(ref, url, Name);

                                    }  break;
                                    case "Mathematics": {
                                        CollectionReference ref = db.collection("Class9MH").document("AJ")
                                                .collection("Mathematics").document("again").collection("answer");
                                        putData(ref, url, Name);

                                    } break;
                                    case "Science": {
                                        CollectionReference ref = db.collection("Class9MH").document("AJ")
                                                .collection("Science").document("again").collection("answer");
                                        putData(ref, url, Name);

                                    } break;
                                    case "Physics": {
                                        CollectionReference ref = db.collection("Class9MH").document("AJ")
                                                .collection("Physics").document("again").collection("answer");
                                        putData(ref, url, Name);

                                    } break;
                                    case "Biology": {
                                        CollectionReference ref = db.collection("Class9MH").document("AJ")
                                                .collection("Biology").document("again").collection("answer");
                                        putData(ref, url, Name);

                                    } break;
                                    case "Chemistry": {
                                        CollectionReference ref = db.collection("Class9MH").document("AJ")
                                                .collection("Chemistry").document("again").collection("answer");
                                        putData(ref, url, Name);

                                    }break;
                                }
                            } break;

                        case "Class11":
                            if (b.contains(sPattern)){
                                switch (subject) {
                                    case "English": {
                                        CollectionReference ref = db.collection("Class11CBSE").document("E")
                                                .collection("Eng").document("again").collection("answer");
                                        putData(ref, url, Name);

                                    }    break;
                                    case "History": {
                                        CollectionReference ref = db.collection("Class11CBSE").document("E")
                                                .collection("History").document("again").collection("answer");
                                        putData(ref, url, Name);

                                    } break;
                                    case "Geography": {
                                        CollectionReference ref = db.collection("Class11CBSE").document("E")
                                                .collection("Geography").document("again").collection("answer");
                                        putData(ref, url, Name);

                                    }  break;
                                    case "Mathematics": {
                                        CollectionReference ref = db.collection("Class11CBSE").document("E")
                                                .collection("Mathematics").document("again").collection("answer");
                                        putData(ref, url, Name);

                                    } break;
                                    case "Science": {
                                        CollectionReference ref = db.collection("Class11CBSE").document("E")
                                                .collection("Science").document("again").collection("answer");
                                        putData(ref, url, Name);

                                    } break;
                                    case "Physics": {
                                        CollectionReference ref = db.collection("Class11CBSE").document("E")
                                                .collection("Physics").document("again").collection("answer");
                                        putData(ref, url, Name);

                                    }break;
                                    case "Biology": {
                                        CollectionReference ref = db.collection("Class11CBSE").document("E")
                                                .collection("Biology").document("again").collection("answer");
                                        putData(ref, url, Name);

                                    } break;
                                    case "Chemistry": {
                                        CollectionReference ref = db.collection("Class11CBSE").document("E")
                                                .collection("Chemistry").document("again").collection("answer");
                                        putData(ref, url, Name);

                                    }break;
                                }
                            }
                            else {
                                switch (subject) {
                                    case "English": {
                                        CollectionReference ref = db.collection("Class11MH").document("AK")
                                                .collection("Eng").document("again").collection("answer");
                                        putData(ref, url, Name);

                                    } break;
                                    case "History": {
                                        CollectionReference ref = db.collection("Class11MH").document("AK")
                                                .collection("History").document("again").collection("answer");
                                        putData(ref, url, Name);

                                    }  break;
                                    case "Geography": {
                                        CollectionReference ref = db.collection("Class9MH").document("AK")
                                                .collection("Geography").document("again").collection("answer");
                                        putData(ref, url, Name);

                                    } break;
                                    case "Mathematics": {
                                        CollectionReference ref = db.collection("Class9MH").document("AK")
                                                .collection("Mathematics").document("again").collection("answer");
                                        putData(ref, url, Name);

                                    } break;
                                    case "Science": {
                                        CollectionReference ref = db.collection("Class9MH").document("AK")
                                                .collection("Science").document("again").collection("answer");
                                        putData(ref, url, Name);

                                    } break;
                                    case "Physics": {
                                        CollectionReference ref = db.collection("Class9MH").document("AK")
                                                .collection("Physics").document("again").collection("answer");
                                        putData(ref, url, Name);

                                    } break;
                                    case "Biology": {
                                        CollectionReference ref = db.collection("Class9MH").document("AK")
                                                .collection("Biology").document("again").collection("answer");
                                        putData(ref, url, Name);

                                    }break;
                                    case "Chemistry": {
                                        CollectionReference ref = db.collection("Class9MH").document("AK")
                                                .collection("Chemistry").document("again").collection("answer");
                                        putData(ref, url, Name);

                                    } break;
                                }
                            } break;

                        case "Class12":
                            if (b.contains(sPattern)){
                                switch (subject) {
                                    case "English": {
                                        CollectionReference ref = db.collection("Class12CBSE").document("F")
                                                .collection("Eng").document("again").collection("answer");
                                        putData(ref, url, Name);

                                    } break;
                                    case "History": {
                                        CollectionReference ref = db.collection("Class12CBSE").document("F")
                                                .collection("History").document("again").collection("answer");
                                        putData(ref, url, Name);

                                    }  break;
                                    case "Geography": {
                                        CollectionReference ref = db.collection("Class12CBSE").document("F")
                                                .collection("Geography").document("again").collection("answer");
                                        putData(ref, url, Name);

                                    } break;
                                    case "Mathematics": {
                                        CollectionReference ref = db.collection("Class12CBSE").document("F")
                                                .collection("Mathematics").document("again").collection("answer");
                                        putData(ref, url, Name);

                                    }  break;
                                    case "Science": {
                                        CollectionReference ref = db.collection("Class12CBSE").document("F")
                                                .collection("Science").document("again").collection("answer");
                                        putData(ref, url, Name);

                                    }  break;
                                    case "Physics": {
                                        CollectionReference ref = db.collection("Class12CBSE").document("F")
                                                .collection("Physics").document("again").collection("answer");
                                        putData(ref, url, Name);

                                    } break;
                                    case "Biology": {
                                        CollectionReference ref = db.collection("Class12CBSE").document("F")
                                                .collection("Biology").document("again").collection("answer");
                                        putData(ref, url, Name);

                                    } break;
                                    case "Chemistry": {
                                        CollectionReference ref = db.collection("Class12CBSE").document("F")
                                                .collection("Chemistry").document("again").collection("answer");
                                        putData(ref, url, Name);

                                    } break;
                                }
                            }
                            else {
                                switch (subject) {
                                    case "English": {
                                        CollectionReference ref = db.collection("Class12MH").document("AL")
                                                .collection("Eng").document("again").collection("answer");
                                        putData(ref, url, Name);

                                    }break;
                                    case "History": {
                                        CollectionReference ref = db.collection("Class12MH").document("AL")
                                                .collection("History").document("again").collection("answer");
                                        putData(ref, url, Name);

                                    } break;
                                    case "Geography": {
                                        CollectionReference ref = db.collection("Class9MH").document("AL")
                                                .collection("Geography").document("again").collection("answer");
                                        putData(ref, url, Name);

                                    }   break;
                                    case "Mathematics": {
                                        CollectionReference ref = db.collection("Class9MH").document("AL")
                                                .collection("Mathematics").document("again").collection("answer");
                                        putData(ref, url, Name);

                                    }  break;
                                    case "Science": {
                                        CollectionReference ref = db.collection("Class9MH").document("AL")
                                                .collection("Science").document("again").collection("answer");
                                        putData(ref, url, Name);

                                    }  break;
                                    case "Physics": {
                                        CollectionReference ref = db.collection("Class9MH").document("AL")
                                                .collection("Physics").document("again").collection("answer");
                                        putData(ref, url, Name);

                                    }  break;
                                    case "Biology": {
                                        CollectionReference ref = db.collection("Class9MH").document("AL")
                                                .collection("Biology").document("again").collection("answer");
                                        putData(ref, url, Name);

                                    } break;
                                    case "Chemistry": {
                                        CollectionReference ref = db.collection("Class9MH").document("AL")
                                                .collection("Chemistry").document("again").collection("answer");
                                        putData(ref, url, Name);

                                    }  break;
                                }
                            }
                            break;
                    }
                }

            }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }


        });
}



    private void putData(CollectionReference ref, String url, String Name) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("AnswerSheet", url);
        hashMap.put("AnswerName", Name);
        //   hashMap.put("pdf", file_namepdf.getText().toString());

        ref.add(hashMap)
                //        .update(hashMap, (url))
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        progressDialog.dismiss();
                        //      textView.setText("Image Uploaded Successfully");
                        Toast.makeText(Tests.this, "File upload successful", Toast.LENGTH_SHORT).show();
                        //      send.setVisibility(View.GONE);
                        ImageList.clear();
                        choose.setVisibility(View.GONE);
                        upload.setVisibility(View.VISIBLE);
                    }
                });
    }


}