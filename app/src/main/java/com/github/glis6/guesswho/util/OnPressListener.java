package com.github.glis6.guesswho.util;

import android.view.View;

/**
 * @author Glis
 */
@FunctionalInterface
public interface OnPressListener {
    void onPress(View view, int position);
}
