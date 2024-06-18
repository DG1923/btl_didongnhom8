package com.example.baitaplonnhom8;

public class Exercise {
    public String getImageResource() {
        return imageResource;
    }

    public void setImageResource(String imageResource) {
        this.imageResource = imageResource;
    }


    private String imageResource;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTimeReality() {
        return timeReality;
    }

    public void setTimeReality(int timeReality) {
        this.timeReality = timeReality;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public int getTimeRequire() {
        return timeRequire;
    }

    public void setTimeRequire(int timeRequire) {
        this.timeRequire = timeRequire;
    }

    private String name;
    private int timeReality;
    private int category;
    private int timeRequire;

    public String getDecription() {
        return decription;
    }

    public void setDecription(String decription) {
        this.decription = decription;
    }

    private String decription;
    public Exercise(String imageResource, String name, int timeRequire, int category,int timeReality,String des) {
        this.imageResource = imageResource;
        this.name = name;
        this.timeRequire = timeRequire;
        this.timeReality = timeReality;
        this.category = category;
        this.decription = des;
    }





}
