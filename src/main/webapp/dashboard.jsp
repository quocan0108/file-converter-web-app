<%@page import="model.bean.PDF2WORD"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" session="true" %>
<%@ page language="java" import="java.util.ArrayList" %>
<%@ page language="java" import="model.bean.WORD2PDF" %>
<%@ page language="java" import="java.sql.Timestamp" %>
<%@ page language="java" import="java.text.SimpleDateFormat" %>

<html>
<head>
    <meta content="text/html;" charset="UTF-8">
    <title>FILE CONVERTER - Dashboard</title>
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
			border-color: #B53836		
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
    <nav class="navbar navbar-expand-lg navbar-light bg-light" style="margin-bottom: 3%;">
        <div class="container-fluid">
            <a class="navbar-brand" href="index">FILE CONVERTER</a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarNav">
                <ul class="navbar-nav">
                    <li class="nav-item">
                        <a class="nav-link active" href="dashboard">Dashboard</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="upload">Word to PDF</a>
                    </li>
                     <li class="nav-item">
                        <a class="nav-link" href="pdf-to-word">PDF to Word</a>
                    </li>
                </ul>
            </div>
            <div>
                <span class="navbar-text">Logged in as <%= request.getSession().getAttribute("user") == null ? "Unknown" : request.getSession().getAttribute("user").toString() %>&nbsp;&nbsp;</span>
                <a href="logout"><button class="btn btn-sm btn-outline-secondary" type="button">Logout</button></a>
            </div>
        </div>
    </nav>

    <div style="width: 75%; margin: auto;">
        <h1>Dashboard</h1>
        <h3>Show historical information about files the user has converted.</h3>
        <br>
        
        <h6>Word to PDF:</h6>
        <table border="1" width=100%>
            <thead>
            	<tr>
	                <th>ID</th>
	                <th>Source name</th>
	                <th>Date added</th>
	                <th>Date completed</th>
	                <th>Status</th>
	                <th>Download</th>
            	</tr>
            </thead>
            <%
                ArrayList<WORD2PDF> wordData = (ArrayList<WORD2PDF>)request.getAttribute("data");
                for (int i = 0; i < wordData.size(); i++) {
            %>
                <tr>
                    <td><%= wordData.get(i).getID() %></td>
                    <td><%= wordData.get(i).getSourceName() %></td>
                    <td><% try { out.print(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(wordData.get(i).getDateStart())); } catch (Exception ex) {  } %></td>
                    <td><% try { out.print(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(wordData.get(i).getDateCompleted())); } catch (Exception ex) {  } %></td>
                    <td><%= wordData.get(i).getResultString() %></td>
                    <td><a href="download?id=<%= wordData.get(i).getID() %>">Download</a></td>
                </tr>
            <% } %>
        </table>
        <h2></h2>
        <h6>PDF to Word:</h6>
         <table border="1" width=100%>
            <thead>
            	<tr>
	                <th>ID</th>
	                <th>Source name</th>
	                <th>Date added</th>
	                <th>Date completed</th>
	                <th>Status</th>
	                <th>Download</th>
            	</tr>
            </thead>
			<%
			    ArrayList<PDF2WORD> pdfData = (ArrayList<PDF2WORD>)request.getAttribute("data2");
			    if (pdfData != null) {
			        for (int i = 0; i < pdfData.size(); i++) {
			%> 
			            <tr>
			                <td><%= pdfData.get(i).getID() %></td>
			                <td><%= pdfData.get(i).getSourceName() %></td>
			                <td><% try { out.print(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(pdfData.get(i).getDateStart())); } catch (Exception ex) {  } %></td>
			                <td><% try { out.print(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(pdfData.get(i).getDateCompleted())); } catch (Exception ex) {  } %></td>
			                <td><%= pdfData.get(i).getResultString() %></td>
			                <td><a href="download2?id=<%= pdfData.get(i).getID() %>">Download</a></td>
			            </tr>
			<%
			        }
			    }
			    else {
			        // Handle the case when data2 is null
			        out.println("<tr><td colspan='6'>No data available</td></tr>");
			    }
			%>
        </table>
                
        
        <br>
        <a href=""><button type="button" class="btn btn-primary">Refresh dashboard</button></a>
    </div>
</body>
</html>
