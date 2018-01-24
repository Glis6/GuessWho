package com.github.glis6.guesswho.domain;

import java.util.List;

import lombok.Data;

/**
 * @author Glis
 */
@Data
public class Character {
    /**
     * The ID of the character.
     */
    private long id = -1;

    /**
     * The name of the character.
     */
    private String name;

    /**
     * The picture of the character.
     */
    private Picture picture;

    /**
     * The traits the character has.
     */
    private List<Trait> traits;

    /**
     * @return A description generated from the traits.
     */
    public String getDescription() {
        final StringBuilder stringBuilder = new StringBuilder();
        for (Trait trait : traits) {
            if(stringBuilder.length() != 0) {
                stringBuilder.append(", ");
            }
            stringBuilder.append(trait.displayTrait());
        }
        return stringBuilder.toString();
    }

    /**
     * @param name The name of the character.
     * @param picture The {@link Picture} of the character.
     * @param traits The traits of the character.
     */
    public Character(String name, Picture picture, List<Trait> traits) {
        this.name = name;
        this.picture = picture;
        this.traits = traits;
    }

    /**
     * @param id The ID of the character.
     * @param name The name of the character.
     * @param picture The {@link Picture} of the character.
     * @param traits The traits of the character.
     */
    public Character(long id, String name, Picture picture, List<Trait> traits) {
        this.id = id;
        this.name = name;
        this.picture = picture;
        this.traits = traits;
    }
}
