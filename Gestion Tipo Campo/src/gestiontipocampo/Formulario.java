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
    public Modelo miModelo;
    public boolean ordenPersonalizado;
    public FormularioBD formBD;

    public Formulario( String nombre, String desc) {
        this.nombre = nombre;
        this.descripcion = desc;
        formBD = new FormularioBD( );
        this.correlativo = formBD.guardaFormulario(nombre, descripcion);
        miModelo = new Modelo();
    }

    /**
     * Devuelve todos los datos de los miembros del fomulario
     * @return
     */
    public MiembroFormulario [] getMiembroFormularioSet() {
        return this.miembrosFormulario;
    }

    public Vector getModeloVector(String consulta, String campoTexto, String campoNumero) {
        return miModelo.getModeloEnVector(consulta, campoTexto, campoNumero);
    }

    public Vector getModeloVector(String consulta, String campoTexto, String campoNumero, String campoNumero2) {
        return miModelo.getModeloEnVector(consulta, campoTexto, campoNumero, campoNumero2);
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
    public void agregarMiembro(String nombre, int valX, int valY, String tipoLetra, String color, int tamanoLetra, int IDTP){
        MiembroFormulario datoNuevo = new MiembroFormulario(nombre, valX, valY, tipoLetra, color, tamanoLetra, IDTP);
        miembrosFormulario[miembrosFormulario.length] = datoNuevo;
        System.out.println("agregue miembro: "+ datoNuevo.getNombre());
    }

    /**
     * Borra a un componente del formulario
     * @param nombre Nombre del componente a eliminar
     */
    public void borrarMiembro(String nombre) {
        //buscador.doUpdate("delete from MIEMBROFORMULARIO where correlativo = " + getID(miembro) + " and IDFormulario=" + this.correlativo + ";");
        int indiceABorrar = 0;
        for(int i = 0; i< miembrosFormulario.length; ++i){
            if(miembrosFormulario[i].getNombre().equalsIgnoreCase(nombre)){
                //guarda el valor
                indiceABorrar = i;
                //borra el elemento hay q borrar de la BD tamb
                miembrosFormulario[i] = null;
                //termina el for
                i = miembrosFormulario.length;
            }
        }
        //se hace hasta length -1 por el valor que se eliminara
        System.out.println("debria de servir, pero puede haber un error aqui");
        for(int i = indiceABorrar; i < miembrosFormulario.length-1; ++i){
            miembrosFormulario[i] = miembrosFormulario[i+1];
        }
    }

    /*
     * Esto es de BD
    public void borrarMiembro(Object miembro) {
        buscador.doUpdate("delete from MIEMBROFORMULARIO where correlativo = " + getID(miembro) + " and IDFormulario=" + this.correlativo + ";");
        this.miembrosFormulario.remove(miembro);
    }
     */

    /**
     * Recibe el ID del miembro del fromulario
     * @param obj
     * @return
     */
    private int getID(Object obj) {

        int ID = 0;
        /*
        try {
            ID = ((MiDato) obj).ID;
        } catch (java.lang.ClassCastException e) {
            try {
                ID = ((MiDato2) obj).ID;
            } catch (java.lang.ClassCastException ex) {
            }
        }
         */
        return ID;
    }

    /**
     *
     * @param miembroAActualizar Objeto que contiene el MiDato (MIEMBROFORMULARIO) a actualizar, a este se se castea para conseguir el ID y el nombre
     * @param valorNuevo Nombre nuevo a asignar al elemento seleccionado
     */
    public void upDateValorMiembro(Object miembroAActualizar, String valorNuevo) {
  /*
        int IDMiembroAActualizar = ((MiDato) (miembroAActualizar)).ID;
        buscador.doUpdate("Update MIEMBROFORMULARIO set valor='" + valorNuevo + "' where Correlativo=" + IDMiembroAActualizar + ";");
        //this.MIEMBROFORMULARIO.remove(miembro);
        this.miembrosFormulario.remove(miembroAActualizar);
        this.miembrosFormulario.add(new MiDato(valorNuevo, IDMiembroAActualizar));
   */
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
