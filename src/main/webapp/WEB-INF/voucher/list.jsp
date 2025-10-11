<%-- 
    Document   : list
    Created on : Oct 11, 2025, 5:22:00 PM
    Author     : PHAT
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/include/header.jsp" %>

<main>
    <div class="container">

        <h2>Voucher List</h2>

        <p class="text-end">
            <a href="<c:url value='voucher'>
                   <c:param name='view' value='create'/>
               </c:url>" class="btn btn-success">Add New</a>
        </p>

        <table class="table table-bordered table-hover text-center">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Code</th>
                    <th>Name</th>
                    <th>Type</th>
                    <th>Discount Value</th>
                    <th>Quantity</th>
                    <th>Start Date</th>
                    <th>End Date</th>
                    <th>Status</th>
                    <th>Action</th>
                </tr>
            </thead>
            <tbody>
                <c:choose>
                    <c:when test="${voucherList == null || empty voucherList}">
                        <tr>
                            <td colspan="10" style="color:red;">No data to display</td>
                        </tr>   
                    </c:when>
                    <c:otherwise>
                        <c:forEach var="voucher" items="${voucherList}" varStatus="loop">
                            <tr>
                                <td><c:out value="${voucher.voucherId}"/></td>
                                <td><c:out value="${voucher.voucherCode}"/></td>
                                <td><c:out value="${voucher.voucherName}"/></td>
                                <td><c:out value="${voucher.discountType}"/></td>
                                <td><c:out value="${voucher.discountValue}"/></td>
                                <td><c:out value="${voucher.quantity}"/></td>
                                <td><c:out value="${voucher.startDate}"/></td>
                                <td><c:out value="${voucher.endDate}"/></td>
                                <td><c:out value="${voucher.status}"/></td>
                                <td>
                                    <a href="<c:url value='voucher'>
                                           <c:param name='view' value='edit'/>
                                           <c:param name='id' value='${voucher.voucherId}'/>
                                       </c:url>" class="btn btn-primary">
                                        Edit
                                    </a>
                                    <button type="button" class="btn btn-danger" onclick="showDeletePopup(${voucher.voucherId})">
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
                    <a class="page-link" href="<c:url value='/voucher'>
                           <c:param name='view' value='list'/>
                           <c:param name='page' value='${param.page - 1}'/>
                       </c:url>" aria-label="Previous">
                        <span aria-hidden="true">&laquo;</span>
                    </a>
                </li>
                <c:forEach begin="1" end="${requestScope.totalPages}" var="i">
                    <li class="page-item ${((empty param.page && i == 1) || param.page == i)?"active":""}">
                        <a class="page-link" href="<c:url value='/voucher'>
                               <c:param name='view' value='list'/>
                               <c:param name='page' value='${i}'/>
                           </c:url>">${i}</a></li>
                    </c:forEach>
                <li class="page-item ${(requestScope.totalPages <= param.page || requestScope.totalPages eq 1 )?"disabled":""}">
                    <a class="page-link" href="<c:url value='/voucher'>
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
                <h6 id="idForDeletePopup">Are you sure you want to delete this voucher?</h6>
                <small class="text-muted">This action cannot be undone.</small>
            </div>
            <form method="post" action="<c:url value='voucher'/>">
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
        document.getElementById("idForDeletePopup").textContent = "Are you sure you want to delete the voucher with id = " + id + "?";
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
                            <p style="color: red">Failed to <c:out value="${param.lastAction}"/> the voucher. Please check the information.</p>
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
