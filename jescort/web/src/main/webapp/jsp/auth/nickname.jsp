<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<div id="content">
    <form id="userCPForm" action="<spring:url value="/"/>" method="post">
        <h2 class="maintitle">Your Options</h2>
        <div class="border row1 rounded-bot clearfix">
            <ul class="tab_bar no_title">
                <li class="active"><a title="Settings for Settings" href="/">Settings</a></li>
                <li><a title="Settings for Profile" href="/">Profile</a></li>
            </ul>
            <div class="tab_body usercp_body">
                <ul id="usercp_menu">
                    <li><a href="<spring:url value="/"/>">General Settings</a></li>
                    <li><a href="<spring:url value="/"/>">Change Email Address</a></li>
                    <li><a href="<spring:url value="/"/>">Change Password</a></li>
                    <li class="active">Change Display Name</li>
                    <li><a href="<spring:url value="/"/>">Manage Attachments</a></li>
                    <li><a href="<spring:url value="/"/>">Message Box</a></li>
                </ul>
                <div id="usercp_content">
                    <fieldset class="row1">
                        <h3>Change Display Name</h3>
                        <br>
                        <p class="message unspecific">You have made 0 of 2 display name changes since 23 May 2011.<br>You are permitted to make 2 changes in a 90 day period.<br><br>Changing your display name will <strong>not</strong> affect your log in details.</p>
                        <ul>
                            <li class="field">
                                <label for="displayName">Enter a new display name</label><input type="text" size="30" value="" id="displayName" name="displayName" maxlength="26" class="input_text"> <br>
                                <span class="desc">This is the display name you wish to now use. Characters not permitted: [ ] | , ; $</span>
                            </li>
                            <li class="field">
                                <label for="displayPassword">Enter your current password</label><input type="password" size="30" value="" id="displayPassword" name="displayPassword" class="input_text"> <br>
                                <span class="desc">This is to ensure security on your account</span>
                            </li>
                        </ul>
                    </fieldset>
                    <fieldset class="submit rounded-bot">
                        <input type="submit" value="Save Changes" name="submit" class="input_submit">
                    </fieldset>
                </div>
            </div>
        </div>
    </form>
    <div class="clear"></div>
</div>