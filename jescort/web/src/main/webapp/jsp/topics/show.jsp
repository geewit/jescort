<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ taglib prefix="jescort" uri="http://www.jescort.net/tags" %>
<div id="breadcrumb">
    <ul class="fleft">
        <li class="first" style="font-weight:bold"><a href="<spring:url value="/"/>">Neowin Forums</a></li>
        <li><b>${topic.subject}</b></li>
    </ul>
    <ul id="breadcrumb-links" class="fright">
        <li style="padding-right:0">
            <a class="button small" href="<spring:url value="/rules"/>"><spring:message code="message.forum_rules"/></a>
        </li>
    </ul>
    <div class="clear"></div>
</div>
<div id="content">
    <div style="margin-top:10px" class="topic_controls">
        <jescort:pager page="${posts}" requestUrl="/topics/${topic.id}"/>
        <ul class="topic_buttons">
            <c:choose>
            <c:when test="${topic.isLocked}">
                <li class="closed">
                    <span><img alt="" src="<spring:url value="/static/images/lock.png"/>"><spring:message code="message.topic_is_locked"/></span>
                </li>
            </c:when>
            <c:otherwise>
            <shiro:notAuthenticated>
                <li class="disabled"><spring:message code="message.topic_cannot_replied"/></li>
            </shiro:notAuthenticated>
            <shiro:authenticated>
                <li>
                    <a accesskey="r" title="Add Reply" href="<spring:url value="/topics/${topic.id}/reply"/>"><img alt="Add Reply" src="<spring:url value="/static/images/arrow_rotate_clockwise.png"/>"><spring:message code="message.post_reply"/></a>
                </li>
            </shiro:authenticated>
            </c:otherwise></c:choose>
        </ul>
    </div>
    <div class="topic hfeed">
        <h2 class="maintitle">
            <span class="main_topic_title">
                <a href="<spring:url value="/topics/${topic.id}"/>" class="fleft">${topic.subject}</a>
            </span>
        </h2>
        <div class="block-forum rounded-bot">
            <c:if test="${not empty posts.content}"> <c:forEach var="post" items="${posts.content}" varStatus="status">
                <a id="${post.id}"></a>

                <div id="post_id_${post.id}" class="post_block hentry clear with_rep row${status.index % 2 + 1}">
                    <div class="post_wrap">
                        <h3>
                            <div class="post-author">
                                <span class="user-status"></span>
                                &nbsp; <span class="author vcard">
                                <c:set value="${post.poster}" var="poster"/>
                                <a href="<spring:url value="/users/${poster.id}"/>" class="url fn">${poster.username}</a>&nbsp; <a title="View Profile" class="__user" href="<spring:url value="/users/${poster.id}"/>">
                                <img alt="" src="<spring:url value="/static/images/user_popup.png"/>"> </a>
                            </span>
                            </div>
                            <div class="post-top-content">
                                <span style="padding-top: 2px;" class="right"></span>
                            <span class="post_id">
                                <a title="Link to post #${status.count}" href="<spring:url value="/topics/${topic.id}"/>">#${status.count}</a>
                            </span>
                                <fmt:formatDate value="${topic.createdate.time}" type="both" pattern="yyyy-MM-dd HH:mm:ss" var="topic_createdate"/>
                                <spring:message code="message.topic_edited_at" arguments="${topic_createdate}"/>
                                <b>id = ${post.id}</b>
                            </div>
                        </h3>
                        <div class="author_info">
                            <ul style="margin-bottom:0" class="user_details">
                                <li class="avatar">
                                    <a title="View Profile" href="<spring:url value="/users/${poster.id}"/>"><img height="68" width="90" alt="" src="${poster.profile.avatar}"></a>
                                </li>
                                <li class="title">${poster.nickname}</li>
                                <li class="group_icon">
                                    <img alt="" src="<spring:url value="/static/images/badge-${fn:toLowerCase(poster.mainGroup.name)}.png"/>"><br>
                                </li>
                            </ul>
                            <ul class="user_fields">
                                <li>
                                    <span class="ft"><spring:message code="message.author_posts"/>:</span>
                                    <span class="fc"><fmt:formatNumber value="${poster.posts}" pattern="#,###"/></span>
                                </li>
                                <li>
                                    <span class="ft"><spring:message code="message.author_joined"/>:</span>
                                    <span class="fc"><fmt:formatDate value="${poster.createdate.time}" type="both" pattern="yyyy-MM-dd"/></span>
                                </li>
                                <li>
                                    <span class="ft"><spring:message code="message.author_location"/>:</span>
                                    <span class="fc">${poster.mainAddress.location.name}</span>
                                </li>
                            </ul>
                        </div>
                        <div class="post_body">
                            <div class="post entry-content">
                                    ${post.content} <c:if test="${not empty post.attachments}">
                                <jescort:attachments attachments="${post.attachments}"/></c:if>
                                <c:if test="${not empty post.edit}">
                                    <p class="edit">
                                        <c:set value="${post.edit}" var="edit"/>
                                        <c:set value="${edit.editor}" var="editor"/>
                                        <spring:url value="/users/${editor.id}" var="postEditorUrl"/>
                                        <fmt:formatDate value="${edit.editdate.time}" type="both" pattern="yyyy-MM-dd HH:mm" var="postEdittime"/>
                                        <spring:message code="message.post_edit_by_editor" arguments="${postEditorUrl},${editor.username},${postEdittime}"/>
                                    </p>
                                </c:if>
                            </div>
                            <c:if test="${not empty poster.profile.signature}">
                                <div class="signature">${poster.profile.signature}</div>
                            </c:if>
                        </div>
                        <ul class="post_controls">
                            <li class="top">
                                <a title="Back to top" class="top" href="#top">
                                    <img style="margin: -3px;vertical-align:baseline" alt="Top" src="<spring:url value="/static/images/bullet_arrow_up.png"/>">
                                </a>
                            </li>
                            <li>
                                <a title="<spring:message code="message.post_reply"/>" href="<spring:url value="/topics/${topic.id}/reply"/>">
                                    <img alt="<spring:message code="message.post_reply"/>" src="<spring:url value="/static/images/comment_add.png"/>"/><spring:message code="message.post_reply"/>
                                </a>
                            </li>
                            <li style="" id="multiq_${topic.id}" class="multiquote">
                                <a title="<spring:message code="message.post_quote"/>" href="<spring:url value="/topics/${post.id}/quote"/>">
                                    <img alt="<spring:message code="message.post_quote"/>" src="<spring:url value="/static/images/comment_add.png"/>"/><spring:message code="message.post_quote"/>
                                </a>
                            </li>
                        </ul>
                    </div>
                </div>
                <c:if test="${!status.last}">
                    <div class="post-sep"></div>
                </c:if> </c:forEach> </c:if>
        </div>
        <!-- THREADED MODE --><!-- BOTTOM BUTTONS -->
        <div style="margin: 10px 0" class="topic_controls clear">
            <table cellspacing="0" cellpadding="0" border="0" width="100%">
                <tbody>
                <tr>
                    <td align="left" style="width:33%">
                        <jescort:pager page="${posts}" requestUrl="/topics/${topic.id}"/>
                    </td>
                    <td align="center">
                        <a href="<spring:url value="/topics/${topic.id}"/>">Previous Topic</a> |
                        <a href="<spring:url value="/topics/${topic.id}"/>">Next Topic</a>
                    </td>
                    <td align="right" style="width:33%">
                        <ul class="topic_buttons">
                            <c:choose> <c:when test="${topic.isLocked}">
                                <li class="closed">
                                    <span><img alt="" src="<spring:url value="/static/images/lock.png"/>"><spring:message code="message.topic_is_locked"/></span>
                                </li>
                            </c:when> <c:otherwise> <shiro:notAuthenticated>
                                <li class="disabled"><spring:message code="message.topic_cannot_replied"/></li>
                            </shiro:notAuthenticated> <shiro:authenticated>
                                <li>
                                    <a accesskey="r" title="Add Reply" href="<spring:url value="/topics/${topic.id}/reply"/>"><img alt="Add Reply" src="<spring:url value="/static/images/arrow_rotate_clockwise.png"/>">Add Reply</a>
                                </li>
                            </shiro:authenticated> </c:otherwise> </c:choose>
                        </ul>
                    </td>
                </tr>
                </tbody>
            </table>
        </div>
        <div style="visible:hidden;" id="fast_reply">
            <h3 class="maintitle">Fast Reply</h3>

            <div class="general_box alt row1 rounded-bot">
                <form method="post" action="<spring:url value="/post/new"/>">
                    <fieldset>
                        <input type="hidden" name="post.topicId" value="${topic.id}">

                        <div id="editor_fast-reply" class="ips_editor">
                            <div class="editor" id="fast-reply_wrap">
                                <textarea name="post.content" style="height: 70px;" tabindex="0" cols="60" rows="10" id="fast-reply_textarea" class="input_rte unloaded_editor"></textarea>
                            </div>
                        </div>
                    </fieldset>
                    <fieldset style="position:relative" class="submit rounded-bot">
                        <input type="submit" id="submit_post" accesskey="s" tabindex="0" value="Post" class="input_submit" name="submit">
                    </fieldset>
                </form>
            </div>
        </div>
        <div class="moderation_bar rounded" style="background:transparent; padding-left: 0; padding-right: 0;"></div>
    </div>
    <div class="clear"></div>
</div>