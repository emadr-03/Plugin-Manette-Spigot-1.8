package commands;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

public class GmCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if (cmd.getName().equalsIgnoreCase("gm"))
		{
			if (args.length == 1 && sender instanceof Player)
			{
				Player p = (Player) sender;
				
				if (args[0].equals("0"))
				{
					p.setGameMode(GameMode.SURVIVAL);
					sender.sendMessage("Impostata modalità sopravvivenza");
				}
				else if (args[0].equals("1"))
				{
					p.setGameMode(GameMode.CREATIVE);
					sender.sendMessage("Impostata modalità creativa");
				}
				else if (args[0].equals("2"))
				{
					p.setGameMode(GameMode.ADVENTURE);
					sender.sendMessage("Impostata modalità hardcore");
				}
				else
				{
					sender.sendMessage("Sintassi errata");
					return true;
				}
			}
			else if (args.length == 1 && sender instanceof ConsoleCommandSender)
			{
				System.out.println("Non ha alcun senso quello che stai facendo");
				return true;
			}
			
			if (args.length == 2)
			{
                Player target = Bukkit.getServer().getPlayerExact(args[1]);
				
				if (target != null)
				{
					if (args[0].equals("0"))
					{
						target.setGameMode(GameMode.SURVIVAL);
						sender.sendMessage("Impostata modalità sopravvivenza al giocatore " + target.getName());
					}
					else if (args[0].equals("1"))
					{
						target.setGameMode(GameMode.CREATIVE);
						sender.sendMessage("Impostata modalità creativa al giocatore " + target.getName());
					}
					else if (args[0].equals("2"))
					{
						target.setGameMode(GameMode.ADVENTURE);
						sender.sendMessage("Impostata modalità hardcore al giocatore " + target.getName());
					}
					else
					{
						sender.sendMessage("Sintassi errata");
						return true;
					}
				}
				else
				{
					sender.sendMessage("Il player è offline");
					return true;
				}
			}
			else if (args.length == 0)
			{
				sender.sendMessage("Sintassi: /gm [0:Survival, 1:Creativa, 2:Hardcore] [NomePlayer]");
			}
			else if (args.length > 2)
			{
				sender.sendMessage("Sintassi errata");
				return true;
			}
		}
		return false;
	}
}
