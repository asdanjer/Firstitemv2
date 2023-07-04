package me.asdanjer.firstitemv2;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

public final class Firstitemv2 extends JavaPlugin implements Listener {
    List<Material> materialsout = new ArrayList<>();

    List<Material> materials = new ArrayList<>();
    public static List<String> gottenmatierals = new ArrayList<>();
    public static List<String> playergotten = new ArrayList<>();
    public static final String MATIRALFILE = "plugins/Firstitemv2/materials.txt";
    public static final String OUTPUTFILE = "plugins/Firstitemv2/materials_out.txt";
    @Override
    public void onEnable() {

        getServer().getPluginManager().registerEvents(this, this);
        materials = MaterialImporter.importMaterials(MATIRALFILE);
        materialsout = MaterialImporter.importMaterials(OUTPUTFILE);
        materials.removeAll(materialsout);
        new Save().runTaskTimerAsynchronously(this, 6000, 6000);

    }

    @Override
    public void onDisable() {
       Save save2 =new Save();
       save2.run();
    }
    @EventHandler
    public void pickup(PlayerPickupItemEvent item) {
        itemcheck(item.getItem().getItemStack().getType(), item.getPlayer());
    }
    @EventHandler
    public void craft(CraftItemEvent item) {
        ItemStack result = item.getRecipe().getResult();
        if(result!=null && item.getAction() != InventoryAction.NOTHING){
        itemcheck(item.getRecipe().getResult().getType(), (Player) item.getWhoClicked());}
    }
    @EventHandler
    public void transfer(InventoryClickEvent item){
        if(item.getCurrentItem()!=null) {
            Material currentmaterial = item.getCurrentItem().getType();
            if (item.getInventory().getType() != InventoryType.PLAYER && item.getAction() != InventoryAction.NOTHING && !currentmaterial.isAir()) {
                itemcheck(currentmaterial, (Player) item.getWhoClicked());
            }
        }

    }
    public void itemcheck(Material item, Player p){
        if(materials.contains(item)){
            materials.remove(item);
            gottenmatierals.add(item.name());
            playergotten.add(p.getDisplayName());
            Bukkit.broadcastMessage(p.getDisplayName()+ " has just got the first " + item.name().replace("_", " ").toLowerCase() + ". Better rename it and cherish it forever!");
        }

    }
}
