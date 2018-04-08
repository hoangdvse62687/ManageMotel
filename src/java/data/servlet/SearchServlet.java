/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data.servlet;

import data.tbl_room.tbl_roomSearchDAO;
import data.tbl_room.tbl_roomSearchDTO;
import data.tbl_room.Cart;
import data.tbl_room.tbl_roomDetailItem;
import data.tbl_room.tbl_roomErrSearch;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
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
@WebServlet(name = "SearchServlet", urlPatterns = {"/SearchServlet"})
public class SearchServlet extends HttpServlet {
    private final String searchPage = "search.jsp";
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
        String searchValue = request.getParameter("txtSearchValue");
        String url = searchPage;
        HttpSession session = request.getSession();
        Cart cart = (Cart)session.getAttribute("CART");
        try {
            /* TODO output your page here. You may use following sample code. */
            int floor;
            if(searchValue.trim().length() > 0){
                floor = Integer.parseInt(searchValue);
            }
            tbl_roomSearchDAO dao = new tbl_roomSearchDAO();
            dao.searchFloor(searchValue);
            List<tbl_roomSearchDTO> listRoom = dao.getListRoom();
            Map<String,tbl_roomDetailItem> items;
            if(cart != null){
               items = cart.getItems();
               if(items != null){
                   for(String key:items.keySet()){
                       for(int i=0;i < listRoom.size();i++){
                           if(listRoom.get(i).getRoomID().equals(key)){
                               listRoom.get(i).setIsBook(true);
                           }
                       }
                   }
               }
            }
            request.setAttribute("SEARCHRESULT", listRoom);
        }catch(SQLException ex){
            log("SearchServlet _SQL:"+ex.getMessage());
        }catch(NamingException ex){
            log("SearchServlet _Naming:"+ex.getMessage());
        }catch(NumberFormatException ex){
            log("SearchServlet _NumberFormat:"+ex.getMessage());
            tbl_roomErrSearch error = new tbl_roomErrSearch("Floor should be a number");
            request.setAttribute("ERRORSEARCH", error);
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
