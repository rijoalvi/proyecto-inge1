/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gestiontipocampo;

import java.sql.*;
import java.util.Vector;
import java.util.Map;
import java.util.HashMap;

/**
 *
 * @author Luis Carlos Chavarría
 */
public class ControladorBD {

    private String conexionString1 = "jdbc:mysql://grupoingegift5.bluechiphosting.com/grupoin2_GiftBD?user=grupoin2_user&password=Qwerty123";
    private String conexionString2 = "jdbc:sqlserver://bd;databaseName=bdInge1g2_g2;user=usuarioInge1_g2;password=ui1_g2";
    protected static int conexionSeleccionada = -1;
    private Connection conexion = null;
    private ResultSet resultado = null;
    protected final int CONEXION_MYSQL = 1;
    protected final int CONEXION_SQLSERVER = 2;

    public int probarConexion(int numeroConexion) {

        String conexionStringAProbar = "";
        switch (numeroConexion) {
            case CONEXION_MYSQL:
                conexionStringAProbar = conexionString1;
                break;
            case CONEXION_SQLSERVER:
                conexionStringAProbar = conexionString2;
                break;
        }

        int estado = 1;// en caso de exito se retorna1
        try {
            switch (numeroConexion) {
                case CONEXION_MYSQL:
                    Class.forName("com.mysql.jdbc.Driver");
                    break;
                case CONEXION_SQLSERVER:
                    Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                    break;
                default:
                    estado = -1;
            }
            if (estado == 1) {
                //Class.forName("com.microsoft.sqlserver.jdbc.Driver");
                // Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                conexion = DriverManager.getConnection(conexionStringAProbar);
                System.out.println("Conexión exitosa: " + numeroConexion + "\n");
            }
        } catch (SQLException e) {
            //  System.out.println("*SQL Exception: *" + e.toString());
            System.out.println("No se pudo acceder a la conexion: " + numeroConexion + "\n");
            estado = -1;//error producido
        } catch (ClassNotFoundException cE) {
            System.out.println("No se pudo acceder a la conexion: " + numeroConexion + ".\n");
            //System.out.println("--Class Not Found Exception: --" + cE.toString());
            estado = -1;//error producido
        }

        return estado;


    }

    /**
     * Constructor por omisión
     */
    public ControladorBD() {
    }

    private String getConexionEstablecida() {
        String conexionEstablecida = "";
        switch (conexionSeleccionada) {
            case 1:
                conexionEstablecida = conexionString1;
                break;
            case 2:
                conexionEstablecida = conexionString2;
                break;
            default:
                conexionEstablecida = "";
        }
        return conexionEstablecida;
    }

    public ResultSet getResultSet(String consulta) {
        String conexionAUtilizar = getConexionEstablecida();
        if (conexionSeleccionada != -1) {
            try {
                if (getConexionEstablecida().equals(conexionString1)) {
                    Class.forName("com.mysql.jdbc.Driver");
                } else {
                    Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                }
                conexion = DriverManager.getConnection(conexionAUtilizar);
                Statement query = null;
                String SQL = consulta;
                query = conexion.createStatement();
                resultado = query.executeQuery(SQL);
                System.out.println("Se realizo la consula con la conexion # " + conexionSeleccionada + "");
            } catch (SQLException e) {
                System.out.println("*SQL Exception: *" + e.toString());
            } catch (ClassNotFoundException cE) {
                System.out.println("--Class Not Found Exception: --" + cE.toString());
            }
        } else {
            System.out.println("GIFT Configurador no se encuentra conectado a ninguna base de datos");

            resultado = null;
        }
        return resultado;
    }

    public int doUpdate(String consulta) {
        String conexionAUtilizar = getConexionEstablecida();
        int result = -1;
        try {
            if (getConexionEstablecida().equals(conexionString1)) {
                Class.forName("com.mysql.jdbc.Driver");
            } else {
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            }
            conexion = DriverManager.getConnection(conexionAUtilizar);
            Statement query = null;
            String SQL = consulta;
            query = conexion.createStatement();
            result = query.executeUpdate(SQL);
            System.out.println("Se realizo la consula con la conexion # " + conexionSeleccionada + "");
        } catch (SQLException e) {
            System.out.println("*SQL Exception: *" + e.toString());
        } catch (ClassNotFoundException cE) {
            System.out.println("--Class Not Found Exception: --" + cE.toString());
        }
        return result;
    }

    /* public Vector getResultSetVector(String consulta) {
    Vector miVector = new Vector();
    try {
    ResultSet result = this.getResultSet("select correlativo ,nombre, descripcion, ultimaActualizacion from TIPOCAMPO where correlativo=31;");
    if (result.next()) {
    miVector.add(result.getObject("nombre"));
    }
    } catch (SQLException e) {
    System.out.println("*SQL Exception: aca?*" + e.toString());
    }
    return miVector;
    }*/
    public Map getResultSetMap(String consulta, Vector campos) {
        Map<String, String> miMapa = new HashMap<String, String>();

        try {
            ResultSet result = this.getResultSet(consulta);
            if (result.next()) {
                // miVector.add(resultado.getObject("nombre"));

                for (int i = 0; i < campos.size(); i++) {
                    miMapa.put(campos.get(i).toString(), result.getObject(campos.get(i).toString()).toString());
                }
            /*  miMapa.put("correlativo",resultado.getObject("correlativo").toString());
            miMapa.put("nombre",resultado.getObject("nombre").toString());
            miMapa.put("descripcion",resultado.getObject("descripcion").toString());
            miMapa.put("ultimaActualizacion",resultado.getObject("ultimaActualizacion").toString());*/
            }
        } catch (SQLException e) {
            System.out.println("*SQL Exception: aca?*" + e.toString());
        }
        return miMapa;
    }

    public ConsultaLista getConsultaLista() {
        ConsultaLista consulta;
        if (CONEXION_MYSQL == conexionSeleccionada) {
            consulta = new ConsultaListaMySQL();
        } else {
            consulta = new ConsultaListaSQLServer();
        }
        return consulta;
    }

    public ConsultaFormulario getConsultaFormulario() {
        ConsultaFormulario consulta;
        if (CONEXION_MYSQL == conexionSeleccionada) {
            consulta = new ConsultaFormularioMySQL();
        } else {
            consulta = new ConsultaFormularioSQLServer();
        }
        return consulta;
    }
}
