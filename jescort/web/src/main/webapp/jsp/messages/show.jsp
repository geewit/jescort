<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="jescort" uri="http://www.jescort.net/tags" %>

<div id="content">
    <div class="left" id="messenger_utilities">
        <div style="padding-right:10px">
            <div class="general_box rounded" id="space_allowance">
                <h3 class="bar rounded-top"><strong>Storage</strong></h3>
                <p>Your messenger storage</p>
                <p title="Your messenger folders are ${totalElements} percent full" class="progress_bar">
                    <span style="width: ${totalElements}%">${totalElements}%</span>
                </p>
                <p>
                    <span class="desc">${totalElements}% of your quota (100 messages)</span>
                </p>
            </div>
        </div>
    </div>
    <div class="right" id="messenger_content">
        <div id="conversation">
            <div class="topic_controls">
                <ul class="topic_buttons">
                    <li><a title="Go to compose screen" href="<spring:url value="/"/>"><img alt="Compose" src="<spring:url value="/static/images/note_add.png"/>"> Compose New</a></li>
                    <li><a href="<spring:url value="/"/>" id="pm_delete_t_${message.id}"><img alt="Delete" src="<spring:url value="/static/images/delete.png"/>"> Delete</a></li>
                </ul>
            </div>
            <h3 class="maintitle">${message.subject}</h3>
            <div class="block-forum rounded-bot">
                <div id="msg_id_${message.id}" class="post_block first hentry">
                    <div class="post_wrap">
                        <h3>
                            <div class="post-author">
                                <span class="user-status"></span>
                                &nbsp;
                                <span class="author vcard"><a href="<spring:url value="/users/${message.sender.id}"/>" class="url fn">${message.sender.nickname}</a>&nbsp;<a title="View Profile" class="__user" href="<spring:url value="/users/${message.sender.id}"/>"><img alt="" src="./message_files/user_popup.png"></a></span>
                            </div>
                            <div class="post-top-content">
                                <br>
                            </div>
                        </h3>
                        <div class="author_info">
                            <ul style="margin-bottom:0" class="user_details">
                                <li class="avatar"><a title="View Profile" href="<spring:url value="/users/${message.sender.id}"/>"></a></li>
                                <li class="title">Lurker</li>
                                <li class="group_icon">
                                    <img alt="" src="<spring:url value="/static/images/badge-${fn:toLowerCase(message.sender.mainGroup.name)}.png"/>"><br>
                                </li>
                            </ul>
                            <ul class="user_fields">
                                <li>
                                    <span class="ft"><spring:message code="message.author_posts"/>:</span>
                                    <span class="fc">${message.sender.posts}</span>
                                </li>
                                <li>
                                    <span class="ft"><spring:message code="message.author_joined"/>:</span>
                                    <span class="fc"><fmt:formatDate value="${message.sender.createdate.time}" type="both" pattern="yyyy-MM-dd"/></span>
                                </li>
                            </ul>
                        </div>
                        <div class="post_body">
                            <p class="posted_info"><fmt:formatDate value="${message.createdate.time}" type="both" pattern="yyyy-MM-dd HH:mm:ss"/></p>
                            <div class="post entry-content">
                                ${message.content}
                                <c:if test="${not empty message.attachments}"><jescort:attachments attachments="${message.attachments}"/></c:if>
                            </div>
                        </div>
                        <ul class="post_controls">
                            <li>
                                <a title="Reply directly to this post" href="<spring:url value="/"/>"><img alt="Reply Icon" src="<spring:url value="/static/images/comment_add.png"/>">Reply</a>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>
            <br>
            <div class="topic_controls clear">
                <ul class="topic_buttons">
                    <li><a title="Go to compose screen" href="<spring:url value="/"/>"><img alt="Compose" src="<spring:url value="/static/images/note_add.png"/>">Compose New</a></li>
                    <!-- SKINNOTE: Can probably do with some kind of 'are you sure' dialogue here.. -->
                    <li><a href="<spring:url value="/"/>" id="msg_delete_${message.id}"><img alt="Delete" src="<spring:url value="/static/images/delete.png"/>">Delete</a></li>
                </ul>
            </div>
        </div>
    </div>
    <!-- end -->
    <!-- // Content Wrapper -->
    <div class="clear"></div>
</div>