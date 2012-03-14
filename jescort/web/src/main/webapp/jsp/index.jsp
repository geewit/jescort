<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ taglib prefix="jescort" uri="http://www.jescort.net/tags" %>


<div id="body">
    <div class="ipsLayout ipsLayout_largeright clearfix " id="board_index">
        <div class="ipsLayout_content clearfix" id="categories">
            <c:if test="${not empty categories}"><c:forEach var="category" items="${categories}" varStatus="status">
                <%@ include file="categories/include_category.jsp"%>
            </c:forEach></c:if>
        </div>
    </div>
    <div class="clear"></div>
</div>
