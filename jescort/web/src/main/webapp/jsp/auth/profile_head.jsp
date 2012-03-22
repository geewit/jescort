<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<link rel="stylesheet" href="<spring:url value="/static/js/ui/1.8.18/base/jquery.ui.all.css"/>">
<script type="text/javascript" src="<spring:url value="/static/js/ajax/LocationSelector.js"/>"></script>
<script type="text/javascript" src="<spring:url value="/static/js/ui/1.8.18/jquery.ui.core.min.js"/>"></script>
<script type="text/javascript" src="<spring:url value="/static/js/ui/1.8.18/jquery.ui.datepicker.min.js"/>"></script>
<script type="text/javascript" src="<spring:url value="/static/js/ui/1.8.18/jquery.ui.datepicker.min.js"/>"></script>
<script type="text/javascript">
    $(function()
    {
        updateLocations("province", "province");
        $("#birthday").datepicker({
            changeMonth: true,
            changeYear: true,
            yearRange: '1920:2011',
            dateFormat : 'yy-mm-dd'
        });
    });
</script>