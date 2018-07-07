package cz.ignissak.fbr;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Map;

public final class Core extends JavaPlugin {

    private static Core instance;

    public static Map<Material, HashMap<String, Integer>> data = new HashMap();

    @Override
    public void onEnable() {
        instance = this;

        saveDefaultConfig();
        registerCommands();
        registerEvents();

        data.put(Material.BEDROCK, new HashMap());
        ((HashMap)data.get(Material.BEDROCK)).put("durability", Integer.valueOf(getConfig().getInt("durability.bedrock")));
        data.put(Material.OBSIDIAN, new HashMap());
        ((HashMap)data.get(Material.OBSIDIAN)).put("durability", Integer.valueOf(getConfig().getInt("durability.obsidian")));

        Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + "FactionsBreak is online! (V" + getDescription().getVersion() + ")");

    }

    @Override
    public void onDisable() {
        instance = null;

        Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "FactionsBreak is offline! (V" + getDescription().getVersion() + ")");
    }

    public static Core getInstance() {
        return instance;
    }

    public void registerEvents() {

    }

    public void registerCommands() {

    }
}
