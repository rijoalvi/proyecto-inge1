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
public class ConsultaFormulario {
    private ControladorBD buscador;

    ///Constructor por omision
    public ConsultaFormulario( ){
        buscador = new ControladorBD();
    }

    /**
     * Guarda un nuevo formulario en la BD
     * @param nombre
     * @param descripcion
     * @return el ID del formulario creado
     */
    public int guardaFormulario(String nombre, String descripcion){
        int ID = -1;
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

    /**
     * Agrega un nuevo miembro formulario a la BD
     * @param IDFormulario
     * @param nombre
     * @param valX
     * @param valY
     * @param tipoLetra
     * @param color
     * @param tamanoLetra
     * @param IDTP
     * @return El ID del nuevo miembro
     */
    public int agregarMiembro(int IDFormulario, String nombre, int valX, int valY, String tipoLetra, String color, int tamanoLetra, int IDTP){
        buscador.doUpdate("insert into MIEMBROFORMULARIO (IDFormulario, nombre, valX, valY, tipoLetra, color, tamanoLetra, IDTipoCampo) values ('"+ IDFormulario +"', '"+ nombre+"', "+ valX+", "+ valY+", '"+ tipoLetra+"', '"+  color+"', '"+ tamanoLetra+"', "+ IDTP +");");
        int ID = -1;
        try { //Se busca el ID de los datos que acaba de insertar
            ResultSet resultado = buscador.getResultSet("select correlativo from MIEMBROFORMULARIO where nombre = '" + nombre + "' AND IDFormulario = "+ IDFormulario+";");
            if (resultado.next()) {
                ID = resultado.getInt("correlativo");
            }
        } catch (SQLException e) {
            System.out.println("*SQL Exception: *" + e.toString());
        }
        return ID;
    }

    /**
     * Borra un miembro formulario en la BD
     * @param ID
     */
    public void borrarMiembro(int ID) {
        buscador.doUpdate("delete from MIEMBROFORMULARIO where correlativo = " + ID +";");
    }

    public void updateMiembro(int ID, String nombre, int valX, int valY, String tipoLetra, String color, int tamanoLetra, int IDTP){
        buscador.doUpdate("UPDATE MIEMBROFORMULARIO set nombre = '"+ nombre +"', valX = " +valX+ ", valY = "+valY+", tipoLetra = '"+ tipoLetra+ "', color = '"+color+"', tamanoLetra = "+ tamanoLetra +", IDTipoCampo = "+ IDTP+" WHERE correlativo = " + ID + ";");
    }

}
