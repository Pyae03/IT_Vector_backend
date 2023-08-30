package models;

import java.sql.Date;

public class QuizResult {

	
	private String quizResultID;
	private int quizID;
	private String userID;
	private int score;
	private Date answerDate;
	
	
	public QuizResult() {
		
	}
	
	public QuizResult(int quizID, String userID, int score) {
		this.setQuizID(quizID);
		this.setUserID(userID);
		this.setScore(score);
		//this.setAnswerDate(answerDate);
	}

	public QuizResult(String quizResultID2, int quizID2, String userID2, int score2, java.util.Date answerDate2) {
		// TODO Auto-generated constructor stub
	}

	public String getQuizResultID() {
		return quizResultID;
	}

	public void setQuizResultID(String quizResultID) {
		this.quizResultID = quizResultID;
	}

	public int getQuizID() {
		return quizID;
	}

	public void setQuizID(int quizID) {
		this.quizID = quizID;
	}

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public Date getAnswerDate() {
		return answerDate;
	}

	public void setAnswerDate(Date answerDate) {
		this.answerDate = answerDate;
	}
}
