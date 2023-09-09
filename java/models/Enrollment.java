package models;

import java.sql.Timestamp;

public class Enrollment {
    private int enrollmentID;
    private String studentID;
    private int courseID;
    private Timestamp enrollmentDate;

    // Constructors
    public Enrollment() {
        // Default constructor
    }

    public Enrollment(int enrollmentID, String studentID, int courseID, Timestamp enrollmentDate) {
        this.enrollmentID = enrollmentID;
        this.studentID = studentID;
        this.courseID = courseID;
        this.enrollmentDate = enrollmentDate;
    }

    // Getters and setters
    public int getEnrollmentID() {
        return enrollmentID;
    }

    public void setEnrollmentID(int enrollmentID) {
        this.enrollmentID = enrollmentID;
    }

    public String getStudentID() {
        return studentID;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    public int getCourseID() {
        return courseID;
    }

    public void setCourseID(int courseID) {
        this.courseID = courseID;
    }

    public Timestamp getEnrollmentDate() {
        return enrollmentDate;
    }

    public void setEnrollmentDate(Timestamp enrollmentDate) {
        this.enrollmentDate = enrollmentDate;
    }

    // toString method for debugging and printing
    @Override
    public String toString() {
        return "Enrollment{" +
                "enrollmentID=" + enrollmentID +
                ", studentID=" + studentID +
                ", courseID=" + courseID +
                ", enrollmentDate=" + enrollmentDate +
                '}';
    }
}

