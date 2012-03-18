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
                <a itemprop="url" title="Return toYour control panel" href="/">
                    <span itemprop="title">Your control panel</span>
                </a>
            </li>
            <li itemtype="http://data-vocabulary.org/Breadcrumb" itemscope="">
                <span class="nav_sep">-></span>
                <a itemprop="url" title="Return toSettings" href="/">
                    <span itemprop="title">Settings</span>
                </a>
            </li>
            <li itemtype="http://data-vocabulary.org/Breadcrumb" itemscope="">
                <span class="nav_sep">-></span>
                <a itemprop="url" title="Return toManage Attachments" href="/">
                    <span itemprop="title">Manage Attachments</span>
                </a>
            </li>
        </ol>
    </div>
    <br/>
    <h1 class="ipsType_pagetitle">My Settings</h1>
    <br/>
    <div class="ipsBox">
        <div class="ipsLayout ipsLayout_withleft ipsLayout_smallleft ipsVerticalTabbed clearfix usercp_body">
            <jsp:include page="include-tab.jsp" flush="true"><jsp:param name="tab" value="attachments"/></jsp:include>

            <div id="usercp_content" class="ipsVerticalTabbed_content ipsLayout_content ipsBox_container">
                <div class="ipsPad">
                    <h3 class="ipsType_subtitle">Manage Attachments</h3>
                    <br/>
                    <div class="row1">
                        <!-- ATTACHMENTS TABLE -->
                        <form method="post" id="checkBoxForm"
                              action="/">
                            <table summary="User Attachments" class="ipb_table">
                                <tbody>
                                <tr class="header">
                                    <th style="width: 2%" scope="col"/>
                                    <th style="width: 35%" scope="col">Attachment</th>
                                    <th style="width: 7%" scope="col">Size</th>
                                    <th style="width: 27%" scope="col">Topic</th>
                                    <th style="width: 3%" class="short" scope="col">
                                        <input type="checkbox" value="Check all" id="checkAllAttachments" class="input_check"/>
                                    </th>
                                </tr>
                                <tr class="row1" id="a296316">
                                    <td class="short altrow">
                                        <img alt="Attached File" src="/static/images/zip.gif"/>
                                    </td>
                                    <td>
                                        <a title="SlickmapCSS.zip" href="/">SlickmapCSS.zip</a>
                                        <br/>
                                        <span class="desc">( downloads: 0 )</span>
                                    </td>
                                    <td class="short altrow">9.72K</td>
                                    <td> Personal Message <br/>
                                        <span class="desc">Aug 13 2011 09:45</span>
                                    </td>
                                    <td class="altrow short">
                                        <input type="checkbox" class="input_check checkall" value="1" name="attach[296316]"/>
                                    </td>
                                </tr>
                                </tbody>
                            </table>
                            <div id="topic_mod" class="moderation_bar rounded with_action clear">
                                <input type="submit" class="input_submit alt" value="Delete Selected"/>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <ol class="breadcrumb bottom ipsList_inline clearfix clear">
        <li>
            <a href="/">Jescort Forums</a>
        </li>
        <li>
            <span class="nav_sep">-></span>
            <a title="Return toYour control panel" href="">Your control panel</a>
        </li>
        <li>
            <span class="nav_sep">-></span>
            <a title="Return toSettings" href="">Settings</a>
        </li>
        <li>
            <span class="nav_sep">-></span>
            <a title="Return toManage Attachments" href="">Manage Attachments</a>
        </li>
    </ol>
    <div class="clear"></div>
</div>
