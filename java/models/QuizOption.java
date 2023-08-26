package models;

public class QuizOption {
    private int quizOptionID;
    private int questionID;
    private String optionText;
    private boolean isCorrect;

    // Constructors, getters, and setters
    // ...

    public QuizOption(int quizOptionID, int questionID, String optionText, boolean isCorrect) {
		
    	this.quizOptionID = quizOptionID;
    	this.questionID = questionID;
    	this.optionText = optionText;
    	this.isCorrect = isCorrect;
	}

	@Override
    public String toString() {
        return "QuizOption [quizOptionID=" + quizOptionID + ", questionID=" + questionID + ", optionText=" + optionText
                + ", isCorrect=" + isCorrect + "]";
    }

	public String getOptionText() {
		// TODO Auto-generated method stub
		return this.optionText;
	}

	public boolean isCorrect() {
		// TODO Auto-generated method stub
		return this.isCorrect;
	}
	
	public int getOptionID() {
		return this.quizOptionID;
	}

	

	

	

	
}

