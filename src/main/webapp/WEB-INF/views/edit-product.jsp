<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Edit Product</title>
</head>
<body>
    <c:import url="header.jsp" />
    <c:import url="top-menu.jsp" />
    <div style="margin: 20px auto; width: max-content">
        <sf:form method="POST" action="/product/edit" modelAttribute="product">
            <table>
                <tr>
                    <td><sf:label path="id">Id:</sf:label></td>
                    <td><sf:input path="id" disabled="true"  /></td>
                </tr>
                <tr>
                    <td><sf:label path="name">Name:</sf:label></td>
                    <td><sf:input path="name" /></td>
                </tr>
                <tr>
                    <td><sf:label path="price">Price:</sf:label></td>
                    <td><sf:input path="price" /></td>
                </tr>
                <tr>
                    <td><sf:label path="quantity">Quantity:</sf:label></td>
                    <td><sf:input path="quantity" /></td>
                </tr>
                <tr>
                    <td><input type="submit" value="Submit" /></td>
                    <td style="text-align: right"><input type="reset" value="Reset" /></td>
                </tr>
            </table>
            <td><sf:hidden path="id" /></td>
        </sf:form>
    </div>

</body>
</html>
