package com.example.mobil_proje;

public class ExampleNote {

    private String title;
    private String gun;
    private String ay;
    private String yil;
    private String priority;
    private String ncontext;
    private int color;

    public ExampleNote(String title,String ncontext, String gun, String ay, String yil, String priority,int color) {
        this.title = title;
        this.gun = gun;
        this.ay = ay;
        this.yil = yil;
        this.priority = priority;
        this.ncontext=ncontext;
        this.color=color;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public String getNcontext() {
        return ncontext;
    }

    public void setNcontext(String ncontext) {
        this.ncontext = ncontext;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGun() {
        return gun;
    }

    public void setGun(String gun) {
        this.gun = gun;
    }

    public String getAy() {
        return ay;
    }

    public void setAy(String ay) {
        this.ay = ay;
    }

    public String getYil() {
        return yil;
    }

    public void setYil(String yil) {
        this.yil = yil;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }
}
