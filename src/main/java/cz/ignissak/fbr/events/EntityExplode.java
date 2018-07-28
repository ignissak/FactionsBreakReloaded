package cz.ignissak.fbr.events;

import cz.ignissak.fbr.Core;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.metadata.MetadataValue;

import java.util.HashMap;

public class EntityExplode implements Listener {

    @EventHandler
    public void onEntityExplode(org.bukkit.event.entity.EntityExplodeEvent e) {
        if (e.getEntityType() == EntityType.CREEPER) {
            return;
        }
        int r = Core.getInstance().getConfig().getInt("tnt.radius");
        if (e.getLocation().getBlock().getType().equals(Material.WATER) || e.getLocation().getBlock().getType().equals(Material.STATIONARY_WATER)) {
            if (Core.getInstance().getConfig().getBoolean("tnt.ignore_water")) {
                e.getLocation().getBlock().setType(Material.AIR);
                e.getLocation().getWorld().createExplosion(e.getLocation().getX(), e.getLocation().getY(), e.getLocation().getZ(), 4.0F, false, true);
                e.getLocation().getBlock().setType(Material.STATIONARY_WATER);
            }

        }
        if (e.getLocation().getBlock().getType().equals(Material.LAVA) || e.getLocation().getBlock().getType().equals(Material.STATIONARY_LAVA)) {
            if (Core.getInstance().getConfig().getBoolean("tnt.ignore_lava")) {
                e.getLocation().getBlock().setType(Material.AIR);
                e.getLocation().getWorld().createExplosion(e.getLocation().getX(), e.getLocation().getY(), e.getLocation().getZ(), 4.0F, false, true);
                e.getLocation().getBlock().setType(Material.STATIONARY_LAVA);
            }

        }
        Location ppos = e.getLocation();
        for (int x = r * -1; x <= r; x++) {
            for (int y = r * -1; y <= r; y++) {
                for (int z = r * -1; z <= r; z++)
                {
                    Block b = ppos.getWorld().getBlockAt(ppos.getBlockX() + x, ppos.getBlockY() + y,
                            ppos.getBlockZ() + z);
                    if (Core.getInstance().data.containsKey(b.getType()))
                    {
                        int resistance = ((Integer)((HashMap)Core.getInstance().data.get(b.getType())).get("durability")).intValue();
                        if (resistance == 0) {
                            return;
                        }
                        if (b.hasMetadata("hits"))
                        {
                            b.setMetadata("hits", new FixedMetadataValue(Core.getInstance(),
                                    Integer.valueOf(((MetadataValue)b.getMetadata("hits").get(0)).asInt() + 1)));
                            if (((MetadataValue)b.getMetadata("hits").get(0)).asInt() >= resistance)
                            {
                                b.setType(Material.AIR);
                                e.getLocation().getWorld().playEffect(e.getLocation(), Effect.valueOf(Core.getInstance().getConfig().getString("on_break_effect")), 1);
                            }
                        }
                        else
                        {
                            b.setMetadata("hits", new FixedMetadataValue(Core.getInstance(), Integer.valueOf(1)));
                        }
                    }
                }
            }
        }
    }
}
