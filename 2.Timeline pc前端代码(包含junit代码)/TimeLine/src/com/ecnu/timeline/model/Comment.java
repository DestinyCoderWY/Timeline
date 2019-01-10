package com.ecnu.timeline.model;


public class Comment {

    /**
     * id : 3
     * username : zyw
     * commment : gfaes
     * picture :
     * time : 2018-12-26
     */

    private int id;
    private String username;
    private String commment;
    private String picture;
    private String time;


    public Comment(int id, String username, String commment, String picture, String time) {
        this.id = id;
        this.username = username;
        this.commment = commment;
        this.picture = picture;
        this.time = time;
    }

    public String getSeriTime() {

        return getTime();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCommment() {
        return commment;
    }

    public void setCommment(String commment) {
        this.commment = commment;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
