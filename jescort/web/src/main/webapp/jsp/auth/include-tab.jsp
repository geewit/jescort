<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<div id="usercp_tabs" class="ipsVerticalTabbed_tabs ipsLayout_left">
    <ul>
        <li<c:if test="${param.tab == 'profile'}"> class="active"</c:if>>
            <a href="<spring:url value="/auth/profile"/>">profile</a>
        </li>
        <li<c:if test="${param.tab == 'password'}"> class="active"</c:if>>
            <a href="<spring:url value="/auth/password"/>">Password</a>
        </li>
        <li<c:if test="${param.tab == 'avatar'}"> class="active"</c:if>>
            <a href="<spring:url value="/auth/avatar"/>">Avatar</a>
        </li>
        <li<c:if test="${param.tab == 'signature'}"> class="active"</c:if>>
            <a href="<spring:url value="/auth/signature"/>">Signature</a>
        </li>
        <li<c:if test="${param.tab == 'attachments'}"> class="active"</c:if>>
            <a href="<spring:url value="/auth/attachments"/>">Manage Attachments</a>
        </li>
    </ul>
</div>