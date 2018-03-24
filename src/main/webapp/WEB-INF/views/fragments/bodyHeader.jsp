<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<div class="row">
    <div class="col-2 offset-4"><h2>Pet space</h2></div>
</div>
<div class="row">
    <div class="col-2">
        <h3><a href="<c:url value="/${fn:toLowerCase(sessionScope.user.role)}/"/>">Home</a></h3>
    </div>
    <div class="col-2 offset-8">
        <h3><a href="<c:url value="/logout"/>">Exit</a></h3>
    </div>
</div>