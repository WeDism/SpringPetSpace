<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ctg" uri="custom_tags" %>
<html>
<head>
    <c:import url="fragments/htmlHeadTags.jsp"/>
</head>
<body>
<div class="container">
    <c:import url="fragments/bodyHeader.jsp"/>
    <c:set var="user" value="${requestScope.user}"/>
    <c:set var="friends" value="${requestScope.friends}"/>
    <div class="row c-row">
        <div class="col">
            <div class="card-deck">
                <div class="card-deck col-2">
                    <div class="card ">
                        <div class="card-header">Dialogs</div>
                        <div class="card-body">
                        </div>
                    </div>
                </div>
                <div class="card-deck col-10">
                    <div class="col-3">
                        <div class="card">
                            <div class="card-header">Friends contacts</div>
                            <div class="card-body">
                                <ul class="list-unstyled">
                                    <c:forEach items="${friends}" var="userFriend">
                                        <li class="media">
                                            <div class="media-body">
                                                <div class="custom-control custom-checkbox">
                                                    <input class="custom-control-input" type="checkbox" id="${userFriend.role}">
                                                    <label class="custom-control-label" for="${userFriend.role}"> ${userFriend.surname} ${userFriend.name}
                                                        <small>(${userFriend.role})</small>
                                                    </label>
                                                </div>
                                            </div>
                                        </li>
                                    </c:forEach>
                                </ul>
                            </div>
                        </div>
                    </div>
                    <div class="col-9">
                        <div class="card">
                            <div class="card-header">Messages</div>
                            <div class="card-body">
                                <div class="row">
                                    <div class="col">
                    <textarea class="form-control" placeholder="Enter here for write new message" name="text"
                              rows="3"></textarea>
                                    </div>
                                </div>
                                <br/>
                                <div class="row">
                                    <div class="col">
                                        <button type="button" class="btn float-right">Send message</button>
                                    </div>
                                </div>
                                <br/>
                                <ul class="list-unstyled">
                                    <li class="media">
                                        <div class="media-body">
                                            <div class="text-muted float-right">
                                                <small>03.04.2018</small>
                                            </div>
                                            <span class="octicon octicon-microscope"></span>
                                            <strong class="text-success">(ROOT,ADMIN)</strong>
                                            <p>Hi</p>
                                        </div>
                                    </li>
                                    <li class="media">
                                        <div class="media-body">
                                            <div class="text-muted float-right">
                                                <small>03.04.2018</small>
                                            </div>
                                            <strong class="text-success">(ROOT,ADMIN)</strong>
                                            <p>Hello</p>
                                        </div>
                                    </li>
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
