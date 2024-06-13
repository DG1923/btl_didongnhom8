package com.example.baitaplonnhom8;

public class Exercise {
    private int imageResource;
    private String name;
    private String time;
    private String category;

    public Exercise(int imageResource, String name, String time, String category) {
        this.imageResource = imageResource;
        this.name = name;
        this.time = time;
        this.category = category;
    }

    public int getImageResource() {
        return imageResource;
    }

    public String getName() {
        return name;
    }

    public String getTime() {
        return time;
    }

    public String getCategory() {
        return category;
    }
}
