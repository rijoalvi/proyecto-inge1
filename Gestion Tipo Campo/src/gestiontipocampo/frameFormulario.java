/*
 * frameFormulario.java
 *
 * Created on 30-abr-2009, 18:16:02
 */
package gestiontipocampo;

import java.sql.*;
import javax.swing.tree.*;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.swing.*;
import javax.swing.JColorChooser;
import java.awt.Color;
import java.awt.FocusTraversalPolicy;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.util.*;

/**
 *
 * @author Alberto
 */
public class frameFormulario extends javax.swing.JFrame {

    /** Creates new form frameFormulario */
    public frameFormulario() {
        this.setTitle("GIFT Configurador - Edición de Formularios1");
        String nombre = JOptionPane.showInputDialog(this, "Favor ingresar el nombre del formulario a crear", "", JOptionPane.QUESTION_MESSAGE);
        String descripcion = JOptionPane.showInputDialog(this, "Favor ingresar la descripcion del formulario a crear", "", JOptionPane.QUESTION_MESSAGE);

        initComponents();
        postInitComponents();
        miFormulario = new Formulario(nombre, descripcion);
        frameVistaPrevia.setResizable(false);
        frameVistaPrevia.setTitle(nombre);

        //Inicializa el valor de los tabOrder
        tabIndex = 0;

        //Inicializa los recolectores de eventos del mouse
        listener = new DragMouseAdapter();
        motionListener = new DragMouseAdapter();

        //Inicializa el arbol para obtener nodo seleccionado
        arbolPrincipal.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
        llenarTreeView();
        ocultarPanes();
    }

    /** Creates new form frameFormulario */
    public frameFormulario(TreeModel arbolHeredado) {
        this.setTitle("GIFT Configurador - Edición de Formularios2");
        String nombre = JOptionPane.showInputDialog(this, "Favor ingresar el nombre del formulario a crear", "", JOptionPane.QUESTION_MESSAGE);
        String descripcion = JOptionPane.showInputDialog(this, "Favor ingresar la descripcion del formulario a crear", "", JOptionPane.QUESTION_MESSAGE);

        initComponents();
        postInitComponents();
        // frameVistaPrevia = new JInternalFrame(nombre, false);
        miFormulario = new Formulario(nombre, descripcion);
        frameVistaPrevia.setResizable(false);
        frameVistaPrevia.setTitle(nombre);

        //Inicializa el valor de los tabOrder
        tabIndex = 0;

        //Inicializa los recolectores de eventos del mouse
        listener = new DragMouseAdapter();
        motionListener = new DragMouseAdapter();

        //Inicializa el arbol para obtener nodo seleccionado
        arbolPrincipal.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
        arbolPrincipal.setModel(arbolHeredado);
        ocultarPanes();
    }

    /**
     * Creates new form frameFormulario
     */
    public frameFormulario(TreeModel arbolHeredado, int correlativo) {
        this.setTitle("GIFT Configurador - Edición de Formularios con correlativo");
        initComponents();
        postInitComponents();
        
        

        //Inicializa el valor de los tabOrder
        tabIndex = 0;

        //Inicializa los recolectores de eventos del mouse
        listener = new DragMouseAdapter();
        motionListener = new DragMouseAdapter();

        //Inicializa el arbol para obtener nodo seleccionado
        arbolPrincipal.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
        arbolPrincipal.setModel(arbolHeredado);
        ocultarPanes();
        cargarFormulario(correlativo);
        
    }

    void cargarFormulario(int correlativo) {
        miFormulario = new Formulario(correlativo);
        String nombre = miFormulario.getNombre();
        String descripcion = miFormulario.getDescripcion();
        frameVistaPrevia.setResizable(false);
        frameVistaPrevia.setTitle(nombre);
        cargarMiembros();
    }

    private class DragMouseAdapter extends MouseAdapter {

        @Override
        public void mousePressed(MouseEvent e) {
            //System.out.print(" ");
            JComponent c = (JComponent) e.getSource();
            compEnUso = c;
            //Aqui hay q mostrar los valores del componente...
            int ID = Integer.parseInt(c.getName());
            IDEnUso = ID;
            llenarDatosMiembro(ID);
        }

        @Override
        public void mouseDragged(MouseEvent e) {
            JComponent c = (JComponent) e.getSource();
            maxX = frameVistaPrevia.getWidth();
            maxY = frameVistaPrevia.getHeight();

            float tx = e.getX() + c.getX();
            if (tx < 0) {
                tx = 0;
            }
            if (tx > maxX - c.getWidth()) {
                tx = maxX - c.getWidth();
            }
            float ty = e.getY() + c.getY();
            if (ty < 0) {
                ty = 0;
            }
            if (ty > maxY - c.getHeight()) {
                ty = maxY - c.getHeight();
            }

            c.setBounds((int) tx, (int) ty, c.getWidth(), c.getHeight());
            valEjeX.setText("" + (int) tx);
            valEjeY.setText("" + (int) ty);
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            JComponent c = (JComponent) e.getSource();
            //Actualiza cual componente es el ultimo en poseer el focus
            compEnUso = c;
            int ID = Integer.parseInt(c.getName());
            IDEnUso = ID;
            //Aqui hay q guardar los nuevos valores a la BD...
            miFormulario.updatePosicion(ID, Integer.parseInt(valEjeX.getText()), Integer.parseInt(valEjeY.getText()));            
        }
    }

    private void cargarMiembros() {
        //En este switch se debe crear una instancia del nuevo tipo campo a agregar al formulario
        SortedSet miembros = this.miFormulario.getMiembroFormularioSet();
        Iterator iterador = miembros.iterator();

        for (int i = 0; i < miembros.size(); i++) {
            MiembroFormulario miembro = (MiembroFormulario) iterador.next();

            String nombre = miembro.getNombre();
            int color = miembro.getColor();
            int id = miembro.getID();
            IDEnUso = id;
            int idTipoCampo = miembro.getIDTipoCampo();
            int tamLetra = miembro.getTamanoLetra();
            int ancho = miembro.getAncho();
            int alto = miembro.getAlto();
            String tipoLetra = miembro.getTipoLetra();
            int valx = miembro.getValX();
            int valy = miembro.getValY();
            JComponent campo;
            switch (idTipoCampo) {
                case 0:
                    //Etiqueta
                    campo = agregarEtiqueta(nombre, id);
                    llenarDatosMiembro(id);
                    actualizarComponente(textoDato.getText(), Integer.parseInt(valEjeX.getText()), Integer.parseInt(valEjeY.getText()), comboTipoLetra.getSelectedItem().toString(), colorDato.getForeground().getRGB(), Integer.parseInt(tamanoLetra.getText()), compEnUso.getWidth(), compEnUso.getHeight(), comboEstiloLetra.getSelectedItem().toString());
                    campo.setBounds(valx, valy, ancho, alto);
                    break;
                case 1:
                    //Numero
                    campo = agregarTipoNumero(nombre, id);
                    llenarDatosMiembro(id);
                    actualizarComponente(textoDato.getText(), Integer.parseInt(valEjeX.getText()), Integer.parseInt(valEjeY.getText()), comboTipoLetra.getSelectedItem().toString(), colorDato.getForeground().getRGB(), Integer.parseInt(tamanoLetra.getText()), compEnUso.getWidth(), compEnUso.getHeight(), comboEstiloLetra.getSelectedItem().toString());
                    //campo.addMouseListener(listener);
                    //campo.addMouseMotionListener(motionListener);
                    //frameVistaPrevia.add( campo );
                    campo.setBounds(valx, valy, ancho, alto);
                    break;
                case 2:
                    //Binario
                    JRadioButton radioB = agregarTipoBinario(nombre, id);
                    llenarDatosMiembro(id);
                    radioB.setBounds(valx, valy, 100, 20);
                    break;
                case 3:
                    //FechaHora
                    campo = new JTextField();
                    agregarTipoFechaHora(nombre, id);
                    campo.setName("" + IDEnUso);
                    campo.addMouseListener(listener);
                    campo.addMouseMotionListener(motionListener);
                    frameVistaPrevia.add(campo);
                    campo.setBounds(valx, valy, 100, 20);
                    frameVistaPrevia.repaint();
                    break;
                case 4:
                    //Texto
                    campo = new JTextField();
                    agregarTipoTexto(nombre, id);
                    campo.setName("" + IDEnUso);
                    campo.addMouseListener(listener);
                    campo.addMouseMotionListener(motionListener);
                    frameVistaPrevia.add(campo);
                    campo.setBounds(valx, valy, 100, 20);
                    frameVistaPrevia.repaint();
                    break;
                case 5:
                    //Incremental
                    campo = new JTextField();
                    agregarTipoIncremental(nombre, id);
                    campo.setName("" + IDEnUso);
                    campo.addMouseListener(listener);
                    campo.addMouseMotionListener(motionListener);
                    frameVistaPrevia.add(campo);
                    campo.setBounds(valx, valy, 100, 20);
                    frameVistaPrevia.repaint();
                    break;
                case 6:
                    //Jerarquia
                    campo = new JTextField();
                    agregarTipoJerarquia(nombre, id);
                    campo.setName("" + IDEnUso);
                    campo.addMouseListener(listener);
                    campo.addMouseMotionListener(motionListener);
                    frameVistaPrevia.add(campo);
                    campo.setBounds(valx, valy, 100, 20);
                    frameVistaPrevia.repaint();
                    break;
                case 7:
                    //Lista
                    campo = new JTextField();
                    agregarTipoLista(nombre, id);
                    campo.setName("" + IDEnUso);
                    campo.addMouseListener(listener);
                    campo.addMouseMotionListener(motionListener);
                    frameVistaPrevia.add(campo);
                    campo.setBounds(valx, valy, 100, 20);
                    frameVistaPrevia.repaint();
                    break;
                default:
                    break;
            }
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

        jScrollPane1 = new javax.swing.JScrollPane();
        arbolPrincipal = new javax.swing.JTree();
        jButton3 = new javax.swing.JButton();
        paneDatos = new javax.swing.JLayeredPane();
        panePrincipal = new javax.swing.JLayeredPane();
        botonAgregar = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        valorNombreGeneral = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        valorNota = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        comboTipos = new javax.swing.JComboBox();
        paneBinario = new javax.swing.JLayeredPane();
        jLabel7 = new javax.swing.JLabel();
        valorNombreBinario1 = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        valorOpcionBinaria1 = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        valorNombreBinario2 = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        valorOpcionBinaria2 = new javax.swing.JTextField();
        radioOpcionBinaria1 = new javax.swing.JRadioButton();
        radioOpcionBinaria2 = new javax.swing.JRadioButton();
        paneNumero = new javax.swing.JLayeredPane();
        jLabel4 = new javax.swing.JLabel();
        valorNumDecimales = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        valorNumeroMascara = new javax.swing.JTextField();
        valorValorDefectoNumero = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        paneIncremental = new javax.swing.JLayeredPane();
        jLabel17 = new javax.swing.JLabel();
        valorValorInicial = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        valorIncremento = new javax.swing.JTextField();
        paneFechaHora = new javax.swing.JLayeredPane();
        jLabel6 = new javax.swing.JLabel();
        valorPreaviso = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        valorFechaDefecto = new javax.swing.JFormattedTextField();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        radioFechaHoraSi = new javax.swing.JRadioButton();
        radioFechaHoraNo = new javax.swing.JRadioButton();
        comboFormatoFecha = new javax.swing.JComboBox();
        paneJerarquia = new javax.swing.JLayeredPane();
        labelCategorias = new javax.swing.JLabel();
        comboCategorias = new javax.swing.JComboBox();
        radioNivelesSi = new javax.swing.JRadioButton();
        radioNivelesNo = new javax.swing.JRadioButton();
        jLabel21 = new javax.swing.JLabel();
        radioCategoriasSi = new javax.swing.JRadioButton();
        radioCategoriasNo = new javax.swing.JRadioButton();
        jLabel22 = new javax.swing.JLabel();
        radioNomUnicoSi = new javax.swing.JRadioButton();
        radioNomUnicoNo = new javax.swing.JRadioButton();
        jLabel23 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        comboNiveles = new javax.swing.JComboBox();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        valorNumNiveles = new javax.swing.JTextField();
        valorNumTerminos = new javax.swing.JTextField();
        paneTexto = new javax.swing.JLayeredPane();
        jLabel16 = new javax.swing.JLabel();
        valorTextoLargo = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        valorTextoDefecto = new javax.swing.JTextField();
        paneLista = new javax.swing.JLayeredPane();
        valorPorDefectoLista = new javax.swing.JTextField();
        jLabel26 = new javax.swing.JLabel();
        paneFormulario = new javax.swing.JLayeredPane();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        comboTipoLetra = new javax.swing.JComboBox();
        colorDato = new javax.swing.JTextField();
        botonGuardar = new javax.swing.JButton();
        tamanoLetra = new javax.swing.JTextField();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        textoDato = new javax.swing.JTextField();
        valEjeY = new javax.swing.JTextField();
        valEjeX = new javax.swing.JTextField();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        bottonColor = new javax.swing.JButton();
        botonBorrarComp = new javax.swing.JButton();
        botonMasAlto = new javax.swing.JButton();
        botonMasAncho = new javax.swing.JButton();
        jLabel33 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        botonMenosAlto = new javax.swing.JButton();
        botonMenosAncho = new javax.swing.JButton();
        comboEstiloLetra = new javax.swing.JComboBox();
        jLabel35 = new javax.swing.JLabel();
        botonAgregarEtq = new javax.swing.JButton();
        botonTabIndex = new javax.swing.JButton();
        botonActualizar = new javax.swing.JButton();
        frameVistaPrevia = new javax.swing.JInternalFrame();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setName("Form"); // NOI18N

        jScrollPane1.setName("jScrollPane1"); // NOI18N

        javax.swing.tree.DefaultMutableTreeNode treeNode1 = new javax.swing.tree.DefaultMutableTreeNode("Tipos Campo");
        javax.swing.tree.DefaultMutableTreeNode treeNode2 = new javax.swing.tree.DefaultMutableTreeNode("Número");
        treeNode1.add(treeNode2);
        treeNode2 = new javax.swing.tree.DefaultMutableTreeNode("Binario");
        treeNode1.add(treeNode2);
        treeNode2 = new javax.swing.tree.DefaultMutableTreeNode("FechaHora");
        treeNode1.add(treeNode2);
        treeNode2 = new javax.swing.tree.DefaultMutableTreeNode("Texto");
        treeNode1.add(treeNode2);
        treeNode2 = new javax.swing.tree.DefaultMutableTreeNode("Incremental");
        treeNode1.add(treeNode2);
        treeNode2 = new javax.swing.tree.DefaultMutableTreeNode("Jerarquía");
        treeNode1.add(treeNode2);
        treeNode2 = new javax.swing.tree.DefaultMutableTreeNode("Lista");
        treeNode1.add(treeNode2);
        arbolPrincipal.setModel(new javax.swing.tree.DefaultTreeModel(treeNode1));
        arbolPrincipal.setName("arbolPrincipal"); // NOI18N
        arbolPrincipal.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                arbolPrincipalMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(arbolPrincipal);

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(gestiontipocampo.GestionTipoCampoApp.class).getContext().getResourceMap(frameFormulario.class);
        jButton3.setText(resourceMap.getString("jButton3.text")); // NOI18N
        jButton3.setToolTipText(resourceMap.getString("jButton3.toolTipText")); // NOI18N
        jButton3.setName("jButton3"); // NOI18N
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        paneDatos.setName("paneDatos"); // NOI18N

        panePrincipal.setAutoscrolls(true);
        panePrincipal.setName("panePrincipal"); // NOI18N

        botonAgregar.setText(resourceMap.getString("botonAgregar.text")); // NOI18N
        botonAgregar.setName("botonAgregar"); // NOI18N
        botonAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonAgregarActionPerformed(evt);
            }
        });
        botonAgregar.setBounds(260, 340, 80, -1);
        panePrincipal.add(botonAgregar, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLabel2.setText(resourceMap.getString("jLabel2.text")); // NOI18N
        jLabel2.setName("jLabel2"); // NOI18N
        jLabel2.setBounds(20, 70, -1, -1);
        panePrincipal.add(jLabel2, javax.swing.JLayeredPane.DEFAULT_LAYER);

        valorNombreGeneral.setEditable(false);
        valorNombreGeneral.setName("valorNombreGeneral"); // NOI18N
        valorNombreGeneral.setBounds(20, 90, 120, -1);
        panePrincipal.add(valorNombreGeneral, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLabel3.setText(resourceMap.getString("jLabel3.text")); // NOI18N
        jLabel3.setName("jLabel3"); // NOI18N
        jLabel3.setBounds(200, 70, -1, -1);
        panePrincipal.add(jLabel3, javax.swing.JLayeredPane.DEFAULT_LAYER);

        valorNota.setEditable(false);
        valorNota.setName("valorNota"); // NOI18N
        valorNota.setBounds(200, 90, 120, -1);
        panePrincipal.add(valorNota, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLabel1.setText(resourceMap.getString("jLabel1.text")); // NOI18N
        jLabel1.setName("jLabel1"); // NOI18N
        jLabel1.setBounds(20, 20, -1, -1);
        panePrincipal.add(jLabel1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        comboTipos.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "Número", "Binario", "FechaHora", "Texto", "Incremental", "Jerarquia", "Lista" }));
        comboTipos.setEnabled(false);
        comboTipos.setFocusable(false);
        comboTipos.setName("comboTipos"); // NOI18N
        comboTipos.setBounds(20, 40, 120, -1);
        panePrincipal.add(comboTipos, javax.swing.JLayeredPane.DEFAULT_LAYER);

        paneBinario.setName("paneBinario"); // NOI18N

        jLabel7.setText(resourceMap.getString("jLabel7.text")); // NOI18N
        jLabel7.setName("jLabel7"); // NOI18N
        jLabel7.setBounds(10, 10, 100, 20);
        paneBinario.add(jLabel7, javax.swing.JLayeredPane.DEFAULT_LAYER);

        valorNombreBinario1.setEditable(false);
        valorNombreBinario1.setName("valorNombreBinario1"); // NOI18N
        valorNombreBinario1.setBounds(10, 30, 120, 20);
        paneBinario.add(valorNombreBinario1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLabel8.setText(resourceMap.getString("jLabel8.text")); // NOI18N
        jLabel8.setName("jLabel8"); // NOI18N
        jLabel8.setBounds(200, 10, 100, 20);
        paneBinario.add(jLabel8, javax.swing.JLayeredPane.DEFAULT_LAYER);

        valorOpcionBinaria1.setEditable(false);
        valorOpcionBinaria1.setName("valorOpcionBinaria1"); // NOI18N
        valorOpcionBinaria1.setBounds(200, 30, 120, 20);
        paneBinario.add(valorOpcionBinaria1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLabel9.setText(resourceMap.getString("jLabel9.text")); // NOI18N
        jLabel9.setName("jLabel9"); // NOI18N
        jLabel9.setBounds(10, 120, 100, 20);
        paneBinario.add(jLabel9, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLabel10.setText(resourceMap.getString("jLabel10.text")); // NOI18N
        jLabel10.setName("jLabel10"); // NOI18N
        jLabel10.setBounds(10, 60, 120, 20);
        paneBinario.add(jLabel10, javax.swing.JLayeredPane.DEFAULT_LAYER);

        valorNombreBinario2.setEditable(false);
        valorNombreBinario2.setName("valorNombreBinario2"); // NOI18N
        valorNombreBinario2.setBounds(10, 80, 120, 20);
        paneBinario.add(valorNombreBinario2, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLabel11.setText(resourceMap.getString("jLabel11.text")); // NOI18N
        jLabel11.setName("jLabel11"); // NOI18N
        jLabel11.setBounds(200, 60, 100, 20);
        paneBinario.add(jLabel11, javax.swing.JLayeredPane.DEFAULT_LAYER);

        valorOpcionBinaria2.setEditable(false);
        valorOpcionBinaria2.setName("valorOpcionBinaria2"); // NOI18N
        valorOpcionBinaria2.setBounds(200, 80, 120, 20);
        paneBinario.add(valorOpcionBinaria2, javax.swing.JLayeredPane.DEFAULT_LAYER);

        radioOpcionBinaria1.setSelected(true);
        radioOpcionBinaria1.setText(resourceMap.getString("radioOpcionBinaria1.text")); // NOI18N
        radioOpcionBinaria1.setEnabled(false);
        radioOpcionBinaria1.setFocusable(false);
        radioOpcionBinaria1.setName("radioOpcionBinaria1"); // NOI18N
        radioOpcionBinaria1.setBounds(10, 140, 100, 20);
        paneBinario.add(radioOpcionBinaria1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        radioOpcionBinaria2.setText(resourceMap.getString("radioOpcionBinaria2.text")); // NOI18N
        radioOpcionBinaria2.setEnabled(false);
        radioOpcionBinaria2.setFocusable(false);
        radioOpcionBinaria2.setName("radioOpcionBinaria2"); // NOI18N
        radioOpcionBinaria2.setBounds(10, 170, 100, 20);
        paneBinario.add(radioOpcionBinaria2, javax.swing.JLayeredPane.DEFAULT_LAYER);

        paneBinario.setBounds(20, 110, 390, 210);
        panePrincipal.add(paneBinario, javax.swing.JLayeredPane.DEFAULT_LAYER);

        paneNumero.setName("paneNumero"); // NOI18N

        jLabel4.setText(resourceMap.getString("jLabel4.text")); // NOI18N
        jLabel4.setName("jLabel4"); // NOI18N
        jLabel4.setBounds(10, 10, 110, -1);
        paneNumero.add(jLabel4, javax.swing.JLayeredPane.DEFAULT_LAYER);

        valorNumDecimales.setEditable(false);
        valorNumDecimales.setName("valorNumDecimales"); // NOI18N
        valorNumDecimales.setBounds(10, 30, 120, -1);
        paneNumero.add(valorNumDecimales, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLabel5.setText(resourceMap.getString("jLabel5.text")); // NOI18N
        jLabel5.setName("jLabel5"); // NOI18N
        jLabel5.setBounds(200, 10, 50, -1);
        paneNumero.add(jLabel5, javax.swing.JLayeredPane.DEFAULT_LAYER);

        valorNumeroMascara.setEditable(false);
        valorNumeroMascara.setName("valorNumeroMascara"); // NOI18N
        valorNumeroMascara.setBounds(200, 30, 120, -1);
        paneNumero.add(valorNumeroMascara, javax.swing.JLayeredPane.DEFAULT_LAYER);

        valorValorDefectoNumero.setEditable(false);
        valorValorDefectoNumero.setText(resourceMap.getString("valorValorDefectoNumero.text")); // NOI18N
        valorValorDefectoNumero.setName("valorValorDefectoNumero"); // NOI18N
        valorValorDefectoNumero.setBounds(10, 80, 120, -1);
        paneNumero.add(valorValorDefectoNumero, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLabel12.setText(resourceMap.getString("jLabel12.text")); // NOI18N
        jLabel12.setName("jLabel12"); // NOI18N
        jLabel12.setBounds(10, 60, 90, -1);
        paneNumero.add(jLabel12, javax.swing.JLayeredPane.DEFAULT_LAYER);

        paneNumero.setBounds(20, 110, 410, 200);
        panePrincipal.add(paneNumero, javax.swing.JLayeredPane.DEFAULT_LAYER);

        paneIncremental.setName("paneIncremental"); // NOI18N

        jLabel17.setText(resourceMap.getString("jLabel17.text")); // NOI18N
        jLabel17.setName("jLabel17"); // NOI18N
        jLabel17.setBounds(10, 10, 100, 20);
        paneIncremental.add(jLabel17, javax.swing.JLayeredPane.DEFAULT_LAYER);

        valorValorInicial.setEditable(false);
        valorValorInicial.setName("valorValorInicial"); // NOI18N
        valorValorInicial.setBounds(10, 30, 120, 20);
        paneIncremental.add(valorValorInicial, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLabel19.setText(resourceMap.getString("jLabel19.text")); // NOI18N
        jLabel19.setName("jLabel19"); // NOI18N
        jLabel19.setBounds(200, 10, 100, 20);
        paneIncremental.add(jLabel19, javax.swing.JLayeredPane.DEFAULT_LAYER);

        valorIncremento.setEditable(false);
        valorIncremento.setName("valorIncremento"); // NOI18N
        valorIncremento.setBounds(200, 30, 120, 20);
        paneIncremental.add(valorIncremento, javax.swing.JLayeredPane.DEFAULT_LAYER);

        paneIncremental.setBounds(10, 110, 440, 190);
        panePrincipal.add(paneIncremental, javax.swing.JLayeredPane.DEFAULT_LAYER);

        paneFechaHora.setName("paneFechaHora"); // NOI18N

        jLabel6.setText(resourceMap.getString("jLabel6.text")); // NOI18N
        jLabel6.setName("jLabel6"); // NOI18N
        jLabel6.setBounds(10, 20, 100, 10);
        paneFechaHora.add(jLabel6, javax.swing.JLayeredPane.DEFAULT_LAYER);

        valorPreaviso.setEditable(false);
        valorPreaviso.setName("valorPreaviso"); // NOI18N
        valorPreaviso.setBounds(200, 90, 120, -1);
        paneFechaHora.add(valorPreaviso, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLabel13.setText(resourceMap.getString("jLabel13.text")); // NOI18N
        jLabel13.setName("jLabel13"); // NOI18N
        jLabel13.setBounds(200, 20, 100, -1);
        paneFechaHora.add(jLabel13, javax.swing.JLayeredPane.DEFAULT_LAYER);

        valorFechaDefecto.setEditable(false);
        valorFechaDefecto.setText(resourceMap.getString("valorFechaDefecto.text")); // NOI18N
        valorFechaDefecto.setName("valorFechaDefecto"); // NOI18N
        valorFechaDefecto.setBounds(200, 40, 120, -1);
        paneFechaHora.add(valorFechaDefecto, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLabel14.setText(resourceMap.getString("jLabel14.text")); // NOI18N
        jLabel14.setName("jLabel14"); // NOI18N
        jLabel14.setBounds(200, 70, 100, -1);
        paneFechaHora.add(jLabel14, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLabel15.setText(resourceMap.getString("jLabel15.text")); // NOI18N
        jLabel15.setName("jLabel15"); // NOI18N
        jLabel15.setBounds(10, 70, 100, -1);
        paneFechaHora.add(jLabel15, javax.swing.JLayeredPane.DEFAULT_LAYER);

        radioFechaHoraSi.setSelected(true);
        radioFechaHoraSi.setText(resourceMap.getString("radioFechaHoraSi.text")); // NOI18N
        radioFechaHoraSi.setEnabled(false);
        radioFechaHoraSi.setFocusable(false);
        radioFechaHoraSi.setName("radioFechaHoraSi"); // NOI18N
        radioFechaHoraSi.setBounds(10, 90, -1, -1);
        paneFechaHora.add(radioFechaHoraSi, javax.swing.JLayeredPane.DEFAULT_LAYER);

        radioFechaHoraNo.setText(resourceMap.getString("radioFechaHoraNo.text")); // NOI18N
        radioFechaHoraNo.setEnabled(false);
        radioFechaHoraNo.setFocusable(false);
        radioFechaHoraNo.setName("radioFechaHoraNo"); // NOI18N
        radioFechaHoraNo.setBounds(10, 110, -1, 20);
        paneFechaHora.add(radioFechaHoraNo, javax.swing.JLayeredPane.DEFAULT_LAYER);

        comboFormatoFecha.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "dd/mm/aaaa", "mm/dd/aaaa", "aaaa/dd/mm", "aaaa/mm/dd" }));
        comboFormatoFecha.setEnabled(false);
        comboFormatoFecha.setFocusable(false);
        comboFormatoFecha.setName("comboFormatoFecha"); // NOI18N
        comboFormatoFecha.setBounds(10, 40, 90, -1);
        paneFechaHora.add(comboFormatoFecha, javax.swing.JLayeredPane.DEFAULT_LAYER);

        paneFechaHora.setBounds(20, 100, 340, 220);
        panePrincipal.add(paneFechaHora, javax.swing.JLayeredPane.DEFAULT_LAYER);

        paneJerarquia.setName("paneJerarquia"); // NOI18N
        paneJerarquia.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                paneJerarquiaComponentShown(evt);
            }
        });

        labelCategorias.setText(resourceMap.getString("labelCategorias.text")); // NOI18N
        labelCategorias.setName("labelCategorias"); // NOI18N
        labelCategorias.setBounds(200, 70, 60, -1);
        paneJerarquia.add(labelCategorias, javax.swing.JLayeredPane.DEFAULT_LAYER);

        comboCategorias.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1- Categoria 1", "2- Categoria 2", "3- Categoría 3", "4- Categoría 4" }));
        comboCategorias.setEnabled(false);
        comboCategorias.setName("comboCategorias"); // NOI18N
        comboCategorias.setBounds(200, 90, 130, -1);
        paneJerarquia.add(comboCategorias, javax.swing.JLayeredPane.DEFAULT_LAYER);

        radioNivelesSi.setText(resourceMap.getString("radioNivelesSi.text")); // NOI18N
        radioNivelesSi.setEnabled(false);
        radioNivelesSi.setName("radioNivelesSi"); // NOI18N
        radioNivelesSi.setBounds(20, 40, -1, -1);
        paneJerarquia.add(radioNivelesSi, javax.swing.JLayeredPane.DEFAULT_LAYER);

        radioNivelesNo.setText(resourceMap.getString("radioNivelesNo.text")); // NOI18N
        radioNivelesNo.setEnabled(false);
        radioNivelesNo.setName("radioNivelesNo"); // NOI18N
        radioNivelesNo.setBounds(60, 40, 50, -1);
        paneJerarquia.add(radioNivelesNo, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLabel21.setText(resourceMap.getString("jLabel21.text")); // NOI18N
        jLabel21.setName("jLabel21"); // NOI18N
        jLabel21.setBounds(20, 20, 130, -1);
        paneJerarquia.add(jLabel21, javax.swing.JLayeredPane.DEFAULT_LAYER);

        radioCategoriasSi.setText(resourceMap.getString("radioCategoriasSi.text")); // NOI18N
        radioCategoriasSi.setEnabled(false);
        radioCategoriasSi.setName("radioCategoriasSi"); // NOI18N
        radioCategoriasSi.setBounds(20, 90, -1, -1);
        paneJerarquia.add(radioCategoriasSi, javax.swing.JLayeredPane.DEFAULT_LAYER);

        radioCategoriasNo.setText(resourceMap.getString("radioCategoriasNo.text")); // NOI18N
        radioCategoriasNo.setEnabled(false);
        radioCategoriasNo.setName("radioCategoriasNo"); // NOI18N
        radioCategoriasNo.setBounds(60, 90, 50, -1);
        paneJerarquia.add(radioCategoriasNo, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLabel22.setText(resourceMap.getString("jLabel22.text")); // NOI18N
        jLabel22.setName("jLabel22"); // NOI18N
        jLabel22.setBounds(20, 70, 80, -1);
        paneJerarquia.add(jLabel22, javax.swing.JLayeredPane.DEFAULT_LAYER);

        radioNomUnicoSi.setText(resourceMap.getString("radioNomUnicoSi.text")); // NOI18N
        radioNomUnicoSi.setEnabled(false);
        radioNomUnicoSi.setName("radioNomUnicoSi"); // NOI18N
        radioNomUnicoSi.setBounds(20, 140, -1, -1);
        paneJerarquia.add(radioNomUnicoSi, javax.swing.JLayeredPane.DEFAULT_LAYER);

        radioNomUnicoNo.setText(resourceMap.getString("radioNomUnicoNo.text")); // NOI18N
        radioNomUnicoNo.setEnabled(false);
        radioNomUnicoNo.setName("radioNomUnicoNo"); // NOI18N
        radioNomUnicoNo.setBounds(60, 140, 50, -1);
        paneJerarquia.add(radioNomUnicoNo, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLabel23.setText(resourceMap.getString("jLabel23.text")); // NOI18N
        jLabel23.setName("jLabel23"); // NOI18N
        jLabel23.setBounds(20, 120, 70, -1);
        paneJerarquia.add(jLabel23, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLabel20.setText(resourceMap.getString("jLabel20.text")); // NOI18N
        jLabel20.setName("jLabel20"); // NOI18N
        jLabel20.setBounds(200, 20, -1, -1);
        paneJerarquia.add(jLabel20, javax.swing.JLayeredPane.DEFAULT_LAYER);

        comboNiveles.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1- Categoria 1", "2- Categoria 2", "3- Categoría 3", "4- Categoría 4" }));
        comboNiveles.setEnabled(false);
        comboNiveles.setName("comboNiveles"); // NOI18N
        comboNiveles.setBounds(200, 40, 130, -1);
        paneJerarquia.add(comboNiveles, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLabel24.setText(resourceMap.getString("jLabel24.text")); // NOI18N
        jLabel24.setName("jLabel24"); // NOI18N
        jLabel24.setBounds(200, 170, 110, -1);
        paneJerarquia.add(jLabel24, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLabel25.setText(resourceMap.getString("jLabel25.text")); // NOI18N
        jLabel25.setName("jLabel25"); // NOI18N
        jLabel25.setBounds(20, 170, 110, -1);
        paneJerarquia.add(jLabel25, javax.swing.JLayeredPane.DEFAULT_LAYER);

        valorNumNiveles.setEnabled(false);
        valorNumNiveles.setName("valorNumNiveles"); // NOI18N
        valorNumNiveles.setBounds(200, 190, 70, -1);
        paneJerarquia.add(valorNumNiveles, javax.swing.JLayeredPane.DEFAULT_LAYER);

        valorNumTerminos.setEnabled(false);
        valorNumTerminos.setName("valorNumTerminos"); // NOI18N
        valorNumTerminos.setBounds(20, 190, 70, -1);
        paneJerarquia.add(valorNumTerminos, javax.swing.JLayeredPane.DEFAULT_LAYER);

        paneJerarquia.setBounds(20, 110, 390, 250);
        panePrincipal.add(paneJerarquia, javax.swing.JLayeredPane.DEFAULT_LAYER);

        paneTexto.setName("paneTexto"); // NOI18N

        jLabel16.setText(resourceMap.getString("jLabel16.text")); // NOI18N
        jLabel16.setName("jLabel16"); // NOI18N
        jLabel16.setBounds(10, 10, 100, 20);
        paneTexto.add(jLabel16, javax.swing.JLayeredPane.DEFAULT_LAYER);

        valorTextoLargo.setEditable(false);
        valorTextoLargo.setName("valorTextoLargo"); // NOI18N
        valorTextoLargo.setBounds(10, 30, 120, 20);
        paneTexto.add(valorTextoLargo, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLabel18.setText(resourceMap.getString("jLabel18.text")); // NOI18N
        jLabel18.setName("jLabel18"); // NOI18N
        jLabel18.setBounds(190, 10, 100, 20);
        paneTexto.add(jLabel18, javax.swing.JLayeredPane.DEFAULT_LAYER);

        valorTextoDefecto.setEditable(false);
        valorTextoDefecto.setName("valorTextoDefecto"); // NOI18N
        valorTextoDefecto.setBounds(190, 30, 120, 20);
        paneTexto.add(valorTextoDefecto, javax.swing.JLayeredPane.DEFAULT_LAYER);

        paneTexto.setBounds(10, 110, 360, 220);
        panePrincipal.add(paneTexto, javax.swing.JLayeredPane.DEFAULT_LAYER);

        paneLista.setName("paneLista"); // NOI18N

        valorPorDefectoLista.setEditable(false);
        valorPorDefectoLista.setName("valorPorDefectoLista"); // NOI18N
        valorPorDefectoLista.setBounds(10, 40, 110, -1);
        paneLista.add(valorPorDefectoLista, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLabel26.setText(resourceMap.getString("jLabel26.text")); // NOI18N
        jLabel26.setName("jLabel26"); // NOI18N
        jLabel26.setBounds(10, 20, 90, -1);
        paneLista.add(jLabel26, javax.swing.JLayeredPane.DEFAULT_LAYER);

        paneLista.setBounds(0, 120, 400, 160);
        panePrincipal.add(paneLista, javax.swing.JLayeredPane.DEFAULT_LAYER);

        panePrincipal.setBounds(10, 30, 420, 410);
        paneDatos.add(panePrincipal, javax.swing.JLayeredPane.DEFAULT_LAYER);

        paneFormulario.setName("paneFormulario"); // NOI18N

        jLabel27.setText(resourceMap.getString("jLabel27.text")); // NOI18N
        jLabel27.setName("jLabel27"); // NOI18N
        jLabel27.setBounds(260, 80, 80, 14);
        paneFormulario.add(jLabel27, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLabel28.setText(resourceMap.getString("jLabel28.text")); // NOI18N
        jLabel28.setName("jLabel28"); // NOI18N
        jLabel28.setBounds(20, 130, 100, 14);
        paneFormulario.add(jLabel28, javax.swing.JLayeredPane.DEFAULT_LAYER);

        java.awt.GraphicsEnvironment grficen = GraphicsEnvironment.getLocalGraphicsEnvironment();
        String[] fonts = grficen.getAvailableFontFamilyNames();
        comboTipoLetra.setModel(new javax.swing.DefaultComboBoxModel(fonts));
        comboTipoLetra.setName("comboTipoLetra"); // NOI18N
        comboTipoLetra.setBounds(10, 100, 80, 20);
        paneFormulario.add(comboTipoLetra, javax.swing.JLayeredPane.DEFAULT_LAYER);

        colorDato.setEditable(false);
        colorDato.setText(resourceMap.getString("colorDato.text")); // NOI18N
        colorDato.setName("colorDato"); // NOI18N
        colorDato.setBounds(140, 50, 60, 20);
        paneFormulario.add(colorDato, javax.swing.JLayeredPane.DEFAULT_LAYER);

        botonGuardar.setText(resourceMap.getString("botonGuardar.text")); // NOI18N
        botonGuardar.setName("botonGuardar"); // NOI18N
        botonGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonGuardarActionPerformed(evt);
            }
        });
        botonGuardar.setBounds(290, 210, 71, 23);
        paneFormulario.add(botonGuardar, javax.swing.JLayeredPane.DEFAULT_LAYER);

        tamanoLetra.setText(resourceMap.getString("tamanoLetra.text")); // NOI18N
        tamanoLetra.setName("tamanoLetra"); // NOI18N
        tamanoLetra.setBounds(260, 100, 80, 20);
        paneFormulario.add(tamanoLetra, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLabel29.setText(resourceMap.getString("jLabel29.text")); // NOI18N
        jLabel29.setName("jLabel29"); // NOI18N
        jLabel29.setBounds(10, 80, 60, 14);
        paneFormulario.add(jLabel29, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLabel30.setText(resourceMap.getString("jLabel30.text")); // NOI18N
        jLabel30.setName("jLabel30"); // NOI18N
        jLabel30.setBounds(320, 30, 60, 14);
        paneFormulario.add(jLabel30, javax.swing.JLayeredPane.DEFAULT_LAYER);

        textoDato.setName("textoDato"); // NOI18N
        textoDato.setBounds(10, 50, 80, 20);
        paneFormulario.add(textoDato, javax.swing.JLayeredPane.DEFAULT_LAYER);

        valEjeY.setText(resourceMap.getString("valEjeY.text")); // NOI18N
        valEjeY.setName("valEjeY"); // NOI18N
        valEjeY.setBounds(320, 50, 50, 20);
        paneFormulario.add(valEjeY, javax.swing.JLayeredPane.DEFAULT_LAYER);

        valEjeX.setText(resourceMap.getString("valEjeX.text")); // NOI18N
        valEjeX.setName("valEjeX"); // NOI18N
        valEjeX.setBounds(250, 50, 50, 20);
        paneFormulario.add(valEjeX, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLabel31.setText(resourceMap.getString("jLabel31.text")); // NOI18N
        jLabel31.setName("jLabel31"); // NOI18N
        jLabel31.setBounds(140, 30, 60, 14);
        paneFormulario.add(jLabel31, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLabel32.setText(resourceMap.getString("jLabel32.text")); // NOI18N
        jLabel32.setName("jLabel32"); // NOI18N
        jLabel32.setBounds(240, 30, 60, 14);
        paneFormulario.add(jLabel32, javax.swing.JLayeredPane.DEFAULT_LAYER);

        bottonColor.setText(resourceMap.getString("bottonColor.text")); // NOI18N
        bottonColor.setName("bottonColor"); // NOI18N
        bottonColor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bottonColorActionPerformed(evt);
            }
        });
        bottonColor.setBounds(200, 50, 30, 23);
        paneFormulario.add(bottonColor, javax.swing.JLayeredPane.DEFAULT_LAYER);

        botonBorrarComp.setText(resourceMap.getString("botonBorrarComp.text")); // NOI18N
        botonBorrarComp.setName("botonBorrarComp"); // NOI18N
        botonBorrarComp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonBorrarCompActionPerformed(evt);
            }
        });
        botonBorrarComp.setBounds(210, 210, 70, 23);
        paneFormulario.add(botonBorrarComp, javax.swing.JLayeredPane.DEFAULT_LAYER);

        botonMasAlto.setText(resourceMap.getString("botonMasAlto.text")); // NOI18N
        botonMasAlto.setName("botonMasAlto"); // NOI18N
        botonMasAlto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonMasAltoActionPerformed(evt);
            }
        });
        botonMasAlto.setBounds(20, 180, 60, 23);
        paneFormulario.add(botonMasAlto, javax.swing.JLayeredPane.DEFAULT_LAYER);

        botonMasAncho.setText(resourceMap.getString("botonMasAncho.text")); // NOI18N
        botonMasAncho.setName("botonMasAncho"); // NOI18N
        botonMasAncho.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonMasAnchoActionPerformed(evt);
            }
        });
        botonMasAncho.setBounds(20, 150, 63, 23);
        paneFormulario.add(botonMasAncho, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLabel33.setText(resourceMap.getString("jLabel33.text")); // NOI18N
        jLabel33.setName("jLabel33"); // NOI18N
        jLabel33.setBounds(10, 30, 80, 14);
        paneFormulario.add(jLabel33, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLabel34.setText(resourceMap.getString("jLabel34.text")); // NOI18N
        jLabel34.setName("jLabel34"); // NOI18N
        jLabel34.setBounds(140, 130, 100, 14);
        paneFormulario.add(jLabel34, javax.swing.JLayeredPane.DEFAULT_LAYER);

        botonMenosAlto.setText(resourceMap.getString("botonMenosAlto.text")); // NOI18N
        botonMenosAlto.setName("botonMenosAlto"); // NOI18N
        botonMenosAlto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonMenosAltoActionPerformed(evt);
            }
        });
        botonMenosAlto.setBounds(140, 180, 60, 23);
        paneFormulario.add(botonMenosAlto, javax.swing.JLayeredPane.DEFAULT_LAYER);

        botonMenosAncho.setText(resourceMap.getString("botonMenosAncho.text")); // NOI18N
        botonMenosAncho.setName("botonMenosAncho"); // NOI18N
        botonMenosAncho.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonMenosAnchoActionPerformed(evt);
            }
        });
        botonMenosAncho.setBounds(140, 150, 63, 23);
        paneFormulario.add(botonMenosAncho, javax.swing.JLayeredPane.DEFAULT_LAYER);

        comboEstiloLetra.setModel(new javax.swing.DefaultComboBoxModel(new String[]{"Plain", "Bold", "Italic", "BoldItalic"}));
        comboEstiloLetra.setName("comboEstiloLetra"); // NOI18N
        comboEstiloLetra.setBounds(140, 100, 90, 20);
        paneFormulario.add(comboEstiloLetra, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLabel35.setText(resourceMap.getString("jLabel35.text")); // NOI18N
        jLabel35.setName("jLabel35"); // NOI18N
        jLabel35.setBounds(140, 80, 60, 14);
        paneFormulario.add(jLabel35, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLabel35.getAccessibleContext().setAccessibleName(resourceMap.getString("jLabel35.AccessibleContext.accessibleName")); // NOI18N

        paneFormulario.setBounds(10, 110, 390, 250);
        paneDatos.add(paneFormulario, javax.swing.JLayeredPane.DEFAULT_LAYER);

        botonAgregarEtq.setText(resourceMap.getString("botonAgregarEtq.text")); // NOI18N
        botonAgregarEtq.setName("botonAgregarEtq"); // NOI18N
        botonAgregarEtq.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonAgregarEtqActionPerformed(evt);
            }
        });
        botonAgregarEtq.setBounds(30, 10, 120, -1);
        paneDatos.add(botonAgregarEtq, javax.swing.JLayeredPane.DEFAULT_LAYER);

        botonTabIndex.setText(resourceMap.getString("botonTabIndex.text")); // NOI18N
        botonTabIndex.setName("botonTabIndex"); // NOI18N
        botonTabIndex.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonTabIndexActionPerformed(evt);
            }
        });
        botonTabIndex.setBounds(190, 10, 180, -1);
        paneDatos.add(botonTabIndex, javax.swing.JLayeredPane.DEFAULT_LAYER);

        botonActualizar.setText(resourceMap.getString("botonActualizar.text")); // NOI18N
        botonActualizar.setName("botonActualizar"); // NOI18N
        botonActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonActualizarActionPerformed(evt);
            }
        });

        frameVistaPrevia.setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        frameVistaPrevia.setIconifiable(true);
        frameVistaPrevia.setTitle(resourceMap.getString("frameVistaPrevia.title")); // NOI18N
        frameVistaPrevia.setMaximumSize(new java.awt.Dimension(49800, 67800));
        frameVistaPrevia.setName("frameVistaPrevia"); // NOI18N
        frameVistaPrevia.setVisible(true);

        javax.swing.GroupLayout frameVistaPreviaLayout = new javax.swing.GroupLayout(frameVistaPrevia.getContentPane());
        frameVistaPrevia.getContentPane().setLayout(frameVistaPreviaLayout);
        frameVistaPreviaLayout.setHorizontalGroup(
            frameVistaPreviaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 496, Short.MAX_VALUE)
        );
        frameVistaPreviaLayout.setVerticalGroup(
            frameVistaPreviaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 846, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(28, 28, 28)
                                .addComponent(botonActualizar)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(paneDatos, javax.swing.GroupLayout.PREFERRED_SIZE, 449, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(frameVistaPrevia, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap(1035, Short.MAX_VALUE)
                        .addComponent(jButton3)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 376, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(botonActualizar))
                    .addComponent(paneDatos, javax.swing.GroupLayout.PREFERRED_SIZE, 398, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(frameVistaPrevia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton3)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void postInitComponents() {
        botonActualizar.setSize(79, 23);
        botonAgregar.setSize(71, 23);
        botonAgregarEtq.setSize(105, 23);
        botonBorrarComp.setSize(63, 23);
        botonGuardar.setSize(71, 23);
        botonMasAlto.setSize(63, 23);
        botonMasAncho.setSize(63, 23);
        botonMenosAlto.setSize(63, 23);
        botonMenosAncho.setSize(63, 23);
        botonTabIndex.setSize(73, 23);
        bottonColor.setSize(45, 23);
        colorDato.setSize(31, 20);
        comboCategorias.setSize(130, 20);
        comboFormatoFecha.setSize(130, 20);
        comboNiveles.setSize(130, 20);
        comboTipoLetra.setSize(130, 20);
        comboTipos.setSize(130, 20);
        frameVistaPrevia.setSize(500, 880);
        jButton3.setSize(63, 23);
        jLabel1.setSize(100, 14);
        jLabel10.setSize(100, 14);
        jLabel11.setSize(100, 14);
        jLabel12.setSize(100, 14);
        jLabel13.setSize(100, 14);
        jLabel14.setSize(100, 14);
        jLabel15.setSize(100, 14);
        jLabel16.setSize(100, 14);
        jLabel17.setSize(100, 14);
        jLabel18.setSize(100, 14);
        jLabel19.setSize(100, 14);
        jLabel2.setSize(100, 14);
        jLabel20.setSize(100, 14);
        jLabel21.setSize(100, 14);
        jLabel22.setSize(100, 14);
        jLabel23.setSize(100, 14);
        jLabel24.setSize(100, 14);
        jLabel25.setSize(100, 14);
        jLabel26.setSize(100, 14);
        jLabel27.setSize(100, 14);
        jLabel28.setSize(100, 14);
        jLabel29.setSize(100, 14);
        jLabel3.setSize(100, 14);
        jLabel30.setSize(100, 14);
        jLabel31.setSize(100, 14);
        jLabel32.setSize(100, 14);
        jLabel33.setSize(100, 14);
        jLabel34.setSize(100, 14);
        jLabel4.setSize(100, 14);
        jLabel5.setSize(100, 14);
        jLabel6.setSize(100, 14);
        jLabel7.setSize(100, 14);
        jLabel8.setSize(100, 14);
        jLabel9.setSize(100, 14);
        jScrollPane1.setSize(121, 376);
        labelCategorias.setSize(100, 14);
        paneBinario.setSize(390, 210);
        paneDatos.setSize(449, 398);
        paneFechaHora.setSize(340, 220);
        paneFormulario.setSize(390, 250);
        paneIncremental.setSize(440, 190);
        paneJerarquia.setSize(390, 250);
        paneLista.setSize(400, 160);
        paneNumero.setSize(410, 200);
        panePrincipal.setSize(420, 410);
        paneTexto.setSize(360, 220);
        radioCategoriasNo.setSize(33, 23);
        radioCategoriasSi.setSize(33, 23);
        radioFechaHoraNo.setSize(33, 23);
        radioFechaHoraSi.setSize(33, 23);
        radioNivelesNo.setSize(33, 23);
        radioNivelesSi.setSize(33, 23);
        radioNomUnicoNo.setSize(33, 23);
        radioNomUnicoSi.setSize(33, 23);
        radioOpcionBinaria1.setSize(33, 23);
        radioOpcionBinaria2.setSize(33, 23);
        tamanoLetra.setSize(80, 20);
        textoDato.setSize(80, 20);
        valEjeX.setSize(80, 20);
        valEjeY.setSize(80, 20);
        valorFechaDefecto.setSize(120, 20);
        valorIncremento.setSize(120, 20);
        valorNombreBinario1.setSize(120, 20);
        valorNombreBinario2.setSize(120, 20);
        valorNombreGeneral.setSize(120, 20);
        valorNota.setSize(120, 20);
        valorNumDecimales.setSize(120, 20);
        valorNumNiveles.setSize(120, 20);
        valorNumTerminos.setSize(120, 20);
        valorNumeroMascara.setSize(120, 20);
        valorOpcionBinaria1.setSize(120, 20);
        valorOpcionBinaria2.setSize(120, 20);
        valorPorDefectoLista.setSize(120, 20);
        valorPreaviso.setSize(120, 20);
        valorTextoDefecto.setSize(120, 20);
        valorTextoLargo.setSize(120, 20);
        valorValorDefectoNumero.setSize(120, 20);
        valorValorInicial.setSize(120, 20);

        jLabel35.setSize(52, 14);
        comboEstiloLetra.setSize(80, 20);
    }

    private void paneJerarquiaComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_paneJerarquiaComponentShown
        Modelo miModelo = new Modelo();
        comboCategorias.setModel(new javax.swing.DefaultComboBoxModel(miModelo.getModeloDeCombo("select nombre, ID from TIPOCATEGORIA;")));
}//GEN-LAST:event_paneJerarquiaComponentShown

    private void arbolPrincipalMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_arbolPrincipalMouseClicked
        //actualizarPath();
        abrirNodoHoja();
}//GEN-LAST:event_arbolPrincipalMouseClicked

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void botonAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonAgregarActionPerformed
        agregarCampo();
}//GEN-LAST:event_botonAgregarActionPerformed

    /**
     * Agrega un nuevo campo al formulario
     */
    private void agregarCampo() {
        //Se agrega el valor al formulario
        String nombre = JOptionPane.showInputDialog(this, "Favor ingresar el nombre del campo a agregar", "", JOptionPane.QUESTION_MESSAGE);
        agregarRotulo(nombre);
        nombre = "v. " + nombre;
        //En este switch se debe crear una instancia del nuevo tipo campo a agregar al formulario
        switch (comboTipos.getSelectedIndex()) {
            case 1:
                //Numero
                //Agrega todos los otros datos por defecto
                IDEnUso = miFormulario.agregarMiembro(nombre, 10, 1, 100, 20, "Arial", Color.BLACK.getRGB(), 12, 1, tabIndex++, "Plain");
                JTextField texto = agregarTipoNumero(nombre, IDEnUso);
                break;
            case 2:
                //Binario
                //agrega el componente 1:
                String temp = valorNombreBinario1.getText();
                //Agrega todos los otros datos por defecto
                IDEnUso = miFormulario.agregarMiembro(nombre+" " +temp, 10, 1, 100, 20, "Arial", Color.BLACK.getRGB(), 12, 2, tabIndex++, "Plain");
                agregarTipoBinario(temp, IDEnUso);
                
                //agrega el componente 2:
                temp = valorNombreBinario2.getText();
                IDEnUso = miFormulario.agregarMiembro(nombre+" " +temp, 10, 1, 100, 20, "Arial", Color.BLACK.getRGB(), 12, 2, tabIndex++, "Plain");
                agregarTipoBinario(temp, IDEnUso);
                break;
            case 3:
                //Agrega todos los otros datos por defecto
                IDEnUso = miFormulario.agregarMiembro(nombre, 10, 1, 100, 20, "Arial", Color.BLACK.getRGB(), 12, 1, tabIndex++, "Plain");
                //FechaHora
                agregarTipoFechaHora(nombre, IDEnUso);
                break;
            case 4:
                //Texto
                //Agrega todos los otros datos por defecto
                IDEnUso = miFormulario.agregarMiembro(nombre, 10, 1, 100, 20, "Arial", Color.BLACK.getRGB(), 12, 1, tabIndex++, "Plain");
                agregarTipoTexto(nombre, IDEnUso);
                break;
            case 5:
                //Incremental
                //Agrega todos los otros datos por defecto
                IDEnUso = miFormulario.agregarMiembro(nombre, 10, 1, 100, 20, "Arial", Color.BLACK.getRGB(), 12, 1, tabIndex++, "Plain");
                agregarTipoIncremental(nombre, IDEnUso);
                break;
            case 6:
                //Jerarquia
                //Agrega todos los otros datos por defecto
                IDEnUso = miFormulario.agregarMiembro(nombre, 10, 1, 100, 20, "Arial", Color.BLACK.getRGB(), 12, 1, tabIndex++, "Plain");
                agregarTipoJerarquia(nombre, IDEnUso);
                break;
            case 7:
                //Lista
                //Agrega todos los otros datos por defecto
                IDEnUso = miFormulario.agregarMiembro(nombre, 10, 1, 100, 20, "Arial", Color.BLACK.getRGB(), 12, 1, tabIndex++, "Plain");
                agregarTipoLista(nombre, IDEnUso);
                break;
            default:
                break;
        }
        //Se abre el pane de formulario
        llenarDatosMiembro(IDEnUso);
    }

    /**
     * Llena los datos del componente para que el usuaro pueda modificarlos
     * @param ID ID del componente a mostrar los datos
     */
    private void llenarDatosMiembro(int ID) {
        ocultarPanes();
        paneFormulario.setVisible(true);
        MiembroFormulario temp = miFormulario.getMiembro(ID);
        //osea q es de tipo etiqueta, o binario
        if (temp.getIDTipoCampo() == 0 || temp.getIDTipoCampo() == 2) {
            textoDato.setText(temp.getNombre());
        } else {
            textoDato.setText("");
        }
        colorDato.setForeground(new Color(temp.getColor()));
        valEjeX.setText("" + temp.getValX());
        valEjeY.setText("" + temp.getValY());
        tamanoLetra.setText("" + temp.getTamanoLetra());
        //comboTipoLetra.addItem(temp.getTipoLetra());
        comboTipoLetra.setSelectedItem(temp.getTipoLetra());
        comboEstiloLetra.setSelectedItem(temp.getEstiloLetra());
    }

    /**
     * Agrega al formulario un campo nuevo tipo numero
     * @param nombre
     */
    private JLabel agregarEtiqueta(String texto, int ID) {
        JLabel etq = new JLabel(texto);
        etq.setName("" + ID);
        etq.addMouseListener(listener);
        etq.addMouseMotionListener(motionListener);
        frameVistaPrevia.add(etq);
        etq.setBounds(1, 1, 100, 20);
        etq.setFont(Font.decode("Arial-Plain-12"));
        compEnUso = etq;
        return etq;
    }

    /**
     * Agrega al formulario un campo nuevo tipo numero
     * @param nombre
     */
    private JTextField agregarTipoNumero(String nombre, int ID) {
        //Esto es temporal!! se debe crear un objeto de numero...
        JTextField jtf = new JTextField(10);
        jtf.setName("" + ID);
        jtf.addMouseListener(listener);
        jtf.addMouseMotionListener(motionListener);
        frameVistaPrevia.add(jtf);
        jtf.setBounds(10, 1, 100, 20);
        jtf.setFont(Font.decode("Arial-Plain-12"));
        compEnUso = jtf;
        return jtf;
    }

    /**
     * Agrega al formulario el componente uno del tipo binario
     * @param nombre
     */
    private JRadioButton agregarTipoBinario(String nombre, int ID) {
        //Esto es temporal!! se debe crear un objeto de binario...
        JRadioButton r1 = new JRadioButton(nombre);
        r1.setName("" + ID);
        r1.addMouseListener(listener);
        r1.addMouseMotionListener(motionListener);
        frameVistaPrevia.add(r1);
        r1.setBounds(10, 1, 100, 20);
        r1.setFont(Font.decode("Arial-Plain-12"));
        compEnUso = r1;
        return r1;
    }

    /**
     * Agrega al formulario un campo nuevo tipo Fecha Hora
     * @param nombre
     */
    private void agregarTipoFechaHora(String nombre, int ID) {

        //Esto es temporal!! se debe crear un objeto de fechahora...
        JTextField jtf = new JTextField(15);
        jtf.setName("" + ID);
        jtf.addMouseListener(listener);
        jtf.addMouseMotionListener(motionListener);
        frameVistaPrevia.add(jtf);
        jtf.setBounds(10, 1, 100, 20);
        jtf.setFont(Font.decode("Arial-Plain-12"));
        compEnUso = jtf;
    }

    /**
     * Agrega al formulario un campo nuevo tipo Texto
     * @param nombre
     */
    private void agregarTipoTexto(String nombre, int ID) {
        //Esto es temporal!! se debe crear un objeto de texto...
        JTextField jtf = new JTextField(15);
        jtf.setName("" + ID);
        jtf.addMouseListener(listener);
        jtf.addMouseMotionListener(motionListener);
        frameVistaPrevia.add(jtf);
        jtf.setBounds(10, 1, 100, 20);
        jtf.setFont(Font.decode("Arial-Plain-12"));
        compEnUso = jtf;
    }

    /**
     * Agrega al formulario un campo nuevo tipo incremental
     * @param nombre
     */
    private void agregarTipoIncremental(String nombre, int ID) {
        //Esto es temporal!! se debe crear un objeto de incremental...
        JTextField jtf = new JTextField(15);
        jtf.setName("" + ID);
        jtf.addMouseListener(listener);
        jtf.addMouseMotionListener(motionListener);
        frameVistaPrevia.add(jtf);
        jtf.setBounds(10, 1, 100, 20);
        jtf.setFont(Font.decode("Arial-Plain-12"));
        compEnUso = jtf;
    }

    /**
     * Agrega al formulario un campo nuevo tipo Jerarquia
     * @param nombre
     */
    private void agregarTipoJerarquia(String nombre, int ID) {
        //Esto es temporal!! se debe crear un objeto de Jerarquia...
        JTextField jtf = new JTextField(15);
        jtf.setName("" + ID);
        jtf.addMouseListener(listener);
        jtf.addMouseMotionListener(motionListener);
        frameVistaPrevia.add(jtf);
        jtf.setBounds(100, 1, 100, 20);
        jtf.setFont(Font.decode("Arial-Plain-12"));
        compEnUso = jtf;
    }

    /**
     * Agrega al formulario un campo nuevo tipo Lista
     * @param nombre
     */
    private void agregarTipoLista(String nombre, int ID) {
        //Esto es temporal!! se debe crear un objeto de Lista...
        JTextField jtf = new JTextField(15);
        jtf.setName("" + ID);
        jtf.addMouseListener(listener);
        jtf.addMouseMotionListener(motionListener);
        frameVistaPrevia.add(jtf);
        jtf.setBounds(10, 1, 100, 20);
        jtf.setFont(Font.decode("Arial-Plain-12"));
        compEnUso = jtf;
    }

    private void botonGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonGuardarActionPerformed
        MiembroFormulario tmp = miFormulario.getMiembro(IDEnUso);
        String texto = textoDato.getText();
        //Si no es etiqueta, ni binario
        if (tmp.getIDTipoCampo() != 0 && tmp.getIDTipoCampo() != 2) {
            texto = "";
        }
        //Se guardan los datos en la instancia del Formulario, al igual que en la BD
        miFormulario.upDateValoresMiembro(IDEnUso, texto, Integer.parseInt(valEjeX.getText()), Integer.parseInt(valEjeY.getText()), compEnUso.getWidth(), compEnUso.getHeight(), comboTipoLetra.getSelectedItem().toString(), colorDato.getForeground().getRGB(), Integer.parseInt(tamanoLetra.getText()), comboEstiloLetra.getSelectedItem().toString());
        //Se actualiza el componente en el preview
        actualizarComponente(textoDato.getText(), Integer.parseInt(valEjeX.getText()), Integer.parseInt(valEjeY.getText()), comboTipoLetra.getSelectedItem().toString(), colorDato.getForeground().getRGB(), Integer.parseInt(tamanoLetra.getText()), compEnUso.getWidth(), compEnUso.getHeight(), comboEstiloLetra.getSelectedItem().toString());
        //desp de guardar se ocultan los panes
        ocultarPanes();
    }//GEN-LAST:event_botonGuardarActionPerformed

    /**
     * Actualiza el componente en el preview
     * @param etiq
     * @param valX
     * @param valY
     * @param tipoLetra
     * @param color
     * @param tamanoLetra
     */
    private void actualizarComponente(String texto, int valX, int valY, String tipoLetra, int color, int tamanoLetra, int ancho, int alto, String estiloLetra) {
        //hay q cambiar el tamaño!!!!!!!!!!!!!!!!!!1
        String componente = "" + compEnUso;
        //Si es una etiqueta:
        if (componente.contains("javax.swing.JLabel")) {
            ((JLabel) (compEnUso)).setText(texto);
        } else {
            //Si es un radio
            if (componente.contains("javax.swing.JRadioButton")) {
                ((JRadioButton) (compEnUso)).setText(texto);
            } else {
                //si es un combo
                if (componente.contains("javax.swing.JComboBox")) {
                    //hay q separar los valores...
                    //((JComboBox)(compEnUso))
                }
            }
        }
        compEnUso.setBounds(valX, valY, ancho, alto);
        compEnUso.setFont(Font.decode(tipoLetra + ' ' + estiloLetra + ' ' + tamanoLetra));
        //cambia color
        compEnUso.setForeground(new Color(color));
    }

    private void bottonColorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bottonColorActionPerformed
        Color color = Color.BLACK;
        color = JColorChooser.showDialog(bottonColor, "Seleccione un color", color);
        // establecer color predeterminado, si no se devuelve un color
        if (color == null) {
            color = Color.BLACK;
        }
        // cambiar color de la letra del campo de texto
        colorDato.setForeground(color);
    }//GEN-LAST:event_bottonColorActionPerformed

    private void botonAgregarEtqActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonAgregarEtqActionPerformed
        String texto = JOptionPane.showInputDialog(this, "Favor ingresar el texto para la etiqueta", "", JOptionPane.QUESTION_MESSAGE);
        agregarRotulo(texto);
    }//GEN-LAST:event_botonAgregarEtqActionPerformed

    /**
     * Agrega un rotulo/etiqueta nueva al formulario
     * Llama al metodo respectivo para guardar en la BD tamb
     */
    private void agregarRotulo(String texto) {
        //Como es etiqueta uso tabIndex -1
        IDEnUso = miFormulario.agregarMiembro(texto, 1, 1, 100, 20, "Arial", Color.BLACK.getRGB(), 12, 0, -1, "Plain");
        agregarEtiqueta(texto, IDEnUso);
        llenarDatosMiembro(IDEnUso);
    }

    private void botonBorrarCompActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonBorrarCompActionPerformed
        // TODO add your handling code here:
        //Se eliminan los datos en la instancia del Formulario, al igual que en la BD
        miFormulario.eliminarMiembro(IDEnUso);
        //Se elimina el componente del preview
        compEnUso.setVisible(false);
        //desp se ocultan los panes
        ocultarPanes();
    }//GEN-LAST:event_botonBorrarCompActionPerformed

    private void botonMasAnchoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonMasAnchoActionPerformed
        // TODO add your handling code here:
        compEnUso.setSize(compEnUso.getWidth() + 3, compEnUso.getHeight());
    //hay q actualizar la instancia del componente!!!
    //miFormulario
    }//GEN-LAST:event_botonMasAnchoActionPerformed

    private void botonMasAltoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonMasAltoActionPerformed
        // TODO add your handling code here:
        compEnUso.setSize(compEnUso.getWidth(), compEnUso.getHeight() + 2);
    //hay q actualizar la instancia del componente!!!
    //miFormulario
    }//GEN-LAST:event_botonMasAltoActionPerformed

    private void botonMenosAnchoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonMenosAnchoActionPerformed
        // TODO add your handling code here:
        compEnUso.setSize(compEnUso.getWidth() - 3, compEnUso.getHeight());
    //hay q actualizar la instancia del componente!!!
    //miFormulario
    }//GEN-LAST:event_botonMenosAnchoActionPerformed

    private void botonMenosAltoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonMenosAltoActionPerformed
        // TODO add your handling code here:
        compEnUso.setSize(compEnUso.getWidth(), compEnUso.getHeight() - 2);
    //hay q actualizar la instancia del componente!!!
    //miFormulario
    }//GEN-LAST:event_botonMenosAltoActionPerformed

    private void botonTabIndexActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonTabIndexActionPerformed
        escogerTabIndex();
    }//GEN-LAST:event_botonTabIndexActionPerformed

    private void botonActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonActualizarActionPerformed
        //Vuelve a llenar el tree view
        llenarTreeView();
    }//GEN-LAST:event_botonActualizarActionPerformed

    /**
     * Abre la ventana donde se muestran los componentes del formulario para ordenar el tab index
     */
    private void escogerTabIndex() {
        SortedSet miembros = miFormulario.getMiembroFormularioSet();
        String[] valores = new String[miembros.size()];
        Iterator iterador = miembros.iterator();
        int k = 0;
        for (int i = 0; i < miembros.size(); i++) {
            MiembroFormulario miembro = (MiembroFormulario) iterador.next();
            int ID = miembro.getID();
            String nombre = miembro.getNombre();
            int IDTipoCampo = miembro.getIDTipoCampo();
            //Mientras no sea una etiqueta
            if(IDTipoCampo != 0){
                valores[k] = ID+"      " + nombre;
                ++k;
            }
        }
        numCampos = k;
        frameTabIndex frameTI = new frameTabIndex(this, valores);
        frameTI.setLocationRelativeTo(GestionTipoCampoApp.getApplication().getMainFrame());
        frameTI.setVisible(true);
    }

    /**
     * Abre la ventana donde se muestran los componentes del formulario para ordenar el tab index
     */
    public void cambioTabIndex(String[] valores) {
        for (int i = 0; i < numCampos; i++) {
            String id = (valores[i].split(" "))[0];
            int ID = Integer.parseInt(id);
            miFormulario.updateTabIndex(ID, i);
        }
        actualizarTabIndex(valores);
    }

    public void actualizarTabIndex(String[] valores){
        Object[] componentes = frameVistaPrevia.getComponents();
        String idAnt = (valores[0].split(" "))[0];
        for (int i = 1; i < numCampos; i++) {
            String id = (valores[i].split(" "))[0];

            //busca entre todos los componentes, se hace -1 porq el ultimo no tiene N.F.C.
            for(int k = 0; k < componentes.length-1; ++k){
                JComponent cmp = (JComponent)componentes[k];
                if( cmp.getName().equalsIgnoreCase(id) ){
                    System.out.println("sig: "+id);
                    for(int j = 0; j < componentes.length; ++j){
                        JComponent cmp2 = (JComponent)componentes[j];
                        if( cmp2.getName().equalsIgnoreCase(idAnt) ){
                            System.out.println("ant: "+idAnt);
                            cmp2.setNextFocusableComponent(cmp);
                        }
                    }
                }
            }
            idAnt = id;
        }
    }

    /**
     * Llena el tree view
     */
    public void llenarTreeView() {
        //Llena los valores del Tree View
        String[] tiposCampo = {"Numero", "Binario", "FechaHora", "Texto", "Incremental", "Jerarquia", "Lista"};
        String valores;
        String[] valTrim;
        DefaultMutableTreeNode nodoTipoCampo;
        DefaultMutableTreeNode nodoTemp;
        DefaultMutableTreeNode raizArbol = new DefaultMutableTreeNode("Gift");

        for (int k = 0; k < tiposCampo.length; k++) {
            nodoTipoCampo = new DefaultMutableTreeNode(tiposCampo[k]);
            //Se llenan los datos con los valores que contenga la base de datos con el tipo campo:
            valores = buscarNombreEnBD(k + 1);
            // System.out.println("valores: " + valores);
            valTrim = valores.split("\n");
            for (int i = 0; i < valTrim.length; ++i) {
                nodoTemp = new DefaultMutableTreeNode(valTrim[i]);
                nodoTipoCampo.add(nodoTemp); //agrega el nodo
            }
            raizArbol.add(nodoTipoCampo);
        }
        JTree arbolnuevo = new JTree(raizArbol);
        arbolPrincipal.setModel(arbolnuevo.getModel());
    }

    /**
     * Encargado de buscar el valor del nombre en la base de datos
     * @param num: Indica el tipo de campo que va a buscar
     */
    public String buscarNombreEnBD(int num) {
        ControladorBD buscador = new ControladorBD();
        String valores = "";
        try {
            ResultSet resultado = buscador.getResultSet("select * from TIPOCAMPO where tipo = '" + num + "';");
            if (resultado != null) {
                while (resultado.next()) {
                    valores += resultado.getObject(2).toString() + "\n";
                }
            }
        } catch (SQLException e) {
            System.out.println("*SQL Exception: *" + e.toString());
        }
        return valores;
    }

    /**
     * Muestra los panes necesarios segun lo que se vaya a mostrar
     */
    public void abrirNodoHoja() {

        DefaultMutableTreeNode node = (DefaultMutableTreeNode) arbolPrincipal.getLastSelectedPathComponent();
        if (node != null) {
            String nombre = node.toString();
            String papa = node.getParent() != null ? node.getParent().toString() : "";
            ocultarPanes();
            paneDatos.setVisible(true);
            panePrincipal.setVisible(true);
            if (papa.equals("Numero")) {
                paneNumero.setVisible(true);
                llenarDatosReadOnly(nombre, 1);
            } else {
                if (papa.equals("Binario")) {
                    paneBinario.setVisible(true);
                    llenarDatosReadOnly(nombre, 2);
                } else {
                    if (papa.equals("FechaHora")) {
                        paneFechaHora.setVisible(true);
                        llenarDatosReadOnly(nombre, 3);
                    } else {
                        if (papa.equals("Texto")) {
                            paneTexto.setVisible(true);
                            llenarDatosReadOnly(nombre, 4);
                        } else {
                            if (papa.equals("Incremental")) {
                                paneIncremental.setVisible(true);
                                llenarDatosReadOnly(nombre, 5);
                            } else {
                                if (papa.equals("Jerarquia")) {
                                    paneJerarquia.setVisible(true);
                                    llenarDatosReadOnly(nombre, 6);
                                } else {
                                    if (papa.equals("Lista")) {
                                        paneLista.setVisible(true);
                                        llenarDatosReadOnly(nombre, 7);
                                    } else {
                                        ocultarPanes();
                                    //JOptionPane.showMessageDialog(null, "¡Favor seleccionar un dato hoja!", "", JOptionPane.ERROR_MESSAGE);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     * Oculta todos los panes
     */
    public void ocultarPanes() {
        panePrincipal.setVisible(false);
        paneBinario.setVisible(false);
        paneFechaHora.setVisible(false);
        paneTexto.setVisible(false);
        paneIncremental.setVisible(false);
        paneNumero.setVisible(false);
        paneJerarquia.setVisible(false);
        paneLista.setVisible(false);
        paneFormulario.setVisible(false);
    }

    /**
     * Llena los datos en read-only
     * @param nombre
     * @param tipo
     */
    public void llenarDatosReadOnly(String nombre, int tipo) {
        //Trae todos los valores de la tabla TIPOCAMPO
        String valoresGlobales = buscarDatosEnBD(nombre);
        String[] valoresSeparados = valoresGlobales.split(";");
        String valoresEspecificos = buscarPorTipoEnBD(Integer.parseInt(valoresSeparados[0]), tipo);
        String[] valoresEspSep = valoresEspecificos.split(";");
        comboTipos.setSelectedIndex(tipo);
        valorNombreGeneral.setText(valoresSeparados[1]);
        valorNota.setText(valoresSeparados[2]);
        switch (tipo) {
            //numero
            case (1):
                valorNumDecimales.setText(valoresEspSep[1]);
                valorNumeroMascara.setText(valoresEspSep[2]);
                valorValorDefectoNumero.setText(valoresEspSep[3]);
                break;
            //binario
            case (2):
                valorNombreBinario1.setText(valoresEspSep[1]);
                valorOpcionBinaria1.setText(valoresEspSep[2]);
                valorNombreBinario2.setText(valoresEspSep[3]);
                valorOpcionBinaria2.setText(valoresEspSep[4]);
                if (valoresEspSep[5].equals("true")) {
                    radioOpcionBinaria1.setSelected(true);
                    radioOpcionBinaria2.setSelected(false);
                } else {
                    radioOpcionBinaria1.setSelected(false);
                    radioOpcionBinaria2.setSelected(true);
                }
                break;
            //FechaHora
            case (3):
                //comboFormatoFecha.setSelectedItem(valoresEspSep[1]);
                valorFechaDefecto.setText(valoresEspSep[2]);
                if (valoresEspSep[3].equals("true")) {
                    radioFechaHoraSi.setSelected(true);
                    radioFechaHoraNo.setSelected(false);
                } else {
                    radioFechaHoraSi.setSelected(false);
                    radioFechaHoraNo.setSelected(true);
                }
                valorPreaviso.setText(valoresEspSep[4]);
                break;
            //Texto
            case (4):
                valorTextoDefecto.setText(valoresEspSep[1]);
                valorTextoLargo.setText(valoresEspSep[2]);
                break;
            //Incremental
            case (5):
                valorValorInicial.setText(valoresEspSep[1]);
                valorIncremento.setText(valoresEspSep[2]);
                break;
            //Jerarquia
            case (6):
                if (valoresEspSep[3].equalsIgnoreCase("true")) {
                    this.radioNomUnicoSi.setSelected(false);
                    this.radioNomUnicoNo.setSelected(true);
                } else {
                    this.radioNomUnicoSi.setSelected(true);
                    this.radioNomUnicoNo.setSelected(false);
                }
                this.valorNumTerminos.setText(valoresEspSep[4]);
                this.valorNumNiveles.setText(valoresEspSep[5]);
                if (valoresEspSep[8].equalsIgnoreCase("true")) {
                    this.radioCategoriasSi.setSelected(true);
                    this.radioCategoriasNo.setSelected(false);
                } else {
                    this.radioCategoriasSi.setSelected(false);
                    this.radioCategoriasNo.setSelected(true);
                }
                if (valoresEspSep[9].equalsIgnoreCase("true")) {
                    this.radioNivelesSi.setSelected(true);
                    this.radioNivelesNo.setSelected(false);
                } else {
                    this.radioNivelesSi.setSelected(false);
                    this.radioNivelesNo.setSelected(true);
                }
                //this.comboCategorias.setSelectedIndex(Integer.parseInt(valoresEspSep[10]));
                break;
            //Lista
            case (7):
                valoresEspSep[1] = buscarMiembroLista(Integer.parseInt(valoresEspSep[1]));
                this.valorPorDefectoLista.setText(valoresEspSep[1].trim());
                break;

            default:
                break;
        }
    }

    /**
     * Encargado de buscar los valores TIPOCAMPO segun el nombre en la base de datos
     * @param nombre: Indica el nombre del tipo de campo que va a buscar
     */
    public String buscarDatosEnBD(String nombre) {
        ControladorBD buscador = new ControladorBD();
        String valores = "";
        try {
            ResultSet resultado = buscador.getResultSet("select * from TIPOCAMPO where nombre = '" + nombre + "';");
            if (resultado.next()) {
                valores += resultado.getObject(1).toString() + ";";
                valores += resultado.getObject(2).toString() + ";";
                valores += resultado.getObject(3).toString() + ";";
                valores += resultado.getObject(4).toString() + ";";
                valores += resultado.getObject(5).toString() + ";";
            }
        } catch (SQLException e) {
            System.out.println("*SQL Exception: *" + e.toString());
        }
        return valores;
    }

    /**
     * Encargado de buscar los valores TIPOCAMPO segun el nombre en la base de datos
     * @param nombre: Indica el nombre del tipo de campo que va a buscar
     */
    public String getIDTipoCampo(String nombre) {
        ControladorBD buscador = new ControladorBD();
        String valores = "";
        try {
            ResultSet resultado = buscador.getResultSet("select correlativo from TIPOCAMPO where nombre = '" + nombre + "';");
            if (resultado.next()) {
                valores += resultado.getObject("correlativo").toString();
            }
        } catch (SQLException e) {
            System.out.println("*SQL Exception: *" + e.toString());
        }
        return valores;
    }

    /**
     * Encargado de buscar el valor del nombre en la base de datos
     * @param ID: Indica el ID del campo que va a buscar
     * @param tipo: Indica el tipo de campo que va a buscar
     */
    public String buscarPorTipoEnBD(int ID, int tipo) {
        ControladorBD buscador = new ControladorBD();
        String[] tiposCampo = {"NUMERO", "BINARIO", "FECHAHORA", "TEXTO", "INCREMENTAL", "JERARQUIA", "LISTA"};
        String valores = "";
        try {
            ResultSet resultado = buscador.getResultSet("select * from " + tiposCampo[tipo - 1] + " where correlativo = " + ID + ";");
            if (resultado.next()) {
                for (int i = 1; i <= resultado.getMetaData().getColumnCount(); ++i) {
                    valores += resultado.getObject(i) != null ? resultado.getObject(i).toString() + ";" : ";";
                }
            }
        } catch (SQLException e) {
            System.out.println("*SQL Exception: *" + e.toString());
        }
        return valores;
    }

    /**
     * Encargado de buscar el valor del nombre en la base de datos
     * @param ID: Indica el ID del campo que va a buscar
     * @param tipo: Indica el tipo de campo que va a buscar
     */
    public String buscarMiembroLista(int ID) {
        ControladorBD buscador = new ControladorBD();
        String valores = "";
        try {
            ResultSet resultado = buscador.getResultSet("select valor from MIEMBROLISTA where correlativo = " + ID + ";");
            if (resultado.next()) {
                valores += resultado.getObject("valor").toString();
            }
        } catch (SQLException e) {
            System.out.println("*SQL Exception: *" + e.toString());
        }
        return valores;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new frameFormulario().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTree arbolPrincipal;
    private javax.swing.JButton botonActualizar;
    private javax.swing.JButton botonAgregar;
    private javax.swing.JButton botonAgregarEtq;
    private javax.swing.JButton botonBorrarComp;
    private javax.swing.JButton botonGuardar;
    private javax.swing.JButton botonMasAlto;
    private javax.swing.JButton botonMasAncho;
    private javax.swing.JButton botonMenosAlto;
    private javax.swing.JButton botonMenosAncho;
    private javax.swing.JButton botonTabIndex;
    private javax.swing.JButton bottonColor;
    private javax.swing.JTextField colorDato;
    private javax.swing.JComboBox comboCategorias;
    private javax.swing.JComboBox comboEstiloLetra;
    private javax.swing.JComboBox comboFormatoFecha;
    private javax.swing.JComboBox comboNiveles;
    private javax.swing.JComboBox comboTipoLetra;
    private javax.swing.JComboBox comboTipos;
    private javax.swing.JInternalFrame frameVistaPrevia;
    private javax.swing.JButton jButton3;
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
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel labelCategorias;
    private javax.swing.JLayeredPane paneBinario;
    private javax.swing.JLayeredPane paneDatos;
    private javax.swing.JLayeredPane paneFechaHora;
    private javax.swing.JLayeredPane paneFormulario;
    private javax.swing.JLayeredPane paneIncremental;
    private javax.swing.JLayeredPane paneJerarquia;
    private javax.swing.JLayeredPane paneLista;
    private javax.swing.JLayeredPane paneNumero;
    private javax.swing.JLayeredPane panePrincipal;
    private javax.swing.JLayeredPane paneTexto;
    private javax.swing.JRadioButton radioCategoriasNo;
    private javax.swing.JRadioButton radioCategoriasSi;
    private javax.swing.JRadioButton radioFechaHoraNo;
    private javax.swing.JRadioButton radioFechaHoraSi;
    private javax.swing.JRadioButton radioNivelesNo;
    private javax.swing.JRadioButton radioNivelesSi;
    private javax.swing.JRadioButton radioNomUnicoNo;
    private javax.swing.JRadioButton radioNomUnicoSi;
    private javax.swing.JRadioButton radioOpcionBinaria1;
    private javax.swing.JRadioButton radioOpcionBinaria2;
    private javax.swing.JTextField tamanoLetra;
    private javax.swing.JTextField textoDato;
    private javax.swing.JTextField valEjeX;
    private javax.swing.JTextField valEjeY;
    private javax.swing.JFormattedTextField valorFechaDefecto;
    private javax.swing.JTextField valorIncremento;
    private javax.swing.JTextField valorNombreBinario1;
    private javax.swing.JTextField valorNombreBinario2;
    private javax.swing.JTextField valorNombreGeneral;
    private javax.swing.JTextField valorNota;
    private javax.swing.JTextField valorNumDecimales;
    private javax.swing.JTextField valorNumNiveles;
    private javax.swing.JTextField valorNumTerminos;
    private javax.swing.JTextField valorNumeroMascara;
    private javax.swing.JTextField valorOpcionBinaria1;
    private javax.swing.JTextField valorOpcionBinaria2;
    private javax.swing.JTextField valorPorDefectoLista;
    private javax.swing.JTextField valorPreaviso;
    private javax.swing.JTextField valorTextoDefecto;
    private javax.swing.JTextField valorTextoLargo;
    private javax.swing.JTextField valorValorDefectoNumero;
    private javax.swing.JTextField valorValorInicial;
    // End of variables declaration//GEN-END:variables
    public MouseListener listener;
    public MouseMotionListener motionListener;
    public float maxX;
    public float maxY;
    public float startX;
    public float startY;
    public Formulario miFormulario;
    public int IDEnUso;
    public JComponent compEnUso;
    public int tabIndex;
    private int numCampos;
}
