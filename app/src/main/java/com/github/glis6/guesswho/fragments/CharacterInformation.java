package com.github.glis6.guesswho.fragments;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.github.glis6.guesswho.R;
import com.github.glis6.guesswho.adaptors.TraitAdaptor;
import com.github.glis6.guesswho.domain.Character;
import com.github.glis6.guesswho.domain.Trait;

import java.util.ArrayList;

/**
 * @author Glis
 */
public class CharacterInformation extends Fragment {
    /**
     * The character that is being displayed.
     */
    private Character character;

    /**
     * The {@link ImageView} that holds the picture.
     */
    private ImageView picture;

    /**
     * The {@link TextView} that holds the name.
     */
    private TextView name;

    /**
     * The {@link ListView} that holds the traits.
     */
    private ListView traits;

    /**
     * {@inheritDoc}
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_character_information, container, false);
        picture = v.findViewById(R.id.picture);
        name = v.findViewById(R.id.name);
        traits = v.findViewById(R.id.traits);
        redraw();
        return v;
    }

    /**
     * @param character The {@link Character} to set.
     */
    public void setCharacter(final Character character) {
        this.character = character;
        redraw();
    }

    /**
     * Attempts to redraw with the current values.
     */
    private void redraw() {
        if(picture != null && name != null && traits != null) {
            if(character != null) {
                picture.setImageDrawable(character.getPicture().toDrawable());
                name.setText(character.getName());
                traits.setAdapter(new TraitAdaptor(getContext(), character.getTraits()));
            } else {
                picture.setImageDrawable(new ColorDrawable(Color.TRANSPARENT));
                name.setText("");
                traits.setAdapter(new TraitAdaptor(getContext(), new ArrayList<Trait>()));
            }
        }
    }
}
