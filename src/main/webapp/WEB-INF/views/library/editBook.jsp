<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<%@ page session="false" %>
<html>
<head>
    <title>Edit Book Page</title>

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
<br/>
<a href="${pageContext.request.contextPath}/index.jsp">Back to main menu</a>
<br/>
<a href="<c:url value="/books"/>">Back to books list</a>
<br/>
<br/>

<h1>Edit Book</h1>

<p style="color:#4dae30">${message}</p>

<c:url var="editAction" value="/books/edit"/>

<form:form action="${editAction}" modelAttribute="book">
    <table>
        <tr>
            <td>
                <form:label path="id">
                    <spring:message text="ID"/>
                </form:label>
            </td>
            <td>
                <form:input path="id" readonly="true" size="8" disabled="true"/>
                <form:hidden path="id"/>
            </td>
        </tr>
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
                <form:checkboxes path="authors" items="${listAuthors}" itemLabel="lastName" itemValue="id"/>

               <%-- <c:forEach items="${listAuthors}" var="g">

                    <c:forEach items="${book.authors}" var="a">

                        <c:if test="${g.lastName eq a.lastName}">
                            <form:checkbox path="authors" value="${g.id}" checked="checked"/>
                            ${g.lastName}
                        </c:if>
                        <c:if test="${g.lastName ne a.lastName}">
                            <form:checkbox path="authors" value="${g.id}"/>
                            ${g.lastName}
                        </c:if>

                    </c:forEach>

                </c:forEach>--%>

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
                <c:set var="name" value="${book.genre.name}"/>
                <c:forEach items="${listGenre}" var="g">
                    <c:if test="${g.name eq name}">
                        <form:radiobutton path="genre" value="${g.id}" checked="checked"/>
                        ${g.name}
                    </c:if>
                    <c:if test="${g.name ne name}">
                        <form:radiobutton path="genre" value="${g.id}"/>
                        ${g.name}
                    </c:if>
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
         <%--       <form:radiobuttons path="publisher" items="${listPublisher}" itemValue="id" itemLabel="name"/>--%>
                <c:set var="name" value="${book.publisher.name}"/>
                <c:forEach items="${listPublisher}" var="g">
                    <c:if test="${g.name eq name}">
                        <form:radiobutton path="publisher" value="${g.id}" checked="checked"/>
                        ${g.name}
                    </c:if>
                    <c:if test="${g.name ne name}">
                        <form:radiobutton path="publisher" value="${g.id}"/>
                        ${g.name}
                    </c:if>
                </c:forEach>
            </td>
            <td>
                <span class="error"><form:errors path="publisher"/></span>
            </td>
        </tr>
        <tr>
            <td colspan="2">
                <input type="submit" value="<spring:message text="Edit Book"/>"/>
            </td>
        </tr>
    </table>
</form:form>
</body>
</html>
