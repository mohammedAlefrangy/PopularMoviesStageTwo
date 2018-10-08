package com.example.hmod_.popularmoviesstageone.DataEntity;


public class MovieTrailerEntity {

    private String id;
    private String iso6391;
    private String key;
    private String name;


    /**
     * No args constructor for use in serialization
     */

    public MovieTrailerEntity() {


    }

    public MovieTrailerEntity(String id, String iso6391, String key, String name) {
        this.id = id;
        this.iso6391 = iso6391;
        this.key = key;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIso6391() {
        return iso6391;
    }

    public void setIso6391(String iso6391) {
        this.iso6391 = iso6391;
    }


    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public  String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "MovieTrailerEntity{" +
                "id='" + id + '\'' +
                ", iso6391='" + iso6391 + '\'' +
                ", key='" + key + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}

