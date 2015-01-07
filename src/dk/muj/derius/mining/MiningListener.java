package dk.muj.derius.mining;

import java.util.Optional;
import java.util.stream.Collectors;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.massivecraft.massivecore.util.MUtil;

import dk.muj.derius.entity.MPlayer;
import dk.muj.derius.mining.entity.MConf;
import dk.muj.derius.util.Listener;

public class MiningListener implements Listener
{
	// -------------------------------------------- //
	// CONSTRUCT
	// -------------------------------------------- //

	private static MiningListener i = new MiningListener();
	public static MiningListener get() { return i; }
	
	public MiningListener()
	{
		i = this;
		registerBlockBreakKeys(MConf.get().expGain.keySet().parallelStream().map(Material::getMaterial).collect(Collectors.toList()));
		Listener.registerTools(MUtil.PICKAXE_MATERIALS);
	}
	
	// -------------------------------------------- //
	// OVERRIDE
	// -------------------------------------------- //
	
	@Override
	public void onBlockBreak(MPlayer mplayer, Block block)
	{
		if ( ! mplayer.isPlayer()) return;
		Player player = mplayer.getPlayer();
		ItemStack inHand = player.getItemInHand();
		if (!MUtil.isPickaxe(inHand))
			return;
		
		if ( ! mplayer.getPreparedTool().equals(Optional.empty()) && MUtil.PICKAXE_MATERIALS.contains(mplayer.getPreparedTool().get()))
		{
			mplayer.activateAbility(SuperMining.get(), null);
		}
		
		Integer exp = MConf.get().expGain.get(block.getTypeId());
		if ( exp != null)
		{
			mplayer.addExp(MiningSkill.get(), exp);
		}
		
		mplayer.activateAbility(DoubleDrop.get(), block);
		
		
		
	}

}
