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
                return true;
            }

            if (sender.hasPermission(PermissionSystem.getInstance().permCommandPermission)) {
                if (args.length == 1) {
                    if (args[0].equalsIgnoreCase("help")) {
                        sendHelpMessage(sender);
                        return true;
                    }

                    sendUnknownCommandMessage(sender);
                    return true;
                }

                if (args.length == 2) {
                    if (args[0].equalsIgnoreCase("group")) {
                        if (args[1].equalsIgnoreCase("list")) {
                            // Show Group Lists
                            return true;
                        }

                        sendUnknownCommandMessage(sender);
                        return true;
                    }

                    sendUnknownCommandMessage(sender);
                    return true;
                }

                if (args.length == 3) {
                    if (args[0].equalsIgnoreCase("group")) {
                        if (args[1].equalsIgnoreCase("list")) {
                            String group = args[2];
                            // Show Group Members
                            return true;
                        }

                        if (args[1].equalsIgnoreCase("add")) {
                            String group = args[2];
                            // Add Group
                            return true;
                        }

                        if (args[1].equalsIgnoreCase("del")) {
                            String group = args[2];
                            // Del Group
                            return true;
                        }

                        sendUnknownCommandMessage(sender);
                        return true;
                    }

                    if (args[0].equalsIgnoreCase("user")) {
                        if (args[1].equalsIgnoreCase("list")) {
                            String user = args[2];
                            // Show Users Groups
                            return true;
                        }

                        sendUnknownCommandMessage(sender);
                        return true;
                    }

                    sendUnknownCommandMessage(sender);
                    return true;
                }

                if (args.length == 4) {
                    if (args[0].equalsIgnoreCase("user")) {
                        if (args[1].equalsIgnoreCase("del")) {
                            String user = args[2];
                            String group = args[3];
                            // Del User from Group
                            return true;
                        }

                        if (args[1].equalsIgnoreCase("set")) {
                            String user = args[2];
                            String group = args[3];
                            // Set User in Group
                            return true;
                        }

                        sendUnknownCommandMessage(sender);
                        return true;
                    }

                    sendUnknownCommandMessage(sender);
                    return true;
                }

                if (args.length == 5) {
                    if (args[0].equalsIgnoreCase("user")) {
                        if (args[1].equalsIgnoreCase("add")) {
                            String user = args[2];
                            String group = args[3];
                            String time = args[4];
                            // Add User in Group for Time
                            return true;
                        }

                        sendUnknownCommandMessage(sender);
                        return true;
                    }

                    sendUnknownCommandMessage(sender);
                    return true;
                }

                sendUnknownCommandMessage(sender);
                return true;
            }

            //No Permission
            return true;
        }
        return true;
    }

    public void sendHelpMessage(CommandSender sender) {
        // Show Help Message
    }

    public void sendUnknownCommandMessage(CommandSender sender) {
        // Show Unknown Command Message
    }
}

// /Perm
// /Perm Help
// /Perm Group list
// /Perm Group list <Group>
// /Perm Group add <Group>
// /Perm Group del <Group>
// /Perm User list <User>
// /Perm User add <User> <Group> <Time>
// /Perm User del <User> <Group>
// /Perm User set <User> <Group>