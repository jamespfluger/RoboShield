package com.jamespfluger.roboshield.utils;

import android.text.Editable;
import android.text.TextWatcher;

public class ExtendedPhoneNumberFormattingTextWatcher implements TextWatcher {
    private boolean isRunning = false;
    private boolean isDeleting = false;

    @Override
    public void beforeTextChanged(CharSequence charSequence, int start, int count, int after) {
        isDeleting = count > after;
    }

    @Override
    public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
    }

    @Override
    public void afterTextChanged(Editable editable) {
        if (isRunning || isDeleting) {
            return;
        }
        isRunning = true;

        editable.replace(0, editable.length(), FormatUtil.formatPhoneNumber(editable.toString().replaceAll("[^0-9]+", "")));

        isRunning = false;
    }
}