/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data.tbl_order;

import data.ultis.DBUltis;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.naming.NamingException;

/**
 *
 * @author USER
 */
public class tbl_orderListOrderDAO {
    ArrayList<String> listOrderID;

    public ArrayList<String> getListOrderID() {
        return listOrderID;
    }
    
    public void listOrder()
    throws SQLException,NamingException{
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        
        try {
            con = DBUltis.makeConnection();
            if(con != null){
                String sql = "Select orderID From tbl_order";
                stm = con.prepareStatement(sql);
                rs = stm.executeQuery();
                while(rs.next()){
                    if(listOrderID == null){
                        listOrderID = new ArrayList<>();
                    }
                    String orderID = rs.getString("orderID");
                    listOrderID.add(orderID);
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
