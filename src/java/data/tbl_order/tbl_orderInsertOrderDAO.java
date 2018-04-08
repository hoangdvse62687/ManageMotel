/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data.tbl_order;

import data.ultis.DBUltis;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.naming.NamingException;

/**
 *
 * @author USER
 */
public class tbl_orderInsertOrderDAO {
    public boolean insertOrder(String orderID,String orderDate,String fromDate
    ,String toDate,float totalPrice,String customerName,String cardID)
    throws SQLException,NamingException{
        Connection con = null;
        PreparedStatement stm = null;
        
        try {
            con = DBUltis.makeConnection();
            if(con != null){
                String sql = "Insert into tbl_order Values(?,?,?,?,?,?,?)";
                stm = con.prepareStatement(sql);
                stm.setString(1, orderID);
                stm.setString(2, orderDate);
                stm.setString(3, fromDate);
                stm.setString(4, toDate);
                stm.setFloat(5, totalPrice);
                stm.setString(6, customerName);
                stm.setString(7, cardID);
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
