/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package gestiontipocampo;
import java.util.*;
/**
 *
 * @author luiscarlosch
 */
public class ConsultaListaSQLServer extends ConsultaLista {
    public void agregarMiembro(String nombreMiembro, int posicion, int correlativo) {//agregar elemento a la lista
        System.out.print("agregando...");
        this.doUpdate("insert into MIEMBROLISTA (valor,IDLista, numeroElemento) values ('" + nombreMiembro + "'," + correlativo + ", " + posicion + ");");
    }
    public Map<String, String> getInfoLista(int correlativo){
        Vector campos = new Vector();
        campos.add("correlativo");//diferencia con mysql
        campos.add("nombre");
        campos.add("descripcion");
        campos.add("ultimaActualizacion");
        campos.add("IDMiembroPorDefecto");
        campos.add("conOrden");
        campos.add("valor");//diferencia con mysql
        return this.getResultSetMap("select t.correlativo, nombre, descripcion, ultimaActualizacion, IDMiembroPorDefecto, conOrden, m.valor from TIPOCAMPO t, LISTA l,MIEMBROLISTA m where t.correlativo=" + correlativo + " AND l.correlativo=t.correlativo  and m.correlativo=l.IDMiembroPorDefecto;", campos);

    }
}
