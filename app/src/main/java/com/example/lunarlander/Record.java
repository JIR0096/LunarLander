package com.example.lunarlander;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class Record implements Serializable, Comparable<Record> {
    private String name;
    private Integer score;

    public Record(String name, String score) {
        this.name = name;
        this.score = Integer.parseInt(score);
    }

    @Override
    public int compareTo(Record r) {
        return r.score - this.score;
    }

    public String getName() {
        return name;
    }

    public String getScore() {
        if (score == null)
            return "0";
        return String.valueOf(score);
    }

    @NonNull
    @Override
    public String toString() {
        return name + " >===< " + score;
    }
}
