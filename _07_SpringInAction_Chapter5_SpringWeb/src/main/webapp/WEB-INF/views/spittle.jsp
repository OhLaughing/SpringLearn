<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
  <head>
    <title>Spitter</title>
    <link rel="stylesheet" 
          type="text/css" 
          href="<c:url value="/resources/style.css" />" >
  </head>
  <body>
    <div class="spittleView">
      <div class="spittleMessage"><c:out value="${spittle.message}" /></div>

    </div>
  </body>
</html>