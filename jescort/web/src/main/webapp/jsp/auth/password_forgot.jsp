<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<div id="content">
    <h2>Lost Password Form</h2>

    <p class="message unspecific">
        If you've lost your password, you can use this form to reset it. Enter your username or email address in the field below. The username or email address is case
        <b>in</b>sensitive.<br>Once you have submitted the form, you will receive an email asking for validation of this request to ensure that no malicious use has occurred. This email will also contain a link that you must click for further instruction.
    </p>

    <form method="post" action="<spring:url value="/"/>">
        <div class="post_form" id="lost_pass_form">
            <fieldset>
                <h3 class="bar">Recover Password</h3>

                <ul>
                    <li class="field">
                        <label for="member_name">Enter your account username</label>
                        <input type="text" class="input_text" id="member_name" name="member_name" size="32" value="sinosaga">
                    </li>
                    <li class="field">
                        <label for="email_addy">OR Enter your email address</label>
                        <input type="text" class="input_text" id="email_addy" name="email_addy" size="32">
                    </li>
                </ul>
            </fieldset>
            <fieldset class="submit">
                <input type="submit" value="Proceed" class="input_submit">
            </fieldset>
        </div>
    </form>
    <!-- // Content Wrapper -->
    <div class="clear"></div>
</div>
