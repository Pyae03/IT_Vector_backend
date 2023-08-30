package models;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class User {
    private String userID;
    private String username;
    private String password;
    private String email;
    private Date dateOfBirth;
    private String gender;
    private String bio;
    private String userRole;
    private Date registrationDate;

    // Constructors, getters, and setters

    public User() {
    }
    
    
    public User(String username, String password, String email,
    		String gender, Date dateOfBirth) {

    	this.username = username;
        this.password = password;
        this.email = email;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
    }
    
    // for admin
//    public User(String username, String password, String email,
//    		String gender, String role, Date dateOfBirth) {
//
//    	this.username = username;
//        this.password = password;
//        this.email = email;
//        this.gender = gender;
//        this.userRole = role;
//        this.dateOfBirth = dateOfBirth;
//    }
    
// for current login user
    
    public User(String userID, String username1, String email1, String gender1, 
    		String bio1, String userRole1, Date registrationDate1) {
    	this.userID = userID;
    	this.username = username1;
    	this.email = email1;
    	//this.dateOfBirth = dateOfBirth;
    	this.gender = gender1;
    	this.bio = bio1;
    	this.userRole = userRole1;
    	this.registrationDate = registrationDate1;
    }
    

    public User(String userID, String username, String password, String email,
                Date dateOfBirth, String gender, String bio, String userRole,
                Date registrationDate) {
        this.userID = userID;
        this.username = username;
        this.password = password;
        this.email = email;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.bio = bio;
        this.userRole = userRole;
        this.registrationDate = registrationDate;
    }
    

    // Getter and setter methods for all fields
    // ...

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {

        this.dateOfBirth = dateOfBirth;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

    public Date formatDate(Date date) throws ParseException {

//		Date data = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String formatedDate = sdf.format(date);
		Date convertedDate = sdf.parse(formatedDate);

		return convertedDate;
    }
}
