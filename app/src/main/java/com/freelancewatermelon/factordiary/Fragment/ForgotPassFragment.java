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
import android.widget.EditText;
import android.widget.TextView;

import com.freelancewatermelon.factordiary.Common.Utils;
import com.freelancewatermelon.factordiary.Interface.SignInCallbackInterface;
import com.freelancewatermelon.factordiary.R;

import net.steamcrafted.materialiconlib.MaterialDrawableBuilder;

/**
 * A simple {@link Fragment} subclass.
 */
public class ForgotPassFragment extends Fragment {

    private FloatingActionButton fab;
    private Toolbar toolbar;
    private String email;
    private EditText ed_email;
    private Bundle args;

    private SignInCallbackInterface mCallback;

    public ForgotPassFragment() {
        // Required empty public constructor
    }

    public static ForgotPassFragment newInstance(Toolbar toolbar, FloatingActionButton fab, Bundle bundle) {
        ForgotPassFragment f = new ForgotPassFragment();
        // TODO do we need to pass some args here?
        f.fab = fab;
        f.toolbar = toolbar;
        f.setArguments(bundle);
        return f;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_forgot_pass, container, false);

        args = getArguments();

        ed_email = (EditText) view.findViewById(R.id.et_email);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO field validation
                email = ed_email.getText().toString();
                mCallback.onForgotPassClick(email);
            }
        });

        forgotPassToolbar();
        fab.setImageDrawable(Utils.getMaterialIconDrawable(getActivity(), MaterialDrawableBuilder.IconValue.ARROW_RIGHT, R.color.colorTextWhite));
        fab.show();
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

    private void forgotPassToolbar() {
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
                        LoginFragment loginFragment = LoginFragment.newInstance(toolbar, fab);
                        fragmentTransaction.replace(R.id.sign_in_fragment_container, loginFragment);
                        fragmentTransaction.commit();
                    }
                }
        );

        TextView tv = (TextView) toolbar.findViewById(R.id.tv_toolbar_txt);
        tv.setText("");
    }
}
