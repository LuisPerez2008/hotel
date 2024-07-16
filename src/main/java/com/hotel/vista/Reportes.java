/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.hotel.vista;

import com.hotel.controlador.DetalleDao;
import com.hotel.controlador.DetalleDaoImpl;
import com.hotel.controlador.InsumoDao;
import com.hotel.controlador.InsumoDaoImpl;
import com.hotel.controlador.UsuarioDao;
import com.hotel.controlador.UsuarioDaoImpl;
import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.HeadlessException;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class Reportes extends javax.swing.JFrame {

    UsuarioDao usuarioDao = new UsuarioDaoImpl();
    DetalleDao detalleDao = new DetalleDaoImpl();
    DefaultTableModel modelo = new DefaultTableModel();
    InsumoDao insumoDao = new InsumoDaoImpl();
    public Reportes() {
        initComponents();
        setLocationRelativeTo(null);
        jTabbedPane1.setSelectedIndex(0);
        TotalUltimoMes();
        ReservasUltimoMes();
        tablaMetodPago();
        GraficoTipoPago();
        txtNombreUsuario.setVisible(false);
        txtIdDetalle.setVisible(false);
    }

    public void TotalUltimoMes() {
        double tot = detalleDao.GanaciaUltimoMes();
        String tota = String.valueOf(tot);
        txtPrecioUltimoMes.setText(tota);
        double totalPro = detalleDao.GanaciaProductoUltimoMes();
        String totas = String.valueOf(totalPro);
        txtPrecioProductosUltimo.setText(totas);
    }

    public void ReservasUltimoMes() {
        XYSeries series = new XYSeries("Reservas");

        Map<String, Integer> data = detalleDao.reservasUltimos30Dias();

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        for (Map.Entry<String, Integer> entry : data.entrySet()) {
            try {
                String fecha = entry.getKey(); // Assuming the key is in 'YYYY-MM-DD' format
                // Extract the day from the date string
                int day = Integer.parseInt(fecha.split("-")[2]); // Get the day part
                series.add(day, entry.getValue()); // Add the day for the X-axis
            } catch (Exception e) {
                e.printStackTrace(); // Handle parsing exceptions
            }
        }
        // Create the dataset and add the series
        XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(series);

        JFreeChart chart = ChartFactory.createXYLineChart("registros ultimo 30 dias",
                "dias",
                "Cantidad de Registros",
                dataset,
                PlotOrientation.VERTICAL, true, true, true);
        Color colorPersonalizado = new Color(255, 153, 153);

        // Cambiar el color de fondo del gráfico
        chart.setBackgroundPaint(colorPersonalizado);

        XYPlot plot = chart.getXYPlot();
        plot.setBackgroundPaint(colorPersonalizado);

        XYLineAndShapeRenderer render = new XYLineAndShapeRenderer();
        render.setSeriesPaint(0, Color.BLUE);
        render.setSeriesStroke(0, new BasicStroke(2));
        chart.getXYPlot().setRenderer(render);

        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(620, 340));
        jpaneReservasMes.setLayout(new BorderLayout());
        jpaneReservasMes.add(chartPanel, BorderLayout.NORTH);
        pack();
        //revalidate();
        repaint();
    }

    public void tablaMetodPago() {
        DefaultTableModel model = (DefaultTableModel) tbtTipoPago.getModel();

        model.setRowCount(0);

        Map<String, Integer> metodoPagoMap = detalleDao.cantMetodoPago();

        for (Map.Entry<String, Integer> entry : metodoPagoMap.entrySet()) {
            String tipoPago = entry.getKey();
            Integer cantidad = entry.getValue();
            model.addRow(new Object[]{tipoPago, cantidad});
        }
    }

    public void tablaUsuarios() {
        modelo = (DefaultTableModel) tbtUsuarios.getModel();

        modelo.setRowCount(0);

        Map<String, Integer> UsuariosMap = usuarioDao.reporteCantidadDeregistrosXusuario();

        for (Map.Entry<String, Integer> entry : UsuariosMap.entrySet()) {
            String tipoPago = entry.getKey();
            Integer cantidad = entry.getValue();
            modelo.addRow(new Object[]{tipoPago, cantidad});
        }
    }
    
     public void tablaProductosmas() {
        modelo = (DefaultTableModel) tableMasVendido.getModel();

        modelo.setRowCount(0);

        Map<String, Integer> InsumosMap = insumoDao.productoxCantidad();

        for (Map.Entry<String, Integer> entry : InsumosMap.entrySet()) {
            String nombre = entry.getKey();
            Integer cantidad = entry.getValue();
            modelo.addRow(new Object[]{nombre, cantidad});
        }
    }
     
      public void tablaProductosmenos() {
        modelo = (DefaultTableModel) tableMenosVendido.getModel();

        modelo.setRowCount(0);

        Map<String, Integer> InsumosMap = insumoDao.productoxCantidadMenos();

        for (Map.Entry<String, Integer> entry : InsumosMap.entrySet()) {
            String nombre = entry.getKey();
            Integer cantidad = entry.getValue();
            modelo.addRow(new Object[]{nombre, cantidad});
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel8 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        btnUsuarios = new javax.swing.JButton();
        btnHbaitaciones = new javax.swing.JButton();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtPrecioUltimoMes = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtPrecioProductosUltimo = new javax.swing.JLabel();
        jpaneReservasMes = new javax.swing.JPanel();
        jgraficotipoPago = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tbtTipoPago = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jgrafico = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbtRegistrosUser = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbtUsuarios = new javax.swing.JTable();
        jLabel7 = new javax.swing.JLabel();
        btnVerRegistros = new javax.swing.JButton();
        btnVerDetalle = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();
        txtNombreUsuario = new javax.swing.JTextField();
        txtIdDetalle = new javax.swing.JTextField();
        jPanel8 = new javax.swing.JPanel();
        jpanelProductos = new javax.swing.JPanel();
        jpanelproductos22 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tableMenosVendido = new javax.swing.JTable();
        jScrollPane5 = new javax.swing.JScrollPane();
        tableMasVendido = new javax.swing.JTable();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();

        jLabel8.setText("PRODCUTS");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);

        btnUsuarios.setIcon(new javax.swing.ImageIcon("C:\\Users\\51901\\Documents\\NetBeansProjects\\hotel\\src\\main\\resourses\\usuarios.png")); // NOI18N
        btnUsuarios.setText("Trabajadores");
        btnUsuarios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUsuariosActionPerformed(evt);
            }
        });

        btnHbaitaciones.setIcon(new javax.swing.ImageIcon("C:\\Users\\51901\\Documents\\NetBeansProjects\\hotel\\src\\main\\resourses\\habitacion.png")); // NOI18N
        btnHbaitaciones.setText("Productos");
        btnHbaitaciones.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHbaitacionesActionPerformed(evt);
            }
        });

        jPanel4.setBackground(new java.awt.Color(102, 102, 255));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel1.setText("GANANCIAS TOTALES DEL ULTIMO MES");

        txtPrecioUltimoMes.setFont(new java.awt.Font("Segoe UI", 3, 36)); // NOI18N

        jLabel2.setFont(new java.awt.Font("Segoe UI", 3, 36)); // NOI18N
        jLabel2.setText("S/.");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(18, Short.MAX_VALUE)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtPrecioUltimoMes, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(45, 45, 45))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(txtPrecioUltimoMes, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel5.setBackground(new java.awt.Color(153, 153, 255));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel5.setText("GANACIA POR PRODUCTOS DEL ULTIMO MES");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 3, 36)); // NOI18N
        jLabel6.setText("S/.");

        txtPrecioProductosUltimo.setFont(new java.awt.Font("Segoe UI", 3, 36)); // NOI18N

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(18, Short.MAX_VALUE)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 287, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(txtPrecioProductosUltimo, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5)
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(txtPrecioProductosUltimo, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(47, 47, 47))
        );

        jpaneReservasMes.setBackground(new java.awt.Color(255, 153, 153));

        javax.swing.GroupLayout jpaneReservasMesLayout = new javax.swing.GroupLayout(jpaneReservasMes);
        jpaneReservasMes.setLayout(jpaneReservasMesLayout);
        jpaneReservasMesLayout.setHorizontalGroup(
            jpaneReservasMesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 618, Short.MAX_VALUE)
        );
        jpaneReservasMesLayout.setVerticalGroup(
            jpaneReservasMesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 301, Short.MAX_VALUE)
        );

        jgraficotipoPago.setBackground(new java.awt.Color(153, 153, 255));

        javax.swing.GroupLayout jgraficotipoPagoLayout = new javax.swing.GroupLayout(jgraficotipoPago);
        jgraficotipoPago.setLayout(jgraficotipoPagoLayout);
        jgraficotipoPagoLayout.setHorizontalGroup(
            jgraficotipoPagoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jgraficotipoPagoLayout.setVerticalGroup(
            jgraficotipoPagoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jPanel6.setBackground(new java.awt.Color(0, 204, 204));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setText("Tipo de Pago Ultimo mes");

        tbtTipoPago.setBackground(new java.awt.Color(0, 204, 204));
        tbtTipoPago.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Tipo", "Cant."
            }
        ));
        tbtTipoPago.setGridColor(new java.awt.Color(255, 255, 255));
        tbtTipoPago.setSelectionBackground(new java.awt.Color(153, 255, 255));
        tbtTipoPago.setSelectionForeground(new java.awt.Color(255, 51, 51));
        jScrollPane3.setViewportView(tbtTipoPago);
        if (tbtTipoPago.getColumnModel().getColumnCount() > 0) {
            tbtTipoPago.getColumnModel().getColumn(1).setMinWidth(75);
            tbtTipoPago.getColumnModel().getColumn(1).setPreferredWidth(75);
            tbtTipoPago.getColumnModel().getColumn(1).setMaxWidth(75);
        }

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap(56, Short.MAX_VALUE)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31))
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(24, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jpaneReservasMes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jgraficotipoPago, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(26, 26, 26))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 128, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jpaneReservasMes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jgraficotipoPago, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(76, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Deashboard", jPanel3);

        jgrafico.setBackground(new java.awt.Color(204, 204, 204));

        javax.swing.GroupLayout jgraficoLayout = new javax.swing.GroupLayout(jgrafico);
        jgrafico.setLayout(jgraficoLayout);
        jgraficoLayout.setHorizontalGroup(
            jgraficoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 502, Short.MAX_VALUE)
        );
        jgraficoLayout.setVerticalGroup(
            jgraficoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 319, Short.MAX_VALUE)
        );

        tbtRegistrosUser.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Cliente", "Total"
            }
        ));
        tbtRegistrosUser.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbtRegistrosUserMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbtRegistrosUser);
        if (tbtRegistrosUser.getColumnModel().getColumnCount() > 0) {
            tbtRegistrosUser.getColumnModel().getColumn(0).setMinWidth(75);
            tbtRegistrosUser.getColumnModel().getColumn(0).setPreferredWidth(75);
            tbtRegistrosUser.getColumnModel().getColumn(0).setMaxWidth(75);
            tbtRegistrosUser.getColumnModel().getColumn(2).setMinWidth(100);
            tbtRegistrosUser.getColumnModel().getColumn(2).setPreferredWidth(100);
            tbtRegistrosUser.getColumnModel().getColumn(2).setMaxWidth(100);
        }

        tbtUsuarios.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nombre", "Cantidad"
            }
        ));
        tbtUsuarios.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbtUsuariosMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tbtUsuarios);
        if (tbtUsuarios.getColumnModel().getColumnCount() > 0) {
            tbtUsuarios.getColumnModel().getColumn(1).setMinWidth(90);
            tbtUsuarios.getColumnModel().getColumn(1).setPreferredWidth(90);
            tbtUsuarios.getColumnModel().getColumn(1).setMaxWidth(90);
        }

        jLabel7.setText("Registros: ");

        btnVerRegistros.setIcon(new javax.swing.ImageIcon("C:\\Users\\51901\\Documents\\NetBeansProjects\\hotel\\src\\main\\resourses\\buscar-2.png")); // NOI18N
        btnVerRegistros.setText("Ver  Registros");
        btnVerRegistros.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVerRegistrosActionPerformed(evt);
            }
        });

        btnVerDetalle.setIcon(new javax.swing.ImageIcon("C:\\Users\\51901\\Documents\\NetBeansProjects\\hotel\\src\\main\\resourses\\buscar-2.png")); // NOI18N
        btnVerDetalle.setText("Ver Detalle");
        btnVerDetalle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVerDetalleActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 451, Short.MAX_VALUE)
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 124, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 418, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addComponent(txtNombreUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnVerRegistros, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(txtIdDetalle, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnVerDetalle, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 78, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jgrafico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(14, 14, 14))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnVerRegistros)
                            .addComponent(txtNombreUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(4, 4, 4)
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnVerDetalle)
                            .addComponent(txtIdDetalle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(72, 72, 72))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jgrafico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27)
                        .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(38, Short.MAX_VALUE))))
        );

        jTabbedPane1.addTab("", jPanel2);

        javax.swing.GroupLayout jpanelProductosLayout = new javax.swing.GroupLayout(jpanelProductos);
        jpanelProductos.setLayout(jpanelProductosLayout);
        jpanelProductosLayout.setHorizontalGroup(
            jpanelProductosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 452, Short.MAX_VALUE)
        );
        jpanelProductosLayout.setVerticalGroup(
            jpanelProductosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 264, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jpanelproductos22Layout = new javax.swing.GroupLayout(jpanelproductos22);
        jpanelproductos22.setLayout(jpanelproductos22Layout);
        jpanelproductos22Layout.setHorizontalGroup(
            jpanelproductos22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 452, Short.MAX_VALUE)
        );
        jpanelproductos22Layout.setVerticalGroup(
            jpanelproductos22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 278, Short.MAX_VALUE)
        );

        tableMenosVendido.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Producto", "Cantidad"
            }
        ));
        jScrollPane4.setViewportView(tableMenosVendido);
        if (tableMenosVendido.getColumnModel().getColumnCount() > 0) {
            tableMenosVendido.getColumnModel().getColumn(1).setMinWidth(100);
            tableMenosVendido.getColumnModel().getColumn(1).setPreferredWidth(100);
            tableMenosVendido.getColumnModel().getColumn(1).setMaxWidth(100);
        }

        tableMasVendido.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Producto", "Cantidad"
            }
        ));
        jScrollPane5.setViewportView(tableMasVendido);
        if (tableMasVendido.getColumnModel().getColumnCount() > 0) {
            tableMasVendido.getColumnModel().getColumn(1).setMinWidth(100);
            tableMasVendido.getColumnModel().getColumn(1).setPreferredWidth(100);
            tableMasVendido.getColumnModel().getColumn(1).setMaxWidth(100);
        }

        jLabel9.setText("PRODUCTOS MAS VENDIDOS");

        jLabel10.setText("PRODUCTOS MENOS VENDIDOS");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(jpanelProductos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(jScrollPane5)
                        .addGap(49, 49, 49))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jpanelproductos22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(40, 40, 40))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(jLabel10))
                .addGap(18, 18, 18)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 104, Short.MAX_VALUE)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(28, 28, 28)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jpanelproductos22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jpanelProductos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(69, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("", jPanel8);

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel3.setText("HOTEL LA PERLA");

        jButton1.setIcon(new javax.swing.ImageIcon("C:\\Users\\51901\\Documents\\NetBeansProjects\\hotel\\src\\main\\resourses\\salir.png")); // NOI18N
        jButton1.setText("Salir");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnUsuarios, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnHbaitaciones, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(51, 51, 51))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnHbaitaciones)
                        .addComponent(jButton1)
                        .addComponent(btnUsuarios))
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 577, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnUsuariosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUsuariosActionPerformed
        jTabbedPane1.setSelectedIndex(1);
        GraficoVetasXVendedor();
        tablaUsuarios();
    }//GEN-LAST:event_btnUsuariosActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btnVerRegistrosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVerRegistrosActionPerformed
        int selectedRow = tbtUsuarios.getSelectedRow();
        if (selectedRow != -1) {
            DefaultTableModel model = (DefaultTableModel) tbtRegistrosUser.getModel();
            String nombre = txtNombreUsuario.getText();
            List<HashMap<String, Object>> registros = usuarioDao.buscarRegistroXUsuario(nombre);
            model.setRowCount(0);

            // Agregar los registros a la tabla
            for (HashMap<String, Object> registro : registros) {
                Object[] rowData = new Object[3];
                rowData[0] = registro.get("id");
                rowData[1] = registro.get("nombre");
                rowData[2] = registro.get("precio_total");
                model.addRow(rowData);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Selecione un Usuario");
        }


    }//GEN-LAST:event_btnVerRegistrosActionPerformed

    private void tbtUsuariosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbtUsuariosMouseClicked
        int fila = tbtUsuarios.rowAtPoint(evt.getPoint());
        txtNombreUsuario.setText(tbtUsuarios.getValueAt(fila, 0).toString());
    }//GEN-LAST:event_tbtUsuariosMouseClicked

    private void tbtRegistrosUserMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbtRegistrosUserMouseClicked
        int fila = tbtRegistrosUser.rowAtPoint(evt.getPoint());
        txtIdDetalle.setText(tbtRegistrosUser.getValueAt(fila, 0).toString());
    }//GEN-LAST:event_tbtRegistrosUserMouseClicked

    private void btnVerDetalleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVerDetalleActionPerformed
        int selectedRow = tbtRegistrosUser.getSelectedRow();
        if (selectedRow != -1) {
            int id = Integer.parseInt(txtIdDetalle.getText());
            List<HashMap<String, Object>> detalles = detalleDao.listarDetalleReporte(id);
            List<HashMap<String, Object>> extras = detalleDao.listarExtrasReporte(id);
            StringBuilder mensaje = new StringBuilder();

            for (HashMap<String, Object> detalle : detalles) {
                mensaje.append("Id Detalle: ").append(detalle.get("Id Detalle")).append("\n");
                mensaje.append("N° Habitacion: ").append(detalle.get("N° Habitacion")).append("\n");
                mensaje.append("Cliente: ").append(detalle.get("Cliente")).append("\n");
                mensaje.append("Fecha de Ingreso: ").append(detalle.get("Fecha de Ingreso")).append("\n");
                mensaje.append("Fecha de Salida: ").append(detalle.get("Fecha de Salida")).append("\n");

                // Agregar los extras antes del Precio Total
                mensaje.append("Extras:\n");
                for (HashMap<String, Object> extra : extras) {

                    mensaje.append("  Producto: ").append(extra.get("Producto")).append("\n");
                    mensaje.append("  Cantidad: ").append(extra.get("cantidad")).append("\n");
                    mensaje.append("  Precio: ").append(extra.get("precio")).append("\n");

                }

                mensaje.append("Precio Total: ").append(detalle.get("Precio Total")).append("\n");
                mensaje.append("\n"); // Añadir un salto de línea entre cada registro
            }

            JOptionPane.showMessageDialog(null, mensaje.toString());

        }
    }//GEN-LAST:event_btnVerDetalleActionPerformed

    private void btnHbaitacionesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHbaitacionesActionPerformed
        jTabbedPane1.setSelectedIndex(2);
        crearGraficoBarras();
        crearGraficoBarras2();
        tablaProductosmas();
        tablaProductosmenos();
        
    }//GEN-LAST:event_btnHbaitacionesActionPerformed

    public static void main(String args[]) {
        /*
         * Set the Nimbus look and feel
         */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /*
         * If Nimbus (introduced in Java SE 6) is not available, stay with the
         * default look and feel. For details see
         * http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Reportes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Reportes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Reportes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Reportes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /*
         * Create and display the form
         */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Reportes().setVisible(true);
            }
        });
    }

    public void GraficoVetasXVendedor() {

        Map<String, Integer> listaMpita = usuarioDao.reporteCantidadDeregistrosXusuario();
        DefaultPieDataset data = new DefaultPieDataset();

        for (Map.Entry<String, Integer> entry : listaMpita.entrySet()) {
            data.setValue(entry.getKey(), entry.getValue());

        }
        JFreeChart grafico = ChartFactory.createPieChart(
                "ventas por vendedores",
                data,
                true,
                true,
                false
        );
        PiePlot plot = (PiePlot) grafico.getPlot();
        plot.setLabelGenerator(new StandardPieSectionLabelGenerator("{0} = {1} ({2})", NumberFormat.getNumberInstance(), NumberFormat.getPercentInstance()));

        ChartPanel chartPanel = new ChartPanel(grafico);
        chartPanel.setPreferredSize(new Dimension(480, 295));

        jgrafico.setLayout(new BorderLayout());
        jgrafico.add(chartPanel, BorderLayout.NORTH);
        pack();
        repaint();
    }

    public void GraficoTipoPago() {

        Map<String, Integer> listaMpita = detalleDao.cantMetodoPago();
        DefaultPieDataset data = new DefaultPieDataset();

        for (Map.Entry<String, Integer> entry : listaMpita.entrySet()) {
            data.setValue(entry.getKey(), entry.getValue());

        }
        JFreeChart grafico = ChartFactory.createPieChart(
                "Tipo de pago",
                data,
                true,
                true,
                false
        );
        Color colorPersonalizado = new Color(153, 153, 255);
        grafico.setBackgroundPaint(colorPersonalizado);

        PiePlot plot = (PiePlot) grafico.getPlot();
        plot.setLabelGenerator(new StandardPieSectionLabelGenerator("{0} = {1} ({2})", NumberFormat.getNumberInstance(), NumberFormat.getPercentInstance()));

        plot.setBackgroundPaint(colorPersonalizado);

        ChartPanel chartPanel = new ChartPanel(grafico);
        chartPanel.setPreferredSize(new Dimension(300, 295));

        jgraficotipoPago.setLayout(new BorderLayout());
        jgraficotipoPago.add(chartPanel, BorderLayout.NORTH);
        pack();
        repaint();
    }

    public void crearGraficoBarras() {
        // Obtener datos
        Map<String, Integer> datosProductos = insumoDao.productoxCantidad();

        // Crear el conjunto de datos
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        for (Map.Entry<String, Integer> entry : datosProductos.entrySet()) {
            dataset.addValue(entry.getValue(), "Cantidad", entry.getKey());
        }

        // Crear el gráfico
        JFreeChart grafico = ChartFactory.createBarChart(
                "Productos x Cantidad", // Título del gráfico
                "Productos", // Etiqueta del eje X
                "Cantidad", // Etiqueta del eje Y
                dataset, // Datos
                PlotOrientation.VERTICAL, // Orientación
                true, // Incluir leyenda
                true, // Tooltips
                false // URLs
        );

        // Crear el panel y agregar el gráfico
        ChartPanel chartPanel = new ChartPanel(grafico);
        chartPanel.setPreferredSize(new Dimension(450, 295));

        // Crear un JFrame para mostrar el gráfico
        jpanelProductos.setLayout(new BorderLayout());
        jpanelProductos.add(chartPanel, BorderLayout.NORTH);
        pack();
        repaint();
    }
    
    public void crearGraficoBarras2() {
        // Obtener datos
        Map<String, Integer> datosProductos = insumoDao.productoxCantidadMenos();

        // Crear el conjunto de datos
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        for (Map.Entry<String, Integer> entry : datosProductos.entrySet()) {
            dataset.addValue(entry.getValue(), "Cantidad", entry.getKey());
        }

        // Crear el gráfico
        JFreeChart grafico = ChartFactory.createBarChart(
                "Productos x Cantidad", // Título del gráfico
                "Productos", // Etiqueta del eje X
                "Cantidad", // Etiqueta del eje Y
                dataset, // Datos
                PlotOrientation.VERTICAL, // Orientación
                true, // Incluir leyenda
                true, // Tooltips
                false // URLs
        );

        // Crear el panel y agregar el gráfico
        ChartPanel chartPanel = new ChartPanel(grafico);
        chartPanel.setPreferredSize(new Dimension(450, 295));

        // Crear un JFrame para mostrar el gráfico
        jpanelproductos22.setLayout(new BorderLayout());
        jpanelproductos22.add(chartPanel, BorderLayout.NORTH);
        pack();
        repaint();
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnHbaitaciones;
    private javax.swing.JButton btnUsuarios;
    private javax.swing.JButton btnVerDetalle;
    private javax.swing.JButton btnVerRegistros;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
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
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JPanel jgrafico;
    private javax.swing.JPanel jgraficotipoPago;
    private javax.swing.JPanel jpaneReservasMes;
    private javax.swing.JPanel jpanelProductos;
    private javax.swing.JPanel jpanelproductos22;
    private javax.swing.JTable tableMasVendido;
    private javax.swing.JTable tableMenosVendido;
    private javax.swing.JTable tbtRegistrosUser;
    private javax.swing.JTable tbtTipoPago;
    private javax.swing.JTable tbtUsuarios;
    private javax.swing.JTextField txtIdDetalle;
    private javax.swing.JTextField txtNombreUsuario;
    private javax.swing.JLabel txtPrecioProductosUltimo;
    private javax.swing.JLabel txtPrecioUltimoMes;
    // End of variables declaration//GEN-END:variables
}
