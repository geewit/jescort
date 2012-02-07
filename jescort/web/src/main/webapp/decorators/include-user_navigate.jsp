<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>

<!-- Guest Links -->
<shiro:notAuthenticated>
<li id="nav-register">
    <a title="<spring:message code="message.register"/>" href="<spring:url value="/auth/register"/>"><spring:message code="message.register"/></a>
</li>
<li id="nav-login">
    <a title="<spring:message code="message.login"/>" href="<spring:url value="/auth/login"/>"><spring:message code="message.login"/></a>
</li>
</shiro:notAuthenticated>
<!-- // Guest Links -->

<!-- User Menu -->
<shiro:authenticated>
<li id="user-menu">
    <a href="<spring:url value="/"/>" title="<spring:message code="message.my_profile"/>">
        <span><shiro:principal property="username"/></span>
    </a>
    <div class="subnav">
        <ul class="menu">
            <li id="user_ucp">
                <span class="usermenu-icon settings"></span>
                <a href="<spring:url value="/auth/profile"/>" title="<spring:message code="message.my_profile"/>"><spring:message code="message.my_profile"/></a>
            </li>
            <li id="user_msg">
                <span class="usermenu-icon messenger"></span>
                <a href="<spring:url value="/messages"/>" title="<spring:message code="message.inbox_str"/>"><spring:message code="message.inbox" arguments="0"/></a>
            </li>
            <li>
                <span class="usermenu-icon logout"></span>
                <a href="<spring:url value="/auth/logout"/>" title="<spring:message code="message.logout"/>"><strong><spring:message code="message.logout"/></strong></a>
            </li>
        </ul>
</div>
</li>
</shiro:authenticated>
<!-- // User Menu -->