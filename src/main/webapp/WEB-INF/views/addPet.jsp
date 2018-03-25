<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<html>
<head><c:import url="fragments/htmlHeadTags.jsp"/></head>
<body>
<div class="container">
    <c:import url="fragments/bodyHeader.jsp"/>
    <div class="row">
        <div class="col-4 offset-4">
            <h2>Add your pet</h2>
        </div>
    </div>
    <div class="row">
        <sf:form class="col" modelAttribute="pet" action="${pageContext.request.contextPath}${requestScope['javax.servlet.forward.servlet_path']}" method="post">
            <div class="row">
                <div class="col-3"><label>Name
                    <input type="text" name="name" placeholder="name" required></label></div>
                <div class="col-5"><sf:errors path="name" cssClass="error"/></div>
                <div class="col-4"><label>Weight
                    <input type="text" name="weight" placeholder="3.14"></label></div>
            </div>
            <div class="row">
                <div class="col-3"><label>Birthday<br/>
                    <input type="datetime-local" name="birthday"></label></div>
                <div class="col-5"><br/><sf:errors path="genusPet" cssClass="error"/></div>
                <div class="col-4">
                    <label>Genus Pet<br/>
                        <select name="genusPet" required>
                            <option disabled selected value>-> select an option <-</option>
                            <c:set var="genusPet" value="${requestScope.genusPet}"/>
                            <c:forEach items="${genusPet}" var="genusPet" varStatus="status">
                                <option><c:out value="${genusPet.name}"/></option>
                            </c:forEach>
                        </select>
                    </label>
                </div>
            </div>
            <div class="row">
                <div class="col-6">
                    <c:choose>
                        <c:when test="${not empty requestScope.genusPetName}">
                            <div class="row">
                                <div class="col-10 bg-danger">
                                    <h4>${requestScope.genusPetName}</h4>
                                </div>
                            </div>
                        </c:when>
                        <c:when test="${not empty petIsAdded and not petIsAdded}">
                            <div class="row">
                                <div class="col-10 bg-danger">
                                    <h4>Pet already exists</h4>
                                </div>
                            </div>
                        </c:when>
                        <c:when test="${not empty petIsAdded and petIsAdded}">
                            <div class="row">
                                <div class="col-10 bg-success">
                                    <h4>Pet added</h4>
                                </div>
                            </div>
                        </c:when>
                    </c:choose>
                </div>
                <div class="col-2 offset-2"><input class="btn btn-lg btn-primary btn-block" type="submit"></div>
            </div>
        </sf:form>
    </div>
</div>
</body>
</html>
