package dk.muj.derius.mining;

import java.util.Collection;

import org.bukkit.Material;

import dk.muj.derius.api.ability.AbilityDoubleDrop;
import dk.muj.derius.api.skill.Skill;

public class DoubleDrop extends AbilityDoubleDrop
{
	// -------------------------------------------- //
	// INSTANCE & CONSTRUCT
	// -------------------------------------------- //
	
	private static DoubleDrop i = new DoubleDrop();
	public static DoubleDrop get() { return i; }

	public DoubleDrop()
	{

	}
	
	// -------------------------------------------- //
	// OVERRIDE
	// -------------------------------------------- //
	
	@Override
	public Skill getSkill()
	{
		return MiningSkill.get();
	}
	
	@Override
	public String getId()
	{
		return "derius:mining:doubledrop";
	}

	@Override
	public Collection<Material> getBlockTypes()
	{
		return MiningSkill.getDoubleDropBlocks();
	}

	@Override
	public int getLevelsPerPercent()
	{
		return MiningSkill.getLevelsPerPercent();
	}

}
