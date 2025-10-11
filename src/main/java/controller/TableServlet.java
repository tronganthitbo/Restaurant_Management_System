/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import static constant.CommonFunction.addEDtoEverything;
import static constant.CommonFunction.getTotalPages;
import static constant.CommonFunction.stringConvertDateTime;
import static constant.CommonFunction.validateInteger;
import static constant.CommonFunction.validateString;
import constant.Constants;
import dao.TableDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author Dai Minh Nhu - CE190213
 */
@WebServlet(name = "TableServlet", urlPatterns = {"/table"})
public class TableServlet extends HttpServlet {

    TableDAO tableDAO = new TableDAO();

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
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet RoleServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet RoleServlet at " + request.getContextPath() + "</h1>");
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

        if (!validateString(view, -1) || view.equalsIgnoreCase("list")) {
            namepage = "list";
        } else if (view.equalsIgnoreCase("create")) {
            namepage = "create";
        } else if (view.equalsIgnoreCase("edit")) {
            namepage = "edit";

            int id;

            try {
                id = Integer.parseInt(request.getParameter("id"));
            } catch (NumberFormatException e) {
                id = -1;
            }

            request.setAttribute("currentTable", tableDAO.getElementByID(id));
        } else if (view.equalsIgnoreCase("delete")) {
            namepage = "delete";
        }

        int page;
        int totalPages = getTotalPages(tableDAO.countItem());

        try {
            page = Integer.parseInt(request.getParameter("page"));
        } catch (NumberFormatException e) {
            page = 1;
        }

        request.setAttribute("totalPages", totalPages);
        request.setAttribute("tablesList", tableDAO.getAll(page));

        request.getRequestDispatcher("/WEB-INF/table/" + namepage + ".jsp").forward(request, response);
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
        if (action != null && !action.isEmpty()) {

            if (action.equalsIgnoreCase("create")) {
                String table_number = request.getParameter("number");
                int table_capacity;

                try {
                    table_capacity = Integer.parseInt(request.getParameter("table_capacity"));
                } catch (NumberFormatException e) {
                    table_capacity = -1;

                    passValidation = false;
                }

//validate
                if (!validateString(table_number, 10)
                        || !validateInteger(table_capacity, false, false, true)) {
                    passValidation = false;
                }
//end
                if (passValidation == true) {
                    if (tableDAO.create(table_number, table_capacity) >= 1) {
                    } else {
                        passValidation = false;
                    }
                }

            } else if (action.equalsIgnoreCase("edit")) {

                int id;
                String table_number = request.getParameter("number");
                int table_capacity;

                try {

                } catch (NumberFormatException e) {
                    id = -1;

                    passValidation = false;
                }
                try {
                    id = Integer.parseInt(request.getParameter("id"));
                    table_capacity = Integer.parseInt(request.getParameter("table_capacity"));
                } catch (NumberFormatException e) {
                    id = -1;
                    table_capacity = -1;

                    passValidation = false;
                }

//validate
                if (!validateInteger(id, false, false, true)
                        || !validateString(table_number, 10)
                        || !validateInteger(table_capacity, false, false, true)) {
                    passValidation = false;
                }
//end
                if (passValidation == true) {
                    int checkError = tableDAO.edit(id, table_number, table_capacity);

                    if (checkError >= 1) {

                    } else {
                        if (checkError - Constants.DUPLICATE_KEY == 0) {                //check trung code/key
                            System.err.println("DUPLICATE_KEY");
                        } else if (checkError - Constants.FOREIGN_KEY_VIOLATION == 0) {
                            System.err.println("FOREIGN_KEY_VIOLATION");
                        } else if (checkError - Constants.NULL_INSERT_VIOLATION == 0) {
                            System.err.println("NULL_INSERT_VIOLATION");
                        }

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

//validate
                if (!validateInteger(id, false, false, true)) {
                    passValidation = false;
                }
//end

                int checkError = tableDAO.delete(id);

                if (checkError >= 1) {

                } else {
                    if (checkError - Constants.DUPLICATE_KEY == 0) {                //check trung code/key
                        System.err.println("DUPLICATE_KEY");
                    } else if (checkError - Constants.FOREIGN_KEY_VIOLATION == 0) {
                        System.err.println("FOREIGN_KEY_VIOLATION");
                    } else if (checkError - Constants.NULL_INSERT_VIOLATION == 0) {
                        System.err.println("NULL_INSERT_VIOLATION");
                    }

                    passValidation = false;
                }
            }
        }

        response.sendRedirect(request.getContextPath() + "/table?" + "status=" + (passValidation ? "success" : "fail") + "&lastAction=" + addEDtoEverything(action));
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
