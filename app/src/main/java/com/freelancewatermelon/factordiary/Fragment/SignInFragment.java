package com.freelancewatermelon.factordiary.Fragment;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.freelancewatermelon.factordiary.Common.Utils;
import com.freelancewatermelon.factordiary.Interface.SignInCallbackInterface;
import com.freelancewatermelon.factordiary.R;

import net.steamcrafted.materialiconlib.MaterialDrawableBuilder;

public class SignInFragment extends Fragment {
    private Button mGmailLoginButton;
    private Button mCreateAcc;
    private SignInCallbackInterface mCallback;
    private Toolbar toolbar;
    private FloatingActionButton fab;

    public SignInFragment() {
        // Required empty public constructor
    }

    public static SignInFragment newInstance(Toolbar toolbar, FloatingActionButton fab) {
        SignInFragment f = new SignInFragment();
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

        View view = inflater.inflate(R.layout.fragment_sign_in, container, false);

        // Assign fields
        mGmailLoginButton = (Button) view.findViewById(R.id.gmail_sign_in_button);
        mCreateAcc = (Button) view.findViewById(R.id.create_acc_button);

        // Add button Left drawable (Google icon)
        // The method returns a MaterialDrawable, but as it is private to the builder you'll have to store it as a regular Drawable ;)
        Drawable myLeftBtnIcon = MaterialDrawableBuilder.with(getActivity()) // provide a context
                .setIcon(MaterialDrawableBuilder.IconValue.GOOGLE) // provide an icon
                .setColor(ContextCompat.getColor(getActivity(), R.color.colorBackground)) // set the icon color
                .setToActionbarSize() // set the icon size
                .build(); // Finally call build
        mGmailLoginButton.setCompoundDrawablesWithIntrinsicBounds(myLeftBtnIcon, null, null, null);

        // Set click listeners
        mGmailLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCallback.onSignInWGoogleClick();
            }
        });

        mCreateAcc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                // Show signIn fragment
                AccNameFragment accNameFragment = AccNameFragment.newInstance(toolbar, fab);
                fragmentTransaction.replace(R.id.sign_in_fragment_container, accNameFragment);
                fragmentTransaction.commit();
            }
        });

        signInToolbar();
        fab.hide();
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

    public void signInToolbar() {
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
        tv.setText(R.string.login);
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                // Show Log in fragment
                LoginFragment logInFragment = LoginFragment.newInstance(toolbar, fab);
                fragmentTransaction.replace(R.id.sign_in_fragment_container, logInFragment);
                fragmentTransaction.commit();

            }
        });
    }
}
