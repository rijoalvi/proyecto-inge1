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
 * @author luiscarlosch@gmail.com
 */
public class Lista extends TipoCampo{
    

    private SortedSet miembroLista;
    public  Modelo  miModelo;
    public  String IDMiembroPorDefecto;

    public Lista(){
        miembroLista= new TreeSet();
        miModelo = new Modelo();
    }
    public SortedSet getMiembroListaSet(){
        return this.miembroLista;
    }

    public void setMiembrosLista(Vector vector){
        miembroLista.clear();
           for(int i=0; i<vector.size();i++){
               miembroLista.add(vector.get(i));
           }
    }


    public Vector getModeloVector(String consulta,String campoTexto,String campoNumero){
        return miModelo.getModeloEnVector(consulta,campoTexto,campoNumero);
    }

    public void setLista(){

        Map<String, String> miMapa;
        ControladorBD buscador=new ControladorBD();

        Vector campos=new Vector();
        campos.add("t.correlativo");
        campos.add("nombre");
        campos.add("descripcion");
        campos.add("ultimaActualizacion");
        campos.add("IDMiembroPorDefecto");
        miMapa=buscador.getResultSetMap("select "+campos.get(0)+", "+campos.get(1)+", "+campos.get(2)+", "+campos.get(3)+", "+campos.get(4)+" from TIPOCAMPO t, LISTA l where t.correlativo=31 AND l.correlativo=t.correlativo;",campos);
        this.correlativo=miMapa.get("t.correlativo");
        this.nombre=miMapa.get("nombre");
        this.descripcion=miMapa.get("descripcion");
        this.IDMiembroPorDefecto=miMapa.get("IDMiembroPorDefecto");

    }
    public void agregarMiembro(String nombreMiembro){//agregar elemento a la lista
        ControladorBD buscador= new ControladorBD();
        buscador.doUpdate("insert into MIEMBROLISTA (valor,IDLista) values ('"+nombreMiembro+"',"+this.correlativo+");");
    }

  /*  public void borrarMiembro(String correlativo){
        ControladorBD buscador= new ControladorBD();
        buscador.doUpdate("delete from MIEMBROLISTA where correlativo = "+correlativo+" and IDLista=31;");
       
    }*/
    public void borrarMiembro(Object miembro){
        ControladorBD buscador= new ControladorBD();
        buscador.doUpdate("delete from MIEMBROLISTA where correlativo = "+((MiDato)(miembro)).ID+" and IDLista=31;");
        this.miembroLista.remove(miembro);

    }
    public Vector setAndGetMiembrosVectorActualizados(){
        this.setMiembrosLista(this.getModeloVector("select valor, ml.correlativo from MIEMBROLISTA ml, LISTA l where ml.IDLista=l.correlativo and l.correlativo="+this.correlativo+"", "valor", "correlativo"));

        SortedSet miembrosListaSet=new TreeSet();
        Vector miembrosListaVector= new Vector();

        miembrosListaSet=this.getMiembroListaSet();

        Iterator it = miembrosListaSet.iterator();

        while (it.hasNext()) {
            miembrosListaVector.add( it.next());
        }
MiDato.valorDefecto=this.IDMiembroPorDefecto;
        return miembrosListaVector;
        
       // lista.setListData(miembrosListaVector);
        //lista.setListData(this.miLista.getModeloVector("select valor, ml.correlativo from MIEMBROLISTA ml, LISTA l where ml.IDLista=l.correlativo and l.correlativo="+this.IDTipoCampo+"", "valor", "correlativo"));
    }

     public Vector getModeloMiembrosVector(){
      //  this.setMiembrosLista(this.getModeloVector("select valor, ml.correlativo from MIEMBROLISTA ml, LISTA l where ml.IDLista=l.correlativo and l.correlativo="+this.correlativo+"", "valor", "correlativo"));
MiDato.valorDefecto=this.IDMiembroPorDefecto;
        SortedSet miembrosListaSet=new TreeSet();
        Vector miembrosListaVector= new Vector();

        miembrosListaSet=this.getMiembroListaSet();

        Iterator it = miembrosListaSet.iterator();

        while (it.hasNext()) {
            miembrosListaVector.add( it.next());
        }

        return miembrosListaVector;
       // lista.setListData(miembrosListaVector);
        //lista.setListData(this.miLista.getModeloVector("select valor, ml.correlativo from MIEMBROLISTA ml, LISTA l where ml.IDLista=l.correlativo and l.correlativo="+this.IDTipoCampo+"", "valor", "correlativo"));
    }

    @Override
    public String toString(){
        return super.toString();
    }
    

}
