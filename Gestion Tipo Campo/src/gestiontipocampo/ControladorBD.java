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

    private String conexionString1 = "jdbc:mysql://grupoingegift5.bluechiphosting.com/grupoin2_GiftBD?user=grupoin2_user&password=Qwerty123";
    private String conexionString2 = "Aquí va a ir la conexión con de la ECCI";
    protected static int conexionSeleccionada;
    private Connection conexion = null;
    private ResultSet resultado = null;

    public int probarConexion(int  numeroConexion){
        String conexionStringAProbar="";
        
        switch(numeroConexion){
            case 1:
                conexionStringAProbar=conexionString1;
            break;
            case 2:
                conexionStringAProbar=conexionString2;
            break;
        }

        int estado=1;// en caso de exito se retorna1
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conexion = DriverManager.getConnection(conexionStringAProbar);
        }
        catch (SQLException e) {
            System.out.println("*SQL Exception: *" + e.toString());
            estado=-1;//error producido
        } catch (ClassNotFoundException cE) {
            System.out.println("--Class Not Found Exception: --" + cE.toString());
            estado=-1;//error producido
        }
        System.out.println("Probada conexion 1\n");
        return estado;


    }

    public ControladorBD() {
        System.out.print("HOla Mundo");
    }
    private String getConexionEstablecida(){
       if(1==conexionSeleccionada){
            return conexionString1;
        }
        else{
            return conexionString2;
        }
    }
    public ResultSet getResultSet(String consulta) {
        String conexionAUtilizar=getConexionEstablecida();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conexion = DriverManager.getConnection(conexionAUtilizar);
            Statement query = null;
            String SQL = consulta;
            query = conexion.createStatement();
            resultado = query.executeQuery(SQL);
            System.out.println("\nSe realizo la consula con la conexion # "+conexionSeleccionada+"\n");
        } catch (SQLException e) {
            System.out.println("*SQL Exception: *" + e.toString());
        } catch (ClassNotFoundException cE) {
            System.out.println("--Class Not Found Exception: --" + cE.toString());
        }
        return resultado;
    }

    /*public ResultSet hacerConsulta(String consulta) {
        String conexionAUtilizar=getConexionEstablecida();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conexion = DriverManager.getConnection(conexionAUtilizar);
            Statement query = null;
            String SQL = consulta;
            query = conexion.createStatement();
            resultado = query.executeQuery(SQL);
        } catch (SQLException e) {
            System.out.println("*SQL Exception: *" + e.toString());
        } catch (ClassNotFoundException cE) {
            System.out.println("--Class Not Found Exception: --" + cE.toString());
        }
        return resultado;
    }*/

}
