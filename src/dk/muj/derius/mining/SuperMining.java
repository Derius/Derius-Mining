package dk.muj.derius.mining;

import com.massivecraft.massivecore.util.MUtil;

import dk.muj.derius.ability.Ability;
import dk.muj.derius.ability.AbilityType;
import dk.muj.derius.entity.MPlayer;
import dk.muj.derius.mining.entity.MConf;
import dk.muj.derius.skill.Skill;

public class SuperMining extends Ability
{
    private static SuperMining i = new SuperMining();
	public static SuperMining get() { return i; }
	
	public SuperMining()
	{
		super.setDescription("Mines faster");
		
		super.setName("Super Mining");
		
		this.addInteractKeys(MUtil.PICKAXE_MATERIALS);
		
		super.setType(AbilityType.ACTIVE);
		
	}
	
	@Override
	public Skill getSkill()
	{
		return MiningSkill.get();
	}
	
	@Override
	public int getId()
	{
		return MConf.get().getSuperMiningId();
	}

	@Override
	public String getLvlDescription(int lvl)
	{
		return "Lasts "+this.getTicksLast(lvl)/20 + " seconds";
	}

	@Override
	public boolean CanPlayerActivateAbility(MPlayer p)
	{
		return true;
	}
	
	@Override
	public int getCooldownTime(MPlayer p)
	{
		return super.getCooldownTime(p);
	}

	@Override
	public void onActivate(MPlayer p, Object other)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onDeactivate(MPlayer p)
	{
		// TODO Auto-generated method stub
	}



	

	

}
