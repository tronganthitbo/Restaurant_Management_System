<%-- 
    Document   : list
    Created on : 21 Sep 2025, 10:04:02 AM
    Author     : Dai Minh Nhu - CE190213
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/include/header.jsp" %>

<main>
    <div class="container">

        <h2>Role list</h2>

        <p class="text-end">
            <button class="btn btn-success" onclick="showCreatePopup()">Add New Role</button>
        </p>

        <table class="table table-bordered table-hover text-center">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Name</th>
                    <th>Action</th>
                </tr>
            </thead>
            <tbody>
                <c:choose>
                    <c:when test="${rolesList == null || empty rolesList}">
                        <tr>
                            <td colspan="8" style="color:red;">No data to display</td>
                        </tr>   
                    </c:when>
                    <c:otherwise>
                        <c:forEach var="role" items="${rolesList}" varStatus="loop">
                            <tr>
                                <td><c:out value="${role.role_id}"/></td>
                                <td><c:out value="${role.role_name}"/></td>

                                <td>
                                    <button type="button" class="btn btn-primary" onclick="showEditPopup(${role.role_id}, '<c:out value="${role.role_name}"/>')">
                                        Edit Name
                                    </button>
                                </td>
                            </tr>
                        </c:forEach>
                    </c:otherwise>
                </c:choose>
            </tbody>
        </table>
        <nav aria-label="Page navigation example">
            <ul class="pagination">
                <li class="page-item ${((empty param.page) || param.page <= 1)?"disabled":""}">
                    <a class="page-link" href="<c:url value="/promotion">
                           <c:param name="view" value="list"/>
                           <c:param name="page" value="${param.page - 1}"/>
                       </c:url>" aria-label="Previous">
                        <span aria-hidden="true">&laquo;</span>
                    </a>
                </li>
                <c:forEach begin="1" end="${requestScope.totalPages}" var="i">
                    <li class="page-item ${((empty param.page && i == 1) || param.page == i)?"active":""}">
                        <a class="page-link" href="<c:url value="/promotion">
                               <c:param name="view" value="list"/>
                               <c:param name="page" value="${i}"/>
                           </c:url>">${i}</a></li>
                    </c:forEach>
                <li class="page-item ${(requestScope.totalPages <= param.page || requestScope.totalPages eq 1 )?"disabled":""}">
                    <a class="page-link" href="<c:url value="/promotion">
                           <c:param name="view" value="list"/>
                           <c:param name="page" value="${(empty param.page)?2:param.page + 1}"/>
                       </c:url>" aria-label="Next">
                        <span aria-hidden="true">&raquo;</span>
                    </a>
                </li>
            </ul>
        </nav>
    </div>
</main>

<div class="modal" id="createPopup" tabindex="-1">
    <div class="modal-dialog modal-dialog-centered">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title">Add New Role</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <form method="post" action="<c:url value="role"/>">
                <div class="modal-body">
                    <table class="table table-borderless">
                        <tr>
                            <th>
                                <label class="form-label" for="name">Name</label>
                            </th>
                            <td>
                                <input type="text" class="form-control" name="name" id="name">
                            </td>
                        </tr>
                    </table>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                    <button type="submit" name="action" value="create" class="btn btn-primary">Save</button>
                </div>
            </form>
        </div>
    </div>
</div>

<div class="modal" id="editPopup" tabindex="-1">
    <div class="modal-dialog modal-dialog-centered">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title">Edit Role Name</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <form method="post" action="<c:url value="role"/>">
                <div class="modal-body">
                    <table class="table table-borderless">
                        <tr>
                            <th>
                                <label class="form-label" for="nameEdit">Name</label>
                            </th>
                            <td>
                                <input type="text" class="form-control" name="name" id="nameEdit" value="">
                            </td>
                        </tr>
                        <input type="hidden" name="id" id="idEditName" value="">
                    </table>
                </div>

                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                    <button type="submit" name="action" value="edit" class="btn btn-primary">Save</button>
                </div>
            </form>
        </div>
    </div>
</div>

<script>
    function showCreatePopup() {
        var myModal = new bootstrap.Modal(document.getElementById('createPopup'));
        myModal.show();
    }

    function showEditPopup(id, name) {
        document.getElementById("idEditName").value = id;
        document.getElementById("nameEdit").value = name;
        var myModal = new bootstrap.Modal(document.getElementById('editPopup'));
        myModal.show();
    }
</script>


<c:if  test="${not empty param.status}">
    <div class="modal" id="exampleModal" tabindex="-1">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title">Action ${param.status == "success"?"Successful":"Fail"}</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <c:choose>
                        <c:when test="${param.status eq 'success'}">
                            <p style="color: green">The promotion <c:out value="${param.lastAction}"/> successfully.</p>
                        </c:when>
                        <c:otherwise>
                            <p style="color: red">Failed to <c:out value="${param.lastAction}"/> the promotion. Please check the information.</p>
                        </c:otherwise>
                    </c:choose>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                    <!--<button type="button" class="btn btn-primary">Save changes</button>-->
                </div>
            </div>
        </div>
    </div>


    <script>
        var myModal = new bootstrap.Modal(document.getElementById('exampleModal'));
        myModal.show();
    </script>
</c:if>

<%@include file="/WEB-INF/include/footer.jsp" %>
