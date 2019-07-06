<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>

<%@ page session="true" %>
<html>
<head>
    <title>Authors Page</title>

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
<br>

<a href="<c:url value="/index.jsp"/>">Back to main menu</a>

<h1>Authors List</h1>

<c:if test="${!empty listAuthors}">
    <table class="tg">
        <tr>
            <th width="120">Author</th>
        </tr>
        <c:forEach items="${listAuthors}" var="author">
            <tr>
                <td><a href="<c:url value='/authors/${author.id}'/> "target="_blank">
                    <c:set var="authorName" value="${author.firstName} ${author.lastName}"/>
                         ${authorName}
                </a></td>
            </tr>
        </c:forEach>
    </table>
</c:if>

<p>
<h1>Search Author</h1></p>

<c:url var="addAction" value="/authors/find"/>

<form:form action="${addAction}">
    <table>
        <tr>
            <td>
                <label for="1"><p>Author title:</p></label>
            </td>
            <td>
                <input type="text" name="text" id="1">
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
    <c:forEach items="${authors}" var="author">
        <tr>
            <td><a href="/authors/${author.id}" target="_blank">
                <c:set var="authorName" value="${author.firstName} ${author.lastName}"/>
                    ${authorName}
            </a></td>
        </tr>
    </c:forEach>
</table>

</body>
</html>
