package main;

import java.io.*;
import java.util.*;
import java.util.List;

@SuppressWarnings("all")
public class QuizManager {
    GamePanel gp;

    // QUESTION RANDOMIZER //
    public String questions;
    public String A, B, C, D;
    public String correctAnswer;

    File[] file = new File[5];
    List<Question> questionList = new ArrayList<>();
    public int x;

    // ANSWER CHECKING //
    public boolean correct;
    public boolean wrong;

    // CLICK //
    public boolean a, b, c, d, confirm;

    public QuizManager(GamePanel gp) {
        this.gp = gp;
    }

    public void getQuestion() {
        Scanner inputFile;

        try {
            inputFile = new Scanner(file[x]);
            StringBuilder fileContent = new StringBuilder();

            while (inputFile.hasNext()) {
                String s = inputFile.nextLine();
                fileContent.append(s).append("\n");
            }

//            for (String questionString : perQuestion) {
//                if (questionString.trim().isEmpty()) {
//                    continue;
//                }
//                String[] token = questionString.split("\n");
//                if (token.length >= 6) {
//                    Question question = new Question();
//                    question.question = token[1];
//                    question.first = token[2];
//                    question.second = token[3];
//                    question.third = token[4];
//                    question.fourth = token[5];
//                    question.correctAnswer = token[6];
//                    questionList.add(question);
//                }
//                else {
//                    throw new Exception("Invalid question format: " + questionString);
//                }
//            }
//            random();
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    public void clear() {
        questionList.clear();
    }

    public void random() {
        Collections.shuffle(questionList);
        // GET USER ANSWER AND ASK QUEST
        for (Question question : questionList) {
            questions = question.question;
            A = question.first;
            B = question.second;
            C = question.third;
            D = question.fourth;
            correctAnswer = question.correctAnswer;
        }
    }

    public String getAnswer() {
        if (a) {
            return "A";
        }
        else if (b) {
            return "B";
        }
        else if (c) {
            return "C";
        }
        else if (d) {
            return "D";
        }
        return null;
    }

    public void checkAnswer() {
        String userAnswer = getAnswer();
        if (userAnswer != null) {
            if (userAnswer.equals(correctAnswer)) {
                correct = true;
                wrong = false;
                //SOUND EFFECT HERE
            }
            else {
                correct = false;
                wrong = true;
                //SOUND EFFECT HERE
            }
        }
    }

    public void update() {

    }

    public void input() {
        boolean placeholder = false;
        boolean placeholder2 = false;
        boolean placeholder3 = false;
        boolean placeholder4 = false;
        if (placeholder) {
            a = true;
            b = false;
            c = false;
            d = false;
            confirm = true;
        }
        if (placeholder2) {
            a = false;
            b = true;
            c = false;
            d = false;
            confirm = true;
        }
        if (placeholder3) {
            a = false;
            b = false;
            c = true;
            d = false;
            confirm = true;
        }
        if (placeholder4) {
            a = false;
            b = false;
            c = false;
            d = true;
            confirm = true;
        }
        if (confirm) {
            checkAnswer();
            random();
        }
    }

}

class Question {
    String question;
    String first, second, third, fourth;
    String correctAnswer;
}
















