package dk.muj.derius.mining;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;

import com.massivecraft.massivecore.MassivePlugin;

import dk.muj.derius.mining.entity.MConfColl;

public class DeriusMining extends MassivePlugin
{

	// -------------------------------------------- //
	// INSTANCE & CONSTRUCT
	// -------------------------------------------- //
    
    private static DeriusMining i;
	public static DeriusMining get() { return i; }
	public DeriusMining() { i = this; }
	

	@Override
	public void onEnable()
	{
		if ( ! super.preEnable()) return;
		
		CommandSender sender = Bukkit.getConsoleSender();
		sender.sendMessage("STARTUP 1");
		
		MConfColl.get().init();
		
		sender.sendMessage("STARTUP 2");
		MiningSkill.get().register();
		sender.sendMessage("STARTUP 3");
		DoubleDrop.get().register();
		sender.sendMessage("STARTUP 4");
		SuperMining.get().register();
		sender.sendMessage("STARTUP 5");
		new MiningListener();
		
		sender.sendMessage("STARTUP 6");
		
		super.postEnable();
		sender.sendMessage("STARTUP 7");
	}
}
