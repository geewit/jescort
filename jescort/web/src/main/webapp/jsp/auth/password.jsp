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
    <spring:bind path="command.*">
        <c:if test="${not empty status.errorMessages}">
            <p class="message error">password incorrect.</p>
        </c:if>
    </spring:bind>
    <br/>

    <h1 class="ipsType_pagetitle">My Settings</h1>
    <br/>
    <div class="ipsBox">
        <div class="ipsLayout ipsLayout_withleft ipsLayout_smallleft ipsVerticalTabbed clearfix usercp_body">
            <jsp:include page="include-tab.jsp"><jsp:param name="tab" value="password"/></jsp:include>
            <div id="usercp_content" class="ipsVerticalTabbed_content ipsLayout_content ipsBox_container">
                <form id="userCPForm" action="<spring:url value="/auth/password"/>" method="post">
                    <div class="ipsPad">
                        <fieldset class="row1">
                            <h3 class="ipsType_subtitle">Change password</h3>
                            <ul class="ipsForm ipsForm_horizontal">
                                <li class="ipsField">
                                    <label class="ipsField_title" for="oldPassword">Current password</label>
                                    <p class="ipsField_content">
                                        <input id="oldPassword" name="oldPassword" type="password" size="30" value="" maxlength="26" class="input_text"/>
                                        <br/>
                                        <span class="desc">
                                            <form:errors path="command.oldPassword" element="span" cssClass="error"/>
                                        </span>
                                    </p>
                                </li>
                                <li class="ipsField">
                                    <label class="ipsField_title" for="password">New password</label>
                                    <p class="ipsField_content">
                                        <input id="password" name="password" type="password" size="30" value="" class="input_text"/>
                                        <br/>
                                        <span class="desc">
                                            <form:errors path="command.password" element="span" cssClass="error"/>
                                        </span>
                                    </p>
                                </li>
                                <li class="ipsField">
                                    <label class="ipsField_title" for="passwordConfirm">New Password Confirm</label>
                                    <p class="ipsField_content">
                                        <input id="passwordConfirm" name="passwordConfirm" type="password" size="30" value="" class="input_text"/>
                                        <br/>
                                        <span class="desc">
                                            <form:errors path="command.passwordConfirm" element="span" cssClass="error"/>
                                        </span>
                                    </p>
                                </li>
                            </ul>
                        </fieldset>
                        <fieldset class="submit">
                            <input type="submit" value="Save Changes" name="submitForm" class="input_submit"/> or <a class="cancel" title="Cancel edit" href="/">Cancel</a>
                        </fieldset>
                    </div>
                </form>
            </div>
        </div>
    </div>

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
            <span itemprop="title">password</span>
        </li>
    </ol>
    <div class="clear"></div>
</div>