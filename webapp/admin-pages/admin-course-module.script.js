// original jsp form
// course module
const add_course_module = document.querySelector(".add-course-module");
const delete_course_module = document.querySelector(".delete-course-module");
const parentContainer = document.querySelector(".container")

add_course_module.addEventListener("click", () => {
    	console.log("clicked");
    	module_creation_form.classList.toggle("hidden");
    });
    function createMaterialForm(moduleID) {
    	console.log("created!")
        // Create the main container for the form
        var formContainer = document.createElement("div");
        formContainer.classList.add("module-material-creation-form");

        // Create the form element
        var form = document.createElement("form");
        form.action = "AddMaterialServlet";
        form.method = "post";
        form.enctype = "multipart/form-data";

        // Create the hidden input for module ID
        var moduleIDInput = document.createElement("input");
        moduleIDInput.type = "hidden";
        moduleIDInput.name = "module-id";
        moduleIDInput.value = moduleID;

        // Create the Material Name input
        var materialNameLabel = document.createElement("label");
        materialNameLabel.textContent = "Material Name";
        var materialNameInput = document.createElement("input");
        materialNameInput.type = "text";
        materialNameInput.name = "material-name";

        // Create the Description textarea
        var descriptionLabel = document.createElement("label");
        descriptionLabel.textContent = "Description";
        var descriptionTextarea = document.createElement("textarea");
        descriptionTextarea.name = "material-description";
        descriptionTextarea.cols = "30";
        descriptionTextarea.rows = "10";

        // Create the Lecture Video File input
        var lectureLabel = document.createElement("label");
        lectureLabel.textContent = "Lecture Video File";
        var lectureFileInput = document.createElement("input");
        lectureFileInput.type = "file";
        lectureFileInput.name = "lecture-file";

        // Create the buttons container
        var btnGroup = document.createElement("div");
        btnGroup.classList.add("btn-group");

        // Create the Add Material button
        var submitButton = document.createElement("input");
        submitButton.type = "submit";
        submitButton.value = "Add Material";

        // Create the Cancel button
        var cancelButton = document.createElement("button");
        cancelButton.type = "button";
        cancelButton.textContent = "Cancel";
        cancelButton.className = "btn-cancel";
        cancelButton.onclick = () => destroyMaterialForm(); // Assuming cancel is a defined function

        // Append all elements to the form
        form.appendChild(moduleIDInput);
        form.appendChild(materialNameLabel);
        form.appendChild(materialNameInput);
        form.appendChild(descriptionLabel);
        form.appendChild(descriptionTextarea);
        form.appendChild(lectureLabel);
        form.appendChild(lectureFileInput);
        btnGroup.appendChild(submitButton);
        btnGroup.appendChild(cancelButton);
        form.appendChild(btnGroup);

        // Append the form to the main container
        formContainer.appendChild(form);

        // Append the main container to the desired parent element
        var parentElement = document.querySelector(".course-module-group"); // Change this selector as needed
        parentElement.appendChild(formContainer)
        
    	//course_group.insertBefore(formContainer)    
    }


    function destroyMaterialForm(){
     	const formContainer = document.querySelector(".module-material-creation-form")
     	formContainer.remove()
    }


// ----------------------------------------




// course module material
const add_course_module_material = document.querySelectorAll(
	".add-course-module-material"
);

const delete_course_module_material = document.querySelector(
	".delete-course-module-material"
);

// forms

const module_creation_form = document.querySelector(".module-creation-form");
//add_course_module.addEventListener("click", () => {
//	console.log("clicked");
//	module_creation_form.classList.toggle("hidden");
//});

// ----------------------------------------

const module_material_creation_form = document.querySelector(
	".module-material-creation-form"
);

	
// in uni
const add_task = document.querySelectorAll(".add-task");
//const addMaterial = document.querySelector(".add-material");
//const addAssignment = document.querySelector(".add-assignment");
//const addQuiz = document.querySelector(".add-quiz");


add_course_module_material.forEach(material => {
	
		material.addEventListener("click", (event) => {
		
		event.stopPropagation();
		
		//console.log("MATERIAL: ", material)
		console.log("EVENT: ", event.target.id)
		const clickedID = event.target.id;
				
		add_task.forEach( task => {
			
			//console.log("TASK: ", task)
			if(task.id === clickedID)
				task.classList.remove("hidden")
			else
				task.classList.add("hidden")
		})
		
	});	
})


//--------------------------------------------------------------------

// FOR ASSIGNMENT

//const assignmentForm = document.querySelector(".assignment-wrapper");

// create task
//const btnCreateAssignment = document.querySelector(".btn-create-assignment");

// form close btn
//const closeBtn = document.querySelector(".close-button");

//addAssignment.addEventListener("click", () => {
//	console.log("create");
//	assignmentForm.style.display = "block";
//});

//closeBtn.addEventListener("click", () => {
//	assignmentForm.style.display = "none";
//});

// FOR QUIZ
// 	MAIN-QUIZ-WRAPPER

//const mainQuizForm = document.querySelector(".main-quiz-wrapper");
//const btnQuiz = document.querySelector(".btn-quiz");
//const closeBtnMainQuiz = document.querySelector(".close-button-main-quiz");
//addQuiz.addEventListener("click", () => {
//	console.log("create");
//	mainQuizForm.style.display = "block";
//});

//closeBtnMainQuiz.addEventListener("click", () => {
//	mainQuizForm.style.display = "none";
//});



//ASSIGNMENT CREATION FORM
function assignmentCreationForm(moduleID) {
    const assignmentWrapper = document.createElement("div");
    assignmentWrapper.classList.add("assignment-wrapper");

    const nav = document.createElement("nav");

    const closeButton = document.createElement("span");
    closeButton.classList.add("close-button");
	


    const ionIcon = document.createElement("ion-icon");
    ionIcon.setAttribute("name", "close-outline");

    closeButton.appendChild(ionIcon);

    const h2 = document.createElement("h2");
    h2.textContent = "Assignment";

    nav.appendChild(closeButton);
    nav.appendChild(h2);

    const form = document.createElement("form");
    form.setAttribute("action", "AssignmentFileUploadServlet");
    form.setAttribute("method", "post");
    form.setAttribute("enctype", "multipart/form-data");

    const assignmentTitle = document.createElement("div");
    assignmentTitle.classList.add("assignment-title");

    const titleLabel = document.createElement("label");
    titleLabel.setAttribute("for", "title");
    titleLabel.textContent = "Title";

    const titleInput = document.createElement("input");
    titleInput.setAttribute("type", "text");
    titleInput.setAttribute("name", "assignmentName");

    assignmentTitle.appendChild(titleLabel);
    assignmentTitle.appendChild(titleInput);

    const description = document.createElement("div");
    description.classList.add("description");

    const descriptionLabel = document.createElement("label");
    descriptionLabel.setAttribute("for", "instructions");
    descriptionLabel.textContent = "Instructions (optional)";

    const textarea = document.createElement("textarea");
    textarea.setAttribute("name", "description");
    textarea.setAttribute("id", "");
    textarea.setAttribute("cols", "30");
    textarea.setAttribute("rows", "10");

    description.appendChild(descriptionLabel);
    description.appendChild(textarea);

    const fileUpload = document.createElement("div");
    fileUpload.classList.add("file-upload");

    const attachLabel = document.createElement("label");
    attachLabel.setAttribute("for", "attach");
    attachLabel.textContent = "Attach";

    const fileInput = document.createElement("input");
    fileInput.setAttribute("type", "file");
    fileInput.setAttribute("name", "file");

    fileUpload.appendChild(attachLabel);
    fileUpload.appendChild(fileInput);

    const assignmentSidebar = document.createElement("div");
    assignmentSidebar.classList.add("assignment-side-bar");

    const h3 = document.createElement("h3");
    h3.textContent = "Course Title: ";
    const courseSpan = document.createElement("span");
    courseSpan.textContent = "Web Application Development";
    h3.appendChild(courseSpan);

    const endDateLabel = document.createElement("label");
    endDateLabel.setAttribute("for", "end-date");
    endDateLabel.textContent = "End Date";

    const endDateInput = document.createElement("input");
    endDateInput.setAttribute("type", "date");
    endDateInput.setAttribute("name", "end-date");

    const submitInput = document.createElement("input");
    submitInput.setAttribute("type", "submit");
    submitInput.setAttribute("value", "Submit");

    const getModuleInput = document.createElement("input");
    getModuleInput.setAttribute("type", "text");
    getModuleInput.setAttribute("value", moduleID);
    getModuleInput.setAttribute("name", "get-module");
    getModuleInput.style.display = "none";

    assignmentSidebar.appendChild(h3);
    assignmentSidebar.appendChild(endDateLabel);
    assignmentSidebar.appendChild(endDateInput);
    assignmentSidebar.appendChild(submitInput);
    assignmentSidebar.appendChild(getModuleInput);

    form.appendChild(nav);
    form.appendChild(assignmentTitle);
    form.appendChild(description);
    form.appendChild(fileUpload);
    form.appendChild(assignmentSidebar);

    assignmentWrapper.appendChild(form);

    parentContainer.appendChild(assignmentWrapper);
    
    
    // close
    const assignmentCreationForm = document.querySelector(".assignment-wrapper")
    closeButton.addEventListener("click", () => {
		
		assignmentCreationForm.style.display = "none"
		})
}
// QUIZ CREATION FORM
function quizCreationForm(moduleID) {
    const mainQuizWrapper = document.createElement("div");
    mainQuizWrapper.classList.add("main-quiz-wrapper");

    const nav = document.createElement("nav");

    const closeButtonMainQuiz = document.createElement("span");
    closeButtonMainQuiz.classList.add("close-button-main-quiz");

    const ionIcon = document.createElement("ion-icon");
    ionIcon.setAttribute("name", "close-outline");

    closeButtonMainQuiz.appendChild(ionIcon);

    const h2 = document.createElement("h2");
    h2.textContent = "Quiz";

    nav.appendChild(closeButtonMainQuiz);
    nav.appendChild(h2);

    const form = document.createElement("form");
    form.setAttribute("action", "AddQuizServlet");
    form.setAttribute("method", "post");

    const quizTitle = document.createElement("div");
    quizTitle.classList.add("assignment-title");

    const titleLabel = document.createElement("label");
    titleLabel.setAttribute("for", "title");
    titleLabel.textContent = "Quiz Name";

    const quizNameInput = document.createElement("input");
    quizNameInput.setAttribute("type", "text");
    quizNameInput.setAttribute("name", "quizName");

    quizTitle.appendChild(titleLabel);
    quizTitle.appendChild(quizNameInput);

    const description = document.createElement("div");
    description.classList.add("description");

    const descriptionLabel = document.createElement("label");
    descriptionLabel.setAttribute("for", "instructions");
    descriptionLabel.textContent = "Instructions (optional)";

    const textarea = document.createElement("textarea");
    textarea.setAttribute("name", "description");
    textarea.setAttribute("id", "");
    textarea.setAttribute("cols", "30");
    textarea.setAttribute("rows", "10");

    description.appendChild(descriptionLabel);
    description.appendChild(textarea);

    const assignmentSidebar = document.createElement("div");
    assignmentSidebar.classList.add("assignment-side-bar");

    const h3 = document.createElement("h3");
    h3.textContent = "Course Title: ";
    const courseSpan = document.createElement("span");
    courseSpan.textContent = "Web Application Development";
    h3.appendChild(courseSpan);

    const timeLimitLabel = document.createElement("label");
    timeLimitLabel.setAttribute("for", "timeLimit");
    timeLimitLabel.textContent = "Time Limit";

    const timeLimitInput = document.createElement("input");
    timeLimitInput.setAttribute("type", "number");
    timeLimitInput.setAttribute("name", "timeLimit");

    const submitInput = document.createElement("input");
    submitInput.setAttribute("type", "submit");
    submitInput.setAttribute("value", "Submit");

    const getModuleInput = document.createElement("input");
    getModuleInput.setAttribute("type", "text");
    getModuleInput.setAttribute("value", moduleID);
    getModuleInput.setAttribute("name", "get-module");
    getModuleInput.style.display = "none";

    assignmentSidebar.appendChild(h3);
    assignmentSidebar.appendChild(timeLimitLabel);
    assignmentSidebar.appendChild(timeLimitInput);
    assignmentSidebar.appendChild(submitInput);
    assignmentSidebar.appendChild(getModuleInput);

    form.appendChild(nav);
    form.appendChild(quizTitle);
    form.appendChild(description);
    form.appendChild(assignmentSidebar);

    mainQuizWrapper.appendChild(form);

    document.body.appendChild(mainQuizWrapper);
}






