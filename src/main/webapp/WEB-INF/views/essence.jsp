<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head><c:import url="fragments/htmlHeadTags.jsp"/></head>
<body>
<div class="container">
    <div>
        <c:import url="fragments/bodyHeader.jsp"/>
    </div>
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
</body>
</html>
