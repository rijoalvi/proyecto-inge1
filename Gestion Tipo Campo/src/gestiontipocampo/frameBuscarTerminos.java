/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * frameBuscarTerminos.java
 *
 * Created on 01/04/2009, 12:11:45 AM
 */
package gestiontipocampo;
import java.sql.*;
import javax.swing.tree.*;
import javax.swing.JTree;

/**
 *
 * @author Ricardo
 */
public class frameBuscarTerminos extends javax.swing.JFrame {

    /** Creates new form frameBuscarTerminos */
    public frameBuscarTerminos() {
        initComponents();
        paneTree.setVisible(false);
        paneLista.setVisible(true);
    }

    frameBuscarTerminos(int i) {
        initComponents();
        if (i == 1) { //alfabeticamente
            paneTree.setVisible(false);
            paneLista.setVisible(true);
        }
    }

    //Constructor de vista por niveles
    frameBuscarTerminos(String nombreJerarquia) {
        initComponents();
        paneTree.setVisible(true);
        paneLista.setVisible(false);
        llenarTreeViewJerarquia(nombreJerarquia);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLayeredPane1 = new javax.swing.JLayeredPane();
        paneTree = new javax.swing.JLayeredPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        arbolJerarquia = new javax.swing.JTree();
        paneLista = new javax.swing.JLayeredPane();
        scrollPaneJerarquia = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        botonBuscar = new javax.swing.JButton();
        BotonAgregarTermino = new javax.swing.JButton();
        fieldBusqueda = new javax.swing.JTextField();
        labelBusqueda = new javax.swing.JLabel();
        botonCerrar = new javax.swing.JButton();
        botonListarSubarbol = new javax.swing.JButton();
        botonListarHijos = new javax.swing.JButton();
        botonExcluir = new javax.swing.JButton();
        botonModificar = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setName("Form"); // NOI18N

        jLayeredPane1.setName("jLayeredPane1"); // NOI18N

        paneTree.setName("paneTree"); // NOI18N

        jScrollPane1.setName("jScrollPane1"); // NOI18N

        javax.swing.tree.DefaultMutableTreeNode treeNode1 = new javax.swing.tree.DefaultMutableTreeNode("Raíz");
        arbolJerarquia.setModel(new javax.swing.tree.DefaultTreeModel(treeNode1));
        arbolJerarquia.setName("arbolJerarquia"); // NOI18N
        jScrollPane1.setViewportView(arbolJerarquia);

        jScrollPane1.setBounds(0, 0, 160, 400);
        paneTree.add(jScrollPane1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        paneTree.setBounds(0, 0, 310, 400);
        jLayeredPane1.add(paneTree, javax.swing.JLayeredPane.DEFAULT_LAYER);

        paneLista.setName("paneLista"); // NOI18N

        scrollPaneJerarquia.setName("scrollPaneJerarquia"); // NOI18N

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Nombre", "Fecha Inserción", "Descripción"
            }
        ));
        jTable1.setName("jTable1"); // NOI18N
        scrollPaneJerarquia.setViewportView(jTable1);
        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(gestiontipocampo.GestionTipoCampoApp.class).getContext().getResourceMap(frameBuscarTerminos.class);
        jTable1.getColumnModel().getColumn(0).setHeaderValue(resourceMap.getString("jTable1.columnModel.title0")); // NOI18N
        jTable1.getColumnModel().getColumn(1).setHeaderValue(resourceMap.getString("jTable1.columnModel.title1")); // NOI18N
        jTable1.getColumnModel().getColumn(2).setHeaderValue(resourceMap.getString("jTable1.columnModel.title2")); // NOI18N

        scrollPaneJerarquia.setBounds(10, 10, 290, 380);
        paneLista.add(scrollPaneJerarquia, javax.swing.JLayeredPane.DEFAULT_LAYER);

        paneLista.setBounds(0, 0, 310, 400);
        jLayeredPane1.add(paneLista, javax.swing.JLayeredPane.DEFAULT_LAYER);

        botonBuscar.setText(resourceMap.getString("botonBuscar.text")); // NOI18N
        botonBuscar.setName("botonBuscar"); // NOI18N
        botonBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonBuscarActionPerformed(evt);
            }
        });
        botonBuscar.setBounds(320, 320, 100, 23);
        jLayeredPane1.add(botonBuscar, javax.swing.JLayeredPane.DEFAULT_LAYER);

        BotonAgregarTermino.setText(resourceMap.getString("BotonAgregarTermino.text")); // NOI18N
        BotonAgregarTermino.setName("BotonAgregarTermino"); // NOI18N
        BotonAgregarTermino.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotonAgregarTerminoActionPerformed(evt);
            }
        });
        BotonAgregarTermino.setBounds(310, 40, 113, 23);
        jLayeredPane1.add(BotonAgregarTermino, javax.swing.JLayeredPane.DEFAULT_LAYER);

        fieldBusqueda.setText(resourceMap.getString("fieldBusqueda.text")); // NOI18N
        fieldBusqueda.setName("fieldBusqueda"); // NOI18N
        fieldBusqueda.setBounds(320, 300, 100, 20);
        jLayeredPane1.add(fieldBusqueda, javax.swing.JLayeredPane.DEFAULT_LAYER);

        labelBusqueda.setText(resourceMap.getString("labelBusqueda.text")); // NOI18N
        labelBusqueda.setName("labelBusqueda"); // NOI18N
        labelBusqueda.setBounds(320, 280, 100, 14);
        jLayeredPane1.add(labelBusqueda, javax.swing.JLayeredPane.DEFAULT_LAYER);

        botonCerrar.setText(resourceMap.getString("botonCerrar.text")); // NOI18N
        botonCerrar.setName("botonCerrar"); // NOI18N
        botonCerrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonCerrarActionPerformed(evt);
            }
        });
        botonCerrar.setBounds(320, 360, 100, 23);
        jLayeredPane1.add(botonCerrar, javax.swing.JLayeredPane.DEFAULT_LAYER);

        botonListarSubarbol.setText(resourceMap.getString("botonListarSubarbol.text")); // NOI18N
        botonListarSubarbol.setName("botonListarSubarbol"); // NOI18N
        botonListarSubarbol.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonListarSubarbolActionPerformed(evt);
            }
        });
        botonListarSubarbol.setBounds(320, 70, 103, 23);
        jLayeredPane1.add(botonListarSubarbol, javax.swing.JLayeredPane.DEFAULT_LAYER);

        botonListarHijos.setText(resourceMap.getString("botonListarHijos.text")); // NOI18N
        botonListarHijos.setName("botonListarHijos"); // NOI18N
        botonListarHijos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonListarHijosActionPerformed(evt);
            }
        });
        botonListarHijos.setBounds(320, 100, 100, 23);
        jLayeredPane1.add(botonListarHijos, javax.swing.JLayeredPane.DEFAULT_LAYER);

        botonExcluir.setText(resourceMap.getString("botonExcluir.text")); // NOI18N
        botonExcluir.setName("botonExcluir"); // NOI18N
        botonExcluir.setBounds(320, 130, 100, 23);
        jLayeredPane1.add(botonExcluir, javax.swing.JLayeredPane.DEFAULT_LAYER);

        botonModificar.setText(resourceMap.getString("botonModificar.text")); // NOI18N
        botonModificar.setName("botonModificar"); // NOI18N
        botonModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonModificarActionPerformed(evt);
            }
        });
        botonModificar.setBounds(320, 10, 100, 23);
        jLayeredPane1.add(botonModificar, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jButton2.setText(resourceMap.getString("jButton2.text")); // NOI18N
        jButton2.setName("jButton2"); // NOI18N
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jButton2.setBounds(310, 160, 111, 23);
        jLayeredPane1.add(jButton2, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLayeredPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 428, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLayeredPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 401, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void botonModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonModificarActionPerformed
        frameTermino termino;
        termino = new frameTermino();
        termino.setVisible(true);
}//GEN-LAST:event_botonModificarActionPerformed

    private void botonListarHijosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonListarHijosActionPerformed
        paneTree.setVisible(false);
        paneLista.setVisible(true);
}//GEN-LAST:event_botonListarHijosActionPerformed

    private void botonListarSubarbolActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonListarSubarbolActionPerformed
        paneTree.setVisible(false);
        paneLista.setVisible(true);
}//GEN-LAST:event_botonListarSubarbolActionPerformed

    private void botonCerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonCerrarActionPerformed
        this.setVisible(false);
}//GEN-LAST:event_botonCerrarActionPerformed

    private void BotonAgregarTerminoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotonAgregarTerminoActionPerformed
        frameTermino fram;
        fram = new frameTermino();
        fram.setVisible(true);
}//GEN-LAST:event_BotonAgregarTerminoActionPerformed

    private void botonBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonBuscarActionPerformed
        frameBusqueda ventanaBusqueda = new frameBusqueda();
        ventanaBusqueda.setVisible(true);        // TODO add your handling code here:
}//GEN-LAST:event_botonBuscarActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
}//GEN-LAST:event_jButton2ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new frameBuscarTerminos().setVisible(true);
            }
        });
    }


    public void llenarTreeViewJerarquia(String nombreJerarquia){
        //Se llena el arbol
        //Llena los valores del Tree View
        String valores;
        String [] valTrim;
        valores = buscarDatosEnBD(nombreJerarquia);
        valTrim = valores.split(";");
        int numHijos;
        String IDsHijos;
        String trimIDsHijos [];
        DefaultMutableTreeNode nodoTemp;
        DefaultMutableTreeNode raizArbol = new DefaultMutableTreeNode(nombreJerarquia);
        if(Integer.parseInt(valTrim[2]) > 1){ //Si tiene mas de un nivel la jerarquia
            numHijos = numDeHijos(Integer.parseInt(valTrim[1])); //cant d hijos de la raiz
            IDsHijos = buscarIDHijos( Integer.parseInt(valTrim[1]), numHijos);
            trimIDsHijos = IDsHijos.split(";");
                for(int j = 0; j < numHijos; ++j){ //Mientras tenga hijos la raiz
                   nodoTemp = llenarSubArbol( Integer.parseInt(trimIDsHijos[j]));
                   raizArbol.add(nodoTemp);
                }
//              raizArbol.add(nodoTipoCampo);
        }
        JTree arbolnuevo = new JTree(raizArbol);
        arbolJerarquia.setModel(arbolnuevo.getModel());
    }
    
    public DefaultMutableTreeNode llenarSubArbol(int ID){
        int numHijos;
        String IDsHijos;
        String trimIDsHijos [];
        DefaultMutableTreeNode nodoActual;
        String nombre = buscarNombreNodo( ID );
        DefaultMutableTreeNode nodoTemp;
        nodoActual = new DefaultMutableTreeNode(nombre);
        numHijos = numDeHijos( ID ); //cant d hijos del nodo
        IDsHijos = buscarIDHijos( ID, numHijos ); //IDs hijos
        trimIDsHijos = IDsHijos.split(";");
        for(int j = 0; j < numHijos; ++j){ //Mientras tenga hijos el nodo
            //nodoTemp = llenarSubArbol(trimIDsHijos[k]); //llena el subarbol
           nodoTemp = llenarSubArbol( Integer.parseInt(trimIDsHijos[j]));
           nodoActual.add(nodoTemp);                   
        }
        return nodoActual;
    }

    /**
     * Encargado de buscar los valores TIPOCAMPO segun el nombre en la base de datos
     * @param nombre: Indica el nombre del tipo de campo que va a buscar
     */
    public String buscarDatosEnBD(String nombre){
        ControladorBD buscador = new ControladorBD();
        String valores = "";
        try {
            ResultSet resultado = buscador.getResultSet("select * from TIPOCAMPO where nombre = '" + nombre + "';");
            if(resultado.next()){
                valores += resultado.getObject(1).toString() + ";"; //ID correlativo
                valores += resultado.getObject(2).toString() + ";"; //IDRaiz
                valores += resultado.getObject(5).toString() + ";"; //NumNiveles
            }
        } catch (SQLException e) {
            System.out.println("*SQL Exception: *" + e.toString());
        }
        return valores;
    }

    /**
     *
     * @param IDnodo
     * @return
     */
    public int numDeHijos(int IDnodo){
        ControladorBD buscador = new ControladorBD();
        String valores = "";
        try {
            ResultSet resultado = buscador.getResultSet("select count(*) from RELACIONHIJO where IDNodoPadre = '" + IDnodo + "';");
            if(resultado.next()){
                valores += resultado.getObject(1).toString() + ";"; //cant hijos
            }
        } catch (SQLException e) {
            System.out.println("*SQL Exception: *" + e.toString());
        }
        return Integer.parseInt(valores);
    }

    /**
     *
     * @param IDnodo
     * @return
     */
    public String buscarIDHijos(int IDnodo, int cantHijos){
        ControladorBD buscador = new ControladorBD();
        String valores = "";
        try {
            ResultSet resultado = buscador.getResultSet("select IDNodoHijo from RELACIONHIJO where IDNodoPadre = '" + IDnodo + "';");
            for(int k = 1; k <= cantHijos; ++k ){
                if(resultado.next()){
                    valores += resultado.getObject(1).toString() + ";"; //ID nodo hijo
                }
            }
        } catch (SQLException e) {
            System.out.println("*SQL Exception: *" + e.toString());
        }
        return valores;
    }

    /**
     * 
     * @param num
     * @return
     */
    public String buscarNombreNodo(int ID){
        ControladorBD buscador = new ControladorBD();
        String valores = "";
        try {
            ResultSet resultado = buscador.getResultSet("select nombre from NODO where ID = '" + ID + "';");
            if(resultado != null)
                while (resultado.next()) {
                    valores += resultado.getObject(1).toString();
                }
        } catch (SQLException e) {
            System.out.println("*SQL Exception: *" + e.toString());
        }
        return valores;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BotonAgregarTermino;
    private javax.swing.JTree arbolJerarquia;
    private javax.swing.JButton botonBuscar;
    private javax.swing.JButton botonCerrar;
    private javax.swing.JButton botonExcluir;
    private javax.swing.JButton botonListarHijos;
    private javax.swing.JButton botonListarSubarbol;
    private javax.swing.JButton botonModificar;
    private javax.swing.JTextField fieldBusqueda;
    private javax.swing.JButton jButton2;
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel labelBusqueda;
    private javax.swing.JLayeredPane paneLista;
    private javax.swing.JLayeredPane paneTree;
    private javax.swing.JScrollPane scrollPaneJerarquia;
    // End of variables declaration//GEN-END:variables
}
