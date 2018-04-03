<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<html>
<head><c:import url="fragments/htmlHeadTags.jsp"/></head>
<body>
<div class="container">
    <c:import url="fragments/bodyHeader.jsp"/>
    <div class="row c-row">
        <div class="col">
            <div class="row">
                <div class="col-4 offset-4">
                    <h2>Add your pet</h2>
                </div>
            </div>
            <div class="row">
                <sf:form class="col" modelAttribute="pet" action="${pageContext.request.contextPath}${requestScope['javax.servlet.forward.servlet_path']}" method="post">
                    <div class="row">
                        <div class="col-4">
                            <div class="form-group row">
                                <label class="col-4 col-form-label" for="petName">Name</label>
                                <div class="col-8">
                                    <input class="form-control" type="text" id="petName" name="name" placeholder="name" required>
                                </div>
                            </div>
                        </div>
                        <div class="col-4"><sf:errors path="name" cssClass="error"/></div>
                        <div class="col-4">
                            <div class="form-group row">
                                <label class="col-4 col-form-label" for="petWeight">Weight</label>
                                <div class="col-8">
                                    <input class="form-control" type="text" id="petWeight" name="weight" placeholder="3.14">
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-4">
                            <div class="form-group row">
                                <label class="col-4 col-form-label" for="petBirthday">Birthday</label>
                                <div class="col-8">
                                    <input class="form-control" id="petBirthday" type="datetime-local" name="birthday">
                                </div>
                            </div>
                        </div>
                        <div class="col-4"><br/><sf:errors path="genusPet" cssClass="error"/></div>
                        <div class="col-4">
                            <div class="form-group row">
                                <label class="col-4 col-form-label" for="genusPet">Genus Pet</label>
                                <div class="col-8">
                                    <select class="custom-select" id="genusPet" name="genusPet" required>
                                        <option disabled selected value>-> select an option <-</option>
                                        <c:set var="genusPet" value="${requestScope.genusPet}"/>
                                        <c:forEach items="${genusPet}" var="genusPet" varStatus="status">
                                            <option><c:out value="${genusPet.name}"/></option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
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
    </div>
</div>
</body>
</html>
