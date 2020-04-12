package com.zensolsoft.biblialite.provider;

import android.net.Uri;
import android.provider.BaseColumns;

public class BibleProviderContract {

    private BibleProviderContract() {}

    public static final String AUTHORITY = "com.zensolsoft.biblialite.provider";
    public static final Uri AUTHORITY_URI = Uri.parse("content://" + AUTHORITY);

    protected interface BibleIdColumn {
        String COLUMN_BIBLE_ID = "bible_id";
    }

    protected interface BookIdColumn {
        String COLUMN_BOOK_ID = "book_id";
    }

    protected interface BibleColumns {
        String COLUMN_BIBLE_ABBREVIATION = "bible_abbreviation";
        String COLUMN_BIBLE_NAME = "bible_name";
        String COLUMN_BIBLE_DESCRIPTION = "bible_description";
    }

    protected interface BookColumns {
        String COLUMN_BOOK_ORDER = "book_order";
        String COLUMN_BOOK_NAME = "book_name";
        String COLUMN_BOOK_ABBREVIATION1 = "book_abbreviation1";
        String COLUMN_BOOK_ABBREVIATION2 = "book_abbreviation2";
        String COLUMN_BOOK_ABBREVIATION3 = "book_abbreviation3";
        String COLUMN_BOOK_TESTAMENT = "book_testament";
        String COLUMN_BOOK_TYPE = "book_type";
        String COLUMN_BOOK_NAME_ALT = "book_name_alt";
    }

    protected interface VerseColumns {
        String COLUMN_VERSE_ID = "verse_id";
        String COLUMN_VERSE_CHAPTER = "verse_chapter";
        String COLUMN_VERSE_NUMBER = "verse_number";
        String COLUMN_VERSE_TEXT = "verse_text";
    }

    public static final class Bible implements BibleColumns, BibleIdColumn, BaseColumns {
        public static final String PATH = "bible";
        public static final Uri CONTENT_URI = Uri.withAppendedPath(AUTHORITY_URI, PATH);
    }

    public static final class Book implements BookColumns, BibleIdColumn, BookIdColumn, BaseColumns {
        public static final String PATH = "book";
        public static final Uri CONTENT_URI = Uri.withAppendedPath(AUTHORITY_URI, PATH);
        public static final String PATH_EXPANDED = "book_expanded";
        public static final Uri CONTENT_EXPANDED_URI = Uri.withAppendedPath(AUTHORITY_URI, PATH_EXPANDED);
    }

    public static final class Verse implements VerseColumns, BookIdColumn, BaseColumns, BookColumns {
        public static final String PATH = "verse";
        public static final Uri CONTENT_URI = Uri.withAppendedPath(AUTHORITY_URI, PATH);
        public static final String PATH_EXPANDED = "verse_expanded";
        public static final Uri CONTENT_EXPANDED_URI = Uri.withAppendedPath(AUTHORITY_URI, PATH_EXPANDED);
    }

}
