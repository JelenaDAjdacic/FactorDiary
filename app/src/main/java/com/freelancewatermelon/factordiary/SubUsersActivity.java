package com.freelancewatermelon.factordiary;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import com.freelancewatermelon.factordiary.Fragment.SubUsersEmptyFragment;
import com.freelancewatermelon.factordiary.Interface.SubUsersCallbackInterface;
import com.freelancewatermelon.factordiary.Model.SubUser;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class SubUsersActivity extends AppCompatActivity implements SubUsersCallbackInterface {
    private Toolbar toolbar;
    private FloatingActionButton fab;

    // Firebase instance variables
    private FirebaseAuth mFirebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private FirebaseDatabase database;
    private DatabaseReference ref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_users);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        fab = (FloatingActionButton) findViewById(R.id.fab);

        //Initialize FirebaseAuth
        mFirebaseAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        ref = database.getReference();


        // TODO pull subUsers
        // If subUsers == 0 show empty fragment

        // TODO show appropriate fragment
        showSubUsersEmptyFragment();

        setSupportActionBar(toolbar);

    }

    private void showSubUsersEmptyFragment() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        // Show sub users empty fragment
        SubUsersEmptyFragment subUsersEmptyFragment = SubUsersEmptyFragment.newInstance(toolbar, fab);
        fragmentTransaction.replace(R.id.sign_in_fragment_container, subUsersEmptyFragment);
        fragmentTransaction.commit();
    }

    @Override
    public void addSubUser(String name, String last_name) {
        // TODO add user
        Toast.makeText(this, "User Add...", Toast.LENGTH_SHORT).show();
        //Getting values to store
        //Creating SubUser object
        SubUser subUser = new SubUser();

        //Adding values
        subUser.setFirstName(name);
        subUser.setLastName(last_name);

        if (mFirebaseAuth.getCurrentUser() != null) {
            ref.child(mFirebaseAuth.getCurrentUser().getUid()).child(name + " " + last_name).setValue(subUser);
        }

    }

    @Override
    public void deleteSubuser() {
        // TODO
    }
}
