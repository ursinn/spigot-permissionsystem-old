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

package dev.ursinn.spigot.permissionsystem;

import com.google.common.reflect.ClassPath;
import dev.ursinn.spigot.permissionsystem.command.Perm;
import dev.ursinn.spigot.permissionsystem.utils.Files.FileManager;
import dev.ursinn.spigot.permissionsystem.utils.Files.FileType;
import dev.ursinn.spigot.permissionsystem.utils.UpdateChecker;
import org.bstats.bukkit.Metrics;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class PermissionSystem extends JavaPlugin {

    public static boolean devBuild = true;

    public static PermissionSystem instance;
    public UpdateChecker updateChecker = new UpdateChecker(0, this);
    private FileManager fileManager;

    public static PermissionSystem getInstance() {
        return instance;
    }

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
                    .getTopLevelClasses("dev.ursinn.spigot.permissionsystem.listeners")) {
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

    public FileManager getFileManager() {
        return fileManager;
    }
}
