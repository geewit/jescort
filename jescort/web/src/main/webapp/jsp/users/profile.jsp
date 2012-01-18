<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!-- Breadcrumb -->
<div id="breadcrumb">
    <ul class="fleft">
        <li style="font-weight:bold" class="first"><a href="<spring:url value="/"/>">Neowin Forums</a></li>
        <li><b>Viewing Profile: ${user.nickname}</b></li>
    </ul>
    <ul class="fright" id="breadcrumb-links">
        <li style="padding-right:0">
            <a href="<spring:url value="/rules"/>" class="button small"><spring:message code="message.forum_rules"/></a>
        </li>
    </ul>
    <div class="clear"></div>
</div>
<!-- // Breadcrumb -->
<div id="content">
    <ul class="topic_buttons">
        <li>
            <a href="http://www.neowin.net/forum/index.php?app=core&module=usercp&tab=members"><img src="<spring:url value="/static/images/user_edit.png"/>" alt="Edit"> Edit my Profile</a>
        </li>
    </ul>
    <div class="vcard clear">
        <div id="userBg">
            <div id="profile_overview" class="left">
                <div style="padding-right:10px">
                    <div class="general_box alt rounded">
                        <h3 class="rounded-top">Member Photo</h3>

                        <div class="personal_info clear">
                            <img class="photo" src="<spring:url value="/static/images/default_large.png"/>" alt="Sinosaga&#39;s Photo">

                            <ul>
                                <li>
                                    <a href="<spring:url value="/"/>"><img src="<spring:url value="/static/images/page_topic_magnify.png"/>" alt="Find Topics"> Find My Content</a>
                                </li>
                                <li id="dname_history">
                                    <a href="<spring:url value="/"/>" title="View display name history"><img src="<spring:url value="/static/images/display_name.png"/>" alt=""> Display name history</a>
                                </li>
                            </ul>
                        </div>
                        <div class="reputation zero">
                            <span class="number">Reputation: <fmt:formatNumber value="${user.reputation}" pattern="#,###"/></span>
                            <span class="title">Neutral</span>
                        </div>
                        <div class="other_contact">
                            <div class="about_info">
                                <dl>
                                    <dt>Group:</dt>
                                    <dd>${fn:toLowerCase(user.mainGroup.name)}</dd>
                                    <dt>Active Posts:</dt>
                                    <dd title="0% of total forum posts">${user.posts} (<fmt:formatNumber value="${user.postsPerday}" pattern="#.##"/> per day)</dd>
                                    <dt>Joined:</dt>
                                    <dd>
                                        <fmt:formatDate value="${user.createdate.time}" type="both" pattern="yyyy-MM-dd HH:mm"/></dd>
                                    <dt>Last Active:</dt>
                                    <dd>
                                        <img title="User is offline" alt="User is offline" src="<spring:url value="/static/images/user_off.gif"/>"><fmt:formatDate value="${user.lastActive.time}" type="both" pattern="yyyy-MM-dd HH:mm"/>
                                    </dd>
                                </dl>
                            </div>
                        </div>
                        <div class="other_contact clearfix clear">
                            <div class="about_info">
                                <h3 class="bar">My Information</h3>
                                <dl>
                                    <dt>Age:</dt>
                                    <dd>${user.profile.age} years old</dd>
                                    <dt>Birthday:</dt>
                                    <dd>
                                        <fmt:formatDate value="${user.profile.birthday.time}" type="both" pattern="yyyy-MM-dd"/></dd>
                                    <dt>Gender:</dt>
                                    <dd>
                                        <c:choose> <c:when test="${not empty user.profile.gender}">
                                            <c:set value="${user.profile.gender}" var="gender"/>
                                            <img alt="${gender}" src="<spring:url value="/static/images/${fn:toLowerCase(gender)}.gif"/>"><spring:message code="message.gender_${gender}"/>
                                        </c:when> <c:otherwise>
                                            <img src="<spring:url value="/static/images/mystery.png"/>" alt="Not Telling">Not Telling
                                        </c:otherwise> </c:choose>
                                    </dd>
                                    <dt>Location:</dt>
                                    <dd>${user.mainAddress.location.name}</dd>
                                </dl>
                            </div>
                        </div>
                        <div class="other_contact clearfix">
                            <h3 class="bar">Contact Information</h3>
                            <dl>
                                <dt>E-mail:</dt>
                                <dd class="clearfix">
                                    <em><i>Private</i></em>
                                    <a href="mailto:<c:out value="${user.mainEmail}"/>">Click here to e-mail me</a>
                                </dd>
                                <dt>MSN:</dt>
                                <dd>
                                    <img alt="MSN" src="<spring:url value="/static/images/profile_msn.gif"/>">&nbsp;
                                    <a href="msnim:chat?contact=<c:out value="${user.properties['msn']}"/>" class="url"><c:out value="${user.properties['msn']}"/></a>
                                </dd>
                                <dt>Website URL:</dt>
                                <dd>
                                    <img alt="Website URL" src="<spring:url value="/static/images/profile_website.gif"/>">&nbsp;
                                    <a href="<c:out value="${user.properties['website']}"/>" rel="me" class="url uid"><c:out value="${user.properties['website']}"/></a>
                                </dd>
                                <dt>QQ:</dt>
                                <dd>
                                    <img alt="QQ" src="<spring:url value="/static/images/profile_qq.jpg"/>">&nbsp;
                                    <a href="tencent://message/?uin=<c:out value="${user.properties['qq']}"/>" class="url"><c:out value="${user.properties['qq']}"/></a>
                                </dd>
                            </dl>
                        </div>
                    </div>
                </div>
            </div>
            <div id="main_profile_body" class="right">
                <h2 class="maintitle">
                    <span class="left"><span class="fn nickname">${user.nickname}</span>'s Profile</span>
                </h2>
                <div class="block-forum rounded-bot clearfix">
                    <div class="recent_activity">
                        <ol class="tab_bar no_title mini">
                            <li id="tab_link_members:status" class="tab_toggle active">
                                <a href="<spring:url value="/"/>" title="View Recent Status Updates">Recent Status Updates</a>
                            </li>
                            <li id="tab_link_members:aboutme" class="tab_toggle ">
                                <a href="<spring:url value="/"/>" title="View About Me">About Me</a></li>
                            <li id="tab_link_forums:topics" class="tab_toggle ">
                                <a href="<spring:url value="/"/>" title="View Topics">Topics</a></li>
                            <li id="tab_link_forums:posts" class="tab_toggle ">
                                <a href="<spring:url value="/"/>" title="View Posts">Posts</a></li>
                        </ol>
                        <div id="tab_content" class="row1 profile_pane">
                            <div id="tab_">
                                <h3 class="bar">About Me</h3>

                                <div class="general_box"></div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="clear"></div>
</div>
