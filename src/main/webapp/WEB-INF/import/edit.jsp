<%-- 
    Document   : remove
    Created on : 21 Jun 2025, 4:58:35 PM
    Author     : Dai Minh Nhu - CE190213
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="utf-8">
        <meta content="width=device-width, initial-scale=1.0" name="viewport">
        <title>Edit Import</title>
        <meta name="description" content="Form to edit supplier information in the Yummy Restaurant admin panel.">
        <meta name="keywords" content="supplier edit, admin, restaurant management">

        <!-- Favicons -->
        <link href="assets/img/favicon.png" rel="icon">
        <link href="assets/img/apple-touch-icon.png" rel="apple-touch-icon">

        <!-- Fonts -->
        <link href="https://fonts.googleapis.com" rel="preconnect">
        <link href="https://fonts.gstatic.com" rel="preconnect" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Roboto:ital,wght@0,100;0,300;0,400;0,500;0,700;0,900;1,100;1,300;1,400;1,500;1,700;1,900&family=Inter:wght@100;200;300;400;500;600;700;800;900&display=swap" rel="stylesheet">

        <!-- Vendor CSS Files -->
        <link href="assets/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
        <link href="assets/vendor/bootstrap-icons/bootstrap-icons.css" rel="stylesheet">
        <link href="assets/vendor/aos/aos.css" rel="stylesheet">

        <!-- Main Site CSS -->
        <link href="assets/css/main.css" rel="stylesheet">

        <!-- Admin Listing CSS -->
        <link href="assets/css/dashboard.css" rel="stylesheet">
    </head>

    <body class="admin-list-page">
        <header class="admin-header shadow-sm">
            <div class="container-fluid d-flex align-items-center justify-content-between">
                <div class="brand d-flex align-items-center gap-3">
                    <div class="brand-info">
                        <h2 class="brand-name mb-0">Dashboard</h2>
                        <p class="brand-subtitle mb-0">Staff &amp; Operations</p>
                    </div>
                </div>
                <div class="header-actions d-flex align-items-center gap-3">
                    <div class="profile-chip d-flex align-items-center gap-2">
                        <div class="avatar">A</div>
                        <div>
                            <p class="mb-0 fw-semibold">Alex Morgan</p>
                            <span class="role">Administrator</span>
                        </div>
                    </div>
                </div>
            </div>
        </header>

        <main class="admin-layout">
            <div class="container">
                <c:choose>
                    <c:when test="${not empty currentImport}">
                        <h1 class="mt-5">Edit Import</h1>
                        <form method="post" action="<c:url value='import'>
                                  <c:param name='id' value='${param.id}'/>
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
                                        <label for="name">Manager</label>
                                    </th>
                                    <td>
                                        <select name="empName">    
                                            <c:forEach var="emp" items="${employeeList}">
                                                <option value="${emp.empName}"
                                                        <c:if test="${emp.empName == currentImport.empName}">selected</c:if>>
                                                    ${emp.empName}
                                                </option>
                                            </c:forEach>
                                        </select>
                                    </td>
                                </tr>

                                <tr>
                                    <th>
                                        <label for="contactPerson">Contact Person</label>
                                    </th>
                                    <td>
                                        <input type="text" name="contactPerson" id="contactPerson" value="<c:out value='${currentImport.contactPerson}'/>" class="form-control" readonly>
                                    </td>
                                </tr>

                                <tr>
                                    <th>
                                        <label for="name">Supplier Name</label>
                                    </th>
                                    <td>
                                        <select name="supplierName">    
                                            <c:forEach var="supplier" items="${supplierList}">
                                                <option value="${supplier.supplierName}"
                                                        <c:if test="${supplier.supplierName == currentImport.supplierName}">selected</c:if>>
                                                    ${supplier.supplierName}
                                                </option>
                                            </c:forEach>
                                        </select>
                                    </td>
                                </tr>

                                <tr>
                                    <th>
                                        <label for="importDate">Date</label>
                                    </th>
                                    <td>
                                        <input type="text" name="importDate" id="importDate" value="<c:out value='${currentImport.importDate}'/>" class="form-control">
                                    </td>
                                </tr>

                                <input type="hidden" name="status" id="status" value="${currentSupplier.status}">


                                <tr>
                                    <td>
                                    </td>
                                    <td>
                                        <button class="btn btn-success" type="submit" name="action" value="edit">Save</button>
                                        <a class="btn btn-outline-dark" href="<c:url value='import'/>">Cancel</a>
                                    </td>
                                </tr>
                            </table>
                        </form>
                    </c:when>
                    <c:otherwise>
                        <h2 class="mt-5">Not found the supplier which id <c:out value="${param.id}"/>. Please check the information.</h2>
                        <a class="btn btn-outline-dark" href="<c:url value='supplier'/>">Back</a>
                    </c:otherwise>
                </c:choose>
            </div>

        </main>

        <!-- Vendor JS Files -->
        <script src="assets/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
        <script src="assets/vendor/aos/aos.js"></script>

        <!-- Main JS File -->
        <script src="assets/js/main.js"></script>
    </body>

</html>
