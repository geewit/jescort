<div id="category_${category.id}" class="category_block block_wrap">
    <h3 class="maintitle">
        <a title="Toggle this category" href="#" class="toggle right">Toggle this category</a>
        <a title="View category" href="<spring:url value="/categories/${category.id}"/>">${category.subject}</a>
    </h3>
    <div class="ipsBox table_wrap">
        <div class="ipsBox_container">
            <table summary="Forums within the category '${category.subject}'" class="ipb_table">
                <tbody>
                <tr class="header hide">
                    <th class="col_c_icon" scope="col">&nbsp;</th>
                    <th class="col_c_forum" scope="col"><spring:message code="messages.forums.show.title"/></th>
                    <th class="col_c_stats stats" scope="col">Stats</th>
                    <th class="col_c_post" scope="col"><spring:message code="message.last_post_info"/></th>
                </tr>
                <c:if test="${not empty category.forums}">
                    <c:forEach var="forum" items="${category.forums}" varStatus="forumStatus">
                        <spring:url value="/posts/${forum.lastPost.topic.id}" var="postUrl"/>
                        <spring:url value="/users/${forum.lastPost.poster.id}" var="posterUrl"/>
                        <spring:url value="/topics/${forum.lastPost.topic.id}" var="topicUrl"/>
                        <tr class="unread">
                            <td class="col_c_icon">
                                <a class="forum_marker" data-tooltip="Mark forum as read?" href="/" id="forum_img_${forum.id}">
                                    <img title="Mark as read" alt="Unread forum" src="<spring:url value="/static/images/f_icon_midnight.png"/>">
                                </a>
                            </td>
                            <td class="col_c_forum">
                                <h4>
                                    <a title="Go to forum" href="<spring:url value="/forums/${forum.id}"/>">${forum.subject}</a>
                                </h4>
                                <p class="desc __forum_desc ipsType_small">${forum.description}</p>
                            </td>
                            <td class="col_c_stats ipsType_small">
                                <ul>
                                    <li><fmt:formatNumber value="${forum.topics}" pattern="#,###" var="topics"/><spring:message code="message.topics_of_forum" arguments="${topics}"/></li>
                                    <li><fmt:formatNumber value="${forum.replies}" pattern="#,###" var="replies"/><spring:message code="message.replies_of_forum" arguments="${replies}"/></li>
                                </ul>
                            </td>
                            <td class="col_c_post">
                                <spring:url value="/posts/${forum.lastPost.id}" var="lastPostUrl"/>
                                <spring:url value="/users/${forum.lastPost.poster.id}" var="lastPosterUrl"/>
                                <a class="ipsUserPhotoLink left" href="${lastPosterUrl}">
                                    <img class="ipsUserPhoto ipsUserPhoto_mini" alt="Photo" src="<spring:url value="/users/${forum.lastPost.poster.id}/avatar"/>">
                                </a>
                                <ul class="last_post ipsType_small">
                                    <li>
                                        <a title="Go to the first unread post: ${forum.lastPost.content}" href="${lastPostUrl}">${jescort:substring(forum.lastPost.content,20)}</a>
                                    </li>
                                    <li><spring:message code="message.last_post_at" arguments="${lastPosterUrl},${forum.lastPost.poster.id},${forum.lastPost.poster.nickname}"/></li>
                                    <li class="desc lighter blend_links"><a title="View last post" href="${lastPostUrl}"><fmt:formatDate value="${forum.lastPost.createdate.time}" type="both" pattern="yyyy-MM-dd HH:mm"/></a></li>
                                </ul>
                            </td>
                        </tr>
                    </c:forEach></c:if>
                </tbody>
            </table>
        </div>
    </div>
</div>