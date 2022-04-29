package com.example.practice;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Quiz {

    private String name;
    public final ArrayList<Question> questions = new ArrayList<>();

    Scanner in = new Scanner(System.in);
    /*Test test = new Test();
    Fillin fillin = new Fillin();*/
    // File not found exception
    // Arithmetic exception

    public Quiz()  {
    }

    public void setName(String s) {
        this.name = s;
    }

    public String getName() {
        return name;
    }

    public void addQuestions(Question sss){
        this.questions.add(sss);
        /*for (int i = 0; i < questions.size(); i++) {
            int value = (int)(Math.random()*questions.size());
            Question temp = questions.get(value);
            questions.set(value,questions.get(i));
            questions.set(i,temp);
        }*/
    }
    public Quiz loadFromFile(String s) throws FileNotFoundException {
        Quiz q = new Quiz();


        File file = new File(s);
        Scanner inFile = new Scanner(file);

        while (inFile.hasNextLine()){
            String line = inFile.nextLine();

            if (line.contains("{blank}")){
                Fillin fillin = new Fillin();
                fillin.setDescription(line);
                fillin.setAnswer(inFile.nextLine());
                addQuestions(fillin);
            }
            else {
                Test test = new Test();
                test.setDescription(line);
                String[] options = new String[4];
                for (int i = 0; i < 4; i++) {
                    options[i] = inFile.nextLine();
                }
                test.setAnswer(options[0]);
                test.setOptions(options);
                addQuestions(test);
            }
        }
        Collections.shuffle(questions);
        return q;
    }
    @Override
    public String toString() {
        return "============================================\n" +
                "Welcome to my \"" + getName() + "\" QUIZ! Good Luck!\n";
    }
    public void start()  {
        int count = 0;
        for (Question q :questions) {
            if (q instanceof Fillin) {
                System.out.println((count + 1) + "." + q.toString());
                System.out.print("Enter the correct answer: ");
                String s = in.next();
                if (s.equals(q.getAnswer())){
                    System.out.println("\nCorrect");
                    count++;
                }else {
                    System.out.println("\nIncorrect");
                    count++;
                }
            }
            else {
                ArrayList<String> arrayList = new ArrayList<>();
                for (int i = 0; i < 4; i++) {
                    arrayList.add("A) ");
                    arrayList.add("B) ");
                    arrayList.add("C) ");
                    arrayList.add("D) ");
                }
                System.out.println((count + 1) + "." + q.getDescription());
                for (int i = 0; i < 4; i++) {
                    System.out.println(arrayList.get(i) + ((Test)q).getOptionAt(i));
                }
                System.out.print("Enter the correct choice: ");
                String s = in.next();
                char symbols = s.charAt(0);
                int indexAnswer = (symbols - 'A');
                if(((Test)q).getOptionAt(indexAnswer).equals(q.getAnswer())){
                    System.out.println("Correct!");
                    count++;
                }else{
                    System.out.println("Incorrect");
                    count++;
                }
            }
        }
    }
    public void warn() {
        System.out.println("I want to warn you that fill answer writing only with lowercase registr");
    }
}
