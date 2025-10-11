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

        <h2>Table list</h2>

        <p class="text-end">
            <a href="<c:url value="table">
                   <c:param name="view" value="create"/>
               </c:url>" class="btn btn-success">Add New</a>
        </p>

        <table class="table table-bordered table-hover text-center">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Number</th>
                    <th>Capacity</th>
                    <th>Action</th>
                </tr>
            </thead>
            <tbody>
                <c:choose>
                    <c:when test="${tablesList == null || empty tablesList}">
                        <tr>
                            <td colspan="8" style="color:red;">No data to display</td>
                        </tr>   
                    </c:when>
                    <c:otherwise>
                        <c:forEach var="table" items="${tablesList}" varStatus="loop">
                            <tr>
                                <td><c:out value="${table.table_id}"/></td>
                                <td><c:out value="${table.table_number}"/></td>
                                <td><c:out value="${table.table_capacity}"/></td>

                                <td>
                                    <a href="<c:url value="table">
                                           <c:param name="view" value="edit"/>
                                           <c:param name="id" value="${table.table_id}"/>
                                       </c:url>" class="btn btn-primary">
                                        Edit
                                    </a>
                                    <button type="button" class="btn btn-danger" onclick="showDeletePopup(${table.table_id})">
                                        Delete
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
                    <a class="page-link" href="<c:url value="/table">
                           <c:param name="view" value="list"/>
                           <c:param name="page" value="${param.page - 1}"/>
                       </c:url>" aria-label="Previous">
                        <span aria-hidden="true">&laquo;</span>
                    </a>
                </li>
                <c:forEach begin="1" end="${requestScope.totalPages}" var="i">
                    <li class="page-item ${((empty param.page && i == 1) || param.page == i)?"active":""}">
                        <a class="page-link" href="<c:url value="/table">
                               <c:param name="view" value="list"/>
                               <c:param name="page" value="${i}"/>
                           </c:url>">${i}</a></li>
                    </c:forEach>
                <li class="page-item ${(requestScope.totalPages <= param.page || requestScope.totalPages eq 1 )?"disabled":""}">
                    <a class="page-link" href="<c:url value="/table">
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

<div class="modal" id="deletePopup" tabindex="-1">
    <div class="modal-dialog modal-dialog-centered">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title">Confirm Deletion</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body text-danger">
                <h6 id="idForDeletePopup">Are you sure you want to delete this?</h6>
                <small class="text-muted">This action cannot be undone.</small>
            </div>
            <form method="post" action="<c:url value="table"/>">
                <input type="hidden" id="hiddenInputIdDelete" name="id" value="">
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                    <button type="submit" name="action" value="delete" class="btn btn-danger">Delete</button>
                </div>
            </form>
        </div>
    </div>
</div>

<script>
    function showDeletePopup(id) {
        document.getElementById("hiddenInputIdDelete").value = id;
        document.getElementById("idForDeletePopup").textContent = "Are you sure you want to delete the table with id = " + id + "?";
        var myModal = new bootstrap.Modal(document.getElementById('deletePopup'));
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
