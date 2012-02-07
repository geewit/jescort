<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title><c:set var="titleKey"><tiles:getAsString name="title" ignore="true"/></c:set><spring:message code="${titleKey}"/></title>
    <link href="<spring:url value="/static/css/escort_common.css"/>" type="text/css" rel="stylesheet"/>
    <link href="<spring:url value="/static/css/escort_style.css"/>" type="text/css" rel="stylesheet"/>
    <link href="<spring:url value="/static/css/escort_color.css"/>" type="text/css" rel="stylesheet"/>
    <link rel="icon" type="image/x-icon" href="<spring:url value="/static/images/logo.gif"/>">
    <script type="text/javascript" src="<spring:url value="/static/js/jquery/1.6.4/jquery.min.js"/>"></script>
</head>

<body>
<a name="top"></a>

<div id="container">
    <div id="wrapper">
        <div id="header">
            <tiles:insertAttribute name="header"/>
        </div>
        <div id="page-body">
            <tiles:insertAttribute name="content"/>
        </div>
        <div id="footer">
            <tiles:insertAttribute name="footer"/>
            <div class="clear"></div>
        </div>
    </div>
</div>

</body>
</html>