/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package VISTA;

import MODELO.Cliente;
import MODELO.ClienteDAO;
import MODELO.Detalle;
import MODELO.Eventos;
import MODELO.Gasto;
import MODELO.GastoDAO;
import MODELO.Producto;
import MODELO.ProductoDAO;
import MODELO.Provedor;
import MODELO.ProvedorDAO;
import MODELO.Venta;
import MODELO.VentaDAO;
import Reportes.Excel;
import Reportes.Excel1;
import Reportes.Excel2;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.text.SimpleDateFormat;

public class Sistema extends javax.swing.JFrame {

    Cliente cl = new Cliente();
    ClienteDAO client = new ClienteDAO();
    DefaultTableModel modelo = new DefaultTableModel();
    DefaultTableModel tmp = new DefaultTableModel();
    Eventos EVENT = new Eventos();
    Provedor pr = new Provedor();
    Gasto ga = new Gasto();
    GastoDAO gaDao = new GastoDAO();
    ProvedorDAO prDao = new ProvedorDAO();
    Producto pro = new Producto();
    ProductoDAO proDao = new ProductoDAO();
    Venta v = new Venta();
    VentaDAO vDao = new VentaDAO();
    Detalle de = new Detalle();
    int Totalpagar = 0;
    int Totalven = 0;
    int Totalgasv = 0;
    int Totalcambio = 0;
    int TotalCantidad = 0;
    int Valorcaja = 0;
    int Dinerorec = 0;

    int item;

    public Sistema() {
        //  btnsalir1.setVisible(false);
        initComponents();
        txtidgasto.setVisible(false);
        this.setLocationRelativeTo(null);
        txtidcliente.setVisible(false);
        txtIdProveedor.setVisible(false);
        txtIdProd.setVisible(false);
        txtidventa.setVisible(false);
        jLabel38.setVisible(false);
        txtvalor.setVisible(false);
        txtstock.setVisible(false);
        AutoCompleteDecorator.decorate(cbxProveedorProd);
        proDao.ConsulatarProvedor(cbxProveedorProd);

    }

    public void ListarCliente() {
        List<Cliente> Listarcl = client.Listarcliente();
        modelo = (DefaultTableModel) TableCliente.getModel();
        LimpirarTable();
        Object[] ob = new Object[5];
        for (int i = 0; i < Listarcl.size(); i++) {
            ob[0] = Listarcl.get(i).getId();
            ob[1] = Listarcl.get(i).getDocumento();
            ob[2] = Listarcl.get(i).getNombre();
            ob[3] = Listarcl.get(i).getCorreo();
            ob[4] = Listarcl.get(i).getTelefono();
            modelo.addRow(ob);

        }
        TableCliente.setModel(modelo);

    }

    public void ListarGastos() {
        List<Gasto> ListarGa = gaDao.ListarGasto();
        modelo = (DefaultTableModel) Tablegas.getModel();
        LimpirarTable();
        Object[] ob = new Object[5];
        for (int i = 0; i < ListarGa.size(); i++) {
            ob[0] = ListarGa.get(i).getId();
            ob[1] = ListarGa.get(i).getTipogasto();
            ob[2] = ListarGa.get(i).getDescripcion();
            ob[3] = ListarGa.get(i).getCantidad();
            ob[4] = ListarGa.get(i).getFecha();
            modelo.addRow(ob);

        }
        Tablegas.setModel(modelo);

    }

    public void ListarDetalle() {
        SimpleDateFormat dFormat = new SimpleDateFormat("yyyy-MM-dd");
        String date = dFormat.format(jdateinicial.getDate());

        List<Detalle> ListarDe = vDao.ListarVentas(date);
        modelo = (DefaultTableModel) Tablevenf.getModel();
        LimpirarTable();
        Object[] ob = new Object[10];
        for (int i = 0; i < ListarDe.size(); i++) {
            ob[0] = ListarDe.get(i).getId();
            ob[1] = ListarDe.get(i).getCodigopro();
            ob[2] = ListarDe.get(i).getMarca();
            ob[3] = ListarDe.get(i).getTall();
            ob[4] = ListarDe.get(i).getCantidad();
            ob[5] = ListarDe.get(i).getPrecio();
            ob[6] = ListarDe.get(i).getValor();
            ob[7] = ListarDe.get(i).getTotal();
            ob[8] = ListarDe.get(i).getId_venta();
            ob[9] = ListarDe.get(i).getFecha();
            modelo.addRow(ob);

        }
        Tablevenf.setModel(modelo);

    }

    public void ListarGast() {
        SimpleDateFormat dFormat = new SimpleDateFormat("yyyy-MM-dd");
        String date = dFormat.format(jdateinicial.getDate());

        List<Gasto> ListarGa = gaDao.ListarGastos(date);
        modelo = (DefaultTableModel) TableGasf.getModel();
        LimpirarTable();
        Object[] ob = new Object[5];
        for (int i = 0; i < ListarGa.size(); i++) {
            ob[0] = ListarGa.get(i).getId();
            ob[1] = ListarGa.get(i).getTipogasto();
            ob[2] = ListarGa.get(i).getDescripcion();
            ob[3] = ListarGa.get(i).getCantidad();
            ob[4] = ListarGa.get(i).getFecha();
            modelo.addRow(ob);

        }
        TableGasf.setModel(modelo);

    }

    public void ListarProvedor() {
        List<Provedor> Listarpr = prDao.ListarProvedor();
        modelo = (DefaultTableModel) Tableprovedor.getModel();
        LimpirarTable();
        Object[] ob = new Object[5];
        for (int i = 0; i < Listarpr.size(); i++) {
            ob[0] = Listarpr.get(i).getId();
            ob[1] = Listarpr.get(i).getDocumento();
            ob[2] = Listarpr.get(i).getNombre();
            ob[3] = Listarpr.get(i).getTelefono();
            ob[4] = Listarpr.get(i).getCorreo();
            modelo.addRow(ob);

        }
        Tableprovedor.setModel(modelo);

    }

    public void ListarProducto() {

        List<Producto> ListarPro = proDao.ListarProducto();
        modelo = (DefaultTableModel) TableProducto.getModel();
        LimpirarTable();
        Object[] ob = new Object[22];
        for (int i = 0; i < ListarPro.size(); i++) {
            ob[0] = ListarPro.get(i).getId();
            ob[1] = ListarPro.get(i).getCodigo();
            ob[2] = ListarPro.get(i).getMarca();
            ob[3] = ListarPro.get(i).getTalla21();
            ob[4] = ListarPro.get(i).getTalla22();
            ob[5] = ListarPro.get(i).getTalla23();
            ob[6] = ListarPro.get(i).getTalla24();
            ob[7] = ListarPro.get(i).getTalla25();
            ob[8] = ListarPro.get(i).getTalla26();
            ob[9] = ListarPro.get(i).getTalla27();
            ob[10] = ListarPro.get(i).getTalla28();
            ob[11] = ListarPro.get(i).getTalla29();
            ob[12] = ListarPro.get(i).getTalla30();
            ob[13] = ListarPro.get(i).getTalla31();
            ob[14] = ListarPro.get(i).getTalla32();
            ob[15] = ListarPro.get(i).getTalla33();
            ob[16] = ListarPro.get(i).getTalla34();
            ob[17] = ListarPro.get(i).getTalla35();
            ob[18] = ListarPro.get(i).getDescripcion();
            ob[19] = ListarPro.get(i).getCantidad();
            ob[20] = ListarPro.get(i).getPrecio();
            ob[21] = ListarPro.get(i).getProvedor();
            modelo.addRow(ob);

        }
        TableProducto.setModel(modelo);

    }

    //
    public void ListarBusqueda() {
        List<Producto> ListarPro = proDao.BuscarProducto();
        modelo = (DefaultTableModel) TableProducto.getModel();
        LimpirarTable();
        //hola
        Object[] ob = new Object[22];
        for (int i = 0; i < ListarPro.size(); i++) {
            ob[0] = ListarPro.get(i).getId();
            ob[1] = ListarPro.get(i).getCodigo();
            ob[2] = ListarPro.get(i).getMarca();
            ob[3] = ListarPro.get(i).getTalla21();
            ob[4] = ListarPro.get(i).getTalla22();
            ob[5] = ListarPro.get(i).getTalla23();
            ob[6] = ListarPro.get(i).getTalla24();
            ob[7] = ListarPro.get(i).getTalla25();
            ob[8] = ListarPro.get(i).getTalla26();
            ob[9] = ListarPro.get(i).getTalla27();
            ob[10] = ListarPro.get(i).getTalla28();
            ob[11] = ListarPro.get(i).getTalla29();
            ob[12] = ListarPro.get(i).getTalla30();
            ob[13] = ListarPro.get(i).getTalla31();
            ob[14] = ListarPro.get(i).getTalla32();
            ob[15] = ListarPro.get(i).getTalla33();
            ob[16] = ListarPro.get(i).getTalla34();
            ob[17] = ListarPro.get(i).getTalla35();
            ob[18] = ListarPro.get(i).getDescripcion();
            ob[19] = ListarPro.get(i).getCantidad();
            ob[20] = ListarPro.get(i).getPrecio();
            ob[21] = ListarPro.get(i).getProvedor();
            modelo.addRow(ob);

        }
        JOptionPane.showMessageDialog(null, "EN EXITENCIA HAY " + ob[19]);
        TableProducto.setModel(modelo);

    }

    public void ListarBusquedaCli() {
        List<Cliente> ListarCli = client.BuscarCli();
        modelo = (DefaultTableModel) TableCliente.getModel();
        LimpirarTable();
        //hola
        Object[] ob = new Object[5];
        for (int i = 0; i < ListarCli.size(); i++) {
            ob[0] = ListarCli.get(i).getId();
            ob[1] = ListarCli.get(i).getDocumento();
            ob[2] = ListarCli.get(i).getNombre();
            ob[3] = ListarCli.get(i).getCorreo();
            ob[4] = ListarCli.get(i).getTelefono();
            modelo.addRow(ob);

        }
        TableCliente.setModel(modelo);

    }

    public void ListarBusquedaGas() {
        List<Gasto> ListarGa = gaDao.BuscarGas();
        modelo = (DefaultTableModel) Tablegas.getModel();
        LimpirarTable();
        //hola
        Object[] ob = new Object[5];
        for (int i = 0; i < ListarGa.size(); i++) {
            ob[0] = ListarGa.get(i).getId();
            ob[1] = ListarGa.get(i).getTipogasto();
            ob[2] = ListarGa.get(i).getDescripcion();
            ob[3] = ListarGa.get(i).getCantidad();
            ob[4] = ListarGa.get(i).getFecha();
            modelo.addRow(ob);

        }
        Tablegas.setModel(modelo);

    }

    public void ListarBusquedaPro() {
        List<Provedor> ListarPro = prDao.BuscarPro();
        modelo = (DefaultTableModel) Tableprovedor.getModel();
        LimpirarTable();
        //hola
        Object[] ob = new Object[5];
        for (int i = 0; i < ListarPro.size(); i++) {
            ob[0] = ListarPro.get(i).getId();
            ob[1] = ListarPro.get(i).getDocumento();
            ob[2] = ListarPro.get(i).getNombre();
            ob[3] = ListarPro.get(i).getCorreo();
            ob[4] = ListarPro.get(i).getTelefono();
            modelo.addRow(ob);

        }
        Tableprovedor.setModel(modelo);

    }

    public void LimpirarTable() {
        for (int i = 0; i < modelo.getRowCount(); i++) {
            modelo.removeRow(i);
            i = i - 1;
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        btnventa = new javax.swing.JButton();
        btncliente = new javax.swing.JButton();
        btnprodu = new javax.swing.JButton();
        btnventas = new javax.swing.JButton();
        btnprovedor = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        btngastos = new javax.swing.JButton();
        jLabel48 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtCodigoVenta = new javax.swing.JTextField();
        txtMarcaVenta = new javax.swing.JTextField();
        txtDescripcionVenta = new javax.swing.JTextField();
        txtCantidadVenta = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableVenta = new javax.swing.JTable();
        jLabel8 = new javax.swing.JLabel();
        txtPrecioVenta = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        btnimprim = new javax.swing.JButton();
        txtDocumentoVenta = new javax.swing.JTextField();
        jLabel26 = new javax.swing.JLabel();
        txtNombreVenta = new javax.swing.JTextField();
        jLabel27 = new javax.swing.JLabel();
        LabelTotalVenta = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        txtstock = new javax.swing.JTextField();
        txtvalor = new javax.swing.JTextField();
        txttallaven = new javax.swing.JTextField();
        txtteleven = new javax.swing.JTextField();
        txtcorreov = new javax.swing.JTextField();
        jLabel38 = new javax.swing.JLabel();
        txtvendor = new javax.swing.JTextField();
        jLabel39 = new javax.swing.JLabel();
        txtidventa = new javax.swing.JTextField();
        jLabel40 = new javax.swing.JLabel();
        txtRecibe = new javax.swing.JTextField();
        jLabel41 = new javax.swing.JLabel();
        txtcambio = new javax.swing.JLabel();
        jLabel43 = new javax.swing.JLabel();
        jLabel44 = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        txtIdentificacionCliente = new javax.swing.JTextField();
        txtNombreCliente = new javax.swing.JTextField();
        txtCorreoCliente = new javax.swing.JTextField();
        txtTelefonoCliente = new javax.swing.JTextField();
        btnGuardarCliente = new javax.swing.JButton();
        btnEditarCliente = new javax.swing.JButton();
        btnEliminarCliente = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        TableCliente = new javax.swing.JTable();
        txtidcliente = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        txtDocumentoProveedor = new javax.swing.JTextField();
        txtNombreProveedor = new javax.swing.JTextField();
        txtTelefonoProveedor = new javax.swing.JTextField();
        txtCorreoProveedor = new javax.swing.JTextField();
        btnGuardarProveedor = new javax.swing.JButton();
        btnEditarProveedor = new javax.swing.JButton();
        btnEliminarProveedor = new javax.swing.JButton();
        txtIdProveedor = new javax.swing.JTextField();
        jScrollPane6 = new javax.swing.JScrollPane();
        Tableprovedor = new javax.swing.JTable();
        btnBuscarprovedor = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        txtCodigoProd = new javax.swing.JTextField();
        txtMarcaProd = new javax.swing.JTextField();
        txtTallaProd = new javax.swing.JTextField();
        txtDescripcionProd = new javax.swing.JTextField();
        txtCantidaProd = new javax.swing.JTextField();
        txtPrecioProd = new javax.swing.JTextField();
        cbxProveedorProd = new javax.swing.JComboBox<>();
        jScrollPane4 = new javax.swing.JScrollPane();
        TableProducto = new javax.swing.JTable();
        btnGuardarProd = new javax.swing.JButton();
        btnEditarProd = new javax.swing.JButton();
        btnEliminarProd = new javax.swing.JButton();
        btnNuevoProd = new javax.swing.JButton();
        txtIdProd = new javax.swing.JTextField();
        btnexel = new javax.swing.JButton();
        jLabel49 = new javax.swing.JLabel();
        txtTallaProd22 = new javax.swing.JTextField();
        jLabel50 = new javax.swing.JLabel();
        txtTallaProd23 = new javax.swing.JTextField();
        jLabel51 = new javax.swing.JLabel();
        txtTallaProd24 = new javax.swing.JTextField();
        jLabel52 = new javax.swing.JLabel();
        txtTallaProd25 = new javax.swing.JTextField();
        jLabel53 = new javax.swing.JLabel();
        txtTallaProd26 = new javax.swing.JTextField();
        jLabel54 = new javax.swing.JLabel();
        txtTallaProd27 = new javax.swing.JTextField();
        jLabel55 = new javax.swing.JLabel();
        txtTallaProd28 = new javax.swing.JTextField();
        jLabel56 = new javax.swing.JLabel();
        txtTallaProd29 = new javax.swing.JTextField();
        jLabel57 = new javax.swing.JLabel();
        txtTallaProd30 = new javax.swing.JTextField();
        jLabel58 = new javax.swing.JLabel();
        txtTallaProd31 = new javax.swing.JTextField();
        jLabel59 = new javax.swing.JLabel();
        txtTallaProd32 = new javax.swing.JTextField();
        jLabel60 = new javax.swing.JLabel();
        txtTallaProd33 = new javax.swing.JTextField();
        jLabel61 = new javax.swing.JLabel();
        txtTallaProd34 = new javax.swing.JTextField();
        jLabel62 = new javax.swing.JLabel();
        txtTallaProd35 = new javax.swing.JTextField();
        jButton8 = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();
        jLabel30 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        txttipogas = new javax.swing.JTextField();
        txtdescgas = new javax.swing.JTextField();
        txtCantidad = new javax.swing.JTextField();
        btnagreg = new javax.swing.JButton();
        btnedigas = new javax.swing.JButton();
        jScrollPane7 = new javax.swing.JScrollPane();
        Tablegas = new javax.swing.JTable();
        txtidgasto = new javax.swing.JTextField();
        btnelig = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        Tablevenf = new javax.swing.JTable();
        jLabel29 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        TableGasf = new javax.swing.JTable();
        jLabel32 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        labelVent = new javax.swing.JLabel();
        labelgas = new javax.swing.JLabel();
        btnbuscarv = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jLabel28 = new javax.swing.JLabel();
        labertotalca = new javax.swing.JLabel();
        jdateinicial = new com.toedter.calendar.JDateChooser();
        jLabel35 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        jLabel45 = new javax.swing.JLabel();
        jLabel46 = new javax.swing.JLabel();
        jLabel47 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(0, 102, 204));

        btnventa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/Nventa.png"))); // NOI18N
        btnventa.setText("Compra");
        btnventa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnventaActionPerformed(evt);
            }
        });

        btncliente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/Clientes.png"))); // NOI18N
        btncliente.setText("Clientes");
        btncliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnclienteActionPerformed(evt);
            }
        });

        btnprodu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/producto.png"))); // NOI18N
        btnprodu.setText("Productos");
        btnprodu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnproduActionPerformed(evt);
            }
        });

        btnventas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/gastos.png"))); // NOI18N
        btnventas.setText("Gastos");
        btnventas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnventasActionPerformed(evt);
            }
        });

        btnprovedor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/proveedor.png"))); // NOI18N
        btnprovedor.setText("Proovedor");
        btnprovedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnprovedorActionPerformed(evt);
            }
        });

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/lg1.png"))); // NOI18N

        btngastos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/Caja_1.png"))); // NOI18N
        btngastos.setText("Arqueo Caja");
        btngastos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btngastosActionPerformed(evt);
            }
        });

        jLabel48.setBackground(new java.awt.Color(204, 255, 204));
        jLabel48.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel48.setForeground(new java.awt.Color(255, 255, 255));
        jLabel48.setText("TWISTER KIDS");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btngastos, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(btncliente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnventa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnprodu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnprovedor, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                        .addComponent(btnventas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel48)
                .addGap(30, 30, 30))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3)
                .addComponent(jLabel48)
                .addGap(18, 18, 18)
                .addComponent(btnventa)
                .addGap(29, 29, 29)
                .addComponent(btncliente, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(btnprovedor, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37)
                .addComponent(btnprodu, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(btnventas, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btngastos, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(89, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 180, 710));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/encabezado.png"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 0, 1000, 120));

        jLabel3.setText("REFERENCIA");

        jLabel4.setText("MARCA");

        jLabel5.setText("TALLA");

        jLabel6.setText("DESCRIPCION");

        jLabel7.setText("UNIDADES");

        txtCodigoVenta.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtCodigoVentaMouseClicked(evt);
            }
        });
        txtCodigoVenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCodigoVentaActionPerformed(evt);
            }
        });
        txtCodigoVenta.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtCodigoVentaKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCodigoVentaKeyTyped(evt);
            }
        });

        txtDescripcionVenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDescripcionVentaActionPerformed(evt);
            }
        });
        txtDescripcionVenta.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtDescripcionVentaKeyPressed(evt);
            }
        });

        txtCantidadVenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCantidadVentaActionPerformed(evt);
            }
        });
        txtCantidadVenta.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtCantidadVentaKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCantidadVentaKeyTyped(evt);
            }
        });

        tableVenta.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "REFERNCIA", "MARCA", "TALLA", "DESCRIPCION", "UNIDADES", "PRECIO", "VALOR", "TOTAL"
            }
        ));
        tableVenta.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                tableVentaAncestorAdded(evt);
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });
        tableVenta.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableVentaMouseClicked(evt);
            }
        });
        tableVenta.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tableVentaKeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(tableVenta);
        if (tableVenta.getColumnModel().getColumnCount() > 0) {
            tableVenta.getColumnModel().getColumn(4).setPreferredWidth(12);
        }

        jLabel8.setText("PRECIO");

        txtPrecioVenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPrecioVentaActionPerformed(evt);
            }
        });
        txtPrecioVenta.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtPrecioVentaKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtPrecioVentaKeyTyped(evt);
            }
        });

        jLabel9.setText("IDENTIFICACIÓN");

        btnimprim.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/print.png"))); // NOI18N
        btnimprim.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnimprimActionPerformed(evt);
            }
        });
        btnimprim.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnimprimKeyPressed(evt);
            }
        });

        txtDocumentoVenta.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtDocumentoVentaKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtDocumentoVentaKeyTyped(evt);
            }
        });

        jLabel26.setText("NOMBRE");

        txtNombreVenta.setEditable(false);

        jLabel27.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/money.png"))); // NOI18N
        jLabel27.setText("TOTAL A PAGAR");

        LabelTotalVenta.setText("*********************");

        jLabel36.setText("CANTIDAD");

        txtstock.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtstockActionPerformed(evt);
            }
        });
        txtstock.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtstockKeyPressed(evt);
            }
        });

        txttallaven.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txttallavenActionPerformed(evt);
            }
        });
        txttallaven.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txttallavenKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txttallavenKeyTyped(evt);
            }
        });

        jLabel38.setText("VALOR");

        txtvendor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtvendorKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtvendorKeyTyped(evt);
            }
        });

        jLabel39.setText("VENDEDOR");

        txtidventa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtidventaActionPerformed(evt);
            }
        });

        jLabel40.setText("RECIBE");

        txtRecibe.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtRecibeKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtRecibeKeyTyped(evt);
            }
        });

        jLabel41.setText("CAMBIO");

        txtcambio.setText("*********************");

        jLabel43.setText("TELEFONO");

        jLabel44.setText("CORREO");

        jButton4.setText("Limpiar");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtCodigoVenta, javax.swing.GroupLayout.DEFAULT_SIZE, 115, Short.MAX_VALUE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtMarcaVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(22, 22, 22)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txttallaven, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtDescripcionVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(29, 29, 29)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtPrecioVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(33, 33, 33)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtCantidadVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jScrollPane1)
                        .addGap(84, 84, 84))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(txtDocumentoVenta)
                                            .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE))
                                        .addGap(37, 37, 37)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(txtNombreVenta, javax.swing.GroupLayout.DEFAULT_SIZE, 145, Short.MAX_VALUE)
                                            .addComponent(jLabel26, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                    .addComponent(jLabel39))
                                .addGap(32, 32, 32)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtteleven, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel43)
                                    .addComponent(jLabel44)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addComponent(txtvendor, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtcorreov, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addComponent(btnimprim)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtRecibe, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel40, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(15, 15, 15)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGap(10, 10, 10)
                                        .addComponent(jLabel41))))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(36, 36, 36)
                                .addComponent(LabelTotalVenta, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtcambio, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(87, 87, 87))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel36)
                    .addComponent(txtstock, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(56, 56, 56)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtvalor, javax.swing.GroupLayout.DEFAULT_SIZE, 90, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel38)
                        .addGap(18, 18, 18)
                        .addComponent(txtidventa, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(187, 187, 187))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel38)
                    .addComponent(txtidventa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel36))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtvalor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtstock, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 37, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7)
                    .addComponent(jLabel8))
                .addGap(16, 16, 16)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCodigoVenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtMarcaVenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDescripcionVenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txttallaven, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPrecioVenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCantidadVenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 304, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(jLabel26)
                            .addComponent(jLabel27)
                            .addComponent(jLabel43))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtteleven, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtNombreVenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtDocumentoVenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(LabelTotalVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel41)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel44)
                                .addComponent(jLabel39)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtcorreov, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtcambio)
                            .addComponent(txtvendor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel40)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnimprim)
                            .addComponent(txtRecibe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(51, 51, 51))
        );

        jTabbedPane1.addTab("tab1", jPanel2);

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel11.setText("Identificación:");

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel12.setText("Nombre:");

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel13.setText("correo");

        jLabel14.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel14.setText("Teléfono:");

        txtIdentificacionCliente.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtIdentificacionClienteKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtIdentificacionClienteKeyTyped(evt);
            }
        });

        txtNombreCliente.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtNombreClienteKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNombreClienteKeyTyped(evt);
            }
        });

        txtCorreoCliente.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtCorreoClienteKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCorreoClienteKeyTyped(evt);
            }
        });

        txtTelefonoCliente.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtTelefonoClienteKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtTelefonoClienteKeyTyped(evt);
            }
        });

        btnGuardarCliente.setText("GUARDAR");
        btnGuardarCliente.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnGuardarCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarClienteActionPerformed(evt);
            }
        });

        btnEditarCliente.setText("EDITAR");
        btnEditarCliente.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnEditarCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarClienteActionPerformed(evt);
            }
        });

        btnEliminarCliente.setText("ELIMINAR");
        btnEliminarCliente.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnEliminarCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarClienteActionPerformed(evt);
            }
        });

        TableCliente.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "ID", "DOCUMENTO", "NOMBRE", "CORREO", "TELEFONO"
            }
        ));
        TableCliente.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TableClienteMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(TableCliente);

        jButton2.setText("BUSCAR");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton5.setText("Limpiar");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtIdentificacionCliente, javax.swing.GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE)
                            .addComponent(txtNombreCliente)
                            .addComponent(txtCorreoCliente)
                            .addComponent(txtTelefonoCliente))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtidcliente, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnEliminarCliente)
                            .addComponent(btnGuardarCliente)
                            .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnEditarCliente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 672, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(44, 44, 44))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(158, 158, 158)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11)
                            .addComponent(txtIdentificacionCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtidcliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel12)
                            .addComponent(txtNombreCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(25, 25, 25)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel13)
                            .addComponent(txtCorreoCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel14)
                            .addComponent(txtTelefonoCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(46, 46, 46)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnEditarCliente)
                            .addComponent(btnGuardarCliente))
                        .addGap(32, 32, 32)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnEliminarCliente)
                            .addComponent(jButton2))))
                .addGap(18, 18, 18)
                .addComponent(jButton5)
                .addContainerGap(197, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("tab2", jPanel3);

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel10.setText("Documento");

        jLabel15.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel15.setText("Nombre:");

        jLabel17.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel17.setText("Teléfono:");

        jLabel18.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel18.setText("correo");

        txtDocumentoProveedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDocumentoProveedorActionPerformed(evt);
            }
        });
        txtDocumentoProveedor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtDocumentoProveedorKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtDocumentoProveedorKeyTyped(evt);
            }
        });

        txtNombreProveedor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtNombreProveedorKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNombreProveedorKeyTyped(evt);
            }
        });

        txtTelefonoProveedor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtTelefonoProveedorKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtTelefonoProveedorKeyTyped(evt);
            }
        });

        txtCorreoProveedor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtCorreoProveedorKeyPressed(evt);
            }
        });

        btnGuardarProveedor.setText("GUARDAR");
        btnGuardarProveedor.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnGuardarProveedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarProveedorActionPerformed(evt);
            }
        });

        btnEditarProveedor.setText("EDITAR");
        btnEditarProveedor.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnEditarProveedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarProveedorActionPerformed(evt);
            }
        });

        btnEliminarProveedor.setText("ELIMINAR");
        btnEliminarProveedor.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnEliminarProveedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarProveedorActionPerformed(evt);
            }
        });

        Tableprovedor.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "ID", "Documento", "Nombre", "Telefono", "correo"
            }
        ));
        Tableprovedor.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TableprovedorMouseClicked(evt);
            }
        });
        jScrollPane6.setViewportView(Tableprovedor);

        btnBuscarprovedor.setText("BUSCAR");
        btnBuscarprovedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarprovedorActionPerformed(evt);
            }
        });

        jButton7.setText("Limpiar");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel15)
                            .addComponent(jLabel10)
                            .addComponent(jLabel17)
                            .addComponent(jLabel18))
                        .addGap(27, 27, 27)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(txtTelefonoProveedor, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 192, Short.MAX_VALUE)
                            .addComponent(txtNombreProveedor, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtDocumentoProveedor, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtCorreoProveedor)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addGap(51, 51, 51)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtIdProveedor, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(btnGuardarProveedor)
                                    .addComponent(btnEliminarProveedor)
                                    .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(31, 31, 31)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(btnBuscarprovedor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btnEditarProveedor, javax.swing.GroupLayout.DEFAULT_SIZE, 79, Short.MAX_VALUE))
                                .addGap(54, 54, 54)))))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 649, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(32, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(121, 121, 121)
                .addComponent(txtIdProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10)
                            .addComponent(txtDocumentoProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel15)
                            .addComponent(txtNombreProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel17)
                            .addComponent(txtTelefonoProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel18)
                            .addComponent(txtCorreoProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(30, 30, 30)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnGuardarProveedor)
                            .addComponent(btnEditarProveedor))
                        .addGap(31, 31, 31)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnEliminarProveedor)
                            .addComponent(btnBuscarprovedor))
                        .addGap(18, 18, 18)
                        .addComponent(jButton7))
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(225, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("tab3", jPanel4);

        jLabel19.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel19.setText("REFERNCIA");

        jLabel20.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel20.setText("MARCA");

        jLabel21.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel21.setText("TALLA 21");

        jLabel22.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel22.setText("DESCRIPCIÓN");

        jLabel23.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel23.setText("CANTIDAD");

        jLabel24.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel24.setText("PRECIO");

        jLabel25.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel25.setText("PROVEEDOR");

        txtCodigoProd.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtCodigoProdKeyPressed(evt);
            }
        });

        txtMarcaProd.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtMarcaProdKeyPressed(evt);
            }
        });

        txtTallaProd.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtTallaProdKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtTallaProdKeyTyped(evt);
            }
        });

        txtDescripcionProd.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtDescripcionProdKeyPressed(evt);
            }
        });

        txtCantidaProd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCantidaProdActionPerformed(evt);
            }
        });
        txtCantidaProd.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtCantidaProdKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCantidaProdKeyTyped(evt);
            }
        });

        txtPrecioProd.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtPrecioProdKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtPrecioProdKeyTyped(evt);
            }
        });

        cbxProveedorProd.setEditable(true);
        cbxProveedorProd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxProveedorProdActionPerformed(evt);
            }
        });

        TableProducto.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "REFERENCIA", "MARCA", "T21", "T22", "T23", "T24", "T25", "T26", "T27", "T28", "T29", "T30", "T31", "T32", "T33", "T34", "T35", "DESCRIPCIÓN", "CANTIDAD", "PRECIO", "PROVEEDOR"
            }
        ));
        TableProducto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TableProductoMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(TableProducto);
        if (TableProducto.getColumnModel().getColumnCount() > 0) {
            TableProducto.getColumnModel().getColumn(0).setPreferredWidth(1);
            TableProducto.getColumnModel().getColumn(1).setPreferredWidth(60);
            TableProducto.getColumnModel().getColumn(2).setPreferredWidth(80);
            TableProducto.getColumnModel().getColumn(3).setPreferredWidth(15);
            TableProducto.getColumnModel().getColumn(4).setPreferredWidth(15);
            TableProducto.getColumnModel().getColumn(5).setPreferredWidth(15);
            TableProducto.getColumnModel().getColumn(6).setPreferredWidth(15);
            TableProducto.getColumnModel().getColumn(7).setPreferredWidth(15);
            TableProducto.getColumnModel().getColumn(8).setPreferredWidth(15);
            TableProducto.getColumnModel().getColumn(9).setPreferredWidth(15);
            TableProducto.getColumnModel().getColumn(10).setPreferredWidth(15);
            TableProducto.getColumnModel().getColumn(11).setPreferredWidth(15);
            TableProducto.getColumnModel().getColumn(12).setPreferredWidth(15);
            TableProducto.getColumnModel().getColumn(13).setPreferredWidth(15);
            TableProducto.getColumnModel().getColumn(14).setPreferredWidth(15);
            TableProducto.getColumnModel().getColumn(15).setPreferredWidth(15);
            TableProducto.getColumnModel().getColumn(16).setPreferredWidth(15);
            TableProducto.getColumnModel().getColumn(17).setResizable(false);
            TableProducto.getColumnModel().getColumn(17).setPreferredWidth(15);
            TableProducto.getColumnModel().getColumn(19).setPreferredWidth(50);
            TableProducto.getColumnModel().getColumn(20).setResizable(false);
            TableProducto.getColumnModel().getColumn(21).setPreferredWidth(60);
        }

        btnGuardarProd.setText("GUARDAR");
        btnGuardarProd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarProdActionPerformed(evt);
            }
        });

        btnEditarProd.setText("EDITAR");
        btnEditarProd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarProdActionPerformed(evt);
            }
        });

        btnEliminarProd.setText("ELIMINAR");
        btnEliminarProd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarProdActionPerformed(evt);
            }
        });

        btnNuevoProd.setText("buscar");
        btnNuevoProd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoProdActionPerformed(evt);
            }
        });

        btnexel.setText("EXEL");
        btnexel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnexelActionPerformed(evt);
            }
        });

        jLabel49.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel49.setText("TALLA 22");

        txtTallaProd22.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTallaProd22ActionPerformed(evt);
            }
        });
        txtTallaProd22.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtTallaProd22KeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtTallaProd22KeyTyped(evt);
            }
        });

        jLabel50.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel50.setText("TALLA 23");

        txtTallaProd23.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtTallaProd23KeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtTallaProd23KeyTyped(evt);
            }
        });

        jLabel51.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel51.setText("TALLA 24");

        txtTallaProd24.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtTallaProd24KeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtTallaProd24KeyTyped(evt);
            }
        });

        jLabel52.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel52.setText("TALLA 25");

        txtTallaProd25.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtTallaProd25KeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtTallaProd25KeyTyped(evt);
            }
        });

        jLabel53.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel53.setText("TALLA 26");

        txtTallaProd26.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtTallaProd26KeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtTallaProd26KeyTyped(evt);
            }
        });

        jLabel54.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel54.setText("TALLA 27");

        txtTallaProd27.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtTallaProd27KeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtTallaProd27KeyTyped(evt);
            }
        });

        jLabel55.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel55.setText("TALLA 28");

        txtTallaProd28.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtTallaProd28KeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtTallaProd28KeyTyped(evt);
            }
        });

        jLabel56.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel56.setText("TALLA 29");

        txtTallaProd29.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtTallaProd29KeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtTallaProd29KeyTyped(evt);
            }
        });

        jLabel57.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel57.setText("TALLA 30");

        txtTallaProd30.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtTallaProd30KeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtTallaProd30KeyTyped(evt);
            }
        });

        jLabel58.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel58.setText("TALLA 31");

        txtTallaProd31.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtTallaProd31KeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtTallaProd31KeyTyped(evt);
            }
        });

        jLabel59.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel59.setText("TALLA 32");

        txtTallaProd32.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtTallaProd32KeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtTallaProd32KeyTyped(evt);
            }
        });

        jLabel60.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel60.setText("TALLA 33");

        txtTallaProd33.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTallaProd33ActionPerformed(evt);
            }
        });
        txtTallaProd33.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtTallaProd33KeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtTallaProd33KeyTyped(evt);
            }
        });

        jLabel61.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel61.setText("TALLA 34");

        txtTallaProd34.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtTallaProd34KeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtTallaProd34KeyTyped(evt);
            }
        });

        jLabel62.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel62.setText("TALLA 35");

        txtTallaProd35.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtTallaProd35KeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtTallaProd35KeyTyped(evt);
            }
        });

        jButton8.setText("Limpiar");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel19)
                                    .addComponent(jLabel20))
                                .addGap(35, 35, 35)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(txtMarcaProd, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtCodigoProd, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel5Layout.createSequentialGroup()
                                    .addComponent(jLabel23)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtCantidaProd, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel5Layout.createSequentialGroup()
                                    .addComponent(jLabel22)
                                    .addGap(18, 18, 18)
                                    .addComponent(txtDescripcionProd, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel5Layout.createSequentialGroup()
                                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel24)
                                        .addComponent(jLabel25))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(cbxProveedorProd, 0, 0, Short.MAX_VALUE)
                                        .addComponent(txtPrecioProd, javax.swing.GroupLayout.DEFAULT_SIZE, 119, Short.MAX_VALUE)))))
                        .addGap(31, 31, 31)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel49, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtTallaProd, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtTallaProd22, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(jLabel51, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtTallaProd24, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel52, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel53, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(34, 34, 34)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(txtTallaProd25, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtTallaProd26, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(jLabel50)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtTallaProd23, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(67, 67, 67)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(jLabel59)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtTallaProd32, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(jLabel58)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtTallaProd31, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel54)
                                    .addComponent(jLabel55)
                                    .addComponent(jLabel56))
                                .addGap(34, 34, 34)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(txtTallaProd28, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtTallaProd27, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtTallaProd29, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                                .addComponent(jLabel57)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtTallaProd30, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(62, 62, 62)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addGroup(jPanel5Layout.createSequentialGroup()
                                            .addComponent(jLabel60)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 45, Short.MAX_VALUE)
                                            .addComponent(txtTallaProd33, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(jPanel5Layout.createSequentialGroup()
                                            .addComponent(jLabel61)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(txtTallaProd34, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addComponent(jLabel62, javax.swing.GroupLayout.Alignment.LEADING))
                                    .addComponent(txtTallaProd35, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(32, 32, 32)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(btnEliminarProd, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnexel, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnGuardarProd, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(btnEditarProd, javax.swing.GroupLayout.DEFAULT_SIZE, 94, Short.MAX_VALUE)
                                    .addComponent(btnNuevoProd, javax.swing.GroupLayout.DEFAULT_SIZE, 94, Short.MAX_VALUE)
                                    .addComponent(jButton8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addComponent(txtIdProd, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 2, Short.MAX_VALUE))
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(110, 110, 110)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtCodigoProd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel19)
                            .addComponent(jLabel21)
                            .addComponent(jLabel54)
                            .addComponent(jLabel60)
                            .addComponent(btnGuardarProd, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtTallaProd33, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnEditarProd)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtTallaProd, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtTallaProd27, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnEliminarProd)
                            .addComponent(btnNuevoProd))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnexel)
                            .addComponent(jButton8)))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel5Layout.createSequentialGroup()
                                    .addGap(9, 9, 9)
                                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel20)
                                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabel49)
                                            .addComponent(jLabel55)
                                            .addComponent(jLabel61)
                                            .addComponent(txtTallaProd22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGroup(jPanel5Layout.createSequentialGroup()
                                    .addGap(11, 11, 11)
                                    .addComponent(txtTallaProd28, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(txtTallaProd34, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGap(11, 11, 11)
                                .addComponent(txtMarcaProd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGap(17, 17, 17)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel50)
                                        .addComponent(txtTallaProd23, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel22)
                                        .addComponent(txtDescripcionProd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel62)
                                        .addComponent(txtTallaProd35, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(20, 20, 20)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel5Layout.createSequentialGroup()
                                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel23)
                                            .addComponent(txtCantidaProd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(18, 18, 18)
                                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabel24)
                                            .addComponent(txtPrecioProd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel52)
                                            .addComponent(txtTallaProd25, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addComponent(txtIdProd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 7, Short.MAX_VALUE))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel56)
                                    .addComponent(txtTallaProd29, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, 18, Short.MAX_VALUE)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel51)
                                    .addComponent(txtTallaProd24, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel57)
                                    .addComponent(txtTallaProd30, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel5Layout.createSequentialGroup()
                                        .addComponent(jLabel58)
                                        .addGap(10, 10, 10))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                                        .addComponent(txtTallaProd31, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(8, 8, 8)))))
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(jPanel5Layout.createSequentialGroup()
                                    .addGap(3, 3, 3)
                                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(txtTallaProd26, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel59)
                                        .addComponent(txtTallaProd32, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addComponent(jLabel25)
                                .addComponent(cbxProveedorProd))
                            .addComponent(jLabel53))
                        .addGap(32, 32, 32)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 16, Short.MAX_VALUE)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(116, 116, 116))
        );

        jTabbedPane1.addTab("tab4", jPanel5);

        jLabel30.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel30.setText("Tipo de Gasto");

        jLabel34.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel34.setText("Descripcion");

        jLabel42.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel42.setText("Cantidad");

        txttipogas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txttipogasKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txttipogasKeyTyped(evt);
            }
        });

        txtdescgas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtdescgasKeyPressed(evt);
            }
        });

        txtCantidad.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtCantidadKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCantidadKeyTyped(evt);
            }
        });

        btnagreg.setText("AGREGAR");
        btnagreg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnagregActionPerformed(evt);
            }
        });

        btnedigas.setText("EDITAR");
        btnedigas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnedigasActionPerformed(evt);
            }
        });

        Tablegas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "TIPO GASTO", "Descripcion", "Cantidad", "fecha"
            }
        ));
        Tablegas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TablegasMouseClicked(evt);
            }
        });
        jScrollPane7.setViewportView(Tablegas);

        btnelig.setText("ELIMINAR");
        btnelig.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btneligActionPerformed(evt);
            }
        });

        jButton3.setText("BUSCAR");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton9.setText("Limpiar");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addComponent(txtidgasto, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton9, javax.swing.GroupLayout.DEFAULT_SIZE, 94, Short.MAX_VALUE)
                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel30, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel34, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel42, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnagreg, javax.swing.GroupLayout.DEFAULT_SIZE, 94, Short.MAX_VALUE)
                        .addComponent(btnelig, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtdescgas)
                            .addComponent(txttipogas)
                            .addComponent(txtCantidad, javax.swing.GroupLayout.DEFAULT_SIZE, 124, Short.MAX_VALUE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                        .addGap(43, 43, 43)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, 104, Short.MAX_VALUE)
                            .addComponent(btnedigas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(68, 68, 68)
                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 560, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(85, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(164, 164, 164)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel30)
                            .addComponent(txttipogas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtidgasto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(39, 39, 39)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel34)
                            .addComponent(txtdescgas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(38, 38, 38)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel42)
                            .addComponent(txtCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(40, 40, 40)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnagreg)
                            .addComponent(btnedigas))))
                .addGap(36, 36, 36)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnelig)
                    .addComponent(jButton3))
                .addGap(18, 18, 18)
                .addComponent(jButton9)
                .addContainerGap(198, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("tab6", jPanel7);

        Tablevenf.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Referencia", "Vendedor", "Talla", "cantidad", "Precio", "Valor", "Total", "num_venta", "Fecha"
            }
        ));
        jScrollPane5.setViewportView(Tablevenf);
        if (Tablevenf.getColumnModel().getColumnCount() > 0) {
            Tablevenf.getColumnModel().getColumn(0).setPreferredWidth(15);
            Tablevenf.getColumnModel().getColumn(3).setPreferredWidth(15);
            Tablevenf.getColumnModel().getColumn(8).setPreferredWidth(30);
        }

        jLabel29.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel29.setText("fecha inicial");

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel16.setText("VENTAS");

        jLabel31.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel31.setText("GASTOS");

        TableGasf.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "tipo de pago", "descripcion", "cantidad", "fecha"
            }
        ));
        jScrollPane2.setViewportView(TableGasf);

        jLabel32.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel32.setText("total ventas");

        jLabel33.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel33.setText("total gastos");

        labelVent.setText("**********************");

        labelgas.setText("**********************");

        btnbuscarv.setText("BUSCAR");
        btnbuscarv.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnbuscarvActionPerformed(evt);
            }
        });

        jButton6.setText("Excel");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jLabel28.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel28.setText("valor total en caja");

        labertotalca.setText("*****************");

        jLabel35.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel35.setText("$");

        jLabel37.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel37.setText("$");

        jLabel45.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel45.setText("$");

        jLabel46.setText("Ventas");

        jLabel47.setText("Gastos");

        jButton1.setText("Excel");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel33)
                            .addComponent(jLabel32, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(jLabel37, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(18, 18, 18)
                                .addComponent(labelgas, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(jLabel35, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(labelVent, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(105, 105, 105)
                        .addComponent(jLabel28)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel45)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(labertotalca, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 887, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 887, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel6Layout.createSequentialGroup()
                            .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(27, 27, 27)
                            .addComponent(jdateinicial, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(74, 74, 74)
                            .addComponent(btnbuscarv, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(130, 130, 130)
                            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jButton6)
                                .addGroup(jPanel6Layout.createSequentialGroup()
                                    .addComponent(jLabel46)
                                    .addGap(101, 101, 101)
                                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel47)
                                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)))))))
                .addContainerGap(260, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap(82, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel46)
                    .addComponent(jLabel47))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jdateinicial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel29))
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnbuscarv)
                        .addComponent(jButton6)
                        .addComponent(jButton1)))
                .addGap(41, 41, 41)
                .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(13, 13, 13)
                .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel32)
                            .addComponent(labelVent)
                            .addComponent(jLabel35)))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(44, 44, 44)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(labertotalca)
                            .addComponent(jLabel28)
                            .addComponent(jLabel45))))
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelgas)
                    .addComponent(jLabel33)
                    .addComponent(jLabel37))
                .addGap(25, 25, 25))
        );

        jTabbedPane1.addTab("tab5", jPanel6);

        getContentPane().add(jTabbedPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 20, 1000, 690));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnventaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnventaActionPerformed

        jTabbedPane1.setSelectedIndex(0);
    }//GEN-LAST:event_btnventaActionPerformed

    private void txtCodigoVentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCodigoVentaActionPerformed

    }//GEN-LAST:event_txtCodigoVentaActionPerformed

    private void txtDescripcionVentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDescripcionVentaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDescripcionVentaActionPerformed

    private void txtPrecioVentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPrecioVentaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPrecioVentaActionPerformed

    private void tableVentaAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_tableVentaAncestorAdded
        // TODO add your handling code here:
    }//GEN-LAST:event_tableVentaAncestorAdded

    private void btnbuscarvActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnbuscarvActionPerformed

        ListarDetalle();
        ListarGast();
        totalVent();
        totalGasv();
        ValorCaja();
    }//GEN-LAST:event_btnbuscarvActionPerformed

    private void btnGuardarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarClienteActionPerformed
        if (!"".equals(txtIdentificacionCliente.getText()) && !"".equals(txtNombreCliente.getText()) && !"".equals(txtCorreoCliente.getText()) && !"".equals(txtTelefonoCliente.getText())) {
            cl.setDocumento(txtIdentificacionCliente.getText());
            cl.setNombre(txtNombreCliente.getText());
            cl.setCorreo(txtCorreoCliente.getText());
            cl.setTelefono(txtTelefonoCliente.getText());
            client.RegistrarCliente(cl);
            LimpirarTable();
            LimpiarCliente();
            ListarCliente();
            JOptionPane.showMessageDialog(null, "Cliente Registrado");
        } else {
            JOptionPane.showMessageDialog(null, "Los campos estan vacios");
        }
    }//GEN-LAST:event_btnGuardarClienteActionPerformed

    private void btnclienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnclienteActionPerformed
        LimpirarTable();
        ListarCliente();
        jTabbedPane1.setSelectedIndex(1);
    }//GEN-LAST:event_btnclienteActionPerformed

    private void TableClienteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TableClienteMouseClicked
        int fila = TableCliente.rowAtPoint(evt.getPoint());
        txtidcliente.setText(TableCliente.getValueAt(fila, 0).toString());
        txtIdentificacionCliente.setText(TableCliente.getValueAt(fila, 1).toString());
        txtNombreCliente.setText(TableCliente.getValueAt(fila, 2).toString());
        txtCorreoCliente.setText(TableCliente.getValueAt(fila, 3).toString());
        txtTelefonoCliente.setText(TableCliente.getValueAt(fila, 4).toString());
    }//GEN-LAST:event_TableClienteMouseClicked

    private void btnEliminarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarClienteActionPerformed
        if (!"".equals(txtidcliente.getText())) {
            int pregunta = JOptionPane.showConfirmDialog(null, "¿Seguro que desea eliminar el Cliente?");
            if (pregunta == 0) {
                int id = Integer.parseInt(txtidcliente.getText());
                client.EliminarCliente(id);
                JOptionPane.showMessageDialog(null, "cleinte eliminado");
                LimpirarTable();
                LimpiarCliente();
                ListarCliente();
            }
        } else {
        }

    }//GEN-LAST:event_btnEliminarClienteActionPerformed

    private void btnEditarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarClienteActionPerformed
        if ("".equals(txtidcliente.getText())) {
            JOptionPane.showMessageDialog(null, "Selecione una fila");
        } else {
            if (!"".equals(txtIdentificacionCliente.getText()) && !"".equals(txtNombreCliente.getText()) && !"".equals(txtTelefonoCliente.getText()) && !"".equals(txtCorreoCliente.getText())) {
                cl.setDocumento(txtIdentificacionCliente.getText());
                cl.setNombre(txtNombreCliente.getText());
                cl.setCorreo(txtCorreoCliente.getText());
                cl.setTelefono(txtTelefonoCliente.getText());
                cl.setId(Integer.parseInt(txtidcliente.getText()));
                client.ModificarCliente(cl);
                JOptionPane.showMessageDialog(null, "cliente editado");
                LimpirarTable();
                LimpiarCliente();
                ListarCliente();
            } else {
                JOptionPane.showMessageDialog(null, "Los campos estan Vacios");
            }

        }
    }//GEN-LAST:event_btnEditarClienteActionPerformed

    private void btnGuardarProveedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarProveedorActionPerformed
        if (!"".equals(txtDocumentoProveedor.getText()) && !"".equals(txtNombreProveedor.getText()) && !"".equals(txtTelefonoProveedor.getText()) && !"".equals(txtCorreoProveedor.getText())) {
            pr.setDocumento(txtDocumentoProveedor.getText());
            pr.setNombre(txtNombreProveedor.getText());
            pr.setTelefono(txtTelefonoProveedor.getText());
            pr.setCorreo(txtCorreoProveedor.getText());
            prDao.RegistrarProvedor(pr);
            JOptionPane.showMessageDialog(null, "Provedor guardado");
            LimpirarTable();
            ListarProvedor();
            LimpiarProvedor();
            cbxProveedorProd.removeAllItems();
            AutoCompleteDecorator.decorate(cbxProveedorProd);
            proDao.ConsulatarProvedor(cbxProveedorProd);
        } else {
            JOptionPane.showMessageDialog(null, "Los campos estan vacios");
        }
    }//GEN-LAST:event_btnGuardarProveedorActionPerformed

    private void txtDocumentoProveedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDocumentoProveedorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDocumentoProveedorActionPerformed

    private void btnprovedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnprovedorActionPerformed
        LimpirarTable();
        ListarProvedor();
        jTabbedPane1.setSelectedIndex(2);
    }//GEN-LAST:event_btnprovedorActionPerformed

    private void TableprovedorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TableprovedorMouseClicked
        // TODO add your handling code here:
        int fila = Tableprovedor.rowAtPoint(evt.getPoint());
        txtIdProveedor.setText(Tableprovedor.getValueAt(fila, 0).toString());
        txtDocumentoProveedor.setText(Tableprovedor.getValueAt(fila, 1).toString());
        txtNombreProveedor.setText(Tableprovedor.getValueAt(fila, 2).toString());
        txtTelefonoProveedor.setText(Tableprovedor.getValueAt(fila, 3).toString());
        txtCorreoProveedor.setText(Tableprovedor.getValueAt(fila, 4).toString());


    }//GEN-LAST:event_TableprovedorMouseClicked

    private void btnEliminarProveedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarProveedorActionPerformed
        if (!"".equals(txtIdProveedor.getText())) {
            int pregunta = JOptionPane.showConfirmDialog(null, "Estas seguro de eliminar");
            if (pregunta == 0) {
                int id = Integer.parseInt(txtIdProveedor.getText());
                prDao.EliminarProvedor(id);
                JOptionPane.showMessageDialog(null, "provedor eliminado");
                LimpirarTable();
                ListarProvedor();
                LimpiarProvedor();
                cbxProveedorProd.removeAllItems();
                AutoCompleteDecorator.decorate(cbxProveedorProd);
                proDao.ConsulatarProvedor(cbxProveedorProd);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Selecione una fila para eliminar");
        }
    }//GEN-LAST:event_btnEliminarProveedorActionPerformed

    private void btnEditarProveedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarProveedorActionPerformed
        if ("".equals(txtIdProveedor.getText())) {
            JOptionPane.showMessageDialog(null, "selecione una fila");
        } else {
            if (!"".equals(txtDocumentoProveedor.getText()) && !"".equals(txtNombreProveedor.getText()) && !"".equals(txtTelefonoProveedor.getText()) && !"".equals(txtCorreoProveedor.getText())) {
                pr.setDocumento(txtDocumentoProveedor.getText());
                pr.setNombre(txtNombreProveedor.getText());
                pr.setTelefono(txtTelefonoProveedor.getText());
                pr.setCorreo(txtCorreoProveedor.getText());
                pr.setId(Integer.parseInt(txtIdProveedor.getText()));
                prDao.ModificarProvedor(pr);
                JOptionPane.showMessageDialog(null, "provedor editado");
                LimpirarTable();
                ListarProvedor();
                LimpiarProvedor();
                cbxProveedorProd.removeAllItems();
                AutoCompleteDecorator.decorate(cbxProveedorProd);
                proDao.ConsulatarProvedor(cbxProveedorProd);
            } else {
                JOptionPane.showMessageDialog(null, "Tiene espacios vacios");
            }
        }
    }//GEN-LAST:event_btnEditarProveedorActionPerformed
// cambiar esto
    private void btnGuardarProdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarProdActionPerformed

        if (!"".equals(txtCodigoProd.getText()) && !"".equals(txtMarcaProd.getText()) && !"".equals(txtTallaProd.getText()) && !"".equals(txtDescripcionProd.getText()) && !"".equals(txtCantidaProd.getText()) && !"".equals(txtPrecioProd.getText()) && !"".equals(cbxProveedorProd.getSelectedItem())) {
            pro.setCodigo(txtCodigoProd.getText());
            pro.setMarca(txtMarcaProd.getText());
            pro.setTalla21(Integer.parseInt(txtTallaProd.getText()));
            pro.setTalla22(Integer.parseInt(txtTallaProd22.getText()));
            pro.setTalla23(Integer.parseInt(txtTallaProd23.getText()));
            pro.setTalla24(Integer.parseInt(txtTallaProd24.getText()));
            pro.setTalla25(Integer.parseInt(txtTallaProd25.getText()));
            pro.setTalla26(Integer.parseInt(txtTallaProd26.getText()));
            pro.setTalla27(Integer.parseInt(txtTallaProd27.getText()));
            pro.setTalla28(Integer.parseInt(txtTallaProd28.getText()));
            pro.setTalla29(Integer.parseInt(txtTallaProd29.getText()));
            pro.setTalla30(Integer.parseInt(txtTallaProd30.getText()));
            pro.setTalla31(Integer.parseInt(txtTallaProd31.getText()));
            pro.setTalla32(Integer.parseInt(txtTallaProd32.getText()));
            pro.setTalla33(Integer.parseInt(txtTallaProd33.getText()));
            pro.setTalla34(Integer.parseInt(txtTallaProd34.getText()));
            pro.setTalla35(Integer.parseInt(txtTallaProd35.getText()));
            pro.setDescripcion(txtDescripcionProd.getText());
            pro.setCantidad(Integer.parseInt(txtCantidaProd.getText()));
            pro.setPrecio(Integer.parseInt(txtPrecioProd.getText()));
            pro.setProvedor(cbxProveedorProd.getSelectedItem().toString());
            proDao.RegistarProductos(pro);
            JOptionPane.showMessageDialog(null, "Producto registrado");
            ListarProducto();
            LimpiarProducto();
        } else {
            JOptionPane.showMessageDialog(null, "los campos estan vacios");
        }
    }//GEN-LAST:event_btnGuardarProdActionPerformed

    private void cbxProveedorProdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxProveedorProdActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbxProveedorProdActionPerformed

    private void btnproduActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnproduActionPerformed
        LimpirarTable();
        ListarProducto();
        jTabbedPane1.setSelectedIndex(3);


    }//GEN-LAST:event_btnproduActionPerformed

    private void TableProductoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TableProductoMouseClicked
        // TODO add your handling code here:
        int fila = TableProducto.rowAtPoint(evt.getPoint());
        txtIdProd.setText(TableProducto.getValueAt(fila, 0).toString());
        txtCodigoProd.setText(TableProducto.getValueAt(fila, 1).toString());
        txtMarcaProd.setText(TableProducto.getValueAt(fila, 2).toString());
        txtTallaProd.setText(TableProducto.getValueAt(fila, 3).toString());
        txtTallaProd22.setText(TableProducto.getValueAt(fila, 4).toString());
        txtTallaProd23.setText(TableProducto.getValueAt(fila, 5).toString());
        txtTallaProd24.setText(TableProducto.getValueAt(fila, 6).toString());
        txtTallaProd25.setText(TableProducto.getValueAt(fila, 7).toString());
        txtTallaProd26.setText(TableProducto.getValueAt(fila, 8).toString());
        txtTallaProd27.setText(TableProducto.getValueAt(fila, 9).toString());
        txtTallaProd28.setText(TableProducto.getValueAt(fila, 10).toString());
        txtTallaProd29.setText(TableProducto.getValueAt(fila, 11).toString());
        txtTallaProd30.setText(TableProducto.getValueAt(fila, 12).toString());
        txtTallaProd31.setText(TableProducto.getValueAt(fila, 13).toString());
        txtTallaProd32.setText(TableProducto.getValueAt(fila, 14).toString());
        txtTallaProd33.setText(TableProducto.getValueAt(fila, 15).toString());
        txtTallaProd34.setText(TableProducto.getValueAt(fila, 16).toString());
        txtTallaProd35.setText(TableProducto.getValueAt(fila, 17).toString());
        txtDescripcionProd.setText(TableProducto.getValueAt(fila, 18).toString());
        txtCantidaProd.setText(TableProducto.getValueAt(fila, 19).toString());
        txtPrecioProd.setText(TableProducto.getValueAt(fila, 20).toString());
        cbxProveedorProd.setSelectedItem(TableProducto.getValueAt(fila, 21).toString());

    }//GEN-LAST:event_TableProductoMouseClicked

    private void btnEliminarProdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarProdActionPerformed
        if (!"".equals(txtIdProd.getText())) {
            int pregunta = JOptionPane.showConfirmDialog(null, "Estas seguro de eliminar");
            if (pregunta == 0) {
                int id = Integer.parseInt(txtIdProd.getText());
                proDao.EliminarProducto(id);
                JOptionPane.showMessageDialog(null, "producto eliminado eliminado");
                LimpirarTable();
                LimpiarProducto();
                ListarProducto();

            }
        } else {
            JOptionPane.showMessageDialog(null, "Selecione una fila para eliminar");
        }
    }//GEN-LAST:event_btnEliminarProdActionPerformed

    private void btnEditarProdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarProdActionPerformed
        if ("".equals(txtIdProd.getText())) {
            JOptionPane.showMessageDialog(null, "selecione una fila");
        } else {
            if (!"".equals(txtCodigoProd.getText()) && !"".equals(txtMarcaProd.getText()) && !"".equals(txtTallaProd.getText()) && !"".equals(txtDescripcionProd.getText()) && !"".equals(txtCantidaProd.getText()) && !"".equals(txtPrecioProd.getText()) && !"".equals(cbxProveedorProd.getSelectedItem())) {
                pro.setCodigo(txtCodigoProd.getText());
                pro.setMarca(txtMarcaProd.getText());
                pro.setTalla21(Integer.parseInt(txtTallaProd.getText()));
                pro.setTalla22(Integer.parseInt(txtTallaProd22.getText()));
                pro.setTalla23(Integer.parseInt(txtTallaProd23.getText()));
                pro.setTalla24(Integer.parseInt(txtTallaProd24.getText()));
                pro.setTalla25(Integer.parseInt(txtTallaProd25.getText()));
                pro.setTalla26(Integer.parseInt(txtTallaProd26.getText()));
                pro.setTalla27(Integer.parseInt(txtTallaProd27.getText()));
                pro.setTalla28(Integer.parseInt(txtTallaProd28.getText()));
                pro.setTalla29(Integer.parseInt(txtTallaProd29.getText()));
                pro.setTalla30(Integer.parseInt(txtTallaProd30.getText()));
                pro.setTalla31(Integer.parseInt(txtTallaProd31.getText()));
                pro.setTalla32(Integer.parseInt(txtTallaProd32.getText()));
                pro.setTalla33(Integer.parseInt(txtTallaProd33.getText()));
                pro.setTalla34(Integer.parseInt(txtTallaProd34.getText()));
                pro.setTalla35(Integer.parseInt(txtTallaProd35.getText()));
                pro.setDescripcion(txtDescripcionProd.getText());
                pro.setCantidad(Integer.parseInt(txtCantidaProd.getText()));
                pro.setPrecio(Integer.parseInt(txtPrecioProd.getText()));
                pro.setProvedor(cbxProveedorProd.getSelectedItem().toString());
                pro.setId(Integer.parseInt(txtIdProd.getText()));
                proDao.ModificarProducto(pro);
                JOptionPane.showMessageDialog(null, "Producto editado");
                LimpirarTable();
                ListarProducto();
                LimpiarProducto();
            }
        }
    }//GEN-LAST:event_btnEditarProdActionPerformed

    private void btnNuevoProdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoProdActionPerformed

        ListarBusqueda();


    }//GEN-LAST:event_btnNuevoProdActionPerformed

    private void btnexelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnexelActionPerformed
        Excel.reporte();
    }//GEN-LAST:event_btnexelActionPerformed

    private void txtCodigoVentaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodigoVentaKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (!"".equals(txtCodigoVenta.getText())) {
                String cod = txtCodigoVenta.getText();
                pro = proDao.BuscarPro(cod);
                if (pro.getMarca() != null) {
                    txtMarcaVenta.setText("" + pro.getMarca());
                    txtDescripcionVenta.setText("" + pro.getDescripcion());
                    txtstock.setText("" + pro.getCantidad());
                    txtPrecioVenta.setText("" + pro.getPrecio());
                    txtvalor.setText("" + pro.getPrecio());
                    txttallaven.requestFocus();
                } else {
                    txtMarcaVenta.setText("");
                    txtDescripcionVenta.setText("");
                    txtPrecioVenta.setText("");
                    txtvalor.setText("");
                    txtstock.setText("");
                    txtCodigoVenta.requestFocus();
                }
            } else {
                JOptionPane.showMessageDialog(null, "Ingrese el Codigo del producto");
                txtCodigoVenta.requestFocus();
            }

        }
    }//GEN-LAST:event_txtCodigoVentaKeyPressed

    private void txtCantidadVentaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCantidadVentaKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (!"".equals(txtCantidadVenta.getText())) {
                String cod = txtCodigoVenta.getText();
                String marca = txtMarcaVenta.getText();
                String talla = txttallaven.getText();
                String descripcion = txtDescripcionVenta.getText();
                int cant = Integer.parseInt(txtCantidadVenta.getText());
                int precio = Integer.parseInt(txtPrecioVenta.getText());
                int valor = Integer.parseInt(txtvalor.getText());
                int total = cant * precio;
                int stock = Integer.parseInt(txtstock.getText());
                if (stock >= cant) {
                    item = item + 1;
                    tmp = (DefaultTableModel) tableVenta.getModel();
                    for (int i = 0; i < tableVenta.getRowCount(); i++) {
                        if (tableVenta.getValueAt(i, 0).equals(txtCodigoVenta.getText()) && tableVenta.getValueAt(i, 2).equals(txttallaven.getText())) {
                            JOptionPane.showMessageDialog(null, "El producto ya esta registrado");
                            return;
                        }

                    }
                    ArrayList lista = new ArrayList();
                    lista.add(item);
                    lista.add(cod);
                    lista.add(marca);
                    lista.add(talla);
                    lista.add(descripcion);
                    lista.add(cant);
                    lista.add(precio);
                    lista.add(valor);
                    lista.add(total);
                    Object[] o = new Object[8];
                    o[0] = lista.get(1);
                    o[1] = lista.get(2);
                    o[2] = lista.get(3);
                    o[3] = lista.get(4);
                    o[4] = lista.get(5);
                    o[5] = lista.get(6);
                    o[6] = lista.get(7);
                    o[7] = lista.get(8);
                    tmp.addRow(o);
                    tableVenta.setModel(tmp);
                    totaPagar();
                    LimpiarVenta();

                    txtCodigoVenta.requestFocus();

                } else {
                    JOptionPane.showMessageDialog(null, "EL NUMERO DE UNIDADES SUPERA LAS PERMITIDAS");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Ingrese cantidad");

            }
        }
    }//GEN-LAST:event_txtCantidadVentaKeyPressed

    private void txtPrecioVentaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPrecioVentaKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (!"".equals(txtPrecioVenta.getText())) {

                txtCantidadVenta.requestFocus();

            } else {
                JOptionPane.showMessageDialog(null, "Ingrese una talla");
                txtPrecioVenta.requestFocus();
            }
        }

    }//GEN-LAST:event_txtPrecioVentaKeyPressed

    private void txttallavenKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txttallavenKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (!"".equals(txttallaven.getText())) {
                int val = Integer.parseInt(txttallaven.getText());
                if (val >= 21 && val <= 35) {
                    txtPrecioVenta.requestFocus();
                } else {
                    JOptionPane.showMessageDialog(null, "Ingrese una talla valida");
                }

            } else {
                JOptionPane.showMessageDialog(null, "Ingrese una talla");
                txttallaven.requestFocus();
            }
        }
    }//GEN-LAST:event_txttallavenKeyPressed

    private void txtDocumentoVentaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDocumentoVentaKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (!"".equals(txtDocumentoVenta.getText())) {
                String documento = txtDocumentoVenta.getText();
                cl = client.BuscarCliente(documento);
                if (cl.getNombre() != null) {
                    txtNombreVenta.setText("" + cl.getNombre());
                    txtteleven.setText("" + cl.getTelefono());
                    txtcorreov.setText("" + cl.getCorreo());
                    txtvendor.requestFocus();
                } else {
                    txtDocumentoVenta.setText("");
                    JOptionPane.showMessageDialog(null, "El cliente no existe");
                }
            }
        }
    }//GEN-LAST:event_txtDocumentoVentaKeyPressed

    private void txtCantidadVentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCantidadVentaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCantidadVentaActionPerformed

    private void tableVentaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableVentaMouseClicked
        int fila = TableCliente.rowAtPoint(evt.getPoint());
        txtCodigoVenta.setText(tableVenta.getValueAt(fila, 0).toString());
        txtMarcaVenta.setText(tableVenta.getValueAt(fila, 1).toString());
        txttallaven.setText(tableVenta.getValueAt(fila, 2).toString());
        txtDescripcionVenta.setText(tableVenta.getValueAt(fila, 3).toString());
        txtPrecioVenta.setText(tableVenta.getValueAt(fila, 5).toString());
        txtCantidadVenta.setText(tableVenta.getValueAt(fila, 4).toString());
        modelo = (DefaultTableModel) tableVenta.getModel();
        modelo.removeRow(tableVenta.getSelectedRow());
        totaPagar();
        txtCodigoVenta.requestFocus();


    }//GEN-LAST:event_tableVentaMouseClicked

    private void txtstockActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtstockActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtstockActionPerformed

    private void txtCodigoVentaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtCodigoVentaMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCodigoVentaMouseClicked

    private void tableVentaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tableVentaKeyPressed

    }//GEN-LAST:event_tableVentaKeyPressed

    private void btnimprimActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnimprimActionPerformed
        if (tableVenta.getRowCount() > 0) {
            if (!"".equals(txtDocumentoVenta.getText())) {
                if (!"".equals(txtvendor.getText())) {
                    if (!"".equals(txtRecibe.getText())) {
                        RestrarVenta();
                        RegistrarDetalle();
                        Actualizarcantidad();
                        pdf();
                        LimpiarTableVenta();
                        LimpiarClienteVenta();
                    } else {
                        JOptionPane.showMessageDialog(null, "Ingrese el monto recibido");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Ingrese un vendedor");

                }
            } else {
                JOptionPane.showMessageDialog(null, "Ingrese un usuario valido");

            }
        } else {
            JOptionPane.showMessageDialog(null, "No hay productos registrados");
        }

    }//GEN-LAST:event_btnimprimActionPerformed

    private void txtstockKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtstockKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtstockKeyPressed

    private void txtidventaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtidventaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtidventaActionPerformed

    private void txtRecibeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtRecibeKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (!"".equals(txtRecibe.getText())) {
                totalCambio();
                btnimprim.requestFocus();
            } else {
                JOptionPane.showMessageDialog(null, "El de recibido no puede estar vacio");
            }

        }
    }//GEN-LAST:event_txtRecibeKeyPressed

    private void txtvendorKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtvendorKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (!"".equals(txtvendor.getText())) {
                txtRecibe.requestFocus();
            } else {
                JOptionPane.showMessageDialog(null, "Digite un vendedor");
            }

        }
    }//GEN-LAST:event_txtvendorKeyPressed

    private void txtCodigoVentaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodigoVentaKeyTyped
        //
    }//GEN-LAST:event_txtCodigoVentaKeyTyped

    private void txttallavenKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txttallavenKeyTyped
        EVENT.numberKeyPress(evt);
    }//GEN-LAST:event_txttallavenKeyTyped

    private void txtCantidadVentaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCantidadVentaKeyTyped
        EVENT.numberKeyPress(evt);
    }//GEN-LAST:event_txtCantidadVentaKeyTyped

    private void txtDocumentoVentaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDocumentoVentaKeyTyped
        EVENT.numberKeyPress(evt);
    }//GEN-LAST:event_txtDocumentoVentaKeyTyped

    private void txtvendorKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtvendorKeyTyped
        EVENT.textKeyPress(evt);
    }//GEN-LAST:event_txtvendorKeyTyped

    private void txtRecibeKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtRecibeKeyTyped
        EVENT.numberKeyPress(evt);
    }//GEN-LAST:event_txtRecibeKeyTyped

    private void txtIdentificacionClienteKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtIdentificacionClienteKeyTyped
        EVENT.numberKeyPress(evt);
    }//GEN-LAST:event_txtIdentificacionClienteKeyTyped

    private void txtCorreoClienteKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCorreoClienteKeyTyped

    }//GEN-LAST:event_txtCorreoClienteKeyTyped

    private void txtNombreClienteKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreClienteKeyTyped
        EVENT.textKeyPress(evt);
    }//GEN-LAST:event_txtNombreClienteKeyTyped

    private void txtDocumentoProveedorKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDocumentoProveedorKeyTyped
        EVENT.numberKeyPress(evt);
    }//GEN-LAST:event_txtDocumentoProveedorKeyTyped

    private void txtTelefonoProveedorKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTelefonoProveedorKeyTyped
        EVENT.numberKeyPress(evt);
    }//GEN-LAST:event_txtTelefonoProveedorKeyTyped

    private void txtNombreProveedorKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreProveedorKeyTyped
        EVENT.textKeyPress(evt);
    }//GEN-LAST:event_txtNombreProveedorKeyTyped

    private void txtTallaProdKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTallaProdKeyTyped
        EVENT.numberKeyPress(evt);
    }//GEN-LAST:event_txtTallaProdKeyTyped

    private void txtCantidaProdKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCantidaProdKeyTyped
        EVENT.numberKeyPress(evt);
    }//GEN-LAST:event_txtCantidaProdKeyTyped

    private void txtPrecioProdKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPrecioProdKeyTyped
        EVENT.numberKeyPress(evt);
    }//GEN-LAST:event_txtPrecioProdKeyTyped

    private void txtPrecioVentaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPrecioVentaKeyTyped
        EVENT.numberKeyPress(evt);
    }//GEN-LAST:event_txtPrecioVentaKeyTyped

    private void txtIdentificacionClienteKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtIdentificacionClienteKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (!"".equals(txtIdentificacionCliente.getText())) {
                txtNombreCliente.requestFocus();
            } else {
                JOptionPane.showMessageDialog(null, "Ingrese la identificacion del cliente");
                txtIdentificacionCliente.requestFocus();
            }
        }

    }//GEN-LAST:event_txtIdentificacionClienteKeyPressed

    private void txtNombreClienteKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreClienteKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (!"".equals(txtNombreCliente.getText())) {
                txtCorreoCliente.requestFocus();
            } else {

                JOptionPane.showMessageDialog(null, "Ingrese el Nombre del cliente");
                txtIdentificacionCliente.requestFocus();
            }
        }
    }//GEN-LAST:event_txtNombreClienteKeyPressed

    private void txtCorreoClienteKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCorreoClienteKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (!"".equals(txtCorreoCliente.getText())) {
                txtTelefonoCliente.requestFocus();
            } else {

                JOptionPane.showMessageDialog(null, "Ingrese el correo del cliente");
                txtCorreoCliente.requestFocus();
            }
        }
    }//GEN-LAST:event_txtCorreoClienteKeyPressed

    private void txtTelefonoClienteKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTelefonoClienteKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if ("".equals(txtTelefonoCliente.getText())) {
                JOptionPane.showMessageDialog(null, "Ingrese el telefono del cliente");
                txtTelefonoCliente.requestFocus();
            }

        }
    }//GEN-LAST:event_txtTelefonoClienteKeyPressed

    private void txtTelefonoClienteKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTelefonoClienteKeyTyped
        EVENT.numberKeyPress(evt);
    }//GEN-LAST:event_txtTelefonoClienteKeyTyped

    private void txtDocumentoProveedorKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDocumentoProveedorKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (!"".equals(txtDocumentoProveedor.getText())) {
                txtNombreProveedor.requestFocus();
            } else {
                JOptionPane.showMessageDialog(null, "Ingrese la identificacion del proveedor");
                txtDocumentoProveedor.requestFocus();
            }
        }
    }//GEN-LAST:event_txtDocumentoProveedorKeyPressed

    private void txtNombreProveedorKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreProveedorKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (!"".equals(txtNombreProveedor.getText())) {
                txtTelefonoProveedor.requestFocus();
            } else {
                JOptionPane.showMessageDialog(null, "Ingrese el Nombre del proveedor");
                txtNombreProveedor.requestFocus();
            }
        }
    }//GEN-LAST:event_txtNombreProveedorKeyPressed

    private void txtCorreoProveedorKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCorreoProveedorKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if ("".equals(txtCorreoProveedor.getText())) {
                JOptionPane.showMessageDialog(null, "Ingrese el correo del provedor");
                txtCorreoProveedor.requestFocus();
            }

        }
    }//GEN-LAST:event_txtCorreoProveedorKeyPressed

    private void txtTelefonoProveedorKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTelefonoProveedorKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (!"".equals(txtTelefonoProveedor.getText())) {
                txtCorreoProveedor.requestFocus();
            } else {
                JOptionPane.showMessageDialog(null, "Ingrese el Telefono del proveedor");
                txtTelefonoProveedor.requestFocus();
            }
        }
    }//GEN-LAST:event_txtTelefonoProveedorKeyPressed

    private void txtCodigoProdKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodigoProdKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (!"".equals(txtCodigoProd.getText())) {
                txtMarcaProd.requestFocus();
            } else {
                JOptionPane.showMessageDialog(null, "Ingrese una Referencia");
                txtCodigoProd.requestFocus();
            }
        }
    }//GEN-LAST:event_txtCodigoProdKeyPressed

    private void txtMarcaProdKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMarcaProdKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (!"".equals(txtMarcaProd.getText())) {
                txtTallaProd.requestFocus();
            } else {
                JOptionPane.showMessageDialog(null, "Ingrese una Marca ");
                txtMarcaProd.requestFocus();
            }
        }
    }//GEN-LAST:event_txtMarcaProdKeyPressed

    private void txtTallaProdKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTallaProdKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (!"".equals(txtTallaProd.getText())) {
                txtTallaProd22.requestFocus();
            } else {
                JOptionPane.showMessageDialog(null, "Ingrese una Talla ");
                txtTallaProd.requestFocus();
            }
        }
    }//GEN-LAST:event_txtTallaProdKeyPressed

    private void txtDescripcionProdKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDescripcionProdKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            totalCantidad();
            if (!"".equals(txtDescripcionProd.getText())) {
                txtCantidaProd.requestFocus();
            } else {
                JOptionPane.showMessageDialog(null, "Ingrese una Descripcion");
                txtDescripcionProd.requestFocus();
            }
        }
    }//GEN-LAST:event_txtDescripcionProdKeyPressed

    private void txtCantidaProdKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCantidaProdKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (!"".equals(txtCantidaProd.getText())) {
                txtPrecioProd.requestFocus();
            } else {
                JOptionPane.showMessageDialog(null, "Ingrese una Cantidad");
                txtCantidaProd.requestFocus();
            }
        }
    }//GEN-LAST:event_txtCantidaProdKeyPressed

    private void txtPrecioProdKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPrecioProdKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if ("".equals(txtPrecioProd.getText())) {
                JOptionPane.showMessageDialog(null, "Ingrese Un Precio");
                txtPrecioProd.requestFocus();
            }

        }
    }//GEN-LAST:event_txtPrecioProdKeyPressed

    private void btnventasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnventasActionPerformed

        LimpirarTable();
        ListarGastos();
        jTabbedPane1.setSelectedIndex(4);
    }//GEN-LAST:event_btnventasActionPerformed

    private void btnagregActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnagregActionPerformed
        if (!"".equals(txttipogas.getText()) && !"".equals(txtdescgas.getText()) && !"".equals(txtCantidad.getText())) {
            ga.setTipogasto(txttipogas.getText());
            ga.setDescripcion(txtdescgas.getText());
            ga.setCantidad(Integer.parseInt(txtCantidad.getText()));
            gaDao.RegistrarGasto(ga);
            LimpirarTable();
            ListarGastos();
            LimpiarGasto();
            JOptionPane.showMessageDialog(null, "Gasto Registrado");
        } else {
            JOptionPane.showMessageDialog(null, "Los campos estan vacios");
        }
    }//GEN-LAST:event_btnagregActionPerformed

    private void btngastosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btngastosActionPerformed

        LimpirarTable();
        jTabbedPane1.setSelectedIndex(5);
    }//GEN-LAST:event_btngastosActionPerformed

    private void btneligActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btneligActionPerformed
        if (!"".equals(txtidgasto.getText())) {
            int pregunta = JOptionPane.showConfirmDialog(null, "¿Seguro que desea eliminar el Gasto?");
            if (pregunta == 0) {
                int id = Integer.parseInt(txtidgasto.getText());
                gaDao.EliminarGasto(id);
                JOptionPane.showMessageDialog(null, "cleinte eliminado");
                LimpirarTable();
                LimpiarGasto();
                ListarGastos();
            }
        } else {
        }
    }//GEN-LAST:event_btneligActionPerformed

    private void TablegasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TablegasMouseClicked
        int fila = Tablegas.rowAtPoint(evt.getPoint());
        txtidgasto.setText(Tablegas.getValueAt(fila, 0).toString());
        txttipogas.setText(Tablegas.getValueAt(fila, 1).toString());
        txtdescgas.setText(Tablegas.getValueAt(fila, 2).toString());
        txtCantidad.setText(Tablegas.getValueAt(fila, 3).toString());


    }//GEN-LAST:event_TablegasMouseClicked

    private void btnedigasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnedigasActionPerformed
        if ("".equals(txtidgasto.getText())) {
            JOptionPane.showMessageDialog(null, "Selecione una fila");
        } else {
            if (!"".equals(txttipogas.getText()) && !"".equals(txtdescgas.getText()) && !"".equals(txtCantidad.getText())) {
                ga.setTipogasto(txttipogas.getText());
                ga.setDescripcion(txtdescgas.getText());
                ga.setCantidad(Integer.parseInt(txtCantidad.getText()));
                ga.setId(Integer.parseInt(txtidgasto.getText()));
                gaDao.ModificarGasto(ga);
                JOptionPane.showMessageDialog(null, "Gasto editado");
                LimpirarTable();
                LimpiarGasto();
                ListarGastos();
            } else {
                JOptionPane.showMessageDialog(null, "Los campos estan Vacios");
            }

        }
    }//GEN-LAST:event_btnedigasActionPerformed

    private void txttipogasKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txttipogasKeyTyped

    }//GEN-LAST:event_txttipogasKeyTyped

    private void txtCantidadKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCantidadKeyTyped
        EVENT.numberKeyPress(evt);
    }//GEN-LAST:event_txtCantidadKeyTyped

    private void txttipogasKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txttipogasKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (!"".equals(txttipogas.getText())) {
                txtdescgas.requestFocus();
            } else {
                JOptionPane.showMessageDialog(null, "Ingrese una Referencia");
                txttipogas.requestFocus();
            }
        }
    }//GEN-LAST:event_txttipogasKeyPressed

    private void txtdescgasKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtdescgasKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (!"".equals(txtdescgas.getText())) {
                txtCantidad.requestFocus();
            } else {
                JOptionPane.showMessageDialog(null, "Ingrese una Referencia");
                txtdescgas.requestFocus();
            }
        }
    }//GEN-LAST:event_txtdescgasKeyPressed

    private void txtCantidadKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCantidadKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if ("".equals(txtCantidad.getText())) {
                JOptionPane.showMessageDialog(null, "Ingrese Un Precio");
                txtCantidad.requestFocus();
            }

        }
    }//GEN-LAST:event_txtCantidadKeyPressed

    private void txtDescripcionVentaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDescripcionVentaKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (!"".equals(txtDescripcionVenta.getText())) {

                txtPrecioVenta.requestFocus();
            } else {
                JOptionPane.showMessageDialog(null, "Ingrese una Descripcion");
                txtDescripcionVenta.requestFocus();
            }
        }
    }//GEN-LAST:event_txtDescripcionVentaKeyPressed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        SimpleDateFormat dFormat = new SimpleDateFormat("yyyy-MM-dd");
        String date = dFormat.format(jdateinicial.getDate());
        Excel1.reporte(date);
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        SimpleDateFormat dFormat = new SimpleDateFormat("yyyy-MM-dd");
        String date = dFormat.format(jdateinicial.getDate());
        Excel2.reporte(date);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        ListarBusquedaCli();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void btnBuscarprovedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarprovedorActionPerformed
        ListarBusquedaPro();
    }//GEN-LAST:event_btnBuscarprovedorActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        ListarBusquedaGas();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void btnimprimKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnimprimKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (!"".equals(txtRecibe.getText())) {
                if (tableVenta.getRowCount() > 0) {
                    if (!"".equals(txtDocumentoVenta.getText())) {
                        if (!"".equals(txtvendor.getText())) {
                            if (!"".equals(txtRecibe.getText())) {
                                RestrarVenta();
                                RegistrarDetalle();
                                Actualizarcantidad();
                                pdf();
                                LimpiarTableVenta();
                                LimpiarClienteVenta();
                            } else {
                                JOptionPane.showMessageDialog(null, "Ingrese el monto recibido");
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "Ingrese un vendedor");

                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Ingrese un usuario valido");

                    }
                } else {
                    JOptionPane.showMessageDialog(null, "No hay productos registrados");
                }
            } else {
                JOptionPane.showMessageDialog(null, "No ha colocado una cantidad");
            }

        }


    }//GEN-LAST:event_btnimprimKeyPressed

    private void txttallavenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txttallavenActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txttallavenActionPerformed

    private void txtTallaProd22ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTallaProd22ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTallaProd22ActionPerformed

    private void txtTallaProd22KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTallaProd22KeyTyped
        EVENT.numberKeyPress(evt);
    }//GEN-LAST:event_txtTallaProd22KeyTyped

    private void txtTallaProd23KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTallaProd23KeyTyped
        EVENT.numberKeyPress(evt);
    }//GEN-LAST:event_txtTallaProd23KeyTyped

    private void txtTallaProd25KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTallaProd25KeyTyped
        EVENT.numberKeyPress(evt);
    }//GEN-LAST:event_txtTallaProd25KeyTyped

    private void txtTallaProd24KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTallaProd24KeyTyped
        EVENT.numberKeyPress(evt);
    }//GEN-LAST:event_txtTallaProd24KeyTyped

    private void txtTallaProd26KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTallaProd26KeyTyped
        EVENT.numberKeyPress(evt);
    }//GEN-LAST:event_txtTallaProd26KeyTyped

    private void txtTallaProd27KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTallaProd27KeyTyped
        EVENT.numberKeyPress(evt);
    }//GEN-LAST:event_txtTallaProd27KeyTyped

    private void txtTallaProd28KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTallaProd28KeyTyped
        EVENT.numberKeyPress(evt);
    }//GEN-LAST:event_txtTallaProd28KeyTyped

    private void txtTallaProd29KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTallaProd29KeyTyped
        EVENT.numberKeyPress(evt);
    }//GEN-LAST:event_txtTallaProd29KeyTyped

    private void txtTallaProd30KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTallaProd30KeyTyped
        EVENT.numberKeyPress(evt);
    }//GEN-LAST:event_txtTallaProd30KeyTyped

    private void txtTallaProd31KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTallaProd31KeyTyped
        EVENT.numberKeyPress(evt);
    }//GEN-LAST:event_txtTallaProd31KeyTyped

    private void txtTallaProd32KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTallaProd32KeyTyped
        EVENT.numberKeyPress(evt);
    }//GEN-LAST:event_txtTallaProd32KeyTyped

    private void txtTallaProd33KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTallaProd33KeyTyped
        EVENT.numberKeyPress(evt);
    }//GEN-LAST:event_txtTallaProd33KeyTyped

    private void txtTallaProd34KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTallaProd34KeyTyped
        EVENT.numberKeyPress(evt);
    }//GEN-LAST:event_txtTallaProd34KeyTyped

    private void txtTallaProd35KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTallaProd35KeyTyped
        EVENT.numberKeyPress(evt);
    }//GEN-LAST:event_txtTallaProd35KeyTyped

    private void txtTallaProd22KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTallaProd22KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (!"".equals(txtTallaProd22.getText())) {
                txtTallaProd23.requestFocus();
            } else {
                JOptionPane.showMessageDialog(null, "Ingrese una Talla ");
                txtTallaProd22.requestFocus();
            }
        }
    }//GEN-LAST:event_txtTallaProd22KeyPressed

    private void txtTallaProd23KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTallaProd23KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (!"".equals(txtTallaProd23.getText())) {
                txtTallaProd24.requestFocus();
            } else {
                JOptionPane.showMessageDialog(null, "Ingrese una Talla ");
                txtTallaProd23.requestFocus();
            }
        }
    }//GEN-LAST:event_txtTallaProd23KeyPressed

    private void txtTallaProd24KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTallaProd24KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (!"".equals(txtTallaProd24.getText())) {
                txtTallaProd25.requestFocus();
            } else {
                JOptionPane.showMessageDialog(null, "Ingrese una Talla ");
                txtTallaProd24.requestFocus();
            }
        }
    }//GEN-LAST:event_txtTallaProd24KeyPressed

    private void txtTallaProd25KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTallaProd25KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (!"".equals(txtTallaProd25.getText())) {
                txtTallaProd26.requestFocus();
            } else {
                JOptionPane.showMessageDialog(null, "Ingrese una Talla ");
                txtTallaProd25.requestFocus();
            }
        }
    }//GEN-LAST:event_txtTallaProd25KeyPressed

    private void txtTallaProd26KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTallaProd26KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (!"".equals(txtTallaProd26.getText())) {
                txtTallaProd27.requestFocus();
            } else {
                JOptionPane.showMessageDialog(null, "Ingrese una Talla ");
                txtTallaProd26.requestFocus();
            }
        }// TODO add your handling code here:
    }//GEN-LAST:event_txtTallaProd26KeyPressed

    private void txtTallaProd27KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTallaProd27KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (!"".equals(txtTallaProd27.getText())) {
                txtTallaProd28.requestFocus();
            } else {
                JOptionPane.showMessageDialog(null, "Ingrese una Talla ");
                txtTallaProd27.requestFocus();
            }
        }
    }//GEN-LAST:event_txtTallaProd27KeyPressed

    private void txtTallaProd28KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTallaProd28KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (!"".equals(txtTallaProd28.getText())) {
                txtTallaProd29.requestFocus();
            } else {
                JOptionPane.showMessageDialog(null, "Ingrese una Talla ");
                txtTallaProd28.requestFocus();
            }
        }
    }//GEN-LAST:event_txtTallaProd28KeyPressed

    private void txtTallaProd29KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTallaProd29KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (!"".equals(txtTallaProd29.getText())) {
                txtTallaProd30.requestFocus();
            } else {
                JOptionPane.showMessageDialog(null, "Ingrese una Talla ");
                txtTallaProd29.requestFocus();
            }
        }
    }//GEN-LAST:event_txtTallaProd29KeyPressed

    private void txtTallaProd30KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTallaProd30KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (!"".equals(txtTallaProd30.getText())) {
                txtTallaProd31.requestFocus();
            } else {
                JOptionPane.showMessageDialog(null, "Ingrese una Talla ");
                txtTallaProd30.requestFocus();
            }
        }
    }//GEN-LAST:event_txtTallaProd30KeyPressed

    private void txtTallaProd31KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTallaProd31KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (!"".equals(txtTallaProd31.getText())) {
                txtTallaProd32.requestFocus();
            } else {
                JOptionPane.showMessageDialog(null, "Ingrese una Talla ");
                txtTallaProd31.requestFocus();
            }
        }
    }//GEN-LAST:event_txtTallaProd31KeyPressed

    private void txtTallaProd32KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTallaProd32KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (!"".equals(txtTallaProd32.getText())) {
                txtTallaProd33.requestFocus();
            } else {
                JOptionPane.showMessageDialog(null, "Ingrese una Talla ");
                txtTallaProd32.requestFocus();
            }
        }
    }//GEN-LAST:event_txtTallaProd32KeyPressed

    private void txtTallaProd33KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTallaProd33KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (!"".equals(txtTallaProd33.getText())) {
                txtTallaProd34.requestFocus();
            } else {
                JOptionPane.showMessageDialog(null, "Ingrese una Talla ");
                txtTallaProd33.requestFocus();
            }
        }
    }//GEN-LAST:event_txtTallaProd33KeyPressed

    private void txtTallaProd34KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTallaProd34KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (!"".equals(txtTallaProd34.getText())) {
                txtTallaProd35.requestFocus();
            } else {
                JOptionPane.showMessageDialog(null, "Ingrese una Talla ");
                txtTallaProd34.requestFocus();
            }
        }
    }//GEN-LAST:event_txtTallaProd34KeyPressed

    private void txtTallaProd35KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTallaProd35KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (!"".equals(txtTallaProd35.getText())) {
                txtDescripcionProd.requestFocus();
            } else {
                JOptionPane.showMessageDialog(null, "Ingrese una Talla ");
                txtTallaProd35.requestFocus();
            }
        }
    }//GEN-LAST:event_txtTallaProd35KeyPressed

    private void txtTallaProd33ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTallaProd33ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTallaProd33ActionPerformed

    private void txtCantidaProdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCantidaProdActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCantidaProdActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        LimpiarVenta();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
       LimpiarCliente();
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        LimpiarProvedor();
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        LimpiarProducto();
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        LimpiarGasto();
    }//GEN-LAST:event_jButton9ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {

            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Sistema.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Sistema.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Sistema.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Sistema.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Sistema().setVisible(true);
            }
        });
    }

    private void LimpiarClienteVenta() {
        txtDocumentoVenta.setText("");
        txtNombreVenta.setText("");
        txtNombreVenta.setText("");
        txtcorreov.setText("");
        txtteleven.setText("");
        txtvendor.setText("");
        txtRecibe.setText("");
        LabelTotalVenta.setText("*********************");
        txtcambio.setText("*********************");
    }

    private void LimpiarCliente() {
        txtidcliente.setText("");
        txtIdentificacionCliente.setText("");
        txtNombreCliente.setText("");
        txtCorreoCliente.setText("");
        txtTelefonoCliente.setText("");
    }

    private void LimpiarGasto() {
        txtidgasto.setText("");
        txttipogas.setText("");
        txtdescgas.setText("");
        txtCantidad.setText("");
    }

    private void LimpiarVenta() {
        txtCodigoVenta.setText("");
        txtMarcaVenta.setText("");
        txttallaven.setText("");
        txtDescripcionVenta.setText("");
        txtPrecioVenta.setText("");
        txtCantidadVenta.setText("");
        txtstock.setText("");
        txtvalor.setText("");
        txtidventa.setText("");
    }

    private void LimpiarProvedor() {
        txtIdProveedor.setText("");
        txtDocumentoProveedor.setText("");
        txtNombreProveedor.setText("");
        txtCorreoProveedor.setText("");
        txtTelefonoProveedor.setText("");
    }

    private void LimpiarFecha() {

    }

    private void LimpiarProducto() {
        txtIdProd.setText("");
        txtCodigoProd.setText("");
        txtMarcaProd.setText("");
        txtTallaProd.setText("");
        txtTallaProd22.setText("");
        txtTallaProd23.setText("");
        txtTallaProd24.setText("");
        txtTallaProd25.setText("");
        txtTallaProd26.setText("");
        txtTallaProd27.setText("");
        txtTallaProd28.setText("");
        txtTallaProd29.setText("");
        txtTallaProd30.setText("");
        txtTallaProd31.setText("");
        txtTallaProd32.setText("");
        txtTallaProd33.setText("");
        txtTallaProd34.setText("");
        txtTallaProd35.setText("");
        txtDescripcionProd.setText("");
        txtCantidaProd.setText("");
        txtPrecioProd.setText("");
        cbxProveedorProd.setSelectedItem("");
    }

    private void totalCambio() {
        Totalcambio = 0;
        int rec = Integer.parseInt(txtRecibe.getText());
        int val = Integer.parseInt(LabelTotalVenta.getText());
        int valor = rec - val;
        txtcambio.setText("" + valor);

    }

    private void totalCantidad() {
        TotalCantidad = 0;
        int t1 = Integer.parseInt(txtTallaProd.getText());
        int t2 = Integer.parseInt(txtTallaProd22.getText());
        int t3 = Integer.parseInt(txtTallaProd23.getText());
        int t4 = Integer.parseInt(txtTallaProd24.getText());
        int t5 = Integer.parseInt(txtTallaProd25.getText());
        int t6 = Integer.parseInt(txtTallaProd26.getText());
        int t7 = Integer.parseInt(txtTallaProd27.getText());
        int t8 = Integer.parseInt(txtTallaProd28.getText());
        int t9 = Integer.parseInt(txtTallaProd29.getText());
        int t10 = Integer.parseInt(txtTallaProd30.getText());
        int t11 = Integer.parseInt(txtTallaProd31.getText());
        int t12 = Integer.parseInt(txtTallaProd32.getText());
        int t13 = Integer.parseInt(txtTallaProd33.getText());
        int t14 = Integer.parseInt(txtTallaProd34.getText());
        int t15 = Integer.parseInt(txtTallaProd35.getText());
        int valor = t1 + t2 + t3 + t4 + t5 + t6 + t7 + t8 + t9 + t10 + t11 + t12 + t13 + t14 + t15;
        txtCantidaProd.setText("" + valor);

    }

    private void totaPagar() {
        Totalpagar = 0;
        int numfila = tableVenta.getRowCount();
        for (int i = 0; i < numfila; i++) {
            int calcular = Integer.parseInt(String.valueOf(tableVenta.getModel().getValueAt(i, 7)));
            Totalpagar = Totalpagar + calcular;
        }
        LabelTotalVenta.setText("" + Totalpagar);
    }

    private void totalVent() {
        Totalven = 0;
        int numfila = Tablevenf.getRowCount();
        for (int i = 0; i < numfila; i++) {
            int calcular = Integer.parseInt(String.valueOf(Tablevenf.getModel().getValueAt(i, 7)));
            Totalven = Totalven + calcular;
        }
        labelVent.setText("" + Totalven);
    }

    private void totalGasv() {
        Totalgasv = 0;
        int numfila = TableGasf.getRowCount();
        for (int i = 0; i < numfila; i++) {
            int calcular = Integer.parseInt(String.valueOf(TableGasf.getModel().getValueAt(i, 3)));
            Totalgasv = Totalgasv + calcular;
        }
        labelgas.setText("" + Totalgasv);
    }

    private void ValorCaja() {
        Valorcaja = 0;
        int Valorven = Integer.parseInt(labelVent.getText());
        int Valorvgas = Integer.parseInt(labelgas.getText());
        Valorcaja = Valorven - Valorvgas;
        labertotalca.setText("" + Valorcaja);
    }

    private void RestrarVenta() {

        String cliente = txtNombreVenta.getText();
        String vendedor = txtvendor.getText();
        int monto = Totalpagar;
        v.setCliente(cliente);
        v.setVendedor(vendedor);
        v.setTotal(monto);
        vDao.Registrarven(v);

    }

    private void RegistrarDetalle() {
        int id = vDao.IdVenta();
        for (int i = 0; i < tableVenta.getRowCount(); i++) {
            String cod = tableVenta.getValueAt(i, 0).toString();
            String marca = txtvendor.getText();
            int talla = Integer.parseInt(tableVenta.getValueAt(i, 2).toString());
            int cant = Integer.parseInt(tableVenta.getValueAt(i, 4).toString());
            int pre = Integer.parseInt(tableVenta.getValueAt(i, 5).toString());
            int val = Integer.parseInt(tableVenta.getValueAt(i, 6).toString());
            int tota = Integer.parseInt(tableVenta.getValueAt(i, 7).toString());
            de.setCodigopro(cod);
            de.setMarca(marca);
            de.setTall(talla);
            de.setCantidad(cant);
            de.setPrecio(pre);
            de.setValor(val);
            de.setTotal(tota);
            de.setId_venta(id);
            vDao.RegistrarDetalle(de);

        }

    }

    private void Actualizarcantidad() {
        for (int i = 0; i < tableVenta.getRowCount(); i++) {
            String cod = tableVenta.getValueAt(i, 0).toString();
            int talla = Integer.parseInt(tableVenta.getValueAt(i, 2).toString());
            int cant = Integer.parseInt(tableVenta.getValueAt(i, 4).toString());
            pro = proDao.BuscarPro(cod);
            int CantidadActual = pro.getCantidad() - cant;
            // colocar condicional
            vDao.ActualizarCantidad(CantidadActual, cod, talla, cant);
        }
    }

    private void LimpiarTableVenta() {
        tmp = (DefaultTableModel) tableVenta.getModel();
        int fila = tableVenta.getRowCount();
        for (int i = 0; i < fila; i++) {
            tmp.removeRow(0);
        }

    }

    private void pdf() {
        try {
            int id = vDao.IdVenta();
            FileOutputStream archivo;
            String fileName = "productos";
            String home = System.getProperty("user.home");
            File file = new File(home + "/Downloads/Ventas/venta" + id + ".pdf");
            //File file = new File("src/PDF/venta"+id+".pdf");
            archivo = new FileOutputStream(file);
            Document doc = new Document();
            PdfWriter.getInstance(doc, archivo);
            doc.open();
            Image img = Image.getInstance("src/img/lg1.png");

            Paragraph fecha = new Paragraph();
            Font negrita = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD, BaseColor.BLUE);
            fecha.add(Chunk.NEWLINE);
            Date date = new Date();
            fecha.add("Factura: " + id + "\n" + "Fecha: " + new SimpleDateFormat("dd-mm-yyyy HH:mm").format(date) + "\n\n");

            PdfPTable Encabezado = new PdfPTable(4);
            Encabezado.setWidthPercentage(100);
            Encabezado.getDefaultCell().setBorder(0);
            float[] ColumnaEncabezado = new float[]{20f, 30f, 70f, 40f};
            Encabezado.setWidths(ColumnaEncabezado);
            Encabezado.setHorizontalAlignment(Element.ALIGN_LEFT);

            Encabezado.addCell(img);

            String ruc = "91.529.252-2";
            String nombre = "Twister Kids";
            String Telefono = "3045945677";
            String Direcion = "Calle 37 #16-04";
            String Razon = "         centro comercial panama \n               111 ";

            Encabezado.addCell("");
            Encabezado.addCell("Rut: " + ruc + "\nNombre :" + nombre + "\nTelefono: " + Telefono + "\nDireccion: " + Direcion + "\n       " + Razon);
            Encabezado.addCell(fecha);
            doc.add(Encabezado);

            Paragraph cli = new Paragraph();
            cli.add(Chunk.NEWLINE);
            cli.add("Datos del cliente\n\n");
            doc.add(cli);

            PdfPTable tableclient = new PdfPTable(4);
            tableclient.setWidthPercentage(100);
            tableclient.getDefaultCell().setBorder(0);
            float[] Columnaclient = new float[]{30f, 50f, 40f, 50f};
            tableclient.setWidths(Columnaclient);
            tableclient.setHorizontalAlignment(Element.ALIGN_LEFT);
            PdfPCell cli1 = new PdfPCell(new Phrase("Documento", negrita));
            PdfPCell cli2 = new PdfPCell(new Phrase("Nombre", negrita));
            PdfPCell cli3 = new PdfPCell(new Phrase("Telefono", negrita));
            PdfPCell cli4 = new PdfPCell(new Phrase("Correo", negrita));
            cli1.setBorder(0);
            cli2.setBorder(0);
            cli3.setBorder(0);
            cli4.setBorder(0);
            tableclient.addCell(cli1);
            tableclient.addCell(cli2);
            tableclient.addCell(cli3);
            tableclient.addCell(cli4);
            tableclient.addCell(txtDocumentoVenta.getText());
            tableclient.addCell(txtNombreVenta.getText());
            tableclient.addCell(txtteleven.getText());
            tableclient.addCell(txtcorreov.getText() + "\n\n");

            doc.add(tableclient);

            //productos
            PdfPTable tableproducto = new PdfPTable(5);
            tableproducto.setWidthPercentage(100);
            tableproducto.getDefaultCell().setBorder(0);
            float[] Columnaprod = new float[]{40f, 40f, 40f, 40f, 40f};
            tableproducto.setWidths(Columnaprod);
            tableproducto.setHorizontalAlignment(Element.ALIGN_LEFT);
            PdfPCell pro1 = new PdfPCell(new Phrase("Referencia", negrita));
            PdfPCell pro2 = new PdfPCell(new Phrase("Marca", negrita));
            PdfPCell pro3 = new PdfPCell(new Phrase("Unidades", negrita));
            PdfPCell pro4 = new PdfPCell(new Phrase("Precio U.", negrita));
            PdfPCell pro5 = new PdfPCell(new Phrase("Precio T.", negrita));
            pro1.setBorder(0);
            pro2.setBorder(0);
            pro3.setBorder(0);
            pro4.setBorder(0);
            pro5.setBorder(0);
            pro1.setBackgroundColor(BaseColor.GREEN);
            pro2.setBackgroundColor(BaseColor.GREEN);
            pro3.setBackgroundColor(BaseColor.GREEN);
            pro4.setBackgroundColor(BaseColor.GREEN);
            pro5.setBackgroundColor(BaseColor.GREEN);

            tableproducto.addCell(pro1);
            tableproducto.addCell(pro2);
            tableproducto.addCell(pro3);
            tableproducto.addCell(pro4);
            tableproducto.addCell(pro5);
            for (int i = 0; i < tableVenta.getRowCount(); i++) {
                String Ref = tableVenta.getValueAt(i, 0).toString();
                String Mar = tableVenta.getValueAt(i, 1).toString();
                String Uni = tableVenta.getValueAt(i, 4).toString();
                String PreU = tableVenta.getValueAt(i, 5).toString();
                String PreT = tableVenta.getValueAt(i, 7).toString();
                tableproducto.addCell(Ref);
                tableproducto.addCell(Mar);
                tableproducto.addCell(Uni);
                tableproducto.addCell(PreU);
                tableproducto.addCell(PreT);

            }

            doc.add(tableproducto);

            Paragraph info = new Paragraph();
            info.add(Chunk.NEWLINE);
            String totalp = LabelTotalVenta.getText();
            info.add("Total a pagar          $ " + totalp);
            info.add("\n\n");
            info.setAlignment(Element.ALIGN_LEFT);
            doc.add(info);

            Paragraph rec = new Paragraph();
            rec.add(Chunk.NEWLINE);
            String dinerorec = txtRecibe.getText();
            rec.add("Monto                    $ " + dinerorec);
            info.add("\n\n");
            rec.setAlignment(Element.ALIGN_LEFT);
            doc.add(rec);

            Paragraph camb = new Paragraph();
            camb.add(Chunk.NEWLINE);
            String totalcam = txtcambio.getText();
            camb.add("Total De cambio     $ " + totalcam);
            info.add("\n\n");
            camb.setAlignment(Element.ALIGN_LEFT);
            doc.add(camb);

            Paragraph mes = new Paragraph();
            mes.add(Chunk.NEWLINE);
            mes.add("Gracias por su compra");
            mes.add("\n\n");
            mes.setAlignment(Element.ALIGN_CENTER);
            doc.add(mes);

            doc.close();
            archivo.close();
            Desktop.getDesktop().open(file);

        } catch (DocumentException | IOException e) {
            System.out.println(e.toString());
        }

    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel LabelTotalVenta;
    private javax.swing.JTable TableCliente;
    private javax.swing.JTable TableGasf;
    private javax.swing.JTable TableProducto;
    private javax.swing.JTable Tablegas;
    private javax.swing.JTable Tableprovedor;
    private javax.swing.JTable Tablevenf;
    private javax.swing.JButton btnBuscarprovedor;
    private javax.swing.JButton btnEditarCliente;
    private javax.swing.JButton btnEditarProd;
    private javax.swing.JButton btnEditarProveedor;
    private javax.swing.JButton btnEliminarCliente;
    private javax.swing.JButton btnEliminarProd;
    private javax.swing.JButton btnEliminarProveedor;
    private javax.swing.JButton btnGuardarCliente;
    private javax.swing.JButton btnGuardarProd;
    private javax.swing.JButton btnGuardarProveedor;
    private javax.swing.JButton btnNuevoProd;
    private javax.swing.JButton btnagreg;
    private javax.swing.JButton btnbuscarv;
    private javax.swing.JButton btncliente;
    private javax.swing.JButton btnedigas;
    private javax.swing.JButton btnelig;
    private javax.swing.JButton btnexel;
    private javax.swing.JButton btngastos;
    private javax.swing.JButton btnimprim;
    private javax.swing.JButton btnprodu;
    private javax.swing.JButton btnprovedor;
    private javax.swing.JButton btnventa;
    private javax.swing.JButton btnventas;
    private javax.swing.JComboBox<String> cbxProveedorProd;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel58;
    private javax.swing.JLabel jLabel59;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel60;
    private javax.swing.JLabel jLabel61;
    private javax.swing.JLabel jLabel62;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JTabbedPane jTabbedPane1;
    private com.toedter.calendar.JDateChooser jdateinicial;
    private javax.swing.JLabel labelVent;
    private javax.swing.JLabel labelgas;
    private javax.swing.JLabel labertotalca;
    private javax.swing.JTable tableVenta;
    private javax.swing.JTextField txtCantidaProd;
    private javax.swing.JTextField txtCantidad;
    private javax.swing.JTextField txtCantidadVenta;
    private javax.swing.JTextField txtCodigoProd;
    private javax.swing.JTextField txtCodigoVenta;
    private javax.swing.JTextField txtCorreoCliente;
    private javax.swing.JTextField txtCorreoProveedor;
    private javax.swing.JTextField txtDescripcionProd;
    private javax.swing.JTextField txtDescripcionVenta;
    private javax.swing.JTextField txtDocumentoProveedor;
    private javax.swing.JTextField txtDocumentoVenta;
    private javax.swing.JTextField txtIdProd;
    private javax.swing.JTextField txtIdProveedor;
    private javax.swing.JTextField txtIdentificacionCliente;
    private javax.swing.JTextField txtMarcaProd;
    private javax.swing.JTextField txtMarcaVenta;
    private javax.swing.JTextField txtNombreCliente;
    private javax.swing.JTextField txtNombreProveedor;
    private javax.swing.JTextField txtNombreVenta;
    private javax.swing.JTextField txtPrecioProd;
    private javax.swing.JTextField txtPrecioVenta;
    private javax.swing.JTextField txtRecibe;
    private javax.swing.JTextField txtTallaProd;
    private javax.swing.JTextField txtTallaProd22;
    private javax.swing.JTextField txtTallaProd23;
    private javax.swing.JTextField txtTallaProd24;
    private javax.swing.JTextField txtTallaProd25;
    private javax.swing.JTextField txtTallaProd26;
    private javax.swing.JTextField txtTallaProd27;
    private javax.swing.JTextField txtTallaProd28;
    private javax.swing.JTextField txtTallaProd29;
    private javax.swing.JTextField txtTallaProd30;
    private javax.swing.JTextField txtTallaProd31;
    private javax.swing.JTextField txtTallaProd32;
    private javax.swing.JTextField txtTallaProd33;
    private javax.swing.JTextField txtTallaProd34;
    private javax.swing.JTextField txtTallaProd35;
    private javax.swing.JTextField txtTelefonoCliente;
    private javax.swing.JTextField txtTelefonoProveedor;
    private javax.swing.JLabel txtcambio;
    private javax.swing.JTextField txtcorreov;
    private javax.swing.JTextField txtdescgas;
    private javax.swing.JTextField txtidcliente;
    private javax.swing.JTextField txtidgasto;
    private javax.swing.JTextField txtidventa;
    private javax.swing.JTextField txtstock;
    private javax.swing.JTextField txttallaven;
    private javax.swing.JTextField txtteleven;
    private javax.swing.JTextField txttipogas;
    private javax.swing.JTextField txtvalor;
    private javax.swing.JTextField txtvendor;
    // End of variables declaration//GEN-END:variables

}
