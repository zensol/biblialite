package com.zensolsoft.biblialite.model;

public class Verse {
    private static final String TAG = Verse.class.getSimpleName();

    private long id;
    private long bookId;
    private int chapter;
    private int number;
    private String text;

    public Verse () {}

    public Verse (long id, long bookId, int chapter, int number, String text) {
        this.id = id;
        this.bookId = bookId;
        this.chapter = chapter;
        this.number = number;
        this.text = text;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getBookId() {
        return bookId;
    }

    public void setBookId(long bookId) {
        this.bookId = bookId;
    }

    public int getChapter() {
        return chapter;
    }

    public void setChapter(int chapter) {
        this.chapter = chapter;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
