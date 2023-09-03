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
	radioButton.name = "correctOptions[]";
	radioButton.value = 0
	//radioButton.required = true;
	
	radioButton.addEventListener("click", () => {
		radioButton.value = 1
		console.log("selected!")
		
	})

	const optionInput = document.createElement("input");
	optionInput.type = "text";
	optionInput.name = "optionTexts[]";
	optionInput.required = true;

	

	

	const deleteButton = document.createElement("button");
	deleteButton.textContent = "Delete";
	deleteButton.className = "btn-delete-option";
	deleteButton.addEventListener("click", deleteOptionGroup);

	optionGroup.appendChild(radioButton);
	optionGroup.appendChild(optionInput);
	
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



// Event listener for form submission
quizForm.addEventListener("submit", function (event) {
    // Find the selected radio button and set its value to "1"
    const selectedRadioButton = document.querySelector('input[name="correctOptions[]"]:checked');
    if (selectedRadioButton) {
        selectedRadioButton.value = "1";
    }
    // The form will now be submitted with the correct values
});



// 	MAIN-QUIZ-WRAPPER

const mainQuizForm = document.querySelector(".main-quiz-wrapper");
const btnQuiz = document.querySelector(".btn-quiz");
const closeBtnMainQuiz = document.querySelector(".close-button-main-quiz");
btnQuiz.addEventListener("click", () => {
	console.log("create");
	mainQuizForm.style.display = "block";
});

closeBtnMainQuiz.addEventListener("click", () => {
	mainQuizForm.style.display = "none";
});
// Todo: write code for java servlet
// todo:  also style for quiz creation form
