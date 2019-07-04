<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<%@ page session="false" %>
<html>
<head>
    <title>Add Book Page</title>

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

<br/>
<a href="${pageContext.request.contextPath}/index.jsp">Back to main menu</a>
<br/>
<a href="<c:url value="/books"/>">Back to books list</a>
<br/>
<br/>

<h1>Add Book</h1>

<p style="color:#4dae30">${message}</p>

<p style="color:red">${messageEx}</p>

<c:url var="addAction" value="/books"/>

<form:form action="${addAction}" modelAttribute="book" method="post">
    <table>
        <tr>
            <td>
                <form:label path="title">
                    <spring:message text="Title"/>
                </form:label>
            </td>
            <td>
                <form:input path="title"/>
            </td>
            <td>
                <span class="error"><form:errors path="title"/></span>
            </td>
        </tr>
        <tr>
            <td>
                <form:label path="year">
                    <spring:message text="Year"/>
                </form:label>
            </td>
            <td>
                <form:input path="year"/>
            </td>
            <td>
                <span class="error"><form:errors path="year"/></span>
            </td>
        </tr>
        <tr>
            <td>
                <form:label path="numberOfPages">
                    <spring:message text="Pages"/>
                </form:label>
            </td>
            <td>
                <form:input path="numberOfPages"/>
            </td>
            <td>
                <span class="error"><form:errors path="numberOfPages"/></span>
            </td>
        </tr>
        <tr>
            <td>
                <form:label path="authors">
                    <spring:message text="Author(s)"/>
                </form:label>
            </td>
            <td>
                <form:select path="authors"> <%-- multiple="true"--%>
                    <%--  <c:set var="str" value="${author.firsName} ${author.lastName}"/>--%>
                    <%--      <form:option value="NONE" label="-Select-"/>--%>
                    <form:options items="${listAuthors}" itemValue="id" itemLabel="lastName"/>
                </form:select>
            </td>
            <td>
                <span class="error"><form:errors path="authors"/></span>
            </td>
        </tr>
        <tr>
            <td>
                <form:label path="genre">
                    <spring:message text="Genre"/>
                </form:label>
            </td>
            <td>
                <c:forEach items="${listGenre}" var="g">
                    <form:radiobutton path="genre" value="${g.id}"/>
                    ${g.name}
                </c:forEach>
            </td>
            <td>
                <span class="error"><form:errors path="genre"/></span>
            </td>
        </tr>
        <tr>
            <td>
                <form:label path="publisher">
                    <spring:message text="Publisher"/>
                </form:label>
            </td>
            <td>
                <c:forEach items="${listPublisher}" var="p">
                    <form:radiobutton path="publisher" value="${p.id}"/>
                    ${p.name}
                </c:forEach>
            </td>
            <td>
                <span class="error"><form:errors path="publisher"/></span>
            </td>

      <%--      <td><form:radiobuttons path="publisher" items="${listPublisher}"/>--%>
        </tr>

            <%--<tr>
                <td>
                    <form:select path="genre">
                        <form:option value="NONE" label="-Select-"/>
                        <form:options items="${book.genre}"/>
                    </form:select>
                </td>
            </tr>
            <tr>
                <td>
                    <form:select path="publisher">
                        <form:option value="NONE" label="-Select-"/>
                        <form:options items="${book.publisher}"/>
                    </form:select>
                </td>
            </tr>--%>
        <tr>
            <td colspan="2">
                <input type="submit" value="<spring:message text="Add Book"/>"/>
            </td>
        </tr>
    </table>
</form:form>
</body>
</html>
