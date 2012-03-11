<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<div id="container">
    <div id="wrapper">
        <div id="page-body">
            <div id="content">
                <form id="userCPForm" action="<spring:url value="/"/>" enctype="multipart/form-data" method="post">
                    <h2 class="maintitle">Your Options</h2>

                    <div class="border row1 rounded-bot clearfix">
                        <ul class="tab_bar no_title">
                            <li>
                                <a title="Settings for Settings" href="http://www.neowin.net/forum/index.php?app=core&amp;module=usercp&amp;tab=core">Settings</a>
                            </li>
                            <li class="active">
                                <a title="Settings for Profile" href="http://www.neowin.net/forum/index.php?app=core&amp;module=usercp&amp;tab=members">Profile</a>
                            </li>
                        </ul>
                        <div class="tab_body usercp_body">
                            <ul id="usercp_menu">
                                <li><a href="<spring:url value="/"/>">Change Profile Information</a></li>
                                <li><a href="<spring:url value="/"/>">Change About Me Page</a></li>
                                <li><a href="<spring:url value="/"/>">Change Signature</a></li>
                                <li class="active">Change Photo</li>
                            </ul>
                            <div id="usercp_content">
                                <fieldset class="row1">
                                    <h3>Your Personal Photo</h3>

                                    <p class="message unspecific">
                                        This section will allow you to specify a avatar to be used in your profile which is viewable by other board members.
                                        <br>All photos must be no larger than 100 kB.<br>Photos must be no bigger than 150 pixels by 150 pixels.
                                    </p>
                                </fieldset>
                                <fieldset class="row2">
                                    <h3>Currently Used Photo</h3>

                                    <div align="center">
                                        <p>
                                            <img width="150" height="150" alt="Your Personal Photo" src="<spring:url value="/"/>"><br>(150 x 150)
                                        </p>

                                        <p><a href="<spring:url value="/"/>">Remove My Photo</a></p>
                                    </div>
                                </fieldset>
                                <fieldset class="row1">
                                    <h3>Change Photo</h3>

                                    <ul>
                                        <li class="field">
                                            <label for="upload_photo">Upload an image from your computer</label>
                                            <input type="file" size="40" value="" id="upload_photo" name="upload_photo">
                                            <p>
                                                <strong>Image Scale On</strong><br>(This will scale the image down for you if it's too big in pixel size)
                                            </p>
                                        </li>
                                    </ul>
                                </fieldset>
                                <fieldset class="submit rounded-bot">
                                    <input type="submit" value="Save Changes" name="submit" class="input_submit">
                                </fieldset>
                            </div>
                        </div>
                    </div>
                </form>
                <div class="clear"></div>
            </div>
        </div>
    </div>
</div>