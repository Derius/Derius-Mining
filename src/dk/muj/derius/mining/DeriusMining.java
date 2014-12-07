package dk.muj.derius.mining;

import com.massivecraft.massivecore.MassivePlugin;

import dk.muj.derius.mining.entity.MConfColl;
import dk.muj.derius.skill.Skill;
import dk.muj.derius.skill.Skills;

public class DeriusMining extends MassivePlugin
{

	// -------------------------------------------- //
	// INSTANCE & CONSTRUCT
	// -------------------------------------------- //
    
    private static DeriusMining i;
	public static DeriusMining get() { return i; }
	public DeriusMining() { i = this; }
	
	// -------------------------------------------- //
	// FIELDS
	// -------------------------------------------- //
	
	private static Skill miningSkill = new MiningSkill();
	public static Skill getMiningSkill () {	return DeriusMining.miningSkill; }
	
	// -------------------------------------------- //
	// LISTENERS
	// -------------------------------------------- //
	
	MiningListener listener;
	
	@Override
	public void onEnable()
	{
		super.preEnable();
		
		Skills.AddSkill(miningSkill);
		
		MConfColl.get().init();
		
		listener = new MiningListener(this);
		
		MConfColl.get().get("Mining", true);
		
		super.postEnable();
	}
}
