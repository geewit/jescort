<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<ol class="comments">
    <li>
    <span>
        <div class="comment-form" id="reply-form">
            <form action="#">
                <c:if test="${not empty topicId}">
                    <input id="topicId" name="topicId" value="${topicId}" type="hidden"/> </c:if>
                <div id="flatreply">
                    <p class="comment-quote">
                        <input type="button" onclick="javascript:addFlatQuote()" value="Quote original message">
                    </p>

                    <p>
                        <label>Your Reply:</label>
                        <textarea rows="7" cols="22" id="bbcode" name="content" style="width: 100%;" type="text"><c:if test="${not empty post}">${post.content}</c:if></textarea>
                    </p>
                </div>
                <span style="font-weight: normal;">Allowed html: a,b,br,blockquote,i,li,pre,u,ul</span>
                <div id="attachments">
                    <p>File1: <input type="file" name="attachments[]" size="60"/></p>
                </div>
                <p class="comment-post">
                    <span>
                        <button id="submitButton" type="submit">OK</button>
                    </span>
                </p>
                <p></p>
            </form>
        </div>
    </span>
    </li>
</ol>