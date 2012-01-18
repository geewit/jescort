<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="jescort" uri="http://www.jescort.net/tags" %>

<div id="content">
    <a id="j_content"></a>

    <div class="left" id="messenger_utilities">
        <div style="padding-right:10px">
            <!-- Show topic participants -->
            <div class="general_box rounded" id="space_allowance">
                <h3 class="bar rounded-top"><strong>Storage</strong></h3>

                <p>Your messenger storage</p>

                <p title="Your messenger folders are ${messageBox.totalElements} percent full" class="progress_bar">
                    <span style="width: ${messageBox.totalElements}%">${messageBox.totalElements}%</span>
                </p>
                <p>
                    <span class="desc">${messageBox.totalElements}% of your quota (100 messages)</span>
                </p>
            </div>
        </div>
    </div>
    <div class="right" id="messenger_content">
        <div class="topic_controls">
            <jescort:pager page="${messageBox}" requestUrl="/messages"/>
            <ul class="topic_buttons">
                <li>
                    <a title="Go to compose screen" href="<spring:url value="/"/>"><img alt="Compose" src="<spring:url value="/static/images/note_add.png"/>"> Compose New</a>
                </li>
            </ul>
        </div>
        <div id="message_list">
            <form method="post" id="msgFolderForm" action="<spring:url value="/"/>">
                <h3 class="maintitle">New</h3>

                <div class="block-forum rounded-bot">
                    <table id="message_table" class="ipb_table">
                        <tbody>
                        <tr class="header">
                            <th class="col_m_status" scope="col">&nbsp;</th>
                            <th class="col_m_subject" scope="col">Topic</th>
                            <th class="col_m_from short" scope="col">Sender</th>
                            <th class="col_m_date" scope="col">Send Time</th>
                            <th class="col_mod short" scope="col">
                                <input type="checkbox" value="1" id="msg_checkall" class="input_check"></th>
                        </tr>
                        <c:if test="${not empty messageBox.content}">
                            <c:forEach var="message" items="${messageBox.content}" varStatus="status">
                                <tr class="row${status.index % 2 + 1}" id="${message.id}">
                                    <td class="altrow short">
                                        <img alt="*" title="New Messages" src="<c:choose><c:when test="${message.isRead}"><spring:url value="/static/images/pm_nonew.png"/></c:when><c:otherwise><spring:url value="/static/images/pm_new.png"/></c:otherwise></c:choose>">
                                    </td>
                                    <td>
                                        <img alt="" src="<spring:url value="/static/images/attachicon.gif"/>">
                                    <span class="m_title">
                                        <a title="Go to first unread post" href="<spring:url value="/messages/new"/>"><img title="" alt="" src="<spring:url value="/static/images/new_post.png"/>"></a>
                                        <strong>
                                            <a href="<spring:url value="/messages/${message.id}"/>">${message.subject}</a>
                                        </strong>
                                    </span>
                                    </td>
                                    <td class="altrow short">
                                        <a href="<spring:url value="/users/${message.sender.id}"/>">${message.sender.username}</a>&nbsp;<a title="View Profile" class="__user" href="<spring:url value="/users/${message.sender.id}"/>"><img alt="" src="<spring:url value="/static/images/user_popup.png"/>"></a>
                                    </td>
                                    <td>
                                        <ul class="last_post">
                                            <li>
                                                <fmt:formatDate value="${message.createdate.time}" type="both" pattern="yyyy-MM-dd HH:mm:ss"/></li>
                                        </ul>
                                    </td>
                                    <td class="short">
                                        <input type="checkbox" id="msg_check_${message.id}" name="msgid[${message.id}]" class="input_check msg_check">
                                    </td>
                                </tr>
                            </c:forEach> </c:if>
                        </tbody>
                    </table>
                    <div style="margin:0;padding-bottom:0;" class="moderation_bar with_action" id="messenger_mod">
                        <select class="input_select" name="method" id="pm_multifile">
                            <optgroup label="With Selected...">
                                <option value="delete">Delete</option>
                                <option value="markread">Mark Read</option>
                                <option value="markunread">Mark Unread</option>
                                <option value="notifyon">Turn On Notifications</option>
                                <option value="notifyoff">Turn Off Notifications</option>
                            </optgroup>
                        </select> <input type="submit" value="Go" id="folder_moderation" class="input_submit alt">
                    </div>
                </div>
            </form>
        </div>
        <br>

        <div class="topic_controls">
            <jescort:pager page="${messageBox}" requestUrl="/messages"/>
            <ul class="topic_buttons">
                <li>
                    <a title="Go to compose screen" href="<spring:url value="/messages/new"/>"><img alt="Compose" src="<spring:url value="/static/images/note_add.png"/>"> Compose New</a>
                </li>
            </ul>
        </div>
    </div>
    <!-- end --><!-- // Content Wrapper -->
    <div class="clear"></div>
</div>