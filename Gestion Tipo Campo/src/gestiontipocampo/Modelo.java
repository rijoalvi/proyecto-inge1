/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gestiontipocampo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

/**
 *
 * @author luiscarlosch@gmail.com
 */
public class Modelo {

    ControladorBD buscador;

    public Modelo() {
        buscador = new ControladorBD();
    }

    //comboNiveles.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "prueba", "asfdsd", "asfdasdf", "asdf" }));
    public Vector getModeloDeCombo(String consulta) {
        //  MiDato d= new MiDato("increible",2);
        Vector vectorValores = new Vector();
        try {
            ResultSet resultado = buscador.getResultSet(consulta);
            int contador = 0;
            while (resultado.next()) {
                vectorValores.add(new MiDato(resultado.getObject("nombre").toString(), Integer.parseInt(resultado.getObject("ID").toString())));
                contador++;
            }
        } catch (SQLException e) {
            System.out.println("*SQL Exception: *" + e.toString());
        }
        return vectorValores;
    }

    public Vector getModeloEnVector(String consulta, String campoTexto, String campoNumero) {
        //  MiDato d= new MiDato("increible",2);
        Vector vectorValores = new Vector();
        try {
            ResultSet resultado = buscador.getResultSet(consulta);
            int contador = 0;
            while (resultado.next()) {
                vectorValores.add(new MiDato(resultado.getObject(campoTexto).toString(), Integer.parseInt(resultado.getObject(campoNumero).toString())));
                contador++;
            }
        } catch (SQLException e) {
            System.out.println("*SQL Exception: *" + e.toString());
        }
        return vectorValores;
    }

    public Vector getModeloEnVector(String consulta, String campoTexto, String campoNumero, String campoNumero2) {
        Vector vectorValores = new Vector();
        try {
            ResultSet resultado = buscador.getResultSet(consulta);
            int contador = 0;
            while (resultado.next()) {
                vectorValores.add(new MiDato2(resultado.getObject(campoTexto).toString(), Integer.parseInt(resultado.getObject(campoNumero).toString()), Integer.parseInt(resultado.getObject(campoNumero2).toString())));
                contador++;
            }
        } catch (SQLException e) {
            System.out.println("*SQL Exception: *" + e.toString());
        }
        return vectorValores;
    }
}
