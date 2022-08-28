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

public class main_using_easy_EulerAngle extends JavaPlugin implements CommandExecutor, Listener {

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(this, this);
    }

    @EventHandler
    public void c(PlayerInteractEvent e) {
        Location loc = e.getPlayer().getLocation();
        for (int yaw = 0; yaw < 360; yaw += 10) {
            loc.setYaw(yaw);
            for (int pitch = -90; pitch < 91; pitch += 10) {
                loc.setPitch(pitch);
                ArmorStand a = (ArmorStand) loc.getWorld().spawnEntity(
                        loc.clone().add(loc.getDirection().multiply(5)), EntityType.ARMOR_STAND);
                a.setGravity(false);
                a.setHeadPose(new EulerAngle(pitch / 57.32, 0,0));
                a.setHelmet(new ItemStack(Material.WOOD));
                Bukkit.getScheduler().runTaskLater(this, a::remove, 600);
                ((CraftArmorStand) a).getHandle().setInvisible(true);
            }
        }
    }
}
