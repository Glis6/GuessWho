package com.github.glis6.guesswho.fragments;

import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.glis6.guesswho.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author Glis
 */
public final class CharacterViewHolder extends RecyclerView.ViewHolder {
    /**
     * The picture of the character.
     */
    @BindView(R.id.picture)
    ImageView picture;

    /**
     * The name of the character.
     */
    @BindView(R.id.name)
    TextView name;

    /**
     * The description of the character.
     */
    @BindView(R.id.description)
    TextView description;

    /**
     * {@inheritDoc}
     */
    public CharacterViewHolder(View view) {
        super(view);
        ButterKnife.bind(this, view);
    }

    /**
     * @param name The name of the character.
     * @param description The description of the character.
     * @param picture The picture of the character.
     */
    public void setData(final String name, final String description, final Drawable picture) {
        this.name.setText(name);
        this.description.setText(description);
        this.picture.setImageDrawable(picture);
    }
}
