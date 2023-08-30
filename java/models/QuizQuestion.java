package models;

import java.util.ArrayList;
import java.util.List;

public class QuizQuestion {
    private int questionID;
    private int quizID;
    private String questionText;
    private String categoryName;

    private List<QuizOption> options = new ArrayList<>();
    // Constructors, getters, and setters
    // ...

    public QuizQuestion(int questionID, int quizID, String questionText, String categoryName) {
		this.questionID = questionID;
		this.quizID = quizID;
		this.questionText = questionText;
		this.categoryName = categoryName;
	}

	@Override
    public String toString() {
        return "QuizQuestion [questionID=" + questionID + ", quizID=" + quizID + ", questionText=" + questionText
                + ", categoryName=" + categoryName + "]";
    }
	
	

	public String getQuestionText() {
		// TODO Auto-generated method stub
		return this.questionText;
	}

	public String getCategoryName() {
		// TODO Auto-generated method stub
		return this.categoryName;
	}

	public List<QuizOption> getOptions() {
        return options;
    }

	public void addOption(QuizOption quizOption) {

		options.add(quizOption);

	}

	public int getQuizID() {

		return this.quizID;
	}

	
}
