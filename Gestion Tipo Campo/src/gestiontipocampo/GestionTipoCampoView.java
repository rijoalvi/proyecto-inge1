/*
 * GestionTipoCampoView.java
 */
package gestiontipocampo;

import java.awt.event.FocusEvent;
import java.util.Vector;
import javax.swing.JButton;
import org.jdesktop.application.Action;
import org.jdesktop.application.ResourceMap;
import org.jdesktop.application.SingleFrameApplication;
import org.jdesktop.application.FrameView;
import org.jdesktop.application.TaskMonitor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;
import javax.swing.Icon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import java.sql.*;
import javax.swing.tree.*;
import javax.swing.JTree;
import javax.swing.JOptionPane;


import javax.swing.*;

/**
 * The application's main frame.
 */
public class GestionTipoCampoView extends FrameView {

    public GestionTipoCampoView(SingleFrameApplication app) throws InterruptedException {
        super(app);

        initComponents();

        //Se esconden todos los datos de panel para abrir datos...
        ocultarPanes();

        //Inicializa el arbol para obtener nodo seleccionado
        arbolPrincipal.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);

        // status bar initialization - message timeout, idle icon and busy animation, etc
        ResourceMap resourceMap = getResourceMap();
        int messageTimeout = resourceMap.getInteger("StatusBar.messageTimeout");
        messageTimer = new Timer(messageTimeout, new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                statusMessageLabel.setText("");
            }
        });
        messageTimer.setRepeats(false);
        int busyAnimationRate = resourceMap.getInteger("StatusBar.busyAnimationRate");
        for (int i = 0; i < busyIcons.length; i++) {
            busyIcons[i] = resourceMap.getIcon("StatusBar.busyIcons[" + i + "]");
        }
        busyIconTimer = new Timer(busyAnimationRate, new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                busyIconIndex = (busyIconIndex + 1) % busyIcons.length;
                statusAnimationLabel.setIcon(busyIcons[busyIconIndex]);
            }
        });
        idleIcon = resourceMap.getIcon("StatusBar.idleIcon");
        statusAnimationLabel.setIcon(idleIcon);
        progressBar.setVisible(false);

        // connecting action tasks to status bar via TaskMonitor
        TaskMonitor taskMonitor = new TaskMonitor(getApplication().getContext());
        taskMonitor.addPropertyChangeListener(new java.beans.PropertyChangeListener() {

        public void propertyChange(java.beans.PropertyChangeEvent evt) {
            String propertyName = evt.getPropertyName();
            if ("started".equals(propertyName)) {
                if (!busyIconTimer.isRunning()) {
                    statusAnimationLabel.setIcon(busyIcons[0]);
                    busyIconIndex = 0;
                    busyIconTimer.start();
                }
                progressBar.setVisible(true);
                progressBar.setIndeterminate(true);
            } else if ("done".equals(propertyName)) {
                busyIconTimer.stop();
                statusAnimationLabel.setIcon(idleIcon);
                progressBar.setVisible(false);
                progressBar.setValue(0);
            } else if ("message".equals(propertyName)) {
                String text = (String) (evt.getNewValue());
                statusMessageLabel.setText((text == null) ? "" : text);
                messageTimer.restart();
            } else if ("progress".equals(propertyName)) {
                int value = (Integer) (evt.getNewValue());
                progressBar.setVisible(true);
                progressBar.setIndeterminate(false);
                progressBar.setValue(value);
            }
        }
    });

        

        frameConexiones ventanaConexiones = new frameConexiones(this);
        ventanaConexiones.setAlwaysOnTop(true);               

        //JFrame mainFrame = frameManejoCampos.getApplication().getMainFrame();
        //coloca el frame segun como este ubicada la ventana principal
        // ventanaBusqueda.setLocationRelativeTo(mainFrame);
        ventanaConexiones.setVisible(true);
        ventanaConexiones.setLocationRelativeTo(null);
        
        //ventanaConexiones.setLocationRelativeTo();
    }

    @Action
    public void showAboutBox() {
        if (aboutBox == null) {
            JFrame mainFrame = GestionTipoCampoApp.getApplication().getMainFrame();
            aboutBox = new GestionTipoCampoAboutBox(mainFrame);
            aboutBox.setLocationRelativeTo(mainFrame);
        }
        GestionTipoCampoApp.getApplication().show(aboutBox);
    }

    @Action
    public void mostrarVentanaManejoCampos() {
        frameManejoCampos ventanaManejoCampos = new frameManejoCampos("nuevo");
        JFrame mainFrame = GestionTipoCampoApp.getApplication().getMainFrame();
        //coloca el frame segun como este ubicada la ventana principal
        ventanaManejoCampos.setLocationRelativeTo(mainFrame);
        ventanaManejoCampos.setVisible(true);
    }

    public void llenarTreeView(){
        //Llena los valores del Tree View
        String [] tiposCampo = {"Numero", "Binario", "FechaHora", "Texto", "Incremental", "Jerarquia", "Lista"};
        String valores;
        String [] valTrim;
        DefaultMutableTreeNode nodoTipoCampo;
        DefaultMutableTreeNode nodoTemp;
        DefaultMutableTreeNode raizArbol = new DefaultMutableTreeNode("Gift");    
        
        for(int k = 0; k < tiposCampo.length; k++){
            nodoTipoCampo = new DefaultMutableTreeNode(tiposCampo[k]);
            //Se llenan los datos con los valores que contenga la base de datos con el tipo campo:
            valores = buscarNombreEnBD(k+1);
           // System.out.println("valores: " + valores);
            valTrim = valores.split("\n");
            for(int i= 0; i < valTrim.length; ++i){
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
    public String buscarNombreEnBD(int num){
        ControladorBD buscador = new ControladorBD();
        String valores = "";
        try {
            ResultSet resultado = buscador.getResultSet("select * from TIPOCAMPO where tipo = '" + num + "';");
            if(resultado != null)
                while (resultado.next()) {
                    valores += resultado.getObject(2).toString()+"\n";
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
    public String buscarDatosEnBD(String nombre){
        ControladorBD buscador = new ControladorBD();
        String valores = "";
        try {
            ResultSet resultado = buscador.getResultSet("select * from TIPOCAMPO where nombre = '" + nombre + "';");           
            if(resultado.next()){
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
    public String getIDTipoCampo(String nombre){
        ControladorBD buscador = new ControladorBD();
        String valores = "";
        try {
            ResultSet resultado = buscador.getResultSet("select correlativo from TIPOCAMPO where nombre = '" + nombre + "';");
            if(resultado.next()){
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
    public String buscarPorTipoEnBD(int ID, int tipo){
        ControladorBD buscador = new ControladorBD();
        String [] tiposCampo = {"NUMERO", "BINARIO", "FECHAHORA", "TEXTO", "INCREMENTAL", "JERARQUIA", "LISTA"};
        String valores = "";
        try {
            ResultSet resultado = buscador.getResultSet("select * from " + tiposCampo[tipo-1] + " where correlativo = '" + Integer.toString(ID) + "';");
            if(resultado.next()){
                for(int i = 1; i<=resultado.getMetaData().getColumnCount()/*resultado.getObject(i) != null*/; ++i){
                    valores += resultado.getObject(i)!=null?resultado.getObject(i).toString() + ";":";";
                   // System.out.println("val: "+ valores);
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
    public String buscarMiembroLista(int ID){
        ControladorBD buscador = new ControladorBD();
        String valores = "";
        try {
            ResultSet resultado = buscador.getResultSet("select valor from MIEMBROLISTA where correlativo = " + ID + ";");
            if(resultado.next()){
                valores += resultado.getObject("valor").toString();
            }
        } catch (SQLException e) {
            System.out.println("*SQL Exception: *" + e.toString());
        }
        return valores;
    }


    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mainPanel = new javax.swing.JPanel();
        canvas1 = new java.awt.Canvas();
        canvas2 = new java.awt.Canvas();
        canvas3 = new java.awt.Canvas();
        canvas4 = new java.awt.Canvas();
        jPanel1 = new javax.swing.JPanel();
        botonActualizar = new javax.swing.JButton();
        paneDatosAbrir = new javax.swing.JLayeredPane();
        panePrincipal = new javax.swing.JLayeredPane();
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
        botonAgregarCategoria = new javax.swing.JButton();
        radioNivelesSi = new javax.swing.JRadioButton();
        radioNivelesNo = new javax.swing.JRadioButton();
        jLabel21 = new javax.swing.JLabel();
        radioCategoriasSi = new javax.swing.JRadioButton();
        radioCategoriasNo = new javax.swing.JRadioButton();
        jLabel22 = new javax.swing.JLabel();
        radioNomUnicoSi = new javax.swing.JRadioButton();
        radioNomUnicoNo = new javax.swing.JRadioButton();
        jLabel23 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel20 = new javax.swing.JLabel();
        comboNiveles = new javax.swing.JComboBox();
        botonAgregarNivel = new javax.swing.JButton();
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
        jLabel2 = new javax.swing.JLabel();
        valorNombreGeneral = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        valorNota = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        comboTipos = new javax.swing.JComboBox();
        botonCancelarPanel = new javax.swing.JButton();
        botonEditar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        arbolPrincipal = new javax.swing.JTree();
        pathPane = new javax.swing.JPanel();
        menuBar = new javax.swing.JMenuBar();
        javax.swing.JMenu fileMenu = new javax.swing.JMenu();
        jMenuItem7 = new javax.swing.JMenuItem();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem8 = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JSeparator();
        javax.swing.JMenuItem exitMenuItem = new javax.swing.JMenuItem();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();
        jSeparator2 = new javax.swing.JSeparator();
        jMenuItem6 = new javax.swing.JMenuItem();
        javax.swing.JMenu helpMenu = new javax.swing.JMenu();
        javax.swing.JMenuItem aboutMenuItem = new javax.swing.JMenuItem();
        statusPanel = new javax.swing.JPanel();
        javax.swing.JSeparator statusPanelSeparator = new javax.swing.JSeparator();
        statusMessageLabel = new javax.swing.JLabel();
        statusAnimationLabel = new javax.swing.JLabel();
        progressBar = new javax.swing.JProgressBar();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();

        mainPanel.setMaximumSize(new java.awt.Dimension(471, 659));
        mainPanel.setMinimumSize(new java.awt.Dimension(471, 659));
        mainPanel.setName("mainPanel"); // NOI18N
        mainPanel.setPreferredSize(new java.awt.Dimension(471, 859));
        mainPanel.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                mainPanelComponentShown(evt);
            }
        });
        mainPanel.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                mainPanelFocusGained(evt);
            }
        });

        canvas1.setName("canvas1"); // NOI18N

        canvas2.setName("canvas2"); // NOI18N

        canvas3.setName("canvas3"); // NOI18N

        canvas4.setName("canvas4"); // NOI18N

        jPanel1.setName("jPanel1"); // NOI18N

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(gestiontipocampo.GestionTipoCampoApp.class).getContext().getResourceMap(GestionTipoCampoView.class);
        botonActualizar.setText(resourceMap.getString("botonActualizar.text")); // NOI18N
        botonActualizar.setName("botonActualizar"); // NOI18N
        botonActualizar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                botonActualizarMouseClicked(evt);
            }
        });

        org.jdesktop.layout.GroupLayout jPanel1Layout = new org.jdesktop.layout.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(71, Short.MAX_VALUE)
                .add(botonActualizar)
                .add(47, 47, 47))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .add(botonActualizar)
                .addContainerGap(11, Short.MAX_VALUE))
        );

        paneDatosAbrir.setName("paneDatosAbrir"); // NOI18N
        paneDatosAbrir.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                paneDatosAbrirComponentShown(evt);
            }
        });

        panePrincipal.setAutoscrolls(true);
        panePrincipal.setName("panePrincipal"); // NOI18N

        paneBinario.setName("paneBinario"); // NOI18N

        jLabel7.setText(resourceMap.getString("jLabel7.text")); // NOI18N
        jLabel7.setName("jLabel7"); // NOI18N
        jLabel7.setBounds(0, 10, 100, 20);
        paneBinario.add(jLabel7, javax.swing.JLayeredPane.DEFAULT_LAYER);

        valorNombreBinario1.setEditable(false);
        valorNombreBinario1.setName("valorNombreBinario1"); // NOI18N
        valorNombreBinario1.setBounds(0, 30, 120, 20);
        paneBinario.add(valorNombreBinario1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLabel8.setText(resourceMap.getString("jLabel8.text")); // NOI18N
        jLabel8.setName("jLabel8"); // NOI18N
        jLabel8.setBounds(190, 10, 100, 20);
        paneBinario.add(jLabel8, javax.swing.JLayeredPane.DEFAULT_LAYER);

        valorOpcionBinaria1.setEditable(false);
        valorOpcionBinaria1.setName("valorOpcionBinaria1"); // NOI18N
        valorOpcionBinaria1.setBounds(190, 30, 120, 20);
        paneBinario.add(valorOpcionBinaria1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLabel9.setText(resourceMap.getString("jLabel9.text")); // NOI18N
        jLabel9.setName("jLabel9"); // NOI18N
        jLabel9.setBounds(350, 10, 100, 20);
        paneBinario.add(jLabel9, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLabel10.setText(resourceMap.getString("jLabel10.text")); // NOI18N
        jLabel10.setName("jLabel10"); // NOI18N
        jLabel10.setBounds(0, 60, 120, 20);
        paneBinario.add(jLabel10, javax.swing.JLayeredPane.DEFAULT_LAYER);

        valorNombreBinario2.setEditable(false);
        valorNombreBinario2.setName("valorNombreBinario2"); // NOI18N
        valorNombreBinario2.setBounds(0, 80, 120, 20);
        paneBinario.add(valorNombreBinario2, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLabel11.setText(resourceMap.getString("jLabel11.text")); // NOI18N
        jLabel11.setName("jLabel11"); // NOI18N
        jLabel11.setBounds(190, 60, 100, 20);
        paneBinario.add(jLabel11, javax.swing.JLayeredPane.DEFAULT_LAYER);

        valorOpcionBinaria2.setEditable(false);
        valorOpcionBinaria2.setName("valorOpcionBinaria2"); // NOI18N
        valorOpcionBinaria2.setBounds(190, 80, 120, 20);
        paneBinario.add(valorOpcionBinaria2, javax.swing.JLayeredPane.DEFAULT_LAYER);

        radioOpcionBinaria1.setSelected(true);
        radioOpcionBinaria1.setText(resourceMap.getString("radioOpcionBinaria1.text")); // NOI18N
        radioOpcionBinaria1.setEnabled(false);
        radioOpcionBinaria1.setFocusable(false);
        radioOpcionBinaria1.setName("radioOpcionBinaria1"); // NOI18N
        radioOpcionBinaria1.setBounds(350, 30, 100, 20);
        paneBinario.add(radioOpcionBinaria1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        radioOpcionBinaria2.setText(resourceMap.getString("radioOpcionBinaria2.text")); // NOI18N
        radioOpcionBinaria2.setEnabled(false);
        radioOpcionBinaria2.setFocusable(false);
        radioOpcionBinaria2.setName("radioOpcionBinaria2"); // NOI18N
        radioOpcionBinaria2.setBounds(350, 60, 100, 20);
        paneBinario.add(radioOpcionBinaria2, javax.swing.JLayeredPane.DEFAULT_LAYER);

        paneBinario.setBounds(30, -10, 450, 210);
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

        paneNumero.setBounds(30, -10, 410, 200);
        panePrincipal.add(paneNumero, javax.swing.JLayeredPane.DEFAULT_LAYER);

        paneIncremental.setName("paneIncremental"); // NOI18N

        jLabel17.setText(resourceMap.getString("jLabel17.text")); // NOI18N
        jLabel17.setName("jLabel17"); // NOI18N
        jLabel17.setBounds(20, 10, 100, 20);
        paneIncremental.add(jLabel17, javax.swing.JLayeredPane.DEFAULT_LAYER);

        valorValorInicial.setEditable(false);
        valorValorInicial.setName("valorValorInicial"); // NOI18N
        valorValorInicial.setBounds(20, 30, 120, 20);
        paneIncremental.add(valorValorInicial, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLabel19.setText(resourceMap.getString("jLabel19.text")); // NOI18N
        jLabel19.setName("jLabel19"); // NOI18N
        jLabel19.setBounds(210, 10, 100, 20);
        paneIncremental.add(jLabel19, javax.swing.JLayeredPane.DEFAULT_LAYER);

        valorIncremento.setEditable(false);
        valorIncremento.setName("valorIncremento"); // NOI18N
        valorIncremento.setBounds(210, 30, 120, 20);
        paneIncremental.add(valorIncremento, javax.swing.JLayeredPane.DEFAULT_LAYER);

        paneIncremental.setBounds(20, -10, 440, 190);
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

        paneFechaHora.setBounds(30, -20, 340, 220);
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

        botonAgregarCategoria.setText(resourceMap.getString("botonAgregarCategoria.text")); // NOI18N
        botonAgregarCategoria.setName("botonAgregarCategoria"); // NOI18N
        botonAgregarCategoria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonAgregarCategoriaActionPerformed(evt);
            }
        });
        botonAgregarCategoria.setBounds(350, 90, 130, -1);
        paneJerarquia.add(botonAgregarCategoria, javax.swing.JLayeredPane.DEFAULT_LAYER);

        radioNivelesSi.setText(resourceMap.getString("radioNivelesSi.text")); // NOI18N
        radioNivelesSi.setEnabled(false);
        radioNivelesSi.setName("radioNivelesSi"); // NOI18N
        radioNivelesSi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioNivelesSiActionPerformed(evt);
            }
        });
        radioNivelesSi.setBounds(20, 40, -1, -1);
        paneJerarquia.add(radioNivelesSi, javax.swing.JLayeredPane.DEFAULT_LAYER);

        radioNivelesNo.setText(resourceMap.getString("radioNivelesNo.text")); // NOI18N
        radioNivelesNo.setEnabled(false);
        radioNivelesNo.setName("radioNivelesNo"); // NOI18N
        radioNivelesNo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioNivelesNoActionPerformed(evt);
            }
        });
        radioNivelesNo.setBounds(60, 40, 50, -1);
        paneJerarquia.add(radioNivelesNo, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLabel21.setText(resourceMap.getString("jLabel21.text")); // NOI18N
        jLabel21.setName("jLabel21"); // NOI18N
        jLabel21.setBounds(20, 20, 130, -1);
        paneJerarquia.add(jLabel21, javax.swing.JLayeredPane.DEFAULT_LAYER);

        radioCategoriasSi.setText(resourceMap.getString("radioCategoriasSi.text")); // NOI18N
        radioCategoriasSi.setEnabled(false);
        radioCategoriasSi.setName("radioCategoriasSi"); // NOI18N
        radioCategoriasSi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioCategoriasSiActionPerformed(evt);
            }
        });
        radioCategoriasSi.setBounds(20, 90, -1, -1);
        paneJerarquia.add(radioCategoriasSi, javax.swing.JLayeredPane.DEFAULT_LAYER);

        radioCategoriasNo.setText(resourceMap.getString("radioCategoriasNo.text")); // NOI18N
        radioCategoriasNo.setEnabled(false);
        radioCategoriasNo.setName("radioCategoriasNo"); // NOI18N
        radioCategoriasNo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioCategoriasNoActionPerformed(evt);
            }
        });
        radioCategoriasNo.setBounds(60, 90, 50, -1);
        paneJerarquia.add(radioCategoriasNo, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLabel22.setText(resourceMap.getString("jLabel22.text")); // NOI18N
        jLabel22.setName("jLabel22"); // NOI18N
        jLabel22.setBounds(20, 70, 80, -1);
        paneJerarquia.add(jLabel22, javax.swing.JLayeredPane.DEFAULT_LAYER);

        radioNomUnicoSi.setText(resourceMap.getString("radioNomUnicoSi.text")); // NOI18N
        radioNomUnicoSi.setEnabled(false);
        radioNomUnicoSi.setName("radioNomUnicoSi"); // NOI18N
        radioNomUnicoSi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioNomUnicoSiActionPerformed(evt);
            }
        });
        radioNomUnicoSi.setBounds(20, 140, -1, -1);
        paneJerarquia.add(radioNomUnicoSi, javax.swing.JLayeredPane.DEFAULT_LAYER);

        radioNomUnicoNo.setText(resourceMap.getString("radioNomUnicoNo.text")); // NOI18N
        radioNomUnicoNo.setEnabled(false);
        radioNomUnicoNo.setName("radioNomUnicoNo"); // NOI18N
        radioNomUnicoNo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioNomUnicoNoActionPerformed(evt);
            }
        });
        radioNomUnicoNo.setBounds(60, 140, 50, -1);
        paneJerarquia.add(radioNomUnicoNo, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLabel23.setText(resourceMap.getString("jLabel23.text")); // NOI18N
        jLabel23.setName("jLabel23"); // NOI18N
        jLabel23.setBounds(20, 120, 70, -1);
        paneJerarquia.add(jLabel23, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jButton1.setText(resourceMap.getString("jButton1.text")); // NOI18N
        jButton1.setName("jButton1"); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jButton1.setBounds(350, 140, 130, -1);
        paneJerarquia.add(jButton1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLabel20.setText(resourceMap.getString("jLabel20.text")); // NOI18N
        jLabel20.setName("jLabel20"); // NOI18N
        jLabel20.setBounds(200, 20, -1, -1);
        paneJerarquia.add(jLabel20, javax.swing.JLayeredPane.DEFAULT_LAYER);

        comboNiveles.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1- Categoria 1", "2- Categoria 2", "3- Categoría 3", "4- Categoría 4" }));
        comboNiveles.setEnabled(false);
        comboNiveles.setName("comboNiveles"); // NOI18N
        comboNiveles.setBounds(200, 40, 130, -1);
        paneJerarquia.add(comboNiveles, javax.swing.JLayeredPane.DEFAULT_LAYER);

        botonAgregarNivel.setText(resourceMap.getString("botonAgregarNivel.text")); // NOI18N
        botonAgregarNivel.setName("botonAgregarNivel"); // NOI18N
        botonAgregarNivel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonAgregarNivelActionPerformed(evt);
            }
        });
        botonAgregarNivel.setBounds(350, 40, 130, -1);
        paneJerarquia.add(botonAgregarNivel, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLabel24.setText(resourceMap.getString("jLabel24.text")); // NOI18N
        jLabel24.setName("jLabel24"); // NOI18N
        jLabel24.setBounds(200, 170, 110, -1);
        paneJerarquia.add(jLabel24, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLabel25.setText(resourceMap.getString("jLabel25.text")); // NOI18N
        jLabel25.setName("jLabel25"); // NOI18N
        jLabel25.setBounds(20, 170, 110, -1);
        paneJerarquia.add(jLabel25, javax.swing.JLayeredPane.DEFAULT_LAYER);

        valorNumNiveles.setText(resourceMap.getString("valorNumNiveles.text")); // NOI18N
        valorNumNiveles.setEnabled(false);
        valorNumNiveles.setName("valorNumNiveles"); // NOI18N
        valorNumNiveles.setBounds(200, 190, 70, -1);
        paneJerarquia.add(valorNumNiveles, javax.swing.JLayeredPane.DEFAULT_LAYER);

        valorNumTerminos.setText(resourceMap.getString("valorNumTerminos.text")); // NOI18N
        valorNumTerminos.setEnabled(false);
        valorNumTerminos.setName("valorNumTerminos"); // NOI18N
        valorNumTerminos.setBounds(20, 190, 70, -1);
        paneJerarquia.add(valorNumTerminos, javax.swing.JLayeredPane.DEFAULT_LAYER);

        paneJerarquia.setBounds(30, -10, 510, 250);
        panePrincipal.add(paneJerarquia, javax.swing.JLayeredPane.DEFAULT_LAYER);

        paneTexto.setName("paneTexto"); // NOI18N

        jLabel16.setText(resourceMap.getString("jLabel16.text")); // NOI18N
        jLabel16.setName("jLabel16"); // NOI18N
        jLabel16.setBounds(20, 10, 100, 20);
        paneTexto.add(jLabel16, javax.swing.JLayeredPane.DEFAULT_LAYER);

        valorTextoLargo.setEditable(false);
        valorTextoLargo.setName("valorTextoLargo"); // NOI18N
        valorTextoLargo.setBounds(20, 30, 120, 20);
        paneTexto.add(valorTextoLargo, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLabel18.setText(resourceMap.getString("jLabel18.text")); // NOI18N
        jLabel18.setName("jLabel18"); // NOI18N
        jLabel18.setBounds(200, 10, 100, 20);
        paneTexto.add(jLabel18, javax.swing.JLayeredPane.DEFAULT_LAYER);

        valorTextoDefecto.setEditable(false);
        valorTextoDefecto.setName("valorTextoDefecto"); // NOI18N
        valorTextoDefecto.setBounds(200, 30, 120, 20);
        paneTexto.add(valorTextoDefecto, javax.swing.JLayeredPane.DEFAULT_LAYER);

        paneTexto.setBounds(20, -10, 360, 220);
        panePrincipal.add(paneTexto, javax.swing.JLayeredPane.DEFAULT_LAYER);

        paneLista.setName("paneLista"); // NOI18N

        valorPorDefectoLista.setEditable(false);
        valorPorDefectoLista.setName("valorPorDefectoLista"); // NOI18N
        valorPorDefectoLista.setBounds(30, 40, 110, -1);
        paneLista.add(valorPorDefectoLista, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLabel26.setText(resourceMap.getString("jLabel26.text")); // NOI18N
        jLabel26.setName("jLabel26"); // NOI18N
        jLabel26.setBounds(30, 20, 90, -1);
        paneLista.add(jLabel26, javax.swing.JLayeredPane.DEFAULT_LAYER);

        paneLista.setBounds(0, 0, 530, 160);
        panePrincipal.add(paneLista, javax.swing.JLayeredPane.DEFAULT_LAYER);

        panePrincipal.setBounds(10, 120, 550, 210);
        paneDatosAbrir.add(panePrincipal, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLabel2.setText(resourceMap.getString("jLabel2.text")); // NOI18N
        jLabel2.setName("jLabel2"); // NOI18N
        jLabel2.setBounds(40, 70, -1, -1);
        paneDatosAbrir.add(jLabel2, javax.swing.JLayeredPane.DEFAULT_LAYER);

        valorNombreGeneral.setEditable(false);
        valorNombreGeneral.setName("valorNombreGeneral"); // NOI18N
        valorNombreGeneral.setBounds(40, 90, 120, -1);
        paneDatosAbrir.add(valorNombreGeneral, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLabel3.setText(resourceMap.getString("jLabel3.text")); // NOI18N
        jLabel3.setName("jLabel3"); // NOI18N
        jLabel3.setBounds(230, 70, -1, -1);
        paneDatosAbrir.add(jLabel3, javax.swing.JLayeredPane.DEFAULT_LAYER);

        valorNota.setEditable(false);
        valorNota.setName("valorNota"); // NOI18N
        valorNota.setBounds(230, 90, 120, -1);
        paneDatosAbrir.add(valorNota, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLabel1.setText(resourceMap.getString("jLabel1.text")); // NOI18N
        jLabel1.setName("jLabel1"); // NOI18N
        jLabel1.setBounds(40, 20, -1, -1);
        paneDatosAbrir.add(jLabel1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        comboTipos.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "Número", "Binario", "FechaHora", "Texto", "Incremental", "Jerarquia", "Lista" }));
        comboTipos.setEnabled(false);
        comboTipos.setFocusable(false);
        comboTipos.setName("comboTipos"); // NOI18N
        comboTipos.setBounds(40, 40, 120, -1);
        paneDatosAbrir.add(comboTipos, javax.swing.JLayeredPane.DEFAULT_LAYER);

        botonCancelarPanel.setText(resourceMap.getString("botonCancelarPanel.text")); // NOI18N
        botonCancelarPanel.setName("botonCancelarPanel"); // NOI18N
        botonCancelarPanel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonCancelarPanelActionPerformed(evt);
            }
        });
        botonCancelarPanel.setBounds(360, 330, 80, -1);
        paneDatosAbrir.add(botonCancelarPanel, javax.swing.JLayeredPane.DEFAULT_LAYER);

        botonEditar.setText(resourceMap.getString("botonEditar.text")); // NOI18N
        botonEditar.setName("botonEditar"); // NOI18N
        botonEditar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                botonEditarMouseClicked(evt);
            }
        });
        botonEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonEditarActionPerformed(evt);
            }
        });
        botonEditar.setBounds(240, 330, 80, -1);
        paneDatosAbrir.add(botonEditar, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jScrollPane1.setName("jScrollPane1"); // NOI18N

        javax.swing.tree.DefaultMutableTreeNode treeNode1 = new javax.swing.tree.DefaultMutableTreeNode("GIFT");
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
        arbolPrincipal.setModel(new javax.swing.tree.DefaultTreeModel(treeNode1));
        arbolPrincipal.setName("arbolPrincipal"); // NOI18N
        arbolPrincipal.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                arbolPrincipalMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(arbolPrincipal);

        pathPane.setName("pathPane"); // NOI18N
        pathPane.setLayout(new javax.swing.BoxLayout(pathPane, javax.swing.BoxLayout.LINE_AXIS));

        org.jdesktop.layout.GroupLayout mainPanelLayout = new org.jdesktop.layout.GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
            mainPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(mainPanelLayout.createSequentialGroup()
                .addContainerGap()
                .add(mainPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jScrollPane1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 203, Short.MAX_VALUE)
                    .add(mainPanelLayout.createSequentialGroup()
                        .add(jPanel1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)))
                .add(mainPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(mainPanelLayout.createSequentialGroup()
                        .add(236, 236, 236)
                        .add(canvas1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 0, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(mainPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(mainPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                .add(org.jdesktop.layout.GroupLayout.TRAILING, canvas3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                .add(org.jdesktop.layout.GroupLayout.TRAILING, canvas2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                            .add(mainPanelLayout.createSequentialGroup()
                                .add(203, 203, 203)
                                .add(canvas4, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))))
                    .add(mainPanelLayout.createSequentialGroup()
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(mainPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(mainPanelLayout.createSequentialGroup()
                                .add(10, 10, 10)
                                .add(paneDatosAbrir, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 581, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                            .add(pathPane, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 739, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );
        mainPanelLayout.setVerticalGroup(
            mainPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(mainPanelLayout.createSequentialGroup()
                .add(11, 11, 11)
                .add(mainPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(mainPanelLayout.createSequentialGroup()
                        .add(jScrollPane1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 376, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .add(jPanel1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .add(mainPanelLayout.createSequentialGroup()
                        .add(pathPane, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 44, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 12, Short.MAX_VALUE)
                        .add(paneDatosAbrir, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 371, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                .add(4, 4, 4)
                .add(mainPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(canvas2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(mainPanelLayout.createSequentialGroup()
                        .add(105, 105, 105)
                        .add(canvas3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 298, Short.MAX_VALUE)
                        .add(canvas4, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .add(canvas1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 403, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        menuBar.setName("menuBar"); // NOI18N

        fileMenu.setText(resourceMap.getString("fileMenu.text")); // NOI18N
        fileMenu.setName("fileMenu"); // NOI18N
        fileMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fileMenuActionPerformed(evt);
            }
        });

        jMenuItem7.setText(resourceMap.getString("jMenuItem7.text")); // NOI18N
        jMenuItem7.setName("jMenuItem7"); // NOI18N
        jMenuItem7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem7ActionPerformed(evt);
            }
        });
        fileMenu.add(jMenuItem7);

        javax.swing.ActionMap actionMap = org.jdesktop.application.Application.getInstance(gestiontipocampo.GestionTipoCampoApp.class).getContext().getActionMap(GestionTipoCampoView.class, this);
        jMenuItem1.setAction(actionMap.get("mostrarVentanaManejoCampos")); // NOI18N
        jMenuItem1.setText(resourceMap.getString("jMenuItem1.text")); // NOI18N
        jMenuItem1.setName("jMenuItem1"); // NOI18N
        fileMenu.add(jMenuItem1);

        jMenuItem2.setText(resourceMap.getString("jMenuItem2.text")); // NOI18N
        jMenuItem2.setName("jMenuItem2"); // NOI18N
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        fileMenu.add(jMenuItem2);

        jMenuItem8.setAction(actionMap.get("AbrirFormulario")); // NOI18N
        jMenuItem8.setText(resourceMap.getString("jMenuItem8.text")); // NOI18N
        jMenuItem8.setName("jMenuItem8"); // NOI18N
        jMenuItem8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem8ActionPerformed(evt);
            }
        });
        fileMenu.add(jMenuItem8);

        jSeparator1.setName("jSeparator1"); // NOI18N
        fileMenu.add(jSeparator1);

        exitMenuItem.setAction(actionMap.get("quit")); // NOI18N
        exitMenuItem.setText(resourceMap.getString("exitMenuItem.text")); // NOI18N
        exitMenuItem.setName("exitMenuItem"); // NOI18N
        fileMenu.add(exitMenuItem);

        menuBar.add(fileMenu);

        jMenu1.setText(resourceMap.getString("jMenu1.text")); // NOI18N
        jMenu1.setName("jMenu1"); // NOI18N

        jMenuItem5.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_X, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem5.setText(resourceMap.getString("jMenuItem5.text")); // NOI18N
        jMenuItem5.setName("jMenuItem5"); // NOI18N
        jMenu1.add(jMenuItem5);

        jMenuItem3.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem3.setText(resourceMap.getString("jMenuItem3.text")); // NOI18N
        jMenuItem3.setName("jMenuItem3"); // NOI18N
        jMenu1.add(jMenuItem3);

        jMenuItem4.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_V, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem4.setText(resourceMap.getString("jMenuItem4.text")); // NOI18N
        jMenuItem4.setName("jMenuItem4"); // NOI18N
        jMenu1.add(jMenuItem4);

        jSeparator2.setName("jSeparator2"); // NOI18N
        jMenu1.add(jSeparator2);

        jMenuItem6.setText(resourceMap.getString("jMenuItem6.text")); // NOI18N
        jMenuItem6.setName("jMenuItem6"); // NOI18N
        jMenu1.add(jMenuItem6);

        menuBar.add(jMenu1);

        helpMenu.setText(resourceMap.getString("helpMenu.text")); // NOI18N
        helpMenu.setName("helpMenu"); // NOI18N

        aboutMenuItem.setAction(actionMap.get("showAboutBox")); // NOI18N
        aboutMenuItem.setIcon(resourceMap.getIcon("aboutMenuItem.icon")); // NOI18N
        aboutMenuItem.setText(resourceMap.getString("aboutMenuItem.text")); // NOI18N
        aboutMenuItem.setName("aboutMenuItem"); // NOI18N
        helpMenu.add(aboutMenuItem);

        menuBar.add(helpMenu);

        statusPanel.setName("statusPanel"); // NOI18N

        statusPanelSeparator.setName("statusPanelSeparator"); // NOI18N

        statusMessageLabel.setName("statusMessageLabel"); // NOI18N

        statusAnimationLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        statusAnimationLabel.setName("statusAnimationLabel"); // NOI18N

        progressBar.setName("progressBar"); // NOI18N

        org.jdesktop.layout.GroupLayout statusPanelLayout = new org.jdesktop.layout.GroupLayout(statusPanel);
        statusPanel.setLayout(statusPanelLayout);
        statusPanelLayout.setHorizontalGroup(
            statusPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(statusPanelSeparator, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 788, Short.MAX_VALUE)
            .add(statusPanelLayout.createSequentialGroup()
                .addContainerGap()
                .add(statusMessageLabel)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 618, Short.MAX_VALUE)
                .add(progressBar, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(statusAnimationLabel)
                .addContainerGap())
        );
        statusPanelLayout.setVerticalGroup(
            statusPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(statusPanelLayout.createSequentialGroup()
                .add(statusPanelSeparator, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .add(statusPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(statusMessageLabel)
                    .add(statusAnimationLabel)
                    .add(progressBar, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .add(3, 3, 3))
        );

        jPanel3.setName("jPanel3"); // NOI18N

        jPanel4.setName("jPanel4"); // NOI18N

        org.jdesktop.layout.GroupLayout jPanel4Layout = new org.jdesktop.layout.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 100, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 100, Short.MAX_VALUE)
        );

        org.jdesktop.layout.GroupLayout jPanel3Layout = new org.jdesktop.layout.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(342, Short.MAX_VALUE)
                .add(jPanel4, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(112, 112, 112))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(31, Short.MAX_VALUE)
                .add(jPanel4, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        setComponent(mainPanel);
        setMenuBar(menuBar);
        setStatusBar(statusPanel);
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        abrirUnCampo();
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void mainPanelComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_mainPanelComponentShown

    }//GEN-LAST:event_mainPanelComponentShown

    private void botonEditarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botonEditarMouseClicked
        // TODO add your handling code here:
}//GEN-LAST:event_botonEditarMouseClicked

    private void botonEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonEditarActionPerformed
        if(comboTipos.getSelectedItem().toString().equals("Lista")){
            String IDLista = getIDTipoCampo(valorNombreGeneral.getText());
            frameLista ventanaLista = new frameLista( Integer.parseInt(IDLista));
            ventanaLista.setLocationRelativeTo(GestionTipoCampoApp.getApplication().getMainFrame());
            ventanaLista.setVisible(true);
        }
        else{
            String ID = getIDTipoCampo(valorNombreGeneral.getText());
            ocultarPanes();
            frameManejoCampos ventanaManejoCampos = new frameManejoCampos();
            ventanaManejoCampos.llenarFormularioCampos(ID);
            JFrame mainFrame = GestionTipoCampoApp.getApplication().getMainFrame();
            //coloca el frame segun como este ubicada la ventana principal
            ventanaManejoCampos.setLocationRelativeTo(mainFrame);
            ventanaManejoCampos.setVisible(true);
        }
}//GEN-LAST:event_botonEditarActionPerformed

    private void arbolPrincipalMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_arbolPrincipalMouseClicked
        actualizarPath();
        abrirNodoHoja();
    }//GEN-LAST:event_arbolPrincipalMouseClicked

    private void botonCancelarPanelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonCancelarPanelActionPerformed
        ocultarPanes();
    }//GEN-LAST:event_botonCancelarPanelActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        frameBuscarTerminos f;
        f=new frameBuscarTerminos(valorNombreGeneral.getText()); //abre el tree view de la jerarquia
        f.setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void botonAgregarCategoriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonAgregarCategoriaActionPerformed
        String response = JOptionPane.showInputDialog(null, "Digite el nombre de la nueva categoría", "Nueva Categoría", JOptionPane.QUESTION_MESSAGE);
        frameManejoCategorias categorias;
        categorias = new frameManejoCategorias(response);
        categorias.setVisible(true);
}//GEN-LAST:event_botonAgregarCategoriaActionPerformed

    private void radioNivelesSiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioNivelesSiActionPerformed
        if (radioNivelesSi.isSelected() == true) {
            radioNivelesNo.setSelected(false);
            comboNiveles.setEnabled(true);
        }
}//GEN-LAST:event_radioNivelesSiActionPerformed

    private void radioNivelesNoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioNivelesNoActionPerformed
        if (radioNivelesNo.isSelected() == true) {
            radioNivelesSi.setSelected(false);
            comboNiveles.setEnabled(false);
        }
}//GEN-LAST:event_radioNivelesNoActionPerformed

    private void radioCategoriasSiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioCategoriasSiActionPerformed
        if (radioCategoriasSi.isSelected() == true) {
            radioCategoriasNo.setSelected(false);
            comboCategorias.setEnabled(true);
        }
}//GEN-LAST:event_radioCategoriasSiActionPerformed

    private void radioCategoriasNoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioCategoriasNoActionPerformed
        if (radioCategoriasNo.isSelected() == true) {
            radioCategoriasSi.setSelected(false);
            comboCategorias.setEnabled(false);
        }
}//GEN-LAST:event_radioCategoriasNoActionPerformed

    private void radioNomUnicoSiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioNomUnicoSiActionPerformed
        if (radioNomUnicoSi.isSelected() == true) {
            radioNomUnicoNo.setSelected(false);
        }
}//GEN-LAST:event_radioNomUnicoSiActionPerformed

    private void radioNomUnicoNoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioNomUnicoNoActionPerformed
        if (radioNomUnicoNo.isSelected() == true) {
            radioNomUnicoSi.setSelected(false);
        }
}//GEN-LAST:event_radioNomUnicoNoActionPerformed

    private void mainPanelFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_mainPanelFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_mainPanelFocusGained

    private void botonActualizarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botonActualizarMouseClicked
        //Se actualiza el tree View
        llenarTreeView();
        ocultarPanes();       
}//GEN-LAST:event_botonActualizarMouseClicked

    private void paneDatosAbrirComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_paneDatosAbrirComponentShown
        botonEditar.setBounds(240, 330, 80, 20);
    }//GEN-LAST:event_paneDatosAbrirComponentShown

    private void paneJerarquiaComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_paneJerarquiaComponentShown
        Modelo  miModelo = new Modelo();
        comboCategorias.setModel(new javax.swing.DefaultComboBoxModel(miModelo.getModeloDeCombo("select nombre, ID from TIPOCATEGORIA;")));
    }//GEN-LAST:event_paneJerarquiaComponentShown

    private void botonAgregarNivelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonAgregarNivelActionPerformed
        // TODO add your handling code here:
}//GEN-LAST:event_botonAgregarNivelActionPerformed

    private void jMenuItem7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem7ActionPerformed
        frameFormulario frameForm = new frameFormulario( arbolPrincipal.getModel() );
        frameForm.setLocationRelativeTo(GestionTipoCampoApp.getApplication().getMainFrame());
        frameForm.setVisible(true);
    }//GEN-LAST:event_jMenuItem7ActionPerformed

    private void jMenuItem8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem8ActionPerformed
        frameFormulario frameForm = null;
        SeleccionFormulario seleccionarForm = new SeleccionFormulario(frameForm,arbolPrincipal);
        seleccionarForm.setVisible(true);
        seleccionarForm.setLocationRelativeTo(GestionTipoCampoApp.getApplication().getMainFrame());
        seleccionarForm.llenarTabla("");
      //  frameForm.setLocationRelativeTo(GestionTipoCampoApp.getApplication().getMainFrame());
//        frameForm.setVisible(true);
        //String val = JOptionPane.showInputDialog(this, "Favor ingresar el ID del formulario a abrir", "", JOptionPane.QUESTION_MESSAGE);
       /* int ID = 12;
        frameFormulario frameForm = new frameFormulario( arbolPrincipal.getModel(), ID);

        frameForm.setLocationRelativeTo(GestionTipoCampoApp.getApplication().getMainFrame());
        frameForm.setVisible(true);*/
    }//GEN-LAST:event_jMenuItem8ActionPerformed

    private void fileMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fileMenuActionPerformed

    }//GEN-LAST:event_fileMenuActionPerformed

    public void llenarDatosReadOnly(String nombre, int tipo){
        //Trae todos los valores de la tabla TIPOCAMPO
        String valoresGlobales = buscarDatosEnBD(nombre);
        String [] valoresSeparados = valoresGlobales.split(";");
        String valoresEspecificos = buscarPorTipoEnBD(Integer.parseInt(valoresSeparados[0]), tipo);
        String [] valoresEspSep = valoresEspecificos.split(";");
        comboTipos.setSelectedIndex(tipo);
        valorNombreGeneral.setText(valoresSeparados[1]);
        valorNota.setText(valoresSeparados[2]);
        switch(tipo){
            //numero
            case(1):
                valorNumDecimales.setText(valoresEspSep[1]);
                valorNumeroMascara.setText(valoresEspSep[2]);
                valorValorDefectoNumero.setText(valoresEspSep[3]);
                break;
            //binario
            case(2):
                valorNombreBinario1.setText(valoresEspSep[1]);
                valorOpcionBinaria1.setText(valoresEspSep[2]);
                valorNombreBinario2.setText(valoresEspSep[3]);
                valorOpcionBinaria2.setText(valoresEspSep[4]);
                if(valoresEspSep[5].equals("true")){
                    radioOpcionBinaria1.setSelected(true);
                    radioOpcionBinaria2.setSelected(false);
                }else{
                    radioOpcionBinaria1.setSelected(false);
                    radioOpcionBinaria2.setSelected(true);
                }
                break;
            //FechaHora
            case(3):
                //comboFormatoFecha.setSelectedItem(valoresEspSep[1]);
                valorFechaDefecto.setText(valoresEspSep[2]);
                if( valoresEspSep[3].equals("true") ){
                    radioFechaHoraSi.setSelected(true);
                    radioFechaHoraNo.setSelected(false);
                }else{
                    radioFechaHoraSi.setSelected(false);
                    radioFechaHoraNo.setSelected(true);
                }
                valorPreaviso.setText(valoresEspSep[4]);
                break;
            //Texto
            case(4):
                valorTextoDefecto.setText(valoresEspSep[1]);
                valorTextoLargo.setText(valoresEspSep[2]);
                break;
            //Incremental
            case(5):
                valorValorInicial.setText(valoresEspSep[1]);
                valorIncremento.setText(valoresEspSep[2]);
                break;
            //Jerarquia
            case(6):
                if(valoresEspSep[3].equalsIgnoreCase("true")){
                    this.radioNomUnicoSi.setSelected(false);
                    this.radioNomUnicoNo.setSelected(true);
                }else{
                    this.radioNomUnicoSi.setSelected(true);
                    this.radioNomUnicoNo.setSelected(false);
                }
                this.valorNumTerminos.setText(valoresEspSep[4]);
                this.valorNumNiveles.setText(valoresEspSep[5]);
                if(valoresEspSep[8].equalsIgnoreCase("true")){
                    this.radioCategoriasSi.setSelected(true);
                    this.radioCategoriasNo.setSelected(false);
                }else{
                    this.radioCategoriasSi.setSelected(false);
                    this.radioCategoriasNo.setSelected(true);
                }
                if(valoresEspSep[9].equalsIgnoreCase("true")){
                    this.radioNivelesSi.setSelected(true);
                    this.radioNivelesNo.setSelected(false);
                }else{
                    this.radioNivelesSi.setSelected(false);
                    this.radioNivelesNo.setSelected(true);
                }
                //this.comboCategorias.setSelectedIndex(Integer.parseInt(valoresEspSep[10]));
                break;
            //Lista
            case(7):
                valoresEspSep[1] = buscarMiembroLista( Integer.parseInt(valoresEspSep[1]));
                this.valorPorDefectoLista.setText(valoresEspSep[1].trim());
                break;

            default:
                break;
        }
    }

    public void abrirUnCampo() {
        frameManejoCampos ventanaManejoCampos = new frameManejoCampos("abrir");
        JFrame mainFrame = GestionTipoCampoApp.getApplication().getMainFrame();
        //coloca el frame segun como este ubicada la ventana principal
        ventanaManejoCampos.setLocationRelativeTo(mainFrame);
        ventanaManejoCampos.setVisible(true);
    }

    public void ocultarPanes(){
        paneDatosAbrir.setVisible(false);
        panePrincipal.setVisible(false);
        paneBinario.setVisible(false);
        paneFechaHora.setVisible(false);
        paneTexto.setVisible(false);
        paneIncremental.setVisible(false);
        paneNumero.setVisible(false);
        paneJerarquia.setVisible(false);
        paneLista.setVisible(false);
    }

    public void abrirNodoHoja(){

        DefaultMutableTreeNode node = (DefaultMutableTreeNode)arbolPrincipal.getLastSelectedPathComponent();
        if (node != null){
            String nombre = node.toString();
            String papa = node.getParent()!=null?node.getParent().toString():"";
            ocultarPanes();
            paneDatosAbrir.setVisible(true);
            panePrincipal.setVisible(true);
            if( papa.equals("Numero")  ){
                paneNumero.setVisible(true);
                llenarDatosReadOnly(nombre, 1);
            }
            else{
                if( papa.equals("Binario")  ){
                    paneBinario.setVisible(true);
                    llenarDatosReadOnly(nombre, 2);
                }
                else{
                    if( papa.equals("FechaHora")  ){
                        paneFechaHora.setVisible(true);
                        llenarDatosReadOnly(nombre, 3);
                    }
                    else{
                        if( papa.equals("Texto")  ){
                            paneTexto.setVisible(true);
                            llenarDatosReadOnly(nombre, 4);
                        }
                        else{
                            if( papa.equals("Incremental")  ){
                                paneIncremental.setVisible(true);
                                llenarDatosReadOnly(nombre, 5);
                            }
                            else{
                                if( papa.equals("Jerarquia")  ){
                                    paneJerarquia.setVisible(true);
                                    llenarDatosReadOnly(nombre, 6);
                                }
                                else{
                                    if( papa.equals("Lista") ){
                                        paneLista.setVisible(true);
                                        llenarDatosReadOnly(nombre, 7);
                                    }
                                    else{
                                        paneDatosAbrir.setVisible(false);
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



    private void buscarElementoPath(){
        int indice = 0;
        while(!((JButton)vectorPath.get(indice)).hasFocus()){           
            indice++;//indice queda con el boton que tiene el focus
        }
        int tamanoAntiguo = vectorPath.size();
        if(indice<tamanoAntiguo-1){           
            TreePath pathCoso = arbolPrincipal.getSelectionPath();
            Object[] vectorObjetos = pathCoso.getPath();
            Object[] vectorNuevo=new Object[indice+1];
            for(int i=0;i<=indice;i++){
                vectorNuevo[i]=vectorObjetos[i];
            }
            TreePath nuevaRuta = new TreePath(vectorNuevo);
            arbolPrincipal.setSelectionPath(nuevaRuta);
            for(int i = indice+1; i<tamanoAntiguo; i++){
                ((JButton)vectorPath.get(i)).setVisible(false);
                vectorPath.remove(i);
            }
            String nombre = ((JButton)vectorPath.get(indice)).getText();
            String nuevo = nombre.substring(0, nombre.length()-3);
            ((JButton)vectorPath.get(indice)).setText(nuevo);
        }

    }

    private void actualizarPath(){
        /////////veamos que sale....
        DefaultMutableTreeNode node = (DefaultMutableTreeNode)arbolPrincipal.getLastSelectedPathComponent();
        if(node!=null){
            pathPane.removeAll();
            pathPane.repaint();
            vectorPath=new Vector();
            javax.swing.JButton temp;
            while(node!=null && !node.toString().equals("")){
                temp = new javax.swing.JButton();
                temp.addFocusListener(new java.awt.event.FocusListener() {
                    public void focusGained(FocusEvent e) {
                        buscarElementoPath();
                        abrirNodoHoja();
                    }
                    public void focusLost(FocusEvent e) {}
                });
                temp.setFocusable(true);
                temp.setFont(new java.awt.Font("SansSerif",java.awt.Font.BOLD,12));
                temp.setForeground(java.awt.Color.blue);

                temp.setText("  "+node.toString()+" >>");
              //  JOptionPane.showMessageDialog(null, node.toString());
                node = (DefaultMutableTreeNode)node.getParent();
                vectorPath.insertElementAt(temp,0);                
            }
            if(!((DefaultMutableTreeNode)arbolPrincipal.getLastSelectedPathComponent()).toString().equals("")){
                String nombre = ((JButton)vectorPath.get(vectorPath.size()-1)).getText();
                String nuevo = nombre.substring(0, nombre.length()-3);
                ((JButton)vectorPath.get(vectorPath.size()-1)).setText(nuevo);
            }
            for(int i=0;i<vectorPath.size(); i++){
                Object temp1 = vectorPath.get(i);
                temp = (javax.swing.JButton)temp1;
                pathPane.add(temp);
            }
        }
    }




    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTree arbolPrincipal;
    private javax.swing.JButton botonActualizar;
    private javax.swing.JButton botonAgregarCategoria;
    private javax.swing.JButton botonAgregarNivel;
    private javax.swing.JButton botonCancelarPanel;
    private javax.swing.JButton botonEditar;
    private java.awt.Canvas canvas1;
    private java.awt.Canvas canvas2;
    private java.awt.Canvas canvas3;
    private java.awt.Canvas canvas4;
    private javax.swing.JComboBox comboCategorias;
    private javax.swing.JComboBox comboFormatoFecha;
    private javax.swing.JComboBox comboNiveles;
    private javax.swing.JComboBox comboTipos;
    private javax.swing.JButton jButton1;
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
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JMenuItem jMenuItem8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JLabel labelCategorias;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JLayeredPane paneBinario;
    private javax.swing.JLayeredPane paneDatosAbrir;
    private javax.swing.JLayeredPane paneFechaHora;
    private javax.swing.JLayeredPane paneIncremental;
    private javax.swing.JLayeredPane paneJerarquia;
    private javax.swing.JLayeredPane paneLista;
    private javax.swing.JLayeredPane paneNumero;
    private javax.swing.JLayeredPane panePrincipal;
    private javax.swing.JLayeredPane paneTexto;
    private javax.swing.JPanel pathPane;
    private javax.swing.JProgressBar progressBar;
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
    private javax.swing.JLabel statusAnimationLabel;
    private javax.swing.JLabel statusMessageLabel;
    private javax.swing.JPanel statusPanel;
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
    private final Timer messageTimer;
    private final Timer busyIconTimer;
    private final Icon idleIcon;
    private final Icon[] busyIcons = new Icon[15];
    private int busyIconIndex = 0;
    private JDialog aboutBox;
    private Vector vectorPath;
}
