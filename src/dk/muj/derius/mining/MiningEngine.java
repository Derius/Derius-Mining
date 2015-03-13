package dk.muj.derius.mining;

import java.util.Map;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.plugin.Plugin;

import com.massivecraft.massivecore.EngineAbstract;
import com.massivecraft.massivecore.util.MUtil;

import dk.muj.derius.api.DeriusAPI;
import dk.muj.derius.api.player.DPlayer;

public class MiningEngine extends EngineAbstract
{
	// -------------------------------------------- //
	// INSTANCE & CONSTRUCT
	// -------------------------------------------- //
   
	private static MiningEngine i = new MiningEngine();
	public static MiningEngine get() { return i; }
	private MiningEngine() { }

	// -------------------------------------------- //
	// OVERRIDE
	// -------------------------------------------- //
	
	@Override
	public Plugin getPlugin()
	{
		return DeriusMining.get();
	}

	// -------------------------------------------- //
	// EVENT
	// -------------------------------------------- //
	
	@EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
	public void blockBreakThingy(BlockBreakEvent event)
	{
		// Get objects
		Player player = event.getPlayer();
		DPlayer dplayer = DeriusAPI.getDPlayer(player);
		Block block = event.getBlock();
		Material type = block.getType();
		
		// Checks
		if ( ! MUtil.isPickaxe(event)) return;
		if (DeriusAPI.isBlockPlacedByPlayer(block)) return;

		// Exp gain
		Map<Material, Integer> expGain = MiningSkill.getExpGain();
		if (expGain.containsKey(type))
		{
			dplayer.addExp(MiningSkill.get(), expGain.get(type));
		}
		
		return;
	}

}
