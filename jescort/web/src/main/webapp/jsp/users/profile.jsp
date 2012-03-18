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
                <span itemprop="title">Viewing Profile: ${user.nickname}</span>
            </li>
        </ol>
    </div>
    <br/>
    <ul class="topic_buttons"></ul>
    <div id="profile_background" class="ipsBox clear vcard">
        <div class="ipsVerticalTabbed ipsLayout ipsLayout_withleft ipsLayout_smallleft clearfix">
            <div id="profile_tabs" class="ipsVerticalTabbed_tabs ipsLayout_left">
                <p class="short photo_holder">
                    <img alt="${user.nickname}'s Photo" src="<spring:url value="/users/${user.id}/avatar"/>" id="profile_photo" class="ipsUserPhoto"/>
                </p>
                <ul class="clear">
                    <li data-tabid="user_info" class="tab_toggle active" id="tab_link_core:info">
                        <a href="<spring:url value="/users/${user.id}/profile"/>">Overview</a>
                    </li>
                    <li data-tabid="topics" class=" tab_toggle" id="tab_link_forums:topics">
                        <a title="View Topics" href="<spring:url value="/users/${user.id}/profile/topics"/>">Topics</a>
                    </li>
                    <li data-tabid="posts" class=" tab_toggle" id="tab_link_forums:posts">
                        <a title="View Posts" href="<spring:url value="/users/${user.id}/profile/posts"/>">Posts</a>
                    </li>
                </ul>
            </div>
            <div id="profile_content"
                 class="ipsVerticalTabbed_content ipsLayout_content ipsBox_container">
                <div class="ipsPad">
                    <div id="profile_content_main">
                        <div id="user_info_cell">
                            <h1 class="ipsType_pagetitle">
                                <span class="fn nickname">${user.nickname}</span>
                            </h1> Member Since <fmt:formatDate value="${user.createdate.time}" type="both" pattern="yyyy-MM-dd"/><br/>
                            <span class="ipsBadge ipsBadge_green reset_cursor">Online</span>
                            <span class="desc lighter">Last Active <fmt:formatDate value="${user.lastActive.time}" type="both" pattern="yyyy-MM-dd HH:mm"/></span>
                        </div>
                    </div>
                    <div class="clearfix" id="profile_panes_wrap">
                        <div class="ipsLayout ipsLayout_withright ipsLayout_largeright clearfix" id="pane_core:info">
                            <div class="ipsLayout_content">
                                <div class="general_box clearfix">
                                    <h3>Community Stats</h3>
                                    <br/>
                                    <ul class="ipsList_data clearfix">
                                        <li>
                                            <span class="row_title">Group</span>
                                            <span class="row_data">${user.mainGroup.name}</span>
                                        </li>
                                        <li>
                                            <span class="row_title">Active Posts</span>
                                            <span class="row_data">${user.posts} (<fmt:formatNumber value="${user.postsPerday}" pattern="#.##"/> per day)</span>
                                        </li>
                                        <li>
                                            <span class="row_title">Age</span>
                                            <span class="row_data">${user.profile.age}</span>
                                        </li>
                                        <li>
                                            <span class="row_title">Birthday</span>
                                            <span class="row_data"><fmt:formatDate value="${user.profile.birthday.time}" type="both" pattern="yyyy-MM-dd"/></span>
                                        </li>
                                        <li>
                                            <span class="row_title">Gender</span>
                                            <div class="row_data">
                                                <c:choose><c:when test="${not empty user.profile.gender}">
                                                    <c:set value="${user.profile.gender}" var="gender"/>
                                                    <img alt="${gender}" src="<spring:url value="/static/images/${fn:toLowerCase(gender)}.gif"/>"><spring:message code="message.gender_${gender}"/>
                                                </c:when><c:otherwise>
                                                    <img src="<spring:url value="/static/images/mystery.png"/>" alt="Not Telling">Not Telling
                                                </c:otherwise></c:choose>
                                            </div>
                                        </li>
                                        <li>
                                            <span class="row_title">Location</span>
                                            <span class="row_data desc lighter">${user.mainAddress.location.name}</span>
                                        </li>
                                    </ul>
                                    <br/>
                                </div>
                                <div class="general_box clearfix">
                                    <h3>Contact Information</h3>
                                    <br/>
                                    <ul class="ipsList_data clearfix">
                                        <li>
                                            <span class="row_title">MSN</span>
                                            <span class="row_data">
                                                <img alt="MSN" src="<spring:url value="/static/images/profile_msn.gif"/>"/>
                                                <a href="msnim:chat?contact=<c:out value="${user.properties['msn']}"/>" class="url"><c:out value="${user.properties['msn']}"/></a>
                                            </span>
                                        </li>
                                        <li>
                                            <span class="row_title">QQ</span>
                                            <span class="row_data">
                                                <img alt="QQ" src="<spring:url value="/static/images/profile_qq.jpg"/>"/>
                                                <a href="tencent://message/?uin=<c:out value="${user.properties['qq']}"/>" class="url"><c:out value="${user.properties['qq']}"/></a>
                                            </span>
                                        </li>
                                    </ul>
                                </div>
                                <div class="general_box clearfix">
                                    <h3>Signature</h3>
                                    <div style="padding:0 10px">
                                        <div data-memberid="0" class="signature">
                                            ${user.profile.signature}
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="ipsLayout_right">
                                <div data-tooltip="${user.nickname} has <fmt:formatNumber value="${user.reputation}" pattern="#,###"/> reputation" class="reputation positive">
                                    <span class="number"><fmt:formatNumber value="${user.reputation}" pattern="#,###"/></span>
                                    <span class="title">Excellent</span>
                                </div>
                                <br/>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <br/>
    <ul class="topic_buttons">
        <li class="non_button clearfix">
            <a href="<spring:url value="/users/${user.id}/report"/>">Report This Member</a>
        </li>
    </ul>
    <ol class="breadcrumb bottom ipsList_inline clearfix clear">
        <li>
            <a href="<spring:url value="/"/>">Jescort Forums</a>
        </li>
        <li><span class="nav_sep">-></span> Viewing Profile: ${user.nickname}</li>
    </ol>
    <div class="clear"></div>
</div>
