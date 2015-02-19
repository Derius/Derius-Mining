package dk.muj.derius.mining;

import com.massivecraft.massivecore.MassivePlugin;

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
		
		MiningListener.get();
		
		super.postEnable();
	}
}
