package dk.muj.derius.mining;

import java.util.Collection;

import org.bukkit.Material;

import com.massivecraft.massivecore.util.MUtil;
import com.massivecraft.massivecore.util.Txt;

import dk.muj.derius.api.ability.AbilitySpecialItem;
import dk.muj.derius.api.inventory.SpecialItemManager;
import dk.muj.derius.api.skill.Skill;

public class SuperMining extends AbilitySpecialItem
{
	// -------------------------------------------- //
	// INSTANCE & CONSTRUCT
	// -------------------------------------------- //
	
	private static SuperMining i = new SuperMining();
	public static SuperMining get() { return i; }
	
	public SuperMining()
	{
		this.setDesc("Mines faster");
		
		this.setName("Super Mining");
	}
	
	// -------------------------------------------- //
	// CONSTANTS
	// -------------------------------------------- //
	
	public static final String ACTIVATED_LORE_TAG = Txt.parse("<lime>SuperMining Pickaxe");
	
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
		return "derius:mining:supermining";
	}

	@Override
	public SpecialItemManager getSpecialItemManager()
	{
		return SuperMiningItemManager.get();
	}

	@Override
	public Collection<Material> getToolTypes()
	{
		return MUtil.PICKAXE_MATERIALS;
	}

	@Override
	public Collection<Material> getBlockTypes()
	{
		return MiningSkill.getSuperMiningBlocks();
	}


}
