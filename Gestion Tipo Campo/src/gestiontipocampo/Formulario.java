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

    private SortedSet miembroFormulario;
    public Modelo miModelo;
    public boolean ordenPersonalizado;

    public Formulario() {
        buscador = new ControladorBD();
        miembroFormulario = new TreeSet();
        miModelo = new Modelo();
        ordenPersonalizado = true;
    }

    /**
     * Devuelve todos los datos de los miembros del fomulario
     * @return
     */
    public SortedSet getMiembroFormularioSet() {
        return this.miembroFormulario;
    }

    /**
     * Cargo los miembros del formulario a partir de un vector
     * @param vector
     */
    public void setMiembrosFormulario(Vector vector) {
        miembroFormulario.clear();
        for (int i = 0; i < vector.size(); i++) {
            miembroFormulario.add(vector.get(i));
        }
    }

    public Vector getModeloVector(String consulta, String campoTexto, String campoNumero) {
        return miModelo.getModeloEnVector(consulta, campoTexto, campoNumero);
    }

    public Vector getModeloVector(String consulta, String campoTexto, String campoNumero, String campoNumero2) {
        return miModelo.getModeloEnVector(consulta, campoTexto, campoNumero, campoNumero2);
    }



    /**
     * Cargo en la clase FORMULARIO los datos respectivos de ésta, como nombre, descripcion y valor por defecto
     */
    public void setFormulario() {
        Map<String, String> miMapa;
        Vector campos = new Vector();

        if (ControladorBD.conexionSeleccionada == 1) {
            campos.add("t.correlativo");
        } else {
            campos.add("correlativo");//**********incompatiblidad mysql t.correlativo
        }
        campos.add("nombre");
        campos.add("descripcion");
        campos.add("ultimaActualizacion");
        campos.add("IDMiembroPorDefecto");
        campos.add("conOrden");

        if (ControladorBD.conexionSeleccionada == 1) {
            campos.add("m.valor");
        } else {
            campos.add("valor");//**********incompatiblidad mysql m.valor
        }


        miMapa = buscador.getResultSetMap("select t.correlativo, t.nombre, t.descripcion," +
                " t.ultimaActualizacion, f.conOrden FROM TIPOCAMPO t, FORMULARIO f" +
                "where t.correlativo=" + this.correlativo + " AND f.correlativo=t.correlativo;", campos);

        this.nombre = miMapa.get("nombre");
        this.descripcion = miMapa.get("descripcion");

        if(miMapa.get("conOrden").trim().equalsIgnoreCase("True")){
            System.out.println(miMapa.get("conOrden"));
            this.ordenPersonalizado = true;
        }
        else{
            System.out.println(miMapa.get("conOrden")+"cogio false");
            this.ordenPersonalizado = false;
        }
    }

    public void agregarMiembro(String nombreMiembro, int posicion) {//agregar elemento a la FORMULARIO
        buscador.doUpdate("insert into MIEMBROFORMULARIO (valor,IDFormulario, numeroElemento) values ('" + nombreMiembro + "'," + this.correlativo + ", " + posicion + ");");
    }


    public void borrarMiembro(Object miembro) {
        buscador.doUpdate("delete from MIEMBROFORMULARIO where correlativo = " + getID(miembro) + " and IDFormulario=" + this.correlativo + ";");
        this.miembroFormulario.remove(miembro);
    }

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
     * Actualiza en la FORMULARIO, el ID del miembro FORMULARIO que ahora será el valor por defecto
     * @param IDMiembroPorDefecto
     */
    public void upDateIDMiembroPorDefecto(String IDMiembroPorDefecto) {
        buscador.doUpdate("UPDATE FORMULARIO  SET IDMiembroPorDefecto=" + IDMiembroPorDefecto + " where correlativo=" + this.correlativo + ";");
    //this.MIEMBROFORMULARIO.remove(miembro);
    }

    /**
     *
     * @param miembroAActualizar Objeto que contiene el MiDato (MIEMBROFORMULARIO) a actualizar, a este se se castea para conseguir el ID y el nombre
     * @param valorNuevo Nombre nuevo a asignar al elemento seleccionado
     */
    public void upDateValorMiembro(Object miembroAActualizar, String valorNuevo) {
        int IDMiembroAActualizar = ((MiDato) (miembroAActualizar)).ID;
        buscador.doUpdate("Update MIEMBROFORMULARIO set valor='" + valorNuevo + "' where Correlativo=" + IDMiembroAActualizar + ";");
        //this.MIEMBROFORMULARIO.remove(miembro);
        this.miembroFormulario.remove(miembroAActualizar);
        this.miembroFormulario.add(new MiDato(valorNuevo, IDMiembroAActualizar));
    }

    /**
     * Se llama despues de agregar o modificar un elemento de la FORMULARIO. Sí agregé un elemento al control FORMULARIO, este lo tengo que agregar a la base de datos y volver a hacer la consulta para ver en que orden quedó, para actualizar el control FORMULARIO
     * @return Vector con el modelo para el control de la FORMULARIO ya actualizado por hago consulta a la BD para ver el orden
     */
    public Vector setAndGetMiembrosVectorActualizados() {
        if(this.ordenPersonalizado){
            this.setMiembrosFormulario(this.getModeloVector("select valor, ml.correlativo, ml.numeroElemento from MIEMBROFORMULARIO ml, FORMULARIO l where ml.IDFORMULARIO=l.correlativo and l.correlativo=" + this.correlativo + "", "valor", "correlativo", "numeroElemento"));
        }
        else{
            this.setMiembrosFormulario(this.getModeloVector("select valor, ml.correlativo from MIEMBROFORMULARIO ml, FORMULARIO l where ml.IDFORMULARIO=l.correlativo and l.correlativo=" + this.correlativo + "", "valor", "correlativo"));
        }

        SortedSet miembrosFormularioSet = new TreeSet();
        Vector miembrosFormularioVector = new Vector();

        miembrosFormularioSet = this.getMiembroFormularioSet();

        Iterator it = miembrosFormularioSet.iterator();

        while (it.hasNext()) {
            miembrosFormularioVector.add(it.next());
        }
        return miembrosFormularioVector;
    }

    /**
     * Se llama despues de borrar un miembro de la FORMULARIO, Aquí es diferente a setAndGet, se usa en un caso como el de borrar, que no tengo que actualizar el modelo ya que con solo eliminarlo del control FORMULARIO, ya es sufuciente
     * @return Un vector con con el modelo actual.
     */
    public Vector getModeloMiembrosVector() {
        //  this.setMiembrosFORMULARIO(this.getModeloVector("select valor, ml.correlativo from MIEMBROFORMULARIO ml, FORMULARIO l where ml.IDFORMULARIO=l.correlativo and l.correlativo="+this.correlativo+"", "valor", "correlativo"));
        SortedSet miembrosFormularioSet = new TreeSet();
        Vector miembrosFormularioVector = new Vector();

        miembrosFormularioSet = this.getMiembroFormularioSet();

        Iterator it = miembrosFormularioSet.iterator();

        while (it.hasNext()) {
            miembrosFormularioVector.add(it.next());
        }

        return miembrosFormularioVector;
    }

    public void setConOrdenPersonalizado(boolean conOrden){
        String orden;
        this.ordenPersonalizado = conOrden;
        if(conOrden){
            orden = "True";
        }
        else{
            orden = "False";
        }
        buscador.doUpdate("UPDATE FORMULARIO  SET conOrden = '" + orden + "' where correlativo = " + this.correlativo + ";");
    }

    /**
     * Cambia la posicion del elemento de la FORMULARIO
     * @param ID - correlativo del elemento
     * @param posicion - nueva posicion
     */
    public void setOrdenDeElemento(int ID, int posicion){
        buscador.doUpdate("UPDATE MIEMBROFORMULARIO  SET numeroElemento = " + posicion + " where correlativo = " + ID + ";");
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
