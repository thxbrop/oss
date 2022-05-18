package com.thxbrop.oss.entity;

import java.util.List;

public class Commodity {
    private final String name;
    private final float price;
    private final String img;
    private int id;

    private List<String> tags;

    public Commodity(int id, String name, float price, String img, List<String> tags) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.img = img;
        this.tags = tags;
    }

    public Commodity(String name, float price, String img, List<String> tags) {
        this.name = name;
        this.price = price;
        this.img = img;
        this.tags = tags;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public float getPrice() {
        return price;
    }

    public String getImg() {
        return img;
    }

    public List<String> getTags() {
        return tags;
    }
}
