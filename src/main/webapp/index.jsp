<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Book Library</title>
</head>
<body>
<h1>Book Library</h1>
<br/>
<a href="<c:url value="/books"/>"><button>Books list</button></a>
<a href="<c:url value="/books/add"/>"><button>Add Book</button></a>
<br/>
</body>
</html>