<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ taglib prefix="jescort" uri="http://www.jescort.net/tags" %>

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
                <a itemprop="url" title="Return to ${forum.subject}" href="<spring:url value="/forums/{forum.id}"/>">
                    <span itemprop="title">${forum.subject}</span>
                </a>
            </li>
        </ol>
    </div>
    <br/>
    <a class="ipsUserPhotoLink" href="<spring:url value="/users/${topic.rootPost.poster.id}"/>">
        <img class="ipsUserPhoto ipsUserPhoto_medium left" src="<spring:url value="/users/${topic.rootPost.poster.id}/avatar"/>"/>
    </a>
    <div class="ipsBox_withphoto">
        <h1 class="ipsType_pagetitle">
            <a href="<spring:url value="/topics/${topic.id}"/>">${topic.subject}</a>
        </h1>
        <div class="desc lighter blend_links" style="line-height:150%;">
            <a title="View Profile" href="<spring:url value="/users/${topic.rootPost.poster.id}"/>"><jescort:user user="${topic.rootPost.poster}"/></a>
            <fmt:formatDate value="${topic.createdate.time}" type="both" pattern="yyyy-MM-dd HH:mm:ss" var="topic_createdate"/>
            <spring:message code="message.topic_created_at" arguments="${topic_createdate}"/>
        </div>
    </div>
    <br/>
    <div class="topic_controls">
        <jescort:pager page="${posts}" requestUrl="/topics/${topic.id}"/>
        <ul class="topic_buttons">
            <c:choose>
                <c:when test="${topic.isLocked}">
            <li class="important">
                <span><img alt="This topic is locked" src="<spring:url value="/static/images/lock.png"/>"><spring:message code="message.topic_is_locked"/></span>
            </li>
                </c:when>
                <c:otherwise>
                    <shiro:notAuthenticated>
            <li class="disabled"><spring:message code="message.topic_cannot_replied"/></li>
                    </shiro:notAuthenticated>
                    <shiro:authenticated>
            <li>
                <a accesskey="r" title="Add Reply" href="<spring:url value="/topics/${topic.id}/reply"/>">
                    <img alt="Add Reply" src="<spring:url value="/static/images/arrow_rotate_clockwise.png"/>"><spring:message code="message.post_reply"/>
                </a>
            </li>
                    </shiro:authenticated>
                </c:otherwise>
            </c:choose>
        </ul>
    </div>
    <div class="maintitle clear clearfix">
        <span class="ipsType_small"><spring:message code="message.replies_num_of_topic" arguments="${topic.replies}"/></span>
    </div>
    <div class="topic hfeed clear clearfix">
        <div class="ipsBox">
            <div id="ips_Posts" class="ipsBox_container">
                <c:if test="${not empty posts.content}"><c:forEach var="post" items="${posts.content}" varStatus="status">
                    <div id="post_id_${post.id}" class="post_block hentry clear clearfix  postrow${status.index % 2 + 1} ">
                        <a id="${post.id}"></a>
                        <div class="post_wrap">
                            <h3 class="row${status.index % 2 + 2}">
                            <span class="post_id right ipsType_small">
                                <a title="Link to post #${status.count}" href="<spring:url value="/posts/${post.id}"/>">#${status.count}</a>
                            </span>
                            <span class="author vcard">
                                <c:set value="${post.poster}" var="poster"/>
                                <a title="View Profile" href="<spring:url value="/users/${poster.id}"/>" class="_hovertrigger url fn" hovercard-id="${poster.id}" hovercard-ref="member">
                                    <jescort:user user="${poster}"/>
                                </a>
                            </span>
                            </h3>
                            <div class="author_info">
                                <div class="user_details">
                                    <ul class="basic_info">
                                        <p class="desc member_title">${poster.nickname}</p>
                                        <li class="avatar">
                                            <a class="ipsUserPhotoLink" title="View Profile" href="<spring:url value="/users/${poster.id}"/>">
                                                <img class="ipsUserPhoto ipsUserPhoto_large" src="${poster.profile.avatar}"/>
                                            </a>
                                        </li>
                                        <li class="group_icon">
                                            <img alt="" src="<spring:url value="/static/images/badge-${fn:toLowerCase(poster.mainGroup.name)}.png"/>"/>
                                        </li>
                                        <li class="post_count desc lighter"><spring:message code="message.author_posts"/>: <fmt:formatNumber value="${poster.posts}" pattern="#,###"/></li>
                                        <span class="post_count desc lighter"><spring:message code="message.author_joined"/>:</span>
                                        <span class="post_count desc lighter"><fmt:formatDate value="${poster.createdate.time}" type="both" pattern="yyyy-MM-dd"/></span>
                                        <li>
                                            <span class="post_count desc lighter"><spring:message code="message.author_location"/>:</span>
                                            <span class="post_count desc lighter">${poster.mainAddress.location.name}</span>
                                        </li>
                                    </ul>
                                </div>
                            </div>
                            <div class="post_body">
                                <c:if test="${not empty post.edit}">
                                <p class="posted_info desc lighter ipsType_small">
                                    <spring:url value="/users/${post.edit.editor.id}" var="postEditorUrl"/>
                                    <fmt:formatDate value="${post.edit.editdate.time}" type="both" pattern="yyyy-MM-dd HH:mm" var="postEdittime"/>
                                    <jescort:user user="${post.edit.editor}" var="postEditor"/>
                                    <spring:message code="message.post_edit_by_editor" arguments="${postEditorUrl},${postEditor},${postEdittime}"/>
                                </p>
                                </c:if>
                                <div class="post entry-content">
                                    ${post.content}
                                    <c:if test="${not empty post.attachments}"><jescort:attachments attachments="${post.attachments}"/></c:if>
                                </div>
                                <c:if test="${not empty poster.profile.signature}">
                                    <div data-memberid="${poster.id}" class="signature">${poster.profile.signature}</div>
                                </c:if>
                                <ul class="post_controls clear clearfix">
                                    <li>
                                        <a title="Reply directly to this post" pid="${post.id}" class="_ips_trigger_quote ipsButton_secondary" href="<spring:url value="/posts/${post.id}/quote"/>">
                                            <spring:message code="message.post_quote"/>
                                        </a>
                                    </li>
                                    <li class="report">
                                        <a href="<spring:url value="/posts/${post.id}/report"/>"><spring:message code="message.report"/></a>
                                    </li>
                                </ul>
                            </div>
                        </div>
                    </div>
                </c:forEach></c:if>
            </div>
        </div>
        <div class="topic_controls clear ipsPad_top_bottom_half">
            <div class="left">
                <jescort:pager page="${posts}" requestUrl="/topics/${topic.id}"/>
            </div>
            <div class="ipsPad_top_slimmer right">
                <a class="ipsType_small desc" href="<spring:url value="/forums/${forum.id}"/>">Back to ${forum.subject}</a>
                <c:if test="not empty nextTopic"><a class="ipsType_small desc" href="<spring:url value="/topics/${nextTopic.id}"/>">Next Topic</a></c:if>
            </div>
        </div>
    </div>
    <br/>
    <ol class="breadcrumb bottom ipsList_inline clearfix clear">
        <li>
            <a href="<spring:url value="/"/>">Jescort Forums</a>
        </li>
        <li>
            <span class="nav_sep">-></span>
            <a title="Return to ${forum.subject}" href="<spring:url value="/forums/${forum.id}"/>">${forum.subject}</a>
        </li>
    </ol>
    <div class="clear"></div>
</div>
