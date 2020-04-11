package com.zensolsoft.biblialite.model;

public class Bible {
    private static final String TAG = Bible.class.getSimpleName();

    private long id;
    private String abbreviation;
    private String name;
    private String description;

    public Bible () {}

    public Bible(long id, String abbreviation, String name, String description) {
        this.id = id;
        this.abbreviation = abbreviation;
        this.name = name;
        this.description = description;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
