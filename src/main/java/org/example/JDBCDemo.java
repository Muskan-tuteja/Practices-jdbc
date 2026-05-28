package org.example;

import java.sql.*;

public class JDBCDemo {
    private static final String URL = "jdbc:postgresql://localhost:5432/staff_db";
    private static final String User = "postgres";
    private static final String Password = "root";

    public static void main(String[] args) {
        try (Connection conn = DriverManager.getConnection(URL, User, Password);) {
            System.out.println("Connected to database successfully");
            insertStudents(conn, "muskanuu","mjshj@45");
//            updateStudents(conn,1,"mahi","mahi@hsadgh");
            selectStudents(conn);
//            deleteStudents(conn,1);

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    private static void insertStudents(Connection conn,String name, String email){
        String sql = "INSERT INTO Students(name,email) VALUES('"+name+";"+email+"')";
    try(Statement stmt = conn.createStatement()){
        int rows = stmt.executeUpdate(sql);
        System.out.println("INSERTED: " + rows);

    }catch (SQLException e){
        e.printStackTrace();
    }

    }

    private static void selectStudents(Connection conn){
        String sql = "SELECT * FROM Studentss";
        try(Statement stmt= conn.createStatement()){
           ResultSet resultSet=  stmt.executeQuery(sql);
            System.out.println("Students List:");
            while(resultSet.next()){
                int id =  resultSet.getInt("id");
                String name = resultSet.getString("name");
                String email = resultSet.getString("email");
                System.out.println(name + " " + email);
            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    private static void updateStudents(Connection conn,int id, String name,String email){
       String sql = "UPDATE Studentss SET name = '" + name +"', email = '" + email +"' WHERE id = '"+id+"'";

    try(Statement stmt = conn.createStatement()){
        int rows = stmt.executeUpdate(sql);
        System.out.println("UPDATED: " + rows);

    }catch (SQLException e){
        e.printStackTrace();
    }

    }

    private  static void deleteStudents(Connection conn,int id){
        String sql = "DELETE FROM Studentss WHERE id = '"+id+"'";

        try(Statement stmt = conn.createStatement()){
            int rows = stmt.executeUpdate(sql);
            System.out.println("DELETED: " + rows);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}





















//
//
//
//
//
//
//        Connection conn = null;
//        try {
//             conn = DriverManager.getConnection(URL,User,Password);
//            System.out.println("Connected to database successfully");
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }finally {
//            try {
//                conn.close();
//                System.out.println("Connection closed successfully");
//            } catch (SQLException e) {
//                throw new RuntimeException(e);
//            }
//        }




