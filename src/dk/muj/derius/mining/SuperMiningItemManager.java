package dk.muj.derius.mining;

import java.util.Collection;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;

import com.massivecraft.massivecore.util.MUtil;

import dk.muj.derius.api.inventory.SpecialItemManagerEnchant;

public class SuperMiningItemManager extends SpecialItemManagerEnchant
{
	// -------------------------------------------- //
	// INSTANCE & CONSTRUCT
	// -------------------------------------------- //
	
	private static SuperMiningItemManager i = new SuperMiningItemManager();
	public static SuperMiningItemManager get() { return i; }
	private SuperMiningItemManager() { }

	// -------------------------------------------- //
	// OVERRIDE
	// -------------------------------------------- //
	
	@Override
	public Collection<Material> getToolTypes()
	{
		return MUtil.PICKAXE_MATERIALS;
	}
	
	@Override
	public String getLoreTag()
	{
		return SuperMining.ACTIVATED_LORE_TAG;
	}
	@Override
	public Enchantment getEnchantment()
	{
		return Enchantment.DIG_SPEED;
	}
	@Override
	public int getBuff()
	{
		return MiningSkill.getEfficiencyBuff();
	}

}
