package com.example.kc_academy;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

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


/**
 * A simple {@link Fragment} subclass.
 */
public class MyclassFragment extends Fragment {
    Button go;
    FirebaseAuth firebaseAuth;
    FirebaseUser user1;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference myref;
    TextView payment;

    public MyclassFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
    View view = inflater.inflate(R.layout.fragment_myclass, container, false);
    payment = view.findViewById(R.id.data);

        firebaseAuth = FirebaseAuth.getInstance();
        user1 = firebaseAuth.getCurrentUser();
        firebaseDatabase = FirebaseDatabase.getInstance();
        myref = firebaseDatabase.getReference("Users");


    go = view.findViewById(R.id.send);
    go.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent payment_test = new Intent(getActivity(), PaymentTest.class);
            startActivity(payment_test);
        }
    });

        Query query = myref.orderByChild("email").equalTo(user1.getEmail());
        query.addValueEventListener(new ValueEventListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                //check untill required data get

                for (DataSnapshot ds: dataSnapshot.getChildren()){
                    //
                    String Name = ""+ ds.child("payment").getValue();
                    payment.setText("Your Last transaction ID : "+ Name);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    return  view;
    }

    public interface OnFragmentInteractionListener {
    }
}
