package com.logixhunt.shikhaaquapartner.utils;

import android.text.InputFilter;
import android.text.Spanned;

public class MaxValueInputFilter implements InputFilter {
    private int maxValue;

    public MaxValueInputFilter(int maxValue) {
        this.maxValue = maxValue;
    }

    @Override
    public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
        try {
            String input = dest.subSequence(0, dstart) + source.toString() + dest.subSequence(dend, dest.length());
            int value = Integer.parseInt(input);
            if (value <= maxValue) {
                return null; // Input is valid, allow it
            }
        } catch (NumberFormatException ignored) {
        }
        return ""; // Input is invalid, don't allow it
    }
}