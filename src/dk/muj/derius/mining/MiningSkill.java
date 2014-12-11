package dk.muj.derius.mining;

import dk.muj.derius.entity.MPlayer;
import dk.muj.derius.mining.entity.MConf;
import dk.muj.derius.skill.Skill;

public class MiningSkill extends Skill
{
	
	private static MiningSkill i = new MiningSkill();
	public static MiningSkill get() { return i; }
	
	
	public MiningSkill()
	{
		super.addEarnExpDesc("Mine ores");
		
		super.setName("Mining");
		
		super.setDescription("Makes you better at mining");
	}

	@Override
	public int getId() 
	{
		return MConf.get().getSuperMiningId();
	}

	@Override
	public boolean CanPlayerLearnSkill(MPlayer p) 
	{
		return true;
	}

}
