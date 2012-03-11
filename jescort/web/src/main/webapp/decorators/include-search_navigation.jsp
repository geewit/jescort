<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>


<!-- Header Search -->
<div id="search">
    <form id="search-box" method="post" action="<spring:url value="/search"/>">
        <label class="hide" for="main_search">Search</label>
        <span id="search_wrap">
            <input type="text" placeholder="Search..." id="main_search" size="17" tabindex="6" name="search_term" />
            <span id="search_options" class="choice ipbmenu clickable menu_active">Search</span>
            <input type="submit" class="submit_input clickable" value="Search"/>
        </span>
    </form>
</div>
<!-- // Header Search -->