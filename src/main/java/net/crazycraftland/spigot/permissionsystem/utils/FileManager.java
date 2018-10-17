package net.crazycraftland.spigot.permissionsystem.utils;

import java.util.List;
import java.util.UUID;

public class FileManager implements  FileInterface {

    private FileType fileType;

    public FileManager(FileType fileType) {
        this.fileType = fileType;
    }

    @Override
    public List<String> getUserGroups(UUID user) {
        if (fileType == FileType.MYSQL)
            return new MySQL().getUserGroups(user);

        if (fileType == FileType.FILE)
            return null;

        return null;
    }

    @Override
    public List<String> getUserPermissions(UUID user) {
        if (fileType == FileType.MYSQL)
            return new MySQL().getUserPermissions(user);

        if (fileType == FileType.FILE)
            return null;

        return null;
    }

    @Override
    public List<String> getGroupPermissions(String group) {
        if (fileType == FileType.MYSQL)
            return new MySQL().getGroupPermissions(group);

        if (fileType == FileType.FILE)
            return null;

        return null;
    }

    @Override
    public List<String> getGroups() {
        if (fileType == FileType.MYSQL)
            return new MySQL().getGroups();

        if (fileType == FileType.FILE)
            return null;

        return null;
    }
}
