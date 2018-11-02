<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Add Student</title>
</head>
<body>
    <sf:form method="POST" action="/student/add" modelAttribute="student">
        <sf:label path="name">Name:</sf:label>
        <sf:input path="name" /><br>
        <sf:label path="age">Age:</sf:label>
        <sf:input path="age" /><br>
        <sf:label path="group">Group:</sf:label>
        <sf:select path="group">
            <sf:option value="0" label="-- Select --"/>
            <sf:options items="${groups}" itemLabel="name"/>
        </sf:select>
        <br>
        <input type="submit" value="Submit" />
    </sf:form>
</body>
</html>
