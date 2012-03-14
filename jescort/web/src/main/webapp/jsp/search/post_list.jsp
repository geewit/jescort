<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ taglib prefix="jescort" uri="http://www.jescort.net/tags" %>

<div id="breadcrumb">
    <ul class="fleft">
        <li class="first" style="font-weight:bold"><a href="<spring:url value="/"/>">Jescort Forums</a></li>
        <li><b>${topic.subject}</b></li>
    </ul>
    <ul id="breadcrumb-links" class="fright">
        <li style="padding-right:0">
            <a class="button small" href="<spring:url value="/"/>"><spring:message code="message.forum_rules"/></a></li>
    </ul>
    <div class="clear"></div>
</div>
<!-- Container -->
<div id="container">
    <!-- Wrapper -->
    <div id="wrapper">
        <!-- Page Body-->
        <div id="page-body">
            <!-- Content Wrapper -->
            <div id="content">
                <p class="message" style="margin-left:0;margin-right:0">
                    <a class="fright" href="http://www.jescort.net/forum/index.php?app=core&amp;module=search" title="Advanced Search" accesskey="4" rel="search"><strong>Advanced Search</strong></a> Your search for the term
                    <em><strong>nlite</strong></em> returned <strong>1000</strong> results </p>

                <div class="topic_controls">
                    <ul class="pagination left">
                        <li id="anonymous_element_1" class="pagejump clickable pj0400895001">
                            40 Pages
                            <img src="/images/dropdown.png" alt="+">
                        </li>
                        <li class="active">1</li>
                        <li>
                            <a href="/" title="2">2</a>
                        </li>
                        <li>
                            <a href="/" title="3">3</a>
                        </li>
                        <li class="next">
                            <a href="/" title="Next page" rel="next">-></a>
                        </li>
                        <li class="last">
                            <a href="/" title="Go to last page" rel="last">Last »</a>
                        </li>
                    </ul>
                    <div class="tab_filters">
                        <ul class="right">
                            <form action="/search" method="post">
                                <label for="search_sort_by">Result <strong>sorting</strong></label>
                                <select name="search_app_filters[forums][sortKey]" id="search_sort_by" class="input_select" style="width:auto">
                                    <option value="date" selected="selected">Last Update Time</option>
                                    <option value="title">Title</option>
                                    <option value="posts">Replies</option>
                                    <option value="views">Views</option>
                                </select>
                                <select name="search_app_filters[forums][sortDir]" id="search_sort_order" class="input_select" style="width:auto">
                                    <option value="0" selected="selected">Descending (z-a)</option>
                                    <option value="1">Ascending (a-z)</option>
                                </select> <input type="submit" class="input_submit alt" value="Update">
                            </form>
                        </ul>
                    </div>
                </div>
                <h2 class="category bar dark rounded-top">Search Results</h2>
                <!--Begin Msg Number 594167632-->
                <div class="maintitle links">
                    <a href="http://www.jescort.net/forum/topic/1012160-video-for-windows/page__view__getnewpost" title="Go to first unread post"><img src="static/images/new_post.png" alt="" title="Go to first unread post"></a>
                    <a href="http://www.jescort.net/forum/forum/118-help-discussion-center/">Help &amp; Discussion Center</a> &gt;
                    <a href="http://www.jescort.net/forum/forum/12-software-discussion-support/">Software Discussion &amp; Support</a> &gt;
                    <a href="http://www.jescort.net/forum/topic/1012160-video-for-windows/">Video For Windows</a>
                </div>
                <div class="post_block hentry clear" id="post_id_594167632">
                    <a id="entry594167632"></a>

                    <div class="post_wrap">
                        <h3>
                            <span class="post_id"><a href="http://www.jescort.net/forum/index.php?showtopic=1012160&amp;view=findpost&amp;p=594167632" rel="bookmark" title="Link to post #594167632">#594167632</a></span>
                            <img src="http://www.jescort.net/forum/public/style_images/atlas31/user_off.png" alt="User is offline" title="User is offline"> &nbsp;
                            <span class="author vcard"><a class="url fn" href="http://www.jescort.net/forum/user/12942-docthor4/">DocThor4</a>&nbsp;<a href="http://www.jescort.net/forum/user/12942-docthor4/" class="__user __id12942" title="View Profile"><img src="http://www.jescort.net/forum/public/style_images/atlas31/user_popup.png" alt=""></a></span>
                        </h3>
                        <div class="author_info">
                            <ul class="user_details" style="margin-bottom:0">
                                <li class="avatar">
                                    <a href="http://www.jescort.net/forum/user/12942-docthor4/" title="View Profile"><img height="66" width="80" src="http://www.jescort.net/forum/uploads/av-12942.jpg" alt=""></a>
                                </li>
                                <li class="title">Jescortian</li>
                                <li class="group_icon">
                                    <img src="http://www.jescort.net/forum/public/style_extra/team_icons/1.png" alt="">
                                    <img src="http://www.jescort.net/forum/public/style_extra/team_icons/badge-member.png" alt=""><br>
                                </li>
                            </ul>
                            <ul class="user_fields">
                                <li>
                                    <span class="ft">Posts:</span>
                                    <span class="fc">55</span>
                                </li>
                                <li>
                                    <span class="ft">Joined:</span>
                                    <span class="fc">25-May 02</span>
                                </li>
                            </ul>
                        </div>
                        <div class="post_body">
                            <p class="posted_info">
                                Posted
                                <abbr class="published" title="2011-07-17T23:12:52+00:00">18 July 2011 - 07:12</abbr>
                            </p>

                            <div class="post entry-content search">
                                <p class="citation">
                                    <a class="snapback" rel="citation" href="http://www.jescort.net/forum/index.php?app=forums&amp;module=forums&amp;section=findpost&amp;pid=594167612"><img src="http://www.jescort.net/forum/public/style_images/atlas31/snapback.png" alt="View Post"></a>roadwarrior, on 18 July 2011 - 07:07, said:
                                </p>

                                <div class="blockquote">
                                    <div class="quote">
                                        No offence intended, but your English is so terrible that I don't think that ANYONE here knows what the hell you are talking about, even those of us who know quite a bit about video transcoding. If you are using some type of translation service, I'd suggest finding a different one, because most of what you have typed here makes no sense whatsoever.<br>
                                        <br><br>

                                        <span class="searchlite">nLite</span>
                                        has absolutely nothing to do with Linux.
                                        <img src="http://www.jescort.net/forum/public/style_emoticons/default/rolleyes.gif" class="bbc_emoticon" alt=":rolleyes:"><br>
                                    </div>
                                </div>
                                <br><br>Sorry my bad english, I have to find out.<br>
                            </div>
                            <div class="signature">
                                <img src="http://i242.photobucket.com/albums/ff95/dicksoriano/Framechar.jpg" alt="Posted Image" class="bbc_img"><br> Standalone
                            </div>
                        </div>
                        <ul class="post_controls"></ul>
                    </div>
                </div>
                <div class="topic_controls">
                    <ul class="pagination left">
                        <li id="anonymous_element_2" class="pagejump clickable pj0400895001">
                            40 Pages
                            <img src="http://www.jescort.net/forum/public/style_images/atlas31/dropdown.png" alt="+">
                        </li>
                        <li class="active">1</li>
                        <li>
                            <a href="http://www.jescort.net/forum/index.php?app=core&amp;module=search&amp;do=search&amp;andor_type=&amp;sid=32637485ce86d2402b951916b4112d7e&amp;search_term=nlite&amp;search_app=forums&amp;st=25" title="2">2</a>
                        </li>
                        <li>
                            <a href="http://www.jescort.net/forum/index.php?app=core&amp;module=search&amp;do=search&amp;andor_type=&amp;sid=32637485ce86d2402b951916b4112d7e&amp;search_term=nlite&amp;search_app=forums&amp;st=50" title="3">3</a>
                        </li>
                        <li class="next">
                            <a href="http://www.jescort.net/forum/index.php?app=core&amp;module=search&amp;do=search&amp;andor_type=&amp;sid=32637485ce86d2402b951916b4112d7e&amp;search_term=nlite&amp;search_app=forums&amp;st=25" title="Next page" rel="next">-></a>
                        </li>
                        <li class="last">
                            <a href="http://www.jescort.net/forum/index.php?app=core&amp;module=search&amp;do=search&amp;andor_type=&amp;sid=32637485ce86d2402b951916b4112d7e&amp;search_term=nlite&amp;search_app=forums&amp;st=975" title="Go to last page" rel="last">Last »</a>
                        </li>
                    </ul>
                </div>
                <!-- // Content Wrapper -->
                <div class="clear"></div>
            </div>
            <!-- // Page Body with Sidebar-->
        </div>
        <!-- // Wrapper -->
    </div>
    <!-- // Container -->
</div>