<%-- 
    Document   : remove
    Created on : 21 Jun 2025, 4:58:35 PM
    Author     : Dai Minh Nhu - CE190213
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/include/header.jsp" %>

<%--<c:forEach items="${salesList}" var="sale" varStatus="loop">
    <c:if test="${sale.id == id}">
        <c:set var="currentSale" value="${salesList[loop.index]}"/>
    </c:if>
</c:forEach>--%>

<main>

    <div class="container">
        <c:choose>
            <c:when test="${not empty currentSupplier}">
                <h1 class="mt-5">Edit Supplier</h1>
                <form method="post" action="<c:url value="supplier">
                          <c:param name="id" value="${param.id}"/>  
                      </c:url>">
                    <table class="table">
                        <tr>
                            <td>
                            </td>
                            <td>
                            </td>
                        </tr>

                        <tr>
                            <th>
                                <label for="id">ID</label>
                            </th>
                            <td>
                                <label name ="id" id="id"><c:out value="${currentSupplier.supplierId}"/></label>
                            </td>
                        </tr>

                        <tr>
                            <th>
                                <label for="name">Name</label>
                            </th>
                            <td>
                                <input type="text" name="name" id="name" value="<c:out value="${currentSupplier.supplierName}"/>" class="form-control" required>
                            </td>
                        </tr>

                        <tr>
                            <th>
                                <label for="phoneNumber">Phone Number</label>
                            </th>
                            <td>
                                <input type="text" name="phoneNumber" id="phoneNumber" value="<c:out value="${currentSupplier.phoneNumber}"/>" class="form-control">
                            </td>
                        </tr>

                        <tr>
                            <th>
                                <label for="email">Email</label>
                            </th>
                            <td>
                                <input type="text" name="email" id="email" value="<c:out value="${currentSupplier.email}"/>" class="form-control">
                            </td>
                        </tr>

                        <tr>
                            <th>
                                <label for="address">Address</label>
                            </th>
                            <td>
                                <input type="text" name="address" id="address" value="<c:out value="${currentSupplier.address}"/>" class="form-control">
                            </td>
                        </tr>

                        <tr>
                            <th>
                                <label for="contactPerson">Contact Person</label>
                            </th>
                            <td>
                                <input type="text" name="contactPerson" id="contactPerson" value="<c:out value="${currentSupplier.contactPerson}"/>" class="form-control">
                            </td>
                        </tr>

                        <input type="hidden" name="status" id="status" value="${currentSupplier.status}">


                        <tr>
                            <td>
                            </td>
                            <td>
                                <button class="btn btn-success" type="submit" name="action" value="edit">Save</button>
                                <a class="btn btn-outline-dark" href="<c:url value="supplier"/>">Cancel</a>
                            </td>
                        </tr>
                    </table>
                </form>
            </c:when>
            <c:otherwise>
                <h2 class="mt-5">Not found the supplier which id <c:out value="${param.id}"/>. Please check the information.</h2>
                <a class="btn btn-outline-dark" href="<c:url value="supplier"/>">Back</a>
            </c:otherwise>
        </c:choose>
    </div>

</main>
<%@include file="/WEB-INF/include/footer.jsp" %>
