<%-- 
    Document   : create
    Created on : 20 Jun 2025, 10:03:41 PM
    Author     : Dai Minh Nhu - CE190213
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="utf-8">
        <meta content="width=device-width, initial-scale=1.0" name="viewport">
        <title>Add Import Detail</title>
        <meta name="description" content="Form to add a new supplier in the Yummy Restaurant admin panel.">
        <meta name="keywords" content="supplier form, admin, restaurant management">

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
                <h1 class="mt-5">Add Import Detail</h1>
                <form method="post" action="<c:url value='import'/>">
                    <table class="table">
                        <tr>
                            <th>
                                <label for="importId">Import ID</label>
                            </th>
                            <td>
                                <input type="number" name="importId" id="importId" value="${currentImport.importId}" class="form-control" readonly>
                            </td>
                        </tr>

                        <tr>
                            <th>
                                <label for="empName">Manager</label>
                            </th>
                            <td>
                                <input type="text" name="empName" id="empName" value="${currentImport.empName}" class="form-control" readonly>
                            </td>
                        </tr>

                        <tr>
                            <th>
                                <label for="supplierName">Supplier</label>
                            </th>
                            <td>
                                <input type="text" name="supplierName" id="supplierName" value="${currentImport.supplierName}" class="form-control" readonly>
                            </td>
                        </tr>

                        <tr>
                            <th>
                                <label for="contactPerson">Contact Person</label>
                            </th>
                            <td>
                                <input type="text" name="contactPerson" id="contactPerson" value="${currentImport.contactPerson}" class="form-control" readonly>
                            </td>
                        </tr>

                        <tr>
                            <th>
                                <label for="importDate">Import Date</label>
                            </th>
                            <td>
                                <input type="date" name="importDate" id="importDate" value="${currentImport.importDate}" class="form-control" readonly>
                            </td>
                        </tr>

                        <tr>
                            <th>
                                <label for="ingredientName">Ingredient Name</label>
                            </th>
                            <td>
                                <input type="text" name="ingredientName" id="ingredientName" class="form-control" required>
                            </td>
                        </tr>

                        <tr>
                            <th>
                                <label for="type">Type</label>
                            </th>
                            <td>
                                <select name="typeId">    
                                    <c:forEach var="type" items="${typeList}">
                                        <option value="${type.typeId}" required class="form-control">${type.typeName}</option>
                                    </c:forEach>
                                </select>
                            </td>
                        </tr>

                        <tr>
                            <th>
                                <label for="quantity">Quantity</label>
                            </th>
                            <td>
                                <input type="number" name="quantity" id="quantity" class="form-control" required>
                            </td>
                        </tr>

                        <tr>
                            <th>
                                <label for="unit">Unit</label>
                            </th>
                            <td>
                                <input type="text" name="unit" id="unit" class="form-control" required>
                            </td>
                        </tr>

                        <tr>
                            <th>
                                <label for="unitPrice">Unit Price</label>
                            </th>
                            <td>
                                <input type="number" name="unitPrice" id="unitPrice" class="form-control" required>
                            </td>
                        </tr>

                        <tr>
                            <td>
                            </td>
                            <td>
                                <button class="btn btn-outline-dark" type="submit" name="action" value="addDetail">Add</button>
                            </td>
                        </tr>
                    </table>
                </form>

            </div>
        </main>

        <!-- Vendor JS Files -->
        <script src="assets/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
        <script src="assets/vendor/aos/aos.js"></script>

        <!-- Main JS File -->
        <script src="assets/js/main.js"></script>
    </body>

</html>
