package com.zensolsoft.biblialite.provider;

import android.content.ContentProvider;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.provider.BaseColumns;
import android.support.annotation.Nullable;

import com.zensolsoft.biblialite.data.BibleContract;
import com.zensolsoft.biblialite.data.BibleContract.*;
import com.zensolsoft.biblialite.data.BibleOpenHelper;

import static com.zensolsoft.biblialite.provider.BibleProviderContract.Bible;
import static com.zensolsoft.biblialite.provider.BibleProviderContract.Book;
import static com.zensolsoft.biblialite.provider.BibleProviderContract.Verse;

public class BibleProvider extends ContentProvider {
    private static final String TAG = BibleProvider.class.getSimpleName();
    public static final String MIME_VENDOR_TYPE = "vnd." + BibleProviderContract.AUTHORITY + ".";

    private SQLiteOpenHelper mBibleOpenHelper;

    private static final int BIBLE = 0;
    private static final int BIBLE_ID = 1;
    private static final int BOOK = 2;
    private static final int BOOK_ID = 3;
    private static final int BOOK_EXPANDED = 4;
    private static final int VERSE = 5;
    private static final int VERSE_ID = 6;
    private static final int VERSE_EXPANDED = 7;

    private static final UriMatcher sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    static {
        sUriMatcher.addURI(BibleProviderContract.AUTHORITY, Bible.PATH, BIBLE);
        sUriMatcher.addURI(BibleProviderContract.AUTHORITY, Bible.PATH + "/#", BIBLE_ID);
        sUriMatcher.addURI(BibleProviderContract.AUTHORITY, Book.PATH, BOOK);
        sUriMatcher.addURI(BibleProviderContract.AUTHORITY, Book.PATH + "/#", BOOK_ID);
        sUriMatcher.addURI(BibleProviderContract.AUTHORITY, Book.PATH, BOOK_EXPANDED);
        sUriMatcher.addURI(BibleProviderContract.AUTHORITY, Verse.PATH, VERSE);
        sUriMatcher.addURI(BibleProviderContract.AUTHORITY,  Verse.PATH + "/#", VERSE_ID);
        sUriMatcher.addURI(BibleProviderContract.AUTHORITY, Verse.PATH_EXPANDED, VERSE_EXPANDED);
    }

    public BibleProvider() {
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        // Implement this to handle requests to delete one or more rows.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public String getType(Uri uri) {
        String mimeType = null;

        int uriMatch = sUriMatcher.match(uri);

        switch (uriMatch) {
            case BIBLE:
                mimeType = ContentResolver.CURSOR_DIR_BASE_TYPE + '/'
                        + MIME_VENDOR_TYPE + Bible.PATH;
            case BIBLE_ID:
                mimeType = ContentResolver.CURSOR_ITEM_BASE_TYPE + '/'
                        + MIME_VENDOR_TYPE + Bible.PATH;
                break;
            case BOOK:
                mimeType = ContentResolver.CURSOR_DIR_BASE_TYPE + "/"
                        + MIME_VENDOR_TYPE + Book.PATH;
                break;
            case BOOK_ID:
                mimeType = ContentResolver.CURSOR_ITEM_BASE_TYPE + "/"
                        + MIME_VENDOR_TYPE + Book.PATH;
                break;
            case BOOK_EXPANDED:
                mimeType = ContentResolver.CURSOR_DIR_BASE_TYPE + "/"
                        + MIME_VENDOR_TYPE + Book.PATH_EXPANDED;
                break;
            case VERSE:
                mimeType = ContentResolver.CURSOR_DIR_BASE_TYPE + "/"
                        + MIME_VENDOR_TYPE + Verse.PATH;
                break;
            case VERSE_ID:
                mimeType = ContentResolver.CURSOR_ITEM_BASE_TYPE + "/"
                        + MIME_VENDOR_TYPE + Verse.PATH;
                break;
            case VERSE_EXPANDED:
                mimeType = ContentResolver.CURSOR_DIR_BASE_TYPE + "/"
                        + MIME_VENDOR_TYPE + Verse.PATH_EXPANDED;
                break;
            default:
                throw new IllegalArgumentException("Unknown Uri: " + uri);
        }

        return mimeType;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        SQLiteDatabase db = mBibleOpenHelper.getWritableDatabase();
        long rowId = -1;
        Uri rowUri = null;

        int uriMatch = sUriMatcher.match(uri);

        switch(uriMatch) {
            case BIBLE:
                rowId = db.insert(BibleEntry.TABLE_NAME,null, values);
                rowUri = ContentUris.withAppendedId(Bible.CONTENT_URI, rowId);
                break;
            default:
                throw new IllegalArgumentException("Query unknown URI: " + uri);
        }

        return rowUri;
    }

    @Override
    public boolean onCreate() {
        mBibleOpenHelper = new BibleOpenHelper(getContext());
        return true;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {

        SQLiteDatabase db = mBibleOpenHelper.getReadableDatabase();
        Cursor cursor;
        int match = sUriMatcher.match(uri);

          switch(match) {
            case BIBLE:
                cursor = db.query(BibleContract.BibleEntry.TABLE_NAME, projection, selection, selectionArgs,
                        null, null, sortOrder);
                break;
            case BIBLE_ID:
                selectionArgs = new String[] {String.valueOf(ContentUris.parseId(uri))};
                selection = BibleContract.BibleEntry.COLUMN_BIBLE_ID + "=?";
                cursor = db.query(BibleContract.BibleEntry.TABLE_NAME, projection, selection, selectionArgs,
                        null, null, null);
                break;
            case BOOK:
                cursor = db.query(BookEntry.TABLE_NAME, projection, selection, selectionArgs,
                        null, null, sortOrder);
                break;
            case BOOK_EXPANDED:
                cursor = bookExpandedQuery(db, projection, selection, selectionArgs, sortOrder);
                break;
            case VERSE:
                cursor = db.query(BibleContract.VerseEntry.TABLE_NAME, projection, selection, selectionArgs,
                        null, null, sortOrder);
                break;
            case VERSE_EXPANDED:
                cursor = verseExpandedQuery(db, projection, selection, selectionArgs, sortOrder);
                break;
            default:
                throw new IllegalArgumentException("Query unknown URI: " + uri);
        }

        cursor.setNotificationUri(getContext().getContentResolver(), uri);
        return cursor;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        // TODO: Implement this to handle requests to update one or more rows.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    private Cursor bookExpandedQuery(SQLiteDatabase db, String[] projection, String selection,
                                     String[] selectionArgs, String sortOrder) {

        String[] columns = new String[projection.length];
        String inTables = BookEntry.TABLE_NAME + " INNER JOIN " + BibleEntry.TABLE_NAME
                + " ON "
                + BookEntry.getQName(BookEntry.COLUMN_BIBLE_ID) + " = "
                + BibleEntry.getQName(BibleEntry.COLUMN_BIBLE_ID);

        SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();
        queryBuilder.setTables(inTables);

        for (int idx = 0; idx < projection.length; idx++) {
            columns[idx] = projection[idx].equals(BaseColumns._ID)  ||
                    projection[idx].equals(BibleProviderContract.BibleIdColumn.COLUMN_BIBLE_ID) ?
                    BookEntry.getQName(projection[idx]) : projection[idx];
        }

        return queryBuilder.query(db, columns, selection, selectionArgs,
                null, null, sortOrder);

    }

    private Cursor verseExpandedQuery(SQLiteDatabase db, String[] projection, String selection,
                                      String[] selectionArgs, String sortOrder) {

        String[] columns = new String[projection.length];
        String inTables = VerseEntry.TABLE_NAME + " INNER JOIN " + BookEntry.TABLE_NAME
                + " ON "
                + VerseEntry.getQName(VerseEntry.COLUMN_BOOK_ID) + " = "
                + BookEntry.getQName(BookEntry.COLUMN_BOOK_ID);

        SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();
        queryBuilder.setTables(inTables);

        for (int idx = 0; idx < projection.length; idx++) {
            columns[idx] = projection[idx].equals(BaseColumns._ID)  ||
                    projection[idx].equals(BibleProviderContract.BookIdColumn.COLUMN_BOOK_ID) ?
                    VerseEntry.getQName(projection[idx]) : projection[idx];
        }

        return queryBuilder.query(db, columns, selection, selectionArgs,
                null, null, sortOrder);
    }

}
