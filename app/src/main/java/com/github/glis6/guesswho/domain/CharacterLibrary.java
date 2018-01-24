package com.github.glis6.guesswho.domain;

import android.support.annotation.NonNull;

import java.util.Collection;
import java.util.List;

/**
 * @author Glis
 */
public interface CharacterLibrary {
    /**
     * @return A {@link Collection} of all {@link Character}s.
     */
    List<Character> getAllCharacters();

    /**
     * @param filteredNames A {@link Collection} of names to filter out.
     * @return A {@link List} of {@link Character}s that do not have any of the {@link Trait}s.
     */
    List<Character> getFilteredCharacters(final @NonNull Collection<String> filteredNames);

    /**
     * @param character The {@link Character} to remove.
     * @return Whether or not the character got removed.
     */
    boolean removeCharacter(final @NonNull Character character);

    /**
     * @param character The {@link Character} to add.
     */
    void addCharacter(final @NonNull Character character);
}
