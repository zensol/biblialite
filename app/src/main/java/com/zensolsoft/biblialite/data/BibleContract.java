package com.zensolsoft.biblialite.data;

import android.provider.BaseColumns;

public class BibleContract {
    private static final String TAG = BibleContract.class.getSimpleName();

    public static final class BibleEntry implements BaseColumns {
        public static final String TABLE_NAME = "bible";

        public static final String _ID = BaseColumns._ID;
        public static final String COLUMN_ABBREVIATION = "abbreviation";
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_DESCRIPTION = "description";
    }

    public static final class BookEntry implements BaseColumns {
        public static final String TABLE_NAME = "book";

        public static final String _ID = BaseColumns._ID;
        public static final String COLUMN_BIBLE_ID = "bibleId";
        public static final String COLUMN_ORDERING = "ordering";
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_ABBREVIATION1 = "abbreviation1";
        public static final String COLUMN_ABBREVIATION2 = "abbreviation2";
        public static final String COLUMN_ABBREVIATION3 = "abbreviation3";
        public static final String COLUMN_TESTAMENT = "testament";
        public static final String COLUMN_TYPE = "type";
        public static final String COLUMN_NAME_ALT = "nameAlt";
    }

    public static final class VerseEntry implements BaseColumns{
        public static final String TABLE_NAME = "verse";

        public static final String _ID = BaseColumns._ID;
        public static final String COLUMN_BOOK_ID = "bookId";
        public static final String COLUMN_CHAPTER = "chapter";
        public static final String COLUMN_NUMBER = "number";
        public static final String COLUMN_TEXT = "text";

    }
}
