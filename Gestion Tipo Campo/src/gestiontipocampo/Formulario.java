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
public class Formulario extends TipoCampo{

    private MiembroFormulario [] miembrosFormulario;
    public boolean ordenPersonalizado;
    public ConsultaFormulario formBD;

    public Formulario( String nombre, String desc) {
        this.nombre = nombre;
        this.descripcion = desc;
        formBD = new ConsultaFormulario( );
        this.correlativo = formBD.guardaFormulario(nombre, descripcion);
    }

    /**
     * Devuelve todos los datos de los miembros del fomulario
     * @return
     */
    public MiembroFormulario [] getMiembroFormularioSet() {
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
    public void agregarMiembro(String nombre, int valX, int valY, String tipoLetra, int color, int tamanoLetra, int IDTP){
        //agrega en la BD el dato nuevo
        int ID = formBD.agregarMiembro(this.correlativo, nombre, valX, valY, tipoLetra, color, tamanoLetra, IDTP);
        //crea una instancia del miembro
        MiembroFormulario datoNuevo = new MiembroFormulario(ID, this.correlativo, nombre, valX, valY, tipoLetra, color, tamanoLetra, IDTP);
        miembrosFormulario[miembrosFormulario.length] = datoNuevo;
        System.out.println("agregue miembro: "+ datoNuevo.getNombre()+ " ID: "+ datoNuevo.getID());
    }

    /**
     * Borra a un componente del formulario
     * @param nombre Nombre del componente a eliminar
     */
    public void borrarMiembro(String nombre) {
        int indiceABorrar = 0;
        int ID = -1;
        int size = miembrosFormulario.length;
        for(int i = 0; i< size; ++i){
            if(miembrosFormulario[i].getNombre().equalsIgnoreCase(nombre)){
                //guarda el valor
                indiceABorrar = i;
                miembrosFormulario[i].getID();
                //borra el elemento hay q borrar de la BD tamb
                miembrosFormulario[i] = null;
                //termina el for
                i = miembrosFormulario.length;
            }
        }
        //se hace hasta size -1 por el valor que se eliminara
        for(int i = indiceABorrar; i < size-1; ++i){
            miembrosFormulario[i] = miembrosFormulario[i+1];
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
        borrarMiembro(nombre);
        MiembroFormulario datoNuevo = new MiembroFormulario(ID, this.correlativo, nombre, valX, valY, tipoLetra, color, tamanoLetra, IDTP);
        //agrega el nuevo con los valores nuevos
        miembrosFormulario[miembrosFormulario.length] = datoNuevo;
    }

    private int getIDTipoCampo( int ID){
        int IDTP = -1;
        for(int i = 0; i< miembrosFormulario.length; ++i){
            if(miembrosFormulario[i].getID() == ID){
                IDTP = miembrosFormulario[i].getIDTipoCampo();
                //termina el for
                i = miembrosFormulario.length;
            }
        }
        return IDTP;
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
