<%-- 
    Document   : list
    Created on : Oct 11, 2025, 3:23:41 PM
    Author     : PHAT
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/include/header.jsp" %>

<main>
    <div class="container">

        <h2>Employee List</h2>

        <p class="text-end">
            <a href="<c:url value='employee'>
                   <c:param name='view' value='create'/>
               </c:url>" class="btn btn-success">Add New</a>
        </p>

        <table class="table table-bordered table-hover text-center">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Account</th>
                    <th>Full Name</th>
                    <th>Gender</th>
                    <th>Date of Birth</th>
                    <th>Phone</th>
                    <th>Email</th>
                    <th>Address</th>
                    <th>Role ID</th>
                    <th>Status</th>
                    <th>Action</th>
                </tr>
            </thead>
            <tbody>
                <c:choose>
                    <c:when test="${employeeList == null || empty employeeList}">
                        <tr>
                            <td colspan="11" style="color:red;">No data to display</td>
                        </tr>   
                    </c:when>
                    <c:otherwise>
                        <c:forEach var="emp" items="${employeeList}" varStatus="loop">
                            <tr>
                                <td><c:out value="${emp.empId}"/></td>
                                <td><c:out value="${emp.empAccount}"/></td>
                                <td><c:out value="${emp.empName}"/></td>
                                <td><c:out value="${emp.gender}"/></td>
                                <td><c:out value="${emp.dob}"/></td>
                                <td><c:out value="${emp.phoneNumber}"/></td>
                                <td><c:out value="${emp.email}"/></td>
                                <td><c:out value="${emp.address}"/></td>
                                <td><c:out value="${emp.roleId}"/></td>
                                <td><c:out value="${emp.status}"/></td>
                                <td>
                                    <a href="<c:url value='employee'>
                                           <c:param name='view' value='edit'/>
                                           <c:param name='id' value='${emp.empId}'/>
                                       </c:url>" class="btn btn-primary">
                                        Edit
                                    </a>
                                    <button type="button" class="btn btn-danger" onclick="showDeletePopup(${emp.empId})">
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
                    <a class="page-link" href="<c:url value='/employee'>
                           <c:param name='view' value='list'/>
                           <c:param name='page' value='${param.page - 1}'/>
                       </c:url>" aria-label="Previous">
                        <span aria-hidden="true">&laquo;</span>
                    </a>
                </li>
                <c:forEach begin="1" end="${requestScope.totalPages}" var="i">
                    <li class="page-item ${((empty param.page && i == 1) || param.page == i)?"active":""}">
                        <a class="page-link" href="<c:url value='/employee'>
                               <c:param name='view' value='list'/>
                               <c:param name='page' value='${i}'/>
                           </c:url>">${i}</a></li>
                    </c:forEach>
                <li class="page-item ${(requestScope.totalPages <= param.page || requestScope.totalPages eq 1 )?"disabled":""}">
                    <a class="page-link" href="<c:url value='/employee'>
                           <c:param name='view' value='list'/>
                           <c:param name='page' value='${(empty param.page)?2:param.page + 1}'/>
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
                <h6 id="idForDeletePopup">Are you sure you want to delete this employee?</h6>
                <small class="text-muted">This action cannot be undone.</small>
            </div>
            <form method="post" action="<c:url value='employee'/>">
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
        document.getElementById("idForDeletePopup").textContent = "Are you sure you want to delete the employee with id = " + id + "?";
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
                            <p style="color: green">The operation <c:out value="${param.lastAction}"/> was successful.</p>
                        </c:when>
                        <c:otherwise>
                            <p style="color: red">Failed to <c:out value="${param.lastAction}"/> the employee. Please check the information.</p>
                        </c:otherwise>
                    </c:choose>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
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
