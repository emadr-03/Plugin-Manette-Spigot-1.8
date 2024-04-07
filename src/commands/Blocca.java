package commands;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import events.BlockBreakingEvent;


public class Blocca implements CommandExecutor{

	private BlockBreakingEvent blockEvent;
	
	public Blocca(BlockBreakingEvent breakingEvent)
	{
		blockEvent = breakingEvent;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if (sender instanceof Player)
		{
			Player p = (Player) sender;
			
			if (!p.hasPermission("ilpazzo.blocca"))
			{ 
				sender.sendMessage("§4Non hai il permesso per eseguire questo comando");
				return true;
			}
		}
		
		
		if (args.length == 0 || args.length > 1)
		{
			sender.sendMessage("§cSintassi: /blocca [Nome Giocatore]");
		}
		else
		{
			Player target = Bukkit.getServer().getPlayerExact(args[0]);
			
			if (target == null)
			{
				sender.sendMessage("§cGiocatore non trovato");
			}
			else
			{
				UUID targetID = target.getUniqueId();
				
				if (!blockEvent.isBlocked(target))
				{
					blockEvent.addBlockedPlayer(targetID);
					sender.sendMessage("§cIl player " + target.getName() + " è stato bloccato");
				}
				else
				{
					blockEvent.removeBlockedPlayer(targetID);
					sender.sendMessage("§cIl player " + target.getName() + " è stato sbloccato");
				}
			}
		}
		
		return false;
	}
}
