<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<div id="body">
    <div class="clearfix" id="secondary_navigation">
        <ol class="breadcrumb top ipsList_inline">
            <li itemtype="http://data-vocabulary.org/Breadcrumb" itemscope="">
                <a itemprop="url" href="<spring:url value="/"/>">
                    <span itemprop="title">Jescort Forums</span>
                </a>
            </li>
            <li itemtype="http://data-vocabulary.org/Breadcrumb" itemscope="">
                <span class="nav_sep">-></span>
                <span itemprop="title">Registration Form</span>
            </li>
        </ol>
    </div>
    <br>
    <div id="register_form">
        <div class="ipsSteps clearfix">
            <ul>
                <li class="ipsSteps_active">
                    <strong class="ipsSteps_title">Step 1</strong>
                    <span class="ipsSteps_desc">Your Account</span>
                    <span class="ipsSteps_arrow">&nbsp;</span>
                </li>
                <li class="">
                    <strong class="ipsSteps_title">Step 2</strong>
                    <span class="ipsSteps_desc">Confirmation</span>
                    <span class="ipsSteps_arrow">&nbsp;</span>
                </li>
            </ul>
        </div>
        <br>
        <form id="register" method="post" action="<spring:url value="/auth/register"/>">
            <input type="hidden" value="0" id="auto_dst" name="dst">
            <h1 class="maintitle">
                Join Our Community
            </h1>
            <div class="ipsBox">
                <div class="ipsBox_container ipsPad">
                    <spring:bind path="command.*">
                        <c:if test="${not empty status.errorMessages}">
                            <p class="message error">Errors were found with your registration. Please correct the highlighted fields below.</p>
                        </c:if>
                    </spring:bind>
                    <br>
                    <fieldset>
                        <ul class="ipsForm ipsForm_horizontal">
                            <li class="ipsField">
                                <p class="ipsField_content">
                                    <span class="ipsForm_required ipsType_smaller">* Required Information</span>
                                </p>
                            </li>
                            <li class="ipsField clear error">
                                <label class="ipsField_title" for="username">Username <span class="ipsForm_required">*</span></label>
                                <p class="ipsField_content">
                                    <input id="username" name="username" value="" type="text" maxlength="26" size="45" class="input_text"><br>
                                    <span class="desc primary lighter">
                                        <form:errors path="command.username" element="span" cssClass="error"/>
                                        Between 3 and 26 characters
                                    </span>
                                </p>
                            </li>
                            <li class="ipsField clear error">
                                <label class="ipsField_title" for="nickname">nickname</label>
                                <p class="ipsField_content">
                                    <input id="nickname" name="nickname" value="" type="text" maxlength="26" size="45" class="input_text">
                                </p>
                            </li>
                            <li class="ipsField clear error">
                                <label class="ipsField_title" for="email">E-mail Address <span class="ipsForm_required">*</span></label>
                                <p class="ipsField_content">
                                    <input type="text" value="" name="email" maxlength="150" size="45" class="input_text email" id="email"><br>
                                    <span class="desc">
                                        <form:errors path="command.email" element="span" cssClass="error"/>
                                    </span>
                                </p>
                            </li>
                            <li class="ipsField clear error">
                                <label class="ipsField_title" for="emailConfirm">E-mail Address confirm<span class="ipsForm_required">*</span></label>
                                <p class="ipsField_content">
                                    <input type="text" value="" name="emailConfirm" maxlength="150" size="45" class="input_text email" id="emailConfirm"><br>
                                    <span class="desc">
                                        <form:errors path="command.emailConfirm" element="span" cssClass="error"/>
                                    </span>
                                </p>
                            </li>
                            <li class="ipsField clear">
                                <label class="ipsField_title" for="password">Password <span class="ipsForm_required">*</span></label>
                                <p class="ipsField_content">
                                    <input type="password" name="password" value="" maxlength="32" size="45" class="input_text password" id="password"><br>
                                    <span class="desc lighter">
                                        <form:errors path="command.password" element="span" cssClass="error"/>
                                        Between 3 and 32 characters
                                    </span>
                                </p>
                            </li>
                            <li class="ipsField clear">
                                <label class="ipsField_title" for="passwordConfirm">Confirm Password <span class="ipsForm_required">*</span></label>
                                <p class="ipsField_content">
                                    <input id="passwordConfirm" name="passwordConfirm" type="password" value="" maxlength="32" size="45" class="input_text password"><br>
                                    <span class="desc lighter">
                                        <form:errors path="command.passwordConfirm" element="span" cssClass="error"/>
                                    </span>
                                </p>
                            </li>
                        </ul>
                    </fieldset>
                    <fieldset>
                        <ul class="ipsForm ipsForm_horizontal">
                            <li class="ipsField clear ipsField_checkbox">
                                <input id="agreeTerms" name="agreeTerms" type="checkbox" class="input_check" value="1">
                                <p class="ipsField_content">
                                    <label error="" for="agreeTerms">
                                        <strong>I've read and agree to the <a id="tou_link" href="#">Terms of Use</a></strong>
                                        <br>
                                        <form:errors path="command.agreeTerms" element="span" cssClass="error"/>
                                    </label>
                                    <textarea style="width: 350px; height: 100px; display: block;" class="input_text" id="tou">aaaaa</textarea>
                                </p>
                            </li>
                        </ul>
                    </fieldset>
                    <br>
                    <fieldset>
                        <input type="submit" value="Create Account" id="register_submit" class="ipsButton">
                    </fieldset>
                </div>
            </div>
        </form>
    </div>
    <ol class="breadcrumb bottom ipsList_inline clearfix clear">
        <li><a href="<spring:url value="/"/>">Jescort Forums</a></li>
        <li><span class="nav_sep">-></span> Registration Form</li>
    </ol>
    <div class="clear"></div>
</div>