package MODELO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import MODELO.Detalle;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class VentaDAO {

    Connection con;
    Conexion cn = new Conexion();
    PreparedStatement ps;
    ResultSet rs;
    int r;

    public int IdVenta() {
        int id = 0;
        String sql = "Select MAX(id) from venta ";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                id = rs.getInt(1);
            }

        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return id;
    }

    public int Registrarven(Venta v) {
        String sql = "Insert into venta ( cliente,vendedor,total,fecha )values(?,?,?,current_timestamp())";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, v.getCliente());
            ps.setString(2, v.getVendedor());
            ps.setInt(3, v.getTotal());
            ps.execute();
        } catch (SQLException e) {
            System.out.println(e.toString());
        } finally {
            try {
                con.close();
            } catch (SQLException ex) {
                System.out.println(ex.toString());
            }
        }
        return r;
    }

    public int RegistrarDetalle(Detalle de) {
        String sql = "Insert into detalle (codigopro,marca,talla,cantidad,precio,valor,total,id_venta,fecha) values (?,?,?,?,?,?,?,?,current_timestamp())";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, de.getCodigopro());
            ps.setString(2, de.getMarca());
            ps.setInt(3, de.getTall());
            ps.setInt(4, de.getCantidad());
            ps.setInt(5, de.getPrecio());
            ps.setInt(6, de.getValor());
            ps.setInt(7, de.getTotal());
            ps.setInt(8, de.getId_venta());
            ps.execute();
        } catch (SQLException e) {
            System.out.println(e.toString());
        } finally {
            try {
                con.close();
            } catch (SQLException ex) {
                System.out.println(ex.toString());
            }
        }
        return r;
    }

    public boolean ActualizarCantidad(int cant, String cod, int talla, int can) {

        if (talla == 21) {
            String sql = "update producto Set cantidad = ?,talla21 =talla21-? where codigo =?";
            try {
                con = cn.getConnection();
                ps = con.prepareStatement(sql);
                ps.setInt(1, cant);
                ps.setInt(2, can);
                ps.setString(3, cod);
                ps.execute();
                return true;
            } catch (SQLException e) {
                System.out.println(e.toString());
                return false;
            }
        } else if (talla == 22) {
            String sql = "update producto Set cantidad = ?,talla22 =talla22-? where codigo =?";
            try {
                con = cn.getConnection();
                ps = con.prepareStatement(sql);
                ps.setInt(1, cant);
                ps.setInt(2, can);
                ps.setString(3, cod);
                ps.execute();
                return true;
            } catch (SQLException e) {
                System.out.println(e.toString());
                return false;
            }

        } else if (talla == 23) {
            String sql = "update producto Set cantidad = ?,talla23 =talla23-? where codigo =?";
            try {
                con = cn.getConnection();
                ps = con.prepareStatement(sql);
                ps.setInt(1, cant);
                ps.setInt(2, can);
                ps.setString(3, cod);
                ps.execute();
                return true;
            } catch (SQLException e) {
                System.out.println(e.toString());
                return false;
            }
        } else if (talla == 24) {
            String sql = "update producto Set cantidad = ?,talla24 =talla24-? where codigo =?";
            try {
                con = cn.getConnection();
                ps = con.prepareStatement(sql);
                ps.setInt(1, cant);
                ps.setInt(2, can);
                ps.setString(3, cod);
                ps.execute();
                return true;
            } catch (SQLException e) {
                System.out.println(e.toString());
                return false;
            }
        } else if (talla == 25) {
            String sql = "update producto Set cantidad = ?,talla25 =talla25-? where codigo =?";
            try {
                con = cn.getConnection();
                ps = con.prepareStatement(sql);
                ps.setInt(1, cant);
                ps.setInt(2, can);
                ps.setString(3, cod);
                ps.execute();
                return true;
            } catch (SQLException e) {
                System.out.println(e.toString());
                return false;
            }
        } else if (talla == 26) {
            String sql = "update producto Set cantidad = ?,talla26 =talla26-? where codigo =?";
            try {
                con = cn.getConnection();
                ps = con.prepareStatement(sql);
                ps.setInt(1, cant);
                ps.setInt(2, can);
                ps.setString(3, cod);
                ps.execute();
                return true;
            } catch (SQLException e) {
                System.out.println(e.toString());
                return false;
            }
        } else if (talla == 27) {
            String sql = "update producto Set cantidad = ?,talla27 =talla27-? where codigo =?";
            try {
                con = cn.getConnection();
                ps = con.prepareStatement(sql);
                ps.setInt(1, cant);
                ps.setInt(2, can);
                ps.setString(3, cod);
                ps.execute();
                return true;
            } catch (SQLException e) {
                System.out.println(e.toString());
                return false;
            }

        } else if (talla == 28) {
            String sql = "update producto Set cantidad = ?,talla28 =talla28-? where codigo =?";
            try {
                con = cn.getConnection();
                ps = con.prepareStatement(sql);
                ps.setInt(1, cant);
                ps.setInt(2, can);
                ps.setString(3, cod);
                ps.execute();
                return true;
            } catch (SQLException e) {
                System.out.println(e.toString());
                return false;
            }
        } else if (talla == 29) {
            String sql = "update producto Set cantidad = ?,talla29 =talla29-? where codigo =?";
            try {
                con = cn.getConnection();
                ps = con.prepareStatement(sql);
                ps.setInt(1, cant);
                ps.setInt(2, can);
                ps.setString(3, cod);
                ps.execute();
                return true;
            } catch (SQLException e) {
                System.out.println(e.toString());
                return false;
            }

        } else if (talla == 30) {
            String sql = "update producto Set cantidad = ?,talla30 =talla30-? where codigo =?";
            try {
                con = cn.getConnection();
                ps = con.prepareStatement(sql);
                ps.setInt(1, cant);
                ps.setInt(2, can);
                ps.setString(3, cod);
                ps.execute();
                return true;
            } catch (SQLException e) {
                System.out.println(e.toString());
                return false;
            }
        }else if (talla==31) {
              String sql = "update producto Set cantidad = ?,talla31 =talla31-? where codigo =?";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, cant);
            ps.setInt(2, can);
            ps.setString(3,cod);
            ps.execute();
            return true;
        } catch (SQLException e) {
            System.out.println(e.toString());
            return false;
        }
        }else if (talla==32) {
              String sql = "update producto Set cantidad = ?,talla32 =talla32-? where codigo =?";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, cant);
            ps.setInt(2, can);
            ps.setString(3,cod);
            ps.execute();
            return true;
        } catch (SQLException e) {
            System.out.println(e.toString());
            return false;
        }
        }else if (talla==33) {
              String sql = "update producto Set cantidad = ?,talla33 =talla33-? where codigo =?";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, cant);
            ps.setInt(2, can);
            ps.setString(3,cod);
            ps.execute();
            return true;
        } catch (SQLException e) {
            System.out.println(e.toString());
            return false;
        }
        }else if (talla==34) {
              String sql = "update producto Set cantidad = ?,talla34 =talla34-? where codigo =?";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, cant);
            ps.setInt(2, can);
            ps.setString(3,cod);
            ps.execute();
            return true;
        } catch (SQLException e) {
            System.out.println(e.toString());
            return false;
        }
        }else if (talla==35) {
              String sql = "update producto Set cantidad = ?,talla35 =talla35-? where codigo =?";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, cant);
            ps.setInt(2, can);
            ps.setString(3,cod);
            ps.execute();
            return true;
        } catch (SQLException e) {
            System.out.println(e.toString());
            return false;
        }
        }else{
            return false;
        }

    }

    public List ListarVentas(String date) {
        List<Detalle> ListaVen = new ArrayList();
        String sql = "SELECT * FROM detalle where fecha like '" + date + "' ";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Detalle de = new Detalle();
                de.setId(rs.getInt("id"));
                de.setCodigopro(rs.getString("codigopro"));
                de.setMarca(rs.getString("marca"));
                de.setTall(rs.getInt("talla"));
                de.setCantidad(rs.getInt("cantidad"));
                de.setPrecio(rs.getInt("precio"));
                de.setValor(rs.getInt("valor"));
                de.setTotal(rs.getInt("total"));
                de.setId_venta(rs.getInt("id_venta"));
                de.setFecha(rs.getString("fecha"));

                ListaVen.add(de);

            }

        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return ListaVen;
    }
}
