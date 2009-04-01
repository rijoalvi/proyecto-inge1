/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * frameBusqueda.java
 *
 * Created on 13-mar-2009, 19:45:31
 */
package gestiontipocampo;

import javax.swing.*;
//import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import javax.swing.table.TableModel;

/**
 *
 * @author Alberto
 */
public class frameBusqueda extends javax.swing.JFrame {
    private TableModel DefaultTableModel;
    private frameManejoCampos madre;
    /** Creates new form frameBusqueda */
    public frameBusqueda() {
        initComponents();
    }

    public frameBusqueda(frameManejoCampos frameMadre) {
        initComponents();
        madre = frameMadre;
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tablaBusqueda = new javax.swing.JTable();
        botonOK = new javax.swing.JButton();
        botonCancelar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setName("Form"); // NOI18N

        jScrollPane1.setName("jScrollPane1"); // NOI18N

        tablaBusqueda.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Incremental", "Nombre", "Nota", "Fecha última actualización"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tablaBusqueda.setName("tablaBusqueda"); // NOI18N
        tablaBusqueda.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                tablaBusquedaMouseEntered(evt);
            }
        });
        jScrollPane1.setViewportView(tablaBusqueda);
        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(gestiontipocampo.GestionTipoCampoApp.class).getContext().getResourceMap(frameBusqueda.class);
        tablaBusqueda.getColumnModel().getColumn(0).setHeaderValue(resourceMap.getString("tablaBusqueda.columnModel.title0")); // NOI18N
        tablaBusqueda.getColumnModel().getColumn(1).setHeaderValue(resourceMap.getString("tablaBusqueda.columnModel.title1")); // NOI18N
        tablaBusqueda.getColumnModel().getColumn(2).setHeaderValue(resourceMap.getString("tablaBusqueda.columnModel.title2")); // NOI18N
        tablaBusqueda.getColumnModel().getColumn(3).setHeaderValue(resourceMap.getString("tablaBusqueda.columnModel.title3")); // NOI18N

        botonOK.setText(resourceMap.getString("botonOK.text")); // NOI18N
        botonOK.setName("botonOK"); // NOI18N
        botonOK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonOKActionPerformed(evt);
            }
        });

        botonCancelar.setText(resourceMap.getString("botonCancelar.text")); // NOI18N
        botonCancelar.setName("botonCancelar"); // NOI18N
        botonCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonCancelarActionPerformed(evt);
            }
        });

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(layout.createSequentialGroup()
                        .add(299, 299, 299)
                        .add(botonCancelar, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 83, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                        .add(botonOK, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 78, Short.MAX_VALUE))
                    .add(layout.createSequentialGroup()
                        .addContainerGap()
                        .add(jScrollPane1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 460, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .add(20, 20, 20)
                .add(jScrollPane1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 148, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(botonOK)
                    .add(botonCancelar))
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void botonCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonCancelarActionPerformed
        this.setVisible(false);
    }//GEN-LAST:event_botonCancelarActionPerformed

    private void botonOKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonOKActionPerformed
        int filaSeleccionada = tablaBusqueda.getSelectedRow();
        String llave = tablaBusqueda.getModel().getValueAt(filaSeleccionada, 0).toString();
        madre.llenarFormularioCampos(llave);
    }//GEN-LAST:event_botonOKActionPerformed

    private void tablaBusquedaMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaBusquedaMouseEntered

}//GEN-LAST:event_tablaBusquedaMouseEntered

    public void llenarTabla(JTextField campo) {
        ControladorBD miPrueba = new ControladorBD();
       // System.out.print("prueba de base de datos");

 //       int contador;
   //     int contadorFila;
        DefaultTableModel modelo = new DefaultTableModel();
        modelo = (DefaultTableModel) tablaBusqueda.getModel();

        //jTable1 = new JTable(modelo);
        Object[] fila = new Object[4];
    //    contador = 0;
      //  contadorFila=0;

        try {
            ResultSet resultado = miPrueba.getResultSet("select * from TIPOCAMPO where nombre like '%"+campo.getText()+"%' or descripcion like '%"+campo.getText()+"%';");
            while (resultado.next()) {
                    for(int i=0;i<4;i++){
                        fila[i] = resultado.getObject(i+1).toString();
  //                      JOptionPane.showMessageDialog(null, fila[i]);

                    }
                    modelo.addRow(fila);

            }
            tablaBusqueda.setModel(modelo);
            
        } catch (SQLException e) {
            System.out.println("*SQL Exception: *" + e.toString());
        }
        

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new frameBusqueda().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botonCancelar;
    private javax.swing.JButton botonOK;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tablaBusqueda;
    // End of variables declaration//GEN-END:variables
}
