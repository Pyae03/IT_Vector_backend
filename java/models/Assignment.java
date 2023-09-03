package models;

public class Assignment {
    private int assignmentID;
    private String assignmentName;
    private String assignmentFilePath;
    private int moduleID;

    public Assignment() {
        // Default constructor
    }

    public Assignment(String assignmentName, String assignmentFilePath, int moduleID) {
        this.assignmentName = assignmentName;
        this.assignmentFilePath = assignmentFilePath;
        this.moduleID = moduleID;
    }
    
    public Assignment(int assignmentID, String assignmentName, String assignmentFilePath, int moduleID) {
    	this.assignmentID = assignmentID;
        this.assignmentName = assignmentName;
        this.assignmentFilePath = assignmentFilePath;
        this.moduleID = moduleID;
    }

    public int getAssignmentID() {
        return assignmentID;
    }

    public void setAssignmentID(int assignmentID) {
        this.assignmentID = assignmentID;
    }

    public String getAssignmentName() {
        return assignmentName;
    }

    public void setAssignmentName(String assignmentName) {
        this.assignmentName = assignmentName;
    }

    public String getAssignmentFilePath() {
        return assignmentFilePath;
    }

    public void setAssignmentFilePath(String assignmentFilePath) {
        this.assignmentFilePath = assignmentFilePath;
    }

    public int getModuleID() {
        return moduleID;
    }

    public void setModuleID(int moduleID) {
        this.moduleID = moduleID;
    }

    @Override
    public String toString() {
        return "Assignment [assignmentID=" + assignmentID + ", assignmentName=" + assignmentName +
               ", assignmentFilePath=" + assignmentFilePath + ", moduleID=" + moduleID + "]";
    }
}
