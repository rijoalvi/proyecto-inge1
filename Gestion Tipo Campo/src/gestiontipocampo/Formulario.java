/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package gestiontipocampo;

import java.util.*;


/**
 *
 * @author Alberto
 */
public class Formulario{
    protected int correlativo;
    protected String nombre;
    protected String descripcion;
    protected String ultimaActualizacion;
    protected ControladorBD buscador;
    private SortedSet miembrosFormulario;
    public boolean ordenPersonalizado;
    public ConsultaFormulario formBD;

    public Formulario( String nombre, String desc) {
        this.nombre = nombre;
        this.descripcion = desc;
        formBD = new ConsultaFormulario( );
        this.correlativo = formBD.guardaFormulario(nombre, descripcion);
        miembrosFormulario = new TreeSet();
    }

    /**
     * Devuelve todos los datos de los miembros del fomulario
     * @return
     */
    public SortedSet getMiembroFormularioSet() {
        return this.miembrosFormulario;
    }

    /**
     * Agrega un nuevo miembro al formulario
     * @param nombre
     * @param valX
     * @param valY
     * @param tipoLetra
     * @param color
     * @param tamañoLetra
     */
    public int agregarMiembro(String nombre, int valX, int valY, String tipoLetra, int color, int tamanoLetra, int IDTP){
        //agrega en la BD el dato nuevo
        int ID = formBD.agregarMiembro(this.correlativo, nombre, valX, valY, tipoLetra, color, tamanoLetra, IDTP);
        //crea una instancia del miembro
        MiembroFormulario datoNuevo = new MiembroFormulario(ID, this.correlativo, nombre, valX, valY, tipoLetra, color, tamanoLetra, IDTP);
        miembrosFormulario.add(datoNuevo);
        return ID;
    }

    /**
     * Borra a un componente del formulario
     * @param nombre Nombre del componente a eliminar
     */
    public void borrarMiembro(String nombre) {
        int ID = -1;
        Object[] vecMiembros = miembrosFormulario.toArray();
        for(int i = 0; i< vecMiembros.length; ++i){
            if(((MiembroFormulario)vecMiembros[i]).getNombre().equalsIgnoreCase(nombre)){
                ID = ((MiembroFormulario)vecMiembros[i]).getID();
                //borra el elemento hay q borrar de la BD tamb
                miembrosFormulario.remove(vecMiembros[i]);
                //termina el for
                i = vecMiembros.length;
            }
        }
        if(ID != -1)
            formBD.borrarMiembro(ID);
    }

    /**
     * Modifica los valores del miembro
     * @param ID
     * @param nombre
     * @param valX
     * @param valY
     * @param tipoLetra
     * @param color
     * @param tamanoLetra
     * @param IDTP
     */
    public void upDateValoresMiembro(int ID, String nombre, int valX, int valY, String tipoLetra, int color, int tamanoLetra){
        int IDTP = getIDTipoCampo(ID);
        //agrega en la BD el dato nuevo
        formBD.updateMiembro(ID, nombre, valX, valY, tipoLetra, color, tamanoLetra, IDTP);
        //borra el miembro viejo
        miembrosFormulario.remove( new MiembroFormulario(ID));
        MiembroFormulario datoNuevo = new MiembroFormulario(ID, this.correlativo, nombre, valX, valY, tipoLetra, color, tamanoLetra, IDTP);
        //agrega el nuevo con los valores nuevos
        miembrosFormulario.add(datoNuevo);
    }

    private int getIDTipoCampo( int ID){
        int IDTP = -1;
        Object[] vecMiembros = miembrosFormulario.toArray();        
        for(int i = 0; i< vecMiembros.length; ++i){
            if(((MiembroFormulario)vecMiembros[i]).getID() == ID){
                IDTP = ((MiembroFormulario)vecMiembros[i]).getIDTipoCampo();
                //termina el for
                i = vecMiembros.length;
            }
        }
        return IDTP;
    }

    /**
     * Devuelve una instancia del Miembro formulario, para poder utilizar sus datos
     * @param ID
     * @return
     */
    public MiembroFormulario getMiembro(int ID){
        Object[] vecMiembros = miembrosFormulario.toArray();
        for(int i = 0; i< vecMiembros.length; ++i){
            if(((MiembroFormulario)vecMiembros[i]).getID() == ID){
                return ((MiembroFormulario)vecMiembros[i]);
            }
        }
        return new MiembroFormulario(-1);
    }

    /**
     * * Retorna el nombre del formulario
     * @return
     */
    public String getNombre() {
        return this.nombre;
    }

    /**
     * Modifica el nombre del formulario
     * @param nombre
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
        formBD.modificarNombre(nombre, correlativo);
    }

    /**
     * Retorna la descripcion del formulario
     * @return
     */
    public String getDescripcion() {
        return this.descripcion;
    }

    /**
     * Modifica la descripcion
     * @param descr
     */
    public void setDescripcion(String descr) {
        this.descripcion = descr;
        formBD.modificarDescripcion(nombre, correlativo);
    }

    /**
     * Sobre escribe el toString del padre
     * @return
     */
    @Override
    public String toString() {
        return super.toString();
    }

}
