/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * frameTermino.java
 *
 * Created on 01/04/2009, 01:29:25 AM
 */
package gestiontipocampo;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Ricardo
 */
public class frameTermino extends javax.swing.JFrame {

    /** Creates new form frameTermino */
    public frameTermino() {
        initComponents();
    }

    public frameTermino(int IDJerarquia, int IDNodoPadre, int estado) {
        this.IDJerarquia = IDJerarquia;
        this.IDNodoPadre = IDNodoPadre;
        this.estado = estado;
        initComponents();
        buscador = new ControladorBD();
        if (estado == 1) {
            botonAceptar.setName("Modificar");
        }
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        campoNombre = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        campoDescripcion = new javax.swing.JTextField();
        comboCategoria = new javax.swing.JComboBox();
        labelCategoria = new javax.swing.JLabel();
        botonCancelar = new javax.swing.JButton();
        botonAceptar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setName("Form"); // NOI18N

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(gestiontipocampo.GestionTipoCampoApp.class).getContext().getResourceMap(frameTermino.class);
        jLabel1.setText(resourceMap.getString("jLabel1.text")); // NOI18N
        jLabel1.setName("jLabel1"); // NOI18N

        campoNombre.setText(resourceMap.getString("campoNombre.text")); // NOI18N
        campoNombre.setName("campoNombre"); // NOI18N

        jLabel2.setText(resourceMap.getString("jLabel2.text")); // NOI18N
        jLabel2.setName("jLabel2"); // NOI18N

        campoDescripcion.setText(resourceMap.getString("campoDescripcion.text")); // NOI18N
        campoDescripcion.setName("campoDescripcion"); // NOI18N

        comboCategoria.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        comboCategoria.setName("comboCategoria"); // NOI18N

        labelCategoria.setText(resourceMap.getString("labelCategoria.text")); // NOI18N
        labelCategoria.setName("labelCategoria"); // NOI18N

        botonCancelar.setText(resourceMap.getString("botonCancelar.text")); // NOI18N
        botonCancelar.setName("botonCancelar"); // NOI18N
        botonCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonCancelarActionPerformed(evt);
            }
        });

        botonAceptar.setText(resourceMap.getString("botonAceptar.text")); // NOI18N
        botonAceptar.setName("botonAceptar"); // NOI18N
        botonAceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonAceptarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelCategoria)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel1)
                                    .addGap(42, 42, 42))
                                .addComponent(campoNombre, javax.swing.GroupLayout.DEFAULT_SIZE, 79, Short.MAX_VALUE))
                            .addComponent(comboCategoria, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(botonCancelar)
                                .addGap(18, 18, 18)
                                .addComponent(botonAceptar))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addComponent(campoDescripcion, javax.swing.GroupLayout.DEFAULT_SIZE, 249, Short.MAX_VALUE))))))
                .addContainerGap(54, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(campoNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(campoDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(labelCategoria)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(comboCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 43, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(botonCancelar)
                    .addComponent(botonAceptar))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void botonCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonCancelarActionPerformed
        this.dispose();
}//GEN-LAST:event_botonCancelarActionPerformed

    private void getIDCategoria() {
        String ID = "";
        try {
            ResultSet resultado = buscador.getResultSet("select IDTIpoCategoria from JERARQUIA where correlativo = " + IDJerarquia + ";");
            if (resultado.next()) {
                ID = resultado.getObject("IDTipoCategoria").toString();
            }
        } catch (SQLException e) {
            System.out.println("*SQL Exception: *" + e.toString());
        }
        if (ID.equalsIgnoreCase("NULL")) {
            IDCategoria = -1;
        } else {
            IDCategoria = Integer.parseInt(ID);
        }
    }

    /**
     * Fabrica de objetos para llenar el combo box con objetos que tienen nombre y id
     * @param item - nombre
     * @param ID - id
     * @return Object
     */
    private Object makeObj(final String item, final int ID) {
        return new Object() {

            @Override
            public String toString() {
                return item;
            }

            @Override
            public int hashCode() {
                return ID;
            }
        };
    }

    public void llenarDatos() {
        String nombre = "";
        String descripcion = "";
        try {
                ResultSet resultado = buscador.getResultSet("select nombre, descripcion from NODO where ID = " + IDNodoPadre + ";");
                if (resultado.next()) {
                    nombre = resultado.getObject("nombre").toString();
                    descripcion = resultado.getObject("descripcion").toString();
                }
            } catch (SQLException e) {
                System.out.println("*SQL Exception: *" + e.toString());
            }
        campoNombre.setText(nombre);
        campoDescripcion.setText(descripcion);
        llenarComboCategoria();
    }

    public void llenarComboCategoria() {
        comboCategoria.removeAllItems();
        getIDCategoria();
        if (IDCategoria < 0) {
            comboCategoria.setVisible(false);
            comboCategoria.setEditable(false);
            labelCategoria.setVisible(false);
            this.repaint();
        } else {
            try {
                ResultSet resultado = buscador.getResultSet("select ID, valor from INSTANCIACATEGORIA where IDTIpoCategoria = " + IDCategoria + ";");
                while (resultado.next()) {
                    String ID = resultado.getObject("ID").toString();
                    String valor = resultado.getObject("valor").toString();
                    comboCategoria.addItem(makeObj(valor, Integer.parseInt(ID)));
                }
            } catch (SQLException e) {
                System.out.println("*SQL Exception: *" + e.toString());
            }
        }
    }

    private void agregarNodo() {
        String nombre = campoNombre.getText();
        String descripcion = campoDescripcion.getText();
        if (comboCategoria.isVisible()) {
            int categoria = comboCategoria.getSelectedItem().hashCode();
            buscador.doUpdate("insert into NODO (IDInstanciaCategoria, nombre, descripcion, IDNodoPadre) values (" + categoria + ", '" + nombre + "', '" + descripcion + "', " + IDNodoPadre + ");");
        } else {
            buscador.doUpdate("insert into NODO (nombre, descripcion, IDNodoPadre) values ('" + nombre + "', '" + descripcion + "', " + IDNodoPadre + ");");
        }
    }

    private void modificarNodo() {
        String nombre = campoNombre.getText();
        String descripcion = campoDescripcion.getText();
        if (comboCategoria.isVisible()) {
            int categoria = comboCategoria.getSelectedItem().hashCode();
            buscador.doUpdate("update NODO set nombre = '" + nombre + "', descripcion = '" + descripcion + "', IDInstanciaCategoria = " + categoria + " where ID = " + IDNodoPadre + ";");
        } else {
            buscador.doUpdate("update NODO set nombre = '" + nombre + "', descripcion = '" + descripcion + "' where ID = " + IDNodoPadre + ";");
        }
    }

    private void botonAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonAceptarActionPerformed
        switch (estado) {
            case 0: //estado para agregar
                this.agregarNodo();
                break;
            case 1: //estado para modificar
                this.modificarNodo();
                break;
            default:
                this.agregarNodo();
                break;

        }
        this.dispose();
}//GEN-LAST:event_botonAceptarActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new frameTermino().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botonAceptar;
    private javax.swing.JButton botonCancelar;
    private javax.swing.JTextField campoDescripcion;
    private javax.swing.JTextField campoNombre;
    private javax.swing.JComboBox comboCategoria;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel labelCategoria;
    // End of variables declaration//GEN-END:variables
    private int IDNodoPadre;
    private int IDJerarquia;
    private int IDCategoria;
    private int estado;
    ControladorBD buscador;
}
