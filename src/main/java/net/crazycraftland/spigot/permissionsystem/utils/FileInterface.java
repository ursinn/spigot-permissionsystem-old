package net.crazycraftland.spigot.permissionsystem.utils;

import java.util.List;
import java.util.UUID;

public interface FileInterface {

    List<String> getGroups();

    List<String> getGroupPermissions(String group);

    List<String> getUserPermissions(UUID user);

    List<String> getUserGroups(UUID user);
}
