<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>List of Products</title>
</head>
<body>
    <c:import url="header.jsp" />
    <c:import url="top-menu.jsp" />
    <div style="margin: 20px auto; width: max-content">
        <table border="1">
            <tr>
                <th>No.</th>
                <th>Name</th>
                <th>Price</th>
                <th>Quantity</th>
                <th>Actions</th>
            </tr>
            <c:forEach items="${products}" var="product">
                <tr>
                    <td>${product.id}</td>
                    <td>${product.name}</td>
                    <td>${product.price}</td>
                    <td>${product.quantity}</td>
                    <td>
                        <a href="<c:url value="/product/edit?id=${product.id}"/>">Edit</a> /
                        <a href="<c:url value="/product/remove?id=${product.id}"/>">Remove</a>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>
</body>
</html>
