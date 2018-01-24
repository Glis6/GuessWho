package com.github.glis6.guesswho.persistency;

import android.net.Uri;
import android.provider.BaseColumns;

/**
 * @author Glis
 */
final class CharacterContract {
    /**
     * The content authority to access the database.
     */
    final static String CONTENT_AUTHORITY = "com.github.glis6.guesswho.provider.characters";

    /**
     * The base URI to access the database.
     */
    final static Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);

    /**
     * The path to the characters.
     */
    final static String PATH_CHARACTERS = "characters";

    final static class CharacterEntry implements BaseColumns {
        /**
         * The content URI to the characters.
         */
        final static Uri CONTENT_URI = Uri.withAppendedPath(BASE_CONTENT_URI, PATH_CHARACTERS);

        /**
         * The name of the table we save the characters in.
         */
        final static String CHARACTERS_TABLE_NAME = "characters";

        /**
         * The ID of the table.
         */
        final static String _ID = BaseColumns._ID;

        /**
         * The column we save the name in.
         */
        final static String COLUMN_NAME = "name";

        /**
         * The column we save the traits in.
         */
        final static String COLUMN_TRAITS = "traits";
    }
}
