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
                <span itemprop="title">Profile Settings</span>
            </li>
        </ol>
    </div>
    <br/>
    <h1 class="ipsType_pagetitle">My Settings</h1>
    <br/>
    <div class="ipsBox">
        <div class="ipsLayout ipsLayout_withleft ipsLayout_smallleft ipsVerticalTabbed clearfix usercp_body">
            <jsp:include page="include-tab.jsp"><jsp:param name="tab" value="profile"/></jsp:include>
            <form id="userCPForm" action="<spring:url value="/auth/profile"/>" method="post">
                <div id="usercp_content" class="ipsVerticalTabbed_content ipsLayout_content ipsBox_container">
                <div class="ipsPad">
                    <h2 class="ipsType_subtitle ipsSettings_pagetitle">General Account Settings</h2>
                    <div class="ipsSettings">
                        <fieldset class="ipsSettings_section">
                            <h3 class="ipsSettings_sectiontitle">Profile Information</h3>
                            <div>
                                <ul>
                                    <li class="ipsField">
                                        <label for="nickname" class="ipsSettings_fieldtitle">nickname</label>
                                        <input id="nickname" name="nickname" type="text" size="40" class="input_text" value="${user.nickname}"/>
                                        <br/>
                                    </li>
                                    <li class="ipsField">
                                        <label for="birthday" class="ipsSettings_fieldtitle">Your Birthday</label>
                                        <input id="birthday" name="birthday" type="text" size="40" class="input_text" value="<c:if test="${not empty user.birthday}"><fmt:formatDate value="${user.birthday.time}" type="both" pattern="yyyy-MM-dd"/></c:if>"/>
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
                                        <select id="timezone" name="profile.timezone" class="input_select">
                                            <c:forEach var="timeZone" items="${timeZones}" varStatus="status">
                                                <option value="<c:if test="${timeZone.offset > 0}">+</c:if>${timeZone.offset}"<c:if test="${not empty user.timezone && user.timezone == timeZone.offset}"> selected="selected"</c:if>>(GMT <c:if test="${timeZone.offset > 0}">+</c:if>${timeZone.offset} hours) ${timeZone.locale}</option>
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
                                        <input id="qq" name="properties['qq']" type="text" size="40" class="input_text" value="<c:out value="${user.properties['qq']}"/>"/>
                                    </li>
                                    <li class="custom">
                                        <label for="msn" class="ipsSettings_fieldtitle">MSN</label>
                                        <input id="msn" name="properties['msn']" type="text" size="40" class="input_text" value="<c:out value="${user.properties['msn']}"/>"/>
                                    </li>
                                    <li class="custom">
                                        <label for="website" class="ipsSettings_fieldtitle">Website</label>
                                        <input id="website" name="properties['website']" type="text" size="40" class="input_text" value="<c:out value="${user.properties['website']}"/>"/>
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
                                        <select id="gender" name="profile.gender" class="input_select">
                                            <c:set value="${user.gender}" var="gender" />
                                            <option value="MALE"<c:if test="gender == 'MALE'"> selected</c:if>>Male</option>
                                            <option value="FEMALE"<c:if test="gender == 'FEMALE'"> selected</c:if>>Female</option>
                                            <option value=""<c:if test="empty gender"> selected</c:if>>Not Telling</option>
                                        </select>
                                    </li>
                                    <li class="custom">
                                        <label for="province" class="ipsSettings_fieldtitle">Location</label>
                                        <select id="province" name="province" onChange="updateLocations('province', 'city', $(this).val());">
                                            <option value="0"><spring:message code="message.select_province"/></option>
                                        </select>
                                        <select id="city" name="city" onChange="updateLocations('city', 'district', $(this).val());">
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
            </form>
        </div>
    </div>
    <div class="clear"></div>
</div>
