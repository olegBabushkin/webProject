<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page session="false" %>

<html>
<head>
    <title>AuthorData</title>

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

</head>
<body>
<h1>Author Details</h1>

<table class="tg">
    <tr>
        <th width="80">ID</th>
        <th width="120">Author</th>
        <th width="120">Birthplace</th>
        <th width="120">ContactInfo</th>
        <th width="120">Books</th>
        <th width="60">Edit</th>
        <th width="60">Delete</th>
    </tr>
    <tr>
        <td>${author.id}</td>
        <td>
            <c:set var="authorName" value="${author.firstName} ${author.lastName}"/>
            ${authorName}
        </td>
        <td>${author.birthplace}</td>
        <td>${author.contactInfo}</td>
        <td>
            <c:forEach items="${author.books}" var="book">
                <a href="<c:url value='/books/${book.id}'/>">${book.title}</a></br>
            </c:forEach>
        </td>
        <td><a href="<c:url value='/authors/${author.id}/edit'/>">Edit</a></td>
        <td><a href="<c:url value='/authors/${author.id}/delete'/>">Delete</a></td>
    </tr>
</table>
</body>
</html>