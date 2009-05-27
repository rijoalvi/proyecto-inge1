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
import javax.swing.JOptionPane;
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
        paneDatosNodo.setVisible(false);
        paneLista.setVisible(true);
        buscador = new ControladorBD();
    }

    public frameBuscarTerminos(int i) {
        initComponents();
        if (i == 1) { //alfabeticamente
            paneTree.setVisible(false);
            paneDatosNodo.setVisible(false);
            paneLista.setVisible(true);
        }
        buscador = new ControladorBD();
    }

    //Constructor de vista por niveles
    public frameBuscarTerminos(String nombreJer) {
        initComponents();
        paneTree.setVisible(true);
        paneDatosNodo.setVisible(true);
        paneLista.setVisible(false);
        buscador = new ControladorBD();
        nombreJerarquia = nombreJer;
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

        paneFondo = new javax.swing.JLayeredPane();
        paneDatosNodo = new javax.swing.JLayeredPane();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        valFechaModifNodo = new javax.swing.JTextField();
        valNombreNodo = new javax.swing.JTextField();
        valNumNivelNodo = new javax.swing.JTextField();
        valFechaCreacionNodo = new javax.swing.JTextField();
        comboCategoriaNodo = new javax.swing.JComboBox();
        comboNivelNodo = new javax.swing.JComboBox();
        jLabel7 = new javax.swing.JLabel();
        valDescripcionNodo = new javax.swing.JTextField();
        paneTree = new javax.swing.JLayeredPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        arbolJerarquia = new javax.swing.JTree();
        paneLista = new javax.swing.JLayeredPane();
        scrollPaneJerarquia = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        ButtonPane = new javax.swing.JPanel();
        botonBuscar = new javax.swing.JButton();
        BotonAgregarTermino = new javax.swing.JButton();
        fieldBusqueda = new javax.swing.JTextField();
        labelBusqueda = new javax.swing.JLabel();
        botonCerrar = new javax.swing.JButton();
        botonListarSubarbol = new javax.swing.JButton();
        botonListarHijos = new javax.swing.JButton();
        botonExcluir = new javax.swing.JButton();
        botonModificar = new javax.swing.JButton();
        botonContarTerminos = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setName("Form"); // NOI18N
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        paneFondo.setName("paneFondo"); // NOI18N

        paneDatosNodo.setName("paneDatosNodo"); // NOI18N

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(gestiontipocampo.GestionTipoCampoApp.class).getContext().getResourceMap(frameBuscarTerminos.class);
        jLabel1.setText(resourceMap.getString("jLabel1.text")); // NOI18N
        jLabel1.setEnabled(false);
        jLabel1.setName("jLabel1"); // NOI18N
        jLabel1.setBounds(10, 310, 110, -1);
        paneDatosNodo.add(jLabel1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLabel2.setText(resourceMap.getString("jLabel2.text")); // NOI18N
        jLabel2.setEnabled(false);
        jLabel2.setName("jLabel2"); // NOI18N
        jLabel2.setBounds(10, 10, 60, -1);
        paneDatosNodo.add(jLabel2, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLabel3.setText(resourceMap.getString("jLabel3.text")); // NOI18N
        jLabel3.setEnabled(false);
        jLabel3.setName("jLabel3"); // NOI18N
        jLabel3.setBounds(10, 110, 90, -1);
        paneDatosNodo.add(jLabel3, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLabel4.setText(resourceMap.getString("jLabel4.text")); // NOI18N
        jLabel4.setEnabled(false);
        jLabel4.setName("jLabel4"); // NOI18N
        jLabel4.setBounds(10, 160, 90, -1);
        paneDatosNodo.add(jLabel4, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLabel5.setText(resourceMap.getString("jLabel5.text")); // NOI18N
        jLabel5.setEnabled(false);
        jLabel5.setName("jLabel5"); // NOI18N
        jLabel5.setBounds(10, 210, 160, -1);
        paneDatosNodo.add(jLabel5, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLabel6.setText(resourceMap.getString("jLabel6.text")); // NOI18N
        jLabel6.setEnabled(false);
        jLabel6.setName("jLabel6"); // NOI18N
        jLabel6.setBounds(10, 260, 140, -1);
        paneDatosNodo.add(jLabel6, javax.swing.JLayeredPane.DEFAULT_LAYER);

        valFechaModifNodo.setText(resourceMap.getString("valFechaModifNodo.text")); // NOI18N
        valFechaModifNodo.setEnabled(false);
        valFechaModifNodo.setName("valFechaModifNodo"); // NOI18N
        valFechaModifNodo.setBounds(10, 230, 100, -1);
        paneDatosNodo.add(valFechaModifNodo, javax.swing.JLayeredPane.DEFAULT_LAYER);

        valNombreNodo.setEnabled(false);
        valNombreNodo.setName("valNombreNodo"); // NOI18N
        valNombreNodo.setBounds(10, 30, 100, -1);
        paneDatosNodo.add(valNombreNodo, javax.swing.JLayeredPane.DEFAULT_LAYER);

        valNumNivelNodo.setEnabled(false);
        valNumNivelNodo.setName("valNumNivelNodo"); // NOI18N
        valNumNivelNodo.setBounds(10, 130, 100, -1);
        paneDatosNodo.add(valNumNivelNodo, javax.swing.JLayeredPane.DEFAULT_LAYER);

        valFechaCreacionNodo.setEnabled(false);
        valFechaCreacionNodo.setName("valFechaCreacionNodo"); // NOI18N
        valFechaCreacionNodo.setBounds(10, 180, 100, -1);
        paneDatosNodo.add(valFechaCreacionNodo, javax.swing.JLayeredPane.DEFAULT_LAYER);

        comboCategoriaNodo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        comboCategoriaNodo.setEnabled(false);
        comboCategoriaNodo.setName("comboCategoriaNodo"); // NOI18N
        comboCategoriaNodo.setBounds(10, 330, 100, -1);
        paneDatosNodo.add(comboCategoriaNodo, javax.swing.JLayeredPane.DEFAULT_LAYER);

        comboNivelNodo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        comboNivelNodo.setEnabled(false);
        comboNivelNodo.setName("comboNivelNodo"); // NOI18N
        comboNivelNodo.setBounds(10, 280, 100, -1);
        paneDatosNodo.add(comboNivelNodo, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLabel7.setText(resourceMap.getString("jLabel7.text")); // NOI18N
        jLabel7.setEnabled(false);
        jLabel7.setName("jLabel7"); // NOI18N
        jLabel7.setBounds(10, 60, 90, -1);
        paneDatosNodo.add(jLabel7, javax.swing.JLayeredPane.DEFAULT_LAYER);

        valDescripcionNodo.setEnabled(false);
        valDescripcionNodo.setName("valDescripcionNodo"); // NOI18N
        valDescripcionNodo.setBounds(10, 80, 210, -1);
        paneDatosNodo.add(valDescripcionNodo, javax.swing.JLayeredPane.DEFAULT_LAYER);

        paneDatosNodo.setBounds(180, 0, 230, 390);
        paneFondo.add(paneDatosNodo, javax.swing.JLayeredPane.DEFAULT_LAYER);

        paneTree.setName("paneTree"); // NOI18N

        jScrollPane1.setName("jScrollPane1"); // NOI18N

        javax.swing.tree.DefaultMutableTreeNode treeNode1 = new javax.swing.tree.DefaultMutableTreeNode("Raíz");
        arbolJerarquia.setModel(new javax.swing.tree.DefaultTreeModel(treeNode1));
        arbolJerarquia.setName("arbolJerarquia"); // NOI18N
        arbolJerarquia.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                arbolJerarquiaMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(arbolJerarquia);

        jScrollPane1.setBounds(10, 10, 150, 390);
        paneTree.add(jScrollPane1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        paneTree.setBounds(0, 0, 160, 400);
        paneFondo.add(paneTree, javax.swing.JLayeredPane.DEFAULT_LAYER);

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
        jTable1.getColumnModel().getColumn(0).setHeaderValue(resourceMap.getString("jTable1.columnModel.title0")); // NOI18N
        jTable1.getColumnModel().getColumn(1).setHeaderValue(resourceMap.getString("jTable1.columnModel.title1")); // NOI18N
        jTable1.getColumnModel().getColumn(2).setHeaderValue(resourceMap.getString("jTable1.columnModel.title2")); // NOI18N

        scrollPaneJerarquia.setBounds(10, 10, 290, 380);
        paneLista.add(scrollPaneJerarquia, javax.swing.JLayeredPane.DEFAULT_LAYER);

        paneLista.setBounds(0, 0, 310, 400);
        paneFondo.add(paneLista, javax.swing.JLayeredPane.DEFAULT_LAYER);

        ButtonPane.setName("ButtonPane"); // NOI18N
        ButtonPane.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        botonBuscar.setText(resourceMap.getString("botonBuscar.text")); // NOI18N
        botonBuscar.setName("botonBuscar"); // NOI18N
        botonBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonBuscarActionPerformed(evt);
            }
        });
        ButtonPane.add(botonBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 320, -1, -1));

        BotonAgregarTermino.setText(resourceMap.getString("BotonAgregarTermino.text")); // NOI18N
        BotonAgregarTermino.setName("BotonAgregarTermino"); // NOI18N
        BotonAgregarTermino.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotonAgregarTerminoActionPerformed(evt);
            }
        });
        ButtonPane.add(BotonAgregarTermino, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 270, -1, -1));

        fieldBusqueda.setText(resourceMap.getString("fieldBusqueda.text")); // NOI18N
        fieldBusqueda.setName("fieldBusqueda"); // NOI18N
        ButtonPane.add(fieldBusqueda, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 20, 60, -1));

        labelBusqueda.setLabelFor(fieldBusqueda);
        labelBusqueda.setText(resourceMap.getString("labelBusqueda.text")); // NOI18N
        labelBusqueda.setName("labelBusqueda"); // NOI18N
        ButtonPane.add(labelBusqueda, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 50, -1));

        botonCerrar.setText(resourceMap.getString("botonCerrar.text")); // NOI18N
        botonCerrar.setName("botonCerrar"); // NOI18N
        botonCerrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonCerrarActionPerformed(evt);
            }
        });
        ButtonPane.add(botonCerrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 240, -1, -1));

        botonListarSubarbol.setText(resourceMap.getString("botonListarSubarbol.text")); // NOI18N
        botonListarSubarbol.setName("botonListarSubarbol"); // NOI18N
        botonListarSubarbol.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonListarSubarbolActionPerformed(evt);
            }
        });
        ButtonPane.add(botonListarSubarbol, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 200, -1, -1));

        botonListarHijos.setText(resourceMap.getString("botonListarHijos.text")); // NOI18N
        botonListarHijos.setName("botonListarHijos"); // NOI18N
        botonListarHijos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonListarHijosActionPerformed(evt);
            }
        });
        ButtonPane.add(botonListarHijos, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 150, -1, -1));

        botonExcluir.setText(resourceMap.getString("botonExcluir.text")); // NOI18N
        botonExcluir.setName("botonExcluir"); // NOI18N
        botonExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonExcluirActionPerformed(evt);
            }
        });
        ButtonPane.add(botonExcluir, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 50, -1, -1));

        botonModificar.setText(resourceMap.getString("botonModificar.text")); // NOI18N
        botonModificar.setName("botonModificar"); // NOI18N
        botonModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonModificarActionPerformed(evt);
            }
        });
        ButtonPane.add(botonModificar, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 80, -1, -1));

        botonContarTerminos.setText(resourceMap.getString("botonContarTerminos.text")); // NOI18N
        botonContarTerminos.setName("botonContarTerminos"); // NOI18N
        botonContarTerminos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonContarTerminosActionPerformed(evt);
            }
        });
        ButtonPane.add(botonContarTerminos, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 110, -1, -1));

        ButtonPane.setBounds(430, 0, -1, 400);
        paneFondo.add(ButtonPane, javax.swing.JLayeredPane.DEFAULT_LAYER);

        getContentPane().add(paneFondo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 580, 430));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void botonModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonModificarActionPerformed
        frameTermino fram;
        DefaultMutableTreeNode node = (DefaultMutableTreeNode) arbolJerarquia.getLastSelectedPathComponent();
        if (node != null) {
            //TreeNode[] jerPath = node.getPath();
            String nombreNodo = node.toString();
            int nivel = node.getLevel() + 1; //Agrega uno xq utilizamos raiz cmo nivel 1
            int IDJerarquia = getIDJerarquia(nombreJerarquia);
            int IDNodoPadre = getIDNodo(nombreNodo);

            fram = new frameTermino(IDJerarquia, IDNodoPadre, 1, this, nombreJerarquia, nivel);
            fram.llenarDatos();
            fram.llenarComboCategoria();

            fram.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(this, "Ningún elemento seleccionado para modificar!");
        }
}//GEN-LAST:event_botonModificarActionPerformed
    int posicion; //una variable global para buscarNodos. En el futuro sera eliminada, es solo para presentarselo al prof mañana viernes.

    private String[] buscarNodos(String nombreJerarquia) {

        String IDNodoRaiz = "";
        String[] result = new String[100];//por ahora asi, despues se puede poner  algo dinamico
        posicion = 0;
        int IDJerarquia = getIDJerarquia(nombreJerarquia);

        //buscamos la raiz
        try {
            ResultSet resultado = buscador.getResultSet("select IDNodoRaiz from JERARQUIA where nombreJerarquia = '" + nombreJerarquia + "';");
            if (resultado.next()) {
                IDNodoRaiz = resultado.getObject("IDNodoRaiz").toString();
            }
        } catch (SQLException e) {
            System.out.println("*SQL Exception: aca?*" + e.toString());
        }

        //Sacamos la lista de nodos de primer nivel y despues recursivamente del resto
        try {
            ResultSet resultado = buscador.getResultSet("select ID from NODO where IDNodoPadre = '" + IDNodoRaiz + "';");
            while (resultado.next()) {
                result[posicion] = new String(resultado.getObject("ID").toString());
                posicion++;
                buscarSubNodos(result[posicion - 1], result);
            }
        } catch (SQLException e) {
            System.out.println("*SQL Exception aqui?: *" + e.toString());
        }
        return result;
    }

    private void buscarSubNodos(String IDNodo, String[] result) {
        try {
            ResultSet resultado2 = buscador.getResultSet("select ID from NODO where IDNodoPadre = '" + IDNodo + "';");
            while (resultado2.next()) {
                result[posicion] = new String(resultado2.getObject("ID").toString());
                posicion++;
                buscarSubNodos(result[posicion - 1], result);
            }
        } catch (SQLException e) {
            System.out.println("*SQL Exception: *" + e.toString());
        }
    }

    private void botonListarHijosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonListarHijosActionPerformed
        String[] listaElementos;
        String jerarq = arbolJerarquia.getModel().getRoot().toString();
        listaElementos = buscarNodos(jerarq);
        frameBusquedaNodos frame;
        frame = new frameBusquedaNodos();
        frame.llenarTabla(listaElementos, posicion+1);
        System.out.println("listado elementos " + posicion + "");
    //paneTree.setVisible(false);
    //paneLista.setVisible(true);
}//GEN-LAST:event_botonListarHijosActionPerformed

    private void botonListarSubarbolActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonListarSubarbolActionPerformed
        paneTree.setVisible(false);
        paneDatosNodo.setVisible(false);
        paneLista.setVisible(true);
}//GEN-LAST:event_botonListarSubarbolActionPerformed

    private void botonCerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonCerrarActionPerformed
        this.dispose();
}//GEN-LAST:event_botonCerrarActionPerformed

    private void BotonAgregarTerminoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotonAgregarTerminoActionPerformed
        frameTermino fram;
        DefaultMutableTreeNode node = (DefaultMutableTreeNode) arbolJerarquia.getLastSelectedPathComponent();
        if (node != null) {
            /*   TreeNode[] jerPath = node.getPath();
            for (int i = 0; i < jerPath.length; i++) {
            System.out.println(i + " " + jerPath[i].toString());
            }*/
            String nombreNodo = node.toString();
            int nivel = node.getLevel() + 1; //Agrega uno xq utilizamos raiz cmo nivel 1
            int IDJerarquia = getIDJerarquia(nombreJerarquia);
            int IDNodoPadre = getIDNodo(nombreNodo);

            fram = new frameTermino(IDJerarquia, IDNodoPadre, 0, this, nombreJerarquia, nivel);
            fram.llenarComboCategoria();
            fram.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(this, "Ningún elemento seleccionado para agregarle termino!");
        }
}//GEN-LAST:event_BotonAgregarTerminoActionPerformed

    private int getIDJerarquia(String nombre) {
        String ID = "";
        try {
            ResultSet resultado = buscador.getResultSet("select correlativo from JERARQUIA where nombreJerarquia = '" + nombre + "';");
            if (resultado.next()) {
                ID = resultado.getObject("correlativo").toString();
            }
        } catch (SQLException e) {
            System.out.println("*SQL Exception: *" + e.toString());
        }
        return Integer.parseInt(ID);
    }

    private int getIDNodo(/*TreeNode[] path*/String nombre) {
        //TreeNode[] jerPath = path;
        String idNodo = "";
        // String tempNodo = "";
       /* try {
        ResultSet resultado = buscador.getResultSet("select IDNodoRaiz from JERARQUIA where nombreJerarquia = '" + nombreJerarquia + "';");
        if (resultado.next()) {
        idNodo = resultado.getObject("IDNodoRaiz").toString();
        }
        } catch (SQLException e) {
        System.out.println("*SQL Exception: *" + e.toString());
        }
        for (int i = 1; i < jerPath.length; i++) {
        tempNodo = idNodo;*/
        try {
            ResultSet resultado = buscador.getResultSet("select ID from NODO where nombre = '" + nombre + "';");
            if (resultado.next()) {
                idNodo = resultado.getObject("ID").toString();
            }
        } catch (SQLException e) {
            System.out.println("*SQL Exception: *" + e.toString());
        }
        //}

        return Integer.parseInt(idNodo);
    }

    private void busquedaSimple(String palabra, String[] lista) {
        /*
        String consulta = "select * from NODO where (";
        consulta += "ID = '" + lista[0] + "'";
        for (int i = 1; i < lista.length; i++) {
        consulta += " or ID = '" + lista[i] + "'";
        }
        consulta += ") and ( nombre = '*" + palabra + "*' or descripcion =  '*" + palabra + "*')";

        try {
        ResultSet resultado = buscador.getResultSet(consulta);

        } catch (SQLException e) {
        System.out.println("*SQL Exception: *" + e.toString());
        }*/
    }

    private void botonBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonBuscarActionPerformed
        String[] listaElementos;
        String jerarq = arbolJerarquia.getModel().getRoot().toString();
        listaElementos = buscarNodos(jerarq);
        frameBusquedaNodos frame;
        frame = new frameBusquedaNodos();
        frame.llenarTablaBusqueda(listaElementos, fieldBusqueda.getText(), posicion+1);
}//GEN-LAST:event_botonBuscarActionPerformed

    private void botonContarTerminosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonContarTerminosActionPerformed
        String[] listaElementos;
        String jerarq = arbolJerarquia.getModel().getRoot().toString();
        listaElementos = buscarNodos(jerarq);
        int temp = posicion + 1;
        JOptionPane.showMessageDialog(this,
                "La cantidad de términos en la Jerarquía es de " + temp + " términos.",
                "Cantidad de Términos",
                JOptionPane.INFORMATION_MESSAGE);
}//GEN-LAST:event_botonContarTerminosActionPerformed

    private void botonExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonExcluirActionPerformed
        DefaultMutableTreeNode node = (DefaultMutableTreeNode) arbolJerarquia.getLastSelectedPathComponent();
        if (node != null) {
            String[] opciones = {"Si", "No"};
            int respuesta = JOptionPane.showOptionDialog(null, "¿Seguro que desea eliminar este termino y todos los subterminos?", "Confirmación", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE, null, opciones, "No");

            switch (respuesta) {
                case 0:
                    /*Si esta seguro de borrar*/
                    String nombreNodo = node.toString();
                    int IDNodo = getIDNodo(nombreNodo);
                    borrarHijo(IDNodo);
                    buscador.doUpdate("delete from NODO where ID = " + IDNodo);
                    break;
                case 1:
                    /*No quiso borrar*/
                    break;
            }
            cambiarNumTerminos(-1); //disminuye el num de terminos
            llenarTreeViewJerarquia(nombreJerarquia); //actualiza los datos
        } else {
            JOptionPane.showMessageDialog(this, "Ningún elemento seleccionado para borrar!");
        }
    }//GEN-LAST:event_botonExcluirActionPerformed

    /**
     * Metodo recursivo para borrar todos los hijos del elemento que se borra
     * @param padre - ID del padre
     */
    private void borrarHijo(int padre) {
        
        try {
            ResultSet resultado = buscador.getResultSet("select ID from NODO where IDNodoPadre =" + padre);
            while (resultado.next()) {
                int IDNodo;
                IDNodo = Integer.parseInt(resultado.getObject("ID").toString());
                borrarHijo(IDNodo);
                buscador.doUpdate("delete from NODO where ID = " + IDNodo);
            }
        } catch (SQLException e) {
            System.out.println("*SQL Exception: *" + e.toString());
        }
    }

    private void arbolJerarquiaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_arbolJerarquiaMouseClicked
        llenarDatosNodo();
    }//GEN-LAST:event_arbolJerarquiaMouseClicked

    /**
     * Llena los campos de atributos del nodo
     */
    private void llenarDatosNodo() {
        DefaultMutableTreeNode nodo = (DefaultMutableTreeNode) arbolJerarquia.getLastSelectedPathComponent();
        if (nodo != null) {
            String nombre = nodo.toString(); //nombre del nodo
            String valores = "";
            try {
                ResultSet resultado = buscador.getResultSet("SELECT * from NODO where nombre = '" + nombre + " '");
                if (resultado.next()) {
                    valores += resultado.getObject("descripcion").toString() + ";";
                    valores += resultado.getObject("fechaCreacion").toString() + ";";
                    valores += resultado.getObject("fechaUltimaModificacion").toString() + ";";
                    valores += resultado.getObject("numNivel").toString() + ";";
                //valores += resultado.getObject("IDInstanciaCategoria").toString() + ";";
                }
            } catch (SQLException e) {
                System.out.println("*SQL Exception: *" + e.toString());
            }
            String[] val = valores.split(";"); //divide los valores
            valNombreNodo.setText(nombre);
            valDescripcionNodo.setText(val[0]);
            valNumNivelNodo.setText(val[1]);
            valFechaCreacionNodo.setText(val[2]);
            valFechaModifNodo.setText(val[3]);
        //valNombreNodo.setText(val[0]);
        }
    }

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

    /**
     * Llena los valores del tree view
     * @param nombreJerarquia
     */
    public void llenarTreeViewJerarquia(String nombreJer) {
        //Se llena el arbol
        //Llena los valores del Tree View
        if (!isJerarquiaVacia()) {
            String valores;
            String[] valTrim;
            valores = buscarDatosEnBD(nombreJer);
            valTrim = valores.split(";");
            String IDsHijos;
            String trimIDsHijos[];
            DefaultMutableTreeNode nodoTemp;
            DefaultMutableTreeNode raizArbol = new DefaultMutableTreeNode(buscarNombreNodo(Integer.parseInt(valTrim[1])));
            // if (Integer.parseInt(valTrim[2]) > 1) { //Si tiene mas de un nivel la jerarquia
            IDsHijos = buscarIDHijos(Integer.parseInt(valTrim[1]));
            if (IDsHijos.length() > 1) {
                trimIDsHijos = IDsHijos.split(";");
                for (int j = 0; j < trimIDsHijos.length; ++j) { //Mientras tenga hijos la raiz
                    nodoTemp = llenarSubArbol(Integer.parseInt(trimIDsHijos[j]));
                    raizArbol.add(nodoTemp);
                }
            }
            //   }
            JTree arbolnuevo = new JTree(raizArbol);
            arbolJerarquia.setModel(arbolnuevo.getModel());
        }
    }

    public DefaultMutableTreeNode llenarSubArbol(int ID) {
        String IDsHijos;
        String trimIDsHijos[];
        DefaultMutableTreeNode nodoActual;
        String nombre = buscarNombreNodo(ID);
        DefaultMutableTreeNode nodoTemp;
        nodoActual = new DefaultMutableTreeNode(nombre);
        IDsHijos = buscarIDHijos(ID); //IDs hijos
        if (IDsHijos.length() > 1) {
            trimIDsHijos = IDsHijos.split(";");
            for (int j = 0; j < trimIDsHijos.length; ++j) { //Mientras tenga hijos el nodo
                nodoTemp = llenarSubArbol(Integer.parseInt(trimIDsHijos[j]));
                nodoActual.add(nodoTemp);
            }
        }
        return nodoActual;
    }

    public boolean isJerarquiaVacia() {
        String valor = "";
        try {
            ResultSet resultado = buscador.getResultSet("select nombreJerarquia, IDNodoRaiz from JERARQUIA where nombreJerarquia = '" + nombreJerarquia + "'");
            if (resultado.next()) {
                valor += resultado.getObject("IDNodoRaiz").toString(); //IDRaiz
            }
        } catch (SQLException e) {
            System.out.println("*SQL Exception: *" + e.toString());
        }
        if (valor.equalsIgnoreCase("-1")) //Si esta vacia
        {
            return true;
        }
        return false;
    }

    /**
     * Encargado de buscar los valores TIPOCAMPO segun el nombre en la base de datos
     * @param nombre: Indica el nombre del tipo de campo que va a buscar
     */
    public String buscarDatosEnBD(String nombre) {
        String valores = "";
        System.out.println(nombre);
        try {
            ResultSet resultado = buscador.getResultSet("select correlativo, IDNodoRaiz, numeroDeNiveles from JERARQUIA where nombreJerarquia = '" + nombre + "'");
            if (resultado.next()) {
                valores += resultado.getObject("correlativo").toString() + ";"; //ID correlativo
                valores += resultado.getObject("IDNodoRaiz").toString() + ";"; //IDRaiz
                valores += resultado.getObject("NumeroDeNiveles").toString() + ";"; //num niveles
            //System.out.println("valores: "+valores);
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
    public String buscarIDHijos(int IDnodo) {
        String valores = "";
        try {
            ResultSet resultado = buscador.getResultSet("select ID from NODO where IDNodoPadre = '" + IDnodo + "';");
            while (resultado.next()) {
                valores += resultado.getObject("ID").toString() + ";"; //ID nodo hijo
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
    public String buscarNombreNodo(int ID) {
        String valores = "";
        try {
            ResultSet resultado = buscador.getResultSet("select nombre from NODO where ID = '" + ID + "';");
            if (resultado != null) {
                while (resultado.next()) {
                    valores += resultado.getObject("nombre").toString();
                }
            }
        } catch (SQLException e) {
            System.out.println("*SQL Exception: *" + e.toString());
        }
        return valores;
    }

    public void cambiarNumTerminos(int cant) {
        //System.out.println("suma/resta: "+ cant + " nombre: "+ nombreJerarquia);
        buscador.doUpdate("UPDATE JERARQUIA SET numeroDeTerminos = (numeroDeTerminos +" + cant + ") WHERE (nombreJerarquia = '" + nombreJerarquia + "')");
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BotonAgregarTermino;
    private javax.swing.JPanel ButtonPane;
    private javax.swing.JTree arbolJerarquia;
    private javax.swing.JButton botonBuscar;
    private javax.swing.JButton botonCerrar;
    private javax.swing.JButton botonContarTerminos;
    private javax.swing.JButton botonExcluir;
    private javax.swing.JButton botonListarHijos;
    private javax.swing.JButton botonListarSubarbol;
    private javax.swing.JButton botonModificar;
    private javax.swing.JComboBox comboCategoriaNodo;
    private javax.swing.JComboBox comboNivelNodo;
    private javax.swing.JTextField fieldBusqueda;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel labelBusqueda;
    private javax.swing.JLayeredPane paneDatosNodo;
    private javax.swing.JLayeredPane paneFondo;
    private javax.swing.JLayeredPane paneLista;
    private javax.swing.JLayeredPane paneTree;
    private javax.swing.JScrollPane scrollPaneJerarquia;
    private javax.swing.JTextField valDescripcionNodo;
    private javax.swing.JTextField valFechaCreacionNodo;
    private javax.swing.JTextField valFechaModifNodo;
    private javax.swing.JTextField valNombreNodo;
    private javax.swing.JTextField valNumNivelNodo;
    // End of variables declaration//GEN-END:variables
    private ControladorBD buscador;
    String nombreJerarquia;
}
