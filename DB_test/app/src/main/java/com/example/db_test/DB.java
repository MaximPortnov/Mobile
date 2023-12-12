package com.example.db_test;


import android.util.Log;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DB {
    static private final String server = "10.0.2.2";
    static private final String database = "test";
    static private final int port = 3306;
    static private final String user = "root";
    static private final String pass = "";
    static private final String driver = "com.mysql.jdbc.Driver";
    static private final String url = String.format("jdbc:mysql://%s:%d/%s", server, port, database);

    static Connection connection = null;
    static public boolean conStatus;
    static public Connect connectionToDataBase = new Connect();

    public static class Connect extends Thread {
        public void run() {
            try {
                Class.forName(driver);
                Log.i("DB", "con" + url);
                connection = DriverManager.getConnection(url, user, pass);
                conStatus = true;
                Log.i("DB", "connectionToDataBase: connection true" + url);
            } catch (SQLException e) {
                Log.e("DB", "connectionToDataBase: connection false" + url);
                e.printStackTrace();
                conStatus = false;
            } catch (ClassNotFoundException e) {
                Log.e("DB", "connectionToDataBase: connection false" + url);
                e.printStackTrace();
                conStatus = false;
            }
            connectionToDataBase = new Connect();
        }
    }

    public static abstract class Query extends Thread{
        protected String sql = null;
        protected ResultSet rs;
        protected abstract void logic() throws SQLException;
        public void run() {
            if (!conStatus) {
                return;
            }
            if (sql == null){
                Log.e("DB", "run: sql query is null");
                return;
            }
            try(Statement stmt = connection.createStatement() ) {
                Log.i("DB", "run: \n" + sql);
                rs = stmt.executeQuery(sql);
                this.logic();
            } catch (SQLException e) {
                Log.e("DB", "query: \n" + e);
            }
        }
    }

    public static ArrayList<Integer> nums = new ArrayList<>();
    public static GetNum getNum = new GetNum();
    public static class GetNum extends Query {
        public GetNum(){
            sql = "SELECT * FROM test";
        }
        @Override
        protected void logic() throws SQLException {
            while (rs.next()) {
                nums.add(rs.getInt("num"));
            }
        }
    }


    static public Close closeConnectionToDataBase = new Close();

    public static class Close extends Thread {
        public void run() {
            try {
                connection.close();
            } catch (SQLException e) {
                Log.e("BD", "close: \n" + e);
            }
            closeConnectionToDataBase = new Close();
        }
    }

}
