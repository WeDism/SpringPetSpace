<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<a class="btn btn-lg btn-primary btn-block" href=${pageContext.request.contextPath}/${fn:toLowerCase(sessionScope.user.role)}/add_genus_pet>Add genus pet</a>
