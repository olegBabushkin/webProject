<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>


<div style="float: right; padding: 10px; text-align: right;">

    <c:if test="${pageContext.request.userPrincipal.name != null}">
        <h2>Welcome ${pageContext.request.remoteUser} | <a href="<c:url value="/users/logout"/>">Logout</a>
        </h2>
    </c:if>

</div>

<div style="padding: 5px;">
    <c:if test="${pageContext.request.userPrincipal.name eq null}">
        |
        <a href="${pageContext.request.contextPath}/users/login">SignIn</a>
        |
        <a href="${pageContext.request.contextPath}/users/registration">SignUp</a>
    </c:if>
</div>
