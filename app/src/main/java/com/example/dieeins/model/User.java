package com.example.dieeins.model;

import com.orm.SugarRecord;

/**
        * Datenklasse / Modelklasse für User
        * extends SugarRecord für Datenbank Zugriff
        */
public class User extends SugarRecord {
    private String name;
    private int score;

    /**
     *  Leerer Konstruktor
     */
    public User() {
    }

    /**
     *  Konstruktor mit allen Attributen
     * @param name  Der Benutzername des Spielers
     * @param score Der erreichte Punktestand des Spielers
     */
    public User(String name, int score) {
        this.name = name;
        this.score = score;
    }

    /**
     *  Getter-Methode für name
     * @return name Der Benutzername
     */
    public String getName() {
        return name;
    }

    /**
     *  Setter-Methode für name
     * @param name Der Benutzername
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *  Getter-Methode für score
     * @return score Der Punktestand
     */
    public int getScore() {
        return score;
    }

    /**
     * Setter-Methode für score
     * @param score Der Punktestand
     */
    public void setScore(int score) {
        this.score = score;
    }
}