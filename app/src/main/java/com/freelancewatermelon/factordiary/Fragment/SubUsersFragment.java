package com.freelancewatermelon.factordiary.Fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.freelancewatermelon.factordiary.Common.Utils;
import com.freelancewatermelon.factordiary.Interface.SubUsersCallbackInterface;
import com.freelancewatermelon.factordiary.Model.SubUser;
import com.freelancewatermelon.factordiary.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import net.steamcrafted.materialiconlib.MaterialDrawableBuilder;

import org.w3c.dom.Comment;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * A simple {@link Fragment} subclass.
 */
public class SubUsersFragment extends Fragment {
    private Toolbar toolbar;
    private FloatingActionButton fab;
    private SubUsersCallbackInterface mCallback;

    private String TAG = "SubUsersFragment";
    private List<String> subUsersList = new ArrayList<>();

    // Firebase instance variables
    private FirebaseAuth mFirebaseAuth;
    private FirebaseDatabase database;
    private DatabaseReference ref;

    public SubUsersFragment() {
        // Required empty public constructor
    }

    public static SubUsersFragment newInstance(Toolbar toolbar, FloatingActionButton fab) {
        SubUsersFragment f = new SubUsersFragment();
        f.toolbar = toolbar;
        f.fab = fab;

        // TODO do we need to pass some args here?
        //Bundle args = new Bundle();


        return f;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        // This makes sure that the container activity has implemented
        // the callback interface. If not, it throws an exception
        try {
            mCallback = (SubUsersCallbackInterface) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement SignInCallbackInterface");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sub_users, container, false);

        //Initialize FirebaseAuth
        mFirebaseAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        ref = database.getReference();

        // Inflate the layout for this fragment
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //SubUsersNameFragment
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                // Show SubUsersNameFragment
                SubUsersNameFragment subUsersNameFragment = SubUsersNameFragment.newInstance(toolbar, fab);
                fragmentTransaction.replace(R.id.sign_in_fragment_container, subUsersNameFragment);
                fragmentTransaction.commit();
            }
        });

        subUsersToolbar();
        fab.setImageDrawable(Utils.getMaterialIconDrawable(getActivity(), MaterialDrawableBuilder.IconValue.ACCOUNT_PLUS, R.color.colorTextWhite));
        fab.show();

        // TODO get subusers from Firebase RealTime database

        if (mFirebaseAuth.getCurrentUser() != null) {
            final Query subUsersQuery = ref.child(mFirebaseAuth.getCurrentUser().getUid());

            subUsersQuery.addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                    Log.d(TAG, dataSnapshot.getKey());
                    subUsersList.add(dataSnapshot.getKey());
                    Log.d(TAG, "" + subUsersList.size());
                }

                @Override
                public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                }

                @Override
                public void onChildRemoved(DataSnapshot dataSnapshot) {
                }

                @Override
                public void onChildMoved(DataSnapshot dataSnapshot, String s) {
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
                // TODO: implement the ChildEventListener methods as documented above
                // ...
            });
        }


        return view;
    }

    public void subUsersToolbar() {
        toolbar.setTitle("");
        //setSupportActionBar(toolbar);

        toolbar.setNavigationIcon(Utils.getMaterialIconDrawable(getActivity(), MaterialDrawableBuilder.IconValue.CLOSE, R.color.colorTextWhite));
        toolbar.setNavigationOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        getActivity().finish();
                    }
                }
        );

        TextView tv = (TextView) toolbar.findViewById(R.id.tv_toolbar_txt);
        tv.setVisibility(View.GONE);
    }
}
