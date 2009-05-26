/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package gestiontipocampo;

import java.util.Vector;

/**
 *
 * @author Alberto
 */
public abstract class ConsultaFormulario extends ControladorBD{

    /**
     * Guarda un nuevo formulario en la BD
     * @param nombre
     * @param descripcion
     * @return el ID del formulario creado
     */
    public abstract int guardaFormulario(String nombre, String descripcion);

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
    public abstract int agregarMiembro(int IDFormulario, String nombre, int valX, int valY, int ancho, int alto, String tipoLetra, int color, int tamanoLetra, int IDTP, int tabIndex, String estiloLetra);

    /**
     * Borra un miembro formulario en la BD
     * @param ID
     */
    public abstract void borrarMiembro(int ID);

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
    public abstract void updateMiembro(int ID, String nombre, int valX, int valY, int ancho, int alto, String tipoLetra, int color, int tamanoLetra, int IDTP, int tabIndex, String estiloLetra);

    /**
     * Actualiza la posicion del componente
     * @param ID
     * @param valX
     * @param valY
     */
    public abstract void updatePosicion(int ID, int valX, int valY);

    /**
     * Actualiza el tab index
     * @param ID
     * @param tab
     */
    public abstract void updateTabIndex(int ID, int tab);

    /**
     * Modifica el nombre del formulario
     * @param nombre
     * @param ID
     */
    public abstract void modificarNombre(String nombre, int ID);

    /**
     * Modifica la descripcion del formulario
     * @param descp
     * @param ID
     */
    public abstract void modificarDescripcion(String descp, int ID);

    /**
     * Obtiene los datos del formulario
     * @param correlativo
     * @return Vector con los datos del formulario en este orden: nombre, descripcion, ultimaActualizacion
     */
    public abstract Vector obtenerDatosFormulario(int correlativo);

    /**
     * Obtiene todos los miembros del formulario y los retorna dentro de un vector
     * @param correlativoFormulario
     * @return
     */
    public abstract Vector obtenerMiembros(int correlativoFormulario);

}
