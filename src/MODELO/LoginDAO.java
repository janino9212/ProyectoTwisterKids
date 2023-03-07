
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MODELO;

import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author ferne
 */
public class LoginDAO {
   Connection con;
   PreparedStatement ps;
   ResultSet rs;
   Conexion cn = new Conexion();
   
   public Login log(String usuario,String contraseña){
       Login l = new Login();
       String sql ="SELECT * FROM usuario WHERE usuario =? and contraseña = ?";
       try {
          con = cn.getConnection();
          ps = con.prepareStatement(sql);
          ps.setString(1, usuario);
          ps.setString(2, contraseña);
          rs = ps.executeQuery();
           if (rs.next()) {
               l.setId(rs.getInt("id"));
               l.setUsuario(rs.getString("usuario"));
               l.setContraseña(rs.getString("contraseña"));
               
           }
       } catch (SQLException e) {
           System.out.println(e.toString());
       }
       return l;
   }
   
}
