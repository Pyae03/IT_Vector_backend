// FOR ASSIGNMENT

const assignmentForm = document.querySelector(".assignment-wrapper");

// create task
const btnCreateAssignment = document.querySelector(".btn-create-assignment");

// form close btn
const closeBtn = document.querySelector(".close-button");

btnCreateAssignment.addEventListener("click", () => {
	console.log("create");
	assignmentForm.style.display = "block";
});

closeBtn.addEventListener("click", () => {
	assignmentForm.style.display = "none";
});

// FOR QUIZ

const quizCreationForm = document.querySelector(".quiz-wrapper");

// create task
const btnCreateQuiz = document.querySelector(".btn-create-quiz");

// form close btn
const closeBtnQuiz = document.querySelector(".close-button-quiz");

btnCreateQuiz.addEventListener("click", () => {
	console.log("create");
	quizCreationForm.style.display = "block";
});

closeBtnQuiz.addEventListener("click", () => {
	quizCreationForm.style.display = "none";
});

// FOR QUIZ FUNCTIONALITY
const quizForm = document.getElementById("quizForm");
const addOptionButton = document.querySelector(".btn-add-option");

// Function to add a new option group
function addOptionGroup() {
	const optionsContainer = document.querySelector(".quiz-options");
	const optionGroup = document.createElement("div");
	optionGroup.className = "quiz-option-group";

	const radioButton = document.createElement("input");
	radioButton.type = "radio";
	radioButton.name = "quiz-question";
	//radioButton.required = true;

	const optionInput = document.createElement("input");
	optionInput.type = "text";
	optionInput.name = "option";
	optionInput.required = true;

	const correctAnswerSelect = document.createElement("select");
	correctAnswerSelect.name = "correct-answer";
	correctAnswerSelect.required = true;

	const correctOption = document.createElement("option");
	correctOption.value = "true";
	correctOption.textContent = "Correct";

	const incorrectOption = document.createElement("option");
	incorrectOption.value = "false";
	incorrectOption.textContent = "Incorrect";

	correctAnswerSelect.appendChild(incorrectOption);
	correctAnswerSelect.appendChild(correctOption);

	const deleteButton = document.createElement("button");
	deleteButton.textContent = "Delete";
	deleteButton.className = "btn-delete-option";
	deleteButton.addEventListener("click", deleteOptionGroup);

	optionGroup.appendChild(radioButton);
	optionGroup.appendChild(optionInput);
	optionGroup.appendChild(correctAnswerSelect);
	optionGroup.appendChild(deleteButton);

	optionsContainer.appendChild(optionGroup);

	return optionGroup;
}

// Function to delete an option group
function deleteOptionGroup(event) {
	event.preventDefault();
	const optionGroup = event.target.parentElement;
	const optionsContainer = optionGroup.parentElement;
	optionsContainer.removeChild(optionGroup);
}

addOptionButton.addEventListener("click", function (event) {
	event.preventDefault();
	addOptionGroup();
});

// Event delegation to handle delete buttons
document.addEventListener("click", function (event) {
	if (event.target.classList.contains("btn-delete-option")) {
		deleteOptionGroup(event);
	}
});

// Todo: write code for java servlet
// todo:  also style for quiz creation form
