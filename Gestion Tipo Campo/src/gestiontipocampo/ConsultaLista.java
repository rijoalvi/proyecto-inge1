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
public abstract class  ConsultaLista extends ControladorBD{
    public abstract void agregarMiembro(String nombreMiembro,int correlativo , int posicion);
    public abstract Map<String, String> getInfoLista(int correlativo);
}
