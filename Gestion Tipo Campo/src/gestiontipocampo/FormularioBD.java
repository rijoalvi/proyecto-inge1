/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package gestiontipocampo;

import java.sql.*;

/**
 *
 * @author Alberto
 */
public class FormularioBD {
    private ControladorBD buscador;

    ///Constructor por omision
    public FormularioBD( ){
        buscador = new ControladorBD();
    }

    public int guardaFormulario(String nombre, String descripcion){
        int ID = 0;
        //Guarda en TIPOCAMPO
        buscador.doUpdate("Insert Into TIPOCAMPO (nombre, descripcion, tipo) VALUES ('" + nombre + "', '" + descripcion + "', 8)");
        try { //Se busca el ID de los datos que acaba de insertar
            ResultSet resultado = buscador.getResultSet("select correlativo from TIPOCAMPO where nombre = '" + nombre + "'");
            if (resultado.next()) {
                ID = resultado.getInt("correlativo");
            }
        } catch (SQLException e) {
            System.out.println("*SQL Exception: *" + e.toString());
        }
        //Guarda en FORMULARIO
        buscador.doUpdate("Insert Into FORMULARIO (correlativo) VALUES (" + ID + ")");
        return ID;
    }


    public void agregarMiembro(String nombreMiembro, int posicion) {//agregar elemento a la FORMULARIO
       // buscador.doUpdate("insert into MIEMBROFORMULARIO (valor,IDFormulario, numeroElemento) values ('" + nombreMiembro + "'," + this.correlativo + ", " + posicion + ");");
    }


}
