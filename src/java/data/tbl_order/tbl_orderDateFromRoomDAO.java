/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data.tbl_order;

import data.tbl_order.tbl_orderDateRoomDTO;
import data.ultis.DBUltis;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.naming.NamingException;

/**
 *
 * @author USER
 */
public class tbl_orderDateFromRoomDAO {
    List<tbl_orderDateRoomDTO> listRoom;

    public List<tbl_orderDateRoomDTO> getListRoom() {
        return listRoom;
    }

    public void setListRoom(List<tbl_orderDateRoomDTO> listRoom) {
        this.listRoom = listRoom;
    }
    
    public void getDate(String roomID)
    throws SQLException,NamingException{
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        
        try {
            con = DBUltis.makeConnection();
            if(con != null){
                String sql = "Select tbl_order.fromDate,tbl_order.toDate,tbl_orderDetail.hourPrice"
                        + ",tbl_orderDetail.total,tbl_orderDetail.price "
                        + "From tbl_order,tbl_orderDetail "
                        + "Where tbl_order.orderID = tbl_orderDetail.orderID and tbl_orderDetail.roomID = ?";
                stm = con.prepareStatement(sql);
                stm.setString(1,roomID);
                rs = stm.executeQuery();
                while(rs.next()){
                    String fromDate = rs.getString("fromDate");
                    String toDate = rs.getString("toDate");
                    float price = rs.getFloat("price");
                    float total = rs.getFloat("total");
                    boolean isHourPrice = rs.getBoolean("hourPrice");                    
                    tbl_orderDateRoomDTO dto = new tbl_orderDateRoomDTO(roomID, fromDate, toDate, price, total, isHourPrice);
                    if(listRoom == null){
                        listRoom = new ArrayList<>();
                    }
                    listRoom.add(dto);
                }
            }
        } finally {
            if(rs != null){
                rs.close();
            }
            if(stm != null){
                stm.close();
            }
            if(con != null){
                con.close();
            }
        }
    
    }
}
