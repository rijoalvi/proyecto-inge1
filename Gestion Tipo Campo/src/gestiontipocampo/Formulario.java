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

    /**
     *
     * @param nombre
     * @param desc
     */
    public Formulario( String nombre, String desc) {
        this.nombre = nombre;
        this.descripcion = desc;
        buscador = new ControladorBD();
        formBD = buscador.getConsultaFormulario();;
        this.correlativo = formBD.guardaFormulario(nombre, descripcion);
        miembrosFormulario = new TreeSet();
    }

    /**
     * Crea un nuevo Formulario extrayendo los datos de la base de datos por medio de la clase consultaFormulario
     * @param correlativo el correlativo del Formulario en la base de datos
     */
    public Formulario( int correlativo) {
        this.correlativo = correlativo;
        buscador = new ControladorBD();
        formBD = buscador.getConsultaFormulario();
        Vector datos = this.formBD.obtenerDatosFormulario(correlativo);
        nombre =  datos.get(0).toString();
        descripcion =  datos.get(1).toString();
        ultimaActualizacion =  datos.get(1).toString();
        
        miembrosFormulario = new TreeSet();
        
        cargarMiembros();

    }

    /**************************Debo seguir aca.....(es para no perderme XD)
     * Carga y crea los miembros en miembrosFormulario
     */
    private void cargarMiembros(){
        Vector miembros = formBD.obtenerMiembros(correlativo);


        /////////lleva la cuenta de por cual valor del vector va....
        int indice=0;
        while(indice<miembros.size()){
            //crea una instancia del miembro
            MiembroFormulario datoNuevo = new MiembroFormulario(Integer.parseInt(miembros.get(indice++).toString()),
                                                                this.correlativo, miembros.get(indice++).toString(),
                                                                Integer.parseInt(miembros.get(indice++).toString()),
                                                                Integer.parseInt(miembros.get(indice++).toString()),
                                                                Integer.parseInt(miembros.get(indice++).toString()),
                                                                Integer.parseInt(miembros.get(indice++).toString()),
                                                                miembros.get(indice++).toString(),
                                                                Integer.parseInt(miembros.get(indice++).toString()),
                                                                Integer.parseInt(miembros.get(indice++).toString()),
                                                                Integer.parseInt(miembros.get(indice++).toString()),
                                                                Integer.parseInt(miembros.get(indice++).toString()),
                                                                miembros.get(indice++).toString());
            miembrosFormulario.add(datoNuevo);
        }
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
    public int agregarMiembro(String nombre, int valX, int valY, int ancho, int alto, String tipoLetra, int color, int tamanoLetra, int IDTP, int tabIndex, String estiloLetra){
        //agrega en la BD el dato nuevo
        int ID = formBD.agregarMiembro(this.correlativo, nombre, valX, valY, ancho, alto, tipoLetra, color, tamanoLetra, IDTP, tabIndex, estiloLetra);
        //crea una instancia del miembro
        MiembroFormulario datoNuevo = new MiembroFormulario(ID, this.correlativo, nombre, valX, valY, ancho, alto, tipoLetra, color, tamanoLetra, IDTP, tabIndex, estiloLetra);
        miembrosFormulario.add(datoNuevo);
        return ID;
    }

    /**
     * Borra a un componente del formulario
     * @param ID ID del componente a eliminar
     */
    public void eliminarMiembro(int ID) {
        //borra la instancia del elemento
        miembrosFormulario.remove(new MiembroFormulario(ID));
        //borra en la BD
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
    public void upDateValoresMiembro(int ID, String nombre, int valX, int valY, int ancho, int alto, String tipoLetra, int color, int tamanoLetra, String estiloLetra){
        int IDTP = getIDTipoCampo(ID);
        MiembroFormulario tmp = getMiembro(ID);
        //agrega en la BD el dato nuevo
        formBD.updateMiembro(ID, nombre, valX, valY, ancho, alto, tipoLetra, color, tamanoLetra, IDTP, tmp.getTabIndex(), estiloLetra);
        //borra el miembro viejo
        miembrosFormulario.remove( tmp );
        MiembroFormulario datoNuevo = new MiembroFormulario(ID, this.correlativo, nombre, valX, valY, ancho, alto, tipoLetra, color, tamanoLetra, IDTP, tmp.getTabIndex(), estiloLetra);
        //agrega el nuevo con los valores nuevos
        miembrosFormulario.add(datoNuevo);
    }

    /**
     * Modifica la posicion del componente
     * @param ID
     * @param valX
     * @param valY
     */
    public void updatePosicion(int ID, int valX, int valY){
        //agrega en la BD el dato nuevo
        formBD.updatePosicion(ID, valX, valY);
        //borra el miembro viejo
        MiembroFormulario tmp = getMiembro(ID);
        miembrosFormulario.remove(tmp);
        MiembroFormulario datoNuevo = new MiembroFormulario(ID, tmp.getID(), tmp.getNombre(), valX, valY, tmp.getAncho(), tmp.getAlto(), tmp.getTipoLetra(), tmp.getColor(), tmp.getTamanoLetra(), tmp.getIDTipoCampo(), tmp.getTabIndex(), tmp.getEstiloLetra());
        //agrega el nuevo con los valores nuevos
        miembrosFormulario.add(datoNuevo);
    }

    /**
     * Modifica el valor del tabIndex
     * @param ID
     * @param tab
     */
    public void updateTabIndex(int ID, int tab){
        //modifica en la BD el dato
        formBD.updateTabIndex(ID, tab);
        MiembroFormulario tmp = getMiembro(ID);
        //borra el miembro viejo
        miembrosFormulario.remove(tmp);
        MiembroFormulario datoNuevo = new MiembroFormulario(ID, tmp.getID(), tmp.getNombre(), tmp.getValX(), tmp.getValY(), tmp.getAncho(), tmp.getAlto(), tmp.getTipoLetra(), tmp.getColor(), tmp.getTamanoLetra(), tmp.getIDTipoCampo(), tab, tmp.getEstiloLetra());
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

    public int getCount() {
        return this.miembrosFormulario.size();
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
