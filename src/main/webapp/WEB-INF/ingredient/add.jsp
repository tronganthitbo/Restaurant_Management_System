<%-- 
    Document   : add
    Created on : Oct 22, 2025
    Author     : TruongBinhTrong
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%-- Includes the header, navigation, and opening tags for the admin layout --%>
<%@include file="/WEB-INF/include/headerDashboard.jsp" %>

<section class="col-12 col-lg-9 col-xxl-10 table-section" aria-label="Ingredient Add Form">
    <div class="content-card shadow-sm">
        <div class="card-header border-0 px-4 py-3 d-flex justify-content-between align-items-center">
            <h1 class="section-title mb-0">Add New Ingredient</h1>
         
        </div>

        <div class="container px-4 py-3">
            <form method="post" action="<c:url value='ingredient'/>">
                <table class="table">
                    
                    <%-- Ingredient Name --%>
                    <tr>
                        <th width="30%">
                            <label for="ingredientName">Name</label>
                        </th>
                        <td>
                            <input type="text" name="ingredientName" id="ingredientName" class="form-control" required>
                        </td>
                    </tr>
                    
                    <%-- Ingredient Type (Dropdown) --%>
                    <tr>
                        <th>
                            <label for="typeSelect">Type</label>
                        </th>
                        <td>
                            <select name="typeId" id="typeSelect" class="form-select" required>
                                <option value="">Select a type</option>
                                <c:forEach var="t" items="${typesList}">
                                    <option value="${t.typeId}">${t.typeName}</option>
                                </c:forEach>
                            </select>
                        </td>
                    </tr>
                    
                    <%-- Ingredient Price (Now just a standard number input) --%>
                    <tr>
                        <th>
                            <label for="priceInput">Price</label>
                        </th>
                        <td>
                            <input type="number" 
                                   name="price" 
                                   id="priceInput" 
                                   class="form-control" 
                                   step="0.01"
                                   min="0.00"
                                   required>
                        </td>
                    </tr>

                    <%-- Action Buttons --%>
                    <tr>
                        <td></td>
                        <td>
                            <button class="btn btn-primary me-2" type="submit" name="action" value="add">
                                <i class="bi bi-check-circle"></i> Add
                            </button>
                            <a class="btn btn-outline-dark" href="<c:url value='ingredient'/>">
                                <i class="bi bi-x-lg"></i> Cancel
                            </a>
                        </td>
                    </tr>
                </table>
            </form>
        </div>
    </div>
</section>

<%@include file="/WEB-INF/include/footerDashboard.jsp" %>