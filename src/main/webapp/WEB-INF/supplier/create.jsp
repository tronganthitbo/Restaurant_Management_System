<%-- 
    Document   : create
    Created on : 20 Jun 2025, 10:03:41 PM
    Author     : Dai Minh Nhu - CE190213
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/include/header.jsp" %>
<main>

    <div class="container">
        <h1 class="mt-5">Add Supplier</h1>
        <form method="post" action="<c:url value="supplier"/>">
            <table class="table">
                <tr>
                    <td>
                    </td>
                    <td>
                    </td>
                </tr>

                <tr>
                    <th>
                        <label for="name">Name</label>
                    </th>
                    <td>
                        <input type="text" name="supplierName" id="supplierName" class="form-control" required>
                    </td>
                </tr>

                <tr>
                    <th>
                        <label for="description">Phone Number</label>
                    </th>
                    <td>
                        <input type="text" name="phoneNumber" id="phoneNumber" class="form-control">
                    </td>
                </tr>

                <tr>
                    <th>
                        <label for="description">Email</label>
                    </th>
                    <td>
                        <input type="text" name="email" id="email" class="form-control">
                    </td>
                </tr>

                <tr>
                    <th>
                        <label for="description">Address</label>
                    </th>
                    <td>
                        <input type="text" name="address" id="address" class="form-control">
                    </td>
                </tr>

                <tr>
                    <th>
                        <label for="description">Contact Person</label>
                    </th>
                    <td>
                        <input type="text" name="contactPerson" id="contactPerson" class="form-control">
                    </td>
                </tr>

                <tr>
                    <td>
                    </td>
                    <td>
                        <button class="btn btn-outline-dark" type="submit" name="action" value="add">Add</button>
                        <a class="btn btn-outline-dark" href="<c:url value="supplier"/>">Cancel</a>
                    </td>
                </tr>
            </table>
        </form>

    </div>

</main>
<%@include file="/WEB-INF/include/footer.jsp" %>
