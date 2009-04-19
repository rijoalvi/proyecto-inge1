/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package gestiontipocampo;

/**
 *
 * @author luiscarlosch@gmail.com
 */
public class MiDato implements Comparable{
    public String nombre;
    public int ID=0;
    public static String valorDefecto;
    public MiDato(String nombre, int ID){
        this.nombre=nombre;
        this.ID=ID;
        valorDefecto="";
    }
    public int compareTo(Object o){
        int aRetornar= this.nombre.toString().compareToIgnoreCase(o.toString());
        /*if(this.ID==((MiDato)(o)).ID){
            aRetornar=0;
        }*/
        
        return aRetornar;
    }
    @Override
    public String toString(){
        String aRetornar;
        if(valorDefecto.equals(ID+"")){
            aRetornar= this.nombre+"*";
        }
        else{
        
            aRetornar= this.nombre;
        }


        return aRetornar;
    }
}
