<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<div id="body">
    <div class="clearfix" id="secondary_navigation">
        <ol class="breadcrumb top ipsList_inline">
            <li itemtype="http://data-vocabulary.org/Breadcrumb" itemscope="">
                <a itemprop="url" href="http://www.neowin.net/forum/">
                    <span itemprop="title">Neowin Forums</span>
                </a>
            </li>
            <li itemtype="http://data-vocabulary.org/Breadcrumb" itemscope="">
                <span class="nav_sep">→</span>
                <a itemprop="url" title="Return toYour control panel"
                   href="http://www.neowin.net/forum/index.php?&amp;app=core&amp;module=usercp">
                    <span itemprop="title">Your control panel</span>
                </a>
            </li>
            <li itemtype="http://data-vocabulary.org/Breadcrumb" itemscope="">
                <span class="nav_sep">→</span>
                <a itemprop="url" title="Return toSettings"
                   href="http://www.neowin.net/forum/index.php?&amp;app=core&amp;module=usercp&amp;tab=core">
                    <span itemprop="title">Settings</span>
                </a>
            </li>
            <li itemtype="http://data-vocabulary.org/Breadcrumb" itemscope="">
                <span class="nav_sep">→</span>
                <a itemprop="url" title="Return toManage Attachments"
                   href="http://www.neowin.net/forum/index.php?app=core&amp;module=usercp&amp;tab=core&amp;area=attachments">
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
            <div id="usercp_tabs" class="ipsVerticalTabbed_tabs ipsLayout_left">
                <ul>
                    <li>
                        <a href="http://www.neowin.net/forum/index.php?app=core&amp;module=usercp&amp;tab=core&amp;area=profileinfo">Profile Settings</a>
                    </li>
                    <li>
                        <a href="http://www.neowin.net/forum/index.php?app=core&amp;module=usercp&amp;tab=core&amp;area=email">Email &amp; Password</a>
                    </li>
                    <li>
                        <a href="http://www.neowin.net/forum/index.php?app=core&amp;module=usercp&amp;tab=core&amp;area=displayname">Display Name</a>
                    </li>
                    <li>
                        <a href="http://www.neowin.net/forum/index.php?app=core&amp;module=usercp&amp;tab=core&amp;area=signature">Signature</a>
                    </li>
                    <li class="active">
                        <a href="http://www.neowin.net/forum/index.php?app=core&amp;module=usercp&amp;tab=core&amp;area=attachments">Manage Attachments</a>
                    </li>
                    <li>
                        <a href="http://www.neowin.net/forum/index.php?app=core&amp;module=usercp&amp;tab=core&amp;area=notificationlog">Your Notifications</a>
                    </li>
                </ul>
            </div>

            <div id="usercp_content" class="ipsVerticalTabbed_content ipsLayout_content ipsBox_container">
                <div class="ipsPad">
                    <h3 class="ipsType_subtitle">Manage Attachments</h3>
                    <br/>
                    <div class="row1">
                        <!-- ATTACHMENTS TABLE -->
                        <form method="post" id="checkBoxForm"
                              action="http://www.neowin.net/forum/index.php?app=core&amp;module=usercp&amp;tab=core&amp;area=updateAttachments&amp;do=saveIt">
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
                                        <img alt="Attached File" src="http://www.neowin.net/forum/public/style_extra/mime_types/zip.gif"/>
                                    </td>
                                    <td>
                                        <a title="SlickmapCSS.zip" href="http://www.neowin.net/forum/index.php?app=core&amp;module=attach&amp;section=attach&amp;attach_rel_module=msg&amp;attach_id=296316">SlickmapCSS.zip</a>
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
                                <input type="hidden" value="02f54ca6b28d29fbded6c715ffd3f4f0" name="authKey"/>
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
            <a href="http://www.neowin.net/forum/">Neowin Forums</a>
        </li>
        <li>
            <span class="nav_sep">-></span>
            <a title="Return toYour control panel" href="http://www.neowin.net/forum/index.php?&amp;app=core&amp;module=usercp">Your control panel</a>
        </li>
        <li>
            <span class="nav_sep">-></span>
            <a title="Return toSettings" href="http://www.neowin.net/forum/index.php?&amp;app=core&amp;module=usercp&amp;tab=core">Settings</a>
        </li>
        <li>
            <span class="nav_sep">-></span>
            <a title="Return toManage Attachments" href="http://www.neowin.net/forum/index.php?app=core&amp;module=usercp&amp;tab=core&amp;area=attachments">Manage Attachments</a>
        </li>
    </ol>
    <div class="clear"/>
</div>
