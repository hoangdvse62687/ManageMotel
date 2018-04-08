/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data.tbl_orderdetail;

import data.ultis.DBUltis;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.naming.NamingException;

/**
 *
 * @author USER
 */
public class tbl_orderdetailInsertOrderDetailDAO {
    public boolean insertOrderDetail(String roomID,float price,float total,String orderID,boolean hourPrice)
    throws SQLException,NamingException{
        Connection con = null;
        PreparedStatement stm = null;
        
        try {
            con = DBUltis.makeConnection();
            if(con != null){
                String sql = "Insert into tbl_orderDetail (roomID,price,total,orderID,hourPrice)"
                        + " Values(?,?,?,?,?)";
                stm = con.prepareStatement(sql);
                stm.setString(1, roomID);
                stm.setFloat(2, price);
                stm.setFloat(3, total);
                stm.setString(4, orderID);
                stm.setBoolean(5, hourPrice);
                int row = stm.executeUpdate();
                
                if(row > 0){
                    return true;
                }
            }
        } finally {
            if(stm != null){
                stm.close();
            }
            if(con != null){
                con.close();
            }
        }
        return false;
    }
}
