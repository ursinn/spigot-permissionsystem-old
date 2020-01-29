package net.crazycraftland.spigot.permissionsystem.utils.Files;

import java.util.List;
import java.util.UUID;

public class YamlFile implements FileInterface {

    @Override
    public List<String> getGroups() {
        return null;
    }

    @Override
    public List<String> getGroupPermissions(String group) {
        return null;
    }

    @Override
    public List<String> getUserPermissions(UUID user) {
        return null;
    }

    @Override
    public List<String> getUserGroups(UUID user) {
        return null;
    }
}
