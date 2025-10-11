<%-- 
    Document   : create
    Created on : Oct 11, 2025, 5:21:51 PM
    Author     : PHAT
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/include/header.jsp" %>

<main>
    <div class="container">
        <h1 class="mt-5">Add New Voucher</h1>
        <form method="post" action="<c:url value='voucher'/>">
            <table class="table">
                <tr>
                    <th><label for="voucherCode">Code</label></th>
                    <td><input type="text" name="voucher_code" id="voucherCode" class="form-control" required></td>
                </tr>
                <tr>
                    <th><label for="voucherName">Name</label></th>
                    <td><input type="text" name="voucher_name" id="voucherName" class="form-control" required></td>
                </tr>
                <tr>
                    <th><label for="discountType">Type</label></th>
                    <td><input type="text" name="discount_type" id="discountType" class="form-control" required></td>
                </tr>
                <tr>
                    <th><label for="discountValue">Discount Value</label></th>
                    <td><input type="number" step="0.01" name="discount_value" id="discountValue" class="form-control" required></td>
                </tr>
                <tr>
                    <th><label for="quantity">Quantity</label></th>
                    <td><input type="number" name="quantity" id="quantity" class="form-control" required></td>
                </tr>
                <tr>
                    <th><label for="startDate">Start Date</label></th>
                    <td><input type="date" name="start_date" id="startDate" class="form-control" required></td>
                </tr>
                <tr>
                    <th><label for="endDate">End Date</label></th>
                    <td><input type="date" name="end_date" id="endDate" class="form-control" required></td>
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
                        <a class="btn btn-outline-dark" href="<c:url value='voucher'/>">Cancel</a>
                    </td>
                </tr>
            </table>
        </form>
    </div>
</main>
<%@include file="/WEB-INF/include/footer.jsp" %>