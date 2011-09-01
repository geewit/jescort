<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="jescort" uri="http://www.jescort.net/tags" %>

<div id="breadcrumb">
    <ul class="fleft">
        <li class="first" style="font-weight:bold"><a href="<spring:url value="/"/>">Forums</a></li>
        <li><b>View Help Topics</b></li>
    </ul>
    <ul id="breadcrumb-links" class="fright">
        <li style="padding-right:0"><a class="button small" href="<spring:url value="/rules"/>"><spring:message code="message.forum_rules"/></a></li>
    </ul>
    <div class="clear"></div>
</div>
<!-- Content Wrapper -->
<div id="content">
    <div style="margin-top:10px;" class="topic_controls">
        <jescort:pager page="${topics}" requestUrl="/forums/${forum.id}"/>
        <ul class="topic_buttons">
            <li><a accesskey="s" title="Start New Topic" href="<spring:url value="/"/>"><img alt="Start New Topic" src="<spring:url value="/static/images/page_white_add.png"/>"><spring:message code="message.start_new_topic"/></a></li>
            <li class="disabled"><spring:message code="message.cannot_start_new_topic"/></li>
        </ul>
    </div>
    <div style="padding-top: 0;" class="category_block block_wrap clear">
        <h3 class="maintitle">
            ${forum.subject}
        </h3>
        <div class="block-forum rounded-bot">
            <table id="forum_table" summary="Topics In This Forum &quot;${forum.subject}&quot;" class="ipb_table topic_list">
                <tbody>
                    <tr class="header">
                        <th class="col_f_icon" scope="col">&nbsp;</th>
                        <th class="col_f_topic" scope="col"><spring:message code="message.topic"/></th>
                        <th class="col_f_starter short" scope="col"><spring:message code="message.author"/></th>
                        <th class="col_f_views stats" scope="col"><spring:message code="message.topic_replies_and_views"/></th>
                        <th class="col_f_post" scope="col"><spring:message code="message.last_post_info"/></th>
                        <th style="width: 1%;"></th>
                    </tr>
                    <!-- ANNOUNCEMENTS -->
                    <!-- BEGIN TOPICS -->
                    <c:if test="${not empty topics.content}">
                    <c:forEach var="topic" items="${topics.content}" varStatus="status">
                    <tr id="trow_${topic.id}" class="row${status.index % 2 + 1}">
                        <td class="short altrow"><img alt="Hot Topic (New)" src="<spring:url value="/static/images/t_hot_unread.png"/>"></td>
                        <td class="__topic" id="anonymous_element_${status.count}">
                            <span style="display:inline-block;min-height:16px;line-height:16px;"></span>
                            <a title="Go to first unread post" href="<spring:url value="/"/>"><img title="Go to first unread post" alt="" src="<spring:url value="/static/images/new_post.png"/>"></a>
                            <strong><a class="topic_title" title="View topic, started <fmt:formatDate value="${topic.createdate.time}" type="both" pattern="yyyy-MM-dd HH:mm:ss"/>" href="<spring:url value="/topics/${topic.id}"/>" id="tid-link-${topic.id}">${topic.subject}</a></strong>
                        </td>
                        <td class="short desc"><a href="<spring:url value="/"/>" class="___hover___member _hoversetup" id="anonymous_element_${status.count}" title="">${topic.rootPost.poster.username}</a>&nbsp;<a title="View Profile" class="__user" href="<spring:url value="/"/>"><img alt="" src="<spring:url value="/static/images/user_popup.png"/>"></a></td>
                        <td class="desc stats">
                            <ul>
                                <li>
                                    ${topic.replies} / ${topic.views}
                                </li>
                            </ul>
                        </td>
                        <td class="desc">
                            <ul class="last_post">
                                <li>
                                    <a title="<spring:message code="message.last_post"/>" href="<spring:url value="/topic/${forum.lastPost.id}"/>">
                                        <c:choose>
                                            <c:when test="${topic.lastPost.edits == 0}">
                                                <fmt:formatDate value="${forum.lastPost.createdate.time}" type="both" pattern="yyyy-MM-dd HH:mm:ss"/>
                                            </c:when>
                                            <c:otherwise>
                                                <c:when test="${not empty forum.lastPost.edit}">
                                                    <fmt:formatDate value="${forum.lastPost.edit.editdate.time}" type="both" pattern="yyyy-MM-dd HH:mm:ss"/>
                                                </c:when>
                                            </c:otherwise>
                                        </c:choose>
                                    </a>
                                </li>
                                <li>
                                    <spring:url value="/posts/${forum.lastPost.topicId}" var="lastPostUrl"/>
                                    <spring:url value="/static/images/user_popup.png" var="user_popup_png"/>
                                    <spring:message code="message.last_post_at" arguments="${lastPosterUrl},${forum.lastPost.poster.username},${user_popup_png}"/>
                                </li>
                            </ul>
                        </td>
                        <td>
                            <a title="<spring:message code="message.last_post"/>" href="<spring:url value="/posts/${forum.lastPost.id}"/>">
                                <img title="<spring:message code="message.last_post"/>" alt="" src="<spring:url value="/static/images/last_post.png"/>"/>
                            </a>
                        </td>
                    </tr>
                    </c:forEach>
                    </c:if>
            </tbody>
            </table>
        </div>
        <div class="topic_controls">
            <jescort:pager page="${topics}" requestUrl="/forums/${forum.id}"/>
            <ul class="topic_buttons">
                <li>
                    <a title="<spring:message code="message.start_new_topic"/>" href="<spring:url value="/forums/${forum.id}/topics/new"/>">
                        <img alt="<spring:message code="message.start_new_topic"/>" src="<spring:url value="/static/images/page_white_add.png"/>">
                        <spring:message code="message.start_new_topic"/>
                    </a>
                </li>
            </ul>
        </div>
        <div class="clear"></div>
    </div>
    <!-- // Content Wrapper -->
    <div class="clear"></div>
</div>
<!-- // Page Body with Sidebar-->