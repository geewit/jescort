<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>


<div id="breadcrumb">
    <ul class="fleft">
        <li class="first">
            <a href="/forum/">Neowin Forums</a>
        </li>
        <li>
            <a title="Return to Help &amp; Discussion Center" href="forum/118">
                Help &amp; Discussion Center
            </a>
        </li>
        <li>
            <b>
                <a title="Return to Hardware Hangout" href="forum/13">
                    Hardware Hangout
                </a>
            </b>
        </li>
    </ul>
    <ul class="fright" id="breadcrumb-links">
        <li style="padding-right:0">
            <a href="/forum/index.php" class="button small"><spring:message code="message.forum_rules"/></a>
        </li>
    </ul>
    <div class="clear"></div>
</div>

<!-- Content Wrapper -->
<div id="content">
    <div class="post_form">
        <!--FORUM RULES-->
        <br />
        <form id="postingform" method="post" action="/forum/index.php" enctype="multipart/form-data">
            <!-- hidden inputs at bottom -->
            <h2 class="maintitle">Posting A New Topic In Hardware Hangout</h2>
            <div class="block-forum rounded-bot">
                <!--SKINNOTE: Not semantic to have an empty list here for styling purposes :P -->
                <fieldset>
                    <h3 class="bar">
                        <strong>Topic</strong>
                    </h3>
                    <ul>
                        <li class="field">
                            <label for="topic_title">Topic Title</label>
                            <br />
                            <input id="topic_title" name="subject" type="text" tabindex="0" value="${topic.subject}" maxlength="80" size="50" class="input_text" />
                            <textarea id="bbcode" name="rootPost.content" type="text">
                                ${topic.rootPost.content}
                            </textarea>
                        </li>
                    </ul>
                </fieldset>
                <fieldset class="attachments">
                    <h3 class="bar">
                        <strong>Attachments</strong>
                    </h3>
                    <!--SKINNOTE: traditional uploader needs this. -->
                    <div style="" class="message error" id="attach_error_box">Error
                        <strong>You aren't permitted to upload this kind of file</strong>
                    </div>
                    <ul class="traditional" id="attachments">

                    </ul>
                    <br />
                    <span id="buttonPlaceholder"></span>
                    <input type="button" tabindex="1" style="clear: both;" value="Attach This File" class="input_submit" id="add_files_attach_0" />
                    <span id="space_info_attach_0" class="desc">Used
                        <strong>0bytes</strong> of your <strong>2MB</strong>
                        global upload quota (Max. single file size:<strong>2MB</strong>)
                    </span>
                    <div style="margin: 15px 15px 0 15px" class="message unspecific">
                        <h4>Help with attaching files</h4>
                        <p id="help_msg" class="desc">
                            <a tabindex="1" title="" href="forum/index.php">
                                Try our advanced uploader which supports multiple file uploading (modern browser required)
                            </a>
                        </p>
                    </div>
                </fieldset>
                <fieldset class="submit">
                    <input type="submit" accesskey="s" class="input_submit" tabindex="0" value="Post New Topic" name="dosubmit"/>
                    &nbsp;
                    <input type="submit" class="input_submit alt" tabindex="0" value="Preview Post" name="preview" />
                </fieldset>
            </div>
        </form>
    </div>
    <div class="clear"></div>
</div>
<!-- // Content Wrapper -->
