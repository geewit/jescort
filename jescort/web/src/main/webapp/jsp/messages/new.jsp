<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<div id="body">
    <div class="clearfix" id="secondary_navigation">
        <ol class="breadcrumb top ipsList_inline">
            <li itemtype="http://data-vocabulary.org/Breadcrumb" itemscope="">
                <a itemprop="url" href="/">
                    <span itemprop="title">Jescort Forums</span>
                </a>
            </li>
            <li itemtype="http://data-vocabulary.org/Breadcrumb" itemscope="">
                <span class="nav_sep">-></span>
                <a itemprop="url" title="Return toMessenger"
                   href="/">
                    <span itemprop="title">Messenger</span>
                </a>
            </li>
            <li itemtype="http://data-vocabulary.org/Breadcrumb" itemscope="">
                <span class="nav_sep">-></span>
                <span itemprop="title">Composing Message</span>
            </li>
        </ol>
    </div>
    <br/>
    <div class="left" id="messenger_utilities">
        <div class="ipsSideBlock" id="folder_list">
            <h3>Folders</h3>
            <ol id="folders">
                <li id="f_new" class="folder protected">
                    <img alt="New" src="/images/folder_page.png"/>

                    <a rel="folder_name" title="Go to folder" href="/new">New</a>
                    <span class="total rounded"> 0 </span>

                    <span style="display: none" class="edit_folders">
                        <a title="Empty this folder" class="f_empty" id="empty_new" href="#">
                            <img alt="Empty this folder" src="/images/bin.png"/>
                        </a>
                    </span>
                </li>
                <li id="f_myconvo" class="folder protected">
                    <img alt="My Conversations" src="/images/email_go.png"/>
                    <a rel="folder_name" title="Go to folder" href="/">My Conversations</a>
                    <span class="total rounded"> 1 </span>
                    <span style="display: none" class="edit_folders">
                        <a title="Empty this folder" class="f_empty" id="empty_myconvo" href="#">
                            <img alt="Empty this folder" src="/images/bin.png"/>
                        </a>
                    </span>
                </li>
                <li id="f_drafts" class="folder protected">
                    <img alt="Folder" src="/images/folder.png"/>
                    <a rel="folder_name" title="Go to folder" href="/">Drafts</a>
                    <span class="total rounded"> 0 </span>
                    <span style="display: none" class="edit_folders">
                        <a title="Empty this folder" class="f_empty" id="empty_drafts" href="#">
                            <img alt="Empty this folder" src="/images/bin.png"/>
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
            <form method="post" action="/search">
                <fieldset>
                    <input type="text" style="width: 60%" size="15" class="input_text"
                           name="searchFor"/>
                    <input type="submit" value="Go" class="input_submit"/>
                </fieldset>
            </form>
        </div>
        <br/>
    </div>
    <div class="right" id="messenger_content">
        <div class="post_form" id="message_compose">
            <form enctype="multipart/form-data" method="post" action="/send" style="display:block" id="msgForm">
                <h2 class="maintitle">Compose New Message</h2>
                <div class="ipsBox">
                    <div class="ipsBox_container">
                        <fieldset>
                            <h3 class="bar">Recipients</h3>
                            <ul>
                                <li class="field">
                                    <label for="entered_name">Recipient's Name</label>
                                    <input type="text" tabindex="0" value="" size="30" name="entered_name" id="entered_name" class="input_text"/>
                                </li>
                                <li class="field">
                                    <label for="more_members">Other Recipients</label>
                                    <input type="text" tabindex="0" id="more_members" value=""
                                           name="inviteUsers" class="input_text" size="50"/>
                                    <span class="desc">You may add up to <strong>5</strong> other recipients</span>
                                </li>
                                <li class="field">
                                    <label for="send_type">Send to others as</label>
                                    <select tabindex="0" id="send_type" name="sendType">
                                        <option value="invite">Invitation</option>
                                        <option value="copy">Copy</option>
                                    </select>
                                    <span class="desc">
                                        <strong>Invitation</strong> allows all invited members to
                                        participate in a single conversation<br/>
                                        <strong>Copy</strong> will send a separate conversation to
                                        each member
                                    </span>
                                </li>
                            </ul>
                        </fieldset>
                        <fieldset>
                            <h3 class="bar">Message</h3>
                            <ul>
                                <li class="field">
                                    <label for="message_subject">Message Subject</label>
                                    <input type="text" value="" maxlength="40" tabindex="0" size="40" class="input_text" id="message_subject" name="msg_title"/>
                                </li>
                                <li>
                                    <textarea id="editor_4f5b3f561ed18" name="Post" class="ipsEditor_textarea input_text"/>
                                </li>
                            </ul>
                        </fieldset>
                        <fieldset class="attachments">
                            <div class="ipsPad">
                                <div id="attach_error_box" style="display:none" class="message error"/>
                                <input id="nojs_attach_0_1" type="file" tabindex="1" name="FILE_UPLOAD" class="input_upload"/>
                                <input id="nojs_attach_0_2" type="file" tabindex="1" name="FILE_UPLOAD" class="input_upload"/>
                                <ul id="attachments">
                                    <li style="display: none"/>
                                </ul>
                                <div class="attach_controls">
                                    <h2 class="ipsType_subtitle">Attach Files</h2>
                                    <span id="buttonPlaceholder"/>
                                    <input type="button" tabindex="-1" style="display: none; clear: both" value="Attach This File" class="ipsType_small ipsButton_secondary attach_button" id="add_files_attach_0"/>
                                    <span id="space_info_attach_0" class="desc ipsType_small">Used
                                            <strong>632.72K</strong> of your <strong>2MB</strong>
                                        global upload quota (Max. single file size:
                                            <strong>1.38MB</strong>)
                                    </span>
                                    <p id="help_msg" class="desc lighter ipsType_smaller">
                                        <a tabindex="-1" title="" data-switch="flash" href="#">Try our advanced uploader (requires Flash 9)</a>
                                    </p>
                                </div>
                            </div>
                        </fieldset>
                        <fieldset class="submit">
                            <input type="submit" accesskey="s" tabindex="0" value="Send Message" name="dosubmit" class="input_submit"/>
                            <input type="submit" name="preview" tabindex="0" value="Preview Message" class="input_submit alt"/>
                            <input type="submit" name="save" tabindex="0" value="Save (Send later)" class="input_submit alt"/> or <a tabindex="0" class="cancel" title="Cancel" href="/">Cancel</a>
                        </fieldset>
                    </div>
                </div>
            </form>
        </div>
        <br/>
    </div>
    <br/>
    <ol class="breadcrumb bottom ipsList_inline clearfix clear">
        <li>
            <a href="/">Jescort Forums</a>
        </li>
        <li>
            <span class="nav_sep">-></span>
            <a title="Return toMessenger" href="/">Messenger</a>
        </li>
        <li><span class="nav_sep">-></span> Composing Message</li>
    </ol>
    <div class="clear"></div>
</div>
