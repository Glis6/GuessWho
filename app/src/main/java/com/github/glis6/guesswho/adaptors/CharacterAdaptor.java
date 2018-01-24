package com.github.glis6.guesswho.adaptors;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.glis6.guesswho.fragments.CharacterViewHolder;
import com.github.glis6.guesswho.R;
import com.github.glis6.guesswho.domain.Character;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Glis
 */

public class CharacterAdaptor extends RecyclerView.Adapter<CharacterViewHolder> {
    /**
     * A {@link List} that holds all the pixel projects.
     */
    private List<Character> characters = new ArrayList<>();

    /**
     * {@inheritDoc}
     */
    @Override
    public CharacterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_character_list, parent, false);
        return new CharacterViewHolder(view);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onBindViewHolder(CharacterViewHolder holder, int position) {
        final Character character = characters.get(position);
        holder.setData(character.getName(), character.getDescription(), character.getPicture().toDrawable());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getItemCount() {
        return characters.size();
    }

    /**
     * @param characters The {@link Character}s to set.
     */
    public void setCharacters(final @NonNull List<Character> characters) {
        this.characters = characters;
        notifyDataSetChanged();
    }

    /**
     * @param position The position to look for.
     * @return The {élink Character} on this position.
     */
    public Character getCharacterOnPosition(int position) {
        return characters.get(position);
    }
}
