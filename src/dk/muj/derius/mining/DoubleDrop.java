package dk.muj.derius.mining;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import dk.muj.derius.DeriusCore;
import dk.muj.derius.api.DPlayer;
import dk.muj.derius.api.Skill;
import dk.muj.derius.entity.ability.DeriusAbility;
import dk.muj.derius.util.SkillUtil;

public class DoubleDrop extends DeriusAbility
{
	// -------------------------------------------- //
	// INSTANCE & CONSTRUCT
	// -------------------------------------------- //
	
	private static DoubleDrop i = new DoubleDrop();
	public static DoubleDrop get() { return i; }

	public DoubleDrop()
	{
		super.setDesc("Gives double drop");
		
		super.setName("Double Drop");
		
		super.setType(AbilityType.PASSIVE);
	}
	
	// -------------------------------------------- //
	// OIVERRIDE
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
	public String getLvlDescriptionMsg(int lvl)
	{
		return "<g>Chance to double drop: <h>" + (double) lvl/MiningSkill.getLevelsPerPercent() + "%";
	}

	@Override
	public Object onActivate(DPlayer dplayer, Object obj)
	{
		if (dplayer == null) return null;
		if ( ! (obj instanceof Block)) return null;
		if ( ! dplayer.isPlayer()) return null;
		
		Skill skill = MiningSkill.get();
		
		Block block = (Block) obj;
		Material oreType = block.getType();
		Player player = dplayer.getPlayer();
		ItemStack inHand = player.getItemInHand();
		Location blockLoc = block.getLocation();
		
		

		if (inHand.getItemMeta().hasEnchant(Enchantment.SILK_TOUCH)) return null;
		if (DeriusCore.getBlockMixin().isBlockPlacedByPlayer(blockLoc)) return null;
		
		if (MiningSkill.getExpGain().containsKey(oreType) && SkillUtil.shouldDoubleDropOccur(dplayer.getLvl(skill), MiningSkill.getLevelsPerPercent()))
		{
			for(ItemStack item: block.getDrops(inHand))
			{
				block.getWorld().dropItem(blockLoc, item);
			}
		}
		
		return null;
	}

	@Override
	public void onDeactivate(DPlayer p, Object other)
	{
		//There is no deactivate for this thing
	}

}
