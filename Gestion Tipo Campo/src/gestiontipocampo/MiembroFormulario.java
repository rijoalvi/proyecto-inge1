/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gestiontipocampo;

/**
 *
 * @author Alberto
 */
public class MiembroFormulario implements Comparable {

    private int IDFormulario;
    private int correlativo;
    private String nombre;
    private int valX;
    private int valY;
    private int ancho;
    private int alto;
    private String tipoLetra;
    private int color;
    private int tamanoLetra;
    private int IDTipoCampo;
    private int tabIndex;
    private String estiloLetra;

    ///Constructor por omision
    public MiembroFormulario() {
    }

    ///Constructor que recibe nada mas el ID
    public MiembroFormulario(int ID) {
        this.correlativo = ID;
    }

    ///Constructor que recibe parametros de inicialización
    public MiembroFormulario(int ID, int IDForm, String nombre, int valX, int valY, int ancho, int alto, String tipoLetra, int color, int tamanoLetra, int IDTipoC, int tabIndex, String estiloLetra) {
        this.correlativo = ID;
        this.IDFormulario = IDForm;
        this.nombre = nombre;
        this.valX = valX;
        this.valY = valY;
        this.ancho = ancho;
        this.alto = alto;
        this.tipoLetra = tipoLetra;
        this.color = color;
        this.tamanoLetra = tamanoLetra;
        this.IDTipoCampo = IDTipoC;
        this.tabIndex = tabIndex;
        this.estiloLetra = estiloLetra;
    }

    /**
     * Compara dos miembroFormulario y devuelve 0 si tienen el correlativo igual
     * @param o miembroFormulario a comparar
     * @return 0 - tienen el mismo correlativo, 1 - tiene el correlativo diferente
     */
    public int compareTo(Object o) {
        int retorno = 1;
        if (this.correlativo == ((MiembroFormulario) o).correlativo) {
            retorno = 0;
        }
        return retorno;
    }

    /**
     * Se indica el nombre del miembro
     * @param y
     */
    public void setNombre(String name) {
        nombre = name;
    }

    /**
     * Se indica la posicion en X del miembro
     * @param x
     */
    public void setValX(int x) {
        valX = x;
    }

    /**
     * Se indica la posicion en Y del miembro
     * @param y
     */
    public void setValY(int y) {
        valY = y;
    }

    /**
     * Se indica el estilo de letra
     * @param estilo Posibles valores son (Plain, Bold, Italic, BoldItalic)
     */
    public void setEstiloLetra(String estilo){
        estiloLetra = estilo;
    }

    /**
     * Indica el correlativo(ID) del miembro
     */
    public int getID() {
        return correlativo;
    }

    /**
     * Indica el ID del tipo campo del miembro
     */
    public int getIDTipoCampo() {
        return IDTipoCampo;
    }

    /**
     * Indica la posicion en X del miembro
     */
    public int getValX() {
        return valX;
    }

    /**
     * Indica la posicion en X del miembro
     */
    public int getValY() {
        return valY;
    }

    /**
     * Indica el ancho del miembro
     */
    public int getAncho() {
        return ancho;
    }

    /**
     * Indica el alto del miembro
     */
    public int getAlto() {
        return alto;
    }

    /**
     * Devuelve el valor del nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Indica el ID del formulario al cual pertenece el miembro
     */
    public int getIDFormulario() {
        return IDFormulario;
    }

    /**
     * Indica el tamaño de letra del miembro
     */
    public int getTamanoLetra() {
        return tamanoLetra;
    }

    /**
     * Indica el color del miembro
     */
    public int getColor() {
        return color;
    }

    /**
     * Devuelve el tipo de Letra
     */
    public String getTipoLetra() {
        return tipoLetra;
    }

    /**
     * Devuelve tab index
     */
    public int getTabIndex() {
        return tabIndex;
    }

    /**
     * Devuelve estilo de letra
     * @return
     */
    public String getEstiloLetra(){
        return estiloLetra;
    }
}
