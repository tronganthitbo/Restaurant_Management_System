</div>
</div>
</main>



<!-- Vendor JS Files -->
<script src="<%=request.getContextPath()%>/assets/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
<script src="<%=request.getContextPath()%>/assets/vendor/aos/aos.js"></script>

<!-- Main JS File -->
<script src="<%=request.getContextPath()%>/assets/js/main.js"></script>
</body>

<c:if  test="${not empty sessionScope.popupStatus}">
    <div class="modal" id="exampleModal" tabindex="-1">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title">Action ${sessionScope.popupStatus eq true?"Successful":"Fail"}</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <c:choose>
                        <c:when test="${sessionScope.popupStatus eq true}">
                            <p style="color: green"><c:out value="${sessionScope.popupMessage}"/></p>
                        </c:when>
                        <c:otherwise>
                            <p style="color: red"><c:out value="${sessionScope.popupMessage}"/></p>
                        </c:otherwise>
                    </c:choose>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                    <!--<button type="button" class="btn btn-primary">Save changes</button>-->
                </div>
            </div>
        </div>
    </div>

    <script>
        var myModal = new bootstrap.Modal(document.getElementById('exampleModal'));
        myModal.show();
    </script>
</c:if>

<script>
    function showDeletePopup(id) {
        document.getElementById("hiddenInputIdDelete").value = id;
        document.getElementById("idForDeletePopup").textContent = "Are you sure you want to delete the object with id = " + id + "?";
        var myModal = new bootstrap.Modal(document.getElementById('deletePopup'));
        myModal.show();
    }
</script>

</html>