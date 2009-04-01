/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * frameConexiones.java
 *
 * Created on 21/03/2009, 04:48:00 PM
 */

package gestiontipocampo;
import java.awt.*;
import java.awt.event.*;
import javax.swing.JFrame;


/**
 *
 * @author Administrator
 */
public class frameConexiones extends javax.swing.JFrame {

    /** Creates new form frameConexiones */
    public frameConexiones() {
        initComponents();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblConexion1 = new javax.swing.JLabel();
        lblConexion2 = new javax.swing.JLabel();
        jRadioButton1 = new javax.swing.JRadioButton();
        jRadioButton2 = new javax.swing.JRadioButton();
        btnEstablecerConexion = new javax.swing.JButton();
        btnRefrescar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(gestiontipocampo.GestionTipoCampoApp.class).getContext().getResourceMap(frameConexiones.class);
        setTitle(resourceMap.getString("Form.title")); // NOI18N
        setName("Form"); // NOI18N
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                formComponentShown(evt);
            }
        });

        lblConexion1.setText(resourceMap.getString("lblConexion1.text")); // NOI18N
        lblConexion1.setName("lblConexion1"); // NOI18N

        lblConexion2.setText(resourceMap.getString("lblConexion2.text")); // NOI18N
        lblConexion2.setName("lblConexion2"); // NOI18N

        jRadioButton1.setText(resourceMap.getString("opt1.text")); // NOI18N
        jRadioButton1.setEnabled(false);
        jRadioButton1.setName("opt1"); // NOI18N
        jRadioButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jRadioButton1MouseClicked(evt);
            }
        });
        jRadioButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton1ActionPerformed(evt);
            }
        });

        jRadioButton2.setText(resourceMap.getString("opt2.text")); // NOI18N
        jRadioButton2.setEnabled(false);
        jRadioButton2.setName("opt2"); // NOI18N
        jRadioButton2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jRadioButton2MouseClicked(evt);
            }
        });

        btnEstablecerConexion.setText(resourceMap.getString("btnEstablecerConexion.text")); // NOI18N
        btnEstablecerConexion.setName("btnEstablecerConexion"); // NOI18N
        btnEstablecerConexion.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnEstablecerConexionMouseClicked(evt);
            }
        });

        btnRefrescar.setText(resourceMap.getString("btnRefrescar.text")); // NOI18N
        btnRefrescar.setName("btnRefrescar"); // NOI18N
        btnRefrescar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnRefrescarMouseClicked(evt);
            }
        });

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText(resourceMap.getString("jLabel1.text")); // NOI18N
        jLabel1.setName("jLabel1"); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(146, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblConexion1)
                            .addComponent(jRadioButton1))
                        .addGap(82, 82, 82)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblConexion2)
                            .addComponent(jRadioButton2)))
                    .addComponent(btnRefrescar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnEstablecerConexion)
                .addContainerGap())
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 562, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblConexion2)
                    .addComponent(lblConexion1))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jRadioButton2)
                    .addComponent(jRadioButton1))
                .addGap(43, 43, 43)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnEstablecerConexion)
                    .addComponent(btnRefrescar))
                .addContainerGap())
        );

        jRadioButton1.getAccessibleContext().setAccessibleName(resourceMap.getString("opt1.AccessibleContext.accessibleName")); // NOI18N
        jRadioButton2.getAccessibleContext().setAccessibleName(resourceMap.getString("opt2.AccessibleContext.accessibleName")); // NOI18N

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentShown
        probarConexiones();
    }//GEN-LAST:event_formComponentShown
    private void probarConexiones(){
        
        btnEstablecerConexion.setEnabled(false);
        ControladorBD probadorConexiones = new ControladorBD();
      /*  if(1==probadorConexiones.probarConexion(1)){

            jRadioButton1.setEnabled(true);
            jRadioButton1.setText("Disponible");

   //         jRadioButton1.setBackground(Color.green);
        }
        else{

            jRadioButton1.setText("No disponible");
            jRadioButton1.setSelected(false);
            jRadioButton1.setEnabled(false);
        }*/

        if(1==probadorConexiones.probarConexion(2)){
            jRadioButton2.setText("Disponible");
            jRadioButton2.setEnabled(true);
        }
        else{
            jRadioButton2.setText("No disponible");
            jRadioButton2.setSelected(false);
            jRadioButton2.setEnabled(false);
        }

        jLabel1.setText("Por favor elija la Base de Datos a la que sea conectarse.");

    }
    private void jRadioButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jRadioButton1MouseClicked
        // TODO add your handling code here:
      if(true==jRadioButton1.isSelected()){
        btnEstablecerConexion.setEnabled(true);
        jRadioButton2.setSelected(false);
      }
      if(false==jRadioButton1.isSelected() && false==jRadioButton2.isSelected()){
              btnEstablecerConexion.setEnabled(false);
      }
    /*  else{
          btnEstablecerConexion.setEnabled(false);
      }*/
    }//GEN-LAST:event_jRadioButton1MouseClicked

    private void jRadioButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton1ActionPerformed
        // TODO add your handling code here:
  
    }//GEN-LAST:event_jRadioButton1ActionPerformed

    private void btnEstablecerConexionMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEstablecerConexionMouseClicked
            // TODO add your handling code here:
        if(jRadioButton1.isSelected()){
            ControladorBD.conexionSeleccionada=1;
        }
        else{
            ControladorBD.conexionSeleccionada=2;
        }
        this.dispose();
    }//GEN-LAST:event_btnEstablecerConexionMouseClicked

    private void jRadioButton2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jRadioButton2MouseClicked
     if(true==jRadioButton2.isSelected()){
        btnEstablecerConexion.setEnabled(true);
        jRadioButton1.setSelected(false);
      }
      if(false==jRadioButton1.isSelected() && false==jRadioButton2.isSelected()){
              btnEstablecerConexion.setEnabled(false);
      }
    }//GEN-LAST:event_jRadioButton2MouseClicked

    private void btnRefrescarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnRefrescarMouseClicked

        btnRefrescar.setEnabled(false);
        btnEstablecerConexion.setEnabled(false);
        jRadioButton1.setText("Revisando...");
        jRadioButton1.setSelected(false);
        jRadioButton1.setEnabled(false);
        jRadioButton2.setText("Revisando...");
        jRadioButton2.setSelected(false);
        jRadioButton2.setEnabled(false);
        jLabel1.setVisible(true);
        jLabel1.setText("Por favor espere un momento.\nSe está revisando la conexión a las Bases de Datos.");
        probarConexiones();
        btnRefrescar.setEnabled(true);


}//GEN-LAST:event_btnRefrescarMouseClicked

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frameConexiones().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEstablecerConexion;
    private javax.swing.JButton btnRefrescar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JLabel lblConexion1;
    private javax.swing.JLabel lblConexion2;
    // End of variables declaration//GEN-END:variables

}
