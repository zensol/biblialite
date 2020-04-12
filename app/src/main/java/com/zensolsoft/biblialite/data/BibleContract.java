package com.zensolsoft.biblialite.data;

import android.net.Uri;
import android.provider.BaseColumns;

public class BibleContract {
    private static final String TAG = BibleContract.class.getSimpleName();

    public static final String CONTENT_AUTHORITY = "com.zensolsoft.biblialite.provider";
    public static final String PATH_BIBLE ="bible";
    public static final String PATH_BOOK ="book";
    public static final String PATH_VERSE ="verse";
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);

    public static final class BibleEntry implements BaseColumns {
        public static final String TABLE_NAME = "bible";

        public static final Uri CONTENT_URI = Uri.withAppendedPath(BASE_CONTENT_URI, PATH_BIBLE);

        public static final String COLUMN_BIBLE_ID = "bible_id";
        public static final String COLUMN_BIBLE_ABBREVIATION = "bible_abbreviation";
        public static final String COLUMN_BIBLE_NAME = "bible_name";
        public static final String COLUMN_BIBLE_DESCRIPTION = "bible_description";

        public static final String getQName(String columnName) {
            return TABLE_NAME + "." + columnName;
        }
    }

    public static final class BookEntry implements BaseColumns {
        public static final String TABLE_NAME = "book";

        public static final Uri CONTENT_URI = Uri.withAppendedPath(BASE_CONTENT_URI, PATH_BOOK);

        public static final String COLUMN_BOOK_ID = "book_id";
        public static final String COLUMN_BIBLE_ID = "bible_id";
        public static final String COLUMN_BOOK_ORDER = "book_order";
        public static final String COLUMN_BOOK_NAME = "book_name";
        public static final String COLUMN_BOOK_ABBREVIATION1 = "book_abbreviation1";
        public static final String COLUMN_BOOK_ABBREVIATION2 = "book_abbreviation2";
        public static final String COLUMN_BOOK_ABBREVIATION3 = "book_abbreviation3";
        public static final String COLUMN_BOOK_TESTAMENT = "book_testament";
        public static final String COLUMN_BOOK_TYPE = "book_type";
        public static final String COLUMN_BOOK_NAME_ALT = "book_name_alt";

        public static final String getQName(String columnName) {
            return TABLE_NAME + "." + columnName;
        }
    }

    public static final class VerseEntry implements BaseColumns{
        public static final String TABLE_NAME = "verse";

        public static final Uri CONTENT_URI = Uri.withAppendedPath(BASE_CONTENT_URI, PATH_VERSE);

        public static final String COLUMN_VERSE_ID = "verse_id";
        public static final String COLUMN_BOOK_ID = "book_id";
        public static final String COLUMN_VERSE_CHAPTER = "verse_chapter";
        public static final String COLUMN_VERSE_NUMBER = "verse_number";
        public static final String COLUMN_VERSE_TEXT = "verse_text";

        public static final String getQName(String columnName) {
            return TABLE_NAME + "." + columnName;
        }
    }
}
