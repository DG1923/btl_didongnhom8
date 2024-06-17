package com.example.baitaplonnhom8;

public class Exercise {
    private int imageResource;
    private String name;
    private String timeReality;
    private String category;
    private String timeRequire;
    public Exercise(int imageResource, String name, String timeRequire, String category) {
        this.imageResource = imageResource;
        this.name = name;
        this.timeRequire = timeRequire;
        this.category = category;
    }


    public String getTimeReality() {
        return timeReality;
    }

    public void setTimeReality(String timeReality) {
        this.timeReality = timeReality;
    }

    public String getTimeRequire() {
        return timeRequire;
    }

    public void setTimeRequire(String timeRequire) {
        this.timeRequire = timeRequire;
    }


    public int getImageResource() {
        return imageResource;
    }

    public String getName() {
        return name;
    }


    public String getCategory() {
        return category;
    }
}
