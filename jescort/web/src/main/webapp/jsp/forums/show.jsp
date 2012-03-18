<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="jescort" uri="http://www.jescort.net/tags" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>


<div id="body">
    <div id="secondary_navigation" class="clearfix">
        <ol class="breadcrumb top ipsList_inline">
            <li itemscope="" itemtype="http://data-vocabulary.org/Breadcrumb">
                <a href="<spring:url value="/categories/${forum.category.id}"/>" itemprop="url">
                    <span itemprop="title">${forum.category.subject}</span>
                </a>
            </li>
            <li itemscope="" itemtype="http://data-vocabulary.org/Breadcrumb">
                <span class="nav_sep">-></span>
                <a href="<spring:url value="/forums/${forum.id}"/>" title="${forum.subject}" itemprop="url"><span itemprop="title">${forum.subject}</span></a>
            </li>
        </ol>
    </div>
    <div class="topic_controls clearfix">
        <jescort:pager page="${topics}" requestUrl="/forums/${forum.id}"/>
        <ul class="topic_buttons">
            <shiro:notAuthenticated>
            <li class="disabled"><spring:message code="message.cannot_start_new_topic"/></li>
            </shiro:notAuthenticated>
            <shiro:authenticated>
            <li><a href="<spring:url value="/forums/${forum.id}/new"/>" title="<spring:message code="message.start_new_topic"/>" accesskey="s"><spring:message code="message.start_new_topic"/></a></li>
            </shiro:authenticated>
        </ul>
    </div>
    <div class="ipsFilterbar maintitle">
        <ul class="ipsList_inline ipsType_small">
            <li class="active"><a rel="nofollow" href="<spring:url value="/forums/${forum.id}"/>">Recently Updated</a></li>
            <li><a rel="nofollow" href="<spring:url value="/forums/${forum.id}"/>">Start Date</a></li>
            <li><a rel="nofollow" href="<spring:url value="/forums/${forum.id}"/>">Most Replies</a></li>
            <li><a rel="nofollow" href="<spring:url value="/forums/${forum.id}"/>">Most Viewed</a></li>
            <li><a id="forum_filter" href="#forum_filter_menucontent">Custom</a></li>
        </ul>
    </div>
    <div class="ipsBox">
        <div class="ipsBox_container">
            <table id="forum_table" class="ipb_table topic_list hover_rows " summary="${forum.subject}">
                <tbody>
                    <tr class="header hide">
                        <th class="col_f_topic" scope="col"><spring:message code="message.topic"/></th>
                        <th class="col_f_starter short" scope="col"><spring:message code="message.author"/></th>
                        <th class="col_f_views stats" scope="col"><spring:message code="message.topic_replies_and_views"/></th>
                        <th class="col_f_post" scope="col"><spring:message code="message.last_post_info"/></th>
                    </tr>
                <!-- BEGIN TOPICS -->
                <c:if test="${not empty topics.content}"><c:forEach var="topic" items="${topics.content}" varStatus="status">
                    <tr class="__topic<c:if test="${status.index % 2 == 1}"> unread</c:if> expandable" id="trow_${topic.id}" data-tid="${topic.id}">
                    <td class="col_f_icon altrow short">
                        <a href="<spring:url value="/topics/${topic.id}"/>" title="Go to first page">
                            <img src="<spring:url value="/static/images/t_unread.png"/>" alt="New Replies"><br>
                        </a>
                    </td>
                    <td class="col_f_content ">
                        <h4>
                            <a id="tid-link-${topic.id}" href="<spring:url value="/topics/${topic.id}"/>" title="View topic, started <fmt:formatDate value="${topic.createdate.time}" type="both" pattern="yyyy-MM-dd HH:mm:ss"/>" class="topic_title">${topic.subject}</a>
                        </h4>
                        <br>
                        <span class="desc lighter blend_links">
                            Started by <a hovercard-ref="member" hovercard-id="${topic.rootPost.poster.id}" class="_hovertrigger url fn" href="<spring:url value="/user/${topic.rootPost.poster.id}"/>">${topic.rootPost.poster.username}</a>, <fmt:formatDate value="${topic.createdate.time}" type="both" pattern="yyyy-MM-dd"/>
                        </span>
                    </td>
                    <td class="col_f_preview __topic_preview">
                        <a href="<spring:url value="/topics/${topic.id}"/>" class="expander closed" title="Preview this topic">&nbsp;</a>
                    </td>
                    <td class="col_f_views desc blend_links">
                        <ul>
                            <li>${topic.replies}</li>
                            <li class="views desc">${topic.views}</li>
                        </ul>
                    </td>
                    <td class="col_f_post">
                        <a href="<spring:url value="/users/${topic.lastPost.poster.id}"/>" class="ipsUserPhotoLink left">
                            <img src="<jescort:avatar avatar="${topic.lastPost.poster.avatar}"/>" class="ipsUserPhoto ipsUserPhoto_mini">
                        </a>
                        <ul class="last_post ipsType_small">
                            <li><a hovercard-ref="member" hovercard-id="${topic.lastPost.poster.id}" class="_hovertrigger url fn " href="<spring:url value="/user/${topic.lastPost.poster.id}"/>">${topic.lastPost.poster.username}</a></li>
                            <li>
                                <a href="<spring:url value="/topics/${topic.id}"/>" title="Go to last post">
                                    <c:choose>
                                        <c:when test="${topic.lastPost.edits == 0}">
                                            <fmt:formatDate value="${topic.lastPost.createdate.time}" type="both" pattern="yyyy-MM-dd HH:mm:ss"/>
                                        </c:when>
                                        <c:otherwise>
                                            <c:when test="${not empty topic.lastPost.edit}">
                                                <fmt:formatDate value="${topic.lastPost.edit.editdate.time}" type="both" pattern="yyyy-MM-dd HH:mm:ss"/>
                                            </c:when>
                                        </c:otherwise>
                                    </c:choose>
                                </a>
                            </li>
                        </ul>
                    </td>
                </tr>
                </c:forEach></c:if>
                </tbody>
            </table>
        </div>
    </div>
    <br/>
    <div class="topic_controls clearfix">
        <jescort:pager page="${topics}" requestUrl="/forums/${forum.id}"/>
        <ul class="topic_buttons">
            <shiro:notAuthenticated>
            <li class="disabled"><spring:message code="message.cannot_start_new_topic"/></li>
            </shiro:notAuthenticated>
            <shiro:authenticated>
            <li><a href="<spring:url value="/forums/${forum.id}/new"/>" title="<spring:message code="message.start_new_topic"/>" accesskey="s"><spring:message code="message.start_new_topic"/></a></li>
            </shiro:authenticated>
        </ul>
    </div>
    <div id="forum_footer" class="statistics clear clearfix"></div>
    <ol class="breadcrumb bottom ipsList_inline clearfix clear">
        <li><a href="<spring:url value="/categories/${forum.category.id}"/>" itemprop="url">${forum.category.subject}</a></li>
        <li><span class="nav_sep">-></span> <a href="<spring:url value="/forums/${forum.id}"/>" title="Return to ${forum.subject}">${forum.subject}</a></li>
    </ol>
    <div class="clear"></div>
</div>