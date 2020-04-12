package com.zensolsoft.biblialite.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

public class BibleOpenHelper extends SQLiteOpenHelper {
    private static final int VERSION = 1;
    private static final String NAME = "bible.db";

    public BibleOpenHelper(@Nullable Context context) {
        super(context, NAME, null, VERSION);
    }

    public static final String TABLE_BIBLE_CREATE = "CREATE TABLE " + BibleContract.BibleEntry.TABLE_NAME + " ( "
            + BibleContract.BibleEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + BibleContract.BibleEntry.COLUMN_BIBLE_ID + "INTEGER, "
            + BibleContract.BibleEntry.COLUMN_BIBLE_ABBREVIATION + " TEXT, "
            + BibleContract.BibleEntry.COLUMN_BIBLE_NAME + " TEXT, "
            + BibleContract.BibleEntry.COLUMN_BIBLE_DESCRIPTION + " TEXT)";

    public static final String TABLE_BOOK_CREATE = "";
    public static final String TABLE_VERSE_CREATE = "";

    @Override
    public void onCreate(SQLiteDatabase db) {
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}
