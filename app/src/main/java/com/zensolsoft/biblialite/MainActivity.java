package com.zensolsoft.biblialite;

import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.zensolsoft.biblialite.data.BibleContract;
import com.zensolsoft.biblialite.provider.BibleProviderContract;
import com.zensolsoft.biblialite.provider.BibleProviderContract.Verse;
import com.zensolsoft.biblialite.provider.BibleProviderContract.Bible;
import com.zensolsoft.biblialite.provider.BibleProviderContract.Book;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        testBible();
        testBook();
        testVerse();
    }

    private void testVerse() {

        String[] columns = new String[] {
                Verse.COLUMN_VERSE_ID,
                Verse.COLUMN_BOOK_NAME,
                Verse.COLUMN_VERSE_CHAPTER,
                Verse.COLUMN_VERSE_NUMBER,
                Verse.COLUMN_VERSE_TEXT
        };

        String selection = Verse.COLUMN_BOOK_NAME + "=? AND " + Verse.COLUMN_VERSE_CHAPTER + "=?";

        String[] selectionArgs = new String[] {"Génesis", "1"};
        String groupBy = null;
        String having = null;
        String orderBy = null;

        Cursor cursor = getContentResolver().query(
                Verse.CONTENT_EXPANDED_URI,
                columns,
                selection,
                selectionArgs,
                orderBy);

        while(cursor.moveToNext()) {
            String verseId = cursor.getString(cursor.getColumnIndex(Verse.COLUMN_VERSE_ID));
            String bookName = cursor.getString(cursor.getColumnIndex(Book.COLUMN_BOOK_NAME));
            String chapter = cursor.getString(cursor.getColumnIndex(Verse.COLUMN_VERSE_CHAPTER));
            String number = cursor.getString(cursor.getColumnIndex(Verse.COLUMN_VERSE_NUMBER));
            String text = cursor.getString(cursor.getColumnIndex(Verse.COLUMN_VERSE_TEXT));

            Log.i(TAG, " verseId: " + verseId + " bookName: " + bookName + " chapter: " + chapter
             + " number: " + number + " text: " + text);
        }

        cursor.close();

    }

    private void testBook() {

        String[] projection = new String[] {
                Book._ID,
                Book.COLUMN_BIBLE_ID,
                Bible.COLUMN_BIBLE_NAME,
                Book.COLUMN_BOOK_ORDER,
                Book.COLUMN_BOOK_NAME,
                Book.COLUMN_BOOK_ABBREVIATION1,
                Book.COLUMN_BOOK_ABBREVIATION2,
                Book.COLUMN_BOOK_ABBREVIATION3,
                Book.COLUMN_BOOK_TESTAMENT,
                Book.COLUMN_BOOK_TYPE,
                Book.COLUMN_BOOK_NAME_ALT
        };

        // String selection = Book.COLUMN_BIBLE_ID + "=?";
        String[] selectionArgs = new String[] {"1"};
        String groupBy = null;
        String having = null;
        String orderBy = null;

        Cursor cursor = getContentResolver().query(Book.CONTENT_URI, projection, null, null, orderBy);

        while(cursor.moveToNext()) {
            String id = cursor.getString(cursor.getColumnIndex(Book._ID));
            String bibleId = cursor.getString(cursor.getColumnIndex(Book.COLUMN_BIBLE_ID));
            String bibleName = cursor.getString(cursor.getColumnIndex(Bible.COLUMN_BIBLE_NAME));
            String ordering = cursor.getString(cursor.getColumnIndex(Book.COLUMN_BOOK_ORDER));
            String name = cursor.getString(cursor.getColumnIndex(Book.COLUMN_BOOK_NAME));
            String abbr1 = cursor.getString(cursor.getColumnIndex(Book.COLUMN_BOOK_ABBREVIATION1));
            String abbr2 = cursor.getString(cursor.getColumnIndex(Book.COLUMN_BOOK_ABBREVIATION2));
            String abbr3 = cursor.getString(cursor.getColumnIndex(Book.COLUMN_BOOK_ABBREVIATION3));
            String testament = cursor.getString(cursor.getColumnIndex(Book.COLUMN_BOOK_TESTAMENT));
            String nameAlt = cursor.getString(cursor.getColumnIndex(Book.COLUMN_BOOK_NAME_ALT));

            Log.i(TAG, "id : " + id + " bibleId: " + bibleId + " bibleName: " + bibleName + " ordering: " + ordering
                    + " name: " + name + " abbreviation1: " + abbr1 + " abbreviation2: " + abbr2
                    + " abbreviation4: " + abbr3 + " testament: " + testament + " nameAlt: " + nameAlt );
        }

        cursor.close();
    }

    private void testBible() {

        String[] projection = new String[] {Bible._ID, Bible.COLUMN_BIBLE_NAME, Bible.COLUMN_BIBLE_DESCRIPTION};
        // String selection = null;
        String[] selectionArgs = null;
        String sortOrder = null;

//        Cursor cursor = getContentResolver().query(Bible.CONTENT_URI, projection, selection,
//                selectionArgs, sortOrder);

        Uri uri =  Uri.withAppendedPath(Bible.CONTENT_URI, "1");

        String selection = Bible.COLUMN_BIBLE_ID + "=?";

        Cursor cursor = getContentResolver().query(uri, projection, selection, selectionArgs, sortOrder);

        while(cursor.moveToNext()) {
            String id = cursor.getString(cursor.getColumnIndex(Bible._ID));
            String name = cursor.getString(cursor.getColumnIndex(Bible.COLUMN_BIBLE_NAME));
            String description = cursor.getString(cursor.getColumnIndex(Bible.COLUMN_BIBLE_DESCRIPTION));

            Log.i(TAG, "id : " + id + " name: " + name + " description: " + description);
        }

        cursor.close();
    }
}
