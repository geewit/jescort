<div id="branding-user">
    <shiro:notAuthenticated>
    <!-- Guest Links -->
    <span id="nav-guest">
        <a id="nav-login" title="Sign In" href="<spring:url value="/login"/>"><spring:message code="message.login"/></a>
        <a id="nav-register" title="Create Account" href="<spring:url value="/register"/>"><spring:message code="message.register"/></a>
    </span>
    <!-- // Guest Links -->
    </shiro:notAuthenticated>

    <shiro:authenticated>
    <c:set var="user_id"><shiro:principal property="id"/></c:set>
    <!-- User Menu -->
    <span class="nav-user-avatar"><img alt="<shiro:principal property="username"/>'s Photo" src="<spring:url value="/users/${user_id}/avatar"/>"/></span>
    <span id="nav-user">
        <a href="<spring:url value="/users/${user_id}"/>" title="View My Profile" class="nav-user-username"><shiro:principal property="username"/><span></span></a>
        <div class="navigation-dropdown">
            <header>
            </header>
            <div>
                <ul>
                    <li><a title="Edit my settings, such as signature, photo and more..." href="<spring:url value="/auth/profile"/>"><spring:message code="message.my_profile"/></a></li>
                    <li><a title="View My Content" href="<spring:url value="/auth/topics"/>">View My Content</a></li>
                    <li><a href="<spring:url value="/auth/messager"/>"><spring:message code="message.inbox_str"/></a></li>
                </ul>
            </div>
            <footer>
                <ul>
                    <li><a href="<spring:url value="/auth/logout"/>"><b><spring:message code="message.logout"/></b></a></li>
                </ul>
            </footer>
        </div>
    </span>
    <!-- // User Menu -->
    </shiro:authenticated>
</div>