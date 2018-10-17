package net.crazycraftland.spigot.permissionsystem.utils;

public enum FileType {

    MYSQL("MySQL"),
    FILE("File");

    String name;

    FileType(String name) {
        name = name;
    }

    public String getName() {
        return name;
    }
}
