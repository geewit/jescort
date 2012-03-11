<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<script type="text/javascript" src="<spring:url value="/static/js/ajax/LocationSelector.js"/>"></script>
<script type="text/javascript">
    $(function()
    {
        updateLocations("province");
    });
</script>