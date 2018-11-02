<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<html>
<head>
    <title>List of Students</title>
</head>
<body>

    <table border="1">
        <tr>
            <th>No.</th>
            <th>Name</th>
            <th>Age</th>
            <th>Group</th>
            <th>Course</th>
        </tr>
        <c:forEach items="students" var="student">
            <tr>
                <td>${student.id}</td>
                <td>${student.name}</td>
                <td>${student.age}</td>
                <td>${student.group.name}</td>
                <td>${student.group.course}</td>
            </tr>
        </c:forEach>
    </table>

</body>
</html>
