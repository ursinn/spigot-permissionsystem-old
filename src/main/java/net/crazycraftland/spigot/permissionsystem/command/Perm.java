package net.crazycraftland.spigot.permissionsystem.command;

import net.crazycraftland.spigot.permissionsystem.PermissionSystem;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class Perm implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("perm")) {
            if (args.length == 0) {
                // Show Permission Plugin v%version% by %authors%
                if (sender.hasPermission(PermissionSystem.getInstance().permCommandPermission)) {
                    // Show /perm help for help Message
                }
            }

            // Unknown Command
            return true;
        }
        return true;
    }
}
