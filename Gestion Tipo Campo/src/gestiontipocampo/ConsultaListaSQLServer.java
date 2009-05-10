/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package gestiontipocampo;

/**
 *
 * @author luiscarlosch
 */
public class ConsultaListaSQLServer extends ConsultaLista {
    public void agregarMiembro(String nombreMiembro, int posicion, int correlativo) {//agregar elemento a la lista
        System.out.print("agregando...");
        this.doUpdate("insert into MIEMBROLISTA (valor,IDLista, numeroElemento) values ('" + nombreMiembro + "'," + correlativo + ", " + posicion + ");");
    }
}
