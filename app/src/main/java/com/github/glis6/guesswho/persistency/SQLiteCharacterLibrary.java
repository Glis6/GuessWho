package com.github.glis6.guesswho.persistency;

import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.support.annotation.NonNull;
import android.util.Log;

import com.github.glis6.guesswho.domain.Character;
import com.github.glis6.guesswho.domain.CharacterLibrary;
import com.github.glis6.guesswho.domain.DrawablePicture;
import com.github.glis6.guesswho.domain.Trait;
import com.github.glis6.guesswho.domain.traits.StringTrait;
import com.github.glis6.guesswho.util.Utils;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static com.github.glis6.guesswho.persistency.CharacterContract.CharacterEntry.COLUMN_NAME;
import static com.github.glis6.guesswho.persistency.CharacterContract.CharacterEntry.COLUMN_TRAITS;
import static com.github.glis6.guesswho.persistency.CharacterContract.CharacterEntry.CONTENT_URI;
import static com.github.glis6.guesswho.persistency.CharacterContract.CharacterEntry._ID;

/**
 * @author Glis
 */
public class SQLiteCharacterLibrary implements CharacterLibrary {
    /**
     * The full character projection.
     */
    private final static String[] CHARACTER_PROJECTION = {
            _ID,
            COLUMN_NAME,
            COLUMN_TRAITS
    };

    /**
     * The context to work around.
     */
    private final Context context;

    /**
     * @param context The context to work around.
     */
    SQLiteCharacterLibrary(Context context) {
        this.context = context;
    }

    @Override
    public List<Character> getAllCharacters() {
        final Cursor cursor = context.getContentResolver().query(CONTENT_URI, CHARACTER_PROJECTION, null, null, null);
        final List<Character> characters = new ArrayList<>();
        if (cursor != null) {
            while (cursor.moveToNext()) {
                try {
                    final long id = cursor.getLong(cursor.getColumnIndex(_ID));
                    final String name = cursor.getString(cursor.getColumnIndex(COLUMN_NAME));
                    final String traitsString = cursor.getString(cursor.getColumnIndex(COLUMN_TRAITS));
                    final List<Trait> traits = new ArrayList<>();
                    for (String trait : traitsString.split(",")) {
                        traits.add(new StringTrait(trait));
                    }

                    Bitmap picture = BitmapFactory.decodeFile(new File(context.getDir("CharacterPictures", Context.MODE_PRIVATE), id + ".png").getPath());
                    characters.add(new Character(id, name, new DrawablePicture(new BitmapDrawable(context.getResources(), picture)), traits));
                } catch (Exception e) {
                    Log.d("CharacterLibrary", "Exception occurred while parsing character.", e);
                }
            }
            cursor.close();
        }
        return characters;
    }

    @Override
    public List<Character> getFilteredCharacters(@NonNull Collection<String> filteredNames) {
        final List<Character> resultList = new ArrayList<>();
        for (Character character : getAllCharacters()) {
            boolean found = false;
            for (String filteredName : filteredNames) {
                if (character.getName().equalsIgnoreCase(filteredName)) {
                    found = true;
                    break;
                }
            }
            if (!found) {
                resultList.add(character);
            }
        }
        return resultList;
    }

    @Override
    public boolean removeCharacter(@NonNull Character character) {
        return character.getId() != -1 && context.getContentResolver().delete(CONTENT_URI, _ID + " = ?", new String[]{Long.toString(character.getId())}) > 1;
    }

    @Override
    public void addCharacter(@NonNull Character character) {
        final ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_NAME, character.getName());
        final StringBuilder traitString = new StringBuilder();
        for (Trait trait : character.getTraits()) {
            if(traitString.length() > 0) {
                traitString.append(",");
            }
            traitString.append(trait.displayTrait());
        }
        contentValues.put(COLUMN_NAME, character.getName());
        contentValues.put(COLUMN_TRAITS, traitString.toString());

        long id;
        if(character.getId() != -1) {
            context.getContentResolver().update(CONTENT_URI, contentValues, _ID + " = ?", new String[]{Long.toString(character.getId())});
            id = character.getId();
        } else {
            id = ContentUris.parseId(context.getContentResolver().insert(CONTENT_URI, contentValues));
        }

        try (FileOutputStream fileOutputStream = new FileOutputStream(new File(context.getDir("CharacterPictures", Context.MODE_PRIVATE), id + ".png"))) {
            Utils.drawableToBitmap(character.getPicture().toDrawable()).compress(Bitmap.CompressFormat.PNG, 100, fileOutputStream);
        } catch (Exception ex) {
            Log.i("CharacterLibrary", "Problem updating picture", ex);
        }
    }
}
