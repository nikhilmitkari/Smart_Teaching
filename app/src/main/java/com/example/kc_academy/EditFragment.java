package com.example.kc_academy;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;


/**
 * A simple {@link Fragment} subclass.
 */
public class EditFragment extends Fragment {

    private static final String TAG = "EditFragment";
//firebase
private FirebaseAuth firebaseAuth;
    private FirebaseUser user1;
    //views from xml
    private ImageView ivdp;

    private TextView tvphone1, tvname1;
    private TextView tvemail1;
    private TextView tvdob, tvgender;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference myref;
    private Button btnEdit;
    Spinner spinner1, spinner2;

    private DatePickerDialog.OnDateSetListener mDateSetListener;
    public EditFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_edit, container, false);

        //initialize firebase
        firebaseAuth = FirebaseAuth.getInstance();
        user1 = firebaseAuth.getCurrentUser();
        firebaseDatabase = FirebaseDatabase.getInstance();
        myref = firebaseDatabase.getReference("Users");

        //initialize value
       tvname1= view.findViewById(R.id.name001);
        tvphone1 = view.findViewById(R.id.mbno002);
        tvemail1 = view.findViewById(R.id.mail002);
        tvgender = view.findViewById(R.id.gender);
        tvdob = view.findViewById(R.id.dob004);
        btnEdit = view.findViewById(R.id.btn_edit);
        spinner1 = view.findViewById(R.id.spinner1);
        spinner2 = view.findViewById(R.id.spinner2);

     /*   String testName = tvname1.getText().toString();
        Intent intent = new Intent(getActivity().getBaseContext(),
                StandBoardActivity.class);
        intent.putExtra("message", testName);
        getActivity().startActivity(intent);
      */

        Query query = myref.orderByChild("email").equalTo(user1.getEmail());
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                    //check untill required data get

                for (DataSnapshot ds: dataSnapshot.getChildren()){
                    //
                    String Name = ""+ ds.child("name").getValue();
                    String Phone = ""+ ds.child("phone").getValue();
                    String Email = ""+ ds.child("email").getValue();
                    String dob = ""+ ds.child("dob").getValue();
                    String gender = ""+ ds.child("gender").getValue();

                    tvname1.setText(Name);
                    tvphone1.setText(Phone);
                    tvemail1.setText(Email);
                    tvdob.setText(dob);
                    tvgender.setText(gender);


                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
       // final DatabaseReference myref = firebaseDatabase.getInstance(firebaseAuth.getUid());
        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = user1.getUid();
                DatabaseReference myRootRef = FirebaseDatabase.getInstance().getReference("Users");
                DatabaseReference ref = myRootRef.child(id);

                String email = user1.getEmail();
                String uid = user1.getUid();

                Map<String, Object> childUpdates = new HashMap<>();
                childUpdates.put("email", email);
                childUpdates.put("uid", uid);
                childUpdates.put("name", tvname1.getText().toString());
                childUpdates.put("phone", tvphone1.getText().toString());
                childUpdates.put("dob", tvdob.getText().toString());
                childUpdates.put("gender", tvgender.getText().toString());
                childUpdates.put("class",spinner1.getSelectedItem().toString());
                childUpdates.put("pattern", spinner2.getSelectedItem().toString());

                ref.updateChildren(childUpdates);
                Toast.makeText(getActivity(), "Profile Update Successfully", Toast.LENGTH_SHORT).show();

            }
        });


        tvdob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        getActivity(),
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
                Log.d(TAG, "onDateSet: mm/dd/yyy: " + month + "/" + day + "/" + year);

                String date = month + "/" + day + "/" + year;
                tvdob.setText(date);
            }
        };






     return view;
    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }
}






/*public void showAlertDialog()
{
    Log.d("-------VALUE-----", "value: "+limit_counter);
    AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);

    alertDialog.setTitle("Limit Reached!");
    alertDialog.setMessage("Buy Pro Version");
    alertDialog.setIcon(R.drawable.action_bar_logo);

    alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            buyClick();
            Log.d("-------OK PRESSED -----", "value: " + limit_counter);
            dialog.cancel();
        }
    });

    alertDialog.setNegativeButton("Later", new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            Log.d("----LATER PRESSED -----", "value: " + limit_counter);
            dialog.cancel();
        }
    });

    alertDialog.show();
}


.........................................................................................................
 user1 = firebaseAuth.getCurrentUser();

                   //get useremail and uid from auth

                   String email = user1.getEmail();
                   String uid = user1.getUid();
                   //store details in realtime
                   HashMap<Object, String> hashMap = new HashMap<>();
                   hashMap.put("email", email);
                   hashMap.put("uid", uid);
                   hashMap.put("name", tvname1.getText().toString());
                   hashMap.put("phone", tvphone1.getText().toString());
                   hashMap.put("dob", tvdob.getText().toString());
                   hashMap.put("gender", tvgender.getText().toString());
                   hashMap.put("class",spinner1.getSelectedItem().toString());
                   hashMap.put("pattern", spinner2.getSelectedItem().toString());

                   FirebaseDatabase database = FirebaseDatabase.getInstance();
                   //path to store data named "Users"
                   DatabaseReference reference = database.getReference("Users");
                   reference.child(uid).setValue(hashMap);






*/