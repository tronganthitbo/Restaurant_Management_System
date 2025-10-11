/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.VoucherDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.sql.Date;

/**
 *
 * @author PHAT
 */
@WebServlet(name = "VoucherServlet", urlPatterns = {"/voucher"})
public class VoucherServlet extends HttpServlet {

    VoucherDAO dao = new VoucherDAO();
    int PAGE_SIZE = 10;

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
            out.println("<title>Servlet VoucherServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet VoucherServlet at " + request.getContextPath() + "</h1>");
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
        String view = request.getParameter("view");
        String pageStr = request.getParameter("page");
        int page = 1;
        if (pageStr != null) try {
            page = Integer.parseInt(pageStr);
        } catch (NumberFormatException e) {
            page = 1;
        }

        if ("create".equalsIgnoreCase(view)) {
            request.getRequestDispatcher("/WEB-INF/voucher/create.jsp").forward(request, response);
        } else if ("edit".equalsIgnoreCase(view)) {
            int id = Integer.parseInt(request.getParameter("id"));
            request.setAttribute("currentVoucher", dao.getById(id));
            request.getRequestDispatcher("/WEB-INF/voucher/edit.jsp").forward(request, response);
        } else {
            request.setAttribute("voucherList", dao.getAll(page, PAGE_SIZE));
            request.getRequestDispatcher("/WEB-INF/voucher/list.jsp").forward(request, response);
        }
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
        boolean success = false;
        if ("create".equalsIgnoreCase(action)) {
            success = dao.create(
                    request.getParameter("voucher_code"),
                    request.getParameter("voucher_name"),
                    request.getParameter("discount_type"),
                    new BigDecimal(request.getParameter("discount_value")),
                    Integer.parseInt(request.getParameter("quantity")),
                    Date.valueOf(request.getParameter("start_date")),
                    Date.valueOf(request.getParameter("end_date")),
                    request.getParameter("status")
            ) > 0;
        } else if ("edit".equalsIgnoreCase(action)) {
            success = dao.edit(
                    Integer.parseInt(request.getParameter("voucher_id")),
                    request.getParameter("voucher_code"),
                    request.getParameter("voucher_name"),
                    request.getParameter("discount_type"),
                    new BigDecimal(request.getParameter("discount_value")),
                    Integer.parseInt(request.getParameter("quantity")),
                    Date.valueOf(request.getParameter("start_date")),
                    Date.valueOf(request.getParameter("end_date")),
                    request.getParameter("status")
            ) > 0;
        } else if ("delete".equalsIgnoreCase(action)) {
            success = dao.delete(Integer.parseInt(request.getParameter("id"))) > 0;
        }
        response.sendRedirect(request.getContextPath() + "/voucher?status=" + (success ? "success" : "fail"));
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
