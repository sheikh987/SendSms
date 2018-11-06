package com.example.sheikh.sendsms;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class FragmentOtp extends android.support.v4.app.Fragment {

    View v;

    public FragmentOtp() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.otp_fragment,container,false);
        return v;
    }
}
