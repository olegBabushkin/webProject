<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page session="false" %>

<html>
<head>
    <title>BookData</title>

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
<h1>Book Details</h1>

<table class="tg">
    <tr>
        <th width="80">ID</th>
        <th width="120">Title</th>
        <th width="120">Author(s)</th>
        <th width="120">Genre</th>
        <th width="120">Publisher</th>
        <th width="120">Pages</th>
    </tr>
    <tr>
    <tr>
        <td>${book.id}</td>
        <td>${book.title}</td>
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
    </tr>
    </tr>
</table>
</body>
</html>