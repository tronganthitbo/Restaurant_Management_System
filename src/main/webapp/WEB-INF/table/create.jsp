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
        <h1 class="mt-5">Add new Table</h1>
        <form method="post" action="<c:url value="table"/>">
            <table class="table">
                <tr>
                    <td>
                    </td>
                    <td>
                    </td>
                </tr>

                <tr>
                    <th>
                        <label for="number">Number</label>
                    </th>
                    <td>
                        <input type="text" name="number" id="number" class="form-control" required>
                    </td>
                </tr>

                <tr>
                    <th>
                        <label for="table_capacity">Capacity</label>
                    </th>
                    <td>
                        <input type="number" name="table_capacity" id="table_capacity" class="form-control" required>
                    </td>
                </tr>

                <tr>
                    <td>
                    </td>
                    <td>
                        <button class="btn btn-outline-dark" type="submit" name="action" value="create">Save</button>
                        <a class="btn btn-outline-dark" href="<c:url value="table"/>">Cancel</a>
                    </td>
                </tr>
            </table>
        </form>

    </div>

</main>
<%@include file="/WEB-INF/include/footer.jsp" %>
