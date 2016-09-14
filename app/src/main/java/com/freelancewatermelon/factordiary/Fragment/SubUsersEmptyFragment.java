package com.freelancewatermelon.factordiary.Fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.freelancewatermelon.factordiary.Interface.SubUsersCallbackInterface;
import com.freelancewatermelon.factordiary.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class SubUsersEmptyFragment extends Fragment {
    private Toolbar toolbar;
    private FloatingActionButton fab;
    private SubUsersCallbackInterface mCallback;

    public SubUsersEmptyFragment() {
        // Required empty public constructor
    }

    public static SubUsersEmptyFragment newInstance(Toolbar toolbar, FloatingActionButton fab) {
        SubUsersEmptyFragment f = new SubUsersEmptyFragment();
        f.toolbar = toolbar;
        f.fab = fab;
        // TODO do we need to pass some args here?
        //Bundle args = new Bundle();
        return f;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sub_users_empty, container, false);
        return view;
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
}
