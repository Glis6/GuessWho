package com.github.glis6.guesswho.persistency;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import static com.github.glis6.guesswho.persistency.CharacterContract.CONTENT_AUTHORITY;
import static com.github.glis6.guesswho.persistency.CharacterContract.CharacterEntry.CHARACTERS_TABLE_NAME;
import static com.github.glis6.guesswho.persistency.CharacterContract.PATH_CHARACTERS;

/**
 * @author Glis
 */
public final class CharacterProvider extends ContentProvider {
    /**
     * The helper to use to query to.
     */
    private CharacterDbHelper characterDbHelper;

    /**
     * The Uri matcher that matches the paths.
     */
    private final static UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    /**
     * The path for all characters.
     */
    private final static int CHARACTERS = 1;

    /*
     * Adds the URIs to the matcher.
     */
    static {
        uriMatcher.addURI(CONTENT_AUTHORITY, PATH_CHARACTERS, CHARACTERS);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean onCreate() {
        characterDbHelper = new CharacterDbHelper(getContext());
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues contentValues) {
        switch (uriMatcher.match(uri)) {
            case CHARACTERS:
                return insertRecord(uri, contentValues, CHARACTERS_TABLE_NAME);
        }
        throw new IllegalArgumentException("Unknown URI: " + uri);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues contentValues, @Nullable String selection, @Nullable String[] selectionArgs) {
        switch (uriMatcher.match(uri)) {
            case CHARACTERS:
                return updateRecord(contentValues, selection, selectionArgs, CHARACTERS_TABLE_NAME);
        }
        throw new IllegalArgumentException("Unknown URI: " + uri);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int delete(@NonNull Uri uri, @Nullable String s, @Nullable String[] strings) {
        switch (uriMatcher.match(uri)) {
            case CHARACTERS:
                return deleteRecord(s, strings, CHARACTERS_TABLE_NAME);
        }
        throw new IllegalArgumentException("Unknown URI: " + uri);
    }

    /**
     * {@inheritDoc}
     */
    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        final SQLiteDatabase database = characterDbHelper.getReadableDatabase();
        Cursor cursor;
        switch (uriMatcher.match(uri)) {
            case CHARACTERS:
                cursor = database.query(CHARACTERS_TABLE_NAME, projection, selection, selectionArgs, null, null, sortOrder);
                break;
            default:
                throw new IllegalArgumentException("Unknown URI: " + uri);
        }
        return cursor;
    }

    /**
     * {@inheritDoc}
     */
    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    private int deleteRecord(String selection, String[] selectionArgs, String tableName) {
        final SQLiteDatabase database = characterDbHelper.getWritableDatabase();
        return database.delete(tableName, selection, selectionArgs);
    }

    private int updateRecord(ContentValues contentValues, String selection, String[] selectionArgs, String tableName) {
        final SQLiteDatabase database = characterDbHelper.getWritableDatabase();
        return database.update(tableName, contentValues, selection, selectionArgs);
    }

    private Uri insertRecord(Uri uri, ContentValues contentValues, String tableName) {
        final SQLiteDatabase database = characterDbHelper.getWritableDatabase();
        final long rowId = database.insert(tableName, null, contentValues);

        if (rowId == -1) {
            Log.e("DATABASE", "Insert error for URI " + uri);
            return null;
        }
        return ContentUris.withAppendedId(uri, rowId);
    }
}
