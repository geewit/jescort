<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<div id="breadcrumb">
    <ul class="fleft">
        <li style="font-weight:bold" class="first"><a href="<spring:url value="/categories/"/>">Neowin Forums</a></li>
        <li><b>Registration Form</b></li>
    </ul>
    <ul class="fright" id="breadcrumb-links">
        <li style="padding-right:0"><a href="<spring:url value="/rules"/>" class="button small">Forum Rules</a></li>
    </ul>
    <div class="clear"></div>
</div>

<div id="content">
    <div class="board_index">
        <form id="register" name="register" method="post" action="<spring:url value="/auth/register"/>">
            <input type="hidden" value="1" name="termsread"> <input type="hidden" value="1" name="agree_to_terms">

            <div class="block_wrap no_sidebar" id="register_form">
                <h2 class="maintitle">Ready to register?</h2>

                <div class="block-forum rounded-bot row1">
                    <p class="extra">
                        It's free and simple to register for our board! We just need a few pieces of information from you, and you'll be ready to make your first post in no time!
                        <br> If you already have an account, you can go directly to the
                        <a title="Go to sign in" href="/auth/login">sign in page</a> <br>
                    </p>
                    <fieldset class="row1">
                        <h3 class="bar">Account Information</h3>

                        <ul>
                            <li class="field required ">
                                <label for="username">Choose a username</label>
                                <input type="text" name="username" maxlength="26" size="25" id="username" class="input_text"><br>

                                <span class="desc">The name you'll sign in with. You can't use: <em>[ ] | ; , $ \ &lt; &gt; "</em></span>
                            </li>
                            <li class="field required ">
                                <label for="nickname">Choose a nickname</label>
                                <input type="text" name="nickname" maxlength="26" size="25" id="nickname" class="input_text"><br>

                                <span class="desc">The name that will be shown next to your topics, posts, etc. This should be between 3 and 26 characters long.</span>
                            </li>
                        </ul>
                        <hr>
                        <ul>
                            <li class="field required ">
                                <label for="email_1">Enter your e-mail address</label>
                                <input type="text" name="email" maxlength="150" size="25" class="input_text email" id="email_1"><br>

                                <span class="desc">So that we can verify your identity, and keep you updated</span>
                            </li>
                            <li class="field nodesc required">
                                <label for="email_2">Re-enter your e-mail address</label>
                                <input type="text" name="emailConfirm" maxlength="150" size="25" class="input_text email" id="email_2">
                            </li>
                        </ul>
                        <hr>
                        <ul>
                            <li class="field required ">
                                <label for="password">Choose your password</label>
                                <input type="password" name="password" value="" maxlength="32" size="25" class="input_text password" id="password"><br>

                                <span class="desc">You should choose a strong password, between 3 and 32 characters</span>
                            </li>
                            <li class="field nodesc required">
                                <label for="passwordConfrim">Re-enter your password</label>
                                <input type="password" name="passwordConfirm" value="" maxlength="32" size="25" class="input_text password" id="passwordConfrim"><br>
                            </li>
                        </ul>
                    </fieldset>
                    <fieldset class="row1">
                        <h3 class="bar">Board Options</h3>

                        <ul>
                            <li class="field checkbox">
                                <input type="checkbox" checked="checked" class="input_check" value="1" id="allow_admin_mail" name="allow_admin_mail">
                                <label for="allow_admin_mail">Receive email from administrators</label>
                            </li>
                            <li class="field checkbox nodesc">
                                <input type="checkbox" class="input_check" value="1" id="allow_member_mail" name="allow_member_mail">
                                <label for="allow_member_mail">Receive email from other members</label>
                            </li>
                            <li class="field">
                                <label for="timezone">Time Zone</label>
                                <select name="timezone" class="input_select" id="timezone">
                                    <c:forEach var="timeZone" items="${timeZones}" varStatus="status">
                                        <option value="<c:if test="${timeZone.offset > 0}">+</c:if>${timeZone.offset}"<c:if test="${not empty formBean.timezone && formBean.timezone == timeZone.offset}"> selected="selected"</c:if>>(GMT <c:if test="${timeZone.offset > 0}">+</c:if>${timeZone.offset} hours) ${timeZone.locale}</option>
                                    </c:forEach>
                                </select>
                            </li>
                            <li class="field checkbox">
                                <input type="checkbox" class="input_check" value="1" name="dst" id="dst">
                                <label id="dst_label" for="dst">Enable automatic DST detection?</label>
                            </li>
                        </ul>
                    </fieldset>
                    <fieldset style="margin-bottom: -5px;" class="submit rounded-bot">
                        <input type="submit" value="Register" class="input_submit">
                    </fieldset>
                </div>
            </div>
        </form>
    </div>
    <div class="clear"></div>
</div>