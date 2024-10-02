package cm.data;

import cm.student.Student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static cm.connection.Conexion.getConexion;

public class StudentDAO {

    public static List<Student> listStudent() {
        List<Student> listStudent = new ArrayList<>();
        PreparedStatement ps;
        ResultSet rs;
        Connection con = getConexion();
        String sql = "SELECT * FROM estudiante ORDER BY id_estudiante";

        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Student estudiante = new Student();
                estudiante.setIdStudent(rs.getInt("id_estudiante"));
                estudiante.setName(rs.getString("nombre"));
                estudiante.setSurname(rs.getString("apellido"));
                estudiante.setPhoneNumber(rs.getInt("telefono"));
                estudiante.setEmail(rs.getString("email"));
                listStudent.add(estudiante);
            }
        } catch (SQLException e) {
            System.out.println("An error ocurred when selecting data " + e.getMessage());
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.out.println("An error ocurred when closing the connection " + e.getMessage());
            }
        }
        return listStudent;
    }

    public static boolean findById(Student student) {
        PreparedStatement ps;
        ResultSet rs;
        Connection con = getConexion();
        String sql = "SELECT * FROM estudiante WHERE id_estudiante = ?";
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, student.getIdStudent());
            rs = ps.executeQuery();
            if (rs.next()) {
                student.setName(rs.getString("nombre"));
                student.setSurname(rs.getString("apellido"));
                student.setPhoneNumber(rs.getInt("telefono"));
                student.setEmail(rs.getString("email"));
                return true;
            }
        } catch (SQLException e) {
            System.out.println("An error ocurred while searching for student " + e.getMessage());
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.out.println("An error occurred while closing the connection" + e.getMessage());
            }
        }
        return false;
    }

    public static boolean insertStudent(Student student) {
        PreparedStatement ps;
        Connection con = getConexion();
        String sql = "INSERT INTO estudiante(nombre, apellido, telefono, email) " +
                "VALUES (?, ?, ?, ?)";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, student.getName());
            ps.setString(2, student.getSurname());
            ps.setInt(3, student.getPhoneNumber());
            ps.setString(4, student.getEmail());
            ps.execute();
            return true;

        } catch (SQLException e) {
            System.out.println("An error occurred when adding the student " + e.getMessage());
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.out.println("An error occurred while closing the connection " + e.getMessage());
            }
        }
        return false;
    }

    public static boolean modifyStudent(Student student) {
        PreparedStatement ps;
        Connection con = getConexion();
        String sql = "UPDATE estudiante SET nombre=?, apellido=?, telefono=?, email=?" +
                "WHERE id_estudiante= ?";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, student.getName());
            ps.setString(2, student.getSurname());
            ps.setInt(3, student.getPhoneNumber());
            ps.setString(4, student.getEmail());
            ps.setInt(5, student.getIdStudent());
            ps.execute();
            return true;
        } catch (SQLException e) {
            System.out.println("Error when modifying the student " + e.getMessage());
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.out.println("An error occurred while closing the connection " + e.getMessage());
            }
        }
        return false;
    }

    public static boolean deleteStudent(Student student) {
        PreparedStatement ps;
        Connection con = getConexion();
        String sql = "DELETE FROM estudiante WHERE id_estudiante = ? ";
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, student.getIdStudent());
            ps.execute();
            return true;
        } catch (SQLException e) {
            System.out.println("Error when trying to delete student " + e.getMessage());
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.out.println("An error occurred while closing the connection " + e.getMessage());
            }
        }
        return false;
    }
 }
