package MODELO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class GastoDAO {

    Conexion cn = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;

    public boolean RegistrarGasto(Gasto ga) {
        String sql = "INSERT INTO gasto (tipogasto,descripcion,cantidad,fecha) VALUES(?,?,?,current_timestamp()) ";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, ga.getTipogasto());
            ps.setString(2, ga.getDescripcion());
            ps.setInt(3, ga.getCantidad());

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

    public List ListarGasto() {
        List<Gasto> ListaGa = new ArrayList();
        String sql = "SELECT * FROM gasto";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Gasto ga = new Gasto();
                ga.setId(rs.getInt("id"));
                ga.setTipogasto(rs.getString("tipogasto"));
                ga.setDescripcion(rs.getString("descripcion"));
                ga.setCantidad(rs.getInt("cantidad"));
                ga.setFecha(rs.getString("fecha"));

                ListaGa.add(ga);
            }

        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return ListaGa;
    }

    public boolean EliminarGasto(int id) {
        String sql = "DELETE FROM gasto where id = ?";
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ps.execute();
            return true;
        } catch (SQLException e) {
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

    public boolean ModificarGasto(Gasto ga) {
        String sql = "Update gasto SET tipogasto=?,descripcion =?, cantidad=? WHERE  id=?;";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, ga.getTipogasto());
            ps.setString(2, ga.getDescripcion());
            ps.setInt(3, ga.getCantidad());
            ps.setInt(4, ga.getId());
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
    
     public List ListarGastos(String date) {
        List<Gasto> ListaGas = new ArrayList();
        String sql = "SELECT * FROM gasto where fecha like '"+date+"' ";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Gasto ga = new Gasto();
                ga.setId(rs.getInt("id"));
                ga.setTipogasto(rs.getString("tipogasto"));
                ga.setDescripcion(rs.getString("descripcion"));
                ga.setCantidad(rs.getInt("cantidad"));
                ga.setFecha(rs.getString("fecha"));
                
                ListaGas.add(ga);
                        
            }

        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return ListaGas;
    }
     
     public List BuscarGas() {
        String valor = JOptionPane.showInputDialog(null, "Digite un Tipo de gasto", "BUSCAR GASTO", JOptionPane.PLAIN_MESSAGE);
       
         List<Gasto> ListaGa = new ArrayList();
        String sql = "SELECT * FROM gasto where tipogasto like '"+valor+"' ";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Gasto Gas = new Gasto();
                Gas.setId(rs.getInt("id"));
                Gas.setTipogasto(rs.getString("tipogasto"));
                Gas.setDescripcion(rs.getString("descripcion"));
                Gas.setCantidad(rs.getInt("cantidad"));
                Gas.setFecha(rs.getString("fecha"));
                ListaGa.add(Gas);
            }

        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return ListaGa;
    }
}
