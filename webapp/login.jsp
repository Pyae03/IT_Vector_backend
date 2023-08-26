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
					<p>Enter the information you entered while registering</p>
				</div>
				<form action="LoginServlet" method="GET">
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

					<div class="remember">
						<input
							type="checkbox"
							name="remember-me"
							id="remember-me"
							value="remember" />
							<label for="remember-me">Remember Me</label>

						<a href="#">Forgot Password?</a>
						<a href="register.jsp">Register</a>
					</div>

					<input
						type="submit"
						name="login"
						value="Login" />
				</form>
			</div>
		</div>
	</body>
</html>
    