<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

    
<!DOCTYPE html>
<html lang="en">
	<head>
		<link
			rel="stylesheet"
			href="login.style.css" />

		<link
			rel="preconnect"
			href="https://fonts.googleapis.com" />
		<link
			rel="preconnect"
			href="https://fonts.gstatic.com"
			crossorigin />
		<link
			href="https://fonts.googleapis.com/css2?family=Lato:wght@100;300;400&family=Nunito:wght@200;300;400&display=swap"
			rel="stylesheet" />
		<link
			rel="preconnect"
			href="https://fonts.googleapis.com" />
		<link
			rel="preconnect"
			href="https://fonts.gstatic.com"
			crossorigin />
		<link
			href="https://fonts.googleapis.com/css2?family=Lato:wght@100;300;400&display=swap"
			rel="stylesheet" />

		<meta charset="UTF-8" />
		<meta
			name="viewport"
			content="width=], initial-scale=1.0" />
		<title>Document</title>
	</head>
	<body>
		<div class="container">
			<div class="wrapper-1">
				<div class="box">
					<h1>Digital Platoform for distance learning</h1>
					<p>You will know everything.</p>
					<p>But you will know more</p>
				</div>
			</div>
			<div class="wrapper-2">
				<div class="welcome">
					<img
						src="images/logo.svg"
						alt="" />
					<h2>Hey, Hello 👋</h2>
					<p>Enter the information for registering</p>
				</div>
				<form action="/controllers/register" method="post">
					<input
						name="username"
						type="text"
						required />
					<label for="username">username</label>

					<input
						name="email"
						type="email"
						required />
					<label for="email">Email</label>

					<input
						name="password"
						type="password"
						required />
					<label for="password">Password</label>

					<select
						name="gender"
						id="gender">
						<option value="Male">Male</option>
						<option value="Female">Female</option>
						<option value="Other">Other</option>
					</select>
					<label for="gender">Gender</label>

					<input
						type="date"
						id="date-of-birth" />
					<label for="date-of-birth">Date Of Birth</label>

					<div class="remember">
						<p>Already have an account?<a href="login.jsp">Login</a></p>
					</div>

					<input
						type="submit"
						name="register"
						value="Register" />
				</form>
			</div>
		</div>
	</body>
</html>
 