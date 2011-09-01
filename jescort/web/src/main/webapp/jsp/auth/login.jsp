<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!-- Content Wrapper -->
<div id="content">
    <div class="clear" id="login_form">
        <div class="left" id="member_login">
            <h2 class="maintitle">Login</h2>
            <div class="general_box rounded-bot">
                <form id="login" method="post" action="<spring:url value="/auth/login"/>">
                    <div id="regular_signin">
                        <a id="_regularsignin"></a>
                        <h3 class="bar">Enter your sign in name and password</h3>
                        <ul>
                            <li class="field">
                                <label for="username">Username:</label>
                                <input type="text" size="25" name="username" class="input_text" id="username" value="sinosaga">
                            </li>
                            <li class="field">
                                <label for="password">Password:</label>
                                <input type="password" size="25" name="password" class="input_text" id="password"><br>
                                <a title="Retrieve password" class="desc" href="<spring:url value="/auth/password_forgotten"/>">I've forgotten my password</a>
                            </li>
                        </ul>
                    </div>
                    <fieldset id="signin_options">
                        <legend>Sign in options</legend>
                        <ul>
                            <li class="field checkbox">
                                <input type="checkbox" class="input_check" value="1" name="rememberMe" checked="checked" id="remember">
                                <label for="remember">
                                    Remember me<br>
                                    <span class="desc">This is not recommended for shared computers</span>
                                </label>
                            </li>
                        </ul>
                    </fieldset>
                    <fieldset style="margin: 10px -10px -10px -10px;" class="submit rounded-bot">
                        <input type="submit" value="Sign In" class="input_submit">
                    </fieldset>
                </form>
            </div>
        </div>
        <div class="right" id="guest_register">
            <div class="general_box rounded">
                <h3 class="bar rounded-top">Not a member?</h3>
                <p>
                    If you aren't a member yet, it only takes a couple of minutes to register! Members get these benefits and more!
                    <br><br>
                </p>
                <ul class="bullets">
                    <li>Start new topics and reply to others</li>
                    <li>Subscribe to topics and forums to get automatic updates</li>
                    <li>Add events to our community calendar</li>
                    <li>Get your own profile and make new friends</li>
                    <li>Customize your experience here</li>
                </ul>
                <br>
                <p style="text-align: center">
                    <a href="<spring:url value="/auth/register"/>">Register Now</a>
                </p>
            </div>
        </div>
    </div>
    <div class="clear"></div>
</div>