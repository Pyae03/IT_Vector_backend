// add form creation features with js

// for event creation
const event_form = document.querySelector(".event-form");
const btn_create = document.querySelector(".btn-create");

//btn_create.addEventListener("click", () => {
//  event_form.classList.toggle("hidden");
//  if (btn_create.innerText === "Create") {
//    btn_create.style.backgroundColor = "var(--red)";
//    return (btn_create.innerText = "Cancel");
//  }

//  btn_create.style.backgroundColor = "var(--light-blue)";
//  return (btn_create.innerHTML = "Create");
//});


// post form data to servlet
			//const fetchOptionsForPost =
			const eventSubmitForm = document.querySelector('.event-submit-form')
			  
			eventSubmitForm.addEventListener('submit', function (event) {
			     
			     event.preventDefault();
			
			   const form = event.target;
			   
			   //const title = form[0].name
			   //const date = form[1].name
			   //const type = form[2].name
			   //const description = form[3].name
			   
            const formData = new FormData(form);
			const obj = {
				eventTitle : form[0].value,
				eventDate : form[1].value, // date error
				eventType : form[2].value,
				eventDescription : form[3].value
			}
			     
				console.log("formdata: ", obj)
			    fetch("http://localhost:8080/IT_Vector_Ver1/EventCreateApiServlet", {
				  method: 'POST',
				  headers: {
				    'Content-Type': 'application/json',
				  },
				  body: JSON.stringify(obj)
				})
			    .then(response => {
			        if (!response.ok) {
			            throw new Error('Network response was not ok');
			        }
			        return response.json();
			    })
			    .then(data => {
			        console.log('Response:', data);
			    
			    })
			    .catch(error => {
			        console.error('Fetch error:', error);
			    });
			});

			
			
			// start
			const event_list = document.querySelector(".event-list")
			// get event data from servlet
			const fetchOptions = {
  				  method: 'GET',
    				headers: {
        			'Content-Type': 'application/json',
    				},
				};
			fetch("http://localhost:8080/IT_Vector_Ver1/EventReadApiServlet", fetchOptions)
			  .then(response => {
			    if (!response.ok) {
			      throw new Error('Network response was not ok');
			    }
			    return response.json(); 
			  })
			  .then(data => {
			    
			    console.log(data); 
			    data.forEach(d => createEventCard(d.eventID, d.eventTitle, d.eventDate, d.eventType, d.eventDescription))
			    
			  })
			  .catch(error => {
			    // Handle errors here
			    console.error('Fetch error:', error);
			  });

			
			

			// for event creation
			//const event_form = document.querySelector(".event-form");
			//const btn_create = document.querySelector(".btn-create");
			btn_create.addEventListener("click", () => {
				event_form.classList.toggle("hidden");
				if (btn_create.innerText === "Create") {
					btn_create.style.backgroundColor = "var(--red)";
					return (btn_create.innerText = "Cancel");
				}

				btn_create.style.backgroundColor = "var(--light-blue)";
				return (btn_create.innerHTML = "Create");
			});
			
			// create event card 
			function createEventCard(id, title, date, type, description) {
				  const eventCard = document.createElement('div');
				  eventCard.classList.add('event-card');
				
				  const eventTitle = document.createElement('h3');
				  eventTitle.textContent = `Event Title: ${title}`;
				
				  const eventDate = document.createElement('p');
				  eventDate.textContent = `Date: ${date}`;
				
				  const eventType = document.createElement('p');
				  eventType.textContent = `Type: ${type}`;
				
				  const expandButton = document.createElement('button');
				  expandButton.className = 'expand';
				  expandButton.textContent = 'Expand';
				
					// delete button
				  const deleteButton = document.createElement('button');
				  deleteButton.className = 'delete';
				  deleteButton.textContent = 'Delete'
				
				  const eventDescription = document.createElement('article');
				  eventDescription.classList.add('event-description', 'hidden');
				
				  const descriptionText = document.createElement('p');
				  descriptionText.textContent = description;
				
				
				
				
				  eventDescription.appendChild(descriptionText);
				  eventCard.appendChild(eventTitle);
				  eventCard.appendChild(eventDate);
				  eventCard.appendChild(eventType);
				  eventCard.appendChild(expandButton);
				  eventCard.appendChild(deleteButton);
				  eventCard.appendChild(eventDescription);
				
				
				// event action (expand description)
			expandButton.addEventListener("click", () => {
				
				eventDescription.classList.toggle("hidden");
			});
				
				// event delete function 
			deleteButton.addEventListener("click", () => {
				console.log("event id: ", id)
				
				 
            
            const eventData = {
                eventID: id,
                eventTitle: title,
                eventDate: date,
                eventType: type,
                eventDescription: description
            };
            
            fetch("http://localhost:8080/IT_Vector_Ver1/EventDeleteApiServlet", {
                method: "POST",
                headers: {
                    "Content-Type": "application/json"
                },
                body: JSON.stringify(eventData)
            })
            .then(function(response) {
                if (response.ok) {
                    return response.json();
                } else {
                    throw new Error("Network response was not ok");
                }
            })
            .then(function(data) {
                if (data.success) {
                    alert("Event deleted successfully");  
                } 
            })
            .catch(function(error) {
                console.log("error: ", error)
            });
        });
			
				
			// parent syntax
			  return event_list.appendChild(eventCard);
				}
