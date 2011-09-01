<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>


<head>
    <link rel="stylesheet" type="text/css" href="<spring:url value="/static/js/markitup/1.1.10/skins/markitup/style.css"/>" />
    <link rel="stylesheet" type="text/css" href="<spring:url value="/static/js/markitup/plugins/bbcode/style.css"/>" />
    <script type="text/javascript" src="<spring:url value="/static/js/markitup/1.1.10/jquery.markitup.js"/>"></script>
    <script type="text/javascript" src="<spring:url value="/static/js/markitup/plugins/bbcode/set.js"/>"></script>
    <script language="javascript">
    $(document).ready(function()
    {
        $('#bbcode').markItUp(bbcodeSettings);
        $('#emoticons a').click(function()
        {
            emoticon = $(this).attr("title");
            $.markItUp({replaceWith:emoticon});
        });
    });
    </script>
</head>