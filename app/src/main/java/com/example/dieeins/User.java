package com.example.dieeins;

public class User {

    private String Name;
    private int score;

    public User() {
    }

    public User(String name, int score) {
        Name = name;
        this.score = score;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
