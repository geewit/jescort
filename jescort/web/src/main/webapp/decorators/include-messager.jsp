<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>

<shiro:authenticated>
<ul id="navigation-user">
    <li id="nav-inbox">
        <a id="inbox_link" title="Messenger" href="<spring:url value="/auth/messager"/>"></a>
    </li>
</ul>
</shiro:authenticated>