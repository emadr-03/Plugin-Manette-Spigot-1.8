package main;

import org.bukkit.Bukkit;
//import org.bukkit.Bukkit;
//import org.bukkit.World;
import org.bukkit.plugin.java.JavaPlugin;

import commands.Blocca;
import commands.GiveManette;
import commands.GmCommand;
import events.Ammanettare;
import events.BlockBreakingEvent;

public class Main extends JavaPlugin {
	
	public static Main plugin;
	private GmCommand gm = new GmCommand();
	private GiveManette manetteCommand = new GiveManette();
	private Ammanettare ammanettaEvent = new Ammanettare();
	private BlockBreakingEvent breakingEvent = new BlockBreakingEvent(ammanettaEvent);
	private Blocca blockCommand = new Blocca(breakingEvent);
	
	public void onEnable()
	{
		plugin = this;
		
		/*for (World w : Bukkit.getServer().getWorlds())
		{
			System.out.println(w.getName());
		} stampa i nomi dei mondi all'avvio del server */
		
		getCommand("gm").setExecutor(gm);
		getCommand("blocca").setExecutor(blockCommand);
		getCommand("givemanette").setExecutor(manetteCommand);
		
		Bukkit.getPluginManager().registerEvents(breakingEvent, plugin);
		Bukkit.getPluginManager().registerEvents(ammanettaEvent, plugin);
	}

	public void onDisable()
	{
		System.out.println("sei un coglione");
	}
}
