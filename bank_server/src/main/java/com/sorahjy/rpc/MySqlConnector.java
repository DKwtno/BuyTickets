package com.sorahjy.rpc;

import java.sql.*;

public class MySqlConnector {
    // 驱动程序名
    private static String driver = "com.mysql.jdbc.Driver";
    // URL指向要访问的数据库名
    private static String DB_URL = "jdbc:mysql://127.0.0.1:3306/bank_rpc?useUnicode=true&characterEncoding=utf-8&useSSL=false";
    // MySQL配置时的用户名
    private static String USER = "root";
    // MySQL配置时的密码
    private static String PASS = "286956679hjy";

    public boolean checkUser(String username){
        boolean hasUser=false;
        Connection conn = null;
        Statement stmt = null;
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();
            String sql;
            sql = "SELECT * FROM user WHERE username='"+username+"'";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                hasUser=true;
//                int id = rs.getInt("id");
//                String name = rs.getString("name");
//                String url = rs.getString("url");

            }
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null) stmt.close();
            } catch (SQLException se2) {
            }
            try {
                if (conn != null) conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
        return hasUser;
    }

    public boolean login(String username,String passwd) {
        boolean hasUser = false;
        Connection conn = null;
        Statement stmt = null;
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();
            String sql;
            sql = "SELECT * FROM user WHERE username='"+username+"' AND passwd='"+passwd+"'";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                hasUser = true;
            }
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null) stmt.close();
            } catch (SQLException se2) {
            }
            try {
                if (conn != null) conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
        return hasUser;
    }

    public boolean register(String username, String passwd) {
        if(checkUser(username)){
            return false;
        }
        Connection conn = null;
        Statement stmt = null;
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();
            String sql;
            sql = "INSERT INTO user VALUES ('"+username+"','"+passwd+"',0)";
            stmt.executeUpdate(sql);
            stmt.close();
            conn.close();
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null) stmt.close();
            } catch (SQLException se2) {
            }
            try {
                if (conn != null) conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
        return true;
    }

    public double getBalance(String username) {
        Connection conn = null;
        Statement stmt = null;
        double balance=0;
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();
            String sql;
            sql = "SELECT deposite FROM user WHERE username='" + username + "'";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                balance = rs.getDouble("deposite");
            }
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null) stmt.close();
            } catch (SQLException se2) {
            }
            try {
                if (conn != null) conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
        return balance;
    }

    public void setBalance(String username,double balance) {
        Connection conn = null;
        Statement stmt = null;
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();
            String sql;
            sql = "UPDATE user SET deposite="+balance+" WHERE username='"+username+"'";
            stmt.executeUpdate(sql);
            stmt.close();
            conn.close();
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null) stmt.close();
            } catch (SQLException se2) {
            }
            try {
                if (conn != null) conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }

}
