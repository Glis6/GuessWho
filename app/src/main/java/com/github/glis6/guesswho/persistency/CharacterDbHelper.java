package com.github.glis6.guesswho.persistency;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static com.github.glis6.guesswho.persistency.CharacterContract.CharacterEntry.*;

/**
 * @author Glis
 */
public class CharacterDbHelper extends SQLiteOpenHelper {
    /**
     * The name of the database.
     */
    private final static String DATABASE_NAME = "characters.db";

    /**
     * The current version of the database.
     */
    private final static int DATABASE_VERSION = 1;

    /**
     * The SQL query to create the latest version of the table.
     */
    private final static String SQL_CREATE_CHARACTERS_TABLE = "CREATE TABLE " + CHARACTERS_TABLE_NAME
            + " (" + _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_NAME + " TEXT NOT NULL, "
            + COLUMN_TRAITS + " TEXT NOT NULL"
            + ");";

    /**
     * @param context The context to work around.
     */
    CharacterDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(SQL_CREATE_CHARACTERS_TABLE);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + CHARACTERS_TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
}
