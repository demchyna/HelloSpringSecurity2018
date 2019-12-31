<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Login Page</title>
</head>
<body>
<c:import url="header.jsp" />
<div style="margin: 0 auto; width: max-content">
    <form action="/login" method="POST">
        <table>
            <tr>
                <td>Username:</td>
                <td><input type="text" name="username"></td>
            </tr>
            <tr>
                <td>Password:</td>
                <td><input type="password" name="password" /></td>
            </tr>
            <tr>
                <td colspan="2"><input type="submit" value="LogIn" style="width: 100%"/></td>
            </tr>
        </table>
    </form>
</div>
</body>
</html>
