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
                <span itemprop="title">Avatar Editor</span>
            </li>
        </ol>
    </div>
    <br/>
    <link href="<spring:url value="/static/css/ipb_avatar_editor.css"/>" media="screen" type="text/css" rel="stylesheet"/>
    <form id="photoEditorForm" name="photoEditorForm" action="<spring:url value="/auth/avatar"/>" enctype="multipart/form-data" method="post">
        <h3>Avatar Editor</h3>
        <div class="ipsBox">
            <div class="fixed_inner">
                <fieldset id="ips_photoWrap" class="fixed_inner ipsBox_container">
                    <div id="ips_sidePanel">
                        <div id="ips_currentPhoto">
                            <img class="ipsUserPhoto" src="<jescort:avatar avatar="${avatar}"/>"/>
                        </div>
                    </div>
                    <div id="ips_photoOptions">
                        <ul>
                            <li class="ips_option row2">
                                <div class="ips_photoPreview _custom">
                                    <label>
                                        <img width="100" height="100" src="<jescort:avatar avatar="${avatar}"/>"/>
                                    </label>
                                </div>
                                <div class="ips_photoControls">
                                    <div class="ips_photoOptionText">
                                    <span class="desc">
                                        Once uploaded, you can
                                        <a class="cropperStart" href="#">adjust the cropped image</a>.<br/>Recommend an image 200px or larger
                                    </span>
                                        <br/>
                                        <input id="avatar" name="avatar" type="file" title="Formats: JPEG, PNG, GIF" size="20" value="" class="input_text"/>
                                        <br/>
                                    </div>
                                </div>
                            </li>
                            <li class="ipsPad_half clearfix right">
                                <input name="saveit" type="submit" class="ipsButton ips_photoSubmit" value="Upload"/>
                            </li>
                        </ul>
                    </div>
                </fieldset>
            </div>
        </div>
    </form>
    <ol class="breadcrumb bottom ipsList_inline clearfix clear">
        <li>
            <a href="<spring:url value="/"/>">Jescort Forums</a>
        </li>
        <li><span class="nav_sep">-></span> Avatar Editor</li>
    </ol>
    <div class="clear"></div>
</div>
