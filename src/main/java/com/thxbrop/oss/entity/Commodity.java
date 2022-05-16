package com.thxbrop.oss.entity;

public class Commodity {
    private final String name;
    private final float price;
    private int id;

    public Commodity(int id, String name, float price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public Commodity(String name, float price) {
        this.name = name;
        this.price = price;
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
}
