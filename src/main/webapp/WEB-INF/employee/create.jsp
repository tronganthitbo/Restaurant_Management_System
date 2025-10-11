<%-- 
    Document   : create
    Created on : Oct 11, 2025, 3:23:25 PM
    Author     : PHAT
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/include/header.jsp" %>

<main>
    <div class="container">
        <h1 class="mt-5">Add New Employee</h1>
        <form method="post" action="<c:url value='employee'/>">
            <table class="table">
                <tr>
                    <th><label for="empAccount">Account</label></th>
                    <td><input type="text" name="emp_account" id="empAccount" class="form-control" required></td>
                </tr>
                <tr>
                    <th><label for="password">Password</label></th>
                    <td><input type="password" name="password" id="password" class="form-control" required></td>
                </tr>
                <tr>
                    <th><label for="empName">Full Name</label></th>
                    <td><input type="text" name="emp_name" id="empName" class="form-control" required></td>
                </tr>
                <tr>
                    <th><label for="gender">Gender</label></th>
                    <td>
                        <select name="gender" id="gender" class="form-control" required>
                            <option value="">--Select--</option>
                            <option value="Male">Male</option>
                            <option value="Female">Female</option>
                            <option value="Other">Other</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <th><label for="dob">Date of Birth</label></th>
                    <td><input type="date" name="dob" id="dob" class="form-control" required></td>
                </tr>
                <tr>
                    <th><label for="phoneNumber">Phone</label></th>
                    <td><input type="text" name="phone_number" id="phoneNumber" class="form-control" required></td>
                </tr>
                <tr>
                    <th><label for="email">Email</label></th>
                    <td><input type="email" name="email" id="email" class="form-control" required></td>
                </tr>
                <tr>
                    <th><label for="address">Address</label></th>
                    <td><input type="text" name="address" id="address" class="form-control" required></td>
                </tr>
                <tr>
                    <th><label for="roleId">Role ID</label></th>
                    <td>
                        <select name="role_id" id="roleId" class="form-control" required>
                            <c:forEach var="role" items="${rolesList}">
                                <option value="${role.role_id}">${role.role_name}</option>
                            </c:forEach>
                        </select>
                    </td>
                </tr>
                <tr>
                    <th><label for="status">Status</label></th>
                    <td>
                        <select name="status" id="status" class="form-control" required>
                            <option value="Active">Active</option>
                            <option value="Inactive">Inactive</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td></td>
                    <td>
                        <button class="btn btn-outline-dark" type="submit" name="action" value="create">Save</button>
                        <a class="btn btn-outline-dark" href="<c:url value='employee'/>">Cancel</a>
                    </td>
                </tr>
            </table>
        </form>
    </div>
</main>

<%@include file="/WEB-INF/include/footer.jsp" %>