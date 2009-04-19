/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package gestiontipocampo;
import java.util.Vector;

import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.*;

/**
 *
 * @author Administrator
 */
public class Lista extends TipoCampo{
    

    private SortedSet miembrosLista;
   // public Map<String, String> miembroLista;
    public  Modelo  miModelo = new Modelo();

    public Lista(){
           //Map<String, String> miembroLista=new HashMap<String, String>();

        miembrosLista= new TreeSet();
    }
    //  SortedSet set = new TreeSet();

    public void setMiembrosLista(Vector vector){
   // miembroLista
           for(int i=0; i<vector.size();i++){

               miembrosLista.add(vector.get(i).toString());
           }

    }
    public SortedSet getMiembrosLista(){
        return this.miembrosLista;
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
    public void agregarMiembro(String nombreMiembro){//agregar elemento a la lista
        ControladorBD buscador= new ControladorBD();
        buscador.doUpdate("insert into MIEMBROLISTA (valor,IDLista) values ('"+nombreMiembro+"',"+this.correlativo+");");
    }
    @Override
    public String toString(){
        return super.toString();
    }
    

}
