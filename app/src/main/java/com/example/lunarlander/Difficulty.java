package com.example.lunarlander;

public enum Difficulty {
    EASY("easy", -5),
    MEDIUM("medium", -10),
    HARD("hard", -15);

    private final String name;
    private final Integer gravity;

    private Difficulty(String s, int g) {
        name = s;
        gravity = g;
    }

    public int getGravity(){
        return gravity;
    }

    @Override
    public String toString() {
        return this.name;
    }

}
