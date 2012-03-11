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
            <a class="button small" href="<spring:url value="/"/>"><spring:message code="message.forum_rules"/></a></li>
    </ul>
    <div class="clear"></div>
</div>
<!-- Content Wrapper -->
<div id="content">
    <p style="margin-left:0;margin-right:0" class="message">
        <a rel="search" accesskey="4" title="Advanced Search" href="http://www.neowin.net/forum/index.php?app=core&amp;module=search" class="fright"><strong>Advanced Search</strong></a> Your search for the term
        <em><strong>nite</strong></em> returned <strong>1000</strong> results </p>

    <div class="topic_controls">
        <ul class="pagination left">
            <li class="pagejump clickable pj0477967001" id="anonymous_element_1">
                40 Pages <img alt="+" src="search_topic_results_files/dropdown.png">
            </li>
            <li class="active">1</li>
            <li>
                <a title="2" href="http://www.neowin.net/forum/index.php?app=core&amp;module=search&amp;do=search&amp;andor_type=&amp;sid=284abdbf22d0d502efdc861d42cd47d8&amp;search_term=nite&amp;search_app=forums&amp;st=25">2</a>
            </li>
            <li>
                <a title="3" href="http://www.neowin.net/forum/index.php?app=core&amp;module=search&amp;do=search&amp;andor_type=&amp;sid=284abdbf22d0d502efdc861d42cd47d8&amp;search_term=nite&amp;search_app=forums&amp;st=50">3</a>
            </li>
            <li class="next">
                <a rel="next" title="Next page" href="http://www.neowin.net/forum/index.php?app=core&amp;module=search&amp;do=search&amp;andor_type=&amp;sid=284abdbf22d0d502efdc861d42cd47d8&amp;search_term=nite&amp;search_app=forums&amp;st=25">→</a>
            </li>
            <li class="last">
                <a rel="last" title="Go to last page" href="http://www.neowin.net/forum/index.php?app=core&amp;module=search&amp;do=search&amp;andor_type=&amp;sid=284abdbf22d0d502efdc861d42cd47d8&amp;search_term=nite&amp;search_app=forums&amp;st=975">Last »</a>
            </li>
        </ul>
        <div class="tab_filters">
            <ul class="right">
                <form method="post" action="http://www.neowin.net/forum/index.php?app=core&amp;module=search&amp;do=search&amp;andor_type=&amp;sid=284abdbf22d0d502efdc861d42cd47d8&amp;search_term=nite&amp;search_app=forums">
                    <label for="search_sort_by">Result <strong>sorting</strong></label>
                    <select style="width:auto" class="input_select" id="search_sort_by" name="search_app_filters[forums][sortKey]">
                        <option value="date" selected="selected">Last Update Time</option>
                        <option value="title">Title</option>
                        <option value="posts">Replies</option>
                        <option value="views">Views</option>
                    </select>
                    <select style="width:auto" class="input_select" id="search_sort_order" name="search_app_filters[forums][sortDir]">
                        <option selected="selected" value="0">Descending (z-a)</option>
                        <option value="1">Ascending (a-z)</option>
                    </select> <input type="submit" value="Update" class="input_submit alt">
                </form>
            </ul>
        </div>
    </div>
    <h2 class="category bar dark rounded-top">Search Results</h2>

    <table id="forum_table" class="ipb_table topic_list">
        <tbody>
        <tr class="header">
            <th class="col_f_icon" scope="col">&nbsp;</th>
            <th class="col_f_topic" scope="col">Topic</th>
            <th class="col_f_starter" scope="col">Forum</th>
            <th class="col_f_starter short" scope="col">Started By</th>
            <th class="col_f_views stats" scope="col">Stats</th>
            <th class="col_f_post" scope="col">Post Information</th>
        </tr>
        <tr id="trow_1013368" class="__topic row1">
            <td class="short altrow"><img alt="New Replies" src="search_topic_results_files/t_unread.png"></td>
            <td>
                <span class="topic_preview right">
                    <a class="__tpopup" title="View topic preview" style="display: none; opacity: 0.3;" id="tidPop_1013368" href="#">
                        <img alt="" src="search_topic_results_files/topicpreview.png"> </a>
                </span>
                <a title="Go to first unread post" href="/topics/1013368"><img title="Go to first unread post" alt="" src="search_topic_results_files/new_post.png"></a>
                <strong>
                    <a title="1 attachment(s): View" href="/forum/1013368"><img alt="Attachments" src="search_topic_results_files/attachicon.gif"></a>
                    <a title="View result" href="/topics/1013368">A Group of Cats is Called a ‘Clowder’ and other Cat factoids</a>
                </strong>
            </td>
            <td class="altrow">
                <a href="/forums/7">General Discussion</a>
            </td>
            <td class="short">
                <a href="/users/243659/" class="___hover___member _hoversetup" hovercard-id="243659" hovercard-ref="member" id="anonymous_element_3" title="">jnelsoninjax</a>&nbsp;
                <a title="View Profile" class="__user __id243659" href="http://www.neowin.net/forum/user/243659-jnelsoninjax/"><img alt="" src="search_topic_results_files/user_popup.png"></a>
            </td>
            <td class="stats altrow">
                <ul>
                    <li>13 Replies</li>
                    <li class="views desc">527 Views</li>
                </ul>
            </td>
            <td>
                <ul class="last_post">
                    <li>
                        <a title="Go to last post" href="/topic/1013368"><img title="" alt="" src="search_topic_results_files/last_post.png"></a>
                        <a title="Go to last post" href="/topic/1013368">Jul 21 2011 07:53</a>
                    </li>
                    <li>By:
                        <a href="/users/353896" class="___hover___member _hoversetup" hovercard-id="353896" hovercard-ref="member" id="anonymous_element_4" title="">Albert Bonici</a>&nbsp;<a title="View Profile" class="__user __id353896" href="http://www.neowin.net/forum/user/353896-albert-bonici/"><img alt="" src="search_topic_results_files/user_popup.png"></a>
                    </li>
                </ul>
            </td>
        </tr>
        </tbody>
    </table>
    <br>

    <div class="topic_controls">
        <ul class="pagination left">
            <li class="pagejump clickable" id="anonymous_element_2">
                40 Pages <img alt="+" src="search_topic_results_files/dropdown.png">
            </li>
            <li class="active">1</li>
            <li>
                <a title="2" href="/">2</a>
            </li>
            <li>
                <a title="3" href="/">3</a>
            </li>
            <li class="next">
                <a rel="next" title="Next page" href="/25">-></a>
            </li>
            <li class="last">
                <a rel="last" title="Go to last page" href="/forums/32">Last</a>
            </li>
        </ul>
    </div>
    <!-- // Content Wrapper -->
    <div class="clear"></div>
</div>
<!-- // Page Body-->