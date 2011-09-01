<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="jescort" uri="http://www.jescort.net/tags" %>

<div id="breadcrumb">
    <ul class="fleft">
        <li class="first" style="font-weight:bold"><a href="<spring:url value="/"/>">Forums</a></li>
        <li><b>${category.subject}</b></li>
    </ul>
    <ul id="breadcrumb-links" class="fright">
        <li style="padding-right:0"><a class="button small" href="<spring:url value="/rules"/>"><spring:message code="message.forum_rules"/></a></li>
    </ul>
    <div class="clear"></div>
</div>
<div id="content">
    <div id="board_index" class="clearfix">
        <div id="categories" class="no_sidebar clearfix">
            <!-- CATS AND FORUMS -->
            <c:if test="${not empty categories}">
            <c:forEach var="category" items="${categories}" varStatus="status">
            <div class="category_block block_wrap">
                <h3 id="category_${category.id}" class="maintitle category">
                    <a href="<spring:url value="/categories/${category.id}"/>" title="View category">${category.subject}</a>
                    <span class="toggle" title="Toggle this category"></span>
                </h3>
                <div class="block-content block-forum rounded-bot">
                    <div class="table_wrap">
                        <table cellspacing="0" class="ipb_table" summary="Forums within the category '${category.subject}'">
                            <tbody>
                                <tr class="header">
                                    <th scope="col" class="col_c_forum" colspan="2"><spring:message code="messages.forums.show.title"/></th>
                                    <th scope="col" class="col_c_stats stats ooga">&nbsp;</th>
                                    <th scope="col" class="col_c_post"><spring:message code="message.last_post_info"/></th>
                                    <th style="width: 1%;"></th>
                                </tr>
                                <!-- / CAT HEADER -->
                                <c:if test="${not empty category.forums}">
                                <c:forEach var="forum" items="${category.forums}" varStatus="forumStatus">
                                <spring:url value="/posts/${forum.lastPost.topicId}" var="postUrl"/>
                                <spring:url value="/users/${forum.lastPost.poster.id}" var="posterUrl"/>
                                <spring:url value="/topics/${forum.lastPost.topicId}" var="topicUrl"/>
                                <tr class="row${forumStatus.index % 2 + 1} forum-row">
                                    <td style="width: 1%; padding-right:0;">
                                        <a class="forum_marker" href="<spring:url value="/forums/${forum.id}"/>">
                                            <img alt="Unread category" src="<spring:url value="/static/images/f_cat_unread.png"/>">
                                        </a>
                                    </td>
                                    <td class="desc">
                                        <h4 style="font-size: 12px; font-weight:bold;"><a href="<spring:url value="/forums/${forum.id}"/>" title="Go to forum">${forum.subject}</a></h4>
                                        <p style="font-size: 11px;">${forum.description}</p>
                                    </td>
                                    <td class="altrow stats desc ooga">
                                        <ul>
                                            <li><fmt:formatNumber value="${forum.topics}" pattern="#,###" var="topics"/><spring:message code="message.topics_of_forum" arguments="${topics}"/></li>
                                            <li><fmt:formatNumber value="${forum.replies}" pattern="#,###" var="replies"/><spring:message code="message.replies_of_forum" arguments="${replies}"/></li>
                                        </ul>
                                    </td>
                                    <td class="desc">
                                        <ul class="last_post">
                                            <spring:url value="/posts/${forum.lastPost.topicId}" var="lastPostUrl"/>
                                            <spring:url value="/users/${forum.lastPost.poster.id}" var="lastPosterUrl"/>
                                            <li><strong><a href="${lastPostUrl}" title="Go to the first unread post: ${forum.lastPost.content}">${jescort:substring(forum.lastPost.content,20)}</a></strong></li>
                                            <li>
                                                <a href="${lastPostUrl}" title="View last post"><fmt:formatDate value="${forum.lastPost.createdate.time}" type="both" pattern="yyyy-MM-dd HH:mm"/></a>&nbsp;
                                                <spring:url value="/static/images/user_popup.png" var="user_popup_png"/>
                                                <spring:message code="message.last_post_at" arguments="${lastPosterUrl},${forum.lastPost.poster.username},${user_popup_png}"/>
                                            </li>
                                        </ul>
                                    </td>
                                    <td><a href="${topicUrl}" title="View last post"><img src="<spring:url value="/static/images/last_post.png"/>" alt="" title="View last post"></a></td>
                                </tr>
                                </c:forEach>
                                </c:if>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
            </c:forEach>
            </c:if>
        </div>
    </div>
    <!-- // Content Wrapper -->
    <div class="clear"></div>
</div>