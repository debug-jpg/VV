package main;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

class Question {
    String questionText;
    String[] options;
    int correctOption;

    public Question(String questionText, String[] options, int correctOption) {
        this.questionText = questionText;
        this.options = options;
        this.correctOption = correctOption;
    }
}

public class QuizManager {
    GamePanel gp;
    List<Question> questions;
    public int currentQuestionIndex;
    public int score;
    public boolean showAnswerFeedback;

    public QuizManager(GamePanel gp) {
        this.gp = gp;
        questions = loadQuestions("/quiz/quiz.txt");
        currentQuestionIndex = 0;
        score = 0;
        showAnswerFeedback = false;
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
                questions.add(new Question(questionText, options, correctOption));

                reader.readLine();
            }
        }
        catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error Reading this file: " + e.getMessage() + " Exiting...", "Error!!", JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }
        return questions;
    }

    public void checkAnswer(int answerIndex) {
        if (currentQuestionIndex < questions.size()) {
            Question question = questions.get(currentQuestionIndex);
            if (answerIndex == question.correctOption) {
                score++;
                showAnswerFeedback = true;
            } else {
                showAnswerFeedback = false;
            }
            currentQuestionIndex++;

            gp.ui.drawQuizState();
        }
    }
}
