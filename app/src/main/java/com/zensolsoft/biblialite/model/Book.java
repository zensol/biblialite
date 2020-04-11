package com.zensolsoft.biblialite.model;

public class Book {
    private static final String TAG = Book.class.getSimpleName();

    private long id;
    private long bibleId;
    private int ordering;
    private String name;
    private String abbreviation1;
    private String abbreviation2;
    private String abbreviation3;
    private String testament;
    private String type;
    private String nameAlt;

    public Book() { }

    public Book(long id, long bibleId, int ordering, String name, String abbreviation1,
                String abbreviation2, String abbreviation3, String testament, String type, String nameAlt) {
        this.id = id;
        this.bibleId = bibleId;
        this.ordering = ordering;
        this.name = name;
        this.abbreviation1 = abbreviation1;
        this.abbreviation2 = abbreviation2;
        this.abbreviation3 = abbreviation3;
        this.testament = testament;
        this.type = type;
        this.nameAlt = nameAlt;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getBibleId() {
        return bibleId;
    }

    public void setBibleId(long bibleId) {
        this.bibleId = bibleId;
    }

    public int getOrdering() {
        return ordering;
    }

    public void setOrdering(int ordering) {
        this.ordering = ordering;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAbbreviation1() {
        return abbreviation1;
    }

    public void setAbbreviation1(String abbreviation1) {
        this.abbreviation1 = abbreviation1;
    }

    public String getAbbreviation2() {
        return abbreviation2;
    }

    public void setAbbreviation2(String abbreviation2) {
        this.abbreviation2 = abbreviation2;
    }

    public String getAbbreviation3() {
        return abbreviation3;
    }

    public void setAbbreviation3(String abbreviation3) {
        this.abbreviation3 = abbreviation3;
    }

    public String getTestament() {
        return testament;
    }

    public void setTestament(String testament) {
        this.testament = testament;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getNameAlt() {
        return nameAlt;
    }

    public void setNameAlt(String nameAlt) {
        this.nameAlt = nameAlt;
    }
}
