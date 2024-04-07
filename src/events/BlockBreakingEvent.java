package events;

import java.util.ArrayList;
import java.util.UUID;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class BlockBreakingEvent implements Listener{
	
	private ArrayList<UUID> blockedPlayers;
	private Ammanettare a;
	
	public BlockBreakingEvent(Ammanettare ammanettaEvent)
	{
		blockedPlayers = new ArrayList<>();
		a = ammanettaEvent;
	}
	
	public boolean isBlocked(Player p)
	{
		if (blockedPlayers.contains(p.getUniqueId())) return true;
		else return false;
	}
	
	public void addBlockedPlayer(UUID targetID)
	{
		blockedPlayers.add(targetID);
	}
	
	public void removeBlockedPlayer(UUID targetID)
	{
		blockedPlayers.remove(targetID);
	}

	
	@EventHandler
	public void BlockBreaking(BlockBreakEvent b)
	{
		Player p = b.getPlayer();
	
		if (isBlocked(p))
		{
			b.setCancelled(true);
			p.sendMessage("§4Sei bloccato");
		}
		
		if (a.isAmmanettato(p)) b.setCancelled(true);
	}
}
