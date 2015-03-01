package dk.muj.derius.mining;

import java.util.Map;
import java.util.Optional;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerItemDamageEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

import com.massivecraft.massivecore.EngineAbstract;
import com.massivecraft.massivecore.util.MUtil;

import dk.muj.derius.api.DPlayer;
import dk.muj.derius.api.DeriusAPI;
import dk.muj.derius.api.VerboseLevel;
import dk.muj.derius.util.AbilityUtil;

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
		// Just in case
		if (event == null) return;
		
		// Get objects
		Player player = event.getPlayer();
		DPlayer dplayer = DeriusAPI.getDPlayer(player);
		ItemStack inHand = player.getItemInHand();
		Block block = event.getBlock();
		Material type = block.getType();
		
		// Checks
		if ( ! MUtil.isPickaxe(inHand)) return;
		if (DeriusAPI.isBlockPlacedByPlayer(block)) return;
		
		// Super Mining
		Optional<Material> optPrepared = dplayer.getPreparedTool();
		// If this block type can activate super mining. They have prepared a toll & it is a pickaxe.
		if (MiningSkill.getSuperMiningBlocks().contains(type) && optPrepared.isPresent() && MUtil.PICKAXE_MATERIALS.contains(optPrepared.get()))
		{
			AbilityUtil.activateAbility(dplayer, SuperMining.get(), null, VerboseLevel.LOW);
		}
		
		// Double Drop
		AbilityUtil.activateAbility(dplayer, DoubleDrop.get(), block, VerboseLevel.HIGH);
		
		// Exp gain
		Map<Material, Integer> expGain = MiningSkill.getExpGain();
		if (expGain.containsKey(type))
		{
			dplayer.addExp(MiningSkill.get(), expGain.get(type));
		}
		
		return;
	}
	
	public void changeDurability(PlayerItemDamageEvent event)
	{
		ItemStack item = event.getItem();
		if ( ! MUtil.isPickaxe(item)) return;
		DPlayer dplayer = DeriusAPI.getDPlayer(event.getPlayer());
		AbilityUtil.activateAbility(dplayer, CarefulMining.get(), event, VerboseLevel.ALWAYS);
		
	}
	
}
