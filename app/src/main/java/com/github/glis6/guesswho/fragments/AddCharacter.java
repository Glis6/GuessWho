package com.github.glis6.guesswho.fragments;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.github.glis6.guesswho.R;
import com.github.glis6.guesswho.domain.Character;
import com.github.glis6.guesswho.domain.DefaultTraits;
import com.github.glis6.guesswho.domain.DrawablePicture;
import com.github.glis6.guesswho.domain.Picture;
import com.github.glis6.guesswho.domain.PossibleTraitProvider;
import com.github.glis6.guesswho.domain.Trait;
import com.github.glis6.guesswho.interfaces.OnAddButtonPress;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Glis
 */

public class AddCharacter extends Fragment implements OnAddButtonPress {
    /**
     * The value for when we requested to take a picture.
     */
    private final static int REQUEST_PICTURE_CAPTURE = 1;

    /**
     * The {@link PossibleTraitProvider} to use.
     */
    private final PossibleTraitProvider possibleTraitProvider = new DefaultTraits();

    /**
     * The imageView that displays the picture preview.
     */
    private ImageView picturePreview;

    /**
     * The textView that holds the name.
     */
    private TextView name;

    /**
     * The listView that holds the traits.
     */
    private ListView traits;

    /**
     * {@inheritDoc}
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_add_character, container, false);
        picturePreview = v.findViewById(R.id.picture_preview);
        picturePreview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                takePicture();
            }
        });

        name = v.findViewById(R.id.name);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_multiple_choice, getPossibleTraitsAsString());
        traits = v.findViewById(R.id.traits);
        traits.setAdapter(adapter);
        traits.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        return v;
    }

    /**
     * @return An array of {@link String}s that holds all the possible {@link Trait}s.
     */
    public String[] getPossibleTraitsAsString() {
        final List<Trait> possibleTraits = possibleTraitProvider.getPossibleTraits();
        final String[] possibleTraitsAsString = new String[possibleTraits.size()];
        for (int i = 0; i < possibleTraits.size(); i++) {
            possibleTraitsAsString[i] = possibleTraits.get(i).displayTrait();
        }
        return possibleTraitsAsString;
    }

    /**
     * Starts a new intent to take a picture.
     */
    private void takePicture() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getActivity().getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_PICTURE_CAPTURE);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_PICTURE_CAPTURE && resultCode == Activity.RESULT_OK) {
            final Bundle extras = data.getExtras();
            if (extras != null && extras.containsKey("data")) {
                final Bitmap imageBitmap = (Bitmap) extras.get("data");
                picturePreview.setImageDrawable(new BitmapDrawable(getResources(), imageBitmap));
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onPress() {
        if (getContext() instanceof SaveCharacter) {
            final SaveCharacter saveCharacter = (SaveCharacter) getContext();
            boolean error = false;
            if (name.getText().toString().trim().isEmpty()) {
                name.setError("Please fill in a name.");
                error = true;
            }
            if (picturePreview.getDrawable() == null) {
                error = true;
            }
            if (traits.getCount() <= 0) {
                error = true;
            }
            if (error) {
                return;
            }
            final List<Trait> chosenTraits = new ArrayList<>();
            final List<Trait> allTraits = possibleTraitProvider.getPossibleTraits();
            SparseBooleanArray sparseBooleanArray = traits.getCheckedItemPositions();
            for (int i = 0; i < traits.getCount(); i++) {
                if (sparseBooleanArray.get(i)) {
                    String selectedTraitString = traits.getItemAtPosition(i).toString();
                    for (Trait trait : allTraits) {
                        if (trait.displayTrait().equals(selectedTraitString)) {
                            chosenTraits.add(trait);
                            break;
                        }
                    }
                }
            }

            final String chosenName = name.getText().toString();
            final Picture picture = new DrawablePicture(picturePreview.getDrawable());
            saveCharacter.saveCharacter(new Character(chosenName, picture, chosenTraits));
        }
    }

    /**
     * An interface that shows that the class can save characters.
     */
    public interface SaveCharacter {
        /**
         * @param character The {@link Character} to save.
         */
        void saveCharacter(final @NonNull Character character);
    }
}
