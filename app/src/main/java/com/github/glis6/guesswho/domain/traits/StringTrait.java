package com.github.glis6.guesswho.domain.traits;

import com.github.glis6.guesswho.domain.Trait;

/**
 * @author Glis
 */
public final class StringTrait implements Trait {
    /**
     * The string for this trait.
     */
    private final String trait;

    /**
     * @param trait The string for this trait.
     */
    public StringTrait(String trait) {
        this.trait = trait;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String displayTrait() {
        return trait;
    }
}
