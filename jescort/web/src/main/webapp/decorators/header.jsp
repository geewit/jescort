<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!--head begin-->
<div id="header-top">
    <!-- Header Logo -->
    <h1><a href="<spring:url value="/"/>" rel="home" accesskey="1">Gelif.net</a></h1>
    <!-- // Header Logo -->
    <div class="clear"></div>
</div>
<div id="header-bar">
    <jsp:include page="include-search_box.jsp"/>
    <!-- Header User Bits -->
    <div id="header-user">
        <ul class="header-nav">
            <jsp:include page="include-user_navigate.jsp"/>
        </ul>
    </div>
    <!-- // Header User Bits -->
    <div class="clear"></div>
</div>
<!--head end-->