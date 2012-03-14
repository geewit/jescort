<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<div id="body">
    <div id="secondary_navigation" class="clearfix">
        <ol class="breadcrumb top ipsList_inline">
            <li itemscope="" itemtype="http://data-vocabulary.org/Breadcrumb">
                <a href="/" itemprop="url">
                    <span itemprop="title">Jescort Forums</span>
                </a>
            </li>
            <li itemscope="" itemtype="http://data-vocabulary.org/Breadcrumb">
                <span class="nav_sep"></span>
                <span itemprop="title">Sign In</span>
            </li>
        </ol>
    </div>
    <br>
    <spring:bind path="command.*">
        <c:if test="${not empty status.errorMessages}">
            <p class="message error">Username or password incorrect.</p>
        </c:if>
    </spring:bind>
    <br>
    <div id="login_form" class="clearfix">
        <div class="ipsBox">
            <div id="member_login">
                <h2 class="maintitle">Sign In</h2>
                <form id="login" action="<spring:url value="/login"/>" method="post">
                    <div id="regular_signin">
                        <a id="_regularsignin"></a>
                        <h3 class="bar">Enter your sign in name and password</h3>
                        <ul class="ipsForm ipsForm_vertical ipsPad_double left">
                            <li class="ipsField">
                                <label for="username" class="ipsField_title">Username or email:</label>
                                <p class="ipsField_content">
                                    <input type="text" id="username" class="input_text" name="username" size="50" tabindex="1"><br>
                                    <span class="desc ipsType_smaller">Need an account? <a href="<spring:url value="/register"/>" title="Register now!">Register now!</a></span>
                                </p>
                            </li>
                            <li class="ipsField">
                                <label for="password" class="ipsField_title">Password</label>
                                <p class="ipsField_content">
                                    <input type="password" id="password" class="input_text" name="password" size="50" tabindex="2"><br>
                                    <a href="<spring:url value="/password_forgotten"/>" class="ipsType_smaller" title="Retrieve password">I've forgotten my password</a>
                                </p>
                            </li>
                        </ul>
                    </div>
                    <hr>
                    <fieldset id="signin_options">
                        <legend>Sign in options</legend>
                        <ul class="ipsForm ipsForm_vertical ipsPad_double">
                            <li class="ipsField ipsField_checkbox clearfix">
                                <input type="checkbox" id="remember" checked="checked" name="rememberMe" value="1" class="input_check" tabindex="3">
                                <p class="ipsField_content">
                                    <label for="remember">Remember me</label><br>
                                    <span class="desc lighter">This is not recommended for shared computers</span>
                                </p>
                            </li>
                        </ul>
                    </fieldset>
                    <fieldset class="submit">
                        <input type="submit" class="input_submit" value="Sign In" tabindex="5"> or <a href="<spring:url value="/"/>" title="Cancel" class="cancel">Cancel</a>
                    </fieldset>
                </form>
            </div>
        </div>
    </div>
    <ol class="breadcrumb bottom ipsList_inline clearfix clear">
        <li><a href="<spring:url value="/"/>">Jescort Forums</a></li>
        <li><span class="nav_sep"></span> Sign In</li>
    </ol>
    <div class="clear"></div>
</div>