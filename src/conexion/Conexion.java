
package conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
    
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String URL = "jdbc:mysql://localhost:3306/supermark";
     static final String USER = "root";
    static final String PASS = "TheOctavarium88**";
    Connection conexion;

    public Conexion() {
         
    }
    
    public Connection conectar() throws ClassNotFoundException, SQLException{
        Class.forName(JDBC_DRIVER);
        System.out.println("Conectando a la base de datos...");
        this.conexion = DriverManager.getConnection(URL, USER, PASS);
        return conexion;
    }

    
}
