package dk.muj.derius.mining;

import java.util.Collection;
import java.util.Map;

import org.bukkit.Material;

import com.massivecraft.massivecore.util.MUtil;

import dk.muj.derius.api.ability.AbilityDurabilityMultiplier;
import dk.muj.derius.api.skill.Skill;

public class CarefulMining extends AbilityDurabilityMultiplier
{
	// -------------------------------------------- //
	// INSTANCE & CONSTRUCT
	// -------------------------------------------- //
   
	private static CarefulMining i = new CarefulMining();
	public static CarefulMining get() { return i; }
	private CarefulMining()
	{
		this.setName("Careful Mining");
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
		return "derius:mining:careful";
	}
	
	@Override
	public Collection<Material> getToolTypes()
	{
		return MUtil.PICKAXE_MATERIALS;
	}
	
	@Override
	public Map<Integer, Double> getDurabilityMultiplier()
	{
		return MiningSkill.getDurabilityMultiplier();
	}
	
	@Override
	public String getToolName()
	{
		return "Pickaxe";
	}

	
}
