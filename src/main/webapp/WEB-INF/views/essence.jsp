<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head><c:import url="fragments/htmlHeadTags.jsp"/>
    <script src='<c:url value="/web_resources/js/custom/initMessageNotification.js"/>'></script>
</head>
<body>
<c:set var="currentUserRole" value="${fn:toLowerCase(requestScope.user.role)}"/>
<div class="container" data-context-path="${pageContext.request.contextPath}/${currentUserRole}">
    <c:import url="fragments/bodyHeader.jsp"/>
    <div class="row c-row">
        <div class="col">
            <div>
                <div class="row">
                    <h5 class="col-3 offset-3">Nickname</h5>
                    <div class="col-3"><c:out value="${foundEssence.nickname}"/></div>
                </div>
                <div class="row">
                    <h5 class="col-3 offset-3">Name</h5>
                    <div class="col-3"><c:out value="${foundEssence.name}"/></div>
                </div>
                <div class="row">
                    <h5 class="col-3 offset-3">Surname</h5>
                    <div class="col-3"><c:out value="${foundEssence.surname}"/></div>
                </div>
                <div class="row">
                    <h5 class="col-3 offset-3">Email</h5>
                    <div class="col-3"><c:out value="${foundEssence.email}"/></div>
                </div>
                <div class="row">
                    <h5 class="col-3 offset-3">Role</h5>
                    <div class="col-3"><c:out value="${foundEssence.role}"/></div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
