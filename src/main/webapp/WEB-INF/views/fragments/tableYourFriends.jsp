<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="ctg" uri="custom_tags" %>
<table class="table table-hover">
    <caption>Your friends</caption>
    <thead>
    <tr>
        <th>Nickname</th>
        <th>Name</th>
        <th>Surname</th>
        <th>Role</th>
        <th>Action</th>
    </tr>
    </thead>
    <c:set var="user" value="${sessionScope.user}"/>
    <tbody data-path-for-state-friend="${pageContext.request.contextPath}${homepage}/${fn:toLowerCase(user.role)}/friend_request">
    <c:forEach items="${user != null ? ctg:getFriends(user) : null}" var="friend">
        <tr data-essence-id="${friend.userEssence.userEssenceId}">
            <td>
                <a href="<c:url value="${homepage}"/>/essence?nickname=<c:out value="${friend.userEssence.nickname}"/>">${friend.userEssence.nickname}</a>
            </td>
            <td><c:out value="${friend.userEssence.name}"/></td>
            <td><c:out value="${friend.userEssence.surname}"/></td>
            <td><c:out value="${friend.userEssence.role}"/></td>
            <td>
                <c:choose>
                    <c:when test="${user.requestedFriendsTo.contains(friend)}">
                        <select class="action-friends" name="action-friends" required>
                            <c:set var="stateFriendSet"
                                   value="<%=com.pet_space.models.essences.StateFriend.StateFriendEnum.values()%>"/>
                            <c:forEach items="${stateFriendSet}" var="stateFriend" varStatus="status">
                                <option value="<c:out value="${stateFriend}"/>"
                                        <c:if test="${friend.state.equals(stateFriend)}">selected</c:if>>
                                    <c:out value="${stateFriend}"/>
                                </option>
                            </c:forEach>
                        </select>
                    </c:when>
                    <c:otherwise>
                        <label><c:out value="${friend.state}"/></label>
                    </c:otherwise>
                </c:choose>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>