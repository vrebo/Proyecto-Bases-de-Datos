package modelos;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTable;

public class ConexionSQL implements Serializable {

    private Connection connection;
    private Statement statement;
    private String nombreConexion;
    private String host;
    private String port;
    private String user;
    private String password;

    /**
     * Crea una conexión con el SGBD local loggueando con el usuario y su
     * contraseña especificada
     *
     * @param nombreConexion
     * @param user
     * @param password
     */
    public ConexionSQL(String nombreConexion, String user, String password) {
        this(nombreConexion, "127.0.0.1", "3306", user, password);
    }

    /**
     * Crea una conexión con el SGBD del host que se especifique
     *
     * @param nombreConexion
     * @param host
     * @param port
     * @param user
     * @param password
     */
    public ConexionSQL(String nombreConexion, String host, String port, String user, String password) {
        this.nombreConexion = nombreConexion;
        this.host = host;
        this.port = port;
        this.user = user;
        this.password = password;
    }

    /**
     * Crea la conexión al SGBD con los valores especificados en el constructor
     *
     * @return
     * @throws java.sql.SQLException
     */
    public boolean crearConexion() throws SQLException {
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            connection = DriverManager.getConnection("jdbc:mysql://" + host
                    + ":" + port + "/", user, password);

            if (connection != null) {
                statement = connection.createStatement();
            } else {
                System.out.println("Problema con la conexión");
            }
            return true;
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            return false;
        }
    }

    /**
     * Crea una BD con el nombre que se especifique.
     *
     * @param nombreBD
     * @return
     * @throws java.sql.SQLException
     */
    public int crearBaseDatos(String nombreBD) throws SQLException {
        return statement.executeUpdate("CREATE DATABASE " + nombreBD);
    }

    /**
     * Crea una tabla en la BD seleccionada actualmente con el nombre y
     * carácteristicas que se especifique.
     *
     * @param instruccion
     * @return
     * @throws java.sql.SQLException
     */
    public int crearTabla(String instruccion) throws SQLException {
        return statement.executeUpdate("CREATE TABLE " + instruccion);
    }

    /**
     * Consulta al SGBD por los datos especificados en la instrucción.
     *
     * @param consulta
     * @return un <code>Resulset </code> con el resultado de la consulta al SGBD
     * @throws java.sql.SQLException
     */
    public JTable consulta(String consulta) throws SQLException {
        ResultSet resultados = statement.executeQuery(consulta);
        ResultSetMetaData data = resultados.getMetaData();
        int filas = 0, columnas;
        columnas = data.getColumnCount();
        String[] titulos = new String[columnas];
        for (int i = 0; i < columnas; i++) {
            titulos[i] = data.getColumnName(i + 1);
        }
        while (resultados.next()) {
            filas++;
        }
        resultados.beforeFirst();
        String[][] datos = new String[filas][columnas];
        for (int i = 0; resultados.next(); i++) {
            for (int j = 0; j < columnas; j++) {
                datos[i][j] = resultados.getString(j + 1);
            }
        }
        return new JTable(datos, titulos);
    }

    /**
     * Hace la consuta especifica SHOW DATABASES o TABLES
     *
     * @param estructura
     * @return
     * @throws java.sql.SQLException
     */
    public DefaultComboBoxModel<String> showEstructuraSimple(String estructura) throws SQLException {
        ResultSet resultados = statement.executeQuery("SHOW " + estructura);
        DefaultComboBoxModel<String> items = new DefaultComboBoxModel<>();
        while (resultados.next()) {
            items.addElement(resultados.getString(1));
        }
        return items;
    }

    /**
     * Describe la tabla especificada.
     *
     * @param tabla
     * @return
     * @throws java.sql.SQLException
     */
    public JTable describeTabla(String tabla) throws SQLException {
        return consulta("DESCRIBE " + tabla);
    }
    
    /**
     * Elimina la tabla que se especifique.
     * @param tabla La tabla a eliminar.
     * @return
     * @throws SQLException 
     */
    public int eliminaTabla(String tabla) throws SQLException {
        return statement.executeUpdate("DROP TABLE " + tabla);
    }
    
    /**
     * Elimina la base que se especifique.
     * @param base La base a eliminar.
     * @return
     * @throws SQLException 
     */
    public int eliminaBase(String base) throws SQLException {
        return statement.executeUpdate("DROP DATABASE " + base);
    }
    
    /**
     * Selecciona una BD para gestionar.
     *
     * @param instruccion
     * @return
     * @throws java.sql.SQLException
     */
    public int seleccionaBase(String instruccion) throws SQLException {
        return statement.executeUpdate("USE " + instruccion);
    }

    /**
     * Cierra la conexión entre este objeto y el SGBD.
     *
     * @throws java.sql.SQLException
     */
    public void cierraConexion() throws SQLException {
        statement.close();
        connection.close();
    }

    public String getNombreConexion() {
        return nombreConexion;
    }

    public void setNombreConexion(String nombreConexion) {
        this.nombreConexion = nombreConexion;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

}
