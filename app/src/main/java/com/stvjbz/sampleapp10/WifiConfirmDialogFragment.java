package com.stvjbz.sampleapp10;

import android.app.DialogFragment;
import android.os.Bundle;

/**
 * Created by stvjbz on 16/05/18.
 */
public class WifiConfirmDialogFragment extends DialogFragment {
    public static final String ARG_TITLE = "title";
    private static final String ARG_MESSAGE = "message";

    private int mTitle;
    private int mMessage;

    public static WifiConfirmDialogFragment newInstance(int title, int message) {
        WifiConfirmDialogFragment fragment = new WifiConfirmDialogFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_TITLE, title);
        args.putInt(ARG_MESSAGE, message);
        fragment.setArguments(args);
        return fragment;
    }
}
