package com.example.practice;

import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.FileNotFoundException;

public class HelloApplication /*extends Application*/ {
/*
    @Override
    public void start(Stage stage) {
        Image image = new Image("C:\\Users\\arupm\\IdeaProjects\\Practice\\src\\main\\java\\com\\example\\practice\\background.jpg");
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(600);
        imageView.setFitWidth(900);
        FadeTransition fadeTransition = new FadeTransition(Duration.seconds(5),imageView);
        fadeTransition.setFromValue(0.0);
        fadeTransition.setToValue(2.0);
        fadeTransition.play();

        StackPane stackPane = new StackPane();
        Button button = new Button("Choose a file");


        stackPane.getChildren().add(imageView);
        stackPane.getChildren().add(button);
        Scene scene = new Scene(stackPane,900,600);
        stage.setTitle("Kahoot");
        stage.setScene(scene);
        stage.show();
    }*/

    public static void main(String[] args) throws FileNotFoundException {
        Quiz quiz = new Quiz();
        quiz.setName("JavaQuizFromKazakhstan");
        //System.out.println(quiz);
        quiz.warn();
        try {
            quiz.loadFromFile("C:\\Users\\arupm\\IdeaProjects\\Practice\\src\\main\\java\\com\\example\\practice\\quiz.txt");
            quiz.start();
        }catch(FileNotFoundException exception){
            System.out.println("Such a file does not exist");
        }
        /*
        quiz.start("C:\\Users\\arupm\\IdeaProjects\\Practice\\src\\main\\java\\com\\example\\practice\\quiz.txt");
*/
        /*quiz.list();*/
    }
}