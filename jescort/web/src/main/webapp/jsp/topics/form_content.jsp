<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

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
                <a itemprop="url" title="Return to ${forum.subject}" href="<spring:url value="/forums/{forum.id}"/>">
                    <span itemprop="title">${forum.subject}</span>
                </a>
            </li>
        </ol>
    </div>
    <br/>
    <h1 class="ipsType_pagetitle">Posting a New Topic in ${forum.subject}</h1>
    <br/>
    <form enctype="multipart/form-data" method="post" action="<spring:url value="/forums/${forum.id}/new"/>" id="postingform">
        <div class="ipsBox ipsForm_vertical ipsLayout ipsPostForm clearfix">
            <div class="ipsBox_container ipsLayout_content">
                <ul class="ipsForm ipsForm_vertical ipsPad">
                    <li class="ipsField ipsField_primary">
                        <label class="ipsField_title" for="subject">Topic subject</label>
                        <p class="ipsField_content">
                            <input id="subject" name="subject" type="text" tabindex="0" value="${topic.subject}" maxlength="80" size="65" class="input_text"/>
                        </p>
                    </li>
                    <li class="ipsField ipsField_editor">
                        <textarea class="ipsEditor_textarea input_text" name="content" id="content">${topic.rootPost.content}</textarea>
                    </li>
                </ul>
                <fieldset class="attachments">
                    <div class="ipsPad">
                        <div style="display:none" class="message error" id="attach_error_box"></div>
                        <input id="attachs" name="attachments[]" type="file" tabindex="1" class="input_upload"/>
                        <ul id="attachments">
                            <li style="display: none"/>
                        </ul>
                        <div class="attach_controls">
                            <h2 class="ipsType_subtitle">Attach Files</h2>
                            <span id="space_info_attach_0" class="desc ipsType_small">
                                Used <strong>0bytes</strong> of your <strong>2MB</strong>
                                global upload quota (Max. single file size: <strong>2MB</strong>)
                            </span>
                        </div>
                    </div>
                </fieldset>
            </div>
        </div>
        <fieldset class="submit clear">
            <input id="submit" name="submit" type="submit" accesskey="s" class="input_submit" tabindex="0" value="Post New Topic" name="submit"/>
            or <a tabindex="0" class="cancel" title="Cancel" href="<spring:url value="/forums/${forum.id}"/>">Cancel</a>
        </fieldset>
    </form>
    <ol class="breadcrumb bottom ipsList_inline clearfix clear">
        <li>
            <a href="/">Jescort Forums</a>
        </li>
        <li>
            <span class="nav_sep">-></span>
            <a title="Return to ${forum.subject}" href="<spring:url value="/forums/${forum.id}"/>">${forum.subject}</a>
        </li>
    </ol>
    <div class="clear"></div>
</div>

