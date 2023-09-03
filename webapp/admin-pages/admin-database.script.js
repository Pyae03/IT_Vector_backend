function fetchUserData() {
	fetch("http://localhost:8080/IT_Vector_Ver1/UserReadApiServlet", {
		method: "GET",
		headers: {
			"Content-Type": "application/json",
		},
		//body: JSON.stringify(dataToSend) //s
	})
		.then((response) => response.json())
		.then((users) => {
			console.log(users);
			users.forEach((user) => {
				console.log(
					"user: ",
					user.username,
					user.gender,
					user.dateOfBirth,
					user.userID,
					user.email
				);
				createUserDetail(
					user.username,
					user.gender,
					user.dateOfBirth,
					user.userID,
					user.email
				);
			});
		}) // hello world
		.catch((error) => {
			console.error("Error fetching user data:", error);
		});
}
fetchUserData();
// for user-update-form
const userUpdateForm = document.getElementById("user-update-form");
// for user-list
const user_list = document.querySelector(".user-list");
function createUserDetail(userName, gender, dateOfBirth, userID, email) {
	const userDetailDiv = document.createElement("div");
	userDetailDiv.classList.add("user-info-detail");

	// Create the profile container
	const profileDiv = document.createElement("div");
	profileDiv.classList.add("profile");

	// Create the anchor element
	const profileLink = document.createElement("a");
	profileLink.href = "#";

	// Create the profile image
	const profileImage = document.createElement("img");
	profileImage.src = "image/user-default.svg";
	profileImage.alt = "";

	// Create the user's name
	const username = document.createElement("p");
	username.textContent = userName;

	// Append image and name to the profile link
	profileLink.appendChild(profileImage);
	profileLink.appendChild(username);

	// Append profile link to the profile container
	profileDiv.appendChild(profileLink);

	// Create and append user information paragraphs
	const userGender = document.createElement("p");
	userGender.textContent = gender;

	const userDateOfBirth = document.createElement("p");
	userDateOfBirth.textContent = dateOfBirth;

	const userIdentification = document.createElement("p");
	userIdentification.textContent = userID;

	const userEmail = document.createElement("p");
	userEmail.textContent = email;

	// Append user information paragraphs to the main container
	userDetailDiv.appendChild(profileDiv);
	userDetailDiv.appendChild(userGender);
	userDetailDiv.appendChild(userDateOfBirth);
	userDetailDiv.appendChild(userIdentification);
	userDetailDiv.appendChild(userEmail);

	const btn_crud = document.createElement("div");
	btn_crud.className = "btn-crud";

	// Create and append the edit button
	const btn_edit = document.createElement("a");
	btn_edit.className = "btn-edit";
	const edit_icon = document.createElement("ion-icon");
	edit_icon.setAttribute("name", "create-outline");
	btn_edit.appendChild(edit_icon);

	// Create and append the delete button
	const btn_delete = document.createElement("a");
	btn_delete.className = "btn-delete";
	const delete_icon = document.createElement("ion-icon");
	delete_icon.setAttribute("name", "trash-outline");
	btn_delete.appendChild(delete_icon);

	btn_crud.appendChild(btn_edit);
	btn_crud.appendChild(btn_delete);

	btn_edit.addEventListener("click", () => {
		console.log("btn_eidt", userID);
		currentUserID = userID;
		userUpdateForm.classList.toggle("hidden");
		console.log("current user id: ", currentUserID);
	});
	btn_delete.addEventListener("click", () => {
		userDeletion(userID);
		console.log("btn_delete");
	});

	userDetailDiv.appendChild(btn_crud);

	// Append the main container to the list of user details
	user_list.appendChild(userDetailDiv);
}

//<!--function for user deletion -->
function userDeletion(currentUserID) {
	fetch("http://localhost:8080/IT_Vector_1/UserDeleteApiServlet", {
		method: "POST",
		headers: {
			"Content-Type": "application/json",
		},
		body: currentUserID,
	})
		.then(function (response) {
			if (response.ok) {
			} else {
			}
		})
		.catch(function (error) {});
}

//<!--function for user updat form -->

let currentUserID;
userUpdateForm.addEventListener("submit", function (event) {
	event.preventDefault();

	// Get form data
	const formData = new FormData(event.target);

	// Convert form data to JSON object
	const jsonObject = { userID: currentUserID };
	formData.forEach((value, key) => {
		jsonObject[key] = value;
	});

	console.log("Form data as JSON:", jsonObject);

	fetch("http://localhost:8080/Test/UserUpdateApiServlet", {
		method: "POST",
		headers: {
			"Content-Type": "application/json",
		},
		body: JSON.stringify(jsonObject),
	})
		.then((response) => {
			if (response.ok) {
				return response.json();
			} else {
				throw new Error("Error updating user.");
			}
		})
		.then((data) => {
			if (data.status === "success") {
				console.log("User updated successfully!");
			} else {
				console.error("User update failed.");
			}
		})
		.catch((error) => {
			console.error(error.message);
		});
});

const btn_add_user = document.querySelector(".btn-add-new-user");
const user_creation_form = document.querySelector(".user-creation-form");

btn_add_user.addEventListener("click", () => {
	user_creation_form.classList.toggle("hidden");
});

const user_name = document.querySelectorAll(".user-name");
user_name.forEach((un) => console.log(un.textContent));

// implement search user features
