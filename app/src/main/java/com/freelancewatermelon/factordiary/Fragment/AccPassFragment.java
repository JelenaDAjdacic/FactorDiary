package com.freelancewatermelon.factordiary.Fragment;


import android.content.Context;
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
import com.freelancewatermelon.factordiary.Interface.SignInCallbackInterface;
import com.freelancewatermelon.factordiary.R;

import net.steamcrafted.materialiconlib.MaterialDrawableBuilder;

/**
 * A simple {@link Fragment} subclass.
 */
public class AccPassFragment extends Fragment {

    private TextView tv_passToggle;
    private FloatingActionButton fab;
    private Toolbar toolbar;

    private SignInCallbackInterface mCallback;

    public AccPassFragment() {
        // Required empty public constructor
    }

    public static AccPassFragment newInstance(Toolbar toolbar, FloatingActionButton fab) {
        AccPassFragment f = new AccPassFragment();
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
        View view = inflater.inflate(R.layout.fragment_acc_pass, container, false);
        tv_passToggle = (TextView) view.findViewById(R.id.tv_pass_toggle_label);
        tv_passToggle.setTag(0);
        tv_passToggle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO toggle pass visibility
                if ((Integer) view.getTag() == 0) {
                    tv_passToggle.setText(R.string.pass_toggle_label_hide);
                    tv_passToggle.setTag(1);
                } else if ((Integer) view.getTag() == 1) {
                    tv_passToggle.setText(R.string.pass_toggle_label_show);
                    tv_passToggle.setTag(0);
                }
            }
        });

        // Inflate the layout for this fragment
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCallback.onCreateAccClick();
            }
        });

        accPassToolbar();
        fab.setImageDrawable(Utils.getMaterialIconDrawable(getActivity(), MaterialDrawableBuilder.IconValue.ARROW_RIGHT, R.color.colorTextWhite));
        fab.show();
        // Inflate the layout for this fragment
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        // This makes sure that the container activity has implemented
        // the callback interface. If not, it throws an exception
        try {
            mCallback = (SignInCallbackInterface) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement SignInCallbackInterface");
        }
    }

    private void accPassToolbar() {
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
                        AccEmailFragment accEmailFragment = AccEmailFragment.newInstance(toolbar, fab);
                        fragmentTransaction.replace(R.id.sign_in_fragment_container, accEmailFragment);
                        fragmentTransaction.commit();
                    }
                }
        );

        TextView tv = (TextView) toolbar.findViewById(R.id.tv_toolbar_txt);
        tv.setText("");
    }
}
