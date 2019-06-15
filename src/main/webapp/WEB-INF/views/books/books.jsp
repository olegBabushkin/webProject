<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>

<%@ page session="false" %>
<html>
<head>
    <title>Books Page</title>

    <style type="text/css">
        .tg {
            border-collapse: collapse;
            border-spacing: 0;
            border-color: #ccc;
        }

        .tg td {
            font-family: Arial, sans-serif;
            font-size: 14px;
            padding: 10px 5px;
            border-style: solid;
            border-width: 1px;
            overflow: hidden;
            word-break: normal;
            border-color: #ccc;
            color: #333;
            background-color: #fff;
        }

        .tg th {
            font-family: Arial, sans-serif;
            font-size: 14px;
            font-weight: normal;
            padding: 10px 5px;
            border-style: solid;
            border-width: 1px;
            overflow: hidden;
            word-break: normal;
            border-color: #ccc;
            color: #333;
            background-color: #f0f0f0;
        }

        .tg .tg-4eph {
            background-color: #f9f9f9
        }
    </style>
    <style type="text/css">
        span.error {
            color: #ff1a10;
        }
    </style>
</head>
<br>

<a href="<c:url value="/index.jsp"/>">Back to main menu</a>

<h1>Book List</h1>

<c:if test="${!empty listBooks}">
    <table class="tg">
        <tr>
            <th width="80">ID</th>
            <th width="120">Title</th>
            <th width="120">Author</th>
            <th width="120">Price</th>
            <th width="60">Edit</th>
            <th width="60">Delete</th>
        </tr>
        <c:forEach items="${listBooks}" var="book">
            <tr>
                <td>${book.id}</td>
                <td><a href="/bookdata/${book.id}" target="_blank">${book.bookTitle}</a></td>
                <td>${book.bookAuthor}</td>
                <td>${book.price/100}${book.price%100}</td>
                <td><a href="<c:url value='/edit/${book.id}'/>">Edit</a></td>
                <td><a href="<c:url value='/remove/${book.id}'/>">Delete</a></td>
            </tr>
        </c:forEach>
    </table>
</c:if>

<p>
<h1>Search book</h1></p>

<c:url var="addAction" value="/find"/>

<form:form action="${addAction}" modelAttribute="searchList">
    <table>
        <tr>
                <%-- <td><label for="1"><p>Book title:</p></label></td>--%>
            <td>
                <form:label path="text">
                    <spring:message text="Input"/>
                </form:label>
            </td>
            <td>
                <form:input path="text"/>
            </td>
            <td>
                <form:select path="find">
                    <form:option value="NONE" label="-Select-"/>
                    <form:options items="${searchList.findMap}"/>
                </form:select>
            </td>
            <td>
                <p style="color:red">${messageEx}</p>
            </td>
        </tr>
        <tr>
            <td>
                <input type="submit" value="<spring:message text="Search"/>"/>
            </td>
        </tr>
    </table>
</form:form>

<table class="tg">
    <c:forEach items="${books}" var="book">
        <tr>
            <td><a href="/bookdata/${book.id}" target="_blank">${book.bookTitle}</a></td>
            <td>${book.bookAuthor}</td>
            <td><a href="<c:url value='/edit/${book.id}'/>">Edit</a></td>
            <td><a href="<c:url value='/remove/${book.id}'/>">Delete</a></td>
        </tr>
    </c:forEach>
</table>

</body>
</html>
