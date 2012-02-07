<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<div id="content">
    <form id="userCPForm" action="<spring:url value="/"/>" method="post">
        <h2 class="maintitle">Your Options</h2>

        <div class="border row1 rounded-bot clearfix">
            <ul class="tab_bar no_title">
                <li><a title="Settings for Settings" href="<spring:url value="/"/>">Settings</a></li>
                <li class="active"><a title="Settings for Profile" href="<spring:url value="/"/>">Profile</a></li>
            </ul>
            <div class="tab_body usercp_body">
                <ul id="usercp_menu">
                    <li class="active">Change Profile Information</li>
                    <li><a href="<spring:url value="/auth/aboutme"/>">Change About Me Page</a></li>
                    <li><a href="<spring:url value="/auth/signature"/>">Change Signature</a></li>
                    <li><a href="<spring:url value="/auth/avatar"/>">Change Photo</a></li>
                </ul>
                <div id="usercp_content">
                    <fieldset class="row1">
                        <h3>Other Information</h3>

                        <ul>
                            <li class="field">
                                <label for="gender">Gender</label>
                                <select name="gender" class="input_select" id="gender">
                                    <option value="Male">Male</option>
                                    <option value="Female">Female</option>
                                    <option value="">Not Telling</option>
                                </select>
                            </li>
                            <li class="field">
                                <label for="qq">QQ</label>
                                <input type="text" value="" name="properties['qq']" class="input_text" size="40" id="qq">
                            </li>
                            <li class="field">
                                <label for="msn">MSN</label>
                                <input type="text" value="" name="properties['msn']" class="input_text" size="40" id="msn">
                            </li>
                            <li class="field">
                                <label for="website">Website</label>
                                <input type="text" value="" name="properties['website']" class="input_text" size="40" id="website">
                            </li>
                            <li class="field">
                                <label>Location</label>
                                <select id="province" name="province" onChange="updateLocations('city', $(this).val());">
                                    <option value="0">--请选择省份--</option>
                                </select>
                                <select id="city" name="city" onChange="updateLocations('district', $(this).val());">
                                    <option value="0">--请选择城市--</option>
                                </select> <select id="district" name="district">
                                <option value="0">--请选择区县--</option>
                            </select>
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