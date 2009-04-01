/*
 * frameManejoCampos.java
 *
 * Created on March 17, 2009, 9:39 AM
 */
package gestiontipocampo;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.MaskFormatter;
import java.text.ParseException;

/**
 *
 * @author  ea60289
 */
public class frameManejoCampos extends javax.swing.JFrame {

    //Son los macros que se usan para seleccion del combo box.
    static final int NUMERO = -1950493636;
    static final int INCREMENTAL = -1541726278;
    static final int FECHAHORA = -1299756429;
    static final int TEXTO = 80703682;
    static final int BINARIO = 1556351614;
    static final int JERARQUIA = 304540448;
    ControladorBD conexionBD = new ControladorBD();

    /** Creates new form frameManejoCampos */
    public frameManejoCampos() {
        initComponents();
        panePrincipal.setVisible(true);
        paneNumero.setVisible(false);
        paneBinario.setVisible(false);
        paneFechaHora.setVisible(false);
        paneTexto.setVisible(false);
        paneIncremental.setVisible(false);
        paneJerarquia.setVisible(false);
    }

    /** Creates new form frameManejoCampos */
    public frameManejoCampos(String tipo) {
        initComponents();
        panePrincipal.setVisible(true);
        paneNumero.setVisible(false);
        paneBinario.setVisible(false);
        paneFechaHora.setVisible(false);
        paneTexto.setVisible(false);
        paneIncremental.setVisible(false);
        paneJerarquia.setVisible(false);

        //Se revisa si se debe abrir con algun valor o si es para ingresar valores nuevos
        if (tipo.equalsIgnoreCase("nuevo")) {
            //No se deben mostrar los botones de borrar ni guardar como
            botonBorrar.setVisible(false);
            botonGuardarComo.setVisible(false);
        } else {
            if (tipo.equalsIgnoreCase("abrir")) {
                //Se deben mostrar los botones de borrar ni guardar como
                botonBorrar.setVisible(true);
                botonGuardarComo.setVisible(true);
            }
        }
    }
    
    /**
     * Constructor que viene de abrir un dato en el tree view
     */
    public frameManejoCampos(String [] valGenerales, String [] valEspecificos){
        initComponents();
        panePrincipal.setVisible(true);
        int tipo = Integer.parseInt(valGenerales[4]);
        comboTipos.setSelectedIndex(tipo);
        valorNombreGeneral.setText(valGenerales[1]);
        valorNota.setText(valGenerales[2]);
        switch(tipo){
            //numero
            case(1):
                valorNumDecimales.setText(valEspecificos[1]);
                valorNumeroMascara.setText(valEspecificos[2]);
                valorValorDefectoNumero.setText(valEspecificos[3]);
                break;
            //binario
            case(2):
                valorNombreBinario1.setText(valEspecificos[1]);
                valorOpcionBinaria1.setText(valEspecificos[2]);
                valorNombreBinario2.setText(valEspecificos[3]);
                valorOpcionBinaria2.setText(valEspecificos[4]);
                if(valEspecificos[5].equals("true")){
                    radioOpcionBinaria1.setSelected(false);
                    radioOpcionBinaria2.setSelected(true);
                }else{
                    radioOpcionBinaria1.setSelected(true);
                    radioOpcionBinaria2.setSelected(false);
                }
                break;
            //FechaHora
            case(3):
                //comboFormatoFecha.setSelectedItem(valoresEspSep[1]);
                valorFechaDefecto.setText(valEspecificos[2]);
                if( valEspecificos[3].equals("true") ){
                    radioFechaHoraSi.setSelected(true);
                    radioFechaHoraNo.setSelected(false);
                }else{
                    radioFechaHoraSi.setSelected(false);
                    radioFechaHoraNo.setSelected(true);
                }
                valorPreaviso.setText(valEspecificos[4]);
                break;
            //Texto
            case(4):
                valorTextoDefecto.setText(valEspecificos[1]);
                valorTextoLargo.setText(valEspecificos[2]);
                break;
            //Incremental
            case(5):
                valorValorInicial.setText(valEspecificos[1]);
                valorIncremento.setText(valEspecificos[2]);
                break;
            default:
                break;
        }    
        ocultarPanes();
        switch (tipo) {
            case 1: 
                paneNumero.setVisible(true);
                break;
            case 2: 
                paneBinario.setVisible(true);                
                break;
            case 3: 
                paneFechaHora.setVisible(true);
                break;
            case 4: 
                paneTexto.setVisible(true);
                break;
            case 5: 
                paneIncremental.setVisible(true);
                break;
            default:
                //Si se selecciona algo raro o el campito en blanco.
                break;
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
        jLabel20 = new javax.swing.JLabel();
        valorBusqueda = new javax.swing.JTextField();
        botonBusqueda = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        valorNombreGeneral = new javax.swing.JTextField();
        valorNota = new javax.swing.JTextField();
        panePrincipal = new javax.swing.JLayeredPane();
        paneJerarquia = new javax.swing.JLayeredPane();
        labelNumTerminos = new javax.swing.JLabel();
        labelNumNiveles = new javax.swing.JLabel();
        fieldTerminos = new javax.swing.JTextField();
        fieldNiveles = new javax.swing.JTextField();
        labelNomNiveles = new javax.swing.JLabel();
        comboNiveles = new javax.swing.JComboBox();
        labelCategorias = new javax.swing.JLabel();
        comboCategorias = new javax.swing.JComboBox();
        botonConfigJerarquia = new javax.swing.JButton();
        botonAgregarCategoria = new javax.swing.JButton();
        botonListarTerminos = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        campoBusqAvanzada = new javax.swing.JTextField();
        botonBusqAvanzada = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
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
        paneTexto = new javax.swing.JLayeredPane();
        jLabel16 = new javax.swing.JLabel();
        valorTextoLargo = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        valorTextoDefecto = new javax.swing.JTextField();
        paneIncremental = new javax.swing.JLayeredPane();
        jLabel17 = new javax.swing.JLabel();
        valorValorInicial = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        valorIncremento = new javax.swing.JTextField();
        paneNumero = new javax.swing.JLayeredPane();
        jLabel4 = new javax.swing.JLabel();
        valorNumDecimales = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        valorNumeroMascara = new javax.swing.JTextField();
        valorValorDefectoNumero = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        botonGuardar = new javax.swing.JButton();
        botonGuardarComo = new javax.swing.JButton();
        botonBorrar = new javax.swing.JButton();
        botonCerrar = new javax.swing.JButton();
        comboTipos = new javax.swing.JComboBox();
        botonGuardar1 = new javax.swing.JButton();
        botonGuardarComo1 = new javax.swing.JButton();
        botonBorrar1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(200, 200));
        setName("Form"); // NOI18N

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(gestiontipocampo.GestionTipoCampoApp.class).getContext().getResourceMap(frameManejoCampos.class);
        jLabel1.setText(resourceMap.getString("jLabel1.text")); // NOI18N
        jLabel1.setName("jLabel1"); // NOI18N

        jLabel20.setText(resourceMap.getString("jLabel20.text")); // NOI18N
        jLabel20.setName("jLabel20"); // NOI18N

        valorBusqueda.setName("valorBusqueda"); // NOI18N

        botonBusqueda.setText(resourceMap.getString("botonBusqueda.text")); // NOI18N
        botonBusqueda.setName("botonBusqueda"); // NOI18N
        botonBusqueda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonBusquedaActionPerformed(evt);
            }
        });

        jLabel2.setText(resourceMap.getString("jLabel2.text")); // NOI18N
        jLabel2.setName("jLabel2"); // NOI18N

        jLabel3.setText(resourceMap.getString("jLabel3.text")); // NOI18N
        jLabel3.setName("jLabel3"); // NOI18N

        valorNombreGeneral.setName("valorNombreGeneral"); // NOI18N

        valorNota.setName("valorNota"); // NOI18N

        panePrincipal.setAutoscrolls(true);
        panePrincipal.setName("panePrincipal"); // NOI18N

        paneJerarquia.setName("paneJerarquia"); // NOI18N

        labelNumTerminos.setText(resourceMap.getString("labelNumTerminos.text")); // NOI18N
        labelNumTerminos.setName("labelNumTerminos"); // NOI18N
        labelNumTerminos.setBounds(10, 10, 100, 14);
        paneJerarquia.add(labelNumTerminos, javax.swing.JLayeredPane.DEFAULT_LAYER);

        labelNumNiveles.setText(resourceMap.getString("labelNumNiveles.text")); // NOI18N
        labelNumNiveles.setName("labelNumNiveles"); // NOI18N
        labelNumNiveles.setBounds(10, 60, 90, 14);
        paneJerarquia.add(labelNumNiveles, javax.swing.JLayeredPane.DEFAULT_LAYER);

        fieldTerminos.setText(resourceMap.getString("fieldTerminos.text")); // NOI18N
        fieldTerminos.setName("fieldTerminos"); // NOI18N
        fieldTerminos.setBounds(10, 30, 100, 20);
        paneJerarquia.add(fieldTerminos, javax.swing.JLayeredPane.DEFAULT_LAYER);

        fieldNiveles.setText(resourceMap.getString("fieldNiveles.text")); // NOI18N
        fieldNiveles.setName("fieldNiveles"); // NOI18N
        fieldNiveles.setBounds(10, 80, 100, 20);
        paneJerarquia.add(fieldNiveles, javax.swing.JLayeredPane.DEFAULT_LAYER);

        labelNomNiveles.setText(resourceMap.getString("labelNomNiveles.text")); // NOI18N
        labelNomNiveles.setName("labelNomNiveles"); // NOI18N
        labelNomNiveles.setBounds(120, 10, 94, 10);
        paneJerarquia.add(labelNomNiveles, javax.swing.JLayeredPane.DEFAULT_LAYER);

        comboNiveles.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "nombreNivel1", "nombreNivel2", "nombreNivel3", "nombreNivel4" }));
        comboNiveles.setName("comboNiveles"); // NOI18N
        comboNiveles.setBounds(120, 30, 90, 20);
        paneJerarquia.add(comboNiveles, javax.swing.JLayeredPane.DEFAULT_LAYER);

        labelCategorias.setText(resourceMap.getString("labelCategorias.text")); // NOI18N
        labelCategorias.setName("labelCategorias"); // NOI18N
        labelCategorias.setBounds(120, 60, 60, 14);
        paneJerarquia.add(labelCategorias, javax.swing.JLayeredPane.DEFAULT_LAYER);

        comboCategorias.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Categoria 1", "Categoria 2", "Categoría 3", "Categoría 4" }));
        comboCategorias.setName("comboCategorias"); // NOI18N
        comboCategorias.setBounds(120, 80, 90, 20);
        paneJerarquia.add(comboCategorias, javax.swing.JLayeredPane.DEFAULT_LAYER);

        botonConfigJerarquia.setText(resourceMap.getString("botonConfigJerarquia.text")); // NOI18N
        botonConfigJerarquia.setName("botonConfigJerarquia"); // NOI18N
        botonConfigJerarquia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonConfigJerarquiaActionPerformed(evt);
            }
        });
        botonConfigJerarquia.setBounds(240, 80, 100, 23);
        paneJerarquia.add(botonConfigJerarquia, javax.swing.JLayeredPane.DEFAULT_LAYER);

        botonAgregarCategoria.setText(resourceMap.getString("botonAgregarCategoria.text")); // NOI18N
        botonAgregarCategoria.setName("botonAgregarCategoria"); // NOI18N
        botonAgregarCategoria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonAgregarCategoriaActionPerformed(evt);
            }
        });
        botonAgregarCategoria.setBounds(350, 120, 130, 23);
        paneJerarquia.add(botonAgregarCategoria, javax.swing.JLayeredPane.DEFAULT_LAYER);

        botonListarTerminos.setText(resourceMap.getString("botonListarTerminos.text")); // NOI18N
        botonListarTerminos.setName("botonListarTerminos"); // NOI18N
        botonListarTerminos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonListarTerminosActionPerformed(evt);
            }
        });
        botonListarTerminos.setBounds(240, 120, 105, 23);
        paneJerarquia.add(botonListarTerminos, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jButton1.setText(resourceMap.getString("jButton1.text")); // NOI18N
        jButton1.setName("jButton1"); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jButton1.setBounds(10, 120, 111, 23);
        paneJerarquia.add(jButton1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jButton2.setText(resourceMap.getString("jButton2.text")); // NOI18N
        jButton2.setName("jButton2"); // NOI18N
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jButton2.setBounds(350, 80, 130, 23);
        paneJerarquia.add(jButton2, javax.swing.JLayeredPane.DEFAULT_LAYER);

        campoBusqAvanzada.setText(resourceMap.getString("campoBusqAvanzada.text")); // NOI18N
        campoBusqAvanzada.setName("campoBusqAvanzada"); // NOI18N
        campoBusqAvanzada.setBounds(240, 30, 100, 20);
        paneJerarquia.add(campoBusqAvanzada, javax.swing.JLayeredPane.DEFAULT_LAYER);

        botonBusqAvanzada.setText(resourceMap.getString("botonBusqAvanzada.text")); // NOI18N
        botonBusqAvanzada.setName("botonBusqAvanzada"); // NOI18N
        botonBusqAvanzada.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonBusqAvanzadaActionPerformed(evt);
            }
        });
        botonBusqAvanzada.setBounds(350, 30, 130, 23);
        paneJerarquia.add(botonBusqAvanzada, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jButton4.setText(resourceMap.getString("jButton4.text")); // NOI18N
        jButton4.setName("jButton4"); // NOI18N
        jButton4.setBounds(150, 120, 63, 23);
        paneJerarquia.add(jButton4, javax.swing.JLayeredPane.DEFAULT_LAYER);

        paneJerarquia.setBounds(0, 0, 490, 160);
        panePrincipal.add(paneJerarquia, javax.swing.JLayeredPane.DEFAULT_LAYER);

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
        radioOpcionBinaria1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioOpcionBinaria1ActionPerformed(evt);
            }
        });
        radioOpcionBinaria1.setBounds(260, 30, 100, 20);
        paneBinario.add(radioOpcionBinaria1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        radioOpcionBinaria2.setText(resourceMap.getString("radioOpcionBinaria2.text")); // NOI18N
        radioOpcionBinaria2.setName("radioOpcionBinaria2"); // NOI18N
        radioOpcionBinaria2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioOpcionBinaria2ActionPerformed(evt);
            }
        });
        radioOpcionBinaria2.setBounds(260, 60, 100, 20);
        paneBinario.add(radioOpcionBinaria2, javax.swing.JLayeredPane.DEFAULT_LAYER);

        paneBinario.setBounds(0, 0, 410, 160);
        panePrincipal.add(paneBinario, javax.swing.JLayeredPane.DEFAULT_LAYER);

        paneFechaHora.setName("paneFechaHora"); // NOI18N

        jLabel6.setText(resourceMap.getString("jLabel6.text")); // NOI18N
        jLabel6.setName("jLabel6"); // NOI18N
        jLabel6.setBounds(10, 10, 100, 10);
        paneFechaHora.add(jLabel6, javax.swing.JLayeredPane.DEFAULT_LAYER);

        valorPreaviso.setName("valorPreaviso"); // NOI18N
        valorPreaviso.setBounds(130, 80, 80, 20);
        paneFechaHora.add(valorPreaviso, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLabel13.setText(resourceMap.getString("jLabel13.text")); // NOI18N
        jLabel13.setName("jLabel13"); // NOI18N
        jLabel13.setBounds(130, 10, 100, 14);
        paneFechaHora.add(jLabel13, javax.swing.JLayeredPane.DEFAULT_LAYER);

        try {
            valorFechaDefecto.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        valorFechaDefecto.setText(resourceMap.getString("valorFechaDefecto.text")); // NOI18N
        valorFechaDefecto.setName("valorFechaDefecto"); // NOI18N
        valorFechaDefecto.setBounds(130, 30, 80, 20);
        paneFechaHora.add(valorFechaDefecto, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLabel14.setText(resourceMap.getString("jLabel14.text")); // NOI18N
        jLabel14.setName("jLabel14"); // NOI18N
        jLabel14.setBounds(130, 60, 100, 14);
        paneFechaHora.add(jLabel14, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLabel15.setText(resourceMap.getString("jLabel15.text")); // NOI18N
        jLabel15.setName("jLabel15"); // NOI18N
        jLabel15.setBounds(10, 60, 100, 14);
        paneFechaHora.add(jLabel15, javax.swing.JLayeredPane.DEFAULT_LAYER);

        radioFechaHoraSi.setSelected(true);
        radioFechaHoraSi.setText(resourceMap.getString("radioFechaHoraSi.text")); // NOI18N
        radioFechaHoraSi.setName("radioFechaHoraSi"); // NOI18N
        radioFechaHoraSi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioFechaHoraSiActionPerformed(evt);
            }
        });
        radioFechaHoraSi.setBounds(10, 80, 33, 23);
        paneFechaHora.add(radioFechaHoraSi, javax.swing.JLayeredPane.DEFAULT_LAYER);

        radioFechaHoraNo.setText(resourceMap.getString("radioFechaHoraNo.text")); // NOI18N
        radioFechaHoraNo.setName("radioFechaHoraNo"); // NOI18N
        radioFechaHoraNo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioFechaHoraNoActionPerformed(evt);
            }
        });
        radioFechaHoraNo.setBounds(10, 100, 39, 20);
        paneFechaHora.add(radioFechaHoraNo, javax.swing.JLayeredPane.DEFAULT_LAYER);

        comboFormatoFecha.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "dd/mm/aaaa", "mm/dd/aaaa", "aaaa/dd/mm", "aaaa/mm/dd" }));
        comboFormatoFecha.setName("comboFormatoFecha"); // NOI18N
        comboFormatoFecha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboFormatoFechaActionPerformed(evt);
            }
        });
        comboFormatoFecha.setBounds(10, 30, 90, 20);
        paneFechaHora.add(comboFormatoFecha, javax.swing.JLayeredPane.DEFAULT_LAYER);

        paneFechaHora.setBounds(0, 0, 380, 130);
        panePrincipal.add(paneFechaHora, javax.swing.JLayeredPane.DEFAULT_LAYER);

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

        paneTexto.setBounds(0, 0, 370, 130);
        panePrincipal.add(paneTexto, javax.swing.JLayeredPane.DEFAULT_LAYER);

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

        paneIncremental.setBounds(0, 0, 370, 130);
        panePrincipal.add(paneIncremental, javax.swing.JLayeredPane.DEFAULT_LAYER);

        paneNumero.setName("paneNumero"); // NOI18N

        jLabel4.setText(resourceMap.getString("jLabel4.text")); // NOI18N
        jLabel4.setName("jLabel4"); // NOI18N
        jLabel4.setBounds(10, 10, 110, 14);
        paneNumero.add(jLabel4, javax.swing.JLayeredPane.DEFAULT_LAYER);

        valorNumDecimales.setName("valorNumDecimales"); // NOI18N
        valorNumDecimales.setBounds(10, 30, 80, 20);
        paneNumero.add(valorNumDecimales, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLabel5.setText(resourceMap.getString("jLabel5.text")); // NOI18N
        jLabel5.setName("jLabel5"); // NOI18N
        jLabel5.setBounds(130, 10, 50, 14);
        paneNumero.add(jLabel5, javax.swing.JLayeredPane.DEFAULT_LAYER);

        valorNumeroMascara.setName("valorNumeroMascara"); // NOI18N
        valorNumeroMascara.setBounds(130, 30, 80, 20);
        paneNumero.add(valorNumeroMascara, javax.swing.JLayeredPane.DEFAULT_LAYER);

        valorValorDefectoNumero.setText(resourceMap.getString("valorValorDefectoNumero.text")); // NOI18N
        valorValorDefectoNumero.setName("valorValorDefectoNumero"); // NOI18N
        valorValorDefectoNumero.setBounds(250, 30, 80, 20);
        paneNumero.add(valorValorDefectoNumero, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLabel12.setText(resourceMap.getString("jLabel12.text")); // NOI18N
        jLabel12.setName("jLabel12"); // NOI18N
        jLabel12.setBounds(250, 10, 90, 14);
        paneNumero.add(jLabel12, javax.swing.JLayeredPane.DEFAULT_LAYER);

        paneNumero.setBounds(0, 0, 400, 160);
        panePrincipal.add(paneNumero, javax.swing.JLayeredPane.DEFAULT_LAYER);

        botonGuardar.setText(resourceMap.getString("botonGuardar.text")); // NOI18N
        botonGuardar.setName("botonGuardar"); // NOI18N
        botonGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonGuardarActionPerformed(evt);
            }
        });

        botonGuardarComo.setText(resourceMap.getString("botonGuardarComo.text")); // NOI18N
        botonGuardarComo.setName("botonGuardarComo"); // NOI18N
        botonGuardarComo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonGuardarComoActionPerformed(evt);
            }
        });

        botonBorrar.setText(resourceMap.getString("botonBorrar.text")); // NOI18N
        botonBorrar.setName("botonBorrar"); // NOI18N
        botonBorrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonBorrarActionPerformed(evt);
            }
        });

        botonCerrar.setText(resourceMap.getString("botonCerrar.text")); // NOI18N
        botonCerrar.setName("botonCerrar"); // NOI18N
        botonCerrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonCerrarActionPerformed(evt);
            }
        });

        comboTipos.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "Número", "Binario", "FechaHora", "Texto", "Incremental", "Jerarquía" }));
        comboTipos.setName("comboTipos"); // NOI18N
        comboTipos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboTiposActionPerformed(evt);
            }
        });

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                        .addContainerGap(188, Short.MAX_VALUE)
                        .add(botonBorrar)
                        .add(18, 18, 18)
                        .add(botonGuardarComo)
                        .add(18, 18, 18)
                        .add(botonGuardar)
                        .add(18, 18, 18)
                        .add(botonCerrar))
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                        .add(25, 25, 25)
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(layout.createSequentialGroup()
                                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                    .add(jLabel2)
                                    .add(valorNombreGeneral, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 100, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                                .add(63, 63, 63)
                                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                    .add(jLabel3)
                                    .add(valorNota, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 199, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                            .add(layout.createSequentialGroup()
                                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                    .add(jLabel1)
                                    .add(comboTipos, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 95, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 214, Short.MAX_VALUE)
                                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                    .add(layout.createSequentialGroup()
                                        .add(valorBusqueda, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 105, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                        .add(14, 14, 14)
                                        .add(botonBusqueda))
                                    .add(jLabel20)))))
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .add(panePrincipal, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 530, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                .addContainerGap(18, Short.MAX_VALUE)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel20)
                    .add(jLabel1))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(valorBusqueda, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(botonBusqueda)
                    .add(comboTipos, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .add(15, 15, 15)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(layout.createSequentialGroup()
                        .add(jLabel2)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(valorNombreGeneral, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 20, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .add(layout.createSequentialGroup()
                        .add(jLabel3)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(valorNota, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(panePrincipal, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 162, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(11, 11, 11)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(botonGuardar)
                    .add(botonGuardarComo)
                    .add(botonBorrar)
                    .add(botonCerrar))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Muestra una ventana que dice que para realizar esta operacion tiene que llenar todos los campos.
     * @return True - Hay campos sin llenar, False - todos los campos estan llenos.
     */
    private boolean hayCamposSinLlenar() {
        boolean alerta = false;
        if (this.valorNombreGeneral.getText().isEmpty() || this.valorNota.getText().isEmpty()) {
            alerta = true;
        }
        switch (comboTipos.getSelectedItem().toString().hashCode()) {
            case NUMERO: //HashCode para Número
                if (this.valorNumDecimales.getText().isEmpty() ||
                        this.valorNumeroMascara.getText().isEmpty() ||
                        this.valorValorDefectoNumero.getText().isEmpty()) {
                    alerta = true;
                }
                break;
            case INCREMENTAL: //HashCode para Incremental
                if (this.valorIncremento.getText().isEmpty() || this.valorValorInicial.getText().isEmpty()) {
                    alerta = true;
                }
                break;
            case FECHAHORA: //HashCode para FechaHora
                if (this.valorFechaDefecto.getText().isEmpty() ||
                        this.valorPreaviso.getText().isEmpty()) {
                    alerta = true;
                }
                break;
            case TEXTO: //HashCode para Texto
                if (this.valorTextoLargo.getText().isEmpty() ||
                        this.valorTextoDefecto.getText().isEmpty()) {
                    alerta = true;
                }
                break;
            case BINARIO: //HashCode para Binario
                if (this.valorNombreBinario1.getText().isEmpty() ||
                        this.valorNombreBinario2.getText().isEmpty() ||
                        this.valorOpcionBinaria1.getText().isEmpty() ||
                        this.valorOpcionBinaria2.getText().isEmpty()) {
                    alerta = true;
                }
                break;
            default: //Si se selecciona algo raro o el campito en blanco.

                break;
        }
        if (alerta) {
            JOptionPane.showMessageDialog(this, "Hay Campos sin llenar");
        }
        return alerta;
    }

    private boolean alertaCamposSinLlenar() {
        boolean alerta = hayCamposSinLlenar();
        if (alerta) {
            JOptionPane.showMessageDialog(this, "Hay Campos sin llenar");
        }
        return alerta;
    }

    private void ocultarPanes() {
        paneNumero.setVisible(false);
        paneBinario.setVisible(false);
        paneFechaHora.setVisible(false);
        paneTexto.setVisible(false);
        paneIncremental.setVisible(false);
        paneJerarquia.setVisible(false);

    }

    private Object[] buscarTipoCampo(String llave) {
        ControladorBD control = new ControladorBD();

        ResultSet resultado = null;
        String tipoCampo = "default";
        Object retorno[] = new Object[2];
        this.ocultarPanes();

        try {
            resultado = control.getResultSet("Select * from NUMERO where correlativo = " + llave);
            if (resultado.next()) {
                tipoCampo = "NUMERO";
                paneNumero.setVisible(true);
            } else {               
                resultado = control.getResultSet("Select * from BINARIO where correlativo = " + llave);
                if (resultado.next()) {
                    tipoCampo = "BINARIO";
                    paneBinario.setVisible(true);
                } else {
                    resultado = control.getResultSet("Select * from TEXTO where correlativo = " + llave);
                    if (resultado.next()) {
                        tipoCampo = "TEXTO";
                        paneTexto.setVisible(true);
                    } else {
                        resultado = control.getResultSet("Select * from FECHAHORA where correlativo = " + llave);
                        if (resultado.next()) {
                            tipoCampo = "FECHAHORA";
                            paneFechaHora.setVisible(true);
                        } else {
                            resultado = control.getResultSet("Select * from INCREMENTAL where correlativo = " + llave);
                            if (resultado.next()) {
                                tipoCampo = "INCREMENTAL";
                                paneIncremental.setVisible(true);
                            }
                        }
                    }
                }
            }
            resultado.beforeFirst();
        } catch (SQLException e) {
            System.out.println("*SQL Exception: *" + e.toString());
        }
        retorno[0] = tipoCampo;
        retorno[1] = resultado;
        return retorno;
    }

private void botonBusquedaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonBusquedaActionPerformed
    frameBusqueda ventanaBusqueda = new frameBusqueda(this);
    ventanaBusqueda.llenarTabla(valorBusqueda);
    //  JFrame mainFrame = frameManejoCampos.getApplication().getMainFrame();
    //coloca el frame segun como este ubicada la ventana principal
    // ventanaBusqueda.setLocationRelativeTo(mainFrame);
    ventanaBusqueda.setVisible(true);
}//GEN-LAST:event_botonBusquedaActionPerformed

private void botonGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonGuardarActionPerformed
    if (alertaCamposSinLlenar()) {
    } else {
        String[] opciones = {"Si", "No"};
        int respuesta = JOptionPane.showOptionDialog(null, "¿Seguro que desea guardar este campo?", "Confirmación", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE, null, opciones, "No");

        switch (respuesta) {
            case 0:
                /*Si quiere modificar el campo*/
                botonGuardarActionAccepted();
                break;
            case 1:
                /*No quiere modificar el campo*/
                break;
        }
    }
}//GEN-LAST:event_botonGuardarActionPerformed

    /**
     * Esta clase determina sobre cual frame se realiza la accion de guardar y
     * hace lo necesario para guardar los cambios en la base de datos.
     */
    private void botonGuardarActionAccepted() {
        boolean existe = false;
        int ID = 0;

        try { //Primero se busca en la base de datos si ya existe este campo
            ResultSet resultado = conexionBD.getResultSet("select correlativo from TIPOCAMPO where nombre = '" + this.valorNombreGeneral.getText() + "'");
            if (resultado.next()) {
                ID = resultado.getInt("correlativo");
                existe = true;
            }
        } catch (SQLException e) {
            System.out.println("*SQL Exception: *" + e.toString());
        }
        //Si este tipocampo ya existe en la base de datos hay que actualizar los campos solamente
        if (existe) {
            JOptionPane.showConfirmDialog(null, "Ya existe campo con este nombre!");

        //Por ahora no la boten, por si la ocupo mas tarde
        //conexionBD.getResultSet("Update TIPOCAMPO set nombre = '" + this.valorNombreGeneral.getText() + "', descripcion = '" + this.valorNota.getText() + "' where correlativo = " + ID);
        } else { //Si no existe en la base hay que agregar una entrada nueva
        }

        switch (comboTipos.getSelectedItem().toString().hashCode()) {
            case NUMERO: //HashCode para Número
                if (existe) {
                    /* Si el nombre ya existe no se hace nada
                    conexionBD.getResultSet("Update NUMERO set numeroDecimal = " + this.valorNumDecimales.getText() + ", mascara = '" + this.valorNumeroMascara.getText() + "', valorDefecto = " + this.valorValorDefectoNumero.getText() + " where correlativo = " + ID);
                    this.valorNombreGeneral.setText("");
                    this.valorNota.setText("");
                    this.valorNumDecimales.setText("");
                    this.valorNumeroMascara.setText("");
                    this.valorValorDefectoNumero.setText("");
                     */
                } else {
                    conexionBD.doUpdate("Insert Into TIPOCAMPO (nombre, descripcion, tipo) VALUES ('" + this.valorNombreGeneral.getText() + "', '" + this.valorNota.getText() + "', 1)");
                    try { //Se busca el ID de los datos que acaba de insertar
                        ResultSet resultado = conexionBD.getResultSet("select correlativo from TIPOCAMPO where nombre = '" + this.valorNombreGeneral.getText() + "'");

                        if (resultado.next()) {
                            ID = resultado.getInt("correlativo");
                        }
                    } catch (SQLException e) {
                        System.out.println("*SQL Exception: *" + e.toString());
                    }
                    conexionBD.doUpdate("Insert Into NUMERO (correlativo, numeroDecimal, mascara, valorDefecto) VALUES (" + ID + ", " + this.valorNumDecimales.getText() + ", '" + this.valorNumeroMascara.getText() + "', " + this.valorValorDefectoNumero.getText() + ")");
                    this.valorNombreGeneral.setText("");
                    this.valorNota.setText("");
                    this.valorNumDecimales.setText("");
                    this.valorNumeroMascara.setText("");
                    this.valorValorDefectoNumero.setText("");
                }
                break;
            case INCREMENTAL: //HashCode para Incremental
                if (existe) {
                } else {
                    conexionBD.doUpdate("Insert Into TIPOCAMPO (nombre, descripcion, tipo) VALUES ('" + this.valorNombreGeneral.getText() + "', '" + this.valorNota.getText() + "', 5)");
                    try { //Se busca el ID de los datos que acaba de insertar
                        ResultSet resultado = conexionBD.getResultSet("select correlativo from TIPOCAMPO where nombre = '" + this.valorNombreGeneral.getText() + "'");

                        if (resultado.next()) {
                            ID = resultado.getInt("correlativo");
                        }
                    } catch (SQLException e) {
                        System.out.println("*SQL Exception: *" + e.toString());
                    }
                    conexionBD.doUpdate("Insert Into INCREMENTAL (correlativo, valInicial, incremento) VALUES (" + ID + ", " + this.valorValorInicial.getText() + ", '" + this.valorIncremento.getText() + "')");
                    this.valorNombreGeneral.setText("");
                    this.valorNota.setText("");
                    this.valorValorInicial.setText("");
                    this.valorIncremento.setText("");
                }
                break;
            case FECHAHORA: //HashCode para FechaHora
                int valorVencimiento = 0; //Se toma como que es no
                if (radioFechaHoraSi.isSelected()) {
                    valorVencimiento = 1; //Se cambia el valor
                }
                if (existe) {
                    /*
                    conexionBD.doUpdate("Update FECHAHORA set despliegue = '" + this.comboFormatoFecha.getSelectedItem().toString() + "', fechaDefecto = '" + this.valorFechaDefecto.getText() + "', preaviso = '" + this.valorPreaviso.getText() + "', vencimiento = '" + valorVencimiento + " ' where correlativo = " + ID);
                    this.valorNombreGeneral.setText("");
                    this.valorNota.setText("");
                     */
                } else {
                    conexionBD.doUpdate("Insert Into TIPOCAMPO (nombre, descripcion, tipo) VALUES ('" + this.valorNombreGeneral.getText() + "', '" + this.valorNota.getText() + "', 3)");
                    try { //Se busca el ID de los datos que acaba de insertar
                        ResultSet resultado = conexionBD.getResultSet("select correlativo from TIPOCAMPO where nombre = '" + this.valorNombreGeneral.getText() + "'");

                        if (resultado.next()) {
                            ID = resultado.getInt("correlativo");
                        }
                    } catch (SQLException e) {
                        System.out.println("*SQL Exception: *" + e.toString());
                    }
                    conexionBD.doUpdate("Insert Into FECHAHORA (correlativo, despliegue, fechaDefecto, vencimiento, preaviso) VALUES (" + ID + ", '" + this.comboFormatoFecha.getSelectedItem().toString() + "', '" + this.valorFechaDefecto.getText() + "', '" + this.radioFechaHoraSi.isSelected() + "', '" + this.valorPreaviso.getText() + "')");
                    this.valorNombreGeneral.setText("");
                    this.valorNota.setText("");
                    this.valorFechaDefecto.setText("");
                    this.valorPreaviso.setText("");
                    this.radioFechaHoraSi.setSelected(true);
                    this.radioFechaHoraNo.setSelected(false);
                }
                break;
            case TEXTO: //HashCode para Texto
                if (existe) {
                    /*
                    conexionBD.doUpdate("Update TEXTO set tamano = '" + this.valorTextoLargo.getText() + "', textoDefecto = '" + this.valorTextoDefecto.getText() + "' where correlativo = " + ID);
                    this.valorNombreGeneral.setText("");
                    this.valorNota.setText("");
                    this.valorTextoLargo.setText("");
                    this.valorTextoDefecto.setText("");
                     */
                } else {
                    conexionBD.doUpdate("Insert Into TIPOCAMPO (nombre, descripcion, tipo) VALUES ('" + this.valorNombreGeneral.getText() + "', '" + this.valorNota.getText() + "', 4)");
                    try { //Se busca el ID de los datos que acaba de insertar
                        ResultSet resultado = conexionBD.getResultSet("select correlativo from TIPOCAMPO where nombre = '" + this.valorNombreGeneral.getText() + "'");

                        if (resultado.next()) {
                            ID = resultado.getInt("correlativo");
                        }
                    } catch (SQLException e) {
                        System.out.println("*SQL Exception: *" + e.toString());
                    }
                    conexionBD.doUpdate("Insert Into TEXTO (correlativo, tamano, textoDefecto) VALUES (" + ID + ", " + this.valorTextoLargo.getText() + ", '" + this.valorTextoDefecto.getText() + "')");
                    this.valorNombreGeneral.setText("");
                    this.valorNota.setText("");
                    this.valorTextoLargo.setText("");
                    this.valorTextoDefecto.setText("");
                }
                break;
            case BINARIO: //HashCode para Binario
                if (existe) {
                } else {
                    conexionBD.doUpdate("Insert Into TIPOCAMPO (nombre, descripcion, tipo) VALUES ('" + this.valorNombreGeneral.getText() + "', '" + this.valorNota.getText() + "', 2)");
                    try { //Se busca el ID de los datos que acaba de insertar
                        ResultSet resultado = conexionBD.getResultSet("select correlativo from TIPOCAMPO where nombre = '" + this.valorNombreGeneral.getText() + "'");
                        if (resultado.next()) {
                            ID = resultado.getInt("correlativo");
                        }
                    } catch (SQLException e) {
                        System.out.println("*SQL Exception: *" + e.toString());
                    }                    
                    conexionBD.doUpdate("Insert Into BINARIO (correlativo, nombre1, valor1, nombre2, valor2, valorDefecto) VALUES (" + ID + ", '" + this.valorNombreBinario1.getText() + "', '" + this.valorOpcionBinaria1.getText() + "', '" + this.valorNombreBinario2.getText() + "', '" + this.valorOpcionBinaria2.getText() + "', '" + radioOpcionBinaria1.isSelected()  + "')");
                    this.valorNombreGeneral.setText("");
                    this.valorNota.setText("");
                    this.valorNombreBinario1.setText("");
                    this.valorOpcionBinaria1.setText("");
                    this.valorNombreBinario2.setText("");
                    this.valorOpcionBinaria2.setText("");
                    this.radioOpcionBinaria1.setSelected(true);
                    this.radioOpcionBinaria2.setSelected(false);
                }
                break;
            default: //Si se selecciona algo raro o el campito en blanco.
                //Nada supongo
                break;
        }
    //this.dispose(); //Se cierra la ventana... No me parece hacer esto, por eso lo comente: Alberto
    }

private void botonCerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonCerrarActionPerformed
    //cierra la ventana
    this.setVisible(false);
    this.dispose();
}//GEN-LAST:event_botonCerrarActionPerformed

private void comboTiposActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboTiposActionPerformed
    /*
    //Esta linea es para ver el hashCode de las nuevas opciones, no la borren.
    JOptionPane.showMessageDialog(null, comboTipos.getSelectedItem().toString().hashCode());
     */
    //La version nueva que usa case, se ve mejor que if anidado y mas facil para agregar nuevas opciones.
    ocultarPanes();
    switch (comboTipos.getSelectedItem().toString().hashCode()) {
        case NUMERO: //HashCode para Número
            paneNumero.setVisible(true);
            break;
        case INCREMENTAL: //HashCode para Incremental
            paneIncremental.setVisible(true);
            break;
        case FECHAHORA: //HashCode para FechaHora
            paneFechaHora.setVisible(true);
            break;
        case TEXTO: //HashCode para Texto
            paneTexto.setVisible(true);
            break;
        case BINARIO: //HashCode para Binario
            paneBinario.setVisible(true);
            break;
        case JERARQUIA: //HashCode para JERARQUIA
            paneJerarquia.setVisible(true);
            break;

        default: //Si se selecciona algo raro o el campito en blanco.
            break;
    }
}//GEN-LAST:event_comboTiposActionPerformed

private void radioFechaHoraSiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioFechaHoraSiActionPerformed
    if (radioFechaHoraSi.isSelected()) {
        radioFechaHoraNo.setSelected(false);
    } else {
        radioFechaHoraSi.setSelected(true);
    }
}//GEN-LAST:event_radioFechaHoraSiActionPerformed

private void radioFechaHoraNoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioFechaHoraNoActionPerformed
    if (radioFechaHoraNo.isSelected()) {
        radioFechaHoraSi.setSelected(false);
    } else {
        radioFechaHoraNo.setSelected(true);
    }
}//GEN-LAST:event_radioFechaHoraNoActionPerformed

private void comboFormatoFechaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboFormatoFechaActionPerformed
    //System.out.println(valorFechaDefecto.getFormatter().toString() );
    if (comboFormatoFecha.getSelectedItem().toString().equalsIgnoreCase("dd/mm/aaaa")) {
        //cambia formato del campo fecha
        formatoFecha("##/##/####", "31012009");
    } else {
        if (comboFormatoFecha.getSelectedItem().toString().equalsIgnoreCase("mm/dd/aaaa")) {
            //cambia formato del campo fecha
            formatoFecha("##/##/####", "01312009");
        } else {
            if (comboFormatoFecha.getSelectedItem().toString().equalsIgnoreCase("aaaa/dd/mm")) {
                //cambia formato del campo fecha
                formatoFecha("####/##/##", "20093101");
            } else {
                if (comboFormatoFecha.getSelectedItem().toString().equalsIgnoreCase("aaaa/mm/dd")) {
                    //cambia formato del campo fecha
                    formatoFecha("####/##/##", "20090131");
                }
            }
        }
    }
}//GEN-LAST:event_comboFormatoFechaActionPerformed

private void radioOpcionBinaria1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioOpcionBinaria1ActionPerformed
    if (radioOpcionBinaria1.isSelected()) {
        radioOpcionBinaria2.setSelected(false);
    } else {
        radioOpcionBinaria1.setSelected(true);
    }
}//GEN-LAST:event_radioOpcionBinaria1ActionPerformed

private void radioOpcionBinaria2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioOpcionBinaria2ActionPerformed
    if (radioOpcionBinaria2.isSelected()) {
        radioOpcionBinaria1.setSelected(false);
    } else {
        radioOpcionBinaria2.setSelected(true);
    }
}//GEN-LAST:event_radioOpcionBinaria2ActionPerformed

private void botonBorrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonBorrarActionPerformed
    String[] opciones = {"Si", "No"};
    int respuesta = JOptionPane.showOptionDialog(null, "¿Realmente desea borrar este campo?", "Confirmación", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE, null, opciones, "No");

    switch (respuesta) {
        case 0:
            /*Si quiere borrar el campo*/
            String temp;
            try {
                ControladorBD control = new ControladorBD();
                ResultSet resultado = control.getResultSet("Select * from TIPOCAMPO where nombre = " + valorNombreGeneral.getText());
                System.out.println("llegue " + valorNombreGeneral.getText());
                if (resultado.next()) {
                    System.out.println("llegue");
                    temp = resultado.getObject(1).toString();
                    ResultSet resultado2 = control.getResultSet("Delete from TIPOCAMPO where correlativo = " + temp);
                }
            } catch (SQLException e) {
                System.out.println("*SQL Exception: *" + e.toString());
            }
            break;
        case 1:
            /*No quiere borrar el campo*/
            break;
        default:
            break;
    }
}//GEN-LAST:event_botonBorrarActionPerformed

private void botonGuardarComoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonGuardarComoActionPerformed
    // TODO add your handling code here:
}//GEN-LAST:event_botonGuardarComoActionPerformed

private void botonConfigJerarquiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonConfigJerarquiaActionPerformed
    configuraJerarquia ventanaConfiguracion = new configuraJerarquia();
    ventanaConfiguracion.setVisible(true);
}//GEN-LAST:event_botonConfigJerarquiaActionPerformed

private void botonBusqAvanzadaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonBusqAvanzadaActionPerformed
    frameBusqueda ventanaBusqueda = new frameBusqueda(this);
    //ventanaBusqueda.llenarTabla(campoBusqAvanzada);
    //  JFrame mainFrame = frameManejoCampos.getApplication().getMainFrame();
    //coloca el frame segun como este ubicada la ventana principal
    // ventanaBusqueda.setLocationRelativeTo(mainFrame);
    ventanaBusqueda.setVisible(true);    // TODO add your handling code here:
}//GEN-LAST:event_botonBusqAvanzadaActionPerformed

private void botonListarTerminosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonListarTerminosActionPerformed
    String[] opciones = {"Niveles", "Alfabéticamente", "Por Fecha de Inserción"};
    int respuesta = JOptionPane.showOptionDialog(null, "¿Cómo desea que se desplieguen los resultados?", "Confirmación", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE, null, opciones, "Niveles");
    frameBuscarTerminos busqueda;
    switch (respuesta) {
        case 0:
            busqueda = new frameBuscarTerminos(0);
            busqueda.setVisible(true);

            break;
        case 1:
            busqueda = new frameBuscarTerminos(1);
            busqueda.setVisible(true);
            break;
        case 2:
            busqueda = new frameBuscarTerminos();
            busqueda.setVisible(true);
            break;
    }
}//GEN-LAST:event_botonListarTerminosActionPerformed

private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
    frameTermino termino;
    termino = new frameTermino();
    termino.setVisible(true);
}//GEN-LAST:event_jButton1ActionPerformed

private void botonAgregarCategoriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonAgregarCategoriaActionPerformed
    String response = JOptionPane.showInputDialog(null, "Digite el nombre de la nueva categoría", "Nueva Categoría", JOptionPane.QUESTION_MESSAGE);
}//GEN-LAST:event_botonAgregarCategoriaActionPerformed

private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
    // TODO add your handling code here:
}//GEN-LAST:event_jButton2ActionPerformed

    /**
     * Cambia la mascara del valorFechaDefecto
     * @param formato String que indica el formato de la máscara. I.E. ##/##/####
     */
    public void formatoFecha(String formato, String valorDefecto) {
        MaskFormatter mascara;
        try {
            valorFechaDefecto.setValue(""); // if the value is on then formatting won't occur, haven't figured it why but it might have something to do with below commented method call.
            mascara = new MaskFormatter(formato); // forma de la máscara
            mascara.setPlaceholderCharacter('_'); // use this to show __/__/____instead of empty.
            DefaultFormatterFactory factory = new DefaultFormatterFactory(mascara);  // here is the change, you transform the formatter to a factory.
            valorFechaDefecto.setFormatterFactory(factory); // and reset the text field with that!
            valorFechaDefecto.setText(valorDefecto);
        } catch (ParseException pe) {
            pe.printStackTrace();
        }
    }

    public void llenarFormularioCampos(String llave) {
        Object tipoEncontrado[] = buscarTipoCampo(llave);
        String tipoCampo = (String) tipoEncontrado[0];
        ResultSet resultado = (ResultSet) tipoEncontrado[1];
        try {
            ControladorBD control = new ControladorBD();
            ResultSet resultadoGeneral = control.getResultSet("Select * from TIPOCAMPO where Correlativo = " + llave);
            //resultado.next();
            resultadoGeneral.next();

            int i = 0;
            while (!this.comboTipos.getItemAt(i).toString().equalsIgnoreCase(tipoCampo) && comboTipos.getItemCount() >= i) {
                i++;
            //JOptionPane.showMessageDialog(null,this.comboTipos.getItemAt(i).toString()+" "+tipoCampo);
            }
            comboTipos.setSelectedIndex(i);
            this.valorNombreGeneral.setText(resultadoGeneral.getObject(2).toString());
            this.valorNota.setText(resultadoGeneral.getObject(3).toString());
            if (tipoCampo.equals("NUMERO")) {
                this.valorNumDecimales.setText(resultado.getObject(2).toString());
                this.valorNumeroMascara.setText(resultado.getObject(3).toString());
                this.valorValorDefectoNumero.setText(resultado.getObject(4).toString());
            } else if (tipoCampo.equals("INCREMENTAL")) {
                this.valorValorInicial.setText(resultado.getObject(2).toString());
                this.valorIncremento.setText(resultado.getObject(3).toString());
            } else if (tipoCampo.equals("FECHAHORA")) {
                this.comboFormatoFecha.setSelectedItem(resultado.getObject(2).toString());
                this.valorFechaDefecto.setText(resultado.getObject(3).toString());
                if (resultado.getObject(4).toString() == "true") {
                    this.radioFechaHoraSi.setSelected(true);
                    this.radioFechaHoraNo.setSelected(false);
                } else {
                    this.radioFechaHoraSi.setSelected(false);
                    this.radioFechaHoraNo.setSelected(true);
                }
                this.valorPreaviso.setText(resultado.getObject(5).toString());
            } else if (tipoCampo.equals("TEXTO")) {
                this.valorTextoDefecto.setText(resultado.getObject(2).toString());
                this.valorTextoLargo.setText(resultado.getObject(3).toString());
            } else if (tipoCampo.equals("BINARIO")) {
                this.valorNombreBinario1.setText(resultado.getObject(2).toString());
                this.valorOpcionBinaria1.setText(resultado.getObject(3).toString());
                this.valorNombreBinario2.setText(resultado.getObject(4).toString());
                this.valorOpcionBinaria2.setText(resultado.getObject(5).toString());
                if (resultado.getObject(6).toString() == "false") {
                    this.radioOpcionBinaria1.setSelected(false);
                    this.radioOpcionBinaria2.setSelected(true);
                } else {
                    this.radioOpcionBinaria1.setSelected(true);
                    this.radioOpcionBinaria2.setSelected(false);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(frameManejoCampos.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new frameManejoCampos().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botonAgregarCategoria;
    private javax.swing.JButton botonBorrar;
    private javax.swing.JButton botonBorrar1;
    private javax.swing.JButton botonBusqAvanzada;
    private javax.swing.JButton botonBusqueda;
    private javax.swing.JButton botonCerrar;
    private javax.swing.JButton botonConfigJerarquia;
    private javax.swing.JButton botonGuardar;
    private javax.swing.JButton botonGuardar1;
    private javax.swing.JButton botonGuardarComo;
    private javax.swing.JButton botonGuardarComo1;
    private javax.swing.JButton botonListarTerminos;
    private javax.swing.JTextField campoBusqAvanzada;
    private javax.swing.JComboBox comboCategorias;
    private javax.swing.JComboBox comboFormatoFecha;
    private javax.swing.JComboBox comboNiveles;
    private javax.swing.JComboBox comboTipos;
    private javax.swing.JTextField fieldNiveles;
    private javax.swing.JTextField fieldTerminos;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton4;
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
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel labelCategorias;
    private javax.swing.JLabel labelNomNiveles;
    private javax.swing.JLabel labelNumNiveles;
    private javax.swing.JLabel labelNumTerminos;
    private javax.swing.JLayeredPane paneBinario;
    private javax.swing.JLayeredPane paneFechaHora;
    private javax.swing.JLayeredPane paneIncremental;
    private javax.swing.JLayeredPane paneJerarquia;
    private javax.swing.JLayeredPane paneNumero;
    private javax.swing.JLayeredPane panePrincipal;
    private javax.swing.JLayeredPane paneTexto;
    private javax.swing.JRadioButton radioFechaHoraNo;
    private javax.swing.JRadioButton radioFechaHoraSi;
    private javax.swing.JRadioButton radioOpcionBinaria1;
    private javax.swing.JRadioButton radioOpcionBinaria2;
    private javax.swing.JTextField valorBusqueda;
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
}
