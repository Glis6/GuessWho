package com.github.glis6.guesswho.adaptors;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.github.glis6.guesswho.R;
import com.github.glis6.guesswho.domain.Trait;

import java.util.List;

/**
 * @author Glis
 */

public class TraitAdaptor extends ArrayAdapter<Trait> {
    /**
     * A {@link List} of all the traits we have.
     */
    private final List<Trait> traits;

    /**
     * {@inheritDoc}
     */
    public TraitAdaptor(Context context, List<Trait> traits) {
        super(context, R.layout.item_trait_list, traits);
        this.traits = traits;
    }

    /**
     * {@inheritDoc}
     */
    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View row = inflater.inflate(R.layout.item_trait_list, parent, false);
        ((TextView)row.findViewById(R.id.trait)).setText(traits.get(position).displayTrait());
        return row;
    }
}
