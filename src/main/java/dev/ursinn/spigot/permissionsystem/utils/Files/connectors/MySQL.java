/*
 * MIT License
 *
 * Copyright (c) 2018 - 2020 Ursin Filli
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 *
 */

package dev.ursinn.spigot.permissionsystem.utils.Files.connectors;

import dev.ursinn.spigot.permissionsystem.PermissionSystem;

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
