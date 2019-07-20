package com.example.rajnish.mygmail;

public class MyGmail {
    private String from;
    private String subject;
    private String message;
    private int image_thumbnail;
    private String date;
    private boolean isSelected;
    private int Id_num;

    public MyGmail(String from, String subject, String message, int image_thumbnail, String date) {
        this.from = from;
        this.subject = subject;
        this.message = message;
        this.image_thumbnail = image_thumbnail;
        this.date = date;
        this.isSelected = isSelected;

    }

    public MyGmail() {

    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getImage_thumbnail() {
        return image_thumbnail;
    }

    public void setImage_thumbnail(int image_thumbnail) {
        this.image_thumbnail = image_thumbnail;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public int getId_num() {
        return Id_num;
    }

    public void setId_num(int id_num) {
        Id_num = id_num;
    }


}
