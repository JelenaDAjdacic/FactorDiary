package com.freelancewatermelon.factordiary;

import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.freelancewatermelon.factordiary.Model.SubUser;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {
    public static final String ANONYMOUS = "anonymous";
    // Firebase instance variables
    private FirebaseAuth mFirebaseAuth;
    private FirebaseUser mFirebaseUser;
    private FirebaseDatabase database;
    private DatabaseReference ref;

    private String mUsername = " ";
    private String TAG = "MainActivity";
    private Query subUsersQuery;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Set default username is anonymous.
        mUsername = ANONYMOUS;
        // Initialize Firebase Auth
        mFirebaseAuth = FirebaseAuth.getInstance();
        mFirebaseUser = mFirebaseAuth.getCurrentUser();
        if (mFirebaseUser == null) {
            // Not signed in, launch the Sign In activity
            startActivity(new Intent(this, SignInActivity.class));
            finish();
            return;
        } else {
            mUsername = mFirebaseUser.getDisplayName();
            if (mFirebaseUser.getEmail() != null) {
                Toast.makeText(this, mFirebaseUser.getEmail(), Toast.LENGTH_LONG).show();
            }
            database = FirebaseDatabase.getInstance();
            ref = database.getReference();
            subUsersQuery = ref.child(mFirebaseAuth.getCurrentUser().getUid());
            subUsersQuery.orderByChild("active").equalTo(false).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    Log.d(TAG, dataSnapshot.getKey());
                    final SubUser subUser = dataSnapshot.getValue(SubUser.class);
                    if (subUser != null) {
                        Log.d(TAG, subUser.toString());
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
            startActivity(new Intent(this, SubUsersActivity.class));
            finish();
            return;
        }


    }

}
