package com.nikita.firtsproget.ui.DataBass;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BD_connect {

    private static final String URL = "jdbc:postgresql://localhost:5433/postgres";
    private static final String USER = "postgres";
    private static final String PASSWORD = "Popcka123";

    public void getNewConnection(ConnectionCallback callback) {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.execute(() -> {
            try {
                Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
                if (callback != null) {
                    callback.onConnectionSuccess(con);
                }
            } catch (SQLException e) {
                if (callback != null) {
                    callback.onConnectionError(e);
                }
            }
        });
    }

    public interface ConnectionCallback {
        void onConnectionSuccess(Connection con);
        void onConnectionError(SQLException error);
    }
}
