package com.github.glis6.guesswho.domain;

import android.graphics.drawable.Drawable;

/**
 * @author Glis
 */
public interface Picture {
    /**
     * @return Translates the picture to a {@link Drawable}.
     */
    Drawable toDrawable();
}
