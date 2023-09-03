<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="models.CourseModule" %>
<%@ page import="models.CourseModuleMaterial" %>
<%@ page import="java.util.List" %>

<!DOCTYPE html>
<html lang="en">
	<head>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/admin-pages/student-course-player.style.css">
		

		<meta charset="UTF-8" />
		<meta
			name="viewport"
			content="width=device-width, initial-scale=1.0" />
		<title>Document</title>
	</head>
	<body>
		<nav>
			<a href="#">
				<div class="teacher-profile">
					<img
						src="image/logo.svg"
						alt="" />
					<h3>Teacher Name</h3>
				</div>
			</a>
			<a href="student-dashboard.html">
				<h3>Back</h3>
			</a>
		</nav>

		<div class="container">
			<div class="video-and-content">
				<div class="video-player">
					<video controls>
						<source
							src="<%= request.getAttribute("videoFilePath") %>"
							type="video/mp4" />
					</video>
				</div>
				<div class="content">
					<h2>Title</h2>
					<p>
						Lorem ipsum dolor sit amet consectetur adipisicing elit. Incidunt
						veritatis quisquam pariatur sint ducimus in cupiditate nam eligendi
						unde, consectetur, officia ratione quo a commodi quaerat
						perspiciatis! Debitis, praesentium odio. Vero, officia similique?
						Provident dolores aut maiores quaerat, iste voluptas in ex
						perferendis temporibus nemo nesciunt distinctio eius dicta
						similique, vitae qui voluptatibus culpa assumenda totam eos? Veniam,
						nemo dolore! Quis, dolores eos quisquam necessitatibus, id doloribus
						aperiam facilis mollitia veniam nam vero voluptatibus, nulla rem
						incidunt vitae quidem sunt velit similique illo dignissimos tempora
						libero delectus. Consectetur, iure eaque.
					</p>
				</div>
			</div>

			<div class="view-course-module">
				<div class="course-module-group">
					<div class="course-module">
						<h2 class="module-title">Introduction to Web Development</h2>
						<div class="module-outline">
							<a
								href=""
								class="done"
								>What is web Development?</a
							>
							<a href="">What is web Development?</a>
							<a href="">What is web Development?</a>
						</div>
					</div>
				</div>
			</div>
		</div>
	</body>

	<script>
		const myArr = [1, 2, 3];
		const linkArr = [1, 2];

		const course_module_group = document.querySelector(".course-module-group");

		function createCourseModule(courseModuleID) {
			const course_module = document.createElement("div");
			course_module.className = "course-module";
			course_module.id = courseModuleID;

			const module_title = document.createElement("h2");
			module_title.className = "module-title";
			module_title.innerText = "hello world";

			const module_outline = document.createElement("div");
			module_outline.className = "module-outline";
			module_outline.classList.add("hidden");
			module_title.addEventListener("click", () => {
				console.log("clicked");
				module_outline.classList.toggle("hidden");
			});

			linkArr.forEach((link) => {
				const course_link = createCourseLink(link);
				module_outline.appendChild(course_link);
			});
			course_module.appendChild(module_title);
			course_module.appendChild(module_outline);
			return course_module;
		}

		function createCourseLink(link, text) {
			const course_link = document.createElement("a");
			course_link.href = link;
			course_link.innerText = "TEXT";
			return course_link;
		}

		myArr.forEach((course) => {
			const course_module = createCourseModule(course);
			console.log("created");

			course_module_group.appendChild(course_module);
		});
	</script>
</html>
