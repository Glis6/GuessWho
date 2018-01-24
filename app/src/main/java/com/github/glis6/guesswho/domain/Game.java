package com.github.glis6.guesswho.domain;

import android.support.annotation.NonNull;

import java.io.Serializable;
import java.util.Random;

import lombok.AccessLevel;
import lombok.Getter;

/**
 * This class controls the game. It acts as a domain controller.
 *
 * @author Glis
 */
public final class Game implements Serializable {
    /**
     * The {@link Hand} of the player.
     */
    @Getter
    private final Hand playerHand;

    /**
     * The {@link CharacterLibrary} that does most of the lookup work.
     */
    @Getter(AccessLevel.PACKAGE)
    private final CharacterLibrary characterLibrary;

    /**
     * @param characterLibrary The {@link CharacterLibrary} that does most of the lookup work.
     */
    public Game(final @NonNull CharacterLibrary characterLibrary) {
        this.characterLibrary = characterLibrary;
        playerHand = new Hand(getRandomCharacter(), this);
    }

    /**
     * @return A random character from the library.
     */
    private Character getRandomCharacter() {
        return characterLibrary.getAllCharacters().get(new Random().nextInt(characterLibrary.getAllCharacters().size()));
    }
}
