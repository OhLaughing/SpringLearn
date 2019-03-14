<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
    <title>Spitter</title>
    <link rel="stylesheet" type="text/css"
          href="<c:url value="/resources/style.css" />">
</head>
<body>
<h1>Register</h1>

<form method="POST">

    Message: <input type="text" name="message"/><br/>
    Latitude: <input type="text" name="latitude"/><br/>
    Longitude: <input type="text" name="longitude"/><br/>
    <input type="submit" value="Register"/>
</form>
</body>
</html>
