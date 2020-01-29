package net.crazycraftland.spigot.permissionsystem.utils.Files;

import net.crazycraftland.spigot.permissionsystem.utils.Files.connectors.MySQL;

import java.util.List;
import java.util.UUID;

public class MySQLFile implements FileInterface {

    private MySQL mySQL;

    public MySQLFile(String host, String port, String database, String username, String password) {
        this.mySQL = new MySQL(host, port, database, username, password);
    }

    @Override
    public List<String> getGroups() {
        return null;
    }

    @Override
    public List<String> getGroupPermissions(String group) {
        return null;
    }

    @Override
    public List<String> getUserPermissions(UUID user) {
        return null;
    }

    @Override
    public List<String> getUserGroups(UUID user) {
        return null;
    }

}
