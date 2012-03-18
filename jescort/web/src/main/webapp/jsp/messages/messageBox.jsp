<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="jescort" uri="http://www.jescort.net/tags" %>

<div id="body">
    <div class="clearfix" id="secondary_navigation">
        <ol class="breadcrumb top ipsList_inline">
            <li itemtype="http://data-vocabulary.org/Breadcrumb" itemscope="">
                <a itemprop="url" href="<spring:url value="/"/>">
                    <span itemprop="title">Jescort Forums</span>
                </a>
            </li>
            <li itemtype="http://data-vocabulary.org/Breadcrumb" itemscope="">
                <span class="nav_sep">-></span>
                <a itemprop="url" title="Return toMessenger" href="<spring:url value="/"/>">
                    <span itemprop="title">Messenger</span>
                </a>
            </li>
            <li itemtype="http://data-vocabulary.org/Breadcrumb" itemscope="">
                <span class="nav_sep">-></span>
                <a itemprop="url" title="Return toMy Conversations" href="<spring:url value="/"/>">
                    <span itemprop="title">My Conversations</span>
                </a>
            </li>
        </ol>
    </div>
    <br/>
    <div class="left" id="messenger_utilities">
        <div class="ipsSideBlock" id="folder_list">
            <h3>Folders</h3>
            <ol id="folders">
                <li id="f_new" class="folder protected">
                    <img alt="New" src="<spring:url value="/static/images/folder_page.png"/>"/>
                    <a rel="folder_name" title="Go to folder" href="/">New</a>
                    <span class="total rounded"> 0 </span>
                    <span style="display: none" class="edit_folders">
                        <a title="Empty this folder" class="f_empty" id="empty_new" href="#">
                            <img alt="Empty this folder" src="<spring:url value="/static/images/bin.png"/>"/>
                        </a>
                    </span>
                </li>
                <li id="f_myconvo" class="folder protected">
                    <img alt="My Conversations" src="<spring:url value="/static/images/email_go.png"/>"/>
                    <a rel="folder_name" title="Go to folder" href="">My Conversations</a>
                    <span class="total rounded"> 1 </span>
                    <span style="display: none" class="edit_folders">
                        <a title="Empty this folder" class="f_empty" id="empty_myconvo" href="#">
                            <img alt="Empty this folder" src="<spring:url value="/static/images/bin.png"/>"/>
                        </a>
                    </span>
                </li>
                <li id="f_drafts" class="folder protected">
                    <img alt="Folder" src="<spring:url value="/static/images/folder.png"/>"/>
                    <a rel="folder_name" title="Go to folder" href="<spring:url value="/"/>">Drafts</a>
                    <span class="total rounded"> 0 </span>
                        <span style="display: none" class="edit_folders">
                            <a title="Empty this folder" class="f_empty" id="empty_drafts" href="#">
                                <img alt="Empty this folder" src="<spring:url value="/static/images/bin.png"/>"/>
                            </a>
                        </span>
                </li>
            </ol>
            <div class="clearfix post_controls">
                <ul class="post_controls">
                    <li id="add_folder">
                        <a title="Add Folder" href="#">Add</a>
                    </li>
                    <li id="edit_folders">
                        <a title="Edit Folders" href="#">Edit</a>
                    </li>
                </ul>
            </div>
        </div>
        <div class="ipsSideBlock" id="space_allowance">
            <h3>Storage</h3>
            <p>Your messenger storage</p>
            <p title="Your messenger folders are 0 percent full" class="progress_bar">
                <span style="width: 0%">0%</span>
            </p>
            <p>
                <span class="desc">0% of your quota (250 messages)</span>
            </p>
        </div>
        <div class="ipsSideBlock" id="message_search">
            <h3>Search Messages</h3>
            <form method="post" action="">
                <fieldset>
                    <input type="text" style="width: 60%" size="15" class="input_text" name="searchFor"/>
                    <input type="submit" value="Go" class="input_submit"/>
                </fieldset>
            </form>
        </div>
        <br/>
        <a href="/" class="ipsButton_secondary cancel">Disable Messenger</a>
    </div>
    <div class="right" id="messenger_content">
        <div class="topic_controls">
            <ul class="topic_buttons">
                <li>
                    <a title="Go to compose screen" href="<spring:url value="/"/>">Compose New</a>
                </li>
            </ul>
        </div>
        <div id="message_list">
            <form method="post" id="msgFolderForm" action="<spring:url value="/"/>">
                <div class="maintitle">
                    <span class="right">
                        <input type="checkbox" value="1" id="msg_checkall" class="input_check"/>
                    </span>
                    My Conversations
                </div>
                <div class="ipsBox">
                    <div class="ipsBox_container">
                        <table id="message_table" class="ipb_table">
                            <tbody>
                            <tr class="header hide">
                                <th class="col_m_status" scope="col"/>
                                <th class="col_m_subject" scope="col">Topic</th>
                                <th class="col_m_replies short" scope="col">Replies</th>
                                <th class="col_m_date" scope="col">Last Message</th>
                                <th class="col_mod short" scope="col"/>
                            </tr>
                            <tr class="" id="177542">
                                <td class="col_m_photo altrow short">
                                    <a class="ipsUserPhotoLink" href="/users/407336">
                                        <img class="ipsUserPhoto ipsUserPhoto_mini" src="<spring:url value="/static/images/default_large.png"/>" />
                                    </a>
                                </td>
                                <td class="col_m_subject">
                                    <h4>
                                        <a title="View this conversation" href="/"> Hi,sinosaga </a>
                                    </h4>
                                    <br/>
                                    <span class="desc lighter blend_links">
                                        Started by
                                        <a title="View Profile" href="/users/407336/" class="_hovertrigger url fn" hovercard-id="407336" hovercard-ref="member">isaga</a>
                                        , <span class="desc lighter blend_links"> sent to <a title="View Profile" href="/users/109849" class="_hovertrigger url fn" hovercard-id="109849" hovercard-ref="member">Sinosaga</a>
                                        </span>
                                    </span>
                                </td>
                                <td class="col_m_replies desc blend_links">
                                    <ul>
                                        <li>0 replies</li>
                                    </ul>
                                </td>
                                <td class="col_f_post">
                                    <a class="ipsUserPhotoLink left" href="<spring:url value="/users/"/>">
                                        <img class="ipsUserPhoto ipsUserPhoto_mini" src="<spring:url value="/static/images/default_large.png"/>"/>
                                    </a>
                                    <ul class="last_post ipsType_small">
                                        <li>By <a title="View Profile"  href="/users/407336" class="_hovertrigger url fn" hovercard-id="407336" hovercard-ref="member">isaga</a></li>
                                        <li class="desc">
                                            <a title="Go to last post" href="<spring:url value="/"/>">13 Aug 2011</a>
                                        </li>
                                    </ul>
                                </td>
                                <td class="col_m_mod short">
                                    <input type="checkbox" id="msg_check_177542" name="msgid[177542]" class="input_check msg_check"/>
                                </td>
                            </tr>
                            </tbody>
                        </table>
                    </div>
                </div>
                <div class="moderation_bar rounded with_action right" id="messenger_mod">
                    <select class="input_select" name="method" id="pm_multifile">
                        <optgroup label="With Selected...">
                            <option value="delete">Delete</option>
                            <option value="markread">Mark Read</option>
                            <option value="markunread">Mark Unread</option>
                            <option value="notifyon">Turn On Notifications</option>
                            <option value="notifyoff">Turn Off Notifications</option>
                        </optgroup>
                    </select>
                    <input type="submit" value="Go" id="folder_moderation" class="input_submit alt"/>
                </div>
            </form>
            <div class="left ipsPad_half" id="messenger_filter">
                <form action="<spring:url value="/"/>" method="post">
                    <label class="desc" for="conversation_filter">Show: </label>
                    <select class="input_select" name="folderFilter" id="conversation_filter">
                        <option selected="selected" value="">All Conversations</option>
                        <option value="in">Conversations Others Started</option>
                        <option value="sent">Conversations I Started</option>
                    </select>
                    <input type="submit" value="Update" class="input_submit alt"/>
                </form>
            </div>
        </div>
        <br/>
        <div class="topic_controls clear clearfix"></div>
    </div>
    <br/>
    <ol class="breadcrumb bottom ipsList_inline clearfix clear">
        <li>
            <a href="<spring:url value="/"/>">Jescort Forums</a>
        </li>
        <li>
            <span class="nav_sep">-></span>
            <a title="Return toMessenger" href="<spring:url value="/"/>">Messenger</a>
        </li>
        <li>
            <span class="nav_sep">-></span>
            <a title="Return toMy Conversations" href="<spring:url value="/"/>">My Conversations</a>
        </li>
    </ol>
    <div class="clear"></div>
</div>
