<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<form id="search-box" method="post" action="<spring:url value="/search"/>">
    <div id="header-search">
        <span id="search_wrap">
            <input type="text" name="search_term" id="main_search" tabindex="1" class="inactive" value="Search...">
            <button type="submit" tabindex="2" rel="search" class="noarrow" id="search-toggle">
                <spring:message code="message.search"/></button>
        </span>
    </div>
</form>