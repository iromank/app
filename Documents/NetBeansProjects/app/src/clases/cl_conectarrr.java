package clases;

//import java.net.Socket;
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.sql.Statement;

//import javax.swing.JOptionPane;

/**
 *
 * @author CALIDAD
 */
import java.net.Socket;
import java.sql.*;
import javax.swing.JOptionPane;
public class cl_conectarrr implements i_datos_bd {
   private static Connection conexion = null;
    private static String bd = "jovi"; // Nombre de BD.
    private static String user = "root"; // Usuario de BD.
    private static String password = "baca7999baca"; // Password de BD.
    private static String url = "localhost";

    // Driver para MySQL en este caso.
    private static String driver = "com.mysql.jdbc.Driver";
    String server = "jdbc:mysql://" + url + ":3306/" + bd;

    /**
     * Método neecesario para conectarse al Driver y poder usar MySQL.
     *
     * @return
     */
    public boolean conectar() {
        boolean conectado;
        try {

            Class.forName(driver);
            conexion = DriverManager.getConnection(server, user, password);
            conectado = true;
            System.out.println("Conectando al Servidor: " + server);

        } catch (ClassNotFoundException | SQLException e) {
            //JOptionPane.showMessageDialog(null, "Error: Imposible realizar la conexion a BD." + server + "," + user + "," + password);
            JOptionPane.showMessageDialog(null, "Error al conectar \n" + e.getLocalizedMessage());
            System.out.print(e);
            e.printStackTrace();
            //System.exit(0);
            conectado = false;
        }
        return conectado;
    }

    /**
     * Método para establecer la conexión con la base de datos.
     *
     * @return
     */
    public Connection conx() {
        return conexion;
    }

    public boolean verificar_conexion() {
        boolean conectado = false;
        String dirWeb = "localhost";
        int puerto = 80;
        try {
            Socket s = new Socket(dirWeb, puerto);
            if (s.isConnected()) {
                System.out.println("Conexión establecida con la dirección: " + dirWeb + " a travéz del puerto: " + puerto);
                conectado = true;
            }
        } catch (Exception e) {
            conectado = false;
            System.err.println("No se pudo establecer conexión con: " + dirWeb + " a travez del puerto: " + puerto);
        }
        return conectado;
    }

    public Statement conexion() {
        Statement st = null;
        try {
            st = conexion.createStatement();
            //JOptionPane.showMessageDialog(null,"si conecto");
        } catch (SQLException e) {
            System.out.println("Error: Conexión incorrecta.");
            e.printStackTrace();
        }
        return st;
    }
  

    /**
     * Método para realizar consultas del tipo: SELECT * FROM tabla WHERE..."
     *
     * @param st
     * @param cadena La consulta en concreto
     * @return
     */
    public ResultSet consulta(Statement st, String cadena) {
        ResultSet rs = null;
        try {
            rs = st.executeQuery(cadena);
        } catch (SQLException e) {
            System.out.println("Error con: " + cadena);
            System.out.println("SQLException: " + e.getMessage());
            e.printStackTrace();
        }
        return rs;
    }

    /**
     * Método para realizar consultas de actualización, creación o eliminación.
     *
     * @param st
     * @param cadena La consulta en concreto
     * @return
     */
    public int actualiza(Statement st, String cadena) {
        int rs = -1;
        try {
            rs = st.executeUpdate(cadena);
        } catch (SQLException e) {
            System.out.println("Error con: " + cadena);
            System.out.println("SQLException: " + e.getMessage());
            JOptionPane.showMessageDialog(null, "SQLException: " + e.getMessage());
            e.printStackTrace();
        }
        return rs;
    }

    /**
     * Método para cerrar la consula
     *
     * @param rs
     */
    public void cerrar(ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (Exception e) {
                System.out.print("Error: No es posible cerrar la consulta.");
            }
        }
    }

    /**
     * Método para cerrar la conexión.
     *
     * @param st
     */
    public void cerrar(java.sql.Statement st) {
        if (st != null) {
            try {
                st.close();
            } catch (Exception e) {
                System.out.print("Error: No es posible cerrar la conexión.");
            }
        }
    }
//    private static Connection conexion = null;
//
//    // Driver para MySQL en este caso.
//    private static final String driver = "com.mysql.jdbc.Driver";
//
//    // Ruta del servidor.
//    // String server = "jdbc:mysql://lunasystemsperu.com:3306/" + bd;
//    String server = "jdbc:mysql://" + servidor + ":3306/" + bd;
//
//    /**
//     * Método para realizar consultas de actualización, creación o eliminación.
//     *
//     * @param st
//     * @param cadena La consulta en concreto
//     * @return
//     */
//    public int actualiza(Statement st, String cadena) {
//        int rs = -1;
//
//        try {
//            rs = st.executeUpdate(cadena);
//        } catch (SQLException e) {
//            JOptionPane.showMessageDialog(null, "Error con: " + cadena + "\nSQLException: " + e.getMessage());
//            System.out.println("Error con: " + cadena);
//            System.out.println("SQLException: " + e.getMessage());
//            e.printStackTrace();
//        }
//
//        return rs;
//    }
//
//    /**
//     * Método para cerrar la conexión.
//     *
//     * @param st
//     */
//    public void cerrar(java.sql.Statement st) {
//        if (st != null) {
//            try {
//                st.close();
//            } catch (SQLException e) {
//                System.out.print("Error: No es posible cerrar la conexión.");
//            }
//        }
//    }
//
//    /**
//     * Método para cerrar la consula
//     *
//     * @param rs
//     */
//    public void cerrar(ResultSet rs) {
//        if (rs != null) {
//            try {
//                rs.close();
//            } catch (SQLException e) {
//                System.out.print("Error: No es posible cerrar la consulta.");
//            }
//        }
//    }
//
//    public boolean conectar() {
//        boolean hecho = false;
//        try {
//            Class.forName(driver);
//            System.out.println(server + "\n");
//            conexion = DriverManager.getConnection(server, user, password);
//            hecho = true;
//            JOptionPane.showMessageDialog(null, "logrado");
//        } catch (ClassNotFoundException | SQLException e) {
//            JOptionPane.showMessageDialog(null, "Error! - Imposible realizar la conexion a BD. \nPongase en contacto con su administrador de Sistema");
//            System.out.print(e);
//            System.out.println("SQLException: " + e.getMessage());
//            e.printStackTrace();
//            hecho = false;
//            //System.exit(0);
//        }
//        return hecho;
//    }
//
//    public boolean verificar_conexion() {
//        boolean conectado = false;
//        String dirWeb = "www.lunasystemsperu.com";
//        int puerto = 80;
//        try {
//            Socket s = new Socket(dirWeb, puerto);
//            if (s.isConnected()) {
//                System.out.println("Conexión establecida con la dirección: " + dirWeb + " a travéz del puerto: " + puerto);
//                conectado = true;
//            }
//        } catch (Exception e) {
//            conectado = false;
//            System.err.println("No se pudo establecer conexión con: " + dirWeb + " a travez del puerto: " + puerto);
//        }
//        return conectado;
//    }
//
//    public void conectar(boolean b) {
//        throw new UnsupportedOperationException("Not supported yet.");    // To change body of generated methods, choose Tools | Templates.
//    }
//
//    public Statement conexion() {
//        Statement st = null;
//
//        try {
//            st = conexion.createStatement();
//        } catch (SQLException e) {
//            System.out.println("Error: Conexión incorrecta.");
//            e.printStackTrace();
//        }
//
//        return st;
//    }
//
//    /**
//     * Método para realizar consultas del tipo: SELECT * FROM tabla WHERE..."
//     *
//     * @param st
//     * @param cadena La consulta en concreto
//     * @return
//     */
//    public ResultSet consulta(Statement st, String cadena) {
//        ResultSet rs = null;
//
//        try {
//            rs = st.executeQuery(cadena);
//        } catch (SQLException e) {
//            System.out.println("Error con: " + cadena);
//            System.out.println("SQLException: " + e.getMessage());
//            e.printStackTrace();
//        }
//
//        return rs;
//    }
//
//    /**
//     * Método para establecer la conexión con la base de datos.
//     *
//     * @return
//     */
//    public Connection conx() {
//        return conexion;
//    }
}


//~ Formatted by Jindent --- http://www.jindent.com
