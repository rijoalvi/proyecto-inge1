/*
 * frameManejoCampos.java
 *
 * Created on March 17, 2009, 9:39 AM
 */
package gestiontipocampo;

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
    static final int NUMERO = -1827665343;
    static final int INCREMENTAL = -1541726278;
    static final int FECHAHORA = -1299756429;
    static final int TEXTO = 80703682;
    static final int BINARIO = 1556351614;
    ControladorBD conexion = new ControladorBD();

    /** Creates new form frameManejoCampos */
    public frameManejoCampos() {
        initComponents();
        panePrincipal.setVisible(true);
        paneNumero.setVisible(false);
        paneBinario.setVisible(false);
        paneFechaHora.setVisible(false);
        paneTexto.setVisible(false);
        paneIncremental.setVisible(false);
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
        botonGuardar = new javax.swing.JButton();
        botonGuardarComo = new javax.swing.JButton();
        botonBorrar = new javax.swing.JButton();
        botonCerrar = new javax.swing.JButton();
        comboTipos = new javax.swing.JComboBox();
        botonGuardar1 = new javax.swing.JButton();
        botonGuardarComo1 = new javax.swing.JButton();
        botonBorrar1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
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

        paneBinario.setName("paneBinario"); // NOI18N

        jLabel7.setText(resourceMap.getString("jLabel7.text")); // NOI18N
        jLabel7.setName("jLabel7"); // NOI18N
        jLabel7.setBounds(10, 10, 100, 14);
        paneBinario.add(jLabel7, javax.swing.JLayeredPane.DEFAULT_LAYER);

        valorNombreBinario1.setName("valorNombreBinario1"); // NOI18N
        valorNombreBinario1.setBounds(10, 30, 80, 20);
        paneBinario.add(valorNombreBinario1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLabel8.setText(resourceMap.getString("jLabel8.text")); // NOI18N
        jLabel8.setName("jLabel8"); // NOI18N
        jLabel8.setBounds(130, 10, 100, 14);
        paneBinario.add(jLabel8, javax.swing.JLayeredPane.DEFAULT_LAYER);

        valorOpcionBinaria1.setName("valorOpcionBinaria1"); // NOI18N
        valorOpcionBinaria1.setBounds(130, 30, 80, 20);
        paneBinario.add(valorOpcionBinaria1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLabel9.setText(resourceMap.getString("jLabel9.text")); // NOI18N
        jLabel9.setName("jLabel9"); // NOI18N
        jLabel9.setBounds(240, 10, 100, 14);
        paneBinario.add(jLabel9, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLabel10.setText(resourceMap.getString("jLabel10.text")); // NOI18N
        jLabel10.setName("jLabel10"); // NOI18N
        jLabel10.setBounds(10, 60, 100, 14);
        paneBinario.add(jLabel10, javax.swing.JLayeredPane.DEFAULT_LAYER);

        valorNombreBinario2.setName("valorNombreBinario2"); // NOI18N
        valorNombreBinario2.setBounds(10, 80, 80, 20);
        paneBinario.add(valorNombreBinario2, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLabel11.setText(resourceMap.getString("jLabel11.text")); // NOI18N
        jLabel11.setName("jLabel11"); // NOI18N
        jLabel11.setBounds(130, 60, 100, 14);
        paneBinario.add(jLabel11, javax.swing.JLayeredPane.DEFAULT_LAYER);

        valorOpcionBinaria2.setName("valorOpcionBinaria2"); // NOI18N
        valorOpcionBinaria2.setBounds(130, 80, 80, 20);
        paneBinario.add(valorOpcionBinaria2, javax.swing.JLayeredPane.DEFAULT_LAYER);

        radioOpcionBinaria1.setSelected(true);
        radioOpcionBinaria1.setText(resourceMap.getString("radioOpcionBinaria1.text")); // NOI18N
        radioOpcionBinaria1.setName("radioOpcionBinaria1"); // NOI18N
        radioOpcionBinaria1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioOpcionBinaria1ActionPerformed(evt);
            }
        });
        radioOpcionBinaria1.setBounds(240, 30, 67, 23);
        paneBinario.add(radioOpcionBinaria1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        radioOpcionBinaria2.setText(resourceMap.getString("radioOpcionBinaria2.text")); // NOI18N
        radioOpcionBinaria2.setName("radioOpcionBinaria2"); // NOI18N
        radioOpcionBinaria2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioOpcionBinaria2ActionPerformed(evt);
            }
        });
        radioOpcionBinaria2.setBounds(240, 60, 93, 23);
        paneBinario.add(radioOpcionBinaria2, javax.swing.JLayeredPane.DEFAULT_LAYER);

        paneIncremental.setName("paneIncremental"); // NOI18N

        jLabel17.setText(resourceMap.getString("jLabel17.text")); // NOI18N
        jLabel17.setName("jLabel17"); // NOI18N
        jLabel17.setBounds(10, 10, 100, 14);
        paneIncremental.add(jLabel17, javax.swing.JLayeredPane.DEFAULT_LAYER);

        valorValorInicial.setName("valorValorInicial"); // NOI18N
        valorValorInicial.setBounds(10, 30, 80, 20);
        paneIncremental.add(valorValorInicial, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLabel19.setText(resourceMap.getString("jLabel19.text")); // NOI18N
        jLabel19.setName("jLabel19"); // NOI18N
        jLabel19.setBounds(130, 10, 100, 14);
        paneIncremental.add(jLabel19, javax.swing.JLayeredPane.DEFAULT_LAYER);

        valorIncremento.setName("valorIncremento"); // NOI18N
        valorIncremento.setBounds(130, 30, 80, 20);
        paneIncremental.add(valorIncremento, javax.swing.JLayeredPane.DEFAULT_LAYER);

        paneIncremental.setBounds(0, -70, 380, 130);
        paneBinario.add(paneIncremental, javax.swing.JLayeredPane.DEFAULT_LAYER);

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

        paneTexto.setName("paneTexto"); // NOI18N

        jLabel16.setText(resourceMap.getString("jLabel16.text")); // NOI18N
        jLabel16.setName("jLabel16"); // NOI18N
        jLabel16.setBounds(10, 10, 100, 14);
        paneTexto.add(jLabel16, javax.swing.JLayeredPane.DEFAULT_LAYER);

        valorTextoLargo.setName("valorTextoLargo"); // NOI18N
        valorTextoLargo.setBounds(10, 30, 80, 20);
        paneTexto.add(valorTextoLargo, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLabel18.setText(resourceMap.getString("jLabel18.text")); // NOI18N
        jLabel18.setName("jLabel18"); // NOI18N
        jLabel18.setBounds(130, 10, 100, 14);
        paneTexto.add(jLabel18, javax.swing.JLayeredPane.DEFAULT_LAYER);

        valorTextoDefecto.setName("valorTextoDefecto"); // NOI18N
        valorTextoDefecto.setBounds(130, 30, 80, 20);
        paneTexto.add(valorTextoDefecto, javax.swing.JLayeredPane.DEFAULT_LAYER);

        paneTexto.setBounds(0, 0, 380, 130);
        paneNumero.add(paneTexto, javax.swing.JLayeredPane.DEFAULT_LAYER);

        paneNumero.setBounds(0, 0, 370, 130);
        paneBinario.add(paneNumero, javax.swing.JLayeredPane.DEFAULT_LAYER);

        paneFechaHora.setName("paneFechaHora"); // NOI18N

        jLabel6.setText(resourceMap.getString("jLabel6.text")); // NOI18N
        jLabel6.setName("jLabel6"); // NOI18N
        jLabel6.setBounds(20, 10, 100, 14);
        paneFechaHora.add(jLabel6, javax.swing.JLayeredPane.DEFAULT_LAYER);

        valorPreaviso.setName("valorPreaviso"); // NOI18N
        valorPreaviso.setBounds(140, 80, 80, 20);
        paneFechaHora.add(valorPreaviso, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLabel13.setText(resourceMap.getString("jLabel13.text")); // NOI18N
        jLabel13.setName("jLabel13"); // NOI18N
        jLabel13.setBounds(140, 10, 100, 14);
        paneFechaHora.add(jLabel13, javax.swing.JLayeredPane.DEFAULT_LAYER);

        try {
            valorFechaDefecto.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        valorFechaDefecto.setText(resourceMap.getString("valorFechaDefecto.text")); // NOI18N
        valorFechaDefecto.setName("valorFechaDefecto"); // NOI18N
        valorFechaDefecto.setBounds(140, 30, 80, 20);
        paneFechaHora.add(valorFechaDefecto, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLabel14.setText(resourceMap.getString("jLabel14.text")); // NOI18N
        jLabel14.setName("jLabel14"); // NOI18N
        jLabel14.setBounds(140, 60, 100, 14);
        paneFechaHora.add(jLabel14, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLabel15.setText(resourceMap.getString("jLabel15.text")); // NOI18N
        jLabel15.setName("jLabel15"); // NOI18N
        jLabel15.setBounds(20, 60, 100, 14);
        paneFechaHora.add(jLabel15, javax.swing.JLayeredPane.DEFAULT_LAYER);

        radioFechaHoraSi.setSelected(true);
        radioFechaHoraSi.setText(resourceMap.getString("radioFechaHoraSi.text")); // NOI18N
        radioFechaHoraSi.setName("radioFechaHoraSi"); // NOI18N
        radioFechaHoraSi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioFechaHoraSiActionPerformed(evt);
            }
        });
        radioFechaHoraSi.setBounds(20, 80, 33, 23);
        paneFechaHora.add(radioFechaHoraSi, javax.swing.JLayeredPane.DEFAULT_LAYER);

        radioFechaHoraNo.setText(resourceMap.getString("radioFechaHoraNo.text")); // NOI18N
        radioFechaHoraNo.setName("radioFechaHoraNo"); // NOI18N
        radioFechaHoraNo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioFechaHoraNoActionPerformed(evt);
            }
        });
        radioFechaHoraNo.setBounds(20, 103, 39, 20);
        paneFechaHora.add(radioFechaHoraNo, javax.swing.JLayeredPane.DEFAULT_LAYER);

        comboFormatoFecha.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "dd/mm/aaaa", "mm/dd/aaaa", "aaaa/dd/mm", "aaaa/mm/dd" }));
        comboFormatoFecha.setName("comboFormatoFecha"); // NOI18N
        comboFormatoFecha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboFormatoFechaActionPerformed(evt);
            }
        });
        comboFormatoFecha.setBounds(20, 30, 90, 20);
        paneFechaHora.add(comboFormatoFecha, javax.swing.JLayeredPane.DEFAULT_LAYER);

        paneFechaHora.setBounds(0, 0, 380, 130);
        paneBinario.add(paneFechaHora, javax.swing.JLayeredPane.DEFAULT_LAYER);

        paneBinario.setBounds(0, 0, 370, 130);
        panePrincipal.add(paneBinario, javax.swing.JLayeredPane.DEFAULT_LAYER);

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

        comboTipos.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "Número", "Binario", "FechaHora", "Texto", "Incremental" }));
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
                        .addContainerGap(118, Short.MAX_VALUE)
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
                                    .add(valorNombreGeneral, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 82, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                    .add(jLabel2))
                                .add(63, 63, 63)
                                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                    .add(jLabel3)
                                    .add(valorNota, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 199, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                            .add(layout.createSequentialGroup()
                                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                    .add(jLabel1)
                                    .add(comboTipos, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 95, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 144, Short.MAX_VALUE)
                                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                    .add(layout.createSequentialGroup()
                                        .add(valorBusqueda, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 105, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                        .add(14, 14, 14)
                                        .add(botonBusqueda))
                                    .add(jLabel20)))))
                    .add(layout.createSequentialGroup()
                        .addContainerGap()
                        .add(panePrincipal, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 460, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .add(18, 18, 18)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel1)
                    .add(jLabel20))
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
                        .add(valorNombreGeneral, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .add(layout.createSequentialGroup()
                        .add(jLabel3)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(valorNota, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                .add(13, 13, 13)
                .add(panePrincipal, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 146, Short.MAX_VALUE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
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
     */
    private void alertaCamposSinLlenar() {
        JOptionPane.showMessageDialog(this, "Hay Campos sin llenar", "Warning", ERROR);
    }

private void botonBusquedaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonBusquedaActionPerformed
    frameBusqueda ventanaBusqueda = new frameBusqueda();
    //  JFrame mainFrame = frameManejoCampos.getApplication().getMainFrame();
    //coloca el frame segun como este ubicada la ventana principal
    // ventanaBusqueda.setLocationRelativeTo(mainFrame);
    ventanaBusqueda.setVisible(true);
}//GEN-LAST:event_botonBusquedaActionPerformed

private void botonGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonGuardarActionPerformed
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
}//GEN-LAST:event_botonGuardarActionPerformed

    /**
     * Esta clase determina sobre cual frame se realiza la accion de guardar y
     * hace lo necesario para guardar los cambios en la base de datos.
     */
    private void botonGuardarActionAccepted() {
        switch (comboTipos.getSelectedItem().toString().hashCode()) {
            case NUMERO: //HashCode para Número
                //if(this.valorNombre)
                break;
            case INCREMENTAL: //HashCode para Incremental

                break;
            case FECHAHORA: //HashCode para FechaHora

                break;
            case TEXTO: //HashCode para Texto

                break;
            case BINARIO: //HashCode para Binario

                break;
            default: //Si se selecciona algo raro o el campito en blanco.

                break;
        }
    }

private void botonGuardarComoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonGuardarComoActionPerformed
// TODO add your handling code here:
}//GEN-LAST:event_botonGuardarComoActionPerformed

private void botonBorrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonBorrarActionPerformed
    String[] opciones = {"Si", "No"};
    int respuesta = JOptionPane.showOptionDialog(null, "¿Realmente desea borrar este campo?", "Confirmación", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE, null, opciones, "No");

    switch (respuesta) {
        case 0:
            /*Si quiere borrar el campo*/
            break;
        case 1:
            /*No quiere borrar el campo*/
            break;
        default:
            break;
    }
}//GEN-LAST:event_botonBorrarActionPerformed

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
    switch (comboTipos.getSelectedItem().toString().hashCode()) {
        case NUMERO: //HashCode para Número
            paneNumero.setVisible(true);
            paneBinario.setVisible(false);
            paneFechaHora.setVisible(false);
            paneTexto.setVisible(false);
            paneIncremental.setVisible(false);
            break;
        case INCREMENTAL: //HashCode para Incremental
            paneNumero.setVisible(false);
            paneBinario.setVisible(false);
            paneFechaHora.setVisible(false);
            paneTexto.setVisible(false);
            paneIncremental.setVisible(true);
            break;
        case FECHAHORA: //HashCode para FechaHora
            paneNumero.setVisible(false);
            paneBinario.setVisible(false);
            paneFechaHora.setVisible(true);
            paneTexto.setVisible(false);
            paneIncremental.setVisible(false);
            break;
        case TEXTO: //HashCode para Texto
            paneNumero.setVisible(false);
            paneBinario.setVisible(false);
            paneFechaHora.setVisible(false);
            paneTexto.setVisible(true);
            paneIncremental.setVisible(false);
            break;
        case BINARIO: //HashCode para Binario
            paneNumero.setVisible(false);
            paneBinario.setVisible(true);
            paneFechaHora.setVisible(false);
            paneTexto.setVisible(false);
            paneIncremental.setVisible(false);
            break;
        default: //Si se selecciona algo raro o el campito en blanco.
            paneNumero.setVisible(false);
            paneBinario.setVisible(false);
            paneFechaHora.setVisible(false);
            paneTexto.setVisible(false);
            paneIncremental.setVisible(false);
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
    private javax.swing.JButton botonBorrar;
    private javax.swing.JButton botonBorrar1;
    private javax.swing.JButton botonBusqueda;
    private javax.swing.JButton botonCerrar;
    private javax.swing.JButton botonGuardar;
    private javax.swing.JButton botonGuardar1;
    private javax.swing.JButton botonGuardarComo;
    private javax.swing.JButton botonGuardarComo1;
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
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLayeredPane paneBinario;
    private javax.swing.JLayeredPane paneFechaHora;
    private javax.swing.JLayeredPane paneIncremental;
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
