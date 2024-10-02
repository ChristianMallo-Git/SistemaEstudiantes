package cm.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {

    public static Connection getConexion(){
        Connection conexion = null;
        String dataBase = "estudiante_db";
        String url = "jdbc:mysql://localhost:3306/" + dataBase;
        String user = "root";
        String password = "RORI";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); //
            conexion = DriverManager.getConnection(url, user, password);
        } catch (ClassNotFoundException | SQLException e){
            System.out.println("The connection could not be made " + e.getMessage());
        }
        return conexion;
    }
    //    public static void main(String[] args) {
//        Connection conexion = Conexion.getConexion();   //--> Comprobación de que la conexión es efectiva
//        if(conexion != null){
//            System.out.println("Connection has been made" + conexion);
//        }else{
//            System.out.println("An error ocurred while connecting");
//        }
//    }
}
