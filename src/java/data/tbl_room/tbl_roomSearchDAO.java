/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data.tbl_room;

import data.ultis.DBUltis;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;

/**
 *
 * @author USER
 */
public class tbl_roomSearchDAO {
    List<tbl_roomSearchDTO> listRoom;
    public List<tbl_roomSearchDTO> getListRoom(){
        return this.listRoom;
    }
    public void searchFloor(String valueSearch)
    throws SQLException,NamingException{
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        
        try {
            con = DBUltis.makeConnection();
            if(con != null){
                if(valueSearch.trim().length() == 0){
                    String sql = "Select * From tbl_room";
                    stm = con.prepareStatement(sql);
                    rs = stm.executeQuery();
                    while(rs.next()){
                        String roomID = rs.getString("roomID");
                        String description = rs.getString("description");
                        float hourPrice = rs.getFloat("hourPrice");
                        float dayPrice = rs.getFloat("dayPrice");
                        int floor = rs.getInt("floor");
                        tbl_roomSearchDTO room = new tbl_roomSearchDTO(roomID, description, hourPrice, dayPrice, floor,false);
                        if(listRoom == null){
                            listRoom = new ArrayList<tbl_roomSearchDTO>();
                        }
                        listRoom.add(room);
                    }
                }else{
                    String sql = "Select * From tbl_room Where floor = ?";
                    stm = con.prepareStatement(sql);
                    stm.setInt(1, Integer.parseInt(valueSearch));
                    rs = stm.executeQuery();
                    while(rs.next()){
                        String roomID = rs.getString("roomID");
                        String description = rs.getString("description");
                        float hourPrice = rs.getFloat("hourPrice");
                        float dayPrice = rs.getFloat("dayPrice");
                        int floor = rs.getInt("floor");
                        tbl_roomSearchDTO room = new tbl_roomSearchDTO(roomID, description, hourPrice, dayPrice, floor,false);
                        if(listRoom == null){
                            listRoom = new ArrayList<tbl_roomSearchDTO>();
                        }
                        listRoom.add(room);
                    }
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
