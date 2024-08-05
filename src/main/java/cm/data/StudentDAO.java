package cm.data;
import cm.connection.Conexion;
import cm.student.Student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import static cm.connection.Conexion.getConexion;

public class StudentDAO {

    public static List<Student> listStudent = new ArrayList<>();

    public static void listStudent() {

        PreparedStatement ps;
        // PreparedStatement --> nos ayuda a prepara la sentencia SQL
        // que vamos a ejecutar hacia la base de datos
        ResultSet rs;
        // ResultSet --> es un objeto que nos permite almacenar el resultado
        // obtenido de la base de datos
        Connection con = getConexion();
        String sql = "SELECT * FROM estudiante ORDER BY id_estudiante";

        try{
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                Student estudiante = new Student();
                estudiante.setIdStudent(rs.getInt("id_estudiante"));
                estudiante.setName(rs.getString("nombre"));
                estudiante.setSurname(rs.getString("apellido"));
                estudiante.setPhoneNumber(rs.getInt("telefono"));
                estudiante.setEmail(rs.getString("email"));
                listStudent.add(estudiante);
            }
        }catch (SQLException e){
            System.out.println("An error ocurred when selecting data " + e.getMessage());
        }finally {
         try{
             con.close();
         }catch(SQLException e){
             System.out.println("An error ocurred when closing the connection " + e.getMessage());
         }
        }
    }
}
