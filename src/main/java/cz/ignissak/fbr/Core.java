package cz.ignissak.fbr;

import org.bukkit.plugin.java.JavaPlugin;

public final class Core extends JavaPlugin {

    private static Core instance;

    FileManager fm = new FileManager();

    @Override
    public void onEnable() {
        instance = this;
        // Plugin startup logic

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static Core getInstance() {
        return instance;
    }
}
