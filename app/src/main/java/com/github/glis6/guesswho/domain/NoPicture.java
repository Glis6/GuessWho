package com.github.glis6.guesswho.domain;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;

/**
 * @author Glis
 */
public final class NoPicture implements Picture {
    /**
     * {@inheritDoc}
     */
    @Override
    public Drawable toDrawable() {
        return new ColorDrawable(Color.TRANSPARENT);
    }
}
