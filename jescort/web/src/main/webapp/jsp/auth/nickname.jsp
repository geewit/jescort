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
                <a itemprop="url" title="Return toYour control panel" href="<spring:url value="/"/>">
                    <span itemprop="title">Your control panel</span>
                </a>
            </li>
            <li itemtype="http://data-vocabulary.org/Breadcrumb" itemscope="">
                <span class="nav_sep">-></span>
                <a itemprop="url" title="Return toSettings" href="<spring:url value="/"/>">
                    <span itemprop="title">Settings</span>
                </a>
            </li>
            <li itemtype="http://data-vocabulary.org/Breadcrumb" itemscope="">
                <span class="nav_sep">-></span>
                <a itemprop="url" title="Return to nickname" href="<spring:url value="/"/>">
                    <span itemprop="title">nickname</span>
                </a>
            </li>
        </ol>
    </div>
    <br/>
    <form id="userCPForm" action="<spring:url value="/"/>" method="post">
        <fieldset></fieldset>
        <h1 class="ipsType_pagetitle">My Settings</h1>
        <br/>
        <div class="ipsBox">
            <div class="ipsLayout ipsLayout_withleft ipsLayout_smallleft ipsVerticalTabbed clearfix usercp_body">
                <div id="usercp_tabs" class="ipsVerticalTabbed_tabs ipsLayout_left">
                    <ul>
                        <li>
                            <a href="<spring:url value="/"/>">Profile Settings</a>
                        </li>
                        <li>
                            <a href="<spring:url value="/"/>">Email &amp; Password</a>
                        </li>
                        <li class="active">
                            <a href="<spring:url value="/"/>">nickname</a>
                        </li>
                        <li>
                            <a href="<spring:url value="/"/>">Signature</a>
                        </li>
                        <li>
                            <a href="<spring:url value="/"/>">Manage Attachments</a>
                        </li>
                    </ul>
                </div>
                <div id="usercp_content" class="ipsVerticalTabbed_content ipsLayout_content ipsBox_container">
                    <div class="ipsPad">
                        <fieldset class="row1">
                            <h3 class="ipsType_subtitle">Change nickname</h3>
                            <p class="ipsType_pagedesc">You have made <strong>0</strong> of
                                <strong>2</strong> nickname changes since 07 Dec 2011. You
                                are permitted to make 2 changes in a 90 day period.<br/>Changing
                                your display name will <strong>not</strong> affect your log in
                                details.</p>
                            <br/>
                            <ul class="ipsForm ipsForm_horizontal">
                                <li class="ipsField">
                                    <label class="ipsField_title" for="nickname">New nickname</label>
                                    <p class="ipsField_content">
                                        <input type="text" size="30" value="" id="nickname" name="nickname" maxlength="26" class="input_text"/>
                                        <br/>
                                        <span class="desc lighter">This is the nickname you wish to now use. Characters not permitted: [ ] | , ; $</span>
                                    </p>
                                </li>
                                <li class="ipsField">
                                    <label class="ipsField_title" for="password">Current password</label>
                                    <p class="ipsField_content">
                                        <input type="password" size="30" value="" id="password" name="password" class="input_text"/>
                                        <br/>
                                        <span class="desc lighter">This is to ensure security on your account</span>
                                    </p>
                                </li>
                            </ul>
                        </fieldset>
                        <fieldset class="submit">
                            <input type="submit" value="Save Changes" name="submitForm" class="input_submit"/> or <a class="cancel" title="Cancel edit" href="/">Cancel</a>
                        </fieldset>
                    </div>
                </div>
            </div>
        </div>
    </form>
    <ol class="breadcrumb bottom ipsList_inline clearfix clear">
        <li>
            <a href="<spring:url value="/"/>">Jescort Forums</a>
        </li>
        <li>
            <span class="nav_sep">-></span>
            <a title="Return toYour control panel" href="<spring:url value="/"/>">Your control panel</a>
        </li>
        <li>
            <span class="nav_sep">-></span>
            <a title="Return toSettings" href="<spring:url value="/"/>">Settings</a>
        </li>
        <li>
            <span class="nav_sep">-></span>
            <a title="Return to <spring:url value="/"/>" href="<spring:url value="/"/>">nickname Name</a>
        </li>
    </ol>
    <div class="clear"/>
</div>