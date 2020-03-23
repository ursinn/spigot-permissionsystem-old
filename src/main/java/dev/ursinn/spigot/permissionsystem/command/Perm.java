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

package dev.ursinn.spigot.permissionsystem.command;

import dev.ursinn.spigot.permissionsystem.utils.MessagesEnum;
import dev.ursinn.spigot.permissionsystem.utils.MessagesManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class Perm implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        String Main_Permission = "perm.cmd";

        if (cmd.getName().equalsIgnoreCase("perm")) {
            if (args.length == 0) {
                sender.sendMessage(MessagesManager.getMessage(MessagesEnum.MADE_BY));
                if (sender.hasPermission(Main_Permission)) {
                    // Show /perm help for help Message
                }
                return true;
            }

            if (sender.hasPermission(Main_Permission)) {
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