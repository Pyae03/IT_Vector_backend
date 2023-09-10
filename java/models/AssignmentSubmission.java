package models;

import java.sql.Timestamp;

public class AssignmentSubmission {
    private int submissionID;
    private int assignmentID;
    private String userID;
    private Timestamp submissionDate;
    private String filePath;

    // Constructors
    public AssignmentSubmission() {
    }

    public AssignmentSubmission(int assignmentID, String userID, String filePath) {
        this.assignmentID = assignmentID;
        this.userID = userID;
        this.filePath = filePath;
    }

    // Getter and Setter methods
    public int getSubmissionID() {
        return submissionID;
    }

    public void setSubmissionID(int submissionID) {
        this.submissionID = submissionID;
    }

    public int getAssignmentID() {
        return assignmentID;
    }

    public void setAssignmentID(int assignmentID) {
        this.assignmentID = assignmentID;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public Timestamp getSubmissionDate() {
        return submissionDate;
    }

    public void setSubmissionDate(Timestamp submissionDate) {
        this.submissionDate = submissionDate;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public String toString() {
        return "AssignmentSubmission{" +
                "submissionID=" + submissionID +
                ", assignmentID=" + assignmentID +
                ", userID='" + userID + '\'' +
                ", submissionDate=" + submissionDate +
                ", filePath='" + filePath + '\'' +
                '}';
    }
}
