package com.github.glis6.guesswho.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.github.glis6.guesswho.fragments.CharacterInformation;
import com.github.glis6.guesswho.fragments.CharacterList;
import com.github.glis6.guesswho.R;
import com.github.glis6.guesswho.domain.Character;
import com.github.glis6.guesswho.domain.Game;
import com.github.glis6.guesswho.fragments.PlayerCharacter;
import com.github.glis6.guesswho.services.ServiceManagerProvider;

public class MainActivity extends AppCompatActivity implements CharacterList.OnSwipeLeft, CharacterList.OnSelect {
    /**
     * The game that is being played.
     */
    private Game game;

    /**
     * The {@link CharacterList} that we display.
     */
    private final CharacterList characterList = new CharacterList();

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getApplication() instanceof ServiceManagerProvider) {
            game = new Game(((ServiceManagerProvider)getApplication()).getServiceManager().getCharacterLibrary());
        }
        setContentView(R.layout.activity_main);
        if (findViewById(R.id.frame) != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.frame, characterList)
                    .commit();
        }
        if (findViewById(R.id.character_frame) != null) {
            final PlayerCharacter playerCharacter = new PlayerCharacter();
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.character_frame, playerCharacter)
                    .commit();
            playerCharacter.setCharacter(game.getPlayerHand().getCharacter());
        }
        if (findViewById(R.id.second_frame) == null && getSupportFragmentManager().findFragmentById(R.id.second_frame) != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .remove(getSupportFragmentManager().findFragmentById(R.id.second_frame))
                    .commit();
        }
        characterList.setCharacters(game.getPlayerHand().getCharacters());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onSwipeLeft(Character character) {
        game.getPlayerHand().removeCharacter(character);
        characterList.setCharacters(game.getPlayerHand().getCharacters());
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
}
