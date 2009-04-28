package gestiontipocampo;

/**
 *Clase para el Tipo Camo Numero
 * @author admin
 */
public class Numero extends TipoCampo {

    /**
     * Numero de decimales que se guardan y se muestran del numero
     */
    protected int numeroDecimales;

    /**
     * Mascara del numero
     */
    protected String mascara;

    /**
     * Valor por defecto
     */
    protected float valorDefecto;

    /**
     * Constructor para crear un numero nuevo
     */
    public Numero(){

    }

    /**
     * toString del tipoCampoNumero
     * @return datos del campo
     */
    @Override
    public String toString() {
        String aRetornar =
                this.correlativo + "\n" +
                this.nombre + "\n" +
                this.descripcion + "\n";
        aRetornar += "Decimales: " + this.numeroDecimales + " \n" +
                this.mascara + "\n" +
                "Por Defecto: " + this.valorDefecto + "\n";
        return aRetornar;
    }

    public String getMascara() {
        return mascara;
    }

    public void setMascara(String mascara) {
        this.mascara = mascara;
    }

    public int getNumeroDecimales() {
        return numeroDecimales;
    }

    public void setNumeroDecimales(int numeroDecimales) {
        this.numeroDecimales = numeroDecimales;
    }

    public float getValorDefecto() {
        return valorDefecto;
    }

    public void setValorDefecto(float valorDefecto) {
        this.valorDefecto = valorDefecto;
    }

    /**
     * Clase para cargar un tipoCampoNumero desde la base de datos
     * @param ID - correlativo
     * @return estado: 1-exito, 0-error
     */
    public int cargarNumero(int ID){
        int estado = 0;
        
        return estado;
    }
}
