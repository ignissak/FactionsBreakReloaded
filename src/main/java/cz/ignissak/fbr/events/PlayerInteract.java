package cz.ignissak.fbr.events;

import cz.ignissak.fbr.Core;
import cz.ignissak.fbr.utils.TitleAPI;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.metadata.MetadataValue;

import java.util.HashMap;

public class PlayerInteract implements Listener {

    @EventHandler
    public void onActionPerformed(PlayerInteractEvent event)
    {
        String durabilita;
        if ((event.getAction() == Action.RIGHT_CLICK_BLOCK) &&
                (event.getPlayer().getItemInHand().getType() == Material.valueOf(Core.getInstance().getConfig().getString("info.item"))) &&
                (Core.getInstance().data.containsKey(event.getClickedBlock().getType())))
        {
            int durability = ((Integer)((HashMap)Core.getInstance().data.get(event.getClickedBlock().getType())).get("durability")).intValue();
            if (event.getClickedBlock().hasMetadata("hits"))
            {
                int hits = ((MetadataValue)event.getClickedBlock().getMetadata("hits").get(0)).asInt();
                durabilita = Integer.valueOf(durability - hits) + "/" + Integer.valueOf(durability);
                TitleAPI.sendPlayerTitle(event.getPlayer(), 10, 15, 10,
                        ChatColor.translateAlternateColorCodes('&', Core.getInstance().getConfig().getString("info.title")),
                        Core.getInstance().getConfig().getString("info.sub-title").replace("%durability%", durabilita));
                //event.getPlayer()
                //.sendMessage(String.format(
                //ChatColor.GRAY + "Durabilita tohto blocku je " + ChatColor.GREEN + "%s/%s",
                //new Object[] { Integer.valueOf(durability - hits), Integer.valueOf(durability) }));
                return;
            }
            else {
                durabilita = Integer.valueOf(durability) + "/" + Integer.valueOf(durability);
                TitleAPI.sendPlayerTitle(event.getPlayer(), 10, 15, 10,
                        ChatColor.translateAlternateColorCodes('&', Core.getInstance().getConfig().getString("info.title")),
                        Core.getInstance().getConfig().getString("info.sub-title").replace("%durability%", durabilita));
                //event.getPlayer().sendMessage(String.format(
                //ChatColor.GRAY + "Durabilita tohto blocku je " + ChatColor.GREEN + "%s/%s",
                //new Object[] { Integer.valueOf(durability), Integer.valueOf(durability) }));
                return;
            }
        }
    }
}
