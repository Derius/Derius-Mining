package dk.muj.derius.mining;

import java.util.Collection;

import org.bukkit.Material;

import com.massivecraft.massivecore.util.MUtil;

import dk.muj.derius.ability.Ability;
import dk.muj.derius.entity.MPlayer;
import dk.muj.derius.mining.entity.MConf;
import dk.muj.derius.skill.Skill;

public class SuperMining extends Ability
{

	@Override
	public int getId()
	{
		return MConf.get().getSkillId();
	}

	@Override
	public String getName()
	{
		return "Super Mining";
	}

	@Override
	public boolean CanPlayerActivateAbility(MPlayer p)
	{
		return true;
	}

	@Override
	public void onActivate(MPlayer p)
	{
	}

	@Override
	public void onDeactivate(MPlayer p)
	{
	}

	@Override
	public Collection<Material> getInteractKeys()
	{
		return MUtil.PICKAXE_MATERIALS;
	}

	@Override
	public Skill getSkill()
	{
		return DeriusMining.getMiningSkill();
	}

}
