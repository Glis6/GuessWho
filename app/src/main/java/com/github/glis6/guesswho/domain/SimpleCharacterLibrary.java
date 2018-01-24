package com.github.glis6.guesswho.domain;

import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * @author Glis
 */
public class SimpleCharacterLibrary implements CharacterLibrary {
    private final List<Character> characters = new ArrayList<>(Arrays.asList(
            new Character("Gilles", new NoPicture(), Arrays.asList(
                    DefaultTraits.BROWN_EYES,
                    DefaultTraits.BLOND_HAIR)
            ),
            new Character("Lydia", new NoPicture(), Arrays.asList(
                    DefaultTraits.BROWN_EYES,
                    DefaultTraits.BROWN_HAIR)
            ))
    );

    @Override
    public List<Character> getAllCharacters() {
        return characters;
    }

    @Override
    public List<Character> getFilteredCharacters(@NonNull Collection<String> filteredNames) {
        final List<Character> resultList = new ArrayList<>();
        for (Character character : characters) {
            boolean found = false;
            for (String filteredName : filteredNames) {
                if(character.getName().equalsIgnoreCase(filteredName)) {
                    found = true;
                    break;
                }
            }
            if(!found) {
                resultList.add(character);
            }
        }
        return resultList;
    }

    @Override
    public boolean removeCharacter(@NonNull Character character) {
        return characters.remove(character);
    }

    @Override
    public void addCharacter(@NonNull Character character) {
        characters.add(character);
    }
}
