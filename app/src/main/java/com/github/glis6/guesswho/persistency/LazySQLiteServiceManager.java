package com.github.glis6.guesswho.persistency;

import android.content.Context;

import com.github.glis6.guesswho.domain.CharacterLibrary;
import com.github.glis6.guesswho.services.ServiceManager;

/**
 * @author Glis
 */
public class LazySQLiteServiceManager implements ServiceManager {
    /**
     * The context used to create the databases.
     */
    private final Context context;

    /**
     * The {@link CharacterLibrary} to use to access characters.
     */
    private CharacterLibrary characterLibrary;

    /**
     * @param context The context used to create the databases.
     */
    public LazySQLiteServiceManager(Context context) {
        this.context = context;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CharacterLibrary getCharacterLibrary() {
        if(characterLibrary == null) {
            characterLibrary = new SQLiteCharacterLibrary(context);
        }
        return characterLibrary;
    }
}
