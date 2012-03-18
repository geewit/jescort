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
                <span itemprop="title">Registration</span>
            </li>
        </ol>
    </div>
    <br/>
    <div id="register_form">
        <div class="ipsSteps clearfix">
            <ul>
                <li class="">
                    <strong class="ipsSteps_title">Step 1</strong>
                    <span class="ipsSteps_desc">Your Account</span>
                    <span class="ipsSteps_arrow"/>
                </li>
                <li class="ipsSteps_active">
                    <strong class="ipsSteps_title">Step 2</strong>
                    <span class="ipsSteps_desc">Confirmation</span>
                    <span class="ipsSteps_arrow"/>
                </li>
            </ul>
        </div>
        <br/>
        <h1 class="maintitle">Join Our Community</h1>
        <div class="ipsBox">
            <div class="ipsBox_container ipsPad"> Thank you ${user.nickname}. Your registration has been
                submitted.<br/><br/>The community administrator has chosen to require validation for
                all email addresses. Within the next 10 minutes (usually instantly) you'll receive
                an email with instructions on the next step. Don't worry, it won't take long before
                you can post!<br/><br/>The email has been sent to ${user.mainEmail} </div>
        </div>
    </div>
    <ol class="breadcrumb bottom ipsList_inline clearfix clear">
        <li>
            <a href="<spring:url value="/"/>">Jescort Forums</a>
        </li>
        <li><span class="nav_sep">-></span>Registration</li>
    </ol>
    <div class="clear"></div>
</div>
