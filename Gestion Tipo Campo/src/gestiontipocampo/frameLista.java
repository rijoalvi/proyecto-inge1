/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * frameLista.java
 *
 * Created on 17/04/2009, 08:57:58 PM
 */

package gestiontipocampo;
import java.util.Vector;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.*;
/**
 *
 * @author Administrator
 */
public class frameLista extends javax.swing.JFrame {

    /** Creates new form frameLista */
    public Lista miLista = new Lista();
    private int IDTipoCampo;
    public frameLista() {
        initComponents();


       // Modelo  miModelo = new Modelo();
        //lista.setListData(miModelo.getModeloEnVector("select * from MIEMBROLISTA ml, LISTA l where ml.IDLista=l.correlativo and l.correlativo=31","valor","IDLIsta"));





    //comboCategorias.setBounds(120, 90, 90, 20);//con esto creo que me quito la maldicion del combo, porque netbeans no se va a atrever a modificar este codigo
  //  Modelo  miModelo = new Modelo();
    //lista.setModel(new javax.swing.DefaultComboBoxModel(miModelo.getModeloDeCombo("select nombre, ID from TIPOCATEGORIA;")));
    }
    public frameLista(int IDLista) {
        initComponents();


       // Modelo  miModelo = new Modelo();
        //lista.setListData(miModelo.getModeloEnVector("select * from MIEMBROLISTA ml, LISTA l where ml.IDLista=l.correlativo and l.correlativo=31","valor","IDLIsta"));

        this.IDTipoCampo=IDLista;

        this.actualizarLista();


        miLista.setLista();
        etiqueltaNombreLista.setText(miLista.getNombre());
        etiquetalDescripcionLista.setText(miLista.getDescripcion());

    //comboCategorias.setBounds(120, 90, 90, 20);//con esto creo que me quito la maldicion del combo, porque netbeans no se va a atrever a modificar este codigo
  //  Modelo  miModelo = new Modelo();
    //lista.setModel(new javax.swing.DefaultComboBoxModel(miModelo.getModeloDeCombo("select nombre, ID from TIPOCATEGORIA;")));
    }
    public void actualizarLista(){
       // miLista.setMiembrosLista(this.m);
        miLista.setMiembrosLista(this.miLista.getModeloVector("select valor, IDLista from MIEMBROLISTA ml, LISTA l where ml.IDLista=l.correlativo and l.correlativo="+this.IDTipoCampo+"", "valor", "IDLIsta"));

                SortedSet miembrosLista=new TreeSet();
        Vector miembrosListaVecto= new Vector();
        miembrosLista=miLista.getMiembrosLista();
        Iterator it = miembrosLista.iterator();
        while (it.hasNext()) {
            // Get element
            miembrosListaVecto.add( it.next());
        }
        lista.setListData(miembrosListaVecto);

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
        botonAgregar = new javax.swing.JButton();
        campoEntrada = new javax.swing.JTextField();
        botonBorrar = new javax.swing.JButton();
        botonPorDefecto = new javax.swing.JButton();
        botonSalir = new javax.swing.JButton();
        etiqueltaNombreLista = new javax.swing.JLabel();
        etiquetalDescripcionLista = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
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

        lista.setName("lista"); // NOI18N
        lista.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                listaValueChanged(evt);
            }
        });
        jScrollPane1.setViewportView(lista);

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(gestiontipocampo.GestionTipoCampoApp.class).getContext().getResourceMap(frameLista.class);
        botonAgregar.setText(resourceMap.getString("botonAgregar.text")); // NOI18N
        botonAgregar.setName("botonAgregar"); // NOI18N
        botonAgregar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                botonAgregarMouseClicked(evt);
            }
        });

        campoEntrada.setText(resourceMap.getString("campoEntrada.text")); // NOI18N
        campoEntrada.setName("campoEntrada"); // NOI18N

        botonBorrar.setText(resourceMap.getString("botonBorrar.text")); // NOI18N
        botonBorrar.setName("botonBorrar"); // NOI18N

        botonPorDefecto.setText(resourceMap.getString("botonPorDefecto.text")); // NOI18N
        botonPorDefecto.setName("botonPorDefecto"); // NOI18N

        botonSalir.setText(resourceMap.getString("botonSalir.text")); // NOI18N
        botonSalir.setName("botonSalir"); // NOI18N
        botonSalir.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                botonSalirMouseClicked(evt);
            }
        });

        etiqueltaNombreLista.setText(resourceMap.getString("etiqueltaNombreLista.text")); // NOI18N
        etiqueltaNombreLista.setName("etiqueltaNombreLista"); // NOI18N

        etiquetalDescripcionLista.setText(resourceMap.getString("etiquetalDescripcionLista.text")); // NOI18N
        etiquetalDescripcionLista.setName("etiquetalDescripcionLista"); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(botonAgregar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(botonBorrar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(botonPorDefecto, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(botonSalir))
                    .addComponent(jScrollPane1)
                    .addComponent(campoEntrada))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(etiquetalDescripcionLista)
                    .addComponent(etiqueltaNombreLista))
                .addContainerGap(108, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(etiqueltaNombreLista)
                        .addGap(29, 29, 29)
                        .addComponent(etiquetalDescripcionLista))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(campoEntrada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(11, 11, 11)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(botonAgregar)
                            .addComponent(botonBorrar)
                            .addComponent(botonPorDefecto)
                            .addComponent(botonSalir))))
                .addContainerGap(64, Short.MAX_VALUE))
        );

        botonAgregar.getAccessibleContext().setAccessibleName(resourceMap.getString("jButton1.AccessibleContext.accessibleName")); // NOI18N

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void botonAgregarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botonAgregarMouseClicked
        // TODO add your handling code here:
        miLista.agregarMiembro(campoEntrada.getText());
        this.actualizarLista();
       // System.out.print(miLista.toString());
}//GEN-LAST:event_botonAgregarMouseClicked

    private void botonSalirMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botonSalirMouseClicked
        // TODO add your handling code here:
        this.dispose();
}//GEN-LAST:event_botonSalirMouseClicked

    private void formMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseClicked
             // TODO add your handling code here:
    }//GEN-LAST:event_formMouseClicked

    private void formComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentShown
        // TODO add your handling code here:

   //     lista.setListData(this.miLista.getModeloVector("select valor, IDLista from MIEMBROLISTA ml, LISTA l where ml.IDLista=l.correlativo and l.correlativo=31", "valor", "IDLIsta"));

    }//GEN-LAST:event_formComponentShown

    private void listaValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_listaValueChanged
        // TODO add your handling code here:
        campoEntrada.setText( lista.getSelectedValue().toString());

    }//GEN-LAST:event_listaValueChanged

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
    private javax.swing.JButton botonAgregar;
    private javax.swing.JButton botonBorrar;
    private javax.swing.JButton botonPorDefecto;
    private javax.swing.JButton botonSalir;
    private javax.swing.JTextField campoEntrada;
    private javax.swing.JLabel etiqueltaNombreLista;
    private javax.swing.JLabel etiquetalDescripcionLista;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JList lista;
    // End of variables declaration//GEN-END:variables

}
