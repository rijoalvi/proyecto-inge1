/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package gestiontipocampo;

/**
 *
 * @author Roman
 */
public class MiDato2 implements Comparable {
    public int orden;
    public String nombre;
    public int ID;
    public static String valorDefecto;

    public MiDato2(String nombre, int ID, int orden) {
        this.nombre = nombre;
        this.ID = ID;
        this.orden = orden;
        valorDefecto = "";
    }

    public int compareTo(Object o) {
        int aRetornar = this.nombre.toString().compareToIgnoreCase(o.toString());
        if (aRetornar == 0)
            return 0;
        return this.orden - ((MiDato2)o).orden;
    }

    @Override
    public String toString() {
        return this.nombre;
    }
}
