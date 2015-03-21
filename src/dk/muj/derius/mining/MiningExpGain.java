package dk.muj.derius.mining;

import java.util.Collection;
import java.util.Map;

import org.bukkit.Material;

import com.massivecraft.massivecore.util.MUtil;

import dk.muj.derius.api.BlockBreakExpGain;
import dk.muj.derius.api.skill.Skill;

public class MiningExpGain implements BlockBreakExpGain
{
	// -------------------------------------------- //
	// INSTANCE & CONSTRUCT
	// -------------------------------------------- //
   
	private static MiningExpGain i = new MiningExpGain();
	public static MiningExpGain get() { return i; }
	private MiningExpGain() {}

	// -------------------------------------------- //
	// OVERRIDE
	// -------------------------------------------- //
	
	@Override
	public Map<Material, Integer> getBlockTypes()
	{
		return MiningSkill.getExpGain();
	}
	
	@Override
	public Collection<Material> getToolTypes()
	{
		return MUtil.PICKAXE_MATERIALS;
	}
	
	@Override
	public Skill getSkill()
	{
		return MiningSkill.get();
	}

}
