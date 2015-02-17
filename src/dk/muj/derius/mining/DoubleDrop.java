package dk.muj.derius.mining;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import dk.muj.derius.api.DPlayer;
import dk.muj.derius.api.Skill;
import dk.muj.derius.entity.ability.DeriusAbility;
import dk.muj.derius.util.SkillUtil;

public class DoubleDrop extends DeriusAbility
{
	private static DoubleDrop i = new DoubleDrop();
	public static DoubleDrop get() { return i; }

	public DoubleDrop()
	{
		super.setDesc("Gives double drop");
		
		super.setName("Double Drop");
		
		super.setType(AbilityType.PASSIVE);
	}
	
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
	public String getLvlDescriptionMsg(int lvl)
	{
		return "Chance to double drop" + lvl/10.0 + "%";
	}

	@Override
	public Object onActivate(DPlayer dplayer, Object block)
	{
		if ( ! (block instanceof Block)) return null;
		
		Skill skill = MiningSkill.get();
		
		Block b = (Block) block;
		Material oreType = b.getType();
		Player p = dplayer.getPlayer();
		ItemStack inHand = p.getItemInHand();
		Location loc = b.getLocation();
		
		

		if (inHand.getItemMeta().hasEnchant(Enchantment.SILK_TOUCH)) return null;
		
		if (MiningSkill.getExpGain().containsKey(oreType) && 
				SkillUtil.shouldDoubleDropOccur(dplayer.getLvl(skill), 10))
		{
			for(ItemStack is: b.getDrops(inHand))
				b.getWorld().dropItem(loc, is);
		}
		
		return null;
	}

	@Override
	public void onDeactivate(DPlayer p, Object other)
	{
		//There is no deactivate for this thing
	}

}
