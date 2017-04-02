<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="content_div2">
    <div class="titlebox">Select activity</div>
    <p class="info_p">
        <select id="activity_select" name="activity_select">
            <c:forEach var="activity" items="${activities}">
                <option value="${activity.id}">${activity.name}</option>
            </c:forEach>
        </select>
    </p>
</div>