package com.freelancewatermelon.factordiary.Fragment;


import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.freelancewatermelon.factordiary.Common.Utils;
import com.freelancewatermelon.factordiary.R;

import net.steamcrafted.materialiconlib.MaterialDrawableBuilder;

/**
 * A simple {@link Fragment} subclass.
 */
public class AccEmailFragment extends Fragment {

    private FloatingActionButton fab;
    private Toolbar toolbar;

    public AccEmailFragment() {
        // Required empty public constructor
    }

    public static AccEmailFragment newInstance(Toolbar toolbar, FloatingActionButton fab) {
        AccEmailFragment f = new AccEmailFragment();
        // TODO do we need to pass some args here?
        f.fab = fab;
        f.toolbar = toolbar;
        //Bundle args = new Bundle();
        return f;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO field validation

                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                AccPassFragment accPassFragment = AccPassFragment.newInstance(toolbar, fab);
                fragmentTransaction.replace(R.id.sign_in_fragment_container, accPassFragment);
                fragmentTransaction.commit();
            }
        });

        accEmailToolbar();
        fab.setImageDrawable(Utils.getMaterialIconDrawable(getActivity(), MaterialDrawableBuilder.IconValue.ARROW_RIGHT, R.color.colorTextWhite));
        fab.show();
        return inflater.inflate(R.layout.fragment_acc_email, container, false);
    }

    private void accEmailToolbar() {
        toolbar.setTitle("");
        //setSupportActionBar(toolbar);

        toolbar.setNavigationIcon(Utils.getMaterialIconDrawable(getActivity(), MaterialDrawableBuilder.IconValue.ARROW_LEFT, R.color.colorTextWhite));
        toolbar.setNavigationOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //showSignInFragment();
                        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                        // Show signIn fragment
                        AccNameFragment accNameFragment = AccNameFragment.newInstance(toolbar, fab);
                        fragmentTransaction.replace(R.id.sign_in_fragment_container, accNameFragment);
                        fragmentTransaction.commit();
                    }
                }
        );

        TextView tv = (TextView) toolbar.findViewById(R.id.tv_toolbar_txt);
        tv.setText("");
    }
}
