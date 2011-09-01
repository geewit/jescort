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
        <li style="padding-right:0"><a class="button small" href="<spring:url value="/"/>"><spring:message code="message.forum_rules"/></a></li>
    </ul>
    <div class="clear"></div>
</div>
<div id="content">
    <div class="KonaBody">
        <br/>
        <p class="message error">One or all of your search keywords were below 3
         characters or you searched for words which are not allowed, such as
        'html', 'img', etc, please increase the length of these search keywords
        or choose different keywords.</p>
        <br/>
        <form id="search-box" method="post" action="/search">
            <h2 class="maintitle">Search</h2>
            <div class="border rounded-bot">
                <div class="clear row2 altrow" id="main_search_form">
                    <fieldset id="main_search">
                        <ul>
                            <li>
                                <label for="query">Find <strong>words</strong></label>
                                <input type="text" value="windows 7 lite" id="query" name="search_term" class="input_text"><br>
                                <span class="desc">Tip: Search for a specific phrase by "enclosing it in quotes"</span>
                            </li>
                        </ul>
                    </fieldset>
                    <fieldset id="other_filters">
                        <ul>
                            <li>
                                <label><strong>Match</strong></label>
                                <input type="checkbox" value="1" name="content_title_only" class="input_check">&nbsp;&nbsp;Search titles only
                            </li>
                            <li>
                                <label for="author">Find <strong>author</strong></label>
                                <input type="text" id="author" name="search_author" class="input_text input">
                            </li>
                            <li>
                                <label for="date_start">Find by <strong>date</strong></label>
                                <input type="text" id="date_start" name="search_date_start" class="input_text date"><img style="cursor: pointer;" id="date_start_icon" alt="" src="search.php_files/date.png"> &nbsp;
                                <strong>to</strong> &nbsp;<input type="text" id="date_end" name="search_date_end" class="input_text date"><img style="cursor: pointer;" id="date_end_icon" alt="" src="search.php_files/date.png">
                            </li>
                        </ul>
                    </fieldset>
                    <div id="app_filter_forums_wrap" class="extra_filter">
                        <div style="" id="app_filter_forums">
                            <fieldset>
                                <span class="search_msg">
                                    These additional filters can be used to find <strong>discussions</strong>
                                </span>
                                <ul>
                                    <li>
                                        <label for="forums_filter">Find in <strong>forum</strong></label>
                                        <select id="forums_filter" multiple="multiple" size="6" class="input input_select" name="search_app_filters[forums][forums][]">
                                            <option value="213">Neowin Services &amp; Support</option>
                                            <option value="214">&nbsp;&nbsp;|-- Site Announcements</option>
                                            <option value="219">&nbsp;&nbsp;|-- Introduce Yourself</option>
                                            <option value="19">&nbsp;&nbsp;|-- Site &amp; Forum Issues</option>
                                            <option value="129">&nbsp;&nbsp;|---- Confirmed (Open)</option>
                                            <option value="130">&nbsp;&nbsp;|---- Fixed (Closed)</option>
                                            <option value="131">&nbsp;&nbsp;|---- Won't Fix / Non Bugs</option>
                                            <option value="97">&nbsp;&nbsp;|---- Subscriber Support</option>
                                        </select>
                                    </li>
                                    <li>
                                        <label for="forums_type"><strong>Search</strong> in</label>
                                        <select name="search_app_filters[forums][contentOnly]" class="input_select" id="forums_type">
                                            <option value="0" selected="selected">Topic titles &amp; posts</option>
                                            <option value="1">Posts only</option>
                                        </select>
                                    </li>
                                    <li>
                                        <label><strong>Display</strong> results</label>
                                        <input type="radio" value="0" name="search_app_filters[forums][noPreview]"> As posts&nbsp;&nbsp;&nbsp;
                                        <input type="radio" checked="checked" value="1" name="search_app_filters[forums][noPreview]"> As a topic list
                                    </li>
                                    <li>
                                        <label>Topics with <strong>at least</strong></label>
                                        <input type="text" size="5" style="vertical-align: middle; width: 40px;" class="input_text" name="search_app_filters[forums][pCount]" id="f_p_count"> posts and <input type="text" size="5" style="vertical-align: middle; width: 40px;" class="input_text" name="search_app_filters[forums][pViews]" id="f_p_views"> views
                                    </li>
                                </ul>
                            </fieldset>
                            <fieldset>
                                <ul>
                                    <li>
                                        <label for="search_sort_by_forums">Result <strong>sorting</strong></label>
                                        <select style="width:auto" class="input_select" id="search_sort_by_forums" name="search_app_filters[forums][sortKey]">
                                            <option value="date" selected="selected">Last Update Time</option>
                                            <option value="title">Title</option>
                                            <option value="posts">Replies</option>
                                            <option value="views">Views</option>
                                        </select>
                                        <select style="width:auto" class="input_select" id="search_sort_order_forums" name="search_app_filters[forums][sortDir]">
                                            <option selected="selected" value="0">Descending (z-a)</option>
                                            <option value="1">Ascending (a-z)</option>
                                        </select>
                                    </li>
                                </ul>
                            </fieldset>
                        </div>
                    </div>
                </div>
                <fieldset class="submit rounded-bot">
                    <input type="submit" value="Search Now" class="input_submit" name="submit">
                </fieldset>
            </div>
        </form>
    </div>
    <!-- // Content Wrapper -->
    <div class="clear"></div>
</div>
