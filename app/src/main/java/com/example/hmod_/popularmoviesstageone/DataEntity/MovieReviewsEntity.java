package com.example.hmod_.popularmoviesstageone.DataEntity;


public class MovieReviewsEntity {

    private String id;
    private String content;
    private String author;



    /**
     * No args constructor for use in serialization
     */

    public MovieReviewsEntity() {


    }

    public MovieReviewsEntity(String id, String content, String author) {
        this.id = id;
        this.content = content;
        this.author = author;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getcontent() {
        return content;
    }

    public void setcontent(String content) {
        this.content = content;
    }


    public String getauthor() {
        return author;
    }

    public void setauthor(String author) {
        this.author = author;
    }


    @Override
    public String toString() {
        return "MovieTrailerEntity{" +
                "id='" + id + '\'' +
                ", content='" + content + '\'' +
                ", author='" + author + '\'' +
                '}';
    }
}

