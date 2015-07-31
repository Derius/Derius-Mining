package dk.muj.derius.mining;

import com.massivecraft.massivecore.MassivePlugin;
import com.massivecraft.massivecore.util.MUtil;

import dk.muj.derius.api.DeriusAPI;

public final class DeriusMining extends MassivePlugin
{
	// -------------------------------------------- //
	// INSTANCE & CONSTRUCT
	// -------------------------------------------- //
	
	private static DeriusMining i;
	public static DeriusMining get() { return i; }
	public DeriusMining() { i = this; }
	
	// -------------------------------------------- //
	// OVERRIDE: PLUGIN
	// -------------------------------------------- //
	
	@Override
	public void onEnable()
	{
		if ( ! this.preEnable()) return;
		
		MiningSkill.get().register();
		DoubleDrop.get().register();
		SuperMining.get().register();
		CarefulMining.get().register();
		
		MiningExpGain.get().register();
		SuperMiningItemManager.get().register();
		
		DeriusAPI.registerPreparableTools(MUtil.PICKAXE_MATERIALS);
		DeriusAPI.addBlockTypesToListenFor(MiningSkill.getExpGain().keySet());
		DeriusAPI.addBlockTypesToListenFor(MiningSkill.getDoubleDropBlocks());
		
		this.postEnable();
	}
	
}
