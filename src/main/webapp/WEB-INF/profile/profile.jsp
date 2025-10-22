<%-- 
    Document   : profile
    Created on : Oct 22, 2025, 9:03:57 AM
    Author     : PHAT
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ include file="/WEB-INF/include/headerDashboard.jsp" %>

<c:if test="${empty currentUser}">
    <c:redirect url="/login"/>
</c:if>

<section class="col-12 col-lg-9 col-xxl-10 table-section" aria-label="Profile">
    <div class="content-card shadow-sm">
        <div class="card-header border-0 px-4 py-3 d-flex flex-column flex-md-row gap-3 gap-md-0 justify-content-between align-items-md-center">
            <div>
                <h1 class="section-title mb-1">My Profile</h1>
                <p class="text-muted">Update your information or change password</p>
            </div>
        </div>
        <div class="container">
            <!-- Update Info Form -->
            <form method="post" action="<c:url value='/profileEmployee'/>">
                <input type="hidden" name="id" value="${currentUser.empId}" />
                <input type="hidden" name="action" value="updateInfo" />
                <table class="table table align-middle admin-table">
                    <tr>
                        <th><label for="empAccount">Account</label></th>
                        <td><input type="text" name="empAccount" id="empAccount" class="form-control" value="${currentUser.empAccount}" required></td>
                    </tr>
                    <tr>
                        <th><label for="empName">Name</label></th>
                        <td><input type="text" name="empName" id="empName" class="form-control" value="${currentUser.empName}" required></td>
                    </tr>
                    <tr>
                        <th><label for="gender">Gender</label></th>
                        <td><input type="text" name="gender" id="gender" class="form-control" value="${currentUser.gender}"></td>
                    </tr>
                    <tr>
                        <th><label for="dob">Date of Birth</label></th>
                        <td>
                            <fmt:formatDate value="${currentUser.dob}" pattern="yyyy-MM-dd" var="dobFormatted"/>
                            <input type="date" name="dob" id="dob" class="form-control" value="${dobFormatted}"/>
                        </td>
                    </tr>
                    <tr>
                        <th><label for="phone">Phone</label></th>
                        <td><input type="text" name="phone" id="phone" class="form-control" value="${currentUser.phoneNumber}"></td>
                    </tr>
                    <tr>
                        <th><label for="email">Email</label></th>
                        <td><input type="email" name="email" id="email" class="form-control" value="${currentUser.email}"></td>
                    </tr>
                    <tr>
                        <th><label for="address">Address</label></th>
                        <td><input type="text" name="address" id="address" class="form-control" value="${currentUser.address}"></td>
                    </tr>
                    <tr>
                        <td></td>
                        <td>
                            <button class="btn btn-outline-success" type="submit">Update Info</button>
                        </td>
                    </tr>
                </table>
            </form>
            <hr/>
            <!-- Change Password Form -->
            <form method="post" action="<c:url value='/profileEmployee'/>">
                <input type="hidden" name="id" value="${currentUser.empId}" />
                <input type="hidden" name="action" value="changePassword" />
                <table class="table table align-middle admin-table">
                    <tr>
                        <th><label for="oldPassword">Old Password</label></th>
                        <td><input type="password" name="oldPassword" id="oldPassword" class="form-control" required></td>
                    </tr>
                    <tr>
                        <th><label for="newPassword">New Password</label></th>
                        <td><input type="password" name="newPassword" id="newPassword" class="form-control" required></td>
                    </tr>
                    <tr>
                        <td></td>
                        <td>
                            <button class="btn btn-outline-warning" type="submit">Change Password</button>
                        </td>
                    </tr>
                </table>
            </form>
        </div>
    </div>
</section>

<%@ include file="/WEB-INF/include/footerDashboard.jsp" %>

<!-- Popup Modal -->
<c:if test="${not empty profilePopupMessage}">
    <div class="modal" id="profileModal" tabindex="-1">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title">Action ${profilePopupStatus ? "Successful" : "Fail"}</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
                </div>
                <div class="modal-body">
                    <p style="color: ${profilePopupStatus ? 'green' : 'red'}">${profilePopupMessage}</p>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                </div>
            </div>
        </div>
    </div>
    <script>
        var myModal = new bootstrap.Modal(document.getElementById('profileModal'));
        myModal.show();
    </script>
</c:if>