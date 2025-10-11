/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import static constant.CommonFunction.addEDtoEverything;
import static constant.CommonFunction.getTotalPages;
import static constant.CommonFunction.validateInteger;
import static constant.CommonFunction.validateString;
import dao.EmployeeDAO;
import dao.RoleDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.Date;

/**
 *
 * @author PHAT
 */
@WebServlet(name = "EmployeeServlet", urlPatterns = {"/employee"})
public class EmployeeServlet extends HttpServlet {

    EmployeeDAO empDAO = new EmployeeDAO();
    RoleDAO roleDAO = new RoleDAO();

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet EmployeeServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet EmployeeServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String namepage = "";
        String view = request.getParameter("view");

        if (!validateString(view, -1)) {
            namepage = "list";
        } else if (view.equalsIgnoreCase("create")) {
            namepage = "create";
            RoleDAO roleDAO = new RoleDAO();
            request.setAttribute("rolesList", roleDAO.getAvailableRoles());
        } else if (view.equalsIgnoreCase("edit")) {
            namepage = "edit";
            int id;
            try {
                id = Integer.parseInt(request.getParameter("id"));
            } catch (NumberFormatException e) {
                id = -1;
            }
            request.setAttribute("currentEmployee", empDAO.getElementByID(id));
            RoleDAO roleDAO = new RoleDAO();
            request.setAttribute("rolesList", roleDAO.getAvailableRoles());
        } else if (view.equalsIgnoreCase("delete")) {
            namepage = "delete";
        }

        int page;
        int totalPages = getTotalPages(empDAO.countItem());
        try {
            page = Integer.parseInt(request.getParameter("page"));
        } catch (NumberFormatException e) {
            page = 1;
        }

        request.setAttribute("totalPages", totalPages);
        request.setAttribute("employeeList", empDAO.getAll(page));
        request.getRequestDispatcher("/WEB-INF/employee/" + namepage + ".jsp").forward(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        boolean passValidation = true;
        String errorMsg = "";

        if (action != null && !action.isEmpty()) {
            if (action.equalsIgnoreCase("create")) {

                // Lấy dữ liệu từ form
                String emp_account = request.getParameter("emp_account");
                String password = request.getParameter("password");
                String emp_name = request.getParameter("emp_name");
                String gender = request.getParameter("gender");
                String dob_raw = request.getParameter("dob");
                String phone_number = request.getParameter("phone_number");
                String email = request.getParameter("email");
                String address = request.getParameter("address");
                String role_raw = request.getParameter("role_id");

                int role_id = -1;

                try {
                    role_id = Integer.parseInt(role_raw);
                } catch (Exception e) {
                    passValidation = false;
                    errorMsg = "Role không hợp lệ!";
                }

                // Kiểm tra role có bị xóa không
                if (roleDAO.isRoleDeleted(role_id)) {
                    passValidation = false;
                    errorMsg = "Role này đã bị xóa, vui lòng chọn role khác!";
                }
                // Validate input
                if (!validateString(emp_account, -1)
                        || !validateString(password, -1)
                        || !validateString(emp_name, -1)
                        || !validateString(gender, -1)
                        || !validateString(dob_raw, -1)
                        || !validateString(phone_number, -1)
                        || !validateString(email, -1)
                        || !validateString(address, -1)
                        || !validateString(role_raw, -1)) {
                    passValidation = false;
                }

                if (passValidation) {
                    try {
                        Date dob = Date.valueOf(dob_raw);
                        int result = empDAO.create(emp_account, password, emp_name, gender, dob,
                                phone_number, email, address, role_id);
                        if (result < 1) {
                            passValidation = false;
                        }
                    } catch (Exception e) {
                        passValidation = false;
                    }
                }

            } else if (action.equalsIgnoreCase("edit")) {

                int emp_id;
                String emp_account = request.getParameter("emp_account");
                String password = request.getParameter("password");
                String emp_name = request.getParameter("emp_name");
                String gender = request.getParameter("gender");
                String dob_raw = request.getParameter("dob");
                String phone_number = request.getParameter("phone_number");
                String email = request.getParameter("email");
                String address = request.getParameter("address");
                String role_raw = request.getParameter("role_id");
                String status = request.getParameter("status");

                try {
                    emp_id = Integer.parseInt(request.getParameter("emp_id"));
                } catch (NumberFormatException e) {
                    emp_id = -1;
                    passValidation = false;
                }
                int role_id = -1;
                try {
                    role_id = Integer.parseInt(role_raw);
                } catch (Exception e) {
                    passValidation = false;
                    errorMsg = "Role không hợp lệ!";
                }

                // Kiểm tra role có bị xóa không
                if (roleDAO.isRoleDeleted(role_id)) {
                    passValidation = false;
                    errorMsg = "Role này đã bị xóa, vui lòng chọn role khác!";
                }
                if (!validateInteger(emp_id, false, false, true)
                        || !validateString(emp_account, -1)
                        || !validateString(password, -1)
                        || !validateString(emp_name, -1)
                        || !validateString(gender, -1)
                        || !validateString(dob_raw, -1)
                        || !validateString(phone_number, -1)
                        || !validateString(email, -1)
                        || !validateString(address, -1)
                        || !validateString(role_raw, -1)
                        || !validateString(status, -1)) {
                    passValidation = false;
                }

                if (passValidation) {
                    try {
                        Date dob = Date.valueOf(dob_raw);
                        int checkError = empDAO.edit(emp_id, emp_account, password, emp_name, gender, dob,
                                phone_number, email, address, role_id, status);
                        if (checkError < 1) {
                            passValidation = false;
                        }
                    } catch (Exception e) {
                        passValidation = false;
                    }
                }

            } else if (action.equalsIgnoreCase("delete")) {
                int id;
                try {
                    id = Integer.parseInt(request.getParameter("id"));
                } catch (NumberFormatException e) {
                    id = -1;
                    passValidation = false;
                }

                if (!validateInteger(id, false, false, true)) {
                    passValidation = false;
                }

                if (passValidation) {
                    int checkError = empDAO.delete(id);
                    if (checkError < 1) {
                        passValidation = false;
                    }
                }
            }
        }
        if (!passValidation) {
            request.setAttribute("errorMsg", errorMsg);
            if ("create".equalsIgnoreCase(action)) {
                request.getRequestDispatcher("/WEB-INF/employee/create.jsp").forward(request, response);
            } else if ("edit".equalsIgnoreCase(action)) {
                request.getRequestDispatcher("/WEB-INF/employee/edit.jsp").forward(request, response);
            }
            return;
        }
        response.sendRedirect(request.getContextPath()
                + "/employee?status=" + (passValidation ? "success" : "fail")
                + "&lastAction=" + addEDtoEverything(action));
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
