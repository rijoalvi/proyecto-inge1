/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package gestiontipocampo;

import java.sql.ResultSet;

/**
 *
 * @author Ricardo
 */
public class consultasSQL {
    
    protected ControladorBD control;

    ResultSet seleccionarTodoTipoCampo(String columnaAComparar, String elemComparacion){
        control= new ControladorBD();
        ResultSet respuesta = null;
        respuesta = control.getResultSet("Select * from TIPOCAMPO where "+columnaAComparar+" = " + elemComparacion);
        return respuesta;
    }

    ResultSet seleccionarTodoGenerico(String tipo, String columnaAComparar, String elemComparacion){
        control= new ControladorBD();
        ResultSet respuesta = null;
        respuesta = control.getResultSet("Select * from " + tipo + " where "+columnaAComparar+" = " + elemComparacion);
        return respuesta;
    }

}
