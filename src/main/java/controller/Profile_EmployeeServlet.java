/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import constant.HashUtil;
import dao.EmployeeDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.Date;
import model.Employee;

/**
 *
 * @author PHAT
 */
@WebServlet(name = "Profile_EmployeeServlet", urlPatterns = {"/profileEmployee"})
public class Profile_EmployeeServlet extends HttpServlet {

    private EmployeeDAO employeeDAO = new EmployeeDAO();

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
            out.println("<title>Servlet Profile_EmployeeServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Profile_EmployeeServlet at " + request.getContextPath() + "</h1>");
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
//        int empId;
//        try {
//            empId = Integer.parseInt(request.getParameter("id"));
//        } catch (NumberFormatException e) {
//            response.sendRedirect(request.getContextPath() + "/login");
//            return;
//        }
        Employee currentUser = (Employee) request.getSession().getAttribute("currentUser");
        if (currentUser == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }
        request.setAttribute("currentUser", currentUser);
        request.getRequestDispatcher("/WEB-INF/profile/profile.jsp").forward(request, response);
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
//        int empId;
//        try {
//            empId = Integer.parseInt(request.getParameter("id"));
//        } catch (NumberFormatException e) {
//            response.sendRedirect(request.getContextPath() + "/login");
//            return;
//        }
        Employee currentUser = (Employee) request.getSession().getAttribute("currentUser");
        if (currentUser == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }
        int empId = currentUser.getEmpId();
        String action = request.getParameter("action");
        boolean success = true;
        String message = "";

        if ("updateInfo".equalsIgnoreCase(action)) {
            String empAccount = request.getParameter("empAccount");
            String empName = request.getParameter("empName");
            String gender = request.getParameter("gender");
            String dobStr = request.getParameter("dob"); // yyyy-mm-dd
            String phoneNumber = request.getParameter("phone");
            String email = request.getParameter("email");
            String address = request.getParameter("address");

            Date dob = null;
            try {
                if (dobStr != null && !dobStr.isEmpty()) {
                    dob = Date.valueOf(dobStr);
                }
            } catch (IllegalArgumentException e) {
                success = false;
                message = "Invalid date format.";
            }

            if (success) {
                int result = employeeDAO.edit(empId, empAccount, empName, gender, dob, phoneNumber, email, address);
                success = result >= 1;
                message = success ? "Update information successful." : "Update failed.";
            }

        } else if ("changePassword".equalsIgnoreCase(action)) {
            String oldPassword = request.getParameter("oldPassword");
            String newPassword = request.getParameter("newPassword");

            String currentHashed = employeeDAO.getElementByID(empId).getPassword();
            if (!currentHashed.equals(HashUtil.toMD5(oldPassword))) {
                success = false;
                message = "Old password is incorrect.";
            } else {
                int result = employeeDAO.edit(empId, HashUtil.toMD5(newPassword));
                success = result >= 1;
                message = success ? "Password changed successfully." : "Password change failed.";
            }
        }

        request.setAttribute("profilePopupStatus", success);
        request.setAttribute("profilePopupMessage", message);
//        request.setAttribute("currentUser", employeeDAO.getElementByID(empId));
        Employee updatedUser = employeeDAO.getElementByID(empId);
        request.getSession().setAttribute("currentUser", updatedUser);
        request.setAttribute("currentUser", updatedUser);
        request.getRequestDispatcher("/WEB-INF/profile/profile.jsp").forward(request, response);
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
