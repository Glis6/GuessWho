package com.github.glis6.guesswho.activities;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.github.glis6.guesswho.R;
import com.github.glis6.guesswho.domain.Character;
import com.github.glis6.guesswho.domain.CharacterLibrary;
import com.github.glis6.guesswho.domain.SimpleCharacterLibrary;
import com.github.glis6.guesswho.fragments.AddCharacter;
import com.github.glis6.guesswho.fragments.CharacterInformation;
import com.github.glis6.guesswho.fragments.CharacterList;
import com.github.glis6.guesswho.interfaces.OnAddButtonPress;
import com.github.glis6.guesswho.services.ServiceManagerProvider;

/**
 * @author Glis
 */
public class SettingsActivity extends AppCompatActivity implements CharacterList.OnSelect, CharacterList.OnSwipeLeft, AddCharacter.SaveCharacter, OnAddButtonPress {
    /**
     * The characterList that we are displaying.
     */
    private CharacterList characterList = new CharacterList();

    /**
     * The {@link CharacterLibrary} that we're editing.
     */
    private CharacterLibrary characterLibrary = new SimpleCharacterLibrary();

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getApplication() instanceof ServiceManagerProvider) {
            characterLibrary = ((ServiceManagerProvider) getApplication()).getServiceManager().getCharacterLibrary();
        }
        setContentView(R.layout.activity_settings);
        if (findViewById(R.id.frame) != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.frame, characterList)
                    .commit();
        }
        if (findViewById(R.id.second_frame) == null && getSupportFragmentManager().findFragmentById(R.id.second_frame) != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .remove(getSupportFragmentManager().findFragmentById(R.id.second_frame))
                    .commit();
        }
        final FloatingActionButton addButton = findViewById(R.id.add_character);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Fragment secondFrameFragment = getSupportFragmentManager().findFragmentById(R.id.second_frame);
                if (secondFrameFragment != null && secondFrameFragment instanceof OnAddButtonPress) {
                    ((OnAddButtonPress) secondFrameFragment).onPress();
                } else {
                    final Fragment frameFragment = getSupportFragmentManager().findFragmentById(R.id.frame);
                    if (frameFragment != null && frameFragment instanceof OnAddButtonPress) {
                        ((OnAddButtonPress) frameFragment).onPress();
                    } else {
                        onPress();
                    }
                }
            }
        });
        characterList.setCharacters(characterLibrary.getAllCharacters());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onSwipeLeft(Character character) {
        if (characterLibrary.removeCharacter(character)) {
            Toast.makeText(this, String.format("%s has been removed.", character.getName()), Toast.LENGTH_SHORT).show();
        }
        characterList.setCharacters(characterLibrary.getAllCharacters());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onSelect(Character character) {
        final CharacterInformation characterInformation = new CharacterInformation();
        //If we have 2 fragments
        if (findViewById(R.id.second_frame) != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.second_frame, characterInformation)
                    .commit();
        } else {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.frame, characterInformation)
                    .addToBackStack(null)
                    .commit();
        }
        characterInformation.setCharacter(character);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void saveCharacter(@NonNull Character character) {
        characterLibrary.addCharacter(character);
        characterList.setCharacters(characterLibrary.getAllCharacters());
        if (findViewById(R.id.second_frame) != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .remove(getSupportFragmentManager().findFragmentById(R.id.second_frame))
                    .commit();
        } else {
            getSupportFragmentManager().popBackStack();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onPress() {
        final AddCharacter addCharacter = new AddCharacter();
        //If we have 2 fragments
        if (findViewById(R.id.second_frame) != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.second_frame, addCharacter)
                    .commit();
        } else {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.frame, addCharacter)
                    .addToBackStack(null)
                    .commit();
        }
    }
}
