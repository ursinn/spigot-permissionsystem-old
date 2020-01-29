package net.crazycraftland.spigot.permissionsystem.utils.Files;

public enum FileType {

    FILE("file"), // YAML
    MYSQL("mysql"), // MySQL
    SQLITE("sqlite"), // SQLite
    MONGODB("mongodb"), // MongoDB
    REDIS("redis"); // Redis

    String name;

    FileType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
