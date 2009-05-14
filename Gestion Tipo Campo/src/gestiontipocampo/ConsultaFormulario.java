/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package gestiontipocampo;

import java.sql.*;
import java.util.Map;
import java.util.Vector;

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
        //Guarda en FORMULARIO
        buscador.doUpdate("Insert Into FORMULARIO (nombre, descripcion) VALUES ('" + nombre + "', '" + descripcion + "')");
        try { //Se busca el ID de los datos que acaba de insertar
            ResultSet resultado = buscador.getResultSet("select correlativo from FORMULARIO where nombre = '" + nombre + "'");
            if (resultado.next()) {
                ID = resultado.getInt("correlativo");
            }
        } catch (SQLException e) {
            System.out.println("*SQL Exception: *" + e.toString());
        }
        
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
    public int agregarMiembro(int IDFormulario, String nombre, int valX, int valY, String tipoLetra, int color, int tamanoLetra, int IDTP){
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

    /**
     * Modifica los datos del miembro
     * @param ID ID del miembro a modificar
     * @param nombre
     * @param valX
     * @param valY
     * @param tipoLetra
     * @param color
     * @param tamanoLetra
     * @param IDTP
     */
    public void updateMiembro(int ID, String nombre, int valX, int valY, String tipoLetra, int color, int tamanoLetra, int IDTP){
        buscador.doUpdate("UPDATE MIEMBROFORMULARIO set nombre = '"+ nombre +"', valX = " +valX+ ", valY = "+valY+", tipoLetra = '"+ tipoLetra+ "', color = "+color+", tamanoLetra = "+ tamanoLetra +", IDTipoCampo = "+ IDTP+" WHERE correlativo = " + ID + ";");
    }

    /**
     * Actualiza la posicion del componente
     * @param ID
     * @param valX
     * @param valY
     */
    public void updatePosicion(int ID, int valX, int valY){
        buscador.doUpdate("UPDATE MIEMBROFORMULARIO set valX = " +valX+ ", valY = "+valY+ " WHERE correlativo = " + ID + ";");
    }

    /**
     * Modifica el nombre del formulario
     * @param nombre
     * @param ID
     */
    public void modificarNombre(String nombre, int ID){
        buscador.doUpdate("UPDATE MIEMBROFORMULARIO set nombre = '"+ nombre +" WHERE correlativo = " + ID + ";");
    }

    /**
     * Modifica la descripcion del formulario
     * @param descp
     * @param ID
     */
    public void modificarDescripcion(String descp, int ID){
        buscador.doUpdate("UPDATE MIEMBROFORMULARIO set descripcion = '"+ descp +" WHERE correlativo = " + ID + ";");
    }

    /**
     * Obtiene los datos del formulario
     * @param correlativo
     * @return Vector con los datos del formulario en este orden: nombre, descripcion, ultimaActualizacion
     */
    public Vector obtenerDatosFormulario(int correlativo){
        String consulta = "Select nombre,descripcion,ultimaActualizacion From Formulario Where correlativo = " + correlativo;
        Vector campos = new Vector();
        campos.add("nombre");
        campos.add("descripcion");
        campos.add("ultimaActualizacion");
        Map<String, String> mapaDatos = buscador.getResultSetMap(consulta,campos);
        campos.clear();
        campos.add(mapaDatos.get("nombre"));
        campos.add(mapaDatos.get("descripcion"));
        campos.add(mapaDatos.get("ultimaActualizacion"));
        return campos;
    }


    //**************************Debo seguir aca.....(es para no perderme XD)
    public Vector obtenerMiembros(int correlativoFormulario){
        Vector miembros = new Vector();
        //ID, this.correlativo, nombre, valX, valY, tipoLetra, color, tamanoLetra, IDTP
        String consulta = "Select id,nombre,valX,valY,tipoLetra,color,tamanoLetra From MIEMBROLISTA Where IDFormulario = " + correlativoFormulario;
        
        miembros.add("id");
        miembros.add("nombre");
        miembros.add("valX");
        miembros.add("valY");
        miembros.add("tipoLetra");
        miembros.add("color");
        miembros.add("tamanoLetra");
        Map<String, String> mapaDatos = buscador.getResultSetMap(consulta,miembros);
        miembros.clear();
        miembros.add(mapaDatos.get("nombre"));
        miembros.add(mapaDatos.get("nombre"));
        miembros.add(mapaDatos.get("nombre"));
        miembros.add(mapaDatos.get("descripcion"));
        miembros.add(mapaDatos.get("ultimaActualizacion"));
        miembros.add(mapaDatos.get("nombre"));
        miembros.add(mapaDatos.get("nombre"));
        return miembros;
    }

}
