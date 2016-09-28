package com.freelancewatermelon.factordiary;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import com.freelancewatermelon.factordiary.Fragment.SubUsersEmptyFragment;
import com.freelancewatermelon.factordiary.Fragment.SubUsersFragment;
import com.freelancewatermelon.factordiary.Interface.SubUsersCallbackInterface;
import com.freelancewatermelon.factordiary.Model.SubUser;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SubUsersActivity extends AppCompatActivity implements SubUsersCallbackInterface, GoogleApiClient.OnConnectionFailedListener {

    private static final String TAG = "SubUsersActivity";

    private Toolbar toolbar;
    private FloatingActionButton fab;

    // Firebase instance variables
    private FirebaseAuth mFirebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private FirebaseDatabase database;
    private DatabaseReference ref;

    private String mUsername;
    private GoogleApiClient mGoogleApiClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_users);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        fab = (FloatingActionButton) findViewById(R.id.fab);

        setSupportActionBar(toolbar);
        //Initialize FirebaseAuth
        mFirebaseAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        database.setPersistenceEnabled(true); // enable offline capabilities
        ref = database.getReference();

        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this /* FragmentActivity */, this /* OnConnectionFailedListener */)
                .addApi(Auth.GOOGLE_SIGN_IN_API)
                .build();
        showSubUsersFragment();
    }

    private void showSubUsersEmptyFragment() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        // Show sub users empty fragment
        SubUsersEmptyFragment subUsersEmptyFragment = SubUsersEmptyFragment.newInstance(toolbar, fab);
        fragmentTransaction.replace(R.id.sign_in_fragment_container, subUsersEmptyFragment);
        fragmentTransaction.commit();
    }

    private void showSubUsersFragment() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        // Show sub users fragment
        SubUsersFragment subUsersFragment = SubUsersFragment.newInstance(toolbar, fab);
        fragmentTransaction.replace(R.id.sign_in_fragment_container, subUsersFragment);
        fragmentTransaction.commit();
    }

    @Override
    public void addSubUser(String name, String last_name) {
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
        // TODO delete subuser
    }

    @Override
    public void signOut() {
        mFirebaseAuth.signOut();
        Auth.GoogleSignInApi.signOut(mGoogleApiClient);
        //mUsername = ANONYMOUS;
        startActivity(new Intent(SubUsersActivity.this, SignInActivity.class));
        finish();
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        // TODO User sign out connection fail
    }
}
