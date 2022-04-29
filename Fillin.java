package com.example.practice;

public class Fillin extends Question {
    @Override
    public String toString(){
        return getDescription().replace("{blank}", "_______");
    }
}
