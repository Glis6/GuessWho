package com.github.glis6.guesswho.domain;

import com.github.glis6.guesswho.domain.traits.EyesColor;
import com.github.glis6.guesswho.domain.traits.HairColor;

import java.util.Arrays;
import java.util.List;

/**
 * @author Glis
 */
public class DefaultTraits implements PossibleTraitProvider {
    /**
     * A trait for blue eyes.
     */
    final static Trait BLUE_EYES = new EyesColor("blue");

    /**
     * A trait for brown eyes.
     */
    final static Trait BROWN_EYES = new EyesColor("brown");

    /**
     * A trait for green eyes.
     */
    final static Trait GREEN_EYES = new EyesColor("green");

    /**
     * A trait for blond hair.
     */
    final static Trait BLOND_HAIR = new HairColor("blond");

    /**
     * A trait for brown hair.
     */
    final static Trait BROWN_HAIR = new HairColor("brown");

    /**
     * A trait for black hair.
     */
    final static Trait BLACK_HAIR = new HairColor("black");

    /**
     * {@inheritDoc}
     */
    public List<Trait> getPossibleTraits() {
        return Arrays.asList(
                BLUE_EYES,
                BROWN_EYES,
                GREEN_EYES,
                BLOND_HAIR,
                BROWN_HAIR,
                BLACK_HAIR
        );
    }
}
