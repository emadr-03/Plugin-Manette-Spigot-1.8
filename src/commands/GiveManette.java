package commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import items.Manette;

public class GiveManette implements CommandExecutor {
	
	private Manette item;
	
	public GiveManette()
	{
		item = new Manette();
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if (sender instanceof Player)
		{
			Player p = (Player) sender;
			
			if (!p.hasPermission("ilpazzo.manette"))
			{
				sender.sendMessage("§cNon hai il permesso per eseguire questo comando");
				return true;
			}
		}
		
		if (args.length > 1)
		{
			sender.sendMessage("§cSintassi errata. Usare: /givem [NomePlayer]");
			return true;
		}
		
		if (args.length == 0 && sender instanceof ConsoleCommandSender)
		{
			System.out.println("No.");
			return true;
		}
		
		if (args.length == 0 && sender instanceof Player)
		{
			Player p = (Player) sender;
			
			if (item.hasManette(p))
			{
				sender.sendMessage("§cPossiedi già le manette nel tuo inventario");
			}
			else
			{
				p.getInventory().addItem(Manette.getManette());
			}
		}
		
		if (args.length == 1)
		{
			Player target = Bukkit.getServer().getPlayerExact(args[0]);
			if (target != null)
			{
				if (sender instanceof ConsoleCommandSender)
				{
					if (item.hasManette(target))
					{
						sender.sendMessage("§cIl player " + target.getName() + " possiede già le manette nel suo inventario");
					}
					else
					{
						target.getInventory().addItem(Manette.getManette());
					}
				}
				else
				{
					Player p = (Player) sender;
					
					if (p == target && item.hasManette(p))
					{
						sender.sendMessage("§cPossiedi già le manette nel tuo inventario");
					}
					else if (p == target && !item.hasManette(p))
					{
						p.getInventory().addItem(Manette.getManette());
					}
					else if (p != target && item.hasManette(target))
					{
						sender.sendMessage("§cIl player " + target.getName() + " possiede già le manette nel suo inventario");
					}
					else
					{
						target.getInventory().addItem(Manette.getManette());
					}
				}
			}
			else
			{
				sender.sendMessage("§cGiocatore non trovato");
				return true;
			}
		}
		
		return false;
	}

}
