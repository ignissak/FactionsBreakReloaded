package cz.ignissak.fbr;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class FileManager {

    // Files & File Configs Here
    public FileConfiguration pluginsConfig;
    public File pluginsFiles;
    // --------------------------

    public void setup() {
        if (!Core.getInstance().getDataFolder().exists()) {
            Core.getInstance().getDataFolder().mkdir();
        }

        pluginsFiles = new File(Core.getInstance().getDataFolder(), "config.yml");

        if (!pluginsFiles.exists()) {
            try {
                pluginsFiles.createNewFile();
                Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "The config.yml file has been created");
            } catch (IOException e) {
                Bukkit.getServer().getConsoleSender()
                        .sendMessage(ChatColor.RED + "Could not create the config.yml file");
            }
        }

        pluginsConfig = YamlConfiguration.loadConfiguration(pluginsFiles);
    }

    public FileConfiguration getPluginsConfig() {
        return pluginsConfig;
    }

    public void savePluginsConfig() {
        try {
            pluginsConfig.save(pluginsFiles);

        } catch (IOException e) {
            Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.RED + "Could not save the config.yml file");
        }
    }

    public void reloadPluginsConfig() {
        pluginsConfig = YamlConfiguration.loadConfiguration(pluginsFiles);

    }
}
