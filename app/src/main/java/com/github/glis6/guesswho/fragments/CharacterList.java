package com.github.glis6.guesswho.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.glis6.guesswho.adaptors.CharacterAdaptor;
import com.github.glis6.guesswho.R;
import com.github.glis6.guesswho.util.RecyclerViewTouchListener;
import com.github.glis6.guesswho.domain.Character;
import com.github.glis6.guesswho.util.OnPressListener;

import java.util.List;

public class CharacterList extends Fragment {
    /**
     * The adapter to translate the characters.
     */
    private CharacterAdaptor adapter = new CharacterAdaptor();

    /**
     * {@inheritDoc}
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_character_list, container, false);
        RecyclerView mRecyclerView = v.findViewById(R.id.characters_recycler_view);
        mRecyclerView.setAdapter(adapter);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);

        ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(final RecyclerView.ViewHolder viewHolder, int direction) {
                final int position = viewHolder.getAdapterPosition();
                if (direction == ItemTouchHelper.LEFT) {
                    if(getActivity() instanceof OnSwipeLeft) {
                        final OnSwipeLeft onSwipeLeft = (OnSwipeLeft)getActivity();
                        final Character character = adapter.getCharacterOnPosition(position);
                        onSwipeLeft.onSwipeLeft(character);
                    }
                }
            }
        };
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleCallback);
        itemTouchHelper.attachToRecyclerView(mRecyclerView);

        mRecyclerView.addOnItemTouchListener(new RecyclerViewTouchListener(getContext(), new OnPressListener() {
            @Override
            public void onPress(View view, int position) {
                if(getActivity() instanceof OnSelect) {
                    final OnSelect onSelect = (OnSelect)getActivity();
                    final Character character = adapter.getCharacterOnPosition(position);
                    onSelect.onSelect(character);
                }
            }
        }));
        return v;
    }

    /**
     * @param characters The characters to display.
     */
    public void setCharacters(final List<Character> characters) {
        if(adapter != null) {
            adapter.setCharacters(characters);
        }
    }

    /**
     * An interface that shows that we support swiping left.
     */
    public interface OnSwipeLeft {
        /**
         * @param character The character that has been swiped.
         */
        void onSwipeLeft(final Character character);
    }

    /**
     * An interface that shows that we support selecting a character.
     */
    public interface OnSelect {
        /**
         * @param character The character that has been selected.
         */
        void onSelect(final Character character);
    }
}
