package com.jiek.hello;

import androidx.annotation.NonNull;

public class Hi {
    private static final String TAG = "Hi";

    @NonNull
    @Override
    public String toString() {
        return TAG + super.toString();
    }
}
