/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gestiontipocampo;

/**
 *
 * @author Administrator
 */
public class TipoCampo {

    //lo cambie a INT, no c porq estaba en STRING - beto
    protected int correlativo;
    protected String nombre;
    protected String descripcion;
    protected String ultimaActualizacion;
    protected ControladorBD buscador;

    ///Constructor por omision
    public TipoCampo(){
        buscador = new ControladorBD();
    }

    @Override
    public String toString() {
        String aRetornar = 
                this.correlativo + "\n" +
                this.nombre + "\n" +
                this.descripcion + "\n";
        return aRetornar;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return this.descripcion;
    }

    public void setDescripcion(String descr) {
        this.descripcion = descr;
    }
    
}
