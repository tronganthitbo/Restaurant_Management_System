<%-- 
    Document   : edit
    Created on : Oct 11, 2025, 3:23:33 PM
    Author     : PHAT
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/include/header.jsp" %>

<main>
    <div class="container">
        <c:choose>
            <c:when test="${not empty currentEmployee}">
                <h1 class="mt-5">Edit Employee</h1>
                <form method="post" action="<c:url value='employee'>
                          <c:param name='id' value='${param.id}'/>
                      </c:url>">
                    <table class="table">
                        <tr>
                            <th><label for="empId">ID</label></th>
                            <td>
                                <label id="empId"><c:out value="${currentEmployee.empId}"/></label>
                                <input type="hidden" name="emp_id" value="${currentEmployee.empId}" />
                            </td>
                        </tr>
                        <tr>
                            <th><label for="empAccount">Account</label></th>
                            <td>
                                <input type="text" name="emp_account" id="empAccount" value="<c:out value='${currentEmployee.empAccount}'/>" class="form-control" required>
                            </td>
                        </tr>
                        <tr>
                            <th><label for="password">Password</label></th>
                            <td>
                                <input type="password" name="password" id="password" value="<c:out value='${currentEmployee.password}'/>" class="form-control" required>
                            </td>
                        </tr>
                        <tr>
                            <th><label for="empName">Full Name</label></th>
                            <td>
                                <input type="text" name="emp_name" id="empName" value="<c:out value='${currentEmployee.empName}'/>" class="form-control" required>
                            </td>
                        </tr>
                        <tr>
                            <th><label for="gender">Gender</label></th>
                            <td>
                                <select name="gender" id="gender" class="form-control" required>
                                    <option value="">--Select--</option>
                                    <option value="Male" <c:if test='${currentEmployee.gender=="Male"}'>selected</c:if>>Male</option>
                                    <option value="Female" <c:if test='${currentEmployee.gender=="Female"}'>selected</c:if>>Female</option>
                                    <option value="Other" <c:if test='${currentEmployee.gender=="Other"}'>selected</c:if>>Other</option>
                                    </select>
                                </td>
                            </tr>
                            <tr>
                                <th><label for="dob">Date of Birth</label></th>
                                <td>
                                    <input type="date" name="dob" id="dob"
                                           value="<c:out value='${currentEmployee.dob}'/>"
                                    class="form-control" required>
                            </td>
                        </tr>
                        <tr>
                            <th><label for="phoneNumber">Phone</label></th>
                            <td>
                                <input type="text" name="phone_number" id="phoneNumber" value="<c:out value='${currentEmployee.phoneNumber}'/>" class="form-control" required>
                            </td>
                        </tr>
                        <tr>
                            <th><label for="email">Email</label></th>
                            <td>
                                <input type="email" name="email" id="email" value="<c:out value='${currentEmployee.email}'/>" class="form-control" required>
                            </td>
                        </tr>
                        <tr>
                            <th><label for="address">Address</label></th>
                            <td>
                                <input type="text" name="address" id="address" value="<c:out value='${currentEmployee.address}'/>" class="form-control" required>
                            </td>
                        </tr>
                        <tr>
                            <th><label for="roleId">Role ID</label></th>
                            <td>
                                <select name="role_id" id="roleId" class="form-control" required>
                                    <c:forEach var="role" items="${rolesList}">
                                        <option value="${role.role_id}" <c:if test="${role.role_id == currentEmployee.roleId}">selected</c:if>>${role.role_name}</option>
                                    </c:forEach>
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <th><label for="status">Status</label></th>
                            <td>
                                <select name="status" id="status" class="form-control" required>
                                    <option value="Active" <c:if test='${currentEmployee.status=="Active"}'>selected</c:if>>Active</option>
                                    <option value="Inactive" <c:if test='${currentEmployee.status=="Inactive"}'>selected</c:if>>Inactive</option>
                                    </select>
                                </td>
                            </tr>
                            <tr>
                                <td></td>
                                <td>
                                    <button class="btn btn-success" type="submit" name="action" value="edit">Save</button>
                                    <a class="btn btn-outline-dark" href="<c:url value='employee'/>">Cancel</a>
                            </td>
                        </tr>
                    </table>
                </form>
            </c:when>
            <c:otherwise>
                <h2 class="mt-5">Not found the Employee with ID <c:out value="${param.id}"/>. Please check the information.</h2>
                <a class="btn btn-outline-dark" href="<c:url value='employee'/>">Back</a>
            </c:otherwise>
        </c:choose>
    </div>
</main>

<%@include file="/WEB-INF/include/footer.jsp" %>
