<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<div id="breadcrumb">
    <ul class="fleft">
        <li class="first" style="font-weight:bold"><a href="<spring:url value="/"/>">Forums</a></li>
        <li><b><spring:message code="message.forum_rules"/></b></li>
    </ul>
    <ul id="breadcrumb-links" class="fright">
        <li style="padding-right:0">
            <a class="button small" href="<spring:url value="/rules"/>"><spring:message code="message.forum_rules"/></a>
        </li>
    </ul>
    <div class="clear"></div>
</div>
<div id="content">
    <h2 class="maintitle">Help Topics</h2>
    <ol id="help_topics">
        <li class="row1 helpRow">
            <h3><a href="<spring:url value="/"/>" title="Read document">How to Register</a></h3>

            <p> How to register and the added benefits of being a registered member. </p>
        </li>
        <li class="row2 helpRow">
            <h3><a href="<spring:url value="/"/>" title="Read document">Logging In and Out</a></h3>

            <p> How to log in and out from the board, how to remain anonymous and not be shown on the active users list and what to do if you forget your password. </p>
        </li>
        <li class="row1 helpRow">
            <h3><a href="<spring:url value="/"/>" title="Read document">Your Settings</a></h3>

            <p> Editing contact information, personal information, avatars, signatures, board settings, languages and style choices. </p>
        </li>
        <li class="row2 helpRow">
            <h3><a href="<spring:url value="/"/>" title="Read document">Topics and Forums</a></h3>

            <p> A guide to forums, topics, posts and polls. </p>
        </li>
        <li class="row1 helpRow">
            <h3><a href="<spring:url value="/"/>" title="Read document">Posting</a></h3>

            <p> A guide to the features available when posting or sending messages. Including the post editor, polls and attachments. </p>
        </li>
        <li class="row2 helpRow">
            <h3><a href="<spring:url value="/"/>" title="Read document">Personal Messenger</a></h3>

            <p> How to send personal messages, track them, edit your messenger folders and archive stored messages. </p>
        </li>
        <li class="row1 helpRow">
            <h3><a href="<spring:url value="/"/>" title="Read document">Members</a></h3>

            <p> A guide to the member list and member profiles, including profile comments, adding friends and contacting members. </p>
        </li>
        <li class="row2 helpRow">
            <h3><a href="<spring:url value="/"/>" title="Read document">Searching Topics and Posts</a></h3>

            <p> How to use the search feature. </p>
        </li>
        <li class="row1 helpRow">
            <h3><a href="<spring:url value="/"/>" title="Read document">Searching</a></h3>

            <p> How to use the search feature. </p>
        </li>
        <li class="row2 helpRow">
            <h3><a href="<spring:url value="/"/>" title="Read document">Viewing active topics and new posts</a></h3>

            <p> How to view all the topics which have a new reply today and the new posts made since your last visit. </p>
        </li>
        <li class="row1 helpRow">
            <h3><a href="<spring:url value="/"/>" title="Read document">Email Notification of new messages</a></h3>

            <p> How to get emailed when a new reply is added to a topic. </p>
        </li>
        <li class="row2 helpRow">
            <h3><a href="<spring:url value="/"/>" title="Read document">Contacting the staff</a></h3>

            <p> Where to find a list of the board moderators and administrators. </p>
        </li>
        <li class="row1 helpRow">
            <h3><a href="<spring:url value="/"/>" title="Read document">Your Control Panel (My Controls)</a></h3>

            <p> Editing contact information, personal information, avatars, signatures, board settings, languages and style choices. </p>
        </li>
        <li class="row2 helpRow">
            <h3><a href="<spring:url value="/"/>" title="Read document">Calendar</a></h3>

            <p> More information on the boards calendar feature. </p>
        </li>
        <li class="row1 helpRow">
            <h3><a href="<spring:url value="/"/>" title="Read document">Your Personal Messenger</a></h3>

            <p> How to send personal messages, track them, edit your messenger folders and archive stored messages. </p>
        </li>
        <li class="row2 helpRow">
            <h3><a href="<spring:url value="/"/>" title="Read document">My Assistant</a></h3>

            <p> A comprehensive guide to use this handy little feature. </p>
        </li>
        <li class="row1 helpRow">
            <h3><a href="<spring:url value="/"/>" title="Read document">Blogs</a></h3>

            <p> A guide to viewing the community blogs and creating your own blog. </p>
        </li>
        <li class="row2 helpRow">
            <h3><a href="<spring:url value="/"/>" title="Read document">Member List</a></h3>

            <p> Explaining the different ways to sort and search through the list of members. </p>
        </li>
        <li class="row1 helpRow">
            <h3><a href="<spring:url value="/"/>" title="Read document">Registration benefits</a></h3>

            <p> How to register and the added benefits of being a registered member. </p>
        </li>
        <li class="row2 helpRow">
            <h3><a href="<spring:url value="/"/>" title="Read document">Cookies and cookie usage</a></h3>

            <p> The benefits of using cookies and how to remove cookies set by this board. </p>
        </li>
        <li class="row1 helpRow">
            <h3><a href="<spring:url value="/"/>" title="Read document">Recovering lost or forgotten passwords</a></h3>

            <p> How to reset your password if you've forgotten it. </p>
        </li>
        <li class="row2 helpRow">
            <h3><a href="<spring:url value="/"/>" title="Read document">Viewing members profile information</a></h3>

            <p> How to view members contact information. </p>
        </li>
        <li class="row1 helpRow">
            <h3><a href="<spring:url value="/"/>" title="Read document">Topic Options</a></h3>

            <p> A guide to the options avaliable when viewing a topic. </p>
        </li>
        <li class="row2 helpRow">
            <h3>
                <a href="<spring:url value="/"/>" title="Read document">Contacting the moderating team &amp; reporting posts</a>
            </h3>

            <p> Where to find a list of the board moderators and administrators. </p>
        </li>
        <li class="row1 helpRow">
            <h3><a href="<spring:url value="/"/>" title="Read document">Community Blog</a></h3>

            <p> More information about the Community Blog module. </p>
        </li>
        <li class="row2 helpRow">
            <h3><a href="<spring:url value="/"/>" title="Read document">Download Manager</a></h3>

            <p> This is a quick overview of the features in our Download Manager and how to access them. </p>
        </li>
        <li class="row1 helpRow">
            <h3><a href="<spring:url value="/"/>" title="Read document">Using the Gallery</a></h3>

            <p> This is a quick overview of the features in our Gallery and how to access them. </p>
        </li>
    </ol>
    <!-- // Content Wrapper -->
    <div class="clear"></div>
</div>