package dk.muj.derius.mining;

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
		super.preEnable();
		
		MConfColl.get().init();
		
		MiningSkill.get().register();
		DoubleDrop.get().register();;
		SuperMining.get().register();
		
		MConfColl.get().get("Mining", true);
		
		super.postEnable();
	}
}
