<%-- 
    Document   : edit
    Created on : Oct 11, 2025, 5:22:09 PM
    Author     : PHAT
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/include/header.jsp" %>

<main>
    <div class="container">
        <c:choose>
            <c:when test="${not empty currentVoucher}">
                <h1 class="mt-5">Edit Voucher</h1>
                <form method="post" action="<c:url value='voucher'>
                    <c:param name='id' value='${param.id}'/>
                </c:url>">
                    <table class="table">
                        <tr>
                            <th><label for="voucherId">ID</label></th>
                            <td>
                                <label id="voucherId"><c:out value="${currentVoucher.voucherId}"/></label>
                                <input type="hidden" name="voucher_id" value="${currentVoucher.voucherId}" />
                            </td>
                        </tr>
                        <tr>
                            <th><label for="voucherCode">Code</label></th>
                            <td>
                                <input type="text" name="voucher_code" id="voucherCode" value="<c:out value='${currentVoucher.voucherCode}'/>" class="form-control" required>
                            </td>
                        </tr>
                        <tr>
                            <th><label for="voucherName">Name</label></th>
                            <td>
                                <input type="text" name="voucher_name" id="voucherName" value="<c:out value='${currentVoucher.voucherName}'/>" class="form-control" required>
                            </td>
                        </tr>
                        <tr>
                            <th><label for="discountType">Type</label></th>
                            <td>
                                <input type="text" name="discount_type" id="discountType" value="<c:out value='${currentVoucher.discountType}'/>" class="form-control" required>
                            </td>
                        </tr>
                        <tr>
                            <th><label for="discountValue">Discount Value</label></th>
                            <td>
                                <input type="number" step="0.01" name="discount_value" id="discountValue" value="<c:out value='${currentVoucher.discountValue}'/>" class="form-control" required>
                            </td>
                        </tr>
                        <tr>
                            <th><label for="quantity">Quantity</label></th>
                            <td>
                                <input type="number" name="quantity" id="quantity" value="<c:out value='${currentVoucher.quantity}'/>" class="form-control" required>
                            </td>
                        </tr>
                        <tr>
                            <th><label for="startDate">Start Date</label></th>
                            <td>
                                <input type="date" name="start_date" id="startDate" value="<c:out value='${currentVoucher.startDate}'/>" class="form-control" required>
                            </td>
                        </tr>
                        <tr>
                            <th><label for="endDate">End Date</label></th>
                            <td>
                                <input type="date" name="end_date" id="endDate" value="<c:out value='${currentVoucher.endDate}'/>" class="form-control" required>
                            </td>
                        </tr>
                        <tr>
                            <th><label for="status">Status</label></th>
                            <td>
                                <select name="status" id="status" class="form-control" required>
                                    <option value="Active" <c:if test='${currentVoucher.status=="Active"}'>selected</c:if>>Active</option>
                                    <option value="Inactive" <c:if test='${currentVoucher.status=="Inactive"}'>selected</c:if>>Inactive</option>
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <td></td>
                            <td>
                                <button class="btn btn-success" type="submit" name="action" value="edit">Save</button>
                                <a class="btn btn-outline-dark" href="<c:url value='voucher'/>">Cancel</a>
                            </td>
                        </tr>
                    </table>
                </form>
            </c:when>
            <c:otherwise>
                <h2 class="mt-5">Not found the Voucher with ID <c:out value="${param.id}"/>. Please check the information.</h2>
                <a class="btn btn-outline-dark" href="<c:url value='voucher'/>">Back</a>
            </c:otherwise>
        </c:choose>
    </div>
</main>
<%@include file="/WEB-INF/include/footer.jsp" %>