/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package gestiontipocampo;

/**
 *
 * @author luiscarlosch
 */
public class ConsultaListaMySQL extends ConsultaLista {

    public void agregarMiembro(String nombreMiembro, int correlativo , int posicion ) {//agregar elemento a la lista
        System.out.print("agregandosssssssss...   "+correlativo+"  ");
        this.doUpdate("insert into MIEMBROLISTA (valor,IDLista, numeroElemento) values ('" + nombreMiembro + "'," + correlativo + ", " + posicion + ");");
    }
}
