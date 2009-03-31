/*
 * GestionTipoCampoView.java
 */
package gestiontipocampo;

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


/**
 * The application's main frame.
 */
public class GestionTipoCampoView extends FrameView {

    public GestionTipoCampoView(SingleFrameApplication app) {
        super(app);

        initComponents();

        //Se esconden todos los datos de panel para abrir datos...
        paneDatosAbrir.setVisible(false);

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

        frameConexiones ventanaConexiones = new frameConexiones();
        //JFrame mainFrame = frameManejoCampos.getApplication().getMainFrame();
        //coloca el frame segun como este ubicada la ventana principal
        // ventanaBusqueda.setLocationRelativeTo(mainFrame);
        ventanaConexiones.setVisible(true);
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
        String [] tiposCampo = {"Numero", "Binario", "FechaHora", "Texto", "Incremental"};
        String valores;
        String [] valTrim;
        DefaultMutableTreeNode nodoTipoCampo;
        DefaultMutableTreeNode nodoTemp;
        DefaultMutableTreeNode raizArbol = new DefaultMutableTreeNode("Gift");    
        
        for(int k = 0; k < tiposCampo.length; k++){
            nodoTipoCampo = new DefaultMutableTreeNode(tiposCampo[k]);
            //Se llenan los datos con los valores que contenga la base de datos con el tipo campo:
            valores = buscarEnBD(tiposCampo[k], k+1);
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



        /*
         MouseListener ml = new MouseAdapter() {
     public void mousePressed(MouseEvent e) {
         int selRow = tree.getRowForLocation(e.getX(), e.getY());
         TreePath selPath = tree.getPathForLocation(e.getX(), e.getY());
         if(selRow != -1) {
             if(e.getClickCount() == 1) {
                 mySingleClick(selRow, selPath);
             }
             else
                if(e.getClickCount() == 2) {
                 myDoubleClick(selRow, selPath);
                }
         }
     }
    */
    }

    /**
     * Encargado de buscar valores en la base de datos
     * @param nombreTP: Indica el nombre del tipoCampo al que se le van a buscar los valores en la BD
     */
    public String buscarEnBD(String nombreTP, int num){
        ControladorBD buscador = new ControladorBD();
        Object[] fila = new Object[4];
        String valores = "";
        try {
            ResultSet resultado = buscador.getResultSet("select * from TIPOCAMPO where tipo = '" + num + "';");
            if(resultado != null)
                while (resultado.next()) {
                    valores += resultado.getObject(2).toString() + "\n";
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
        jScrollPane1 = new javax.swing.JScrollPane();
        arbolPrincipal = new javax.swing.JTree();
        botonActualizarArbol = new javax.swing.JButton();
        botonAbrirArbol = new javax.swing.JButton();
        paneDatosAbrir = new javax.swing.JLayeredPane();
        panePrincipal = new javax.swing.JLayeredPane();
        paneIncremental = new javax.swing.JLayeredPane();
        jLabel17 = new javax.swing.JLabel();
        valorValorInicial = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        valorIncremento = new javax.swing.JTextField();
        paneTexto = new javax.swing.JLayeredPane();
        jLabel16 = new javax.swing.JLabel();
        valorTextoLargo = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        valorTextoDefecto = new javax.swing.JTextField();
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
        jLabel2 = new javax.swing.JLabel();
        valorNombreGeneral = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        valorNota = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        comboTipos = new javax.swing.JComboBox();
        botonCancelarPanel = new javax.swing.JButton();
        botonEditar = new javax.swing.JButton();
        menuBar = new javax.swing.JMenuBar();
        javax.swing.JMenu fileMenu = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
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

        mainPanel.setMaximumSize(new java.awt.Dimension(471, 659));
        mainPanel.setMinimumSize(new java.awt.Dimension(471, 659));
        mainPanel.setName("mainPanel"); // NOI18N
        mainPanel.setPreferredSize(new java.awt.Dimension(471, 859));
        mainPanel.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                mainPanelComponentShown(evt);
            }
        });

        canvas1.setName("canvas1"); // NOI18N

        canvas2.setName("canvas2"); // NOI18N

        canvas3.setName("canvas3"); // NOI18N

        canvas4.setName("canvas4"); // NOI18N

        jPanel1.setName("jPanel1"); // NOI18N

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
        jScrollPane1.setViewportView(arbolPrincipal);

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(gestiontipocampo.GestionTipoCampoApp.class).getContext().getResourceMap(GestionTipoCampoView.class);
        botonActualizarArbol.setText(resourceMap.getString("botonActualizarArbol.text")); // NOI18N
        botonActualizarArbol.setName("botonActualizarArbol"); // NOI18N
        botonActualizarArbol.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonActualizarArbolActionPerformed(evt);
            }
        });

        botonAbrirArbol.setText(resourceMap.getString("botonAbrirArbol.text")); // NOI18N
        botonAbrirArbol.setName("botonAbrirArbol"); // NOI18N
        botonAbrirArbol.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonAbrirArbolActionPerformed(evt);
            }
        });

        org.jdesktop.layout.GroupLayout jPanel1Layout = new org.jdesktop.layout.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jScrollPane1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 181, Short.MAX_VALUE)
                    .add(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .add(botonActualizarArbol)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(botonAbrirArbol, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 70, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .add(jScrollPane1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 285, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(botonActualizarArbol)
                    .add(botonAbrirArbol))
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        paneDatosAbrir.setName("paneDatosAbrir"); // NOI18N

        panePrincipal.setAutoscrolls(true);
        panePrincipal.setName("panePrincipal"); // NOI18N

        paneIncremental.setName("paneIncremental"); // NOI18N

        jLabel17.setText(resourceMap.getString("jLabel17.text")); // NOI18N
        jLabel17.setName("jLabel17"); // NOI18N
        jLabel17.setBounds(10, 10, 100, 20);
        paneIncremental.add(jLabel17, javax.swing.JLayeredPane.DEFAULT_LAYER);

        valorValorInicial.setName("valorValorInicial"); // NOI18N
        valorValorInicial.setBounds(10, 30, 100, 20);
        paneIncremental.add(valorValorInicial, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLabel19.setText(resourceMap.getString("jLabel19.text")); // NOI18N
        jLabel19.setName("jLabel19"); // NOI18N
        jLabel19.setBounds(130, 10, 100, 20);
        paneIncremental.add(jLabel19, javax.swing.JLayeredPane.DEFAULT_LAYER);

        valorIncremento.setName("valorIncremento"); // NOI18N
        valorIncremento.setBounds(130, 30, 100, 20);
        paneIncremental.add(valorIncremento, javax.swing.JLayeredPane.DEFAULT_LAYER);

        paneIncremental.setBounds(10, 20, 430, 180);
        panePrincipal.add(paneIncremental, javax.swing.JLayeredPane.DEFAULT_LAYER);

        paneTexto.setName("paneTexto"); // NOI18N

        jLabel16.setText(resourceMap.getString("jLabel16.text")); // NOI18N
        jLabel16.setName("jLabel16"); // NOI18N
        jLabel16.setBounds(10, 10, 100, 20);
        paneTexto.add(jLabel16, javax.swing.JLayeredPane.DEFAULT_LAYER);

        valorTextoLargo.setName("valorTextoLargo"); // NOI18N
        valorTextoLargo.setBounds(10, 30, 100, 20);
        paneTexto.add(valorTextoLargo, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLabel18.setText(resourceMap.getString("jLabel18.text")); // NOI18N
        jLabel18.setName("jLabel18"); // NOI18N
        jLabel18.setBounds(130, 10, 100, 20);
        paneTexto.add(jLabel18, javax.swing.JLayeredPane.DEFAULT_LAYER);

        valorTextoDefecto.setName("valorTextoDefecto"); // NOI18N
        valorTextoDefecto.setBounds(130, 30, 100, 20);
        paneTexto.add(valorTextoDefecto, javax.swing.JLayeredPane.DEFAULT_LAYER);

        paneTexto.setBounds(10, 30, 370, 130);
        panePrincipal.add(paneTexto, javax.swing.JLayeredPane.DEFAULT_LAYER);

        paneFechaHora.setName("paneFechaHora"); // NOI18N

        jLabel6.setText(resourceMap.getString("jLabel6.text")); // NOI18N
        jLabel6.setName("jLabel6"); // NOI18N
        jLabel6.setBounds(10, 30, 100, 10);
        paneFechaHora.add(jLabel6, javax.swing.JLayeredPane.DEFAULT_LAYER);

        valorPreaviso.setName("valorPreaviso"); // NOI18N
        valorPreaviso.setBounds(130, 100, 80, 20);
        paneFechaHora.add(valorPreaviso, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLabel13.setText(resourceMap.getString("jLabel13.text")); // NOI18N
        jLabel13.setName("jLabel13"); // NOI18N
        jLabel13.setBounds(130, 30, 100, 14);
        paneFechaHora.add(jLabel13, javax.swing.JLayeredPane.DEFAULT_LAYER);

        valorFechaDefecto.setText(resourceMap.getString("valorFechaDefecto.text")); // NOI18N
        valorFechaDefecto.setName("valorFechaDefecto"); // NOI18N
        valorFechaDefecto.setBounds(130, 50, 80, 20);
        paneFechaHora.add(valorFechaDefecto, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLabel14.setText(resourceMap.getString("jLabel14.text")); // NOI18N
        jLabel14.setName("jLabel14"); // NOI18N
        jLabel14.setBounds(130, 80, 100, 14);
        paneFechaHora.add(jLabel14, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLabel15.setText(resourceMap.getString("jLabel15.text")); // NOI18N
        jLabel15.setName("jLabel15"); // NOI18N
        jLabel15.setBounds(10, 80, 100, 14);
        paneFechaHora.add(jLabel15, javax.swing.JLayeredPane.DEFAULT_LAYER);

        radioFechaHoraSi.setSelected(true);
        radioFechaHoraSi.setText(resourceMap.getString("radioFechaHoraSi.text")); // NOI18N
        radioFechaHoraSi.setName("radioFechaHoraSi"); // NOI18N
        radioFechaHoraSi.setBounds(10, 100, 33, 23);
        paneFechaHora.add(radioFechaHoraSi, javax.swing.JLayeredPane.DEFAULT_LAYER);

        radioFechaHoraNo.setText(resourceMap.getString("radioFechaHoraNo.text")); // NOI18N
        radioFechaHoraNo.setName("radioFechaHoraNo"); // NOI18N
        radioFechaHoraNo.setBounds(10, 120, 39, 20);
        paneFechaHora.add(radioFechaHoraNo, javax.swing.JLayeredPane.DEFAULT_LAYER);

        comboFormatoFecha.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "dd/mm/aaaa", "mm/dd/aaaa", "aaaa/dd/mm", "aaaa/mm/dd" }));
        comboFormatoFecha.setName("comboFormatoFecha"); // NOI18N
        comboFormatoFecha.setBounds(10, 50, 90, 20);
        paneFechaHora.add(comboFormatoFecha, javax.swing.JLayeredPane.DEFAULT_LAYER);

        paneFechaHora.setBounds(10, 0, 380, 180);
        panePrincipal.add(paneFechaHora, javax.swing.JLayeredPane.DEFAULT_LAYER);

        paneBinario.setName("paneBinario"); // NOI18N

        jLabel7.setText(resourceMap.getString("jLabel7.text")); // NOI18N
        jLabel7.setName("jLabel7"); // NOI18N
        jLabel7.setBounds(10, 10, 100, 20);
        paneBinario.add(jLabel7, javax.swing.JLayeredPane.DEFAULT_LAYER);

        valorNombreBinario1.setName("valorNombreBinario1"); // NOI18N
        valorNombreBinario1.setBounds(10, 30, 100, 20);
        paneBinario.add(valorNombreBinario1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLabel8.setText(resourceMap.getString("jLabel8.text")); // NOI18N
        jLabel8.setName("jLabel8"); // NOI18N
        jLabel8.setBounds(130, 10, 100, 20);
        paneBinario.add(jLabel8, javax.swing.JLayeredPane.DEFAULT_LAYER);

        valorOpcionBinaria1.setName("valorOpcionBinaria1"); // NOI18N
        valorOpcionBinaria1.setBounds(130, 30, 100, 20);
        paneBinario.add(valorOpcionBinaria1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLabel9.setText(resourceMap.getString("jLabel9.text")); // NOI18N
        jLabel9.setName("jLabel9"); // NOI18N
        jLabel9.setBounds(260, 10, 100, 20);
        paneBinario.add(jLabel9, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLabel10.setText(resourceMap.getString("jLabel10.text")); // NOI18N
        jLabel10.setName("jLabel10"); // NOI18N
        jLabel10.setBounds(10, 60, 100, 20);
        paneBinario.add(jLabel10, javax.swing.JLayeredPane.DEFAULT_LAYER);

        valorNombreBinario2.setName("valorNombreBinario2"); // NOI18N
        valorNombreBinario2.setBounds(10, 80, 100, 20);
        paneBinario.add(valorNombreBinario2, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLabel11.setText(resourceMap.getString("jLabel11.text")); // NOI18N
        jLabel11.setName("jLabel11"); // NOI18N
        jLabel11.setBounds(130, 60, 100, 20);
        paneBinario.add(jLabel11, javax.swing.JLayeredPane.DEFAULT_LAYER);

        valorOpcionBinaria2.setName("valorOpcionBinaria2"); // NOI18N
        valorOpcionBinaria2.setBounds(130, 80, 100, 20);
        paneBinario.add(valorOpcionBinaria2, javax.swing.JLayeredPane.DEFAULT_LAYER);

        radioOpcionBinaria1.setSelected(true);
        radioOpcionBinaria1.setText(resourceMap.getString("radioOpcionBinaria1.text")); // NOI18N
        radioOpcionBinaria1.setName("radioOpcionBinaria1"); // NOI18N
        radioOpcionBinaria1.setBounds(260, 30, 100, 20);
        paneBinario.add(radioOpcionBinaria1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        radioOpcionBinaria2.setText(resourceMap.getString("radioOpcionBinaria2.text")); // NOI18N
        radioOpcionBinaria2.setName("radioOpcionBinaria2"); // NOI18N
        radioOpcionBinaria2.setBounds(260, 60, 100, 20);
        paneBinario.add(radioOpcionBinaria2, javax.swing.JLayeredPane.DEFAULT_LAYER);

        paneBinario.setBounds(10, 20, 410, 160);
        panePrincipal.add(paneBinario, javax.swing.JLayeredPane.DEFAULT_LAYER);

        paneNumero.setName("paneNumero"); // NOI18N

        jLabel4.setText(resourceMap.getString("jLabel4.text")); // NOI18N
        jLabel4.setName("jLabel4"); // NOI18N
        jLabel4.setBounds(10, 20, 110, 14);
        paneNumero.add(jLabel4, javax.swing.JLayeredPane.DEFAULT_LAYER);

        valorNumDecimales.setName("valorNumDecimales"); // NOI18N
        valorNumDecimales.setBounds(10, 40, 80, 20);
        paneNumero.add(valorNumDecimales, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLabel5.setText(resourceMap.getString("jLabel5.text")); // NOI18N
        jLabel5.setName("jLabel5"); // NOI18N
        jLabel5.setBounds(130, 20, 50, 14);
        paneNumero.add(jLabel5, javax.swing.JLayeredPane.DEFAULT_LAYER);

        valorNumeroMascara.setName("valorNumeroMascara"); // NOI18N
        valorNumeroMascara.setBounds(130, 40, 80, 20);
        paneNumero.add(valorNumeroMascara, javax.swing.JLayeredPane.DEFAULT_LAYER);

        valorValorDefectoNumero.setText(resourceMap.getString("valorValorDefectoNumero.text")); // NOI18N
        valorValorDefectoNumero.setName("valorValorDefectoNumero"); // NOI18N
        valorValorDefectoNumero.setBounds(250, 40, 80, 20);
        paneNumero.add(valorValorDefectoNumero, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLabel12.setText(resourceMap.getString("jLabel12.text")); // NOI18N
        jLabel12.setName("jLabel12"); // NOI18N
        jLabel12.setBounds(250, 20, 90, 14);
        paneNumero.add(jLabel12, javax.swing.JLayeredPane.DEFAULT_LAYER);

        paneNumero.setBounds(10, 10, 400, 160);
        panePrincipal.add(paneNumero, javax.swing.JLayeredPane.DEFAULT_LAYER);

        panePrincipal.setBounds(0, 90, 470, 210);
        paneDatosAbrir.add(panePrincipal, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLabel2.setText(resourceMap.getString("jLabel2.text")); // NOI18N
        jLabel2.setName("jLabel2"); // NOI18N
        jLabel2.setBounds(20, 70, 41, 14);
        paneDatosAbrir.add(jLabel2, javax.swing.JLayeredPane.DEFAULT_LAYER);

        valorNombreGeneral.setName("valorNombreGeneral"); // NOI18N
        valorNombreGeneral.setBounds(20, 90, 80, 20);
        paneDatosAbrir.add(valorNombreGeneral, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLabel3.setText(resourceMap.getString("jLabel3.text")); // NOI18N
        jLabel3.setName("jLabel3"); // NOI18N
        jLabel3.setBounds(140, 70, 58, 14);
        paneDatosAbrir.add(jLabel3, javax.swing.JLayeredPane.DEFAULT_LAYER);

        valorNota.setName("valorNota"); // NOI18N
        valorNota.setBounds(140, 90, 150, 20);
        paneDatosAbrir.add(valorNota, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLabel1.setText(resourceMap.getString("jLabel1.text")); // NOI18N
        jLabel1.setName("jLabel1"); // NOI18N
        jLabel1.setBounds(20, 10, 73, 14);
        paneDatosAbrir.add(jLabel1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        comboTipos.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "Número", "Binario", "FechaHora", "Texto", "Incremental" }));
        comboTipos.setName("comboTipos"); // NOI18N
        comboTipos.setBounds(20, 30, 82, 20);
        paneDatosAbrir.add(comboTipos, javax.swing.JLayeredPane.DEFAULT_LAYER);

        botonCancelarPanel.setText(resourceMap.getString("botonCancelarPanel.text")); // NOI18N
        botonCancelarPanel.setName("botonCancelarPanel"); // NOI18N
        botonCancelarPanel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonCancelarPanelActionPerformed(evt);
            }
        });
        botonCancelarPanel.setBounds(320, 290, 80, 23);
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
        botonEditar.setBounds(230, 290, 80, 23);
        paneDatosAbrir.add(botonEditar, javax.swing.JLayeredPane.DEFAULT_LAYER);

        org.jdesktop.layout.GroupLayout mainPanelLayout = new org.jdesktop.layout.GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
            mainPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(mainPanelLayout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(paneDatosAbrir, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 437, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(10, 10, 10)
                .add(mainPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(mainPanelLayout.createSequentialGroup()
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(mainPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(mainPanelLayout.createSequentialGroup()
                                .add(canvas1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 0, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 203, Short.MAX_VALUE)
                                .add(canvas3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                            .add(org.jdesktop.layout.GroupLayout.TRAILING, canvas2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                    .add(mainPanelLayout.createSequentialGroup()
                        .add(203, 203, 203)
                        .add(canvas4, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        mainPanelLayout.setVerticalGroup(
            mainPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(mainPanelLayout.createSequentialGroup()
                .addContainerGap()
                .add(mainPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 324, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(paneDatosAbrir, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 392, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(canvas2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(canvas1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 620, Short.MAX_VALUE)
                    .add(mainPanelLayout.createSequentialGroup()
                        .add(105, 105, 105)
                        .add(canvas3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 515, Short.MAX_VALUE)
                        .add(canvas4, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        menuBar.setName("menuBar"); // NOI18N

        fileMenu.setText(resourceMap.getString("fileMenu.text")); // NOI18N
        fileMenu.setName("fileMenu"); // NOI18N

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
            .add(statusPanelSeparator, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 652, Short.MAX_VALUE)
            .add(statusPanelLayout.createSequentialGroup()
                .addContainerGap()
                .add(statusMessageLabel)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 482, Short.MAX_VALUE)
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

        setComponent(mainPanel);
        setMenuBar(menuBar);
        setStatusBar(statusPanel);
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        abrirUnCampo();
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void mainPanelComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_mainPanelComponentShown

    }//GEN-LAST:event_mainPanelComponentShown

    private void botonActualizarArbolActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonActualizarArbolActionPerformed
        //Se actualiza el tree View
        llenarTreeView();
}//GEN-LAST:event_botonActualizarArbolActionPerformed

    private void botonEditarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botonEditarMouseClicked
        // TODO add your handling code here:
}//GEN-LAST:event_botonEditarMouseClicked

    private void botonEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonEditarActionPerformed
        // TODO add your handling code here:
}//GEN-LAST:event_botonEditarActionPerformed

    private void botonCancelarPanelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonCancelarPanelActionPerformed
        paneDatosAbrir.setVisible(false);
    }//GEN-LAST:event_botonCancelarPanelActionPerformed

    private void botonAbrirArbolActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonAbrirArbolActionPerformed
        DefaultMutableTreeNode node = (DefaultMutableTreeNode)arbolPrincipal.getLastSelectedPathComponent();
        if (node == null){
            JOptionPane.showMessageDialog(null, "¡Favor seleccionar un dato para abrir!", "", JOptionPane.ERROR_MESSAGE);
        }
        else{
            String nombre = node.toString();
            System.out.println("selecciono: " + nombre + " padre: "+ node.getParent().toString());
        }
         
    }//GEN-LAST:event_botonAbrirArbolActionPerformed

    public void abrirUnCampo() {
        frameManejoCampos ventanaManejoCampos = new frameManejoCampos("abrir");
        JFrame mainFrame = GestionTipoCampoApp.getApplication().getMainFrame();
        //coloca el frame segun como este ubicada la ventana principal
        ventanaManejoCampos.setLocationRelativeTo(mainFrame);
        ventanaManejoCampos.setVisible(true);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTree arbolPrincipal;
    private javax.swing.JButton botonAbrirArbol;
    private javax.swing.JButton botonActualizarArbol;
    private javax.swing.JButton botonCancelarPanel;
    private javax.swing.JButton botonEditar;
    private java.awt.Canvas canvas1;
    private java.awt.Canvas canvas2;
    private java.awt.Canvas canvas3;
    private java.awt.Canvas canvas4;
    private javax.swing.JComboBox comboFormatoFecha;
    private javax.swing.JComboBox comboTipos;
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
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JLayeredPane paneBinario;
    private javax.swing.JLayeredPane paneDatosAbrir;
    private javax.swing.JLayeredPane paneFechaHora;
    private javax.swing.JLayeredPane paneIncremental;
    private javax.swing.JLayeredPane paneNumero;
    private javax.swing.JLayeredPane panePrincipal;
    private javax.swing.JLayeredPane paneTexto;
    private javax.swing.JProgressBar progressBar;
    private javax.swing.JRadioButton radioFechaHoraNo;
    private javax.swing.JRadioButton radioFechaHoraSi;
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
    private javax.swing.JTextField valorNumeroMascara;
    private javax.swing.JTextField valorOpcionBinaria1;
    private javax.swing.JTextField valorOpcionBinaria2;
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
}
