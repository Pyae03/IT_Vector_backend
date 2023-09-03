
const btn_create_user = document.querySelector(".btn-create-new-user")
const user_creation_form = document.querySelector(".user-creation-form")

btn_create_user.addEventListener("click", () => {
    user_creation_form.classList.toggle("hidden")
})

//<!-- add form creation features with js -->
//const expand = document.getElementById("expand");
//const event_description = document.querySelector(".event-description");
//expand.addEventListener("click", () => {
//	event_description.classList.toggle("hidden");
//});

// for event creation
const event_form = document.querySelector(".event-form");
const btn_create = document.querySelector(".btn-create-event");
btn_create.addEventListener("click", () => {
    event_form.classList.toggle("hidden");
    if (btn_create.innerText === "Create Event Announcement") {
        btn_create.style.backgroundColor = "var(--red)";
        return (btn_create.innerText = "Cancel Event Announcement");
    }

    btn_create.style.backgroundColor = "var(--light-blue)";
    return (btn_create.innerHTML = "Create Event Announcement");
});
//todo: remove event and user creation in dashboard