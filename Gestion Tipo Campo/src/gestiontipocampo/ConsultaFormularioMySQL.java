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
public class ConsultaFormularioMySQL extends ConsultaFormulario {

    /**
     * Guarda un nuevo formulario en la BD
     * @param nombre
     * @param descripcion
     * @return el ID del formulario creado
     */
    public int guardaFormulario(String nombre, String descripcion) {
        int ID = -1;
        //Guarda en FORMULARIO
        this.doUpdate("Insert Into FORMULARIO (nombre, descripcion) VALUES ('" + nombre + "', '" + descripcion + "')");
        try { //Se busca el ID de los datos que acaba de insertar
            ResultSet resultado = this.getResultSet("select correlativo from FORMULARIO where nombre = '" + nombre + "'");
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
    public int agregarMiembro(int IDFormulario, String nombre, int valX, int valY, int ancho, int alto, String tipoLetra, int color, int tamanoLetra, int IDTP, int tabIndex, String estiloLetra) {
        this.doUpdate("insert into MIEMBROFORMULARIO (IDFormulario, nombre, valX, valY, ancho, alto, tipoLetra, color, tamanoLetra, IDTipoCampo, tabIndex, estiloLetra) values ('" + IDFormulario + "', '" + nombre + "', " + valX + ", " + valY + ", " + ancho + ", " + alto + ", '" + tipoLetra + "', '" + color + "', '" + tamanoLetra + "', " + IDTP + ", " + tabIndex + ", '" + estiloLetra + "');");
        int ID = -1;
        try { //Se busca el ID de los datos que acaba de insertar
            ResultSet resultado = this.getResultSet("select correlativo from MIEMBROFORMULARIO where nombre = '" + nombre + "' AND IDFormulario = " + IDFormulario + ";");
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
        this.doUpdate("delete from MIEMBROFORMULARIO where correlativo = " + ID + ";");
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
    public void updateMiembro(int ID, String nombre, int valX, int valY, int ancho, int alto, String tipoLetra, int color, int tamanoLetra, int IDTP, int tabIndex, String estiloLetra) {
        this.doUpdate("UPDATE MIEMBROFORMULARIO set nombre = '" + nombre + "', valX = " + valX + ", valY = " + valY + ", ancho = " + ancho + ", alto = " + alto + ", tipoLetra = '" + tipoLetra + "', color = " + color + ", tamanoLetra = " + tamanoLetra + ", IDTipoCampo = " + IDTP + ", tabIndex = " + tabIndex + ", estiloLetra = '" + estiloLetra + "' WHERE correlativo = " + ID + ";");
    }

    /**
     * Actualiza la posicion del componente
     * @param ID
     * @param valX
     * @param valY
     */
    public void updatePosicion(int ID, int valX, int valY) {
        this.doUpdate("UPDATE MIEMBROFORMULARIO set valX = " + valX + ", valY = " + valY + " WHERE correlativo = " + ID + ";");
    }

    /**
     * Actualiza el tab index
     * @param ID
     * @param tab
     */
    public void updateTabIndex(int ID, int tab) {
        this.doUpdate("UPDATE MIEMBROFORMULARIO set tabIndex = " + tab + " WHERE correlativo = " + ID + ";");
    }

    /**
     * Modifica el nombre del formulario
     * @param nombre
     * @param ID
     */
    public void modificarNombre(String nombre, int ID) {
        this.doUpdate("UPDATE MIEMBROFORMULARIO set nombre = '" + nombre + " WHERE correlativo = " + ID + ";");
    }

    /**
     * Modifica la descripcion del formulario
     * @param descp
     * @param ID
     */
    public void modificarDescripcion(String descp, int ID) {
        this.doUpdate("UPDATE MIEMBROFORMULARIO set descripcion = '" + descp + " WHERE correlativo = " + ID + ";");
    }

    /**
     * Obtiene los datos del formulario
     * @param correlativo
     * @return Vector con los datos del formulario en este orden: nombre, descripcion, ultimaActualizacion
     */
    public Vector obtenerDatosFormulario(int correlativo) {
        String consulta = "Select nombre, descripcion, ultimaActualizacion From FORMULARIO Where correlativo = " + correlativo;
        Vector campos = new Vector();
        campos.add("nombre");
        campos.add("descripcion");
        campos.add("ultimaActualizacion");
        Map<String, String> mapaDatos = this.getResultSetMap(consulta, campos);
        campos.clear();
        campos.add(mapaDatos.get("nombre"));
        campos.add(mapaDatos.get("descripcion"));
        campos.add(mapaDatos.get("ultimaActualizacion"));
        return campos;
    }


    //**************************Debo seguir aca.....(es para no perderme XD)
    /**
     * Obtiene todos los miembros del formulario y los retorna dentro de un vector
     * @param correlativoFormulario
     * @return
     */
    public Vector obtenerMiembros(int correlativoFormulario) {
        Vector miembros = new Vector();
        //ID, this.correlativo, nombre, valX, valY, tipoLetra, color, tamanoLetra, IDTP
        String consulta = "Select correlativo,nombre,valX,valY,ancho,alto,tipoLetra,color,tamanoLetra,IDTipoCampo,tabIndex,estiloLetra From MIEMBROFORMULARIO Where IDFormulario = " + correlativoFormulario;
        ResultSet resultado = this.getResultSet(consulta);


        try {
            while (resultado.next()) {
                miembros.add(resultado.getObject("correlativo"));
                miembros.add(resultado.getObject("nombre"));
                miembros.add(resultado.getObject("valX"));
                miembros.add(resultado.getObject("valY"));
                miembros.add(resultado.getObject("ancho"));
                miembros.add(resultado.getObject("alto"));
                miembros.add(resultado.getObject("tipoLetra"));
                miembros.add(resultado.getObject("color"));
                miembros.add(resultado.getObject("tamanoLetra"));
                miembros.add(resultado.getObject("IDTipoCampo"));
                miembros.add(resultado.getObject("tabIndex"));
                miembros.add(resultado.getObject("estiloLetra"));


            /*for (int i = 0; i < campos.size(); i++) {
            miMapa.put(campos.get(i).toString(), result.getObject(campos.get(i).toString()).toString());
            }*/
            /*  miMapa.put("correlativo",resultado.getObject("correlativo").toString());
            miMapa.put("nombre",resultado.getObject("nombre").toString());
            miMapa.put("descripcion",resultado.getObject("descripcion").toString());
            miMapa.put("ultimaActualizacion",resultado.getObject("ultimaActualizacion").toString());*/
            }
        } catch (SQLException e) {
            System.out.println("*SQL Exception: aca?*" + e.toString());
        }
        /*
        miembros.add("id");
        miembros.add("nombre");
        miembros.add("valX");
        miembros.add("valY");
        miembros.add("ancho");
        miembros.add("alto");
        miembros.add("tipoLetra");
        miembros.add("color");
        miembros.add("tamanoLetra");
        miembros.add(resultado.getObject("tabIndex"));
        Map<String, String> mapaDatos = this.getResultSetMap(consulta,miembros);
        miembros.clear();
        miembros.add(mapaDatos.get("nombre"));
        miembros.add(mapaDatos.get("nombre"));
        miembros.add(mapaDatos.get("nombre"));
        miembros.add(mapaDatos.get("descripcion"));
        miembros.add(mapaDatos.get("ultimaActualizacion"));
        miembros.add(mapaDatos.get("nombre"));
        miembros.add(mapaDatos.get("nombre"));*/
        return miembros;
    }
}
