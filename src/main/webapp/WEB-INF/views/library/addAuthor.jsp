<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page session="false" %>
<html>
<head>
    <title>Add Author Page</title>

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
<br/>
<a href="${pageContext.request.contextPath}/index.jsp">Back to main menu</a>
<br/>
<a href="<c:url value="/books"/>">Back to books list</a>
<br/>
<br/>

<h1>Add Author</h1>

<p style="color:#4dae30">${message}</p>

<p style="color:red">${messageEx}</p>

<c:url var="addAction" value="/authors"/>

<form:form action="${addAction}" modelAttribute="author" method="post">
    <table>
        <tr>
            <td>
                <form:label path="firstName">
                    <spring:message text="FirstName"/>
                </form:label>
            </td>
            <td>
                <form:input path="firstName"/>
            </td>
            <td>
                <span class="error"><form:errors path="firstName"/></span>
            </td>
        </tr>
        <tr>
            <td>
                <form:label path="lastName">
                    <spring:message text="LastName"/>
                </form:label>
            </td>
            <td>
                <form:input path="lastName"/>
            </td>
            <td>
                <span class="error"><form:errors path="lastName"/></span>
            </td>
        </tr>
        <tr>
            <td>
                <form:label path="birthplace">
                    <spring:message text="Birthplace"/>
                </form:label>
            </td>
            <td>
                <form:input path="birthplace"/>
            </td>
            <td>
                <span class="error"><form:errors path="birthplace"/></span>
            </td>
        </tr>
        <tr>
            <td>
                <form:label path="contactInfo">
                    <spring:message text="ContactInfo"/>
                </form:label>
            </td>
            <td>
                <form:textarea path="contactInfo" rows="5" cols="30" />
            </td>
            <td>
                <span class="error"><form:errors path="contactInfo"/></span>
            </td>
        </tr>
        <tr>
            <td colspan="2">
                <input type="submit" value="<spring:message text="Add Author"/>"/>
            </td>
        </tr>
    </table>
</form:form>
</body>
</html>
