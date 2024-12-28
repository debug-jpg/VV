package main;

import entity.Entity;
import object.Key;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class Question {
    String questionText;
    String[] options;
    int correctOption;
    String imagePath;

    public Question(String questionText, String[] options, int correctOption, String imagePath) {
        this.questionText = questionText;
        this.options = options;
        this.correctOption = correctOption;
        this.imagePath = imagePath;
    }
}

@SuppressWarnings("all")
public class QuizManager {
    GamePanel gp;
    List<Question> questions;
    public int currentQuestionIndex;
    public int score;
    public boolean showAnswerFeedback;

    private Key currentKey;

    public QuizManager(GamePanel gp) {
        this.gp = gp;
        questions = loadQuestions("/quiz/quiz.txt");
        currentQuestionIndex = 0;
        score = 0;
        showAnswerFeedback = false;
    }

    public void setCurrentKey(Key key) {
        this.currentKey = key;
    }

    public List<Question> loadQuestions(String filepath) {
        List<Question> questions = new ArrayList<>();
        try {
            InputStream stream = getClass().getResourceAsStream(filepath);
            assert stream != null;
            BufferedReader reader = new BufferedReader(new InputStreamReader(stream));

            String line;
            while ((line = reader.readLine()) != null ) {
                String questionText = line;
                String[] options = new String[4];
                for (int i = 0; i < 4; i++) {
                    options[i] = reader.readLine();
                }
                int correctOption = Integer.parseInt(reader.readLine());
                String imagePath = reader.readLine();
                questions.add(new Question(questionText, options, correctOption, imagePath));

                reader.readLine();
            }
        }
        catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error Reading this file: " + e.getMessage() + " Exiting...", "Error!!", JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }
        Collections.shuffle(questions);
        return questions;
    }

    public void checkAnswer(int answerIndex) {
        if (currentQuestionIndex < questions.size()) {
            Question question = questions.get(currentQuestionIndex);
            if (answerIndex == question.correctOption) {
                score++;
                showAnswerFeedback = true;

                if (currentKey != null) {
                    currentKey.accessibilty = true;
                }

                currentQuestionIndex++;
            } else {
                showAnswerFeedback = false;
            }
            gp.gameState = gp.playState;
            gp.ui.drawQuizState();
        }
    }
}
