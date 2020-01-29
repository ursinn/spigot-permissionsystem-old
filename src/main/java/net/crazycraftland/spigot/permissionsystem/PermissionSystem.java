package net.crazycraftland.spigot.permissionsystem;

import com.google.common.reflect.ClassPath;
import net.crazycraftland.spigot.permissionsystem.command.Perm;
import net.crazycraftland.spigot.permissionsystem.utils.Files.FileManager;
import net.crazycraftland.spigot.permissionsystem.utils.Files.FileType;
import net.crazycraftland.spigot.permissionsystem.utils.UpdateChecker;
import org.bstats.bukkit.Metrics;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class PermissionSystem extends JavaPlugin {

    public static boolean devBuild = true;

    public static PermissionSystem instance;
    public UpdateChecker updateChecker = new UpdateChecker(0, this);
    private FileManager fileManager;

    @Override
    public void onEnable() {
        saveDefaultConfig();
        fileManager = new FileManager(FileType.valueOf(getConfig().getString("file-type")), this);
        regCommands();
        registerListener();
        if (!devBuild) {
            if (getConfig().getBoolean("metrics"))
                new Metrics(this, 4860);
            if (getConfig().getBoolean("update-check"))
                updateChecker.checkUpdates.start();
        }
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    private void regCommands() {
        Perm perm = new Perm();
        getCommand("perm").setExecutor(perm);
    }

    private void registerListener() {
        PluginManager pluginManager = getServer().getPluginManager();
        try {
            for (ClassPath.ClassInfo classInfo : ClassPath.from(getClassLoader())
                    .getTopLevelClasses("net.crazycraftland.spigot.permissionsystem.listeners")) {
                @SuppressWarnings("rawtypes")
                Class clazz = Class.forName(classInfo.getName());
                if (Listener.class.isAssignableFrom(clazz)) {
                    pluginManager.registerEvents((Listener) clazz.newInstance(), this);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static PermissionSystem getInstance() {
        return instance;
    }

    public FileManager getFileManager() {
        return fileManager;
    }
}
