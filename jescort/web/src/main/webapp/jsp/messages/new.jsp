<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<div id="content">
    <div class="left" id="messenger_utilities">
        <div style="padding-right:10px">
            <div class="general_box rounded" id="space_allowance">
                <h3 class="bar rounded-top"><strong>Storage</strong></h3>
                <p>Your messenger storage</p>
                <p title="Your messenger folders are 1 percent full" class="progress_bar">
                    <span style="width: 1%">1%</span>
                </p>
                <p>
                    <span class="desc">1% of your quota (100 messages)</span>
                </p>
            </div>
        </div>
    </div>
    <div class="right" id="messenger_content">
        <div class="post_form" id="message_compose">
            <form enctype="multipart/form-data" method="post" action="http://www.neowin.net/forum/index.php?app=members&amp;module=messaging&amp;section=send&amp;do=send" style="display:block" id="msgForm">
                <h3 class="maintitle">Compose New Message</h3>
                <div class="block-forum rounded-bot">
                    <fieldset>
                        <h3 class="bar">Recipients</h3>
                        <ul>
                            <li class="field">
                                <label for="entered_name">Recipient's Name</label>
                                <input type="text" autocomplete="off" tabindex="0" value="" size="30" name="entered_name" id="entered_name" class="input_text">
                            </li>
                            <li class="field">
                                <label for="more_members">Other Recipients</label>
                                <input type="text" autocomplete="off" tabindex="0" id="more_members" value="" name="inviteUsers" class="input_text" size="50">
                                <span class="desc">You may add up to <strong>5</strong> other recipients</span>
                            </li>
                        </ul>
                    </fieldset>
                    <fieldset>
                        <h3 class="bar">Message</h3>
                        <ul>
                            <li class="field">
                                <label for="message_subject">Message Subject</label>
                                <input type="text" value="" maxlength="40" tabindex="0" size="40" class="input_text" id="message_subject" name="msg_title">
                            </li>
                            <li>
                                <div class="ips_editor ">
                                    <div class="editor">
                                        <textarea tabindex="0" cols="60" rows="10" class="input_rte" name="Post"></textarea>
                                    </div>
                                </div>
                            </li>
                        </ul>
                    </fieldset>
                    <fieldset class="attachments">
                        <h3 class="bar"><strong>Attachments</strong></h3>
                        <ul class="traditional" id="attachments">
                            <li attachid="cur_296318" style="" class="attach_row complete" id="ali_attach_0_cur_296318">
                                <div>
                                    <h4 class="attach_name">SlickmapCSS.zip</h4>
                                    <p class="info">9.72 KB</p>
                                    <span class="img_holder"></span>
                                    <p style="display: none; " class="progress_bar">
                                        <span style="width: 0%">0%</span>
                                    </p>
                                    <p class="links">
                                        <a attachid="attach_0" fileindex="cur_296318" title="Adds a placeholder tag for the attachment in the post" class="add_to_post" href="http://www.neowin.net/forum/index.php?app=members&amp;module=messaging&amp;section=send&amp;do=form#">Add to Post</a> | <a title="Delete this attachment" class="cancel delete" href="http://www.neowin.net/forum/index.php?app=members&amp;module=messaging&amp;section=send&amp;do=form#">Delete</a>
                                    </p>
                                </div>
                            </li>
                        </ul>
                        <br>
                        <span id="buttonPlaceholder"></span>
                        <input type="button" tabindex="1" style="clear: both; " value="Attach This File" class="input_submit" id="add_files_attach_0">
                        <span id="space_info_attach_0" class="desc">Used <strong>9.72K</strong> of your <strong>2MB</strong> global upload quota (Max. single file size: <strong>1.38MB</strong>)</span>
                        <div style="margin: 15px 15px 0 15px" class="message unspecific">
                            <h4>Help with attaching files</h4>
                            <p id="help_msg" class="desc">
                                <a tabindex="1" title="" href="http://www.neowin.net/forum/index.php?app=core&amp;module=usercp&amp;hl=true">Try our advanced uploader which supports multiple file uploading (modern browser required)</a>
                            </p>
                        </div>
                    </fieldset>
                    <fieldset class="submit">
                        <input type="submit" accesskey="s" tabindex="0" value="Send Message" name="dosubmit" class="input_submit">
                        <input type="submit" name="preview" tabindex="0" value="Preview Message" class="input_submit alt">
                    </fieldset>
                </div>
            </form>
        </div>
        <br>
    </div>
    <!-- // Content Wrapper -->
    <div class="clear"></div>
</div>