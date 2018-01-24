package com.github.glis6.guesswho.services;

import com.github.glis6.guesswho.domain.CharacterLibrary;

/**
 * @author Glis
 */
public interface ServiceManager {
    /**
     * @return The currently active {@link CharacterLibrary}.
     */
    CharacterLibrary getCharacterLibrary();
}
