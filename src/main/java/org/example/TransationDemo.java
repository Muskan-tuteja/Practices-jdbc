package org.example;

import java.sql.*;

public class TransationDemo {
    private static final String URL = "jdbc:postgresql://localhost:5432/transat_db";
    private static final String USER = "postgres";
    private static final String PASSWORD = "root";


    public static void main(String[] args) {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
            System.out.println("Connected to database.");
            //TURNED OF AUTO COMMIT == NO AUTO SAVE
            conn.setAutoCommit(false);
            try{
                // Order, OrderItem

//            insert into orerder
                int orderId = insertOrder(conn, 101, "Muskan", 2000.0);
// INSERT INTO ORDER ITEM
                insertOrderItem(conn, orderId, "Lapotop01", 1, 2000.0);
// MANUAL COMMIT
                conn.commit();
                System.out.println("Transaction successful.");


            }catch (Exception e) {
                e.printStackTrace();
                conn.rollback();
                System.out.println("Transaction rolled back.");
            }
            finally{
                conn.setAutoCommit(true);


            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    private static int insertOrderItem(Connection conn, int orderId, String productName, int quatity, double price) {

        String sql = "INSERT INTO order_items (order_id, product_name, quatity,price) " + "VALUES (?, ?, ?,?)";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, orderId);
            pstmt.setString(2, productName);
            pstmt.setInt(3, quatity);
            pstmt.setDouble(4, price);

            int x = 10/0;
            int rows = pstmt.executeUpdate();
            System.out.println("INSARTED INTO ORDER_ITEMS : " + rows);


            return rows;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static int insertOrder(Connection conn, int customerId, String CustomerName, double price) {

        String sql = "INSERT INTO orders (user_id, customer_name, total_amount) " + "VALUES (?, ?, ?)";

        try (PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setInt(1, customerId);
            pstmt.setString(2, CustomerName);
            pstmt.setDouble(3, price);
            int rows = pstmt.executeUpdate();
            System.out.println("INSARTED INTO ORDERS : " + rows);

            try (ResultSet rs = pstmt.getGeneratedKeys()) {
                if (rs.next()) {
                    int orderId = rs.getInt(1);
                    System.out.println("Order ID : " + orderId);
                    return orderId;
                } else {
                    throw new SQLException("Failed to insert order");
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}


