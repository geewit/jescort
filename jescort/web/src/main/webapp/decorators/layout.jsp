<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
    <title><c:set var="titleKey"><tiles:getAsString name="title" ignore="true"/></c:set><spring:message code="${titleKey}"/></title>
    <link rel="shortcut icon" href="/favicon.ico" type="image/ico">
    <style type="text/css">html, body, input, select, textarea { font-family: 'Segoe UI', Segoe, Helvetica, Arial, FreeSans, sans-serif }</style>

    <link rel="stylesheet" href="<spring:url value="/static/css/pegasus.css"/>" type="text/css" media="screen,print"/>
    <link rel="stylesheet" href="<spring:url value="/static/css/ipb_styles.css"/>" type="text/css" media="screen,print"/>
    <link rel="stylesheet" href="<spring:url value="/static/css/ipb_common.css"/>" type="text/css" media="screen,print"/>
    <link rel="stylesheet" href="<spring:url value="/static/css/calendar_select.css"/>" type="text/css" media="screen,print"/>
    <link rel="stylesheet" href="<spring:url value="/static/css/prettify.css"/>" type="text/css" media="screen,print"/>
    <link rel="stylesheet" href="<spring:url value="/static/css/ipb_auth.css"/>" type="text/css" media="screen,print"/>
    <script type="text/javascript" src="<spring:url value="/static/js/jquery/1.7.1/jquery.js"/>"></script>
    <tiles:insertAttribute name="head"/>
</head>


<body id="gelif_body" class="f-arial s-default style_noshadow">
<div id="fb-root"></div>

<!-- Container -->
<div id="container">
    <!-- Header -->
    <header id="branding">
        <tiles:insertAttribute name="header"/>
    </header>
    <!-- // Header -->
    <!-- Wrapper -->
    <div id="wrapper">
        <!-- Header Navigation -->
        <tiles:insertAttribute name="navigation"/>
        <!-- // Header Navigation -->

        <!-- Header Sub Navigation -->
        <tiles:insertAttribute name="sub_navigation"/>
        <!-- // Header Sub Navigation -->

        <!-- Page Content -->
        <tiles:insertAttribute name="content"/>
        <!-- // Page Content -->
    </div>
    <!-- // Wrapper -->
    <!-- Footer -->
    <footer id="siteinfo" role="contentinfo">
        <tiles:insertAttribute name="footer"/>
    </footer>
    <!-- // Footer -->
</div>
<!-- // Container -->

</body>
</html>