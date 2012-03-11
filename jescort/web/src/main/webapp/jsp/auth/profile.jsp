<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>


<div id="body">
    <div id="secondary_navigation" class="clearfix">
        <ol class="breadcrumb top ipsList_inline">
            <li itemscope="" itemtype="http://data-vocabulary.org/Breadcrumb">
                <a href="<spring:url value="/"/>" itemprop="url">
                    <span itemprop="title">Jescort Forums</span>
                </a>
            </li>
            <li itemscope="" itemtype="http://data-vocabulary.org/Breadcrumb">
                <span class="nav_sep"/>
                <a href="<spring:url value="/"/>" title="Return toYour control panel" itemprop="url">
                    <span itemprop="title">Your control panel</span>
                </a>
            </li>
            <li itemscope="" itemtype="http://data-vocabulary.org/Breadcrumb">
                <span class="nav_sep"/>
                <a href="<spring:url value="/"/>" title="Return toSettings" itemprop="url">
                    <span itemprop="title">Settings</span>
                </a>
            </li>
            <li itemscope="" itemtype="http://data-vocabulary.org/Breadcrumb">
                <span class="nav_sep"/>
                <a href="<spring:url value="/"/>" title="Return toProfile Settings" itemprop="url">
                    <span itemprop="title">Profile Settings</span>
                </a>
            </li>
        </ol>
    </div>
    <br/>
    <form method="post" action="<spring:url value="/auth/profile"/>" id="userCPForm">
        <h1 class="ipsType_pagetitle">My Settings</h1>
        <br/>
        <div class="ipsBox">
            <div class="ipsLayout ipsLayout_withleft ipsLayout_smallleft ipsVerticalTabbed clearfix usercp_body">
                <div id="usercp_tabs" class="ipsVerticalTabbed_tabs ipsLayout_left">
                    <ul>
                        <li class="active">
                            <a href="<spring:url value="/auth/profile"/>">profile</a>
                        </li>
                        <li>
                            <a href="<spring:url value="/auth/password"/>">Password</a>
                        </li>
                        <li>
                            <a href="<spring:url value="/auth/avatar"/>">Avatar</a>
                        </li>
                        <li>
                            <a href="<spring:url value="/auth/signature"/>">Signature</a>
                        </li>
                        <li>
                            <a href="<spring:url value="/auth/attachments"/>">Manage Attachments</a>
                        </li>
                    </ul>
                </div>
                <div class="ipsVerticalTabbed_content ipsLayout_content ipsBox_container"
                     id="usercp_content">
                    <div class="ipsPad">
                        <h2 class="ipsType_subtitle ipsSettings_pagetitle">General Account Settings</h2>
                        <div class="ipsSettings">
                            <fieldset class="ipsSettings_section">
                                <h3 class="ipsSettings_sectiontitle">Profile Information</h3>
                                <div>
                                    <ul>
                                        <li class="ipsField">
                                            <label for="nickname" class="ipsSettings_fieldtitle">nickname</label>
                                            <input id="nickname" name="nickname" type="text" size="40" class="input_text" value=""/>
                                            <br/>
                                        </li>
                                        <li class="ipsField">
                                            <label for="birthday" class="ipsSettings_fieldtitle">Your Birthday</label>
                                            <input id="birthday" name="birthday" type="text" size="40" class="input_text" value=""/>
                                            <br/>
                                            <span class="desc">Entering your birthday is optional</span>
                                        </li>
                                    </ul>
                                </div>
                            </fieldset>
                            <fieldset class="ipsSettings_section">
                                <h3 class="ipsSettings_sectiontitle">Time Zone</h3>
                                <div>
                                    <ul class="ipsForm ipsForm_horizontal">
                                        <li class="ipsField">
                                            <label for="timezone"/>
                                            <select id="timezone" name="timezone" class="input_select">
                                                <c:forEach var="timeZone" items="${timeZones}" varStatus="status">
                                                    <option value="<c:if test="${timeZone.offset > 0}">+</c:if>${timeZone.offset}"<c:if test="${not empty formBean.timezone && formBean.timezone == timeZone.offset}"> selected="selected"</c:if>>(GMT <c:if test="${timeZone.offset > 0}">+</c:if>${timeZone.offset} hours) ${timeZone.locale}</option>
                                                </c:forEach>
                                            </select>
                                            <br/>
                                        </li>
                                    </ul>
                                </div>
                            </fieldset>
                            <fieldset class="ipsSettings_section">
                                <h3 class="ipsSettings_sectiontitle">Contact Methods</h3>
                                <div>
                                    <ul>
                                        <li class="custom">
                                            <label for="qq" class="ipsSettings_fieldtitle">QQ</label>
                                            <input id="qq" name="properties['qq']" type="text" size="40" class="input_text" value=""/>
                                        </li>
                                        <li class="custom">
                                            <label for="msn" class="ipsSettings_fieldtitle">MSN</label>
                                            <input id="msn" name="properties['msn']" type="text" size="40" class="input_text" value=""/>
                                        </li>
                                        <li class="custom">
                                            <label for="website" class="ipsSettings_fieldtitle">Website</label>
                                            <input id="website" name="properties['website']" type="text" size="40" class="input_text" value=""/>
                                        </li>
                                    </ul>
                                </div>
                            </fieldset>
                            <fieldset class="ipsSettings_section">
                                <h3 class="ipsSettings_sectiontitle">Profile Information</h3>
                                <div>
                                    <ul>
                                        <li class="custom">
                                            <label for="gender" class="ipsSettings_fieldtitle">Gender</label>
                                            <select id="gender" name="gender" class="input_select">
                                                <option value="Male">Male</option>
                                                <option value="Female">Female</option>
                                                <option value="">Not Telling</option>
                                            </select>
                                        </li>
                                        <li class="custom">
                                            <label for="province" class="ipsSettings_fieldtitle">Location</label>
                                            <select id="province" name="province" onChange="updateLocations('city', $(this).val());">
                                                <option value="0"><spring:message code="message.select_province"/></option>
                                            </select>
                                            <select id="city" name="city" onChange="updateLocations('district', $(this).val());">
                                                <option value="0"><spring:message code="message.select_city"/></option>
                                            </select>
                                            <select id="district" name="district">
                                                <option value="0"><spring:message code="message.select_district"/></option>
                                            </select>
                                        </li>
                                    </ul>
                                </div>
                            </fieldset>
                        </div>
                        <fieldset class="submit">
                            <input name="submitForm" type="submit" class="input_submit" value="Save Changes"/> or <a href="/" title="Cancel edit" class="cancel">Cancel</a>
                        </fieldset>
                    </div>
                </div>
            </div>
        </div>
    </form>
    <div class="clear"/>
</div>
