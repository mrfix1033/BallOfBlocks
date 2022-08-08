package mrfix1033.understanding_unknown;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.CommandExecutor;
import org.bukkit.craftbukkit.v1_12_R1.entity.CraftArmorStand;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.EulerAngle;
import org.bukkit.util.Vector;

public class main extends JavaPlugin implements CommandExecutor, Listener {

    boolean p = false;

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(this, this);
    }

    @EventHandler
    public void c(PlayerInteractEvent e) {
        if (p) return;
        p = true;
        Location loc = e.getPlayer().getLocation();
        for (float i = 0; i < 360; i += 10) {
            loc.setYaw(i);
            for (float o = -90; o < 90; o += 10) {
                loc.setPitch(o);
                Vector vec = loc.getDirection().multiply(5);
                ArmorStand a = (ArmorStand) loc.getWorld().spawnEntity(loc.clone().add(vec), EntityType.ARMOR_STAND);
                a.setHeadPose(new EulerAngle(o / 57.32, 0,0));
                //a.setHeadPose(new EulerAngle(1.57, 1 / (i * 1.744444), (o + 90) * 3.488888));
                a.setGravity(false);
                a.setHelmet(new ItemStack(Material.WOOD));
                Bukkit.getScheduler().runTaskLater(this, () -> {
                    a.remove();
                    p = false;
                }, 600);
                ((CraftArmorStand) a).getHandle().setInvisible(true);
            }
        }
    }
}
