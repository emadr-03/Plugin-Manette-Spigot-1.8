package items;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;



public class Manette {
	
	private static ItemStack manette;
	
	public Manette()
	{
		manette = new ItemStack(Material.FLINT_AND_STEEL);
		ItemMeta meta = manette.getItemMeta();
	
		meta.setDisplayName("§9Manette");
		List<String> description = new ArrayList<>();
		description.add("§7Tasto destro su un player per ammanettare");
		meta.setLore(description);
		meta.spigot().setUnbreakable(true);
		
		manette.setItemMeta(meta);
	}
	
	public static ItemStack getManette()
	{
		return manette;
	}

	public boolean hasManette(Player p)
	{
		if (p.getInventory().contains(manette)) return true;
		else return false;
	}
}
