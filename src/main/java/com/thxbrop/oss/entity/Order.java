package com.thxbrop.oss.entity;

import java.util.List;

public class Order {
    private final int userId;
    private final long createdAt;
    private final List<Integer> commodities;
    private int id;

    public Order(int userId, long createdAt, List<Integer> commodities) {
        this.userId = userId;
        this.createdAt = createdAt;
        this.commodities = commodities;
    }

    public Order(int id, int userId, long createdAt, List<Integer> commodities) {
        this.id = id;
        this.userId = userId;
        this.createdAt = createdAt;
        this.commodities = commodities;
    }

    public int getId() {
        return id;
    }

    public int getUserId() {
        return userId;
    }

    public long getCreatedAt() {
        return createdAt;
    }

    public List<Integer> getCommodities() {
        return commodities;
    }

    @Override
    public String toString() {
        return "Order{" +
                "userId=" + userId +
                ", createdAt=" + createdAt +
                ", commodities=" + commodities +
                ", id=" + id +
                '}';
    }
}
