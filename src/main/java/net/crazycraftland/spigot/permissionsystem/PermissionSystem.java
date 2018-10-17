package net.crazycraftland.spigot.permissionsystem;

import net.crazycraftland.spigot.permissionsystem.command.Perm;
import net.crazycraftland.spigot.permissionsystem.utils.Config;
import net.crazycraftland.spigot.permissionsystem.utils.FileManager;
import net.crazycraftland.spigot.permissionsystem.utils.FileType;
import org.bukkit.plugin.java.JavaPlugin;

public final class PermissionSystem extends JavaPlugin {

    private static PermissionSystem instance;

    private Config config = new Config();

    private FileManager fileManager = null;
    private FileType fileType = null;

    public String permCommandPermission = "";

    @Override
    public void onEnable() {
        // Plugin startup logic
        instance = this;
        // Config Load fileType
        config.createConfig();
        config.loadConfig();
        fileManager = new FileManager(fileType);
        regCommands();
        regEvents();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }


    private void regCommands() {
        Perm perm = new Perm();
        getCommand("perm").setExecutor(perm);
    }

    private void regEvents() {
        // Events
    }

    public static PermissionSystem getInstance() {
        return instance;
    }

    public FileManager getFileManager() {
        return fileManager;
    }
}
