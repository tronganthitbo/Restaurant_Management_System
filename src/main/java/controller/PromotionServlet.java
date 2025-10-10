/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import constant.CommonFunction;
import static constant.CommonFunction.*;
import constant.Constants;
import dao.PromotionDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import model.Promotion;

/**
 *
 * @author Dai Minh Nhu - CE190213
 */
@WebServlet(name = "PromotionServlet", urlPatterns = {"/promotion"})
public class PromotionServlet extends HttpServlet {

    PromotionDAO promotionDAO = new PromotionDAO();

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
            out.println("<title>Servlet PromotionServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet PromotionServlet at " + request.getContextPath() + "</h1>");
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
        String typePage = "";

        int page;
        int totalPages = getTotalPages(promotionDAO.countItem());
        
        try {
            page = Integer.parseInt(request.getParameter("page"));
        } catch (NumberFormatException e) {
            page = 1;
        }

        List<Promotion> promotionsList = promotionDAO.getAll(page);

        if (CommonFunction.isEmptyString(view) || view.equalsIgnoreCase("list")) {
            typePage = "list";

            request.setAttribute("totalPages", totalPages);
        } else if (view.equalsIgnoreCase("create")) {
            typePage = "create";
        } else if (view.equalsIgnoreCase("remove")) {
            int id;
            try {
                id = Integer.parseInt(request.getParameter("id"));
            } catch (NumberFormatException e) {
                id = -1;
            }

            request.setAttribute("currentPromotion", promotionDAO.getElementByID(id));
            typePage = "remove";
        } else if (view.equalsIgnoreCase("edit")) {
            int id;
            try {
                id = Integer.parseInt(request.getParameter("id"));
            } catch (NumberFormatException e) {
                id = -1;
            }

            request.setAttribute("currentPromotion", promotionDAO.getElementByID(id));
            typePage = "edit";
        }

        request.setAttribute("promotionsList", promotionsList);
        request.getRequestDispatcher("/WEB-INF/promotion/" + typePage + ".jsp").forward(request, response);
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

//        Sale sale = new Sale(0, name, discount, typeOfDiscount, amount, coHanSuDung, dateStart, dateEnd);
//        System.out.println(sale);
        String action = request.getParameter("action");
        boolean passValidation = true;
        if (action != null && !action.isEmpty()) {

            if (action.equalsIgnoreCase("create")) {

                String code = request.getParameter("code");
                String name = request.getParameter("name");
                String typeOfDiscount = request.getParameter("typeOfDiscount");
                int discountValue;
                int quantity;
                boolean hasExpiry = request.getParameter("hasExpiry") != null;
                String dateStart = null;
                String dateEnd = null;

                try {
                    discountValue = Integer.parseInt(request.getParameter("discountValue"));
                    quantity = Integer.parseInt(request.getParameter("quantity"));
                } catch (NumberFormatException e) {
                    discountValue = -1;
                    quantity = -1;
                    passValidation = false;
                }

//validate
                if (!validateString(code)
                        || !validateString(name)
                        || !validateString(typeOfDiscount)
                        || !validateInteger(discountValue, false, false, true)
                        || !validateInteger(quantity, false, false, true)) {
                    passValidation = false;
                }

                if (typeOfDiscount.equalsIgnoreCase("percent") && discountValue > 100) {
                    discountValue = 100;
                }
//end

                if (hasExpiry) {
                    dateStart = request.getParameter("dateStart");
                    dateEnd = request.getParameter("dateEnd");

                    dateStart = stringConvertDateTime(dateStart);
                    dateEnd = stringConvertDateTime(dateEnd);

                    if (dateStart == null || dateEnd == null) {
                        hasExpiry = false;
                    }
                }

                if (promotionDAO.create(code, name, typeOfDiscount, discountValue, quantity, dateStart, dateEnd) >= 1) {

                } else {
                    passValidation = false;
                }

            } else if (action.equalsIgnoreCase("remove")) {
                int id;
                try {
                    id = Integer.parseInt(request.getParameter("id"));
                } catch (NumberFormatException nfe) {
                    id = -1;
                }

                if (promotionDAO.remove(id) >= 0) {

                } else {
                    passValidation = false;
                }

            } else if (action.equalsIgnoreCase("edit")) {
                int promo_id;
                String promo_code = request.getParameter("code");
                String promo_name = request.getParameter("name");
                String discount_type = request.getParameter("typeOfDiscount");
                int discount_value;
                int quantity;
                boolean hasExpiry = request.getParameter("hasExpiry") != null;
                String start_date = null;
                String end_date = null;

                try {
                    promo_id = Integer.parseInt(request.getParameter("id"));
                    discount_value = Integer.parseInt(request.getParameter("value"));
                    quantity = Integer.parseInt(request.getParameter("quantity"));
                } catch (NumberFormatException e) {
                    promo_id = -1;
                    discount_value = -1;
                    quantity = -1;

                    passValidation = false;
                }

//validate
                if (!validateString(promo_code)
                        || !validateString(promo_name)
                        || !validateString(discount_type)
                        || !validateInteger(promo_id, false, false, true)
                        || !validateInteger(discount_value, false, false, true)
                        || !validateInteger(quantity, false, false, true)) {
                    passValidation = false;
                }

                if (discount_type.equalsIgnoreCase("percent") && discount_value > 100) {
                    discount_value = 100;
                }
//end

                if (hasExpiry) {
                    start_date = request.getParameter("dateStart");
                    end_date = request.getParameter("dateEnd");

                    start_date = stringConvertDateTime(start_date);
                    end_date = stringConvertDateTime(end_date);

                    if (start_date == null || end_date == null) {
                        hasExpiry = false;
                    }
                }

                int checkError = promotionDAO.update(promo_id, promo_code, promo_name, discount_type, discount_value, quantity, start_date, end_date);

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

        response.sendRedirect(request.getContextPath() + "/promotion?" + "status=" + (passValidation ? "success" : "fail") + "&lastAction=" + addEDtoEverything(action));
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
