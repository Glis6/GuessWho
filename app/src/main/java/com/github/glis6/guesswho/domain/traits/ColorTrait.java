package com.github.glis6.guesswho.domain.traits;

import com.github.glis6.guesswho.domain.Trait;

/**
 * @author Glis
 */
public abstract class ColorTrait implements Trait {
    /**
     * The color of the eyes.
     */
    private final String color;

    /**
     * @param color The color of the eyes.
     */
    protected ColorTrait(String color) {
        this.color = color;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final String displayTrait() {
        return color.toLowerCase() + " " + getClass().getSimpleName().toLowerCase().replace("color", "");
    }
}
