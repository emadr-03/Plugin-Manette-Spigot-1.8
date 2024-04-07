package events;

import java.util.ArrayList;
import java.util.UUID;

import org.bukkit.Material;
import org.bukkit.entity.Creeper;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockDispenseEvent;
import org.bukkit.event.block.BlockIgniteEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import items.Manette;

public class Ammanettare implements Listener {

	private ArrayList<UUID> playersAmmanettati;
	private int foodLevel;
	
	public Ammanettare()
	{
		playersAmmanettati = new ArrayList<>();
		foodLevel = 0;
	}
	
	public boolean hasInHand(Player p)
	{
		if (p.getInventory().getItemInHand().equals(Manette.getManette())) return true;
		else return false;
	}
	
	public boolean isAmmanettato(Player p)
	{
		if (playersAmmanettati.contains(p.getUniqueId())) return true;
		else return false;
	}
	
	public void Ammanetta(UUID targetID, Player target)
	{
		target.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 999999999, 6));
		target.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 999999999, -6));
		foodLevel = target.getFoodLevel();
		target.setFoodLevel(6);
		playersAmmanettati.add(targetID);
	}
	
	public void Libera(UUID targetID, Player target)
	{
		target.removePotionEffect(PotionEffectType.SLOW);
		target.removePotionEffect(PotionEffectType.JUMP);
		target.setFoodLevel(foodLevel);
		playersAmmanettati.remove(targetID);
	}
	
	@EventHandler
	public void onRightClick(PlayerInteractEntityEvent e)
	{
		Player p = e.getPlayer();
		Player target = (Player) e.getRightClicked();
		
		if (hasInHand(p) && !isAmmanettato(target) && !isAmmanettato(p) && p.hasPermission("ilpazzo.ammanetta"))
		{
			Ammanetta(target.getUniqueId(), target);
			p.sendMessage("§cHai ammanettato " + target.getName());
			target.sendMessage("§4Sei stato ammanettato!");
		}
		else if (hasInHand(p) && isAmmanettato(target) && !isAmmanettato(p) && p.hasPermission("ilpazzo.ammanetta"))
		{
			Libera(target.getUniqueId(), target);
			p.sendMessage("§cHai liberato " + target.getName());
			target.sendMessage("§6Sei stato liberato");
		}
		
		
		if (!p.hasPermission("ilpazzo.ammanetta"))
		{
			p.sendMessage("§4Non hai il permesso per ammanettare questo giocatore");
		}
	}
	

	@EventHandler
	public void placeBlock(BlockPlaceEvent b)
	{
		Player p = b.getPlayer();
		if (isAmmanettato(p)) b.setBuild(false);
	}
	
	@EventHandler
	public void usingManette1(PlayerInteractEvent e) //se il player prova ad accendere una tnt
	{
		Player p = e.getPlayer();
		
		if (hasInHand(p) && e.getClickedBlock() != null && e.getClickedBlock().getType().equals(Material.TNT))
		{
			e.setCancelled(true);
		}
	}
	
	@EventHandler
	public void usingManette2(PlayerInteractEntityEvent b) //se il player prova a far scoppiare un creeper
	{
		Player p = b.getPlayer();
		if (hasInHand(p) && b.getRightClicked() instanceof Creeper)
		{
			b.setCancelled(true);
		}
	}
	
	@EventHandler
	public void usingManette3(BlockDispenseEvent b) //se un dispenser prova ad usare le manette
	{
		if (b.getItem() != null && b.getItem().equals(Manette.getManette()))
		{
			b.setCancelled(true);
		}
	}
	
	@EventHandler
	public void usingManette4(BlockIgniteEvent b) // se un player prova ad usare le manette(acciarino)
	{
		Player p = b.getPlayer();
		if (p != null && hasInHand(p)) b.setCancelled(true);
	}
	
	@EventHandler
	public void stopFromEating(PlayerItemConsumeEvent p)
	{
		Player player = p.getPlayer();
		if (isAmmanettato(player)) p.setCancelled(true);
	}
}
