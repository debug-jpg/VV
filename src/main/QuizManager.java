package main;

import java.awt.event.KeyEvent;
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
        questions = loadQuestions();
        currentQuestionIndex = 0;
        score = 0;
        showAnswerFeedback = false;
    }

    public List<Question> loadQuestions() {
        List<Question> questions = new ArrayList<>();
        questions.add(new Question("What is the capital of France?", new String[]{"1) Paris", "2) London", "3) Berlin", "4) Madrid"}, 0));
        questions.add(new Question("What is 2 + 2?", new String[]{"1) 3", "2) 4", "3) 5", "4) 6"}, 1));
        questions.add(new Question("What color is the sky?", new String[]{"1) Blue", "2) Green", "3) Red", "4) Yellow"}, 0));
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
