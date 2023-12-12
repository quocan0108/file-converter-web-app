<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" session="true" %>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Main menu</title>
</head>
<body>
    <h1>Main menu</h1>
    <h2>Current account: <%= request.getSession().getAttribute("user") == null ? "Unknown" : request.getSession().getAttribute("user").toString() %></h2>
    <a href="dashboard"><h3>1. Xem danh sách đã convert</h3></a>
    <a href="upload"><h3>2. Convert mới</h3></a>
    <a href="logout"><h3>Đăng xuất</h3></a>
</body>
</html>
