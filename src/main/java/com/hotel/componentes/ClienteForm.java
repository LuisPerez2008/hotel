/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.hotel.componentes;

import com.hotel.controlador.HuespedDao;
import com.hotel.controlador.HuespedDaoImpl;
import com.hotel.modelo.Huesped;
import com.hotel.utils.Evento;
import com.hotel.utils.ReporteExcel;
import com.hotel.utils.ReporteExcelClie;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author 51901
 */
public class ClienteForm extends javax.swing.JPanel {

    HuespedDao huespedDao = new HuespedDaoImpl();
    Evento ev = new Evento();

    public ClienteForm() {
        initComponents();
        txtId.setVisible(false);
        listarClientes();
        btnLimpiar.setEnabled(false);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtDni = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtCorreo = new javax.swing.JTextField();
        txtTelefono = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtId = new javax.swing.JTextField();
        btnEditar = new javax.swing.JButton();
        btnElimnar = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();
        btnLimpiar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbtCliente = new javax.swing.JTable();
        btnBuscar = new javax.swing.JButton();
        txtBuscar = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel1.setText("Nombre :");

        txtDni.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtDniKeyTyped(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel2.setText("DNI :");

        txtNombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNombreKeyTyped(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel3.setText("Telefono :");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel4.setText("Email :");

        txtTelefono.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtTelefonoKeyTyped(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel5.setText("CLIENTES");

        btnEditar.setIcon(new javax.swing.ImageIcon("C:\\Users\\51901\\Documents\\NetBeansProjects\\hotel\\src\\main\\resourses\\editar.png")); // NOI18N
        btnEditar.setText("EDITAR");
        btnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarActionPerformed(evt);
            }
        });

        btnElimnar.setIcon(new javax.swing.ImageIcon("C:\\Users\\51901\\Documents\\NetBeansProjects\\hotel\\src\\main\\resourses\\tacho.png")); // NOI18N
        btnElimnar.setText("ELIMINAR");
        btnElimnar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnElimnarActionPerformed(evt);
            }
        });

        btnGuardar.setIcon(new javax.swing.ImageIcon("C:\\Users\\51901\\Documents\\NetBeansProjects\\hotel\\src\\main\\resourses\\guardar.png")); // NOI18N
        btnGuardar.setText("REGISTRAR");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        btnLimpiar.setIcon(new javax.swing.ImageIcon("C:\\Users\\51901\\Documents\\NetBeansProjects\\hotel\\src\\main\\resourses\\limpiar.png")); // NOI18N
        btnLimpiar.setText("LIMPIAR");
        btnLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(47, 47, 47)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtDni, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(54, 54, 54)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtCorreo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(0, 64, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnEditar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btnLimpiar, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnElimnar, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(9, 9, 9)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnLimpiar, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnElimnar, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCorreo)
                    .addComponent(txtDni))
                .addGap(27, 27, 27))
        );

        tbtCliente.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "NOMBRE", "DNI", "TELEFONO", "EMAIL"
            }
        ));
        tbtCliente.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbtClienteMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbtCliente);
        if (tbtCliente.getColumnModel().getColumnCount() > 0) {
            tbtCliente.getColumnModel().getColumn(0).setMinWidth(70);
            tbtCliente.getColumnModel().getColumn(0).setPreferredWidth(70);
            tbtCliente.getColumnModel().getColumn(0).setMaxWidth(70);
        }

        btnBuscar.setIcon(new javax.swing.ImageIcon("C:\\Users\\51901\\Documents\\NetBeansProjects\\hotel\\src\\main\\resourses\\buscar.png")); // NOI18N
        btnBuscar.setText("BUSCAR");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        jButton1.setIcon(new javax.swing.ImageIcon("C:\\Users\\51901\\Documents\\NetBeansProjects\\hotel\\src\\main\\resourses\\exel.png")); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 919, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 744, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(116, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton1)
                .addGap(43, 43, 43))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        if (validarLabels()) {
            String documento = txtDni.getText();
            if (huespedDao.documentoDuplicado(documento)) {
                JOptionPane.showInternalMessageDialog(null, "El DNI ya Existe", "Aviso", JOptionPane.ERROR_MESSAGE);
            } else {
                int res = JOptionPane.showConfirmDialog(null, "¿Registrar Cliente?");
                if (res == 0) {
                    Huesped hp = new Huesped();
                    hp.setNombre(txtNombre.getText());
                    hp.setDocumento(txtDni.getText());
                    hp.setTelefono(txtTelefono.getText());
                    hp.setCorreo(txtCorreo.getText());
                    huespedDao.registrar(hp);
                    listarClientes();
                    limpiarLabels();
                    JOptionPane.showMessageDialog(null, "Cliente Registrado");
                }
            }

        } else {
            JOptionPane.showMessageDialog(null, "Completa todos los campos");
        }
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        try {
            DefaultTableModel model = (DefaultTableModel) tbtCliente.getModel();
            model.setRowCount(0); // Limpiar la tabla antes de agregar nuevos registros
            String nombre = txtBuscar.getText();
            List<Huesped> Huespedes = huespedDao.BuscarPorNombre(nombre);

            if (Huespedes.isEmpty()) {
                // Mostrar una alerta si no se encontraron registros
                JOptionPane.showMessageDialog(this, "No se encontraron registros", "Sin resultados", JOptionPane.INFORMATION_MESSAGE);
            } else {
                Object[] ob = new Object[5];
                for (int i = 0; i < Huespedes.size(); i++) {
                    ob[0] = Huespedes.get(i).getId();
                    ob[1] = Huespedes.get(i).getNombre();
                    ob[2] = Huespedes.get(i).getDocumento();
                    ob[3] = Huespedes.get(i).getTelefono();
                    ob[4] = Huespedes.get(i).getCorreo();

                    model.addRow(ob);
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarActionPerformed
        limpiarLabels();
    }//GEN-LAST:event_btnLimpiarActionPerformed

    private void tbtClienteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbtClienteMouseClicked
        int fila = tbtCliente.rowAtPoint(evt.getPoint());
        txtId.setText(tbtCliente.getValueAt(fila, 0).toString());
        txtDni.setText(tbtCliente.getValueAt(fila, 2).toString());
        txtNombre.setText(tbtCliente.getValueAt(fila, 1).toString());
        txtTelefono.setText(tbtCliente.getValueAt(fila, 3).toString());
        txtCorreo.setText(tbtCliente.getValueAt(fila, 4).toString());
        btnGuardar.setEnabled(false);
        btnLimpiar.setEnabled(true);

    }//GEN-LAST:event_tbtClienteMouseClicked

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
        int selectedRow = tbtCliente.getSelectedRow();
        if (selectedRow != -1) {
            if (validarLabels()) {
                int res = JOptionPane.showConfirmDialog(null, "¿Editar Cliente?");
                if (res == 0) {
                    Huesped hp = new Huesped();
                    hp.setId((Integer.parseInt(txtId.getText())));
                    hp.setNombre(txtNombre.getText());
                    hp.setDocumento(txtDni.getText());
                    hp.setTelefono(txtTelefono.getText());
                    hp.setCorreo(txtCorreo.getText());
                    huespedDao.actualizar(hp);
                    listarClientes();
                    limpiarLabels();
                    JOptionPane.showMessageDialog(null, "Cliente Actualizado");
                }

            }
        } else {
            JOptionPane.showMessageDialog(null, "Selecione un Cliente");
        }
    }//GEN-LAST:event_btnEditarActionPerformed

    private void btnElimnarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnElimnarActionPerformed
        if (!"".equals(txtId.getText())) {
            int pregunta = JOptionPane.showConfirmDialog(null, "Esta seguro de eliminar");

            if (pregunta == 0) {
                int id = Integer.parseInt(txtId.getText());

                huespedDao.borrar(id);
                JOptionPane.showMessageDialog(null, "Husped eliminado");
                limpiarLabels();
                listarClientes();
            }
        } else {
            JOptionPane.showMessageDialog(null, "Selecione un cliente");
        }
    }//GEN-LAST:event_btnElimnarActionPerformed

    private void txtNombreKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreKeyTyped

        ev.textKeyPress(evt);
    }//GEN-LAST:event_txtNombreKeyTyped

    private void txtDniKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDniKeyTyped
        ev.numberKeyPress(evt);
    }//GEN-LAST:event_txtDniKeyTyped

    private void txtTelefonoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTelefonoKeyTyped
        ev.numberKeyPress(evt);
    }//GEN-LAST:event_txtTelefonoKeyTyped

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        DefaultTableModel modeloTabla = (DefaultTableModel) tbtCliente.getModel();
        ReporteExcelClie.generateReport(modeloTabla);
    }//GEN-LAST:event_jButton1ActionPerformed

    public void listarClientes() {
        try {
            List<Huesped> listaClientes = huespedDao.listar();
            DefaultTableModel model = (DefaultTableModel) tbtCliente.getModel();
            model.setRowCount(0);
            Object[] ob = new Object[5];
            for (int i = 0; i < listaClientes.size(); i++) {
                ob[0] = listaClientes.get(i).getId();
                ob[1] = listaClientes.get(i).getNombre();
                ob[2] = listaClientes.get(i).getDocumento();
                ob[3] = listaClientes.get(i).getTelefono();
                ob[4] = listaClientes.get(i).getCorreo();

                model.addRow(ob);
            }
            tbtCliente.setModel(model);

        } catch (Exception e) {
            System.out.println(e.toString());
        }

    }

    public boolean validarLabels() {
        if (!"".equals(txtNombre.getText()) && !"".equals(txtDni.getText()) && !"".equals(txtCorreo.getText()) && !"".equals(txtTelefono.getText())) {
            return true;
        }
        return false;
    }

    public void limpiarLabels() {
        txtCorreo.setText("");
        txtNombre.setText("");
        txtTelefono.setText("");
        txtDni.setText("");
        txtId.setText("");
        btnGuardar.setEnabled(true);
        btnLimpiar.setEnabled(false);
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnElimnar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tbtCliente;
    private javax.swing.JTextField txtBuscar;
    private javax.swing.JTextField txtCorreo;
    private javax.swing.JTextField txtDni;
    private javax.swing.JTextField txtId;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtTelefono;
    // End of variables declaration//GEN-END:variables
}
