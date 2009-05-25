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

import java.text.SimpleDateFormat;

/**
 *
 * @author  ea60289
 */
public class frameManejoCampos extends javax.swing.JFrame {

    //Son los macros que se usan para seleccion del combo box.//CAMBIE EL HASH CODE POR LOS NUMEROS ESTABLECIDOS
    static final int NUMERO = 1;// -1950493636;
    static final int INCREMENTAL = 5;//-1541726278;
    static final int FECHAHORA = 3; //-1299756429;
    static final int TEXTO = 4;// 80703682;
    static final int BINARIO = 2;// 1556351614;
    static final int JERARQUIA = 6;// 304540448;
    static final int LISTA = 7;
    ControladorBD conexionBD = new ControladorBD();

    /** Creates new form frameManejoCampos */
    public frameManejoCampos() {
        initComponents();
        ocultarPanes();
    }

    /** Creates new form frameManejoCampos */
    public frameManejoCampos(String tipo) {
        initComponents();
        ocultarPanes();
        //Se revisa si se debe abrir con algun valor o si es para ingresar valores nuevos
        if (tipo.equalsIgnoreCase("nuevo")) {
            //No se deben mostrar los botones de borrar ni guardar como
            botonBorrar.setVisible(false);
            botonGuardarComo.setVisible(false);
            botonBusqueda.setVisible(false);
            valorBusqueda.setVisible(false);
            jLabel20.setVisible(false);
        } else {
            if (tipo.equalsIgnoreCase("abrir")) {
                //Se deben mostrar los botones de borrar ni guardar como
                botonBorrar.setVisible(true);
                botonGuardarComo.setVisible(true);
                comboTipos.setEnabled(false);
                valorNombreGeneral.setEditable(false);
            }
        }
    }

    /**
     * Constructor que viene de abrir un dato en el tree view
     */
    public frameManejoCampos(String[] valGenerales, String[] valEspecificos) {
        initComponents();
        panePrincipal.setVisible(true);
        int tipo = Integer.parseInt(valGenerales[4]);
        comboTipos.setSelectedIndex(tipo);
        valorNombreGeneral.setText(valGenerales[1]);
        valorNota.setText(valGenerales[2]);
        switch (tipo) {
            //numero
            case (1):
                valorNumDecimales.setText(valEspecificos[1]);
                valorNumeroMascara.setText(valEspecificos[2]);
                valorValorDefectoNumero.setText(valEspecificos[3]);
                break;
            //binario
            case (2):
                valorNombreBinario1.setText(valEspecificos[1]);
                valorOpcionBinaria1.setText(valEspecificos[2]);
                valorNombreBinario2.setText(valEspecificos[3]);
                valorOpcionBinaria2.setText(valEspecificos[4]);
                if (valEspecificos[5].equals("true")) {
                    radioOpcionBinaria1.setSelected(false);
                    radioOpcionBinaria2.setSelected(true);
                } else {
                    radioOpcionBinaria1.setSelected(true);
                    radioOpcionBinaria2.setSelected(false);
                }
                break;
            //FechaHora
            case (3):
                //comboFormatoFecha.setSelectedItem(valoresEspSep[1]);
                valorFechaDefecto.setText(valEspecificos[2]);
                if (valEspecificos[3].equals("true")) {
                    radioFechaHoraSi.setSelected(true);
                    radioFechaHoraNo.setSelected(false);
                } else {
                    radioFechaHoraSi.setSelected(false);
                    radioFechaHoraNo.setSelected(true);
                }
                valorPreaviso.setText(valEspecificos[4]);
                break;
            //Texto
            case (4):
                valorTextoDefecto.setText(valEspecificos[1]);
                valorTextoLargo.setText(valEspecificos[2]);
                break;
            //Incremental
            case (5):
                valorValorInicial.setText(valEspecificos[1]);
                valorIncremento.setText(valEspecificos[2]);
                break;
            default:
                break;
        }
        ocultarPanes();
        activarPaneEspecifico(tipo);
    }

    /**
     * Activa un Pane en especifico
     * @param tipo El num de pane que va a activar
     */
    public void activarPaneEspecifico(int tipo) {
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
            case 6:
                paneJerarquia.setVisible(true);
                break;
            case 7:
                paneLista.setVisible(true);
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
        jLabel24 = new javax.swing.JLabel();
        comboNiveles = new javax.swing.JComboBox();
        botonAgregarNivel = new javax.swing.JButton();
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
        paneLista = new javax.swing.JLayeredPane();
        valorPorDefectoLista = new javax.swing.JTextField();
        jLabel25 = new javax.swing.JLabel();
        botonGuardar = new javax.swing.JButton();
        botonGuardarComo = new javax.swing.JButton();
        botonBorrar = new javax.swing.JButton();
        botonCerrar = new javax.swing.JButton();
        comboTipos = new javax.swing.JComboBox();
        botonGuardar1 = new javax.swing.JButton();
        botonGuardarComo1 = new javax.swing.JButton();
        botonBorrar1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(gestiontipocampo.GestionTipoCampoApp.class).getContext().getResourceMap(frameManejoCampos.class);
        setTitle(resourceMap.getString("Form.title")); // NOI18N
        setMinimumSize(new java.awt.Dimension(200, 200));
        setName("Form"); // NOI18N

        jLabel1.setText(resourceMap.getString("jLabel1.text")); // NOI18N
        jLabel1.setName("jLabel1"); // NOI18N

        jLabel20.setText(resourceMap.getString("jLabel20.text")); // NOI18N
        jLabel20.setName("jLabel20"); // NOI18N

        valorBusqueda.setName("valorBusqueda"); // NOI18N
        valorBusqueda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                valorBusquedaActionPerformed(evt);
            }
        });

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
        paneJerarquia.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                paneJerarquiaComponentShown(evt);
            }
        });

        labelCategorias.setText(resourceMap.getString("labelCategorias.text")); // NOI18N
        labelCategorias.setName("labelCategorias"); // NOI18N
        labelCategorias.setBounds(150, 60, 60, -1);
        paneJerarquia.add(labelCategorias, javax.swing.JLayeredPane.DEFAULT_LAYER);

        comboCategorias.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1- Categoria 1", "2- Categoria 2", "3- Categoría 3", "4- Categoría 4" }));
        comboCategorias.setEnabled(false);
        comboCategorias.setName("comboCategorias"); // NOI18N
        comboCategorias.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboCategoriasActionPerformed(evt);
            }
        });
        comboCategorias.setBounds(150, 80, 130, -1);
        paneJerarquia.add(comboCategorias, javax.swing.JLayeredPane.DEFAULT_LAYER);

        botonAgregarCategoria.setText(resourceMap.getString("botonAgregarCategoria.text")); // NOI18N
        botonAgregarCategoria.setName("botonAgregarCategoria"); // NOI18N
        botonAgregarCategoria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonAgregarCategoriaActionPerformed(evt);
            }
        });
        botonAgregarCategoria.setBounds(300, 80, 140, -1);
        paneJerarquia.add(botonAgregarCategoria, javax.swing.JLayeredPane.DEFAULT_LAYER);

        radioNivelesSi.setText(resourceMap.getString("radioNivelesSi.text")); // NOI18N
        radioNivelesSi.setName("radioNivelesSi"); // NOI18N
        radioNivelesSi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioNivelesSiActionPerformed(evt);
            }
        });
        radioNivelesSi.setBounds(10, 30, -1, -1);
        paneJerarquia.add(radioNivelesSi, javax.swing.JLayeredPane.DEFAULT_LAYER);

        radioNivelesNo.setText(resourceMap.getString("radioNivelesNo.text")); // NOI18N
        radioNivelesNo.setName("radioNivelesNo"); // NOI18N
        radioNivelesNo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioNivelesNoActionPerformed(evt);
            }
        });
        radioNivelesNo.setBounds(50, 30, 50, -1);
        paneJerarquia.add(radioNivelesNo, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLabel21.setText(resourceMap.getString("jLabel21.text")); // NOI18N
        jLabel21.setName("jLabel21"); // NOI18N
        jLabel21.setBounds(10, 10, 130, -1);
        paneJerarquia.add(jLabel21, javax.swing.JLayeredPane.DEFAULT_LAYER);

        radioCategoriasSi.setText(resourceMap.getString("radioCategoriasSi.text")); // NOI18N
        radioCategoriasSi.setName("radioCategoriasSi"); // NOI18N
        radioCategoriasSi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioCategoriasSiActionPerformed(evt);
            }
        });
        radioCategoriasSi.setBounds(10, 80, -1, -1);
        paneJerarquia.add(radioCategoriasSi, javax.swing.JLayeredPane.DEFAULT_LAYER);

        radioCategoriasNo.setText(resourceMap.getString("radioCategoriasNo.text")); // NOI18N
        radioCategoriasNo.setName("radioCategoriasNo"); // NOI18N
        radioCategoriasNo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioCategoriasNoActionPerformed(evt);
            }
        });
        radioCategoriasNo.setBounds(50, 80, 50, -1);
        paneJerarquia.add(radioCategoriasNo, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLabel22.setText(resourceMap.getString("jLabel22.text")); // NOI18N
        jLabel22.setName("jLabel22"); // NOI18N
        jLabel22.setBounds(10, 60, 80, -1);
        paneJerarquia.add(jLabel22, javax.swing.JLayeredPane.DEFAULT_LAYER);

        radioNomUnicoSi.setText(resourceMap.getString("radioNomUnicoSi.text")); // NOI18N
        radioNomUnicoSi.setName("radioNomUnicoSi"); // NOI18N
        radioNomUnicoSi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioNomUnicoSiActionPerformed(evt);
            }
        });
        radioNomUnicoSi.setBounds(10, 130, -1, -1);
        paneJerarquia.add(radioNomUnicoSi, javax.swing.JLayeredPane.DEFAULT_LAYER);

        radioNomUnicoNo.setText(resourceMap.getString("radioNomUnicoNo.text")); // NOI18N
        radioNomUnicoNo.setName("radioNomUnicoNo"); // NOI18N
        radioNomUnicoNo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioNomUnicoNoActionPerformed(evt);
            }
        });
        radioNomUnicoNo.setBounds(50, 130, 50, -1);
        paneJerarquia.add(radioNomUnicoNo, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLabel23.setText(resourceMap.getString("jLabel23.text")); // NOI18N
        jLabel23.setName("jLabel23"); // NOI18N
        jLabel23.setBounds(10, 110, 70, -1);
        paneJerarquia.add(jLabel23, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLabel24.setText(resourceMap.getString("jLabel24.text")); // NOI18N
        jLabel24.setName("jLabel24"); // NOI18N
        jLabel24.setBounds(150, 10, -1, -1);
        paneJerarquia.add(jLabel24, javax.swing.JLayeredPane.DEFAULT_LAYER);

        comboNiveles.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1- Categoria 1", "2- Categoria 2", "3- Categoría 3", "4- Categoría 4" }));
        comboNiveles.setEnabled(false);
        comboNiveles.setName("comboNiveles"); // NOI18N
        comboNiveles.setBounds(150, 30, 130, -1);
        paneJerarquia.add(comboNiveles, javax.swing.JLayeredPane.DEFAULT_LAYER);

        botonAgregarNivel.setText(resourceMap.getString("botonAgregarNivel.text")); // NOI18N
        botonAgregarNivel.setName("botonAgregarNivel"); // NOI18N
        botonAgregarNivel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonAgregarNivelActionPerformed(evt);
            }
        });
        botonAgregarNivel.setBounds(300, 30, 140, -1);
        paneJerarquia.add(botonAgregarNivel, javax.swing.JLayeredPane.DEFAULT_LAYER);

        paneJerarquia.setBounds(10, 0, 480, 160);
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

        paneBinario.setBounds(10, 0, 400, 160);
        panePrincipal.add(paneBinario, javax.swing.JLayeredPane.DEFAULT_LAYER);

        paneFechaHora.setName("paneFechaHora"); // NOI18N

        jLabel6.setText(resourceMap.getString("jLabel6.text")); // NOI18N
        jLabel6.setName("jLabel6"); // NOI18N
        jLabel6.setBounds(10, 10, 100, 10);
        paneFechaHora.add(jLabel6, javax.swing.JLayeredPane.DEFAULT_LAYER);

        valorPreaviso.setName("valorPreaviso"); // NOI18N
        valorPreaviso.setBounds(130, 80, 80, -1);
        paneFechaHora.add(valorPreaviso, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLabel13.setText(resourceMap.getString("jLabel13.text")); // NOI18N
        jLabel13.setName("jLabel13"); // NOI18N
        jLabel13.setBounds(130, 10, 100, -1);
        paneFechaHora.add(jLabel13, javax.swing.JLayeredPane.DEFAULT_LAYER);

        try {
            valorFechaDefecto.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        valorFechaDefecto.setText(resourceMap.getString("valorFechaDefecto.text")); // NOI18N
        valorFechaDefecto.setName("valorFechaDefecto"); // NOI18N
        valorFechaDefecto.setBounds(130, 30, 80, -1);
        paneFechaHora.add(valorFechaDefecto, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLabel14.setText(resourceMap.getString("jLabel14.text")); // NOI18N
        jLabel14.setName("jLabel14"); // NOI18N
        jLabel14.setBounds(130, 60, 100, -1);
        paneFechaHora.add(jLabel14, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLabel15.setText(resourceMap.getString("jLabel15.text")); // NOI18N
        jLabel15.setName("jLabel15"); // NOI18N
        jLabel15.setBounds(10, 50, 90, 20);
        paneFechaHora.add(jLabel15, javax.swing.JLayeredPane.DEFAULT_LAYER);

        radioFechaHoraSi.setSelected(true);
        radioFechaHoraSi.setText(resourceMap.getString("radioFechaHoraSi.text")); // NOI18N
        radioFechaHoraSi.setName("radioFechaHoraSi"); // NOI18N
        radioFechaHoraSi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioFechaHoraSiActionPerformed(evt);
            }
        });
        radioFechaHoraSi.setBounds(10, 80, -1, -1);
        paneFechaHora.add(radioFechaHoraSi, javax.swing.JLayeredPane.DEFAULT_LAYER);

        radioFechaHoraNo.setText(resourceMap.getString("radioFechaHoraNo.text")); // NOI18N
        radioFechaHoraNo.setName("radioFechaHoraNo"); // NOI18N
        radioFechaHoraNo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioFechaHoraNoActionPerformed(evt);
            }
        });
        radioFechaHoraNo.setBounds(10, 100, -1, 20);
        paneFechaHora.add(radioFechaHoraNo, javax.swing.JLayeredPane.DEFAULT_LAYER);

        comboFormatoFecha.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "dd/mm/aaaa", "mm/dd/aaaa", "aaaa/dd/mm", "aaaa/mm/dd" }));
        comboFormatoFecha.setName("comboFormatoFecha"); // NOI18N
        comboFormatoFecha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboFormatoFechaActionPerformed(evt);
            }
        });
        comboFormatoFecha.setBounds(10, 30, 90, -1);
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

        paneTexto.setBounds(10, 0, 360, 130);
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

        paneIncremental.setBounds(10, 0, 360, 130);
        panePrincipal.add(paneIncremental, javax.swing.JLayeredPane.DEFAULT_LAYER);

        paneNumero.setName("paneNumero"); // NOI18N

        jLabel4.setText(resourceMap.getString("jLabel4.text")); // NOI18N
        jLabel4.setName("jLabel4"); // NOI18N
        jLabel4.setBounds(20, 10, 110, 20);
        paneNumero.add(jLabel4, javax.swing.JLayeredPane.DEFAULT_LAYER);

        valorNumDecimales.setName("valorNumDecimales"); // NOI18N
        valorNumDecimales.setBounds(20, 30, 80, 20);
        paneNumero.add(valorNumDecimales, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLabel5.setText(resourceMap.getString("jLabel5.text")); // NOI18N
        jLabel5.setName("jLabel5"); // NOI18N
        jLabel5.setBounds(140, 10, 50, 20);
        paneNumero.add(jLabel5, javax.swing.JLayeredPane.DEFAULT_LAYER);

        valorNumeroMascara.setName("valorNumeroMascara"); // NOI18N
        valorNumeroMascara.setBounds(140, 30, 80, 20);
        paneNumero.add(valorNumeroMascara, javax.swing.JLayeredPane.DEFAULT_LAYER);

        valorValorDefectoNumero.setText(resourceMap.getString("valorValorDefectoNumero.text")); // NOI18N
        valorValorDefectoNumero.setName("valorValorDefectoNumero"); // NOI18N
        valorValorDefectoNumero.setBounds(260, 30, 80, 20);
        paneNumero.add(valorValorDefectoNumero, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLabel12.setText(resourceMap.getString("jLabel12.text")); // NOI18N
        jLabel12.setName("jLabel12"); // NOI18N
        jLabel12.setBounds(260, 10, 90, 20);
        paneNumero.add(jLabel12, javax.swing.JLayeredPane.DEFAULT_LAYER);

        paneNumero.setBounds(0, 0, 400, 160);
        panePrincipal.add(paneNumero, javax.swing.JLayeredPane.DEFAULT_LAYER);

        paneLista.setName("paneLista"); // NOI18N

        valorPorDefectoLista.setText(resourceMap.getString("valorPorDefectoLista.text")); // NOI18N
        valorPorDefectoLista.setName("valorPorDefectoLista"); // NOI18N
        valorPorDefectoLista.setBounds(10, 40, 110, -1);
        paneLista.add(valorPorDefectoLista, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLabel25.setText(resourceMap.getString("jLabel25.text")); // NOI18N
        jLabel25.setName("jLabel25"); // NOI18N
        jLabel25.setBounds(10, 20, 90, -1);
        paneLista.add(jLabel25, javax.swing.JLayeredPane.DEFAULT_LAYER);

        paneLista.setBounds(0, 0, 530, 160);
        panePrincipal.add(paneLista, javax.swing.JLayeredPane.DEFAULT_LAYER);

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

        comboTipos.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "Número", "Binario", "FechaHora", "Texto", "Incremental", "Jerarquía", "Lista" }));
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
        switch (comboTipos.getSelectedIndex()) {
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

    //ESTO LO COMENTE PORQUE HACE LO MISMO QUE hayCamposSinLlenar()
/*
    private boolean alertaCamposSinLlenar() {
        boolean alerta = hayCamposSinLlenar();
        if (alerta) {
            JOptionPane.showMessageDialog(this, "Hay Campos sin llenar");
        }
        return alerta;
    }
*/
    private void ocultarPanes() {
        panePrincipal.setVisible(true);
        paneNumero.setVisible(false);
        paneBinario.setVisible(false);
        paneFechaHora.setVisible(false);
        paneTexto.setVisible(false);
        paneIncremental.setVisible(false);
        paneJerarquia.setVisible(false);
        paneLista.setVisible(false);
    }

    private Object[] buscarTipoCampo(String llave) {
        ControladorBD control = new ControladorBD();
        ResultSet resultado = null;
        int tipoCampo = -1;
        String tipo = null;
        Object retorno[] = new Object[2];
        this.ocultarPanes();
        try {
            resultado = control.getResultSet("Select * from TIPOCAMPO where correlativo = " + llave);
            //resultado = consulta.seleccionarTodoTipoCampo("correlativo", llave); //consulta es una variable global de tipo consultasSQL. Es para el modelo de 3 capas
            if (resultado.next()) {
                tipoCampo = Integer.parseInt(resultado.getObject("tipo").toString());
                //JOptionPane.showMessageDialog(null, "Tipo de campo = "+tipoCampo);
                switch (tipoCampo) {
                    case 1:
                        tipo = "NUMERO";
                        paneNumero.setVisible(true);
                        break;
                    case 2:
                        tipo = "BINARIO";
                        paneBinario.setVisible(true);
                        break;
                    case 3:
                        tipo = "FECHAHORA";
                        paneFechaHora.setVisible(true);
                        break;
                    case 4:
                        tipo = "TEXTO";
                        paneTexto.setVisible(true);
                        break;
                    case 5:
                        tipo = "INCREMENTAL";
                        paneIncremental.setVisible(true);
                        break;
                    case 6:
                        tipo = "JERARQUIA";
                        break;
                    case 7:
                        tipo = "LISTA";
                        break;
                    default:

                        break;
                }
            }
            resultado = control.getResultSet("Select * from " + tipo + " where correlativo = " + llave);
            //resultado = consulta.seleccionarTodoGenerico(tipo, "correlativo, llave); // para modelo 3 capas
        } catch (SQLException e) {
            System.out.println("*SQL Exception: *" + e.toString());
        }
        retorno[0] = tipoCampo;
        retorno[1] = resultado;
        return retorno;
    }

private void botonBusquedaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonBusquedaActionPerformed
    frameBusqueda ventanaBusqueda = new frameBusqueda(this);
    ventanaBusqueda.setLocationRelativeTo(this);
    ventanaBusqueda.setVisible(true);
    ventanaBusqueda.llenarTabla(valorBusqueda);

}//GEN-LAST:event_botonBusquedaActionPerformed

private void botonGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonGuardarActionPerformed
    if (hayCamposSinLlenar()) {
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
        this.dispose();
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
            //
            if (resultado.next()) {
                ID = resultado.getInt("correlativo");
                existe = true;
            }
        } catch (SQLException e) {
            System.out.println("*SQL Exception: *" + e.toString());
        }
        /*
        //Si este tipocampo ya existe en la base de datos hay que actualizar los campos solamente
        if (existe) {
        JOptionPane.showConfirmDialog(null, "Ya existe campo con este nombre!");

        //Por ahora no la boten, por si la ocupo mas tarde
        //conexionBD.getResultSet("Update TIPOCAMPO set nombre = '" + this.valorNombreGeneral.getText() + "', descripcion = '" + this.valorNota.getText() + "' where correlativo = " + ID);
        }
        else { //Si no existe en la base hay que agregar una entrada nueva
        }
         */
        SimpleDateFormat fecha = new SimpleDateFormat("dd/MM/yyyy");
        switch (comboTipos.getSelectedIndex()) {
            case NUMERO: //HashCode para Número
                if (existe) {
                    conexionBD.doUpdate("Update TIPOCAMPO set descripcion = '" + this.valorNota.getText() + "' where correlativo = " + ID);
                    conexionBD.doUpdate("Update NUMERO set numeroDecimales = '" + this.valorNumDecimales.getText() + "', mascara = '" + this.valorNumeroMascara.getText() + "', valorDefecto = '" + this.valorValorDefectoNumero.getText() + "' where correlativo = " + ID);
                    limpiarValoresNumero();
                    System.out.println("Modifica");
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
                    conexionBD.doUpdate("Insert Into NUMERO (correlativo, numeroDecimales, mascara, valorDefecto) VALUES (" + ID + ", " + this.valorNumDecimales.getText() + ", '" + this.valorNumeroMascara.getText() + "', " + this.valorValorDefectoNumero.getText() + ")");
                    limpiarValoresNumero();
                }
                break;

            case BINARIO: //HashCode para Binario
                if (existe) {
                    conexionBD.doUpdate("Update TIPOCAMPO set descripcion = '" + this.valorNota.getText() + "' where correlativo = " + ID);
                    conexionBD.doUpdate("Update BINARIO set nombre1 = '" + this.valorNombreBinario1.getText() + "', valor1 = '" + this.valorOpcionBinaria1.getText() + "' , nombre2 = '" + this.valorNombreBinario2.getText() + "' , valor2 = '" + this.valorOpcionBinaria2.getText() + "' , valorDefecto = '" + radioOpcionBinaria1.isSelected() + "' where correlativo = " + ID);
                    limpiarValoresBinario();
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
                    conexionBD.doUpdate("Insert Into BINARIO (correlativo, nombre1, valor1, nombre2, valor2, valorDefecto) VALUES (" + ID + ", '" + this.valorNombreBinario1.getText() + "', '" + this.valorOpcionBinaria1.getText() + "', '" + this.valorNombreBinario2.getText() + "', '" + this.valorOpcionBinaria2.getText() + "', '" + radioOpcionBinaria1.isSelected() + "')"/*"' where correlativo = " + ID*/); //OJO COMENTE ESTO PORQUE ME PARECE QUE NO ES NECESARIO
                    limpiarValoresBinario();
                }
                break;

            case FECHAHORA: //HashCode para FechaHora
                if (existe) {
                    conexionBD.doUpdate("Update TIPOCAMPO set descripcion = '" + this.valorNota.getText() + "' where correlativo = " + ID);
                    conexionBD.doUpdate("Update FECHAHORA set despliegue = '" + this.comboFormatoFecha.getSelectedItem().toString() + "', fechaDefecto = '" + this.valorFechaDefecto.getText() + "', preaviso = '" + this.valorPreaviso.getText() + "', vencimiento = '" + this.radioFechaHoraSi.isSelected() + "' where correlativo = " + ID);
                    limpiarValoresFechaHora();
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
                    limpiarValoresFechaHora();

                }
                break;
            case TEXTO: //HashCode para Texto
                if (existe) {
                    conexionBD.doUpdate("Update TIPOCAMPO set descripcion = '" + this.valorNota.getText() + "' where correlativo = " + ID);
                    conexionBD.doUpdate("Update TEXTO set tamano = '" + this.valorTextoLargo.getText() + "', textoDefecto = '" + this.valorTextoDefecto.getText() + "' where correlativo = " + ID);
                    limpiarValoresTexto();
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
                    limpiarValoresTexto();
                }
                break;

            case INCREMENTAL: //HashCode para Incremental
                if (existe) {
                    conexionBD.doUpdate("Update TIPOCAMPO set descripcion = '" + this.valorNota.getText() + "' where correlativo = " + ID);
                    conexionBD.doUpdate("Update INCREMENTAL set valInicial = '" + this.valorValorInicial.getText() + "', incremento = '" + this.valorIncremento.getText() + "' where correlativo = " + ID);
                    limpiarValoresIncremental();
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
                    limpiarValoresIncremental();
                }
                break;

            case JERARQUIA:
                if (existe) {
                    conexionBD.doUpdate("Update TIPOCAMPO set descripcion = '" + this.valorNota.getText() + "' where correlativo = " + ID);
                    conexionBD.doUpdate("Update JERARQUIA set repeticionNombreNodo = '" + this.radioNomUnicoSi.isSelected() + "', conCategorias = '" + this.radioCategoriasSi.isSelected() + "' , IDTIpoCategoria = '" + this.comboCategorias.getSelectedItem().toString().charAt(0) + "' where correlativo = " + ID);
                } else {
                    conexionBD.doUpdate("Insert Into TIPOCAMPO (nombre, descripcion, tipo) VALUES ('" + this.valorNombreGeneral.getText() + "', '" + this.valorNota.getText() + "', 6)");
                    try { //Se busca el ID de los datos que acaba de insertar
                        ResultSet resultado = conexionBD.getResultSet("select correlativo from TIPOCAMPO where nombre = '" + this.valorNombreGeneral.getText() + "'");
                        if (resultado.next()) {
                            ID = resultado.getInt("correlativo");
                        }
                    } catch (SQLException e) {
                        System.out.println("*SQL Exception: *" + e.toString());
                    }
                    try {
                        java.sql.Date sqlDate = new java.sql.Date(new java.util.Date().getTime());
                        //la raiz se guarada cmo -1 dado q no existe! NO CAMBIAR---> xq no simplemente null??????
                        conexionBD.doUpdate("Insert Into JERARQUIA ( correlativo,nombreJerarquia, IDNodoRaiz, repeticionNombreNodo, " + "fechaCreacion," + " conCategorias, IDTIpoCategoria) VALUES (" + ID + ", '" + this.valorNombreGeneral.getText() + "', " + -1 + ", '" + this.radioNomUnicoNo.isSelected() + "', " + sqlDate/*new Date( fecha.parse( "15/02/1982" ).getTime() ) */ + " , '" + this.radioCategoriasSi.isSelected() + "' , '" + ((MiDato) comboCategorias.getSelectedItem()).ID + "')");
                    } catch (Exception ex) {
                        Logger.getLogger(frameManejoCampos.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                //limpiarValoresJerarquia();
                break;
            case LISTA:
                if (existe) {
                    conexionBD.doUpdate("Update TIPOCAMPO set descripcion = '" + this.valorNota.getText() + "' where correlativo = " + ID);
                    conexionBD.doUpdate("Update LISTA set valorPorDefecto = '" + this.valorPorDefectoLista.getText() + "' where correlativo = " + ID);
                } else {
                    System.out.println("guarda en tipo campo la lista");
                    conexionBD.doUpdate("Insert Into TIPOCAMPO (nombre, descripcion, tipo) VALUES ('" + this.valorNombreGeneral.getText() + "', '" + this.valorNota.getText() + "', 7)");
                    try { //Se busca el ID de los datos que acaba de insertar
                        ResultSet resultado = conexionBD.getResultSet("select correlativo from TIPOCAMPO where nombre = '" + this.valorNombreGeneral.getText() + "'");
                        if (resultado.next()) {
                            ID = resultado.getInt("correlativo");
                        }
                    } catch (SQLException e) {
                        System.out.println("*SQL Exception: *" + e.toString());
                    }
                    try {
                        System.out.println("Entro a guardar Lista con ID: " + ID);
                        //java.sql.Date sqlDate = new java.sql.Date ( new java.util.Date () .getTime ()) ;
                        //guarda el miembro por defecto
                        conexionBD.doUpdate("insert into MIEMBROLISTA ( valor, IDLista, numeroElemento) " +
                                "values( '" + this.valorPorDefectoLista.getText() + "', " + ID + ", 0)");
                        int IDMiembroPorDefecto = 0;
                        //obtiene el ID del miembro que acaba de guardar
                        System.out.println("obtengo IDValorPordefecto");
                        ResultSet resultado = conexionBD.getResultSet("select correlativo from MIEMBROLISTA where valor = '" + this.valorPorDefectoLista.getText() + "'");
                        if (resultado.next()) {
                            IDMiembroPorDefecto = resultado.getInt("correlativo");
                        }
                        //guarda la lista con valor por defecto el que acaba d insertar
                        System.out.println("guardo Lista, IDDefecto: " + IDMiembroPorDefecto);
                        conexionBD.doUpdate("insert into LISTA (correlativo, IDMiembroPorDefecto, conOrden) " +
                                "values (" + ID + ", " + IDMiembroPorDefecto + ", 'False')");
                    } catch (Exception e) {
                        System.out.println("*SQL Exception: *" + e.toString());
                    }
                }
                System.out.println("abre lista de ID: " + ID);
                frameLista fLista = new frameLista(ID);
                fLista.setVisible(true);
                this.dispose();
                //limpiarCamposLista();
                break;
            default: //Si se selecciona algo raro o el campito en blanco.

                break;
        }
    //this.dispose(); //Se cierra la ventana... No me parece hacer esto, por eso lo comente: Alberto
    }

    public void limpiarValoresNumero() {
        this.valorNombreGeneral.setText("");
        this.valorNota.setText("");
        this.valorNumDecimales.setText("");
        this.valorNumeroMascara.setText("");
        this.valorValorDefectoNumero.setText("");
    }

    public void limpiarValoresBinario() {
        this.valorNombreGeneral.setText("");
        this.valorNota.setText("");
        this.valorNombreBinario1.setText("");
        this.valorOpcionBinaria1.setText("");
        this.valorNombreBinario2.setText("");
        this.valorOpcionBinaria2.setText("");
        this.radioOpcionBinaria1.setSelected(true);
        this.radioOpcionBinaria2.setSelected(false);
    }

    public void limpiarValoresFechaHora() {
        this.valorNombreGeneral.setText("");
        this.valorNota.setText("");
        this.valorFechaDefecto.setText("");
        this.valorPreaviso.setText("");
        this.radioFechaHoraSi.setSelected(true);
        this.radioFechaHoraNo.setSelected(false);
    }

    public void limpiarValoresTexto() {
        this.valorNombreGeneral.setText("");
        this.valorNota.setText("");
        this.valorTextoLargo.setText("");
        this.valorTextoDefecto.setText("");
    }

    public void limpiarValoresIncremental() {
        this.valorNombreGeneral.setText("");
        this.valorNota.setText("");
        this.valorValorInicial.setText("");
        this.valorIncremento.setText("");
    }

    public void limpiarValoresJerarquia() {
        this.valorNombreGeneral.setText("");
        this.valorNota.setText("");
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
    ocultarPanes();
    activarPaneEspecifico(comboTipos.getSelectedIndex());
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
                //System.out.println("llegue " + valorNombreGeneral.getText());
                if (resultado.next()) {
                    //System.out.println("llegue");
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
    if (hayCamposSinLlenar()) {
    } else {
        String nuevoNombre = "";
        //Strin opciones
        // nuevoNombre = JOptionPane.showInputDialog(null, "Favor ingresar el nombre con el que desea guardar los nuevos datos" );
        nuevoNombre = JOptionPane.showInputDialog(null, "Favor ingresar el nombre con el que desea guardar los nuevos datos", "Confirmación", JOptionPane.PLAIN_MESSAGE);//, null, JOptionPane.YES_NO_OPTION);
        valorNombreGeneral.setText(nuevoNombre);
        botonGuardarActionAccepted();
    }
}//GEN-LAST:event_botonGuardarComoActionPerformed

private void botonAgregarCategoriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonAgregarCategoriaActionPerformed
    String response = JOptionPane.showInputDialog(null, "Digite el nombre de la nueva categoría", "Nueva Categoría", JOptionPane.QUESTION_MESSAGE);
    frameManejoCategorias categorias;
    categorias = new frameManejoCategorias(response);
    categorias.setVisible(true);
}//GEN-LAST:event_botonAgregarCategoriaActionPerformed

private void valorBusquedaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_valorBusquedaActionPerformed
    frameBusqueda ventanaBusqueda = new frameBusqueda(this);
    ventanaBusqueda.setLocationRelativeTo(this);
    ventanaBusqueda.llenarTabla(valorBusqueda);
    //  JFrame mainFrame = frameManejoCampos.getApplication().getMainFrame();
    //coloca el frame segun como este ubicada la ventana principal
    // ventanaBusqueda.setLocationRelativeTo(mainFrame);
    ventanaBusqueda.setVisible(true);
}//GEN-LAST:event_valorBusquedaActionPerformed

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

private void paneJerarquiaComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_paneJerarquiaComponentShown
    comboCategorias.setBounds(120, 90, 90, 20);//con esto creo que me quito la maldicion del combo, porque netbeans no se va a atrever a modificar este codigo
    Modelo miModelo = new Modelo();
    comboCategorias.setModel(new javax.swing.DefaultComboBoxModel(miModelo.getModeloDeCombo("select nombre, ID from TIPOCATEGORIA;")));
}//GEN-LAST:event_paneJerarquiaComponentShown

private void botonAgregarNivelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonAgregarNivelActionPerformed
    // TODO add your handling code here:
}//GEN-LAST:event_botonAgregarNivelActionPerformed

private void comboCategoriasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboCategoriasActionPerformed
    // TODO add your handling code here:
}//GEN-LAST:event_comboCategoriasActionPerformed

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
        int tipoCampo = Integer.parseInt(tipoEncontrado[0].toString());
        ResultSet resultado = (ResultSet) tipoEncontrado[1];
        try {
            ControladorBD control = new ControladorBD();
            ResultSet resultadoGeneral = control.getResultSet("Select * from TIPOCAMPO where Correlativo = " + llave);
            resultado.next();
            resultadoGeneral.next();

            if (tipoCampo <= comboTipos.getItemCount()) {
                comboTipos.setSelectedIndex(tipoCampo);
                this.valorNombreGeneral.setText(resultadoGeneral.getObject(2).toString());
                this.valorNota.setText(resultadoGeneral.getObject(3).toString());
                switch (tipoCampo) {
                    case 1:
                        this.valorNumDecimales.setText(resultado.getObject(2).toString());
                        this.valorNumeroMascara.setText(resultado.getObject(3).toString());
                        this.valorValorDefectoNumero.setText(resultado.getObject(4).toString());
                        break;
                    case 2:
                        this.valorNombreBinario1.setText(resultado.getObject(2).toString());
                        this.valorOpcionBinaria1.setText(resultado.getObject(3).toString());
                        this.valorNombreBinario2.setText(resultado.getObject(4).toString());
                        this.valorOpcionBinaria2.setText(resultado.getObject(5).toString());
                        System.out.println("valor por defecto: " + resultado.getObject(6).toString() + " d la llave: " + llave);
                        if (resultado.getObject(6).toString().equalsIgnoreCase("true")) {
                            this.radioOpcionBinaria1.setSelected(true);
                            this.radioOpcionBinaria2.setSelected(false);
                        } else {
                            this.radioOpcionBinaria1.setSelected(false);
                            this.radioOpcionBinaria2.setSelected(true);
                        }
                        break;
                    case 3:
                        this.comboFormatoFecha.setSelectedItem(resultado.getObject(2).toString());
                        this.valorFechaDefecto.setText(resultado.getObject(3).toString());
                        if (resultado.getObject(4).toString().equalsIgnoreCase("true")) {
                            this.radioFechaHoraSi.setSelected(true);
                            this.radioFechaHoraNo.setSelected(false);
                        } else {
                            this.radioFechaHoraSi.setSelected(false);
                            this.radioFechaHoraNo.setSelected(true);
                        }
                        this.valorPreaviso.setText(resultado.getObject(5).toString());
                        break;
                    case 4:
                        this.valorTextoDefecto.setText(resultado.getObject(2).toString());
                        this.valorTextoLargo.setText(resultado.getObject(3).toString());
                        break;
                    case 5:
                        this.valorValorInicial.setText(resultado.getObject(2).toString());
                        this.valorIncremento.setText(resultado.getObject(3).toString());
                        break;
                    default:

                        break;
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
    private javax.swing.JButton botonAgregarNivel;
    private javax.swing.JButton botonBorrar;
    private javax.swing.JButton botonBorrar1;
    private javax.swing.JButton botonBusqueda;
    private javax.swing.JButton botonCerrar;
    private javax.swing.JButton botonGuardar;
    private javax.swing.JButton botonGuardar1;
    private javax.swing.JButton botonGuardarComo;
    private javax.swing.JButton botonGuardarComo1;
    private javax.swing.JComboBox comboCategorias;
    private javax.swing.JComboBox comboFormatoFecha;
    private javax.swing.JComboBox comboNiveles;
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
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel labelCategorias;
    private javax.swing.JLayeredPane paneBinario;
    private javax.swing.JLayeredPane paneFechaHora;
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
    private javax.swing.JTextField valorPorDefectoLista;
    private javax.swing.JTextField valorPreaviso;
    private javax.swing.JTextField valorTextoDefecto;
    private javax.swing.JTextField valorTextoLargo;
    private javax.swing.JTextField valorValorDefectoNumero;
    private javax.swing.JTextField valorValorInicial;
    // End of variables declaration//GEN-END:variables
}
