package com.freelancewatermelon.factordiary.Dialogs;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;

import com.freelancewatermelon.factordiary.R;
import com.wang.avi.AVLoadingIndicatorView;

/**
 * Created by 1 on 9/15/2016.
 */
public class CustomProgressDialog extends ProgressDialog {
    AVLoadingIndicatorView loadingIndicatorView;

    public CustomProgressDialog(Context context, int theme) {
        super(context, theme);
    }


    public static ProgressDialog newInstance(Context context) {
        CustomProgressDialog dialog = new CustomProgressDialog(context, R.style.CustomProgressDialogTheme);
        dialog.setIndeterminate(true);

        dialog.setCancelable(false);
        return dialog;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Add custom layout
        setContentView(R.layout.dialog_custom_progress);
        loadingIndicatorView = (AVLoadingIndicatorView) findViewById(R.id.avi);
    }

    @Override
    public void show() {
        super.show();
        loadingIndicatorView.show();
    }

    @Override
    public void dismiss() {
        super.dismiss();
        loadingIndicatorView.hide();
    }
}
