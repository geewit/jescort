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
                <span itemprop="title">password</span>
            </li>
        </ol>
    </div>
    <br/>
    <form id="userCPForm" action="<spring:url value="/"/>" method="post">
        <h1 class="ipsType_pagetitle">My Settings</h1>
        <br/>
        <div class="ipsBox">
            <div class="ipsLayout ipsLayout_withleft ipsLayout_smallleft ipsVerticalTabbed clearfix usercp_body">
                <jsp:include page="include-tab.jsp"><jsp:param name="tab" value="password"/></jsp:include>
                <div id="usercp_content" class="ipsVerticalTabbed_content ipsLayout_content ipsBox_container">
                    <div class="ipsPad">
                        <fieldset class="row1">
                            <h3 class="ipsType_subtitle">Change password</h3>
                            <ul class="ipsForm ipsForm_horizontal">
                                <li class="ipsField">
                                    <label class="ipsField_title" for="password">Current password</label>
                                    <p class="ipsField_content">
                                        <input id="password" name="password" type="text" size="30" value="" maxlength="26" class="input_text"/>
                                        <br/>
                                        <span class="desc lighter">This is to ensure security on your account</span>
                                    </p>
                                </li>
                                <li class="ipsField">
                                    <label class="ipsField_title" for="newPassword">Current password</label>
                                    <p class="ipsField_content">
                                        <input id="newPassword" name="newPassword" type="password" size="30" value="" class="input_text"/>
                                        <br/>
                                        <span class="desc lighter">This is the password you wish to now use.</span>
                                    </p>
                                </li>
                                <li class="ipsField">
                                    <label class="ipsField_title" for="passwordConfirm">Password Confirm</label>
                                    <p class="ipsField_content">
                                        <input id="passwordConfirm" name="passwordConfirm" type="password" size="30" value="" class="input_text"/>
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
    <div class="clear"></div>
</div>