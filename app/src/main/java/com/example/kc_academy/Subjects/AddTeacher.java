package com.example.kc_academy.Subjects;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.kc_academy.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.HashMap;

public class AddTeacher extends AppCompatActivity {
    EditText name,email,phone, password,gender;
    Button Add;
    FirebaseAuth mAuth;
    StorageReference mStorageRef;
    FirebaseStorage storage;
    FirebaseUser user;
    ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_teacher);
        name = findViewById(R.id.FullName);
        email = findViewById(R.id.EmailId);
        phone = findViewById(R.id.PhoneNo);
        password = findViewById(R.id.Password);
        gender = findViewById(R.id.sex);
        Add = findViewById(R.id.button);

        mAuth = FirebaseAuth.getInstance();
        mStorageRef = FirebaseStorage.getInstance().getReference();
        storage = FirebaseStorage.getInstance();


        Add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signUpUser(email.getText().toString(),password.getText().toString());
            }
        });
    }

    private void signUpUser(String email, String password) {
        if(email.equals("")){
            Toast.makeText(AddTeacher.this, "Enter Email!!",
                    Toast.LENGTH_SHORT).show();
        }
        else if(password.equals("")){
            Toast.makeText(AddTeacher.this, "Enter Password!!",
                    Toast.LENGTH_SHORT).show();
        }
        else if((name.getText().toString()).equals("")){
            Toast.makeText(AddTeacher.this, "Enter Name!!",
                    Toast.LENGTH_SHORT).show();
        }
       else if(phone.getText().toString().equals("")){
            Toast.makeText(AddTeacher.this, "Enter Phone no!!",
                    Toast.LENGTH_SHORT).show();
        }
        else if(gender.getText().toString().equals("")){
            Toast.makeText(AddTeacher.this, "Enter Gender!",
                    Toast.LENGTH_SHORT).show();
        }

        else {
            pd = new ProgressDialog(this);
            pd.setMessage("Registering");
            pd.show();
            mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                               pd.dismiss();
                                Toast.makeText(AddTeacher.this, "Teacher Added Successfully ",
                                        Toast.LENGTH_SHORT).show();

                                // Sign in success, update UI with the signed-in user's information
                                userProfile();
                            } else {
                                // If sign in fails, display a message to the user.
                                Toast.makeText(AddTeacher.this, "Sign up failed: " + task.getException(),
                                        Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }
    }

    private void userProfile() {
        user = mAuth.getCurrentUser();
        //get useremail and uid from auth

        String email = user.getEmail();
        String uid = user.getUid();
        //store details in realtime
        HashMap<Object, String> hashMap = new HashMap<>();
        hashMap.put("email",email);
        hashMap.put("uid",uid);
        hashMap.put("name",name.getText().toString());
        hashMap.put("phone",phone.getText().toString());
        hashMap.put("gender",gender.getText().toString());

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        //path to store data named "Users"
        DatabaseReference reference = database.getReference("Teacher");
        reference.child(uid).setValue(hashMap);

      /*  UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                .setDisplayName(name.getText().toString())
                .setDisplayName(phone.getText().toString()).build();
        user.updateProfile(profileUpdates).addOnCompleteListener(this, new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Log.d("this", "User profile updated.");
                }
            }
        });

       */
    }

}
