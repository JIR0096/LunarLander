package com.example.lunarlander;

public enum Difficulty{
    EASY("easy"),
    MEDIUM("medium"),
    HARD("hard");

    private final String name;

    private Difficulty(String s) {
        name = s;
    }

    public boolean equalsName(String otherName) {
        // (otherName == null) check is not needed because name.equals(null) returns false
        return name.equals(otherName);
    }

    @Override
    public String toString() {
        return this.name;
    }

}
