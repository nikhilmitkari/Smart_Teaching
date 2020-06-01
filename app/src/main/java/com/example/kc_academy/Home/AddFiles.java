package com.example.kc_academy.Home;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.kc_academy.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.ArrayList;
import java.util.HashMap;

public class AddFiles extends AppCompatActivity {
    private static final int PICK_IMG = 1;
    private ArrayList<Uri> ImageList = new ArrayList<Uri>();
    private int uploads = 0;

    Button choosepdf, sendpdf;
    Button choosevdo, sendvdo;
    Button choosetst, sendtst;
    EditText file_namepdf, filevideo, fileTest;
    FirebaseUser user1;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference myref;
    FirebaseFirestore db;
    FirebaseAuth firebaseAuth;
    TextView tvblinking;
Spinner Class, pattern, subject;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_files);
        tvblinking = findViewById(R.id.usage);
        blink();

        Class = findViewById(R.id.spinner1);
        pattern = findViewById(R.id.spinner2);
        subject= findViewById(R.id.spinner3);

        db = FirebaseFirestore.getInstance();
        //    databaseReference = FirebaseDatabase.getInstance().getReference().child("User_one");
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Uploading ..........");

        choosepdf = findViewById(R.id.choosep);
        sendpdf = findViewById(R.id.uploadp);
        file_namepdf = findViewById(R.id.etpdf);

        choosevdo = findViewById(R.id.choose3);
        sendvdo = findViewById(R.id.upload2);
        filevideo = findViewById(R.id.etvideo);

        choosetst = findViewById(R.id.choose4);
        sendtst = findViewById(R.id.upload3);
        fileTest = findViewById(R.id.etTest);
    }

    public void choose(View view) {
        //we will pick images
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("*/*");
        //     intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
        startActivityForResult(intent, PICK_IMG);
        sendpdf.setVisibility(View.GONE);
        sendvdo.setVisibility(View.GONE);
        sendtst.setVisibility(View.GONE);

    }

    @SuppressLint("SetTextI18n")
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMG) {
            if (resultCode == RESULT_OK) {

                Uri imageuri = data.getData();
                ImageList.add(imageuri);
                Toast.makeText(AddFiles.this, "You have selected " + ImageList.size() + " File", Toast.LENGTH_SHORT).show();
                sendpdf.setVisibility(View.VISIBLE);
                choosepdf.setVisibility(View.GONE);

                sendvdo.setVisibility(View.VISIBLE);
                choosevdo.setVisibility(View.GONE);

                sendtst.setVisibility(View.VISIBLE);
                choosetst.setVisibility(View.GONE);
                //    }
            }
        }
    }

    @SuppressLint("SetTextI18n")
    public void upload(View view) {
        //      textView.setText("Please Wait ... If Uploading takes Too much time please the button again ");
        progressDialog.show();
        final StorageReference ImageFolder = FirebaseStorage.getInstance().getReference().child("Study Materials");
        for (uploads = 0; uploads < ImageList.size(); uploads++) {
            Uri Image = ImageList.get(uploads);
            final StorageReference imagename = ImageFolder.child("pdf/" + Image.getLastPathSegment());
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

    @SuppressLint("SetTextI18n")
    public void upload2(View view) {
        //      textView.setText("Please Wait ... If Uploading takes Too much time please the button again ");
        progressDialog.show();
        final StorageReference ImageFolder = FirebaseStorage.getInstance().getReference().child("Study Materials");
        for (uploads = 0; uploads < ImageList.size(); uploads++) {
            Uri Image = ImageList.get(uploads);
            final StorageReference imagename = ImageFolder.child("videos/" + Image.getLastPathSegment());
            imagename.putFile(ImageList.get(uploads)).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    imagename.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {

                            String url = String.valueOf(uri);
                           SendLink2(url);

                        }
                    });
                }
            });
        }
    }

    private void SendLink2(String url) {
        String a = "Class1 Class2 Class3 Class4 Class5 Class6 Class7 Class8 Class9 Class10 Class11 Class12";
        final String b = "CBSE";

        String classT = Class.getSelectedItem().toString();
        String patternT = pattern.getSelectedItem().toString();
        String Tsubject = subject.getSelectedItem().toString();
        Integer No = 2;

        switch (classT){
            case "Class1":
                if (b.contains(patternT)){
                    switch (Tsubject) {
                        case "English": {
                            CollectionReference ref = db.collection("Class1CBSE").document("CK6aEA860T6TIHucpEXw")
                                    .collection("Eng").document("PePdIxgaBgIy5p677HY1").collection("video");
                            putData(ref, url, No);
                        }
                        break;

                        case "History": {
                            CollectionReference ref = db.collection("Class1CBSE").document("CK6aEA860T6TIHucpEXw")
                                    .collection("History").document("L7k3y6ZZmI2iO8QFSvs9").collection("video");
                            putData(ref, url, No);
                        }
                        break;

                        case "Geography": {
                            CollectionReference ref = db.collection("Class1CBSE").document("CK6aEA860T6TIHucpEXw")
                                    .collection("Geography").document("XtQkhD23tifMQ8qdchWr").collection("video");
                            putData(ref, url, No);
                        }
                        break;

                        case "Mathematics": {
                            CollectionReference ref = db.collection("Class1CBSE").document("CK6aEA860T6TIHucpEXw")
                                    .collection("Mathematics").document("NYCjx8n8qIqtJtTDNJGt").collection("video");
                            putData(ref, url, No);
                        }
                        break;

                        case "Science": {
                            CollectionReference ref = db.collection("Class1CBSE").document("CK6aEA860T6TIHucpEXw")
                                    .collection("Science").document("freMDiIPbqstwDgE7Zyd").collection("video");
                            putData(ref, url, No);
                        }
                        break;

                        case "Physics": {
                            CollectionReference ref = db.collection("Class1CBSE").document("CK6aEA860T6TIHucpEXw")
                                    .collection("Physics").document("307IuL9VYw7Esmi8Fl7m").collection("video");
                            putData(ref, url, No);
                        }
                        break;

                        case "Biology": {
                            CollectionReference ref = db.collection("Class1CBSE").document("CK6aEA860T6TIHucpEXw")
                                    .collection("Biology").document("1Dm0FouhEU5EW2bkzyuD").collection("video");
                            putData(ref, url, No);
                        }
                        break;

                        case "Chemistry": {
                            CollectionReference ref = db.collection("Class1CBSE").document("CK6aEA860T6TIHucpEXw")
                                    .collection("Chemistry").document("zKV4PwDP2eAlMSBkXJJj").collection("video");
                            putData(ref, url, No);
                        }
                        break;
                    }
                }
                else {
                    switch (Tsubject) {
                        case "English": {
                            CollectionReference ref = db.collection("Class1MH").document("AA")
                                    .collection("Eng").document("again").collection("video");
                            putData(ref, url, No);
                        }
                        break;

                        case "History": {
                            CollectionReference ref = db.collection("Class1MH").document("AA")
                                    .collection("History").document("again").collection("video");
                            putData(ref, url, No);
                        }
                        break;

                        case "Geography": {
                            CollectionReference ref = db.collection("Class1MH").document("AA")
                                    .collection("Geography").document("again").collection("video");
                            putData(ref, url, No);
                        }

                        case "Mathematics": {
                            CollectionReference ref = db.collection("Class1MH").document("AA")
                                    .collection("Mathematics").document("again").collection("video");
                            putData(ref, url, No);
                        }
                        break;

                        case "Science": {
                            CollectionReference ref = db.collection("Class1MH").document("AA")
                                    .collection("Science").document("again").collection("video");
                            putData(ref, url, No);
                        }
                        break;

                        case "Physics": {
                            CollectionReference ref = db.collection("Class1MH").document("AA")
                                    .collection("Physics").document("again").collection("video");
                            putData(ref, url, No);
                        }
                        break;

                        case "Biology": {
                            CollectionReference ref = db.collection("Class1MH").document("AA")
                                    .collection("Biology").document("again").collection("video");
                            putData(ref, url, No);
                        }
                        break;

                        case "Chemistry": {
                            CollectionReference ref = db.collection("Class1MH").document("AA")
                                    .collection("Chemistry").document("again").collection("video");
                            putData(ref, url, No);
                        }
                        break;
                    }
                }
                break;

            case "Class2":
                if (b.contains(patternT)){
                    switch (Tsubject) {
                        case "English": {
                            CollectionReference ref = db.collection("Class2CBSE").document("gPOVSQW8gCKyO1RVCRQo")
                                    .collection("Eng").document("again").collection("video");
                            putData(ref, url, No);
                        }
                        break;

                        case "History": {
                            CollectionReference ref = db.collection("Class2CBSE").document("gPOVSQW8gCKyO1RVCRQo")
                                    .collection("History").document("again").collection("video");
                            putData(ref, url, No);
                        }
                        case "Geography": {
                            CollectionReference ref = db.collection("Class2CBSE").document("gPOVSQW8gCKyO1RVCRQo")
                                    .collection("Geography").document("again").collection("video");
                            putData(ref, url, No);
                        }
                        break;

                        case "Mathematics": {
                            CollectionReference ref = db.collection("Class2CBSE").document("gPOVSQW8gCKyO1RVCRQo")
                                    .collection("Mathematics").document("again").collection("video");
                            putData(ref, url, No);
                        }
                        break;

                        case "Science": {
                            CollectionReference ref = db.collection("Class2CBSE").document("gPOVSQW8gCKyO1RVCRQo")
                                    .collection("Science").document("again").collection("video");
                            putData(ref, url, No);
                        }
                        break;

                        case "Physics": {
                            CollectionReference ref = db.collection("Class2CBSE").document("gPOVSQW8gCKyO1RVCRQo")
                                    .collection("Physics").document("again").collection("video");
                            putData(ref, url, No);
                        }
                        break;

                        case "Biology": {
                            CollectionReference ref = db.collection("Class2CBSE").document("gPOVSQW8gCKyO1RVCRQo")
                                    .collection("Biology").document("again").collection("video");
                            putData(ref, url, No);
                        }
                        break;

                        case "Chemistry": {
                            CollectionReference ref = db.collection("Class2CBSE").document("gPOVSQW8gCKyO1RVCRQo")
                                    .collection("Chemistry").document("again").collection("video");
                            putData(ref, url, No);
                        }
                        break;

                    }
                }
                else {
                    switch (Tsubject) {
                        case "English": {
                            CollectionReference ref = db.collection("Class2MH").document("AB")
                                    .collection("Eng").document("again").collection("video");
                            putData(ref, url, No);
                        }
                        break;

                        case "History": {
                            CollectionReference ref = db.collection("Class2MH").document("AB")
                                    .collection("History").document("again").collection("video");
                            putData(ref, url, No);
                        }
                        case "Geography": {
                            CollectionReference ref = db.collection("Class2MH").document("AB")
                                    .collection("Geography").document("again").collection("video");
                            putData(ref, url, No);
                        }
                        break;

                        case "Mathematics": {
                            CollectionReference ref = db.collection("Class2MH").document("AB")
                                    .collection("Mathematics").document("again").collection("video");
                            putData(ref, url, No);
                        }
                        break;

                        case "Science": {
                            CollectionReference ref = db.collection("Class2MH").document("AB")
                                    .collection("Science").document("again").collection("video");
                            putData(ref, url, No);
                        }
                        break;

                        case "Physics": {
                            CollectionReference ref = db.collection("Class2MH").document("AB")
                                    .collection("Physics").document("again").collection("video");
                            putData(ref, url, No);
                        }
                        break;

                        case "Biology": {
                            CollectionReference ref = db.collection("Class2MH").document("AB")
                                    .collection("Biology").document("again").collection("video");
                            putData(ref, url, No);
                        }
                        break;

                        case "Chemistry": {
                            CollectionReference ref = db.collection("Class2MH").document("AB")
                                    .collection("Chemistry").document("again").collection("video");
                            putData(ref, url, No);
                        }
                        break;

                    }
                }
                break;

            case "Class3":
                if (b.contains(patternT)){
                    switch (Tsubject) {
                        case "English": {
                            CollectionReference ref = db.collection("Class3CBSE").document("GfdFx0V3BaxIVfcT8VnX")
                                    .collection("Eng").document("again").collection("video");
                            putData(ref, url, No);
                        }
                        break;

                        case "History": {
                            CollectionReference ref = db.collection("Class3CBSE").document("GfdFx0V3BaxIVfcT8VnX")
                                    .collection("History").document("again").collection("video");
                            putData(ref, url, No);
                        }
                        case "Geography": {
                            CollectionReference ref = db.collection("Class3CBSE").document("GfdFx0V3BaxIVfcT8VnX")
                                    .collection("Geography").document("again").collection("video");
                            putData(ref, url, No);
                        }
                        break;

                        case "Mathematics": {
                            CollectionReference ref = db.collection("Class3CBSE").document("GfdFx0V3BaxIVfcT8VnX")
                                    .collection("Mathematics").document("again").collection("video");
                            putData(ref, url, No);
                        }
                        break;

                        case "Science": {
                            CollectionReference ref = db.collection("Class3CBSE").document("GfdFx0V3BaxIVfcT8VnX")
                                    .collection("Science").document("again").collection("video");
                            putData(ref, url, No);
                        }
                        break;

                        case "Physics": {
                            CollectionReference ref = db.collection("Class3CBSE").document("GfdFx0V3BaxIVfcT8VnX")
                                    .collection("Physics").document("again").collection("video");
                            putData(ref, url, No);
                        }
                        break;

                        case "Biology": {
                            CollectionReference ref = db.collection("Class3CBSE").document("GfdFx0V3BaxIVfcT8VnX")
                                    .collection("Biology").document("again").collection("video");
                            putData(ref, url, No);
                        }
                        break;

                        case "Chemistry": {
                            CollectionReference ref = db.collection("Class3CBSE").document("GfdFx0V3BaxIVfcT8VnX")
                                    .collection("Chemistry").document("again").collection("video");
                            putData(ref, url, No);
                        }
                        break;

                    }
                }
                else {
                    switch (Tsubject) {
                        case "English": {
                            CollectionReference ref = db.collection("Class3MH").document("AC")
                                    .collection("Eng").document("again").collection("video");
                            putData(ref, url, No);
                        }
                        break;

                        case "History": {
                            CollectionReference ref = db.collection("Class3MH").document("AC")
                                    .collection("History").document("again").collection("video");
                            putData(ref, url, No);
                        }
                        case "Geography": {
                            CollectionReference ref = db.collection("Class3MH").document("AC")
                                    .collection("Geography").document("again").collection("video");
                            putData(ref, url, No);
                        }
                        break;

                        case "Mathematics": {
                            CollectionReference ref = db.collection("Class3MH").document("AC")
                                    .collection("Mathematics").document("again").collection("video");
                            putData(ref, url, No);
                        }
                        break;

                        case "Science": {
                            CollectionReference ref = db.collection("Class3MH").document("AC")
                                    .collection("Science").document("again").collection("video");
                            putData(ref, url, No);
                        }
                        break;

                        case "Physics": {
                            CollectionReference ref = db.collection("Class3MH").document("AC")
                                    .collection("Physics").document("again").collection("video");
                            putData(ref, url, No);
                        }
                        break;

                        case "Biology": {
                            CollectionReference ref = db.collection("Class3MH").document("AC")
                                    .collection("Biology").document("again").collection("video");
                            putData(ref, url, No);
                        }
                        break;

                        case "Chemistry": {
                            CollectionReference ref = db.collection("Class3MH").document("AC")
                                    .collection("Chemistry").document("again").collection("video");
                            putData(ref, url, No);
                        }
                        break;

                    }
                }
                break;

            case "Class4":
                if (b.contains(patternT)){
                    switch (Tsubject) {
                        case "English": {
                            CollectionReference ref = db.collection("Class4CBSE").document("jvcOWa6MXnTBVOAh6DYS")
                                    .collection("Eng").document("again").collection("video");
                            putData(ref, url, No);
                        }
                        case "History": {
                            CollectionReference ref = db.collection("Class4CBSE").document("jvcOWa6MXnTBVOAh6DYS")
                                    .collection("History").document("again").collection("video");
                            putData(ref, url, No);
                        }
                        break;

                        case "Geography": {
                            CollectionReference ref = db.collection("Class4CBSE").document("jvcOWa6MXnTBVOAh6DYS")
                                    .collection("Geography").document("again").collection("video");
                            putData(ref, url, No);
                        }
                        break;

                        case "Mathematics": {
                            CollectionReference ref = db.collection("Class4CBSE").document("jvcOWa6MXnTBVOAh6DYS")
                                    .collection("Mathematics").document("again").collection("video");
                            putData(ref, url, No);
                        }
                        break;

                        case "Science": {
                            CollectionReference ref = db.collection("Class4CBSE").document("jvcOWa6MXnTBVOAh6DYS")
                                    .collection("Science").document("again").collection("video");
                            putData(ref, url, No);
                        }
                        break;

                        case "Physics": {
                            CollectionReference ref = db.collection("Class4CBSE").document("GfdFx0V3BaxIVfcT8VnX")
                                    .collection("Physics").document("again").collection("video");
                            putData(ref, url, No);
                        }
                        break;

                        case "Biology": {
                            CollectionReference ref = db.collection("Class4CBSE").document("GfdFx0V3BaxIVfcT8VnX")
                                    .collection("Biology").document("again").collection("video");
                            putData(ref, url, No);
                        }
                        break;

                        case "Chemistry": {
                            CollectionReference ref = db.collection("Class4CBSE").document("GfdFx0V3BaxIVfcT8VnX")
                                    .collection("Chemistry").document("again").collection("video");
                            putData(ref, url, No);
                        }
                        break;
                    }
                }
                else {
                    switch (Tsubject) {
                        case "English": {
                            CollectionReference ref = db.collection("Class4MH").document("AD")
                                    .collection("Eng").document("again").collection("video");
                            putData(ref, url, No);
                        }
                        break;

                        case "History": {
                            CollectionReference ref = db.collection("Class4MH").document("AD")
                                    .collection("History").document("again").collection("video");
                            putData(ref, url, No);
                        }
                        break;

                        case "Geography": {
                            CollectionReference ref = db.collection("Class4MH").document("AD")
                                    .collection("Geography").document("again").collection("video");
                            putData(ref, url, No);
                        }
                        break;

                        case "Mathematics": {
                            CollectionReference ref = db.collection("Class4MH").document("AD")
                                    .collection("Mathematics").document("again").collection("video");
                            putData(ref, url, No);
                        }
                        break;

                        case "Science": {
                            CollectionReference ref = db.collection("Class4MH").document("AD")
                                    .collection("Science").document("again").collection("video");
                            putData(ref, url, No);
                        } break;

                        case "Physics": {
                            CollectionReference ref = db.collection("Class4MH").document("AD")
                                    .collection("Physics").document("again").collection("video");
                            putData(ref, url, No);
                        } break;

                        case "Biology": {
                            CollectionReference ref = db.collection("Class4MH").document("AD")
                                    .collection("Biology").document("again").collection("video");
                            putData(ref, url, No);
                        } break;

                        case "Chemistry": {
                            CollectionReference ref = db.collection("Class4MH").document("AD")
                                    .collection("Chemistry").document("again").collection("video");
                            putData(ref, url, No);
                        } break;

                    }
                }
                break;

            case "Class5":
                if (b.contains(patternT)){
                    switch (Tsubject) {
                        case "English": {
                            CollectionReference ref = db.collection("Class5CBSE").document("zu9Z7oNZM8Up9f9wRMjV")
                                    .collection("Eng").document("again").collection("video");
                            putData(ref, url, No);
                        } break;
                        case "History": {
                            CollectionReference ref = db.collection("Class5CBSE").document("zu9Z7oNZM8Up9f9wRMjV")
                                    .collection("History").document("again").collection("video");
                            putData(ref, url, No);
                        } break;
                        case "Geography": {
                            CollectionReference ref = db.collection("Class5CBSE").document("zu9Z7oNZM8Up9f9wRMjV")
                                    .collection("Geography").document("again").collection("video");
                            putData(ref, url, No);

                        } break;
                        case "Mathematics": {
                            CollectionReference ref = db.collection("Class5CBSE").document("zu9Z7oNZM8Up9f9wRMjV")
                                    .collection("Mathematics").document("again").collection("video");
                            putData(ref, url, No);

                        }break;
                        case "Science": {
                            CollectionReference ref = db.collection("Class5CBSE").document("zu9Z7oNZM8Up9f9wRMjV")
                                    .collection("Science").document("again").collection("video");
                            putData(ref, url, No);

                        }  break;
                        case "Physics": {
                            CollectionReference ref = db.collection("Class5CBSE").document("zu9Z7oNZM8Up9f9wRMjV")
                                    .collection("Physics").document("again").collection("video");
                            putData(ref, url, No);

                        } break;
                        case "Biology": {
                            CollectionReference ref = db.collection("Class5CBSE").document("zu9Z7oNZM8Up9f9wRMjV")
                                    .collection("Biology").document("again").collection("video");
                            putData(ref, url, No);

                        } break;
                        case "Chemistry": {
                            CollectionReference ref = db.collection("Class5CBSE").document("zu9Z7oNZM8Up9f9wRMjV")
                                    .collection("Chemistry").document("again").collection("video");
                            putData(ref, url, No);

                        }break;
                    }
                }
                else {
                    switch (Tsubject) {
                        case "English": {
                            CollectionReference ref = db.collection("Class5MH").document("AD")
                                    .collection("Eng").document("again").collection("video");
                            putData(ref, url, No);

                        }break;
                        case "History": {
                            CollectionReference ref = db.collection("Class5MH").document("AE")
                                    .collection("History").document("again").collection("video");
                            putData(ref, url, No);

                        } break;
                        case "Geography": {
                            CollectionReference ref = db.collection("Class5MH").document("AE")
                                    .collection("Geography").document("again").collection("video");
                            putData(ref, url, No);

                        }break;
                        case "Mathematics": {
                            CollectionReference ref = db.collection("Class5MH").document("AE")
                                    .collection("Mathematics").document("again").collection("video");
                            putData(ref, url, No);

                        } break;
                        case "Science": {
                            CollectionReference ref = db.collection("Class5MH").document("AE")
                                    .collection("Science").document("again").collection("video");
                            putData(ref, url, No);

                        }  break;
                        case "Physics": {
                            CollectionReference ref = db.collection("Class5MH").document("AE")
                                    .collection("Physics").document("again").collection("video");
                            putData(ref, url, No);

                        } break;
                        case "Biology": {
                            CollectionReference ref = db.collection("Class5MH").document("AE")
                                    .collection("Biology").document("again").collection("video");
                            putData(ref, url, No);

                        }break;
                        case "Chemistry": {
                            CollectionReference ref = db.collection("Class5MH").document("AE")
                                    .collection("Chemistry").document("again").collection("video");
                            putData(ref, url, No);

                        }break;
                    }
                } break;

            case "Class6":
                if (b.contains(patternT)){
                    switch (Tsubject) {
                        case "English": {
                            CollectionReference ref = db.collection("Class6CBSE").document("FIRST")
                                    .collection("Eng").document("again").collection("video");
                            putData(ref, url, No);

                        } break;
                        case "History": {
                            CollectionReference ref = db.collection("Class6CBSE").document("FIRST")
                                    .collection("History").document("again").collection("video");
                            putData(ref, url, No);

                        } break;
                        case "Geography": {
                            CollectionReference ref = db.collection("Class6CBSE").document("FIRST")
                                    .collection("Geography").document("again").collection("video");
                            putData(ref, url, No);

                        }break;
                        case "Mathematics": {
                            CollectionReference ref = db.collection("Class6CBSE").document("FIRST")
                                    .collection("Mathematics").document("again").collection("video");
                            putData(ref, url, No);

                        } break;
                        case "Science": {
                            CollectionReference ref = db.collection("Class6CBSE").document("FIRST")
                                    .collection("Science").document("again").collection("video");
                            putData(ref, url, No);

                        }  break;
                        case "Physics": {
                            CollectionReference ref = db.collection("Class6CBSE").document("FIRST")
                                    .collection("Physics").document("again").collection("video");
                            putData(ref, url, No);

                        } break;
                        case "Biology": {
                            CollectionReference ref = db.collection("Class6CBSE").document("FIRST")
                                    .collection("Biology").document("again").collection("video");
                            putData(ref, url, No);

                        }break;
                        case "Chemistry": {
                            CollectionReference ref = db.collection("Class6CBSE").document("FIRST")
                                    .collection("Chemistry").document("again").collection("video");
                            putData(ref, url, No);

                        }break;
                    }
                }
                else {
                    switch (Tsubject) {
                        case "English": {
                            CollectionReference ref = db.collection("Class6MH").document("AF")
                                    .collection("Eng").document("again").collection("video");
                            putData(ref, url, No);

                        } break;
                        case "History": {
                            CollectionReference ref = db.collection("Class6MH").document("AF")
                                    .collection("History").document("again").collection("video");
                            putData(ref, url, No);

                        } break;
                        case "Geography": {
                            CollectionReference ref = db.collection("Class6MH").document("AF")
                                    .collection("Geography").document("again").collection("video");
                            putData(ref, url, No);

                        } break;
                        case "Mathematics": {
                            CollectionReference ref = db.collection("Class6MH").document("AF")
                                    .collection("Mathematics").document("again").collection("video");
                            putData(ref, url, No);

                        } break;
                        case "Science": {
                            CollectionReference ref = db.collection("Class6MH").document("AF")
                                    .collection("Science").document("again").collection("video");
                            putData(ref, url, No);

                        } break;
                        case "Physics": {
                            CollectionReference ref = db.collection("Class6MH").document("AF")
                                    .collection("Physics").document("again").collection("video");
                            putData(ref, url, No);

                        } break;
                        case "Biology": {
                            CollectionReference ref = db.collection("Class6MH").document("AF")
                                    .collection("Biology").document("again").collection("video");
                            putData(ref, url, No);

                        }  break;
                        case "Chemistry": {
                            CollectionReference ref = db.collection("Class6MH").document("AF")
                                    .collection("Chemistry").document("again").collection("video");
                            putData(ref, url, No);

                        } break;
                    }
                } break;

            case "Class7":
                if (b.contains(patternT)){
                    switch (Tsubject) {
                        case "English": {
                            CollectionReference ref = db.collection("Class7CBSE").document("A")
                                    .collection("Eng").document("again").collection("video");
                            putData(ref, url, No);

                        } break;
                        case "History": {
                            CollectionReference ref = db.collection("Class7CBSE").document("A")
                                    .collection("History").document("again").collection("video");
                            putData(ref, url, No);

                        } break;
                        case "Geography": {
                            CollectionReference ref = db.collection("Class7CBSE").document("A")
                                    .collection("Geography").document("again").collection("video");
                            putData(ref, url, No);

                        } break;
                        case "Mathematics": {
                            CollectionReference ref = db.collection("Class7CBSE").document("A")
                                    .collection("Mathematics").document("again").collection("video");
                            putData(ref, url, No);

                        } break;
                        case "Science": {
                            CollectionReference ref = db.collection("Class7CBSE").document("A")
                                    .collection("Science").document("again").collection("video");
                            putData(ref, url, No);

                        }  break;
                        case "Physics": {
                            CollectionReference ref = db.collection("Class7CBSE").document("A")
                                    .collection("Physics").document("again").collection("video");
                            putData(ref, url, No);

                        } break;
                        case "Biology": {
                            CollectionReference ref = db.collection("Class7CBSE").document("A")
                                    .collection("Biology").document("again").collection("video");
                            putData(ref, url, No);

                        } break;
                        case "Chemistry": {
                            CollectionReference ref = db.collection("Class7CBSE").document("A")
                                    .collection("Chemistry").document("again").collection("video");
                            putData(ref, url, No);

                        }  break;
                    }
                }
                else {
                    switch (Tsubject) {
                        case "English": {
                            CollectionReference ref = db.collection("Class7MH").document("AG")
                                    .collection("Eng").document("again").collection("video");
                            putData(ref, url, No);

                        } break;
                        case "History": {
                            CollectionReference ref = db.collection("Class7MH").document("AG")
                                    .collection("History").document("again").collection("video");
                            putData(ref, url, No);

                        }break;
                        case "Geography": {
                            CollectionReference ref = db.collection("Class7MH").document("AG")
                                    .collection("Geography").document("again").collection("video");
                            putData(ref, url, No);

                        } break;
                        case "Mathematics": {
                            CollectionReference ref = db.collection("Class7MH").document("AG")
                                    .collection("Mathematics").document("again").collection("video");
                            putData(ref, url, No);

                        }  break;
                        case "Science": {
                            CollectionReference ref = db.collection("Class7MH").document("AG")
                                    .collection("Science").document("again").collection("video");
                            putData(ref, url, No);

                        }  break;
                        case "Physics": {
                            CollectionReference ref = db.collection("Class7MH").document("AG")
                                    .collection("Physics").document("again").collection("video");
                            putData(ref, url, No);

                        }  break;
                        case "Biology": {
                            CollectionReference ref = db.collection("Class7MH").document("AG")
                                    .collection("Biology").document("again").collection("video");
                            putData(ref, url, No);

                        } break;
                        case "Chemistry": {
                            CollectionReference ref = db.collection("Class7MH").document("AG")
                                    .collection("Chemistry").document("again").collection("video");
                            putData(ref, url, No);

                        } break;
                    }
                }break;

            case "Class8":
                if (b.contains(patternT)){
                    switch (Tsubject) {
                        case "English": {
                            CollectionReference ref = db.collection("Class8CBSE").document("B")
                                    .collection("Eng").document("again").collection("video");
                            putData(ref, url, No);

                        }break;
                        case "History": {
                            CollectionReference ref = db.collection("Class8CBSE").document("B")
                                    .collection("History").document("again").collection("video");
                            putData(ref, url, No);

                        }break;
                        case "Geography": {
                            CollectionReference ref = db.collection("Class8CBSE").document("B")
                                    .collection("Geography").document("again").collection("video");
                            putData(ref, url, No);

                        }break;
                        case "Mathematics": {
                            CollectionReference ref = db.collection("Class8CBSE").document("B")
                                    .collection("Mathematics").document("again").collection("video");
                            putData(ref, url, No);

                        }break;
                        case "Science": {
                            CollectionReference ref = db.collection("Class8CBSE").document("B")
                                    .collection("Science").document("again").collection("video");
                            putData(ref, url, No);

                        } break;
                        case "Physics": {
                            CollectionReference ref = db.collection("Class8CBSE").document("B")
                                    .collection("Physics").document("again").collection("video");
                            putData(ref, url, No);

                        }break;
                        case "Biology": {
                            CollectionReference ref = db.collection("Class8CBSE").document("B")
                                    .collection("Biology").document("again").collection("video");
                            putData(ref, url, No);

                        }  break;
                        case "Chemistry": {
                            CollectionReference ref = db.collection("Class8CBSE").document("B")
                                    .collection("Chemistry").document("again").collection("video");
                            putData(ref, url, No);

                        }  break;
                    }
                }
                else {
                    switch (Tsubject) {
                        case "English": {
                            CollectionReference ref = db.collection("Class8MH").document("AH")
                                    .collection("Eng").document("again").collection("video");
                            putData(ref, url, No);

                        }  break;
                        case "History": {
                            CollectionReference ref = db.collection("Class8MH").document("AH")
                                    .collection("History").document("again").collection("video");
                            putData(ref, url, No);

                        }   break;
                        case "Geography": {
                            CollectionReference ref = db.collection("Class8MH").document("AH")
                                    .collection("Geography").document("again").collection("video");
                            putData(ref, url, No);

                        } break;
                        case "Mathematics": {
                            CollectionReference ref = db.collection("Class8MH").document("AH")
                                    .collection("Mathematics").document("again").collection("video");
                            putData(ref, url, No);

                        } break;
                        case "Science": {
                            CollectionReference ref = db.collection("Class8MH").document("AH")
                                    .collection("Science").document("again").collection("video");
                            putData(ref, url, No);

                        }  break;
                        case "Physics": {
                            CollectionReference ref = db.collection("Class8MH").document("AH")
                                    .collection("Physics").document("again").collection("video");
                            putData(ref, url, No);

                        }  break;
                        case "Biology": {
                            CollectionReference ref = db.collection("Class8MH").document("AH")
                                    .collection("Biology").document("again").collection("video");
                            putData(ref, url, No);

                        } break;
                        case "Chemistry": {
                            CollectionReference ref = db.collection("Class8MH").document("AH")
                                    .collection("Chemistry").document("again").collection("video");
                            putData(ref, url, No);

                        }break;
                    }
                } break;

            case "Class9":
                if (b.contains(patternT)){
                    switch (Tsubject) {
                        case "English": {
                            CollectionReference ref = db.collection("Class9CBSE").document("C")
                                    .collection("Eng").document("again").collection("video");
                            putData(ref, url, No);

                        } break;
                        case "History": {
                            CollectionReference ref = db.collection("Class9CBSE").document("C")
                                    .collection("History").document("again").collection("video");
                            putData(ref, url, No);

                        }  break;
                        case "Geography": {
                            CollectionReference ref = db.collection("Class9CBSE").document("C")
                                    .collection("Geography").document("again").collection("video");
                            putData(ref, url, No);

                        }  break;
                        case "Mathematics": {
                            CollectionReference ref = db.collection("Class9CBSE").document("C")
                                    .collection("Mathematics").document("again").collection("video");
                            putData(ref, url, No);

                        }  break;
                        case "Science": {
                            CollectionReference ref = db.collection("Class9CBSE").document("C")
                                    .collection("Science").document("again").collection("video");
                            putData(ref, url, No);

                        } break;
                        case "Physics": {
                            CollectionReference ref = db.collection("Class9CBSE").document("C")
                                    .collection("Physics").document("again").collection("video");
                            putData(ref, url, No);

                        }  break;
                        case "Biology": {
                            CollectionReference ref = db.collection("Class9CBSE").document("C")
                                    .collection("Biology").document("again").collection("video");
                            putData(ref, url, No);

                        } break;
                        case "Chemistry": {
                            CollectionReference ref = db.collection("Class9CBSE").document("C")
                                    .collection("Chemistry").document("again").collection("video");
                            putData(ref, url, No);

                        } break;
                    }
                }
                else {
                    switch (Tsubject) {
                        case "English": {
                            CollectionReference ref = db.collection("Class9MH").document("AI")
                                    .collection("Eng").document("again").collection("video");
                            putData(ref, url, No);

                        } break;
                        case "History": {
                            CollectionReference ref = db.collection("Class9MH").document("AI")
                                    .collection("History").document("again").collection("video");
                            putData(ref, url, No);

                        } break;
                        case "Geography": {
                            CollectionReference ref = db.collection("Class9MH").document("AI")
                                    .collection("Geography").document("again").collection("video");
                            putData(ref, url, No);

                        }break;
                        case "Mathematics": {
                            CollectionReference ref = db.collection("Class9MH").document("AI")
                                    .collection("Mathematics").document("again").collection("video");
                            putData(ref, url, No);

                        }   break;
                        case "Science": {
                            CollectionReference ref = db.collection("Class9MH").document("AI")
                                    .collection("Science").document("again").collection("video");
                            putData(ref, url, No);

                        } break;
                        case "Physics": {
                            CollectionReference ref = db.collection("Class9MH").document("AI")
                                    .collection("Physics").document("again").collection("video");
                            putData(ref, url, No);

                        }  break;
                        case "Biology": {
                            CollectionReference ref = db.collection("Class9MH").document("AI")
                                    .collection("Biology").document("again").collection("video");
                            putData(ref, url, No);

                        } break;
                        case "Chemistry": {
                            CollectionReference ref = db.collection("Class9MH").document("AI")
                                    .collection("Chemistry").document("again").collection("video");
                            putData(ref, url, No);

                        }break;
                    }
                } break;

            case "Class10":
                if (b.contains(patternT)){
                    switch (Tsubject) {
                        case "English": {
                            CollectionReference ref = db.collection("Class10CBSE").document("D")
                                    .collection("Eng").document("again").collection("video");
                            putData(ref, url, No);

                        } break;
                        case "History": {
                            CollectionReference ref = db.collection("Class10CBSE").document("D")
                                    .collection("History").document("again").collection("video");
                            putData(ref, url, No);

                        } break;
                        case "Geography": {
                            CollectionReference ref = db.collection("Class10CBSE").document("D")
                                    .collection("Geography").document("again").collection("video");
                            putData(ref, url, No);

                        }  break;
                        case "Mathematics": {
                            CollectionReference ref = db.collection("Class10CBSE").document("D")
                                    .collection("Mathematics").document("again").collection("video");
                            putData(ref, url, No);

                        } break;
                        case "Science": {
                            CollectionReference ref = db.collection("Class10CBSE").document("D")
                                    .collection("Science").document("again").collection("video");
                            putData(ref, url, No);

                        }break;
                        case "Physics": {
                            CollectionReference ref = db.collection("Class10CBSE").document("D")
                                    .collection("Physics").document("again").collection("video");
                            putData(ref, url, No);

                        } break;
                        case "Biology": {
                            CollectionReference ref = db.collection("Class10CBSE").document("D")
                                    .collection("Biology").document("again").collection("video");
                            putData(ref, url, No);

                        } break;
                        case "Chemistry": {
                            CollectionReference ref = db.collection("Class10CBSE").document("D")
                                    .collection("Chemistry").document("again").collection("video");
                            putData(ref, url, No);

                        } break;
                    }
                }
                else {
                    switch (Tsubject) {
                        case "English": {
                            CollectionReference ref = db.collection("Class10MH").document("AJ")
                                    .collection("Eng").document("again").collection("video");
                            putData(ref, url, No);

                        } break;
                        case "History": {
                            CollectionReference ref = db.collection("Class10MH").document("AJ")
                                    .collection("History").document("again").collection("video");
                            putData(ref, url, No);

                        }break;
                        case "Geography": {
                            CollectionReference ref = db.collection("Class9MH").document("AJ")
                                    .collection("Geography").document("again").collection("video");
                            putData(ref, url, No);

                        }  break;
                        case "Mathematics": {
                            CollectionReference ref = db.collection("Class9MH").document("AJ")
                                    .collection("Mathematics").document("again").collection("video");
                            putData(ref, url, No);

                        } break;
                        case "Science": {
                            CollectionReference ref = db.collection("Class9MH").document("AJ")
                                    .collection("Science").document("again").collection("video");
                            putData(ref, url, No);

                        } break;
                        case "Physics": {
                            CollectionReference ref = db.collection("Class9MH").document("AJ")
                                    .collection("Physics").document("again").collection("video");
                            putData(ref, url, No);

                        } break;
                        case "Biology": {
                            CollectionReference ref = db.collection("Class9MH").document("AJ")
                                    .collection("Biology").document("again").collection("video");
                            putData(ref, url, No);

                        } break;
                        case "Chemistry": {
                            CollectionReference ref = db.collection("Class9MH").document("AJ")
                                    .collection("Chemistry").document("again").collection("video");
                            putData(ref, url, No);

                        }break;
                    }
                } break;

            case "Class11":
                if (b.contains(patternT)){
                    switch (Tsubject) {
                        case "English": {
                            CollectionReference ref = db.collection("Class11CBSE").document("E")
                                    .collection("Eng").document("again").collection("video");
                            putData(ref, url, No);

                        }    break;
                        case "History": {
                            CollectionReference ref = db.collection("Class11CBSE").document("E")
                                    .collection("History").document("again").collection("video");
                            putData(ref, url, No);

                        } break;
                        case "Geography": {
                            CollectionReference ref = db.collection("Class11CBSE").document("E")
                                    .collection("Geography").document("again").collection("video");
                            putData(ref, url, No);

                        }  break;
                        case "Mathematics": {
                            CollectionReference ref = db.collection("Class11CBSE").document("E")
                                    .collection("Mathematics").document("again").collection("video");
                            putData(ref, url, No);

                        } break;
                        case "Science": {
                            CollectionReference ref = db.collection("Class11CBSE").document("E")
                                    .collection("Science").document("again").collection("video");
                            putData(ref, url, No);

                        } break;
                        case "Physics": {
                            CollectionReference ref = db.collection("Class11CBSE").document("E")
                                    .collection("Physics").document("again").collection("video");
                            putData(ref, url, No);

                        }break;
                        case "Biology": {
                            CollectionReference ref = db.collection("Class11CBSE").document("E")
                                    .collection("Biology").document("again").collection("video");
                            putData(ref, url, No);

                        } break;
                        case "Chemistry": {
                            CollectionReference ref = db.collection("Class11CBSE").document("E")
                                    .collection("Chemistry").document("again").collection("video");
                            putData(ref, url, No);

                        }break;
                    }
                }
                else {
                    switch (Tsubject) {
                        case "English": {
                            CollectionReference ref = db.collection("Class11MH").document("AK")
                                    .collection("Eng").document("again").collection("video");
                            putData(ref, url, No);

                        } break;
                        case "History": {
                            CollectionReference ref = db.collection("Class11MH").document("AK")
                                    .collection("History").document("again").collection("video");
                            putData(ref, url, No);

                        }  break;
                        case "Geography": {
                            CollectionReference ref = db.collection("Class9MH").document("AK")
                                    .collection("Geography").document("again").collection("video");
                            putData(ref, url, No);

                        } break;
                        case "Mathematics": {
                            CollectionReference ref = db.collection("Class9MH").document("AK")
                                    .collection("Mathematics").document("again").collection("video");
                            putData(ref, url, No);

                        } break;
                        case "Science": {
                            CollectionReference ref = db.collection("Class9MH").document("AK")
                                    .collection("Science").document("again").collection("video");
                            putData(ref, url, No);

                        } break;
                        case "Physics": {
                            CollectionReference ref = db.collection("Class9MH").document("AK")
                                    .collection("Physics").document("again").collection("video");
                            putData(ref, url, No);

                        } break;
                        case "Biology": {
                            CollectionReference ref = db.collection("Class9MH").document("AK")
                                    .collection("Biology").document("again").collection("video");
                            putData(ref, url, No);

                        }break;
                        case "Chemistry": {
                            CollectionReference ref = db.collection("Class9MH").document("AK")
                                    .collection("Chemistry").document("again").collection("video");
                            putData(ref, url, No);

                        } break;
                    }
                } break;

            case "Class12":
                if (b.contains(patternT)){
                    switch (Tsubject) {
                        case "English": {
                            CollectionReference ref = db.collection("Class12CBSE").document("F")
                                    .collection("Eng").document("again").collection("video");
                            putData(ref, url, No);

                        } break;
                        case "History": {
                            CollectionReference ref = db.collection("Class12CBSE").document("F")
                                    .collection("History").document("again").collection("video");
                            putData(ref, url, No);

                        }  break;
                        case "Geography": {
                            CollectionReference ref = db.collection("Class12CBSE").document("F")
                                    .collection("Geography").document("again").collection("video");
                            putData(ref, url, No);

                        } break;
                        case "Mathematics": {
                            CollectionReference ref = db.collection("Class12CBSE").document("F")
                                    .collection("Mathematics").document("again").collection("video");
                            putData(ref, url, No);

                        }  break;
                        case "Science": {
                            CollectionReference ref = db.collection("Class12CBSE").document("F")
                                    .collection("Science").document("again").collection("video");
                            putData(ref, url, No);

                        }  break;
                        case "Physics": {
                            CollectionReference ref = db.collection("Class12CBSE").document("F")
                                    .collection("Physics").document("again").collection("video");
                            putData(ref, url, No);

                        } break;
                        case "Biology": {
                            CollectionReference ref = db.collection("Class12CBSE").document("F")
                                    .collection("Biology").document("again").collection("video");
                            putData(ref, url, No);

                        } break;
                        case "Chemistry": {
                            CollectionReference ref = db.collection("Class12CBSE").document("F")
                                    .collection("Chemistry").document("again").collection("video");
                            putData(ref, url, No);

                        } break;
                    }
                }
                else {
                    switch (Tsubject) {
                        case "English": {
                            CollectionReference ref = db.collection("Class12MH").document("AL")
                                    .collection("Eng").document("again").collection("video");
                            putData(ref, url, No);

                        }break;
                        case "History": {
                            CollectionReference ref = db.collection("Class12MH").document("AL")
                                    .collection("History").document("again").collection("video");
                            putData(ref, url, No);

                        } break;
                        case "Geography": {
                            CollectionReference ref = db.collection("Class9MH").document("AL")
                                    .collection("Geography").document("again").collection("video");
                            putData(ref, url, No);

                        }   break;
                        case "Mathematics": {
                            CollectionReference ref = db.collection("Class9MH").document("AL")
                                    .collection("Mathematics").document("again").collection("video");
                            putData(ref, url, No);

                        }  break;
                        case "Science": {
                            CollectionReference ref = db.collection("Class9MH").document("AL")
                                    .collection("Science").document("again").collection("video");
                            putData(ref, url, No);

                        }  break;
                        case "Physics": {
                            CollectionReference ref = db.collection("Class9MH").document("AL")
                                    .collection("Physics").document("again").collection("video");
                            putData(ref, url, No);

                        }  break;
                        case "Biology": {
                            CollectionReference ref = db.collection("Class9MH").document("AL")
                                    .collection("Biology").document("again").collection("video");
                            putData(ref, url, No);

                        } break;
                        case "Chemistry": {
                            CollectionReference ref = db.collection("Class9MH").document("AL")
                                    .collection("Chemistry").document("again").collection("video");
                            putData(ref, url, No);

                        }  break;
                    }
                }
                break;
        }
    }

    @SuppressLint("SetTextI18n")
    public void upload3(View view) {
        //      textView.setText("Please Wait ... If Uploading takes Too much time please the button again ");
        progressDialog.show();
        final StorageReference ImageFolder = FirebaseStorage.getInstance().getReference().child("Study Materials");
        for (uploads = 0; uploads < ImageList.size(); uploads++) {
            Uri Image = ImageList.get(uploads);
            final StorageReference imagename = ImageFolder.child("Test/" + Image.getLastPathSegment());
            imagename.putFile(ImageList.get(uploads)).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    imagename.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {

                            String url = String.valueOf(uri);
                            SendLink3(url);

                        }
                    });
                }
            });
        }
    }

    private void SendLink3(String url) {
        String a = "Class1 Class2 Class3 Class4 Class5 Class6 Class7 Class8 Class9 Class10 Class11 Class12";
        final String b = "CBSE";

        String classT = Class.getSelectedItem().toString();
        String patternT = pattern.getSelectedItem().toString();
        String Tsubject = subject.getSelectedItem().toString();
        Integer No = 3;

        switch (classT){
            case "Class1":
                if (b.contains(patternT)){
                    switch (Tsubject) {
                        case "English": {
                            CollectionReference ref = db.collection("Class1CBSE").document("CK6aEA860T6TIHucpEXw")
                                    .collection("Eng").document("PePdIxgaBgIy5p677HY1").collection("test");
                            putData(ref, url, No);
                        }
                        break;

                        case "History": {
                            CollectionReference ref = db.collection("Class1CBSE").document("CK6aEA860T6TIHucpEXw")
                                    .collection("History").document("L7k3y6ZZmI2iO8QFSvs9").collection("test");
                            putData(ref, url, No);
                        }
                        break;

                        case "Geography": {
                            CollectionReference ref = db.collection("Class1CBSE").document("CK6aEA860T6TIHucpEXw")
                                    .collection("Geography").document("XtQkhD23tifMQ8qdchWr").collection("test");
                            putData(ref, url, No);
                        }
                        break;

                        case "Mathematics": {
                            CollectionReference ref = db.collection("Class1CBSE").document("CK6aEA860T6TIHucpEXw")
                                    .collection("Mathematics").document("NYCjx8n8qIqtJtTDNJGt").collection("test");
                            putData(ref, url, No);
                        }
                        break;

                        case "Science": {
                            CollectionReference ref = db.collection("Class1CBSE").document("CK6aEA860T6TIHucpEXw")
                                    .collection("Science").document("freMDiIPbqstwDgE7Zyd").collection("test");
                            putData(ref, url, No);
                        }
                        break;

                        case "Physics": {
                            CollectionReference ref = db.collection("Class1CBSE").document("CK6aEA860T6TIHucpEXw")
                                    .collection("Physics").document("307IuL9VYw7Esmi8Fl7m").collection("test");
                            putData(ref, url, No);
                        }
                        break;

                        case "Biology": {
                            CollectionReference ref = db.collection("Class1CBSE").document("CK6aEA860T6TIHucpEXw")
                                    .collection("Biology").document("1Dm0FouhEU5EW2bkzyuD").collection("test");
                            putData(ref, url, No);
                        }
                        break;

                        case "Chemistry": {
                            CollectionReference ref = db.collection("Class1CBSE").document("CK6aEA860T6TIHucpEXw")
                                    .collection("Chemistry").document("zKV4PwDP2eAlMSBkXJJj").collection("test");
                            putData(ref, url, No);
                        }
                        break;
                    }
                }
                else {
                    switch (Tsubject) {
                        case "English": {
                            CollectionReference ref = db.collection("Class1MH").document("AA")
                                    .collection("Eng").document("again").collection("test");
                            putData(ref, url, No);
                        }
                        break;

                        case "History": {
                            CollectionReference ref = db.collection("Class1MH").document("AA")
                                    .collection("History").document("again").collection("test");
                            putData(ref, url, No);
                        }
                        break;

                        case "Geography": {
                            CollectionReference ref = db.collection("Class1MH").document("AA")
                                    .collection("Geography").document("again").collection("test");
                            putData(ref, url, No);
                        }

                        case "Mathematics": {
                            CollectionReference ref = db.collection("Class1MH").document("AA")
                                    .collection("Mathematics").document("again").collection("test");
                            putData(ref, url, No);
                        }
                        break;

                        case "Science": {
                            CollectionReference ref = db.collection("Class1MH").document("AA")
                                    .collection("Science").document("again").collection("test");
                            putData(ref, url, No);
                        }
                        break;

                        case "Physics": {
                            CollectionReference ref = db.collection("Class1MH").document("AA")
                                    .collection("Physics").document("again").collection("test");
                            putData(ref, url, No);
                        }
                        break;

                        case "Biology": {
                            CollectionReference ref = db.collection("Class1MH").document("AA")
                                    .collection("Biology").document("again").collection("test");
                            putData(ref, url, No);
                        }
                        break;

                        case "Chemistry": {
                            CollectionReference ref = db.collection("Class1MH").document("AA")
                                    .collection("Chemistry").document("again").collection("test");
                            putData(ref, url, No);
                        }
                        break;
                    }
                }
                break;

            case "Class2":
                if (b.contains(patternT)){
                    switch (Tsubject) {
                        case "English": {
                            CollectionReference ref = db.collection("Class2CBSE").document("gPOVSQW8gCKyO1RVCRQo")
                                    .collection("Eng").document("again").collection("test");
                            putData(ref, url, No);
                        }
                        break;

                        case "History": {
                            CollectionReference ref = db.collection("Class2CBSE").document("gPOVSQW8gCKyO1RVCRQo")
                                    .collection("History").document("again").collection("test");
                            putData(ref, url, No);
                        }
                        case "Geography": {
                            CollectionReference ref = db.collection("Class2CBSE").document("gPOVSQW8gCKyO1RVCRQo")
                                    .collection("Geography").document("again").collection("test");
                            putData(ref, url, No);
                        }
                        break;

                        case "Mathematics": {
                            CollectionReference ref = db.collection("Class2CBSE").document("gPOVSQW8gCKyO1RVCRQo")
                                    .collection("Mathematics").document("again").collection("test");
                            putData(ref, url, No);
                        }
                        break;

                        case "Science": {
                            CollectionReference ref = db.collection("Class2CBSE").document("gPOVSQW8gCKyO1RVCRQo")
                                    .collection("Science").document("again").collection("test");
                            putData(ref, url, No);
                        }
                        break;

                        case "Physics": {
                            CollectionReference ref = db.collection("Class2CBSE").document("gPOVSQW8gCKyO1RVCRQo")
                                    .collection("Physics").document("again").collection("test");
                            putData(ref, url, No);
                        }
                        break;

                        case "Biology": {
                            CollectionReference ref = db.collection("Class2CBSE").document("gPOVSQW8gCKyO1RVCRQo")
                                    .collection("Biology").document("again").collection("test");
                            putData(ref, url, No);
                        }
                        break;

                        case "Chemistry": {
                            CollectionReference ref = db.collection("Class2CBSE").document("gPOVSQW8gCKyO1RVCRQo")
                                    .collection("Chemistry").document("again").collection("test");
                            putData(ref, url, No);
                        }
                        break;

                    }
                }
                else {
                    switch (Tsubject) {
                        case "English": {
                            CollectionReference ref = db.collection("Class2MH").document("AB")
                                    .collection("Eng").document("again").collection("test");
                            putData(ref, url, No);
                        }
                        break;

                        case "History": {
                            CollectionReference ref = db.collection("Class2MH").document("AB")
                                    .collection("History").document("again").collection("test");
                            putData(ref, url, No);
                        }
                        case "Geography": {
                            CollectionReference ref = db.collection("Class2MH").document("AB")
                                    .collection("Geography").document("again").collection("test");
                            putData(ref, url, No);
                        }
                        break;

                        case "Mathematics": {
                            CollectionReference ref = db.collection("Class2MH").document("AB")
                                    .collection("Mathematics").document("again").collection("test");
                            putData(ref, url, No);
                        }
                        break;

                        case "Science": {
                            CollectionReference ref = db.collection("Class2MH").document("AB")
                                    .collection("Science").document("again").collection("test");
                            putData(ref, url, No);
                        }
                        break;

                        case "Physics": {
                            CollectionReference ref = db.collection("Class2MH").document("AB")
                                    .collection("Physics").document("again").collection("test");
                            putData(ref, url, No);
                        }
                        break;

                        case "Biology": {
                            CollectionReference ref = db.collection("Class2MH").document("AB")
                                    .collection("Biology").document("again").collection("test");
                            putData(ref, url, No);
                        }
                        break;

                        case "Chemistry": {
                            CollectionReference ref = db.collection("Class2MH").document("AB")
                                    .collection("Chemistry").document("again").collection("test");
                            putData(ref, url, No);
                        }
                        break;

                    }
                }
                break;

            case "Class3":
                if (b.contains(patternT)){
                    switch (Tsubject) {
                        case "English": {
                            CollectionReference ref = db.collection("Class3CBSE").document("GfdFx0V3BaxIVfcT8VnX")
                                    .collection("Eng").document("again").collection("test");
                            putData(ref, url, No);
                        }
                        break;

                        case "History": {
                            CollectionReference ref = db.collection("Class3CBSE").document("GfdFx0V3BaxIVfcT8VnX")
                                    .collection("History").document("again").collection("test");
                            putData(ref, url, No);
                        }
                        case "Geography": {
                            CollectionReference ref = db.collection("Class3CBSE").document("GfdFx0V3BaxIVfcT8VnX")
                                    .collection("Geography").document("again").collection("test");
                            putData(ref, url, No);
                        }
                        break;

                        case "Mathematics": {
                            CollectionReference ref = db.collection("Class3CBSE").document("GfdFx0V3BaxIVfcT8VnX")
                                    .collection("Mathematics").document("again").collection("test");
                            putData(ref, url, No);
                        }
                        break;

                        case "Science": {
                            CollectionReference ref = db.collection("Class3CBSE").document("GfdFx0V3BaxIVfcT8VnX")
                                    .collection("Science").document("again").collection("test");
                            putData(ref, url, No);
                        }
                        break;

                        case "Physics": {
                            CollectionReference ref = db.collection("Class3CBSE").document("GfdFx0V3BaxIVfcT8VnX")
                                    .collection("Physics").document("again").collection("test");
                            putData(ref, url, No);
                        }
                        break;

                        case "Biology": {
                            CollectionReference ref = db.collection("Class3CBSE").document("GfdFx0V3BaxIVfcT8VnX")
                                    .collection("Biology").document("again").collection("test");
                            putData(ref, url, No);
                        }
                        break;

                        case "Chemistry": {
                            CollectionReference ref = db.collection("Class3CBSE").document("GfdFx0V3BaxIVfcT8VnX")
                                    .collection("Chemistry").document("again").collection("test");
                            putData(ref, url, No);
                        }
                        break;

                    }
                }
                else {
                    switch (Tsubject) {
                        case "English": {
                            CollectionReference ref = db.collection("Class3MH").document("AC")
                                    .collection("Eng").document("again").collection("test");
                            putData(ref, url, No);
                        }
                        break;

                        case "History": {
                            CollectionReference ref = db.collection("Class3MH").document("AC")
                                    .collection("History").document("again").collection("test");
                            putData(ref, url, No);
                        }
                        case "Geography": {
                            CollectionReference ref = db.collection("Class3MH").document("AC")
                                    .collection("Geography").document("again").collection("test");
                            putData(ref, url, No);
                        }
                        break;

                        case "Mathematics": {
                            CollectionReference ref = db.collection("Class3MH").document("AC")
                                    .collection("Mathematics").document("again").collection("test");
                            putData(ref, url, No);
                        }
                        break;

                        case "Science": {
                            CollectionReference ref = db.collection("Class3MH").document("AC")
                                    .collection("Science").document("again").collection("test");
                            putData(ref, url, No);
                        }
                        break;

                        case "Physics": {
                            CollectionReference ref = db.collection("Class3MH").document("AC")
                                    .collection("Physics").document("again").collection("test");
                            putData(ref, url, No);
                        }
                        break;

                        case "Biology": {
                            CollectionReference ref = db.collection("Class3MH").document("AC")
                                    .collection("Biology").document("again").collection("test");
                            putData(ref, url, No);
                        }
                        break;

                        case "Chemistry": {
                            CollectionReference ref = db.collection("Class3MH").document("AC")
                                    .collection("Chemistry").document("again").collection("test");
                            putData(ref, url, No);
                        }
                        break;

                    }
                }
                break;

            case "Class4":
                if (b.contains(patternT)){
                    switch (Tsubject) {
                        case "English": {
                            CollectionReference ref = db.collection("Class4CBSE").document("jvcOWa6MXnTBVOAh6DYS")
                                    .collection("Eng").document("again").collection("test");
                            putData(ref, url, No);
                        }
                        case "History": {
                            CollectionReference ref = db.collection("Class4CBSE").document("jvcOWa6MXnTBVOAh6DYS")
                                    .collection("History").document("again").collection("test");
                            putData(ref, url, No);
                        }
                        break;

                        case "Geography": {
                            CollectionReference ref = db.collection("Class4CBSE").document("jvcOWa6MXnTBVOAh6DYS")
                                    .collection("Geography").document("again").collection("test");
                            putData(ref, url, No);
                        }
                        break;

                        case "Mathematics": {
                            CollectionReference ref = db.collection("Class4CBSE").document("jvcOWa6MXnTBVOAh6DYS")
                                    .collection("Mathematics").document("again").collection("test");
                            putData(ref, url, No);
                        }
                        break;

                        case "Science": {
                            CollectionReference ref = db.collection("Class4CBSE").document("jvcOWa6MXnTBVOAh6DYS")
                                    .collection("Science").document("again").collection("test");
                            putData(ref, url, No);
                        }
                        break;

                        case "Physics": {
                            CollectionReference ref = db.collection("Class4CBSE").document("GfdFx0V3BaxIVfcT8VnX")
                                    .collection("Physics").document("again").collection("test");
                            putData(ref, url, No);
                        }
                        break;

                        case "Biology": {
                            CollectionReference ref = db.collection("Class4CBSE").document("GfdFx0V3BaxIVfcT8VnX")
                                    .collection("Biology").document("again").collection("test");
                            putData(ref, url, No);
                        }
                        break;

                        case "Chemistry": {
                            CollectionReference ref = db.collection("Class4CBSE").document("GfdFx0V3BaxIVfcT8VnX")
                                    .collection("Chemistry").document("again").collection("test");
                            putData(ref, url, No);
                        }
                        break;
                    }
                }
                else {
                    switch (Tsubject) {
                        case "English": {
                            CollectionReference ref = db.collection("Class4MH").document("AD")
                                    .collection("Eng").document("again").collection("test");
                            putData(ref, url, No);
                        }
                        break;

                        case "History": {
                            CollectionReference ref = db.collection("Class4MH").document("AD")
                                    .collection("History").document("again").collection("test");
                            putData(ref, url, No);
                        }
                        break;

                        case "Geography": {
                            CollectionReference ref = db.collection("Class4MH").document("AD")
                                    .collection("Geography").document("again").collection("test");
                            putData(ref, url, No);
                        }
                        break;

                        case "Mathematics": {
                            CollectionReference ref = db.collection("Class4MH").document("AD")
                                    .collection("Mathematics").document("again").collection("test");
                            putData(ref, url, No);
                        }
                        break;

                        case "Science": {
                            CollectionReference ref = db.collection("Class4MH").document("AD")
                                    .collection("Science").document("again").collection("test");
                            putData(ref, url, No);
                        } break;

                        case "Physics": {
                            CollectionReference ref = db.collection("Class4MH").document("AD")
                                    .collection("Physics").document("again").collection("test");
                            putData(ref, url, No);
                        } break;

                        case "Biology": {
                            CollectionReference ref = db.collection("Class4MH").document("AD")
                                    .collection("Biology").document("again").collection("test");
                            putData(ref, url, No);
                        } break;

                        case "Chemistry": {
                            CollectionReference ref = db.collection("Class4MH").document("AD")
                                    .collection("Chemistry").document("again").collection("test");
                            putData(ref, url, No);
                        } break;

                    }
                }
                break;

            case "Class5":
                if (b.contains(patternT)){
                    switch (Tsubject) {
                        case "English": {
                            CollectionReference ref = db.collection("Class5CBSE").document("zu9Z7oNZM8Up9f9wRMjV")
                                    .collection("Eng").document("again").collection("test");
                            putData(ref, url, No);
                        } break;
                        case "History": {
                            CollectionReference ref = db.collection("Class5CBSE").document("zu9Z7oNZM8Up9f9wRMjV")
                                    .collection("History").document("again").collection("test");
                            putData(ref, url, No);
                        } break;
                        case "Geography": {
                            CollectionReference ref = db.collection("Class5CBSE").document("zu9Z7oNZM8Up9f9wRMjV")
                                    .collection("Geography").document("again").collection("test");
                            putData(ref, url, No);

                        } break;
                        case "Mathematics": {
                            CollectionReference ref = db.collection("Class5CBSE").document("zu9Z7oNZM8Up9f9wRMjV")
                                    .collection("Mathematics").document("again").collection("test");
                            putData(ref, url, No);

                        }break;
                        case "Science": {
                            CollectionReference ref = db.collection("Class5CBSE").document("zu9Z7oNZM8Up9f9wRMjV")
                                    .collection("Science").document("again").collection("test");
                            putData(ref, url, No);

                        }  break;
                        case "Physics": {
                            CollectionReference ref = db.collection("Class5CBSE").document("zu9Z7oNZM8Up9f9wRMjV")
                                    .collection("Physics").document("again").collection("test");
                            putData(ref, url, No);

                        } break;
                        case "Biology": {
                            CollectionReference ref = db.collection("Class5CBSE").document("zu9Z7oNZM8Up9f9wRMjV")
                                    .collection("Biology").document("again").collection("test");
                            putData(ref, url, No);

                        } break;
                        case "Chemistry": {
                            CollectionReference ref = db.collection("Class5CBSE").document("zu9Z7oNZM8Up9f9wRMjV")
                                    .collection("Chemistry").document("again").collection("test");
                            putData(ref, url, No);

                        }break;
                    }
                }
                else {
                    switch (Tsubject) {
                        case "English": {
                            CollectionReference ref = db.collection("Class5MH").document("AD")
                                    .collection("Eng").document("again").collection("test");
                            putData(ref, url, No);

                        }break;
                        case "History": {
                            CollectionReference ref = db.collection("Class5MH").document("AE")
                                    .collection("History").document("again").collection("test");
                            putData(ref, url, No);

                        } break;
                        case "Geography": {
                            CollectionReference ref = db.collection("Class5MH").document("AE")
                                    .collection("Geography").document("again").collection("test");
                            putData(ref, url, No);

                        }break;
                        case "Mathematics": {
                            CollectionReference ref = db.collection("Class5MH").document("AE")
                                    .collection("Mathematics").document("again").collection("test");
                            putData(ref, url, No);

                        } break;
                        case "Science": {
                            CollectionReference ref = db.collection("Class5MH").document("AE")
                                    .collection("Science").document("again").collection("test");
                            putData(ref, url, No);

                        }  break;
                        case "Physics": {
                            CollectionReference ref = db.collection("Class5MH").document("AE")
                                    .collection("Physics").document("again").collection("test");
                            putData(ref, url, No);

                        } break;
                        case "Biology": {
                            CollectionReference ref = db.collection("Class5MH").document("AE")
                                    .collection("Biology").document("again").collection("test");
                            putData(ref, url, No);

                        }break;
                        case "Chemistry": {
                            CollectionReference ref = db.collection("Class5MH").document("AE")
                                    .collection("Chemistry").document("again").collection("test");
                            putData(ref, url, No);

                        }break;
                    }
                } break;

            case "Class6":
                if (b.contains(patternT)){
                    switch (Tsubject) {
                        case "English": {
                            CollectionReference ref = db.collection("Class6CBSE").document("FIRST")
                                    .collection("Eng").document("again").collection("test");
                            putData(ref, url, No);

                        } break;
                        case "History": {
                            CollectionReference ref = db.collection("Class6CBSE").document("FIRST")
                                    .collection("History").document("again").collection("test");
                            putData(ref, url, No);

                        } break;
                        case "Geography": {
                            CollectionReference ref = db.collection("Class6CBSE").document("FIRST")
                                    .collection("Geography").document("again").collection("test");
                            putData(ref, url, No);

                        }break;
                        case "Mathematics": {
                            CollectionReference ref = db.collection("Class6CBSE").document("FIRST")
                                    .collection("Mathematics").document("again").collection("test");
                            putData(ref, url, No);

                        } break;
                        case "Science": {
                            CollectionReference ref = db.collection("Class6CBSE").document("FIRST")
                                    .collection("Science").document("again").collection("test");
                            putData(ref, url, No);

                        }  break;
                        case "Physics": {
                            CollectionReference ref = db.collection("Class6CBSE").document("FIRST")
                                    .collection("Physics").document("again").collection("test");
                            putData(ref, url, No);

                        } break;
                        case "Biology": {
                            CollectionReference ref = db.collection("Class6CBSE").document("FIRST")
                                    .collection("Biology").document("again").collection("test");
                            putData(ref, url, No);

                        }break;
                        case "Chemistry": {
                            CollectionReference ref = db.collection("Class6CBSE").document("FIRST")
                                    .collection("Chemistry").document("again").collection("test");
                            putData(ref, url, No);

                        }break;
                    }
                }
                else {
                    switch (Tsubject) {
                        case "English": {
                            CollectionReference ref = db.collection("Class6MH").document("AF")
                                    .collection("Eng").document("again").collection("test");
                            putData(ref, url, No);

                        } break;
                        case "History": {
                            CollectionReference ref = db.collection("Class6MH").document("AF")
                                    .collection("History").document("again").collection("test");
                            putData(ref, url, No);

                        } break;
                        case "Geography": {
                            CollectionReference ref = db.collection("Class6MH").document("AF")
                                    .collection("Geography").document("again").collection("test");
                            putData(ref, url, No);

                        } break;
                        case "Mathematics": {
                            CollectionReference ref = db.collection("Class6MH").document("AF")
                                    .collection("Mathematics").document("again").collection("test");
                            putData(ref, url, No);

                        } break;
                        case "Science": {
                            CollectionReference ref = db.collection("Class6MH").document("AF")
                                    .collection("Science").document("again").collection("test");
                            putData(ref, url, No);

                        } break;
                        case "Physics": {
                            CollectionReference ref = db.collection("Class6MH").document("AF")
                                    .collection("Physics").document("again").collection("test");
                            putData(ref, url, No);

                        } break;
                        case "Biology": {
                            CollectionReference ref = db.collection("Class6MH").document("AF")
                                    .collection("Biology").document("again").collection("test");
                            putData(ref, url, No);

                        }  break;
                        case "Chemistry": {
                            CollectionReference ref = db.collection("Class6MH").document("AF")
                                    .collection("Chemistry").document("again").collection("test");
                            putData(ref, url, No);

                        } break;
                    }
                } break;

            case "Class7":
                if (b.contains(patternT)){
                    switch (Tsubject) {
                        case "English": {
                            CollectionReference ref = db.collection("Class7CBSE").document("A")
                                    .collection("Eng").document("again").collection("test");
                            putData(ref, url, No);

                        } break;
                        case "History": {
                            CollectionReference ref = db.collection("Class7CBSE").document("A")
                                    .collection("History").document("again").collection("test");
                            putData(ref, url, No);

                        } break;
                        case "Geography": {
                            CollectionReference ref = db.collection("Class7CBSE").document("A")
                                    .collection("Geography").document("again").collection("test");
                            putData(ref, url, No);

                        } break;
                        case "Mathematics": {
                            CollectionReference ref = db.collection("Class7CBSE").document("A")
                                    .collection("Mathematics").document("again").collection("test");
                            putData(ref, url, No);

                        } break;
                        case "Science": {
                            CollectionReference ref = db.collection("Class7CBSE").document("A")
                                    .collection("Science").document("again").collection("test");
                            putData(ref, url, No);

                        }  break;
                        case "Physics": {
                            CollectionReference ref = db.collection("Class7CBSE").document("A")
                                    .collection("Physics").document("again").collection("test");
                            putData(ref, url, No);

                        } break;
                        case "Biology": {
                            CollectionReference ref = db.collection("Class7CBSE").document("A")
                                    .collection("Biology").document("again").collection("test");
                            putData(ref, url, No);

                        } break;
                        case "Chemistry": {
                            CollectionReference ref = db.collection("Class7CBSE").document("A")
                                    .collection("Chemistry").document("again").collection("test");
                            putData(ref, url, No);

                        }  break;
                    }
                }
                else {
                    switch (Tsubject) {
                        case "English": {
                            CollectionReference ref = db.collection("Class7MH").document("AG")
                                    .collection("Eng").document("again").collection("test");
                            putData(ref, url, No);

                        } break;
                        case "History": {
                            CollectionReference ref = db.collection("Class7MH").document("AG")
                                    .collection("History").document("again").collection("test");
                            putData(ref, url, No);

                        }break;
                        case "Geography": {
                            CollectionReference ref = db.collection("Class7MH").document("AG")
                                    .collection("Geography").document("again").collection("test");
                            putData(ref, url, No);

                        } break;
                        case "Mathematics": {
                            CollectionReference ref = db.collection("Class7MH").document("AG")
                                    .collection("Mathematics").document("again").collection("test");
                            putData(ref, url, No);

                        }  break;
                        case "Science": {
                            CollectionReference ref = db.collection("Class7MH").document("AG")
                                    .collection("Science").document("again").collection("test");
                            putData(ref, url, No);

                        }  break;
                        case "Physics": {
                            CollectionReference ref = db.collection("Class7MH").document("AG")
                                    .collection("Physics").document("again").collection("test");
                            putData(ref, url, No);

                        }  break;
                        case "Biology": {
                            CollectionReference ref = db.collection("Class7MH").document("AG")
                                    .collection("Biology").document("again").collection("test");
                            putData(ref, url, No);

                        } break;
                        case "Chemistry": {
                            CollectionReference ref = db.collection("Class7MH").document("AG")
                                    .collection("Chemistry").document("again").collection("test");
                            putData(ref, url, No);

                        } break;
                    }
                }break;

            case "Class8":
                if (b.contains(patternT)){
                    switch (Tsubject) {
                        case "English": {
                            CollectionReference ref = db.collection("Class8CBSE").document("B")
                                    .collection("Eng").document("again").collection("test");
                            putData(ref, url, No);

                        }break;
                        case "History": {
                            CollectionReference ref = db.collection("Class8CBSE").document("B")
                                    .collection("History").document("again").collection("test");
                            putData(ref, url, No);

                        }break;
                        case "Geography": {
                            CollectionReference ref = db.collection("Class8CBSE").document("B")
                                    .collection("Geography").document("again").collection("test");
                            putData(ref, url, No);

                        }break;
                        case "Mathematics": {
                            CollectionReference ref = db.collection("Class8CBSE").document("B")
                                    .collection("Mathematics").document("again").collection("test");
                            putData(ref, url, No);

                        }break;
                        case "Science": {
                            CollectionReference ref = db.collection("Class8CBSE").document("B")
                                    .collection("Science").document("again").collection("test");
                            putData(ref, url, No);

                        } break;
                        case "Physics": {
                            CollectionReference ref = db.collection("Class8CBSE").document("B")
                                    .collection("Physics").document("again").collection("test");
                            putData(ref, url, No);

                        }break;
                        case "Biology": {
                            CollectionReference ref = db.collection("Class8CBSE").document("B")
                                    .collection("Biology").document("again").collection("test");
                            putData(ref, url, No);

                        }  break;
                        case "Chemistry": {
                            CollectionReference ref = db.collection("Class8CBSE").document("B")
                                    .collection("Chemistry").document("again").collection("test");
                            putData(ref, url, No);

                        }  break;
                    }
                }
                else {
                    switch (Tsubject) {
                        case "English": {
                            CollectionReference ref = db.collection("Class8MH").document("AH")
                                    .collection("Eng").document("again").collection("test");
                            putData(ref, url, No);

                        }  break;
                        case "History": {
                            CollectionReference ref = db.collection("Class8MH").document("AH")
                                    .collection("History").document("again").collection("test");
                            putData(ref, url, No);

                        }   break;
                        case "Geography": {
                            CollectionReference ref = db.collection("Class8MH").document("AH")
                                    .collection("Geography").document("again").collection("test");
                            putData(ref, url, No);

                        } break;
                        case "Mathematics": {
                            CollectionReference ref = db.collection("Class8MH").document("AH")
                                    .collection("Mathematics").document("again").collection("test");
                            putData(ref, url, No);

                        } break;
                        case "Science": {
                            CollectionReference ref = db.collection("Class8MH").document("AH")
                                    .collection("Science").document("again").collection("test");
                            putData(ref, url, No);

                        }  break;
                        case "Physics": {
                            CollectionReference ref = db.collection("Class8MH").document("AH")
                                    .collection("Physics").document("again").collection("test");
                            putData(ref, url, No);

                        }  break;
                        case "Biology": {
                            CollectionReference ref = db.collection("Class8MH").document("AH")
                                    .collection("Biology").document("again").collection("test");
                            putData(ref, url, No);

                        } break;
                        case "Chemistry": {
                            CollectionReference ref = db.collection("Class8MH").document("AH")
                                    .collection("Chemistry").document("again").collection("test");
                            putData(ref, url, No);

                        }break;
                    }
                } break;

            case "Class9":
                if (b.contains(patternT)){
                    switch (Tsubject) {
                        case "English": {
                            CollectionReference ref = db.collection("Class9CBSE").document("C")
                                    .collection("Eng").document("again").collection("test");
                            putData(ref, url, No);

                        } break;
                        case "History": {
                            CollectionReference ref = db.collection("Class9CBSE").document("C")
                                    .collection("History").document("again").collection("test");
                            putData(ref, url, No);

                        }  break;
                        case "Geography": {
                            CollectionReference ref = db.collection("Class9CBSE").document("C")
                                    .collection("Geography").document("again").collection("test");
                            putData(ref, url, No);

                        }  break;
                        case "Mathematics": {
                            CollectionReference ref = db.collection("Class9CBSE").document("C")
                                    .collection("Mathematics").document("again").collection("test");
                            putData(ref, url, No);

                        }  break;
                        case "Science": {
                            CollectionReference ref = db.collection("Class9CBSE").document("C")
                                    .collection("Science").document("again").collection("test");
                            putData(ref, url, No);

                        } break;
                        case "Physics": {
                            CollectionReference ref = db.collection("Class9CBSE").document("C")
                                    .collection("Physics").document("again").collection("test");
                            putData(ref, url, No);

                        }  break;
                        case "Biology": {
                            CollectionReference ref = db.collection("Class9CBSE").document("C")
                                    .collection("Biology").document("again").collection("test");
                            putData(ref, url, No);

                        } break;
                        case "Chemistry": {
                            CollectionReference ref = db.collection("Class9CBSE").document("C")
                                    .collection("Chemistry").document("again").collection("test");
                            putData(ref, url, No);

                        } break;
                    }
                }
                else {
                    switch (Tsubject) {
                        case "English": {
                            CollectionReference ref = db.collection("Class9MH").document("AI")
                                    .collection("Eng").document("again").collection("test");
                            putData(ref, url, No);

                        } break;
                        case "History": {
                            CollectionReference ref = db.collection("Class9MH").document("AI")
                                    .collection("History").document("again").collection("test");
                            putData(ref, url, No);

                        } break;
                        case "Geography": {
                            CollectionReference ref = db.collection("Class9MH").document("AI")
                                    .collection("Geography").document("again").collection("test");
                            putData(ref, url, No);

                        }break;
                        case "Mathematics": {
                            CollectionReference ref = db.collection("Class9MH").document("AI")
                                    .collection("Mathematics").document("again").collection("test");
                            putData(ref, url, No);

                        }   break;
                        case "Science": {
                            CollectionReference ref = db.collection("Class9MH").document("AI")
                                    .collection("Science").document("again").collection("test");
                            putData(ref, url, No);

                        } break;
                        case "Physics": {
                            CollectionReference ref = db.collection("Class9MH").document("AI")
                                    .collection("Physics").document("again").collection("test");
                            putData(ref, url, No);

                        }  break;
                        case "Biology": {
                            CollectionReference ref = db.collection("Class9MH").document("AI")
                                    .collection("Biology").document("again").collection("test");
                            putData(ref, url, No);

                        } break;
                        case "Chemistry": {
                            CollectionReference ref = db.collection("Class9MH").document("AI")
                                    .collection("Chemistry").document("again").collection("test");
                            putData(ref, url, No);

                        }break;
                    }
                } break;

            case "Class10":
                if (b.contains(patternT)){
                    switch (Tsubject) {
                        case "English": {
                            CollectionReference ref = db.collection("Class10CBSE").document("D")
                                    .collection("Eng").document("again").collection("test");
                            putData(ref, url, No);

                        } break;
                        case "History": {
                            CollectionReference ref = db.collection("Class10CBSE").document("D")
                                    .collection("History").document("again").collection("test");
                            putData(ref, url, No);

                        } break;
                        case "Geography": {
                            CollectionReference ref = db.collection("Class10CBSE").document("D")
                                    .collection("Geography").document("again").collection("test");
                            putData(ref, url, No);

                        }  break;
                        case "Mathematics": {
                            CollectionReference ref = db.collection("Class10CBSE").document("D")
                                    .collection("Mathematics").document("again").collection("test");
                            putData(ref, url, No);

                        } break;
                        case "Science": {
                            CollectionReference ref = db.collection("Class10CBSE").document("D")
                                    .collection("Science").document("again").collection("test");
                            putData(ref, url, No);

                        }break;
                        case "Physics": {
                            CollectionReference ref = db.collection("Class10CBSE").document("D")
                                    .collection("Physics").document("again").collection("test");
                            putData(ref, url, No);

                        } break;
                        case "Biology": {
                            CollectionReference ref = db.collection("Class10CBSE").document("D")
                                    .collection("Biology").document("again").collection("test");
                            putData(ref, url, No);

                        } break;
                        case "Chemistry": {
                            CollectionReference ref = db.collection("Class10CBSE").document("D")
                                    .collection("Chemistry").document("again").collection("test");
                            putData(ref, url, No);

                        } break;
                    }
                }
                else {
                    switch (Tsubject) {
                        case "English": {
                            CollectionReference ref = db.collection("Class10MH").document("AJ")
                                    .collection("Eng").document("again").collection("test");
                            putData(ref, url, No);

                        } break;
                        case "History": {
                            CollectionReference ref = db.collection("Class10MH").document("AJ")
                                    .collection("History").document("again").collection("test");
                            putData(ref, url, No);

                        }break;
                        case "Geography": {
                            CollectionReference ref = db.collection("Class9MH").document("AJ")
                                    .collection("Geography").document("again").collection("test");
                            putData(ref, url, No);

                        }  break;
                        case "Mathematics": {
                            CollectionReference ref = db.collection("Class9MH").document("AJ")
                                    .collection("Mathematics").document("again").collection("test");
                            putData(ref, url, No);

                        } break;
                        case "Science": {
                            CollectionReference ref = db.collection("Class9MH").document("AJ")
                                    .collection("Science").document("again").collection("test");
                            putData(ref, url, No);

                        } break;
                        case "Physics": {
                            CollectionReference ref = db.collection("Class9MH").document("AJ")
                                    .collection("Physics").document("again").collection("test");
                            putData(ref, url, No);

                        } break;
                        case "Biology": {
                            CollectionReference ref = db.collection("Class9MH").document("AJ")
                                    .collection("Biology").document("again").collection("test");
                            putData(ref, url, No);

                        } break;
                        case "Chemistry": {
                            CollectionReference ref = db.collection("Class9MH").document("AJ")
                                    .collection("Chemistry").document("again").collection("test");
                            putData(ref, url, No);

                        }break;
                    }
                } break;

            case "Class11":
                if (b.contains(patternT)){
                    switch (Tsubject) {
                        case "English": {
                            CollectionReference ref = db.collection("Class11CBSE").document("E")
                                    .collection("Eng").document("again").collection("test");
                            putData(ref, url, No);

                        }    break;
                        case "History": {
                            CollectionReference ref = db.collection("Class11CBSE").document("E")
                                    .collection("History").document("again").collection("test");
                            putData(ref, url, No);

                        } break;
                        case "Geography": {
                            CollectionReference ref = db.collection("Class11CBSE").document("E")
                                    .collection("Geography").document("again").collection("test");
                            putData(ref, url, No);

                        }  break;
                        case "Mathematics": {
                            CollectionReference ref = db.collection("Class11CBSE").document("E")
                                    .collection("Mathematics").document("again").collection("test");
                            putData(ref, url, No);

                        } break;
                        case "Science": {
                            CollectionReference ref = db.collection("Class11CBSE").document("E")
                                    .collection("Science").document("again").collection("test");
                            putData(ref, url, No);

                        } break;
                        case "Physics": {
                            CollectionReference ref = db.collection("Class11CBSE").document("E")
                                    .collection("Physics").document("again").collection("test");
                            putData(ref, url, No);

                        }break;
                        case "Biology": {
                            CollectionReference ref = db.collection("Class11CBSE").document("E")
                                    .collection("Biology").document("again").collection("test");
                            putData(ref, url, No);

                        } break;
                        case "Chemistry": {
                            CollectionReference ref = db.collection("Class11CBSE").document("E")
                                    .collection("Chemistry").document("again").collection("test");
                            putData(ref, url, No);

                        }break;
                    }
                }
                else {
                    switch (Tsubject) {
                        case "English": {
                            CollectionReference ref = db.collection("Class11MH").document("AK")
                                    .collection("Eng").document("again").collection("test");
                            putData(ref, url, No);

                        } break;
                        case "History": {
                            CollectionReference ref = db.collection("Class11MH").document("AK")
                                    .collection("History").document("again").collection("test");
                            putData(ref, url, No);

                        }  break;
                        case "Geography": {
                            CollectionReference ref = db.collection("Class9MH").document("AK")
                                    .collection("Geography").document("again").collection("test");
                            putData(ref, url, No);

                        } break;
                        case "Mathematics": {
                            CollectionReference ref = db.collection("Class9MH").document("AK")
                                    .collection("Mathematics").document("again").collection("test");
                            putData(ref, url, No);

                        } break;
                        case "Science": {
                            CollectionReference ref = db.collection("Class9MH").document("AK")
                                    .collection("Science").document("again").collection("test");
                            putData(ref, url, No);

                        } break;
                        case "Physics": {
                            CollectionReference ref = db.collection("Class9MH").document("AK")
                                    .collection("Physics").document("again").collection("test");
                            putData(ref, url, No);

                        } break;
                        case "Biology": {
                            CollectionReference ref = db.collection("Class9MH").document("AK")
                                    .collection("Biology").document("again").collection("test");
                            putData(ref, url, No);

                        }break;
                        case "Chemistry": {
                            CollectionReference ref = db.collection("Class9MH").document("AK")
                                    .collection("Chemistry").document("again").collection("test");
                            putData(ref, url, No);

                        } break;
                    }
                } break;

            case "Class12":
                if (b.contains(patternT)){
                    switch (Tsubject) {
                        case "English": {
                            CollectionReference ref = db.collection("Class12CBSE").document("F")
                                    .collection("Eng").document("again").collection("test");
                            putData(ref, url, No);

                        } break;
                        case "History": {
                            CollectionReference ref = db.collection("Class12CBSE").document("F")
                                    .collection("History").document("again").collection("test");
                            putData(ref, url, No);

                        }  break;
                        case "Geography": {
                            CollectionReference ref = db.collection("Class12CBSE").document("F")
                                    .collection("Geography").document("again").collection("test");
                            putData(ref, url, No);

                        } break;
                        case "Mathematics": {
                            CollectionReference ref = db.collection("Class12CBSE").document("F")
                                    .collection("Mathematics").document("again").collection("test");
                            putData(ref, url, No);

                        }  break;
                        case "Science": {
                            CollectionReference ref = db.collection("Class12CBSE").document("F")
                                    .collection("Science").document("again").collection("test");
                            putData(ref, url, No);

                        }  break;
                        case "Physics": {
                            CollectionReference ref = db.collection("Class12CBSE").document("F")
                                    .collection("Physics").document("again").collection("test");
                            putData(ref, url, No);

                        } break;
                        case "Biology": {
                            CollectionReference ref = db.collection("Class12CBSE").document("F")
                                    .collection("Biology").document("again").collection("test");
                            putData(ref, url, No);

                        } break;
                        case "Chemistry": {
                            CollectionReference ref = db.collection("Class12CBSE").document("F")
                                    .collection("Chemistry").document("again").collection("test");
                            putData(ref, url, No);

                        } break;
                    }
                }
                else {
                    switch (Tsubject) {
                        case "English": {
                            CollectionReference ref = db.collection("Class12MH").document("AL")
                                    .collection("Eng").document("again").collection("test");
                            putData(ref, url, No);

                        }break;
                        case "History": {
                            CollectionReference ref = db.collection("Class12MH").document("AL")
                                    .collection("History").document("again").collection("test");
                            putData(ref, url, No);

                        } break;
                        case "Geography": {
                            CollectionReference ref = db.collection("Class9MH").document("AL")
                                    .collection("Geography").document("again").collection("test");
                            putData(ref, url, No);

                        }   break;
                        case "Mathematics": {
                            CollectionReference ref = db.collection("Class9MH").document("AL")
                                    .collection("Mathematics").document("again").collection("test");
                            putData(ref, url, No);

                        }  break;
                        case "Science": {
                            CollectionReference ref = db.collection("Class9MH").document("AL")
                                    .collection("Science").document("again").collection("test");
                            putData(ref, url, No);

                        }  break;
                        case "Physics": {
                            CollectionReference ref = db.collection("Class9MH").document("AL")
                                    .collection("Physics").document("again").collection("test");
                            putData(ref, url, No);

                        }  break;
                        case "Biology": {
                            CollectionReference ref = db.collection("Class9MH").document("AL")
                                    .collection("Biology").document("again").collection("test");
                            putData(ref, url, No);

                        } break;
                        case "Chemistry": {
                            CollectionReference ref = db.collection("Class9MH").document("AL")
                                    .collection("Chemistry").document("again").collection("test");
                            putData(ref, url, No);

                        }  break;
                    }
                }
                break;
        }

    }


    private void SendLink(String url) {

        String a = "Class1 Class2 Class3 Class4 Class5 Class6 Class7 Class8 Class9 Class10 Class11 Class12";
        final String b = "CBSE";

        String classT = Class.getSelectedItem().toString();
        String patternT = pattern.getSelectedItem().toString();
       String Tsubject = subject.getSelectedItem().toString();
        Integer No = 1;

        switch (classT){
            case "Class1":
                if (b.contains(patternT)){
                    switch (Tsubject) {
                        case "English": {
                            CollectionReference ref = db.collection("Class1CBSE").document("CK6aEA860T6TIHucpEXw")
                                    .collection("Eng").document("PePdIxgaBgIy5p677HY1").collection("pdf");
                            putData(ref, url, No);
                        }
                        break;

                        case "History": {
                            CollectionReference ref = db.collection("Class1CBSE").document("CK6aEA860T6TIHucpEXw")
                                    .collection("History").document("L7k3y6ZZmI2iO8QFSvs9").collection("pdf");
                            putData(ref, url, No);
                        }
                        break;

                        case "Geography": {
                            CollectionReference ref = db.collection("Class1CBSE").document("CK6aEA860T6TIHucpEXw")
                                    .collection("Geography").document("XtQkhD23tifMQ8qdchWr").collection("pdf");
                            putData(ref, url, No);
                        }
                        break;

                        case "Mathematics": {
                            CollectionReference ref = db.collection("Class1CBSE").document("CK6aEA860T6TIHucpEXw")
                                    .collection("Mathematics").document("NYCjx8n8qIqtJtTDNJGt").collection("pdf");
                            putData(ref, url, No);
                        }
                        break;

                        case "Science": {
                            CollectionReference ref = db.collection("Class1CBSE").document("CK6aEA860T6TIHucpEXw")
                                    .collection("Science").document("freMDiIPbqstwDgE7Zyd").collection("pdf");
                            putData(ref, url, No);
                        }
                        break;

                        case "Physics": {
                            CollectionReference ref = db.collection("Class1CBSE").document("CK6aEA860T6TIHucpEXw")
                                    .collection("Physics").document("307IuL9VYw7Esmi8Fl7m").collection("pdf");
                            putData(ref, url, No);
                        }
                        break;

                        case "Biology": {
                            CollectionReference ref = db.collection("Class1CBSE").document("CK6aEA860T6TIHucpEXw")
                                    .collection("Biology").document("1Dm0FouhEU5EW2bkzyuD").collection("pdf");
                            putData(ref, url, No);
                        }
                        break;

                        case "Chemistry": {
                            CollectionReference ref = db.collection("Class1CBSE").document("CK6aEA860T6TIHucpEXw")
                                    .collection("Chemistry").document("zKV4PwDP2eAlMSBkXJJj").collection("pdf");
                            putData(ref, url, No);
                        }
                        break;
                    }
                }
                else {
                    switch (Tsubject) {
                        case "English": {
                            CollectionReference ref = db.collection("Class1MH").document("AA")
                                    .collection("Eng").document("again").collection("pdf");
                            putData(ref, url, No);
                        }
                        break;

                        case "History": {
                            CollectionReference ref = db.collection("Class1MH").document("AA")
                                    .collection("History").document("again").collection("pdf");
                            putData(ref, url, No);
                        }
                        break;

                        case "Geography": {
                            CollectionReference ref = db.collection("Class1MH").document("AA")
                                    .collection("Geography").document("again").collection("pdf");
                            putData(ref, url, No);
                        }

                        case "Mathematics": {
                            CollectionReference ref = db.collection("Class1MH").document("AA")
                                    .collection("Mathematics").document("again").collection("pdf");
                            putData(ref, url, No);
                        }
                        break;

                        case "Science": {
                            CollectionReference ref = db.collection("Class1MH").document("AA")
                                    .collection("Science").document("again").collection("pdf");
                            putData(ref, url, No);
                        }
                        break;

                        case "Physics": {
                            CollectionReference ref = db.collection("Class1MH").document("AA")
                                    .collection("Physics").document("again").collection("pdf");
                            putData(ref, url, No);
                        }
                        break;

                        case "Biology": {
                            CollectionReference ref = db.collection("Class1MH").document("AA")
                                    .collection("Biology").document("again").collection("pdf");
                            putData(ref, url, No);
                        }
                        break;

                        case "Chemistry": {
                            CollectionReference ref = db.collection("Class1MH").document("AA")
                                    .collection("Chemistry").document("again").collection("pdf");
                            putData(ref, url, No);
                        }
                        break;
                    }
                }
                break;

            case "Class2":
                if (b.contains(patternT)){
                    switch (Tsubject) {
                        case "English": {
                            CollectionReference ref = db.collection("Class2CBSE").document("gPOVSQW8gCKyO1RVCRQo")
                                    .collection("Eng").document("again").collection("pdf");
                            putData(ref, url, No);
                        }
                        break;

                        case "History": {
                            CollectionReference ref = db.collection("Class2CBSE").document("gPOVSQW8gCKyO1RVCRQo")
                                    .collection("History").document("again").collection("pdf");
                            putData(ref, url, No);
                        }
                        case "Geography": {
                            CollectionReference ref = db.collection("Class2CBSE").document("gPOVSQW8gCKyO1RVCRQo")
                                    .collection("Geography").document("again").collection("pdf");
                            putData(ref, url, No);
                        }
                        break;

                        case "Mathematics": {
                            CollectionReference ref = db.collection("Class2CBSE").document("gPOVSQW8gCKyO1RVCRQo")
                                    .collection("Mathematics").document("again").collection("pdf");
                            putData(ref, url, No);
                        }
                        break;

                        case "Science": {
                            CollectionReference ref = db.collection("Class2CBSE").document("gPOVSQW8gCKyO1RVCRQo")
                                    .collection("Science").document("again").collection("pdf");
                            putData(ref, url, No);
                        }
                        break;

                        case "Physics": {
                            CollectionReference ref = db.collection("Class2CBSE").document("gPOVSQW8gCKyO1RVCRQo")
                                    .collection("Physics").document("again").collection("pdf");
                            putData(ref, url, No);
                        }
                        break;

                        case "Biology": {
                            CollectionReference ref = db.collection("Class2CBSE").document("gPOVSQW8gCKyO1RVCRQo")
                                    .collection("Biology").document("again").collection("pdf");
                            putData(ref, url, No);
                        }
                        break;

                        case "Chemistry": {
                            CollectionReference ref = db.collection("Class2CBSE").document("gPOVSQW8gCKyO1RVCRQo")
                                    .collection("Chemistry").document("again").collection("pdf");
                            putData(ref, url, No);
                        }
                        break;

                    }
                }
                else {
                    switch (Tsubject) {
                        case "English": {
                            CollectionReference ref = db.collection("Class2MH").document("AB")
                                    .collection("Eng").document("again").collection("pdf");
                            putData(ref, url, No);
                        }
                        break;

                        case "History": {
                            CollectionReference ref = db.collection("Class2MH").document("AB")
                                    .collection("History").document("again").collection("pdf");
                            putData(ref, url, No);
                        }
                        case "Geography": {
                            CollectionReference ref = db.collection("Class2MH").document("AB")
                                    .collection("Geography").document("again").collection("pdf");
                            putData(ref, url, No);
                        }
                        break;

                        case "Mathematics": {
                            CollectionReference ref = db.collection("Class2MH").document("AB")
                                    .collection("Mathematics").document("again").collection("pdf");
                            putData(ref, url, No);
                        }
                        break;

                        case "Science": {
                            CollectionReference ref = db.collection("Class2MH").document("AB")
                                    .collection("Science").document("again").collection("pdf");
                            putData(ref, url, No);
                        }
                        break;

                        case "Physics": {
                            CollectionReference ref = db.collection("Class2MH").document("AB")
                                    .collection("Physics").document("again").collection("pdf");
                            putData(ref, url, No);
                        }
                        break;

                        case "Biology": {
                            CollectionReference ref = db.collection("Class2MH").document("AB")
                                    .collection("Biology").document("again").collection("pdf");
                            putData(ref, url, No);
                        }
                        break;

                        case "Chemistry": {
                            CollectionReference ref = db.collection("Class2MH").document("AB")
                                    .collection("Chemistry").document("again").collection("pdf");
                            putData(ref, url, No);
                        }
                        break;

                    }
                }
                break;

            case "Class3":
                if (b.contains(patternT)){
                    switch (Tsubject) {
                        case "English": {
                            CollectionReference ref = db.collection("Class3CBSE").document("GfdFx0V3BaxIVfcT8VnX")
                                    .collection("Eng").document("again").collection("pdf");
                            putData(ref, url, No);
                        }
                        break;

                        case "History": {
                            CollectionReference ref = db.collection("Class3CBSE").document("GfdFx0V3BaxIVfcT8VnX")
                                    .collection("History").document("again").collection("pdf");
                            putData(ref, url, No);
                        }
                        case "Geography": {
                            CollectionReference ref = db.collection("Class3CBSE").document("GfdFx0V3BaxIVfcT8VnX")
                                    .collection("Geography").document("again").collection("pdf");
                            putData(ref, url, No);
                        }
                        break;

                        case "Mathematics": {
                            CollectionReference ref = db.collection("Class3CBSE").document("GfdFx0V3BaxIVfcT8VnX")
                                    .collection("Mathematics").document("again").collection("pdf");
                            putData(ref, url, No);
                        }
                        break;

                        case "Science": {
                            CollectionReference ref = db.collection("Class3CBSE").document("GfdFx0V3BaxIVfcT8VnX")
                                    .collection("Science").document("again").collection("pdf");
                            putData(ref, url, No);
                        }
                        break;

                        case "Physics": {
                            CollectionReference ref = db.collection("Class3CBSE").document("GfdFx0V3BaxIVfcT8VnX")
                                    .collection("Physics").document("again").collection("pdf");
                            putData(ref, url, No);
                        }
                        break;

                        case "Biology": {
                            CollectionReference ref = db.collection("Class3CBSE").document("GfdFx0V3BaxIVfcT8VnX")
                                    .collection("Biology").document("again").collection("pdf");
                            putData(ref, url, No);
                        }
                        break;

                        case "Chemistry": {
                            CollectionReference ref = db.collection("Class3CBSE").document("GfdFx0V3BaxIVfcT8VnX")
                                    .collection("Chemistry").document("again").collection("pdf");
                            putData(ref, url, No);
                        }
                        break;

                    }
                }
                else {
                    switch (Tsubject) {
                        case "English": {
                            CollectionReference ref = db.collection("Class3MH").document("AC")
                                    .collection("Eng").document("again").collection("pdf");
                            putData(ref, url, No);
                        }
                        break;

                        case "History": {
                            CollectionReference ref = db.collection("Class3MH").document("AC")
                                    .collection("History").document("again").collection("pdf");
                            putData(ref, url, No);
                        }
                        case "Geography": {
                            CollectionReference ref = db.collection("Class3MH").document("AC")
                                    .collection("Geography").document("again").collection("pdf");
                            putData(ref, url, No);
                        }
                        break;

                        case "Mathematics": {
                            CollectionReference ref = db.collection("Class3MH").document("AC")
                                    .collection("Mathematics").document("again").collection("pdf");
                            putData(ref, url, No);
                        }
                        break;

                        case "Science": {
                            CollectionReference ref = db.collection("Class3MH").document("AC")
                                    .collection("Science").document("again").collection("pdf");
                            putData(ref, url, No);
                        }
                        break;

                        case "Physics": {
                            CollectionReference ref = db.collection("Class3MH").document("AC")
                                    .collection("Physics").document("again").collection("pdf");
                            putData(ref, url, No);
                        }
                        break;

                        case "Biology": {
                            CollectionReference ref = db.collection("Class3MH").document("AC")
                                    .collection("Biology").document("again").collection("pdf");
                            putData(ref, url, No);
                        }
                        break;

                        case "Chemistry": {
                            CollectionReference ref = db.collection("Class3MH").document("AC")
                                    .collection("Chemistry").document("again").collection("pdf");
                            putData(ref, url, No);
                        }
                        break;

                    }
                }
                break;

            case "Class4":
                if (b.contains(patternT)){
                    switch (Tsubject) {
                        case "English": {
                            CollectionReference ref = db.collection("Class4CBSE").document("jvcOWa6MXnTBVOAh6DYS")
                                    .collection("Eng").document("again").collection("pdf");
                            putData(ref, url, No);
                        }
                        case "History": {
                            CollectionReference ref = db.collection("Class4CBSE").document("jvcOWa6MXnTBVOAh6DYS")
                                    .collection("History").document("again").collection("pdf");
                            putData(ref, url, No);
                        }
                        break;

                        case "Geography": {
                            CollectionReference ref = db.collection("Class4CBSE").document("jvcOWa6MXnTBVOAh6DYS")
                                    .collection("Geography").document("again").collection("pdf");
                            putData(ref, url, No);
                        }
                        break;

                        case "Mathematics": {
                            CollectionReference ref = db.collection("Class4CBSE").document("jvcOWa6MXnTBVOAh6DYS")
                                    .collection("Mathematics").document("again").collection("pdf");
                            putData(ref, url, No);
                        }
                        break;

                        case "Science": {
                            CollectionReference ref = db.collection("Class4CBSE").document("jvcOWa6MXnTBVOAh6DYS")
                                    .collection("Science").document("again").collection("pdf");
                            putData(ref, url, No);
                        }
                        break;

                        case "Physics": {
                            CollectionReference ref = db.collection("Class4CBSE").document("GfdFx0V3BaxIVfcT8VnX")
                                    .collection("Physics").document("again").collection("pdf");
                            putData(ref, url, No);
                        }
                        break;

                        case "Biology": {
                            CollectionReference ref = db.collection("Class4CBSE").document("GfdFx0V3BaxIVfcT8VnX")
                                    .collection("Biology").document("again").collection("pdf");
                            putData(ref, url, No);
                        }
                        break;

                        case "Chemistry": {
                            CollectionReference ref = db.collection("Class4CBSE").document("GfdFx0V3BaxIVfcT8VnX")
                                    .collection("Chemistry").document("again").collection("pdf");
                            putData(ref, url, No);
                        }
                        break;
                    }
                }
                else {
                    switch (Tsubject) {
                        case "English": {
                            CollectionReference ref = db.collection("Class4MH").document("AD")
                                    .collection("Eng").document("again").collection("pdf");
                            putData(ref, url, No);
                        }
                        break;

                        case "History": {
                            CollectionReference ref = db.collection("Class4MH").document("AD")
                                    .collection("History").document("again").collection("pdf");
                            putData(ref, url, No);
                        }
                        break;

                        case "Geography": {
                            CollectionReference ref = db.collection("Class4MH").document("AD")
                                    .collection("Geography").document("again").collection("pdf");
                            putData(ref, url, No);
                        }
                        break;

                        case "Mathematics": {
                            CollectionReference ref = db.collection("Class4MH").document("AD")
                                    .collection("Mathematics").document("again").collection("pdf");
                            putData(ref, url, No);
                        }
                        break;

                        case "Science": {
                            CollectionReference ref = db.collection("Class4MH").document("AD")
                                    .collection("Science").document("again").collection("pdf");
                            putData(ref, url, No);
                        } break;

                        case "Physics": {
                            CollectionReference ref = db.collection("Class4MH").document("AD")
                                    .collection("Physics").document("again").collection("pdf");
                            putData(ref, url, No);
                        } break;

                        case "Biology": {
                            CollectionReference ref = db.collection("Class4MH").document("AD")
                                    .collection("Biology").document("again").collection("pdf");
                            putData(ref, url, No);
                        } break;

                        case "Chemistry": {
                            CollectionReference ref = db.collection("Class4MH").document("AD")
                                    .collection("Chemistry").document("again").collection("pdf");
                            putData(ref, url, No);
                        } break;

                    }
                }
                break;

            case "Class5":
                if (b.contains(patternT)){
                    switch (Tsubject) {
                        case "English": {
                            CollectionReference ref = db.collection("Class5CBSE").document("zu9Z7oNZM8Up9f9wRMjV")
                                    .collection("Eng").document("again").collection("pdf");
                            putData(ref, url, No);
                        } break;
                        case "History": {
                            CollectionReference ref = db.collection("Class5CBSE").document("zu9Z7oNZM8Up9f9wRMjV")
                                    .collection("History").document("again").collection("pdf");
                            putData(ref, url, No);
                        } break;
                        case "Geography": {
                            CollectionReference ref = db.collection("Class5CBSE").document("zu9Z7oNZM8Up9f9wRMjV")
                                    .collection("Geography").document("again").collection("pdf");
                            putData(ref, url, No);

                        } break;
                        case "Mathematics": {
                            CollectionReference ref = db.collection("Class5CBSE").document("zu9Z7oNZM8Up9f9wRMjV")
                                    .collection("Mathematics").document("again").collection("pdf");
                            putData(ref, url, No);

                        }break;
                        case "Science": {
                            CollectionReference ref = db.collection("Class5CBSE").document("zu9Z7oNZM8Up9f9wRMjV")
                                    .collection("Science").document("again").collection("pdf");
                            putData(ref, url, No);

                        }  break;
                        case "Physics": {
                            CollectionReference ref = db.collection("Class5CBSE").document("zu9Z7oNZM8Up9f9wRMjV")
                                    .collection("Physics").document("again").collection("pdf");
                            putData(ref, url, No);

                        } break;
                        case "Biology": {
                            CollectionReference ref = db.collection("Class5CBSE").document("zu9Z7oNZM8Up9f9wRMjV")
                                    .collection("Biology").document("again").collection("pdf");
                            putData(ref, url, No);

                        } break;
                        case "Chemistry": {
                            CollectionReference ref = db.collection("Class5CBSE").document("zu9Z7oNZM8Up9f9wRMjV")
                                    .collection("Chemistry").document("again").collection("pdf");
                            putData(ref, url, No);

                        }break;
                    }
                }
                else {
                    switch (Tsubject) {
                        case "English": {
                            CollectionReference ref = db.collection("Class5MH").document("AD")
                                    .collection("Eng").document("again").collection("pdf");
                            putData(ref, url, No);

                        }break;
                        case "History": {
                            CollectionReference ref = db.collection("Class5MH").document("AE")
                                    .collection("History").document("again").collection("pdf");
                            putData(ref, url, No);

                        } break;
                        case "Geography": {
                            CollectionReference ref = db.collection("Class5MH").document("AE")
                                    .collection("Geography").document("again").collection("pdf");
                            putData(ref, url, No);

                        }break;
                        case "Mathematics": {
                            CollectionReference ref = db.collection("Class5MH").document("AE")
                                    .collection("Mathematics").document("again").collection("pdf");
                            putData(ref, url, No);

                        } break;
                        case "Science": {
                            CollectionReference ref = db.collection("Class5MH").document("AE")
                                    .collection("Science").document("again").collection("pdf");
                            putData(ref, url, No);

                        }  break;
                        case "Physics": {
                            CollectionReference ref = db.collection("Class5MH").document("AE")
                                    .collection("Physics").document("again").collection("pdf");
                            putData(ref, url, No);

                        } break;
                        case "Biology": {
                            CollectionReference ref = db.collection("Class5MH").document("AE")
                                    .collection("Biology").document("again").collection("pdf");
                            putData(ref, url, No);

                        }break;
                        case "Chemistry": {
                            CollectionReference ref = db.collection("Class5MH").document("AE")
                                    .collection("Chemistry").document("again").collection("pdf");
                            putData(ref, url, No);

                        }break;
                    }
                } break;

            case "Class6":
                if (b.contains(patternT)){
                    switch (Tsubject) {
                        case "English": {
                            CollectionReference ref = db.collection("Class6CBSE").document("FIRST")
                                    .collection("Eng").document("again").collection("pdf");
                            putData(ref, url, No);

                        } break;
                        case "History": {
                            CollectionReference ref = db.collection("Class6CBSE").document("FIRST")
                                    .collection("History").document("again").collection("pdf");
                            putData(ref, url, No);

                        } break;
                        case "Geography": {
                            CollectionReference ref = db.collection("Class6CBSE").document("FIRST")
                                    .collection("Geography").document("again").collection("pdf");
                            putData(ref, url, No);

                        }break;
                        case "Mathematics": {
                            CollectionReference ref = db.collection("Class6CBSE").document("FIRST")
                                    .collection("Mathematics").document("again").collection("pdf");
                            putData(ref, url, No);

                        } break;
                        case "Science": {
                            CollectionReference ref = db.collection("Class6CBSE").document("FIRST")
                                    .collection("Science").document("again").collection("pdf");
                            putData(ref, url, No);

                        }  break;
                        case "Physics": {
                            CollectionReference ref = db.collection("Class6CBSE").document("FIRST")
                                    .collection("Physics").document("again").collection("pdf");
                            putData(ref, url, No);

                        } break;
                        case "Biology": {
                            CollectionReference ref = db.collection("Class6CBSE").document("FIRST")
                                    .collection("Biology").document("again").collection("pdf");
                            putData(ref, url, No);

                        }break;
                        case "Chemistry": {
                            CollectionReference ref = db.collection("Class6CBSE").document("FIRST")
                                    .collection("Chemistry").document("again").collection("pdf");
                            putData(ref, url, No);

                        }break;
                    }
                }
                else {
                    switch (Tsubject) {
                        case "English": {
                            CollectionReference ref = db.collection("Class6MH").document("AF")
                                    .collection("Eng").document("again").collection("pdf");
                            putData(ref, url, No);

                        } break;
                        case "History": {
                            CollectionReference ref = db.collection("Class6MH").document("AF")
                                    .collection("History").document("again").collection("pdf");
                            putData(ref, url, No);

                        } break;
                        case "Geography": {
                            CollectionReference ref = db.collection("Class6MH").document("AF")
                                    .collection("Geography").document("again").collection("pdf");
                            putData(ref, url, No);

                        } break;
                        case "Mathematics": {
                            CollectionReference ref = db.collection("Class6MH").document("AF")
                                    .collection("Mathematics").document("again").collection("pdf");
                            putData(ref, url, No);

                        } break;
                        case "Science": {
                            CollectionReference ref = db.collection("Class6MH").document("AF")
                                    .collection("Science").document("again").collection("pdf");
                            putData(ref, url, No);

                        } break;
                        case "Physics": {
                            CollectionReference ref = db.collection("Class6MH").document("AF")
                                    .collection("Physics").document("again").collection("pdf");
                            putData(ref, url, No);

                        } break;
                        case "Biology": {
                            CollectionReference ref = db.collection("Class6MH").document("AF")
                                    .collection("Biology").document("again").collection("pdf");
                            putData(ref, url, No);

                        }  break;
                        case "Chemistry": {
                            CollectionReference ref = db.collection("Class6MH").document("AF")
                                    .collection("Chemistry").document("again").collection("pdf");
                            putData(ref, url, No);

                        } break;
                    }
                } break;

            case "Class7":
                if (b.contains(patternT)){
                    switch (Tsubject) {
                        case "English": {
                            CollectionReference ref = db.collection("Class7CBSE").document("A")
                                    .collection("Eng").document("again").collection("pdf");
                            putData(ref, url, No);

                        } break;
                        case "History": {
                            CollectionReference ref = db.collection("Class7CBSE").document("A")
                                    .collection("History").document("again").collection("pdf");
                            putData(ref, url, No);

                        } break;
                        case "Geography": {
                            CollectionReference ref = db.collection("Class7CBSE").document("A")
                                    .collection("Geography").document("again").collection("pdf");
                            putData(ref, url, No);

                        } break;
                        case "Mathematics": {
                            CollectionReference ref = db.collection("Class7CBSE").document("A")
                                    .collection("Mathematics").document("again").collection("pdf");
                            putData(ref, url, No);

                        } break;
                        case "Science": {
                            CollectionReference ref = db.collection("Class7CBSE").document("A")
                                    .collection("Science").document("again").collection("pdf");
                            putData(ref, url, No);

                        }  break;
                        case "Physics": {
                            CollectionReference ref = db.collection("Class7CBSE").document("A")
                                    .collection("Physics").document("again").collection("pdf");
                            putData(ref, url, No);

                        } break;
                        case "Biology": {
                            CollectionReference ref = db.collection("Class7CBSE").document("A")
                                    .collection("Biology").document("again").collection("pdf");
                            putData(ref, url, No);

                        } break;
                        case "Chemistry": {
                            CollectionReference ref = db.collection("Class7CBSE").document("A")
                                    .collection("Chemistry").document("again").collection("pdf");
                            putData(ref, url, No);

                        }  break;
                    }
                }
                else {
                    switch (Tsubject) {
                        case "English": {
                            CollectionReference ref = db.collection("Class7MH").document("AG")
                                    .collection("Eng").document("again").collection("pdf");
                            putData(ref, url, No);

                        } break;
                        case "History": {
                            CollectionReference ref = db.collection("Class7MH").document("AG")
                                    .collection("History").document("again").collection("pdf");
                            putData(ref, url, No);

                        }break;
                        case "Geography": {
                            CollectionReference ref = db.collection("Class7MH").document("AG")
                                    .collection("Geography").document("again").collection("pdf");
                            putData(ref, url, No);

                        } break;
                        case "Mathematics": {
                            CollectionReference ref = db.collection("Class7MH").document("AG")
                                    .collection("Mathematics").document("again").collection("pdf");
                            putData(ref, url, No);

                        }  break;
                        case "Science": {
                            CollectionReference ref = db.collection("Class7MH").document("AG")
                                    .collection("Science").document("again").collection("pdf");
                            putData(ref, url, No);

                        }  break;
                        case "Physics": {
                            CollectionReference ref = db.collection("Class7MH").document("AG")
                                    .collection("Physics").document("again").collection("pdf");
                            putData(ref, url, No);

                        }  break;
                        case "Biology": {
                            CollectionReference ref = db.collection("Class7MH").document("AG")
                                    .collection("Biology").document("again").collection("pdf");
                            putData(ref, url, No);

                        } break;
                        case "Chemistry": {
                            CollectionReference ref = db.collection("Class7MH").document("AG")
                                    .collection("Chemistry").document("again").collection("pdf");
                            putData(ref, url, No);

                        } break;
                    }
                }break;

            case "Class8":
                if (b.contains(patternT)){
                    switch (Tsubject) {
                        case "English": {
                            CollectionReference ref = db.collection("Class8CBSE").document("B")
                                    .collection("Eng").document("again").collection("pdf");
                            putData(ref, url, No);

                        }break;
                        case "History": {
                            CollectionReference ref = db.collection("Class8CBSE").document("B")
                                    .collection("History").document("again").collection("pdf");
                            putData(ref, url, No);

                        }break;
                        case "Geography": {
                            CollectionReference ref = db.collection("Class8CBSE").document("B")
                                    .collection("Geography").document("again").collection("pdf");
                            putData(ref, url, No);

                        }break;
                        case "Mathematics": {
                            CollectionReference ref = db.collection("Class8CBSE").document("B")
                                    .collection("Mathematics").document("again").collection("pdf");
                            putData(ref, url, No);

                        }break;
                        case "Science": {
                            CollectionReference ref = db.collection("Class8CBSE").document("B")
                                    .collection("Science").document("again").collection("pdf");
                            putData(ref, url, No);

                        } break;
                        case "Physics": {
                            CollectionReference ref = db.collection("Class8CBSE").document("B")
                                    .collection("Physics").document("again").collection("pdf");
                            putData(ref, url, No);

                        }break;
                        case "Biology": {
                            CollectionReference ref = db.collection("Class8CBSE").document("B")
                                    .collection("Biology").document("again").collection("pdf");
                            putData(ref, url, No);

                        }  break;
                        case "Chemistry": {
                            CollectionReference ref = db.collection("Class8CBSE").document("B")
                                    .collection("Chemistry").document("again").collection("pdf");
                            putData(ref, url, No);

                        }  break;
                    }
                }
                else {
                    switch (Tsubject) {
                        case "English": {
                            CollectionReference ref = db.collection("Class8MH").document("AH")
                                    .collection("Eng").document("again").collection("pdf");
                            putData(ref, url, No);

                        }  break;
                        case "History": {
                            CollectionReference ref = db.collection("Class8MH").document("AH")
                                    .collection("History").document("again").collection("pdf");
                            putData(ref, url, No);

                        }   break;
                        case "Geography": {
                            CollectionReference ref = db.collection("Class8MH").document("AH")
                                    .collection("Geography").document("again").collection("pdf");
                            putData(ref, url, No);

                        } break;
                        case "Mathematics": {
                            CollectionReference ref = db.collection("Class8MH").document("AH")
                                    .collection("Mathematics").document("again").collection("pdf");
                            putData(ref, url, No);

                        } break;
                        case "Science": {
                            CollectionReference ref = db.collection("Class8MH").document("AH")
                                    .collection("Science").document("again").collection("pdf");
                            putData(ref, url, No);

                        }  break;
                        case "Physics": {
                            CollectionReference ref = db.collection("Class8MH").document("AH")
                                    .collection("Physics").document("again").collection("pdf");
                            putData(ref, url, No);

                        }  break;
                        case "Biology": {
                            CollectionReference ref = db.collection("Class8MH").document("AH")
                                    .collection("Biology").document("again").collection("pdf");
                            putData(ref, url, No);

                        } break;
                        case "Chemistry": {
                            CollectionReference ref = db.collection("Class8MH").document("AH")
                                    .collection("Chemistry").document("again").collection("pdf");
                            putData(ref, url, No);

                        }break;
                    }
                } break;

            case "Class9":
                if (b.contains(patternT)){
                    switch (Tsubject) {
                        case "English": {
                            CollectionReference ref = db.collection("Class9CBSE").document("C")
                                    .collection("Eng").document("again").collection("pdf");
                            putData(ref, url, No);

                        } break;
                        case "History": {
                            CollectionReference ref = db.collection("Class9CBSE").document("C")
                                    .collection("History").document("again").collection("pdf");
                            putData(ref, url, No);

                        }  break;
                        case "Geography": {
                            CollectionReference ref = db.collection("Class9CBSE").document("C")
                                    .collection("Geography").document("again").collection("pdf");
                            putData(ref, url, No);

                        }  break;
                        case "Mathematics": {
                            CollectionReference ref = db.collection("Class9CBSE").document("C")
                                    .collection("Mathematics").document("again").collection("pdf");
                            putData(ref, url, No);

                        }  break;
                        case "Science": {
                            CollectionReference ref = db.collection("Class9CBSE").document("C")
                                    .collection("Science").document("again").collection("pdf");
                            putData(ref, url, No);

                        } break;
                        case "Physics": {
                            CollectionReference ref = db.collection("Class9CBSE").document("C")
                                    .collection("Physics").document("again").collection("pdf");
                            putData(ref, url, No);

                        }  break;
                        case "Biology": {
                            CollectionReference ref = db.collection("Class9CBSE").document("C")
                                    .collection("Biology").document("again").collection("pdf");
                            putData(ref, url, No);

                        } break;
                        case "Chemistry": {
                            CollectionReference ref = db.collection("Class9CBSE").document("C")
                                    .collection("Chemistry").document("again").collection("pdf");
                            putData(ref, url, No);

                        } break;
                    }
                }
                else {
                    switch (Tsubject) {
                        case "English": {
                            CollectionReference ref = db.collection("Class9MH").document("AI")
                                    .collection("Eng").document("again").collection("pdf");
                            putData(ref, url, No);

                        } break;
                        case "History": {
                            CollectionReference ref = db.collection("Class9MH").document("AI")
                                    .collection("History").document("again").collection("pdf");
                            putData(ref, url, No);

                        } break;
                        case "Geography": {
                            CollectionReference ref = db.collection("Class9MH").document("AI")
                                    .collection("Geography").document("again").collection("pdf");
                            putData(ref, url, No);

                        }break;
                        case "Mathematics": {
                            CollectionReference ref = db.collection("Class9MH").document("AI")
                                    .collection("Mathematics").document("again").collection("pdf");
                            putData(ref, url, No);

                        }   break;
                        case "Science": {
                            CollectionReference ref = db.collection("Class9MH").document("AI")
                                    .collection("Science").document("again").collection("pdf");
                            putData(ref, url, No);

                        } break;
                        case "Physics": {
                            CollectionReference ref = db.collection("Class9MH").document("AI")
                                    .collection("Physics").document("again").collection("pdf");
                            putData(ref, url, No);

                        }  break;
                        case "Biology": {
                            CollectionReference ref = db.collection("Class9MH").document("AI")
                                    .collection("Biology").document("again").collection("pdf");
                            putData(ref, url, No);

                        } break;
                        case "Chemistry": {
                            CollectionReference ref = db.collection("Class9MH").document("AI")
                                    .collection("Chemistry").document("again").collection("pdf");
                            putData(ref, url, No);

                        }break;
                    }
                } break;

            case "Class10":
                if (b.contains(patternT)){
                    switch (Tsubject) {
                        case "English": {
                            CollectionReference ref = db.collection("Class10CBSE").document("D")
                                    .collection("Eng").document("again").collection("pdf");
                            putData(ref, url, No);

                        } break;
                        case "History": {
                            CollectionReference ref = db.collection("Class10CBSE").document("D")
                                    .collection("History").document("again").collection("pdf");
                            putData(ref, url, No);

                        } break;
                        case "Geography": {
                            CollectionReference ref = db.collection("Class10CBSE").document("D")
                                    .collection("Geography").document("again").collection("pdf");
                            putData(ref, url, No);

                        }  break;
                        case "Mathematics": {
                            CollectionReference ref = db.collection("Class10CBSE").document("D")
                                    .collection("Mathematics").document("again").collection("pdf");
                            putData(ref, url, No);

                        } break;
                        case "Science": {
                            CollectionReference ref = db.collection("Class10CBSE").document("D")
                                    .collection("Science").document("again").collection("pdf");
                            putData(ref, url, No);

                        }break;
                        case "Physics": {
                            CollectionReference ref = db.collection("Class10CBSE").document("D")
                                    .collection("Physics").document("again").collection("pdf");
                            putData(ref, url, No);

                        } break;
                        case "Biology": {
                            CollectionReference ref = db.collection("Class10CBSE").document("D")
                                    .collection("Biology").document("again").collection("pdf");
                            putData(ref, url, No);

                        } break;
                        case "Chemistry": {
                            CollectionReference ref = db.collection("Class10CBSE").document("D")
                                    .collection("Chemistry").document("again").collection("pdf");
                            putData(ref, url, No);

                        } break;
                    }
                }
                else {
                    switch (Tsubject) {
                        case "English": {
                            CollectionReference ref = db.collection("Class10MH").document("AJ")
                                    .collection("Eng").document("again").collection("pdf");
                            putData(ref, url, No);

                        } break;
                        case "History": {
                            CollectionReference ref = db.collection("Class10MH").document("AJ")
                                    .collection("History").document("again").collection("pdf");
                            putData(ref, url, No);

                        }break;
                        case "Geography": {
                            CollectionReference ref = db.collection("Class9MH").document("AJ")
                                    .collection("Geography").document("again").collection("pdf");
                            putData(ref, url, No);

                        }  break;
                        case "Mathematics": {
                            CollectionReference ref = db.collection("Class9MH").document("AJ")
                                    .collection("Mathematics").document("again").collection("pdf");
                            putData(ref, url, No);

                        } break;
                        case "Science": {
                            CollectionReference ref = db.collection("Class9MH").document("AJ")
                                    .collection("Science").document("again").collection("pdf");
                            putData(ref, url, No);

                        } break;
                        case "Physics": {
                            CollectionReference ref = db.collection("Class9MH").document("AJ")
                                    .collection("Physics").document("again").collection("pdf");
                            putData(ref, url, No);

                        } break;
                        case "Biology": {
                            CollectionReference ref = db.collection("Class9MH").document("AJ")
                                    .collection("Biology").document("again").collection("pdf");
                            putData(ref, url, No);

                        } break;
                        case "Chemistry": {
                            CollectionReference ref = db.collection("Class9MH").document("AJ")
                                    .collection("Chemistry").document("again").collection("pdf");
                            putData(ref, url, No);

                        }break;
                    }
                } break;

            case "Class11":
                if (b.contains(patternT)){
                    switch (Tsubject) {
                        case "English": {
                            CollectionReference ref = db.collection("Class11CBSE").document("E")
                                    .collection("Eng").document("again").collection("pdf");
                            putData(ref, url, No);

                        }    break;
                        case "History": {
                            CollectionReference ref = db.collection("Class11CBSE").document("E")
                                    .collection("History").document("again").collection("pdf");
                            putData(ref, url, No);

                        } break;
                        case "Geography": {
                            CollectionReference ref = db.collection("Class11CBSE").document("E")
                                    .collection("Geography").document("again").collection("pdf");
                            putData(ref, url, No);

                        }  break;
                        case "Mathematics": {
                            CollectionReference ref = db.collection("Class11CBSE").document("E")
                                    .collection("Mathematics").document("again").collection("pdf");
                            putData(ref, url, No);

                        } break;
                        case "Science": {
                            CollectionReference ref = db.collection("Class11CBSE").document("E")
                                    .collection("Science").document("again").collection("pdf");
                            putData(ref, url, No);

                        } break;
                        case "Physics": {
                            CollectionReference ref = db.collection("Class11CBSE").document("E")
                                    .collection("Physics").document("again").collection("pdf");
                            putData(ref, url, No);

                        }break;
                        case "Biology": {
                            CollectionReference ref = db.collection("Class11CBSE").document("E")
                                    .collection("Biology").document("again").collection("pdf");
                            putData(ref, url, No);

                        } break;
                        case "Chemistry": {
                            CollectionReference ref = db.collection("Class11CBSE").document("E")
                                    .collection("Chemistry").document("again").collection("pdf");
                            putData(ref, url, No);

                        }break;
                    }
                }
                else {
                    switch (Tsubject) {
                        case "English": {
                            CollectionReference ref = db.collection("Class11MH").document("AK")
                                    .collection("Eng").document("again").collection("pdf");
                            putData(ref, url, No);

                        } break;
                        case "History": {
                            CollectionReference ref = db.collection("Class11MH").document("AK")
                                    .collection("History").document("again").collection("pdf");
                            putData(ref, url, No);

                        }  break;
                        case "Geography": {
                            CollectionReference ref = db.collection("Class9MH").document("AK")
                                    .collection("Geography").document("again").collection("pdf");
                            putData(ref, url, No);

                        } break;
                        case "Mathematics": {
                            CollectionReference ref = db.collection("Class9MH").document("AK")
                                    .collection("Mathematics").document("again").collection("pdf");
                            putData(ref, url, No);

                        } break;
                        case "Science": {
                            CollectionReference ref = db.collection("Class9MH").document("AK")
                                    .collection("Science").document("again").collection("pdf");
                            putData(ref, url, No);

                        } break;
                        case "Physics": {
                            CollectionReference ref = db.collection("Class9MH").document("AK")
                                    .collection("Physics").document("again").collection("pdf");
                            putData(ref, url, No);

                        } break;
                        case "Biology": {
                            CollectionReference ref = db.collection("Class9MH").document("AK")
                                    .collection("Biology").document("again").collection("pdf");
                            putData(ref, url, No);

                        }break;
                        case "Chemistry": {
                            CollectionReference ref = db.collection("Class9MH").document("AK")
                                    .collection("Chemistry").document("again").collection("pdf");
                            putData(ref, url, No);

                        } break;
                    }
                } break;

            case "Class12":
                if (b.contains(patternT)){
                    switch (Tsubject) {
                        case "English": {
                            CollectionReference ref = db.collection("Class12CBSE").document("F")
                                    .collection("Eng").document("again").collection("pdf");
                            putData(ref, url, No);

                        } break;
                        case "History": {
                            CollectionReference ref = db.collection("Class12CBSE").document("F")
                                    .collection("History").document("again").collection("pdf");
                            putData(ref, url, No);

                        }  break;
                        case "Geography": {
                            CollectionReference ref = db.collection("Class12CBSE").document("F")
                                    .collection("Geography").document("again").collection("pdf");
                            putData(ref, url, No);

                        } break;
                        case "Mathematics": {
                            CollectionReference ref = db.collection("Class12CBSE").document("F")
                                    .collection("Mathematics").document("again").collection("pdf");
                            putData(ref, url, No);

                        }  break;
                        case "Science": {
                            CollectionReference ref = db.collection("Class12CBSE").document("F")
                                    .collection("Science").document("again").collection("pdf");
                            putData(ref, url, No);

                        }  break;
                        case "Physics": {
                            CollectionReference ref = db.collection("Class12CBSE").document("F")
                                    .collection("Physics").document("again").collection("pdf");
                            putData(ref, url, No);

                        } break;
                        case "Biology": {
                            CollectionReference ref = db.collection("Class12CBSE").document("F")
                                    .collection("Biology").document("again").collection("pdf");
                            putData(ref, url, No);

                        } break;
                        case "Chemistry": {
                            CollectionReference ref = db.collection("Class12CBSE").document("F")
                                    .collection("Chemistry").document("again").collection("pdf");
                            putData(ref, url, No);

                        } break;
                    }
                }
                else {
                    switch (Tsubject) {
                        case "English": {
                            CollectionReference ref = db.collection("Class12MH").document("AL")
                                    .collection("Eng").document("again").collection("pdf");
                            putData(ref, url, No);

                        }break;
                        case "History": {
                            CollectionReference ref = db.collection("Class12MH").document("AL")
                                    .collection("History").document("again").collection("pdf");
                            putData(ref, url, No);

                        } break;
                        case "Geography": {
                            CollectionReference ref = db.collection("Class9MH").document("AL")
                                    .collection("Geography").document("again").collection("pdf");
                            putData(ref, url, No);

                        }   break;
                        case "Mathematics": {
                            CollectionReference ref = db.collection("Class9MH").document("AL")
                                    .collection("Mathematics").document("again").collection("pdf");
                            putData(ref, url, No);

                        }  break;
                        case "Science": {
                            CollectionReference ref = db.collection("Class9MH").document("AL")
                                    .collection("Science").document("again").collection("pdf");
                            putData(ref, url, No);

                        }  break;
                        case "Physics": {
                            CollectionReference ref = db.collection("Class9MH").document("AL")
                                    .collection("Physics").document("again").collection("pdf");
                            putData(ref, url, No);

                        }  break;
                        case "Biology": {
                            CollectionReference ref = db.collection("Class9MH").document("AL")
                                    .collection("Biology").document("again").collection("pdf");
                            putData(ref, url, No);

                        } break;
                        case "Chemistry": {
                            CollectionReference ref = db.collection("Class9MH").document("AL")
                                    .collection("Chemistry").document("again").collection("pdf");
                            putData(ref, url, No);

                        }  break;
                    }
                }
                break;
        }

    }

    private void putData(CollectionReference ref, String url, Integer No) {
        HashMap<String, String> hashMap = new HashMap<>();
        if (No.equals(1)){
            hashMap.put("url", url);
            hashMap.put("pdf", file_namepdf.getText().toString());
        }
        else   if (No.equals(2)) {
            hashMap.put("vurl", url);
            hashMap.put("VideoName", filevideo.getText().toString());
        }
        else   if (No.equals(3)) {
            hashMap.put("TestLink", url);
            hashMap.put("TestName", fileTest.getText().toString());
        }


        ref.add(hashMap)
                //        .update(hashMap, (url))
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        progressDialog.dismiss();
                        //      textView.setText("Image Uploaded Successfully");
                        Toast.makeText(AddFiles.this, "File upload successful", Toast.LENGTH_SHORT).show();
                        //      send.setVisibility(View.GONE);
                        ImageList.clear();
                        choosepdf.setVisibility(View.GONE);
                        sendpdf.setVisibility(View.VISIBLE);

                        choosevdo.setVisibility(View.GONE);
                        sendvdo.setVisibility(View.VISIBLE);

                        choosetst.setVisibility(View.GONE);
                        sendtst.setVisibility(View.VISIBLE);
                    }
                });
    }
    //For Text animation
    private void blink() {
        final Handler handler = new Handler();
        new Thread(new Runnable() {
            @Override
            public void run() {
                int timeToBlink = 500;    //in milissegunds
                try {
                    Thread.sleep(timeToBlink);
                } catch (Exception e) {
                }
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        TextView txt = findViewById(R.id.usage);
                        if (txt.getVisibility() == View.VISIBLE) {
                            txt.setVisibility(View.INVISIBLE);
                        } else {
                            txt.setVisibility(View.VISIBLE);
                        }
                        blink();
                    }
                });
            }
        })
                .start();
    }
}

