package dk.muj.derius.mining;

import com.massivecraft.massivecore.MassivePlugin;
import com.massivecraft.massivecore.util.MUtil;

import dk.muj.derius.api.DeriusAPI;
import dk.muj.derius.util.Listener;

public class DeriusMining extends MassivePlugin
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
		if ( ! super.preEnable()) return;
		
		MiningSkill.get().register();
		DoubleDrop.get().register();
		SuperMining.get().register();
		
		MiningEngine.get().activate();
		
		Listener.registerTools(MUtil.PICKAXE_MATERIALS);
		DeriusAPI.getBlockMixin().addBlockTypesToListenFor(MiningSkill.getExpGain().keySet());
		
		
		super.postEnable();
	}
}
