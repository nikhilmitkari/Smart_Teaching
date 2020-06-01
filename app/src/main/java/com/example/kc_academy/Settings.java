package com.example.kc_academy;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Settings extends AppCompatActivity {

    Button btSignOut, btnRest;
       Button btndis;
       FirebaseUser user;
       FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        firebaseAuth = FirebaseAuth.getInstance();
        user = firebaseAuth.getCurrentUser();
        btSignOut = findViewById(R.id.btn_logOut);
        btndis = findViewById(R.id.sharefromStg);
        btSignOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               logout();
            }

            private void logout() {
                AlertDialog.Builder alertdialog = new AlertDialog.Builder(Settings.this);
                alertdialog.setTitle("Logout");
                alertdialog.setMessage("Are you sure you want Logout?");
                alertdialog.setIcon(R.drawable.ic_logout);
                alertdialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finish();
                        FirebaseAuth.getInstance().signOut();
                        Intent close = new Intent(Settings.this, LoginActivity.class);
                        startActivity(close);
                        Toast.makeText(Settings.this, "Successfully Logout",Toast.LENGTH_SHORT).show();
                    }
                });
                alertdialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
                alertdialog.show();
            }
        });

        btndis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(Intent.ACTION_SEND);
                myIntent.setType("text/plain");
                String shareBody = "Share Using";
                String sharesub = "Download this app from Google play store. And enjoy!";
                myIntent.putExtra(Intent.EXTRA_SUBJECT,sharesub);
                myIntent.putExtra(Intent.EXTRA_TEXT,shareBody);
                startActivity(Intent.createChooser(myIntent,"Share using"));
            }
        });

        btnRest = findViewById(R.id.btn_resetPas);
        btnRest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                
                final AlertDialog.Builder passwordResetDialog = new AlertDialog.Builder(view.getContext());
                final EditText resetPassword = new EditText(view.getContext());
                resetPassword.setHint("Enter new password");
                passwordResetDialog.setView(resetPassword);

                passwordResetDialog.setTitle("Reset Password?");
                passwordResetDialog.setIcon(R.drawable.ic_password);
                passwordResetDialog.setMessage("Enter new password\n" +
                        "Password must be minimum of 6 char");
                
                passwordResetDialog.setPositiveButton("Change", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                            String newPassword = resetPassword.getText().toString();
                            user.updatePassword(newPassword).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {

                                    Toast.makeText(Settings.this, "Password reset Successfully",Toast.LENGTH_SHORT).show();
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(Settings.this, "Somthing went Wrong",Toast.LENGTH_SHORT).show();
                                }
                            });
                    }
                });
                passwordResetDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
                passwordResetDialog.create().show();
            }
        });

    }

}

/*..........................................................................................................................................................................................................................................

 @Override
    public void onBackPressed() {
        Exit();
    }

    public void Exit() {
        new android.app.AlertDialog.Builder(this)
                .setIcon(R.mipmap.ic_launcher)
                .setTitle(getString(R.string.app_name))
                .setMessage(getString(R.string.backbutton))
                .setPositiveButton(getString(R.string.yes_dialog), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();

                                Intent i = new Intent();
                                i.setAction(Intent.ACTION_MAIN);
                                i.addCategory(Intent.CATEGORY_HOME);
                                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                startActivity(i);
                                finish();

                    }
                })
                .setNegativeButton(getString(R.string.no_dialog), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .show();
    }


......................................................................................................................................................................................................................................................... */