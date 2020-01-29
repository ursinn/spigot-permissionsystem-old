package net.crazycraftland.spigot.permissionsystem.utils.Files;

import net.crazycraftland.spigot.permissionsystem.PermissionSystem;

import java.util.List;
import java.util.UUID;

public class FileManager implements FileInterface {

    private FileType fileType;
    private PermissionSystem plugin;
    private YamlFile yamlFile;
    private MySQLFile mySQLFile;
    private SQLiteFile sqLiteFile;
    private MongoDbFile mongoDbFile;
    private RedisFile redisFile;

    public FileManager(FileType fileType, PermissionSystem plugin) {
        this.fileType = fileType;
        this.plugin = plugin;

        this.yamlFile = new YamlFile();
        this.mySQLFile = new MySQLFile(
                plugin.getConfig().getString("mysql.host"),
                plugin.getConfig().getString("mysql.port"),
                plugin.getConfig().getString("mysql.database"),
                plugin.getConfig().getString("mysql.username"),
                plugin.getConfig().getString("mysql.password")
        );
        this.sqLiteFile = new SQLiteFile();
        this.mongoDbFile = new MongoDbFile();
        this.redisFile = new RedisFile();
    }

    @Override
    public List<String> getGroups() {
        if (fileType == FileType.FILE) return yamlFile.getGroups();
        if (fileType == FileType.MYSQL) return mySQLFile.getGroups();
        if (fileType == FileType.SQLITE) return sqLiteFile.getGroups();
        if (fileType == FileType.MONGODB) return mongoDbFile.getGroups();
        if (fileType == FileType.REDIS) return redisFile.getGroups();
        return null;
    }

    @Override
    public List<String> getGroupPermissions(String group) {
        if (fileType == FileType.FILE) return yamlFile.getGroupPermissions(group);
        if (fileType == FileType.MYSQL) return mySQLFile.getGroupPermissions(group);
        if (fileType == FileType.SQLITE) return sqLiteFile.getGroupPermissions(group);
        if (fileType == FileType.MONGODB) return mongoDbFile.getGroupPermissions(group);
        if (fileType == FileType.REDIS) return redisFile.getGroupPermissions(group);
        return null;
    }

    @Override
    public List<String> getUserPermissions(UUID user) {
        if (fileType == FileType.FILE) return yamlFile.getUserPermissions(user);
        if (fileType == FileType.MYSQL) return mySQLFile.getUserPermissions(user);
        if (fileType == FileType.SQLITE) return sqLiteFile.getUserPermissions(user);
        if (fileType == FileType.MONGODB) return mongoDbFile.getUserPermissions(user);
        if (fileType == FileType.REDIS) return redisFile.getUserPermissions(user);
        return null;
    }

    @Override
    public List<String> getUserGroups(UUID user) {
        if (fileType == FileType.FILE) return yamlFile.getUserGroups(user);
        if (fileType == FileType.MYSQL) return mySQLFile.getUserGroups(user);
        if (fileType == FileType.SQLITE) return sqLiteFile.getUserGroups(user);
        if (fileType == FileType.MONGODB) return mongoDbFile.getUserGroups(user);
        if (fileType == FileType.REDIS) return redisFile.getUserGroups(user);
        return null;
    }
}
