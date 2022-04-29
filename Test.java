package com.example.practice;

import java.util.*;

public class Test extends Question {
    private String[] options = {"A", "B", "C", "D"};
    private final ArrayList<String> labels = new ArrayList<>();

    public Test(){
        labels.add("A");
        labels.add("B");
        labels.add("C");
        labels.add("D");
    }
    public void setOptions(String[] options){
        this.options =  options;
        ArrayList<String> shuffle = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            shuffle.add(options[i]);
        }
        Collections.shuffle(shuffle);
        for (int i = 0; i < 4; i++) {
            this.options[i] = shuffle.get(i);
        }
    }
    public String getOptionAt(int index){
        return options[index];
    }
    @Override
    public String toString(){
        return getDescription();
    }
}
