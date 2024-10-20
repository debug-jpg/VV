package main;

import java.awt.Color;
import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

public class QuizManager {

	GamePanel gp;

	public String Question = "";
	public String A = "", B = "", C = "", D = "";
	public String correctAnswer = "";

	List<Question> questionList = new ArrayList<>();

	AssetSetter setter;
	public boolean Correct, Wrong;

	// CLICK
	public boolean a, b, c, d;

	InputStream inputStream;

	public QuizManager(GamePanel gp) {
		this.gp = gp;
		setter = new AssetSetter(gp);
		inputStream = getClass().getResourceAsStream("/quiz/quiz.txt");

	}

	public void getQuestion() {
		Scanner inputFile;

		try (BufferedReader fr = new BufferedReader(new InputStreamReader(inputStream))) {
			inputFile = new Scanner(fr);
			StringBuilder fileContent = new StringBuilder();

			while (inputFile.hasNext()) {
				String s = inputFile.nextLine();
				fileContent.append(s).append("\n");
			}

			String[] perQuestion = fileContent.toString().split("///");

			for (String questionString : perQuestion) {
				if (questionString.trim().isEmpty()) {
					continue;
				}
				String[] token = questionString.split("\n");
				if (token.length >= 6) {
					Question question = new Question();
					question.question = token[1];
					question.first = token[2];
					question.second = token[3];
					question.third = token[4];
					question.fourth = token[5];
					question.correctAnswer = token[6];

					questionList.add(question);
				} else {
					System.out.println("Incomplete question format: " + questionString);
				}
			}
			random();
		} catch (IOException e) {
			System.out.println("Error: " + e.getMessage());
		}
	}

	public void clear() {
		questionList.clear();
	}

	public void random() {
		Collections.shuffle(questionList);
		for (Question question : questionList) {
			Question = question.question;
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
		} else if (b) {
			return "B";
		} else if (c) {
			return "C";
		} else if (d) {
			return "D";
		}

		return null;
	}

	public void checkAnswer() {
		String userAnswer = getAnswer();
		if (userAnswer != null) {
            Correct = userAnswer.equals(correctAnswer);
            Wrong = false;
            gp.playSE(2);
        }
	}

	public void render(Graphics2D g2) {
		g2.drawImage(setter.backgroundImage, 0, 0, null);

		boolean inQA, inQB, inQC, inQD;

		int BoxLength = 300;
		int BoxWidth = 120;

		// POPUP SCREEN
		int TextBoxY = 225;

		int TextBoxAX = 125;

        int TextBoxBX = 125;
		int TextBoxBY = TextBoxY + 150;

		int TextBoxCX = 600;

        int TextBoxDX = 600;
		int TextBoxDY = TextBoxY + 150;

		getQuestion();
		if (Question != null) {
			setter.textBox(TextBoxAX, TextBoxY, 200, 100, g2);
			setter.textBox(TextBoxBX, TextBoxBY, 200, 100, g2);
			setter.textBox(TextBoxCX, TextBoxY, 200, 100, g2);
			setter.textBox(TextBoxDX, TextBoxDY, 200, 100, g2);

			g2.setColor(Color.BLACK);
			int Qy = 125;
			int Qx = 320;
			setter.printText(Question, Qx, Qy, 48, 55, false, g2);

			setter.printText(A, TextBoxAX + 20, TextBoxY + 65, 48, 30, false, g2);
			setter.printText(B, TextBoxBX + 20, TextBoxBY + 65, 48, 30, false, g2);
			setter.printText(C, TextBoxCX + 20, TextBoxY + 65, 48, 30, false, g2);
			setter.printText(D, TextBoxDX + 20, TextBoxDY + 65, 48, 30, false, g2);
		} else {
			System.err.println("Question is null");
		}
	}

	public boolean stopTimer;
	public int seconds;

	public void timer() {
		stopTimer = false;
		seconds = 0;
		Timer timer = new Timer();
		TimerTask task = new TimerTask() {

			@Override
			public void run() {
				if (seconds > 0) {
					seconds--;
				} else {
					timer.cancel();
				}
				if (stopTimer) {
					timer.cancel();
				}

			}
		};
		timer.scheduleAtFixedRate(task, 0, 1000);
	}

}

class Question {
	String question;
	String first, second, third, fourth;
	String correctAnswer;
}