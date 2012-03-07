<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>

<!-- Header Search -->
<div id="search">
    <form id="search-box" action="/search" method="post">
        <label for="main_search" class="hide">Search</label>
        <span id="search_wrap">
            <input type="text" placeholder="Search..." id="main_search" class="" size="17" tabindex="6" name="search_term">
            <span style="" id="search_options" class="choice ipbmenu clickable menu_active">Forums</span>
            <ul id="search_options_menucontent" class="ipbmenu_content ipsPad" style="display: none">
                <li class="title"><strong>Search section:</strong></li>
                <li class="app special">
                    <label for="s_forum" title="This forum">
                        <input type="radio" name="search_app" value="forums:forum:242" class="input_radio" id="s_forum" checked="checked">
                        <strong>This forum</strong>
                    </label>
                </li>
                <li class="app">
                    <label for="s_forums" title="Forums">
                        <input type="radio" name="search_app" class="input_radio" id="s_forums" value="forums">Forums
                    </label>
                </li>
                <li class="app">
                    <label for="s_members" title="Members">
                        <input type="radio" name="search_app" class="input_radio" id="s_members" value="members">Members
                    </label>
                </li>
                <li class="app">
                    <label for="s_core" title="Help Files">
                        <input type="radio" name="search_app" class="input_radio" id="s_core" value="core">Help Files
                    </label>
                </li>
                <li class="advanced">
                    <a href="/search" title="Advanced Search" accesskey="4" rel="search">Advanced Search</a>
                </li>
            </ul>
            <input type="submit" class="submit_input clickable" value="Search">
        </span>
    </form>
</div>
<!-- // Header Search -->