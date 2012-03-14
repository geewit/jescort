<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

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
                <span itemprop="title">Lost Your Password</span>
            </li>
        </ol>
    </div>
    <br/>
    <h1 class="ipsType_pagetitle">Lost Your Password</h1>
    <div class="ipsType_pagedesc"> If you've lost your password, you can use this form to reset it.
        Enter your username or email address in the field below. The username or email address is
        case <b>in</b>sensitive. Once you have submitted the form, you will receive an email asking
        for validation of this request to ensure that no malicious use has occurred. This email will
        also contain a link that you must click for further instruction. </div>
    <br/>
    <form method="post" action="<spring:url value="/auth/password_forgot"/>">
        <h2 class="maintitle">Recover Password</h2>
        <div class="ipsBox" id="lost_pass_form">
            <div class="ipsBox_container ipsPad_double">
                <fieldset>
                    <ul class="ipsForm ipsForm_horizontal">
                        <li class="ipsField clear">
                            <label class="ipsField_title" for="username">Account username</label>
                            <p class="ipsField_content">
                                <input type="text" class="input_text" id="username" name="username" size="32"/>
                            </p>
                        </li>
                        <li class="ipsField clear">
                            <label class="ipsField_title" for="email"><em>-OR-</em> Email Address</label>
                            <p class="ipsField_content">
                                <input type="text" class="input_text" id="email" name="email" size="32"/>
                            </p>
                        </li>
                    </ul>
                </fieldset>
            </div>
            <fieldset class="submit">
                <input type="submit" value="Proceed" class="input_submit"/> or <a class="cancel" title="Cancel" href="<spring:url value="/"/>">Cancel</a>
            </fieldset>
        </div>
    </form>
    <ol class="breadcrumb bottom ipsList_inline clearfix clear">
        <li>
            <a href="<spring:url value="/"/>">Jescort Forums</a>
        </li>
        <li><span class="nav_sep">-></span> Lost Your Password?</li>
    </ol>
    <div class="clear"/>
</div>

