/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TestFormSection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

/**
 *
 * @author trainee
 */
public class DBCon {
    
    public static void main(String args[]) throws Exception
    {
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        String dbURL = "jdbc:sqlserver://192.168.2.19;databaseName=KovairTraining";
        String user = "kt";
        String pass = "kovair@123";
        Connection conn = DriverManager.getConnection(dbURL, user, pass);
        try{
            if(conn!=null)
            {
                System.out.println("Connected");
            }
            else
            {
                System.out.println("Not Connected");
            }
            
        }catch(Exception e)
        {
            System.out.println(e.toString());
        }
        
        Statement st=conn.createStatement();
        st.executeUpdate("INSERT INTO TESTDB VALUES (2,'SARMISTHA','FAILED')");
    }
    
}
