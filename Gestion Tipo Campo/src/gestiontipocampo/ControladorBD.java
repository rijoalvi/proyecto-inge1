/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package gestiontipocampo;
import java.sql.*;
/**
 *
 * @author Luis Carlos Chavarría
 */


public class ControladorBD {
    protected static String connectionUrl;// = "jdbc:mysql://lucachaco.bluechiphosting.com/lucachac_db?user=lucachac_user&password=todosepuede";
    private Connection conexion;
    private ResultSet resultado = null;
    public ControladorBD(){
        System.out.print("HOla Mundo");


    }
    public ResultSet getResultSet(String consulta){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conexion = DriverManager.getConnection(connectionUrl);
            Statement query = null;
            String SQL = consulta;
            query = conexion.createStatement();
            resultado = query.executeQuery(SQL);
        } catch (SQLException e) {
            System.out.println("*SQL Exception: *"+ e.toString());
        } catch (ClassNotFoundException cE) {
            System.out.println("--Class Not Found Exception: --"+ cE.toString());
        }
        return resultado;
    }

        public ResultSet hacerConsulta(String consulta){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conexion = DriverManager.getConnection(connectionUrl);
            Statement query = null;
            String SQL = consulta;
            query = conexion.createStatement();
            resultado = query.executeQuery(SQL);
        } catch (SQLException e) {
            System.out.println("*SQL Exception: *"+ e.toString());
        } catch (ClassNotFoundException cE) {
            System.out.println("--Class Not Found Exception: --"+ cE.toString());
        }
        return resultado;
    }

}
