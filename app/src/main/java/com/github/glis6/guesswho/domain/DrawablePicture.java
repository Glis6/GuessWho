package com.github.glis6.guesswho.domain;

import android.graphics.drawable.Drawable;

/**
 * @author Glis
 */
public final class DrawablePicture implements Picture {
    /**
     * The drawable that contains the picture.
     */
    private final Drawable drawable;

    /**
     * @param drawable The drawable that contains the picture.
     */
    public DrawablePicture(Drawable drawable) {
        this.drawable = drawable;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Drawable toDrawable() {
        return drawable;
    }
}
