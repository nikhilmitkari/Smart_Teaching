package com.example.kc_academy;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.Calendar;
import java.util.HashMap;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener{

    EditText user_email,user_password,user_name, phone_no, dob, gender;
    TextView tvsignin, tvpicselect;
    Button signup_btn;
    private FirebaseAuth mAuth;
    private FirebaseUser user;
    private FirebaseStorage storage;
    private StorageReference mStorageRef;
    ImageView user_profile;
    Uri file;
    private DatePickerDialog.OnDateSetListener mDateSetListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        user_email = findViewById(R.id.editText4);
        user_password = findViewById(R.id.editText5);
        user_name = findViewById(R.id.editText2);
        phone_no = findViewById(R.id.editText3);
        gender = findViewById(R.id.editText6);
        signup_btn = findViewById(R.id.button);
        tvsignin = findViewById(R.id.textView10);
        dob = findViewById(R.id.editText7);
        tvsignin.setOnClickListener(this);
        signup_btn.setOnClickListener(this);
      //  user_profile.setOnClickListener(this);
        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();
        mStorageRef = FirebaseStorage.getInstance().getReference();
        storage = FirebaseStorage.getInstance();

        dob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(RegisterActivity.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mDateSetListener,
                        year,month,day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
          //      Log.d(TAG, "onDateSet: mm/dd/yyy: " + month + "/" + day + "/" + year);

                String date = month + "/" + day + "/" + year;
                dob.setText(date);
            }
        };


    }


    @Override
    public void onClick(@NonNull View v) {
        if(v.getId() == R.id.textView10){
            startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
        }
        else if(v.getId() == R.id.button){
            signUpUser(user_email.getText().toString(),user_password.getText().toString());
            //startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
        }

    }


    /*------------ Below Code is for successful sign up process -----------*/

    private void signUpUser(@NonNull String email, String password) {
        if(email.equals("")){
            Toast.makeText(RegisterActivity.this, "Enter Email!!",
                    Toast.LENGTH_SHORT).show();
        }
        else if(password.equals("")){
            Toast.makeText(RegisterActivity.this, "Enter Password!!",
                    Toast.LENGTH_SHORT).show();
        }
        else if((user_name.getText().toString()).equals("")){
            Toast.makeText(RegisterActivity.this, "Enter Name!!",
                    Toast.LENGTH_SHORT).show();
        }
        if(phone_no.equals("")){
            Toast.makeText(RegisterActivity.this, "Enter Phone no!!",
                    Toast.LENGTH_SHORT).show();
        }

        else {
            mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                userProfile();
                            } else {
                                // If sign in fails, display a message to the user.
                                Toast.makeText(RegisterActivity.this, "Sign up failed: " + task.getException(),
                                        Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }
    }

    /*----------For saving image and user name in Firebase Database-------*/
    private void userProfile() {
        user = mAuth.getCurrentUser();
        //get useremail and uid from auth
        
        String email = user.getEmail();
        String uid = user.getUid();
        String userName = user.getDisplayName();
        String userPhone = user.getPhoneNumber();
        //store details in realtime
        HashMap<Object, String> hashMap = new HashMap<>();
        hashMap.put("email",email);
        hashMap.put("uid",uid);
        hashMap.put("name",user_name.getText().toString());
        hashMap.put("phone",phone_no.getText().toString());
        hashMap.put("dob",dob.getText().toString());
        hashMap.put("class","");
        hashMap.put("pattern","");
        hashMap.put("payment","");
        hashMap.put("gender",gender.getText().toString());

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        //path to store data named "Users"
        DatabaseReference reference = database.getReference("Users");
        reference.child(uid).setValue(hashMap);

        UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                .setDisplayName(user_name.getText().toString())
                .setDisplayName(phone_no.getText().toString())
                .setPhotoUri(file).build();
        user.updateProfile(profileUpdates).addOnCompleteListener(this, new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Log.d("this", "User profile updated.");
                }
            }
        });
    }
}
