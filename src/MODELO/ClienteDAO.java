/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MODELO;

import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class ClienteDAO {

    Conexion cn = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;

    public boolean RegistrarCliente(Cliente cl) {
        String sql = "INSERT INTO cliente (documento,nombre,correo,telefono) VALUES(?,?,?,?) ";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, cl.getDocumento());
            ps.setString(2, cl.getNombre());
            ps.setString(3, cl.getCorreo());
            ps.setString(4, cl.getTelefono());
            ps.execute();
            return true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.toString());
            return false;
        } finally {
            try {
                con.close();

            } catch (SQLException e) {
                System.out.println(e.toString());
            }
        }

    }

    public List Listarcliente() {
        List<Cliente> ListaCl = new ArrayList();
        String sql = "SELECT * FROM cliente";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Cliente cl = new Cliente();
                cl.setId(rs.getInt("id"));
                cl.setDocumento(rs.getString("documento"));
                cl.setNombre(rs.getString("nombre"));
                cl.setCorreo(rs.getString("correo"));
                cl.setTelefono(rs.getString("telefono"));
                ListaCl.add(cl);
            }

        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return ListaCl;
    }

    public boolean EliminarCliente(int id) {
        String sql = "DELETE FROM cliente where id = ?";
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ps.execute();
            return true;
        } catch (SQLException e) {
            System.out.println("estoy en cliente dao");
            System.out.println(e.toString());
            return false;
        } finally {
            try {
                con.close();
            } catch (SQLException ex) {

                System.out.println(ex.toString());
            }
        }

    }
    

    public boolean ModificarCliente(Cliente cl) {
        String sql = "Update cliente SET documento=?,nombre =?, correo=? , telefono=? WHERE  id=?;";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, cl.getDocumento());
            ps.setString(2, cl.getNombre());
            ps.setString(3, cl.getCorreo());
            ps.setString(4, cl.getTelefono());
            ps.setInt(5, cl.getId());
            ps.execute();

            return true;

        } catch (SQLException e) {
            System.out.println(e.toString());
            return false;
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.out.println(e.toString());
            }
        }

    }

    public Cliente BuscarCliente(String documento) {
        Cliente cl = new Cliente();
        String sql = "Select * from cliente where  documento= ?";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, documento);
            rs = ps.executeQuery();
            if (rs.next()) {
                cl.setDocumento(rs.getString("documento"));
                cl.setNombre(rs.getString("nombre"));
                cl.setCorreo(rs.getString("correo"));
                cl.setTelefono(rs.getString("telefono"));
            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return cl;

    }
    
    public List BuscarCli() {
        String valor = JOptionPane.showInputDialog(null, "Digite un Documento", "BUSCAR CLIENTE", JOptionPane.PLAIN_MESSAGE);
       
         List<Cliente> ListaCli = new ArrayList();
        String sql = "SELECT * FROM cliente where documento like '"+valor+"' ";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Cliente cli = new Cliente();
                cli.setId(rs.getInt("id"));
                cli.setDocumento(rs.getString("documento"));
                cli.setNombre(rs.getString("nombre"));
                cli.setCorreo(rs.getString("correo"));
                cli.setTelefono(rs.getString("telefono"));
                ListaCli.add(cli);
            }

        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return ListaCli;
    }

}
