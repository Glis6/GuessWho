package com.github.glis6.guesswho.fragments;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.glis6.guesswho.R;
import com.github.glis6.guesswho.domain.Character;

/**
 * @author Glis
 */

public class PlayerCharacter extends Fragment {
    /**
     * The picture of the character.
     */
    private ImageView picture;

    /**
     * The name of the character.
     */
    private TextView name;

    /**
     * The description of the character.
     */
    private TextView description;

    /**
     * The character that we are displaying.
     */
    private Character character;

    /**
     * {@inheritDoc}
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_player_character, container, false);
        picture = v.findViewById(R.id.picture);
        name = v.findViewById(R.id.name);
        description = v.findViewById(R.id.description);
        refreshCharacter();
        return v;
    }

    /**
     * @param character The character to set.
     */
    public void setCharacter(final @NonNull Character character) {
        this.character = character;
        refreshCharacter();
    }

    /**
     * The character to refresh.
     */
    public void refreshCharacter() {
        if(picture == null || name == null || description == null) {
            return;
        }
        if(character == null) {
            picture.setImageDrawable(new ColorDrawable(Color.TRANSPARENT));
            name.setText("");
            description.setText("");
        } else {
            picture.setImageDrawable(character.getPicture().toDrawable());
            name.setText(character.getName());
            description.setText(character.getDescription());
        }
    }
}
