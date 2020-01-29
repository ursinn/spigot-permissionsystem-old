package net.crazycraftland.spigot.permissionsystem.utils;

public enum MessagesEnum {

    PREFIX("prefix"), // §7[§bPerm§7]§f
    MADE_BY("made.by"), // %prefix% Permission System v%version% by %authors%
    NO_PERMISSION("no.permission"); //

    private String name;

    MessagesEnum(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
