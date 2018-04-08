/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data.servlet;

import data.tbl_room.Cart;
import data.tbl_room.tbl_roomDetailItem;
import data.tbl_room.tbl_roomErrSearch;
import data.tbl_room.tbl_roomErrSelectTypePrice;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author USER
 */
@WebServlet(name = "BookServlet", urlPatterns = {"/BookServlet"})
public class BookServlet extends HttpServlet {

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
        PrintWriter out = response.getWriter();
        String roomID = request.getParameter("roomID");
        String lastSearchValue = request.getParameter("lastSearchValue");
        int quantityHour = Integer.parseInt(request.getParameter("cboHourPrice"));
        int quantityDay = Integer.parseInt(request.getParameter("cboDayPrice"));
        float priceHour = Float.parseFloat(request.getParameter("hourPrice"));
        float priceDay = Float.parseFloat(request.getParameter("dayPrice"));
        String urlRewritting = "SearchServlet?"
                +"txtSearchValue="+lastSearchValue;
        try {
            /* TODO output your page here. You may use following sample code. */
            //1 den noi lay gio
            HttpSession session = request.getSession();
            //2 lay gio
            Cart cart = (Cart) session.getAttribute("CART");
            if(cart == null){
                cart = new Cart();
            }
            //3 xem gio do la loai PriceHour hay PriceDay va bo vao gio
            String chkPrice = request.getParameter("chkTypePrice");
            if(chkPrice.equals("hourChosen")){
                tbl_roomDetailItem item = new tbl_roomDetailItem(priceHour, priceDay, quantityHour, true);
                cart.addItemToCart(roomID, item);
            }else if(chkPrice.equals("dayChosen")){
                tbl_roomDetailItem item = new tbl_roomDetailItem(priceHour, priceDay, quantityDay, false);
                cart.addItemToCart(roomID, item);
            }
            //4.cap nhat lai scope
            session.setAttribute("CART", cart);
        }catch(NullPointerException ex){
            log("BookServlet _NullPointer:"+ex.getMessage());
            tbl_roomErrSelectTypePrice error = new tbl_roomErrSelectTypePrice("You must select hour or day Price before booking");
            request.setAttribute("ERRORTYPEPRICE", error);
        }
        finally{
            RequestDispatcher rd = request.getRequestDispatcher(urlRewritting);
            rd.forward(request, response);
            out.close();
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
        processRequest(request, response);
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
        processRequest(request, response);
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
