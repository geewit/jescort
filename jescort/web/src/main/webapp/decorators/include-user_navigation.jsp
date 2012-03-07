<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<shiro:notAuthenticated>
<ul id="navigation-user">
    <li id="nav-register">
        <a href="<spring:url value="/auth/register"/>" title="<spring:message code="message.register"/>" id="register_link">
            <spring:message code="message.register"/>
        </a>
    </li>
    <li id="nav-login">
        <a href="<spring:url value="/auth/login"/>" title="<spring:message code="message.login"/>" id="sign_in">
            <spring:message code="message.login"/>
        </a>
    </li>
</ul>
</shiro:notAuthenticated>
<shiro:authenticated>
<c:set var="user_id"><shiro:principal property="id"/></c:set>
<ul id="navigation-user">
    <li id="nav-user">
        <a id="user_link" title="View My Profile" href="<spring:url value="/user/${user_id}"/>">
            <strong><shiro:principal property="username"/></strong><span></span>
        </a>
        <div class="navigation-dropdown">
            <header>
                <a href="<spring:url value="/user/${user_id}"/>" title="Your Profile" class="ipsUserPhotoLink left">
                    <img src="<spring:url value="/user/${user_id}/avatar"/>" alt="<shiro:principal property="username"/>'s Photo" class="ipsUserPhoto ipsUserPhoto_medium photo">
                </a>
            </header>
            <div class="col col-1">
                <ul>
                    <li><a href="/" title="Edit my settings, such as signature, photo and more...">My Settings</a></li>
                    <li><a href="/" title="View My Content">View My Content</a></li>
                    <li><a href="/" title="Content I Follow">Content I Follow</a></li>
                    <li><a href="/">Personal Messenger</a></li>
                    <li><a href="/" title="Improve your Neowin experience" class="tooltip-e">Upgrade Account</a></li>
                </ul>
            </div>
            <div class="col col-2">
                <ul>
                    <li><a href="/" title="Manage Friends" class="manage_friends">Manage Friends</a></li>
                    <li><a href="/" title="Manage Ignore Prefs" class="manage_enemies">Manage Ignore Prefs</a></li>
                    <li><a href="/">Manage Blogs</a></li>
                    <li><a href="/">My Blog</a></li>
                    <li><a href="/">Mark Community Read</a></li>
                </ul>
            </div>
            <footer>
                <ul>
                    <li><a href="/"><b>Sign Out</b></a></li>
                </ul>
            </footer>
        </div>
    </li>
</ul>
</shiro:authenticated>