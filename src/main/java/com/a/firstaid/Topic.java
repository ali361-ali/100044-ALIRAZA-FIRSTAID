package com.a.firstaid;

import java.util.List;

public class Topic {
    private String id;
    private String title;
    private String description;
    private String category;
    private int iconRes;
    private List<String> steps;

    public Topic(String id, String title, String description, String category, int iconRes, List<String> steps) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.category = category;
        this.iconRes = iconRes;
        this.steps = steps;
    }

    public String getId() { return id; }
    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public String getCategory() { return category; }
    public int getIconRes() { return iconRes; }
    public List<String> getSteps() { return steps; }
}