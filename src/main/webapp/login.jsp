<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" session="true" %>

<html>
<head>
    <meta content="text/html;" charset="UTF-8">
    <title>FILE CONVERTER - Login</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
    <style>
        body {
            background-color: #242424;
            color: #ffffff;
        }

        .container {
            max-width: 600px;
            margin-top: 50px;
        }

        .navbar {
            background-color: #ffffff;
            box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
            margin-bottom: 20px;
        }

        .navbar-brand {
            font-weight: bold;
        }

        .navbar-text {
            margin-right: 15px;
            font-weight: bold;
        }
        .btn-primary{
			background-color: #B53836;
			border-color: #B53836;
        }
        .btn-primary:hover{
        	background-color: #8D0E07;
        	border-color: #8D0E07;
        }
        
        .active{
        	font-weight: bold;
        }
    </style>
</head>
<body> 
    <nav class="navbar navbar-expand-lg navbar-light bg-light" style="margin-bottom: 3%">
        <div class="container-fluid">
            <a class="navbar-brand" href="index">FILE CONVERTER</a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarNav">
                <ul class="navbar-nav">
                    <li class="nav-item">
                        <a class="nav-link" href="dashboard">Dashboard</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="upload">Word to PDF</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="pdftoword">PDF to Word</a>
                    </li>
                </ul>
            </div>
            <div>
                <span class="navbar-text">Not logged in&nbsp;&nbsp;</span>
                <button class="btn btn-sm btn-outline-secondary active" type="button">Log in</button>
            </div>
        </div>
    </nav>

    <div style="width: 50%; margin: auto;"> 
        <h1>Log in</h1> 
        <h4>You need to log in to use the converter.</h4>
        <br> 
        <form action="<%=request.getContextPath()%>/login" method="post">
            <div class="form-group">
            <label for="uname">Username:</label> <input type="text"
                class="form-control" id="username" placeholder="username"
                name="username" required>
            </div>
            <br>
            <div class="form-group">
                <label for="uname">Password:</label>
                <input type="password" class="form-control" id="password" placeholder="password" name="password" required>
            </div>
            <br>
            <button type="submit" class="btn btn-primary">Submit</button>
        </form>    
    </div>
</body>
</html>
