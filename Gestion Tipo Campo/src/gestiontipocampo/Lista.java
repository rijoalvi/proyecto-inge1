/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package gestiontipocampo;
import java.util.Vector;

import java.util.Map;
import java.util.HashMap;

/**
 *
 * @author Administrator
 */
public class Lista extends TipoCampo{
    
    private Vector miembroLista;
    public  Modelo  miModelo = new Modelo();

    public Lista(){
        
    }
    public Vector getModeloVector(String consulta,String campoTexto,String campoNumero){

        return miModelo.getModeloEnVector(consulta,campoTexto,campoNumero);
    }
    public void setLista(){

      //  Map<String, String>  miMapa= new Map<String, String>();
        Map<String, String> miMapa;
        ControladorBD buscador=new ControladorBD();
        Vector campos=new Vector();
        campos.add("correlativo");
        campos.add("nombre");
        campos.add("descripcion");
        campos.add("ultimaActualizacion");
        miMapa=buscador.getResultSetMap("select "+campos.get(0)+", "+campos.get(1)+", "+campos.get(2)+", "+campos.get(3)+" from TIPOCAMPO where correlativo=31;",campos);
        this.correlativo=miMapa.get("correlativo");
        this.nombre=miMapa.get("nombre");
        this.descripcion=miMapa.get("descripcion");

    }
    @Override
    public String toString(){
        return super.toString();
    }
    

}
