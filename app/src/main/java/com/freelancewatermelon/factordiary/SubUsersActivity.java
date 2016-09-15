package com.freelancewatermelon.factordiary;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.freelancewatermelon.factordiary.Fragment.SubUsersEmptyFragment;
import com.freelancewatermelon.factordiary.Interface.SubUsersCallbackInterface;

public class SubUsersActivity extends AppCompatActivity implements SubUsersCallbackInterface{
    private Toolbar toolbar;
    private FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_users);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        fab = (FloatingActionButton) findViewById(R.id.fab);

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
        // TODO
    }

    @Override
    public void deleteSubuser() {
        // TODO
    }
}
