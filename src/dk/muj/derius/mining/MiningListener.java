package dk.muj.derius.mining;

import org.apache.commons.lang.Validate;
import org.bukkit.block.BlockState;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.massivecraft.massivecore.util.MUtil;

import dk.muj.derius.DeriusCore;
import dk.muj.derius.api.DPlayer;
import dk.muj.derius.util.AbilityUtil;
import dk.muj.derius.util.Listener;

public class MiningListener implements Listener
{
	// -------------------------------------------- //
	// INSTANCE & CONSTRUCT
	// -------------------------------------------- //

	private static MiningListener i = new MiningListener();
	public static MiningListener get() { return i; }
	
	public MiningListener()
	{
		Listener.registerBlockBreakKeys(this, MiningSkill.getExpGain().keySet());
		Listener.registerTools(MUtil.PICKAXE_MATERIALS);
		DeriusCore.getBlockMixin().addBlockTypesToListenFor(MiningSkill.getExpGain().keySet());
	}
	
	// -------------------------------------------- //
	// OVERRIDE
	// -------------------------------------------- //
	
	@Override
	public void onBlockBreak(DPlayer dplayer, BlockState block)
	{
		Validate.notNull(dplayer, "dplayer musn't be null");
		Validate.notNull(block, "block musn't be null");
		if ( ! dplayer.isPlayer()) return;
		Player player = dplayer.getPlayer();
		ItemStack inHand = player.getItemInHand();
		if ( ! MUtil.isPickaxe(inHand)) return;
		
		if (DeriusCore.getBlockMixin().isBlockPlacedByPlayer(block.getBlock())) return;
		
		if (dplayer.getPreparedTool().isPresent() && MUtil.PICKAXE_MATERIALS.contains(dplayer.getPreparedTool().get()))
		{
			AbilityUtil.activateAbility(dplayer, SuperMining.get(), null, true);
		}
		
		Integer exp = MiningSkill.getExpGain().get(block.getType());
		if (exp != null)
		{
			dplayer.addExp(MiningSkill.get(), exp);
		}
		
		AbilityUtil.activateAbility(dplayer, DoubleDrop.get(), block, true);

	}

}
