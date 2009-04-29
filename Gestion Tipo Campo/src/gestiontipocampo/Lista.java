/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gestiontipocampo;

import java.util.*;

/**
 *
 * @author luiscarlosch@gmail.com
 */
public class Lista extends TipoCampo {

    private SortedSet miembroLista;
    public Modelo miModelo;
    public String nombreMiembroPorDefecto;
    public String IDMiembroPorDefecto;
    public boolean ordenPersonalizado;

    public Lista() {
        buscador = new ControladorBD();
        miembroLista = new TreeSet();
        miModelo = new Modelo();
        ordenPersonalizado = false;
    }

    public SortedSet getMiembroListaSet() {
        return this.miembroLista;
    }

    /**
     * Cargo los miembros de la lista a partir de un vector
     * @param vector
     */
    public void setMiembrosLista(Vector vector) {
        miembroLista.clear();
        for (int i = 0; i < vector.size(); i++) {
            miembroLista.add(vector.get(i));
        }
    }

    public Vector getModeloVector(String consulta, String campoTexto, String campoNumero) {
        return miModelo.getModeloEnVector(consulta, campoTexto, campoNumero);
    }

    public Vector getModeloVector(String consulta, String campoTexto, String campoNumero, String campoNumero2) {
        return miModelo.getModeloEnVector(consulta, campoTexto, campoNumero, campoNumero2);
    }



    /**
     * Cargo en la clase Lista los datos respectivos de ésta, como nombre, descripcion y valor por defecto
     */
    public void setLista() {
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


        miMapa = buscador.getResultSetMap("select t.correlativo, nombre, descripcion, ultimaActualizacion, IDMiembroPorDefecto, conOrden, m.valor from TIPOCAMPO t, LISTA l,MIEMBROLISTA m where t.correlativo=" + this.correlativo + " AND l.correlativo=t.correlativo  and m.correlativo=l.IDMiembroPorDefecto;", campos);
        /* //No tiene sentido hacer esto ya que obtiene el mapa usando correlativo
        if (ControladorBD.conexionSeleccionada == 1) {
            this.correlativo = miMapa.get("t.correlativo");
        } else {
            this.correlativo = miMapa.get("correlativo");
        }*/

        this.nombre = miMapa.get("nombre");
        this.descripcion = miMapa.get("descripcion");
        this.IDMiembroPorDefecto = miMapa.get("IDMiembroPorDefecto");

        if(miMapa.get("conOrden").equalsIgnoreCase("true")){
            this.ordenPersonalizado = true;
        }
        else{
            this.ordenPersonalizado = false;
        }

        if (ControladorBD.conexionSeleccionada == 1) {
            this.nombreMiembroPorDefecto = miMapa.get("m.valor");
        } else {
            this.nombreMiembroPorDefecto = miMapa.get("valor");
        }
    }

    public void agregarMiembro(String nombreMiembro) {//agregar elemento a la lista
        buscador.doUpdate("insert into MIEMBROLISTA (valor,IDLista) values ('" + nombreMiembro + "'," + this.correlativo + ");");
    }

    public void borrarMiembro(Object miembro) {
        buscador.doUpdate("delete from MIEMBROLISTA where correlativo = " + ((MiDato) (miembro)).ID + " and IDLista=" + this.correlativo + ";");
        this.miembroLista.remove(miembro);
    }

    /**
     * Actualiza en la lista, el ID del miembro lista que ahora será el valor por defecto
     * @param IDMiembroPorDefecto
     */
    public void upDateIDMiembroPorDefecto(String IDMiembroPorDefecto) {
        buscador.doUpdate("UPDATE LISTA  SET IDMiembroPorDefecto=" + IDMiembroPorDefecto + " where correlativo=" + this.correlativo + ";");
    //this.miembroLista.remove(miembro);
    }

    /**
     *
     * @param miembroAActualizar Objeto que contiene el MiDato (MiembroLista) a actualizar, a este se se castea para conseguir el ID y el nombre
     * @param valorNuevo Nombre nuevo a asignar al elemento seleccionado
     */
    public void upDateValorMiembro(Object miembroAActualizar, String valorNuevo) {
        int IDMiembroAActualizar = ((MiDato) (miembroAActualizar)).ID;
        buscador.doUpdate("Update MIEMBROLISTA set valor='" + valorNuevo + "' where Correlativo=" + IDMiembroAActualizar + ";");
        //this.miembroLista.remove(miembro);
        this.miembroLista.remove(miembroAActualizar);
        this.miembroLista.add(new MiDato(valorNuevo, IDMiembroAActualizar));
        if (("" + IDMiembroAActualizar).equalsIgnoreCase(this.IDMiembroPorDefecto)) {
            this.nombreMiembroPorDefecto = valorNuevo;
        }
    }

    /**
     * Se llama despues de agregar o modificar un elemento de la lista. Sí agregé un elemento al control lista, este lo tengo que agregar a la base de datos y volver a hacer la consulta para ver en que orden quedó, para actualizar el control lista
     * @return Vector con el modelo para el control de la lista ya actualizado por hago consulta a la BD para ver el orden
     */
    public Vector setAndGetMiembrosVectorActualizados() {
        if(this.ordenPersonalizado){
            this.setMiembrosLista(this.getModeloVector("select valor, ml.correlativo, ml.numeroElemento from MIEMBROLISTA ml, LISTA l where ml.IDLista=l.correlativo and l.correlativo=" + this.correlativo + "", "valor", "correlativo", "numeroElemento"));
        }
        else{
            this.setMiembrosLista(this.getModeloVector("select valor, ml.correlativo from MIEMBROLISTA ml, LISTA l where ml.IDLista=l.correlativo and l.correlativo=" + this.correlativo + "", "valor", "correlativo"));
        }

        SortedSet miembrosListaSet = new TreeSet();
        Vector miembrosListaVector = new Vector();

        miembrosListaSet = this.getMiembroListaSet();

        Iterator it = miembrosListaSet.iterator();

        while (it.hasNext()) {
            miembrosListaVector.add(it.next());
        }
        MiDato.valorDefecto = this.IDMiembroPorDefecto + "";
        return miembrosListaVector;
    }

    /**
     * Se llama despues de borrar un miembro de la lista, Aquí es diferente a setAndGet, se usa en un caso como el de borrar, que no tengo que actualizar el modelo ya que con solo eliminarlo del control lista, ya es sufuciente
     * @return Un vector con con el modelo actual.
     */
    public Vector getModeloMiembrosVector() {
        //  this.setMiembrosLista(this.getModeloVector("select valor, ml.correlativo from MIEMBROLISTA ml, LISTA l where ml.IDLista=l.correlativo and l.correlativo="+this.correlativo+"", "valor", "correlativo"));
        MiDato.valorDefecto = this.IDMiembroPorDefecto;
        SortedSet miembrosListaSet = new TreeSet();
        Vector miembrosListaVector = new Vector();

        miembrosListaSet = this.getMiembroListaSet();

        Iterator it = miembrosListaSet.iterator();

        while (it.hasNext()) {
            miembrosListaVector.add(it.next());
        }

        return miembrosListaVector;
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
        buscador.doUpdate("UPDATE LISTA  SET conOrden = '" + orden + "' where correlativo = " + this.correlativo + ";");
    }

    /**
     * Cambia la posicion del elemento de la lista
     * @param ID - correlativo del elemento
     * @param posicion - nueva posicion
     */
    public void setOrdenDeElemento(int ID, int posicion){
        buscador.doUpdate("UPDATE MIEMBROLISTA  SET numeroElemento = " + posicion + " where correlativo = " + ID + ";");
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
