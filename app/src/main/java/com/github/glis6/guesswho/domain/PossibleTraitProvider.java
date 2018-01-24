package com.github.glis6.guesswho.domain;

import java.util.List;

/**
 * @author Glis
 */
public interface PossibleTraitProvider {
    /**
     * @return A {@link List} of possible traits.
     */
    List<Trait> getPossibleTraits();
}
