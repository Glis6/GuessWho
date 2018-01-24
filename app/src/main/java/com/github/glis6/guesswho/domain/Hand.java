package com.github.glis6.guesswho.domain;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;

/**
 * @author Glis
 */
public class Hand {
    /**
     * The characters that are still in the game.
     */
    @Getter
    private final List<String> removedCharacters = new ArrayList<>();

    /**
     * The character that the opponent has to guess.
     */
    @Getter
    private final Character character;

    /**
     * The {@link CharacterLibrary} that does most of the lookup work.
     */
    private final Game game;

    /**
     * @param character The character that the opponent has to guess.
     *                  @param game The game that the hand is a part of.
     */
    Hand(Character character, Game game) {
        this.character = character;
        this.game = game;
    }

    /**
     * @param character The {@link Character} to remove.
     * @return Whether or not the character has been removed.
     */
    public boolean removeCharacter(final Character character) {
        return !removedCharacters.contains(character.getName()) && removedCharacters.add(character.getName());
    }

    /**
     * @return A {@link List} of all {@link Character}s still in the game.
     */
    public List<Character> getCharacters() {
        return game.getCharacterLibrary().getFilteredCharacters(removedCharacters);
    }
}
