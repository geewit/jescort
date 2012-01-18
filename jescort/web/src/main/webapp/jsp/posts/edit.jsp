<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<%@ include file="form_head.jsp" %>
<html>
<body>
<div id="form" style="border: 1px solid #ccc">
    <form id="postForm" action=""
    <spring:url value='/posts/${post.id}/edit'/>" method="PUT">
    <%@ include file="form_content.jsp" %>
    </form>
</div>
</body>
</html>