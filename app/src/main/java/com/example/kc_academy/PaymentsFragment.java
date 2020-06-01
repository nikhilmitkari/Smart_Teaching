package com.example.kc_academy;

import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
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
import com.google.firebase.storage.StorageReference;


/**
 * A simple {@link Fragment} subclass.
 */
public class PaymentsFragment extends Fragment {
    FirebaseAuth firebaseAuth;
    FirebaseUser user1;
    FirebaseDatabase firebaseDatabase;
    StorageReference mStorageref;
    DatabaseReference myref;
RelativeLayout layoutParams;
    TextView tvclass, tvpattern;
    Button btnUpdted;

    final PaymentsFragment context = this;
    private EditText result;

    public PaymentsFragment(EditText result) {
        // Required empty public constructor
        this.result = result;
    }

    public PaymentsFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootview = inflater.inflate(R.layout.fragment_payments, container, false);
     tvclass = rootview.findViewById(R.id.selected_class);
     tvpattern=  rootview.findViewById(R.id.selected_pattern);
     btnUpdted = rootview.findViewById(R.id.btn_updated);


        firebaseAuth = FirebaseAuth.getInstance();
        user1 = firebaseAuth.getCurrentUser();
        firebaseDatabase = FirebaseDatabase.getInstance();
        myref = firebaseDatabase.getReference("Users");

        Query query = myref.orderByChild("email").equalTo(user1.getEmail());
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    //
                    String s_class = "" + ds.child("class").getValue();
                    String s_pattern = "" + ds.child("pattern").getValue();


                    tvclass.setText(s_class);
                    tvpattern.setText(s_pattern);
                    tvpattern.setTextSize(18);
                    tvpattern.setGravity(View.TEXT_ALIGNMENT_GRAVITY);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });

        btnUpdted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(),"Please update in Edit Profile Page",Toast.LENGTH_SHORT).show();
            }
        });

        return rootview;
    }




    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }
}