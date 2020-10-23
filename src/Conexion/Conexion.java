/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Ronal
 */
public class Conexion 
{

    public static Connection  getConexion()
    {
    
        String conexionUrl  = "jdbc:sqlserver://localhost;"
                +"database=Sistema_ventas;"
                +"user=sa;"
                +"password=12345;"
                +"loginTimeout=30";
        try {
            Connection conn  = DriverManager.getConnection(conexionUrl);
             System.out.println("Conexion establecita");
            return conn;
           
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Conexion fail");
            System.out.println(ex.toString());
            return null;
        }
        
        
    }
    
  
    
    
public static void main(String args[]) 
{
Conexion.getConexion();
}  
}
  

