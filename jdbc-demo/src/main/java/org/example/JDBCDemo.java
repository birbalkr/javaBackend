package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCDemo {
    private static final String URL = "jdbc:mysql://localhost:3306/demo_db";
    private static final String USER = "root";
    private static final String PASSWORD = "passwd";

    public static void main(String[] args) {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
            System.out.println("Connected to Databases");
//            insertData(conn, "Aditya", "aditya@gmail.com");
            selectStudent(conn);
            updateStudent(conn, 2,"admin1","admin@gmail.com");
            deleteStudents(conn,3);
            selectStudent(conn);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    private static void insertData(Connection conn, String name, String email) {
        String sql = "insert into student (name, email) values('" + name + "','" + email + "')";
        try (Statement stmt = conn.createStatement()) {
           int rows=stmt.executeUpdate(sql);
            System.out.println("Insert: "+rows);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void selectStudent(Connection conn){
        String sql =" select * from student";
        try(Statement stmt = conn.createStatement()){
            ResultSet resultSet= stmt.executeQuery(sql);
            System.out.println("Student List: ");
            while(resultSet.next()){
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String email=resultSet.getString("email");
                System.out.print(id+" : " + name + " : "+ email +"\n");
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private  static void updateStudent(Connection conn, int id, String name, String email){
        String sql = "UPDATE student SET name = '" + name +
                "', email = '" + email +
                "' WHERE id = " + id;
        try(Statement stmt = conn.createStatement()){
           stmt.executeUpdate(sql);
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void deleteStudents(Connection conn,int id){
        String sql="delete from student where id = "+id;
    try(Statement stmt=conn.createStatement()){
        int rows = stmt.executeUpdate(sql);
        System.out.println("Delete"+rows);
    } catch (SQLException e) {
        throw new RuntimeException(e);
    }

    }

}

