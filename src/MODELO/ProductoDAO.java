package MODELO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JComboBox;
import MODELO.Producto;
import javax.swing.JOptionPane;

public class ProductoDAO {

    Connection con;
    Conexion cn = new Conexion();
    PreparedStatement ps;
    ResultSet rs;

    public boolean RegistarProductos(Producto pro) {
        String sql = "Insert into producto ( codigo,marca,talla21, talla22, talla23, talla24,talla25,talla26,talla27,talla28,talla29, talla30,talla31, talla32,talla33,talla34,talla35, descripcion ,cantidad, precio, provedor )values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, pro.getCodigo());
            ps.setString(2, pro.getMarca());
            ps.setInt(3, pro.getTalla21());
            ps.setInt(4, pro.getTalla22());
            ps.setInt(5, pro.getTalla23());
            ps.setInt(6, pro.getTalla24());
            ps.setInt(7, pro.getTalla25());
            ps.setInt(8, pro.getTalla26());
            ps.setInt(9, pro.getTalla27());
            ps.setInt(10, pro.getTalla28());
            ps.setInt(11, pro.getTalla29());
            ps.setInt(12, pro.getTalla30());
            ps.setInt(13, pro.getTalla31());
            ps.setInt(14, pro.getTalla32());
            ps.setInt(15, pro.getTalla33());
            ps.setInt(16, pro.getTalla34());
            ps.setInt(17, pro.getTalla35());
            ps.setString(18, pro.getDescripcion());
            ps.setInt(19, pro.getCantidad());
            ps.setInt(20, pro.getPrecio());
            ps.setString(21, pro.getProvedor());
            ps.execute();
            return true;
        } catch (SQLException e) {
            System.out.println(e.toString());
            return false;
        }

    }

    public void ConsulatarProvedor(JComboBox provedor) {
        String sql = "Select nombre from provedor";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                provedor.addItem(rs.getString("nombre"));
            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        }

    }

    public List BuscarProducto() {
        String valor = JOptionPane.showInputDialog(null, "Digite una Referencia", "BUSCAR CALZADO", JOptionPane.PLAIN_MESSAGE);

        List<Producto> Listapro = new ArrayList();
        String sql = "SELECT * FROM producto where codigo like '" + valor + "' ";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Producto pro = new Producto();
                pro.setId(rs.getInt("id"));
                pro.setCodigo(rs.getString("codigo"));
                pro.setMarca(rs.getString("marca"));
                pro.setTalla21(rs.getInt("talla21"));
                pro.setTalla22(rs.getInt("talla22"));
                pro.setTalla23(rs.getInt("talla23"));
                pro.setTalla24(rs.getInt("talla24"));
                pro.setTalla25(rs.getInt("talla25"));
                pro.setTalla26(rs.getInt("talla26"));
                pro.setTalla27(rs.getInt("talla27"));
                pro.setTalla28(rs.getInt("talla28"));
                pro.setTalla29(rs.getInt("talla29"));
                pro.setTalla30(rs.getInt("talla30"));
                pro.setTalla31(rs.getInt("talla31"));
                pro.setTalla32(rs.getInt("talla32"));
                pro.setTalla33(rs.getInt("talla33"));
                pro.setTalla34(rs.getInt("talla34"));
                pro.setTalla35(rs.getInt("talla35"));
                pro.setDescripcion(rs.getString("descripcion"));
                pro.setCantidad(rs.getInt("cantidad"));
                pro.setPrecio(rs.getInt("precio"));
                pro.setProvedor(rs.getString("provedor"));
                Listapro.add(pro);
            }

        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return Listapro;
    }

    public List ListarProducto() {
        List<Producto> Listapro = new ArrayList();
        String sql = "SELECT * FROM producto";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Producto pro = new Producto();
                pro.setId(rs.getInt("id"));
                pro.setCodigo(rs.getString("codigo"));
                pro.setMarca(rs.getString("marca"));
                pro.setTalla21(rs.getInt("talla21"));
                pro.setTalla22(rs.getInt("talla22"));
                pro.setTalla23(rs.getInt("talla23"));
                pro.setTalla24(rs.getInt("talla24"));
                pro.setTalla25(rs.getInt("talla25"));
                pro.setTalla26(rs.getInt("talla26"));
                pro.setTalla27(rs.getInt("talla27"));
                pro.setTalla28(rs.getInt("talla28"));
                pro.setTalla29(rs.getInt("talla29"));
                pro.setTalla30(rs.getInt("talla30"));
                pro.setTalla31(rs.getInt("talla31"));
                pro.setTalla32(rs.getInt("talla32"));
                pro.setTalla33(rs.getInt("talla33"));
                pro.setTalla34(rs.getInt("talla34"));
                pro.setTalla35(rs.getInt("talla35"));
                pro.setDescripcion(rs.getString("descripcion"));
                pro.setCantidad(rs.getInt("cantidad"));
                pro.setPrecio(rs.getInt("precio"));
                pro.setProvedor(rs.getString("provedor"));
                Listapro.add(pro);
            }

        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return Listapro;
    }

    public boolean EliminarProducto(int id) {
        String sql = "DELETE FROM producto where id = ?";
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

    public boolean ModificarProducto(Producto pro) {
        String sql = "Update producto SET codigo=?, marca =?, talla21=? , talla22=? , talla23=? , talla24=? , talla25=? , talla26=? , talla27=? , talla28=? , talla29=? , talla30=? , talla31=? , talla32=? , talla33=? , talla34=? , talla35=? , descripcion=?, cantidad=?, precio=?, provedor=? WHERE  id=? ;";

        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, pro.getCodigo());
            ps.setString(2, pro.getMarca());
            ps.setInt(3, pro.getTalla21());
            ps.setInt(4, pro.getTalla22());
            ps.setInt(5, pro.getTalla23());
            ps.setInt(6, pro.getTalla24());
            ps.setInt(7, pro.getTalla25());
            ps.setInt(8, pro.getTalla26());
            ps.setInt(9, pro.getTalla27());
            ps.setInt(10, pro.getTalla28());
            ps.setInt(11, pro.getTalla29());
            ps.setInt(12, pro.getTalla30());
            ps.setInt(13, pro.getTalla31());
            ps.setInt(14, pro.getTalla32());
            ps.setInt(15, pro.getTalla33());
            ps.setInt(16, pro.getTalla34());
            ps.setInt(17, pro.getTalla35());
            ps.setString(18, pro.getDescripcion());
            ps.setInt(19, pro.getCantidad());
            ps.setInt(20, pro.getPrecio());
            ps.setString(21, pro.getProvedor());
            ps.setInt(22, pro.getId());
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

    public Producto BuscarPro(String cod) {
        Producto producto = new Producto();
        String sql = "Select * from producto where codigo = ?";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, cod);
            rs = ps.executeQuery();
            if (rs.next()) {
                producto.setMarca(rs.getString("marca"));
                producto.setDescripcion(rs.getString("descripcion"));
                producto.setCantidad(rs.getInt("cantidad"));
                producto.setPrecio(rs.getInt("precio"));

            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return producto;
    }
}
