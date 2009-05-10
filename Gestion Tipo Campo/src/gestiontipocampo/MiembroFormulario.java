/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package gestiontipocampo;

/**
 *
 * @author Alberto
 */
public class MiembroFormulario {

    
    private int correlativo;
    private String nombre;
    private int valX;
    private int valY;
    private String tipoLetra;
    private String color;
    private int tamanoLetra;
    private int IDTipoCampo;

    ///Constructor por omision
    public MiembroFormulario(){

    }

    ///Constructor que recibe parametros de inicialización
    public MiembroFormulario(String nombre, int valX, int valY, String tipoLetra, String color, int tamanoLetra, int IDTipoC){
        this.nombre = nombre;
        this.valX = valX;
        this.valY = valY;
        this.tipoLetra = tipoLetra;
        this.color = color;
        this.tamanoLetra = tamanoLetra;
        this.IDTipoCampo = IDTipoC;
    }

    /**
     * Se indica el nombre del miembro
     * @param y
     */
    public void setNoombre(String name){
        nombre = name;
    }

    /**
     * Se indica la posicion en X del miembro
     * @param x
     */
    public void setValX(int x){
        valX = x;
    }

    /**
     * Se indica la posicion en Y del miembro
     * @param y
     */
    public void setValY(int y){
        valY = y;
    }

    /**
     * Indica la posicion en X del miembro
     */
    public int getValX( ){
        return valX;
    }

    /**
     * Indica la posicion en X del miembro
     */
    public int getValY( ){
        return valX;
    }

    /**
     * Devuelve el valor del nombre
     */
    public String getNombre( ){
        return nombre;
    }



}
