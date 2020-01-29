package net.crazycraftland.spigot.permissionsystem.utils.Files.connectors;

import net.crazycraftland.spigot.permissionsystem.PermissionSystem;

import java.sql.*;

public class MySQL {

    private static Connection con;
    private String host;
    private String port;
    private String database;
    private String username;
    private String password;

    public MySQL(String host, String port, String database, String username, String password) {
        this.host = host;
        this.port = port;
        this.database = database;
        this.username = username;
        this.password = password;
    }

    public void connect() {
        if (!isConnected()) {
            try {
                con = DriverManager.getConnection(
                        "jdbc:mariadb://" + this.host + ":" + this.port + "/" + this.database + "?autoReconnect=true&zeroDateTimeBehavior=convertToNull&tinyInt1isBit=false&characterEncoding=utf8&characterSetResults=utf8&yearIsDateType=false",
                        this.username, this.password);
                PermissionSystem.getInstance().getLogger().info("MySQL: Connected");
            } catch (SQLException e) {
                PermissionSystem.getInstance().getLogger().severe("MySQL: Connect Error | " + e.getMessage());
            }
        }
    }

    public void close() {
        if (isConnected()) {
            try {
                con.close();
                PermissionSystem.getInstance().getLogger().info("MySQL: Closed");
            } catch (SQLException e) {
                PermissionSystem.getInstance().getLogger().severe("MySQL: Close Error | " + e.getMessage());
            }
        }
    }

    private boolean isConnected() {
        return con != null;
    }

    public void update(String query) {
        if (isConnected()) {
            try {
                Statement st = con.createStatement();
                st.executeUpdate(query);
                st.close();
            } catch (SQLException e) {
                PermissionSystem.getInstance().getLogger().severe("MySQL: Update Error | " + e.getMessage());
            }
        }
    }

    public ResultSet getResult(String query) {
        ResultSet rs = null;
        if (isConnected()) {
            try {
                Statement st = con.createStatement();
                rs = st.executeQuery(query);
            } catch (SQLException e) {
                PermissionSystem.getInstance().getLogger().severe("MySQL: Result Error | " + e.getMessage());
            }
        }
        return rs;
    }
}
