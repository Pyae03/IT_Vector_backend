<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <link rel="stylesheet" href="user-profile.style.css" />
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>User Profile</title>
</head>
<body>
    <div class="container">
        <div class="user-profile card">
            <div class="bg-component">BG component</div>
            <div class="profile-img">
                <img class="profile-pic" src="image/muchiro.jpg" alt="" />
                <ion-icon class="edit-icon" name="options-outline"></ion-icon>
            </div>

            <div class="profile-info">
                <h3>${sessionScope.user.username}</h3>
                <p class="flag">🚩 ${sessionScope.user.gender}</p>
                <p class="role">🧩 Role: ${sessionScope.user.userRole}</p>
                <p>${sessionScope.user.email} <span>since ${sessionScope.user.registrationDate}</span></p>
                <button class="btn-share">
                    <span><ion-icon name="arrow-redo-outline"></ion-icon></span> Share
                    Profile
                </button>
            </div>
        </div>

        <div class="skill card">
            <h3>Skills</h3>
            <div class="skill-list">
                <p>Student</p>
                <p>FrontEnd</p>
                <p>Backend</p>
                <p>Full Stack</p>
                <p>Designer</p>
            </div>
        </div>
        
        
    </div>

    <div class="bio card">
        <h3>Bio</h3>
        <p>
            ${sessionScope.user.bio}
        </p>
    </div>
</body>
</html>
