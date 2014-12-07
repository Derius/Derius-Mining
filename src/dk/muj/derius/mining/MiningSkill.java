package dk.muj.derius.mining;

import java.util.ArrayList;
import java.util.List;

import dk.muj.derius.entity.MPlayer;
import dk.muj.derius.skill.Skill;

public class MiningSkill extends Skill
{
	public MiningSkill()
	{
		this.addEarnExpDesc("Mine ores");
		this.addPassiveAbilityDesc("Double drop", "Get twice as many ores");
		this.addActiveAbilityDesc("Super Mining", "Destroy blocks faster & 3x drop");
	}
	
	@Override
	public String getId() 
	{
		return Const.ID;
	}

	@Override
	public String getName() 
	{
		return Const.NAME;
	}

	@Override
	public String getDesc() 
	{
		return "Makes you better at mining";
	}

	@Override
	public boolean CanPlayerLearnSkill(MPlayer p) 
	{
		return true;
	}

	@Override
	public List<String> getAbilitiesDecriptionByLvl(int lvl) 
	{
		List<String> list = new ArrayList<String>();
		double doubleDropChance = lvl/10.0;
		/*if(doubleDropChance <= 0.0)
			doubleDropChance = 0.0;*/
		list.add("<i>Double drop: <yellow>"+doubleDropChance+"% chance to double drop");
		return list;
	}

}
