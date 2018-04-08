/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data.servlet;

import data.tbl_order.tbl_orderDateFromRoomDAO;
import data.tbl_order.tbl_orderDateRoomDTO;
import data.tbl_room.Cart;
import data.tbl_room.tbl_roomDetailItem;
import data.tbl_order.tbl_orderErrValidDateRoom;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
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
@WebServlet(name = "rentRoomServlet", urlPatterns = {"/rentRoomServlet"})
public class rentRoomServlet extends HttpServlet {
    private final String viewCartPage = "viewCart.jsp";
    private final String confirmPage = "confirm.html";
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
        String url = viewCartPage;
        String dateFrom = request.getParameter("dateFrom");
        String hourFrom = request.getParameter("hourFrom");
        String TimeFrom = dateFrom +" "+hourFrom+":00:00";
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        HttpSession session = request.getSession(false);
        Date todayDate = new Date();
        try {
            /* TODO output your page here. You may use following sample code. */
            Date fromDate = df.parse(TimeFrom);// lay fromDate cua customer set ra
            if(fromDate.compareTo(todayDate) < 0){// kiem tra ngay dat co phai la ngay trong qua khu hay khong
                tbl_orderErrValidDateRoom error = new tbl_orderErrValidDateRoom();
                error.setErrSetDateInPast("You have to chose a future day");
                request.setAttribute("ERRORSETDATE", error);
                return;
            }
            if(session != null){
                Cart cart = (Cart)session.getAttribute("CART");
                if(cart != null){
                    Map<String,tbl_roomDetailItem> items = cart.getItems();
                    if(items != null){
                        for(String key:items.keySet()){//lay du lieu tu trong cart ra kiem tra valid date set
                            tbl_orderDateFromRoomDAO dao = new tbl_orderDateFromRoomDAO();
                            dao.getDate(key);
                            List<tbl_orderDateRoomDTO> listDateRoom = dao.getListRoom();
                            if(listDateRoom != null){// neu room da dat ton tai co date thi check date co trung khong
                                for(int i = 0;i < listDateRoom.size();i++){
                                    Calendar c = Calendar.getInstance();
                                    Date toDate = new Date();//lay toDate cua customer ra
                                    if(items.get(key).isIsHourPrice()){//kiem tra loai phong la hour or day?
                                        c.setTime(fromDate);//hour thi toDate bao gom fromDate + Hour * quantityHour
                                        c.add(Calendar.HOUR, items.get(key).getQuantiy());
                                        toDate = c.getTime();
                                    }else if(!items.get(key).isIsHourPrice()){
                                        c.setTime(fromDate);//day thi toDate gom fromDate + Date * quantityDay
                                        c.add(Calendar.DATE, items.get(key).getQuantiy());
                                        toDate = c.getTime();
                                    }
                                    Date toDateRoom;//bat dau lay list room chua toDate va fromDate cua room 
                                    Date fromDateRoom = df.parse(listDateRoom.get(i).getFromDate());
                                    if(listDateRoom.get(i).isIsHourPrice()){
                                        float quantityHour = (int)listDateRoom.get(i).getTotal() / listDateRoom.get(i).getPrice();
                                        toDateRoom = df.parse(listDateRoom.get(i).getFromDate());
                                        c.setTime(toDateRoom);
                                        c.add(Calendar.HOUR, (int)quantityHour);//neu room dc dat la Hour thi toDate cua room
                                        toDateRoom = c.getTime();//toDateRoom = fromDate + so gio` duoc dat
                                    }else{
                                        float quantityDay =(int)listDateRoom.get(i).getTotal() / listDateRoom.get(i).getPrice();
                                        toDateRoom = df.parse(listDateRoom.get(i).getFromDate());
                                        c.setTime(toDateRoom);
                                        c.add(Calendar.DATE, (int)quantityDay);//neu room dc dat la date thi toDate cua room
                                        toDateRoom = c.getTime();// toDateroom = fromDate + so days duoc dat
                                    }
                                    if(fromDate.compareTo(toDateRoom) <= 0 && fromDate.compareTo(fromDateRoom) >= 0){
                                        //so sanh neu nhu diem thoi gian fromDate cua customer nam trong khoang tu 
                                        //fromDateRoom -> toDateRoom bao loi vi trung thoi gian va khong lam tiep 
                                        tbl_orderErrValidDateRoom error = new tbl_orderErrValidDateRoom();
                                        error.setErrSetDateduplicate("The Date time room "+ key +" you set is duplicate with another"
                                                + ".Please try another time");
                                        request.setAttribute("ERRORSETDATE", error);
                                        return;
                                    }else if(toDate.compareTo(fromDateRoom) >= 0 && toDate.compareTo(toDateRoom) <= 0){
                                        //so sanh neu nhu diem thoi gian toDate cua customer nam trong khoang tu 
                                        //fromDateRoom -> toDateRoom bao loi vi trung thoi gian va khong lam tiep
                                        tbl_orderErrValidDateRoom error = new tbl_orderErrValidDateRoom();
                                        error.setErrSetDateduplicate("The Date time room "+ key +" you set is duplicate with another"
                                                + ".Please try another time");
                                        request.setAttribute("ERRORSETDATE", error);
                                        return;
                                    }else if(toDate.compareTo(toDateRoom) >= 0 && fromDate.compareTo(fromDateRoom) <= 0){
                                        //so sanh neu nhu diem thoi gian cua toDate va fromDate cua customer o vi tri
                                        // fromDate - fromDateRoom -> toDateRoom - toDate bao loi vi trung thoi gian 
                                        tbl_orderErrValidDateRoom error = new tbl_orderErrValidDateRoom();
                                        error.setErrSetDateduplicate("The Date time room "+ key +" you set is duplicate with another"
                                                + ".Please try another time");
                                        request.setAttribute("ERRORSETDATE", error);
                                        return;
                                    }
                                }
                            }
                        }
                    }//end items
                }//end cart
                url = confirmPage;
                session.setAttribute("DATE", fromDate);
            }//end session
            
        }catch(SQLException ex){
            log("rentRoomServlet SQL:"+ex.getMessage());
        }catch(NamingException ex){
            log("rentRoomServlet _Naming:"+ex.getMessage());
        }
        catch(ParseException ex){
            log("rentRoomServlet _ParseEx:"+ ex.getMessage());
            tbl_orderErrValidDateRoom error = new tbl_orderErrValidDateRoom();
            error.setErrDateFormFormat("Please select right the date and time before rent room");
            request.setAttribute("ERRORSETDATE", error);
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
