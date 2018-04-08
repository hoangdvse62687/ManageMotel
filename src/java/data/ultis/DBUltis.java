/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data.ultis;

import java.sql.Connection;
import java.sql.SQLException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 *
 * @author USER
 */
public class DBUltis {
    public static Connection makeConnection()
    throws /*ClassNotFoundException*/SQLException,NamingException{
        Context cxt = new InitialContext();
        Context tomcatCxt = (Context) cxt.lookup("java:comp/env");
        DataSource ds = (DataSource)tomcatCxt.lookup("Manage_Motel");
        Connection con = ds.getConnection();
        return con;
    }
}
