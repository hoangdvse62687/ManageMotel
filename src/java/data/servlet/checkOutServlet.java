/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data.servlet;

import data.tbl_order.tbl_orderInsertOrderDAO;
import data.tbl_room.Cart;
import data.tbl_room.tbl_roomDetailItem;
import data.tbl_order.tbl_orderErrConfirmForm;
import data.tbl_order.tbl_orderListOrderDAO;
import data.tbl_order.tbl_orderOrderDetailCart;
import data.tbl_orderdetail.tbl_orderdetailInsertOrderDetailDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import javax.naming.NamingException;
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
@WebServlet(name = "checkOutServlet", urlPatterns = {"/checkOutServlet"})
public class checkOutServlet extends HttpServlet {
    private final String errConfirmFormPage = "confirm.jsp";
    private final String errPage = "errCheckOut.html";
    private final String viewOrder = "viewOrder.jsp";
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
        HttpSession session = request.getSession(false);
        String customerName = request.getParameter("txtCustomerName");
        String cardID = request.getParameter("txtCardID");
        boolean isValid = true;
        String url = errPage;
        try {
            /* TODO output your page here. You may use following sample code. */
            if(customerName.trim().length() < 5 || customerName.trim().length() > 50){
                isValid = false;
                tbl_orderErrConfirmForm error = new tbl_orderErrConfirmForm();
                error.setErrCustomerNameLength("Your name is required 5-50");
                request.setAttribute("ERRORCONFIRM", error);
            }
            if(cardID.trim().length() < 8 || cardID.trim().length() > 10){
                isValid = false;
                tbl_orderErrConfirmForm error = new tbl_orderErrConfirmForm();
                error.setErrCarDIDLength("Your cardID is required 8-10 number");
                request.setAttribute("ERRORCONFIRM", error);
            }
            int checkCardID = Integer.parseInt(cardID);
            if(!isValid){
                url = errConfirmFormPage;
            }
            if(isValid){
                if(session != null){
                    Cart cart = (Cart)session.getAttribute("CART");
                    if(cart != null){
                        Map<String,tbl_roomDetailItem> items = cart.getItems();
                        if(items != null){
                            Date todayDate = new Date();
                            DateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                            String orderDate = df.format(todayDate);
                            Date fromdate = (Date)session.getAttribute("DATE");
                            String fromDate = df.format(fromdate);
                            int quantityHour = 0;
                            int quantityDay = 0;
                            for(String key:items.keySet()){
                                if(items.get(key).isIsHourPrice()){// chuan bi fromDate va toDate de insert
                                    if(quantityHour < items.get(key).getQuantiy()){
                                        quantityHour = items.get(key).getQuantiy();
                                    }
                                }else{
                                    if(quantityDay < items.get(key).getQuantiy()){
                                        quantityDay = items.get(key).getQuantiy();
                                    }
                                }
                            }
                            Calendar c = Calendar.getInstance();
                            c.setTime(fromdate);
                            if(quantityDay != 0){
                                c.add(Calendar.DATE, quantityDay);
                            }else{
                                c.add(Calendar.HOUR, quantityHour);
                            }
                            fromdate = c.getTime();
                            String toDate = df.format(fromdate);
                            tbl_orderListOrderDAO dao = new tbl_orderListOrderDAO();//lay ma orderID cua th cuoi cung ra
                            dao.listOrder();//va cong them 1 de lay ma orderID moi
                            ArrayList<String> listOrderID = dao.getListOrderID();
                            String orderID = "OD0";
                            if(listOrderID != null){
                                String lastOrderID = listOrderID.get(listOrderID.size()-1);
                                int numberID = Integer.parseInt(lastOrderID.substring(2));
                                numberID++;
                                orderID = "OD" + numberID;
                            }
                            float totalPrice = cart.getTotalPrice();
                            tbl_orderInsertOrderDAO dao1 = new tbl_orderInsertOrderDAO();
                            boolean result = dao1.insertOrder(orderID, orderDate, fromDate, 
                                    toDate, totalPrice, customerName, cardID);//insert tbl_order
                            if(result){
                                tbl_orderdetailInsertOrderDetailDAO dao2 = new tbl_orderdetailInsertOrderDetailDAO();
                                for(String key:items.keySet()){//insert tbl_orderDetail
                                    if(items.get(key).isIsHourPrice()){
                                        dao2.insertOrderDetail(key, items.get(key).getHourPrice(), 
                                            items.get(key).getQuantiy() * items.get(key).getHourPrice()
                                            , orderID, true);
                                    }
                                    if(!items.get(key).isIsHourPrice()){
                                        dao2.insertOrderDetail(key, items.get(key).getDayPrice(),
                                               items.get(key).getQuantiy() * items.get(key).getDayPrice() ,
                                               orderID, false);
                                    }
                                }
                                url = viewOrder;
                                Cart orderCart = cart;
                                request.setAttribute("ORDERCART", orderCart);//chiet suat du lieu de bieu dien view Order cho customer
                                tbl_orderOrderDetailCart orderDetailCart = new tbl_orderOrderDetailCart(customerName, orderDate, fromDate, toDate);
                                request.setAttribute("ORDERDETAILCART", orderDetailCart);
                                session.removeAttribute("CART");
                            }
                        }//end items
                    }//end cart
                }//end Session
            }
        }catch(SQLException ex){
            log("checkOutServlet _SQL:"+ex.getMessage());
        }catch(NamingException ex){
            log("checkOutServlet _Naming:"+ex.getMessage());
        }catch(NumberFormatException ex){
            log("checkOutServlet _NumberFormat:"+ex.getMessage());
            tbl_orderErrConfirmForm error = new tbl_orderErrConfirmForm();
            error.setErrCardIDformat("Card ID is required a number");
            request.setAttribute("ERRORCONFIRM", error);
            url = errConfirmFormPage;
        }
        finally{
            RequestDispatcher rd = request.getRequestDispatcher(url);
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
