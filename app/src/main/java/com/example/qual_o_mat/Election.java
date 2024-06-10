package com.example.qual_o_mat;

public class Election {
    private int id;
    private String name;
    private String date;
    private String path;

    public Election(int id, String name, String date, String path) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.path = path;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDate() {
        return date;
    }

    public String getPath() {
        return path;
    }
}

