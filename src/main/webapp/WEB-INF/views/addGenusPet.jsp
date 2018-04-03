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
                    <h2>Add genus pet</h2>
                </div>
            </div>
            <div class="row">
                <sf:form class="col" modelAttribute="genusPet" action="${pageContext.request.contextPath}${requestScope['javax.servlet.forward.servlet_path']}"
                         method="post">
                    <div class="row">
                        <div class="col-4">
                            <div class="form-group row">
                                <label class="col-4 col-form-label" for="petName">Genus pet</label>
                                <div class="col-8">
                                    <input class="form-control" id="petName" type="text" name="name" placeholder="Input name" required>
                                </div>
                            </div>
                        </div>
                        <div class="col-8">
                            <sf:errors path="name" cssClass="error"/>
                            <c:choose>
                                <c:when test="${not empty requestScope.genusPetIsAdded and not requestScope.genusPetIsAdded}">
                                    <div class="row">
                                        <div class="col-4 offset-4 bg-danger">
                                            <span>Genus pet already exists</span>
                                        </div>
                                    </div>
                                </c:when>
                                <c:when test="${not empty requestScope.genusPetIsAdded and requestScope.genusPetIsAdded}">
                                    <div class="row">
                                        <div class="col-4 offset-4 bg-success">
                                            <span>Genus pet added</span>
                                        </div>
                                    </div>
                                </c:when>
                            </c:choose></div>
                        <div class="col-2 offset-8"><input class="btn btn-lg btn-primary btn-block" type="submit"></div>
                    </div>
                </sf:form>
            </div>
            <div class="row">
                <div class="col">
                    <table class="table table-hover">
                        <caption>Genus pet</caption>
                        <c:forEach items="${requestScope.genusPetSet}" var="genusPet">
                            <tr>
                                <td>
                                    <c:out value="${genusPet.name}"/>
                                </td>
                            </tr>
                        </c:forEach>
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
