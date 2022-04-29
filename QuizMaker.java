package com.example.practice;

import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class QuizMaker extends Application {

    private Stage windows;
    private Label label;
    private ArrayList<Question> quiz = new ArrayList<>();
    private final double W = 605, H = 405;
    private MediaPlayer mediaPlayer;
    private Media media1;
    private File media11;
    private int i = 1;
    private int count = 0;
    private ArrayList<String> counter = new ArrayList<>();
    private String[] field;
    private ArrayList<String> descript = new ArrayList<>();
    private Label description;
    private RadioButton red, green, orange, pink;
    private int[] tester;

    @Override
    public void start(Stage stage) {

        windows = stage;
        windows.setScene(new Scene(chooseFile(),W, H));
        windows.show();

    }

    public StackPane chooseFile(){
        int i = 5;
        Image image = new Image("C:\\Users\\arupm\\IdeaProjects\\Practice\\src\\main\\java\\com\\example\\practice\\background.jpg");
        ImageView imageView = new ImageView(image);
        FadeTransition fadeTransition = new FadeTransition(Duration.seconds(i),imageView);
        imageView.setFitWidth(W);
        imageView.setFitHeight(H);
        double fr = 0, to = 2;
        fadeTransition.setFromValue(fr);
        fadeTransition.setToValue(to);
        fadeTransition.play();

        StackPane stackPane = new StackPane();
        stackPane.getChildren().add(imageView);
        Quiz quiz = new Quiz();
        Button buttonFile = new Button(lab());

        stackPane.getChildren().addAll(buttonFile);

        buttonFile.setOnAction(actionEvent -> {
            FileChooser fc = new FileChooser();
            File file = fc.showOpenDialog(windows);
            media();
            try {
                quiz.loadFromFile(file.getPath());
                this.quiz = quiz.questions;
                field = new String[this.quiz.size()];
                tester = new int[this.quiz.size()];

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

            windows.setScene(new Scene(curr(0), W, H));
        });
        return stackPane;
    }

    public String lab(){
        return "Choose a file";
    }

    public BorderPane curr(int index){
        BorderPane pane = new BorderPane();
        StackPane stackPane = new StackPane();
        Question s = quiz.get(index);
            Button next = new Button(">>");
            Button prev = new Button("<<");
            next.setOnAction(actionEvent -> {
                windows.setScene(new Scene(curr(index + 1), W, H));
            });
            prev.setOnAction(actionEvent -> {
                windows.setScene(new Scene(curr(index - 1), W, H));
            });
            if (index == 0) {
                prev.setVisible(false);
            }
            if (index == quiz.size() - 1) {
                next.setVisible(false);
            }

            Image k = new Image("C:\\Users\\arupm\\IdeaProjects\\Practice\\src\\main\\java\\com\\example\\practice\\k.png");
            ImageView imageView1 = new ImageView(k);
            imageView1.setFitWidth(40);
            imageView1.setFitHeight(20);


            pane.setRight(new StackPane(next));
            pane.setLeft(new StackPane(prev));
            pane.setTop(stackPane);



                if (s instanceof Fillin fillin) {
                    label = new Label(quiz.get(index).toString(),imageView1);
                    stackPane.getChildren().add(label);
                    int size = 20;

                    label.setFont(Font.font("Times New Roman", FontWeight.MEDIUM, FontPosture.REGULAR, size));
                    label.setWrapText(true);
                    label.setContentDisplay(ContentDisplay.LEFT);

                    TextField textField = new TextField();
                    if (field[index] != null){
                        textField.setText(field[index]);
                    }

                    int i = 300, j = 20;
                    textField.setMaxWidth(i);
                    textField.setMaxHeight(j);

                    textField.setOnKeyTyped(e -> {
                        field[index] = textField.getText();
                    });


                    Image image = new Image("C:\\Users\\arupm\\IdeaProjects\\Practice\\src\\main\\java\\com\\example\\practice\\fillin.png");
                    ImageView imageView = new ImageView(image);
                    int q = 300, w = 200;
                    imageView.setFitWidth(q);
                    imageView.setFitHeight(w);

                    VBox vbox = new VBox();
                    vbox.getChildren().addAll(imageView, textField);
                    vbox.setAlignment(Pos.CENTER);



                    pane.setCenter(new StackPane(vbox));

                } else {
                    label = new Label(quiz.get(index).toString());
                    stackPane.getChildren().add(label);

                    int size = 20;

                    label.setFont(Font.font("Times New Roman", FontWeight.MEDIUM, FontPosture.REGULAR, size));
                    label.setWrapText(true);

                    Test test = ((Test) s);
                    /*RadioButton*/ orange = kahoot(test.getOptionAt(0), "orange");
                    orange.setWrapText(true);
                    /*RadioButton*/ red = kahoot(((Test) s).getOptionAt(1), "red");
                    red.setWrapText(true);
                    /*RadioButton*/ green = kahoot(((Test) s).getOptionAt(2), "green");
                    green.setWrapText(true);
                    /*RadioButton*/ pink = kahoot(((Test) s).getOptionAt(3), "pink");
                    pink.setWrapText(true);

                    ToggleGroup toggleGroup = new ToggleGroup();
                    red.setToggleGroup(toggleGroup);
                    orange.setToggleGroup(toggleGroup);
                    green.setToggleGroup(toggleGroup);
                    pink.setToggleGroup(toggleGroup);

                    VBox vBox = new VBox();
                    VBox vBox1 = new VBox();
                    HBox hBox = new HBox();



                    Image image = new Image("C:\\Users\\arupm\\IdeaProjects\\Practice\\src\\main\\java\\com\\example\\practice\\kahoot.gif");
                    ImageView imageView = new ImageView(image);
                    int z = 200, x = 100;
                    imageView.setFitWidth(z);
                    imageView.setFitHeight(x);
                    pane.setCenter(imageView);

                    vBox.getChildren().addAll(green, pink);
                    vBox1.getChildren().addAll(orange, red);
                    int a = 5;
                    vBox.setSpacing(a);
                    vBox1.setSpacing(a);

                    hBox.getChildren().addAll(vBox, vBox1);
                    hBox.setSpacing(a);
                    hBox.setAlignment(Pos.CENTER);
                    pane.setBottom(new StackPane(hBox));

                    if (tester[index] != 0){
                        if (tester[index] == -1){
                            red.fire();
                        }
                        if (tester[index] == -2){
                            green.fire();
                        }
                        if (tester[index] == -3){
                            orange.fire();
                        }
                        if (tester[index] == -4){
                            pink.fire();
                        }
                    }

                    red.setOnAction(actionEvent -> {
                        if (red.isSelected()){
                            tester[index] = -1;
                        }
                        field[index] = red.getText();
                    });
                    green.setOnAction(actionEvent -> {
                        if(green.isSelected()) {
                            tester[index] = -2;
                        }
                        field[index] = green.getText();
                    });
                    orange.setOnAction(actionEvent -> {
                        if (orange.isSelected()){
                            tester[index] = -3;
                        }
                        field[index] = orange.getText();
                    });
                    pink.setOnAction(actionEvent -> {
                        if(pink.isSelected()){
                            tester[index] = -4;
                        }
                        field[index] = pink.getText();
                    });
                }

                if (index == quiz.size()-1){
                    Button button = new Button(but());
                    pane.setRight(new StackPane(button));
                    button.setOnAction(actionEvent -> {
                        windows.setScene(new Scene(closePane(),W,H));
                    });
                }
        return  pane;
    }

    public String but(){
        return "!!!";
    }

    public int ret(){
        return 0;
    }public int ret2(){
        return ret();
    }

    public  int ret3(){
        return ret2();
    }

    public String ret4(){
        return Integer.toString(ret3());
    }

    public int Width(){
        return 300;
    }

    public int Height(){
        return 150;
    }

    public String last(){
        return "Your score: ";
    }

    public String button(){
        return "Close the Quiz";
    }

    public BorderPane closePane(){
        BorderPane borderPane = new BorderPane();
        counterr();
        Label label1 = new Label(last()+ count+ "/"+ quiz.size());
        Label label2 = new Label("Your percentage is: "+ (((float)count/ quiz.size())*100)+" %");
        Button show = new Button("Show the answer");
        
        show.setOnAction(actionEvent -> {

          windows.setScene(new Scene(showAnswer(), W, H));
          windows.show();
        });
        
        Button close = new Button(button());

        Font font = Font.font("Times New Roman", FontWeight.MEDIUM, FontPosture.REGULAR, 18);

        show.setFont(font);
        show.setMinWidth(200);
        show.setMinHeight(40);
        show.setStyle("-fx-background-color: green");

        close.setFont(font);
        close.setMinWidth(200);
        close.setMinHeight(40);
        close.setStyle("-fx-background-color: red");
        close.setOnAction(actionEvent -> {

            System.exit(Integer.parseInt(ret4()));
        });

        Image image = new Image("C:\\Users\\arupm\\IdeaProjects\\Practice\\src\\main\\java\\com\\example\\practice\\result.png");
        ImageView imageView = new ImageView(image);
        int i = Width(), j = Height();
        imageView.setFitWidth(i);
        imageView.setFitHeight(j);

        HBox hBox = new HBox();
        hBox.setAlignment(Pos.TOP_CENTER);
        hBox.getChildren().addAll(label1);

        HBox hBox2 = new HBox();
        hBox2.setAlignment(Pos.BOTTOM_CENTER);
        hBox2.getChildren().add(imageView);

        VBox vBox = new VBox(30);
        vBox.setAlignment(Pos.TOP_CENTER);
        vBox.getChildren().addAll(hBox,label2);

        VBox vBox2 = new VBox();
        vBox2.getChildren().add(hBox2);

        VBox vBox1 = new VBox();
        vBox1.getChildren().addAll(show, close);
        vBox1.setSpacing(5);
        vBox1.setAlignment(Pos.CENTER);

        borderPane.setTop(new StackPane(vBox));
        /*borderPane.setTop(new StackPane(vBox3));*/
        borderPane.setCenter(new StackPane(vBox1));
        borderPane.setBottom(new StackPane(vBox2));

        return borderPane;
    }

    public RadioButton kahoot(String text, String color){
        int n = 18;
        Font font = Font.font("Times New Roman", FontWeight.MEDIUM, FontPosture.REGULAR, n);
        RadioButton button = new RadioButton(text);
        int i = 80, j = 295;
        button.setMinHeight(i);
        button.setMinWidth(j);
        button.setStyle("-fx-background-color:"+color);
        button.setFont(font);

        return  button;
    }

    public MediaPlayer media(){
        media11 = new File("C:\\Users\\arupm\\IdeaProjects\\Practice\\src\\main\\java\\com\\example\\practice\\kahoot_music.mp3");
        media1 = new Media(media11.toURI().toString());
        mediaPlayer = new MediaPlayer(media1);
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        mediaPlayer.play();

        return  mediaPlayer;
    }

    public BorderPane showAnswer(){
        BorderPane pane = new BorderPane();
        String ss = "The question  ==> right answer";
        Label finish = new Label(ss);

        finish.setFont(Font.font("Times New Roman",FontWeight.BOLD, FontPosture.ITALIC, 22));

        VBox vBox = new VBox();
        finish.setAlignment(Pos.TOP_CENTER);
        for (Question question : quiz) {
            description = new Label(""+(i++) +") "+ question.getDescription().replace("{blank}", "____")+"   ==>  "+question.getAnswer());
            description.setFont(Font.font("Times New Roman",FontWeight.BOLD, FontPosture.REGULAR, 18));

            vBox.setSpacing(10);
            vBox.getChildren().add(description);
        }
        description.setWrapText(true);
        vBox.setAlignment(Pos.CENTER);
        pane.setCenter(new StackPane(vBox));
        pane.setTop(new StackPane(finish));

        return pane;
    }
    public int counterr(){
        for (int j = 0; j < quiz.size(); j++) {
            if (field[j] != null && field[j].equals(quiz.get(j).getAnswer())){
                    count++;
            }
        }
        return count;
    }

}
