<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>

<%@ page session="true" %>
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
<body>
<jsp:include page="/WEB-INF/views/library/_menu.jsp"></jsp:include>
<a href="<c:url value="/index.jsp"/>">Back to main menu</a>

<h1>Book List</h1>

<c:if test="${!empty listBooks}">
    <table class="tg">
        <tr>
            <th width="80">ID</th>
            <th width="120">Title</th>
            <th width="120">Author</th>
            <th width="120">Genre</th>
            <th width="120">Publisher</th>
            <th width="120">Pages</th>
            <th width="60">Edit</th>
            <th width="60">Delete</th>
        </tr>
        <c:forEach items="${listBooks}" var="book">
            <tr>
                <td>${book.id}</td>
                <td><a href="/books/${book.id}" target="_blank">${book.title}</a></td>
                <td>
                    <c:forEach items="${book.authors}" var="author">
                        <c:set var="str" value="${str}${author.firstName} ${author.lastName};"/>
                    </c:forEach>
                    <c:set var="array" value="${fn:split(str, ';')}"/>
                    <c:set var="string" value="${fn:join(array, ', ')}"/>
                        ${string}
                    <c:set var="str" value=""/>
                </td>
                <td>${book.genre.name}</td>
                <td>${book.publisher.name}</td>
                <td>${book.numberOfPages}</td>
                <td><a href="<c:url value='/books/${book.id}/edit'/>">Edit</a></td>
                <td><a href="<c:url value='/books/${book.id}/delete'/>">Delete</a></td>
            </tr>
        </c:forEach>
    </table>
</c:if>

<p>
<h1>Search book</h1></p>

<c:url var="addAction" value="/books/find"/>

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
            <td><a href="/bookdata/${book.id}" target="_blank">${book.title}</a></td>
            <td>
                <c:forEach items="${book.authors}" var="author">
                    <c:set var="str" value="${str}${author.firstName} ${author.lastName};"/>
                </c:forEach>
                <c:set var="array" value="${fn:split(str, ';')}"/>
                <c:set var="string" value="${fn:join(array, ', ')}"/>
                    ${string}
                <c:set var="str" value=""/>
            </td>
            <td>${book.genre.name}</td>
            <td>${book.publisher.name}</td>
            <td>${book.numberOfPages}</td>
            <td><a href="<c:url value='/edit/${book.id}'/>">Edit</a></td>
            <td><a href="<c:url value='/remove/${book.id}'/>">Delete</a></td>
        </tr>
    </c:forEach>
</table>

</body>
</html>
