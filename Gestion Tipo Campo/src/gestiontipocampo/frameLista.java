/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * frameLista.java
 *
 * Created on 17/04/2009, 08:57:58 PM luiscarlosch@gmail.com
 */
package gestiontipocampo;
import javax.swing.*;
import java.*;

/**
 *
 * @author Administrator
 */
public class frameLista extends javax.swing.JFrame {

    /** Creates new form frameLista */
    public Lista miLista;
    //   public String elementoSeleccionado;
    //  private int IDTipoCampo;
    public boolean modificando;

    public frameLista() {
        initComponents();
        miLista = new Lista();
    }

    public frameLista(int IDLista) {
        initComponents();
        miLista = new Lista();
        campoEntrada.setText("");
        campoEntrada.requestFocus();
        //this.IDTipoCampo=IDLista;
        miLista.correlativo = IDLista + "";
        miLista.setLista();
        this.actualizarLista();

        etiquetaNombreLista.setText(miLista.getNombre());
        etiquetaDescripcionLista.setText(miLista.getDescripcion());
        nombreMiembroPorDefecto.setText(miLista.nombreMiembroPorDefecto);
    }

    public void actualizarLista() {
        lista.setListData(miLista.setAndGetMiembrosVectorActualizados());
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
        lista = new javax.swing.JList();
        LabelPanel = new javax.swing.JPanel();
        etiquetaNombreLista = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        etiquetaDescripcionLista = new javax.swing.JLabel();
        nombreMiembroPorDefecto = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        ButtonPanel = new javax.swing.JPanel();
        botonAgregar = new javax.swing.JButton();
        botonBorrar = new javax.swing.JButton();
        botonPorDefecto = new javax.swing.JButton();
        botonSalir = new javax.swing.JButton();
        campoEntrada = new javax.swing.JTextField();
        SidePanel = new javax.swing.JPanel();
        botonTop = new javax.swing.JButton();
        botonUp = new javax.swing.JButton();
        botonDown = new javax.swing.JButton();
        botonBottom = new javax.swing.JButton();
        panelBotonPersonalizado = new javax.swing.JPanel();
        jToggleButton1 = new javax.swing.JToggleButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setName("Form"); // NOI18N
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                formMouseClicked(evt);
            }
        });
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                formComponentShown(evt);
            }
        });

        jScrollPane1.setName("jScrollPane1"); // NOI18N

        lista.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "aasdfasf", "asdfasf", "asdfasdf", "asfasdfsadf", "asdfsfdsadf", " " };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        lista.setName("lista"); // NOI18N
        lista.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                listaMouseClicked(evt);
            }
        });
        lista.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                listaValueChanged(evt);
            }
        });
        jScrollPane1.setViewportView(lista);

        LabelPanel.setName("LabelPanel"); // NOI18N

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(gestiontipocampo.GestionTipoCampoApp.class).getContext().getResourceMap(frameLista.class);
        etiquetaNombreLista.setText(resourceMap.getString("etiquetaNombreLista.text")); // NOI18N
        etiquetaNombreLista.setName("etiquetaNombreLista"); // NOI18N

        jLabel1.setFont(resourceMap.getFont("jLabel1.font")); // NOI18N
        jLabel1.setText(resourceMap.getString("jLabel1.text")); // NOI18N
        jLabel1.setName("jLabel1"); // NOI18N

        jLabel2.setFont(resourceMap.getFont("jLabel2.font")); // NOI18N
        jLabel2.setText(resourceMap.getString("jLabel2.text")); // NOI18N
        jLabel2.setName("jLabel2"); // NOI18N

        etiquetaDescripcionLista.setText(resourceMap.getString("etiquetaDescripcionLista.text")); // NOI18N
        etiquetaDescripcionLista.setName("etiquetaDescripcionLista"); // NOI18N

        nombreMiembroPorDefecto.setText(resourceMap.getString("nombreMiembroPorDefecto.text")); // NOI18N
        nombreMiembroPorDefecto.setName("nombreMiembroPorDefecto"); // NOI18N

        jLabel3.setFont(resourceMap.getFont("jLabel3.font")); // NOI18N
        jLabel3.setText(resourceMap.getString("jLabel3.text")); // NOI18N
        jLabel3.setName("jLabel3"); // NOI18N

        javax.swing.GroupLayout LabelPanelLayout = new javax.swing.GroupLayout(LabelPanel);
        LabelPanel.setLayout(LabelPanelLayout);
        LabelPanelLayout.setHorizontalGroup(
            LabelPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(LabelPanelLayout.createSequentialGroup()
                .addGroup(LabelPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(etiquetaNombreLista)
                    .addComponent(jLabel1))
                .addGap(32, 32, 32)
                .addGroup(LabelPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(etiquetaDescripcionLista))
                .addGap(47, 47, 47)
                .addGroup(LabelPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(nombreMiembroPorDefecto)
                    .addComponent(jLabel3))
                .addContainerGap(182, Short.MAX_VALUE))
        );
        LabelPanelLayout.setVerticalGroup(
            LabelPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(LabelPanelLayout.createSequentialGroup()
                .addGroup(LabelPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(LabelPanelLayout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(etiquetaDescripcionLista))
                    .addGroup(LabelPanelLayout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(5, 5, 5)
                        .addComponent(etiquetaNombreLista))
                    .addGroup(LabelPanelLayout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(nombreMiembroPorDefecto)))
                .addContainerGap(15, Short.MAX_VALUE))
        );

        ButtonPanel.setName("ButtonPanel"); // NOI18N

        botonAgregar.setText(resourceMap.getString("botonAgregar.text")); // NOI18N
        botonAgregar.setEnabled(false);
        botonAgregar.setName("botonAgregar"); // NOI18N
        botonAgregar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                botonAgregarMouseClicked(evt);
            }
        });
        botonAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonAgregarActionPerformed(evt);
            }
        });

        botonBorrar.setText(resourceMap.getString("botonBorrar.text")); // NOI18N
        botonBorrar.setEnabled(false);
        botonBorrar.setName("botonBorrar"); // NOI18N
        botonBorrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonBorrarActionPerformed(evt);
            }
        });

        botonPorDefecto.setText(resourceMap.getString("botonPorDefecto.text")); // NOI18N
        botonPorDefecto.setEnabled(false);
        botonPorDefecto.setName("botonPorDefecto"); // NOI18N
        botonPorDefecto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                botonPorDefectoMouseClicked(evt);
            }
        });

        botonSalir.setText(resourceMap.getString("botonSalir.text")); // NOI18N
        botonSalir.setName("botonSalir"); // NOI18N
        botonSalir.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                botonSalirMouseClicked(evt);
            }
        });

        campoEntrada.setText(resourceMap.getString("campoEntrada.text")); // NOI18N
        campoEntrada.setName("campoEntrada"); // NOI18N
        campoEntrada.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                campoEntradaActionPerformed(evt);
            }
        });
        campoEntrada.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
            }
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
                campoEntradaInputMethodTextChanged(evt);
            }
        });
        campoEntrada.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                campoEntradaKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout ButtonPanelLayout = new javax.swing.GroupLayout(ButtonPanel);
        ButtonPanel.setLayout(ButtonPanelLayout);
        ButtonPanelLayout.setHorizontalGroup(
            ButtonPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ButtonPanelLayout.createSequentialGroup()
                .addGroup(ButtonPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(campoEntrada, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, ButtonPanelLayout.createSequentialGroup()
                        .addComponent(botonAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(botonBorrar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(botonPorDefecto, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(botonSalir)))
                .addGap(154, 154, 154))
        );
        ButtonPanelLayout.setVerticalGroup(
            ButtonPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ButtonPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(campoEntrada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
                .addGroup(ButtonPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(botonAgregar)
                    .addComponent(botonBorrar)
                    .addComponent(botonPorDefecto)
                    .addComponent(botonSalir))
                .addContainerGap())
        );

        botonAgregar.getAccessibleContext().setAccessibleName(resourceMap.getString("jButton1.AccessibleContext.accessibleName")); // NOI18N

        SidePanel.setName("SidePanel"); // NOI18N

        botonTop.setText(resourceMap.getString("botonTop.text")); // NOI18N
        botonTop.setName("botonTop"); // NOI18N

        botonUp.setText(resourceMap.getString("botonUp.text")); // NOI18N
        botonUp.setName("botonUp"); // NOI18N

        botonDown.setText(resourceMap.getString("botonDown.text")); // NOI18N
        botonDown.setName("botonDown"); // NOI18N

        botonBottom.setText(resourceMap.getString("botonBottom.text")); // NOI18N
        botonBottom.setName("botonBottom"); // NOI18N

        javax.swing.GroupLayout SidePanelLayout = new javax.swing.GroupLayout(SidePanel);
        SidePanel.setLayout(SidePanelLayout);
        SidePanelLayout.setHorizontalGroup(
            SidePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(SidePanelLayout.createSequentialGroup()
                .addGroup(SidePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(botonBottom, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(botonUp, javax.swing.GroupLayout.DEFAULT_SIZE, 67, Short.MAX_VALUE)
                    .addComponent(botonDown, javax.swing.GroupLayout.DEFAULT_SIZE, 67, Short.MAX_VALUE)
                    .addComponent(botonTop, javax.swing.GroupLayout.DEFAULT_SIZE, 67, Short.MAX_VALUE))
                .addContainerGap())
        );
        SidePanelLayout.setVerticalGroup(
            SidePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(SidePanelLayout.createSequentialGroup()
                .addComponent(botonTop)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(botonUp)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(botonDown)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(botonBottom))
        );

        panelBotonPersonalizado.setName("panelBotonPersonalizado"); // NOI18N

        jToggleButton1.setText(resourceMap.getString("jToggleButton1.text")); // NOI18N
        jToggleButton1.setName("jToggleButton1"); // NOI18N

        javax.swing.GroupLayout panelBotonPersonalizadoLayout = new javax.swing.GroupLayout(panelBotonPersonalizado);
        panelBotonPersonalizado.setLayout(panelBotonPersonalizadoLayout);
        panelBotonPersonalizadoLayout.setHorizontalGroup(
            panelBotonPersonalizadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBotonPersonalizadoLayout.createSequentialGroup()
                .addComponent(jToggleButton1)
                .addContainerGap(20, Short.MAX_VALUE))
        );
        panelBotonPersonalizadoLayout.setVerticalGroup(
            panelBotonPersonalizadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jToggleButton1)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(LabelPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(SidePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(panelBotonPersonalizado, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(ButtonPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 457, Short.MAX_VALUE)
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(LabelPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(9, 9, 9)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(SidePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(panelBotonPersonalizado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(14, 14, 14))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(ButtonPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void botonAgregarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botonAgregarMouseClicked
}//GEN-LAST:event_botonAgregarMouseClicked

    private void botonSalirMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botonSalirMouseClicked
        this.dispose();
}//GEN-LAST:event_botonSalirMouseClicked

    private void formMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_formMouseClicked

    private void formComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentShown

        //     lista.setListData(this.miLista.getModeloVector("select valor, IDLista from MIEMBROLISTA ml, LISTA l where ml.IDLista=l.correlativo and l.correlativo=31", "valor", "IDLIsta"));
    }//GEN-LAST:event_formComponentShown

    private void listaValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_listaValueChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_listaValueChanged

    private void botonBorrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonBorrarActionPerformed

        if (((((MiDato) lista.getSelectedValue()).ID) + "").equals(miLista.IDMiembroPorDefecto)) {
            JOptionPane.showMessageDialog(null, "No se puede eliminar el elemento por defecto.");
        } else {

            String[] opciones = {"Si", "No"};
            int respuesta = JOptionPane.showOptionDialog(null, "¿Está seguro de que desea borrar esta entrada?", "Confirmación", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE, null, opciones, "No");

            switch (respuesta) {
                case 0:
                    //miLista.borrarMiembro(((MiDato)lista.getSelectedValue()).ID+"");
                    miLista.borrarMiembro(lista.getSelectedValue());
                    // this.actualizarLista();
                    lista.setListData(miLista.getModeloMiembrosVector());
                    setConfiguracionBase();
                    break;
                case 1:
                    /*No quiso borrar*/
                    break;
            }
        }

    }//GEN-LAST:event_botonBorrarActionPerformed

    private void listaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_listaMouseClicked
        // TODO add your handling code here:

        // botonAgregar.setEnabled(true);

        if (evt.getClickCount() == 2) {
            modificando = true;
            botonBorrar.setEnabled(false);
            botonPorDefecto.setEnabled(false);
            if (lista.getSelectedValue() != null) {
                campoEntrada.setText(lista.getSelectedValue().toString());
                botonAgregar.setEnabled(true);
                botonAgregar.setText("Modificar");
            } else {
                campoEntrada.setText("");
            }
            campoEntrada.requestFocus();
        } else {
            botonAgregar.setText("Agregar");
            modificando = false;
            botonAgregar.setEnabled(false);
            botonBorrar.setEnabled(true);
            botonPorDefecto.setEnabled(true);
            campoEntrada.setText("");
        }

        this.campoEntrada.setText(this.campoEntrada.getText().trim());

    //   System.out.print(evt.getClickCount()+".");
    }//GEN-LAST:event_listaMouseClicked

    private void botonPorDefectoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botonPorDefectoMouseClicked
        // TODO add your handling code here:

        String[] opciones = {"Si", "No"};
        int respuesta = JOptionPane.showOptionDialog(null, "¿Está seguro de que desea cambiar el valor por defecto?", "Confirmación", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE, null, opciones, "No");

        switch (respuesta) {
            case 0:
                miLista.IDMiembroPorDefecto = (((MiDato) lista.getSelectedValue()).ID) + "";
                // miLista.IDMiembroPorDefecto=100+"";
                miLista.upDateIDMiembroPorDefecto((((MiDato) lista.getSelectedValue()).ID) + "");


                lista.setListData(miLista.getModeloMiembrosVector());
                miLista.setLista();
                nombreMiembroPorDefecto.setText(miLista.nombreMiembroPorDefecto);
                this.setConfiguracionBase();
                break;
            case 1:
                /*No quiso borrar*/
                break;
        }






    }//GEN-LAST:event_botonPorDefectoMouseClicked

    private void campoEntradaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_campoEntradaActionPerformed
    }//GEN-LAST:event_campoEntradaActionPerformed

    private void campoEntradaInputMethodTextChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_campoEntradaInputMethodTextChanged
    }//GEN-LAST:event_campoEntradaInputMethodTextChanged

    private void botonAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonAgregarActionPerformed
        // TODO add your handling code here:


        if (miLista.getMiembroListaSet().contains(new MiDato(campoEntrada.getText(), 0))) {
            JOptionPane.showMessageDialog(null, "Ya existe un elemento con ese nombre.");
        } else {
            if (modificando) {
                miLista.upDateValorMiembro(lista.getSelectedValue(), campoEntrada.getText());
                lista.setListData(miLista.getModeloMiembrosVector());
                this.nombreMiembroPorDefecto.setText(miLista.nombreMiembroPorDefecto);
                setConfiguracionBase();
                modificando = false;
            } else {
                miLista.agregarMiembro(campoEntrada.getText());
                //elementoSeleccionado=campoEntrada.getText();
                this.actualizarLista();
                this.setConfiguracionBase();

            }


            //  campoEntrada.requestFocus();        // TODO add your handling code here:
            botonAgregar.setText("Agregar");
        }
        campoEntrada.requestFocus();
    }//GEN-LAST:event_botonAgregarActionPerformed
    public void setConfiguracionBase() {
        campoEntrada.setText("");
        campoEntrada.requestFocus();
        botonAgregar.setEnabled(false);
        botonBorrar.setEnabled(false);
        botonPorDefecto.setEnabled(false);
    }
    private void campoEntradaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_campoEntradaKeyTyped
        if (!this.modificando) {
            lista.clearSelection();
        }
        botonAgregar.setEnabled(true);        // TODO add your handling code here:
        if (campoEntrada.getText().trim().isEmpty()) {
            botonAgregar.setEnabled(false);
        }
    }//GEN-LAST:event_campoEntradaKeyTyped

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new frameLista().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel ButtonPanel;
    private javax.swing.JPanel LabelPanel;
    private javax.swing.JPanel SidePanel;
    private javax.swing.JButton botonAgregar;
    private javax.swing.JButton botonBorrar;
    private javax.swing.JButton botonBottom;
    private javax.swing.JButton botonDown;
    private javax.swing.JButton botonPorDefecto;
    private javax.swing.JButton botonSalir;
    private javax.swing.JButton botonTop;
    private javax.swing.JButton botonUp;
    private javax.swing.JTextField campoEntrada;
    private javax.swing.JLabel etiquetaDescripcionLista;
    private javax.swing.JLabel etiquetaNombreLista;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JToggleButton jToggleButton1;
    private javax.swing.JList lista;
    private javax.swing.JLabel nombreMiembroPorDefecto;
    private javax.swing.JPanel panelBotonPersonalizado;
    // End of variables declaration//GEN-END:variables
}
