package models;

public class Quiz {
    private int quizID;
    private int courseID;
    private int moduleID;
    private String quizName;
    private String description;
    private int timeLimit;

    // Constructors
    public Quiz() {
        // Default constructor
    }

    public Quiz(int quizID, String quizName, String description) {
        this.quizID = quizID;
        
        this.quizName = quizName;
        this.description = description;
        
    }

    public Quiz(int courseID, int moduleID, String quizName, String description) {
    	this.courseID = courseID;
    	this.moduleID = moduleID;
        this.quizName = quizName;
        this.description = description;
        
    }
    
    public Quiz(int courseID, int moduleID, int quizID, String quizName, String description) {
    	this.courseID = courseID;
    	this.moduleID = moduleID;
    	this.quizID = quizID;
    	this.quizName = quizName;
    	this.description = description;
    }

    public Quiz(int quizID, int courseID, int moduleID, String quizName, String description, int timeLimit) {
        this.quizID = quizID;
        this.courseID = courseID;
        this.moduleID = moduleID;
        this.quizName = quizName;
        this.description = description;
        this.timeLimit = timeLimit;
    }

    // Getters and Setters
    public int getQuizID() {
        return quizID;
    }

    public void setQuizID(int quizID) {
        this.quizID = quizID;
    }

    public int getCourseID() {
        return courseID;
    }

    public void setCourseID(int courseID) {
        this.courseID = courseID;
    }

    public int getModuleID() {
        return moduleID;
    }

    public void setModuleID(int moduleID) {
        this.moduleID = moduleID;
    }

    public String getQuizName() {
        return quizName;
    }

    public void setQuizName(String quizName) {
        this.quizName = quizName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getTimeLimit() {
        return timeLimit;
    }

    public void setTimeLimit(int timeLimit) {
        this.timeLimit = timeLimit;
    }

    // toString() method for debugging and logging
    @Override
    public String toString() {
        return "Quiz [quizID=" + quizID + ", courseID=" + courseID + ", moduleID=" + moduleID +
               ", quizName=" + quizName + ", description=" + description + ", timeLimit=" + timeLimit + "]";
    }
}
