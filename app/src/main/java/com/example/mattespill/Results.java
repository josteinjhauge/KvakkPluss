package com.example.mattespill;

public class Results {
    String name;
    String score;

    public Results(String name, String score){
        this.name = name;
        this.score = score;
    }

    // denne er bare for å teste
    public void changeTest(String text){
        name = text;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }
}
